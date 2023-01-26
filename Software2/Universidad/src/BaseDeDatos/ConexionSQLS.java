/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionSQLS {
    public Connection conexion() {
        String bd = "EMPRESA";
        String user = "sa";
        String pass = "root";
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=" + bd + ";user=" + user + ";password=" + pass + ";TrustServerCertificate=True;";

        Connection con = null;
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
    
    
}
