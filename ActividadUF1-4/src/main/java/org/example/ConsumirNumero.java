package org.example;

import java.time.Duration;
import java.time.LocalTime;

public class ConsumirNumero implements Runnable {

    private BufferResultado buffer;
    private static int contador = 0;
    private Thread hilo;

    public ConsumirNumero(BufferResultado buffer, int numero1){
        contador++;
        this.buffer = buffer;
        hilo = new Thread(this,"hilo:" + contador);
        hilo.start();
    }


    @Override
    public void run() {

        int numero = buffer.consumirNumero();
       LocalTime inicio = LocalTime.now();
       boolean esPrimo = numerosPrimos(numero);
       LocalTime fin = LocalTime.now();

        Duration tiempo = Duration.between(inicio,fin);


        System.out.println("Número: " + numero + ", Es Primo: " + (esPrimo ? "Sí" : "No") +
                ", Hilo: " + Thread.currentThread().getName() + ", Tiempo:" + tiempo.toMillis() + "ms");




    }

    public boolean numerosPrimos(int num){

        if (num <=1) return false;
        for(int i = 2; i<=Math.sqrt(num); i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;

    }
}


