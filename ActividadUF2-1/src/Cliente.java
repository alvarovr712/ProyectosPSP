import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {


        Scanner leer = new Scanner(System.in);
       // Puerto en el que escucha el servidor

        try {
            Socket socket = new Socket("192.168.1.36", 5000); // Establecer conexión con el servidor


            OutputStream salida = socket.getOutputStream();
            InputStream entrada = socket.getInputStream();

            // Bucle para enviar solicitudes de consulta
            String codigoProducto;
            while (true) {
                System.out.print("Introduce el código del producto  o escribe FIN para terminar: ");
                codigoProducto = leer.nextLine().trim().toUpperCase(); // Leemos el código del producto

                if (codigoProducto.equals("FIN")) {
                    salida.write("FIN".getBytes());
                    System.out.println("Desconectando del servidor...");
                    break;
                }

                // Enviar el código del producto al servidor
                salida.write(codigoProducto.getBytes());

                // Leer la respuesta del servidor
                byte[] respuesta = new byte[1024];
                int bytesLeidos = entrada.read(respuesta);
                String mensaje = new String(respuesta, 0, bytesLeidos).trim();

                // Mostrar la respuesta del servidor
                System.out.println("Respuesta del servidor: " + mensaje);
            }

            // Cerrar la conexión
            entrada.close();
            salida.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }



    }
}
