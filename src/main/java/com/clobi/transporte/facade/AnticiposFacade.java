/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.Anticipo;
import com.clobi.transporte.entity.OperacionUnidad;
import java.math.BigDecimal;
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
public class AnticiposFacade extends AbstractFacade<Anticipo> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //private List<String> tiposEmpleados;
    public AnticiposFacade() {
        super(Anticipo.class);
        // InitializeComponents();
    }

    public BigDecimal totalAnticipos(OperacionUnidad o) {
        BigDecimal sum = BigDecimal.ZERO;
        Query query = getEntityManager().createNamedQuery("Anticipo.sumAnticipos");
        query.setParameter("idoperacion", o);
        List<BigDecimal> tmp = query.getResultList();
        return (tmp.isEmpty())?sum:tmp.get(0);
    }
    

}
