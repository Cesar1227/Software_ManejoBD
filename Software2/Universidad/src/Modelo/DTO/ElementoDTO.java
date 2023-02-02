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
public class ElementoDTO {
    private float codigo;
    private String elemento;
    private String devolutivo;
    private String talla;
    private String uso;
    private String materiales;
    private String mantenimiento;
    private String usos;
    private String norma;
    private String atenuacion;
    private String serial;
    private String tallas;
    private float unidad;
    private String ruta;

    public ElementoDTO(float codigo, String elemento) {
        this.codigo = codigo;
        this.elemento = elemento;
    }

    public ElementoDTO(float codigo, String elemento, String devolutivo, String talla) {
        this.codigo = codigo;
        this.elemento = elemento;
        this.devolutivo = devolutivo;
        this.talla = talla;
    }

    public ElementoDTO(float codigo, String elemento, String devolutivo, String talla, String uso, String materiales, String mantenimiento, String usos, String norma, String atenuacion, String serial, String tallas, float unidad, String ruta) {
        this.codigo = codigo;
        this.elemento = elemento;
        this.devolutivo = devolutivo;
        this.talla = talla;
        this.uso = uso;
        this.materiales = materiales;
        this.mantenimiento = mantenimiento;
        this.usos = usos;
        this.norma = norma;
        this.atenuacion = atenuacion;
        this.serial = serial;
        this.tallas = tallas;
        this.unidad = unidad;
        this.ruta = ruta;
    }

    public float getCodigo() {
        return codigo;
    }

    public void setCodigo(float codigo) {
        this.codigo = codigo;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getDevolutivo() {
        return devolutivo;
    }

    public void setDevolutivo(String devolutivo) {
        this.devolutivo = devolutivo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public String getUsos() {
        return usos;
    }

    public void setUsos(String usos) {
        this.usos = usos;
    }

    public String getNorma() {
        return norma;
    }

    public void setNorma(String norma) {
        this.norma = norma;
    }

    public String getAtenuacion() {
        return atenuacion;
    }

    public void setAtenuacion(String atenuacion) {
        this.atenuacion = atenuacion;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTallas() {
        return tallas;
    }

    public void setTallas(String tallas) {
        this.tallas = tallas;
    }

    public float getUnidad() {
        return unidad;
    }

    public void setUnidad(float unidad) {
        this.unidad = unidad;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
}
