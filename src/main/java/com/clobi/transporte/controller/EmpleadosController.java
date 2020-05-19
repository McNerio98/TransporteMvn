/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.Enums;
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
import java.util.ArrayList;
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
    private Empleado selected;
    private DocumentoByEmpleado licencia;
    private DocumentoByEmpleado carnet;
    private Part licenciaFile;
    private Part carnetFile;
    private boolean actionUpdate; 

    @PostConstruct
    public void init() {
        this.setLstEmpleados(ejbEmpleado.ALL_EMPLADOS());
    }

    //Creando empleado 
    public void create() {
        this.persist(PersistAction.CREATE, "Se Registro exitosamente...");
    }

    public void saveDocument() {
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String folderToSave = "documentsSaved";
        String realFolder = realPath + folderToSave;
        String fileName = "";
        try {
            InputStream input = this.licenciaFile.getInputStream();
            fileName = this.generateCode("LIC") + ".pdf";
            Files.copy(input, new File(realFolder, fileName).toPath());
            JsfUtil.addSuccessMessage("Se subio un elemento");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cargar elemento");
        }
    }

    public void update() {
        this.persist(PersistAction.UPDATE, "Se Actualizo Exitosamente...");
    }

    //True si los guardo con exito tanto los archivos como los registros  
    public boolean UploadDocuments() {
        boolean estado = false;
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String folderToSave = "documentsSaved";
        String realFolder = realPath + folderToSave;
        String fileName = "";

        try {
            //ejbDocEmpleado.initTransaction();
            System.out.print("Size: " + this.licenciaFile.getSize());
            System.out.print("Size2: " + this.carnetFile.getSize());
            InputStream streamLicencia = this.licenciaFile.getInputStream();
            InputStream streamCarnet = this.carnetFile.getInputStream();

            fileName = this.generateCode("LIC") + ".pdf";
            Files.copy(streamLicencia, new File(realFolder, fileName).toPath());
            this.licencia.setFilepath(fileName);

            fileName = this.generateCode("CAR") + ".pdf";
            Files.copy(streamCarnet, new File(realFolder, fileName).toPath());
            this.carnet.setFilepath(fileName);

            ejbDocEmpleado.edit(licencia);
            ejbDocEmpleado.edit(carnet);

            //ejbDocEmpleado.commit();
            estado = true;
        } catch (Exception e) {
            //ejbDocEmpleado.rollback();
            JsfUtil.addErrorMessage("Error al subir los documentos");
        }
        return estado;
    }

    public Empleado prepareCreate() {
        this.licencia = new DocumentoByEmpleado();
        this.carnet = new DocumentoByEmpleado();
        this.selected = new Empleado();
        this.selected.setEstadoregistro(true);
        this.selected.setAvatarPath("any.jpg");
        this.setActionUpdate(false);
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

    private DocEmpleadoFacade getDocEmpleadoFacade() {
        return ejbDocEmpleado;
    }

    public EmpleadosFacade getEjbEmpleado() {
        return ejbEmpleado;
    }

    private String generateCode(String idenDocument) {
        String code = "generic";
        code = this.selected.getDui() + idenDocument;
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

    public void showStatus() {
        System.out.print("Se envio al metodo");
    }

    private void persist(PersistAction persistAction, String infoResult) {
        try {
            //ejbEmpleado.initTransaction();
            if (persistAction != PersistAction.DELETE) {
                Empleado p = getFacade().edit(selected);
                //Si esta creando es obligacion subir los archivos 
                if (persistAction == PersistAction.CREATE) {
                    this.licencia.setIdempleado(p);
                    this.carnet.setIdempleado(p);
                    if (!UploadDocuments()) {
                        throw new Exception("Error al subir archivos");
                    }
                }
            } else {
                this.selected.setEstadoregistro(false);
                getFacade().edit(selected);
            }
            this.setLstEmpleados(ejbEmpleado.ALL_EMPLADOS());
            JsfUtil.addSuccessMessage(infoResult);
            //ejbEmpleado.commit();
        } catch (Exception e) {
            String msg = "Error en la operacion";
            Throwable cause = e.getCause();
            JsfUtil.addErrorMessage(msg);
            //ejbEmpleado.rollback();
        }
        this.selected = null;
    }

    public DocEmpleadoFacade getEjbDocEmpleado() {
        return ejbDocEmpleado;
    }

    public void setEjbDocEmpleado(DocEmpleadoFacade ejbDocEmpleado) {
        this.ejbDocEmpleado = ejbDocEmpleado;
    }

    public DocumentoByEmpleado getLicencia() {
        return licencia;
    }

    public void setLicencia(DocumentoByEmpleado licencia) {
        this.licencia = licencia;
    }

    public Part getLicenciaFile() {
        return licenciaFile;
    }

    public void setLicenciaFile(Part licenciaFile) {
        this.licenciaFile = licenciaFile;
    }

    public Part getCarnetFile() {
        return carnetFile;
    }

    public void setCarnetFile(Part carnetFile) {
        this.carnetFile = carnetFile;
    }

    public DocumentoByEmpleado getCarnet() {
        return carnet;
    }

    public void setCarnet(DocumentoByEmpleado carnet) {
        this.carnet = carnet;
    }

    public boolean isActionUpdate() {
        return actionUpdate;
    }

    public void setActionUpdate(boolean actionUpdate) {
        this.actionUpdate = actionUpdate;
    }
    
    public void actionUpdate(Empleado e){
        this.setActionUpdate(true);
        this.setSelected(e);
    }
}
