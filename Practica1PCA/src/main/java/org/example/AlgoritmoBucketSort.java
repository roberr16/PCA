package org.example;
import practicas_pca.TesterRun;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoBucketSort implements TesterRun{
    @Override
    public ArrayList<Integer> bucketSort(List<Integer> numbers, int num_threads) {
        ArrayList<Integer> solucion = new ArrayList<>();
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();

        //Inicializacion del ArrayList buckets
        for (int i = 0; i < num_threads; i++) {
            buckets.add(new ArrayList<>());
        }

        //Relleno de cada bucket, almacenado en buckets utilizando un metodo privado auxiliar
        for (int i = 0; i < numbers.size(); i++) {
            int elemento = numbers.get(i);
            int num = seleccionarBucket(num_threads, elemento);
            buckets.get(num).add(elemento);
        }

        //Creacion de un ArrayList con tantos hilos como buckets haya
        ArrayList<ModifiedThread> hilos = null;
        try {
            hilos = crearHilos(num_threads, buckets);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Inicio y sincronizacion de los hilos
        for(ModifiedThread hilo : hilos) {
            hilo.start();
        }
        for (ModifiedThread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        //Relleno de ArrayList solucion con todos los subs ArrayList de los hilos
        for (ModifiedThread hilo : hilos) {
            solucion.addAll(hilo.getList());
        }

        return solucion;
    }


    /**
     * Metodo que determina, a partir del rango total de numeros que cubre la clase Integer,
     * y el numero de intervalos en el que queremos dividir este rango, a que intervalo
     * pertenece num.
     *
     * @param num_threads
     * @param num
     * @return
     */
    private int seleccionarBucket (int num_threads, int num){
        long rango = (long) Integer.MAX_VALUE - Integer.MIN_VALUE;
        long min = Integer.MIN_VALUE;
        float tamanoIntervalo = (float) rango / num_threads;

        if(num == Integer.MAX_VALUE){
            return num_threads;
        }
        return (int) ((num - min)/tamanoIntervalo);
    }

    /**
     * Metodo que crea e inicializa un ArrayList de objetos ModifiedThread (clase que extiende
     * de Thread) con tantos como se pidan.
     *
     * @param num_threads
     * @param buckets
     * @return
     * @throws InterruptedException
     */
    private ArrayList<ModifiedThread> crearHilos (int num_threads, ArrayList<ArrayList<Integer>> buckets  ) throws InterruptedException {
        ArrayList<ModifiedThread> hilos = new ArrayList<>();

        for (int i = 0; i < num_threads; i++) {
            hilos.add(new ModifiedThread("" + i, buckets.get(i)));
        }

        return hilos;
    }




}