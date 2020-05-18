/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.ADRegistrosPOJO;
import com.clobi.transporte.facade.ADFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Tinkpad PC
 */
@Named(value = "ADRegistros")
@SessionScoped
public class ADRegistrosController implements Serializable{
    private List<ADRegistrosPOJO> listData;

    @EJB
    private ADFacade ejbADFacade;
    
    @PostConstruct
    public void init(){
        List<ADRegistrosPOJO> aux = new ArrayList<ADRegistrosPOJO>();
        aux = ejbADFacade.listADRegistros();
        this.listData = aux;
    }
    
    public List<ADRegistrosPOJO> getListData() {
        return listData;
    }

    public void setListData(List<ADRegistrosPOJO> listData) {
        this.listData = listData;
    }


    
    
}
