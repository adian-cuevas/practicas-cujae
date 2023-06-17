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
@Table(name = "tema", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tema.findAll", query = "SELECT t FROM Tema t"),
    @NamedQuery(name = "Tema.findByIdtema", query = "SELECT t FROM Tema t WHERE t.idtema = :idtema"),
    @NamedQuery(name = "Tema.findByDescripcion", query = "SELECT t FROM Tema t WHERE t.descripcion = :descripcion")})
public class Tema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtema", nullable = false)
    private Integer idtema;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "idtema")
    private List<Ejercicio> ejercicioList;

    public Tema() {
    }

    public Tema(Integer idtema) {
        this.idtema = idtema;
    }

    public Tema(Integer idtema, String descripcion) {
        this.idtema = idtema;
        this.descripcion = descripcion;
    }

    public Integer getIdtema() {
        return idtema;
    }

    public void setIdtema(Integer idtema) {
        this.idtema = idtema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idtema != null ? idtema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tema)) {
            return false;
        }
        Tema other = (Tema) object;
        if ((this.idtema == null && other.idtema != null) || (this.idtema != null && !this.idtema.equals(other.idtema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Tema[ idtema=" + idtema + " ]";
    }
    
}
