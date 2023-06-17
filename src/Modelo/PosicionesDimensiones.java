/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "posiciones_dimensiones", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosicionesDimensiones.findAll", query = "SELECT p FROM PosicionesDimensiones p"),
    @NamedQuery(name = "PosicionesDimensiones.findByIdposicionesDimensiones", query = "SELECT p FROM PosicionesDimensiones p WHERE p.idposicionesDimensiones = :idposicionesDimensiones"),
    @NamedQuery(name = "PosicionesDimensiones.findByNombDimension", query = "SELECT p FROM PosicionesDimensiones p WHERE p.nombDimension = :nombDimension"),
    @NamedQuery(name = "PosicionesDimensiones.findByPosX1", query = "SELECT p FROM PosicionesDimensiones p WHERE p.posX1 = :posX1"),
    @NamedQuery(name = "PosicionesDimensiones.findByPosY1", query = "SELECT p FROM PosicionesDimensiones p WHERE p.posY1 = :posY1"),
    @NamedQuery(name = "PosicionesDimensiones.findByPosX2", query = "SELECT p FROM PosicionesDimensiones p WHERE p.posX2 = :posX2"),
    @NamedQuery(name = "PosicionesDimensiones.findByPosY2", query = "SELECT p FROM PosicionesDimensiones p WHERE p.posY2 = :posY2"),
    @NamedQuery(name = "PosicionesDimensiones.findByValor", query = "SELECT p FROM PosicionesDimensiones p WHERE p.valor = :valor")})
public class PosicionesDimensiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idposiciones_dimensiones", nullable = false)
    private Integer idposicionesDimensiones;
    @Basic(optional = false)
    @Column(name = "nomb_dimension", nullable = false, length = 45)
    private String nombDimension;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pos_x1", precision = 12)
    private Float posX1;
    @Column(name = "pos_y1", precision = 12)
    private Float posY1;
    @Column(name = "pos_x2", precision = 12)
    private Float posX2;
    @Column(name = "pos_y2", precision = 12)
    private Float posY2;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private int valor;
    @JoinColumn(name = "idsujeto_posiciones", referencedColumnName = "idsujeto_posiciones", nullable = false)
    @ManyToOne(optional = false)
    private SujetoPosiciones idsujetoPosiciones;

    public PosicionesDimensiones() {
    }

    public PosicionesDimensiones(Integer idposicionesDimensiones) {
        this.idposicionesDimensiones = idposicionesDimensiones;
    }

    public PosicionesDimensiones(Integer idposicionesDimensiones, String nombDimension, int valor) {
        this.idposicionesDimensiones = idposicionesDimensiones;
        this.nombDimension = nombDimension;
        this.valor = valor;
    }

    public Integer getIdposicionesDimensiones() {
        return idposicionesDimensiones;
    }

    public void setIdposicionesDimensiones(Integer idposicionesDimensiones) {
        this.idposicionesDimensiones = idposicionesDimensiones;
    }

    public String getNombDimension() {
        return nombDimension;
    }

    public void setNombDimension(String nombDimension) {
        this.nombDimension = nombDimension;
    }

    public Float getPosX1() {
        return posX1;
    }

    public void setPosX1(Float posX1) {
        this.posX1 = posX1;
    }

    public Float getPosY1() {
        return posY1;
    }

    public void setPosY1(Float posY1) {
        this.posY1 = posY1;
    }

    public Float getPosX2() {
        return posX2;
    }

    public void setPosX2(Float posX2) {
        this.posX2 = posX2;
    }

    public Float getPosY2() {
        return posY2;
    }

    public void setPosY2(Float posY2) {
        this.posY2 = posY2;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public SujetoPosiciones getIdsujetoPosiciones() {
        return idsujetoPosiciones;
    }

    public void setIdsujetoPosiciones(SujetoPosiciones idsujetoPosiciones) {
        this.idsujetoPosiciones = idsujetoPosiciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idposicionesDimensiones != null ? idposicionesDimensiones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionesDimensiones)) {
            return false;
        }
        PosicionesDimensiones other = (PosicionesDimensiones) object;
        if ((this.idposicionesDimensiones == null && other.idposicionesDimensiones != null) || (this.idposicionesDimensiones != null && !this.idposicionesDimensiones.equals(other.idposicionesDimensiones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.PosicionesDimensiones[ idposicionesDimensiones=" + idposicionesDimensiones + " ]";
    }
    
}
