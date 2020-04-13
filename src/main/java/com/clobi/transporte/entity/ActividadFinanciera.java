/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "ActividadFinanciera.actividaddiaria", query = "SELECT a FROM ActividadFinanciera a WHERE a.idactividaddiaria = :ad")})
public class ActividadFinanciera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo", nullable = false)
    private short tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
    @JoinColumn(name = "idactividaddiaria", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ActividadDiaria idactividaddiaria;
    @JoinColumn(name = "idcategoria", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Categoria idcategoria;

    public ActividadFinanciera() {
    }

    public ActividadFinanciera(Integer id) {
        this.id = id;
    }

    public ActividadFinanciera(Integer id, short tipo, BigDecimal monto) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public ActividadDiaria getIdactividaddiaria() {
        return idactividaddiaria;
    }

    public void setIdactividaddiaria(ActividadDiaria idactividaddiaria) {
        this.idactividaddiaria = idactividaddiaria;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadFinanciera)) {
            return false;
        }
        ActividadFinanciera other = (ActividadFinanciera) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.ActividadFinanciera[ id=" + id + " ]";
    }
}
