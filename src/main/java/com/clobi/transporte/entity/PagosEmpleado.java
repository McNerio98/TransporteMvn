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
 * @author desarrollo
 */
@Entity
@Table(name = "pagosempleados", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagosEmpleado.findAll", query = "SELECT p FROM PagosEmpleado p")
    , @NamedQuery(name = "PagosEmpleado.findByIdpago", query = "SELECT p FROM PagosEmpleado p WHERE p.idpago = :idpago")
    , @NamedQuery(name = "PagosEmpleado.findBySubtotal", query = "SELECT p FROM PagosEmpleado p WHERE p.subtotal = :subtotal")
    , @NamedQuery(name = "PagosEmpleado.findByMontoahorro", query = "SELECT p FROM PagosEmpleado p WHERE p.montoahorro = :montoahorro")
    , @NamedQuery(name = "PagosEmpleado.findByMontopago", query = "SELECT p FROM PagosEmpleado p WHERE p.montopago = :montopago")
    , @NamedQuery(name = "PagosEmpleado.findByFechapago", query = "SELECT p FROM PagosEmpleado p WHERE p.fechapago = :fechapago")})
public class PagosEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpago")
    private Integer idpago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private double subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montoahorro")
    private double montoahorro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montopago")
    private double montopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechapago")
    private double fechapago;
    @JoinColumn(name = "idactividad", referencedColumnName = "idactividad")
    @ManyToOne(optional = false)
    private ActividadDiaria idactividad;
    @JoinColumn(name = "idempleado", referencedColumnName = "dui")
    @ManyToOne(optional = false)
    private Empleado idempleado;

    public PagosEmpleado() {
    }

    public PagosEmpleado(Integer idpago) {
        this.idpago = idpago;
    }

    public PagosEmpleado(Integer idpago, double subtotal, double montoahorro, double montopago, double fechapago) {
        this.idpago = idpago;
        this.subtotal = subtotal;
        this.montoahorro = montoahorro;
        this.montopago = montopago;
        this.fechapago = fechapago;
    }

    public Integer getIdpago() {
        return idpago;
    }

    public void setIdpago(Integer idpago) {
        this.idpago = idpago;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getMontoahorro() {
        return montoahorro;
    }

    public void setMontoahorro(double montoahorro) {
        this.montoahorro = montoahorro;
    }

    public double getMontopago() {
        return montopago;
    }

    public void setMontopago(double montopago) {
        this.montopago = montopago;
    }

    public double getFechapago() {
        return fechapago;
    }

    public void setFechapago(double fechapago) {
        this.fechapago = fechapago;
    }

    public ActividadDiaria getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(ActividadDiaria idactividad) {
        this.idactividad = idactividad;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpago != null ? idpago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagosEmpleado)) {
            return false;
        }
        PagosEmpleado other = (PagosEmpleado) object;
        if ((this.idpago == null && other.idpago != null) || (this.idpago != null && !this.idpago.equals(other.idpago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.PagosEmpleado[ idpago=" + idpago + " ]";
    }
    
}
