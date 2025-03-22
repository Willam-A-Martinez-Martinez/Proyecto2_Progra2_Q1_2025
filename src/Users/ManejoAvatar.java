/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author DELL
 */
public class ManejoAvatar {
    public static final String DEFAULT_AVATAR = "src/UI_Images/default profile.png";

    public String seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes PNG y JPG", "png", "jpg");
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String nombreArchivo = archivoSeleccionado.getName().toLowerCase();

            if (nombreArchivo.endsWith(".png") || nombreArchivo.endsWith(".jpg")) {
                return archivoSeleccionado.getAbsolutePath();
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten archivos .png y .jpg", "Error", JOptionPane.ERROR_MESSAGE);
                return DEFAULT_AVATAR; // Return default if invalid file
            }
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            // User canceled, don't change the avatar
            return DEFAULT_AVATAR; // This should be the current avatar path
        }

        return DEFAULT_AVATAR; // Fallback
    }

    public String seleccionarImagen(String avatar) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes PNG y JPG", "png", "jpg");
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String nombreArchivo = archivoSeleccionado.getName().toLowerCase();

            if (nombreArchivo.endsWith(".png") || nombreArchivo.endsWith(".jpg")) {
                return archivoSeleccionado.getAbsolutePath();
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten archivos .png y .jpg", "Error", JOptionPane.ERROR_MESSAGE);
                return avatar; // Return original avatar if invalid file
            }
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            // User canceled, don't change the avatar
            return avatar;
        }

        return avatar; // Fallback
    }
}
