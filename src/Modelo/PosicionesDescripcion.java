/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "posiciones_descripcion", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosicionesDescripcion.findAll", query = "SELECT p FROM PosicionesDescripcion p"),
    @NamedQuery(name = "PosicionesDescripcion.findByIdposicionesDescripcion", query = "SELECT p FROM PosicionesDescripcion p WHERE p.idposicionesDescripcion = :idposicionesDescripcion"),
    @NamedQuery(name = "PosicionesDescripcion.findByDescripcion", query = "SELECT p FROM PosicionesDescripcion p WHERE p.descripcion = :descripcion")})
public class PosicionesDescripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idposiciones_descripcion", nullable = false)
    private Integer idposicionesDescripcion;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 45)
    private String descripcion;
    @OneToMany(mappedBy = "idposiciondescripcion")
    private List<Dimensiones> dimensionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idposicionesDescripcion")
    private List<SujetoPosiciones> sujetoPosicionesList;

    public PosicionesDescripcion() {
    }

    public PosicionesDescripcion(Integer idposicionesDescripcion) {
        this.idposicionesDescripcion = idposicionesDescripcion;
    }

    public PosicionesDescripcion(Integer idposicionesDescripcion, String descripcion) {
        this.idposicionesDescripcion = idposicionesDescripcion;
        this.descripcion = descripcion;
    }

    public Integer getIdposicionesDescripcion() {
        return idposicionesDescripcion;
    }

    public void setIdposicionesDescripcion(Integer idposicionesDescripcion) {
        this.idposicionesDescripcion = idposicionesDescripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Dimensiones> getDimensionesList() {
        return dimensionesList;
    }

    public void setDimensionesList(List<Dimensiones> dimensionesList) {
        this.dimensionesList = dimensionesList;
    }

    @XmlTransient
    public List<SujetoPosiciones> getSujetoPosicionesList() {
        return sujetoPosicionesList;
    }

    public void setSujetoPosicionesList(List<SujetoPosiciones> sujetoPosicionesList) {
        this.sujetoPosicionesList = sujetoPosicionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idposicionesDescripcion != null ? idposicionesDescripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionesDescripcion)) {
            return false;
        }
        PosicionesDescripcion other = (PosicionesDescripcion) object;
        if ((this.idposicionesDescripcion == null && other.idposicionesDescripcion != null) || (this.idposicionesDescripcion != null && !this.idposicionesDescripcion.equals(other.idposicionesDescripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.PosicionesDescripcion[ idposicionesDescripcion=" + idposicionesDescripcion + " ]";
    }
    
}
