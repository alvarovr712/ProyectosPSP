import java.time.LocalDateTime;
import java.util.Scanner;

public class Triangulo {
    public static void main(String[] args)
    {
        System.out.println("Fecha y hora de inicio del proceso : " + LocalDateTime.now());
        if (args.length == 0)
        {
            System.out.println("Se requiere un argumento");
            return;
        }
         int filas = Integer.parseInt(args[0]);

        for (int i=filas; i>=1; i--)
        {
            for (int n=1; n<=i; n++)
            {
                System.out.print(n);
            }
            System.out.println();
        }
        System.out.println("Fecha y hora de finalización del proceso : " + LocalDateTime.now());
    }
}
