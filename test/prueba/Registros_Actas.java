/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion_base_de_datos;

/**
 *
 * @author ANDY
 */
public class Registros_Actas {
    private conexion_base_de_datos cbd;
    /** ("cedula")//0<br>("estudiante")//1<br>("carrera")//2<br>("semestre")//3<br>("codigo")//4<br>("materia")//5<br>("uc")//6<br>("seccion")//7<br>("periodo")//8<br>("docente")//9<br>("condicion")//10<br> */
    private LinkedList<String> inscripciones;
   /** ("semestre")//0<br>("codigo")//1<br>("asignatura")//2<br>("hora_t")//3<br>("hora_p")//4<br>("hora_l")//5<br>("uc")//6<br>("en_aula")//7<br>("fuera_del_aula")//8<br>("laboratorio")//9<br>("total")//10<br>("autogestion")//11<br>("pensum")//12<br>("carrera")//13<br>("turno")//14 */
    private LinkedList<String> pensa;
    /**("cedula")//0<br>("turno")//1<br>("pensum")//2*/
    private LinkedList<String> vigencias;
    
    private String ruta;
    
    public Registros_Actas() {
        cbd = new conexion_base_de_datos();
        inscripciones = new LinkedList<>();
        pensa = new LinkedList<>();
        vigencias = new LinkedList<>();
        ruta = "C:\\Users\\ANDY\\Desktop\\SARECNI\\RECURSOS\\";
    }
    
    
    public void cargando_inscripciones(String periodo){
   System.out.println("cargando inscripciones");
        try {
            Connection con = cbd.getConexion();
            Statement sentencia = null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion where periodo = '"+periodo+"' order by docente, cedula;");
            while(resultado.next()){
                inscripciones.add(resultado.getString("cedula"));//0
                inscripciones.add(resultado.getString("estudiante"));//1
                inscripciones.add(resultado.getString("carrera"));//2
                
                inscripciones.add(resultado.getString("semestre"));//3
                inscripciones.add(resultado.getString("codigo"));//4
                inscripciones.add(resultado.getString("materia"));//5
                
                inscripciones.add(resultado.getString("uc"));//6
                inscripciones.add(resultado.getString("seccion"));//7
                inscripciones.add(resultado.getString("periodo"));//8
                
                inscripciones.add(resultado.getString("docente"));//9
                inscripciones.add(resultado.getString("condicion"));//10
                                
            }
            
            sentencia.close();
            resultado.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Registros_Actas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("inscripciones cargado con exito. "+(inscripciones.size()/11) );
    }
    
     public void cargando_pensa(){
   System.out.println("cargando todos los pensum");
  
        try {
            Connection con = cbd.getConexion();
            Statement sentencia = null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM pensum.pensa ORDER BY carrera, pensum, turno;");
            while(resultado.next()){
                
                pensa.add(resultado.getString("semestre"));//0
                pensa.add(resultado.getString("codigo"));//1
                pensa.add(resultado.getString("asignatura"));//2
                pensa.add(resultado.getString("hora_t"));//3
                pensa.add(resultado.getString("hora_p"));//4
                
                pensa.add(resultado.getString("hora_l"));//5                                
                pensa.add(resultado.getString("uc"));//6
                pensa.add(resultado.getString("en_aula"));//7
                pensa.add(resultado.getString("fuera_del_aula"));//8
                pensa.add(resultado.getString("laboratorio"));//9
                
                pensa.add(resultado.getString("total"));//10                
                pensa.add(resultado.getString("autogestion"));//11
                pensa.add(resultado.getString("pensum"));//12
                pensa.add(resultado.getString("carrera"));//13
                pensa.add(resultado.getString("turno"));//14
                
            }
            
            sentencia.close();
            resultado.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Registros_Actas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("pensa cargado con exito. "+(pensa.size()/15) );
    }
    
    public void cargando_datos_academicos(){
        
   System.out.println("cargando datos academicos");
  
        try {
            Connection con = cbd.getConexion();
            Statement sentencia = null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos ORDER BY carrera;");
            while(resultado.next()){
                
                vigencias.add(resultado.getString("cedula"));//0
                vigencias.add(resultado.getString("turno"));//1
                vigencias.add(resultado.getString("pensum"));//2
                
            }
            
            sentencia.close();
            resultado.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Registros_Actas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("datos academicos cargado con exito. "+(vigencias.size()/3) );
    }
    
    
    
    
    /**Metodo que permite agrupar por un registro en especifico. se especifica la longitud minima para ser considerado por el filtro */
    private void filtro(LinkedList<String> aux, String registro, int longitud){
        boolean coincidencia = false;
        if(registro.length()>longitud && registro != null){//evitando registros vacios
            if(aux.isEmpty()){
                aux.add(registro); //agregando primer registro
            }else{
                for(int i=0; i<aux.size(); i++){
                    if(registro.equalsIgnoreCase(aux.get(i))){
                        coincidencia = true;//existe. se sale del ciclo
                        break;
                    }else{
                        coincidencia = false;
                    }
                }
                
                if(coincidencia == false) aux.add(registro);//despues de revisar todo el registro no encontro nada, entonces se agrega el elemento
            }    
            
        }
    }
    
     private void filtro_con_indice(LinkedList<String> aux, String registro, int longitud){ 
         boolean coincidencia = false;
         
         if(registro.length() > longitud && registro != null){
             
            if(aux.isEmpty()){             
                aux.add("1;"+registro);//seccion 

            }else{
                for(int s=0; s<aux.size(); s++){//recirriendo los auxiliares
                    if(aux.get(s).split(";")[1].equalsIgnoreCase(registro)){
                        coincidencia = true;
                        break;
                    }else{
                        coincidencia = false;
                    }
                }//fin recorrido auxiliares

                     if(coincidencia == false){
                      aux.add(String.valueOf(aux.size()+1)+";"+registro);   
                     } 
            }
            
         }
    }
     
     /**Este metodo fue modificado para ser usado con el pensum */ 
    private void filtro_con_indice_2(LinkedList<String> aux, String registro, int longitud){ 
         boolean coincidencia = false;
        // System.out.println("registro:" +registro+" long:"+longitud);
         if(registro.length() > longitud && registro != null){
             
            if(aux.isEmpty()){             
                aux.add("1;"+registro);//seccion 

            }else{
                for(int s=0; s<aux.size(); s++){//recirriendo los auxiliares
                    if(aux.get(s).equalsIgnoreCase((s+1)+";"+registro)){
                        
                        coincidencia = true;
                        break;
                    }else{
                        coincidencia = false;
                    }
                }//fin recorrido auxiliares

                     if(coincidencia == false){
                      aux.add(String.valueOf(aux.size()+1)+";"+registro);   
                     } 
            }
            
         }
    }
    
    
    public LinkedList<String> estrategias(){
      LinkedList<String> aux = new LinkedList<>();
        aux.add("1;PC;PRUEBA CORTA");
        aux.add("2;PP;PRUEBA PARCIAL");
        aux.add("3;PROY;PROYECTO");
        aux.add("4;TALL;TALLER");
        aux.add("5;EXP;EXPOSICIÓN");
        aux.add("6;TRA;TRABAJO");
        aux.add("7;LAB;LABORATORIO");      
      return aux;
    }
     
     
    public LinkedList<String> agrupando_docentes(){
        System.out.println("agrupando docentes");
        LinkedList<String> docentes = new LinkedList<>();
        
        for(int i=0; i<inscripciones.size(); i=i+11){
           // System.out.println("buscando docentes: "+(i/11));
            this.filtro(docentes, inscripciones.get(i+9),5);
        }

        for(int x=0; x<docentes.size(); x++){System.out.println("docente: "+docentes.get(x));}
        
        System.out.println("agrupacion de docentes exitosa");
     return docentes;
    }
    
     
    public LinkedList<String> registros_asociados(String docente){
        System.out.println("Extrayendo registros asociados al docente");
        LinkedList<String> extraccion = new LinkedList<>();
        for(int i=0; i<inscripciones.size(); i=i+11){//recorriendo todos los registros de interes asociados con el docente
            if(inscripciones.get(i+9).equalsIgnoreCase(docente)){//si es el mismo docente, se toman los registros asociados a el. se toman todos los registros
                extraccion.add(inscripciones.get(i));
                extraccion.add(inscripciones.get(i+1));
                extraccion.add(inscripciones.get(i+2));
                extraccion.add(inscripciones.get(i+3));
                extraccion.add(inscripciones.get(i+4));
                
                extraccion.add(inscripciones.get(i+5));
                extraccion.add(inscripciones.get(i+6));
                extraccion.add(inscripciones.get(i+7));
                extraccion.add(inscripciones.get(i+8));
                extraccion.add(inscripciones.get(i+9));
                
                extraccion.add(inscripciones.get(i+10));                
            }
        }//fin recorrido
        
       // for(int x=0; x<extraccion.size(); x=x+11){System.out.println("docente: "+extraccion.get(x)+extraccion.get(x+1)+extraccion.get(x+5)+extraccion.get(x+9));}
       
        System.out.println(extraccion.size()+" Registros extraidos");
         return extraccion;
    }
    
    public LinkedList<String> estudiantes(LinkedList<String> extraccion){
        System.out.println("Filtrando por estudiantes");
        LinkedList<String> aux = new LinkedList<>();
        
       for(int i=0; i<extraccion.size(); i=i+11){  
           this.filtro_con_indice(aux, extraccion.get(i), 5);
       }
        System.out.println("Estudiantes Filtrados: "+(aux.size()));
        return aux;
    }
    
    public LinkedList<String> datos_personales_estudiantes(LinkedList<String> estudiantes, LinkedList<String> extraccion){
        System.out.println("Determinando datos personales del estudiantes");
        LinkedList<String> aux = new LinkedList<>();
            
            for(int i=0; i<estudiantes.size(); i++){//recorriendo las cedulas de todos los estudiantes
                String cedula = estudiantes.get(i).split(";")[1];
                    for(int e=0; e<extraccion.size(); e=e+11){//recorriendo los datos extradidos para el docente                        
                        if(cedula.equalsIgnoreCase(extraccion.get(e))){//si hay coincidencia por cedula, se toma el nombre y apellido y se asigna el id de las cedulas
                            String[] nom_ape = extraccion.get(e+1).replace(",", ";").split(";");
                            aux.add(estudiantes.get(i).split(";")[0]+";"+nom_ape[0]+";"+nom_ape[1]);
                            break;
                        }
                    }//fin recorrido de datos extraidos para el docente
            }//fin recorrido cedula de los estudiantes
        
        System.out.println("proceso datos personales terminado");
     return aux;
    }
    
     public LinkedList<String> vigencias_estudiantes(LinkedList<String> estudiantes){
        System.out.println("Filtrando por vigencias estudiantes");
        LinkedList<String> aux = new LinkedList<>();
        
             for(int i=0; i<estudiantes.size(); i++){//recorriendo las cedulas de todos los estudiantes
                String cedula = estudiantes.get(i).split(";")[1];
                 for(int v=0; v<vigencias.size(); v=v+3){//recorriendo las vigencias
                     if(cedula.equalsIgnoreCase(vigencias.get(v))){//coincidencia por cedula
                         aux.add(estudiantes.get(i).split(";")[0]+";"+vigencias.get(v+2)+";"+vigencias.get(v+1));
                         break;
                     }
                 }//fin recorrido de las vigencias  
             }//fin recorrido cedula de los estudiantes
        
        System.out.println("proceso Filtrando por vigencias estudiantes terminado");
        return aux;
    }
    
    
    public LinkedList<String> secciones(LinkedList<String> extraccion){
         System.out.println("Filtrando por secciones");
         LinkedList<String> aux = new LinkedList<>();         
         
        for(int i=0; i<extraccion.size(); i=i+11){            
           this.filtro_con_indice(aux, extraccion.get(i+7), 5);
        }
       
        System.out.println("Secciones Filtradas: "+(aux.size()));
        return aux;
    }
  
   
    private String buscando_id_seccion(String seccion, LinkedList<String> secciones){
        String aux = null;        
                   for(int s=0; s<secciones.size(); s++){
                      if(seccion.equalsIgnoreCase(secciones.get(s).split(";")[1])){//comparacion la seccion a buscar
                          aux = secciones.get(s).split(";")[0];
                          break;
                      }
                   }
      return aux;
    
    }
    
    
    
    /**Este metodo regresa por separado el id del estudiante, pensum y turno */ 
    private String[] deteccion_vigencia(String id_estudiante, LinkedList<String> vigencias_estudiantes){
        String texto[] = null;
        for(int v=0; v<vigencias_estudiantes.size(); v++){//recorriendo todas las vigencias de los estudiantes
            texto = vigencias_estudiantes.get(v).split(";");                 
            String id = texto[0], pensum = texto[1], turno = texto[2];  
            if(id.equalsIgnoreCase(id_estudiante)){ break; }
         }
        return texto;
    }
    
    private LinkedList<String> separando_inscripciones_estudiante(LinkedList<String> extraccion, String cedula){
        int inicio = 0, fin = 0;
        boolean a = false, b = false;
        LinkedList<String> materias_inscritas = new LinkedList<>();
      //  System.out.println("Extraccion sub lista: "+extraccion.size());
        
          for(int i=0; i<extraccion.size(); i=i+11){//recorriendo todos los registros de inscripcion
            //  System.out.println("cedula ext: "+extraccion.get(i)+" - "+cedula+" -- "+(extraccion.get(i).equalsIgnoreCase(cedula)));
               //System.out.println(i+"cedula ext: "+extraccion.get(i));
              if(a == false && extraccion.get(i).equalsIgnoreCase(cedula)){//se obtiene el principio del bloque de interes
                  inicio = i;  
                  a = true;
                 // System.out.println(" --inicio: "+i+" carrera: "+extraccion.get(i+2));//inscripciones
              }
              
              if(a == true && !extraccion.get(i).equals(cedula)){
                  b = true;
                 // System.out.println(" --fin: "+i+" carrera: "+extraccion.get(i+2));
              }
              //System.out.println("distinta: "+(!extraccion.get(i).equals(cedula)));
              if(b){fin = i; break;}
              
          }//fin recorrido de todos los registros de inscripcion
            if(a && b == false){fin = extraccion.size();}//asegurando el ultimo registro
          
        //  System.out.println("mi: inicio: "+inicio+" fin:"+(fin));
          materias_inscritas.addAll(extraccion.subList(inicio, fin));//sublista de las materias inscritas por el estudiante
          //materias_inscritas.add(extraccion.get(fin));//se agrega este registro porque la sublista excluye el registro del ultimo valor que se desea en fin
      //     System.out.println(" mi:"+materias_inscritas.size()/11);
          return materias_inscritas;
    
    }
    
    private LinkedList<String> separando_pensum(String carrera, String pensum, String turno){
        int inicio = 0, fin=0;
        boolean a = false, b = false;
        LinkedList<String> pensum_alternativo = new LinkedList<>();
        
        for(int p=0; p<pensa.size(); p=p+15){//recorriendo todos los pensum
          //  System.out.println(p+" -->pensa: "+pensa.get(p+1)+" - "+pensa.get(p+12)+" - "+pensa.get(p+13)+" - "+pensa.get(p+14));
           // System.out.println("  Analisis -->pensa: "+(a == true)+" - "+(!carrera.equals(pensa.get(p+13)))+" - "+(!pensum.equals(pensa.get(p+12)))+" - "+(!turno.equals(pensa.get(p+14)))); 
           // System.out.println(" comp: "+pensum+" - "+carrera+" - "+turno);  
            if(a == false && pensum.equalsIgnoreCase(pensa.get(p+12)) && carrera.equalsIgnoreCase(pensa.get(p+13)) && turno.equalsIgnoreCase(pensa.get(p+14))){
                inicio = p;//inicio
                a = true;   
             //   System.out.println(p+"  Analisis -->pensa: "+pensa.get(p+12)+" - "+pensa.get(p+13)+" - "+pensa.get(p+14));
                
            }
            if(a == true && (!pensum.equals(pensa.get(p+12)) || !carrera.equals(pensa.get(p+13)) || !turno.equals(pensa.get(p+14))) ){                
                b = true;
               // System.out.println("///----////"+p);
            }
            //System.out.println("------"+(a == true)+" - "+(!pensum.equals(pensa.get(p+12)))+" - "+(!carrera.equals(pensa.get(p+13)))+" - "+(!turno.equals(pensa.get(p+14))) );
            if(b){fin = p; break;}
        }
        if(a && b == false){fin = pensa.size();}//asegurando el ultimo registro
        
      //  System.out.println("pa: inicio: "+inicio+" fin:"+(fin)+" dimension: "+pensa.size());        
        pensum_alternativo.addAll(pensa.subList(inicio, fin));
        //pensum_alternativo.add(pensa.get(fin));//se agrega este registro porque la sublista excluye el registro del ultimo valor que se desea en fin
     //   System.out.println(" pensum alt:"+pensum_alternativo.size()/15);
        
        return pensum_alternativo;
        
    }
    
    private String identificando_materia(String codigo, String vigencia, String carrera, String turno, LinkedList<String> materias_pensum){
         String materia = null;
       //  System.out.println("identificando materia: \n"+codigo+" \n"+vigencia+" \n"+carrera+" \n"+turno);
                for(int p=0; p<materias_pensum.size(); p=p+15){//recorriendo todos los pensum                    
                   // System.out.println("   pensum: "+pensa.get(p+1)+"|"+pensa.get(p+13)+"|"+pensa.get(p+12)+"|"+pensa.get(p+14));
                    if(codigo.equalsIgnoreCase(materias_pensum.get(p+1)) && //coincidencia por codigo de materia
                       carrera.equalsIgnoreCase(materias_pensum.get(p+13)) && //coincidencia por carrera
                       vigencia.equalsIgnoreCase(materias_pensum.get(p+12)) && //coincidencia por vigencia del pensum
                       turno.equalsIgnoreCase(materias_pensum.get(p+14))     //coincidencia por el turno del pensum
                      ){
                          materia = materias_pensum.get(p)+";"+ //semestre
                                    materias_pensum.get(p+1)+";"+ //codigo
                                    materias_pensum.get(p+2)+";"+ //asignatura
                                    materias_pensum.get(p+3)+";"+ //hora_t
                                    materias_pensum.get(p+4)+";"+ //hora_p
                                    materias_pensum.get(p+5)+";"+ //hora_l
                                    materias_pensum.get(p+6)+";"+ //uc
                                    materias_pensum.get(p+7)+";"+ //en_aula
                                    materias_pensum.get(p+8)+";"+ //fuera_del_aula
                                    materias_pensum.get(p+9)+";"+ //laboratorio
                                    materias_pensum.get(p+10)+";"+ //total
                                    materias_pensum.get(p+11)+";"+ //autogestion
                                    materias_pensum.get(p+12)+";"+ //pensum = vigencia
                                    materias_pensum.get(p+13)+";"+ //carrera
                                    materias_pensum.get(p+14); //turno
                        break;//rompiendo recorrido pensum
                    }
                              
                }//fin recorrido de todos los pensum
        
       return materia;
    
    }
    private String buscando_id_materia(String codigo, String vigencia ,String carrera, String turno, LinkedList<String> materias_pensum, LinkedList<String> materias){
        String id_materia = null;
        System.out.println(" Buscando Codigo: "+codigo);
        String mat = this.identificando_materia(codigo, vigencia, carrera, turno, materias_pensum); 
        System.out.println("MATERIA: "+mat);
        this.filtro_con_indice_2(materias, mat, 5);//colocando el indice a la materia
        
        for(int m=0; m<materias.size(); m++){//revisando para encontrar la id
            //System.out.println("  COMPARACIONES: "+mat+"\n                 "+materias.get(m));
            if(materias.get(m).contains(mat)){
             //   System.out.println("    Encontrado");
             id_materia = materias.get(m).split(";")[0];//tomando solo el identificador
             break;
            }
        }
        
        return id_materia;
    }
    
    
    public Object[] datos_academicos(LinkedList<String> estudiantes, LinkedList<String> extraccion, LinkedList<String> secciones, LinkedList<String> vigencias_estudiantes){
         System.out.println("Obteniendo datos academicos y materias");
            LinkedList<String> materias = new LinkedList<>();
            LinkedList<String> aux = new LinkedList<>();
            LinkedList<String> subregistro = new LinkedList<>();
            
            String id_estudiante = null, id_materia = null, id_seccion = null;
            String est_indice = null, est_cedula = null;
            String ext_cedula = null, ext_carrera = null, ext_codigo = null, ext_seccion = null;
            String mat = null;
           
            
        for(int e=0; e<estudiantes.size(); e++){//recorriendo estudiantes del docente con indice
          est_indice = estudiantes.get(e).split(";")[0];//indice
          est_cedula = estudiantes.get(e).split(";")[1];//cedula
            //System.out.println("Estudiantes: "+est_cedula+" "+e);
            id_estudiante = est_indice;
              
            //nota: ademas de tomar el bloque correspondiente al estudiante, tambien se puede eliminar el bloque para reducir el tiempo de busqueda         
            subregistro = this.separando_inscripciones_estudiante(extraccion,est_cedula );
              //  System.out.println("Subregistro: "+subregistro.size()/11);
            
            String [] vpt = this.deteccion_vigencia(id_estudiante, vigencias_estudiantes);//buscando la vigencia del estudiantes, pensum y turno. en un arreglo
              //  System.out.println("Vigencia: id-pensum-turno: "+vpt[0]+"-"+vpt[1]+"-"+vpt[2]);
              //  System.out.println("Carrera: "+subregistro.get(2));
            LinkedList<String> materias_pensum = this.separando_pensum(subregistro.get(2), vpt[1], vpt[2]);//obteniendo las materias del pensum del estudiante segun los paramentros de intereres
              //  System.out.println("MATERIAS PENUSM: "+(materias_pensum.size()/15)+" - "+materias_pensum.get(0)+"|"+materias_pensum.get(1)+"|"+materias_pensum.get(2)+
               // "|"+materias_pensum.get(12)+"|"+materias_pensum.get(13)+"|"+materias_pensum.get(14));
                
            
                for(int x=0; x<subregistro.size(); x=x+11){//recorriendo las extraciones del docente
                    ext_cedula = subregistro.get(x);
                    ext_codigo = subregistro.get(x+4);
                    ext_carrera = subregistro.get(x+2);
                    ext_seccion = subregistro.get(x+7); 

                    id_seccion = this.buscando_id_seccion(ext_seccion, secciones);                
                    id_materia = this.buscando_id_materia(ext_codigo,vpt[1],ext_carrera,vpt[2], materias_pensum, materias);
                  //  System.out.println("subregistro: "+aux+"-"+id_estudiante+"-"+id_materia+"-"+id_seccion);
                    this.filtro_con_indice_2(aux, id_estudiante+";"+id_materia+";"+id_seccion, 3);

                }//fin recorrido extraccion del docente

            
            
        }//fin recorrido estudiantes del docente con indice
    System.out.println("terminado proceso de Obtencion de datos academicos y materias: estudiantes:"+estudiantes.size()+" registros:"+extraccion.size()+" registros finales: "+aux.size());
    return new Object[]{aux, materias};
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void crendo_registros_del_docente(LinkedList<String> docentes){
       
        for(int i=0; i<docentes.size(); i++){//recorriendo todos los docentes del periodo seleccionado
            
            if(docentes.get(i).equalsIgnoreCase("x") || docentes.get(i).isEmpty() || !docentes.get(i).contains(",")){
                System.out.println("Registro Incompleto: "+docentes.get(i));
            }else{
               System.out.println("\nRastreando: ------------------------------------> "+docentes.get(i));
               
               this.crear_directorio(ruta+docentes.get(i)); //creando carpeta del docente
               
               LinkedList<String> datos = new LinkedList<>();
               datos = this.estrategias(); 
               this.escribir_archivo(ruta+docentes.get(i)+"\\Estrategias.csv", datos);//creando el archivo con las estrategias de evaluacion existentes
               
               datos = new LinkedList<>();
               String aux = docentes.get(i).replace(",", " - "); // es opcional. lo ideal es que sea CI - nombre docente
               datos.add(aux.split(" - ")[0]+";"+aux.split(" - ")[1]);                 
               this.escribir_archivo(ruta+docentes.get(i)+"\\Datos_Docente.csv", datos);//creando el archivo con datos del docente
               
               datos = this.registros_asociados(docentes.get(i));
               
               LinkedList<String> datos1 = this.secciones(datos);
               this.escribir_archivo(ruta+docentes.get(i)+"\\Secciones.csv", datos1);//creando el archivo con datos de las secciones
               
               LinkedList<String> datos2 = this.estudiantes(datos);
               this.escribir_archivo(ruta+docentes.get(i)+"\\Estudiantes.csv", datos2);//creando el archivo con datos con cedulas de los estudiantes
               
               LinkedList<String> datos3 = this.vigencias_estudiantes(datos2);
               this.escribir_archivo(ruta+docentes.get(i)+"\\Vigencias_Estudiantes.csv", datos3);//creando el archivo con datos con cedulas de los estudiantes
               
               Object[] elementos = this.datos_academicos(datos2, datos, datos1, datos3);//obteniendo los datos academicos y materias
               this.escribir_archivo(ruta+docentes.get(i)+"\\Datos_Academicos.csv", (LinkedList<String>) elementos[0]);//creando el archivo con datos academicos
               this.escribir_archivo(ruta+docentes.get(i)+"\\Materias.csv", (LinkedList<String>) elementos[1]);//creando el archivo con datos de las materias
               
               datos2 = this.datos_personales_estudiantes(datos2, datos);
               this.escribir_archivo(ruta+docentes.get(i)+"\\Datos_Personales.csv", datos2);//creando el archivo con nombre y apellidos del estudiante
              
            
            }
               
             //  System.exit(0);
               
               
               
               
        }//fin recorrido de todos los docentes del periodo seleccionado
        
    }
    
    
    
    
    
    
    //---------------------GENERANDO LOS ARCHIVOS--------------
    
    
    private void crear_directorio(String direccion){
       File directorio ;
       System.out.println("Creando directorio");       
       
           directorio = new File(direccion);//creando carpeta con nombre del docente                  
           if(directorio.exists()){
               System.out.println("Directorio existe: "+direccion);
           }else{
              System.out.println("Directorio creado? "+directorio.mkdir());
           }       
    }
    
    private void crear_archivo(String direccion){
       File archivo ;
       FileWriter escritor = null;
       
       System.out.println("Creando Archivo");       
       
           archivo = new File(direccion);//creando carpeta con nombre del docente                  
           
           if(archivo.exists()){
               System.out.println("Archivo existe: "+direccion);
           }else{
           try {
               escritor = new FileWriter(archivo);
               escritor.write("");//solo para crear el archivo
               escritor.close();
               System.out.println("Archivo creado");
           } catch (IOException ex) {
               Logger.getLogger(Registros_Actas.class.getName()).log(Level.SEVERE, null, ex);
           }
           }       
    }
    
    private void escribir_archivo(String direccion, LinkedList<String> datos){
        FileWriter escritor = null;
        PrintWriter lapiz = null;
        System.out.println(direccion);
        try {
            File archivo = new File(direccion);
            escritor = new FileWriter(archivo,true);
            lapiz = new PrintWriter(escritor);
            
            if(archivo.exists()){//solo si el archivo existe
                for(int i=0; i<datos.size(); i++){//recorriendo los datos almacenados
                     lapiz.println(datos.get(i));//escribiendo linea a linea                        
                }// fin recorrido datos almacenados
            }else{
                System.out.println("El archivo no existe");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Registros_Actas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escritor.close();
            } catch (IOException ex) {
                Logger.getLogger(Registros_Actas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    
    }
    
   
    
    
    
    public static void main(String [] args){
        Registros_Actas ra = new Registros_Actas();
        ra.cargando_pensa();
        ra.cargando_datos_academicos();
        ra.cargando_inscripciones("2-2015");
        LinkedList<String> x = ra.agrupando_docentes();
        ra.crendo_registros_del_docente(x);
    }
    
    
}//fin de la clase
