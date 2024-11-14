import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LanzadorTriangulo {

    public static void main(String[] args) {

        /*Creo un Array con los tres parametros y al crear el proccesBuilder de cada proceso le doy la posición
        * del Array que quiero que pase como argumento casteandola a String.*/

        int[] filas = {5, 7, 9};
        String classpath = System.getProperty("java.class.path");


        /*Crear los procesos al crear cada proceso le paso la posicion del array que tiene que castear, para que me pase
           esa posicion como argumento*/

        ProcessBuilder proceso1, proceso2, proceso3;
        proceso1 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", String.valueOf(filas[0]));
        proceso2 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", String.valueOf(filas[1]));
        proceso3 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", String.valueOf(filas[2]));


        //Triangulo5
        proceso1.redirectError(new File("erroresTriangulo5.txt"));
        proceso1.redirectOutput(new File("triangulo5.txt"));

        //Triangulo7
        proceso2.redirectError(new File("erroresTriangulo7.txt"));
        proceso2.redirectOutput(new File("triangulo7.txt"));


        //Triangulo9

        proceso3.redirectError(new File("erroresTriangulo9.txt"));
        proceso3.redirectOutput(new File("triangulo9.txt"));

        //Iniciar los procesos y capturar la excepcion

        try {
            proceso1.start();
            proceso2.start();
            proceso3.start();
            System.out.println("Proceso ejecutado");
            System.out.println("Proceso finalizado revise los archivos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
