/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import vista.progreso;

/**
 *
 * @author AN
 */
public class segundo_plano extends SwingWorker<String, Integer>{

    private registro_masivo rm;
    private Archivos doc;
    private progreso v;
    private registro_pdf rpdf;
    private Gestor_Ruta gr;

    private int completo =0;
    private int opcion=0;
    private String carrera, ruta;

    
//---CONSTRUCTORES
    public segundo_plano(registro_masivo rm, progreso v, int opcion, String carrera) {
        this.rm = rm;        
        this.v = v;
        this.opcion = opcion;
        this.carrera = carrera;
       gr = new Gestor_Ruta();
        
        v.setVisible(true);
    }

//---MANIPULACION INTERFAZ GRAFICA
private void configuracion_barra_progreso(String titulo, int maximo){

    v.setTitle("AVANCES "+titulo.toUpperCase());
    v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    v.setLocationRelativeTo(null);
    

    v.getPrincipal().setMinimum(0);
    v.getPrincipal().setMaximum(maximo);
    v.getPrincipal().setStringPainted(true);
    


}





//---PROCESOS EN SEGUNDO PLANO
    @Override
    protected String doInBackground() throws Exception {
           // this.masivo_depurador("LIC. CONTADURIA PUBLICA",this.ruta);
            this.masivo_depurador(this.carrera);
            System.out.println("TERMINADO");
        
    return "FINALIZADO EL PROCESO";
    }

    @Override
    protected void done() {
        imagenes ima = new imagenes();
        Toolkit.getDefaultToolkit().beep();
        ima.mensaje_informacion("OPERACION TERMINADA", "AVANCES", "finalizar", "png");
        v.dispose();
    }

    @Override
    protected void process(List<Integer> valor) {
        System.out.println("Traza proceso "+v.getTitle()+" - "+valor.get(0));
        v.getPrincipal().setValue(valor.get(0));
    }






//----------------PROCESOS DE TODO LO QUE SE QUIERE HACER----------------------


/**LA ruta debe ser colocada de la siguiente manera RUTA= d://REPORTES/INDICES CARRERAS/....
 y donde estan los puntos suspensivos automaticamente se colocara ...notas_carrera.txt*/
private void masivo_depurador(String carrera){
    
   ruta = gr.guardar_archivo();
    if(ruta!=null){
    
                DecimalFormat df = new DecimalFormat("0.00");

                rm.lista_estudiantes_carrera(new conexion_base_de_datos().getConexion(),carrera);
                int conteo=0;
                int filas=0;

                this.configuracion_barra_progreso(carrera, (rm.getEst_x_carrera().size()-1) );
                rm.setRuta_externa(ruta);
                
            for(int x=0; x<rm.getEst_x_carrera().size(); x++){ //recorriendo listado de estudiantes
                System.out.println("posicion: "+x+" / "+rm.getEst_x_carrera().size());
                publish(x);//sacando informacion al exterior


                            v.getInformacion_1().setText("Estudiante: "+rm.getEst_x_carrera().get(x)+"  "+x+"/"+(rm.getEst_x_carrera().size()-1));
                            System.out.println("___________CEDULA: ("+rm.getEst_x_carrera().get(x)+")__________________ESTUDIANTE N°: ("+x+") de ("+(rm.getEst_x_carrera().size()-1)+")_________________________________");
                            rm.depurador_estudiantes_semestres(rm.getEst_x_carrera().get(x), carrera);//depurando estudiante por estudiante
                            
                            //----------------------------------------
                        /*    for(int i=0;i<rm.getCant_mat().size();i++){//recorriendo la cantidad de materia por semestre
                                System.out.println("semestre "+(i+1)+" cantidad materias= "+rm.getCant_mat().get(i));
                                doc.escritor_textos("d://REPORTES/MATERIAS_RESTANTES/analisis_materia_por_semestre","semestre "+(i+1)+" cantidad materias= "+rm.getCant_mat().get(i) );
                                conteo=conteo+rm.getCant_mat().get(i);//contando para sacar el total de materias que debe
                            }//fin recorrido cantidad de materia por semestre
*/
                       /*     for(int i=0; i<rm.getEst_y_mat().size(); i=i+3){
                                doc.escritor_textos("d://REPORTES/MATERIAS_RESTANTES/analisis_estudiante_materia.txt","cedula: "+rm.getEst_y_mat().get(i+0)+"; codigo: "+rm.getEst_y_mat().get(i+1)+"; materia: "+rm.getEst_y_mat().get(i+2));
                                System.out.println("cedula: "+rm.getEst_y_mat().get(i+0)+"; codigo: "+rm.getEst_y_mat().get(i+1)+"; materia: "+rm.getEst_y_mat().get(i+2));
                            }*/
                            //----------------------------------------
                            

            }//fin recorrido listado por estudiantes
    }else{
        new imagenes().mensaje_informacion("Este proceso ha sido detenido intencionalmente.", "CALCULOS ESPECIALES", "exclamacion", "png");
       // this.done();
    }
}












public static void main(String [] args){
    registro_masivo rm = new registro_masivo();
    progreso p = new progreso();

    segundo_plano sp = new segundo_plano(rm, p, 1,"TSU EN TURISMO");
    sp.execute();
    System.exit(0);
    //sp.cancel(true);

}





}//fin de la clase
