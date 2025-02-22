package org.example;

public class AgregarNumero implements Runnable {

    private BufferResultado buffer;
    private static int contador = 0;
    private Thread hilo;
    private int numero;

    public AgregarNumero(BufferResultado buffer,int numero){
         contador++;
         this.buffer = buffer;
         this.numero = numero;
         hilo = new Thread(this,"hilo:" + contador);
         hilo.start();


    }

    @Override
    public void run() {

        buffer.agregarNumero(numero);
        System.out.println("Número" + numero + "agregado, hilo: " + Thread.currentThread().getName());



    }
}
