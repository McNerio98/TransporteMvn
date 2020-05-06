/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.entity.Recordatorio;
import com.clobi.transporte.facade.RecordatorioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author ALEX
 */
@Named(value = "recordatior")
@SessionScoped
public class RecordatiorioController implements Serializable {

    private List<Recordatorio> list;
    private Recordatorio selected;
    private String datefrominput;
    @EJB
    private RecordatorioFacade ejbFacade;

    public RecordatiorioController() {
        this.ejbFacade = new RecordatorioFacade();
        this.selected = new Recordatorio();
    }
    
    public void setDateInput(String i){
        this.datefrominput = i;
    }
    
    public String getDateInput(){
        return this.datefrominput;
    }
    
    @PostConstruct
    public void init(){
        this.selected = new Recordatorio();
        this.selected.setFechacreacion(new Date());
        this.setList(this.ejbFacade.findAll());
    }

    private RecordatorioFacade getFacade() {
        return this.ejbFacade;
    }
    
    public void delete(Recordatorio s){
        this.selected = s;
        persist(JsfUtil.PersistAction.DELETE, "Se borro exitosamente");
    }

    public void setRecordatorio(Recordatorio r) {
        this.selected = r;
    }

    public Recordatorio getRecordatorio() {
        return this.selected;
    }
    
    public void setList(List<Recordatorio> l){
        this.list = l;
    }
    
    public List<Recordatorio> getRecordatorioList(){
        return this.list;
    }
    
    public List<Recordatorio> getTodayRecordatorios(){
        return getFacade().getToday();
    }

    public void save() {
        /*Date date1 = new Date();
        try{
             date1 = new SimpleDateFormat("yyyy-mm-dd").parse(this.getDateInput());
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        this.selected.setFechacreacion(date1);
        this.selected.setDescripcion("Descripcion");
        System.out.print("FECHA1:: "+this.datefrominput);
        System.out.print("FECHA:: "+this.selected.getFechacreacion());
        
        persist(JsfUtil.PersistAction.CREATE, "Recordatorio con exito");*/
        this.selected.setDescripcion("Descripcion");
        getFacade().insertRecordatorio(selected, datefrominput);
        this.init();
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                    this.selected = null;
                    
                } else {
                    getFacade().remove(selected);

                }
                this.init();
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ex.getMessage());
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ex.getMessage());
            }
        }
    }

}
