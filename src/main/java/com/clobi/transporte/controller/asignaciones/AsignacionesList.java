/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.asignaciones;

import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.entity.Asignacion;
import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.facade.AsignacionFacade;
import com.clobi.transporte.facade.EmpleadosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ALEX
 */
@Named(value = "asignacionesList")
@SessionScoped
public class AsignacionesList implements Serializable {
    
    private Asignacion selected;
    private List<Asignacion> list;
    @EJB
    private AsignacionFacade ejbAsignacion;
    @EJB
    private EmpleadosFacade ejbEmpleado;
    
    public AsignacionesList() {
        this.selected = new Asignacion();
        this.list = new ArrayList<>();
        this.ejbAsignacion = new AsignacionFacade();
        this.ejbEmpleado = new EmpleadosFacade();
    }
    
    private AsignacionFacade getFacade(){
        return this.ejbAsignacion;
    }
    
    private EmpleadosFacade getEmpleadoFacade(){
        return this.ejbEmpleado;
    }
    
    @PostConstruct
    public void init(){
        this.list = new ArrayList<>();
        this.setAsignacionesList(ejbAsignacion.findAll());
        
    }
    
    public void rowSelected(SelectEvent event) {
        System.out.println("Asignado a " + selected.getMotorista());
        this.setAsignacion(selected);
    }
    
    public void eliminarAsignacion(){
        persist(JsfUtil.PersistAction.DELETE,"Accion exitosa");
    }
    
    public void guardarCambios(){
        persist(JsfUtil.PersistAction.UPDATE, "Se han guardado los cambios");
    }
    
    public List<Empleado> getListEmpleado(int o){
        if(o == 1){
            return getEmpleadoFacade().getEmpleadosByTipo("mot");
        }else{
            return getEmpleadoFacade().getEmpleadosByTipo("ayu");
        }
    }
    
    public void setAsignacionesList(List<Asignacion> l){
        this.list = l;
    }
    
    public List<Asignacion> getAsignacionesList(){
        return this.list;
    }
    
    public List<Asignacion> listAsignaciones(){
        return getFacade().findAll();
    }
    
    public Asignacion getAsignacion(){
        return this.selected;
    }
    
    public void setAsignacion(Asignacion s){
        this.selected = s;
        System.out.print(this.selected.getAyudante().getDui());
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formConfig:tablaM");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formConfig:tablaA");
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
