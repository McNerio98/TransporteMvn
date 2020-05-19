/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.controller.util.Enums;
import com.clobi.transporte.entity.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author desarrollo
 */
@Stateless
public class EmpleadosFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //private List<String> tiposEmpleados;
    public EmpleadosFacade() {
        super(Empleado.class);
        // InitializeComponents();
    }

    public List<Empleado> getEmpleados(String tipo) {
        List<Empleado> list;
        String sql = "";
        if (tipo.equals("mot")) {
            sql = "select e.dui , e.nombres, e.apellidos, e.fechanacimiento, e.estadocivil, \n"
                    + "e.telefono, e.tipo \n"
                    + "from empleados e\n"
                    + "where e.tipo = ? and e.dui not in (\n"
                    + "    SELECT DISTINCT p.motorista FROM asignaciones p\n"
                    + ");";
        } else {
            sql = "select e.dui , e.nombres, e.apellidos, e.fechanacimiento, e.estadocivil, \n" +
"                    e.telefono, e.tipo \n" +
"                    from empleados e\n" +
"                    where e.tipo = 'ayu' and e.dui not in (\n" +
"                      SELECT DISTINCT p.ayudante FROM asignaciones p\n" +
"                      where p.ayudante != ''\n" +
"                    );";
        }
        Query query = em.createNativeQuery(sql, Empleado.class);
        query.setParameter(1, tipo);
        List<Object[]> listo = query.getResultList();
        System.out.print(listo);
        list = query.getResultList();
        return list;
    }

    public List<Empleado> getEmpleadosByTipo(String tipo) {
        List<Empleado> list;
        Query query = getEntityManager().createNamedQuery("Empleado.findByTipo");
        query.setParameter("tipo", tipo);
        list = query.getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    public List<Empleado> EmpleadosDisponibles(String tipo, Integer idAD) {
        List<Empleado> tmp;
        String sql = "";
        if (tipo.equals(Enums.TIPO_EMPLEADO.AYUDANTE)) {
            sql = "select * from empleados e where e.tipo=? and e.dui not in(select o.idauxiliar \n"
                    + "from operacionesunidades o where idactividaddiaria = ?)";
        } else {
            sql = "select * from empleados e where e.tipo=? and e.dui not in(select o.idconductor \n"
                    + "from operacionesunidades o where idactividaddiaria = ?)";
        }
        Query query = getEntityManager().createNativeQuery(sql, Empleado.class);
        query.setParameter(1, tipo);
        query.setParameter(2, idAD);
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<Empleado>() : tmp;
    }

    public List<Empleado> ALL_EMPLADOS(){
        List<Empleado> tmp;
        Query query = getEntityManager().createNamedQuery("Empleado.findAll");
        query.setParameter("r_estado", true);
        tmp = query.getResultList();
        
        return tmp.isEmpty()? new ArrayList<Empleado>():tmp;
    }
}
