/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Tinkpad PC
 */
@Named(value = "TControl")
@SessionScoped
public class TestingController implements Serializable {

    private String json;
    private String nombre;

    public TestingController() {
        nombre = "";
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void procesar() {
        System.out.print("varNombre: " + this.nombre);
    }

    public void doNothing() {
        System.out.print("varNombre: " + this.nombre);
    }

}
