/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 50488
 */
public class menu extends JFrame {
    private static menu instancia;
    private int nivel = 0; // Inicializa en 0
    private JFrame ventanaJuego; // Almacena la ventana del juego
    private int nivel_actual = 1;

    private JButton crear_player;
    private JButton nivel_2;
    private JButton salir;
    private JButton nivel_4;
    private JButton nivel_5;

    public menu() {
        setTitle("Menu de inicio");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        Color summerWhite = new Color(0xFFFAF0);
        Color croissant = new Color(0xF1C27D);
        Color cayenne = new Color(0xE2583E);

        getContentPane().setBackground(summerWhite);

        JLabel menu = new JLabel("Bienvenido al menú de inicio de sokoban");
        menu.setBounds(100, 10, 400, 30);
        menu.setForeground(cayenne);
        add(menu);

        crear_player = new JButton("Nivel 1");
        crear_player.setBounds(100, 90, 200, 30);
        crear_player.setBackground(croissant);
        crear_player.setForeground(cayenne);
        add(crear_player);

        nivel_2 = new JButton("Nivel 2");
        nivel_2.setBounds(100, 180, 200, 30);
        nivel_2.setBackground(croissant);
        nivel_2.setForeground(cayenne);
        add(nivel_2);

        salir = new JButton("Nivel 3");
        salir.setBounds(100, 270, 200, 30);
        salir.setBackground(croissant);
        salir.setForeground(cayenne);
        add(salir);

        nivel_4 = new JButton("Nivel 4");
        nivel_4.setBounds(100, 360, 200, 30);
        nivel_4.setBackground(croissant);
        nivel_4.setForeground(cayenne);
        add(nivel_4);

        nivel_5 = new JButton("Nivel 5");
        nivel_5.setBounds(100, 450, 200, 30);
        nivel_5.setBackground(croissant);
        nivel_5.setForeground(cayenne);
        add(nivel_5);
       
        JButton salirse = new JButton("Nivel 5");
        salirse.setBounds(100, 540, 200, 30);
        salirse.setBackground(croissant);
        salirse.setForeground(cayenne);
        add(salirse);

        // Desactivar todos los niveles excepto el 1
        actualizarNiveles();

        // Asignar acciones a los botones
        crear_player.addActionListener(e -> seleccionarNivel(1));
        nivel_2.addActionListener(e -> seleccionarNivel(2));
        salir.addActionListener(e -> seleccionarNivel(3));
        nivel_4.addActionListener(e -> seleccionarNivel(4));
        nivel_5.addActionListener(e -> seleccionarNivel(5));
        salirse.addActionListener(e->{
            
        });
    }

    public void siguiente_nivel() {
        if (nivel_actual < 5) {
            nivel_actual++;
        }
    }

    public void actualizarNiveles() {
        crear_player.setEnabled(nivel_actual >= 1);
        nivel_2.setEnabled(nivel_actual >= 2);
        salir.setEnabled(nivel_actual >= 3);
        nivel_4.setEnabled(nivel_actual >= 4);
        nivel_5.setEnabled(nivel_actual >= 5);
    }

    public int getNivel() {
        return nivel_actual;
    }

    public void setNivel(int nivel) {
        nivel_actual = nivel;
    }

    public static menu getInstance() {
        if (instancia == null) {
            instancia = new menu();
        }
        return instancia;
    }

    public int getnivel() {
        return nivel;
    }

    private void seleccionarNivel(int nivelSeleccionado) {
        this.nivel = nivelSeleccionado;
        abrirJuego();
    }

    public void abrirJuego() {
        System.out.println("Abriendo juego en nivel: " + this.nivel);

        ventanaJuego = new JFrame();
        ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaJuego.setResizable(false);
        ventanaJuego.setTitle("MAPAS");

        gamepanel xd = new gamepanel(this.nivel);
        ventanaJuego.add(xd);
        ventanaJuego.pack();

        ventanaJuego.setLocationRelativeTo(null);
        ventanaJuego.setVisible(true);
        xd.iniciothread();

        this.setVisible(false);
    }

    public void cerrar_juego() {
        if (ventanaJuego != null) {
            ventanaJuego.dispose();
            ventanaJuego = null;
        }
        this.setVisible(true);
    }
}
