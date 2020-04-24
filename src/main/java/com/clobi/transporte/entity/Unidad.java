/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALEX
 */
@Entity
@Table(name = "unidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u")
    , @NamedQuery(name = "Unidad.findByPlaca", query = "SELECT u FROM Unidad u WHERE u.placa = :placa")
    , @NamedQuery(name = "Unidad.findByNumerounidad", query = "SELECT u FROM Unidad u WHERE u.numerounidad = :numerounidad")
    , @NamedQuery(name = "Unidad.findByModelo", query = "SELECT u FROM Unidad u WHERE u.modelo = :modelo")
    , @NamedQuery(name = "Unidad.findByYeAr", query = "SELECT u FROM Unidad u WHERE u.yeAr = :yeAr")
    , @NamedQuery(name = "Unidad.findByMotor", query = "SELECT u FROM Unidad u WHERE u.motor = :motor")
    , @NamedQuery(name = "Unidad.findByChasis", query = "SELECT u FROM Unidad u WHERE u.chasis = :chasis")
    , @NamedQuery(name = "Unidad.findByMarca", query = "SELECT u FROM Unidad u WHERE u.marca = :marca")
    , @NamedQuery(name = "Unidad.findByFechavencimiento", query = "SELECT u FROM Unidad u WHERE u.fechavencimiento = :fechavencimiento")
    , @NamedQuery(name = "Unidad.findByEstadoregistro", query = "SELECT u FROM Unidad u WHERE u.estadoregistro = :estadoregistro")
    , @NamedQuery(name = "Unidad.ChangeEstadoregistro", query = "UPDATE Unidad u SET u.estadoregistro = false WHERE u.placa = :placa")
    , @NamedQuery(name = "Unidad.getDocsByPlaca", query = "SELECT u FROM DocumentoByUnidad u WHERE u.placa.placa = :placa")
})
public class Unidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numerounidad")
    private int numerounidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ye_ar")
    private String yeAr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "motor")
    private String motor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "chasis")
    private String chasis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estadoregistro")
    private boolean estadoregistro;

    public Unidad() {
    }

    public Unidad(String placa) {
        this.placa = placa;
    }

    public Unidad(String placa, int numerounidad, String modelo, String yeAr, String motor, String chasis, String marca, Date fechavencimiento, boolean estadoregistro) {
        this.placa = placa;
        this.numerounidad = numerounidad;
        this.modelo = modelo;
        this.yeAr = yeAr;
        this.motor = motor;
        this.chasis = chasis;
        this.marca = marca;
        this.fechavencimiento = fechavencimiento;
        this.estadoregistro = estadoregistro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getNumerounidad() {
        return numerounidad;
    }

    public void setNumerounidad(int numerounidad) {
        this.numerounidad = numerounidad;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getYeAr() {
        return yeAr;
    }

    public void setYeAr(String yeAr) {
        this.yeAr = yeAr;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public boolean getEstadoregistro() {
        return estadoregistro;
    }

    public void setEstadoregistro(boolean estadoregistro) {
        this.estadoregistro = estadoregistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Unidad[ placa=" + placa + " ]";
    }
    
}
