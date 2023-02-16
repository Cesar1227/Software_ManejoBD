/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DTO;


import java.io.File;
import javax.swing.ImageIcon;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Bonilla
 */
public class UsuarioDTO {
    private String printText;
    private int id;
    private String nombre;
    private int edad;
    private String profesion;
    private File foto;
    private ImageIcon fotoIcon;
    private ByteArrayOutputStream baos;
    private static final String UTF8_NAME = StandardCharsets.UTF_8.name();

    public UsuarioDTO() {

    }

    public UsuarioDTO(int id, String nombre, int edad, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;
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

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public ImageIcon getFotoIcon() {
        return fotoIcon;
    }

    public void setFotoIcon(ImageIcon fotoIcon) {
        this.fotoIcon = fotoIcon;
    }

    public ByteArrayOutputStream getBaos() {
        return baos;
    }

    public void setBaos(ByteArrayOutputStream baos) {
        this.baos = baos;
    }
    
    @Override
    public String toString() {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outputStream, true, UTF8_NAME))
        {
            System.out.format("\n %10d %20s %2s %2s ",id, nombre, edad, profesion);
            printStream.println("");

            printText = outputStream.toString();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< Updated upstream
        return printText ;
        
=======
        return printText ;        
>>>>>>> Stashed changes
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
