import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        int opcion;


        while (true) {
            Socket socket = new Socket();
            InetSocketAddress direccion = new InetSocketAddress("127.0.0.1", 2020);
            try {
                //Conectar con el servidor
                socket.connect(direccion);
                System.out.println("Conectado al servidor en 127.0.0.1:8080");

                //Creo El inputStream y el OutputStream
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                //Creo el menú que usara el cliente para interactuar


                while (true) {
                    System.out.println("-*-*-*-*-*  MENÚ  *-*-*-*-*-*");
                    System.out.println("1.Consultar libros por ISBN");
                    System.out.println("2.Consultar libro por titulo");
                    System.out.println("3.Consultar libro por autor");
                    System.out.println("4.Añadir un libro");
                    System.out.println("5.Salir");

                    opcion = leer.nextInt();
                    leer.nextLine();

                    //Con este return logro parar el programa directamente y que no siga en bucle con los dos while(true) que tengo
                    if (opcion == 5) {
                        System.out.println("Fin de Programa");

                        return;
                    }

                    switch (opcion) {
                        case 1:
                            //Escribir mensaje al servidor
                            System.out.println("Ingrese el ISBN del libro");
                            String ISBN = leer.next();
                            String mensajeCliente = "1/" + ISBN;
                            outputStream.write(mensajeCliente.getBytes());
                            outputStream.flush();
                            System.out.println("Mensaje enviado al servidor");
                            break;

                        case 2:

                            System.out.println("Ingrese el título del libro");
                            String titulo = leer.nextLine();
                            String mensajeCliente1 = "2/" + titulo;
                            outputStream.write(mensajeCliente1.getBytes());
                            outputStream.flush();
                            System.out.println("Mensaje enviado al servidor");
                            break;

                        case 3:
                            System.out.println("Ingrese el autor");
                            String autor = leer.nextLine();
                            String mensajeCliente2 = "3/" + autor;
                            outputStream.write(mensajeCliente2.getBytes());
                            System.out.println("Mensaje enviado al servidor");
                            break;

                        case 4:
                            double precio;

                            System.out.println("Dame el ISBN");
                            ISBN = leer.nextLine();
                            System.out.println("Dame el titulo");
                            titulo = leer.nextLine();
                            System.out.println("Dame el autor");
                            autor = leer.nextLine();
                            System.out.println("Dame el precio");
                            precio = leer.nextDouble();


                            String mensaje3 = "4/" + ISBN + "/" + titulo + "/" + autor + "/" + precio;
                            outputStream.write(mensaje3.getBytes());
                            System.out.println("Mensaje enviado al servidor");

                    }

                    //Leer el mensaje del servidor

                    byte[] buffer = new byte[1024];
                    int bytesRead = inputStream.read(buffer);
                    if (bytesRead == -1) {
                        System.out.println("Se perdió la conexión con el servidor.");
                        break;  // Salir del ciclo si no se recibe respuesta
                    }

                    String mensajeServidor = new String(buffer, 0, bytesRead);
                    System.out.println("Libros: " + mensajeServidor);


                }


            } catch (IOException e) {
                System.out.println("No se pudo conectar al servidor: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();  // Asegurarse de cerrar el socket al final
                        System.out.println("Conexión cerrada.");
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar el socket: " + e.getMessage());
                }
            }


        }
    }
}


