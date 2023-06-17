/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "error", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Error.findAll", query = "SELECT e FROM Error e"),
    @NamedQuery(name = "Error.findByIdError", query = "SELECT e FROM Error e WHERE e.idError = :idError"),
    @NamedQuery(name = "Error.findByDescripcion", query = "SELECT e FROM Error e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Error.findByCantidad", query = "SELECT e FROM Error e WHERE e.cantidad = :cantidad")})
public class Error implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idError", nullable = false)
    private Integer idError;
    @Column(name = "descripcion", length = 10)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @JoinColumn(name = "respuesta_experimentacionid", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RespuestaExperimentacion respuestaExperimentacionid;

    public Error() {
    }

    public Error(Integer idError) {
        this.idError = idError;
    }

    public Error(Integer idError, int cantidad) {
        this.idError = idError;
        this.cantidad = cantidad;
    }

    public Integer getIdError() {
        return idError;
    }

    public void setIdError(Integer idError) {
        this.idError = idError;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public RespuestaExperimentacion getRespuestaExperimentacionid() {
        return respuestaExperimentacionid;
    }

    public void setRespuestaExperimentacionid(RespuestaExperimentacion respuestaExperimentacionid) {
        this.respuestaExperimentacionid = respuestaExperimentacionid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idError != null ? idError.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Error)) {
            return false;
        }
        Error other = (Error) object;
        if ((this.idError == null && other.idError != null) || (this.idError != null && !this.idError.equals(other.idError))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Error[ idError=" + idError + " ]";
    }
    
}
