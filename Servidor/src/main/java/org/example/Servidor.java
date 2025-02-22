package org.example;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    //Creo una lista de los libros que tengo
    private List<Libro> libros;
    private List<Libro> carrito;
    //Inicializo la lista en mi clase servidor y el metodo que he creado cargarDatos() para que agregue a la lista los libros.
    public Servidor(){
        libros = new ArrayList<>();
        carrito = new ArrayList<>();
        cargarDatos();
    }

    public void cargarDatos(){

        libros.add(new Libro("El Quijote", "Miguel de Cervantes", "Ficción", 15.50));
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "Ficción", 18.00));
        libros.add(new Libro("1984", "George Orwell", "Distopía", 12.75));
        libros.add(new Libro("El señor de los anillos", "J.R.R. Tolkien", "Fantasía", 22.50));
        libros.add(new Libro("Orgullo y prejuicio", "Jane Austen", "Romántico", 10.99));
        libros.add(new Libro("La sombra del viento", "Carlos Ruiz Zafón", "Misterio", 16.40));
        libros.add(new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", "Fantasía", 20.30));
        libros.add(new Libro("Matar a un ruiseñor", "Harper Lee", "Drama", 14.99));
        libros.add(new Libro("Los juegos del hambre", "Suzanne Collins", "Ciencia ficción", 17.00));
        libros.add(new Libro("La casa de los espíritus", "Isabel Allende", "Realismo mágico", 19.10));

    }
    public static void main(String[] args) {

        System.out.println("SERVIDOR MULTITAREA");

        try {
            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("127.0.0.1", 8080);
            servidor.bind(direccion);
            System.out.println("Servidor escuchando en el puerto ...");
            Servidor servidor1 = new Servidor();

            while (true){

                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Comunicacion entrante desde: " + enchufeAlCliente.getInetAddress());

               new Thread( new HiloEscuchador(enchufeAlCliente,servidor1));


            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**Método para buscar por titulo**/
    public Libro buscarPorTitulo(String titulo) {

        for (Libro item : libros) {
            if (titulo.equals(item.getTitulo())) {
                return item;
            }
        }
        System.out.println("No se encontró ningún libro con ese Titulo");
        return null;
    }
    /**Método para buscar por genero**/

    public List<Libro> buscarPorGenero(String genero){

        List<Libro> libroEncontrado = new ArrayList<>();

        for(Libro item : libros){
            if(genero.equals(item.getGenero())){
                libroEncontrado.add(item);

            }
        }
        if(libroEncontrado.isEmpty()){
        System.out.println("No se encontró ningún libro con ese Genero");
        return null;}
        return libroEncontrado;
    }
    /**Método ppara buscar por autor**/

    public List<Libro> buscarPorAutor(String autor){
        List<Libro> libroEncontrado = new ArrayList<>();

        for(Libro item : libros){
            if(autor.equals(item.getAutor())){
            libroEncontrado.add(item);
        }
        }
        if(libroEncontrado.isEmpty()){
            System.out.println("No se encontro ningún libro con ese Autor");
            return null;
        }
        return libroEncontrado;
    }

    /**Método para añadir al carrito**/
    public void añadirAlCarrito(List<Libro> libros){

        for(Libro item: libros){
        carrito.add(item);}
    }

    /**Método para ver el carrito**/

    public List<Libro> verElCarrito(){
        for(Libro item: carrito){
            System.out.println(item);
        }
        return carrito;
    }

}
