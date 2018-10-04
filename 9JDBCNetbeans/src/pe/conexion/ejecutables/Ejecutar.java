/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.conexion.ejecutables;

import java.util.Scanner;
import pe.conexion.ado.interfaces.UsuarioDAO;
import pe.conexion.ado.mysql.MySQLUsuario;
import pe.conexion.modelos.Usuario;

/**
 *
 * @author Christian
 */
public class Ejecutar {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.setUsuario("Tom");
        user.setClave("Tom");
        user.setCorreo("tom@gmail.com");
        
        MySQLUsuario mysqluser = new MySQLUsuario();
        mysqluser.insert(user);
    }
}
