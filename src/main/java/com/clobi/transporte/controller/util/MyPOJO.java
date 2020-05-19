/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.util;

import java.util.Date;

/**
 *
 * @author Tinkpad PC
 */
public class MyPOJO {
    private Integer id;
    private String tipoestado;
    private String fechastring;
    
    public MyPOJO() {
    }
    
    public MyPOJO(Integer id, String tipoestado, String fechastring) {
        this.id = id;
        this.tipoestado = tipoestado;
        this.fechastring = fechastring;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoestado() {
        return tipoestado;
    }

    public void setTipoestado(String tipoestado) {
        this.tipoestado = tipoestado;
    }

    public String getFechastring() {
        return fechastring;
    }

    public void setFechastring(String fechastring) {
        this.fechastring = fechastring;
    }
}
