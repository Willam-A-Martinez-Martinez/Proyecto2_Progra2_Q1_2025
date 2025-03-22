package GUI;

import static GUI.Registrarse.scaleImage;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import prueba_sprite.menu;

public class PgPrincipal extends Grafico{
    JFrame frame= new JFrame();
    JLabel lblPerfil       = new JLabel();
    private JLabel fondo = new JLabel();
    private JLabel titulo= new JLabel();
    private JButton btnIniciarPartida= new JButton();
    private JButton btnPerfilUsuario= new JButton();
    private JButton btnEstadisticas= new JButton();
    private JButton btnRanking= new JButton();
    private JButton btnPreferencias= new JButton();
    private JButton btnSalir= new JButton();
    
    //variables de preferencia
    JButton botonPP;
    JButton botonP;
    JButton botonNickname;
    JLabel labelP;
    JLabel labelPP;
    JLabel labelNickname;

    ImageIcon backgroundI= new ImageIcon("src/Assets/FondoRegistro-Inicio Sesion.gif");
    
    String rutaAvatar;
    private ImageIcon fotoPerfilImg;
    
    public PgInicial pgInicial;
    
    private int contadorCambiarP=0;
    
    public PgPrincipal(PgInicial pgInicial) {
        if(pgInicial.mPreferencia.cargarPreferenciasUser(pgInicial.logUser.getNombreCompleto()).getVolumen()==-40.0f){
            pgInicial.music.volumeMute();
        }else{
            pgInicial.music.fc.setValue(pgInicial.mPreferencia.cargarPreferenciasUser(pgInicial.logUser.getNombreCompleto()).getVolumen());
        }
        System.out.println(pgInicial.locale.toString());
        pgInicial.locale=new Locale(pgInicial.mPreferencia.cargarPreferenciasUser(pgInicial.logUser.getNombreCompleto()).getIdioma());
        pgInicial.bundle = ResourceBundle.getBundle("resources.mensajes", pgInicial.locale);
        
        rutaAvatar = pgInicial.logUser.getAvatar();
        fotoPerfilImg = new ImageIcon(rutaAvatar);
        
        metrics = frame.getFontMetrics(pixelMplus);
        this.pgInicial= pgInicial;
        confFrame(frame, "Sokoban", 1000, 700, "Pantalla");
        /*
        Jugar
        Perfil de usuario
        Configuracion
        Estadisticas
        Ranking
        Log out
        */
        fondo(fondo            , 0, 0, backgroundI, frame);
        titulo(titulo           , 400, 50 , (metrics.stringWidth((pgInicial.bundle.getString("menuPrincipal")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("menuPrincipal"));
        boton(btnIniciarPartida, 380, 110, (metrics.stringWidth(pgInicial.bundle.getString("jugar")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("jugar"),250);
        boton(btnPerfilUsuario , 380, 170, (metrics.stringWidth(pgInicial.bundle.getString("perfilUsuario")))+34  , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("perfilUsuario"), 250);
        boton(btnPreferencias  , 380, 230, (metrics.stringWidth(pgInicial.bundle.getString("configuracion")))+35  , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("configuracion"), 250);
        boton(btnEstadisticas  , 380, 290, (metrics.stringWidth(pgInicial.bundle.getString("estadisticas")))+35   , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("estadisticas"), 250);
        boton(btnRanking       , 380, 350, (metrics.stringWidth(pgInicial.bundle.getString("ranking")))+35        , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("ranking"), 250);
        boton(btnSalir         , 380, 410, (metrics.stringWidth(pgInicial.bundle.getString("salirSesion")))+35    , 50, false, false, "Dialog", 28,pgInicial.bundle.getString("salirSesion"), 250);
        btnIniciarPartida.setAlignmentX(SwingConstants.LEFT);
        btnPerfilUsuario .setAlignmentX(SwingConstants.LEFT);
        btnPreferencias  .setAlignmentX(SwingConstants.LEFT);
        btnEstadisticas  .setAlignmentX(SwingConstants.LEFT);
        btnRanking       .setAlignmentX(SwingConstants.LEFT);
        btnSalir         .setAlignmentX(SwingConstants.LEFT);
        
        frame.add(titulo,0);
        frame.add(btnIniciarPartida,1);
        frame.add(btnPerfilUsuario,2);
        frame.add(btnPreferencias,3);
        frame.add(btnEstadisticas,4);
        frame.add(btnRanking,5);
        frame.add(btnSalir,6);
        frame.add(fondo,7);
        
        frame.invalidate();
        frame.validate();
        frame.repaint();
        
        frame.setVisible(true);
        btnPerfilUsuario.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.revalidate();
            perfilUsuario();
        });
        
        btnPreferencias.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.revalidate();
            preferencia();
        });
        
        btnEstadisticas.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.revalidate();
            estadisticas();
        });
        
        btnRanking.addActionListener((ActionEvent e) -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.revalidate();
            ranking();
        });
        btnIniciarPartida.addActionListener((ActionEvent e)->{
             SwingUtilities.invokeLater(() -> {
          menu.getInstance().setVisible(true);
          frame.dispose();
        });
        });
        
        btnSalir.addActionListener((ActionEvent e) -> {
            if(pgInicial.music.mute==true){
                pgInicial.muteCB.setSelected(true);
            }else{
                pgInicial.muteCB.setSelected(false);
            }
            if(pgInicial.locale.toString().equals("en")){
                pgInicial.esCB.setSelected(false);
                pgInicial.enCB.setSelected(true);
                pgInicial.titulo.setText(pgInicial.bundle.getString("sokoban"));
                pgInicial.muteL.setText(pgInicial.bundle.getString("silenciar"));
                pgInicial.idiomaL.setText(pgInicial.bundle.getString("idiomaPgInicial"));
                pgInicial.iniciarSesion.setText(pgInicial.bundle.getString("iniciar_sesion"));
                pgInicial.registrarse.setText(pgInicial.bundle.getString("registrarse"));
                pgInicial.salir.setText(pgInicial.bundle.getString("salir"));
                // Establecer correctamente los bounds
                pgInicial.idiomaL.setBounds(10, 100, 18*14, 50);
                pgInicial.muteL.setBounds(10, 50, 18*9, 50);
                pgInicial.muteCB.setBounds((10+18*4), 50, 50, 50);
                pgInicial.esCB.setBounds(10+18*10, 100, 50, 50);
                pgInicial.enCB.setBounds(10+18*11+50, 100, 50, 50);

                // Actualizar estado de los checkboxes
                pgInicial.enCB.setEnabled(false);
                pgInicial.esCB.setEnabled(true);
                

                // Refrescar frame (repintar todo el frame, no solo un componente)
                frame.repaint();
                frame.validate();
            }else if(pgInicial.locale.toString().equals("es")){
                pgInicial.enCB.setSelected(false);
                pgInicial.esCB.setSelected(true);

                pgInicial.titulo.setText(pgInicial.bundle.getString("sokoban"));
                pgInicial.muteL.setText(pgInicial.bundle.getString("silenciar"));
                pgInicial.idiomaL.setText(pgInicial.bundle.getString("idiomaPgInicial"));
                pgInicial.iniciarSesion.setText(pgInicial.bundle.getString("iniciar_sesion"));
                pgInicial.registrarse.setText(pgInicial.bundle.getString("registrarse"));
                pgInicial.salir.setText(pgInicial.bundle.getString("salir"));
                
                // Establecer correctamente los bounds
                pgInicial.idiomaL.setBounds(10, 100, 18*13, 50);
                pgInicial.muteL.setBounds(10, 50, 18*9, 50);
                pgInicial.muteCB.setBounds((10+18*8),50, 50, 50);
                pgInicial.esCB.setBounds(10+18*8, 100, 50, 50);
                pgInicial.enCB.setBounds(10+18*9+50, 100, 50, 50);

                // Actualizar estado de los checkboxes
                pgInicial.enCB.setEnabled(true);
                pgInicial.esCB.setEnabled(false);

                // Refrescar frame
                frame.repaint();
                frame.validate();
            }
            pgInicial.music.setVolumen1(-8);
            pgInicial.logUser = null;
            pgInicial.frame.setVisible(true);
            frame.dispose();
        });
    }

    

    private void perfilUsuario() {
        JLabel tituloPerfil = new JLabel();
        lblPerfil       = new JLabel();
        JLabel lblNombre       = new JLabel();
        JLabel lblApodo        = new JLabel();
        JLabel lblFechaIngreso = new JLabel();
        JLabel lblUltimaSesion = new JLabel();
        JButton salir = new JButton();
        
        ImageIcon perfil = new ImageIcon(pgInicial.logUser.getAvatar());
        
        System.out.println("Ruta de imagen: "+pgInicial.logUser.getAvatar());
        
        /*
        Nombre:
        Apodo: 
        Fecha de ingreso:
        Ultima sesion: 
        */
        
        titulo(tituloPerfil   , 400, 50 , 250, 50, "Dialog", 28, 250, pgInicial.bundle.getString("perfilUsuario"));
        titulo(lblPerfil      , 400, 110 , 100, 100, "Dialog", 28, 250, "");
        titulo(lblNombre      , 400, 215 , 18*(8+pgInicial.logUser.getNombreCompleto().length()) , 50, "Dialog", 28, 250, pgInicial.bundle.getString("nombre")+pgInicial.logUser.getNombreCompleto());
        titulo(lblApodo       , 400, 275 , (18*(7+pgInicial.logUser.getNombreUser().length()))+20 , 50, "Dialog", 28, 250, pgInicial.bundle.getString("apodo")+pgInicial.logUser.getNombreUser());
        titulo(lblFechaIngreso, 400, 335 , 18*(18+pgInicial.logUser.getFechaRegistro().length()), 50, "Dialog", 28, 250, pgInicial.bundle.getString("fechaIngreso")+pgInicial.logUser.getFechaRegistro());
        titulo(lblUltimaSesion, 400, 395 , 18*(15+pgInicial.logUser.getUltimaSesion().length()), 50, "Dialog", 28, 250, pgInicial.bundle.getString("ultimaSesion")+pgInicial.logUser.getUltimaSesion());
        
        lblPerfil.setIcon(scaleImage(perfil, 100, 100));
        boton(salir, 140, 180, 120, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("volver"), 250);
        
        frame.add(tituloPerfil);
        frame.add(lblPerfil);
        frame.add(salir);
        frame.add(lblNombre);
        frame.add(lblApodo);
        frame.add(lblFechaIngreso);
        frame.add(lblUltimaSesion);
        frame.add(fondo);
        
        
        
        frame.repaint();
        frame.revalidate();
        
        salir.addActionListener((ActionEvent e) -> {
            
            pgInicial.idiomaL.setBounds(10, 100 , (metrics.stringWidth(pgInicial.bundle.getString("silenciar"))), 50);
            pgInicial.muteCB.setBounds((metrics.stringWidth(pgInicial.bundle.getString("silenciar"))), 50, 50, 50);
            pgInicial.esCB.setBounds((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial"))), 100, 50, 50);
            pgInicial.enCB.setBounds(metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")), 100, 50, 50);
            
            frame.add(titulo,0);
            frame.add(btnIniciarPartida,1);
            frame.add(btnPerfilUsuario,2);
            frame.add(btnPreferencias,3);
            frame.add(btnEstadisticas,4);
            frame.add(btnRanking,5);
            frame.add(btnSalir,6);
            frame.add(fondo,7);
            
            frame.repaint();
            frame.revalidate();
        });
    }
    
    private void preferencia() {
        UIManager.put("TabbedPane.contentOpaque", false);
        UIManager.put("TabbedPane.tabsOpaque", false);
        
        JButton     salir             = new JButton();
        JTabbedPane configuracionTabs = new JTabbedPane();
        
        configuracionTabs.setBounds(320, 50, 650, 400);
        
        //juego
        JPanel    juego         = new JPanel();
        JCheckBox españolCB     = new JCheckBox();
        JCheckBox inglesCB      = new JCheckBox();
        JLabel    volumenTitulo = new JLabel();
        JLabel    idiomaTitulo  = new JLabel();
        JSlider   volumenM      = new JSlider(-40,6);
        
        if(pgInicial.music.mute==true){
            volumenM.setValue(-40);
        }
        
        titulo(volumenTitulo, 20, 20 , (metrics.stringWidth(pgInicial.bundle.getString("volumen")))+2, 50, "Dialog", 28, 250, pgInicial.bundle.getString("volumen"));
        titulo(idiomaTitulo, 20, 80 , (metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+2, 50, "Dialog", 28, 250, pgInicial.bundle.getString("idiomaPgInicial"));
        System.out.println((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+2);
        
        españolCB.setBounds(130+((metrics.stringWidth(pgInicial.bundle.getString("volumen")))-(5*18)), 80, 50, 50);
        inglesCB.setBounds((metrics.stringWidth(pgInicial.bundle.getString("volumen")))+110, 80, 50, 50);
        españolCB.setContentAreaFilled(false);
        inglesCB.setContentAreaFilled(false);
        
        
        
        volumenM.setBounds((18*7), 35, 200, 25);
        volumenM.setLayout(null);
        volumenM.setOpaque(false);
        
        juego.add(españolCB);
        juego.add(inglesCB);
        juego.add(volumenTitulo);
        juego.add(idiomaTitulo);
        juego.setOpaque(false);
        juego.setLayout(null);
        juego.add(volumenM);
        
        
        
        //perfil
        JPanel perfil = new JPanel();
        
        contPanelConfPerfil(frame, configuracionTabs, perfil, salir);
        
        //
        configuracionTabs.addTab(pgInicial.bundle.getString("juego"), juego);
        configuracionTabs.addTab(pgInicial.bundle.getString("perfil"), perfil);
        configuracionTabs.setOpaque(false);
        configuracionTabs.setBorder(null);
        configuracionTabs.setFont(pixelMplus);
        
        if(pgInicial.locale.toString().equals("en")){
            españolCB.setSelected(false);
            inglesCB.setSelected(true);
            pgInicial.bundle = ResourceBundle.getBundle("resources.mensajes", pgInicial.locale);
            pgInicial.actualizarIdioma(new Locale("en"));

            volumenTitulo.setText(pgInicial.bundle.getString("volumen"));
            idiomaTitulo.setText(pgInicial.bundle.getString("idiomaPgInicial"));

            configuracionTabs.setTitleAt(0, pgInicial.bundle.getString("juego"));
            configuracionTabs.setTitleAt(1, pgInicial.bundle.getString("perfil"));
            salir.setText(pgInicial.bundle.getString("volver"));

            titulo.setText(pgInicial.bundle.getString("menuPrincipal"));
            btnIniciarPartida.setText(pgInicial.bundle.getString("jugar"));
            btnPerfilUsuario.setText(pgInicial.bundle.getString("perfilUsuario"));
            btnEstadisticas.setText(pgInicial.bundle.getString("estadisticas"));
            btnRanking.setText(pgInicial.bundle.getString("ranking"));
            btnPreferencias.setText(pgInicial.bundle.getString("configuracion"));
            btnSalir.setText(pgInicial.bundle.getString("salirSesion"));

            inglesCB.setEnabled(false);
            españolCB.setEnabled(true);

            btnIniciarPartida.setAlignmentX(SwingConstants.LEFT);
            btnPerfilUsuario .setAlignmentX(SwingConstants.LEFT);
            btnPreferencias  .setAlignmentX(SwingConstants.LEFT);
            btnEstadisticas  .setAlignmentX(SwingConstants.LEFT);
            btnRanking       .setAlignmentX(SwingConstants.LEFT);
            btnSalir         .setAlignmentX(SwingConstants.LEFT);
            
            

            idiomaTitulo.setBounds(20, 80 , (metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+2, 50);
            españolCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+10-(3*18), 80);
            inglesCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+28, 80);
            juego.repaint();
            juego.validate();
        }else if(pgInicial.locale.toString().equals("es")){
            inglesCB.setSelected(false);
            españolCB.setSelected(true);
            pgInicial.bundle = ResourceBundle.getBundle("resources.mensajes", pgInicial.locale);
            pgInicial.actualizarIdioma(new Locale("es"));
            
            

            volumenTitulo.setText(pgInicial.bundle.getString("volumen"));
            idiomaTitulo.setText(pgInicial.bundle.getString("idiomaPgInicial"));

            configuracionTabs.setTitleAt(0, pgInicial.bundle.getString("juego"));
            configuracionTabs.setTitleAt(1, pgInicial.bundle.getString("perfil"));
            salir.setText(pgInicial.bundle.getString("volver"));

            titulo.setText(pgInicial.bundle.getString("menuPrincipal"));
            btnIniciarPartida.setText(pgInicial.bundle.getString("jugar"));
            btnPerfilUsuario.setText(pgInicial.bundle.getString("perfilUsuario"));
            btnEstadisticas.setText(pgInicial.bundle.getString("estadisticas"));
            btnRanking.setText(pgInicial.bundle.getString("ranking"));
            btnPreferencias.setText(pgInicial.bundle.getString("configuracion"));
            btnSalir.setText(pgInicial.bundle.getString("salirSesion"));


            españolCB.setEnabled(false);
            inglesCB.setEnabled(true);

            idiomaTitulo.setBounds(20, 80 , (metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+2, 50);
            españolCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+10-(3*18), 80);
            inglesCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+28, 80);
            juego.repaint();
            juego.validate();
        }
        
        volumenM.setValue(Math.round(pgInicial.music.volumen1));
        
        volumenM.addChangeListener((ChangeEvent e) -> {
                
            pgInicial.music.volumen1 = volumenM.getValue();
            pgInicial.mPreferencia.actualizarVolumen(pgInicial.logUser.getNombreCompleto(), pgInicial.music.volumen1);
            if(pgInicial.music.mute==false && pgInicial.music.volumen1==-40){
                System.out.println("Esta en -40");
                pgInicial.music.volumeMute();
            }else if(pgInicial.music.volumen1!=-40){
                if (pgInicial.music.mute) {  // Si estaba muteado, desmutea
                    pgInicial.music.volumeMute();
                }
                pgInicial.music.fc.setValue(pgInicial.music.volumen1);
            }
            System.out.println("Valor de slider: "+volumenM.getValue());
        });
        
        
        
        
        españolCB.addChangeListener((ChangeEvent e) -> {
            if(españolCB.isSelected()){
                inglesCB.setSelected(false);
                pgInicial.locale = new Locale("es");
                pgInicial.bundle = ResourceBundle.getBundle("resources.mensajes", pgInicial.locale);
                pgInicial.actualizarIdioma(new Locale("es"));
                
                pgInicial.mPreferencia.actualizarIdioma(pgInicial.logUser.getNombreCompleto(), pgInicial.locale.toString());

                volumenTitulo.setText(pgInicial.bundle.getString("volumen"));
                idiomaTitulo.setText(pgInicial.bundle.getString("idiomaPgInicial"));

                configuracionTabs.setTitleAt(0, pgInicial.bundle.getString("juego"));
                configuracionTabs.setTitleAt(1, pgInicial.bundle.getString("perfil"));
                salir.setText(pgInicial.bundle.getString("volver"));
                
                titulo.setText(pgInicial.bundle.getString("menuPrincipal"));
                btnIniciarPartida.setText(pgInicial.bundle.getString("jugar"));
                btnPerfilUsuario.setText(pgInicial.bundle.getString("perfilUsuario"));
                btnEstadisticas.setText(pgInicial.bundle.getString("estadisticas"));
                btnRanking.setText(pgInicial.bundle.getString("ranking"));
                btnPreferencias.setText(pgInicial.bundle.getString("configuracion"));
                btnSalir.setText(pgInicial.bundle.getString("salirSesion"));
                
                labelNickname.setText(pgInicial.bundle.getString("cambioApodo"));
                labelP.setText(pgInicial.bundle.getString("cambioContraseña"));
                labelPP.setText(pgInicial.bundle.getString("cambioPerfil"));
                botonNickname.setText(pgInicial.bundle.getString("cambiarApodo"));
                botonP.setText(pgInicial.bundle.getString("ingreseContra"));
                botonPP.setText(pgInicial.bundle.getString("cambiarFP"));
                salir.setText(pgInicial.bundle.getString("volver"));
                
                españolCB.setEnabled(false);
                inglesCB.setEnabled(true);
                
                idiomaTitulo.setBounds(20, 80 , (metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+5, 50);
                volumenTitulo.setBounds(20, 20 , (metrics.stringWidth(pgInicial.bundle.getString("volumen")))+2, 50);
                españolCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+10-(3*18), 80);
                inglesCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+28, 80);
                juego.repaint();
                juego.validate();
            }
        });
        
        inglesCB.addChangeListener((ChangeEvent e) -> {
            if(inglesCB.isSelected()){
                españolCB.setSelected(false);
                pgInicial.locale = new Locale("en");
                pgInicial.bundle = ResourceBundle.getBundle("resources.mensajes", pgInicial.locale);
                pgInicial.actualizarIdioma(new Locale("en"));
                
                pgInicial.mPreferencia.actualizarIdioma(pgInicial.logUser.getNombreCompleto(), pgInicial.locale.toString());

                volumenTitulo.setText(pgInicial.bundle.getString("volumen"));
                idiomaTitulo.setText(pgInicial.bundle.getString("idiomaPgInicial"));

                configuracionTabs.setTitleAt(0, pgInicial.bundle.getString("juego"));
                configuracionTabs.setTitleAt(1, pgInicial.bundle.getString("perfil"));
                salir.setText(pgInicial.bundle.getString("volver"));
                
                titulo.setText(pgInicial.bundle.getString("menuPrincipal"));
                btnIniciarPartida.setText(pgInicial.bundle.getString("jugar"));
                btnPerfilUsuario.setText(pgInicial.bundle.getString("perfilUsuario"));
                btnEstadisticas.setText(pgInicial.bundle.getString("estadisticas"));
                btnRanking.setText(pgInicial.bundle.getString("ranking"));
                btnPreferencias.setText(pgInicial.bundle.getString("configuracion"));
                btnSalir.setText(pgInicial.bundle.getString("salirSesion"));
                
                labelNickname.setText(pgInicial.bundle.getString("cambioApodo"));
                labelP.setText(pgInicial.bundle.getString("cambioContraseña"));
                labelPP.setText(pgInicial.bundle.getString("cambioPerfil"));
                botonNickname.setText(pgInicial.bundle.getString("cambiarApodo"));
                botonP.setText(pgInicial.bundle.getString("ingreseContra"));
                botonPP.setText(pgInicial.bundle.getString("cambiarFP"));
                salir.setText(pgInicial.bundle.getString("volver"));
                
                inglesCB.setEnabled(false);
                españolCB.setEnabled(true);
                
                btnIniciarPartida.setAlignmentX(SwingConstants.LEFT);
                btnPerfilUsuario .setAlignmentX(SwingConstants.LEFT);
                btnPreferencias  .setAlignmentX(SwingConstants.LEFT);
                btnEstadisticas  .setAlignmentX(SwingConstants.LEFT);
                btnRanking       .setAlignmentX(SwingConstants.LEFT);
                btnSalir         .setAlignmentX(SwingConstants.LEFT);
                
                idiomaTitulo.setBounds(20, 80 , (metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+2, 50);
                españolCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+10-(3*18), 80);
                inglesCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+28, 80);
                juego.repaint();
                juego.validate();
            }
        });
        
        salir.addActionListener((ActionEvent e) -> {
            titulo(titulo           , 400, 50 , (metrics.stringWidth((pgInicial.bundle.getString("menuPrincipal")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("menuPrincipal"));
            boton(btnIniciarPartida, 380, 110, (metrics.stringWidth(pgInicial.bundle.getString("jugar")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("jugar"),250);
            boton(btnPerfilUsuario , 380, 170, (metrics.stringWidth(pgInicial.bundle.getString("perfilUsuario")))+34  , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("perfilUsuario"), 250);
            boton(btnPreferencias  , 380, 230, (metrics.stringWidth(pgInicial.bundle.getString("configuracion")))+35  , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("configuracion"), 250);
            boton(btnEstadisticas  , 380, 290, (metrics.stringWidth(pgInicial.bundle.getString("estadisticas")))+35   , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("estadisticas"), 250);
            boton(btnRanking       , 380, 350, (metrics.stringWidth(pgInicial.bundle.getString("ranking")))+35        , 50, false, false, "Dialog", 28, pgInicial.bundle.getString("ranking"), 250);
            boton(btnSalir         , 380, 410, (metrics.stringWidth(pgInicial.bundle.getString("salirSesion")))+35    , 50, false, false, "Dialog", 28,pgInicial.bundle.getString("salirSesion"), 250);
            
            frame.add(titulo, 0);
            frame.add(btnIniciarPartida, 1);
            frame.add(btnPerfilUsuario, 2);
            frame.add(btnPreferencias, 3);
            frame.add(btnEstadisticas, 4);
            frame.add(btnRanking, 5);
            frame.add(btnSalir, 6);
            frame.add(fondo, 7);
            
            frame.repaint();
            frame.revalidate();
        });
        
    }

    private void estadisticas() {
        JButton salir = new JButton();
        
        boton(salir, 140, 180, 120, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("volver"), 250);
        
        frame.add(salir, 0);
        frame.add(fondo, 1);
        frame.repaint();
        frame.revalidate();
        
        salir.addActionListener((ActionEvent e) -> {
            frame.add(titulo,0);
            frame.add(btnIniciarPartida,1);
            frame.add(btnPerfilUsuario,2);
            frame.add(btnPreferencias,3);
            frame.add(btnEstadisticas,4);
            frame.add(btnRanking,5);
            frame.add(btnSalir,6);
            frame.add(fondo,7);
            
            frame.repaint();
            frame.revalidate();
        });
    }
    
    private void ranking() {
        JButton salir = new JButton();
        
        boton(salir, 140, 180, 120, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("volver"), 250);
        
        frame.add(salir, 0);
        frame.add(fondo, 1);
        frame.repaint();
        frame.revalidate();
        
        salir.addActionListener((ActionEvent e) -> {
            frame.add(titulo,0);
            frame.add(btnIniciarPartida,1);
            frame.add(btnPerfilUsuario,2);
            frame.add(btnPreferencias,3);
            frame.add(btnEstadisticas,4);
            frame.add(btnRanking,5);
            frame.add(btnSalir,6);
            frame.add(fondo,7);
            
            frame.repaint();
            frame.revalidate();
        });
    }
    
    public void contPanelConfPerfil(JFrame frame, JTabbedPane tabbedPane, JPanel perfil, JButton salir){
        
        System.out.println("Initial rutaAvatar: " + rutaAvatar);
        perfil.setOpaque(false);
        perfil.setLayout(null);
        
         botonPP          = new JButton();
         botonP           = new JButton();
         botonNickname    = new JButton();
         labelP            = new JLabel();
         labelPP           = new JLabel();
         labelNickname     = new JLabel();
        JLabel imgPerfil         = new JLabel();
        JTextField fieldNickname = new JTextField();
        JPasswordField fieldP    = new JPasswordField();
        
        fotoPerfilImg = new ImageIcon(rutaAvatar);
        System.out.println("Ruta del avatar: " + rutaAvatar);
        fotoPerfilImg = scaleImage(fotoPerfilImg, 100, 100);
        
        //dimencion de panel 650 400
        //nickname
        titulo(labelNickname, 20, 20, (metrics.stringWidth((pgInicial.bundle.getString("cambioApodo")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("cambioApodo"));
        textfield(fieldNickname, 20, 80, 250, 30, "Dialog", 20);
        boton(botonNickname, 5, 110, (metrics.stringWidth(pgInicial.bundle.getString("cambiarApodo")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("cambiarApodo"), 250);
        //password
        titulo(labelP, 20, 200, (metrics.stringWidth((pgInicial.bundle.getString("cambioContraseña")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("cambioContraseña"));
        passwordfield(fieldP, 20, 260, 250, 30, "Dialog", 20);
        boton(botonP, 5, 282, (metrics.stringWidth(pgInicial.bundle.getString("ingreseContra")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("ingreseContra"), 250);
        //Profile picture
        titulo(labelPP, 300, 50, (metrics.stringWidth((pgInicial.bundle.getString("cambioPerfil")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("cambioPerfil"));
        fondo(imgPerfil, 0, 0, fotoPerfilImg, frame);
        imgPerfil.setSize(100, 100);
        imgPerfil.setLocation(400, 120);
        boton(botonPP, 280, 230, (metrics.stringWidth(pgInicial.bundle.getString("cambiarFP")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("cambiarFP"), 250);
        
        perfil.add(imgPerfil);
        perfil.add(botonPP);
        perfil.add(botonP);
        perfil.add(botonNickname);
        perfil.add(labelP);
        perfil.add(labelPP);
        perfil.add(labelNickname);
        perfil.add(fieldNickname);
        perfil.add(fieldP);

        botonPP.addActionListener((ActionEvent e) -> {
            
            System.out.println("After selection: " + rutaAvatar);
            System.out.println("After guardarImagen: " + rutaAvatar);
            
            rutaAvatar = pgInicial.mAvatar.seleccionarImagen(pgInicial.logUser.getAvatar());
            System.out.println(pgInicial.logUser.getAvatar());
            if(!rutaAvatar.equals(pgInicial.logUser.getAvatar())){
                rutaAvatar=pgInicial.mUser.guardarImagen(rutaAvatar, pgInicial.logUser.getNombreCompleto());
            }
            fotoPerfilImg= new ImageIcon(rutaAvatar);
            fotoPerfilImg = scaleImage(fotoPerfilImg, 100, 100);
            imgPerfil.setIcon(fotoPerfilImg);
            lblPerfil.setIcon(fotoPerfilImg);
            pgInicial.logUser.setAvatar(rutaAvatar);
        });
        botonP.addActionListener((ActionEvent e) -> {
            String password = new String(fieldP.getPassword()).trim();
            if(contadorCambiarP==0){
                if(password.equals(pgInicial.logUser.getContraseña())){
                    JOptionPane.showMessageDialog(null, pgInicial.locale.toString().equals("es") ? 
                    "Ingreso la contraseña correctamente!\nAhora ingrese la nueva contraseña." : 
                    "Password entered correctly!\nNow enter the new password.");
                    salir.setEnabled(false);
                    botonNickname.setEnabled(false);
                    botonPP.setEnabled(false);
                    tabbedPane.setEnabled(false);
                    contadorCambiarP++;
                    fieldP.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, pgInicial.locale.toString().equals("es") ? 
                    "Ingrese la contraseña correcta!" : 
                    "Enter the correct password!");
                    fieldP.setText("");
                }
            }
            else if(contadorCambiarP==1){
                if(!password.equals(pgInicial.logUser.getContraseña()) && !password.equals("")){
                    if(pgInicial.mUser.cambiarContra(new File(("Usuarios/"+pgInicial.logUser.getNombreCompleto()+"/datos.dat")), password)){   
                        pgInicial.logUser.setContraseña(password);
                        salir.setEnabled(true);
                        botonNickname.setEnabled(true);
                        botonPP.setEnabled(true);
                        tabbedPane.setEnabled(true);
                        contadorCambiarP--;
                        fieldP.setText("");
                    }
                }
                else if(password.equals(pgInicial.logUser.getContraseña())){
                    JOptionPane.showMessageDialog(null, pgInicial.locale.toString().equals("es") ? 
                    "Ingreso la misma contraseña! ingrese una nueva contraseña." : 
                    "You entered the same password! Enter a new password.");
                    fieldP.setText("");
                }else if(password.equals("")){
                    JOptionPane.showMessageDialog(null, pgInicial.locale.toString().equals("es") ? 
                    "Ingrese una contraseña valida!" : 
                    "Enter a valid password!");
                    fieldP.setText("");
                }
            }
        });
        botonNickname.addActionListener((ActionEvent e) -> {
            String nombreN = fieldNickname.getText();
            if(!nombreN.equals(pgInicial.logUser.getNombreUser()) && !nombreN.equals("")){
                pgInicial.logUser.setNombreUser(nombreN);
                if(pgInicial.mUser.cambiarNombre(new File(("Usuarios/"+pgInicial.logUser.getNombreCompleto()+"/datos.dat")),nombreN)){
                    JOptionPane.showMessageDialog(null, pgInicial.locale.toString().equals("es") ? 
                    "Cambio de apodo exitoso!" : 
                    "Nickname change successful!");
                    fieldNickname.setText("");
                }
                
            }else if(nombreN.equals("")){
                JOptionPane.showMessageDialog(null, pgInicial.locale.toString().equals("es") ? 
                "Ingrese un nombre valido." : 
                "Enter a valid name.");
            }else if(nombreN.equals(pgInicial.logUser.getNombreUser())){
                JOptionPane.showMessageDialog(null, pgInicial.locale.toString().equals("es") ? 
                "Ingrese un nombre diferente." : 
                "Enter a different name.");
                fieldNickname.setText("");
            }
        });
        
        boton(salir, 140, 180, 120, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("volver"), 250);
        frame.add(tabbedPane);
        frame.add(salir);
        frame.add(fondo);
        frame.repaint();
        frame.revalidate();
        
        
    }
}