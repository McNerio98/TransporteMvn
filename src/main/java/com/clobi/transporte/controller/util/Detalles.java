/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.util;

import java.math.BigDecimal;
import java.math.BigInteger;
/**
 *
 * @author McNerio
 */

public class Detalles {
    private Integer viajesRealizados;
    private BigDecimal precioPasaje;
    private Integer conteoActual;
    private Integer conteoAnterior;
    private Integer diferencia;
    private BigDecimal ingresoCalculado;
    private BigDecimal precioViajeMotorista;
    private BigDecimal precioViajeAuxiliar;
    private BigDecimal Anticipos;
    private BigDecimal pagoMotorista;
    private BigDecimal pagoAuxiliar;
    private BigDecimal descuentoMotorista;
    private BigDecimal descuentoAuxiliar;

    public Detalles() {
        this.viajesRealizados = 0;
        this.precioPasaje = new BigDecimal(0.23);
        this.conteoAnterior = 0;
        this.diferencia = 0;
        this.ingresoCalculado = new BigDecimal(0.0);
        this.precioViajeMotorista = new BigDecimal(1.81); //Pueden establecerse cambio
        this.precioViajeAuxiliar = new BigDecimal(0.90); //Pueden establecerse cambio
        this.Anticipos = new BigDecimal(0.0);
        this.pagoMotorista = new BigDecimal(0.0);
        this.pagoAuxiliar = new BigDecimal(0.0);
        this.descuentoMotorista = new BigDecimal(0.0);
        this.descuentoAuxiliar = new BigDecimal(0.0);
    }

    public void realizarCalculos(){
        this.diferencia = this.conteoActual - this.conteoAnterior;
        this.ingresoCalculado = this.precioPasaje.multiply(new BigDecimal(this.diferencia)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    public BigDecimal getPrecioPasaje() {
        return precioPasaje;
    }

    public void setPrecioPasaje(BigDecimal precioPasaje) {
        this.precioPasaje = precioPasaje;
    }

    public Integer getConteoActual() {
        return conteoActual;
    }

    public void setConteoActual(Integer conteoActual) {
        this.conteoActual = conteoActual;    
    }

    public Integer getConteoAnterior() {
        return conteoAnterior;
    }

    public void setConteoAnterior(Integer conteoAnterior) {
        this.conteoAnterior = conteoAnterior;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Integer diferencia) {
        this.diferencia = diferencia;
    }

    public BigDecimal getIngresoCalculado() {
        return ingresoCalculado;
    }

    public void setIngresoCalculado(BigDecimal ingresoCalculado) {
        this.ingresoCalculado = ingresoCalculado;
    }

    public BigDecimal getPrecioViajeMotorista() {
        return precioViajeMotorista;
    }

    public void setPrecioViajeMotorista(BigDecimal precioViajeMotorista) {
        this.precioViajeMotorista = precioViajeMotorista;
    }

    public BigDecimal getPrecioViajeAuxiliar() {
        return precioViajeAuxiliar;
    }

    public void setPrecioViajeAuxiliar(BigDecimal precioViajeAuxiliar) {
        this.precioViajeAuxiliar = precioViajeAuxiliar;
    }

    public BigDecimal getAnticipos() {
        return Anticipos;
    }

    public void setAnticipos(BigDecimal Anticipos) {
        this.Anticipos = Anticipos;
    }

    public BigDecimal getPagoMotorista() {
        return pagoMotorista;
    }

    public void setPagoMotorista(BigDecimal pagoMotorista) {
        this.pagoMotorista = pagoMotorista;
    }

    public BigDecimal getPagoAuxiliar() {
        return pagoAuxiliar;
    }

    public void setPagoAuxiliar(BigDecimal pagoAuxiliar) {
        this.pagoAuxiliar = pagoAuxiliar;
    }

    public BigDecimal getDescuentoMotorista() {
        return descuentoMotorista;
    }

    public void setDescuentoMotorista(BigDecimal descuentoMotorista) {
        this.descuentoMotorista = descuentoMotorista;
    }

    public BigDecimal getDescuentoAuxiliar() {
        return descuentoAuxiliar;
    }

    public void setDescuentoAuxiliar(BigDecimal descuentoAuxiliar) {
        this.descuentoAuxiliar = descuentoAuxiliar;
    }

    public Integer getViajesRealizados() {
        return viajesRealizados;
    }

    public void setViajesRealizados(Integer viajesRealizados) {
        this.viajesRealizados = viajesRealizados;
    }
    
}
