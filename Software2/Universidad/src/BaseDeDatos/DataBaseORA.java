/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelo.DTO.EstudianteDTO;
import Modelo.DTO.UsuarioDTO;
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

 

}
