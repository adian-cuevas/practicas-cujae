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
@Table(name = "puntosteoria", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puntosteoria.findAll", query = "SELECT p FROM Puntosteoria p"),
    @NamedQuery(name = "Puntosteoria.findByIdpuntosteoria", query = "SELECT p FROM Puntosteoria p WHERE p.idpuntosteoria = :idpuntosteoria"),
    @NamedQuery(name = "Puntosteoria.findByXCoord", query = "SELECT p FROM Puntosteoria p WHERE p.xCoord = :xCoord"),
    @NamedQuery(name = "Puntosteoria.findByYCoord", query = "SELECT p FROM Puntosteoria p WHERE p.yCoord = :yCoord")})
public class Puntosteoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpuntosteoria", nullable = false)
    private Integer idpuntosteoria;
    @Basic(optional = false)
    @Column(name = "x_coord", nullable = false)
    private int xCoord;
    @Basic(optional = false)
    @Column(name = "y_coord", nullable = false)
    private int yCoord;
    @Lob
    @Column(name = "fot_punteor")
    private byte[] fotPunteor;
    @OneToMany(mappedBy = "idpuntteoria")
    private List<Puntantrp> puntantrpList;

    public Puntosteoria() {
    }

    public Puntosteoria(Integer idpuntosteoria) {
        this.idpuntosteoria = idpuntosteoria;
    }

    public Puntosteoria(Integer idpuntosteoria, int xCoord, int yCoord) {
        this.idpuntosteoria = idpuntosteoria;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Integer getIdpuntosteoria() {
        return idpuntosteoria;
    }

    public void setIdpuntosteoria(Integer idpuntosteoria) {
        this.idpuntosteoria = idpuntosteoria;
    }

    public int getXCoord() {
        return xCoord;
    }

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public byte[] getFotPunteor() {
        return fotPunteor;
    }

    public void setFotPunteor(byte[] fotPunteor) {
        this.fotPunteor = fotPunteor;
    }

    @XmlTransient
    public List<Puntantrp> getPuntantrpList() {
        return puntantrpList;
    }

    public void setPuntantrpList(List<Puntantrp> puntantrpList) {
        this.puntantrpList = puntantrpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpuntosteoria != null ? idpuntosteoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntosteoria)) {
            return false;
        }
        Puntosteoria other = (Puntosteoria) object;
        if ((this.idpuntosteoria == null && other.idpuntosteoria != null) || (this.idpuntosteoria != null && !this.idpuntosteoria.equals(other.idpuntosteoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Puntosteoria[ idpuntosteoria=" + idpuntosteoria + " ]";
    }
    
}
