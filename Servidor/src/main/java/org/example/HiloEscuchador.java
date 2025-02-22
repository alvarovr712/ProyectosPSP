package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HiloEscuchador implements  Runnable{
    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    private Servidor servidor;

    public HiloEscuchador(Socket cliente, Servidor servidor){
        numCliente ++;
        this.enchufeAlCliente = cliente;
        this.servidor = servidor;
        hilo = new Thread( this, "Cliente"+numCliente);

        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicacion con " + hilo.getName());
        try {
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();
            String texto = "";
            String respuesta = "";
            String respuesta1 = "";
            String respuesta2 = "";

            while(!texto.trim().equals("FIN")){
                byte[] mensaje = new byte[100];
                int bytesLeidos = entrada.read(mensaje);
                if(bytesLeidos > 0) {


                    //Convierto la respuesta en un String para poder leerla y compararla asi dependiendo de la respuesta que me envie el cliente podré
                    //contestarle una cosa diferente.

                    texto = new String(mensaje, 0, bytesLeidos).trim();
                    System.out.println("Mensaje recibido " + texto);

                    //Respuestas al cliente dependiendo de el mensaje recibido

                    switch(texto) {
                        //Buscar Libro por titulo
                        case "1":
                        //Primer Mensaje enviado
                        respuesta = "Ingresa el titulo del libro que quieres consultar";
                        salida.write(respuesta.getBytes());
                        salida.flush();
                        //Segundo mensaje
                        byte[] mensaje1 = new byte[100];
                        int bytesLeidos1 = entrada.read(mensaje1);
                         respuesta1 = new String(mensaje1, 0 ,bytesLeidos1);
                        Libro libroEncontrado = servidor.buscarPorTitulo(respuesta1);
                        if(libroEncontrado != null){
                            respuesta1 = libroEncontrado.toString();
                            salida.write(respuesta1.getBytes());
                            salida.flush();
                        }else{
                            respuesta1 = "Libro no encontrado";
                            salida.write(respuesta1.getBytes());
                            salida.flush();

                        }
                        //Tercer mensaje
                            respuesta2 = "¿Deseas añadirlo al carrito  S/N?";
                            salida.write(respuesta2.getBytes());
                            salida.flush();

                            byte[] mensaje3 = new byte[100];
                            int byteLeidos3 = entrada.read(mensaje3);
                            respuesta = new String(mensaje3,0,byteLeidos3);
                            if(respuesta.equalsIgnoreCase("S")){
                                List<Libro> libroEncontrado2 = new ArrayList<>();
                                libroEncontrado2.add(libroEncontrado);
                                servidor.añadirAlCarrito(libroEncontrado2);
                                respuesta = "Su libro a sido añadido al carrito";
                                salida.write(respuesta.getBytes());
                                salida.flush();
                            }else{
                                respuesta = "No se ha agregado al carrito";
                                salida.write(respuesta.getBytes());
                                salida.flush();
                            }

                        break;

                        case "2":

                            //Primer mensaje
                            respuesta = "Ingresa el genero del libro que quieres consultar";
                            salida.write(respuesta.getBytes());
                            salida.flush();

                            /*Segundo mensaje -> en este segundo mensaje he tendio que hacer algo parecido a el punto 5 porque al tener la posibilidad
                            * de poder tener mas de un libro con el mismo genero debo devolver mas de uno.
                            * He tenido que crearme un ArrayList he igualarlo a mi metodo buscarPorGenero que devuelve otra Lista, después he creado un
                            * StringBuilder para cuando recorra my nuevo Arraylist poder ir añadiendole a mi StringBuilder esos libros que coinciden
                            * con el apend una vez he finalizado la busqueda devuelvo la respuesta al cliente en modo toString papra que vea todo el libro*/
                            byte[] mensaje4 = new byte[100];
                            int byteLeidos4 = entrada.read(mensaje4);
                            respuesta2 = new String(mensaje4,0,byteLeidos4);
                            List<Libro> libroEncontrado1 = servidor.buscarPorGenero(respuesta2);
                            StringBuilder librosGenero = new StringBuilder();

                            if(libroEncontrado1 != null){
                                for(Libro item: libroEncontrado1){
                                    librosGenero.append(item).append("\n");
                                }
                                respuesta2 = librosGenero.toString();
                                salida.write(respuesta2.getBytes());
                                salida.flush();

                            }else{
                                respuesta2 = "Libro no encontrado";
                                salida.write(respuesta2.getBytes());
                                salida.flush();
                            }

                            //Tercer mensaje
                            respuesta2 = "¿Deseas añadirlo al carrito  S/N?";
                            salida.write(respuesta2.getBytes());
                            salida.flush();

                            byte[] mensaje5 = new byte[100];
                            int byteLeidos5 = entrada.read(mensaje5);
                            respuesta = new String(mensaje5,0,byteLeidos5);
                            if(respuesta.equalsIgnoreCase("S")){
                                servidor.añadirAlCarrito(libroEncontrado1);
                                respuesta = "Su libro a sido añadido al carrito";
                                salida.write(respuesta.getBytes());
                                salida.flush();
                            }else{
                                respuesta = "No se ha agregado al carrito";
                                salida.write(respuesta.getBytes());
                                salida.flush();
                            }

                            break;

                        case "3":
                            //Primer mensaje
                            respuesta = "Ingresa el autor  que quieres consultar";
                            salida.write(respuesta.getBytes());
                            salida.flush();
                            //Segundo mensaje
                            byte[] mensaje6 = new byte[100];
                            int byteLeidos6 = entrada.read(mensaje6);
                            respuesta2 = new String(mensaje6,0,byteLeidos6);
                            List<Libro> libroEncontrado2 = servidor.buscarPorAutor(respuesta2);
                            StringBuilder librosAutor = new StringBuilder();

                            if(libroEncontrado2 != null){
                                for(Libro item: libroEncontrado2){
                                    librosAutor.append(item).append("\n");
                                }
                                respuesta2 = librosAutor.toString();
                                salida.write(respuesta2.getBytes());
                                salida.flush();

                            }else{
                                respuesta2 = "Libro no encontrado";
                                salida.write(respuesta2.getBytes());
                                salida.flush();
                            }
                            //Tercer mensaje
                            respuesta2 = "¿Deseas añadirlo al carrito  S/N?";
                            salida.write(respuesta2.getBytes());
                            salida.flush();

                            byte[] mensaje7 = new byte[100];
                            int byteLeidos7 = entrada.read(mensaje7);
                            respuesta = new String(mensaje7,0,byteLeidos7);
                            if(respuesta.equalsIgnoreCase("S")){
                                servidor.añadirAlCarrito(libroEncontrado2);
                                respuesta = "Su libro a sido añadido al carrito";
                                salida.write(respuesta.getBytes());
                                salida.flush();
                            }else{
                                respuesta = "No se ha agregado al carrito";
                                salida.write(respuesta.getBytes());
                                salida.flush();
                            }

                            break;

                        case "4":
                            /*Para poder pasar todos los libros que añado al carrito ya que es una lista he tenido que crear un StringBuilder
                            * igualar mi lista carrito a una lista que he creado aqui y entrar con un foreach, una vez dentro recorrer el array con mi
                            * variable item e irsela añadiendo a mi StringBuilder mediante apend, para poder devolver al cliente una respuesta a este
                            * StringBuilder le he metido un toString de cada libro para que los envie en un String.
                            * Es la unica manera que se me a ocurrido no se si es un poco chapucera, el metodo void no podia devolverlo porque necesito
                            * un String para poder enviar una respuesta al cliente, y la lista tampoco podia devolverla como tal me pide un String.*/

                            List<Libro> libross = servidor.verElCarrito();
                            StringBuilder libros = new StringBuilder();
                            for(Libro item: libross){
                                libros.append(item).append("\n");
                            }
                            respuesta = libros.toString();
                            salida.write(respuesta.getBytes());
                            salida.flush();


                            break;


                    }


                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
