/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idmedio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medio other = (Medio) obj;
        if (!Objects.equals(this.idmedio, other.idmedio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medio{" + "idmedio=" + idmedio + ", nombMedio=" + nombMedio + ", descripMedio=" + descripMedio + ", fotoMedio=" + fotoMedio + '}';
    }

}
