import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {


    private BufferExamenes buffer;

    private static int numeroExamen = 0;

    private Thread hilo;
    private int length;



    public ProductorExamenes(BufferExamenes buffer, int length) {

// Incrementa el contador de exámenes (variable numeroExamen).
        numeroExamen++;

// Construye el hilo. El nombre será la letra E seguida

// del valor de la variable numeroExamen.
        hilo = new Thread(this, "E" + numeroExamen);

// Establece el valor de la propiedad buffer
        this.buffer = buffer;
        this.length = length;

// Inicia el hilo.
        hilo.start();

    }

    @Override

    public void run() {

        int aa = LocalDateTime.now().getYear();

        /*En esta clase he cambiado respecto a el proyecto anterior metiendole un bucle for diciendole que fabrique tantos examenes como el length así no solo
        * un examinador hará el examen cuando lo produzca que es lo que me pasaba */

        for (int i = 0; i < length; i++) {


// Generación del código de examen.
            String codigo = this.hilo.getName() + "-" + aa;

// Añade el nuevo código al buffer de exámenes.

            buffer.fabricarNuevoExamen(codigo);


// Muestra un mensaje en consola informando sobre el

// código del examen que se acaba de producir.
            System.out.println("Producido examen " + codigo);

        }
    }
}
