/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.asignaciones;

import com.clobi.transporte.controller.Contrato.EmpleadoContrato;
import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.entity.Asignacion;
import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.entity.Unidad;
import com.clobi.transporte.facade.AsignacionFacade;
import com.clobi.transporte.facade.EmpleadosFacade;
import com.clobi.transporte.facade.UnidadFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ALEX
 */
@Named(value = "asignacionCreate")
@RequestScoped
public class AsignacionCreate {
    private Asignacion selected;
    @EJB
    private AsignacionFacade ejbAsignacion;
    @EJB
    private EmpleadosFacade ejbEmpleado;
    @EJB
    private UnidadFacade ejbUnidad;
    
    public AsignacionCreate() {
        this.selected = new Asignacion();
        this.ejbAsignacion = new AsignacionFacade();
        this.ejbEmpleado = new EmpleadosFacade();
        this.ejbUnidad = new UnidadFacade();
    }
    
    private AsignacionFacade getFacade(){
        return this.ejbAsignacion;
    }
    
    public void setContrato(Empleado dui) {
        FacesContext context = FacesContext.getCurrentInstance();
        EmpleadoContrato ec = context.getApplication().evaluateExpressionGet(context, "#{empleadosContrato}", EmpleadoContrato.class);
        ec.prepareCreate();
        ec.setEmpleadoContratoByDUI(dui);
    }
    
    private EmpleadosFacade getEmpleadoFacade(){
        return this.ejbEmpleado; 
    }
    
    private UnidadFacade getUnidadFacade(){
        return this.ejbUnidad;
    }
    
    public void create(){
        System.out.print("HEY" + this.selected);
        System.out.print("HEY" + this.selected.getUnidad());
        System.out.print("HEY" + this.selected.getMotorista());
        System.out.print("HEY" + this.selected.getAyudante());
        if(this.selected.getUnidad() == null || this.selected.getMotorista() == null){
            JsfUtil.addErrorMessage("Debes seleccionar una unidad, motorista y auxiliar");
        }else{
           persist(JsfUtil.PersistAction.CREATE, "Asignacion exitosa"); 
        }
        
    }
    
    public void transferir() {
        FacesContext context = FacesContext.getCurrentInstance();
        AsignacionesList al = context.getApplication().evaluateExpressionGet(context, "#{asignacionesList}", AsignacionesList.class);
        al.init();
    }
    
    public List<Empleado> getMotoristas(){
        List<Empleado> list;
        if(getEmpleadoFacade().findAll().isEmpty()){
            list = getEmpleadoFacade().getEmpleadosByTipo("mot");
        }else{
            list = getEmpleadoFacade().getEmpleados("mot");
        }
        return list;
    }
    
    public List<Empleado> getAuxiliares(){
        List<Empleado> list;
        System.out.print("ESTAMOS  " + getEmpleadoFacade().findAll().isEmpty());
        if(getEmpleadoFacade().findAll().isEmpty()){
            list = getEmpleadoFacade().getEmpleadosByTipo("ayu");
        }else{
            list = getEmpleadoFacade().getEmpleados("ayu");
        }
        return list;
    }
    
    public List<Unidad> getUnidades(){
        List<Unidad> list;
        if(getFacade().findAll().isEmpty()){
            list = getUnidadFacade().findAll();
        }else{
            list = getUnidadFacade().getUnidadesNotAsignadas();
        }
        
        return list;
    }
    
    public void setAsignacion(Asignacion as){
        this.selected = as;
    }
     
    public Asignacion getAsignacion(){
        return this.selected;
    }
    
    public void uSelected(SelectEvent event) {
        System.out.println("Unidad seleccionada " + selected.getUnidad().getPlaca());
        this.selected.setUnidad(selected.getUnidad());
    } 
    
    public void mSelected(SelectEvent event) {
        System.out.println("Motorista seleccionado " + selected.getMotorista().getDui());
        this.selected.setMotorista(selected.getMotorista());
        this.setContrato(this.selected.getMotorista());
    }
    
    public void aSelected(SelectEvent event) {
        System.out.println("Ayudante seleccionado " + selected.getAyudante().getDui());
        this.selected.setAyudante(selected.getAyudante());
        this.setContrato(this.selected.getAyudante());
    }
    
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                    this.selected = null;
                    this.transferir();
                } else {
                    getFacade().remove(selected);
                }
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
