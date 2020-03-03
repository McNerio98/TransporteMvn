/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.facade.EmpleadosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author desarrollo
 */
@Named(value = "empleadosControl")
@SessionScoped
public class EmpleadosController implements Serializable{
    private List<Empleado> lstEmpleados;
    @EJB
    private EmpleadosFacade ejbEmpleado;

    @PostConstruct
    public void init(){
        this.setLstEmpleados(ejbEmpleado.findAll());
    }
    
public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }

    public List<Empleado> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<Empleado> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }
    
    
    
}
