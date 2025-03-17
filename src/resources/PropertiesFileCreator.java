/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author DELL
 */
public class PropertiesFileCreator {
    public static void createFile() {
        createPropertiesFile("src/resources/mensajes_es.properties", "es");
        createPropertiesFile("src/resources/mensajes_en.properties", "en");
    }

    private static void createPropertiesFile(String filePath, String lang) {
        Properties properties = new Properties();
        if ("es".equals(lang)) {
            properties.setProperty("sokoban","Sokoban");
            properties.setProperty("registrarse", "Registrarse");
            properties.setProperty("iniciar_sesion", "Iniciar Sesión");
            properties.setProperty("salir", "Salir");
            properties.setProperty("silenciar","Silenciar: ");
            properties.setProperty("idiomaPgInicial","Idioma: ES   EN");
            properties.setProperty("nombre","Nombre: ");
            properties.setProperty("contra","Contraseña:");
            properties.setProperty("volver","Volver");
            properties.setProperty("cambiarFP","Cambiar foto de perfil");
            properties.setProperty("nombreC","Nombre Completo:");
            properties.setProperty("registrar","Registrar");
            properties.setProperty("menuPrincipal", "Menu principal");
            properties.setProperty("jugar","Jugar");
            properties.setProperty("perfilUsuario","Perfil de usuario");
            properties.setProperty("apodo","Apodo: ");
            properties.setProperty("configuracion","Configuracion");
            properties.setProperty("estadisticas","Estadisticas");
            properties.setProperty("ranking","Ranking");
            properties.setProperty("salirSesion","Salir de sesion");
            properties.setProperty("fechaIngreso","Fecha de ingreso:");
            properties.setProperty("ultimaSesion","Ultima sesion: ");
            properties.setProperty("volumen","Volumen: ");
            properties.setProperty("idiomaP","Idioma: ");
            properties.setProperty("juego","Juego");
            properties.setProperty("perfil","Perfil");
            properties.setProperty("cambioApodo","Cambio de apodo");
            properties.setProperty("cambiarApodo","Cambiar apodo");
            properties.setProperty("cambioPerfil","Cambio de foto de perfil");
            properties.setProperty("cambioContraseña","Cambio de contraseña");
            properties.setProperty("ingreseContra","Ingrese contraseña");
            properties.setProperty("cambiarContra","Cambiar contraseña");
        } else if ("en".equals(lang)) {
            properties.setProperty("sokoban","Sokoban");
            properties.setProperty("registrarse", "Sign up");
            properties.setProperty("iniciar_sesion", "Log in");
            properties.setProperty("salir", "Exit");
            properties.setProperty("silenciar","Mute: ");
            properties.setProperty("idiomaPgInicial","Language: ES   EN");
            properties.setProperty("nombre","Name: ");
            properties.setProperty("contra","Password:");
            properties.setProperty("volver","Back");
            properties.setProperty("cambiarFP","Change profile picture");
            properties.setProperty("nombreC","Complete name:");
            properties.setProperty("registrar","Sign up");
            properties.setProperty("menuPrincipal", "Main menu");
            properties.setProperty("jugar","Play");
            properties.setProperty("perfilUsuario","User profile");
            properties.setProperty("apodo","Nickname: ");
            properties.setProperty("configuracion","Configuration");
            properties.setProperty("estadisticas","Statistics");
            properties.setProperty("ranking","Ranking");
            properties.setProperty("salirSesion","Log out");
            properties.setProperty("fechaIngreso","Registration date:");
            properties.setProperty("ultimaSesion","Last sesion: ");
            properties.setProperty("volumen","Volume: ");
            properties.setProperty("idiomaP","Language: ");
            properties.setProperty("juego","Game");
            properties.setProperty("perfil","Profile");
            properties.setProperty("cambioApodo","Nickname change");
            properties.setProperty("cambiarApodo","Change nickname");
            properties.setProperty("cambioPerfil","Profile change");
            properties.setProperty("cambioContraseña","Password change");
            properties.setProperty("ingreseContra","Enter password");
            properties.setProperty("cambiarContra","Change password");
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.store(fos, "Language Properties File - " + lang);
            System.out.println("✅ Archivo creado: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Error creando archivo: " + e.getMessage());
        }
    }
}
