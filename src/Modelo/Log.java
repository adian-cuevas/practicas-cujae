/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "log", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findByIdLog", query = "SELECT l FROM Log l WHERE l.idLog = :idLog"),
    @NamedQuery(name = "Log.findByUsuarioLog", query = "SELECT l FROM Log l WHERE l.usuarioLog = :usuarioLog"),
    @NamedQuery(name = "Log.findByFechaLog", query = "SELECT l FROM Log l WHERE l.fechaLog = :fechaLog"),
    @NamedQuery(name = "Log.findByNivelLog", query = "SELECT l FROM Log l WHERE l.nivelLog = :nivelLog"),
    @NamedQuery(name = "Log.findByInfoLog", query = "SELECT l FROM Log l WHERE l.infoLog = :infoLog")})
public class Log implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOG", nullable = false)
    private Integer idLog;
    @Basic(optional = false)
    @Column(name = "USUARIO_LOG", nullable = false, length = 15)
    private String usuarioLog;
    @Basic(optional = false)
    @Column(name = "FECHA_LOG", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaLog;
    @Basic(optional = false)
    @Column(name = "NIVEL_LOG", nullable = false, length = 10)
    private String nivelLog;
    @Basic(optional = false)
    @Column(name = "INFO_LOG", nullable = false, length = 500)
    private String infoLog;

    public Log() {
    }

    public Log(Integer idLog) {
        this.idLog = idLog;
    }

    public Log(Integer idLog, String usuarioLog, Date fechaLog, String nivelLog, String infoLog) {
        this.idLog = idLog;
        this.usuarioLog = usuarioLog;
        this.fechaLog = fechaLog;
        this.nivelLog = nivelLog;
        this.infoLog = infoLog;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        Integer oldIdLog = this.idLog;
        this.idLog = idLog;
        changeSupport.firePropertyChange("idLog", oldIdLog, idLog);
    }

    public String getUsuarioLog() {
        return usuarioLog;
    }

    public void setUsuarioLog(String usuarioLog) {
        String oldUsuarioLog = this.usuarioLog;
        this.usuarioLog = usuarioLog;
        changeSupport.firePropertyChange("usuarioLog", oldUsuarioLog, usuarioLog);
    }

    public Date getFechaLog() {
        return fechaLog;
    }

    public void setFechaLog(Date fechaLog) {
        Date oldFechaLog = this.fechaLog;
        this.fechaLog = fechaLog;
        changeSupport.firePropertyChange("fechaLog", oldFechaLog, fechaLog);
    }

    public String getNivelLog() {
        return nivelLog;
    }

    public void setNivelLog(String nivelLog) {
        String oldNivelLog = this.nivelLog;
        this.nivelLog = nivelLog;
        changeSupport.firePropertyChange("nivelLog", oldNivelLog, nivelLog);
    }

    public String getInfoLog() {
        return infoLog;
    }

    public void setInfoLog(String infoLog) {
        String oldInfoLog = this.infoLog;
        this.infoLog = infoLog;
        changeSupport.firePropertyChange("infoLog", oldInfoLog, infoLog);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLog != null ? idLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.idLog == null && other.idLog != null) || (this.idLog != null && !this.idLog.equals(other.idLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Log[ idLog=" + idLog + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
