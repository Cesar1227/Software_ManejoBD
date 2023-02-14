/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.LOGICA;

import Control.Imagenes;
import Modelo.DAO.UsuarioDAO;
import Modelo.DTO.UsuarioDTO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Cesar Bonilla
 */
public class Usuario {

    UsuarioDAO objUserDAO;

    public Usuario(String db) {
        objUserDAO = new UsuarioDAO(db);
    }

    public List<UsuarioDTO> consultadorUsuarios() {
        return objUserDAO.consultarDatos();
    }

    public boolean insertarUsuario(UsuarioDTO user) {
        boolean insert = objUserDAO.insertarUsuario(user);
        if (insert && user.getFotoIcon()!=null && user.getFoto()!=null) {
            this.guardarFotoEnLocal(user);
            return true;
        }
        return insert;
    }

    public boolean modificarUsuario(UsuarioDTO user) {
        boolean update = objUserDAO.modificarUsuario(user);
        if(update && user.getFotoIcon()!=null && user.getFoto()!=null){
            System.out.println(user.getFoto());
            System.out.println(user.getFotoIcon());
            this.guardarFotoEnLocal(user);
            return true;
        }
        return update;
    }

    public boolean eliminarUsuario(UsuarioDTO user) {
        return objUserDAO.eliminarUsuario(user);
    }

    public UsuarioDTO buscarUsuario(UsuarioDTO user) {
        return objUserDAO.buscarUsuario(user);
    }

    private void guardarFotoEnLocal(UsuarioDTO user) {
        this.guardarImagen(user.getFoto().getPath(), String.valueOf(user.getId()));
    }

    private ByteArrayOutputStream crearOutputStream(UsuarioDTO user) {
        BufferedImage bi = Imagenes.getBufferedImage(user.getFotoIcon().getImage());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String destino = new File("").getAbsolutePath() + "\\src\\Resources\\Usuario";
        String archivo = destino + user.getId() + ".png";
        try {
            ImageIO.write(bi, "jpg", new File(archivo));
            user.setFoto(new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return baos;
    }

    private File crearOutputStream(UsuarioDTO user, boolean respuestaFile) {
        BufferedImage bi = Imagenes.getBufferedImage(user.getFotoIcon().getImage());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String destino = new File("").getAbsolutePath() + "\\src\\Resources\\Usuario";
        String archivo = destino + user.getId() + ".png";
        try {
            ImageIO.write(bi, "jpg", new File(archivo));
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new File(archivo);
    }

    /*
        copiar un archivo del sistema en otra carpeta
     */
    private void guardarImagen(String ruta, String id) {

        String destino = new File("").getAbsolutePath() + "\\src\\Resources\\";
        String archivo = destino + "Usuario_"+ id + ".png";

        File folder = new File(id);

        if (!folder.exists()) {

            folder.mkdirs();
        }

        copyFile_java(ruta, archivo);

    }

    /*
        copiar archivos de un lugar a otro
     */
    private static void copyFile_java(String origen, String destino) {

        try {
            Path from = Paths.get(origen);
            Path to = Paths.get(destino);

            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES

            };

            Files.copy(from, to, options);
            JOptionPane.showMessageDialog(null, "Imagen Guardada en local");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar imagen en local");
            System.err.println(e.toString());
        }

    }
}
