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
import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.entity.OperacionUnidad;
import com.clobi.transporte.entity.Unidad;
import com.clobi.transporte.facade.ADFacade;
import com.clobi.transporte.facade.EmpleadosFacade;
import com.clobi.transporte.facade.OperationFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Named(value = "ADController")
@SessionScoped
public class ActividadDiariaController implements Serializable {

    private boolean activityStatus;
    private ActividadDiaria actividad;
    private OperacionUnidad operacionSelected;
    //Aqui deberia ir el objeto donde esta relacionada la unidad con el motorista y el auxiliar 
    private Unidad unidadFornewOperation;
    private List<OperacionUnidad> listOperaciones;
    

    @EJB
    private ADFacade ejbADFacade;
    @EJB
    private EmpleadosFacade ejbEmpleadosFacade;
    @EJB
    private OperationFacade ejbOperacion;

    @PostConstruct
    public void init() {
        this.actividad = ejbADFacade.currentActivity();
        activityStatus = (actividad != null) ? true : false;
        if(activityStatus){
            this.listOperaciones = ejbOperacion.listOperaciones(this.actividad);
        }
        
    }

    

    public void prepareActivity() {
        Date currentDay = new Date();
        this.actividad = new ActividadDiaria();
        this.actividad.setTotalviajes(0);
        this.actividad.setIngresototal(new BigDecimal(0));
        this.actividad.setFecha(currentDay);
        this.actividad.setEstado(Enums.ESTADO_ACTIVIDAD.EJECUCION);

    }

    public void prepareOperation() {
        this.operacionSelected = new OperacionUnidad();
        this.operacionSelected.setEstado(Enums.ESTADO_ACTIVIDAD.EJECUCION);
        this.operacionSelected.setViajesrealizados((short) 1);
        this.operacionSelected.setIdactividaddiaria(this.actividad);

        //Aqui ira la selecion  
    }
    public void createOperation(){
        persistOperation(PersistAction.CREATE, "Operacion Exitosa");
    }
    
    public void persistOperation(PersistAction actionPersist, String infoResult) {
        //Este es un codigo auxiliar mientras se termina la logica del proceso 
        // 1. Selecionar: Unidad, 2 Empleados, un conductor y un auxiliar 
        // Id Unidad(length: 7) -> ABC145789      
        // Id Unidad(length: 7) -> 456789
        // Id Unidad(length: 7) -> 987456
        try {
            Unidad u = ejbADFacade.obtenerUnidadAux("ABC1457");
            Empleado e1 = ejbEmpleadosFacade.find("345666");
            Empleado e2 = ejbEmpleadosFacade.find("345566");
            this.operacionSelected.setPlaca(u);
            this.operacionSelected.setIdconductor(e1);
            this.operacionSelected.setIdauxiliar(e2);
            if(actionPersist != PersistAction.DELETE){
                ejbOperacion.edit(operacionSelected);
            }
            JsfUtil.addSuccessMessage("Se Inserto con exito");
            this.listOperaciones = ejbOperacion.listOperaciones(this.actividad);
        } catch (Exception e) {
            System.out.print("Error..");
            JsfUtil.addErrorMessage("Error al registrar Operacion");
        }
    }

    public void createActivity() {
        prepareActivity();
        persistActivity(PersistAction.CREATE, "Actividad Insertada");
    }

    public boolean isActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    public OperacionUnidad getOperacionSelected() {
        return operacionSelected;
    }

    public void setOperacionSelected(OperacionUnidad operacionSelected) {
        this.operacionSelected = null;
        this.operacionSelected = operacionSelected;
        System.out.print("Entro y se asigno, Valor: " + this.operacionSelected.getPlaca().getPlaca());
    }
    
    public List<OperacionUnidad> getListOperaciones() {
        return listOperaciones;
    }

    public void setListOperaciones(List<OperacionUnidad> listOperaciones) {
        this.listOperaciones = listOperaciones;
    }
    
    

    private void persistActivity(PersistAction actionPersist, String infoResult) {
        try {
            if (actionPersist != PersistAction.DELETE) {
                this.actividad = ejbADFacade.edit(this.actividad);
            }
            JsfUtil.addSuccessMessage(infoResult);
            activityStatus = true;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error en la Operacion 2");
        }
    }

    
    
    
    
}
