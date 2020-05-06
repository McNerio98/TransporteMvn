/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.controller.util.JsfUtil.PersistAction;
import com.clobi.transporte.entity.DocumentoByUnidad;
import com.clobi.transporte.entity.Unidad;
import com.clobi.transporte.facade.DocumentoByUnidadFacade;
import com.clobi.transporte.facade.UnidadFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

/**
 *
 * @author ALEX
 */
@Named(value = "unidadesController")
@RequestScoped
public class UnidadesController {

    private Part file;
    private Unidad selected;
    @EJB
    private UnidadFacade ejbUnidad;
    @EJB
    private DocumentoByUnidadFacade ejbDocs;

    public UnidadesController() {
        this.selected = new Unidad();
        this.ejbUnidad = new UnidadFacade();
        this.ejbDocs = new DocumentoByUnidadFacade();
    }

    public UnidadFacade getFacade() {
        return this.ejbUnidad;
    }

    public DocumentoByUnidadFacade getFacadeDocs() {
        return this.ejbDocs;
    }
    public void create() {
        this.selected.setEstadoregistro(true);
        persist(JsfUtil.PersistAction.CREATE, "Se guardo exitosamente");
//        try {
//            this.submit();
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage("Error al cargar elemento 1");
//        }

    }

    public void submit(Unidad u) throws ServletException, IOException {
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String folderToSave = "documentsSaved";
        String realFolder = realPath + folderToSave;
        if (u != null && file != null) {
            try {
                for (Part part : JsfUtil.getAllParts(file)) {

                    String fileName = part.getSubmittedFileName();
                    InputStream fileContent = part.getInputStream();
                    Files.copy(fileContent, new File(realFolder, fileName).toPath());
                    DocumentoByUnidad doc = new DocumentoByUnidad();
                    doc.setFilepath(fileName);
                    doc.setPlaca(u);
                    doc.setEspecificacion("Archivo de " + fileName);
                    getFacadeDocs().create(doc);
                } 
                JsfUtil.addSuccessMessage("Se subio un elemento");
            } catch (IOException e) {
                System.out.print("FILE: " + e.getMessage());
                JsfUtil.addErrorMessage("Error al cargar elemento" + e.getMessage());
            }
        }
    }

    public Unidad prepareCreate() {
        if (this.selected != null) {
            selected = null;
        }
        this.selected = new Unidad();
        System.out.print("Entro para la preparacion");
        return this.selected;

    }

    public void setUnidad(Unidad u) {
        this.selected = u;
    }

    public Unidad getUnidades() {
        return this.selected;
    }

    public Part getFile() {
        return null; // Important!
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void transferir() {
        FacesContext context = FacesContext.getCurrentInstance();
        UnidadesListController ulc = context.getApplication().evaluateExpressionGet(context, "#{unidadesListController}", UnidadesListController.class);
        ulc.init();
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                    this.submit(getFacade().edit(selected));
                    this.selected = null;
                } else {
                    getFacade().remove(selected);
                }
                this.transferir();
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
