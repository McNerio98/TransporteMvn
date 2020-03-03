/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "anticiposbyactividad", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnticiposByActividad.findAll", query = "SELECT a FROM AnticiposByActividad a")
    , @NamedQuery(name = "AnticiposByActividad.findByIdanticipo", query = "SELECT a FROM AnticiposByActividad a WHERE a.idanticipo = :idanticipo")
    , @NamedQuery(name = "AnticiposByActividad.findByAnticipo", query = "SELECT a FROM AnticiposByActividad a WHERE a.anticipo = :anticipo")})
public class AnticiposByActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idanticipo")
    private Integer idanticipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anticipo")
    private double anticipo;
    @JoinColumn(name = "idactividad", referencedColumnName = "idactividad")
    @ManyToOne(optional = false)
    private ActividadDiaria idactividad;

    public AnticiposByActividad() {
    }

    public AnticiposByActividad(Integer idanticipo) {
        this.idanticipo = idanticipo;
    }

    public AnticiposByActividad(Integer idanticipo, double anticipo) {
        this.idanticipo = idanticipo;
        this.anticipo = anticipo;
    }

    public Integer getIdanticipo() {
        return idanticipo;
    }

    public void setIdanticipo(Integer idanticipo) {
        this.idanticipo = idanticipo;
    }

    public double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(double anticipo) {
        this.anticipo = anticipo;
    }

    public ActividadDiaria getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(ActividadDiaria idactividad) {
        this.idactividad = idactividad;
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
        if (!(object instanceof AnticiposByActividad)) {
            return false;
        }
        AnticiposByActividad other = (AnticiposByActividad) object;
        if ((this.idanticipo == null && other.idanticipo != null) || (this.idanticipo != null && !this.idanticipo.equals(other.idanticipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.AnticiposByActividad[ idanticipo=" + idanticipo + " ]";
    }
    
}
