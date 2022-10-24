/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class DataBase {

    Connection con;

    public DataBase() {
        con = conexion();
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConected() {
        return con != null;
    }
    
    public void cerrarConexion(){
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection conexion() {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";  //jdbc:oracle:thin:[ip/localhost/]:[puerto]:xe
        String user = "SYSTEM";
        String pass = "cesar12";
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public String realizarCommit() {
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return "TRANSACIÓN NO REALIZADA";
        }
        return "TRANSACIÓN REALIZADA";
    }

    public String realizarRollBack() {

        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un problema al realizar el RollBack";
        }

        return "ROLLBACK REALIZADO";
    }

    public List<Usuario> consultarDatos() {
        Connection con = conexion();
        ResultSet rslt = null;
        List<Usuario> usuarios = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM USUARIO");
            rslt = stmt.executeQuery();

            usuarios = new ArrayList<>();
            Usuario user;
            while (rslt.next()) {
                user = new Usuario();
                user.setId(rslt.getInt(1));
                user.setNombre(rslt.getString(2));
                user.setEdad(rslt.getInt(3));
                user.setProfesion(rslt.getString(4));
                usuarios.add(user);
            }
            //System.out.println(usuarios.size()+" size");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                rslt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;
    }

    public void insertar(Usuario user) {
        PreparedStatement stm;
        try {
            stm = con.prepareStatement("INSERT INTO usuario VALUES (AUTO_INC.NEXTVAL,?,?,?)");
            stm.setString(1, user.getNombre());
            stm.setInt(2, user.getEdad());
            stm.setString(3, user.getProfesion());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean modificar(Usuario user) {
        PreparedStatement stm;

        if (existeUsuario(user.getId())) {
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
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("\n:::: EL USUARIO CON ID "+user.getId()+" NO EXISTE\n");
        }
        return false;
    }

    boolean existeUsuario(int id) {
        PreparedStatement stm;
        String sql = "SELECT * FROM usuario WHERE id=?";
        ResultSet rslt = null;

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rslt = stm.executeQuery();

            return rslt.next();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean eliminar(int id) {
        PreparedStatement stm;
        if (existeUsuario(id)) {
            String sql = "DELETE FROM usuario WHERE id=?";
            try {

                stm = con.prepareStatement(sql);
                stm.setInt(1, id);

                return stm.executeUpdate() > 0;

            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("\n:::: EL USUARIO CON ID "+id+" NO EXISTE\n");
        }
        return false;
    }

}
