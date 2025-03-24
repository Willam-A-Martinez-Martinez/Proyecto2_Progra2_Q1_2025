/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import java.io.*;
import java.util.*;

/**
 *
 * @author Usuario
 */
/*

   

 */
public class Ranking {

    private String baseDirectory = "usuarios/"; // Ajustar según sea necesario
    private String usuariosFile = baseDirectory + File.separator + "users.txt";
    private static Ranking instancia;

    public Ranking() {
        cargarUsuarios(); // Carga usuarios desde el archivo al iniciar
    }

    public static Ranking obtenerInstancia() {
        if (instancia == null) {
            instancia = new Ranking();
        }
        return instancia;
    } // Guarda un usuario en el archivo si no está repetido

    public void guardarUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("[ERROR] Usuario inválido.");
            return;
        }

        Set<String> usuarios = cargarUsuarios(); // Leer usuarios actuales

        if (!usuarios.contains(usuario)) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(usuariosFile, true))) {
                bw.write(usuario);
                bw.newLine();
                System.out.println("[INFO] Usuario agregado: " + usuario);
            } catch (IOException e) {
                System.out.println("[ERROR] No se pudo guardar el usuario: " + e.getMessage());
            }
        } else {
            System.out.println("[INFO] Usuario ya existe.");
        }
    }

    // Carga los usuarios desde el archivo
    public Set<String> cargarUsuarios() {
        Set<String> usuarios = new HashSet<>();

        File archivo = new File(usuariosFile);
        if (!archivo.exists()) {
            return usuarios;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(usuariosFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                usuarios.add(linea.trim());
            }
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo leer el archivo de usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    // Organiza y muestra el ranking
    public String organizarRanking() {
        Set<String> usuarios = cargarUsuarios();
        List<Jugador> jugadores = new ArrayList<>();
        String ranking = "";

        for (String usuario : usuarios) {
            int nivel = cargarNivel(usuario);
            int movimientos = obtenerMovimientos(usuario);
            jugadores.add(new Jugador(usuario, nivel, movimientos));
        }

        jugadores.sort(Comparator.comparingInt(Jugador::getNivel).reversed()
                .thenComparingInt(Jugador::getMovimientos));

        System.out.println("\n=== RANKING ===");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i));
            ranking+="\n"+(i + 1) + ". " + jugadores.get(i);
        }
        return ranking;
    }

    // Obtiene el nivel del usuario desde su archivo de progreso
    public int cargarNivel(String usuario) {
        File archivo = new File(baseDirectory + File.separator + usuario + File.separator + "progreso.txt");
        if (!archivo.exists()) {
            return 1;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            return (linea != null) ? Integer.parseInt(linea.trim()) : 1;
        } catch (Exception e) {
            return 1;
        }
    }

    // Obtiene los movimientos del usuario desde su archivo
    public int obtenerMovimientos(String usuario) {
        File archivo = new File(baseDirectory + File.separator + usuario + File.separator + "movimientos.txt");
        if (!archivo.exists()) {
            return 0;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            return (linea != null) ? Integer.parseInt(linea.trim()) : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    // Clase interna para manejar jugadores en el ranking
    private static class Jugador {

        private String nombre;
        private int nivel;
        private int movimientos;

        public Jugador(String nombre, int nivel, int movimientos) {
            this.nombre = nombre;
            this.nivel = nivel;
            this.movimientos = movimientos;
        }

        public int getNivel() {
            return nivel;
        }

        public int getMovimientos() {
            return movimientos;
        }

        @Override
        public String toString() {
            return nombre + " - Nivel: " + nivel + " - Movimientos: " + movimientos;
        }
    }
}
