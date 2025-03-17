/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import entidad.jugador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.fileManager;

/**
 *
 * @author 50488
 */
public class gamepanel extends JPanel implements Runnable {

    private int nivel;
    final int tamanobase = 16;
    final int escala = 3;

    public final int tamanopersonaje = tamanobase * escala;

    public final int pantallacolumnas = 16;
    public final int pantallafilas = 12;
    public final int pantallaancho = tamanopersonaje * pantallacolumnas;
    public final int pantallalargo = tamanopersonaje * pantallafilas;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tamanopersonaje * maxWorldCol;
    public final int worldHeight = tamanopersonaje * maxWorldRow;
    int FPS = 60;

    MovimientoTeclado movimiento = new MovimientoTeclado();

    Thread juegoTH;

    public jugador esdras = new jugador(this, movimiento);
    fileManager tileM = new fileManager(this);
    public CollisionCheker colision;

    public gamepanel(int nivel) {
        this.nivel = nivel;
        System.out.println("El nivel en gamepanel es: " + this.nivel);
        this.setPreferredSize(new Dimension(pantallaancho, pantallalargo));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(movimiento);
        this.setFocusable(true);
        colision = new CollisionCheker(this);
    }

    public int getnivel() {
        return nivel;
    }

    public void iniciothread() {
        juegoTH = new Thread(this);
        juegoTH.start();
    }


    public void run() {
        double dibujointervalo = 1000000000 / FPS;
        double delta = 0;
        long ultimavez = System.nanoTime();
        long tiempoactal;

        while (juegoTH != null) {
            tiempoactal = System.nanoTime();
            delta += (tiempoactal - ultimavez) / dibujointervalo;

            ultimavez = tiempoactal;

            if (delta >= 1) {
                update();
                repaint();

                delta--;

            }

        }
    }

    public void update() {
        esdras.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        esdras.dibujar(g2);
        g2.dispose();

    }

}

//    @Override
//    public void run() {
//        double dibujointervalo = 1000000000/FPS;
//        double siguientedibujo = System.nanoTime()+dibujointervalo;
//        while(juegoTH != null){
//            
//            update();
//            
//            repaint();
//            try{
//                double tiemporestante = siguientedibujo - System.nanoTime();
//                tiemporestante = tiemporestante/1000000;
//                if (tiemporestante <0){
//                    tiemporestante = 0;
//                            
//                }
//            Thread.sleep((long)tiemporestante);
//            
//            siguientedibujo+=dibujointervalo;
//            
//            } catch(InterruptedException e){
//                e.printStackTrace();
//            }
//            
//        }
//    }
