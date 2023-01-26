/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.Usuario;
import java.sql.CallableStatement;
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
public class DataBaseSQLS {

    Connection con;
    ConexionSQLS objCone;
    
    public DataBaseSQLS() {
        objCone = new ConexionSQLS();
        con = objCone.conexion();
    }

    public boolean isConected() {
        return con != null;
    }

    /**
     * 
     * @param num1
     * @param num2
     * @return 
     * 
     */
    
    //PROCEDIMIENTO EN BASE DE DATOS
    /*   
    USE [EMPRESA]
    GO
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
    ALTER   PROCEDURE [dbo].[Comparar_numeros]
            @num1 int,
            @num2 int,
            @res int output
    AS
        begin
            if @num1>@num2
                            begin
                                    print(concat('El : ',@num1,' es mayor que: ',@num2));
                                    set @res=@num1;
                            end
            else if @num1<@num2
                            begin
                                    print(concat('El : ',@num2,' es mayor que: ',@num1));
                                    set @res=@num2;
                            end
            else 
                            begin
                                    print(concat('El : ',@num2,' es igual que: ',@num1));
                                    set @res=-1;
                            end
        end
    */
    public String llamarProcedimiento1(int num1, int num2) {
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{call comparar_numeros(?,?,?)}");

            cstmt.setInt(1, num1);
            cstmt.setInt(2, num2);
            cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            int rs = cstmt.getInt(3);

            res = (rs > 0) ? String.valueOf(rs) : "iguales";

            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     * 
     * @param codigo
     * @return 
     */
    
    /*
    USE [EMPRESA]
    GO

    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
    ALTER   FUNCTION [dbo].[promedio_est](@estcodigo int)
    RETURNS float
    AS
    BEGIN
            declare @promedio float;
        select @promedio=avg(i.nota)
        from inscripciones i 
        where i.estudiante=@estcodigo and i.nota>=3;

        return @promedio;

    END
    */
    public float llamarFuncion1(int codigo) {
        float res = 0f;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call promedio_est(?)}");

            cstmt.registerOutParameter(1, oracle.jdbc.OracleType.NUMBER);
            cstmt.setInt(2, codigo);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            res = cstmt.getFloat(1);
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    //Métodos para tabla usuarios
    
    //Consultar 
    public List<Usuario> consultarDatos() {
        //Connection con = conexion();
        ResultSet rslt = null;
        List<Usuario> usuarios = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM USUARIO");
            rslt = stmt.executeQuery();

            usuarios = new ArrayList<>();
            Usuario user;
            //System.out.println(usuarios.size()+" size");
            while (rslt.next()) {
                user = new Usuario();
                user.setId(rslt.getInt(1));
                user.setNombre(rslt.getString(2));
                user.setEdad(rslt.getInt(3));
                user.setProfesion(rslt.getString(4));
                usuarios.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    public boolean modificarUsuario(Usuario user) {
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
                Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("\n:::: EL USUARIO CON ID " + user.getId() + " NO EXISTE\n");
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
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    public boolean eliminarUsuario(int id) {
        PreparedStatement stm;
        if (existeUsuario(id)) {
            String sql = "DELETE FROM usuario WHERE id=?";
            try {

                stm = con.prepareStatement(sql);
                stm.setInt(1, id);

                return stm.executeUpdate() > 0;

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("\n:::: EL USUARIO CON ID " + id + " NO EXISTE\n");
        }
        return false;
    }
    public boolean insertarUsuario(Usuario user) {
        PreparedStatement stm;
        try {
            stm = con.prepareStatement("INSERT INTO usuario VALUES (AUTO_INC.NEXTVAL,?,?,?)");
            stm.setString(1, user.getNombre());
            stm.setInt(2, user.getEdad());
            stm.setString(3, user.getProfesion());

            return (stm.executeUpdate() > 0);

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
            //con.rollback();
        }
        return false;
    }
    
}
