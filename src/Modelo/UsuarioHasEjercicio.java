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
@Table(name = "usuario_has_ejercicio", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioHasEjercicio.findAll", query = "SELECT u FROM UsuarioHasEjercicio u"),
    @NamedQuery(name = "UsuarioHasEjercicio.findByRespA", query = "SELECT u FROM UsuarioHasEjercicio u WHERE u.respA = :respA"),
    @NamedQuery(name = "UsuarioHasEjercicio.findByRespB", query = "SELECT u FROM UsuarioHasEjercicio u WHERE u.respB = :respB"),
    @NamedQuery(name = "UsuarioHasEjercicio.findByRespC", query = "SELECT u FROM UsuarioHasEjercicio u WHERE u.respC = :respC"),
    @NamedQuery(name = "UsuarioHasEjercicio.findByRespD", query = "SELECT u FROM UsuarioHasEjercicio u WHERE u.respD = :respD"),
    @NamedQuery(name = "UsuarioHasEjercicio.findByRespE", query = "SELECT u FROM UsuarioHasEjercicio u WHERE u.respE = :respE"),
    @NamedQuery(name = "UsuarioHasEjercicio.findByNota", query = "SELECT u FROM UsuarioHasEjercicio u WHERE u.nota = :nota"),
    @NamedQuery(name = "UsuarioHasEjercicio.findByIdrespejerc", query = "SELECT u FROM UsuarioHasEjercicio u WHERE u.idrespejerc = :idrespejerc")})
public class UsuarioHasEjercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "resp_A", length = 20)
    private String respA;
    @Column(name = "resp_B", length = 20)
    private String respB;
    @Column(name = "resp_C", length = 20)
    private String respC;
    @Column(name = "resp_D", length = 20)
    private String respD;
    @Column(name = "resp_E", length = 20)
    private String respE;
    @Column(name = "nota")
    private Integer nota;
    @Id
    @Basic(optional = false)
    @Column(name = "idrespejerc", nullable = false)
    private Integer idrespejerc;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioIdusuario;
    @JoinColumn(name = "idejercicios", referencedColumnName = "idejercicio")
    @ManyToOne
    private Ejercicio idejercicios;

    public UsuarioHasEjercicio() {
    }

    public UsuarioHasEjercicio(Integer idrespejerc) {
        this.idrespejerc = idrespejerc;
    }

    public String getRespA() {
        return respA;
    }

    public void setRespA(String respA) {
        this.respA = respA;
    }

    public String getRespB() {
        return respB;
    }

    public void setRespB(String respB) {
        this.respB = respB;
    }

    public String getRespC() {
        return respC;
    }

    public void setRespC(String respC) {
        this.respC = respC;
    }

    public String getRespD() {
        return respD;
    }

    public void setRespD(String respD) {
        this.respD = respD;
    }

    public String getRespE() {
        return respE;
    }

    public void setRespE(String respE) {
        this.respE = respE;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Integer getIdrespejerc() {
        return idrespejerc;
    }

    public void setIdrespejerc(Integer idrespejerc) {
        this.idrespejerc = idrespejerc;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    public Ejercicio getIdejercicios() {
        return idejercicios;
    }

    public void setIdejercicios(Ejercicio idejercicios) {
        this.idejercicios = idejercicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrespejerc != null ? idrespejerc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasEjercicio)) {
            return false;
        }
        UsuarioHasEjercicio other = (UsuarioHasEjercicio) object;
        if ((this.idrespejerc == null && other.idrespejerc != null) || (this.idrespejerc != null && !this.idrespejerc.equals(other.idrespejerc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.UsuarioHasEjercicio[ idrespejerc=" + idrespejerc + " ]";
    }
    
}
