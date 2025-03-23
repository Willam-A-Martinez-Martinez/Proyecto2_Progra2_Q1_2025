package Users;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Datos implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreUser, nombreCompleto, contraseña, avatar;
    private LocalDateTime fechaRegistro, ultimaSesion, inicioSesion, finSesion;
    private Duration tiempoJugado; 

    public Datos(String nombreUser, String nombreCompleto, String contraseña) {
        this.nombreUser = nombreUser;
        this.nombreCompleto = nombreCompleto;
        this.contraseña = contraseña;
        this.avatar = "";
        this.fechaRegistro = LocalDateTime.now();
        this.ultimaSesion = LocalDateTime.now();
        this.tiempoJugado = Duration.ZERO;
    }

    public void iniciaSesion() {
        if (inicioSesion == null) {
            this.inicioSesion = LocalDateTime.now();
            System.out.println(nombreUser + " inició sesión el " + getInicioSesion());
        } else {
            System.out.println("Ya hay una sesión en curso.");
        }
    }

    public void cierraSesion() {
        if (inicioSesion != null) {
            this.finSesion = LocalDateTime.now();
            Duration sesionActual = Duration.between(inicioSesion, finSesion);
            this.tiempoJugado = this.tiempoJugado.plus(sesionActual);
            this.ultimaSesion = finSesion;
            inicioSesion = null; // Resetea para la próxima sesión

            System.out.println(nombreUser + " cerró sesión el " + getFinSesion());
            System.out.println("Tiempo jugado en esta sesión: " + formatearDuracion(sesionActual));
            System.out.println("Tiempo total jugado: " + getTiempoTotalFormateado());
        } else {
            System.out.println("No hay sesión iniciada.");
        }
    }

    public String getInicioSesion() {
        return (inicioSesion != null) ? formatearFecha(inicioSesion) : "No ha iniciado sesión";
    }

    public String getFinSesion() {
        return (finSesion != null) ? formatearFecha(finSesion) : "No ha cerrado sesión";
    }

    public String getTiempoTotalFormateado() {
        return formatearDuracion(tiempoJugado);
    }

    private String formatearFecha(LocalDateTime fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formato);
    }

    private String formatearDuracion(Duration duracion) {
        long horas = duracion.toHours();
        long minutos = duracion.toMinutesPart();
        long segundos = duracion.toSecondsPart();
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
    
    public String getNombreCompleto() { return nombreCompleto; }
    public String getContraseña() { return contraseña; }
    public String getNombreUser() { return nombreUser; }
    public String getAvatar() { return avatar;}
    public Duration getTiempoJugado() { return tiempoJugado; }
    public LocalDateTime getFechaRegistro() {return fechaRegistro;}
    public LocalDateTime getUltimaSesion() {return ultimaSesion;}
    public String getFechaRegistroFormateada() {return formatearFecha(fechaRegistro);}
    public String getUltimaSesionFormateada() {return formatearFecha(ultimaSesion);}
    
    public void setNombreUser(String nombreUser) {this.nombreUser = nombreUser;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}
    public void setAvatar(String avatar) {this.avatar = avatar;}
    public void setUltimaSesion(LocalDateTime nuevaSesion) {this.ultimaSesion = nuevaSesion;}
    
}

