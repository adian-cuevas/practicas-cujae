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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "experimentacion", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experimentacion.findAll", query = "SELECT e FROM Experimentacion e"),
    @NamedQuery(name = "Experimentacion.findByIdExperimentacion", query = "SELECT e FROM Experimentacion e WHERE e.idExperimentacion = :idExperimentacion"),
    @NamedQuery(name = "Experimentacion.findByDescripcion", query = "SELECT e FROM Experimentacion e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Experimentacion.findByNombre", query = "SELECT e FROM Experimentacion e WHERE e.nombre = :nombre")})
public class Experimentacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_experimentacion", nullable = false)
    private Integer idExperimentacion;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 1024)
    private String descripcion;
    @Lob
    @Column(name = "puestotrabaj")
    private byte[] puestotrabaj;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @ManyToMany(mappedBy = "experimentacionList")
    private List<Posicionespuestotrabajo> posicionespuestotrabajoList;
    @JoinTable(name = "puesto_dimensiones", joinColumns = {
        @JoinColumn(name = "experimentacion_idexperimentacion", referencedColumnName = "id_experimentacion", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "dimensionrelev_iddimensionrelev", referencedColumnName = "iddimensiones_relevantes", nullable = false)})
    @ManyToMany
    private List<DimensionesRelevantes> dimensionesRelevantesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experimentacionidExperimentacion")
    private List<RespuestaExperimentacion> respuestaExperimentacionList;

    public Experimentacion() {
    }

    public Experimentacion(Integer idExperimentacion) {
        this.idExperimentacion = idExperimentacion;
    }

    public Experimentacion(Integer idExperimentacion, String descripcion) {
        this.idExperimentacion = idExperimentacion;
        this.descripcion = descripcion;
    }

    public Integer getIdExperimentacion() {
        return idExperimentacion;
    }

    public void setIdExperimentacion(Integer idExperimentacion) {
        this.idExperimentacion = idExperimentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getPuestotrabaj() {
        return puestotrabaj;
    }

    public void setPuestotrabaj(byte[] puestotrabaj) {
        this.puestotrabaj = puestotrabaj;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Posicionespuestotrabajo> getPosicionespuestotrabajoList() {
        return posicionespuestotrabajoList;
    }

    public void setPosicionespuestotrabajoList(List<Posicionespuestotrabajo> posicionespuestotrabajoList) {
        this.posicionespuestotrabajoList = posicionespuestotrabajoList;
    }

    @XmlTransient
    public List<DimensionesRelevantes> getDimensionesRelevantesList() {
        return dimensionesRelevantesList;
    }

    public void setDimensionesRelevantesList(List<DimensionesRelevantes> dimensionesRelevantesList) {
        this.dimensionesRelevantesList = dimensionesRelevantesList;
    }

    @XmlTransient
    public List<RespuestaExperimentacion> getRespuestaExperimentacionList() {
        return respuestaExperimentacionList;
    }

    public void setRespuestaExperimentacionList(List<RespuestaExperimentacion> respuestaExperimentacionList) {
        this.respuestaExperimentacionList = respuestaExperimentacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExperimentacion != null ? idExperimentacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experimentacion)) {
            return false;
        }
        Experimentacion other = (Experimentacion) object;
        if ((this.idExperimentacion == null && other.idExperimentacion != null) || (this.idExperimentacion != null && !this.idExperimentacion.equals(other.idExperimentacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Experimentacion[ idExperimentacion=" + idExperimentacion + " ]";
    }
    
}
