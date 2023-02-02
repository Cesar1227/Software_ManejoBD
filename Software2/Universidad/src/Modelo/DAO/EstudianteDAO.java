/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import BaseDeDatos.ConexionORA;
import BaseDeDatos.ConexionSQLS;
import BaseDeDatos.DataBaseORA;
import Modelo.DTO.EstudianteDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class EstudianteDAO {

    Connection con;

    public EstudianteDAO(Class db) {
        this.setDataBase(db);
    }
    
    

    private void setDataBase(Class db) {
        if (db.equals(ConexionORA.class)) {
            con = ConexionORA.getIntance();
            try {
                con.setAutoCommit(false);
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            con = ConexionSQLS.getIntance();
        }

    }

    /**
     *
     * @param est
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
    public float func_obtenerPromedio(EstudianteDTO est) {
        float res = 0f;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call promedio_est(?)}");

            cstmt.registerOutParameter(1, oracle.jdbc.OracleType.NUMBER);
            cstmt.setInt(2, est.getCodigo());
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

    /**
     * Retorna el nombre del estudiante a partir del código
     *
     * @param est
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
    public String func_obtenerNombre(EstudianteDTO est) {
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call nom_estudiante(?)}");

            cstmt.registerOutParameter(1, oracle.jdbc.OracleType.VARCHAR2);
            cstmt.setInt(2, est.getCodigo());
            cstmt.execute();

            res = cstmt.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    //PROCEDIMIENTO ORACLE
    public String proc_obtenerInformacionEst() {
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{call info_usuarios(?)}");

            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            cstmt.execute();

            res = cstmt.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
    
}
