/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

/**
 *
 * @author DELL
 */
public class Movement {
    private int totalMovements;

    public Movement() {
        this.totalMovements = 0;
    }

    // Increase movements
    public void increaseMovement() {
        totalMovements++;
    }

    // Getters
    public  int getTotalMovements() {
        return totalMovements;
    }

    // Set movements from file data
    public void setTotalMovements(int total) {
        this.totalMovements = total;
    }
}

