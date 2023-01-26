/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.ControladorORA;
import Control.ControladorSQLS;
import Modelo.Usuario;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner sc;
    ControladorORA objControlORA;
    ControladorSQLS objControlSQLS;

    public static void main(String[] args) {
        Main objMain = new Main();
    }

    public Main() {
        objControlORA = new ControladorORA();
        objControlSQLS = new ControladorSQLS();
        sc = new Scanner(System.in);
        this.operar();
    }

    void modificarUsuario(String aux) {
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
        if ("oracle".equals(aux)){
            if (objControlORA.modificarUsuario(objUser)) {
                System.out.println("\nMODIFICADO EXITOSAMENTE");
            } else {
                System.out.println("\nERROR AL MODIFICAR");
            }
        }else{
            if (objControlSQLS.modificarUsuario(objUser)) {
                System.out.println("\nMODIFICADO EXITOSAMENTE");
            } else {
                System.out.println("\nERROR AL MODIFICAR");
            }
        }
    }

    void eliminarUsuario(String aux) {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id;
        id = sc.nextInt();
        if ("oracle".equals(aux)){
            if (objControlORA.eliminarUsuario(id)) {
                System.out.println("Usuario con id " + id + " eliminado correctamente");
            } else {
                System.out.println("NO FUE POSIBLE ELIMINAR EL USUARIO");
            }
        }else{
            if (objControlSQLS.eliminarUsuario(id)) {
                System.out.println("Usuario con id " + id + " eliminado correctamente");
            } else {
                System.out.println("NO FUE POSIBLE ELIMINAR EL USUARIO");
            }
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

    void funcionORA2() {
        int codigo;
        
        System.out.print("Ingrese el código del estudiante: ");
        codigo = sc.nextInt();
        
        String respuesta = objControlORA.obtenerNomEstudiante(codigo);
        System.out.println("El nombre del estudiante con código " + codigo + " es " + respuesta);
    }

    void procedimientoSSER1() {
        int num1, num2;
        String respuesta;
        
        System.out.print("Ingrese el primer número a comparar: ");
        num1 = sc.nextInt();

        System.out.print("Ingrese el segundo número a comparar: ");
        num2 = sc.nextInt();

        respuesta = objControlSQLS.procedimiento1(num1, num2);
        if (respuesta.equals("iguales")) {
            System.out.println("Los números son iguales");
        } else {
            System.out.println("El número " + respuesta + " es el mayor");
        }
    }

    void funcionSSER1() {
        String respuesta;
        int codigo;
        
        System.out.print("Ingrese el código del estudiante: ");
        codigo = sc.nextInt();
        
        respuesta = String.valueOf(objControlSQLS.funcion1(codigo));
        System.out.println("El promedio del estudiante con código " + codigo + " es " + respuesta);
    }

    public void opcionesOracle() {
        int entrada;
        do {
            System.out.println("\nDIGITE EL NÚMERO DE LA OPERACIÓN QUE DESEA EJECUTAR:\n"
                    + "1. CONSULTAR USUARIOS\n"
                    + "2. INSERTAR USUARIO\n"
                    + "3. ACTUALIZAR USUARIO\n"
                    + "4. ELIMINAR USUARIO\n"
                    + "5. APLICAR TRANSACIÓN\n"
                    + "6. DESCARTAR TRANSACIÓN\n"
                    + "7. LLAMAR PROCEDIMIENTO 1\n"
                    + "8. LLAMAR FUNCIÓN 1\n"
                    + "9. LLAMAR FUNCIÓN 2\n"
                    + "10. LLAMAR PROCEDIMIENTO 2\n"
                    + "0. VOLVER\n");

            System.out.print(">>: ");
            entrada = sc.nextInt();
            
            System.out.println("");
            String resp;
            switch (entrada) {
                case 1:
                    List<Usuario> usuarios;
                    usuarios=objControlORA.consultarUsuarios();
                    System.out.println(usuarios.toString());
                    break;
                case 2:
                    if(objControlORA.insertarUsuario()){
                        System.out.println("Usuario ingresado correctamente");
                    }else{
                        System.out.println("No fue posible ingresar el usuario");
                    }
                    break;
                case 3:
                    this.modificarUsuario("oracle");
                    break;
                case 4:
                    this.eliminarUsuario("oracle");
                    break;
                case 5:
                    if(objControlORA.aplicarTransacionORA()){
                        System.out.println("Transacción realizada");
                    }else{
                        System.out.println("No fue posible realizar la transacción");
                    }
                    break;
                case 6:
                    if(objControlORA.descartarTransacionORA()){
                        System.out.println("Rollback realizada");
                    }else{
                        System.out.println("No fue posible realizar el rollback");
                    }
                    break;
                case 7:
                    this.procedimientoORA1();
                    break;
                case 8:
                    this.funcionORA1();
                    break;
                case 9:
                    this.funcionORA2();
                    break;
                case 0:
                    entrada = 0;
                    break;
                case 10:
                    System.out.println(objControlORA.informacionEstudiantes());
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }
        } while (entrada != 0);
    }

    public void opcionesSQLSERVER() {
        int entrada;
        do {
            System.out.println("\nDIGITE EL NÚMERO DE LA OPERACIÓN QUE DESEA EJECUTAR:\n"
                    + "1. CONSULTAR USUARIOS\n"
                    + "2. INSERTAR USUARIO\n"
                    + "3. ACTUALIZAR USUARIO\n"
                    + "4. ELIMINAR USUARIO\n"
                    + "5. INICIAR TRANSACCIÓN"
                    + "6. APLICAR TRANSACIÓN\n"
                    + "7. DESCARTAR TRANSACIÓN\n"
                    + "8. LLAMAR PROCEDIMIENTO 1\n"
                    + "9. LLAMAR FUNCIÓN 1\n"
                    + "0. VOLVER\n");

            System.out.print(">>: ");
            entrada = sc.nextInt();
            
            System.out.println("");
            switch (entrada) {
                case 1:
                    List<Usuario> usuarios;
                    usuarios=objControlSQLS.consultarUsuarios();
                    System.out.println(usuarios.toString());
                    break;
                case 2:
                    if(objControlSQLS.insertarUsuario()){
                        System.out.println("Usuario ingresado correctamente");
                    }else{
                        System.out.println("No fue posible ingresar el usuario");
                    }
                    break;
                case 3:
                    this.modificarUsuario("sqlsserver");
                    break;
                case 4:
                    this.eliminarUsuario("sqlsserver");
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    procedimientoSSER1();
                    break;
                case 9:
                    funcionSSER1();
                    break;
                case 0:
                    entrada = 0;
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }
        } while (entrada != 0);
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
                    this.opcionesOracle();
                    break;
                case 2:
                    this.opcionesSQLSERVER();
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }

        }
    }

}
