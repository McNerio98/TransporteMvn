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
@Table(name = "movimientos", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
    , @NamedQuery(name = "Movimiento.findByIdmovimiento", query = "SELECT m FROM Movimiento m WHERE m.idmovimiento = :idmovimiento")
    , @NamedQuery(name = "Movimiento.findByMonto", query = "SELECT m FROM Movimiento m WHERE m.monto = :monto")
    , @NamedQuery(name = "Movimiento.findByTipomovimiento", query = "SELECT m FROM Movimiento m WHERE m.tipomovimiento = :tipomovimiento")
    , @NamedQuery(name = "Movimiento.findByFecha", query = "SELECT m FROM Movimiento m WHERE m.fecha = :fecha")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimiento")
    private Integer idmovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipomovimiento")
    private String tipomovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idahorro", referencedColumnName = "idahorro")
    @ManyToOne(optional = false)
    private Ahorros idahorro;

    public Movimiento() {
    }

    public Movimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Movimiento(Integer idmovimiento, double monto, String tipomovimiento, Date fecha) {
        this.idmovimiento = idmovimiento;
        this.monto = monto;
        this.tipomovimiento = tipomovimiento;
        this.fecha = fecha;
    }

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ahorros getIdahorro() {
        return idahorro;
    }

    public void setIdahorro(Ahorros idahorro) {
        this.idahorro = idahorro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Movimiento[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
