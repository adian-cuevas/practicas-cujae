/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adian
 */
public class ConexionMySQL {

    public static Connection obtenerConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String usuario = "adian";
        String contrasena = "adian.cuevas";
        Connection conn = DriverManager.getConnection(url, usuario, contrasena);
        return conn;
    }
}
