/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import Users.Estadisticas;
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

   
    private static MovimientoTeclado instancia;
    private Menu_pausa pausa;
    private final String baseDirectory = "usuarios/";
    public boolean ARRIBA, ABAJO, IZQUIERDA, DERECHA;
    private String usuario;
    
    Estadisticas estadisticas;

    // Contador de movimientos en la sesión actual
    public int cant_move = 0;

    // Bandera para evitar inicializaciones múltiples
    private boolean movimientosCargados = false;

    // Constructor privado para patrón singleton
    private MovimientoTeclado() {
        System.out.println("[INIT] Creando nueva instancia de MovimientoTeclado");
    }

    // Obtener la única instancia
    public static MovimientoTeclado obtenerInstancia() {
        if (instancia == null) {
            instancia = new MovimientoTeclado();
        }
        return instancia;
    }

    // IMPORTANTE: Esta función DEBE llamarse después de establecer el usuario
    // Pero ANTES de usar el contador de movimientos
    public void cargarMovimientosPersistentes() {
        if (usuario == null || usuario.isEmpty()) {
            System.out.println("[ERROR] No se pueden cargar movimientos porque el usuario no está definido");
            return;
        }

        // Evitar cargar múltiples veces si ya se ha cargado para este usuario
        if (movimientosCargados) {
            System.out.println("[INFO] Los movimientos ya fueron cargados para el usuario: " + usuario);
            return;
        }

        // Leer el valor existente desde el archivo
        int movimientosGuardados = obtenerMovimientos();
        System.out.println("[INFO] Movimientos cargados desde archivo para " + usuario + ": " + movimientosGuardados);

        // NO resetear el contador - usamos el valor leído del archivo directamente
        cant_move = 0;

        // Marcar como cargado para este usuario
        movimientosCargados = true;

        System.out.println("[INFO] Contador inicializado con valores persistentes: " + cant_move);
    }

    public void guardarMovimientos() {
        if (usuario == null || usuario.isEmpty()) {
            System.out.println("[ERROR] No se pueden guardar movimientos porque el usuario no está definido");
            return;
        }

        System.out.println("[INFO] Guardando movimientos para " + usuario + ": " + cant_move);

        File userDir = new File(baseDirectory + usuario);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        File movimientosFile = new File(userDir, "movimientos.txt");

        // Obtener los movimientos previos antes de sobrescribir
        int movimientosPrevios = obtenerMovimientos();

        // SUMAR los movimientos actuales con los previos
        int nuevosMovimientos = movimientosPrevios + cant_move;
        
        cant_move = 0;

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(movimientosFile)))) {
            pw.println(nuevosMovimientos);
            System.out.println("[INFO] Movimientos actualizados y guardados correctamente: " + nuevosMovimientos);
        } catch (IOException e) {
            System.out.println("[ERROR] Error al guardar movimientos: " + e.getMessage());
            e.printStackTrace();
        }

        // Reiniciar cant_move local después de guardar (evita que se duplique en próximas llamadas)
        cant_move = 0;
    }

    public int obtenerMovimientos() {
        if (usuario == null || usuario.isEmpty()) {
            System.out.println("[ERROR] No se pueden obtener movimientos porque el usuario no está definido");
            return 0;
        }

        File userDir = new File(baseDirectory + usuario);
        File movimientosFile = new File(userDir, "movimientos.txt");

        // Verificar si el archivo existe antes de leer
        if (!movimientosFile.exists()) {
            System.out.println("[INFO] No se encontró un archivo previo de movimientos. Se inicia en 0.");
            return 0;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(movimientosFile))) {
            String linea = br.readLine();
            System.out.println("AQUIII"+linea);
            if (linea != null) {
                try {
                    
                    int movimientos = Integer.parseInt(linea.trim());
                    System.out.println("[INFO] Movimientos leídos del archivo para " + usuario + ": " + movimientos);
                    return movimientos;
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] El archivo contiene datos no válidos. Reiniciando a 0.");
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Error al leer movimientos: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

   public void inicializarMovimientos() { 
    if (usuario == null || usuario.isEmpty()) {
        System.out.println("[ERROR] No se pueden inicializar movimientos porque el usuario no está definido");
        return;
    }

    File userDir = new File(baseDirectory + usuario);
    if (!userDir.exists()) {
        userDir.mkdirs();
    }

    File archivo = new File(userDir, "movimientos.txt");

    // Evitar sobrescribir si ya existen movimientos
    if (archivo.exists()) {
        System.out.println("[ADVERTENCIA] El archivo de movimientos ya existe. No se reiniciará a menos que se solicite explícitamente.");
        return;
        
    }

    try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
        pw.println(0);
        cant_move = 0;
        System.out.println("[INFO] Movimientos inicializados a 0 para " + usuario);
    } catch (IOException e) {
        System.out.println("[ERROR] Error al inicializar movimientos: " + e.getMessage());
        e.printStackTrace();
    }
    cant_move = 0;
}

    public void setUsuario(String usuario) {
        boolean cambioUsuario = (this.usuario == null || !this.usuario.equals(usuario));
        this.usuario = usuario;
        System.out.println("[INFO] Usuario establecido: " + usuario);

        // Si cambió el usuario, reiniciamos la bandera de carga
        if (cambioUsuario) {
            movimientosCargados = false;
            System.out.println("[INFO] Bandera de carga reiniciada por cambio de usuario");
        }
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se requiere implementación
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Asegurar que los movimientos se hayan cargado antes de incrementar
        if (!movimientosCargados && usuario != null && !usuario.isEmpty()) {
            cargarMovimientosPersistentes();
        }

        // Registrar movimientos
        if (code == KeyEvent.VK_W && !ARRIBA) {
            ARRIBA = true;
            cant_move++;
            System.out.println("[INFO] Movimiento W registrado. Total: " + cant_move);
        }
        if (code == KeyEvent.VK_A && !IZQUIERDA) {
            IZQUIERDA = true;
            cant_move++;
            System.out.println("[INFO] Movimiento A registrado. Total: " + cant_move);
        }
        if (code == KeyEvent.VK_S && !ABAJO) {
            ABAJO = true;
            cant_move++;
            System.out.println("[INFO] Movimiento S registrado. Total: " + cant_move);
        }
        if (code == KeyEvent.VK_D && !DERECHA) {
            DERECHA = true;
            cant_move++;
            System.out.println("[INFO] Movimiento D registrado. Total: " + cant_move);
        }

        if (code == KeyEvent.VK_M) {
            new Menu_pausa(estadisticas).setVisible(true);

        }
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

    public int getMovimiento() {
        // Asegurar que los movimientos se hayan cargado antes de consultarlos
        if (!movimientosCargados && usuario != null && !usuario.isEmpty()) {
            cargarMovimientosPersistentes();
        }
        return cant_move;
    }

    // IMPORTANTE: Este método ya NO debe usarse, ya que resetearía el contador
    // Se mantiene por compatibilidad, pero está vacío
    public void setMovimiento() {
        // NO resetear el contador aquí
        System.out.println("[AVISO] Se llamó a setMovimiento() pero no se reseteará el contador para mantener la persistencia");
    }

  

    // Método para cerrar componentes
    public void cerrar(Component component) {
        // Guardar los movimientos antes de cerrar
        guardarMovimientos();

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
