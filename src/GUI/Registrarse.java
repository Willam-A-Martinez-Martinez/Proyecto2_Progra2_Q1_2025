/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Users.Datos;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class Registrarse extends Grafico{
    JFrame frame = new JFrame();
    JLabel tituloSuperior = new JLabel();
    JLabel background = new JLabel();
    JLabel nombreUserTxt = new JLabel();
    JLabel nombreCTxt = new JLabel ();
    JLabel contraseñaTxt = new JLabel();
    JLabel fotoPerfil = new JLabel();
    
    JButton cambiarPerfil = new JButton();
    JButton volver = new JButton();
    JButton registrarse = new JButton();   
    JTextField nombreUserTxtF = new JTextField();
    JTextField nombreCTxtF = new JTextField();
    JPasswordField contraseñaTxtF = new JPasswordField();
    
    String rutaAvatar;
    ImageIcon fotoPerfilImg;
    ImageIcon backgroundI= new ImageIcon("src/Assets/FondoRegistro-Inicio Sesion.gif");
    //Info de usuarios
    PgInicial pgInicial;
    public Registrarse(PgInicial pgInicial){
        this.pgInicial =pgInicial;
        rutaAvatar= "src/UI_Images/default profile.png";
        
        fotoPerfilImg= new ImageIcon(rutaAvatar);
        Image scaleI = scaleImage(fotoPerfilImg.getImage(), 128, 128);
        fotoPerfilImg = new ImageIcon(scaleI);
        //FRAME
        confFrame(frame, "Segmented & Corp", 1000, 700, "Pantalla");
        
        frame.add(cambiarPerfil);
        frame.add(fotoPerfil);
        frame.add(tituloSuperior);
        frame.add(nombreCTxt );
        frame.add(nombreCTxtF);
        frame.add(nombreUserTxtF);
        frame.add(contraseñaTxtF);
        frame.add(nombreUserTxt);
        frame.add(contraseñaTxt);
        frame.add(volver);
        frame.add(registrarse);
        frame.add(background);
        frame.setVisible(true);
        
        //BACKGROUND
        fondo(background, 0, 0, backgroundI, frame);
        
        //Foto perfil
        /*
        Cambiar foto de perfil
        Nombre Completo:
        Registrar
        
        */
        titulo(fotoPerfil,710, 150, 128, 128, "Dialog", 28, 250, "");
        fotoPerfil.setIcon(fotoPerfilImg);
        //cambiar perfil
        boton(cambiarPerfil, 600, 310, 400, 42, false, false, "Dialog", 28, pgInicial.bundle.getString("cambiarFP"), 250);
        
        //TITULO
        titulo(tituloSuperior, 590, 71, 170, 50, "Dialog", 28, 250, pgInicial.bundle.getString("registrarse"));
        
        //Sub titulo 1
        titulo(nombreUserTxt, 450, 128, 170, 50, "Dialog", 28, 250, pgInicial.bundle.getString("nombre"));
        
        //Textfield 1
        textfield(nombreUserTxtF, 425, 175, 170, 30, "Dialog", 20);
        
        //Sub titulo 2
        titulo(nombreCTxt , 400, 215, 255, 50, "Dialog", 28, 250, pgInicial.bundle.getString("nombreC"));
        
        //Textfield 2 
        textfield(nombreCTxtF, 425, 255, 170, 30, "Dialog", 20);
        
        //Sub titulo 3
        titulo(contraseñaTxt, 425, 290, 170, 50, "Dialog", 28, 250, pgInicial.bundle.getString("contra"));
        
        //Textfield 3
        passwordfield(contraseñaTxtF, 425, 335, 170, 30, "Dialog", 20);
        
        //BOTON 1 Iniciar sesion
        boton(volver, 370, 400, 235, 42, false, false, "Dialog", 28, pgInicial.bundle.getString("volver"), 250);

        //BOTON 2 registrarse
        boton(registrarse, 660, 400, 235, 42, false, false, "Dialog", 28, pgInicial.bundle.getString("registrar"), 250);


        volver.addActionListener((ActionEvent e) -> {
            pgInicial.frame.setVisible(true);
            frame.dispose();
        });

        cambiarPerfil.addActionListener((ActionEvent e) -> {
            rutaAvatar=pgInicial.mAvatar.seleccionarImagen();
            fotoPerfilImg= new ImageIcon(rutaAvatar);
            
            Image scaleIH = scaleImage(fotoPerfilImg.getImage(), 128, 128);
            fotoPerfilImg = new ImageIcon(scaleIH);
            fotoPerfil.setIcon(fotoPerfilImg);
        });
        
        registrarse.addActionListener((ActionEvent e) -> {
            String nombreUser = nombreUserTxtF.getText().trim();
            String nombreC = nombreCTxtF.getText().trim();
            String contra = new String(contraseñaTxtF.getPassword()).trim();
            

            System.out.println("DEBUG: Username=[" + nombreUser + "], Full Name=[" + nombreC + "], Password=[" + contra + "]"+", Avatar ="+rutaAvatar);

            if(!nombreUser.equals("") || !nombreC.equals("") || !contra.equals("")){
                if(pgInicial.mUser.agregarUsuario(nombreUser, nombreC, contra, rutaAvatar)){
                    
                    System.out.println("Ruta de avatar despues de acceder a registrarse: "+rutaAvatar);
                    
                   PgPrincipal pgP = new PgPrincipal(pgInicial);
                   pgP.frame.setVisible(true);
                   pgInicial.logUser = pgInicial.mUser.cargaUsuario(nombreC);
                   System.out.println("Ruta que aparece con el user guardado: "+pgInicial.logUser.getAvatar());

                   if(pgInicial.logUser==null){
                        System.out.println("Log user es null");
                   }else{
                       System.out.println("Contra de user: "+pgInicial.logUser.getContraseña());
                       System.out.println("Nombre completo de usar: "+pgInicial.logUser.getNombreCompleto());
                   }
                   frame.dispose();
                }
                nombreUserTxtF.setText("");
                nombreCTxtF.setText("");
                contraseñaTxtF.setText("");
            }
        });
    }
    
    public static Image scaleImage(Image originalImage, int width, int height) {
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
   

