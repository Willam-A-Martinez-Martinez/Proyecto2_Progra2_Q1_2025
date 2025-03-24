package Users;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejoUser {
    private final String DIRECTORIO_USUARIOS = "Usuarios";
    private final String ARCHIVO_DATO_USUARIO = "datos.dat";
    private File usersDir;
    private File[] usuarios;

    public ManejoUser() {
        usersDir = new File(DIRECTORIO_USUARIOS);
        if (!usersDir.exists()) {
            usersDir.mkdir();
        }
        usuarios = usersDir.listFiles();
    }

    public Datos cargaUsuario(String nombreC) {
        File userF = new File(DIRECTORIO_USUARIOS + File.separator + nombreC + File.separator + ARCHIVO_DATO_USUARIO);

        if (!userF.exists()) {
            System.out.println("Error: El archivo de datos del usuario '" + nombreC + "' no existe.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userF))) {
            Datos user = (Datos) ois.readObject();
            user.setUltimaSesion(LocalDateTime.now());
            guardarDatosUsuario(user, userF);
            return user;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean guardarUsuario(String nombreUser, String nombreC, String contraseña) {
        File dir = new File(DIRECTORIO_USUARIOS + File.separator + nombreC);

        if (!dir.exists()) {
            dir.mkdir();
        }

        File userF = new File(dir, ARCHIVO_DATO_USUARIO);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userF))) {
            Datos user = new Datos(nombreUser, nombreC, contraseña);
            oos.writeObject(user);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarUsuario(String nombreUser, String nombreC, String contraseña) {
        if (cargaUsuario(nombreC) == null) {
            return guardarUsuario(nombreUser, nombreC, contraseña);
        }
        return false;
    }

    public Datos existeUsuario(String nombreC, String contraseña) {
        Datos user = cargaUsuario(nombreC);
        return (user != null && user.getContraseña().equals(contraseña)) ? user : null;
    }

    public boolean eliminarUsuario(String nombreC, String contraseña) {
        Datos user = cargaUsuario(nombreC);
        File dir = new File(DIRECTORIO_USUARIOS + File.separator + nombreC);

        if (user == null || !contraseña.equals(user.getContraseña())) {
            return false;
        }

        return borrarRF(dir) && dir.delete();
    }

    private boolean borrarRF(File file) {
        if (file.isDirectory()) {
            File[] fileL = file.listFiles();
            if (fileL != null) {
                for (File child : fileL) {
                    if (!borrarRF(child)) {
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }

    public int contarUsuario() {
        return usuarios.length;
    }

    public void iniciaSesionUsuario(String nombreC) {
        Datos user = cargaUsuario(nombreC);
        if (user != null) {
            user.iniciaSesion();
            guardarDatosUsuario(user, new File(DIRECTORIO_USUARIOS + File.separator + nombreC + File.separator + ARCHIVO_DATO_USUARIO));
        }
    }

    public void cierraSesionUsuario(String nombreC) {
        Datos user = cargaUsuario(nombreC);
        if (user != null) {
            user.cierraSesion();
            guardarDatosUsuario(user, new File(DIRECTORIO_USUARIOS + File.separator + nombreC + File.separator + ARCHIVO_DATO_USUARIO));
        }
    }

    private void guardarDatosUsuario(Datos user, File userFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cambiarContra(File user, String contra) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user))) {
            Datos userO = (Datos) ois.readObject();
            if (!userO.getContraseña().equals(contra)) {
                userO.setContraseña(contra);
                guardarDatosUsuario(userO, user);
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cambiarNombre(File user, String apodo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user))) {
            Datos userO = (Datos) ois.readObject();
            if (!userO.getNombreUser().equals(apodo)) {
                userO.setNombreUser(apodo);
                guardarDatosUsuario(userO, user);
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String guardarImagen(String rutaOrigen, String nombreCompleto) {
        try {
            String directorioUsuario = DIRECTORIO_USUARIOS + File.separator + nombreCompleto;
            File perfilPng = new File(directorioUsuario, "Perfil.png");
            File perfilJpg = new File(directorioUsuario, "Perfil.jpg");

            if (perfilPng.exists()) perfilPng.delete();
            if (perfilJpg.exists()) perfilJpg.delete();

            String extension = rutaOrigen.toLowerCase().endsWith(".png") ? "png" : "jpg";
            String nuevoPerfil = "Perfil." + extension;

            Files.copy(Path.of(rutaOrigen), Path.of(directorioUsuario, nuevoPerfil), StandardCopyOption.REPLACE_EXISTING);
            
            File userFile = new File(directorioUsuario + File.separator + ARCHIVO_DATO_USUARIO);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile));
            Datos userO = (Datos) ois.readObject();
            ois.close();
            
            userO.setAvatar(directorioUsuario + File.separator + nuevoPerfil);
            guardarDatosUsuario(userO, userFile);

            return directorioUsuario + File.separator + nuevoPerfil;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void actualizarSesion(String nombreC) {
    Datos user = cargaUsuario(nombreC);
    if (user != null) {
        user.setUltimaSesion(LocalDateTime.now());
        guardarDatosUsuario(user, new File(DIRECTORIO_USUARIOS + File.separator + nombreC + File.separator + ARCHIVO_DATO_USUARIO));
        System.out.println("Última sesión actualizada para " + nombreC + ": " + user.getUltimaSesionFormateada());
    } else {
        System.out.println("No se pudo actualizar la sesión. Usuario no encontrado.");
    }
}
}