/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.conexion.ado.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Christian
 */
public class PSQLConexion {
    Connection conectar(){
        Connection conexion = null;
        String url = "jdbc:postgresql://127.0.0.1:5432/prueba";
        Properties propiedades = new Properties();
        propiedades.setProperty("user", "ursprueba");
        propiedades.setProperty("password", "abcd.12345");
        try {
            conexion = DriverManager.getConnection(url, propiedades);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return conexion;
    }
}
