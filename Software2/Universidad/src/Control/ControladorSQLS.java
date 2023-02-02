/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.DataBaseSQLS;
import Modelo.DAO.EstudianteDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.DTO.EstudianteDTO;
import Modelo.DTO.UsuarioDTO;
import java.util.List;
import java.util.Scanner;


public class ControladorSQLS {

    DataBaseSQLS objDbSQLS;
    UsuarioDAO objUsuario;
    EstudianteDAO objEstu;
    Scanner sc;

    public ControladorSQLS() {
        objDbSQLS = new DataBaseSQLS();
        objUsuario = new UsuarioDAO();
        objEstu = new EstudianteDAO();
        if (!objDbSQLS.isConected()) {
            System.err.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE SQLSERVER");
        }
    }
    
    /*
    public void iniciarTransaccion(){
       //objDbSQLS.trasaccionesImplicitas(); 
    }*/

    public String func_compararDosNumeros(int num1, int num2){
        String respuesta;
        respuesta = objDbSQLS.compararDosNumeros(num1, num2);
        if (respuesta.equals("iguales")) {
            return ("iguales");
        } else {
            return (respuesta);
        }
    }

    public Float proc_obtenerPromedio(EstudianteDTO est){
        return objEstu.proc_obtenerPromedio(est);
    }
    public List<UsuarioDTO> consultarUsuarios() {
        return objUsuario.consultarDatos();
    }

    public boolean insertarUsuario(UsuarioDTO user) {
        return objUsuario.insertarUsuario(user);
    }

    public boolean modificarUsuario(UsuarioDTO user) {
        return objUsuario.modificarUsuario(user);
    }

    public boolean eliminarUsuario(UsuarioDTO user) {
        return objUsuario.eliminarUsuario(user);
    }

    public void aplicarTransaccion() {
        objDbSQLS.aplicarTrasaccion();
    }

    public void descartarTransaccion() {
        objDbSQLS.descartarTransaccion();
    }

}
