import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class RecibeSD {

    public static void main(String[] args) {
        try {
            InetSocketAddress direccion = new InetSocketAddress("192.168.1.36",5000);
            DatagramSocket ds = new DatagramSocket(direccion);
            System.out.println("Preparado para recibir");
            String texto = "";

               for(int i=1;i<=100;i++){
                   byte[] buffer = new byte[1024];
                   DatagramPacket paquete = new DatagramPacket(buffer,buffer.length);

                   ds.receive(paquete);
                   texto = new String(paquete.getData(),0,paquete.getLength()).trim();
                   System.out.println("Recibido: " + texto);
               }
                System.out.println("Todos los mensajes han sido recibidos");

            ds.close();
            System.out.println("Socket Datagram cerrado");
        } catch (SocketException | UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
