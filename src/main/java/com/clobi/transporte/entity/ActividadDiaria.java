/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tinkpad PC
 */
@Entity
@Table(name = "actividadesdiarias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadDiaria.findAll", query = "SELECT a FROM ActividadDiaria a")
    , @NamedQuery(name = "ActividadDiaria.findById", query = "SELECT a FROM ActividadDiaria a WHERE a.id = :id")
    , @NamedQuery(name = "ActividadDiaria.findByTotalviajes", query = "SELECT a FROM ActividadDiaria a WHERE a.totalviajes = :totalviajes")
    , @NamedQuery(name = "ActividadDiaria.findByIngresototal", query = "SELECT a FROM ActividadDiaria a WHERE a.ingresototal = :ingresototal")
    , @NamedQuery(name = "ActividadDiaria.countToday", query = "SELECT count(a) from ActividadDiaria a")
    , @NamedQuery(name = "ActividadDiaria.findByFecha", query = "SELECT a FROM ActividadDiaria a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "ActividadDiaria.findByEstado", query = "SELECT a FROM ActividadDiaria a WHERE a.estado = :estado")})
public class ActividadDiaria implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ingresototal")
    private BigDecimal ingresototal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idactividaddiaria")
    private List<OperacionUnidad> operacionUnidadList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalviajes")
    private int totalviajes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autocierre")    
    private boolean autocierre;
    
    public ActividadDiaria() {
    }

    public ActividadDiaria(Integer id) {
        this.id = id;
    }

    public ActividadDiaria(Integer id, int totalviajes, BigDecimal ingresototal, Date fecha, short estado) {
        this.id = id;
        this.totalviajes = totalviajes;
        this.ingresototal = ingresototal;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTotalviajes() {
        return totalviajes;
    }

    public void setTotalviajes(int totalviajes) {
        this.totalviajes = totalviajes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
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
        if (!(object instanceof ActividadDiaria)) {
            return false;
        }
        ActividadDiaria other = (ActividadDiaria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.ActividadDiaria[ id=" + id + " ]";
    }


    @XmlTransient
    public List<OperacionUnidad> getOperacionUnidadList() {
        return operacionUnidadList;
    }

    public void setOperacionUnidadList(List<OperacionUnidad> operacionUnidadList) {
        this.operacionUnidadList = operacionUnidadList;
    }




    public boolean isAutocierre() {
        return autocierre;
    }

    public void setAutocierre(boolean autocierre) {
        this.autocierre = autocierre;
    }

    public BigDecimal getIngresototal() {
        return ingresototal;
    }

    public void setIngresototal(BigDecimal ingresototal) {
        this.ingresototal = ingresototal;
    }
}
