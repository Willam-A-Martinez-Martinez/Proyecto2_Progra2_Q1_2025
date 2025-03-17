package GUI;


import Users.Datos;
import Users.ManejoAvatar;
import Users.ManejoUser;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PgInicial extends Grafico{
    JFrame frame = new JFrame();
    JLabel background = new JLabel();
    JLabel titulo = new JLabel();
    JLabel muteL = new JLabel();
    JLabel idiomaL = new JLabel();
    JCheckBox muteCB = new JCheckBox();
    JCheckBox esCB = new JCheckBox();
    JCheckBox enCB = new JCheckBox();
    
    JButton iniciarSesion = new JButton();
    JButton registrarse = new JButton();
    JButton salir = new JButton();
    
    ImageIcon fondoGif = new ImageIcon("src/Assets/FondoRegistro-Inicio Sesion.gif");
    
    //Imagenes
    
    //Info de usuarios
    public Datos logUser;
    
    ManejoUser mUser;
    ManejoAvatar mAvatar;
    Musica music;
    
    private File archM = new File("src/Musica/happy 8bit.wav");
    
    Locale locale;
    ResourceBundle bundle;
    
    public PgInicial(){
        
        mAvatar= new ManejoAvatar();
        mUser = new ManejoUser();
        music = new Musica();
        
        music.setMusic(archM);
        music.play();
        music.loop();
        
        locale = new Locale("es");
        bundle = ResourceBundle.getBundle("resources.mensajes", locale);
        
        //FRAME
        confFrame(frame, "Segmented & Corp", 1000, 700, "Pantalla");

        frame.add(muteCB);
        frame.add(esCB);
        frame.add(enCB);
        frame.add(titulo);
        frame.add(muteL);
        frame.add(idiomaL);
        frame.add(iniciarSesion);
        frame.add(registrarse);
        frame.add(salir);
        frame.add(background);
        frame.setVisible(true);

        //BACKGROUND
        fondo(background, 0, 0, fondoGif, frame);

        titulo(titulo   , 450, 50 , 18*7, 50, "Dialog", 28, 250, bundle.getString("sokoban"));
        titulo.setAlignmentX(SwingConstants.LEFT);
        
        titulo(muteL   , 10, 50 , 18*9, 50, "Dialog", 28, 250, bundle.getString("silenciar"));
        muteCB.setBounds((10+18*8),50, 50, 50);
        muteCB.setContentAreaFilled(false);
        
        titulo(idiomaL   , 10, 100 , 18*13, 50, "Dialog", 28, 250, bundle.getString("idiomaPgInicial"));
        esCB.setBounds(10+18*8, 100, 50, 50);
        enCB.setBounds(10+18*9+50, 100, 50, 50);
        esCB.setEnabled(false);
        
        if(locale.toString().equals("es")){
            enCB.setSelected(false);
            esCB.setSelected(true);
            actualizarIdioma(new Locale("es"));
        }
        
        esCB.setContentAreaFilled(false);
        enCB.setContentAreaFilled(false);
        
        //BOTON 1 Iniciar sesion
        boton(iniciarSesion, 375, 150, 235, 42, false, false, "Dialog", 28, bundle.getString("iniciar_sesion"), 250);

        //BOTON 2 iniciar_Sesion
        boton(registrarse, 375, 225, 235, 42, false, false, "Dialog", 28, bundle.getString("registrarse"), 250);

        //BOTON 3 salir del sistema
        boton(salir, 375, 325, 235, 42, false, false, "Dialog", 28, bundle.getString("salir"), 250);
        
        
        
        muteCB.addActionListener((ActionEvent e) -> {
            music.volumeMute();
        });
        
        esCB.addActionListener((ActionEvent e) -> {
            if(esCB.isSelected()){
                enCB.setSelected(false);
                actualizarIdioma(new Locale("es"));

                titulo.setText(bundle.getString("sokoban"));
                muteL.setText(bundle.getString("silenciar"));
                idiomaL.setText(bundle.getString("idiomaPgInicial"));
                iniciarSesion.setText(bundle.getString("iniciar_sesion"));
                registrarse.setText(bundle.getString("registrarse"));
                salir.setText(bundle.getString("salir"));
                
                // Establecer correctamente los bounds
                idiomaL.setBounds(10, 100, 18*13, 50);
                muteL.setBounds(10, 50, 18*9, 50);
                muteCB.setBounds((10+18*8),50, 50, 50);
                esCB.setBounds(10+18*8, 100, 50, 50);
                enCB.setBounds(10+18*9+50, 100, 50, 50);

                // Actualizar estado de los checkboxes
                enCB.setEnabled(true);
                esCB.setEnabled(false);

                // Refrescar frame
                frame.repaint();
                frame.validate();
            }
        });

        enCB.addActionListener((ActionEvent e) -> {
            if(enCB.isSelected()){
                esCB.setSelected(false);
                actualizarIdioma(new Locale("en"));

                titulo.setText(bundle.getString("sokoban"));
                muteL.setText(bundle.getString("silenciar"));
                idiomaL.setText(bundle.getString("idiomaPgInicial"));
                iniciarSesion.setText(bundle.getString("iniciar_sesion"));
                registrarse.setText(bundle.getString("registrarse"));
                salir.setText(bundle.getString("salir"));
                // Establecer correctamente los bounds
                idiomaL.setBounds(10, 100, 18*14, 50);
                muteL.setBounds(10, 50, 18*9, 50);
                muteCB.setBounds((10+18*4), 50, 50, 50);
                esCB.setBounds(10+18*10, 100, 50, 50);
                enCB.setBounds(10+18*11+50, 100, 50, 50);

                // Actualizar estado de los checkboxes
                enCB.setEnabled(false);
                esCB.setEnabled(true);
                

                // Refrescar frame (repintar todo el frame, no solo un componente)
                frame.repaint();
                frame.validate();
            }
        });

        iniciarSesion.addActionListener((ActionEvent e) -> {
            if(mUser.contarUsuario()>0){
                IniciarSesion iniciarSesion = new IniciarSesion(this);
                iniciarSesion.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        
        

        registrarse.addActionListener((ActionEvent e) -> {
            Registrarse registrarse = new Registrarse(this);
            registrarse.frame.setVisible(true);
            frame.setVisible(false);
        });
        
        salir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }
    
    public void actualizarIdioma(Locale nuevaLocale) {
        locale = nuevaLocale;
        bundle = ResourceBundle.getBundle("resources.mensajes", locale);
        
        
        frame.repaint();
        frame.validate();
    }
    
}
