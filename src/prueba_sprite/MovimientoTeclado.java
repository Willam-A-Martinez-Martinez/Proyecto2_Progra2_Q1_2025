/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import GUI.PgInicial;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import tile.Menu_pausa;

/**
 *
 * @author 50488
 */
public class MovimientoTeclado implements KeyListener {
    PgInicial pgInicial;
    Menu_pausa pausa =  new Menu_pausa(pgInicial);
    private final String baseDirectory = "usuarios/";
    public boolean ARRIBA, ABAJO, IZQUIERDA, DERECHA;
    int cant_move=0;

    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W && !ARRIBA) {
            ARRIBA = true;
            cant_move++;
            System.out.println("Movimientos: " + cant_move);
        }
        if (code == KeyEvent.VK_A && !IZQUIERDA) {
            IZQUIERDA = true;
            cant_move++;
            System.out.println("Movimientos: " + cant_move);
        }
        if (code == KeyEvent.VK_S && !ABAJO) {
            ABAJO = true;
            cant_move++;
            System.out.println("Movimientos: " + cant_move);
        }
        if (code == KeyEvent.VK_D && !DERECHA) {
            DERECHA = true;
            cant_move++;
            System.out.println("Movimientos: " + cant_move);
        }
        if (code == KeyEvent.VK_M) {
            new Menu_pausa(pgInicial).setVisible(true);
        }
    }
    public int getMovimiento(){
        return cant_move;
    }
    public void setMovimiento(){
        cant_move = 0;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            ARRIBA = false;
        }
        if (code == KeyEvent.VK_A) {
            IZQUIERDA = false;
        }
        if (code == KeyEvent.VK_S) {
            ABAJO = false;
        }
        if (code == KeyEvent.VK_D) {
            DERECHA = false;
        }

    }
    
    public void guardarMovimientos(String usuario, int nuevosMovimientos) {
        int movimientosPrevios = obtenerMovimientos(usuario); // Obtener movimientos previos
         System.out.println(movimientosPrevios);
        int totalMovimientos = movimientosPrevios + nuevosMovimientos; // Sumar con los nuevos movimientos

         System.out.println(totalMovimientos);
        // Crea la carpeta del usuario si no existe
        File userDir = new File(baseDirectory + usuario);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        // Guardar el total de movimientos en movimientos.txt
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(userDir.getPath() + "/movimientos.txt")))) {
            pw.println(totalMovimientos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int obtenerMovimientos(String usuario) {
        File archivo = new File(baseDirectory + usuario + "/movimientos.txt");

        if (!archivo.exists()) {
            return 0; // Si no existe el archivo, devuelve 0 movimientos previos
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            return (linea != null) ? Integer.parseInt(linea) : 0;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public void inicializarMovimientos(String usuario) {
        File userDir = new File(baseDirectory + usuario);
        if (!userDir.exists()) {
            userDir.mkdirs(); // Crear la carpeta si no existe
        }

        File archivo = new File(userDir, "movimientos.txt");

        // Escribir "0" en movimientos.txt
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
            pw.println(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrar(Component component) {  
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(component);
        if (frame != null) {
            frame.dispose();
        }
    }

}
/*
   int opcion = JOptionPane.showConfirmDialog(null, 
                    "¿Deseas volver al menú?", "Volver al menú",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
               
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
                if (frame != null) {
                    frame.dispose();
                }

                
                menu.getInstance().setVisible(true);
            }
 */
