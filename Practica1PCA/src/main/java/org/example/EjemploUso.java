package org.example;
import practicas_pca.TesterPracticas;
public class EjemploUso {
    public static void main(String[] args) {
        TesterPracticas TP = new TesterPracticas(new AlgoritmoBucketSort());
        TP.evaluarPractica(TesterPracticas.Instancias.NUMBER_2500000, 6);
    }
}
