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
@Table(name = "usuario", catalog = "mydb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByApellido1er", query = "SELECT u FROM Usuario u WHERE u.apellido1er = :apellido1er"),
    @NamedQuery(name = "Usuario.findByApellido2do", query = "SELECT u FROM Usuario u WHERE u.apellido2do = :apellido2do"),
    @NamedQuery(name = "Usuario.findByNombUsuario", query = "SELECT u FROM Usuario u WHERE u.nombUsuario = :nombUsuario"),
    @NamedQuery(name = "Usuario.findByContrase\u00f1a", query = "SELECT u FROM Usuario u WHERE u.contrase\u00f1a = :contrase\u00f1a"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario", nullable = false)
    private Integer idusuario;
    @Basic(optional = false)
    @Column(name = "apellido_1er", nullable = false, length = 45)
    private String apellido1er;
    @Column(name = "apellido_2do", length = 45)
    private String apellido2do;
    @Basic(optional = false)
    @Column(name = "nomb_usuario", nullable = false, length = 45)
    private String nombUsuario;
    @Column(name = "contrase\u00f1a", length = 1024)
    private String contraseña;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @JoinColumn(name = "rol_idrol", referencedColumnName = "idrol")
    @ManyToOne
    private Rol rolIdrol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdusuario")
    private List<UsuarioHasEjercicio> usuarioHasEjercicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioidusuario")
    private List<RespuestaExperimentacion> respuestaExperimentacionList;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String apellido1er, String nombUsuario) {
        this.idusuario = idusuario;
        this.apellido1er = apellido1er;
        this.nombUsuario = nombUsuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getApellido1er() {
        return apellido1er;
    }

    public void setApellido1er(String apellido1er) {
        this.apellido1er = apellido1er;
    }

    public String getApellido2do() {
        return apellido2do;
    }

    public void setApellido2do(String apellido2do) {
        this.apellido2do = apellido2do;
    }

    public String getNombUsuario() {
        return nombUsuario;
    }

    public void setNombUsuario(String nombUsuario) {
        this.nombUsuario = nombUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRolIdrol() {
        return rolIdrol;
    }

    public void setRolIdrol(Rol rolIdrol) {
        this.rolIdrol = rolIdrol;
    }

    @XmlTransient
    public List<UsuarioHasEjercicio> getUsuarioHasEjercicioList() {
        return usuarioHasEjercicioList;
    }

    public void setUsuarioHasEjercicioList(List<UsuarioHasEjercicio> usuarioHasEjercicioList) {
        this.usuarioHasEjercicioList = usuarioHasEjercicioList;
    }

    @XmlTransient
    public List<RespuestaExperimentacion> getRespuestaExperimentacionList() {
        return respuestaExperimentacionList;
    }

    public void setRespuestaExperimentacionList(List<RespuestaExperimentacion> respuestaExperimentacionList) {
        this.respuestaExperimentacionList = respuestaExperimentacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
