/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.entity.OperacionUnidad;
import java.util.ArrayList;
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
    
    public OperationFacade(){
        super(OperacionUnidad.class);
    }
    
    public List<OperacionUnidad> listOperaciones(ActividadDiaria actividad){
        List<OperacionUnidad> tmp;
        
        Query query = getEntityManager().createNamedQuery("OperacionUnidad.findByActividad", OperacionUnidad.class);
        query.setParameter("actividad", actividad);
        tmp = query.getResultList();
        
        return tmp.isEmpty() ? new ArrayList<OperacionUnidad>() : tmp;
    }
    
    
}

