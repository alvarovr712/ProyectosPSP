import java.util.Random;

public class Examinado  implements Runnable{

    private Thread hilo;
    BufferExamenes buffer;
    private String alumno;



    public Thread getHilo() {

        return hilo;

    }

    public Examinado(String alumno, BufferExamenes generador) {
        this.alumno = alumno;

// Construye el hilo. El nombre será el nombre del alumno.

        hilo = new Thread(this, alumno );

// Establece el valor de la propiedad buffer
        this.buffer = generador;

// Inicia el hilo.
        hilo.start();



    }
    @Override

    public void run() {
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {

            simularExamen(codigoExamen);
        } else {
            System.out.println("Agotado tiempo de espera y no hay más exámenes");
        }
    }
    // Simula aquí un examen de 10 preguntas
    //Creo un metodo simularExamen en el que declaro un Array con las respuestas entro con un
    //for y dentro del array declaro respuestas seran aleatorias con ramdon teniendo como tope
    //el 10 que sera la longitud (length) del array

    private void simularExamen(String codigoExamen) {
        Random random = new Random();
        String[] respuestas = {"A", "B", "C", "D", "-"}; // Posibles respuestas

        for (int i = 1; i <= 10; i++) {
            // Simula una respuesta aleatoria para cada pregunta
            String respuesta = respuestas[random.nextInt(respuestas.length)];
            System.out.println(codigoExamen + ";" + alumno + "; Pregunta " + i + ";" + respuesta);
        }

    }
}
