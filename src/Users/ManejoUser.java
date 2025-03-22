/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class ManejoUser {
    private final String DIRECTORIO_USUARIOS = "Usuarios";
    private final String ARCHIVO_DATO_USUARIO = "datos.dat";
    private File usersDir;
    private File []usuarios;
    
    public ManejoUser(){
        usersDir = new File(DIRECTORIO_USUARIOS);
        if(!usersDir.exists()){
            usersDir.mkdir();
        }
        usuarios= usersDir.listFiles();
    }
    
    public Datos cargaUsuario(String nombreC) {
        File userF = new File(DIRECTORIO_USUARIOS + File.separator + nombreC + File.separator + ARCHIVO_DATO_USUARIO);

        // Verificación para asegurarse de que el archivo existe
        System.out.println("Ruta del archivo: " + userF.getAbsolutePath());

        if (!userF.exists()) {
            System.out.println("Error: El archivo de datos del usuario '" + nombreC + "' no existe.");
            return null;
        }

        // Intentar leer el archivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userF))) {
            Datos user = (Datos) ois.readObject();
            System.out.println("Usuario cargado con éxito: " + user.getNombreCompleto());
            ois.close();
            return user;
        } catch (IOException e) {
            System.err.println("Error de E/S al cargar el usuario '" + nombreC + "': " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: La clase Datos no se encontró al cargar el usuario '" + nombreC + "'.");
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean guardarUsuario(String nombreUser, String nombreC, String contraseña){
        
        File dir = new File(DIRECTORIO_USUARIOS+File.separator+nombreC+ File.separator);
        
        if(!dir.exists()){
            dir.mkdir();
        }
        
        File userF = new File(dir, ARCHIVO_DATO_USUARIO);
        
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userF))){
            usuarios= dir.listFiles();
            
            Datos user = new Datos(nombreUser, nombreC, contraseña);
            oos.writeObject(user);
            oos.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean agregarUsuario(String nombreUser, String nombreC, String contraseña){
        Datos user = cargaUsuario(nombreC);
        
        if(user==null){
            return guardarUsuario(nombreUser, nombreC, contraseña);
        }
        return false;
    }
    
    
    public Datos existeUsuario(String nombreC, String contraseña){
        Datos user = cargaUsuario(nombreC);
        
        if(user!=null){
            if(user.getContraseña().equals(contraseña)){
                return user;
            }
        }
        
        return null;
    }
    
    public boolean eliminarUsuario(String nombreC, String contraseña){
        Datos user = cargaUsuario(nombreC);
        File dir = new File(DIRECTORIO_USUARIOS+File.separator+nombreC);
        
        if(user==null){
            return false;
        }
        
        if(!contraseña.equals(user.getContraseña())){
            return false;
        }
        
        if(!borrarRF(dir)){
            System.out.println("Error en la funcion de recursividad para borrar directorio");
            return false;
        }
        
        if(!dir.delete()){
            System.out.println("Error al borrar archivo principal");
            return false;
        }
        
        return true;
    }
    
    public boolean borrarRF(File file){
        if(file.isDirectory()){
            File []fileL=file.listFiles();
            if(fileL!=null){
                for (File child: fileL) {
                    if(!borrarRF(child)){
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }
    
    public int contarUsuario(){
        return usuarios.length;
    }
    
    public void actualizaUltimaSesion(File user){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user));
            Datos userO = (Datos) ois.readObject();
            ois.close();
            
            userO.setUltimaSesion(Calendar.getInstance());
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user));
            oos.writeObject(userO);
            oos.close();
            
            usuarios= (new File(DIRECTORIO_USUARIOS)).listFiles();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ManejoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean cambiarContra(File user, String contra){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user));
            Datos userO = (Datos) ois.readObject();
            ois.close();
            
            if(!userO.getContraseña().equals(contra)){
                userO.setContraseña(contra);
                
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user));
                oos.writeObject(userO);
                oos.close();

                usuarios= (new File(DIRECTORIO_USUARIOS)).listFiles();
                return true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ManejoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean cambiarNombre(File user, String apodo){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user));
            Datos userO = (Datos) ois.readObject();
            ois.close();
            
            if(!userO.getNombreUser().equals(apodo)){
                userO.setNombreUser(apodo);
                
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user));
                oos.writeObject(userO);
                oos.close();

                usuarios= (new File(DIRECTORIO_USUARIOS)).listFiles();
                return true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejoUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ManejoUser.class.getName()).log(Level.SEVERE, null, ex);
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
            
            // Copiar la nueva imagen
            Files.copy(
                Path.of(rutaOrigen), 
                Path.of(directorioUsuario, nuevoPerfil), 
                StandardCopyOption.REPLACE_EXISTING
            );
            File user= new File(directorioUsuario+File.separator+ARCHIVO_DATO_USUARIO);
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user));
            Datos userO = (Datos) ois.readObject();
            ois.close();
            userO.setAvatar(directorioUsuario + File.separator + nuevoPerfil);
            System.out.println("Avatar seteado como: "+directorioUsuario + File.separator + nuevoPerfil);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user));
            oos.writeObject(userO);
            oos.close();

            usuarios= (new File(DIRECTORIO_USUARIOS)).listFiles();
            return directorioUsuario + File.separator + nuevoPerfil;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
