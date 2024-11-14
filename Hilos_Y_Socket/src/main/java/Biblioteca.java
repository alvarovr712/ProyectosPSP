import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
        cargarDatos();
    }

    public void cargarDatos() {
        libros.add(new Libro("978-3-16-148410-0", "Cien años de soledad", "Gabriel García Márquez", 19.99));
        libros.add(new Libro("978-1-86197-876-9", "1984", "George Orwell", 14.99));
        libros.add(new Libro("978-0-7432-7356-5", "El gran Gatsby", "F. Scott Fitzgerald", 10.99));
        libros.add(new Libro("978-0-451-53103-8", "Fahrenheit 451", "Gabriel García Márquez", 12.99));
        libros.add(new Libro("978-0-06-112241-5", "To Kill a Mockingbird", "Harper Lee", 15.99));
    }

    public static void main(String[] args) {


        ServerSocket serverSocket;

        {
            try {
                serverSocket = new ServerSocket(2020);
                System.out.println("Servidor escuchando en el puerto 8080...");
                Biblioteca biblioteca = new Biblioteca();
                //Con este while true coloco la creacion de un bucle para poder acceder a multiples clientes
                while (true) {
                    //Esperar a que el cliente se conecte
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Cliente conectado");
                    //new HiloCliente(clientSocket);}

                    new Thread(new HiloCliente(clientSocket, biblioteca));
                }
            } catch (IOException e) {
                System.out.println("Error en el servidor: " + e.getMessage());
            }
        }

    }
    //Método para buscar libro por ISBN

    public Libro buscarPorISBN(String ISBN) {

        System.out.println("Buscando el libro con ISBN: " + ISBN);

        for (Libro item : libros) {
            System.out.println("Comparando con el libro ISBN: " + item.getISBN());

            if (ISBN.equals(item.getISBN())) {

                System.out.println("Libro encontrado: " + item);
                return item;
            }
        }
        System.out.println("No se encontró ningún libro con ese ISBN");
        return null;
    }
    //Método para buscar por titulo

    public Libro buscarPorTitulo(String titulo) {

        for (Libro item : libros) {
            if (titulo.equals(item.getTitulo())) {
                return item;
            }
        }
        System.out.println("No se encontró ningún libro con ese Titulo");
        return null;
    }

    //Método para buscar libros por autor

    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> aux = new ArrayList<>();

        for (Libro item : libros) {
            if (autor.equals(item.getAutor())) {
                aux.add(item);

            }

        }
        return aux;
    }

    public String añadirLibro(Libro libro) {
        libros.add(libro);
        String respuesta = "El libro ha sido añadido";

        return respuesta;


    }
}


