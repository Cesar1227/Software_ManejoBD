/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.DAO.EstudianteDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.DTO.EstudianteDTO;
import Modelo.DTO.LogicaDBDTO;
import Modelo.DTO.UsuarioDTO;
import Modelo.LOGICA.LogicaDB;

import java.util.List;
import java.util.Scanner;

public class ControladorSQLS {

    UsuarioDAO objUsuario;
    EstudianteDAO objEstu;
    LogicaDB objLogicDB;
    Scanner sc;

    public ControladorSQLS() {
        objUsuario = new UsuarioDAO("SQLS");
        objEstu = new EstudianteDAO("SQLS");
        objLogicDB = new LogicaDB();
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
        return objEstu.func_obtenerPromedio(est);
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
