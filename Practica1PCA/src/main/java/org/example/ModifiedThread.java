package org.example;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ModifiedThread extends Thread {
    private String name;
    private ArrayList<Integer> list;

    public ModifiedThread(String name, ArrayList<Integer> list ){
        this.name = name;
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("Hilo " + name + " está ejecutándose.");
        Collections.sort(list);
        System.out.println("Hilo " + name + " ha terminado.");
    }

    public ArrayList<Integer> getList() {
        return list;
    }
}











