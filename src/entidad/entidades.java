/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author 50488
 */
public class entidades {
    public int worldx, worldy;
    public int speed;
    
    public BufferedImage up1,up2,up3,up4,up5,up6,down1,down2,down3,down4,down5, down6,left1,left2,left3,left4,left5,left6,de1,de2,de3,de4,de5,de6;
    public String direccion;
    public int spriteCounter=0;
    public int spritenum=1;
    public Rectangle solidArea;
    public boolean collisionON= false;
    public String collisionType = "";
}
