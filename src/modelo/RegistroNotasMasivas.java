/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 *
 * Created by @author andy on 22-may-2016
 */
public class RegistroNotasMasivas {
    private registro_pdf rpdf;
    private DecimalFormat df = new DecimalFormat("0.00");
    
    public RegistroNotasMasivas(){
        rpdf = new registro_pdf();
  
    }

void formato_contenido_tabla(LinkedList<String> orden, int z, LinkedList<String> pensum, String cedula,String carrera,String turno, String matricula, String nucleo_extension){
                                   int multiplicacion = 0, uc = 0, nota = 0;
                                   
                                   String aux = "", aux2="0";
                                   
                                   if(orden.get(z+2) != null && Integer.valueOf(orden.get(z+2)) > 0) nota = Integer.valueOf(orden.get(z+2));// se considera la definitiva
                                   if(orden.get(z+3) != null && Integer.valueOf(orden.get(z+3)) > 0) nota = Integer.valueOf(orden.get(z+3));// se considera la reparacion
                                   if(orden.get(z+6) != null && Integer.valueOf(orden.get(z+6)) > 0) nota = Integer.valueOf(orden.get(z+6));// se considera la def rep.
                                   //se toma la nota de la ultima condicion que se cumpla
                                   
                                    System.out.println("CONTENIDO:");
                                    
                                    //PERÍODO ACADÉMICO DE LA MATERIA
                                    System.out.println("periodo: "+rpdf.efecto_intesivo_periodo(orden.get(z+5)));
                                    
                                    //SEMESTRE DE LA MATERIA
                                    if(orden.get(z+4).equalsIgnoreCase("EQUIVALENCIA")){aux2 = "0";}
                                    else{aux2 = rpdf.semestre_materia(orden.get(z),orden.get(z+5) ,pensum);}//si es una materia por equivalencia se coloca el 0 el semestre
                                    System.out.println("semestre: "+aux2);
                                    
                                    //CODIGO DE LA MATERIA
                                    String codigo_materia = orden.get(z);
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO") && (orden.get(z+1).contains("PASANTÍA") || orden.get(z+1).contains("PASANTIA") || orden.get(z+1).contains("TRABAJO ESPECIAL")) ){ codigo_materia = "";}        
                                    System.out.println("codigo: "+codigo_materia);
                                    
                                    //NOMBRE DE LA MATERIA
                                    String nombre = orden.get(z+1);
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO") && (nombre.contains("PASANTÍA") || nombre.contains("PASANTIA")) ){ nombre = "DESARROLLO DE PASANTÍA";}
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO") && (nombre.contains("TRABAJO ESPECIAL") ) ){ nombre = "DESARROLLO TRABAJO ESPECIAL DE GRADO";}
                                    if(nombre.contains("(ELECTIVA NO TECNICA)") && carrera.contains("INGENIER") == false){ nombre = nombre.replace("ELECTIVA NO TECNICA", "ELECTIVA");}
                                    System.out.println("nombre: "+nombre);
                                    
                                    //NOTA DEFINITIVA DE LA MATERIA
                                    System.out.println("DEFINITIVA: "+rpdf.sin_cero(orden.get(z+4),rpdf.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[0],orden.get(z+1)));
                                    
                                    //NOTA DE REPARACION DE LA MATERIA
                                    System.out.println("REPARACION:"+rpdf.nota_presentacion(orden.get(z+2),orden.get(z+3), orden.get(z+6),orden.get(z+4))[1]);
                                     
                                    //UNIDADES DE CREDITO
                                    uc = rpdf.uc_materia(orden.get(z), orden.get(z+5),pensum);
                                    if(uc<=0){ aux = "";}else{ aux = String.valueOf(uc);}
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO")) aux = ""; else aux = String.valueOf(uc);
                                    System.out.println("credito: "+uc);
                                    
                                    //PUNTOS
                                   // if(uc<=0 || orden.get(z+4).equalsIgnoreCase("EQUIVALENCIA")){ aux = "";}else{ aux = String.valueOf(uc*nota);}
                                    aux = rpdf.puntos_visuales_pasantias(orden.get(z+1),uc ,orden.get(z+4), String.valueOf(uc*nota));
                                    System.out.println("puntos: "+aux);
                                    
                                    //CONDICION DE LA MATERIA
                                    System.out.println("condicion: "+rpdf.sin_condicion(orden.get(z+4),orden.get(z+3)));
                                    
}    
    

public void formato_record_academico(LinkedList<String> orden, String nucleo_extension, String cedula, String estudiante, String carrera, String matricula, int conteo ,int conteo_2, LinkedList<String> pensum, String turno){
    System.out.println("FORMATO -->RECORD ACADEMICO: "+orden.size());
        
    String semestre_actual = rpdf.determinar_semestre_actual(orden, pensum);
    
    System.out.println("......................................................RECORD ACADEMICO.........................");
    System.out.println("INFORMACION: ");
    System.out.println(
            "Cédula: "+cedula+" "+
            "Estudiante: "+estudiante+" "+
            "Matrícula: "+matricula+" "+
            "Carrera: "+carrera+" "+
            "Turno: "+turno+" "+
            "Semestre Actual: "+semestre_actual
    );
    

   rpdf.setComp_periodo(orden.get(5));

   for(int z=0;z<orden.size();z=z+11){//escribiendo las materias del alumno en el archivo pdf
         
     if(rpdf.getComp_periodo().equalsIgnoreCase(rpdf.ajuste_temporal_semestre_equivalencias(orden.get(z+5))) ){//si son el mismo periodo                                       
                                       
        this.formato_contenido_tabla(orden, z, pensum, cedula, carrera, turno, matricula, nucleo_extension);                                       
                                       
     }else{//en caso que sean distinto los periodos, copio el espacio en blanco y luego la fila que corresponde
                  
                                       System.out.println("Acumulado hasta el período: "+rpdf.efecto_intesivo_periodo(rpdf.getComp_periodo())); //semestre de la materia. actuando sobre los semestres de equivalencia e intensivos                                       
                                       System.out.println("-------:::> "+((rpdf.getAcu_per().size()/3))+" ---- indice:> "+(conteo_2/3+1));
                                       System.out.println("         :   "+rpdf.getAcu_per().get(conteo_2+0)+
                                                                 "      "+rpdf.getAcu_per().get(conteo_2+1)+
                                                                 "       Indice  "+rpdf.presentacion_indice(rpdf.getAcu_per().get(conteo_2+2)));
                                       
                                        conteo_2 = conteo_2+3;//es para recorrer el LinkedList que guarda el valor acumulado por periodo

                                        //..........................................................................................


                                    //GARANTIZANDO COPIAR EL REGISTRO ACTUAL

                                         this.formato_contenido_tabla(orden, z, pensum, cedula, carrera, turno, matricula, nucleo_extension);
                                        
                                         rpdf.setComp_periodo(orden.get(z+5));
                                          
      }

    }//fin del recorrido del LinkedList orden
                            
                                System.out.println("");
                                System.out.println("    Total de U.C. Equivalencias: "+rpdf.getUc_equivalencias()+"\n"+
                                                           "     Total de U.C. Cursadas: "+rpdf.getUc_cursadas()+"\n"+
                                                           "     Total de U.C.: "+(rpdf.getUc_cursadas()+rpdf.getUc_equivalencias())+"\n"+
                                                           "     Total de Puntos: "+rpdf.getPuntos()+"\n"+
                                                           "     ÍNDICE ACADÉMICO: "+df.format(rpdf.getIndice_academico()));
                            
                                //------------------------------------

        //_-------------------------FIN RECORD ACADEMICO-----------------------------

}    
    
    
    //metodos de la clase
public void record_academico_texto(String cedula, String estudiante, String carrera, String turno, String nucleo_extension, String inscrito  , LinkedList<String> pdf_mi, LinkedList<String> orden, LinkedList<String> pensum, String periodo_acade) {
encriptacion encriptar = new encriptacion();
char c[] = encriptar.conversor(Math.random()+"-"+cedula, "SHA-1").toCharArray();


            int conteo=0;
            int conteo_2=0;
            String matricula=null;
            rpdf.setNumero_pagina(0);
            registro_autoridades ra = new registro_autoridades();//creando una instancia
            ra.lista_autoridades(new conexion_base_de_datos().getConexion());//creando lista de autoridades

            matricula = rpdf.opsu_matricula(inscrito,rpdf.opsu_sede(nucleo_extension), rpdf.opsu_carrera(carrera), rpdf.opsu_turno(turno) , cedula);

                    //------------------------------------orden de  merito

                     // this.tabla_merito(cedula,matricula, this.getPuntos(), this.getPuntos_merito(), this.getUc_cursadas(),  df.format(this.getIndice_academico()),  df.format(this.getIndice_merito()));

                   //--------------------------------------------

            System.out.println("\tNOTAS DEL ESTUDIANTE: "+cedula);

            System.out.println("RECORD ACADEMICO: "+orden.size());

                if(orden.size()<=0){
                      System.out.println("Este alumno no tiene materia registrada");                      
                  }else{                      
                        System.out.println("......................................................RECORD ACADEMICO.........................");
                        
                        
                        
                  }
                
                           //PAGINA SIGUIENTE . RECORD ACADEMICO
                this.formato_record_academico(orden,                                            
                                            nucleo_extension,
                                            cedula,
                                            estudiante,
                                            carrera,
                                            matricula,
                                            conteo,
                                            conteo_2,                                            
                                            pensum,                                            
                                            turno);
                    
                                                    
                                                    
                                                    
            
               
//limpiando variableas acumulativas
                rpdf.setUc_cursadas(0);
                rpdf.setNumero_pagina(0);
                rpdf.setUc_aprobadas(0);
                rpdf.setUc_inscritas(0);
                rpdf.setPuntos(0);
                rpdf.setUc_equivalencias(0);
                rpdf.getAlmacen().clear();
                orden.clear();//limpiando materias

        //_-------------------------FIN RECORD ACADEMICO-----------------------------
 
}
    






}// fin de la clase
