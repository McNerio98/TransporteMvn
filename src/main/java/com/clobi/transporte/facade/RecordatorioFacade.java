/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;

import com.clobi.transporte.entity.Recordatorio;
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
public class RecordatorioFacade extends AbstractFacade<Recordatorio> {

    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecordatorioFacade() {
        super(Recordatorio.class);
    }

    public List<Recordatorio> getToday() {
        List<Recordatorio> list;
        String sql = "select * from recordatorios where fechacreacion::date = now()::date;";
        Query query = em.createNativeQuery(sql, Recordatorio.class);
        list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    public void insertRecordatorio(Recordatorio r, String f) {
        String sql = "insert into recordatorios(titulo,descripcion,fechacreacion,estado) values (?,?,?,?)";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, r.getTitulo());
        query.setParameter(2, r.getDescripcion());
        Date date1 = new Date();
        try{
             date1 = new SimpleDateFormat("yyyy-MM-dd").parse(f);
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        System.out.print("FECHA A GUARDAR:" + date1);
        query.setParameter(3, date1);
        query.setParameter(4, 0);
        query.executeUpdate();
    }

}
