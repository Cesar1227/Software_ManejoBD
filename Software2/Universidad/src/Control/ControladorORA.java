package Control;

import BaseDeDatos.DataBaseORA;
import Modelo.Estudiante;
import Modelo.Usuario;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ControladorORA {

    DataBaseORA objDbORA;
    Usuario objUsuario;
    Estudiante objEstu;
    Scanner sc;

    public ControladorORA() {
        objDbORA = new DataBaseORA();
        objUsuario = new Usuario();
        objEstu = new Estudiante();
        if (!objDbORA.isConected()) {
            System.err.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE ORACLE");
        }
    }

    public List<Usuario> consultarUsuarios() {
        /*usuarios.forEach(user -> {
            System.out.println(user.toString());
        });*/
        return objUsuario.consultar("oracle");
    }

    /*public boolean insertarUsuario() {
        return objUsuario.insertar("oracle");
    }*/

    public boolean insertarUsuario(Usuario user) {
        return objUsuario.insertar(user,"oracle");
    }

    public boolean modificarUsuario(Usuario user) {
        return objUsuario.modificar(user,"oracle");
    }

    public boolean eliminarUsuario(int id) {
        return objUsuario.eliminar(id,"oracle");
    }

    public boolean aplicarTransacionORA() {
        return objDbORA.realizarCommit();
    }

    public boolean descartarTransacionORA() {
        return objDbORA.realizarRollBack();
    }

    /**
     * LLamar procedimiento de Oracle información de Estudiantes
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
    
    public String informacionEstudiantes(){
        return objEstu.obtenerInformacion();
    }

    //FUNCIÓN 1
    public String funcion1(String oper) {
        String respuesta = String.valueOf(objDbORA.llamarFuncion1(oper));
        return respuesta;
    }

    //FUNCIÓN 2
    public String obtenerNomEstudiante(int codigo) {
        String respuesta = objEstu.obtenerNombreEstudiante(codigo);
        return respuesta;   
    }

    

}
