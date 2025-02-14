package org.example;
import practicas_pca.TesterRun;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoBucketSort implements TesterRun{
    @Override
    public ArrayList<Integer> bucketSort(List<Integer> numbers, int num_threads) {
        ArrayList<Integer> solucion = new ArrayList<>();
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < num_threads; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int i = 0; i < numbers.size(); i++) {
            int elemento = numbers.get(i);
            int num = seleccionarBucket(num_threads, elemento);
            buckets.get(num).add(elemento);
        }

        ArrayList<ModifiedThread> hilos = null;
        try {
            hilos = crearHilos(num_threads, buckets);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        for (Thread hilo : hilos) {
            try {
                hilo.start();
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (ModifiedThread hilo : hilos) {
            solucion.addAll(hilo.getLista());
        }

        return solucion;
    }

    public int seleccionarBucket (int num_threads, int num){
        long rango = (long) Integer.MAX_VALUE - Integer.MIN_VALUE;
        long min = Integer.MIN_VALUE;
        return (int) ((num - min)/rango)*num_threads;

    }

    public ArrayList<ModifiedThread> crearHilos (int num_threads, ArrayList<ArrayList<Integer>> buckets  ) throws InterruptedException {
        ArrayList<ModifiedThread> hilos = new ArrayList<>();
        for (int i = 0; i < num_threads; i++) {
            ModifiedThread hilo = new ModifiedThread("" + i, buckets.get(i));
            hilos.add(hilo);
        }
        return hilos;
    }




}