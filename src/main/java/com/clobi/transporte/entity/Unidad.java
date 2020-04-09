/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "unidades", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidad.findAll", query = "SELECT u FROM Unidad u")
    , @NamedQuery(name = "Unidad.findByPlaca", query = "SELECT u FROM Unidad u WHERE u.placa = :placa")
    , @NamedQuery(name = "Unidad.findByNumerounidad", query = "SELECT u FROM Unidad u WHERE u.numerounidad = :numerounidad")})
public class Unidad implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placa")
    private List<OperacionUnidad> operacionUnidadList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numerounidad")
    private int numerounidad;
    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placa")
    private List<ActividadDiaria> actividadDiariaList;
    */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placa")
    private List<UnidadByEmpleado> unidadByEmpleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placa")
    private List<DocumentoByUnidad> documentoByUnidadList;
    @JoinColumn(name = "idtarjetacirculacion", referencedColumnName = "idtarjeta")
    @ManyToOne(optional = false)
    private TarjetaCirculacion idtarjetacirculacion;

    public Unidad() {
    }

    public Unidad(String placa) {
        this.placa = placa;
    }

    public Unidad(String placa, int numerounidad) {
        this.placa = placa;
        this.numerounidad = numerounidad;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getNumerounidad() {
        return numerounidad;
    }

    public void setNumerounidad(int numerounidad) {
        this.numerounidad = numerounidad;
    }

    /*
    @XmlTransient
    public List<ActividadDiaria> getActividadDiariaList() {
        return actividadDiariaList;
    }

    public void setActividadDiariaList(List<ActividadDiaria> actividadDiariaList) {
        this.actividadDiariaList = actividadDiariaList;
    }
*/

    @XmlTransient
    public List<UnidadByEmpleado> getUnidadByEmpleadoList() {
        return unidadByEmpleadoList;
    }

    public void setUnidadByEmpleadoList(List<UnidadByEmpleado> unidadByEmpleadoList) {
        this.unidadByEmpleadoList = unidadByEmpleadoList;
    }

    @XmlTransient
    public List<DocumentoByUnidad> getDocumentoByUnidadList() {
        return documentoByUnidadList;
    }

    public void setDocumentoByUnidadList(List<DocumentoByUnidad> documentoByUnidadList) {
        this.documentoByUnidadList = documentoByUnidadList;
    }

    public TarjetaCirculacion getIdtarjetacirculacion() {
        return idtarjetacirculacion;
    }

    public void setIdtarjetacirculacion(TarjetaCirculacion idtarjetacirculacion) {
        this.idtarjetacirculacion = idtarjetacirculacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidad)) {
            return false;
        }
        Unidad other = (Unidad) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Unidad[ placa=" + placa + " ]";
    }

    @XmlTransient
    public List<OperacionUnidad> getOperacionUnidadList() {
        return operacionUnidadList;
    }

    public void setOperacionUnidadList(List<OperacionUnidad> operacionUnidadList) {
        this.operacionUnidadList = operacionUnidadList;
    }
    
}
