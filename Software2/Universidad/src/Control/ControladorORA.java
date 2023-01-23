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
public class ControladorORA {

    DataBaseORA objDbORA;
    Scanner sc;

    public ControladorORA() {
        objDbORA = new DataBaseORA();
        if (!objDbORA.isConected()) {
            System.err.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE ORACLE");
        }
    }

    public List<Usuario> consultarUsuarios() {
        List<Usuario> usuarios = objDbORA.consultarDatos();
        /*usuarios.forEach(user -> {
            System.out.println(user.toString());
        });*/
        return usuarios;
    }

    public boolean insertarUsuario() {
        Usuario objUser;
        objUser = new Usuario();
        objUser.llenarAleatorio();

        //System.out.println(objUser.toString());
        return objDbORA.insertarUsuario(objUser);
    }

    public boolean insertarUsuario(Usuario user) {
        if (user != null) {
            return objDbORA.insertarUsuario(user);
        } else {
            System.err.println("El objeto usuario no puede ser NULL");
            return false;
        }
    }

    public boolean modificarUsuario(Usuario user) {
        return objDbORA.modificarUsuario(user);
    }

    public boolean eliminarUsuario(int id) {
        return objDbORA.eliminarUsuario(id);
    }

    public boolean aplicarTransacionORA() {
        return objDbORA.realizarCommit();
    }

    public boolean descartarTransacionORA() {
        return objDbORA.realizarRollBack();
    }

    /**
     * LLamar procedimiento de Oracle
     *
     * @param num1
     * @param num2
     * @return
     */
    public String procedimiento1(int num1, int num2) {
        String respuesta;
        respuesta = objDbORA.llamarProcedimiento1(num1, num2);
        if (respuesta.equals("iguales")) {
            return ("Los números son iguales");
        } else {
            return ("El número " + respuesta + " es el mayor");
        }
    }

    public String funcion1(String oper) {
        String respuesta = String.valueOf(objDbORA.llamarFuncion1(oper));
        return respuesta;
    }

    public String funcion2(int codigo) {
        String respuesta = String.valueOf(objDbORA.llamarFuncion2(codigo));
        return respuesta;   
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
                    + "0. VOLVER\n");

            System.out.print(">>: ");
            entrada = sc.nextInt();
            System.out.println("");
            switch (entrada) {
                //Consultar datos
                case 1:
                    consultarUsuarios();
                    break;
                //Insertar 
                case 2:
                    this.insertarUsuario();
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
                    aplicarTransacionORA();
                    break;
                case 6:
                    descartarTransacionORA();
                    break;
                case 0:
                    entrada = 0;
                    break;
                case 7:
                    procedimientosORA(1);
                    break;
                case 8:
                    funcion1(1);
                    break;
                case 9:
                    funcion1(2);
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }
        } while (entrada != 0);
    }

}
