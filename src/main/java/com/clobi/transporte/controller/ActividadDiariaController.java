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
import com.clobi.transporte.entity.Anticipo;
import com.clobi.transporte.entity.Asignacion;
import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.entity.OperacionUnidad;
import com.clobi.transporte.entity.Unidad;
import com.clobi.transporte.facade.ADFacade;
import com.clobi.transporte.facade.AnticiposFacade;
import com.clobi.transporte.facade.AsignacionFacade;
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
    private ActividadDiaria actividad; //Es la actividad Actual 
    private OperacionUnidad operacionSelected;
    private List<OperacionUnidad> listOperaciones;
    private Anticipo anticipoInsert;
    private BigDecimal totalAnticipos;
    private List<Asignacion> listAsignaciones;
    private Asignacion asignacionSelected;

    @EJB
    private ADFacade ejbADFacade;
    @EJB
    private EmpleadosFacade ejbEmpleadosFacade;
    @EJB
    private OperationFacade ejbOperacion;
    @EJB
    private AnticiposFacade ejbAnticipos;
    @EJB
    private AsignacionFacade ebjAsignacion;

    @PostConstruct
    public void init() {
        this.actividad = ejbADFacade.currentActivity();
        activityStatus = (actividad != null) ? true : false;
        if (activityStatus) {
            this.listOperaciones = ejbOperacion.listOperaciones(this.actividad);
        }

    }

    public void showAdvance(OperacionUnidad e) {
        this.operacionSelected = e;
        this.anticipoInsert = null;
        this.totalAnticipos = ejbAnticipos.totalAnticipos(e);
    }

    public void showDetailsEnd(OperacionUnidad e) {
        this.operacionSelected = e;
    }

    public void prepareActivity() {
        Date currentDay = new Date();
        this.actividad = new ActividadDiaria();
        this.actividad.setTotalviajes(0);
        this.actividad.setIngresototal(new BigDecimal(0.0));
        this.actividad.setFecha(currentDay);
        this.actividad.setEstado(Enums.ESTADO_ACTIVIDAD.EJECUCION);

    }

    public void prepareOperation() {
        this.operacionSelected = new OperacionUnidad();
        this.operacionSelected.setEstado(Enums.ESTADO_ACTIVIDAD.EJECUCION);
        this.operacionSelected.setViajesrealizados((short) 1);
        this.operacionSelected.setContador(0);
        this.operacionSelected.setIdactividaddiaria(this.actividad);
        this.operacionSelected.setPagoconductor(new BigDecimal(0.0));
        this.operacionSelected.setPagoauxiliar(new BigDecimal(0.0));
        this.operacionSelected.setIngreso(new BigDecimal(0.0));
        this.listAsignaciones = ebjAsignacion.findAll();
    }

    public void prepareAnticipo() {
        this.anticipoInsert = new Anticipo();
    }

    public void createAnticipo() {
        this.anticipoInsert.setHora(new Date());
        this.anticipoInsert.setIdoperacion(operacionSelected);
        persistAnticipo(PersistAction.CREATE, "Anticipo Registrado!");
    }

    public void addNewViaje() {
        short n = this.operacionSelected.getViajesrealizados();
        n++;
        this.operacionSelected.setViajesrealizados(n);
        persistOperation(PersistAction.UPDATE, "Viaje Registrado");
    }

    public void createOperation() {
        this.operacionSelected.setPlaca(this.asignacionSelected.getUnidad());
        this.operacionSelected.setIdconductor(this.asignacionSelected.getMotorista());
        if (!this.asignacionSelected.getAyudante().getDui().equals("")) {
            this.operacionSelected.setIdauxiliar(this.asignacionSelected.getAyudante());
        }
        persistOperation(PersistAction.CREATE, "Operacion Exitosa");
    }

    public void persistOperation(PersistAction actionPersist, String infoResult) {
        try {
            if (actionPersist != PersistAction.DELETE) {
                ejbOperacion.edit(operacionSelected);
            }
            JsfUtil.addSuccessMessage(infoResult);
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

    private void persistAnticipo(PersistAction actionPersist, String infoResult) {
        try {
            if (actionPersist != PersistAction.DELETE) {
                ejbAnticipos.edit(anticipoInsert);
            }
            JsfUtil.addSuccessMessage(infoResult);
            //Preguntar como recargar solo el componente que se altero en lugar de toda la lista 
            this.listOperaciones = ejbOperacion.listOperaciones(this.actividad);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error en la Operacion 2");
        }
    }

    public Anticipo getAnticipoInsert() {
        return anticipoInsert;
    }

    public void setAnticipoInsert(Anticipo anticipoInsert) {
        this.anticipoInsert = anticipoInsert;
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
        this.operacionSelected = operacionSelected;
    }

    public List<OperacionUnidad> getListOperaciones() {
        return listOperaciones;
    }

    public void setListOperaciones(List<OperacionUnidad> listOperaciones) {
        this.listOperaciones = listOperaciones;
    }

    public BigDecimal getTotalAnticipos() {
        return totalAnticipos;
    }

    public void setTotalAnticipos(BigDecimal totalAnticipos) {
        this.totalAnticipos = totalAnticipos;
    }

    public List<Asignacion> getListAsignaciones() {
        return listAsignaciones;
    }

    public void setListAsignaciones(List<Asignacion> listAsignaciones) {
        this.listAsignaciones = listAsignaciones;
    }

    public Asignacion getAsignacionSelected() {
        return asignacionSelected;
    }

    public void setAsignacionSelected(Asignacion asignacionSelected) {
        this.asignacionSelected = asignacionSelected;
    }

}
