import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EnviaSD {
    public static void main(String[] args) {


        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress destino = InetAddress.getByName("192.168.1.36");
            String mensaje = "";
            while (!mensaje.equals("FIN")) {
               for(int i = 1; i<=100;i++){
                   String tiempo = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
                   mensaje = tiempo + "Paqueteenviado" + i;

                   byte[] buffer = mensaje.getBytes();
                   DatagramPacket carta = new DatagramPacket(buffer,buffer.length,destino,5000);
                   ds.send(carta);
                   System.out.println("Enviado: " + mensaje);
                   Thread.sleep(100);

               }
                System.out.println("Todos los paquetes han sido enviados");
               ds.close();
            }


        } catch (SocketException | UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}