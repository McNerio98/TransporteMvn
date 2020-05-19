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
@Table(name = "documentosbyunidades", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoByUnidad.findAll", query = "SELECT d FROM DocumentoByUnidad d")
    , @NamedQuery(name = "DocumentoByUnidad.findByIddu", query = "SELECT d FROM DocumentoByUnidad d WHERE d.iddu = :iddu")
    , @NamedQuery(name = "DocumentoByUnidad.findByFilepath", query = "SELECT d FROM DocumentoByUnidad d WHERE d.filepath = :filepath")
    , @NamedQuery(name = "DocumentoByUnidad.findByEspecificacion", query = "SELECT d FROM DocumentoByUnidad d WHERE d.especificacion = :especificacion")
    ,@NamedQuery(name = "DocumentoByUnidad.findByPlaca", query = "SELECT d FROM DocumentoByUnidad d WHERE d.placa.placa = :placa")})
public class DocumentoByUnidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddu")
    private Integer iddu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "filepath")
    private String filepath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "especificacion")
    private String especificacion;
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Unidad placa;

    public DocumentoByUnidad() {
    }

    public DocumentoByUnidad(Integer iddu) {
        this.iddu = iddu;
    }

    public DocumentoByUnidad(Integer iddu, String filepath, String especificacion) {
        this.iddu = iddu;
        this.filepath = filepath;
        this.especificacion = especificacion;
    }

    public Integer getIddu() {
        return iddu;
    }

    public void setIddu(Integer iddu) {
        this.iddu = iddu;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
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
        hash += (iddu != null ? iddu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoByUnidad)) {
            return false;
        }
        DocumentoByUnidad other = (DocumentoByUnidad) object;
        if ((this.iddu == null && other.iddu != null) || (this.iddu != null && !this.iddu.equals(other.iddu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.DocumentoByUnidad[ iddu=" + iddu + " ]";
    }
    
}
