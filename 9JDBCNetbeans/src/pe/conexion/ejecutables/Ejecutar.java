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
        UsuarioDAO userDAO = null;
        byte opcion;
        
        mostrarOpcionBases();
        opcion = teclado.nextByte();
        
        switch(opcion) {
            case 1:
                userDAO = new PSQLUsuario();
                break;
            case 2:
                userDAO = new MySQLUsuario();
                break;
            default:
                System.out.println("No es una opcion valida");
                System.exit(0);
        }
        
        mostrarOpcionCRUD();
        opcion = teclado.nextByte();
        
        switch (opcion) { 
            case 1:
                insertarUsuario(userDAO);
		break;
            case 2:
		listarUsuarios(userDAO);
		break;
		default:
		System.out.println("Opcion no valida!!!");
		System.exit(0);
        }
    }

    private static void mostrarOpcionBases() {
        System.out.println("************************************");
        System.out.println("** Seleccione la BD que va a usar **");
        System.out.println("** 1. Postgresql                  **");
        System.out.println("** 2. MySQL                       **");
        System.out.println("************************************");
        System.out.print("   Digite su opción:  ");
    }
    
    private static void mostrarOpcionCRUD() {
        System.out.println("************************************");
        System.out.println("** Seleccione opcion de ejecutar  **");
        System.out.println("** 1. Insertar                    **");
        System.out.println("** 2. Listar                      **");
        System.out.println("************************************");
        System.out.print("   Digite su opción:  ");
    }

    private static void insertarUsuario(UsuarioDAO userDAO) {
        Usuario usuario = new Usuario();
        usuario.setUsuario("Pedro");
        usuario.setClave("MuySegura");
        usuario.setCorreo("pedro@gmail.com");
        try {
            userDAO.insert(usuario);
            System.out.println("Usuario insertado con el ID: " + usuario.getIdUsuario());
        } catch (ExcepcionGeneral eg) {
            System.out.println(eg.getMessage());
        }
    }

    private static void listarUsuarios(UsuarioDAO userDAO) {
        try {
            List<Usuario> listado = userDAO.listar();
            for (Usuario usuario : listado) {
                System.out.println(usuario);
            }
        } catch (ExcepcionGeneral eg) {
            System.out.println(eg.getMessage());
        }
    }
}
