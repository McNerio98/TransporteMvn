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

    @Basic(optional = false)
    @NotNull
    @Column(name = "ingreso")
    private BigDecimal ingreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pagoconductor")
    private BigDecimal pagoconductor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pagoauxiliar")
    private BigDecimal pagoauxiliar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idoperacion")
    private List<Pago> pagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idoperacion")
    private List<Anticipo> anticipoList;

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
    @Column(name = "contadorold")
    private Integer contadorold;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "autocierre")    
    private boolean autocierre;
    private static final long serialVersionUID = 1L;

    public OperacionUnidad() {
    }

    public OperacionUnidad(Integer id) {
        this.id = id;
    }

    public OperacionUnidad(Integer id, short estado, short viajesrealizados, BigDecimal ingreso, BigDecimal pagoconductor, BigDecimal pagoauxiliar) {
        this.id = id;
        this.estado = estado;
        this.viajesrealizados = viajesrealizados;
        this.ingreso = ingreso;
        this.pagoconductor = pagoconductor;
        this.pagoauxiliar = pagoauxiliar;
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


    @XmlTransient
    public List<Anticipo> getAnticipoList() {
        return anticipoList;
    }

    public void setAnticipoList(List<Anticipo> anticipoList) {
        this.anticipoList = anticipoList;
    }


    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public boolean isAutocierre() {
        return autocierre;
    }

    public void setAutocierre(boolean autocierre) {
        this.autocierre = autocierre;
    }

    public Integer getContadorold() {
        return contadorold;
    }

    public void setContadorold(Integer contadorold) {
        this.contadorold = contadorold;
    }

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public BigDecimal getPagoconductor() {
        return pagoconductor;
    }

    public void setPagoconductor(BigDecimal pagoconductor) {
        this.pagoconductor = pagoconductor;
    }

    public BigDecimal getPagoauxiliar() {
        return pagoauxiliar;
    }

    public void setPagoauxiliar(BigDecimal pagoauxiliar) {
        this.pagoauxiliar = pagoauxiliar;
    }
    
    
    
}
