package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        BufferResultado buffer = new BufferResultado();
        Scanner leer = new Scanner(System.in);

        System.out.print("Introduce el primer número: ");
        int numero1 = leer.nextInt();

        System.out.print("Introduce el segundo número: ");
        int numero2 = leer.nextInt();

        System.out.print("Introduce el tercer número: ");
        int numero3 = leer.nextInt();

        System.out.print("Introduce el cuarto número: ");
        int numero4 = leer.nextInt();


        new AgregarNumero(buffer, numero1);
        new AgregarNumero(buffer, numero2);
        new AgregarNumero(buffer, numero3);
        new AgregarNumero(buffer, numero4);


        new ConsumirNumero(buffer,numero1);
        new ConsumirNumero(buffer,numero2);
        new ConsumirNumero(buffer,numero3);
        new ConsumirNumero(buffer,numero4);

        leer.close();


    }
}