/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.Enums;
import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.controller.util.JsfUtil.PersistAction;
import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.facade.ADFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Tinkpad PC
 */
@Named(value = "ADController")
@SessionScoped
public class ActividadDiariaController implements Serializable{
    private boolean activityStatus;
    private ActividadDiaria actividad;
    //private Operacion
    @EJB
    private ADFacade ejbADFacade;
    
    
    @PostConstruct
    public void init(){
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        activityStatus = (ejbADFacade.todayRecords()>0)?true:false;
    }
    
    public void prepareActivity(){
        Date currentDay = new Date();
        this.actividad = new ActividadDiaria();
        this.actividad.setTotalviajes(0);
        this.actividad.setIngresototal(new BigDecimal(0));
        this.actividad.setFecha(currentDay);
        this.actividad.setEstado(Enums.ESTADO_ACTIVIDAD.EJECUCION);    
    }
    
    public void prepareOperation(){

    }
    
    public void createActivity(){
        prepareActivity();
        persistActivity(PersistAction.CREATE, "Actividad Insertada");   
    }

    public boolean isActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    private void persistActivity(PersistAction actionPersist, String infoResult){
        try{
            if(actionPersist != PersistAction.DELETE){
                ejbADFacade.edit(this.actividad);
            }
            JsfUtil.addSuccessMessage(infoResult);
            activityStatus = true;
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error en la Operacion 2");
        }    
    }
}
