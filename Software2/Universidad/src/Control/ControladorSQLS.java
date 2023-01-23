/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.DataBaseSQLS;
import java.util.Scanner;

/**
 *
 * @author Cesar Bonilla
 */
public class ControladorSQLS {

    DataBaseSQLS objDbSQLS;
    Scanner sc;

    public ControladorSQLS() {
        objDbSQLS = new DataBaseSQLS();
        if (!objDbSQLS.isConected()) {
            System.err.println("HA OCURRIDO UN ERROR, NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS DE SQLSERVER");
        }
    }

    void procedimientosSSER() {
        int num1, num2;
        String respuesta;
        sc = new Scanner(System.in);
        System.out.print("Ingrese el primer número a comparar: ");
        num1 = sc.nextInt();
        System.out.print("Ingrese el segundo número a comparar: ");
        num2 = sc.nextInt();
        respuesta = objDbSQLS.llamarProcedimiento1(num1, num2);
        if (respuesta.equals("iguales")) {
            System.out.println("Los números son iguales");
        } else {
            System.out.println("El número " + respuesta + " es el mayor");
        }
    }

    void funcionesSSER() {
        String respuesta;
        int codigo;
        sc = new Scanner(System.in);
        System.out.print("Ingrese el código del estudiante: ");
        codigo = sc.nextInt();
        respuesta = Float.toString(objDbSQLS.llamarFuncion1(codigo));
        System.out.println("El promedio del estudiante con código " + codigo + " es " + respuesta);
    }

    public void opcionesSQLSERVER() {
        int entrada;
        do {
            System.out.println("\nDIGITE EL NÚMERO DE LA OPERACIÓN QUE DESEA EJECUTAR:\n"
                    + "1. LLAMAR PROCEDIMIENTO 1\n"
                    + "2. LLAMAR FUNCIÓN 1\n"
                    + "0. VOLVER\n");

            System.out.print(">>: ");
            entrada = sc.nextInt();
            System.out.println("");
            switch (entrada) {
                case 1:
                    procedimientosSSER();
                    break;
                case 2:
                    funcionesSSER();
                    break;
                case 0:
                    entrada = 0;
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }
        } while (entrada != 0);
    }

}
