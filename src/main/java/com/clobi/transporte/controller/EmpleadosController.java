/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.controller.util.JsfUtil.PersistAction;
import com.clobi.transporte.entity.DocumentoByEmpleado;
import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.facade.DocEmpleadoFacade;
import com.clobi.transporte.facade.EmpleadosFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author desarrollo
 */
@Named(value = "empleadosControl")
@SessionScoped
public class EmpleadosController implements Serializable {

    private List<Empleado> lstEmpleados;
    @EJB
    private EmpleadosFacade ejbEmpleado;
    @EJB
    private DocEmpleadoFacade ejbDocEmpleado;
    private DocumentoByEmpleado DocToUp;
    private Empleado selected;
    private Part DUIDocumentFile;


    @PostConstruct
    public void init() {
        this.setLstEmpleados(ejbEmpleado.findAll());
    }

    public void create() {
        this.persist(PersistAction.CREATE, "Se Registro exitosamente...");
    }

    public Empleado prepareCreate() {
        if (this.selected != null) {
            System.out.print("Dato 1: " + selected.getNombres());
            System.out.print("Dato 1: " + selected.getApellidos());
            selected = null;
        }
        this.selected = new Empleado();
        System.out.print("Entro para la preparacion");
        return this.selected;

    }

    public List<Empleado> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<Empleado> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }

    private EmpleadosFacade getFacade() {
        return ejbEmpleado;
    }
    
    private DocEmpleadoFacade getDocEmpleadoFacade(){
        return ejbDocEmpleado;
    }
    public Part getDUIDocumentFile() {
        return DUIDocumentFile;
    }

    public void setDUIDocumentFile(Part DUIDocumentFile) {
        this.DUIDocumentFile = DUIDocumentFile;
    }

    public EmpleadosFacade getEjbEmpleado() {
        return ejbEmpleado;
    }

    public void saveDocument() {
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String folderToSave = "documentsSaved";
        String realFolder = realPath + folderToSave;
        String fileName = "";
        try{
//Registro en la base de datos            
//Subir archivo
             
            InputStream input = this.DUIDocumentFile.getInputStream();
            fileName = this.generateCode("DUI")+".pdf";
            Files.copy(input, new File(realFolder, fileName).toPath());
            JsfUtil.addSuccessMessage("Se subio un elemento");
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al cargar elemento");
        }
    }
    
    public void prototypeUpDocument(){
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String folderToSave = "documentsSaved";
        String realFolder = realPath + folderToSave;
        String fileName = "";
        
        try{
            getDocEmpleadoFacade().initTransaction();
            fileName = this.generateCode("DUI")+".pdf";
            this.DocToUp.setFilepath("/" + fileName);
            getDocEmpleadoFacade().create(DocToUp);
            InputStream input = this.DUIDocumentFile.getInputStream();
            
            Files.copy(input, new File(realFolder, fileName).toPath());
            JsfUtil.addSuccessMessage("Se subio un elemento");
            getDocEmpleadoFacade().commit();
        }catch(Exception e){
            getDocEmpleadoFacade().rollback();
            JsfUtil.addErrorMessage("Error al cargar elemento");
        }    
    }
    
    private String generateCode(String idenDocument){
        String code = "generic";
        code = this.selected.getDui()+idenDocument;
        return code;
    }

    public void setEjbEmpleado(EmpleadosFacade ejbEmpleado) {
        this.ejbEmpleado = ejbEmpleado;
    }

    public Empleado getSelected() {
        return selected;
    }

    public void setSelected(Empleado selected) {
        this.selected = selected;
    }

    public void recargarEmpleados() {
        this.setSelected(null);
    }

    public void destroy() {
        this.persist(PersistAction.DELETE, "Se Removio un elemento con exito!");
    }
    
    public void showStatus(){
        System.out.print("Se envio al metodo");
    }

    private void persist(PersistAction persistAction, String infoResult) {

        try {
            if (persistAction != PersistAction.DELETE) {
                Empleado p = getFacade().edit(selected);
                this.selected = null;
            } else {
                getFacade().remove(selected);
            }
            this.setLstEmpleados(ejbEmpleado.findAll());
            JsfUtil.addSuccessMessage(infoResult);
        } catch (EJBException ex) {
            String msg = "Error en la operacion";
            Throwable cause = ex.getCause();
            JsfUtil.addErrorMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error de persistencia");
        }
    }

}
