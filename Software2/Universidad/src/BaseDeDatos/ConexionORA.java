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

/**
 *
 * @author Cesar Bonilla
 */
public class ConexionORA {

    /**
     * Conexión al motor de base de datos de Oracle
     *
     * @return Objeto de la conexión
     */
    public Connection conexion() {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";  //jdbc:oracle:thin:[ip/localhost/]:[puerto]:xe
        String user = "cesar";
        String pass = "sistemas";
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBaseORA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}