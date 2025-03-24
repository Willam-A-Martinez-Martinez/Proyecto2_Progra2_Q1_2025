/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import GUI.PgInicial;
import Users.Estadisticas;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import prueba_sprite.MovimientoTeclado;
import prueba_sprite.gamepanel;
import prueba_sprite.menu;

/**
 *
 * @author 50488
 */
public class jugador extends entidades {

    gamepanel gp;
    MovimientoTeclado mt;
    public final int screenY;
    public final int screenX;
    
    PgInicial pgInicial;
    Estadisticas estadisticas;

    public jugador(gamepanel gp, MovimientoTeclado mt, PgInicial pgInicial) {
        this.estadisticas=estadisticas;
        this.pgInicial=pgInicial;
        this.gp = gp;
        this.mt = mt;
        screenX = gp.pantallaancho / 2 - (gp.tamanopersonaje / 2);
        screenY = gp.pantallalargo / 2 - (gp.tamanopersonaje / 2);
        valoresjugador();
        getPlayerImagen();
        direccion = "down";
        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 13;
        solidArea.width = 25;
        solidArea.height = 25;
    }

    public void getPlayerImagen() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/folder/17.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/folder/18.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/folder/19.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/folder/20.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/folder/21.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/folder/22.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/folder/0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/folder/1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/folder/2.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/folder/3.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/folder/4.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/folder/4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/folder/5.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/folder/6.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/folder/7.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/folder/8.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/folder/9.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/folder/10.png"));
            de1 = ImageIO.read(getClass().getResourceAsStream("/folder/11.png"));
            de2 = ImageIO.read(getClass().getResourceAsStream("/folder/12.png"));
            de3 = ImageIO.read(getClass().getResourceAsStream("/folder/13.png"));
            de4 = ImageIO.read(getClass().getResourceAsStream("/folder/14.png"));
            de5 = ImageIO.read(getClass().getResourceAsStream("/folder/15.png"));
            de6 = ImageIO.read(getClass().getResourceAsStream("/folder/16.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    primer nivel
     x = 335;
     y = 240;
    segundo nivel 
       x = 288;
       y = 95;
    tercer nivel 
     x = 335;
     y = 190;
    cuarto nivel 
       x = 240;
        y = 145;
    quinto nivel
      x = 145;
        y = 240;
     */
    public void valoresjugador() {
        if (menu.getInstance().getnivel() == 1) {
            worldx = gp.tamanopersonaje * 24;
            worldy = gp.tamanopersonaje * 24;
        } else if (menu.getInstance().getnivel() == 2) {
            worldx = gp.tamanopersonaje * 23;
            worldy = gp.tamanopersonaje * 26;
        } else if (menu.getInstance().getnivel() == 3) {
            worldx = gp.tamanopersonaje * 23;
            worldy = gp.tamanopersonaje * 21;
        } else if (menu.getInstance().getnivel() == 4) {
            worldx = gp.tamanopersonaje * 24;
            worldy = gp.tamanopersonaje * 23;
        } else if (menu.getInstance().getnivel() == 5) {
            worldx = gp.tamanopersonaje * 22;
            worldy = gp.tamanopersonaje * 22;

        }
        speed = 2;
    }

    public void update() {
        if (mt.ARRIBA || mt.ABAJO || mt.DERECHA || mt.IZQUIERDA) {
            if (mt.ARRIBA == true) {
                direccion = "up";

            } else if (mt.ABAJO == true) {
                direccion = "down";

            } else if (mt.IZQUIERDA == true) {
                direccion = "left";

            } else if (mt.DERECHA == true) {
                direccion = "de";

            }
            collisionON = false;
            if (gp.colision != null) {
                gp.colision.checktile(this, estadisticas);
            }

            //if collision is false, player can move 
            if (collisionON == false) {
                switch (direccion) {
                    case "up":
                        worldy -= speed;
                        break;
                    case "down":
                        worldy += speed;
                        break;
                    case "left":
                        worldx -= speed;
                        break;
                    case "de":
                        worldx += speed;
                        break;

                }
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spritenum == 1) {
                    spritenum = 2;
                } else if (spritenum == 2) {
                    spritenum = 3;
                } else if (spritenum == 3) {
                    spritenum = 4;
                } else if (spritenum == 4) {
                    spritenum = 5;

                } else if (spritenum == 5) {
                    spritenum = 6;
                } else if (spritenum == 6) {
                    spritenum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void dibujar(Graphics2D g2) {
        BufferedImage imagen = null;

        switch (direccion) {
            case "up":
                if (spritenum == 1) {
                    imagen = up1;
                }
                if (spritenum == 2) {
                    imagen = up2;
                }
                if (spritenum == 3) {
                    imagen = up3;
                }
                if (spritenum == 4) {
                    imagen = up4;
                }
                if (spritenum == 5) {
                    imagen = up5;
                }
                if (spritenum == 6) {
                    imagen = up6;
                }

                break;
            case "down":
                if (spritenum == 1) {
                    imagen = down1;
                }
                if (spritenum == 2) {
                    imagen = down2;
                }
                if (spritenum == 3) {
                    imagen = down3;
                }
                if (spritenum == 4) {
                    imagen = down4;
                }
                if (spritenum == 5) {
                    imagen = down5;
                }
                if (spritenum == 6) {
                    imagen = down6;
                }

                break;

            case "left":
                if (spritenum == 1) {
                    imagen = left1;
                }
                if (spritenum == 2) {
                    imagen = left2;
                }
                if (spritenum == 3) {
                    imagen = left3;
                }
                if (spritenum == 4) {
                    imagen = left4;
                }
                if (spritenum == 5) {
                    imagen = left5;
                }
                if (spritenum == 6) {
                    imagen = left6;
                }
                break;
            case "de":
                if (spritenum == 1) {
                    imagen = de1;
                }
                if (spritenum == 2) {
                    imagen = de2;
                }
                if (spritenum == 3) {
                    imagen = de3;
                }
                if (spritenum == 4) {
                    imagen = de4;
                }
                if (spritenum == 5) {
                    imagen = de5;
                }
                if (spritenum == 6) {
                    imagen = de6;
                }
                break;
        }
        g2.drawImage(imagen, screenX, screenY, gp.tamanopersonaje, gp.tamanopersonaje, null);
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tamanopersonaje, gp.tamanopersonaje);

    }

}
