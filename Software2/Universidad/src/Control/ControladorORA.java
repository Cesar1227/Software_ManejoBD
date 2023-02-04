package Control;

import Modelo.DTO.EstudianteDTO;
import Modelo.DTO.LogicaDBDTO;
import Modelo.DTO.UsuarioDTO;
import Modelo.LOGICA.Estudiante;
import Modelo.LOGICA.Usuario;
import Modelo.LOGICA.LogicaDB;

import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ControladorORA {

    Usuario objUsuario;
    Estudiante objEstu;
    LogicaDB objLogicDB;
    Scanner sc;

    public ControladorORA() {
        objEstu = new Estudiante("ORACLE");
        objUsuario = new Usuario("ORACLE");
        objLogicDB = new LogicaDB();
    }

    /*
        Usuarios
    */
    
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
    
    public UsuarioDTO guardarImagen(){
        
        return null;
        
    }
    
    /*
        Transacciones
    */

    public boolean aplicarTransacionORA() {
        return objLogicDB.realizarCommit();
    }

    public boolean descartarTransacionORA() {
        return objLogicDB.realizarRollBack();
    }

    /**
     * LLamar procedimiento de Oracle información de Estudiantes
     *
     * @param obj
     * @return
     */
    public LogicaDBDTO compararDosNumeros(LogicaDBDTO obj) {
        obj = objLogicDB.comparar_numeros(obj);
        return obj;
    }

    public String informacionEstudiantes() {
        return objEstu.obtenerInformacion();
    }

    //FUNCIÓN 1
    public LogicaDBDTO func_cantOperaciones(LogicaDBDTO obj) {
        obj = (objLogicDB.cant_operaciones(obj));
        return obj;
    }

    //FUNCIÓN 2
    public EstudianteDTO func_obtenerNomEstudiante(EstudianteDTO est) {
        return objEstu.obtenerNombre(est);
        
    }

    

}
