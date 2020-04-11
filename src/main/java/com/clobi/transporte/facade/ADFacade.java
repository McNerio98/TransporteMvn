/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.entity.DocumentoByEmpleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tinkpad PC
 */
@Stateless
public class ADFacade extends AbstractFacade<ActividadDiaria>{
    
    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ADFacade(){
        super(ActividadDiaria.class);
    }
    
    public Integer todayRecords(){
        Integer cantidadRecords = -1;
        try{ 
            cantidadRecords = (int)((long)getEntityManager().createNamedQuery("ActividadDiaria.countToday").getSingleResult());
        }catch(Exception e){
            System.out.print("Error al obtener el total de registros ");
        }
        return cantidadRecords;
    }
}
