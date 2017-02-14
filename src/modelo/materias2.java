/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author AN
 */
public class materias2 {

  private imagenes ima;
  private materias materia;

  private boolean servicio=false;

    public materias2() {
        ima = new imagenes();
        materia = new materias();
    }

    public boolean isServicio() {
        return servicio;
    }

    public void setServicio(boolean servicio) {
        this.servicio = servicio;
    }







public int conteo_materias_aprobadas(materias m){
    //System.out.println("[Metodo conteo_materias_aprobadas]");
 int cant_materias=0;

//for(int p=0; p<m.getPensum().size(); p=p+7){//recorriendo las materias del pensum
 for(int p=0; p<m.getPensum().size(); p=p+10){//recorriendo las materias del pensum

   // for(int z=0; z<m.getOrden().size(); z=z+6){//recorriendo el record
    for(int z=0; z<m.getOrden().size(); z=z+11){//recorriendo el record

    
        if(m.getPensum().get(p).equalsIgnoreCase(m.getOrden().get(z))){//si los codigos coinciden


            if(materia.evaluacion(z, m.getOrden())){//verificando si paso las materias de interes
                cant_materias=cant_materias+1;//contando la cantidad de materias aprobadas
            }
            
       /*      if( ((Integer.valueOf(m.getOrden().get(z+2))>=10 || //que halla aprobado la materia
           (m.getOrden().get(z+3)!=null && Integer.valueOf(m.getOrden().get(z+3))>=10)) && // o que si reparo que la halla aprobado
          //  m.getOrden().get(z+4)!="POR INASISTENCIA" && //y que no halla perdido la materia por inasistencia
          ( m.getOrden().get(z+4)!="POR INASISTENCIA" && (m.getOrden().get(z+4)!="REPROBÓ-25% DE INASISTENCIA" || m.getOrden().get(z+4)!="REPROBO-50% DE INASISTENCIA") ) && //y que no halla perdido la materia por inasistencia
            m.getOrden().get(z+4)!="ERROR") || //y que la condicion de la materia este dentro de las pautadas
            m.getOrden().get(z+4).equalsIgnoreCase("APROBÓ") ||//o que la condicion de la materia este en aprobado
            m.getOrden().get(z+4).equalsIgnoreCase("EQUIVALENCIA") //o que la tenga aprobada por equivalencia...no se toma la nota
          ){
           // System.out.println("paso "+m.getOrden().get(z+1));//muestra el nombre de la materia aprobada

            cant_materias=cant_materias+1;//contando la cantidad de materias aprobadas


            }
       */

        }

     }//fin recorrido record


}//fin recorrido pensum
 
 
return cant_materias;
}



/**Este metodo es para saber si tiene alguna materia de servicio comunitario inscrito
 * y en tal caso que asi sea se verifica que la halla cumplido los requerimientos sino
 * se le habilitara la misma pero en condicion de repitencia*/
public void servicio_comunitario(materias m){
//System.out.println("Metodo servicio_comunitario");
int asistente=0;
int cantidad=0;
boolean salir=false;
this.setServicio(false);//inicializando el servicio comunitario


    System.out.println("\n......ANALISIS DE SERVICIO COMUNITARIO.......");


 //for(int x=0; x<m.getSer_comunitario().size(); x=x+7){//recorriendo materias del servicio comunitario
    for(int x=0; x<m.getSer_comunitario().size(); x=x+10){//recorriendo materias del servicio comunitario
   salir=false;

                            //    System.out.println(m.getSer_comunitario().get(x+2));


        //for(int z=0; z<m.getOrden().size(); z=z+6){//recorriendo las materias del record del alumno
          for(int z=0; z<m.getOrden().size(); z=z+11){//recorriendo las materias del record del alumno
                              //  System.out.println(m.getSer_comunitario().get(x+2)+" - - - "+m.getOrden().get(z+1));

             if( m.getSer_comunitario().get(x).toString().equalsIgnoreCase(m.getOrden().get(z).toString())){// 1_si coincide la materia del servicio comunitario con una del record

                    
                            
                            /* if( m.getOrden().get(z+4)!="POR INASISTENCIA" && //y que no halla perdido la materia por inasistencia                                     
                                 m.getOrden().get(z+4)!="ERROR" && //y que la condicion de la materia este dentro de las pautadas
                                (m.getOrden().get(z+4).equalsIgnoreCase("APROBÓ") ||//o que la condicion de la materia este en aprobado
                                 m.getOrden().get(z+4).equalsIgnoreCase("EQUIVALENCIA") ||
                                 m.getOrden().get(z+4).equalsIgnoreCase("NORMAL")
                                 ) //o que la tenga aprobada por equivalencia...no se toma la nota
                               ){*/
                                       //System.out.println("ETAPA1");

                               if(materia.evaluacion(z, m.getOrden())){
                    
                    
                                    System.out.println("\nMateria Servicio Comunitario( "+m.getSer_comunitario().get(x)+" - "+m.getSer_comunitario().get(x+2)+" ) Aprobada");
                                    break;//si la materia cumple con todo entonces revisar la otra materia de servicio comunitario


                                }else{//en caso de que no haya aprobado la materia
                                        //a pesar de no cumplirse lo anterior pero al menos es la materia, habilitarla en condicion de repitencia
                                           // System.out.println("ETAPA2");

                                            m.getLista_autorizada().add(m.getSer_comunitario().get(x));//codigo de la materia
                                            m.getLista_autorizada().add(m.getSer_comunitario().get(x+2));//nombre de la materia
                                            m.getLista_autorizada().add("P");//condicion de la materia(repitencia)
                                            m.getLista_autorizada().add(m.getSer_comunitario().get(x+1));//semestre de la materia

                                            System.out.println("\nMateria Servicio Comunitario( "+m.getSer_comunitario().get(x)+" - "+m.getSer_comunitario().get(x+2)+" ) Reprobada");
                                            System.out.println("materia: "+m.getSer_comunitario().get(x+2)+" condicion: Repitencia....autorizado");

                                            this.setServicio(true);//puede ver servicio comunitario
                                            salir=true;//se usa para romper el primer for de recorridos de materias servicio comunitario
                                            break;
                                       
                                         }

             }else{//en caso de que no coincida


                //System.out.println("ETAPA3 "+((z/6)+1)+" "+m.getOrden().size()/6);

                //if(((z/6)+1)>=m.getOrden().size()/6){//y se reviso todo el record del alumno sin exito de coincidencia
                if(((z/11)+1)>=m.getOrden().size()/11){//y se reviso todo el record del alumno sin exito de coincidencia
                                   //         System.out.println("ETAPA4");

                        cantidad=this.conteo_materias_aprobadas(m);

                     //System.out.println("N° Materias Vistas: "+m.getOrden().size()/6+" N° Materias Pensum: "+m.getPensum().size()/7+" cantidad materias aprobadas: "+cantidad+" cantidad necesarias: "+(m.getPensum().size()/7)/2);
                     System.out.println("N° Materias Vistas: "+m.getOrden().size()/11+" N° Materias Pensum: "+m.getPensum().size()/7+" cantidad materias aprobadas: "+cantidad+" cantidad necesarias: "+(m.getPensum().size()/7)/2);

                        if(cantidad >=(m.getPensum().size()/7)/2){//si la cantidad de materias aprobadas es mayor o igual al 50% de las materias de la carrera se le habilitara servicio comunitario
                       // System.out.println("ETAPA5"+(m.getPensum().size()/7)/2);

                            System.out.println("Codigo: "+m.getSer_comunitario().get(x)+" Materia: "+m.getSer_comunitario().get(x+2)+" condicion: Normal....autorizado");
                            
                                            m.getLista_autorizada().add(m.getSer_comunitario().get(x));//codigo de la materia
                                            m.getLista_autorizada().add(m.getSer_comunitario().get(x+2));//nombre de la materia
                                            m.getLista_autorizada().add("N");//condicion de la materia(normal)
                                            m.getLista_autorizada().add(m.getSer_comunitario().get(x+1));//semestre de la materia

                                            this.setServicio(true);//puede ver servicio comunitario
                                            salir=true;//rompiendo el segundo for....
                                            break;
                        }else{break;}//en caso de que no tenga la cantidad de materias aprobadas requeridas para inscribir servicio comunitario


                }



             }//fin 1
     

        }//fin recorrido record alumno

if(salir){break;}//se usa para romper este ciclo cuando se inscribe la materia en cond repitencia
      

 }//fin recorrido servicio comunitario







}//fin metodo servicio_comunitario








public void actividades(String materia){
System.out.println("Metodo Actividades");
Object[] opciones={"CULTURA","DEPORTE"};
int seleccion=0;

        if(materia.startsWith("ACTIVIDADES COMPLEMENTARIAS")){

            seleccion = JOptionPane.showOptionDialog(new JFrame(),
                                      "ELIJA EL TIPO DE ACTIVIDAD EXTRACURRICULAR\n"
                                      +"QUE DESEA CURSAR",
                                      "ACTIVIDADES COMPLEMENTARIAS",
                                      JOptionPane.YES_NO_OPTION,
                                      JOptionPane.QUESTION_MESSAGE,
                                      ima.imagenes("eleccion", "png"),
                                      opciones,
                                      opciones[0]);

          
        }


if(seleccion==0){System.out.println(seleccion+" CULTURA");}else{System.out.println(seleccion+" DEPORTE");}





    }















    

    

}//fin de la clase
