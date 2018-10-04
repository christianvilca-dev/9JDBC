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
import pe.conexion.excepciones.ExcepcionGeneral;
import pe.conexion.modelos.Usuario;

/**
 *
 * @author Christian
 */
public class Ejecutar {
    public static void main(String[] args) {
//        Usuario user = new Usuario();
//        user.setIdUsuario(5);
//        user.setUsuario("Tom");
//        user.setClave("otraclave");
//        user.setCorreo("tom@gmail.com");
        
        MySQLUsuario mysqluser = new MySQLUsuario();
        try {
            List<Usuario> listado = mysqluser.listar();
            
            for (Usuario u : listado) {
                System.out.println(u);
            }
        } catch (ExcepcionGeneral eg) {
            System.out.println(eg.getMessage());
        }
//        Usuario{idUsuario=1, usuario=christian, clave=1234, correo=christian@gmail.com}
//        Usuario{idUsuario=2, usuario=Usuario2, clave=1234, correo=usuario@gmail.com}
//        Usuario{idUsuario=4, usuario=Tom, clave=d9ffaca46d5990ec39501bcdf22ee7a1, correo=tom@gmail.com}
//        Usuario{idUsuario=6, usuario=Tom, clave=d9ffaca46d5990ec39501bcdf22ee7a1, correo=tom@gmail.com}
    }
}
