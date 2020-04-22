/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@Table(name = "documentosbyempleados", catalog = "transporte", schema = "public")

public class DocumentoByEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddocumento")
    private Integer iddocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiracion")
    @Temporal(TemporalType.DATE)
    private Date expiracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "filepath")
    private String filepath;
    @Size(max = 5)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idempleado", referencedColumnName = "dui")
    @ManyToOne(optional = false)
    private Empleado idempleado;

    public DocumentoByEmpleado() {
    }

    public DocumentoByEmpleado(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public DocumentoByEmpleado(Integer iddocumento, String numero, Date expiracion, String filepath) {
        this.iddocumento = iddocumento;
        this.numero = numero;
        this.expiracion = expiracion;
        this.filepath = filepath;
    }

    public Integer getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Date expiracion) {
        this.expiracion = expiracion;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (iddocumento != null ? iddocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoByEmpleado)) {
            return false;
        }
        DocumentoByEmpleado other = (DocumentoByEmpleado) object;
        if ((this.iddocumento == null && other.iddocumento != null) || (this.iddocumento != null && !this.iddocumento.equals(other.iddocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.transporte.entity.DocumentoByEmpleado[ iddocumento=" + iddocumento + " ]";
    }
    
    public String tipoCase(){
        String result = "any";
            switch(this.tipo){
                case "lic":
                    result = "Licencia de Conducir";
                    break;
                case "car":
                    result = "Carnet de Motorista";
                    break;
            }
        return result;
    }
    
}
