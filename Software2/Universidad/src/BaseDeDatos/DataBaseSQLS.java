/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.Connection;

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
  
}
