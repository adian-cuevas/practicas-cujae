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
@Table(name = "instrumento", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instrumento.findAll", query = "SELECT i FROM Instrumento i"),
    @NamedQuery(name = "Instrumento.findByIdinstrumento", query = "SELECT i FROM Instrumento i WHERE i.idinstrumento = :idinstrumento"),
    @NamedQuery(name = "Instrumento.findByNombInstrumento", query = "SELECT i FROM Instrumento i WHERE i.nombInstrumento = :nombInstrumento"),
    @NamedQuery(name = "Instrumento.findByDescripInstrumento", query = "SELECT i FROM Instrumento i WHERE i.descripInstrumento = :descripInstrumento")})
public class Instrumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinstrumento", nullable = false)
    private Integer idinstrumento;
    @Basic(optional = false)
    @Column(name = "nomb_instrumento", nullable = false, length = 45)
    private String nombInstrumento;
    @Basic(optional = false)
    @Column(name = "descrip_instrumento", nullable = false, length = 1024)
    private String descripInstrumento;
    @Basic(optional = false)
    @Lob
    @Column(name = "foto_instrumento", nullable = false)
    private byte[] fotoInstrumento;
    @OneToMany(mappedBy = "idinstrumento")
    private List<Dimensiones> dimensionesList;

    public Instrumento() {
    }

    public Instrumento(Integer idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    public Instrumento(Integer idinstrumento, String nombInstrumento, String descripInstrumento, byte[] fotoInstrumento) {
        this.idinstrumento = idinstrumento;
        this.nombInstrumento = nombInstrumento;
        this.descripInstrumento = descripInstrumento;
        this.fotoInstrumento = fotoInstrumento;
    }

    public Integer getIdinstrumento() {
        return idinstrumento;
    }

    public void setIdinstrumento(Integer idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    public String getNombInstrumento() {
        return nombInstrumento;
    }

    public void setNombInstrumento(String nombInstrumento) {
        this.nombInstrumento = nombInstrumento;
    }

    public String getDescripInstrumento() {
        return descripInstrumento;
    }

    public void setDescripInstrumento(String descripInstrumento) {
        this.descripInstrumento = descripInstrumento;
    }

    public byte[] getFotoInstrumento() {
        return fotoInstrumento;
    }

    public void setFotoInstrumento(byte[] fotoInstrumento) {
        this.fotoInstrumento = fotoInstrumento;
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
        hash += (idinstrumento != null ? idinstrumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        if ((this.idinstrumento == null && other.idinstrumento != null) || (this.idinstrumento != null && !this.idinstrumento.equals(other.idinstrumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Instrumento[ idinstrumento=" + idinstrumento + " ]";
    }
    
}
