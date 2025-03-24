package Users;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejoPreferencia {
    private final String DIRECTORIO_USUARIOS = "Usuarios";
    private final String ARCHIVO_PREFERENCIA_USUARIO = "preferencias.dat";

    public Preferencias cargarPreferenciasUser(String nombreC) {
        File prefeArch = new File(DIRECTORIO_USUARIOS + File.separator + nombreC + File.separator + ARCHIVO_PREFERENCIA_USUARIO);

        if (!prefeArch.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(prefeArch))) {
            return (Preferencias) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ManejoPreferencia.class.getName()).log(Level.SEVERE, "Error al cargar las preferencias", ex);
        }

        return null;
    }

    public Preferencias guardarPreferenciasUser(String idioma, float volumen, String nombreC) {
        File dirUsuario = new File(DIRECTORIO_USUARIOS + File.separator + nombreC);
        if (!dirUsuario.exists()) {
            dirUsuario.mkdirs();  // Crear directorio si no existe
        }

        File prefeArch = new File(dirUsuario, ARCHIVO_PREFERENCIA_USUARIO);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(prefeArch))) {
            Preferencias prefe = new Preferencias(idioma, volumen);
            oos.writeObject(prefe);
            return prefe;
        } catch (IOException e) {
            Logger.getLogger(ManejoPreferencia.class.getName()).log(Level.SEVERE, "Error al guardar las preferencias", e);
        }

        return null;
    }

    public void actualizarVolumen(String nombreC, float nuevoVolumen) {
        
        Preferencias prefe = cargarPreferenciasUser(nombreC);

        if (prefe != null) {
            prefe.setVolumen(nuevoVolumen);
            guardarPreferenciasUser(prefe.getIdioma(), nuevoVolumen, nombreC);
        }
    }

    public void actualizarIdioma(String nombreC, String nuevoIdioma) {
        Preferencias prefe = cargarPreferenciasUser(nombreC);
        System.out.println("Actualizando idioma a: " + nuevoIdioma + " para usuario: " + nombreC);

        if (prefe != null) {
            // Existing user preferences
            prefe.setIdioma(nuevoIdioma);
            boolean success = guardarPreferenciasUser(nuevoIdioma, prefe.getVolumen(), nombreC) != null;
            System.out.println("Resultado de guardar: " + (success ? "Éxito" : "Fallo"));
        } else {
            // No existing preferences, create new ones
            System.out.println("No se encontraron preferencias previas, creando nuevas");
            float volumenPredeterminado = 0.5f; // Use a default value
            boolean success = guardarPreferenciasUser(nuevoIdioma, volumenPredeterminado, nombreC) != null;
            System.out.println("Resultado de guardar nuevas preferencias: " + (success ? "Éxito" : "Fallo"));
        }

        // Verify if file was created
        File prefeArch = new File(DIRECTORIO_USUARIOS + File.separator + nombreC + File.separator + ARCHIVO_PREFERENCIA_USUARIO);
        System.out.println("¿Existe archivo de preferencias? " + prefeArch.exists());
    }
}
