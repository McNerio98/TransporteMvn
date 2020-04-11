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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALEX
 */
@Entity
@Table(name = "empleadoscontratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpleadosContrato.findAll", query = "SELECT e FROM EmpleadosContrato e")
    , @NamedQuery(name = "EmpleadosContrato.findByEmpleado", query = "SELECT e FROM EmpleadosContrato e WHERE e.empleado = :empleado")
    , @NamedQuery(name = "EmpleadosContrato.findByTipocontrato", query = "SELECT e FROM EmpleadosContrato e WHERE e.tipocontrato = :tipocontrato")})
public class EmpleadosContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "empleado")
    private String empleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipocontrato")
    private int tipocontrato;
    @JoinColumn(name = "empleado", referencedColumnName = "dui", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Empleado empleado1;

    public EmpleadosContrato() {
    }

    public EmpleadosContrato(String empleado) {
        this.empleado = empleado;
    }

    public EmpleadosContrato(String empleado, int tipocontrato) {
        this.empleado = empleado;
        this.tipocontrato = tipocontrato;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(int tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleado != null ? empleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadosContrato)) {
            return false;
        }
        EmpleadosContrato other = (EmpleadosContrato) object;
        if ((this.empleado == null && other.empleado != null) || (this.empleado != null && !this.empleado.equals(other.empleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.EmpleadosContrato[ empleado=" + empleado + " ]";
    }
    
}
