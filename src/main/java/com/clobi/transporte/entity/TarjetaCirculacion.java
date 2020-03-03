/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "tarjetascirculacion", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarjetaCirculacion.findAll", query = "SELECT t FROM TarjetaCirculacion t")
    , @NamedQuery(name = "TarjetaCirculacion.findByIdtarjeta", query = "SELECT t FROM TarjetaCirculacion t WHERE t.idtarjeta = :idtarjeta")
    , @NamedQuery(name = "TarjetaCirculacion.findByModelo", query = "SELECT t FROM TarjetaCirculacion t WHERE t.modelo = :modelo")
    , @NamedQuery(name = "TarjetaCirculacion.findByYeAr", query = "SELECT t FROM TarjetaCirculacion t WHERE t.yeAr = :yeAr")
    , @NamedQuery(name = "TarjetaCirculacion.findByMotor", query = "SELECT t FROM TarjetaCirculacion t WHERE t.motor = :motor")
    , @NamedQuery(name = "TarjetaCirculacion.findByChasis", query = "SELECT t FROM TarjetaCirculacion t WHERE t.chasis = :chasis")
    , @NamedQuery(name = "TarjetaCirculacion.findByFechavencimiento", query = "SELECT t FROM TarjetaCirculacion t WHERE t.fechavencimiento = :fechavencimiento")})
public class TarjetaCirculacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtarjeta")
    private Integer idtarjeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
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
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @JoinColumn(name = "idmarca", referencedColumnName = "idmarca")
    @ManyToOne(optional = false)
    private Marca idmarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtarjetacirculacion")
    private List<Unidad> unidadList;

    public TarjetaCirculacion() {
    }

    public TarjetaCirculacion(Integer idtarjeta) {
        this.idtarjeta = idtarjeta;
    }

    public TarjetaCirculacion(Integer idtarjeta, String modelo, String yeAr, String motor, String chasis, Date fechavencimiento) {
        this.idtarjeta = idtarjeta;
        this.modelo = modelo;
        this.yeAr = yeAr;
        this.motor = motor;
        this.chasis = chasis;
        this.fechavencimiento = fechavencimiento;
    }

    public Integer getIdtarjeta() {
        return idtarjeta;
    }

    public void setIdtarjeta(Integer idtarjeta) {
        this.idtarjeta = idtarjeta;
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

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public Marca getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Marca idmarca) {
        this.idmarca = idmarca;
    }

    @XmlTransient
    public List<Unidad> getUnidadList() {
        return unidadList;
    }

    public void setUnidadList(List<Unidad> unidadList) {
        this.unidadList = unidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarjeta != null ? idtarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaCirculacion)) {
            return false;
        }
        TarjetaCirculacion other = (TarjetaCirculacion) object;
        if ((this.idtarjeta == null && other.idtarjeta != null) || (this.idtarjeta != null && !this.idtarjeta.equals(other.idtarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.TarjetaCirculacion[ idtarjeta=" + idtarjeta + " ]";
    }
    
}
