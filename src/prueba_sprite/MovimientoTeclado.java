/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import tile.Menu_pausa;

/**
 *
 * @author 50488
 */
public class MovimientoTeclado implements KeyListener {
Menu_pausa pausa =  new Menu_pausa();
    public boolean ARRIBA, ABAJO, IZQUIERDA, DERECHA;
    int cant_move=0;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            ARRIBA = true;
            cant_move++;
        }
        if (code == KeyEvent.VK_A) {
            IZQUIERDA = true;
             cant_move++;
        }
        if (code == KeyEvent.VK_S) {
            ABAJO = true;
             cant_move++;
        }
        if (code == KeyEvent.VK_D) {
            DERECHA = true;
             cant_move++;
        }
        if (code == KeyEvent.VK_M) {
            new Menu_pausa().setVisible(true);

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
