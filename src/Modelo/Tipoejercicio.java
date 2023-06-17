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
@Table(name = "tipoejercicio", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoejercicio.findAll", query = "SELECT t FROM Tipoejercicio t"),
    @NamedQuery(name = "Tipoejercicio.findByIdtipoejercicio", query = "SELECT t FROM Tipoejercicio t WHERE t.idtipoejercicio = :idtipoejercicio"),
    @NamedQuery(name = "Tipoejercicio.findByNombTipoejerc", query = "SELECT t FROM Tipoejercicio t WHERE t.nombTipoejerc = :nombTipoejerc")})
public class Tipoejercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoejercicio", nullable = false)
    private Integer idtipoejercicio;
    @Basic(optional = false)
    @Column(name = "nomb_tipoejerc", nullable = false, length = 45)
    private String nombTipoejerc;
    @OneToMany(mappedBy = "idtipoejercicio")
    private List<Ejercicio> ejercicioList;

    public Tipoejercicio() {
    }

    public Tipoejercicio(Integer idtipoejercicio) {
        this.idtipoejercicio = idtipoejercicio;
    }

    public Tipoejercicio(Integer idtipoejercicio, String nombTipoejerc) {
        this.idtipoejercicio = idtipoejercicio;
        this.nombTipoejerc = nombTipoejerc;
    }

    public Integer getIdtipoejercicio() {
        return idtipoejercicio;
    }

    public void setIdtipoejercicio(Integer idtipoejercicio) {
        this.idtipoejercicio = idtipoejercicio;
    }

    public String getNombTipoejerc() {
        return nombTipoejerc;
    }

    public void setNombTipoejerc(String nombTipoejerc) {
        this.nombTipoejerc = nombTipoejerc;
    }

    @XmlTransient
    public List<Ejercicio> getEjercicioList() {
        return ejercicioList;
    }

    public void setEjercicioList(List<Ejercicio> ejercicioList) {
        this.ejercicioList = ejercicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoejercicio != null ? idtipoejercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoejercicio)) {
            return false;
        }
        Tipoejercicio other = (Tipoejercicio) object;
        if ((this.idtipoejercicio == null && other.idtipoejercicio != null) || (this.idtipoejercicio != null && !this.idtipoejercicio.equals(other.idtipoejercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tipoejercicio[ idtipoejercicio=" + idtipoejercicio + " ]";
    }
    
}
