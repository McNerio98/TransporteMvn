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
}
