/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

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
        String sql = "select e.dui , e.nombres, e.apellidos, e.fechanacimiento, e.estadocivil, \n"
                + "e.telefono, e.tipo \n"
                + "from empleados e, asignaciones a \n"
                + "where a.motorista != e.dui \n"
                + "and a.ayudante != e.dui\n"
                + "and e.tipo =  ?";
        Query query = em.createNativeQuery(sql, Empleado.class);
        query.setParameter(1, tipo);
        List<Object[]> listo = query.getResultList();
        System.out.print(listo);
        list = query.getResultList();
        if(list.isEmpty()){
            Query q = em.createNamedQuery("Empleado.findByTipo");
            q.setParameter("tipo", tipo);
            list = q.getResultList();
        }
        return list;
    }

    public List<Empleado> getEmpleadosByTipo(String tipo) {
        List<Empleado> list;
        Query query = getEntityManager().createNamedQuery("Empleado.findByTipo");
        query.setParameter("tipo", tipo);
        list = query.getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    /*
    private void InitializeComponents(){
        this.tiposEmpleados = new ArrayList<String>();
        this.tiposEmpleados.add("Empleado Fijo");
        this.tiposEmpleados.add("Empleado por dia");
    }
     */
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
