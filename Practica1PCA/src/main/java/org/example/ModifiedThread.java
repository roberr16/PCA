package org.example;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        Collections.sort(lista);
        System.out.println("Hilo " + nombre + " ha terminado.");
    }

    public ArrayList<Integer> getLista() {
        return lista;
    }
}











