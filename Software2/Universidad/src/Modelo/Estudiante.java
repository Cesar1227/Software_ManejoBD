/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BaseDeDatos.DataBaseORA;
import BaseDeDatos.DataBaseSQLS;
import java.util.Date;

/**
 *
 * @author Cesar Bonilla
 */
public class Estudiante {
    private int codigo;
    private String nombres;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private int facultad;
    private int programa;
    private Date fecha_inicio;
    private DataBaseORA objDbORA;
    private DataBaseSQLS objDbSQLS;

    public Estudiante() {
        objDbORA = new DataBaseORA();
        objDbSQLS = new DataBaseSQLS();
    }

    public Estudiante(int codigo, String nombres, String apellido1, String apellido2, String telefono, int facultad, int programa, Date fecha_inicio) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.facultad = facultad;
        this.programa = programa;
        this.fecha_inicio = fecha_inicio;
        objDbORA = new DataBaseORA();
        objDbSQLS = new DataBaseSQLS();
    }
    
    public String obtenerInformacion(){
        return objDbORA.llamarProcedimiento2();
    }
    
    public String obtenerNombreEstudiante(int codigo){
        return String.valueOf(objDbORA.llamarFuncion2(codigo));
    }
    
    //FUNCIÓN SQLSERVER
    public float obtenerPromedio(int codigo, String motor){ 
        if(motor.equals("SQLS")){
            return objDbSQLS.llamarFuncion1(codigo);   
        }else if(motor.equals("ORA")){
            return 0.0f;
        }else{
            System.err.println("Motor de base de datos no reconocido.");
            return 0.0f;
        }        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getFacultad() {
        return facultad;
    }

    public void setFacultad(int facultad) {
        this.facultad = facultad;
    }

    public int getPrograma() {
        return programa;
    }

    public void setPrograma(int programa) {
        this.programa = programa;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", telefono=" + telefono + ", facultad=" + facultad + ", programa=" + programa + ", fecha_inicio=" + fecha_inicio + ", objDbORA=" + objDbORA + '}';
    }
    
}
