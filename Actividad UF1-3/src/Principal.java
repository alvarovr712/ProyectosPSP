public class Principal {
    public static void main(String[] args) {



        /*En esta clase he creado un bucle for para recibir los argumentos de la clase Lanzador */
            System.out.println("Argumentos recibidos:");

            for (String item : args) {
                System.out.println(item);
            }


        //He creado un buffer donde se almacenan los examenes y he creado un bucle para tantos examenes como argumentos tenga. Tantos examenes como examinados tenga.
                BufferExamenes generador = new BufferExamenes();
                new ProductorExamenes(generador, args.length);



                for (String nombre : args) {
                    System.out.println("Nuevo examinado: " + nombre);
                    new Examinado(nombre, generador);
                }



        }
    }
