/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import GUI.PgInicial;
import GUI.PgPrincipal;
import Users.Progreso;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author 50488
 */
public class menu extends JFrame {
    private static menu instancia;
    private int nivel = 0; // Inicializa en 0
    private JFrame ventanaJuego; // Almacena la ventana del juego
    private int nivel_actual = 1;

    private JLabel fondo;
    private JButton crear_player;
    private JButton nivel_2;
    private JButton nivel_3;
    private JButton nivel_4;
    private JButton nivel_5;
    String usuario;

    Font pixelMplus;
    FontMetrics metrics;
    
    private PgInicial pgInicial;
    private static PgInicial staticPgInicial;

    ImageIcon logoVentana = new ImageIcon("src/UI_Images/CajaCafeOscuro.png");
    ImageIcon backGround = new ImageIcon("src/Assets/FondoMenu.gif");
    
    public void setUser(String user) {
        this.usuario = user;
        System.out.println("Usuario establecido: " + usuario);
        Asignar_nivel();  // Cargar el nivel cuando se establece el usuario
    }

    public String getNombre() {
        return usuario;
    }

    public void Asignar_nivel() {
        if (usuario != null && !usuario.isEmpty()) {
            nivel_actual = Progreso.obtenerInstancia().cargarNivel(usuario);
            actualizarNiveles();  // Actualizar botones después de cargar el nivel
        } else {
            System.out.println("No se pudo cargar el nivel: usuario inválido.");
        }
    }
    
    public menu(PgInicial pgInicial) {
        try{
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("src/PixelMplus-20130602/PixelMplus12-Bold.ttf"));
            pixelMplus = pixelMplus.deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelMplus);
        }catch(IOException | FontFormatException e){
            
        }
        
        fondo = new JLabel();
        
        this.pgInicial = pgInicial;
        
        
        if (staticPgInicial.logUser != null) {
            System.out.println("Nombre de usuario: " + staticPgInicial.logUser.getNombreCompleto());
        } else {
            System.out.println("Usuario no iniciado sesión");
        }
        
        setTitle("Menu de inicio");
        setSize(1000, 700);
        setIconImage(logoVentana.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setFont(pixelMplus);
        
        fondo.setBounds(0, 0, 1000, 700);
        fondo.setIcon(backGround);

        Color summerWhite = new Color(0xFFFAF0);
        Color croissant = new Color(0xF1C27D);
        Color cayenne = new Color(0xE2583E);

        getContentPane().setBackground(summerWhite);
        int xBtn = 111,yBtn= 115, y=190, menuWidth = 400, frameWidth = getWidth(), x = (frameWidth - menuWidth) / 2;
        
        
        JLabel menu = new JLabel("Sokoban");
        menu.setFont(pixelMplus);
        menu.setBounds(x, 10, menuWidth, 30);
        menu.setForeground(cayenne);
        add(menu);

        
        crear_player = new JButton();
        crear_player.setBounds(58, y, xBtn, yBtn);
        crear_player.setBorder(null);
        crear_player.setContentAreaFilled(false);
        crear_player.setOpaque(false);
        add(crear_player);

        nivel_2 = new JButton();
        nivel_2.setBounds(250, y, xBtn, yBtn);
        nivel_2.setBorder(null);
        nivel_2.setContentAreaFilled(false);
        nivel_2.setOpaque(false);
        add(nivel_2);

        nivel_3 = new JButton();
        nivel_3.setBounds(450, y, xBtn, yBtn);
        nivel_3.setBorder(null);
        nivel_3.setContentAreaFilled(false);
        nivel_3.setOpaque(false);
        add(nivel_3);

        nivel_4 = new JButton();
        nivel_4.setBounds(638, y, xBtn, yBtn);
        nivel_4.setBorder(null);
        nivel_4.setContentAreaFilled(false);
        nivel_4.setOpaque(false);
        add(nivel_4);

        nivel_5 = new JButton();
        nivel_5.setBounds(825, y, xBtn, yBtn);
        nivel_5.setBorder(null);
        nivel_5.setContentAreaFilled(false);
        nivel_5.setOpaque(false);
        add(nivel_5);
       
        JButton salirse = new JButton();
        salirse.setBounds(184, y+150, xBtn, yBtn);
        salirse.setBorder(null);
        salirse.setContentAreaFilled(false);
        salirse.setOpaque(false);
        add(salirse);
        
        add(fondo);
        
        // Desactivar todos los niveles excepto el 1
        actualizarNiveles();

        // Asignar acciones a los botones
        crear_player.addActionListener(e -> seleccionarNivel(1));
        nivel_2.addActionListener(e -> seleccionarNivel(2));
        nivel_3.addActionListener(e -> seleccionarNivel(3));
        nivel_4.addActionListener(e -> seleccionarNivel(4));
        nivel_5.addActionListener(e -> seleccionarNivel(5));
        salirse.addActionListener(e->{
            PgPrincipal pgPrincipal = new PgPrincipal(pgInicial);
            if(pgInicial.music.getVolumen1()==-40.0f){
                pgInicial.music.volumeMute();
            }
            pgInicial.music.fc.setValue(pgInicial.music.getVolumen1());
            this.dispose();
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
        nivel_3.setEnabled(nivel_actual >= 3);
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
            if (staticPgInicial != null) {
                instancia = new menu(staticPgInicial);
            } else {
                throw new IllegalStateException("PgInicial not initialized. Call initializeWithPgInicial first.");
            }
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

        gamepanel xd = new gamepanel(this.nivel, pgInicial);
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
    
    public static void initializeWithPgInicial(PgInicial pgInicial) {
        staticPgInicial = pgInicial;
    }
}
