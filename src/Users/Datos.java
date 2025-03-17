package Users;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;

public class Datos implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreUser, nombreCompleto, contraseña, avatar;
    private Calendar fechaRegistro, ultimaSesion;
    private Duration tiempoJugado;
    private transient Instant juegoEmpieza;
    
    public Datos(String NombreUser, String NombreCompleto, String contraseña, String avatar) {
        this.nombreUser = NombreUser;
        this.nombreCompleto = NombreCompleto;
        this.contraseña = contraseña;
        this.avatar=avatar;
        this.fechaRegistro = Calendar.getInstance();
        this.ultimaSesion = Calendar.getInstance();
        this.tiempoJugado = Duration.ZERO;
    }

    public void empiezaP() {
        if (juegoEmpieza == null) {
            juegoEmpieza = Instant.now();
            System.out.println(nombreUser + " empezó a jugar.");
        } else {
            System.out.println("La sesión ya está en curso.");
        }
    }

    public void terminaP() {
        if (juegoEmpieza != null) {
            Instant parar = Instant.now();
            Duration sessionTime = Duration.between(juegoEmpieza, parar);
            tiempoJugado = tiempoJugado.plus(sessionTime);
            actUltimaSesion();

            long minutos = tiempoJugado.toMinutes();
            long segundos = tiempoJugado.toSecondsPart();

            System.out.printf("%s paró de jugar. Tiempo total jugado: %d minutos y %d segundos.%n", 
                               nombreUser, minutos, segundos);
            juegoEmpieza = null;
        } else {
            System.out.println("No se ha iniciado partida.");
        }
    }

    public void actUltimaSesion() {
        this.ultimaSesion = Calendar.getInstance();
    }

    public long getTotalPlaytimeMinutes() {
        return tiempoJugado.toMinutes();
    }

    public String getFechaRegistro(){
        int año=fechaRegistro.get(Calendar.YEAR), mes=fechaRegistro.get(Calendar.MONTH),dia=fechaRegistro.get(Calendar.DAY_OF_MONTH);
        return dia+"/"+mes+"/"+año;
    }
    
    public String getUltimaSesion() {
        int año=ultimaSesion.get(Calendar.YEAR), mes=ultimaSesion.get(Calendar.MONTH),dia=ultimaSesion.get(Calendar.DAY_OF_MONTH);
        return dia+"/"+mes+"/"+año;
    }
    
    public String getNombreCompleto() { return nombreCompleto; }
    public String getContraseña() { return contraseña; }
    public String getNombreUser() { return nombreUser; }
    public String getAvatar() { return avatar;}
    public Duration getTiempoJugado() { return tiempoJugado; }

    public void setNombreUser(String nombreUser) {this.nombreUser = nombreUser;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
    public void setAvatar(String avatar) {this.avatar = avatar;}
    public void setUltimaSesion(Calendar nuevaSesion) {this.ultimaSesion = nuevaSesion;}
}

