package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Implementation {
    public static void main(String[] args) {
        List<Callable<Integer>> task = Arrays.asList(() -> {
                int sum = 0;
                int pay = 0;
                for (int i = 0; i < 5; i++) {
                    pay = (int) ((Math.random()*20)+1)*1000;
                    sum += pay;
                    try {
                        System.out.println("tarea 1, Pago N°"+i+", Ganancia "+pay);
                        Thread.sleep(1000); // Pausa el hilo por 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return sum; // Retorna la suma de los números
            }, () -> {int sum = 0;
                int pay = 0;
                for (int i = 0; i < 5; i++) {
                    pay = (int) ((Math.random()*20)+20)*1000;
                    sum += pay;
                    try {
                        System.out.println("tarea 12, Pago N°"+i+", Ganancia "+pay);
                        Thread.sleep(1000); // Pausa el hilo por 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return sum; // Retorna la suma de los números
                }, () -> {int sum = 0;
                    int pay = 0;
                    for (int i = 0; i < 5; i++) {
                        pay = (int) ((Math.random()*20)+40)*1000;
                        sum += pay;
                        try {
                            System.out.println("tarea 3, Pago N°"+i+", Ganancia "+pay);
                            System.out.println("===============================");
                            Thread.sleep(1000); // Pausa el hilo por 1 segundo
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return sum; // Retorna la suma de los números
                    });

    ExecutorService executor = Executors.newFixedThreadPool(3);

    try {
            List<Future<Integer>> results = executor.invokeAll(task);
            int sum = 0;
            for (Future<Integer> result : results) {
                sum += result.get();
            }
            System.out.println("Ganancias Totales: " + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
