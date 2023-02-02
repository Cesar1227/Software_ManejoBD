/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
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

    


    

 

}
