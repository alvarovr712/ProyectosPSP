import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {



    private Queue<String> colaExamenes;



    public BufferExamenes() {

        colaExamenes = new LinkedList<String>();

    }

    public synchronized void fabricarNuevoExamen(String codigo) {

        colaExamenes.add(codigo);
        notify();


    }

    public synchronized String consumirExamen() {


        while(colaExamenes.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        if(!colaExamenes.isEmpty()){
            String examen = colaExamenes.remove();
            return examen;
        }else{
            return null;
        }

    }
}
