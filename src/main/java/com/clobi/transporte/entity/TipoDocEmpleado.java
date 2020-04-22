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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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


public class TipoDocEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idtipodoc;
    private String tipodoc;
    private String descripcion;
    private List<DocumentoByEmpleado> documentoByEmpleadoList;

    public TipoDocEmpleado() {
    }

    public TipoDocEmpleado(Integer idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public TipoDocEmpleado(Integer idtipodoc, String tipodoc) {
        this.idtipodoc = idtipodoc;
        this.tipodoc = tipodoc;
    }

    public Integer getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(Integer idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DocumentoByEmpleado> getDocumentoByEmpleadoList() {
        return documentoByEmpleadoList;
    }

    public void setDocumentoByEmpleadoList(List<DocumentoByEmpleado> documentoByEmpleadoList) {
        this.documentoByEmpleadoList = documentoByEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipodoc != null ? idtipodoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocEmpleado)) {
            return false;
        }
        TipoDocEmpleado other = (TipoDocEmpleado) object;
        if ((this.idtipodoc == null && other.idtipodoc != null) || (this.idtipodoc != null && !this.idtipodoc.equals(other.idtipodoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.TipoDocEmpleado[ idtipodoc=" + idtipodoc + " ]";
    }
    
}
