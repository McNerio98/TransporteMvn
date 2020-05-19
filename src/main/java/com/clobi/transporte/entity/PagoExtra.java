/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tinkpad PC
 */
@Entity
@Table(name = "extrapay")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoExtra.findAll", query = "SELECT p FROM PagoExtra p")
    , @NamedQuery(name = "PagoExtra.findById", query = "SELECT p FROM PagoExtra p WHERE p.id = :id")
    , @NamedQuery(name = "PagoExtra.findByDescripcion", query = "SELECT p FROM PagoExtra p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "PagoExtra.findByMonto", query = "SELECT p FROM PagoExtra p WHERE p.monto = :monto")})
public class PagoExtra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;
    @JoinColumn(name = "idoperacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OperacionUnidad idoperacion;

    public PagoExtra() {
    }

    public PagoExtra(Integer id) {
        this.id = id;
    }

    public PagoExtra(Integer id, String descripcion, BigDecimal monto) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public OperacionUnidad getIdoperacion() {
        return idoperacion;
    }

    public void setIdoperacion(OperacionUnidad idoperacion) {
        this.idoperacion = idoperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoExtra)) {
            return false;
        }
        PagoExtra other = (PagoExtra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.PagoExtra[ id=" + id + " ]";
    }
    
}
