/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.ControladorORA;
import Control.ControladorSQLS;
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Cesar Bonilla
 */
public class Main {

    Scanner sc;
    ControladorORA objControlORA;
    ControladorSQLS objControlSQLS;

    public static void Main(String[] args) {
        Main objMain = new Main();
    }

    public Main() {
        objControlORA = new ControladorORA();
        objControlSQLS = new ControladorSQLS();
        sc = new Scanner(System.in);
    }

    void modificarUsuario() {
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

        if (objControlORA.modificarUsuario(objUser)) {
            System.out.println("\nMODIFICADO EXITOSAMENTE");
        } else {
            System.out.println("\nERROR AL MODIFICAR");
        }
    }

    void eliminarUsuario() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id;
        id = sc.nextInt();
        if (objControlORA.eliminarUsuario(id)) {
            System.out.println("Usuario con id " + id + " eliminado correctamente");
        } else {
            System.out.println("NO FUE POSIBLE ELIMINAR EL USUARIO");
        }
    }

    void procedimientoORA1() {
        int num1, num2;
        System.out.print("Ingrese el primer número a comparar: ");
        num1 = sc.nextInt();
        System.out.print("Ingrese el segundo número a comparar: ");
        num2 = sc.nextInt();
        String res = objControlORA.procedimiento1(num1, num2);
        System.out.println(res);
    }

    void funcionORA1() {
        boolean correct = true;
        System.out.println("Seleccion la operación sobre la que desea consultar");
        System.out.println("1. INSERT\n"
                + "2. UPDATE\n"
                + "3. DELETE\n");
        int opc = sc.nextInt();
        String operacion = null;
        switch (opc) {
            case 1:
                operacion = "INSERT";
                break;
            case 2:
                operacion = "UPDATE";
                break;
            case 3:
                operacion = "DELETE";
                break;
            default:
                System.out.println("Opción incorrecta");
                correct = false;
                break;
        }
        String respuesta = objControlORA.funcion1(operacion);
        System.out.println("SE HAN REALIZADO " + respuesta + " operaciones DML sobre la tabla auditorias");
    }
    
    void funcion2(){
        int codigo;
        System.out.print("Ingrese el código del estudiante: ");
        codigo = sc.nextInt();
        String respuesta = objControlORA.funcion2(codigo);
        System.out.println("El nombre del estudiante con código " + codigo + " es " + respuesta);
    }

    void salir() {
        //objDbORA.cerrarConexion();
        System.exit(0);
    }

    public void operar() {
        int entrada;
        while (true) {

            System.out.println("\n¿A QUÉ BASE DE DATOS DESEA INGRESAR?\n"
                    + "1. ORACLE\n"
                    + "2. SQLSERVER\n"
                    + "0. SALIR\n");
            System.out.print(">>: ");
            entrada = sc.nextInt();
            System.out.println("");

            switch (entrada) {
                case 0:
                    salir();
                    break;
                case 1:
                    objControlORA.opcionesOracle();
                    break;
                case 2:
                    objControlSQLS.opcionesSQLSERVER();
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }

        }
    }

}
