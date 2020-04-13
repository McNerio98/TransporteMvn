/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.entity.ActividadFinanciera;
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
public class FinanzasFacade extends AbstractFacade<ActividadFinanciera> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinanzasFacade() {
        super(ActividadFinanciera.class);
    }

    //Busca los gastos en relacion a la activiad diaria 
    public List<ActividadFinanciera> updateListFinanzas(ActividadDiaria ad) {
        List<ActividadFinanciera> tmp;
        Query query = getEntityManager().createNamedQuery("ActividadFinanciera.actividaddiaria", ActividadFinanciera.class);
        query.setParameter("ad", ad);
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<ActividadFinanciera>() : tmp;
    }

}
