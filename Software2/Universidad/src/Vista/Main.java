/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Controlador;
import Modelo.Usuario;
import java.util.Scanner;

/**
 *
 * @author Cesar Bonilla
 */
public class Main {

    Scanner sc;
    Controlador objControl;
    
    public Main(){
        objControl = new Controlador();
    }

    void modificarUsuario() {
        Usuario objUser;
        objUser = new Usuario();

        String nombre;
        sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del usuario a modificar: ");
        objUser.setId((sc.nextInt()));
        
        System.out.print("Ingrese el NOMBRE: ");
        nombre = sc.next();
        
        System.out.print("Ingrese el APELLIDO: ");
        nombre = nombre + " " + sc.next();
        objUser.setNombre(nombre);
        
        System.out.print("Ingrese la EDAD: ");
        objUser.setEdad((sc.nextInt()));
        
        System.out.print("Ingrese la PROFESIÓN: ");
        objUser.setProfesion(sc.next());
        
        if (objControl.modificarUsuario(objUser)) {
            System.out.println("\nMODIFICADO EXITOSAMENTE");
        } else {
            System.out.println("\nERROR AL MODIFICAR");
        }
    }

}
