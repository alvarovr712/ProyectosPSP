package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("APLICACION CLIENTE");
        System.out.println("**************************");
        Scanner leer = new Scanner(System.in);
        try {
        Socket cliente = new Socket();
        InetSocketAddress direccionServidor = new InetSocketAddress("127.0.0.1", 8080);
        System.out.println("Esperando a que el servidor acepte la conexión");
        cliente.connect(direccionServidor);
            System.out.println("Comunicacion establecida");
        InputStream entrada = cliente.getInputStream();
        OutputStream salida = cliente.getOutputStream();
        String texto = "";
        while(!texto.equalsIgnoreCase("s")){
            System.out.println("-*-*-*-*-*  MENÚ  *-*-*-*-*-*");
            System.out.println("1.Consultar libros por titulo");
            System.out.println("2.Consultar libro por genero");
            System.out.println("3.Consultar libro por autor");
            System.out.println("4.Consultar carrito");

            int opcion = leer.nextInt();
            leer.nextLine();
            String mensajeS;
            String respuesta = "";
            switch (opcion){

                case 1:
                    //Primer mensaje enviado y recibido
                    mensajeS = "1";
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    byte[] mensaje = new byte[100];
                    int bytesLeidos = entrada.read(mensaje);
                    respuesta = new String(mensaje,0,bytesLeidos);
                    System.out.println(respuesta);
                    //Segundo mensaje enviado y recibido
                    mensajeS = leer.nextLine();
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    byte[] mensaje1 = new byte[100];
                    int bytesLeidos1 = entrada.read(mensaje1);
                    respuesta = new String(mensaje1,0,bytesLeidos1);
                    System.out.println(respuesta);
                    //Tercer mensaje enviado y recibido
                    byte[] mensaje2 = new byte[100];
                    int bytesLeidos2 = entrada.read(mensaje2);
                    respuesta = new String(mensaje2,0,bytesLeidos2);
                    System.out.println(respuesta);

                    mensajeS = leer.nextLine();
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    //Cuarto mensaje este solo recibido
                    byte[] mensaje3 = new byte[100];
                    int byteLeidos3 = entrada.read(mensaje3);
                    respuesta = new String(mensaje3,0,byteLeidos3);
                    System.out.println(respuesta);

                    break;

                case 2:
                    //Primer mensaje enviado y recibido
                    mensajeS = "2";
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    byte[] mensaje5 = new byte[100];
                    int bytesLeidos5 = entrada.read(mensaje5);
                    respuesta = new String(mensaje5,0,bytesLeidos5);
                    System.out.println(respuesta);
                    //Segundo mensaje enviado y recibido
                    mensajeS = leer.nextLine();
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    byte[] mensaje6 = new byte[10000];
                    int bytesLeidos6 = entrada.read(mensaje6);
                    respuesta = new String(mensaje6,0,bytesLeidos6);
                    System.out.println(respuesta);
                    //Tercer mensaje enviado y recibido
                    byte[] mensaje7 = new byte[100];
                    int bytesLeidos7 = entrada.read(mensaje7);
                    respuesta = new String(mensaje7,0,bytesLeidos7);
                    System.out.println(respuesta);

                    mensajeS = leer.nextLine();
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    //Cuarto mensaje este solo recibido
                    byte[] mensaje8 = new byte[100];
                    int byteLeidos8 = entrada.read(mensaje8);
                    respuesta = new String(mensaje8,0,byteLeidos8);
                    System.out.println(respuesta);


                    break;

                case 3:
                    //Primer mensaje enviado y recibido
                    mensajeS = "3";
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    byte[] mensaje9 = new byte[100];
                    int bytesLeidos9 = entrada.read(mensaje9);
                    respuesta = new String(mensaje9,0,bytesLeidos9);
                    System.out.println(respuesta);

                    //Segundo mensaje enviado y recibido
                    mensajeS = leer.nextLine();
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    byte[] mensaje10 = new byte[10000];
                    int bytesLeidos10 = entrada.read(mensaje10);
                    respuesta = new String(mensaje10,0,bytesLeidos10);
                    System.out.println(respuesta);
                    //Tercer mensaje enviado y recibido
                    byte[] mensaje11 = new byte[100];
                    int bytesLeidos11 = entrada.read(mensaje11);
                    respuesta = new String(mensaje11,0,bytesLeidos11);
                    System.out.println(respuesta);

                    mensajeS = leer.nextLine();
                    salida.write(mensajeS.getBytes());
                    salida.flush();

                    //Cuarto mensaje este solo recibido
                    byte[] mensaje12 = new byte[100];
                    int byteLeidos12 = entrada.read(mensaje12);
                    respuesta = new String(mensaje12,0,byteLeidos12);
                    System.out.println(respuesta);

                    break;


                case 4:
                    //Primer mensaje enviado y recibido
                    mensajeS = "4";
                    salida.write(mensajeS.getBytes());
                    salida.flush();
                    byte[] mensaje4 = new byte[10000];
                    int bytesLeidos4 = entrada.read(mensaje4);
                    respuesta = new String(mensaje4,0,bytesLeidos4);
                    System.out.println(respuesta);

                    break;
            }

        // **************************** -------- ***************************
            System.out.println("¿Quieres salir S/N?");
            texto = leer.nextLine();

        }

        entrada.close();
        salida.close();
        cliente.close();
            System.out.println("Comunicación finalizada");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
