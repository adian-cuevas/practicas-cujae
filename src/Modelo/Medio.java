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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adian
 */
@Entity
@Table(name = "medio", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medio.findAll", query = "SELECT i FROM Medio i")
    ,
    @NamedQuery(name = "Medio.findByIdmedio", query = "SELECT i FROM Medio i WHERE i.idmedio = :idmedio")
    ,
    @NamedQuery(name = "Medio.findByNombMedio", query = "SELECT i FROM Medio i WHERE i.nombMedio = :nombMedio")
    ,
    @NamedQuery(name = "Medio.findByDescripMedio", query = "SELECT i FROM Medio i WHERE i.descripMedio = :descripMedio")})
public class Medio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmedio", nullable = false)
    private Integer idmedio;
    @Basic(optional = false)
    @Column(name = "nomb_medio", nullable = false, length = 45)
    private String nombMedio;
    @Basic(optional = false)
    @Column(name = "descrip_medio", nullable = false, length = 1024)
    private String descripMedio;
    @Basic(optional = false)
    @Lob
    @Column(name = "foto_medio", nullable = false)
    private byte[] fotoMedio;

    public Medio() {
    }

    public Medio(Integer idmedio) {
        this.idmedio = idmedio;
    }

    public Medio(Integer idmedio, String nombMedio, String descripMedio, byte[] fotoMedio) {
        this.idmedio = idmedio;
        this.nombMedio = nombMedio;
        this.descripMedio = descripMedio;
        this.fotoMedio = fotoMedio;
    }

    public Integer getIdmedio() {
        return idmedio;
    }

    public void setIdmedio(Integer idmedio) {
        this.idmedio = idmedio;
    }

    public String getNombMedio() {
        return nombMedio;
    }

    public void setNombMedio(String nombMedio) {
        this.nombMedio = nombMedio;
    }

    public String getDescripMedio() {
        return descripMedio;
    }

    public void setDescripMedio(String descripMedio) {
        this.descripMedio = descripMedio;
    }

    public byte[] getFotoMedio() {
        return fotoMedio;
    }

    public void setFotoMedio(byte[] fotoMedio) {
        this.fotoMedio = fotoMedio;
    }

    @XmlTransient
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedio != null ? idmedio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medio)) {
            return false;
        }
        Medio other = (Medio) object;
        if ((this.idmedio == null && other.idmedio != null) || (this.idmedio != null && !this.idmedio.equals(other.idmedio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Instrumento[ idinstrumento=" + idmedio + " ]";
    }

}
