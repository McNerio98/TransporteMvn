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
@Entity
@Table(name = "categorias", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByIdcategoria", query = "SELECT c FROM Categoria c WHERE c.idcategoria = :idcategoria")
    , @NamedQuery(name = "Categoria.findByCategoria", query = "SELECT c FROM Categoria c WHERE c.categoria = :categoria")
    , @NamedQuery(name = "Categoria.findByTipocategoria", query = "SELECT c FROM Categoria c WHERE c.tipocategoria = :tipocategoria")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private Integer idcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "categoria")
    private String categoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipocategoria")
    private String tipocategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategoria")
    private List<ActividadFinanciera> actividadFinancieraList;

    public Categoria() {
    }

    public Categoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Categoria(Integer idcategoria, String categoria, String tipocategoria) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
        this.tipocategoria = tipocategoria;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipocategoria() {
        return tipocategoria;
    }

    public void setTipocategoria(String tipocategoria) {
        this.tipocategoria = tipocategoria;
    }

    @XmlTransient
    public List<ActividadFinanciera> getActividadFinancieraList() {
        return actividadFinancieraList;
    }

    public void setActividadFinancieraList(List<ActividadFinanciera> actividadFinancieraList) {
        this.actividadFinancieraList = actividadFinancieraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Categoria[ idcategoria=" + idcategoria + " ]";
    }
    
}
