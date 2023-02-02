/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DTO;

import java.util.ArrayList;

/**
 *
 * @author Cesar Bonilla
 */
public class UsuarioDTO {

    private int id;
    private String nombre;
    private int edad;
    private String profesion;
    //private Random r;

    public UsuarioDTO() {

    }

    public UsuarioDTO(int id, String nombre, int edad, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;

    }

    /*void llenarAleatorio() {

        String[] nombres = {"DANIEL", "FELIPE", "DAYANA", "MARCELA", "JULIAN", "SANDRA", "MARLON", "JHOANA", "ADRIAN", "MARTA"};
        String[] apellidos = {"DUARTE", "OLAYA", "VALVUENA", "ZAPATA", "CETINA", "ROMERO", "FLOREZ", "CUARTAS", "LOPEZ", "SALGADO"};
        String[] profesiones = {"ESTUDIANTE", "DOCENTE", "FUNCIONARIO", "ADMINISTRATIVO"};
        r = new Random();
        this.setNombre(nombres[r.nextInt(nombres.length)] + " " + apellidos[r.nextInt(apellidos.length)]);
        this.setProfesion(profesiones[r.nextInt(profesiones.length)]);
        this.setEdad(r.nextInt(20) + 20);

    }*/

 /*
    public List<UsuarioDTO> consultar(String aux) {
        if("oracle".equals(aux)){
            List<UsuarioDTO> usuarios = objDbORA.consultarDatos();
            return usuarios;
        }else{
            List<UsuarioDTO> usuarios = objDbSQLS.consultarDatos();
            return usuarios;
        }
        
    }*/

 /*public boolean insertar(int id,String aux) {
        UsuarioDTO objUser;
        objUser = new UsuarioDTO();
        objUser.llenarAleatorio();
        if("oracle".equals(aux)){
            return objDbORA.insertarUsuario(objUser);
        }else{
            return objDbSQLS.insertarUsuario(objUser);
        }
    }*/
 /*
    public boolean insertar(UsuarioDTO user,String aux) {
        if (user != null) {
            if("oracle".equals(aux)){
                return objDbORA.insertarUsuario(user);
            }else{
                return objDbSQLS.insertarUsuario(user);
            }
        } else {
            System.err.println("El objeto usuario no puede ser NULL");
            return false;
        }
    }
    
    public boolean modificar(UsuarioDTO user,String aux){
        if("oracle".equals(aux)){
            return objDbORA.modificarUsuario(user);
        }else{
            return objDbSQLS.modificarUsuario(user);
        }
        
    }
    
    public boolean eliminar(int id,String aux){
        if("oracle".equals(aux)){
            return objDbORA.eliminarUsuario(id);
        }else{
            return objDbSQLS.eliminarUsuario(id);
        }
    }
     */
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

    public Object[] toVector() {
        Object[] vector = new Object[4];
        vector[0] = this.getId();
        vector[1] = this.getNombre();
        vector[2] = this.getEdad();
        vector[3] = this.getProfesion();

        return vector;
    }
}
