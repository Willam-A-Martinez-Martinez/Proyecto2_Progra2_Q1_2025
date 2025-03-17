package Users;

import java.io.Serializable;

public class Preferencias implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String idioma;
    private int volumen;
    private String teclaArriba;
    private String teclaAbajo;
    private String teclaDerecha;
    private String teclaIzquierda;

    public Preferencias() {
        this.idioma         = "español";
        this.volumen        = 50;
        this.teclaArriba    = "w";
        this.teclaAbajo     = "s";
        this.teclaDerecha   = "d";
        this.teclaIzquierda = "a";
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        if(volumen<100 && volumen>=0){
            this.volumen = volumen;
        }else{
            System.out.println("NO SE CAMBIO VOLUMEN POR QUE ESTA FUERA DE RANGO");
        }
    }

    public String getTeclaArriba() {
        return teclaArriba;
    }

    public void setTeclaArriba(String teclaArriba) {
        this.teclaArriba = teclaArriba;
    }

    public String getTeclaAbajo() {
        return teclaAbajo;
    }

    public void setTeclaAbajo(String teclaAbajo) {
        this.teclaAbajo = teclaAbajo;
    }

    public String getTeclaDerecha() {
        return teclaDerecha;
    }

    public void setTeclaDerecha(String teclaDerecha) {
        this.teclaDerecha = teclaDerecha;
    }

    public String getTeclaIzquierda() {
        return teclaIzquierda;
    }

    public void setTeclaIzquierda(String teclaIzquierda) {
        this.teclaIzquierda = teclaIzquierda;
    }
    
    
}
