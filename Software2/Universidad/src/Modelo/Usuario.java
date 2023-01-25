/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BaseDeDatos.DataBaseORA;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Cesar Bonilla
 */
public class Usuario {

    private int id;
    private String nombre;
    private int edad;
    private String profesion;
    private Random r;

    private DataBaseORA objDbORA;

    public Usuario() {
         this.objDbORA = new DataBaseORA();
    }

    public Usuario(int id, String nombre, int edad, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;
        this.objDbORA = new DataBaseORA();
    }

    void llenarAleatorio() {

        String[] nombres = {"DANIEL", "FELIPE", "DAYANA", "MARCELA", "JULIAN", "SANDRA", "MARLON", "JHOANA", "ADRIAN", "MARTA"};
        String[] apellidos = {"DUARTE", "OLAYA", "VALVUENA", "ZAPATA", "CETINA", "ROMERO", "FLOREZ", "CUARTAS", "LOPEZ", "SALGADO"};
        String[] profesiones = {"ESTUDIANTE", "DOCENTE", "FUNCIONARIO", "ADMINISTRATIVO"};
        r = new Random();
        this.setNombre(nombres[r.nextInt(nombres.length)] + " " + apellidos[r.nextInt(apellidos.length)]);
        this.setProfesion(profesiones[r.nextInt(profesiones.length)]);
        this.setEdad(r.nextInt(20) + 20);

    }

    public List<Usuario> consultar() {
        List<Usuario> usuarios = objDbORA.consultarDatos();
        return usuarios;
    }

    public boolean insertar() {
        Usuario objUser;
        objUser = new Usuario();
        objUser.llenarAleatorio();
        return objDbORA.insertarUsuario(objUser);
    }
    
    public boolean insertar(Usuario user) {
        if (user != null) {
            return objDbORA.insertarUsuario(user);
        } else {
            System.err.println("El objeto usuario no puede ser NULL");
            return false;
        }
    }
    
    public boolean modificar(Usuario user){
        return objDbORA.modificarUsuario(user);
    }
    
    public boolean eliminar(int id){
        return objDbORA.eliminarUsuario(id);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "{" + "id= " + id + ", nombre= " + nombre + ", edad= " + edad + ", profesion= " + profesion + "}";
    }
}
