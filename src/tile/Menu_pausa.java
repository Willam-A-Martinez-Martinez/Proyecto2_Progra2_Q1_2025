/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import GUI.PgInicial;
import Users.Estadisticas;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import prueba_sprite.menu;

/**
 *
 * @author 50488
 */
public class Menu_pausa extends JFrame {
    PgInicial pgInicial;
    Estadisticas estadisticas;
    menu menuu;
 private static boolean salirJuego = false; 
    public Menu_pausa(Estadisticas estadisticas) {
        this.estadisticas=estadisticas;
        
        menuu = new menu(pgInicial);
        setTitle("Menu de Pausa");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // No cierra toda la aplicación
        setLayout(null);
        setLocationRelativeTo(null);

        // Colores
        Color summerWhite = new Color(0xFFFAF0);
        Color croissant = new Color(0xF1C27D);
        Color cayenne = new Color(0xE2583E);
        getContentPane().setBackground(summerWhite);

        // Título
        JLabel menuu = new JLabel("--- MENU DE PAUSA ---", SwingConstants.CENTER);
        menuu.setBounds(50, 10, 300, 30);
        menuu.setForeground(cayenne);
        add(menuu);

        // Botón "Continuar"
        JButton crear_player = new JButton("CONTINUAR");
        crear_player.setBounds(100, 90, 200, 30);
        crear_player.setBackground(croissant);
        crear_player.setForeground(cayenne);
        add(crear_player);

        // Botón "Reiniciar Nivel"
        JButton nivel_2 = new JButton("REINICIAR NIVEL");
        nivel_2.setBounds(100, 180, 200, 30);
        nivel_2.setBackground(croissant);
        nivel_2.setForeground(cayenne);
        add(nivel_2);

       

        // Botón "Salir"
        JButton salir = new JButton("SALIR");
        salir.setBounds(100, 450, 200, 30);
        salir.setBackground(croissant);
        salir.setForeground(cayenne);
        add(salir);

        // Acción para "Continuar"
        crear_player.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Siga con el juego");
            this.dispose(); // Cierra solo el menú de pausa
        });

        // Acción para "Salir"
        salir.addActionListener(e -> {
    int opcion = JOptionPane.showConfirmDialog(
            null, "¿Deseas volver al menú?", "Volver al menú",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
    );

    if (opcion == JOptionPane.YES_OPTION) {
         // Pasamos el botón como referencia
         menu.getInstance().cerrar_juego();
         this.dispose();
         menu.getInstance().setVisible(true);
    }
});

        
        nivel_2.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Reiniciando nivel...");
            this.dispose();
            menu.getInstance().cerrar_juego();
            menu.getInstance().abrirJuego();
        });

        
    }
    
  
   
}
