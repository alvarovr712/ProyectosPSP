import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

    public static void main(String[] args) {

        /*He creado dos procesos distintos con el ProcessBuilder para cada tipo de examen en el que he metido aparte de hacia donde debe ir que es a la clase
        * Principal le he pasado los examinados como argumento para que los recoja en la otra clase.
        * He creado dos file para que guarde a los examinados y sus examenes como especifica el ejercicio.*/



        String classpath = System.getProperty("java.class.path");

        System.out.println("Classpath: " + classpath);


        ProcessBuilder primerExamen = new ProcessBuilder("java","-cp",classpath,"Principal","Alvaro","Maria","Nano");

        File examen1 = new File("examen1.txt");
        primerExamen.redirectOutput(examen1);

        ProcessBuilder segundoExamen = new ProcessBuilder("java","-cp",classpath, "Principal", "Rosa","Miguel","Pedro");

        File examen2 = new File("examen2.txt");
        segundoExamen.redirectOutput(examen2);

        try {
            Process proceso1 = primerExamen.start();
            Process proceso2 = segundoExamen.start();


            proceso1.waitFor();
            proceso2.waitFor();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
