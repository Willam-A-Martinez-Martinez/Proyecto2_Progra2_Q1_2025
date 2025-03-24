package Users;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejoHistorial {
    private static final String ARCHIVO_HISTORIAL = "historial.dat";

    public static void guardarHistorial(Historial historial) {
        List<Historial> historiales = cargarHistoriales();
        historiales.add(historial);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_HISTORIAL))) {
            out.writeObject(historiales);
        } catch (IOException e) {
            System.err.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    public static List<Historial> cargarHistoriales() {
        File archivo = new File(ARCHIVO_HISTORIAL);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_HISTORIAL))) {
            return (List<Historial>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar historiales: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void mostrarHistoriales() {
        List<Historial> historiales = cargarHistoriales();
        if (historiales.isEmpty()) {
            System.out.println("No hay historiales guardados.");
        } else {
            for (Historial h : historiales) {
                System.out.println(h);
            }
        }
    }

    public static void limpiarHistorial() {
        File archivo = new File(ARCHIVO_HISTORIAL);
        if (archivo.exists() && archivo.delete()) {
            System.out.println("Historial eliminado.");
        } else {
            System.out.println("No se pudo eliminar el historial.");
        }
    }
}
