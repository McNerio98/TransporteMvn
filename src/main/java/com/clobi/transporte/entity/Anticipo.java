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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tinkpad PC
 */
@Entity
@Table(name = "anticiposoperacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anticipo.findAll", query = "SELECT a FROM Anticipo a")
    , @NamedQuery(name = "Anticipo.findByIdanticipo", query = "SELECT a FROM Anticipo a WHERE a.idanticipo = :idanticipo")
    , @NamedQuery(name = "Anticipo.findByAnticipo", query = "SELECT a FROM Anticipo a WHERE a.anticipo = :anticipo")
    , @NamedQuery(name = "Anticipo.sumAnticipos", query = "SELECT sum(a.anticipo) from Anticipo a where a.idoperacion = :idoperacion GROUP BY a.idoperacion")
    , @NamedQuery(name = "Anticipo.findByHora", query = "SELECT a FROM Anticipo a WHERE a.hora = :hora")})
public class Anticipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idanticipo", nullable = false)
    private Integer idanticipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anticipo", nullable = false)
    private BigDecimal anticipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "idoperacion", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private OperacionUnidad idoperacion;

    public Anticipo() {
    }

    public Anticipo(Integer idanticipo) {
        this.idanticipo = idanticipo;
    }

    public Anticipo(Integer idanticipo, BigDecimal anticipo, Date hora) {
        this.idanticipo = idanticipo;
        this.anticipo = anticipo;
        this.hora = hora;
    }

    public Integer getIdanticipo() {
        return idanticipo;
    }

    public void setIdanticipo(Integer idanticipo) {
        this.idanticipo = idanticipo;
    }

    public BigDecimal getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(BigDecimal anticipo) {
        this.anticipo = anticipo;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
        hash += (idanticipo != null ? idanticipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anticipo)) {
            return false;
        }
        Anticipo other = (Anticipo) object;
        if ((this.idanticipo == null && other.idanticipo != null) || (this.idanticipo != null && !this.idanticipo.equals(other.idanticipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Anticipo[ idanticipo=" + idanticipo + " ]";
    }

}
