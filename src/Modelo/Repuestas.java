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
@Table(name = "repuestas", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Repuestas.findAll", query = "SELECT r FROM Repuestas r"),
    @NamedQuery(name = "Repuestas.findByIdrespuestas", query = "SELECT r FROM Repuestas r WHERE r.idrespuestas = :idrespuestas"),
    @NamedQuery(name = "Repuestas.findByRespuestaincA", query = "SELECT r FROM Repuestas r WHERE r.respuestaincA = :respuestaincA"),
    @NamedQuery(name = "Repuestas.findByRespuestaincB", query = "SELECT r FROM Repuestas r WHERE r.respuestaincB = :respuestaincB"),
    @NamedQuery(name = "Repuestas.findByRespuestaincC", query = "SELECT r FROM Repuestas r WHERE r.respuestaincC = :respuestaincC"),
    @NamedQuery(name = "Repuestas.findByRespuestaincD", query = "SELECT r FROM Repuestas r WHERE r.respuestaincD = :respuestaincD"),
    @NamedQuery(name = "Repuestas.findByRespuestaincE", query = "SELECT r FROM Repuestas r WHERE r.respuestaincE = :respuestaincE")})
public class Repuestas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrespuestas", nullable = false)
    private Integer idrespuestas;
    @Basic(optional = false)
    @Column(name = "respuesta_incA", nullable = false, length = 100)
    private String respuestaincA;
    @Basic(optional = false)
    @Column(name = "respuesta_incB", nullable = false, length = 100)
    private String respuestaincB;
    @Basic(optional = false)
    @Column(name = "respuesta_incC", nullable = false, length = 100)
    private String respuestaincC;
    @Basic(optional = false)
    @Column(name = "respuesta_incD", nullable = false, length = 100)
    private String respuestaincD;
    @Basic(optional = false)
    @Column(name = "respuesta_incE", nullable = false, length = 100)
    private String respuestaincE;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcomplementosRespuesta")
    private List<Ejercicio> ejercicioList;

    public Repuestas() {
    }

    public Repuestas(Integer idrespuestas) {
        this.idrespuestas = idrespuestas;
    }

    public Repuestas(Integer idrespuestas, String respuestaincA, String respuestaincB, String respuestaincC, String respuestaincD, String respuestaincE) {
        this.idrespuestas = idrespuestas;
        this.respuestaincA = respuestaincA;
        this.respuestaincB = respuestaincB;
        this.respuestaincC = respuestaincC;
        this.respuestaincD = respuestaincD;
        this.respuestaincE = respuestaincE;
    }

    public Integer getIdrespuestas() {
        return idrespuestas;
    }

    public void setIdrespuestas(Integer idrespuestas) {
        this.idrespuestas = idrespuestas;
    }

    public String getRespuestaincA() {
        return respuestaincA;
    }

    public void setRespuestaincA(String respuestaincA) {
        this.respuestaincA = respuestaincA;
    }

    public String getRespuestaincB() {
        return respuestaincB;
    }

    public void setRespuestaincB(String respuestaincB) {
        this.respuestaincB = respuestaincB;
    }

    public String getRespuestaincC() {
        return respuestaincC;
    }

    public void setRespuestaincC(String respuestaincC) {
        this.respuestaincC = respuestaincC;
    }

    public String getRespuestaincD() {
        return respuestaincD;
    }

    public void setRespuestaincD(String respuestaincD) {
        this.respuestaincD = respuestaincD;
    }

    public String getRespuestaincE() {
        return respuestaincE;
    }

    public void setRespuestaincE(String respuestaincE) {
        this.respuestaincE = respuestaincE;
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
        hash += (idrespuestas != null ? idrespuestas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Repuestas)) {
            return false;
        }
        Repuestas other = (Repuestas) object;
        if ((this.idrespuestas == null && other.idrespuestas != null) || (this.idrespuestas != null && !this.idrespuestas.equals(other.idrespuestas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Repuestas[ idrespuestas=" + idrespuestas + " ]";
    }
    
}
