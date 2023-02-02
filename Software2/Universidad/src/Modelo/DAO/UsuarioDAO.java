/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import BaseDeDatos.ConexionORA;
import BaseDeDatos.ConexionSQLS;
import Modelo.DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class UsuarioDAO {

    Connection con;

    public UsuarioDAO(Class db) {
        this.setDataBase(db);

    }

    private void setDataBase(Class db) {
        if (db.equals(ConexionORA.class)) {
            con = ConexionORA.getIntance();
            try {
                con.setAutoCommit(false);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            con = ConexionSQLS.getIntance();
        }

    }

    public List<UsuarioDTO> consultarDatos() {
        ResultSet rslt = null;
        List<UsuarioDTO> usuarios = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM USUARIO");
            rslt = stmt.executeQuery();

            usuarios = new ArrayList<>();
            UsuarioDTO user;
            while (rslt.next()) {
                user = new UsuarioDTO();
                user.setId(rslt.getInt(1));
                user.setNombre(rslt.getString(2));
                user.setEdad(rslt.getInt(3));
                user.setProfesion(rslt.getString(4));
                usuarios.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return usuarios;
    }

    public boolean insertarUsuario(UsuarioDTO user) {
        PreparedStatement stm;
        if (existeUsuario(user)) {
            System.err.println("\n:::: EL USUARIO CON ID " + user.getId() + " YA EXISTE\n");
        } else {
            try {
                stm = con.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?)");
                stm.setInt(1, user.getId());
                stm.setString(2, user.getNombre());
                stm.setInt(3, user.getEdad());
                stm.setString(4, user.getProfesion());

                return (stm.executeUpdate() > 0);

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                //con.rollback();
            }
        }
        return false;
    }

    public boolean modificarUsuario(UsuarioDTO user) {
        PreparedStatement stm;

        if (existeUsuario(user)) {
            String sql = "UPDATE usuario SET nombre=?, edad=?, profesion=? "
                    + "WHERE id=?";

            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getNombre());
                stm.setInt(2, user.getEdad());
                stm.setString(3, user.getProfesion());
                stm.setInt(4, user.getId());

                return stm.executeUpdate() > 0;

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("\n:::: EL USUARIO CON ID " + user.getId() + " NO EXISTE\n");
        }
        return false;
    }

    boolean existeUsuario(UsuarioDTO user) {
        PreparedStatement stm;
        String sql = "SELECT * FROM usuario WHERE id=?";
        ResultSet rslt = null;

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, user.getId());
            rslt = stm.executeQuery();

            return rslt.next();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean eliminarUsuario(UsuarioDTO user) {
        PreparedStatement stm;
        if (existeUsuario(user)) {
            String sql = "DELETE FROM usuario WHERE id=?";
            try {

                stm = con.prepareStatement(sql);
                stm.setInt(1, user.getId());

                return stm.executeUpdate() > 0;

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("\n:::: EL USUARIO CON ID " + user.getId() + " NO EXISTE\n");
        }
        return false;
    }

    public UsuarioDTO buscarUsuario(UsuarioDTO user) {
        ResultSet rslt = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM USUARIO WHERE id=?");
            stmt.setInt(1, user.getId());
            rslt = stmt.executeQuery();

            if (rslt.next()) {
                
                user = new UsuarioDTO();
                user.setId(rslt.getInt(1));
                user.setNombre(rslt.getString(2));
                user.setEdad(rslt.getInt(3));
                user.setProfesion(rslt.getString(4));
                System.out.println(user.toString());
            }else{
                UsuarioDTO user2 = null;
                return user2;             
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return user;
    }

    
}
