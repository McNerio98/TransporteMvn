/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.Asignacion;
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
public class AsignacionFacade extends AbstractFacade<Asignacion> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionFacade() {
        super(Asignacion.class);
    }
    
    
    public List<Asignacion> unidadesDisponibles(Integer c){
        //Devuelve las unidades que aun no estan en la asignacion
        List<Asignacion> tmp;
        String sql = "select * from asignaciones a where a.unidad not \n"
                + "in(select o.placa from operacionesunidades o where idactividaddiaria = "+ c +")";
        System.err.println("El query ejecutado es:" + sql);
        Query query = getEntityManager().createNativeQuery(sql,Asignacion.class);
        tmp = query.getResultList();
        return tmp.isEmpty()?new ArrayList<Asignacion>():tmp;
    }

    
}
