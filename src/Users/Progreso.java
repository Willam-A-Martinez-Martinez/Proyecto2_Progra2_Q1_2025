package Users;

import java.io.*;
import java.util.*;

public class Progreso {

    private static Progreso instancia;
    private String user; // Usuario actual
    private final String baseDirectory = "usuarios/"; // Base de directorios para guardar datos

    // Método para guardar el progreso del usuario
    public void guardarProgreso(String usuario) {
        // Crea la carpeta del usuario si no existe
        File userDir = new File(baseDirectory + usuario);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        System.out.println(userDir);

        // Guardar nivel inicial (1) en progreso.txt
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(userDir.getPath() + "/progreso.txt")))) {
            System.out.println(pw);
            pw.println(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer el nombre del usuario guardado
    public String leerNombreUsuario() {
        if (user == null) {
            return null; // Si no hay usuario guardado, retorna null
        }
        String archivoNombre = baseDirectory + user + "/nombre.txt";
        File file = new File(archivoNombre);
        if (!file.exists()) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para verificar si el usuario ya está registrado
    public boolean usuarioRegistrado(String usuario) {
        return new File(baseDirectory + usuario + "/progreso.txt").exists();
    }

    public int cargarNivel(String usuario) {
    // Validar si el usuario es nulo o vacío
    if (usuario == null || usuario.trim().isEmpty()) {
        System.out.println("Usuario inválido");
        return 1; // Si el usuario no es válido, devolver nivel 1
    }

    // Crear la ruta del archivo
    File archivo = new File(baseDirectory + File.separator + usuario + File.separator + "progreso.txt");

    // Verificar si el archivo existe
    if (!archivo.exists()) {
        System.out.println("El archivo de progreso no existe para el usuario: " + usuario);
        return 1; // Si no existe, asignar nivel 1 por defecto
    }

    // Leer el archivo
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea = br.readLine();

        // Verificar si la línea leída es válida
        if (linea != null && !linea.trim().isEmpty()) {
            try {
                int nivel = Integer.parseInt(linea.trim());
                System.out.println("Nivel cargado: " + nivel);
                return nivel;
            } catch (NumberFormatException e) {
                System.out.println("Error: el archivo contiene datos inválidos -> " + linea);
            }
        } else {
            System.out.println("El archivo está vacío");
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }

    // Si algo falla, devolver nivel 1 por defecto
    return 1;
}

    public void guardarNivel(String usuario, int nivel) {

        System.out.println(usuario);
        String archivo = baseDirectory + usuario + "/progreso.txt";
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
            pw.println(nivel);
            System.out.println("Nivel guardado en archivo: " + nivel); // Verifica si se guarda bien
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String user) {
        this.user = user;
        System.out.println("Usuario cambiado a: " + this.user); // Verificación
    }

    public static Progreso obtenerInstancia() {
        if (instancia == null) {
            instancia = new Progreso(); // Se pasa el mismo `PgInicial`
        }
        return instancia;
    }
}