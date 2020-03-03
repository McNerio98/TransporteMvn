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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "unidadesbyempleados", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadByEmpleado.findAll", query = "SELECT u FROM UnidadByEmpleado u")
    , @NamedQuery(name = "UnidadByEmpleado.findByIdube", query = "SELECT u FROM UnidadByEmpleado u WHERE u.idube = :idube")
    , @NamedQuery(name = "UnidadByEmpleado.findByTipocontrato", query = "SELECT u FROM UnidadByEmpleado u WHERE u.tipocontrato = :tipocontrato")})
public class UnidadByEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idube")
    private Integer idube;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipocontrato")
    private String tipocontrato;
    @JoinColumn(name = "idempleado", referencedColumnName = "dui")
    @ManyToOne(optional = false)
    private Empleado idempleado;
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Unidad placa;

    public UnidadByEmpleado() {
    }

    public UnidadByEmpleado(Integer idube) {
        this.idube = idube;
    }

    public UnidadByEmpleado(Integer idube, String tipocontrato) {
        this.idube = idube;
        this.tipocontrato = tipocontrato;
    }

    public Integer getIdube() {
        return idube;
    }

    public void setIdube(Integer idube) {
        this.idube = idube;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
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
        hash += (idube != null ? idube.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadByEmpleado)) {
            return false;
        }
        UnidadByEmpleado other = (UnidadByEmpleado) object;
        if ((this.idube == null && other.idube != null) || (this.idube != null && !this.idube.equals(other.idube))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.UnidadByEmpleado[ idube=" + idube + " ]";
    }
    
}
