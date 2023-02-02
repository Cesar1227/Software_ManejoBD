/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class DataBaseSQLS {

    Connection con;
    ConexionSQLS objConeSQLS;
    
    public DataBaseSQLS() {
        //objCone = new ConexionSQLS();
        System.out.println("OBJETO CONEXIÓN SQL SERVER: "+objConeSQLS.getIntance());
        con = objConeSQLS.getIntance();
        //con.setAutoCommit(false);
        //this.trasaccionesImplicitas();
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
    public String compararDosNumeros(int num1, int num2) {
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

    public void trasaccionesImplicitas() {
        PreparedStatement stm;
        try {
            stm = con.prepareStatement("SET IMPLICIT_TRANSACTIONS ON");
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseSQLS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void aplicarTrasaccion(){
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseSQLS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void descartarTransaccion(){
        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseSQLS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
