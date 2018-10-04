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
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione la BD que va a usar");
        System.out.println("1. Postgresql");
        System.out.println("2. MySQL");
        int opcion = teclado.nextInt();
        
        UsuarioDAO userDAO = null;
        
        switch (opcion){
            case 1:
                userDAO = new PSQLUsuario();
                break;
            case 2:
                userDAO = new MySQLUsuario();
                break;
            default:
                System.out.println("No es una opcion valida");
        }
        
        Usuario user = new Usuario();
        user.setIdUsuario(2);
        user.setUsuario("otro");
        user.setClave("otro");
        user.setCorreo("otro@gmail.com");
        
        try {
            // No importa si es una BD relacional o no relacional
            userDAO.insert(user);
//            List<Usuario> listado = userDAO.listar();
//            for (Usuario u : listado) {
//                System.out.println(u);
//            }
        } catch (ExcepcionGeneral eg) {
            System.out.println(eg.getMessage());
        }
        
//        PSQLUsuario psqluser = new PSQLUsuario();
//        try {
//            psqluser.insert(user);
//            System.out.println("Usuario registrado con el id: " + user.getIdUsuario());
//        } catch (ExcepcionGeneral eg) {
//            System.out.println(eg.getMessage());
//        }
        
        
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
