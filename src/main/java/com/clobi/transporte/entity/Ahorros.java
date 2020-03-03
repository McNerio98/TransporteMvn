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
@Table(name = "ahorros", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ahorros.findAll", query = "SELECT a FROM Ahorros a")
    , @NamedQuery(name = "Ahorros.findByIdahorro", query = "SELECT a FROM Ahorros a WHERE a.idahorro = :idahorro")
    , @NamedQuery(name = "Ahorros.findBySaldo", query = "SELECT a FROM Ahorros a WHERE a.saldo = :saldo")
    , @NamedQuery(name = "Ahorros.findByEstado", query = "SELECT a FROM Ahorros a WHERE a.estado = :estado")
    , @NamedQuery(name = "Ahorros.findByCantidadestablecida", query = "SELECT a FROM Ahorros a WHERE a.cantidadestablecida = :cantidadestablecida")})
public class Ahorros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idahorro")
    private Integer idahorro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private double saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadestablecida")
    private double cantidadestablecida;
    @JoinColumn(name = "idempleado", referencedColumnName = "dui")
    @ManyToOne(optional = false)
    private Empleado idempleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idahorro")
    private List<Movimiento> movimientoList;

    public Ahorros() {
    }

    public Ahorros(Integer idahorro) {
        this.idahorro = idahorro;
    }

    public Ahorros(Integer idahorro, double saldo, boolean estado, double cantidadestablecida) {
        this.idahorro = idahorro;
        this.saldo = saldo;
        this.estado = estado;
        this.cantidadestablecida = cantidadestablecida;
    }

    public Integer getIdahorro() {
        return idahorro;
    }

    public void setIdahorro(Integer idahorro) {
        this.idahorro = idahorro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getCantidadestablecida() {
        return cantidadestablecida;
    }

    public void setCantidadestablecida(double cantidadestablecida) {
        this.cantidadestablecida = cantidadestablecida;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idahorro != null ? idahorro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ahorros)) {
            return false;
        }
        Ahorros other = (Ahorros) object;
        if ((this.idahorro == null && other.idahorro != null) || (this.idahorro != null && !this.idahorro.equals(other.idahorro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Ahorros[ idahorro=" + idahorro + " ]";
    }
    
}
