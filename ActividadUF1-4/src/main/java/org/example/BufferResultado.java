package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BufferResultado {

    private Queue<Integer> cola;
    public  BufferResultado(){
        cola = new LinkedList<Integer>();
    }


    public synchronized void agregarNumero(int numero){
        cola.add(numero);
        notify();

    }

    public synchronized  int consumirNumero(){

        while(cola.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        //Es una cualidad de listas que se utiliza para recuperar y eliminar el primer elemento de la cola.
        return cola.poll();
    }



}
