/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.conexion.ejecutables;

import java.util.Scanner;
import pe.conexion.ado.interfaces.UsuarioDAO;
import pe.conexion.ado.mysql.MySQLUsuario;
import pe.conexion.excepciones.ExcepcionGeneral;
import pe.conexion.modelos.Usuario;

/**
 *
 * @author Christian
 */
public class Ejecutar {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.setIdUsuario(5);
        user.setUsuario("Tom");
        user.setClave("otraclave");
        user.setCorreo("tom@gmail.com");
        
        MySQLUsuario mysqluser = new MySQLUsuario();
        try {
            mysqluser.eliminar(user);
        } catch (ExcepcionGeneral eg) {
            System.out.println(eg.getMessage());
        }
    }
}
