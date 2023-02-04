/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.LOGICA;

import Modelo.DAO.UsuarioDAO;
import Modelo.DTO.UsuarioDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
        return objUserDAO.insertarUsuario(user);
    }

    public boolean modificarUsuario(UsuarioDTO user) {
        return objUserDAO.insertarUsuario(user);
    }

    public boolean eliminarUsuario(UsuarioDTO user) {
        return objUserDAO.eliminarUsuario(user);
    }

    public UsuarioDTO buscarUsuario(UsuarioDTO user) {
        return objUserDAO.buscarUsuario(user);
    }

    
    /*
        copiar un archivo del sistema en otra carpeta
     */
    private void guardarImagen(String ruta, String id) {

        String destino = "C:/softSPORT/Image/";
        String archivo = destino + "" + id + ".png";

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
            JOptionPane.showMessageDialog(null, "Imagen Guardada");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar datos");
            System.err.println(e.toString());
        }

    }
}
