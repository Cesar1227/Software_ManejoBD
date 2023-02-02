/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.LOGICA;

import Modelo.DAO.UsuarioDAO;
import Modelo.DTO.UsuarioDTO;
import java.util.List;

/**
 *
 * @author Cesar Bonilla
 */
public class Usuario {

    UsuarioDAO objUserDAO;

    public Usuario(String db) {
        objUserDAO = new UsuarioDAO(db);
    }

    public List<UsuarioDTO> consultadorUsuarios() {
        return objUserDAO.consultarDatos();
    }

    public boolean insertarUsuario(UsuarioDTO user) {
        return objUserDAO.insertarUsuario(user);
    }

    public boolean modificarUsuario(UsuarioDTO user) {
        return objUserDAO.insertarUsuario(user);
    }

    public boolean eliminarUsuario(UsuarioDTO user) {
        return objUserDAO.eliminarUsuario(user);
    }
    
    public UsuarioDTO buscarUsuario(UsuarioDTO user){
        return objUserDAO.buscarUsuario(user);
    }

}
