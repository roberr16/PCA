package org.example;


public class ModifiedThread extends Thread {
    private String nombre;

    public ModifiedThread(String nombre) throws InterruptedException {
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











