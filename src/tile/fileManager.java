/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import GUI.PgInicial;
import Users.Estadisticas;
import Users.ManejoEstadisticas;
import Users.Progreso;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import prueba_sprite.MovimientoTeclado;
import prueba_sprite.gamepanel;
import prueba_sprite.menu;
import Users.Ranking;

/**
 *
 * @author 50488
 */
public class fileManager {

    private static fileManager instancia;
    gamepanel gp;
    public tiles[] tile;
    public int maptilenum[][];
    public String[][] mapTileString;
    public ArrayList<int[]> puntosPosiciones;
    MovimientoTeclado xd = MovimientoTeclado.obtenerInstancia();

    Estadisticas estadisticas;
    PgInicial pgInicial;
    String mapa = "";

    public fileManager(gamepanel gp, PgInicial pgInicial, Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
        this.pgInicial=pgInicial;
        this.gp = gp;
        tile = new tiles[10];

        mapTileString = new String[gp.maxWorldCol][gp.maxWorldRow];
        maptilenum = new int[gp.maxWorldCol][gp.maxWorldRow];

        gettileImage();
        puntosPosiciones = new ArrayList<>();
        cargarMapaEnArreglo(mapa_escogido());
        loadmap();
    }

    public void cargarMapaEnArreglo(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                System.err.println("Error: No se encontró el archivo del mapa: " + filePath);
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            String line;

            while ((line = br.readLine()) != null && row < gp.maxWorldRow) {
                String[] numbers = line.split(" ");
                for (int col = 0; col < Math.min(numbers.length, gp.maxWorldCol); col++) {
                    mapTileString[col][row] = numbers[col];

                    if (numbers[col].equals("2")) {
                        puntosPosiciones.add(new int[]{col, row});
                    }
                }
                row++;
            }
            br.close();
            imprimirPuntos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imprimirPuntos() {
        System.out.println("Puntos de destino encontrados:");
        for (int[] coordenadas : puntosPosiciones) {
            System.out.println("Columna: " + coordenadas[0] + ", Fila: " + coordenadas[1]);
        }
    }

    public String mapa_escogido() {

        if (menu.getInstance().getnivel() == 1) {
            mapa = "/niveles/primer_nivel.txt";
        } else if (menu.getInstance().getnivel() == 2) {
            mapa = "/niveles/segundo_nivel.txt";
        } else if (menu.getInstance().getnivel() == 3) {
            mapa = "/niveles/Tercer_nivel.txt";
        } else if (menu.getInstance().getnivel() == 4) {
            mapa = "/niveles/cuarto_nivel.txt";
        } else if (menu.getInstance().getnivel() == 5) {
            mapa = "/niveles/quinto_nivel.txt";
        }
        return mapa;
    }

    public void loadmap() {
        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                if (mapTileString[col][row] != null) {
                    try {
                        maptilenum[col][row] = Integer.parseInt(mapTileString[col][row]);
                    } catch (NumberFormatException e) {
                        maptilenum[col][row] = 0;
                    }
                }
            }
        }
    }

    public void moverCaja(int nuevaCol, int nuevaRow, int cajaCol, int cajaRow, Estadisticas estadisticas) {
        if (nuevaCol < 0 || nuevaCol >= gp.maxWorldCol || nuevaRow < 0 || nuevaRow >= gp.maxWorldRow) {
            return;
        }

        if (maptilenum[nuevaCol][nuevaRow] == 1 || maptilenum[nuevaCol][nuevaRow] == 3) {
            return;
        }

        boolean estabaSobrePunto = (maptilenum[cajaCol][cajaRow] == 2 || mapTileString[cajaCol][cajaRow].equals("2"));

        maptilenum[nuevaCol][nuevaRow] = 3;
        mapTileString[nuevaCol][nuevaRow] = "3";

        if (estabaSobrePunto) {
            maptilenum[cajaCol][cajaRow] = 2;
            mapTileString[cajaCol][cajaRow] = "2";
        } else {
            maptilenum[cajaCol][cajaRow] = 0;
            mapTileString[cajaCol][cajaRow] = "0";
        }

        for (int[] coordenadas : puntosPosiciones) {
            int col = coordenadas[0];
            int row = coordenadas[1];

            if (maptilenum[col][row] != 3) {
                maptilenum[col][row] = 2;
                mapTileString[col][row] = "2";
            }
        }

        loadmap();
        verificarGanar();
    }

    public void verificarGanar() {
        boolean ganado = true;

        for (int[] coordenadas : puntosPosiciones) {
            int col = coordenadas[0];
            int row = coordenadas[1];

            if (maptilenum[col][row] != 3) {
                ganado = false;
                break;
            }
        }

        if (ganado) {
            JOptionPane.showMessageDialog(null, "¡Ganaste!", "Victoria", JOptionPane.INFORMATION_MESSAGE);

            if (xd.getUsuario() == null) {
                System.out.println("ERROR: El usuario no está definido en MovimientoTeclado antes de guardar.");
            } else {
                xd.guardarMovimientos();
            }
            ManejoEstadisticas mEstadisticas = new ManejoEstadisticas("Usuarios");
            Progreso progreso = new Progreso();
            
            
            
            menu menuPrincipal = menu.getInstance();
            menuPrincipal.cerrar_juego();
            menuPrincipal.siguiente_nivel();
            estadisticas.setTotalLevelsCompleted((progreso.cargarNivel(pgInicial.logUser.getNombreCompleto()))-1);
            menuPrincipal.actualizarNiveles();
            menuPrincipal.setVisible(true);
            Ranking.obtenerInstancia().organizarRanking();

            estadisticas.endGame();
            
            mEstadisticas.guardarEstadisticas(pgInicial.logUser.getNombreCompleto(), estadisticas);
            System.out.println(xd.getMovimiento());
        }
    }

    public void gettileImage() {
        try {
            tile[0] = new tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/folder1/fondo.jpg"));
            tile[1] = new tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/folder1/muro.png"));
            tile[1].collision = true;
            tile[2] = new tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/folder1/puntos.png"));
            tile[3] = new tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/folder1/cajannueva.jpg"));
            tile[3].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int Worldcol = 0;
        int Worldrow = 0;

        while (Worldcol < gp.maxWorldCol && Worldrow < gp.maxWorldRow) {

            int tileNum = maptilenum[Worldcol][Worldrow];
            int worldX = Worldcol * gp.tamanopersonaje;
            int worldY = Worldrow * gp.tamanopersonaje;
            int screenX = worldX - gp.esdras.worldx + gp.esdras.screenX;
            int screenY = worldY - gp.esdras.worldy + gp.esdras.screenY;

            if (worldX + gp.tamanopersonaje > gp.esdras.worldx - gp.esdras.screenX
                    && worldX - gp.tamanopersonaje < gp.esdras.worldx + gp.esdras.screenX
                    && worldY + gp.tamanopersonaje > gp.esdras.worldy - gp.esdras.screenY
                    && worldY - gp.tamanopersonaje < gp.esdras.worldy + gp.esdras.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tamanopersonaje, gp.tamanopersonaje, null);
            }
            Worldcol++;

            if (Worldcol == gp.maxWorldCol) {
                Worldcol = 0;
                Worldrow++;

            }

        }

    }

}
