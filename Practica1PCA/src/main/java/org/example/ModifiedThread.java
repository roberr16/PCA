package org.example;


import java.util.ArrayList;

public class ModifiedThread extends Thread {
    private String nombre;
    private ArrayList<Integer> lista;

    public ModifiedThread(String nombre, ArrayList<Integer> lista ) throws InterruptedException {
        this.nombre = nombre;
        this.lista = lista;
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











