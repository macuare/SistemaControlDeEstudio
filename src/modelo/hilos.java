/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import vista.progreso;

/**
 *
 * @author AN
 */
public class hilos extends Thread{
    private progreso p;
    private int opcion;
    private boolean finalizar=false;
    private String titulo_proceso=null, informacion_1=null;
    private int avance=0, inicio=0, fin=100;
    private imagenes ima;
    
    public hilos(String titulo_proceso,int opcion, boolean finalizar,progreso p) {
        this.p=p;
        this.opcion=opcion;        
        this.finalizar = finalizar;
        this.titulo_proceso = titulo_proceso;
        ima = new imagenes();
    }

    
    
    public boolean isFinalizar() {
        return finalizar;
    }

    public void setFinalizar(boolean finalizar) {
        this.finalizar = finalizar;
    }

    public int getAvance() {
        return avance;
    }

    public void setAvance(int avance) {
        this.avance = avance;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public String getInformacion_1() {
        return informacion_1;
    }

    public void setInformacion_1(String informacion_1) {
        this.informacion_1 = informacion_1;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    
    
    
    
    /*
public void cancelados(String carrera, String periodo, int maximos_periodos_reprobados,){

    
     rm.estudiantes_inscritos(new conexion_base_de_datos().getConexion() ,carrera , periodo);//listando los estudiantes de la jornada de inscripcion
     System.out.println("CARRERA: "+carrera+" - POBLACION: "+(rm.getAlumnos().size()/2)+" estudiantes");

    for(int x=0;x<rm.getAlumnos().size(); x=x+2){//recorriendo todos los alumnos inscritos
        
        
        System.out.println("CONTEO: "+(x/2)+" - "+(rm.getAlumnos().size()/2)+" Cedula: "+rm.getAlumnos().get(x)+"  pensum: "+rm.getAlumnos().get(x+1));
        this.lista_cancelados(maximos_periodos_reprobados, maximas_materias_reprobados, rm.getAlumnos().get(x),rm.getAlumnos().get(x+1),carrera);//calculando los cancelados por carrera
    //  rm.depurador_estudiantes_semestres(rm.getAlumnos().get(x), "LIC. ECONOMIA SOCIAL");

    }




}
*/
    
        
    
    
    @Override
    public void run() {
        super.run();
        p.setVisible(true);//haciendo visible la ventana
        p.getPrincipal().setMinimum(this.getInicio());//estableciendo el valor minimo
        p.getPrincipal().setMaximum(this.getFin());//estableciendo el valor maximo
        p.getPrincipal().setValue(this.getAvance());//estableciendo el valor actual
        p.setTitle(titulo_proceso);
        
            while(true){
                System.out.println("PROCESANDO.. "+this.getAvance());
                p.getPrincipal().setMinimum(this.getInicio());//estableciendo el valor minimo
                p.getPrincipal().setMaximum(this.getFin());//estableciendo el valor maximo
                
                p.getPrincipal().setValue(this.getAvance());//estableciendo el valor actual
                p.getInformacion_1().setText(this.getInformacion_1());//estableciendo algun comentario o algo de interes a mostrar
                    
                if(this.getAvance()>=this.getFin())break;//se rompe el while
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
            System.out.println("TERMINADO EL PROCESO...!!!");
            ima.mensaje_informacion("EL PROCESO HA TERMINADO CON EXITO...!!!", titulo_proceso, "finalizar", "png");
            p.dispose();
    }

    
    
    
    
    
    
    
    
    
    
   
}//fin de la clase
