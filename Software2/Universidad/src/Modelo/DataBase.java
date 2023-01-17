/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class DataBase {

    Connection con;

    public DataBase() {
        con = conexion();
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    /**
     * Realizar commit a transación en proceso
     *
     * @return Estado de la transación
     */
    public String realizarCommit() {
        try {
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return "TRANSACIÓN NO REALIZADA";
        }
        return "TRANSACIÓN REALIZADA";
    }

    /**
     * Realizar roolback a transación en proceso
     *
     * @return Estado de la transación
     */
    public String realizarRollBack() {

        try {
            con.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocurrio un problema al realizar el RollBack";
        }

        return "ROLLBACK REALIZADO";
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
     * create or replace procedure comparar_numeros(num1 number,num2 number,res out varchar2) is
        begin
            if num1>num2 then
                dbms_output.put_line('El número '||num1||' es mayor que '||num2);
                res:=num1;
            elsif num2>num1 then
                dbms_output.put_line('El número '||num2||' es mayor que '||num1);
                res:=num2;
            else
                dbms_output.put_line('Los números son iguales');
                res:='iguales';
            end if;
        end;
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
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     * LLamar un procedimiento almacenado en la base de datos de Oracle
     * Procedimiento que calcula las raices reales de una ecuación cuadratica
     *
     * @param coefA coeficiente A de la función cuadratica
     * @param coefB coeficiente B de la función cuadratica
     * @param coefC coeficiente C de la función cuadratica
     * @return String que contiene la respuesta del procedimiento
     * 
     */
    
    /*
    create or replace PROCEDURE ecuacionGrado2 (coeA number, coeB number, coeC number, res out varchar2) is 
            inRaiz float:=0;
            res1 float:=0;
            res2 float:=0;
        begin
            inRaiz:=power(coeB,2) - 4*coeA*coeC;
            if coeA=0 then
                dbms_output.put_line('COEFICIENTE A NO PUEDE SER 0');
                res:='COEFICIENTE A NO PUEDE SER 0';
            elsif inRaiz < 0 then
                dbms_output.put_line('LAS RAICES SON IMAGINARIAS');
                res:='LAS RAICES SON IMAGINARIAS';
            else
                res1:=((-coeB+sqrt(inRaiz))/(2*coeA));
                res2:=((-coeB-sqrt(inRaiz))/(2*coeA));
                dbms_output.put_line('LA SOLUCIÓN 1 ES: '|| res1);
                dbms_output.put_line('LA SOLUCIÓN 2 ES: '|| res2);
                res:='LA SOLUCIÓN 1 ES: '|| res1 || ' LA SOLUCIÓN 2 ES: '|| res2;
            end if;
        end ecuacionGrado2;
    */
    public String llamarProcedimiento2(int coefA, int coefB, int coefC) {
        String res = null;
        try {
            CallableStatement cstmt = con.prepareCall("{call ecuacionGrado2(?,?,?,?)}");

            cstmt.setInt(1, coefA);
            cstmt.setInt(2, coefB);
            cstmt.setInt(3, coefC);
            cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            res = cstmt.getString(4);
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     * Retorna la cantidad de operaciones DML realizadas (INSERT, UPDATE, DELETE)
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
    public int llamarFuncion1() {
        int res = 0;
        try {
            CallableStatement cstmt = con.prepareCall("{? = call cant_DML(?)}");

            cstmt.registerOutParameter(1, oracle.jdbc.OracleType.NUMBER);
            cstmt.setString(2, "INSERT");
            cstmt.execute();
            //aca retorna el valor del procedimiento almacenado.

            res = cstmt.getInt(1);
            //System.out.println(res);
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public List<Usuario> consultarDatos() {
        //Connection con = conexion();
        ResultSet rslt = null;
        List<Usuario> usuarios = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT IDENTIFICACION,NOMBRE_1,APELLIDO_2 FROM EMPLEADOS");
            rslt = stmt.executeQuery();

            usuarios = new ArrayList<>();
            Usuario user;
            while (rslt.next()) {
                user = new Usuario();
                user.setId(rslt.getInt(1));
                user.setNombre(rslt.getString(2));
                user.setEdad(rslt.getInt(3));
                user.setProfesion(rslt.getString(4));
                usuarios.add(user);
            }
            //System.out.println(usuarios.size()+" size");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
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

    public void insertar(Usuario user) {
        PreparedStatement stm;
        try {
            stm = con.prepareStatement("INSERT INTO usuario VALUES (AUTO_INC.NEXTVAL,?,?,?)");
            stm.setString(1, user.getNombre());
            stm.setInt(2, user.getEdad());
            stm.setString(3, user.getProfesion());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            //con.rollback();
        }

    }

    public boolean modificar(Usuario user) {
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
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("\n:::: EL USUARIO CON ID " + user.getId() + " NO EXISTE\n");
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
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean eliminar(int id) {
        PreparedStatement stm;
        if (existeUsuario(id)) {
            String sql = "DELETE FROM usuario WHERE id=?";
            try {

                stm = con.prepareStatement(sql);
                stm.setInt(1, id);

                return stm.executeUpdate() > 0;

            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("\n:::: EL USUARIO CON ID " + id + " NO EXISTE\n");
        }
        return false;
    }

}
