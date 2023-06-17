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
@Table(name = "sujeto", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sujeto.findAll", query = "SELECT s FROM Sujeto s"),
    @NamedQuery(name = "Sujeto.findByIdsujeto", query = "SELECT s FROM Sujeto s WHERE s.idsujeto = :idsujeto"),
    @NamedQuery(name = "Sujeto.findByNombSujet", query = "SELECT s FROM Sujeto s WHERE s.nombSujet = :nombSujet"),
    @NamedQuery(name = "Sujeto.findByPeso", query = "SELECT s FROM Sujeto s WHERE s.peso = :peso"),
    @NamedQuery(name = "Sujeto.findByEdad", query = "SELECT s FROM Sujeto s WHERE s.edad = :edad")})
public class Sujeto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsujeto", nullable = false)
    private Integer idsujeto;
    @Column(name = "nomb_sujet", length = 40)
    private String nombSujet;
    @Basic(optional = false)
    @Column(name = "peso", nullable = false)
    private float peso;
    @Basic(optional = false)
    @Column(name = "edad", nullable = false)
    private int edad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsujeto")
    private List<SujetoPosiciones> sujetoPosicionesList;

    public Sujeto() {
    }

    public Sujeto(Integer idsujeto) {
        this.idsujeto = idsujeto;
    }

    public Sujeto(Integer idsujeto, float peso, int edad) {
        this.idsujeto = idsujeto;
        this.peso = peso;
        this.edad = edad;
    }

    public Integer getIdsujeto() {
        return idsujeto;
    }

    public void setIdsujeto(Integer idsujeto) {
        this.idsujeto = idsujeto;
    }

    public String getNombSujet() {
        return nombSujet;
    }

    public void setNombSujet(String nombSujet) {
        this.nombSujet = nombSujet;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @XmlTransient
    public List<SujetoPosiciones> getSujetoPosicionesList() {
        return sujetoPosicionesList;
    }

    public void setSujetoPosicionesList(List<SujetoPosiciones> sujetoPosicionesList) {
        this.sujetoPosicionesList = sujetoPosicionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsujeto != null ? idsujeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sujeto)) {
            return false;
        }
        Sujeto other = (Sujeto) object;
        if ((this.idsujeto == null && other.idsujeto != null) || (this.idsujeto != null && !this.idsujeto.equals(other.idsujeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Sujeto[ idsujeto=" + idsujeto + " ]";
    }
    
}
