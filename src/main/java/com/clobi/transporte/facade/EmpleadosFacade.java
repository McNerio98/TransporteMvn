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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author desarrollo
 */
@Stateless
public class EmpleadosFacade extends AbstractFacade<Empleado>{
    
    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    //private List<String> tiposEmpleados;
    
    public EmpleadosFacade(){
        super(Empleado.class);
       // InitializeComponents();
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
