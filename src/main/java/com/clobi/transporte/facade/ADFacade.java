/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.controller.util.ADRegistrosPOJO;
import com.clobi.transporte.controller.util.MyPOJO;
import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.entity.DocumentoByEmpleado;
import com.clobi.transporte.entity.Unidad;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tinkpad PC
 */
@Stateless
public class ADFacade extends AbstractFacade<ActividadDiaria> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ADFacade() {
        super(ActividadDiaria.class);
    }

    public ActividadDiaria currentActivity() {
        ActividadDiaria ad = null;
        Query sql = getEntityManager().createNamedQuery("ActividadDiaria.findByFecha", ActividadDiaria.class);
        sql.setParameter("fecha", new Date());
        List<ActividadDiaria> tmp = sql.getResultList();
        return (tmp.isEmpty() ? ad : tmp.get(0));
    }

    public Integer todayRecords() {
        Integer c = -1;
        List<Integer> tmp = getEntityManager().createNamedQuery("ActividadDiaria.countToday").getResultList();
        return (tmp.isEmpty() ? c : tmp.get(0));
    }

    public Unidad obtenerUnidadAux(String id) {
        Unidad u = null;
        u = getEntityManager().find(Unidad.class, id);
        return u;
    }

    public List<ADRegistrosPOJO> listADRegistros() {
        List<ADRegistrosPOJO> ads = new ArrayList<ADRegistrosPOJO>();

        String sql = "select a2.id, \n"
                + "case when a2.estado = 1 then 'Ejecucion' when a2.estado = 2 then 'Finalizada' end as tipoestado,\n"
                + "a2.totalviajes, a2.ingresototal, to_char(a2.fecha,'dd/mm/yyyy') as fechareg,\n"
                + "(select count(*) from operacionesunidades o3 where o3.autocierre = false and idactividaddiaria = a2.id) as uniactivas,\n"
                + "((select coalesce((sum(p.monto)),0) as aumento from pagos p where p.idoperacion in(\n"
                + "select o.id from operacionesunidades o where o.idactividaddiaria = a2.id and o.autocierre = false\n"
                + "))+(select coalesce((sum(ex.monto)),0) as aumento from extrapay ex where ex.idoperacion in(\n"
                + "select o.id from operacionesunidades o where o.idactividaddiaria = a2.id and o.autocierre = false\n"
                + "))) as totalpagos\n"
                + "from actividadesdiarias a2";

        try {
            Query query = getEntityManager().createNativeQuery(sql, "ADMapping");
            ads = (List<ADRegistrosPOJO>) query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        return ads.isEmpty() ? new ArrayList<ADRegistrosPOJO>() : ads;
    }

    public ADRegistrosPOJO ADRegistroActual(int id) {
        List<ADRegistrosPOJO> tmp;
        String sql = "select a2.id, \n"
                + "case when a2.estado = 1 then 'Ejecucion' when a2.estado = 2 then 'Finalizada' end as tipoestado,\n"
                + "(select sum(o.viajesrealizados) from operacionesunidades o where o.idactividaddiaria = a2.id) as totalviajes,\n"
                + "(select sum(o2.ingreso) from operacionesunidades o2 where o2.idactividaddiaria = a2.id) as ingresototal,\n"
                + "to_char(a2.fecha,'dd/mm/yyyy') as fechareg,\n"
                + "(select count(*) from operacionesunidades o3 where o3.autocierre = false and idactividaddiaria = a2.id) as uniactivas,\n"
                + "((select coalesce((sum(p.monto)),0) as aumento from pagos p where p.idoperacion in(\n"
                + "select o.id from operacionesunidades o where o.idactividaddiaria = a2.id and o.autocierre = false\n"
                + "))+(select coalesce((sum(ex.monto)),0) as aumento from extrapay ex where ex.idoperacion in(\n"
                + "select o.id from operacionesunidades o where o.idactividaddiaria = a2.id and o.autocierre = false\n"
                + "))) as totalpagos\n"
                + "from actividadesdiarias a2 where a2.id = ?";
        
        Query query = getEntityManager().createNativeQuery(sql,"ADMapping");
        query.setParameter(1, id);
        tmp = query.getResultList();
        
        return tmp.isEmpty()?null:tmp.get(0);
    }
}
