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
import javax.persistence.Lob;
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
@Table(name = "dimensiones_relevantes", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DimensionesRelevantes.findAll", query = "SELECT d FROM DimensionesRelevantes d"),
    @NamedQuery(name = "DimensionesRelevantes.findByIddimensionesRelevantes", query = "SELECT d FROM DimensionesRelevantes d WHERE d.iddimensionesRelevantes = :iddimensionesRelevantes"),
    @NamedQuery(name = "DimensionesRelevantes.findByNombDimension", query = "SELECT d FROM DimensionesRelevantes d WHERE d.nombDimension = :nombDimension"),
    @NamedQuery(name = "DimensionesRelevantes.findByDescripcion", query = "SELECT d FROM DimensionesRelevantes d WHERE d.descripcion = :descripcion")})
public class DimensionesRelevantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddimensiones_relevantes", nullable = false)
    private Integer iddimensionesRelevantes;
    @Basic(optional = false)
    @Column(name = "nomb_dimension", nullable = false, length = 100)
    private String nombDimension;
    @Lob
    @Column(name = "dimensionrelevfoto")
    private byte[] dimensionrelevfoto;
    @Column(name = "descripcion", length = 1024)
    private String descripcion;
    @JoinTable(name = "dimensiones_relevantes_has_dimensionesantrop", joinColumns = {
        @JoinColumn(name = "dr_iddimensionrelevante", referencedColumnName = "iddimensiones_relevantes", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "da_iddimensiones", referencedColumnName = "iddimensiones", nullable = false)})
    @ManyToMany
    private List<Dimensiones> dimensionesList;
    @ManyToMany(mappedBy = "dimensionesRelevantesList")
    private List<Experimentacion> experimentacionList;

    public DimensionesRelevantes() {
    }

    public DimensionesRelevantes(Integer iddimensionesRelevantes) {
        this.iddimensionesRelevantes = iddimensionesRelevantes;
    }

    public DimensionesRelevantes(Integer iddimensionesRelevantes, String nombDimension) {
        this.iddimensionesRelevantes = iddimensionesRelevantes;
        this.nombDimension = nombDimension;
    }

    public Integer getIddimensionesRelevantes() {
        return iddimensionesRelevantes;
    }

    public void setIddimensionesRelevantes(Integer iddimensionesRelevantes) {
        this.iddimensionesRelevantes = iddimensionesRelevantes;
    }

    public String getNombDimension() {
        return nombDimension;
    }

    public void setNombDimension(String nombDimension) {
        this.nombDimension = nombDimension;
    }

    public byte[] getDimensionrelevfoto() {
        return dimensionrelevfoto;
    }

    public void setDimensionrelevfoto(byte[] dimensionrelevfoto) {
        this.dimensionrelevfoto = dimensionrelevfoto;
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
    public List<Experimentacion> getExperimentacionList() {
        return experimentacionList;
    }

    public void setExperimentacionList(List<Experimentacion> experimentacionList) {
        this.experimentacionList = experimentacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddimensionesRelevantes != null ? iddimensionesRelevantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DimensionesRelevantes)) {
            return false;
        }
        DimensionesRelevantes other = (DimensionesRelevantes) object;
        if ((this.iddimensionesRelevantes == null && other.iddimensionesRelevantes != null) || (this.iddimensionesRelevantes != null && !this.iddimensionesRelevantes.equals(other.iddimensionesRelevantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.DimensionesRelevantes[ iddimensionesRelevantes=" + iddimensionesRelevantes + " ]";
    }
    
}
