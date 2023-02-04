package Control;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Cesar Bonilla
 */
public class Imagenes {
    
    

    public static ImageIcon ConverImagen(JLabel label, String ruta) {
        ImageIcon icon = null;
        try {

            ImageIcon image = new ImageIcon(ruta);
            icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
            //label.setIcon(icon);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return icon;
    }

    public static ImageIcon ConverImagen(JLabel label, ImageIcon image) {
        ImageIcon icon = null;
        try {

            icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
            
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return icon;
    }
    
    public static ImageIcon ConverImagen(JButton boton, ImageIcon image) {
        ImageIcon icon = null;
        try {

            icon = new ImageIcon(image.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
            
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return icon;
    }
    
    }


/*
public class Imagenes extends javax.swing.JPanel {

    String ruta;

    public Imagenes(javax.swing.JPanel panel, String ruta) {

        this.setSize(panel.getWidth(), panel.getHeight());
        this.ruta = ruta;

    }

    @Override
    public void paint(Graphics g) {

        try {
            getClass().getResource(ruta);
            Dimension height = getSize();
            ImageIcon img = new ImageIcon(ruta);

            g.drawImage(img.getImage(), 0, 0, height.width, height.height, null);

            setOpaque(false);
            super.paintComponent(g);
        } catch (NullPointerException e) {
            System.out.println("exception realizada" + e.getMessage());
        }
    }

}
*/
