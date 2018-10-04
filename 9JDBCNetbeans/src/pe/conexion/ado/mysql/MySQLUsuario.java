/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.conexion.ado.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import pe.conexion.ado.interfaces.UsuarioDAO;
import pe.conexion.excepciones.ExcepcionGeneral;
import pe.conexion.modelos.Usuario;

/**
 *
 * @author Christian
 */
public class MySQLUsuario implements UsuarioDAO{
    
    // No afecta el sqlInjection puesto que lo toma como un valor a buscar
    // Se utiliza si no usuamos procedimientos almacenados(ya obligaria a solo usar una BD)
    private final String INSERTAR = "INSERT INTO usuarios (usuario, clave, correo) values (?, md5(?), ?)";
    private final String MODIFICAR = "UPDATE usuarios SET usuario = ?, clave = md5(?), correo = ? WHERE id_usuario = ?";
    private final String ELIMINAR = "DELETE FROM usuarios WHERE id_usuario = ?";
    private final String OBTENERPORID = "SELECT id_usuario, usuario, clave, correo FROM usuarios WHERE id_usuario = ?";
    private final String OBTENER = "SELECT id_usuario, usuario, clave, correo FROM usuarios";
    
    private Connection conexion;
    // Recomendado usarlo asi no halla parametros en vez de 
    // ("statement" -> 
    // unParametro = "' or 1 = 1 --'";
    // "insert into tabla (a,b) values ('" + unParametro +"', '" + otroParametro +"')"; )
    // Con lo que se evita ataques "sqlInjection"
    private PreparedStatement sentencia;
    private ResultSet resultados;
    
    @Override
    public void insert(Usuario o) throws ExcepcionGeneral{
        try {
            conexion = new MySQLConexion().conectar();
            // RETURN_GENERATED_KEYS -> nos permite obtener las llaves generadas de mysql
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, o.getUsuario());
            sentencia.setString(2, o.getClave());
            sentencia.setString(3, o.getCorreo());
            // executeUpdate = INSERT, UPDATE, DELETE
            // executeQuery = SELECT
            if (sentencia.executeUpdate() ==0) { // Devuelve la cantidad de registros que fueron afectados
                throw new ExcepcionGeneral("No se inserto el registro");
            }
            // Devuelve un set de resultados(registros) con las llaves que se han generado
            // ubicado antes del primer registro
            resultados = sentencia.getGeneratedKeys(); 
            // Para saber si hay registros se utiliza "next()" y devuelve "true", en el ultimo registro devuelve "false"
            if (resultados.next()) {
                // Obtener el dato entero de la primera columna
                o.setIdUsuario(resultados.getInt(1));
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
    public void obtenerPorId(Integer k) {
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
