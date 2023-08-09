/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.DTO.EstudianteDTO;
import Modelo.DTO.LogicaDBDTO;
import Modelo.DTO.UsuarioDTO;
import Modelo.LOGICA.Estudiante;
import Modelo.LOGICA.LogicaDB;
import Modelo.LOGICA.Usuario;

import java.util.List;
import java.util.Scanner;

public class ControladorSQLS {

    Usuario objUsuario;
    Estudiante objEstu;
    LogicaDB objLogicDB;
    Scanner sc;

    public ControladorSQLS() {
        objUsuario = new Usuario("SQLS");
        objEstu = new Estudiante("SQLS");
        objLogicDB = new LogicaDB("SQLS");
    }

    /*
    public void iniciarTransaccion(){
       //objDbSQLS.trasaccionesImplicitas(); 
    }*/
    public LogicaDBDTO func_compararDosNumeros(LogicaDBDTO obj) {
        obj = objLogicDB.comparar_numeros(obj);
        if (obj.getRespString().equals("iguales")) {
            return (obj);
        } else {
            return (obj);
        }
    }

    public Float func_obtenerPromedio(EstudianteDTO est) {
        return objEstu.obtenerPromedio(est);
    }

    public List<UsuarioDTO> consultarUsuarios() {
        return objUsuario.consultadorUsuarios();
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

    public UsuarioDTO buscarUsuario(UsuarioDTO user) {
        return objUsuario.buscarUsuario(user);
    }

    public void aplicarTransaccion() {
        objLogicDB.realizarCommit();
    }

    public void descartarTransaccion() {
        objLogicDB.realizarRollBack();
    }

}
