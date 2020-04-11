/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.Contrato;

import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.entity.EmpleadosContrato;
import com.clobi.transporte.facade.EmpleadosContratoFacade;
import com.clobi.transporte.facade.EmpleadosFacade;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author ALEX
 */
@Named(value = "empleadosContrato")
@SessionScoped
public class EmpleadoContrato implements Serializable {

    private EmpleadosContrato selected;
    @EJB
    private EmpleadosContratoFacade ejbEC;

    public EmpleadoContrato() {
        this.selected = new EmpleadosContrato();
        this.ejbEC = new EmpleadosContratoFacade();
    }

    private EmpleadosContratoFacade getFacade() {
        return this.ejbEC;
    }
    
    public void update(){
       
        System.out.print(this.selected);
        persist(JsfUtil.PersistAction.CREATE, "Se actualizo exitosamente");
    }

    public EmpleadosContrato prepareCreate() {
        if (this.selected != null) {
            selected = null;
        }
        this.selected = new EmpleadosContrato();
        System.out.print("Entro para la preparacion " + selected.getEmpleado());
        return this.selected;

    }

    public void setEmpleadoContrato(EmpleadosContrato ec) {
        this.selected = ec;
    }

    public void setEmpleadoContratoByDUI(Empleado dui) {
        this.setEmpleadoContrato(getFacade().find(dui.getDui()));
        System.out.print(this.selected.getEmpleado1().getDui());
    }

    public EmpleadosContrato getEmpleadoContrato() {
        return this.selected;
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
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "Hubo un problema al guardar los cambios";
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
