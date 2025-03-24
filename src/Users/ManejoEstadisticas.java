/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Usuario
 */
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

        // Check if this is the first time saving stats for this user
        boolean isFirstSave = !archivo.exists();

        if (isFirstSave) {
            // First time saving - just save the new stats
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
                out.writeObject(nuevasStats);
                estadisticas.put(username, nuevasStats);
                System.out.println("Estadísticas iniciales creadas para el usuario: " + username);
            } catch (IOException e) {
                System.err.println("Error al guardar estadísticas: " + e.getMessage());
            }
        } else {
            // Updating existing stats
            Estadisticas statsAnteriores = cargarEstadisticas(username);

            // Instead of copying and then accumulating, just accumulate directly
            statsAnteriores.acumularEstadisticas(nuevasStats);

            // Update in-memory cache and save
            estadisticas.put(username, statsAnteriores);
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
                out.writeObject(statsAnteriores);
                System.out.println("Estadísticas actualizadas para el usuario: " + username);
            } catch (IOException e) {
                System.err.println("Error al guardar estadísticas: " + e.getMessage());
            }
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
