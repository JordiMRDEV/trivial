package model;

public class Jugador {
    private String name;
    private int aciertos;
    private int puntuacion;

    public Jugador(String name) {
        this.name = name;
        this.aciertos = 0;
        this.puntuacion = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
