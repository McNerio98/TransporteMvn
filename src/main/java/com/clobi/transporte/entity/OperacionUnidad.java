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
 * @author Tinkpad PC
 */
@Entity
@Table(name = "operacionesunidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperacionUnidad.findAll", query = "SELECT o FROM OperacionUnidad o")
    , @NamedQuery(name = "OperacionUnidad.findById", query = "SELECT o FROM OperacionUnidad o WHERE o.id = :id")
    , @NamedQuery(name = "OperacionUnidad.findByEstado", query = "SELECT o FROM OperacionUnidad o WHERE o.estado = :estado")
    , @NamedQuery(name = "OperacionUnidad.findByActividad", query = "SELECT o FROM OperacionUnidad o WHERE o.idactividaddiaria = :actividad")
    , @NamedQuery(name = "OperacionUnidad.findByViajesrealizados", query = "SELECT o FROM OperacionUnidad o WHERE o.viajesrealizados = :viajesrealizados")
    , @NamedQuery(name = "OperacionUnidad.findByContador", query = "SELECT o FROM OperacionUnidad o WHERE o.contador = :contador")})
public class OperacionUnidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "viajesrealizados")
    private short viajesrealizados;
    @Column(name = "contador")
    private Integer contador;
    @JoinColumn(name = "idactividaddiaria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ActividadDiaria idactividaddiaria;
    @JoinColumn(name = "idauxiliar", referencedColumnName = "dui")
    @ManyToOne
    private Empleado idauxiliar;
    @JoinColumn(name = "idconductor", referencedColumnName = "dui")
    @ManyToOne(optional = false)
    private Empleado idconductor;
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Unidad placa;

    public OperacionUnidad() {
    }

    public OperacionUnidad(Integer id) {
        this.id = id;
    }

    public OperacionUnidad(Integer id, short estado, short viajesrealizados) {
        this.id = id;
        this.estado = estado;
        this.viajesrealizados = viajesrealizados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getViajesrealizados() {
        return viajesrealizados;
    }

    public void setViajesrealizados(short viajesrealizados) {
        this.viajesrealizados = viajesrealizados;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public ActividadDiaria getIdactividaddiaria() {
        return idactividaddiaria;
    }

    public void setIdactividaddiaria(ActividadDiaria idactividaddiaria) {
        this.idactividaddiaria = idactividaddiaria;
    }

    public Empleado getIdauxiliar() {
        return idauxiliar;
    }

    public void setIdauxiliar(Empleado idauxiliar) {
        this.idauxiliar = idauxiliar;
    }

    public Empleado getIdconductor() {
        return idconductor;
    }

    public void setIdconductor(Empleado idconductor) {
        this.idconductor = idconductor;
    }

    public Unidad getPlaca() {
        return placa;
    }

    public void setPlaca(Unidad placa) {
        this.placa = placa;
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
        if (!(object instanceof OperacionUnidad)) {
            return false;
        }
        OperacionUnidad other = (OperacionUnidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.OperacionUnidad[ id=" + id + " ]";
    }

}
