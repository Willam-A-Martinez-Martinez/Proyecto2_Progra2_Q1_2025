package Users;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ManejoEstadisticas {
    private final String directorioBase;
    private final Map<String, Estadisticas> estadisticas;

    public ManejoEstadisticas(String directorioBase) {
        this.directorioBase = directorioBase;
        this.estadisticas = new HashMap<>();
        File dir = new File(directorioBase);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void guardarEstadisticas(String username, Estadisticas nuevasStats) {
        File usuarioDirectorio = new File(directorioBase + "/" + username);
        if (!usuarioDirectorio.exists()) {
            usuarioDirectorio.mkdirs();
        }

        File archivo = new File(usuarioDirectorio, "estadisticas.dat");

        // Cargar estadísticas previas si existen
        Estadisticas statsAnteriores = cargarEstadisticas(username);

        // Actualizar datos en memoria
        statsAnteriores.acumularEstadisticas(nuevasStats);
        estadisticas.put(username, statsAnteriores);

        // Guardar las estadísticas actualizadas
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(statsAnteriores);
            System.out.println("Estadísticas agregadas para el usuario: " + username);
        } catch (IOException e) {
            System.err.println("Error al guardar estadísticas: " + e.getMessage());
        }
    }

    public Estadisticas cargarEstadisticas(String username) {
        File archivo = new File(directorioBase + "/" + username + "/estadisticas.dat");

        if (!archivo.exists()) {
            return new Estadisticas();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Estadisticas) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar estadísticas de " + username + ": " + e.getMessage());
            return new Estadisticas();
        }
    }

    public void mostrarEstadisticas(String username) {
        Estadisticas stats = cargarEstadisticas(username);
        System.out.println("\nEstadísticas de " + username + ":\n" + stats);
    }

    public void limpiarEstadisticas(String username) {
        File archivo = new File(directorioBase + "/" + username + "/estadisticas.dat");
        if (archivo.exists() && archivo.delete()) {
            estadisticas.remove(username);
            System.out.println("Estadísticas eliminadas para el usuario: " + username);
        } else {
            System.out.println("No se pudo eliminar las estadísticas.");
        }
    }
}
