/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.DocumentoByUnidad;
import com.clobi.transporte.entity.Unidad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ALEX
 */
@Stateless
public class UnidadFacade extends AbstractFacade<Unidad> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnidadFacade() {
        super(Unidad.class);
    }

    public List<Unidad> getUnidadesNotAsignadas() {
        List<Unidad> list;
        String sql = "select u.placa, u.numerounidad, u.modelo, u.ye_ar, u.motor, u.chasis,\n"
                + "u.marca, u.fechavencimiento\n"
                + "from asignaciones a, unidades u \n"
                + "where a.unidad != u.placa;";
        Query query = em.createNativeQuery(sql, Unidad.class);
        List<Object[]> listo = query.getResultList();
        System.out.print(listo);
        list = query.getResultList();
        if(list.isEmpty()){
            list = em.createNamedQuery("Unidad.findAll").getResultList();
        }
        return list;
    }

    public List<DocumentoByUnidad> getDocsByPlaca(Unidad u) {
        List<DocumentoByUnidad> list;
        Query query = getEntityManager().createNamedQuery("Unidad.getDocsByPlaca");
        query.setParameter("placa", u.getPlaca());
        list = query.getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

}
