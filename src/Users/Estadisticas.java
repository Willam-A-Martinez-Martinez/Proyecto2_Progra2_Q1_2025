package Users;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
        // Ensure we don’t double count already accumulated values
        if (nuevasStats == null) return;

        this.totalGamesPlayed = Math.max(this.totalGamesPlayed, nuevasStats.totalGamesPlayed);
        this.totalLevelsCompleted = Math.max(this.totalLevelsCompleted, nuevasStats.totalLevelsCompleted);

        // Add only new game durations that are not already counted
        for (Duration dur : nuevasStats.gameDurations) {
            if (!this.gameDurations.contains(dur)) { 
                this.gameDurations.add(dur);
            }
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

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return "Estadísticas:\n" +
               "Juegos jugados: " + totalGamesPlayed + "\n" +
               "Niveles completados: " + totalLevelsCompleted + "\n" +
               "Tiempo total de juego: " + formatDuration(getTotalPlayTime()) + "\n" +
               "Promedio por nivel: " + getAverageTimePerLevel() + "\n" +
               "Promedio por partida: " + getAverageGameDuration() + "\n";
    }
}
