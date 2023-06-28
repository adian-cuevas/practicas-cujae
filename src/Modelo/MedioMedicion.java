/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Auxiliar.ConexionMySQL;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author adian
 */
public class MedioMedicion implements Serializable {

    private Integer idmedio;
    private String nombMedio;
    private String descripMedio;
    private byte[] fotoMedio;

    public MedioMedicion() {
    }

    public byte[] getFoto() {
        return this.fotoMedio;
    }

    public void setFoto(String nombre) {
        try (Connection conn = ConexionMySQL.obtenerConexion();
                PreparedStatement stmt = conn.prepareStatement("SELECT foto_medio FROM medio WHERE nomb_medio = ?");) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.fotoMedio = rs.getBytes("foto_medio");
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            System.out.println(e);
        }
    }

    public void setId(String nombre) {
        try (Connection conn = ConexionMySQL.obtenerConexion();
                PreparedStatement stmt = conn.prepareStatement("SELECT idmedio FROM medio WHERE nomb_medio = ?");) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.idmedio = rs.getInt("idmedio");
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            System.out.println(e);
        }
    }
}
