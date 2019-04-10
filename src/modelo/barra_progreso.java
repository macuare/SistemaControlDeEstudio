/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import vista.masivos;

/**
 *
 * @author AN
 */
public class barra_progreso extends SwingWorker<Double, Integer>{

   private registro_masivo rm;
    private final JLabel etiqueta;
    // Barra de progreso que vamos a ir incrementando.
    private final JProgressBar progreso;

    /**
     * Le pasamos la etiqueta que tiene que poner "Hecho" cuando termine la
     * tarea y el barra de progreso que queremos que se vaya a actualizar.
     *
     * @param unaEtiqueta
     * @param unaBarraProgreso
     */
    public barra_progreso(JLabel unaEtiqueta, JProgressBar unaBarraProgreso, registro_masivo rm) {
        etiqueta = unaEtiqueta;
        progreso = unaBarraProgreso;
        this.rm = rm;
    }

    /**
     * Tarea que tarda mucho y se ejecutara en un hilo separado.
     */
    @Override
    protected Double doInBackground() throws Exception {
        System.out.println("doInBackground() esta en el hilo "
                + Thread.currentThread().getName());
            
        this.rm.lista_estudiantes_carrera(new conexion_base_de_datos().getConexion(),"TSU EN TURISMO");
        int conteo=0;
        int filas=0;

        progreso.setMaximum(rm.getEst_x_carrera().size());
        progreso.setStringPainted(true);
        

       for(int x=0; x<rm.getEst_x_carrera().size(); x++){ //recorriendo listado de estudiantes
           publish(x);
           System.out.println("_____________________________ESTUDIANTE N°: ("+x+")_________________________________");
           progreso.setString("CI: "+rm.getEst_x_carrera().get(x));

           rm.depurador_estudiantes_semestres(rm.getEst_x_carrera().get(x), "civil");//depurando estudiante por estudiante
            for(int i=0;i<rm.getCant_mat().size();i++){//recorriendo la cantidad de materia por semestre
                System.out.println("semestre "+(i+1)+" cantidad materias= "+rm.getCant_mat().get(i));
                conteo=conteo+rm.getCant_mat().get(i);//contando para sacar el total de materias que debe
            }//fin recorrido cantidad de materia por semestre
                conteo=0; //inicializando la variable para el conteo por cada estudiante de materias que en total debe


    }//fin recorrido listado por estudiantes


        progreso.setMaximum(100);
        return  100.0;
    }

    /**
     * Tarea terminada, SwingWorker llama a este metodo en el hilo de despacho
     * de eventos. Aqui actualizamos la etiqueta para que ponga "Hecho".
     */
    @Override
    protected void done() {
        System.out.println("done() esta en el hilo "
                + Thread.currentThread().getName());
        etiqueta.setText("hecho");
    }

    /**
     * SwingWorker llama a este metodo en el hilo de despacho de eventos cuando
     * llamamos a publish() y le pasa los mismos parametros que nosotros
     * pongamos en publish().<br>
     * En este ejemplo, nosotros pasamos el valor de la barra de progreso.
     */
    @Override
    protected void process(List<Integer> chunks) {
        System.out.println("process() esta en el hilo "
                + Thread.currentThread().getName());
        progreso.setValue(chunks.get(0));
    }







public static void main(String args[]){

     // La ventana principal, con una etiqueta que pondrá "Hecho" cuando
        // termine el SwingWorker.
        JFrame ventanaPrincipal = new JFrame();
        JLabel etiqueta = new JLabel("Sin hacer");
        ventanaPrincipal.getContentPane().add(etiqueta);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.pack();
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);

        // Una ventana con la barra de progreso
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        JDialog dialogoProgreso = new JDialog(ventanaPrincipal, "progreso");
        dialogoProgreso.getContentPane().add(barraProgreso);
        dialogoProgreso.pack();
        dialogoProgreso.setLocationRelativeTo(null);

        registro_masivo rm = new registro_masivo();


        // Hacemos visible la barra de progreso y lanzamos
        // el SwingWorker.
        dialogoProgreso.setVisible(true);
        barra_progreso worker = new barra_progreso(etiqueta, barraProgreso,rm);
        worker.execute();
        try {
            // Mostramos el resultado y ocultamos la barra de progreso.
            // Esta llamada se queda bloqueada hasta que termine
            // el SwingWorker. Debemos ocultar la barra de progreso
            // inmediatamente después.
            System.out.println("El resultado es " + worker.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(barra_progreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(barra_progreso.class.getName()).log(Level.SEVERE, null, ex);
        }


}









}//fin de la clase
