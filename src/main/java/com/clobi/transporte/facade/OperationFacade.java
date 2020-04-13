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
    
    public Integer ContadorAnterior(String placa, Date todayActivity){
        Integer contador = 0;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "SELECT o.contador FROM operacionesunidades o \n"
                +"WHERE placa = '"+placa+"' and o.idactividaddiaria = \n" 
                +"(SELECT a.id FROM actividadesdiarias a \n"
                +"WHERE a.fecha = date('"+formater.format(todayActivity)+"')-1)";
        
        try{
            Query query = getEntityManager().createNativeQuery(sql);
            contador = (Integer)query.getSingleResult();
        }catch(Exception e){
            System.out.print("Error..");
        }
        
        return contador;
    }
    
    
}

