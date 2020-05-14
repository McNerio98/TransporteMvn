/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller.util;

import com.clobi.transporte.entity.Pago;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author McNerio
 */

public class Detalles {
    private int viajesRealizados;
    private BigDecimal precioPasaje;
    private Integer conteoActual;
    private Integer conteoAnterior;
    private Integer diferencia;
    private BigDecimal ingresoCalculado;
    private BigDecimal precioViajeMotorista;
    private BigDecimal precioViajeAuxiliar;
    private BigDecimal Anticipos;
    private BigDecimal descuentoMotorista;
    private BigDecimal descuentoAuxiliar;
    private List<Pago> listPagos;

    public Detalles() {
        this.viajesRealizados = 0;
        this.precioPasaje = new BigDecimal(0.23);
        this.conteoActual = 0;
        this.conteoAnterior = 0;
        this.diferencia = 0;
        this.ingresoCalculado = new BigDecimal(0.0);
        this.precioViajeMotorista = new BigDecimal(1.81); //Pueden establecerse cambio
        this.precioViajeAuxiliar = new BigDecimal(0.90); //Pueden establecerse cambio
        this.Anticipos = new BigDecimal(0.0);
        this.descuentoMotorista = new BigDecimal(0.0);
        this.descuentoAuxiliar = new BigDecimal(0.0);
        
        this.listPagos = new ArrayList<Pago>(); 
    }

    public void realizarCalculos(){
        this.diferencia = this.conteoActual - this.conteoAnterior;
        this.ingresoCalculado = this.precioPasaje.multiply(new BigDecimal(this.diferencia)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        calcPagoChofer();
        calcPagoAuxiliar();
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

    public int getViajesRealizados() {
        return viajesRealizados;
    }

    public void setViajesRealizados(int viajesRealizados) {
        this.viajesRealizados = viajesRealizados;
    }

    //Calculos
    private void calcPagoChofer(){
        BigDecimal pago = this.getPrecioViajeMotorista().multiply(new BigDecimal(this.viajesRealizados)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        listPagos.get(2).setMonto(pago);
    }
    
    private void calcPagoAuxiliar(){
        BigDecimal pago = this.getPrecioViajeAuxiliar().multiply(new BigDecimal(this.viajesRealizados)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        listPagos.get(3).setMonto(pago);
    }

    public List<Pago> getListPagos() {
        return listPagos;
    }

    public void setListPagos(List<Pago> listPagos) {
        this.listPagos = listPagos;
    }
    
    
}
