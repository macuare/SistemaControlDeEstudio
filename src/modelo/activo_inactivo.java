/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author AN
 */
public class activo_inactivo {
    private imagenes ima;
    private String cedula;
    private JTextArea resumen;
    
    /** posicion 0 = cedula<br> posicion 1 = causa<br> posicion 2 = periodo<br> posicion 3 = usuario<br> posicion 4 = fecha*/
    private LinkedList<String> can_ret = new LinkedList<>();
    /**posicion 0 cedula estudiante<br>posicion 1 planilla datos personales<br>posicion 2 foto carnet<br>posicio 3 certificado salud<br>posicion 4 comprobante cinu<br>posicion 5 planilla inscripcion cnu o rusnieus<br>posicion 6 fondo negro título<br>posicion 7 copia título<br>posicion 8 notas certificadas<br>posicion 9 usuario que ingreso los datos<br>posicion 10 fecha en lo que lo realizo */
    private LinkedList<Object> doc_alum = new LinkedList<Object>();
    /**posicion 0 cedula estudiante<br>posicion 1 periodo de cuando se proceso el reingreso<br>posicion 2 usuario que ingreso los datos<br>posicion 3 fecha en lo que lo realizo */
    private LinkedList<String> rein = new LinkedList<>();
    
    
    
    public activo_inactivo() {
        ima = new imagenes();
        
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public JTextArea getResumen() {
        return resumen;
    }

    public void setResumen(JTextArea resumen) {
        this.resumen = resumen;
    }
    
    
    
    
    
    
    /**MEtodo para obtener todo el historial del estudiante en cuanto a retiros y cancelaciones */
    public void cancelacion_retiros(Connection con){
        can_ret.clear();//se limpia el LinkedList antes de llenarlo con la nueva informacion
        
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.cancelacion_retiros WHERE cedula='"+this.getCedula()+"';");
            while(resultado.next()){//mientras halla algo se hacen las siguientes operaciones
                can_ret.add(resultado.getString("cedula"));// 0 
                can_ret.add(resultado.getString("causa"));// 1
                can_ret.add(resultado.getString("periodo"));// 2
                can_ret.add(resultado.getString("usuario"));// 3
                can_ret.add(resultado.getString("fecha"));// 4
               
            }
            
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR LEYENDO TABLA DE BASE DE DATOS", "CONEXION BASE DE DATOS", "error", "png");
            Logger.getLogger(activo_inactivo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(activo_inactivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
            
    
    }
    
    public void documentos_alumno(Connection con){
        doc_alum.clear();//se limpia el LinkedList antes de llenarlo con la nueva informacion
        
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.documentos WHERE cedula='"+this.getCedula()+"';");
            while(resultado.next()){//mientras halla algo se hacen las siguientes operaciones
                doc_alum.add(resultado.getString("cedula"));// 0 cedula estudiante
                doc_alum.add(resultado.getBoolean("pdp"));// 1 planilla datos personales
                doc_alum.add(resultado.getBoolean("fc"));// 2 foto carnet
                doc_alum.add(resultado.getBoolean("cs"));// 3 certificado salud
                doc_alum.add(resultado.getBoolean("ccinu"));// 4 comprobante cinu
                doc_alum.add(resultado.getBoolean("pi_cnu_rusnieus"));// 5 planilla inscripcion cnu o rusnieus
                doc_alum.add(resultado.getBoolean("fnt"));// 6 fondo negro título
                doc_alum.add(resultado.getBoolean("ct"));// 7 copia título
                doc_alum.add(resultado.getBoolean("nc"));// 8 notas certificadas
                doc_alum.add(resultado.getBoolean("usuario"));// 9 usuario que ingreso los datos
                doc_alum.add(resultado.getBoolean("fecha"));// 10 fecha en lo que lo realizo
               
            }
           
            
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR LEYENDO TABLA DE BASE DE DATOS", "CONEXION BASE DE DATOS", "error", "png");
            Logger.getLogger(activo_inactivo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(activo_inactivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
       
    
    }
    
    
    public void reingresos(Connection con){    
        rein.clear();//se limpia el LinkedList antes de llenarlo con la nueva informacion
        
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.reingresos WHERE cedula='"+this.getCedula()+"';");
            while(resultado.next()){//mientras halla algo se hacen las siguientes operaciones
                rein.add(resultado.getString("cedula"));// 0 cedula estudiante
                rein.add(resultado.getString("periodo"));// 1 periodo de cuando se proceso el reingreso
                rein.add(resultado.getString("usuario"));// 2 usuario que ingreso los datos
                rein.add(resultado.getString("fecha"));// 3 fecha en lo que lo realizo
               
            }
    
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR LEYENDO TABLA DE BASE DE DATOS", "CONEXION BASE DE DATOS", "error", "png");
            Logger.getLogger(activo_inactivo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(activo_inactivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
       
        
    
    }
    
    /**Este metodo determina si el estudiante tiene la misma cantidad de reingresos vs retiros cancelacion. \n
     El objetivo es que tenga la misma cantidad de ambos, en caso de que falte no aprueba el analisis*/
    public boolean analisis_cancelados_retiros(){
        boolean inactivar = false;
        
        if(can_ret.isEmpty()){//el estudiante no tiene ni retiros ni ha sido cancelado
            System.out.println("APROBADO. El estudiante no tiene ni retiros ni ha sido cancelado");            
            inactivar = false;
        //    this.resumen.append("APROBADO. El estudiante no tiene ni retiros ni ha sido cancelado\n");
        }else{//se realizan las comparaciones
        
            if(rein.isEmpty()){//esto es porque tienen alguna cancelacion o retiro pero no se encuentra ningun reingreso que compense su situacion
                inactivar = true; 
        //        this.resumen.append("REPROBADO. No tiene REINGRESOS que compensen sus CANCELACIONES-RETIROS\n");
                System.out.println("REPROBADO. No tiene REINGRESOS que compensen sus CANCELACIONES-RETIROS");
            }else{//se recorre el registro de las cancelaciones retiros
                
                for(int i=0; i<can_ret.size(); i=i+5){//recorriendo cancelado retiro
                        for(int r=0; r<rein.size(); r=r+4){//recorriendo sus reingresos aprobados
                           
                            if(can_ret.get(i).equalsIgnoreCase(rein.get(r)) && //se compara la cedula
                               can_ret.get(i+2).equalsIgnoreCase(rein.get(r+1))// y el periodo que deben ser estrictamente iguales  
                               ){
                                inactivar = false;
          //                      this.resumen.append("Cancelacion-Retiro:"+can_ret.get(i+2)+" Reingreso:"+rein.get(r+1)+" en orden.\n");
                                System.out.println("Cancelacion-Retiro:"+can_ret.get(i+2)+" Reingreso:"+rein.get(r+1)+" en orden.");
                                break;
                            }else{     
                                
                                inactivar = true;
                            }
                            
                        }//fin recorrido reingreso
                        
                            if(inactivar){ //esto es para analisar si despues de comparar el registro de cancelado retiro no existe en todos los reingresos aprobados para el estudiante significa que esa cancelacion no tiene reingreso generando problema  y se rompe el ciclo exterior
                                System.out.println("Reprobó. El estudiante tiene problema con los reingresos y retiros-cancelación. Causa:"+can_ret.get(i+2));
                 //               this.resumen.append("Reprobó. El estudiante tiene problema con los reingresos y retiros-cancelación. Causa:"+can_ret.get(i+2)+"\n");
                                break;                      
                            }
                }//fin recorrido cancelado retiro        
            
            }//analisis en caso de que posea algun reingreso
        }
    
        if(inactivar){
            System.out.println("1_NO PASO ANALISIS RETIROS-CANCELACIÓN-REINGRESO");
   //         this.resumen.append("1_NO PASO ANALISIS RETIROS-CANCELACIÓN-REINGRESO\n");
        }else{
            System.out.println("1_PASO ANALISIS RETIROS-CANCELACIÓN-REINGRESO");
    //        this.resumen.append("1_PASO ANALISIS RETIROS-CANCELACIÓN-REINGRESO\n");
        }
    
    return inactivar;
    
    }
    
    /**Se revisa los documentos entregados a control de estudio por el estudiante y se evalua los requerimientos minimos 
     para aprobar. Por lo tanto si no lo cumple simplemente se sugiere inactivar*/
    public boolean analisis_documentos_alumnos(){
        boolean inactivar = false;
        
        
        if(doc_alum.isEmpty()){
            inactivar = true;//esto es porque no tiene ningun tipo de documentacion            
            System.out.println("NO Aprueba por no tener ningun tipo de registro en la base de datos. Documentos Mínimos Necesarios");
       //     this.resumen.append("NO Aprueba por no tener ningun tipo de registro en la base de datos. Documentos Mínimos Necesarios\n");
        }else{
                
            if(doc_alum.get(1).equals(true) &&//fdp = planilla de datos personales
               doc_alum.get(6).equals(true) &&//fnt = Fondo Negro Título
               doc_alum.get(7).equals(true) &&//ct = Copia Título    
               doc_alum.get(8).equals(true) &&//nc = Notas Certificadas
               doc_alum.get(3).equals(true) //cs = Certificado de Salud                    
                    ){//solo si cumple con esto requisitos se podra inscribir. activo
              inactivar = false;      
              System.out.println("2_APROBÓ el análisis de Documentos Mínimos Necesarios");
      //        this.resumen.append("2_APROBÓ el análisis de Documentos Mínimos Necesarios\n");
            }else{
              inactivar = true;//se inactiva por no cumplir con los requisitos mínimos  
              System.out.println("NO pasó el analisis de Documentos Mínimos Necesarios...!!!");
      //        this.resumen.append("NO pasó el analisis de Documentos Mínimos Necesarios...!!!\n");
            }
                    
        }
        
        return inactivar;
    }
    
    
    public void cambio_activo(Connection con, String activo){
     
        try {
            Statement sentencia = null;

            con.setAutoCommit(false);

            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.todos_alumnos  SET activo='"+activo+"' WHERE cedula='"+this.getCedula()+"';");
            
            con.commit();//haciendo permanente los cambios y liberando la base de datos
            System.out.println("Haciendo permanente el cambio en ACTIVO del estudiante: "+cedula+" - actual: "+activo);
   //         this.resumen.append("Haciendo permanente el cambio en ACTIVO del estudiante: "+cedula+" - actual: "+activo+"\n");
            sentencia.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
             if(con!=null){
                System.err.println("Error en el envio de informacion. Transaccion revertida. Rolling Back");
                try {
                    con.rollback();//regresando los cambios
                   // ima.mensaje_informacion("Error enviando informacion a la base de datos. Devolviendo cambios. Rolling Back\n"+ex.getMessage(), "ERROR SQL", "error", "png");
                } catch (SQLException ex1) {
                    Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex1);

                }

            }
        }finally{//se ejecuta pase lo que paso con la base de datos. Se garantiza bajo cualquier circunstancia el ejecutese de los procedimientos
              try {
                con.setAutoCommit(true);//regresando la condicion original del autocommit
                con.close();
                System.out.println("Cerrando base de datos");
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



}
  
    /**Se realiza el procesamiento por los alumnos */
  public void procesamiento_analisis(String cedula){
      //this.resumen.append("PROCESANDO ESTUDIANTE: "+cedula+"\n");
      this.setCedula(cedula);
      //this.resumen.append("CARGANDO REINGRESOS\n");
      this.reingresos(new conexion_base_de_datos().getConexion());
      //this.resumen.append("CARGANDO CANCELACION RETIROS\n");
      this.cancelacion_retiros(new conexion_base_de_datos().getConexion());
      //this.resumen.append("CARGANDO DOCUMENTACION\n");
      this.documentos_alumno(new conexion_base_de_datos().getConexion());
      //this.resumen.append("ANALISIS Y COMPARACIONES\n");
      this.resumen_analisis();
  }  
    
    
  public void resumen_analisis(){
      
      boolean analisis_1 = this.analisis_cancelados_retiros();
      boolean analisis_2 = this.analisis_documentos_alumnos();
      
      if(analisis_1==false && analisis_2==false){//los analisis sugirieron activar al estudiante
          this.cambio_activo(new conexion_base_de_datos().getConexion(), "SI");
          System.out.println("Estudiante "+this.getCedula()+", ACTIVADO ");
    //      this.resumen.append("Estudiante "+this.getCedula()+", ACTIVADO \n");
      }else{//las variantes sugieren inactivar al estudiante
          this.cambio_activo(new conexion_base_de_datos().getConexion(), "NO");
          System.out.println("Estudiante "+this.getCedula()+", INACTIVADO ");
      ///    this.resumen.append("Estudiante "+this.getCedula()+", INACTIVADO \n");
      }
       
  
  }
    
    
  
    
    
  public static void main(String [] args){
  
      activo_inactivo ai = new activo_inactivo();
      ai.procesamiento_analisis("10001820");
  
  
  }
  
    
}//fin de la clase
