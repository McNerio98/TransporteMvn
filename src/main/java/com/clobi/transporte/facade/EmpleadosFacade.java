/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.Empleado;
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
    
    public EmpleadosFacade(){
        super(Empleado.class);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
