/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.util;

import java.math.BigDecimal;

/**
 *
 * @author Tinkpad PC
 */
public class ADRegistrosPOJO {
    private Integer id;
    private String tipoestado;
    private Integer totalviajes;
    private BigDecimal ingresototal;
    private String fechareg;
    private Integer uniactivas;
    private BigDecimal totalpagos;

    public ADRegistrosPOJO() {
    }

    public ADRegistrosPOJO(Integer id, String tipoestado, Integer totalviajes, BigDecimal ingresototal, String fechareg, Integer uniactivas, BigDecimal totalpagos) {
        this.id = id;
        this.tipoestado = tipoestado;
        this.totalviajes = totalviajes;
        this.ingresototal = ingresototal;
        this.fechareg = fechareg;
        this.uniactivas = uniactivas;
        this.totalpagos = totalpagos;
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

    public Integer getTotalviajes() {
        return totalviajes;
    }

    public void setTotalviajes(Integer totalviajes) {
        this.totalviajes = totalviajes;
    }

    public BigDecimal getIngresototal() {
        return ingresototal;
    }

    public void setIngresototal(BigDecimal ingresototal) {
        this.ingresototal = ingresototal;
    }

    public String getFechareg() {
        return fechareg;
    }

    public void setFechareg(String fechareg) {
        this.fechareg = fechareg;
    }

    public Integer getUniactivas() {
        return uniactivas;
    }

    public void setUniactivas(Integer uniactivas) {
        this.uniactivas = uniactivas;
    }

    public BigDecimal getTotalpagos() {
        return totalpagos;
    }

    public void setTotalpagos(BigDecimal totalpagos) {
        this.totalpagos = totalpagos;
    }
}
