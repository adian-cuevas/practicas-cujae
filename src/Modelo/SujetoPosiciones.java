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
@Table(name = "sujeto_posiciones", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SujetoPosiciones.findAll", query = "SELECT s FROM SujetoPosiciones s"),
    @NamedQuery(name = "SujetoPosiciones.findByIdsujetoPosiciones", query = "SELECT s FROM SujetoPosiciones s WHERE s.idsujetoPosiciones = :idsujetoPosiciones")})
public class SujetoPosiciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsujeto_posiciones", nullable = false)
    private Integer idsujetoPosiciones;
    @Basic(optional = false)
    @Lob
    @Column(name = "posicion_foto", nullable = false)
    private byte[] posicionFoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsujetoPosiciones")
    private List<PosicionesDimensiones> posicionesDimensionesList;
    @JoinColumn(name = "idposiciones_descripcion", referencedColumnName = "idposiciones_descripcion", nullable = false)
    @ManyToOne(optional = false)
    private PosicionesDescripcion idposicionesDescripcion;
    @JoinColumn(name = "idsujeto", referencedColumnName = "idsujeto", nullable = false)
    @ManyToOne(optional = false)
    private Sujeto idsujeto;

    public SujetoPosiciones() {
    }

    public SujetoPosiciones(Integer idsujetoPosiciones) {
        this.idsujetoPosiciones = idsujetoPosiciones;
    }

    public SujetoPosiciones(Integer idsujetoPosiciones, byte[] posicionFoto) {
        this.idsujetoPosiciones = idsujetoPosiciones;
        this.posicionFoto = posicionFoto;
    }

    public Integer getIdsujetoPosiciones() {
        return idsujetoPosiciones;
    }

    public void setIdsujetoPosiciones(Integer idsujetoPosiciones) {
        this.idsujetoPosiciones = idsujetoPosiciones;
    }

    public byte[] getPosicionFoto() {
        return posicionFoto;
    }

    public void setPosicionFoto(byte[] posicionFoto) {
        this.posicionFoto = posicionFoto;
    }

    @XmlTransient
    public List<PosicionesDimensiones> getPosicionesDimensionesList() {
        return posicionesDimensionesList;
    }

    public void setPosicionesDimensionesList(List<PosicionesDimensiones> posicionesDimensionesList) {
        this.posicionesDimensionesList = posicionesDimensionesList;
    }

    public PosicionesDescripcion getIdposicionesDescripcion() {
        return idposicionesDescripcion;
    }

    public void setIdposicionesDescripcion(PosicionesDescripcion idposicionesDescripcion) {
        this.idposicionesDescripcion = idposicionesDescripcion;
    }

    public Sujeto getIdsujeto() {
        return idsujeto;
    }

    public void setIdsujeto(Sujeto idsujeto) {
        this.idsujeto = idsujeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsujetoPosiciones != null ? idsujetoPosiciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SujetoPosiciones)) {
            return false;
        }
        SujetoPosiciones other = (SujetoPosiciones) object;
        if ((this.idsujetoPosiciones == null && other.idsujetoPosiciones != null) || (this.idsujetoPosiciones != null && !this.idsujetoPosiciones.equals(other.idsujetoPosiciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.SujetoPosiciones[ idsujetoPosiciones=" + idsujetoPosiciones + " ]";
    }
    
}
