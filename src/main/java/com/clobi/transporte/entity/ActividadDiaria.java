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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "actividadesdiarias", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActividadDiaria.findAll", query = "SELECT a FROM ActividadDiaria a")
    , @NamedQuery(name = "ActividadDiaria.findByIdactividad", query = "SELECT a FROM ActividadDiaria a WHERE a.idactividad = :idactividad")
    , @NamedQuery(name = "ActividadDiaria.findByTotalviajes", query = "SELECT a FROM ActividadDiaria a WHERE a.totalviajes = :totalviajes")
    , @NamedQuery(name = "ActividadDiaria.findByContador", query = "SELECT a FROM ActividadDiaria a WHERE a.contador = :contador")
    , @NamedQuery(name = "ActividadDiaria.findByIngresototal", query = "SELECT a FROM ActividadDiaria a WHERE a.ingresototal = :ingresototal")})
public class ActividadDiaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactividad")
    private Integer idactividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalviajes")
    private int totalviajes;
    @Column(name = "contador")
    private Integer contador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ingresototal")
    private Double ingresototal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idactividad")
    private List<Nota> notaList;
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Unidad placa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idactividad")
    private List<AnticiposByActividad> anticiposByActividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idactividad")
    private List<PagosEmpleado> pagosEmpleadoList;

    public ActividadDiaria() {
    }

    public ActividadDiaria(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public ActividadDiaria(Integer idactividad, int totalviajes) {
        this.idactividad = idactividad;
        this.totalviajes = totalviajes;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public int getTotalviajes() {
        return totalviajes;
    }

    public void setTotalviajes(int totalviajes) {
        this.totalviajes = totalviajes;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Double getIngresototal() {
        return ingresototal;
    }

    public void setIngresototal(Double ingresototal) {
        this.ingresototal = ingresototal;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    public Unidad getPlaca() {
        return placa;
    }

    public void setPlaca(Unidad placa) {
        this.placa = placa;
    }

    @XmlTransient
    public List<AnticiposByActividad> getAnticiposByActividadList() {
        return anticiposByActividadList;
    }

    public void setAnticiposByActividadList(List<AnticiposByActividad> anticiposByActividadList) {
        this.anticiposByActividadList = anticiposByActividadList;
    }

    @XmlTransient
    public List<PagosEmpleado> getPagosEmpleadoList() {
        return pagosEmpleadoList;
    }

    public void setPagosEmpleadoList(List<PagosEmpleado> pagosEmpleadoList) {
        this.pagosEmpleadoList = pagosEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividad != null ? idactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadDiaria)) {
            return false;
        }
        ActividadDiaria other = (ActividadDiaria) object;
        if ((this.idactividad == null && other.idactividad != null) || (this.idactividad != null && !this.idactividad.equals(other.idactividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.ActividadDiaria[ idactividad=" + idactividad + " ]";
    }
    
}
