/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.PagoExtra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tinkpad PC
 */
@Stateless
public class PagoExtraFacade extends AbstractFacade<PagoExtra> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    public PagoExtraFacade() {
        super(PagoExtra.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
