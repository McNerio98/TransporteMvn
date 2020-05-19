/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.entity.OperacionUnidad;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tinkpad PC
 */
@Stateless
public class OperationFacade extends AbstractFacade<OperacionUnidad> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(OperacionUnidad.class);
    }

    public List<OperacionUnidad> listOperaciones(ActividadDiaria actividad) {
        List<OperacionUnidad> tmp;

        Query query = getEntityManager().createNamedQuery("OperacionUnidad.findByActividad", OperacionUnidad.class);
        query.setParameter("actividad", actividad);
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<OperacionUnidad>() : tmp;
    }
/*
    public Integer ContadorAnterior(String placa, Date todayActivity) { // Esta se eliminara 
        List<OperacionUnidad> tmp;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

        String sql = "select * from operacionesunidades o where o.idactividaddiaria = \n"
                + "(select a.id from actividadesdiarias a where \n"
                + "a.fecha = date('" + formater.format(todayActivity) + "')-1) and o.placa = '" + placa + "'";
        Query query = getEntityManager().createNativeQuery(sql, OperacionUnidad.class);
        tmp = query.getResultList();

        return tmp.isEmpty() ? 0 : tmp.get(0).getContador();
    }
*/

    public OperacionUnidad registroAnterior(String placa, Date actual) {
        List<OperacionUnidad> tmp;
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "select * from operacionesunidades o2 where o2.placa = ? and o2.idactividaddiaria = \n"
                + "(select a2.id from actividadesdiarias a2 where a2.fecha = date(?)-1)";
        System.out.print("Esta es la query "+sql);
        Query query = getEntityManager().createNativeQuery(sql,OperacionUnidad.class);
        query.setParameter(1, placa);
        query.setParameter(2, formateador.format(actual));
        tmp = query.getResultList();
        return tmp.isEmpty() ? null:tmp.get(0);
    }

}
