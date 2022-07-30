package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Partida {

    private Jugador[] jugadores;
    private ArrayList<Pregunta> preguntas;
    private Scanner scanner;

    public Partida() {
        initPartida();
        initPreguntas();
        initPlayers();
    }

    private void initPartida() {
        scanner = new Scanner(System.in);
        System.out.println("Introduce el número de jugadores:");
        jugadores = new Jugador[scanner.nextInt()];
    }

    private void initPlayers() {
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("Introduce el nombre del jugador " + (i+1) + ":");
            jugadores[i] = new Jugador(scanner.next());
        }
    }

    private void initPreguntas() {
        this.preguntas = new ArrayList<>();

        createPregunta("¿La capital de Francia es Paris?", true, 1);
        createPregunta("¿La capital de España es Berlín?", false, 2);
        createPregunta("¿La capital de Alemania es Praga?", false, 1);
        createPregunta("¿La capital de Venezuela es Caracas?", true, 2);
        createPregunta("¿La capital de Mexico es Miami?", false, 1);
    }

    private void createPregunta(String afirmacion, boolean isTrue, int dificultad) {
        Pregunta pregunta = new Pregunta(afirmacion, isTrue, dificultad);
        this.preguntas.add(pregunta);
    }

    public void jugarPartida() {
        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta preguntaActual = preguntas.get(i);
            mostrarPregunta(preguntaActual);

            for (Jugador jugador : jugadores) {
                boolean respuesta = preguntaUsuario(jugador);
                if (respuesta == preguntaActual.isTrue()) {
                    jugador.setPuntuacion(jugador.getPuntuacion() + preguntaActual.getDificultad());
                    jugador.setAciertos(jugador.getAciertos() + 1);
                }
            }

            System.out.println("La respuesta correcta era " + preguntaActual.isTrue());
        }
        mostrarEstadisticas();
        anunciarGanador();
    }

    private void anunciarGanador() {

        int puntuacionMaxima = 0;

        for (int i = 0; i < jugadores.length; i++) {
            if (puntuacionMaxima < jugadores[i].getPuntuacion()) {
                puntuacionMaxima = jugadores[i].getPuntuacion();
            }
        }

        System.out.print("El ganador es: ");
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].getPuntuacion() == puntuacionMaxima) {
                System.out.print(jugadores[i].getName() + ", ");
            }
        }
    }

    private void mostrarPregunta(Pregunta pregunta) {
        String afirmacion = pregunta.getAfirmacion();
        System.out.println(afirmacion);
    }

    private boolean preguntaUsuario(Jugador jugador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(jugador.getName() + ", true o false?");
        return scanner.nextBoolean();
    }

    private void mostrarEstadisticas() {
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("La puntuación final del jugador " + jugadores[i].getName() + " es:");
            System.out.println("\tTotal de respuestas acertadas: " + jugadores[i].getAciertos());
            System.out.println("\tTotal de puntuación obtenida: " + jugadores[i].getPuntuacion());
            double percentage = (double)jugadores[i].getAciertos() / this.preguntas.size() * 100;
            System.out.println("\tTanto por ciento de aciertos: " + percentage + "%");
        }
    }
}
