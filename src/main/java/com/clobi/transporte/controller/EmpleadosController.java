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
    private Empleado employe;

    @PostConstruct
    public void init(){
        this.setLstEmpleados(ejbEmpleado.findAll());
    }
    
    public void create(){
        System.out.print("Data:"+this.employe.getNombres()+" , "+this.employe.getApellidos());
    }
    
    public Empleado prepareCreate(){
        this.employe = new Empleado();
        return this.employe;
    }
        
    public List<Empleado> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<Empleado> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }

    public EmpleadosFacade getFacade() {
        return ejbEmpleado;
    }

    public Empleado getEmploye() {
        return employe;
    }

    public void setEmploye(Empleado employe) {
        this.employe = employe;
    }
    
}
