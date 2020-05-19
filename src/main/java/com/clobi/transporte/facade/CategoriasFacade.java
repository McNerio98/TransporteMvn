/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.facade;


import com.clobi.transporte.entity.Categoria;
import com.clobi.transporte.entity.OperacionUnidad;
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
public class CategoriasFacade extends AbstractFacade<Categoria>{
    @PersistenceContext(unitName = "transportePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Categoria> listCategoriasAsc(){
        String sql = "select * from categorias order by id asc";
        Query query = getEntityManager().createNativeQuery(sql,Categoria.class);
        List<Categoria> tmp= query.getResultList();
        return tmp;
    }
    
    public CategoriasFacade(){
        super(Categoria.class);
    }    

}
