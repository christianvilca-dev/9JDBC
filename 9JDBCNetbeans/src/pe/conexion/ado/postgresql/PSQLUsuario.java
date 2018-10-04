/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.conexion.ado.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import pe.conexion.ado.interfaces.UsuarioDAO;
import pe.conexion.ado.mysql.MySQLConexion;
import pe.conexion.excepciones.ExcepcionGeneral;
import pe.conexion.modelos.Usuario;

/**
 *
 * @author Christian
 */
public class PSQLUsuario implements UsuarioDAO{
    
    private final String INSERTAR = "INSERT INTO usuarios (usuario, clave, correo) VALUES (?, md5(?), ?) RETURNING id_usuario";
    private final String MODIFICAR = "UPDATE usuarios SET usuario = ?, clave = md5(?), correo = ? WHERE id_usuario = ?";
    private final String ELIMINAR = "DELETE FROM usuarios WHERE id_usuario = ?";
    private final String OBTENERPORID = "SELECT id_usuario, usuario, clave, correo FROM usuarios WHERE id_usuario = ?";
    private final String OBTENER = "SELECT id_usuario, usuario, clave, correo FROM usuarios";
    
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;
    
    @Override
    public void insert(Usuario o) throws ExcepcionGeneral{
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getUsuario());
            sentencia.setString(2, o.getClave());
            sentencia.setString(3, o.getCorreo());
            resultados = sentencia.executeQuery();
            if (resultados.next()) {
                o.setIdUsuario(resultados.getInt(1));
            } else { 
                throw new ExcepcionGeneral("No se inserto el registro");
            }
        } catch (SQLException sqle) {
            throw new ExcepcionGeneral(sqle.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void modificar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario obtenerPorId(Integer k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void cerrarConexiones(){
        try {
            if (resultados!= null) {
                resultados.close();
            }
            if (sentencia!= null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException sqle) {
        }
    }
}
