package org.example;

import java.util.ArrayList;
import java.util.List;


public class MiHilo extends Thread {

    public static class MiHilo extends Thread {
        private String nombre;

        public MiHilo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            System.out.println("Hilo " + nombre + " está ejecutándose.");
            try {
                // Simula alguna tarea del hilo
                Thread.sleep(1000); // Pausa de 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Hilo " + nombre + " fue interrumpido.");
            }
            System.out.println("Hilo " + nombre + " ha terminado.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Crear una lista para almacenar los hilos
        List<Thread> hilos = new ArrayList<>();

        // Digamos que determinamos el número de hilos dinámicamente
        int numHilos = 5;  // Este valor podría venir de cualquier fuente dinámica

        // Crear y almacenar los hilos dinámicamente
        for (int i = 1; i <= numHilos; i++) {
            Thread hilo = new MiHilo("Hilo " + i);
            hilos.add(hilo);  // Agregar hilo a la lista
        }

        // Iniciar todos los hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            hilo.join();  // Espera que cada hilo termine
        }

        System.out.println("Todos los hilos han terminado.");
    }

}