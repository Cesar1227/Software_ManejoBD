
import Modelo.DataBase;
import Modelo.Usuario;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cesar Bonilla
 */
public class Main {

    DataBase objDb;
    Scanner sc;

    public static void main(String[] args) {
        Main obj = new Main();
        obj.sc = new Scanner(System.in);
        obj.init();
    }

    public void init() {
        objDb = new DataBase();
        if (!objDb.isConected()) {
            System.out.println("NO FUE POSIBLE CONECTARSE A LA BASE DE DATOS");
        } else {
            operar(objDb);
        }
    }

    void consultar() {
        List<Usuario> usuarios = objDb.consultarDatos();
        usuarios.forEach(user -> {
            System.out.println(user.toString());
        });
    }

    void insertar() {
        Usuario objUser;
        objUser = new Usuario();
        objUser.llenarAleatorio();
        objDb.insertar(objUser);
        System.out.println(objUser.toString());
    }

    void modificar() {
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

        if (objDb.modificar(objUser)) {
            System.out.println("\nMODIFICADO EXITOSAMENTE");
        } else {
            System.out.println("\nERROR AL MODIFICAR");
        }
    }

    void eliminar() {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        int id;
        id = sc.nextInt();
        if (objDb.eliminar(id)) {
            System.out.println("Usuario con id " + id + " eliminado correctamente");
        } else {
            System.out.println("NO FUE POSIBLE ELIMINAR EL USUARIO");
        }
    }

    void aplicarTransacion() {
        System.out.println(objDb.realizarCommit());
    }

    void descartarTransacion() {
        System.out.println(objDb.realizarRollBack());
    }

    /**
     * LLamar procedimiento de Oracle
     *
     * @param procedimiento procedimiento a llamar
     */
    void procedimientosORA(int procedimiento) {
        if (procedimiento==(1)) {
            int num1, num2;
            String respuesta;
            sc = new Scanner(System.in);
            System.out.print("Ingrese el primer número a comparar: ");
            num1 = sc.nextInt();
            System.out.print("Ingrese el segundo número a comparar: ");
            num2 = sc.nextInt();
            respuesta = objDb.llamarProcedimiento1(num1, num2);
            System.out.println("El número " + respuesta + " es el mayor");
        } else if (procedimiento==(2)) {
            int coefA, coefB, coefC;
            String respuesta;
            sc = new Scanner(System.in);
            System.out.print("Ingrese el coeficiente A de la ecuación: ");
            coefA = sc.nextInt();
            System.out.print("Ingrese el coeficiente B de la ecuación: ");
            coefB = sc.nextInt();
            System.out.print("Ingrese el coeficiente C de la ecuación: ");
            coefC = sc.nextInt();
            respuesta = objDb.llamarProcedimiento2(coefA, coefB, coefC);
            System.out.println(respuesta);
        }
    }
    
    void funcionesORA(int func){
        String respuesta;
        if(func == 1){
            respuesta = String.valueOf(objDb.llamarFuncion1());
            System.out.println("SE HAN REALIZADO "+respuesta+" operaciones DML sobre la tabla auditorias");
        }else if(func == 2){
            int codigo;
            sc = new Scanner(System.in);
            System.out.print("Ingrese el código del estudiante: ");
            codigo = sc.nextInt();
            respuesta = objDb.llamarFuncion2(codigo);
            System.out.println("El nombre del estudiante con código "+codigo+ " es "+respuesta);
        }
        
        
    }
    
    void procedimientosSSER(){
        
    }
    
    void funcionesSSER(){
        
    }

    void salir() {
        objDb.cerrarConexion();
        System.exit(0);
    }
    
    void opcionesOracle(){
        int entrada;
        do{
            System.out.println("\nDIGITE EL NÚMERO DE LA OPERACIÓN QUE DESEA EJECUTAR:\n"
                    + "1. CONSULTAR USUARIOS\n"
                    + "2. INSERTAR USUARIO\n"
                    + "3. ACTUALIZAR USUARIO\n"
                    + "4. ELIMINAR USUARIO\n"
                    + "5. APLICAR TRANSACIÓN\n"
                    + "6. DESCARTAR TRANSACIÓN\n"
                    + "8. LLAMAR PROCEDIMIENTO 1\n"
                    + "9. LLAMAR PROCEDIMIENTO 2\n"
                    + "10. LLAMAR FUNCIÓN 1\n"
                    + "11. LLAMAR FUNCIÓN 2\n"
                    + "0. VOLVER\n");

            System.out.print(">>: ");
            entrada = sc.nextInt();
            System.out.println("");
            switch (entrada) {
                //Consultar datos
                case 1:
                    consultar();
                    break;
                //Insertar 
                case 2:
                    this.insertar();
                    break;
                //Actualizar registro
                case 3:
                    modificar();
                    break;
                //Eliminar registro
                case 4:
                    eliminar();
                    break;
                //Aplicar transación
                case 5:
                    aplicarTransacion();
                    break;
                case 6:
                    descartarTransacion();
                    break;
                case 0:
                    entrada=0;
                    break;
                case 8:
                    procedimientosORA(1);
                    break;
                case 9:
                    procedimientosORA(2);
                    break;
                case 10:
                    funcionesORA(1);
                    break;
                case 11:
                    funcionesORA(2);
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }
        }while(entrada!=0);
    }
    
    void opcionesSQLSERVER() {
        
    }
    
    public void operar(DataBase objDb) {
        int entrada;
        while (true) {

            System.out.println("\n¿A QUÉ BASE DE DATOS DESEA INGRESAR?\n"
                    + "1. ORACLE\n"
                    + "2. SQLSERVER\n"
                    + "0. SALIR\n");
            System.out.print(">>: ");
            entrada = sc.nextInt();
            System.out.println("");
            
            switch (entrada){
                case 0:
                    salir();
                    break;
                case 1:
                    opcionesOracle();
                    break;
                case 2:
                    opcionesSQLSERVER();
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }

            

        }
    }



}
