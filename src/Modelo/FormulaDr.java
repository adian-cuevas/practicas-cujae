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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "formula_dr", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormulaDr.findAll", query = "SELECT f FROM FormulaDr f"),
    @NamedQuery(name = "FormulaDr.findByIdformulaDr", query = "SELECT f FROM FormulaDr f WHERE f.idformulaDr = :idformulaDr"),
    @NamedQuery(name = "FormulaDr.findByFormulacion", query = "SELECT f FROM FormulaDr f WHERE f.formulacion = :formulacion"),
    @NamedQuery(name = "FormulaDr.findByIddimensionRelevante", query = "SELECT f FROM FormulaDr f WHERE f.iddimensionRelevante = :iddimensionRelevante")})
public class FormulaDr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idformula_dr", nullable = false)
    private Integer idformulaDr;
    @Basic(optional = false)
    @Column(name = "formulacion", nullable = false, length = 100)
    private String formulacion;
    @Column(name = "iddimension_relevante")
    private Integer iddimensionRelevante;

    public FormulaDr() {
    }

    public FormulaDr(Integer idformulaDr) {
        this.idformulaDr = idformulaDr;
    }

    public FormulaDr(Integer idformulaDr, String formulacion) {
        this.idformulaDr = idformulaDr;
        this.formulacion = formulacion;
    }

    public Integer getIdformulaDr() {
        return idformulaDr;
    }

    public void setIdformulaDr(Integer idformulaDr) {
        this.idformulaDr = idformulaDr;
    }

    public String getFormulacion() {
        return formulacion;
    }

    public void setFormulacion(String formulacion) {
        this.formulacion = formulacion;
    }

    public Integer getIddimensionRelevante() {
        return iddimensionRelevante;
    }

    public void setIddimensionRelevante(Integer iddimensionRelevante) {
        this.iddimensionRelevante = iddimensionRelevante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformulaDr != null ? idformulaDr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormulaDr)) {
            return false;
        }
        FormulaDr other = (FormulaDr) object;
        if ((this.idformulaDr == null && other.idformulaDr != null) || (this.idformulaDr != null && !this.idformulaDr.equals(other.idformulaDr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.FormulaDr[ idformulaDr=" + idformulaDr + " ]";
    }
    
}
