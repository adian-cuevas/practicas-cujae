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
@Table(name = "puntantrp", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puntantrp.findAll", query = "SELECT p FROM Puntantrp p"),
    @NamedQuery(name = "Puntantrp.findByIdpuntantrp", query = "SELECT p FROM Puntantrp p WHERE p.idpuntantrp = :idpuntantrp"),
    @NamedQuery(name = "Puntantrp.findByNombPto", query = "SELECT p FROM Puntantrp p WHERE p.nombPto = :nombPto"),
    @NamedQuery(name = "Puntantrp.findByDescripPto", query = "SELECT p FROM Puntantrp p WHERE p.descripPto = :descripPto")})
public class Puntantrp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpuntantrp", nullable = false)
    private Integer idpuntantrp;
    @Basic(optional = false)
    @Column(name = "nomb_pto", nullable = false, length = 45)
    private String nombPto;
    @Column(name = "descrip_pto", length = 1024)
    private String descripPto;
    @Lob
    @Column(name = "foto_puntantrop")
    private byte[] fotoPuntantrop;
    @OneToMany(mappedBy = "idpuntantrop")
    private List<Dimensiones> dimensionesList;
    @JoinColumn(name = "idpuntteoria", referencedColumnName = "idpuntosteoria")
    @ManyToOne
    private Puntosteoria idpuntteoria;

    public Puntantrp() {
    }

    public Puntantrp(Integer idpuntantrp) {
        this.idpuntantrp = idpuntantrp;
    }

    public Puntantrp(Integer idpuntantrp, String nombPto) {
        this.idpuntantrp = idpuntantrp;
        this.nombPto = nombPto;
    }

    public Integer getIdpuntantrp() {
        return idpuntantrp;
    }

    public void setIdpuntantrp(Integer idpuntantrp) {
        this.idpuntantrp = idpuntantrp;
    }

    public String getNombPto() {
        return nombPto;
    }

    public void setNombPto(String nombPto) {
        this.nombPto = nombPto;
    }

    public String getDescripPto() {
        return descripPto;
    }

    public void setDescripPto(String descripPto) {
        this.descripPto = descripPto;
    }

    public byte[] getFotoPuntantrop() {
        return fotoPuntantrop;
    }

    public void setFotoPuntantrop(byte[] fotoPuntantrop) {
        this.fotoPuntantrop = fotoPuntantrop;
    }

    @XmlTransient
    public List<Dimensiones> getDimensionesList() {
        return dimensionesList;
    }

    public void setDimensionesList(List<Dimensiones> dimensionesList) {
        this.dimensionesList = dimensionesList;
    }

    public Puntosteoria getIdpuntteoria() {
        return idpuntteoria;
    }

    public void setIdpuntteoria(Puntosteoria idpuntteoria) {
        this.idpuntteoria = idpuntteoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpuntantrp != null ? idpuntantrp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntantrp)) {
            return false;
        }
        Puntantrp other = (Puntantrp) object;
        if ((this.idpuntantrp == null && other.idpuntantrp != null) || (this.idpuntantrp != null && !this.idpuntantrp.equals(other.idpuntantrp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Puntantrp[ idpuntantrp=" + idpuntantrp + " ]";
    }
    
}
