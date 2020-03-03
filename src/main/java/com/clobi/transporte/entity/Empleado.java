/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "empleados", catalog = "transporte", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByDui", query = "SELECT e FROM Empleado e WHERE e.dui = :dui")
    , @NamedQuery(name = "Empleado.findByNombres", query = "SELECT e FROM Empleado e WHERE e.nombres = :nombres")
    , @NamedQuery(name = "Empleado.findByApellidos", query = "SELECT e FROM Empleado e WHERE e.apellidos = :apellidos")
    , @NamedQuery(name = "Empleado.findByEdad", query = "SELECT e FROM Empleado e WHERE e.edad = :edad")
    , @NamedQuery(name = "Empleado.findByEstadocivil", query = "SELECT e FROM Empleado e WHERE e.estadocivil = :estadocivil")
    , @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleado.findByTipo", query = "SELECT e FROM Empleado e WHERE e.tipo = :tipo")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "dui")
    private String dui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    @Temporal(TemporalType.DATE)
    private Date edad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "estadocivil")
    private String estadocivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempleado")
    private List<Ahorros> ahorrosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempleado")
    private List<UnidadByEmpleado> unidadByEmpleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempleado")
    private List<DocumentoByEmpleado> documentoByEmpleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempleado")
    private List<PagosEmpleado> pagosEmpleadoList;

    public Empleado() {
    }

    public Empleado(String dui) {
        this.dui = dui;
    }

    public Empleado(String dui, String nombres, String apellidos, Date edad, String estadocivil, String telefono, String tipo) {
        this.dui = dui;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.estadocivil = estadocivil;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getEdad() {
        return edad;
    }

    public void setEdad(Date edad) {
        this.edad = edad;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Ahorros> getAhorrosList() {
        return ahorrosList;
    }

    public void setAhorrosList(List<Ahorros> ahorrosList) {
        this.ahorrosList = ahorrosList;
    }

    @XmlTransient
    public List<UnidadByEmpleado> getUnidadByEmpleadoList() {
        return unidadByEmpleadoList;
    }

    public void setUnidadByEmpleadoList(List<UnidadByEmpleado> unidadByEmpleadoList) {
        this.unidadByEmpleadoList = unidadByEmpleadoList;
    }

    @XmlTransient
    public List<DocumentoByEmpleado> getDocumentoByEmpleadoList() {
        return documentoByEmpleadoList;
    }

    public void setDocumentoByEmpleadoList(List<DocumentoByEmpleado> documentoByEmpleadoList) {
        this.documentoByEmpleadoList = documentoByEmpleadoList;
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
        hash += (dui != null ? dui.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.dui == null && other.dui != null) || (this.dui != null && !this.dui.equals(other.dui))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.Empleado[ dui=" + dui + " ]";
    }
    
}
