import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class HiloCliente implements Runnable {

    private Thread hilo;
    private static int numeroCliente = 0;
    private Socket puente;
    private Biblioteca biblioteca;

    public HiloCliente(Socket cliente, Biblioteca biblioteca){
        numeroCliente++;
        this.biblioteca = biblioteca;
        this.puente = cliente;
        hilo = new Thread(this, "cliente" + numeroCliente);
        hilo.start();
    }

    @Override
    public void run() {

        System.out.println("Estableciendo comunicación con " + hilo.getName());


        try {
            InputStream inputStream = puente.getInputStream();
            OutputStream outputStream = puente.getOutputStream();

            //Leer mensaje enviado por el cliente
            byte[] buffer = new byte[1024];
            int bytesRead;

            while((bytesRead = inputStream.read(buffer))  != -1){
            String mensaje = new String(buffer,0,bytesRead);
            /**/
            System.out.println("Mensaje recibido del cliente " + mensaje);
            //Procesar el mensaje recibido
            String respuesta = "Operación no reconocida";
            
            //Consultar libros por ISBN
            if(mensaje.startsWith("1")) {

                String ISBN = mensaje.split("/")[1];
                Libro libroEncontrado = biblioteca.buscarPorISBN(ISBN);

                if (libroEncontrado != null) {
                    respuesta = libroEncontrado.toString();
                } else {
                    respuesta = "Libro no encontrado";
                }
                }

            //Consultar libros por titulo
                if(mensaje.startsWith("2")){
                    String titulo = mensaje.split("/")[1];
                    Libro libroEncontrado = biblioteca.buscarPorTitulo(titulo);
                    if(libroEncontrado != null){
                        respuesta = libroEncontrado.toString();
                    }else{
                        respuesta = "Libro no encontrado";
                    }

                }
            //Consultar libros por autor

                if(mensaje.startsWith("3")){
                    String autor = mensaje.split("/") [1];
                    List<Libro>libroEncontrado = biblioteca.buscarPorAutor(autor);
                    if(libroEncontrado != null){
                        respuesta = libroEncontrado.toString();
                    }else {
                        respuesta= "Libro no encontrado";
                    }

                }

            //Añadir libro

               if(mensaje.startsWith("4")){
                   String ISBN = mensaje.split("/")[1];
                   String titulo = mensaje.split("/")[2];
                   String autor = mensaje.split("/")[3];
                   double precio = Double.parseDouble(mensaje.split("/")[4]);

                   Libro libroNuevo = new Libro(ISBN,titulo,autor,precio);
                   biblioteca.añadirLibro(libroNuevo);
                   Libro libroBuscado = biblioteca.buscarPorISBN(ISBN);
                   if(libroNuevo != null){
                       respuesta = "El libro ha sido añadido";
                   }else{
                       respuesta = "Error al añadir el libro";
                   }


               }


            // Enviar la respuesta al cliente
            outputStream.write(respuesta.getBytes());
            outputStream.flush();}
        } catch (IOException e) {
            System.out.println(e.getMessage());


        }finally {
            try {
                puente.close();
                System.out.println("Conexión con " + hilo.getName() + " cerrada.");
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());

            }
        }

        }

    }
