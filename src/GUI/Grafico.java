package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

    
public class Grafico {
    ImageIcon logoVentana = new ImageIcon("src/UI_Images/CajaCafeOscuro.png");
    Font pixelMplus;
    FontMetrics metrics;
    private int r=76,g=70,b=66;
    
    public Grafico(){
        try{
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("src/PixelMplus-20130602/PixelMplus12-Bold.ttf"));
            pixelMplus = pixelMplus.deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelMplus);
        }catch(IOException | FontFormatException e){
            
        }
    }
    
    
    
    public ImageIcon scaleImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
    
    public void confFrame(JFrame frame, String title,int width,int heigth, String tipoMusic){
        
        frame.setIconImage(logoVentana.getImage());
        frame.setTitle(title);
        frame.setSize(width, heigth);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void fondo(JLabel a, int widthExtra, int heightExtra, ImageIcon icon, JFrame frame){
        a.setBounds(0, 0, (frame.getWidth()+widthExtra), (frame.getHeight()+heightExtra));
        a.setIcon(icon);
    }
    
    public void postBackground(JLabel a, int x, int y, int width, int heigth, int r , int g, int b, int opaque, boolean isOpaque){
        a.setBounds(x, y, width, heigth);
        a.setBackground(new Color(r, g, b, opaque));
        a.setOpaque(isOpaque);
    }
    
    public void titulo(JLabel a, int x, int y, int width, int heigth, String font, 
    int fSize, int opaque, String text){
        a.setBounds(x, y, width, heigth);
        a.setFont(pixelMplus);
        a.setForeground(new Color(r, g, b, opaque));
        a.setText(text);
    }
    
    public void textfield(JTextField a, int x, int y, int width, int heigth, String font, int fSize){
        a.setBounds(x, y, width, heigth);
        a.setText("");
        a.setFont(pixelMplus);
    }
    
    public void passwordfield(JPasswordField a, int x, int y, int width, int heigth, String font, int fSize){
        a.setBounds(x, y, width, heigth);
        a.setText("");
        a.setFont(pixelMplus);
    }
    
    public void boton(JButton a,int x, int y, int width, int heigth, boolean setBorderPainted, boolean setContentAreaFilled, String font, int size, String texto, int opaque){
        a.setBounds(x, y, width, heigth);
        a.setBorderPainted(setBorderPainted);
        a.setContentAreaFilled(setContentAreaFilled);
        a.setFont(pixelMplus);
        a.setText(texto);
        a.setForeground(new Color(r, g, b, opaque));
        
        Image img = logoVentana.getImage();
        Image scaledImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon imgS =  new ImageIcon(scaledImg);
        
        a.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                a.setForeground(new Color(250, 250, 250, opaque));
                a.setBounds(x, y, (width+29), heigth);
                a.setIcon(imgS);
            }
        });
        
        a.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                a.setForeground(new Color(r, g, b, opaque));
                a.setBounds(x, y, width, heigth);
                a.setIcon(null);
            }
        });
    }
    
    public void toggleButton(JToggleButton a,int x, int y, int width, int heigth, boolean setBorderPainted, boolean setContentAreaFilled, String font, int size, String texto, int opaque){
        a.setBounds(x, y, width, heigth);
        a.setBorderPainted(setBorderPainted);
        a.setContentAreaFilled(setContentAreaFilled);
        a.setFont(pixelMplus);
        a.setText(texto);
        a.setForeground(new Color(r, g, b, opaque));
        
    }
    
    public void jPanel(JPanel a, int x, int y, int width, int heigth, int r , int g, int b, int opaque){
        a.setBounds(x, y, width, heigth);
        a.setBackground(new Color(r, g, b, opaque));
    }
}
