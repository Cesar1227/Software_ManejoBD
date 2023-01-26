/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.DataBaseSQLS;
import Modelo.Estudiante;
import Modelo.Usuario;
import java.util.List;
import java.util.Scanner;


public class ControladorSQLS {

    DataBaseSQLS objDbSQLS;
    Usuario objUsuario;
    Estudiante objEstu;
    Scanner sc;

    public ControladorSQLS() {
        objDbSQLS = new DataBaseSQLS();
        objUsuario = new Usuario();
        objEstu = new Estudiante();
        if (!objDbSQLS.isConected()) {
            System.err.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE SQLSERVER");
        }
    }

    public String procedimiento1(int num1, int num2){
        String respuesta;
        respuesta = objDbSQLS.llamarProcedimiento1(num1, num2);
        if (respuesta.equals("iguales")) {
            return ("iguales");
        } else {
            return (respuesta);
        }
    }

    public Float funcion1(int codigo){
        return objEstu.obtenerPromedio(codigo, "SQLS");
    }
    public List<Usuario> consultarUsuarios() {
        /*usuarios.forEach(user -> {
            System.out.println(user.toString());
        });*/
        return objUsuario.consultar("sqlsserver");
    }

    public boolean insertarUsuario() {
        return objUsuario.insertar("sqlsserver");
    }

    public boolean insertarUsuario(Usuario user) {
        return objUsuario.insertar(user,"sqlsserver");
    }

    public boolean modificarUsuario(Usuario user) {
        return objUsuario.modificar(user,"sqlsserver");
    }

    public boolean eliminarUsuario(int id) {
        return objUsuario.eliminar(id,"sqlsserver");
    }

}
