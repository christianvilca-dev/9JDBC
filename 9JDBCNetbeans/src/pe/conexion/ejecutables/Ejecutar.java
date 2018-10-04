/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.conexion.ejecutables;

import java.util.List;
import java.util.Scanner;
import pe.conexion.ado.interfaces.UsuarioDAO;
import pe.conexion.ado.mysql.MySQLUsuario;
import pe.conexion.ado.postgresql.PSQLConexion;
import pe.conexion.ado.postgresql.PSQLUsuario;
import pe.conexion.excepciones.ExcepcionGeneral;
import pe.conexion.modelos.Usuario;

/**
 *
 * @author Christian
 */
public class Ejecutar {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.setIdUsuario(2);
        user.setUsuario("Tom");
        user.setClave("otraclave");
        user.setCorreo("tom@gmail.com");
        
        PSQLUsuario psqluser = new PSQLUsuario();
        try {
            psqluser.insert(user);
            System.out.println("Usuario registrado con el id: " + user.getIdUsuario());
        } catch (ExcepcionGeneral eg) {
            System.out.println(eg.getMessage());
        }
        
        
//        MySQLUsuario mysqluser = new MySQLUsuario();
//        try {
//            List<Usuario> listado = mysqluser.listar();
//            
//            for (Usuario u : listado) {
//                System.out.println(u);
//            }
//        } catch (ExcepcionGeneral eg) {
//            System.out.println(eg.getMessage());
//        }
    }
}
