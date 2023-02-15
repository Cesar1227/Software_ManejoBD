/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import BaseDeDatos.ConexionORA;
import BaseDeDatos.ConexionSQLS;
import Modelo.DTO.LogicaDBDTO;
import Modelo.LOGICA.LogicaDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class LogicaDBDAO {

    Connection con;

    public LogicaDBDAO(String db) {
        this.setDataBase(db);
    }

    private void setDataBase(String db) {
        if (db.equals("ORACLE")) {
            con = ConexionORA.getIntance();
            try {
                con.setAutoCommit(false);
            } catch (SQLException ex) {
                Logger.getLogger(LogicaDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            con = ConexionSQLS.getIntance();
        }
    }

    public boolean isConected() {
        return con != null;
    }

    /**
     * LLamar un procedimiento almacenado en la base de datos de Oracle
     * Procedimiento que compara si un número es mayor que otro
     *
     * @param obj
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
    public LogicaDBDTO comparar_numeros(LogicaDBDTO obj) {
        try {
            CallableStatement cstmt = con.prepareCall("{call comparar_numeros(?,?,?)}");

            cstmt.setInt(1, obj.getNum1());
            cstmt.setInt(2, obj.getNum2());
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            cstmt.execute();

            obj.setRespString(cstmt.getString(3));

        } catch (SQLException ex) {
            Logger.getLogger(LogicaDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    /**
     * Retorna la cantidad de operaciones DML realizadas (INSERT, UPDATE,
     * DELETE)
     *
     * @param obj
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
    public int cant_operaciones_DML(LogicaDBDTO obj) {
        int res = 0;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call cant_DML(?)}");

            cstmt.registerOutParameter(1, oracle.jdbc.OracleType.NUMBER);
            cstmt.setString(2, obj.getOperacion());
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            obj.setRespInt(cstmt.getInt(1));
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LogicaDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

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
 /*
    public String compararDosNumeros(LogicaDBDTO obj) {
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{call comparar_numeros(?,?,?)}");

            cstmt.setInt(1, obj.getNum1());
            cstmt.setInt(2, obj.getNum2());
            cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            int rs = cstmt.getInt(3);

            obj.setRespString((rs > 0) ? String.valueOf(rs) : "iguales");

            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }*/
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
            Logger.getLogger(LogicaDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LogicaDBDAO.class.getName()).log(Level.SEVERE, null, ex1);
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
            Logger.getLogger(LogicaDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Ha ocurrido un problema al realizar el RollBack");
            return false;
        }

        return true;
    }

}
