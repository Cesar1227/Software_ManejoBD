/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.LOGICA;

import Modelo.DAO.EstudianteDAO;
import Modelo.DTO.EstudianteDTO;

/**
 *
 * @author Cesar Bonilla
 */
public class Estudiante {
    
    EstudianteDAO objEstu;

    public Estudiante(String db) {
        objEstu = new EstudianteDAO(db);
    }
     
    public float obtenerPromedio(EstudianteDTO est){       
        return objEstu.func_obtenerPromedio(est);
    }
    
    public EstudianteDTO obtenerNombre(EstudianteDTO est){
        return objEstu.func_obtenerNombre(est);
    }
    
    public String obtenerInformacion(){
        return objEstu.proc_obtenerInformacionEst();
    }
    
    
}
