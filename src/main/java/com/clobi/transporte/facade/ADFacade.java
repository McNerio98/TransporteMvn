/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.entity.DocumentoByEmpleado;
import com.clobi.transporte.entity.Unidad;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        try {
            Query sql = getEntityManager().createNamedQuery("ActividadDiaria.findByFecha", ActividadDiaria.class);
            sql.setParameter("fecha", new Date());
            ad = (ActividadDiaria) sql.getSingleResult();
        } catch (Exception e) {
            System.out.print("Error o no se encontro ningun resultado");
        }
        return ad;
    }

    public Integer todayRecords() {
        Integer cantidadRecords = -1;
        try {
            cantidadRecords = (int) ((long) getEntityManager().createNamedQuery("ActividadDiaria.countToday").getSingleResult());
        } catch (Exception e) {
            System.out.print("Error al obtener el total de registros ");
        }
        return cantidadRecords;
    }
    
    //Metodos Auxiliares 
    public Unidad obtenerUnidadAux(String id){
        Unidad u = null;
        try{
            u = getEntityManager().find(Unidad.class, id);
        }catch(Exception e){
            System.out.print("Error al encontrar unidad");
        }
        return u;
    }
}
