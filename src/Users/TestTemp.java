package Users;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class TestTemp {
    private static String rutaImagen = "src/UI_Images/default profile.png"; // Ruta por defecto

    public static void main(String[] args) {
        JFrame frame = new JFrame("Seleccionar Imagen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new FlowLayout());

        JButton btnAbrir = new JButton("Abrir Explorador");
        JLabel lblRuta = new JLabel("Ruta: " + rutaImagen);

        btnAbrir.addActionListener((ActionEvent e) -> {
            String nuevaRuta = abrirExplorador();
            if (nuevaRuta != null) {
                rutaImagen = nuevaRuta; // Actualiza la ruta si se selecciona una imagen válida
                lblRuta.setText("Ruta: " + rutaImagen);
            }
        });

        frame.add(btnAbrir);
        frame.add(lblRuta);
        frame.setVisible(true);
    }

    public static String abrirExplorador() {
        JFileChooser fileChooser = new JFileChooser();

        // Filtro para solo mostrar archivos PNG y JPG
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes PNG y JPG", "png", "jpg");
        fileChooser.setFileFilter(filtro);
        
        // Evita que el usuario pueda seleccionar "Todos los archivos"
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        // Solo permite seleccionar archivos, no carpetas
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            
            // Verifica manualmente si el archivo tiene la extensión correcta
            String nombreArchivo = archivoSeleccionado.getName().toLowerCase();
            if (nombreArchivo.endsWith(".png") || nombreArchivo.endsWith(".jpg")) {
                return archivoSeleccionado.getAbsolutePath(); // Devuelve la nueva ruta
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten archivos .png y .jpg", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null; // Retorna null si no se seleccionó una imagen válida
    }
}