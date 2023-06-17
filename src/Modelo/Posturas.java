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
import javax.persistence.Lob;
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
@Table(name = "posturas", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posturas.findAll", query = "SELECT p FROM Posturas p"),
    @NamedQuery(name = "Posturas.findByIdposturas", query = "SELECT p FROM Posturas p WHERE p.idposturas = :idposturas"),
    @NamedQuery(name = "Posturas.findByNombPostura", query = "SELECT p FROM Posturas p WHERE p.nombPostura = :nombPostura")})
public class Posturas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idposturas", nullable = false)
    private Integer idposturas;
    @Basic(optional = false)
    @Column(name = "nomb_postura", nullable = false, length = 1024)
    private String nombPostura;
    @Lob
    @Column(name = "descrip_postura", length = 65535)
    private String descripPostura;
    @Basic(optional = false)
    @Lob
    @Column(name = "foto_postura", nullable = false)
    private byte[] fotoPostura;
    @OneToMany(mappedBy = "posturasIdposturas")
    private List<Dimensiones> dimensionesList;

    public Posturas() {
    }

    public Posturas(Integer idposturas) {
        this.idposturas = idposturas;
    }

    public Posturas(Integer idposturas, String nombPostura, byte[] fotoPostura) {
        this.idposturas = idposturas;
        this.nombPostura = nombPostura;
        this.fotoPostura = fotoPostura;
    }

    public Integer getIdposturas() {
        return idposturas;
    }

    public void setIdposturas(Integer idposturas) {
        this.idposturas = idposturas;
    }

    public String getNombPostura() {
        return nombPostura;
    }

    public void setNombPostura(String nombPostura) {
        this.nombPostura = nombPostura;
    }

    public String getDescripPostura() {
        return descripPostura;
    }

    public void setDescripPostura(String descripPostura) {
        this.descripPostura = descripPostura;
    }

    public byte[] getFotoPostura() {
        return fotoPostura;
    }

    public void setFotoPostura(byte[] fotoPostura) {
        this.fotoPostura = fotoPostura;
    }

    @XmlTransient
    public List<Dimensiones> getDimensionesList() {
        return dimensionesList;
    }

    public void setDimensionesList(List<Dimensiones> dimensionesList) {
        this.dimensionesList = dimensionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idposturas != null ? idposturas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posturas)) {
            return false;
        }
        Posturas other = (Posturas) object;
        if ((this.idposturas == null && other.idposturas != null) || (this.idposturas != null && !this.idposturas.equals(other.idposturas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Posturas[ idposturas=" + idposturas + " ]";
    }
    
}
