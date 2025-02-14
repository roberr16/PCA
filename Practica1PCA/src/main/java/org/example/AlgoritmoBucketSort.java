package org.example;
import practicas_pca.TesterRun;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

public class AlgoritmoBucketSort implements TesterRun{
    @Override
    public ArrayList<Integer> bucketSort(List<Integer> numbers, int num_threads) {
        ArrayList<Integer> solucion = new ArrayList<>();
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            int elemento = numbers.get(i);
            int num = seleccionarBucket(num_threads, elemento);
            buckets.get(num).add(elemento);
        }
        for (int i = 0; i < num_threads; i++) {
            Thread hilo = new Thread
        }
    }

    public int seleccionarBucket (int num_threads, int num){
        long rango = (long) Integer.MAX_VALUE - Integer.MIN_VALUE;
        long min = Integer.MIN_VALUE;
        return (int) ((num - min)/rango)*num_threads;
    }


}