/*
 * Tiene toda la informacion necesaria para crear una conexion BD
 */
package pe.conexion.ado.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christian
 */
public class MySQLConexion {
    
    // Acceso a nivel de paquete
    Connection conectar() throws SQLException{
        Connection conexion = null;
        String url = "jdbc:mysql://127.0.0.1/prueba";
        try {
            conexion = DriverManager.getConnection(url, "root", "abcd.1234");
        } catch (SQLException sqle) {
            throw sqle;
        }
        return conexion;
    }
}
