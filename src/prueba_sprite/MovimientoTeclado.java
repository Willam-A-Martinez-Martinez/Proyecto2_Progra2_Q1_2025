/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import GUI.PgInicial;
import Users.ManejoMovement;
import Users.Movement;
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
    private static PgInicial pgInicial;
    private static MovimientoTeclado instancia;
    private final String baseDirectory = "Usuarios/";
    public boolean ARRIBA, ABAJO, IZQUIERDA, DERECHA;
    private String usuario;
    public int cant_move = 0;
    int nuevosMovimientos;
    
    private static ManejoMovement mMovement;
    private static Movement move;
          
    public static void inicializarPgInicial(PgInicial pg) {
        pgInicial = pg;
    }

// Then modify obtenerInstancia to check if pgInicial is initialized
    public static MovimientoTeclado obtenerInstancia(PgInicial pg) {
        if (pg != null) {
            pgInicial = pg;
        }

        if (instancia == null) {
            if (pgInicial == null) {
                throw new IllegalStateException("pgInicial no ha sido inicializado.");
            }
            instancia = new MovimientoTeclado();
            mMovement = new ManejoMovement();
            move = new Movement();
        }
        return instancia;
    }
 
    public void guardarMovimientos() {
        
        
        int movimientosPrevios = mMovement.loadMovements(usuario);
        System.out.println(usuario);
        nuevosMovimientos=move.getTotalMovements();
        System.out.println(movimientosPrevios);
        System.out.println(nuevosMovimientos);
        System.out.println("Movimientos con variable cant_move:"+cant_move);
        int totalMovimientos = movimientosPrevios + nuevosMovimientos;
        move.setTotalMovements(totalMovimientos);
        
        mMovement.saveMovements(usuario, move);
        System.out.println("Movimientos previos: " + movimientosPrevios);
        System.out.println("Nuevos movimientos: " + nuevosMovimientos);
        System.out.println("Total movimientos guardados: " + totalMovimientos);

        if (usuario == null) {
            System.out.println("ERROR: Usuario no definido. No se puede guardar movimientos.");
            return;
        }

        File userDir = new File(baseDirectory + usuario);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        File archivo = new File(userDir, "movimientos.txt");

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo, false)))) {
            pw.println(totalMovimientos);
            System.out.println("Movimientos guardados correctamente: " + totalMovimientos);

            // Solo reiniciar movimientos si la escritura fue exitosa
//            setMovimiento();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudieron guardar los movimientos.");
        }
    }

    public int obtenerMovimientos() {
        if (usuario == null) {
            System.out.println("ERROR: No se puede obtener movimientos porque el usuario es null.");
            return 0;
        }

        File archivo = new File(baseDirectory + usuario + "/movimientos.txt");

        if (!archivo.exists()) {
            return 0; // Si no hay archivo, significa que no hay movimientos guardados
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            if (linea == null || linea.trim().isEmpty()) {
                return 0; // Si el archivo está vacío, devolvemos 0
            }
            return Integer.parseInt(linea.trim());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudo leer el archivo de movimientos.");
            return 0;
        }
    }

    public void inicializarMovimientos() {
        if (usuario == null) {
            System.out.println("ERROR: No se puede inicializar movimientos porque el usuario es null.");
            return;
        }

        File userDir = new File(baseDirectory + usuario);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        File archivo = new File(userDir, "movimientos.txt");

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

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        System.out.println("Usuario establecido: " + usuario);
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W && !ARRIBA) {
            ARRIBA = true;
            cant_move++;
            move.increaseMovement();
        }
        if (code == KeyEvent.VK_A && !IZQUIERDA) {
            IZQUIERDA = true;
            cant_move++;
            move.increaseMovement();
        }
        if (code == KeyEvent.VK_S && !ABAJO) {
            ABAJO = true;
            cant_move++;
            move.increaseMovement();
        }
        if (code == KeyEvent.VK_D && !DERECHA) {
            DERECHA = true;
            cant_move++;
            move.increaseMovement();
        }
        nuevosMovimientos=cant_move;
        System.out.println("Movimientos de nuevos movimientos: "+nuevosMovimientos);
        System.out.println("Movimientos actuales: " + cant_move);

        if (code == KeyEvent.VK_M) {
            new Menu_pausa().setVisible(true);
        }
    }
    
    public int getMovimiento() {
        System.out.println("Cantidad de movimentos en getMovimiento: "+cant_move);
        return cant_move;
    }

    public void setMovimiento() {
        System.out.println("Reiniciando movimientos a 0");
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
