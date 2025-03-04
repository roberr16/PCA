package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

public class ProductorConsumidor {
    private static final int TAM_BUFFER = 5;
    private static final Queue<Integer> buffer = new LinkedList<>();
    
    // Semáforos para gestionar el acceso al buffer
    private static final Semaphore espacioDisponible = new Semaphore(TAM_BUFFER);
    private static final Semaphore elementosDisponibles = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1);

    public void ejecutarHilos() {
        // Crear y ejecutar productores y consumidores
        Thread productor1 = new Thread(new Productor(), "Productor 1");
        Thread productor2 = new Thread(new Productor(), "Productor 2");
        Thread consumidor1 = new Thread(new Consumidor(), "Consumidor 1");
        Thread consumidor2 = new Thread(new Consumidor(), "Consumidor 2");

        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();

        
    }

    // Clase Productor
    static class Productor implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int item = (int) (Math.random() * 100); // Producir un elemento
                    espacioDisponible.acquire(); // Esperar espacio disponible
                    mutex.acquire(); // acceso exclusivo
                    buffer.add(item); // Introducir el elemento al buffer
                    System.out.println(Thread.currentThread().getName() + " produjo: " + item);
                    mutex.release(); // Liberar acceso
                    elementosDisponibles.release(); // Indicar que hay un nuevo elemento
                    Thread.sleep(1000); // Simular tiempo de procesamiento
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumidor implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    elementosDisponibles.acquire(); //Espera a que haya elementos disponible para consumir
                    mutex.acquire();//Espera el acceso exclusivo
                    int item = buffer.remove();//Saca el elemento de la lista
                    System.out.println(Thread.currentThread().getName() + " consumio: " + item);
                    mutex.release();//Libera el acceso exclusivo
                    espacioDisponible.release();//Libera un permiso para la creacion de productos
                    Thread.sleep(1000); //Simula el tiempo de procesamiento
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

