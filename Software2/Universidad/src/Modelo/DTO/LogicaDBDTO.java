/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DTO;

/**
 *
 * @author Cesar Bonilla
 */
public class LogicaDBDTO {
    
    private int num1;
    private int num2;
    private int respInt;
    private String operacion;
    private String respString;

    public LogicaDBDTO() {
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getRespInt() {
        return respInt;
    }

    public void setRespInt(int respInt) {
        this.respInt = respInt;
    }

    public String getRespString() {
        return respString;
    }

    public void setRespString(String respString) {
        this.respString = respString;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    
    
}
