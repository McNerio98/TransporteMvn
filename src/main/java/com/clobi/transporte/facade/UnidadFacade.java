/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.DocumentoByUnidad;
import com.clobi.transporte.entity.Unidad;
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
                + "from unidades u \n"
                + "where u.placa not in (\n"
                + "    SELECT DISTINCT p.unidad FROM asignaciones p\n"
                + ");";
        Query query = em.createNativeQuery(sql, Unidad.class);
        List<Object[]> listo = query.getResultList();
        System.out.print(listo);
        list = query.getResultList();
        return list;
    }
    
    public void setUnidadStatus(Unidad u){
//        Query query = getEntityManager().createNamedQuery("Unidad.ChangeEstadoregistro", Unidad.class);
//        query.setParameter("placa", u.getPlaca());
//        int i = query.executeUpdate();
          String sql = "update unidades set estadoregistro = :estado"
                  + "where placa = :placa";
          Query query = em.createNativeQuery(sql,Unidad.class);
          query.setParameter("estado", false);
          query.setParameter("placa", u.getPlaca());
          query.executeUpdate();
    }

    public List<DocumentoByUnidad> getDocsByPlaca(Unidad u) {
        List<DocumentoByUnidad> list;
        Query query = getEntityManager().createNamedQuery("Unidad.getDocsByPlaca");
        query.setParameter("placa", u.getPlaca());
        list = query.getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }
    
    public List<Unidad> getUnidadesByStatus(boolean status){
        List<Unidad> list;
        Query query = getEntityManager().createNamedQuery("Unidad.findByEstadoregistro");
        query.setParameter("estadoregistro", status);
        list = query.getResultList();
        return list.isEmpty() ? new ArrayList<>() : list;
    }

    public boolean insertUnidad(Unidad u, String f){
        String sql = "insert into unidades(placa,numerounidad,modelo,ye_ar,motor,chasis,marca,fechavencimiento,estadoregistro) values "
                + "(?,?,?,?,?,?,?,?,?)";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, u.getPlaca());
        query.setParameter(2, u.getNumerounidad());
        query.setParameter(3, u.getModelo());
        query.setParameter(4, u.getYeAr());
        query.setParameter(5, u.getMotor());
        query.setParameter(6, u.getChasis());
        query.setParameter(7, u.getMarca());
        Date date1 = new Date();
        try{
             date1 = new SimpleDateFormat("yyyy-MM-dd").parse(f);
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        query.setParameter(8,date1);
        query.setParameter(9, u.getEstadoregistro());
        int val = query.executeUpdate();
        return val > 0 ? true : false;
    }
}
