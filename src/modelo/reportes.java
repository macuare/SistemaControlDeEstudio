/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class reportes {

    private Archivos doc;
    private imagenes ima;


    


    public reportes() {
        doc = new Archivos();
        ima = new imagenes();
    }


    













public String arreglo_cod_mat(String codigo_materia){
    String cod_real=null;

        //codigo_materia.substring(3, 8);
        //codigo_materia.substring(0, 3);
if(codigo_materia.length()>=8){
    cod_real=codigo_materia.substring(0, 3)+"-"+codigo_materia.substring(3, 8);    //ensamblando la nota ejm. de MAT32543 a MAT-32543
    }else{System.out.println("REVISAR--------CODIGO--------MATERIA--"+codigo_materia);}

return cod_real;
}




public void reporte_notas_egresados(String ruta, LinkedList<String> egresados){

    System.out.println("CAPACIDAD "+egresados.size());

  if(egresados.size()>0){//comprobando que halla registros

            try {
                doc.escritor_csv_2(ruta, egresados, 13);
                ima.mensaje_informacion("REPORTE DE NOTAS DE EGRESADOS REALIZADO CON EXITO","REPORTE NOTA EGRESADOS", "reportes", "png");
               } catch (IOException ex) {
                Logger.getLogger(reportes.class.getName()).log(Level.SEVERE, null, ex);
                ima.mensaje_informacion("Error al intentar guradar los registros del reporte de notas de egresados.\n"+ex.getMessage(), "ERROR REPORTE EGRESADOS", "error", "png");
            }

      
  }else{ima.mensaje_informacion("NO EXISTEN REGISTROS PARA REALIZAR EL REPORTE DE EGRESADOS", "ADVERTENCIA","precaucion" , "png");}


 egresados.clear();//limpiando el LinkedList
}




    

}//fin de la clase
