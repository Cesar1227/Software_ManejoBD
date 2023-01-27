/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.Estudiante;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class DataBaseORA {

    Connection con;
    ConexionORA objCone;

    public DataBaseORA() {
        //objCone = new ConexionORA();
        System.out.println("OBJETO CONEXIÓN ORACLE: "+objCone.getIntance());
        con = objCone.getIntance();
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConected() {
        return con != null;
    }

    public void cerrarConexion() {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Realizar commit a transación en proceso
     *
     * @return Estado de la transación
     */
    public boolean realizarCommit() {
        
        try {
            con.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }

    /**
     * Realizar roolback a transación en proceso
     *
     * @return Estado de la transación
     */
    public boolean realizarRollBack() {

        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Ha ocurrido un problema al realizar el RollBack");
            return false;
        }

        return true;
    }

    //Métodos para tabla usuarios
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
        } finally {
            /*try {
                //con.close();
                //rslt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
        return usuarios;
    }

    public boolean insertarUsuario(Usuario user) {
        PreparedStatement stm;
        if (existeUsuario(user.getId())){
            System.err.println("\n:::: EL USUARIO CON ID " + user.getId() + " YA EXISTE\n");
        }else {
            try {
            stm = con.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?)");
            stm.setInt(1, user.getId());
            stm.setString(2, user.getNombre());
            stm.setInt(3, user.getEdad());
            stm.setString(4, user.getProfesion());

            return (stm.executeUpdate() > 0);

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
                //con.rollback();
            }
        }
        return false;
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

    /**
     * LLamar un procedimiento almacenado en la base de datos de Oracle
     * Procedimiento que compara si un número es mayor que otro
     *
     * @param num1 Número a comparar
     * @param num2 Número a comparar
     * @return String que contiene la respuesta del procedimiento
     *
     *
     *
     * create or replace procedure comparar_numeros(num1 number,num2 number,res
     * out varchar2) is begin if num1>num2 then dbms_output.put_line('El número
     * '||num1||' es mayor que '||num2); res:=num1; elsif num2>num1 then
     * dbms_output.put_line('El número '||num2||' es mayor que '||num1);
     * res:=num2; else dbms_output.put_line('Los números son iguales');
     * res:='iguales'; end if; end;
     *
     */
    public String llamarProcedimiento1(int num1, int num2) {
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{call comparar_numeros(?,?,?)}");

            cstmt.setInt(1, num1);
            cstmt.setInt(2, num2);
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            res = cstmt.getString(3);
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public String llamarProcedimiento2(){
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{call info_usuarios(?)}");

            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            res = cstmt.getString(1);
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;      
    }

    /**
     * Retorna la cantidad de operaciones DML realizadas (INSERT, UPDATE,
     * DELETE)
     *
     * @param oper
     * @return
     */
    /*
    create or replace function cant_DML(operaciones auditorias.operacion%type) return NUMBER is
            cantidad number;
        begin
            select count(a.operacion) into cantidad
            from auditorias a
            where a.operacion=operaciones;

            return cantidad;
        end cant_DML;
        /
     */
    public int llamarFuncion1(String oper) {
        int res = 0;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call cant_DML(?)}");

            cstmt.registerOutParameter(1, oracle.jdbc.OracleType.NUMBER);
            cstmt.setString(2, oper);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            res = cstmt.getInt(1);
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     * Retorna el nombre del estudiante a partir del código
     *
     * @param codigo
     * @return
     */
    /*
    create or replace FUNCTION nom_estudiante(micodigo number) return varchar2 is
            v_nombre varchar2(100);
        BEGIN
            SELECT e.nombres ||' '|| e.apellido1||' '||e.apellido2 into v_nombre
            from estudiante e
            where e.codigo=micodigo;

            return v_nombre;
        end nom_estudiante;
     */
    public String llamarFuncion2(int codigo) {
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call nom_estudiante(?)}");

            cstmt.registerOutParameter(1, oracle.jdbc.OracleType.VARCHAR2);
            cstmt.setInt(2, codigo);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            res = cstmt.getString(1);
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

}
