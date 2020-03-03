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
@Table(name = "recordatorios", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recordatorio.findAll", query = "SELECT r FROM Recordatorio r")
    , @NamedQuery(name = "Recordatorio.findByIdrecordatorio", query = "SELECT r FROM Recordatorio r WHERE r.idrecordatorio = :idrecordatorio")
    , @NamedQuery(name = "Recordatorio.findByTitulo", query = "SELECT r FROM Recordatorio r WHERE r.titulo = :titulo")
    , @NamedQuery(name = "Recordatorio.findByDescripcion", query = "SELECT r FROM Recordatorio r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Recordatorio.findByFechacreacion", query = "SELECT r FROM Recordatorio r WHERE r.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "Recordatorio.findByEstado", query = "SELECT r FROM Recordatorio r WHERE r.estado = :estado")})
public class Recordatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrecordatorio")
    private Integer idrecordatorio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;

    public Recordatorio() {
    }

    public Recordatorio(Integer idrecordatorio) {
        this.idrecordatorio = idrecordatorio;
    }

    public Recordatorio(Integer idrecordatorio, String titulo, String descripcion, Date fechacreacion, int estado) {
        this.idrecordatorio = idrecordatorio;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechacreacion = fechacreacion;
        this.estado = estado;
    }

    public Integer getIdrecordatorio() {
        return idrecordatorio;
    }

    public void setIdrecordatorio(Integer idrecordatorio) {
        this.idrecordatorio = idrecordatorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecordatorio != null ? idrecordatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recordatorio)) {
            return false;
        }
        Recordatorio other = (Recordatorio) object;
        if ((this.idrecordatorio == null && other.idrecordatorio != null) || (this.idrecordatorio != null && !this.idrecordatorio.equals(other.idrecordatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Recordatorio[ idrecordatorio=" + idrecordatorio + " ]";
    }
    
}
