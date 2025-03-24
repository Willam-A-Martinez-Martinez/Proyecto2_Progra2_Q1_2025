/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_sprite;

import Users.Estadisticas;
import entidad.entidades;

/**
 *
 * @author 50488
 */
public class CollisionCheker {

    gamepanel gp;
    int tileNum1, tileNum2;

    public CollisionCheker(gamepanel gp) {
        this.gp = gp;
    }

    public void checktile(entidades entity, Estadisticas estadisticas) {
        int entityLeftWorldX = entity.worldx + entity.solidArea.x;
        int entityRightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldy + entity.solidArea.y;
        int entityButtonWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tamanopersonaje;
        int entityRightCol = entityRightWorldX / gp.tamanopersonaje;
        int entityTopRow = entityTopWorldY / gp.tamanopersonaje;
        int entityButtonRow = entityButtonWorldY / gp.tamanopersonaje;

        int cajaNuevaCol = 0, cajaNuevaRow = 0; // Variables para la nueva posición de la caja
        boolean empujarCaja = false;

        switch (entity.direccion) {

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tamanopersonaje;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;
                }
                cajaNuevaRow = entityTopRow - 1;
                cajaNuevaCol = entityLeftCol;
                break;

            case "down":
                entityButtonRow = (entityButtonWorldY + entity.speed) / gp.tamanopersonaje;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityButtonRow];
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityButtonRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;
                }
                cajaNuevaRow = entityButtonRow + 1;
                cajaNuevaCol = entityLeftCol;

                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tamanopersonaje;
                tileNum1 = gp.tileM.maptilenum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.maptilenum[entityLeftCol][entityButtonRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;
                }
                cajaNuevaCol = entityLeftCol - 1;
                cajaNuevaRow = entityTopRow;

                break;

            case "de": // Corrige la dirección 'de' a 'derecha' o 'right'
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tamanopersonaje;
                tileNum1 = gp.tileM.maptilenum[entityRightCol][entityTopRow]; // Verificar la colisión en la fila superior
                tileNum2 = gp.tileM.maptilenum[entityRightCol][entityButtonRow]; // Verificar la colisión en la fila inferior
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;
                }
                cajaNuevaCol = entityRightCol + 1;
                cajaNuevaRow = entityTopRow; // Asegúrate de que el valor de fila sea correcto en la dirección derecha
                break;

        }
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            entity.collisionON = true;

            if (tileNum1 == 3 || tileNum2 == 3) { // Si es una caja
                entity.collisionType = "Caja";
                empujarCaja = true;
//        System.out.println("⚠️ Colisión con una caja en [" + entityLeftCol + ", " + entityTopRow + "]");
            } else if (tileNum1 == 1 || tileNum2 == 1) {
                entity.collisionType = "Muro";
//        System.out.println("🚧 Colisión con un muro.");
            } else {
                entity.collisionType = "Otro";
            }
        }
        if (tileNum1 == 3 || tileNum2 == 3) { // Si hay una caja
            int cajaCol = entityLeftCol;
            int cajaRow = entityTopRow;

            // Ajustar la posición correcta de la caja según la dirección
            switch (entity.direccion) {
                case "left":
                    cajaCol = entityLeftCol;  // Caja está a la izquierda
                    break;
                case "de":
                    cajaCol = entityRightCol; // Caja está a la derecha
                    break;
                case "up":
                    cajaRow = entityTopRow;   // Caja está arriba
                    break;
                case "down":
                    cajaRow = entityButtonRow; // Caja está abajo
                    break;
            }

            int nuevaCol = cajaCol;
            int nuevaRow = cajaRow;

            // Mover la caja en la dirección correcta
            switch (entity.direccion) {
                case "up":
                    nuevaRow--; // Mover la caja hacia arriba
                    break;
                case "down":
                    nuevaRow++; // Mover la caja hacia abajo
                    break;
                case "left":
                    nuevaCol--; // Mover la caja a la izquierda
                    break;
                case "de":
                    nuevaCol++; // Mover la caja a la derecha
                    break;
            }

            gp.tileM.moverCaja(nuevaCol, nuevaRow, cajaCol, cajaRow, estadisticas);
        }

    }
}
