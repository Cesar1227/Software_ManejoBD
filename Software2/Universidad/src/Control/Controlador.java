package Control;

import BaseDeDatos.DataBaseORA;
import BaseDeDatos.DataBaseSQLS;
import Modelo.Usuario;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cesar Bonilla
 */
public class Controlador {

    DataBaseORA objDbORA;
    DataBaseSQLS objDbSQLS;
    Scanner sc;

    public static void main(String[] args) {
        Controlador obj = new Controlador();
        obj.sc = new Scanner(System.in);
        obj.init();
    }

    public void init() {
        objDbORA = new DataBaseORA();
        objDbSQLS = new DataBaseSQLS();
        if (!objDbORA.isConected() && !objDbSQLS.isConected()) {
            if (!objDbORA.isConected()) {
                System.out.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE ORACLE");
            } else {
                System.out.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE SQLSERVER");
            }
        } else {
            operar(objDbORA, objDbSQLS);
        }

    }

    public List<Usuario> consultar() {
        List<Usuario> usuarios = objDbORA.consultarDatos();
        /*usuarios.forEach(user -> {
            System.out.println(user.toString());
        });*/
        return usuarios;
    }

    public boolean insertar() {
        Usuario objUser;
        objUser = new Usuario();
        objUser.llenarAleatorio();
        
        //System.out.println(objUser.toString());
        return objDbORA.insertar(objUser);
    }

    public boolean insertar(Usuario user) {
        if(user != null){
            return objDbORA.insertar(user);
        }else{
            System.err.println("El objeto usuario no puede ser NULL");
            return false;
        }
    }

    public boolean modificarUsuario(Usuario user) {
       
        
    }

    void eliminar() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id;
        id = sc.nextInt();
        if (objDbORA.eliminar(id)) {
            System.out.println("Usuario con id " + id + " eliminado correctamente");
        } else {
            System.out.println("NO FUE POSIBLE ELIMINAR EL USUARIO");
        }
    }

    void aplicarTransacion() {
        System.out.println(objDbORA.realizarCommit());
    }

    void descartarTransacion() {
        System.out.println(objDbORA.realizarRollBack());
    }

    /**
     * LLamar procedimiento de Oracle
     *
     * @param procedimiento procedimiento a llamar
     */
    void procedimientosORA(int procedimiento) {
        if (procedimiento == (1)) {
            int num1, num2;
            String respuesta;
            sc = new Scanner(System.in);
            System.out.print("Ingrese el primer número a comparar: ");
            num1 = sc.nextInt();
            System.out.print("Ingrese el segundo número a comparar: ");
            num2 = sc.nextInt();
            respuesta = objDbORA.llamarProcedimiento1(num1, num2);
            if (respuesta.equals("iguales")) {
                System.out.println("Los números son iguales");
            } else {
                System.out.println("El número " + respuesta + " es el mayor");
            }
        }
    }

    void funcionesORA(int func) {
        String respuesta;
        if (func == 1) {
            boolean correct = true;
            System.out.println("Seleccion la operación sobre la que desea consultar");
            System.out.println("1. INSERT\n"
                    + "2. UPDATE\n"
                    + "3. DELETE\n");
            sc = new Scanner(System.in);
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
            if (correct) {
                respuesta = String.valueOf(objDbORA.llamarFuncion1(operacion));
                System.out.println("SE HAN REALIZADO " + respuesta + " operaciones DML sobre la tabla auditorias");
            }
        } else if (func == 2) {
            int codigo;
            sc = new Scanner(System.in);
            System.out.print("Ingrese el código del estudiante: ");
            codigo = sc.nextInt();
            respuesta = objDbORA.llamarFuncion2(codigo);
            System.out.println("El nombre del estudiante con código " + codigo + " es " + respuesta);
        }

    }

    void procedimientosSSER() {
        int num1, num2;
        String respuesta;
        sc = new Scanner(System.in);
        System.out.print("Ingrese el primer número a comparar: ");
        num1 = sc.nextInt();
        System.out.print("Ingrese el segundo número a comparar: ");
        num2 = sc.nextInt();
        respuesta = objDbSQLS.llamarProcedimiento1(num1, num2);
        if (respuesta.equals("iguales")) {
            System.out.println("Los números son iguales");
        } else {
            System.out.println("El número " + respuesta + " es el mayor");
        }
    }

    void funcionesSSER() {
        String respuesta;
        int codigo;
        sc = new Scanner(System.in);
        System.out.print("Ingrese el código del estudiante: ");
        codigo = sc.nextInt();
        respuesta = Float.toString(objDbSQLS.llamarFuncion1(codigo));
        System.out.println("El promedio del estudiante con código " + codigo + " es " + respuesta);
    }

    void salir() {
        objDbORA.cerrarConexion();
        System.exit(0);
    }

    void opcionesOracle() {
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
                    + "0. VOLVER\n");

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
                    modificarUsuario();
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
                case 0:
                    entrada = 0;
                    break;
                case 7:
                    procedimientosORA(1);
                    break;
                case 8:
                    funcionesORA(1);
                    break;
                case 9:
                    funcionesORA(2);
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }
        } while (entrada != 0);
    }

    void opcionesSQLSERVER() {
        int entrada;
        do {
            System.out.println("\nDIGITE EL NÚMERO DE LA OPERACIÓN QUE DESEA EJECUTAR:\n"
                    + "1. LLAMAR PROCEDIMIENTO 1\n"
                    + "2. LLAMAR FUNCIÓN 1\n"
                    + "0. VOLVER\n");

            System.out.print(">>: ");
            entrada = sc.nextInt();
            System.out.println("");
            switch (entrada) {
                case 1:
                    procedimientosSSER();
                    break;
                case 2:
                    funcionesSSER();
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

    public void operar(DataBaseORA objDbORA, DataBaseSQLS objDbSQLS) {
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
                    opcionesOracle();
                    break;
                case 2:
                    opcionesSQLSERVER();
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }

        }
    }

}
