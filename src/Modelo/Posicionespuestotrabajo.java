/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "posicionespuestotrabajo", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posicionespuestotrabajo.findAll", query = "SELECT p FROM Posicionespuestotrabajo p"),
    @NamedQuery(name = "Posicionespuestotrabajo.findByIdposicionespuesto", query = "SELECT p FROM Posicionespuestotrabajo p WHERE p.idposicionespuesto = :idposicionespuesto"),
    @NamedQuery(name = "Posicionespuestotrabajo.findByDescripcion", query = "SELECT p FROM Posicionespuestotrabajo p WHERE p.descripcion = :descripcion")})
public class Posicionespuestotrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idposicionespuesto", nullable = false)
    private Integer idposicionespuesto;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    @JoinTable(name = "cestudio_ppuesto", joinColumns = {
        @JoinColumn(name = "ppuesto_idposicionpuesto", referencedColumnName = "idposicionespuesto", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "cestudio_idexperimentacion", referencedColumnName = "id_experimentacion", nullable = false)})
    @ManyToMany
    private List<Experimentacion> experimentacionList;

    public Posicionespuestotrabajo() {
    }

    public Posicionespuestotrabajo(Integer idposicionespuesto) {
        this.idposicionespuesto = idposicionespuesto;
    }

    public Posicionespuestotrabajo(Integer idposicionespuesto, String descripcion) {
        this.idposicionespuesto = idposicionespuesto;
        this.descripcion = descripcion;
    }

    public Integer getIdposicionespuesto() {
        return idposicionespuesto;
    }

    public void setIdposicionespuesto(Integer idposicionespuesto) {
        this.idposicionespuesto = idposicionespuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Experimentacion> getExperimentacionList() {
        return experimentacionList;
    }

    public void setExperimentacionList(List<Experimentacion> experimentacionList) {
        this.experimentacionList = experimentacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idposicionespuesto != null ? idposicionespuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posicionespuestotrabajo)) {
            return false;
        }
        Posicionespuestotrabajo other = (Posicionespuestotrabajo) object;
        if ((this.idposicionespuesto == null && other.idposicionespuesto != null) || (this.idposicionespuesto != null && !this.idposicionespuesto.equals(other.idposicionespuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Posicionespuestotrabajo[ idposicionespuesto=" + idposicionespuesto + " ]";
    }
    
}
