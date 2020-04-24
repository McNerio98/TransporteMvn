/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.entity.DocumentoByUnidad;
import com.clobi.transporte.entity.Unidad;
import com.clobi.transporte.facade.DocumentoByUnidadFacade;
import com.clobi.transporte.facade.UnidadFacade;
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
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ALEX
 */
@Named(value = "unidadesListController")
@SessionScoped
public class UnidadesListController implements Serializable {

    private List<Unidad> listUnidad;
    private Unidad selected;
    private List<DocumentoByUnidad> listDocs;
    @EJB
    private UnidadFacade ejbUnidad;

    public UnidadesListController() {
        this.ejbUnidad = new UnidadFacade();
        this.selected = new Unidad();
        this.listDocs = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        this.listUnidad = new ArrayList<>();
        this.setUnidadesList(ejbUnidad.getUnidadesByStatus(true));
    }

    private UnidadFacade getFacade() {
        return this.ejbUnidad;
    }

    public void deleteRegistry() {
        persist(JsfUtil.PersistAction.DELETE, "Accion exitosas");
    }

    public List<DocumentoByUnidad> getAllDocs() {

        return getFacade().getDocsByPlaca(this.selected);
    }

    public void rowSelected(SelectEvent event) {
        System.out.println("Hey, i'm here! " + selected.getPlaca());
        for (int i = 0; i < (getFacade().getDocsByPlaca(this.selected)).size(); i++) {
            System.out.println("DOCNAME " + (getFacade().getDocsByPlaca(this.selected)).get(i).getPlaca());
        }
        this.setDocsList(getFacade().getDocsByPlaca(this.selected));
    }

    public void setDocsList(List<DocumentoByUnidad> l) {
        listDocs = l;
    } 

    public List<DocumentoByUnidad> getDocsList() {
        return this.listDocs;
    }

    public void savechanges() {
        persist(JsfUtil.PersistAction.UPDATE, "Se ha guardado los cambios");
    }

    public void setUnidadesList(List<Unidad> lst) {
        this.listUnidad = lst;
    }

    public List<Unidad> getUnidadesList() {
        return this.listUnidad;
    }

    public void setUnidad(Unidad u) {
        this.selected = u;
    }

    public Unidad getUnidad() {
        return this.selected;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                    this.selected = null;
                } else {
                    //getFacade().remove(selected);
                    getFacade().setUnidadStatus(selected);
                    this.init();
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
