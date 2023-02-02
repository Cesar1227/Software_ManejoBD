/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionSQLS {
    
    private static final ConexionSQLS singleton = new ConexionSQLS();
    private static Connection con;
    
    
    private ConexionSQLS(){
        con = conexion();
    }
    
    
    private Connection conexion() {
        String bd = "EMPRESA";
        String user = "sa";
        String pass = "cesar12";
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=" + bd + ";user=" + user + ";password=" + pass + ";TrustServerCertificate=True;";

        con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            //System.out.println("Conectado...");
        } catch (Exception ex) {
            System.out.println("Error al conectar al motor de bases de datos SQLSERVER " + ex.toString());
            Logger.getLogger(DataBaseSQLS.class.getName()).log(Level.SEVERE, null, ex);
        }    

        return con;
    }
    
    public static Connection getIntance(){
        return con;
    }
    
    public static boolean realizarCommit(){
        try {
            con.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionORA.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Ha ocurrido un problema al realizar el Commit");
        }
        return false;
    }
    
    public static boolean realizarRollback(){
        try {
            con.rollback();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionORA.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Ha ocurrido un problema al realizar el RollBack");
        }
        return false;
    }
}
