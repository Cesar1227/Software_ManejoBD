
import Modelo.DataBase;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cesar Bonilla
 */
public class Main {

    DataBase objDb;
    Scanner sc;

    public static void main(String[] args) {
        Main obj = new Main();
        obj.sc = new Scanner(System.in);
        obj.init();
    }

    public void init() {
        objDb = new DataBase();
        if (!objDb.isConected()) {
            System.out.println("NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS");
        } else {
            operar(objDb);
        }
    }

    void consultar() {
        List<Usuario> usuarios = objDb.consultarDatos();
        usuarios.forEach(user -> {
            System.out.println(user.toString());
        });
    }

    void insertar() {
        Usuario objUser;
        objUser = new Usuario();
        objUser.llenarAleatorio();
        objDb.insertar(objUser);
        System.out.println(objUser.toString());
    }

    void modificar() {
        Usuario objUser;
        objUser = new Usuario();
        String nombre;
        sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del usuario a modificar: ");
        objUser.setId((sc.nextInt()));
        System.out.print("Ingrese el NOMBRE: ");
        nombre = sc.next();
        System.out.print("Ingrese el APELLIDO: ");
        nombre = nombre + " " + sc.next();
        objUser.setNombre(nombre);
        System.out.print("Ingrese la EDAD: ");
        objUser.setEdad((sc.nextInt()));
        System.out.print("Ingrese la PROFESIÓN: ");
        objUser.setProfesion(sc.next());

        if (objDb.modificar(objUser)) {
            System.out.println("\nMODIFICADO EXITOSAMENTE");
        } else {
            System.out.println("\nERROR AL MODIFICAR");
        }
    }

    void eliminar() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id;
        id = sc.nextInt();
        if (objDb.eliminar(id)) {
            System.out.println("Usuario con id " + id + " eliminado correctamente");
        } else {
            System.out.println("NO FUE POSIBLE ELIMINAR EL USUARIO");
        }
    }

    void aplicarTransacion() {
        System.out.println(objDb.realizarCommit());
    }

    void descartarTransacion() {
        System.out.println(objDb.realizarRollBack());
    }

    void salir() {
        objDb.cerrarConexion();
        System.exit(0);
    }

    public void operar(DataBase objDb) {
        int entrada;
        while (true) {
            System.out.println("\nDIGITE EL NÚMERO DE LA OPERACIÓN QUE DESEA EJECUTAR:\n"
                    + "1. CONSULTAR USUARIOS\n"
                    + "2. INSERTAR USUARIO\n"
                    + "3. ACTUALIZAR USUARIO\n"
                    + "4. ELIMINAR USUARIO\n"
                    + "5. APLICAR TRANSACIÓN\n"
                    + "6. DESCARTAR TRANSACIÓN\n"
                    + "7. SALIR\n");

            System.out.print(">>: ");
            entrada = sc.nextInt();
            System.out.println("");
            switch (entrada) {
                //Consultar datos
                case 1:
                    consultar();
                    break;
                //Insertar 
                case 2:
                    this.insertar();
                    break;
                //Actualizar registro
                case 3:
                    modificar();
                    break;
                //Eliminar registro
                case 4:
                    eliminar();
                    break;
                //Aplicar transación
                case 5:
                    aplicarTransacion();
                    break;
                case 6:
                    descartarTransacion();
                    break;
                case 7:
                    salir();
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }

        }
    }

}
