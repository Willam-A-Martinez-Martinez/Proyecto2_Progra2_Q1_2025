package GUI;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private JLabel fondo = new JLabel();
    private JLabel titulo= new JLabel();
    private JButton btnIniciarPartida= new JButton();
    private JButton btnPerfilUsuario= new JButton();
    private JButton btnEstadisticas= new JButton();
    private JButton btnRanking= new JButton();
    private JButton btnPreferencias= new JButton();
    private JButton btnSalir= new JButton();

    ImageIcon backgroundI= new ImageIcon("src/Assets/FondoRegistro-Inicio Sesion.gif");
    
    public PgInicial pgInicial;
    
    public PgPrincipal(PgInicial pgInicial) {
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
            pgInicial.logUser = null;
            pgInicial.frame.setVisible(true);
            frame.dispose();
        });
    }

    

    private void perfilUsuario() {
        JLabel tituloPerfil = new JLabel();
        JLabel lblPerfil       = new JLabel();
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
        JCheckBox muteCB        = new JCheckBox();
        JCheckBox españolCB     = new JCheckBox();
        JCheckBox inglesCB      = new JCheckBox();
        JLabel    volumenTitulo = new JLabel();
        JLabel    idiomaTitulo  = new JLabel();
        JLabel    muteL         = new JLabel();
        JSlider   volumenM      = new JSlider(-40,6);
        
        if(pgInicial.music.mute==true){
            muteCB.setSelected(true);
        }
        
        titulo(volumenTitulo, 20, 20 , (metrics.stringWidth(pgInicial.bundle.getString("volumen")))+2, 50, "Dialog", 28, 250, pgInicial.bundle.getString("volumen"));
        titulo(idiomaTitulo, 20, 80 , (metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+2, 50, "Dialog", 28, 250, pgInicial.bundle.getString("idiomaPgInicial"));
        titulo(muteL, (18*8)+200, 20 , (metrics.stringWidth(pgInicial.bundle.getString("silenciar")))+2, 50, "Dialog", 28, 250, pgInicial.bundle.getString("silenciar"));
        System.out.println((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+2);
        muteCB.setBounds((18*8)+(18*10)+200, 20, 50, 50);
        muteCB.setContentAreaFilled(false);
        
        españolCB.setBounds(130+((metrics.stringWidth(pgInicial.bundle.getString("volumen")))-(5*18)), 80, 50, 50);
        inglesCB.setBounds((metrics.stringWidth(pgInicial.bundle.getString("volumen")))+110, 80, 50, 50);
        españolCB.setContentAreaFilled(false);
        inglesCB.setContentAreaFilled(false);
        
        
        
        volumenM.setBounds((18*7), 35, 200, 25);
        volumenM.setLayout(null);
        volumenM.setOpaque(false);
        
        juego.add(muteL);
        juego.add(españolCB);
        juego.add(inglesCB);
        juego.add(muteCB);
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

            muteL.setText(pgInicial.bundle.getString("silenciar"));
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
            muteCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("silenciar")))+metrics.stringWidth(pgInicial.bundle.getString("volumen"))+215, 20);
            juego.repaint();
            juego.validate();
        }else if(pgInicial.locale.toString().equals("es")){
            inglesCB.setSelected(false);
            españolCB.setSelected(true);
            pgInicial.bundle = ResourceBundle.getBundle("resources.mensajes", pgInicial.locale);
            pgInicial.actualizarIdioma(new Locale("es"));

            volumenTitulo.setText(pgInicial.bundle.getString("volumen"));
            idiomaTitulo.setText(pgInicial.bundle.getString("idiomaPgInicial"));
            muteL.setText(pgInicial.bundle.getString("silenciar"));

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
            muteCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("silenciar")))+metrics.stringWidth(pgInicial.bundle.getString("volumen"))+200, 20);
            juego.repaint();
            juego.validate();
        }
        
        volumenM.setValue(Math.round(pgInicial.music.volumen1));
        volumenM.addChangeListener((ChangeEvent e) -> {
            if(pgInicial.music.mute==false){
                pgInicial.music.volumen1 = volumenM.getValue();
                pgInicial.music.fc.setValue(pgInicial.music.volumen1);
                System.out.println("Valor de slider: "+volumenM.getValue());
            }
        });
        
        muteCB.addActionListener((ActionEvent e) -> {
            pgInicial.music.volumeMute();
        });
        
        
        
        españolCB.addChangeListener((ChangeEvent e) -> {
            if(españolCB.isSelected()){
                inglesCB.setSelected(false);
                pgInicial.locale = new Locale("es");
                pgInicial.bundle = ResourceBundle.getBundle("resources.mensajes", pgInicial.locale);
                pgInicial.actualizarIdioma(new Locale("es"));

                volumenTitulo.setText(pgInicial.bundle.getString("volumen"));
                idiomaTitulo.setText(pgInicial.bundle.getString("idiomaPgInicial"));
                muteL.setText(pgInicial.bundle.getString("silenciar"));

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
                
                idiomaTitulo.setBounds(20, 80 , (metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+5, 50);
                muteL.setBounds((18*8)+200, 20 , (metrics.stringWidth(pgInicial.bundle.getString("silenciar")))+5, 50);
                volumenTitulo.setBounds(20, 20 , (metrics.stringWidth(pgInicial.bundle.getString("volumen")))+2, 50);
                españolCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+10-(3*18), 80);
                inglesCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("idiomaPgInicial")))+28, 80);
                muteCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("silenciar")))+metrics.stringWidth(pgInicial.bundle.getString("volumen"))+200, 20);
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

                muteL.setText(pgInicial.bundle.getString("silenciar"));
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
                muteCB.setLocation((metrics.stringWidth(pgInicial.bundle.getString("silenciar")))+metrics.stringWidth(pgInicial.bundle.getString("volumen"))+215, 20);
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
        
        
        perfil.setOpaque(false);
        perfil.setLayout(null);
        
        JButton botonPP = new JButton();
        JButton botonP = new JButton();
        JButton botonNickname = new JButton();
        JLabel labelP = new JLabel();
        JLabel labelPP = new JLabel();
        JLabel labelNickname = new JLabel();
        JTextField fieldNickname = new JTextField();
        JPasswordField fieldP = new JPasswordField();
        //dimencion de panel 650 400
        boton(botonPP, 245, 110, (metrics.stringWidth(pgInicial.bundle.getString("cambiarFP")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("cambiarFP"),250);
        boton(botonP, 50, 110, (metrics.stringWidth(pgInicial.bundle.getString("jugar")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("ingreseContra"),250);
        boton(botonNickname, 50, 110, (metrics.stringWidth(pgInicial.bundle.getString("jugar")))+40, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("cambiarApodo"),250);
        titulo(labelP, 60, 50 , (metrics.stringWidth((pgInicial.bundle.getString("menuPrincipal")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("cambioContraseña"));
        titulo(labelPP, 60, 50 , (metrics.stringWidth((pgInicial.bundle.getString("menuPrincipal")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("cambioPerfil"));
        titulo(labelNickname, 60, 50 , (metrics.stringWidth((pgInicial.bundle.getString("menuPrincipal")))), 50, "Dialog", 28, 250, pgInicial.bundle.getString("cambioApodo"));
        textfield(fieldNickname, 100, 175, 170, 30, "Dialog", 20);
        passwordfield(fieldP, 100, 275, 170, 30, "Dialog", 20);
        
        perfil.add(botonPP);
        perfil.add(botonP);
        perfil.add(botonNickname);
        perfil.add(labelP);
        perfil.add(labelPP);
        perfil.add(labelNickname);
        perfil.add(fieldNickname);
        perfil.add(fieldP);

        boton(salir, 140, 180, 120, 50, false, false, "Dialog", 28, pgInicial.bundle.getString("volver"), 250);
        frame.add(tabbedPane);
        frame.add(salir);
        frame.add(fondo);
        frame.repaint();
        frame.revalidate();
        
        
    }
}