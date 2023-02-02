package Control;

import BaseDeDatos.ConexionORA;
import BaseDeDatos.DataBaseORA;
import Modelo.DAO.EstudianteDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.DTO.EstudianteDTO;
import Modelo.DTO.UsuarioDTO;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ControladorORA {

    DataBaseORA objDbORA;
    UsuarioDAO objUsuario;
    EstudianteDAO objEstu;
    Scanner sc;

    public ControladorORA() {
        objDbORA = new DataBaseORA();
        objEstu = new EstudianteDAO();
        objUsuario = new UsuarioDAO();
        if (!objDbORA.isConected()) {
            System.err.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE ORACLE");
        }
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

    public boolean aplicarTransacionORA() {
        return ConexionORA.realizarCommit();
    }

    public boolean descartarTransacionORA() {
        return ConexionORA.realizarRollback();
    }

    /**
     * LLamar procedimiento de Oracle información de Estudiantes
     *
     * @param num1
     * @param num2
     * @return
     */
    public String compararDosNumeros(int num1, int num2) {
        String respuesta;
        respuesta = objDbORA.llamarProcedimiento1(num1, num2);
        if (respuesta.equals("iguales")) {
            return ("Los números son iguales");
        } else {
            return ("El número " + respuesta + " es el mayor");
        }
    }
    
    public String informacionEstudiantes(){
        return objEstu.proc_obtenerInformacionEst();
    }

    //FUNCIÓN 1
    public String func_cantOperaciones(String oper) {
        String respuesta = String.valueOf(objDbORA.llamarFuncion1(oper));
        return respuesta;
    }

    //FUNCIÓN 2
    public String obtenerNomEstudiante(EstudianteDTO est) {
        String respuesta = objEstu.proc_obtenerNombre(est);
        return respuesta;   
    }

    

}
