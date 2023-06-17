/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "respuesta_experimentacion", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaExperimentacion.findAll", query = "SELECT r FROM RespuestaExperimentacion r"),
    @NamedQuery(name = "RespuestaExperimentacion.findById", query = "SELECT r FROM RespuestaExperimentacion r WHERE r.id = :id"),
    @NamedQuery(name = "RespuestaExperimentacion.findByFecha", query = "SELECT r FROM RespuestaExperimentacion r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RespuestaExperimentacion.findByCantTotalErrores", query = "SELECT r FROM RespuestaExperimentacion r WHERE r.cantTotalErrores = :cantTotalErrores")})
public class RespuestaExperimentacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "cant_total_errores")
    private Integer cantTotalErrores;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "respuestaExperimentacionid")
    private List<Error> errorList;
    @JoinColumn(name = "usuarioidusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioidusuario;
    @JoinColumn(name = "experimentacionid_experimentacion", referencedColumnName = "id_experimentacion", nullable = false)
    @ManyToOne(optional = false)
    private Experimentacion experimentacionidExperimentacion;
    @Column(name = "evaluacion")
    private Integer evaluacion;
    @Column(name = "finalizada")
    private  boolean finalizada;

    public RespuestaExperimentacion() {
    }

    public RespuestaExperimentacion(Integer id) {
        this.id = id;
    }

    public RespuestaExperimentacion(Integer id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantTotalErrores() {
        return cantTotalErrores;
    }

    public void setCantTotalErrores(Integer cantTotalErrores) {
        this.cantTotalErrores = cantTotalErrores;
    }

    public Integer getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Integer evaluacion) {
        this.evaluacion = evaluacion;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
    
    
    
    @XmlTransient
    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }

    public Usuario getUsuarioidusuario() {
        return usuarioidusuario;
    }

    public void setUsuarioidusuario(Usuario usuarioidusuario) {
        this.usuarioidusuario = usuarioidusuario;
    }

    public Experimentacion getExperimentacionidExperimentacion() {
        return experimentacionidExperimentacion;
    }

    public void setExperimentacionidExperimentacion(Experimentacion experimentacionidExperimentacion) {
        this.experimentacionidExperimentacion = experimentacionidExperimentacion;
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
        if (!(object instanceof RespuestaExperimentacion)) {
            return false;
        }
        RespuestaExperimentacion other = (RespuestaExperimentacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.RespuestaExperimentacion[ id=" + id + " ]";
    }
    
}
