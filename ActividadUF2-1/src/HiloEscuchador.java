import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TreeMap;

public class HiloEscuchador implements Runnable {

    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    private TreeMap<String,Producto> productos;

    public HiloEscuchador(Socket cliente, TreeMap<String, Producto> productos) {
        numCliente++;
        hilo = new Thread(this, "Cliente" + numCliente);
        this.enchufeAlCliente = cliente;
        this.productos = productos;
        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + hilo.getName());
        try {
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();
            String texto = "";
            while (!texto.trim().equals("FIN")) {
                byte[] mensaje = new byte[100];
                entrada.read(mensaje);
                texto = new String(mensaje).trim();
                if (texto.trim().equals("FIN")) {
                    salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
                    System.out.println(hilo.getName() + " ha cerrado la comunicación");
                } else {
                    Producto producto = productos.get(texto);
                    if(producto != null){
                        salida.write(("El prodcuto es: " + producto.toString()).getBytes());
                    }else{
                        salida.write("Producto no encontrado".getBytes());
                    }
                }
            }
            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
