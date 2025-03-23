package Users;

import java.io.Serializable;
import java.util.List;

public class Historial implements Serializable {
    private static final long serialVersionUID = 1L;

    private int nivel;
    private List<String> movimientos;
    private long tiempoJugado;

    public Historial(int nivel, List<String> movimientos, long tiempoJugado) {
        this.nivel = nivel;
        this.movimientos = movimientos;
        this.tiempoJugado = tiempoJugado;
    }

    public int getNivel() {
        return nivel;
    }

    public List<String> getMovimientos() {
        return movimientos;
    }

    public int getTotalMovimientos() {
        return movimientos.size();
    }

    public long getTiempoJugado() {
        return tiempoJugado;
    }

    public String obtenerTiempoFormateado() {
        long horas = tiempoJugado / 3600;
        long minutos = (tiempoJugado % 3600) / 60;
        long segundos = tiempoJugado % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    private String obtenerMovimientosFormateados() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < movimientos.size(); i++) {
            sb.append(movimientos.get(i));
            if ((i + 1) % 8 == 0) {
                sb.append("\n");
            } else {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Nivel: " + nivel +
               "\nMovimientos totales: " + getTotalMovimientos() +
               "\nMovimientos: \n" + obtenerMovimientosFormateados() +
               "\nTiempo: " + obtenerTiempoFormateado() +
               "\n-------------------------";
    }
}
