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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author desarrollo
 */
@Entity
@Table(name = "actividadesfinancieras", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadFinanciera.findAll", query = "SELECT a FROM ActividadFinanciera a")
    , @NamedQuery(name = "ActividadFinanciera.findByIdactividad", query = "SELECT a FROM ActividadFinanciera a WHERE a.idactividad = :idactividad")
    , @NamedQuery(name = "ActividadFinanciera.findByConcepto", query = "SELECT a FROM ActividadFinanciera a WHERE a.concepto = :concepto")
    , @NamedQuery(name = "ActividadFinanciera.findByMonto", query = "SELECT a FROM ActividadFinanciera a WHERE a.monto = :monto")
    , @NamedQuery(name = "ActividadFinanciera.findByFecha", query = "SELECT a FROM ActividadFinanciera a WHERE a.fecha = :fecha")})
public class ActividadFinanciera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactividad")
    private Integer idactividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categoria idcategoria;

    public ActividadFinanciera() {
    }

    public ActividadFinanciera(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public ActividadFinanciera(Integer idactividad, String concepto, double monto, Date fecha) {
        this.idactividad = idactividad;
        this.concepto = concepto;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividad != null ? idactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadFinanciera)) {
            return false;
        }
        ActividadFinanciera other = (ActividadFinanciera) object;
        if ((this.idactividad == null && other.idactividad != null) || (this.idactividad != null && !this.idactividad.equals(other.idactividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.ActividadFinanciera[ idactividad=" + idactividad + " ]";
    }
    
}
