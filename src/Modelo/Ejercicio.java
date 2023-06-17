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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "ejercicio", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e"),
    @NamedQuery(name = "Ejercicio.findByIdejercicio", query = "SELECT e FROM Ejercicio e WHERE e.idejercicio = :idejercicio"),
    @NamedQuery(name = "Ejercicio.findByPregunta", query = "SELECT e FROM Ejercicio e WHERE e.pregunta = :pregunta")})
public class Ejercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idejercicio", nullable = false)
    private Integer idejercicio;
    @Basic(optional = false)
    @Column(name = "pregunta", nullable = false, length = 1024)
    private String pregunta;
    @Lob
    @Column(name = "foto_ejercicio")
    private byte[] fotoEjercicio;
    @OneToMany(mappedBy = "idejercicios")
    private List<UsuarioHasEjercicio> usuarioHasEjercicioList;
    @JoinColumn(name = "complementos_pregunta_idcomplementos_pregunta", referencedColumnName = "idcomplementos_pregunta", nullable = false)
    @ManyToOne(optional = false)
    private ComplementosPregunta complementosPreguntaIdcomplementosPregunta;
    @JoinColumn(name = "idtipoejercicio", referencedColumnName = "idtipoejercicio")
    @ManyToOne
    private Tipoejercicio idtipoejercicio;
    @JoinColumn(name = "idcomplementos_respuesta", referencedColumnName = "idrespuestas", nullable = false)
    @ManyToOne(optional = false)
    private Repuestas idcomplementosRespuesta;
    @JoinColumn(name = "idtema", referencedColumnName = "idtema")
    @ManyToOne
    private Tema idtema;

    public Ejercicio() {
    }

    public Ejercicio(Integer idejercicio) {
        this.idejercicio = idejercicio;
    }

    public Ejercicio(Integer idejercicio, String pregunta) {
        this.idejercicio = idejercicio;
        this.pregunta = pregunta;
    }

    public Integer getIdejercicio() {
        return idejercicio;
    }

    public void setIdejercicio(Integer idejercicio) {
        this.idejercicio = idejercicio;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public byte[] getFotoEjercicio() {
        return fotoEjercicio;
    }

    public void setFotoEjercicio(byte[] fotoEjercicio) {
        this.fotoEjercicio = fotoEjercicio;
    }

    @XmlTransient
    public List<UsuarioHasEjercicio> getUsuarioHasEjercicioList() {
        return usuarioHasEjercicioList;
    }

    public void setUsuarioHasEjercicioList(List<UsuarioHasEjercicio> usuarioHasEjercicioList) {
        this.usuarioHasEjercicioList = usuarioHasEjercicioList;
    }

    public ComplementosPregunta getComplementosPreguntaIdcomplementosPregunta() {
        return complementosPreguntaIdcomplementosPregunta;
    }

    public void setComplementosPreguntaIdcomplementosPregunta(ComplementosPregunta complementosPreguntaIdcomplementosPregunta) {
        this.complementosPreguntaIdcomplementosPregunta = complementosPreguntaIdcomplementosPregunta;
    }

    public Tipoejercicio getIdtipoejercicio() {
        return idtipoejercicio;
    }

    public void setIdtipoejercicio(Tipoejercicio idtipoejercicio) {
        this.idtipoejercicio = idtipoejercicio;
    }

    public Repuestas getIdcomplementosRespuesta() {
        return idcomplementosRespuesta;
    }

    public void setIdcomplementosRespuesta(Repuestas idcomplementosRespuesta) {
        this.idcomplementosRespuesta = idcomplementosRespuesta;
    }

    public Tema getIdtema() {
        return idtema;
    }

    public void setIdtema(Tema idtema) {
        this.idtema = idtema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idejercicio != null ? idejercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.idejercicio == null && other.idejercicio != null) || (this.idejercicio != null && !this.idejercicio.equals(other.idejercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Ejercicio[ idejercicio=" + idejercicio + " ]";
    }
    
}
