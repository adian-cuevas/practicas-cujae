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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "dimensiones", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dimensiones.findAll", query = "SELECT d FROM Dimensiones d"),
    @NamedQuery(name = "Dimensiones.findByIddimensiones", query = "SELECT d FROM Dimensiones d WHERE d.iddimensiones = :iddimensiones"),
    @NamedQuery(name = "Dimensiones.findByNombmedid", query = "SELECT d FROM Dimensiones d WHERE d.nombmedid = :nombmedid"),
    @NamedQuery(name = "Dimensiones.findByDescripMedida", query = "SELECT d FROM Dimensiones d WHERE d.descripMedida = :descripMedida"),
    @NamedQuery(name = "Dimensiones.findByCriterioDeUso", query = "SELECT d FROM Dimensiones d WHERE d.criterioDeUso = :criterioDeUso"),
    @NamedQuery(name = "Dimensiones.findBySigla", query = "SELECT d FROM Dimensiones d WHERE d.sigla = :sigla")})
public class Dimensiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddimensiones", nullable = false)
    private Integer iddimensiones;
    @Basic(optional = false)
    @Column(name = "nombmedid", nullable = false, length = 100)
    private String nombmedid;
    @Basic(optional = false)
    @Column(name = "descrip_medida", nullable = false, length = 1024)
    private String descripMedida;
    @Column(name = "criterio_de_uso", length = 1024)
    private String criterioDeUso;
    @Basic(optional = false)
    @Lob
    @Column(name = "fotodim", nullable = false)
    private byte[] fotodim;
    @Column(name = "sigla", length = 10)
    private String sigla;
    @ManyToMany(mappedBy = "dimensionesList")
    private List<DimensionesRelevantes> dimensionesRelevantesList;
    @JoinColumn(name = "posturas_idposturas", referencedColumnName = "idposturas")
    @ManyToOne
    private Posturas posturasIdposturas;
    @JoinColumn(name = "idposiciondescripcion", referencedColumnName = "idposiciones_descripcion")
    @ManyToOne
    private PosicionesDescripcion idposiciondescripcion;
    @JoinColumn(name = "idpuntantrop", referencedColumnName = "idpuntantrp")
    @ManyToOne
    private Puntantrp idpuntantrop;
    @JoinColumn(name = "idinstrumento", referencedColumnName = "idinstrumento")
    @ManyToOne
    private Instrumento idinstrumento;

    public Dimensiones() {
    }

    public Dimensiones(Integer iddimensiones) {
        this.iddimensiones = iddimensiones;
    }

    public Dimensiones(Integer iddimensiones, String nombmedid, String descripMedida, byte[] fotodim) {
        this.iddimensiones = iddimensiones;
        this.nombmedid = nombmedid;
        this.descripMedida = descripMedida;
        this.fotodim = fotodim;
    }

    public Integer getIddimensiones() {
        return iddimensiones;
    }

    public void setIddimensiones(Integer iddimensiones) {
        this.iddimensiones = iddimensiones;
    }

    public String getNombmedid() {
        return nombmedid;
    }

    public void setNombmedid(String nombmedid) {
        this.nombmedid = nombmedid;
    }

    public String getDescripMedida() {
        return descripMedida;
    }

    public void setDescripMedida(String descripMedida) {
        this.descripMedida = descripMedida;
    }

    public String getCriterioDeUso() {
        return criterioDeUso;
    }

    public void setCriterioDeUso(String criterioDeUso) {
        this.criterioDeUso = criterioDeUso;
    }

    public byte[] getFotodim() {
        return fotodim;
    }

    public void setFotodim(byte[] fotodim) {
        this.fotodim = fotodim;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @XmlTransient
    public List<DimensionesRelevantes> getDimensionesRelevantesList() {
        return dimensionesRelevantesList;
    }

    public void setDimensionesRelevantesList(List<DimensionesRelevantes> dimensionesRelevantesList) {
        this.dimensionesRelevantesList = dimensionesRelevantesList;
    }

    public Posturas getPosturasIdposturas() {
        return posturasIdposturas;
    }

    public void setPosturasIdposturas(Posturas posturasIdposturas) {
        this.posturasIdposturas = posturasIdposturas;
    }

    public PosicionesDescripcion getIdposiciondescripcion() {
        return idposiciondescripcion;
    }

    public void setIdposiciondescripcion(PosicionesDescripcion idposiciondescripcion) {
        this.idposiciondescripcion = idposiciondescripcion;
    }

    public Puntantrp getIdpuntantrop() {
        return idpuntantrop;
    }

    public void setIdpuntantrop(Puntantrp idpuntantrop) {
        this.idpuntantrop = idpuntantrop;
    }

    public Instrumento getIdinstrumento() {
        return idinstrumento;
    }

    public void setIdinstrumento(Instrumento idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddimensiones != null ? iddimensiones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dimensiones)) {
            return false;
        }
        Dimensiones other = (Dimensiones) object;
        if ((this.iddimensiones == null && other.iddimensiones != null) || (this.iddimensiones != null && !this.iddimensiones.equals(other.iddimensiones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Dimensiones[ iddimensiones=" + iddimensiones + " ]";
    }
    
}
