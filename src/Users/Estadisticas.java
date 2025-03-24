/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Estadisticas implements Serializable {
    private static final long serialVersionUID = 1L;

    private int totalGamesPlayed;
    private int totalLevelsCompleted;
    private List<Duration> gameDurations;
    private long startTime;

    public Estadisticas() {
        this.totalGamesPlayed = 0;
        this.totalLevelsCompleted = 0;
        this.gameDurations = new ArrayList<>();
    }

    public void startGame() {
        startTime = System.currentTimeMillis(); // Store start time in milliseconds
        System.out.println("Juego iniciado...");
    }


    public void endGame() {
        long endTime = System.currentTimeMillis(); // Capture end time
        long durationInSeconds = (endTime - startTime) / 1000; // Convert to seconds

        if (durationInSeconds < 1) {
            durationInSeconds = 1; // Avoid 0s durations
        }

        Duration gameDuration = Duration.ofSeconds(durationInSeconds);
        gameDurations.add(gameDuration);
        totalGamesPlayed++;

        System.out.println("Juego terminado. Duración: " + formatDuration(gameDuration));
    }

    public void completeLevel() {
        if (totalLevelsCompleted < 5) {
            totalLevelsCompleted++;
        }
        System.out.println("Nivel completado. Total: " + totalLevelsCompleted);
    }

    // Nuevo método para acumular estadísticas previas
    public void acumularEstadisticas(Estadisticas nuevasStats) {
        if (nuevasStats == null) return;

        // Add the counts instead of taking the maximum
        this.totalGamesPlayed = nuevasStats.totalGamesPlayed;
        this.totalLevelsCompleted = Math.max(this.totalLevelsCompleted, nuevasStats.totalLevelsCompleted);

        // Add only new game durations
        for (Duration dur : nuevasStats.gameDurations) {
            this.gameDurations.add(dur);
        }
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public int getTotalLevelsCompleted() {
        return totalLevelsCompleted;
    }

    public Duration getTotalPlayTime() {
        return gameDurations.stream().reduce(Duration.ZERO, Duration::plus);
    }

    public String getAverageTimePerLevel() {
        if (totalLevelsCompleted == 0) return "00:00:00";
        long totalSeconds = getTotalPlayTime().getSeconds();
        return formatDuration(Duration.ofSeconds(totalSeconds / totalLevelsCompleted));
    }

    public String getAverageGameDuration() {
        if (totalGamesPlayed == 0) return "00:00:00";
        long totalSeconds = getTotalPlayTime().getSeconds();
        return formatDuration(Duration.ofSeconds(totalSeconds / totalGamesPlayed));
    }
    
    public List<Duration> getGameDurations() {
        return new ArrayList<>(gameDurations);
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public void setTotalLevelsCompleted(int totalLevelsCompleted) {
        this.totalLevelsCompleted = totalLevelsCompleted;
    }

    public void addGameDuration(Duration duration) {
        this.gameDurations.add(duration);
    }
    

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public String toString(String idioma) {
        return (idioma.equals("es"))?"Estadísticas:\n" +
               "Juegos jugados: " + totalGamesPlayed + "\n" +
               "Niveles completados: " + totalLevelsCompleted + "\n" +
               "Tiempo total de juego: " + formatDuration(getTotalPlayTime()) + "\n" +
               "Promedio por nivel: " + getAverageTimePerLevel() + "\n" +
               "Promedio por partida: " + getAverageGameDuration() + "\n":"Statistics:\n" +
                "Games played: " + totalGamesPlayed + "\n" +
                "Levels completed: " + totalLevelsCompleted + "\n" +
                "Total play time: " + formatDuration(getTotalPlayTime()) + "\n" +
                "Average per level: " + getAverageTimePerLevel() + "\n" +
                "Average per game: " + getAverageGameDuration() + "\n";
        }
}
