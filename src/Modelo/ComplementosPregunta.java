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
@Table(name = "complementos_pregunta", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplementosPregunta.findAll", query = "SELECT c FROM ComplementosPregunta c"),
    @NamedQuery(name = "ComplementosPregunta.findByIdcomplementosPregunta", query = "SELECT c FROM ComplementosPregunta c WHERE c.idcomplementosPregunta = :idcomplementosPregunta"),
    @NamedQuery(name = "ComplementosPregunta.findByIncA", query = "SELECT c FROM ComplementosPregunta c WHERE c.incA = :incA"),
    @NamedQuery(name = "ComplementosPregunta.findByIncB", query = "SELECT c FROM ComplementosPregunta c WHERE c.incB = :incB"),
    @NamedQuery(name = "ComplementosPregunta.findByIncC", query = "SELECT c FROM ComplementosPregunta c WHERE c.incC = :incC"),
    @NamedQuery(name = "ComplementosPregunta.findByIncD", query = "SELECT c FROM ComplementosPregunta c WHERE c.incD = :incD"),
    @NamedQuery(name = "ComplementosPregunta.findByIncE", query = "SELECT c FROM ComplementosPregunta c WHERE c.incE = :incE")})
public class ComplementosPregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomplementos_pregunta", nullable = false)
    private Integer idcomplementosPregunta;
    @Column(name = "inc_A", length = 100)
    private String incA;
    @Column(name = "inc_B", length = 100)
    private String incB;
    @Column(name = "inc_C", length = 100)
    private String incC;
    @Column(name = "inc_D", length = 100)
    private String incD;
    @Column(name = "inc_E", length = 100)
    private String incE;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "complementosPreguntaIdcomplementosPregunta")
    private List<Ejercicio> ejercicioList;

    public ComplementosPregunta() {
    }

    public ComplementosPregunta(Integer idcomplementosPregunta) {
        this.idcomplementosPregunta = idcomplementosPregunta;
    }

    public Integer getIdcomplementosPregunta() {
        return idcomplementosPregunta;
    }

    public void setIdcomplementosPregunta(Integer idcomplementosPregunta) {
        this.idcomplementosPregunta = idcomplementosPregunta;
    }

    public String getIncA() {
        return incA;
    }

    public void setIncA(String incA) {
        this.incA = incA;
    }

    public String getIncB() {
        return incB;
    }

    public void setIncB(String incB) {
        this.incB = incB;
    }

    public String getIncC() {
        return incC;
    }

    public void setIncC(String incC) {
        this.incC = incC;
    }

    public String getIncD() {
        return incD;
    }

    public void setIncD(String incD) {
        this.incD = incD;
    }

    public String getIncE() {
        return incE;
    }

    public void setIncE(String incE) {
        this.incE = incE;
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
        hash += (idcomplementosPregunta != null ? idcomplementosPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComplementosPregunta)) {
            return false;
        }
        ComplementosPregunta other = (ComplementosPregunta) object;
        if ((this.idcomplementosPregunta == null && other.idcomplementosPregunta != null) || (this.idcomplementosPregunta != null && !this.idcomplementosPregunta.equals(other.idcomplementosPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ComplementosPregunta[ idcomplementosPregunta=" + idcomplementosPregunta + " ]";
    }
    
}
