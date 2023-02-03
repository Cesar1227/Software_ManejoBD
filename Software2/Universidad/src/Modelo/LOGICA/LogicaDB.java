/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.LOGICA;

import Modelo.DAO.LogicaDBDAO;
import Modelo.DTO.LogicaDBDTO;

/**
 *
 * @author Cesar Bonilla
 */
public class LogicaDB {

    LogicaDBDAO objLogic;

    public LogicaDB() {
    }
    
    
    public LogicaDBDTO comparar_numeros(LogicaDBDTO obj) {
        objLogic.comparar_numeros(obj);
        return obj;
    }

    public LogicaDBDTO cant_operaciones(LogicaDBDTO obj) {
        objLogic.cant_operaciones_DML(obj);
        return obj;
    }
    
    public boolean realizarCommit() {
        return objLogic.realizarCommit();
    }

     public boolean realizarRollBack() {
        return objLogic.realizarRollBack();
     }
}
