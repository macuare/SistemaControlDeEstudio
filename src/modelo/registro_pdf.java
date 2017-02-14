/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.mysql.jdbc.ResultSet;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.progreso;

/**
 *
 * @author AURORA
 */
public class registro_pdf {


private registro_ingenieria reging;
private imagenes ima;
private Archivos doc;
private reportes reporte;
private materias materia;
//private masivos masivo;

private String comp_periodo;
private int uc_inscritas=0;
private int uc_aprobadas=0;
private int uc_equivalencias=0;
private int uc_cursadas=0;
private int puntos=0,puntos_merito=0;
private float indice_academico=0.00F, indice_merito=0.00F;
private DecimalFormat df = new DecimalFormat("0.00");
private int numero_pagina=1;
private String periodo_ingreso,mes_ingreso,año_ingreso;
private LinkedList<String> almacen = new LinkedList<>();
private LinkedList<String> reporte_indices = new LinkedList<>();
private LinkedList<String> analisis_merito = new LinkedList<>();
private LinkedList<String> egresados = new LinkedList<>();
private LinkedList<String> acu_per = new LinkedList<>();
/*posicion = 0 //nota definitiva<br>posicion = 1 //nombre de la materia<br>posicion = 2 //condicion de la materia<br>posicion = 3 //periodo academico*/
private LinkedList<String> confexion = new LinkedList<>();
private LinkedList<String> asociacion_merito = new LinkedList<>();


private String observacion;

private LinkedList<String> textos_archivo= new LinkedList<>();

//constructore de la clase
    public registro_pdf(registro_ingenieria reging, materias materia) {
        this.reging = reging;
       
    }

    public registro_pdf() {
        ima = new imagenes();
        doc = new Archivos();
        reporte = new reportes();
        materia = new materias();
  //      masivo = new masivos();
    }



   //  setters y getters


     public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getComp_periodo() {
        return comp_periodo;
    }

    public void setComp_periodo(String comp_periodo) {
        this.comp_periodo = comp_periodo;
    }

    public int getUc_inscritas() {
        return uc_inscritas;
    }

    public void setUc_inscritas(int uc_inscritas) {
        this.uc_inscritas = uc_inscritas;
    }

    public float getIndice_academico() {
        return indice_academico;
    }

    public void setIndice_academico(float indice_academico) {
        this.indice_academico = indice_academico;
    }

    public LinkedList<String> getAlmacen() {
        return almacen;
    }

    public void setAlmacen(LinkedList<String> almacen) {
        this.almacen = almacen;
    }

    public int getUc_cursadas() {
        return uc_cursadas;
    }

    public void setUc_cursadas(int uc_cursadas) {
        this.uc_cursadas = uc_cursadas;
    }

   

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getUc_aprobadas() {
        return uc_aprobadas;
    }

    public void setUc_aprobadas(int uc_aprobadas) {
        this.uc_aprobadas = uc_aprobadas;
    }

    public int getNumero_pagina() {
        return numero_pagina;
    }

    public void setNumero_pagina(int numero_pagina) {
        this.numero_pagina = numero_pagina;
    }

    public String getAño_ingreso() {
        return año_ingreso;
    }

    public void setAño_ingreso(String año_ingreso) {
        this.año_ingreso = año_ingreso;
    }

    public String getMes_ingreso() {
        return mes_ingreso;
    }

    public void setMes_ingreso(String mes_ingreso) {
        this.mes_ingreso = mes_ingreso;
    }

    public String getPeriodo_ingreso() {
        return periodo_ingreso;
    }

    public void setPeriodo_ingreso(String periodo_ingreso) {
        this.periodo_ingreso = periodo_ingreso;
    }

    public int getUc_equivalencias() {
        return uc_equivalencias;
    }

    public void setUc_equivalencias(int uc_equivalencias) {
        this.uc_equivalencias = uc_equivalencias;
    }

    public LinkedList<String> getReporte_indices() {
        return reporte_indices;
    }

    public void setReporte_indices(LinkedList<String> reporte_indices) {
        this.reporte_indices = reporte_indices;
    }

    public float getIndice_merito() {
        return indice_merito;
    }

    public void setIndice_merito(float indice_merito) {
        this.indice_merito = indice_merito;
    }

    public int getPuntos_merito() {
        return puntos_merito;
    }

    public void setPuntos_merito(int puntos_merito) {
        this.puntos_merito = puntos_merito;
    }

    public LinkedList<String> getAnalisis_merito() {
        return analisis_merito;
    }

    public void setAnalisis_merito(LinkedList<String> analisis_merito) {
        this.analisis_merito = analisis_merito;
    }

    public LinkedList<String> getEgresados() {
        return egresados;
    }

    public void setEgresados(LinkedList<String> egresados) {
        this.egresados = egresados;
    }

    public LinkedList<String> getAcu_per() {
        return acu_per;
    }

    public void setAcu_per(LinkedList<String> acu_per) {
        this.acu_per = acu_per;
    }

  


    




/**Este metodo se encarga de colocar la nota definitiva de una materia de repitencia en
 la definitiva de la materia que se arma. A efecto de calculo esto no cambia solo a nivel
 de presentacion. La idea es que la definitiva de la materia por repitencia no se coloque en la
 reparacion. NOTA: Recordar que la nota que aparece en reparacion de la repitencia es la definitiva real y
 la definitiva es la nota de la ultima vez que la vio que puede ser la definitiva si no fue a reparar
 o la reparacion si lo fue*/
public String[] nota_presentacion(String definitiva, String reparacion, String def_rep, String condicion){
    String nota[]= new String[]{"x","x"};//inicializando
    //nota[0]= casilla del record academico DEFINITIVA
    //nota[1]= casilla del record academico REPARACION

//System.out.println("notas: def:"+definitiva+" rep:"+reparacion+"  presentacion condicion: "+condicion);
    if(condicion.equalsIgnoreCase("REPITENCIA") ||       
       condicion.equalsIgnoreCase("REPITENCIA-NO REPARÓ-PENDIENTE") ||
       condicion.equalsIgnoreCase("PARALELO") ||       
       condicion.equalsIgnoreCase("PARALEO-NO REPARÓ-PENDIENTE")
            ){//esto se hace solo para materias en condicion de repitencia "P" o "L" y su nueva codificacion

        nota[0] = reparacion;//solo si la materia esta en condicion P se coloca su nota en la definitiva. cambiando
        nota[1]=""; //se establece a cero la casilla de reparacion ya que se esta moviendo la nota def de la repitencia a la definitiva desde el punto visual
    }else {//sino es asi se coloca el valor original que corresponde

        nota[0] = definitiva;
        nota[1]= reparacion;
    }

    //caso excepcional cuando hay que considerar la nota de definitiva reparacion
    if(condicion.equalsIgnoreCase("REPITENCIA-REPARÓ-PENDIENTE") ||
       condicion.equalsIgnoreCase("REPITENCIA-REPARÓ") ||
       condicion.equalsIgnoreCase("PARALELO-REPARÓ-PENDIENTE") ||
       condicion.equalsIgnoreCase("PARALELO-REPARÓ")
       ){
        nota[0] = def_rep;
        nota[1]= reparacion;


    }

    if(condicion.equalsIgnoreCase("EQUIVALENCIA") || condicion.equalsIgnoreCase("MANTENIMIENTO") ){//solo si es equivalencia se ignora las notas y se dejan las casillas en blanco
        nota[0] = "";
        nota[1] = "";
    }
    



return nota;
}

public String semestre_materia(String codigo,String periodo, LinkedList<String> v){
        String semestre=null;
        String auxiliar=null;
    //revisando si la materia a analizar esta dentro de las electivas para intercambiar su nombre y a la generica asociada y asi con la siguiente iteracion encotrar su semestre segun el pensum
      auxiliar=this.intercambiador(codigo, periodo);

       if(auxiliar.equalsIgnoreCase("No Existe")){
        //es porque la materia no es una electiva y no esta asociada, por lo tanto no se intercambia condigo temporalmente. pasa con las materias normales
       }else{//es una electiva real y se toma su codigo generico del pensum segun la asociacion para que la siguiente iteracion busque el semestre en el pensum
        codigo=auxiliar;
        System.out.println("Usando codigo auxiliar: Intercambio: "+auxiliar);
       }

    //for(int s=0; s<v.size();s=s+7){//recorriendo el pensum   
    for(int s=0; s<v.size();s=s+10){//recorriendo el pensum

        if(codigo.equalsIgnoreCase(v.get(s+0))){ semestre=v.get(s+1); break;}//si los codigos son iguales dame el semestre de la materia


        // if(codigo.equalsIgnoreCase("TAL00001")){semestre="6";break;}//materias del servicion comunitario
       // if(codigo.equalsIgnoreCase("PRO00001")){semestre="6";break;}
    }//fin recorrido pensum




return semestre;
}


public String sin_cero(String condicion, String def, String nombre_materia){
    String definitiva = null;
            
        if(condicion.equalsIgnoreCase("APROBÓ") ||
           condicion.equalsIgnoreCase("REPROBÓ") ||
           condicion.equalsIgnoreCase("EXONERADO") ||
           condicion.equalsIgnoreCase("REPROBÓ-50% DE INASISTENCIA") ||
           condicion.equalsIgnoreCase("REPROBÓ-25% DE INASISTENCIA") ||
           condicion.equalsIgnoreCase("EQUIVALENCIA")
        )  { 
            definitiva="";
        }
        else{
         definitiva=def;
        }

        //considerando las materias que reprueban del 1 al 15 pero tienen  condicion 06=REPROBÓ entonces la nota debe verse
        if(condicion.equalsIgnoreCase("REPROBÓ") && (nombre_materia.contains("PASANTÍA") || nombre_materia.contains("PASANTIA") || nombre_materia.contains("TRABAJO ESPECIAL DE GRADO") || nombre_materia.contains("PRÁCTICA EDUCATIVA") || nombre_materia.contains("PRACTICA EDUCATIVA"))){//ademas de reprobo no es cualitativa porque tiene ponderacion
          //definitiva = def;  
            definitiva = "";  
        }
        
    return definitiva;
}

public String sin_condicion(String condicion, String reparacion){
    String cond=null;
   // System.out.println("ANALISIS cond: "+condicion+" - rep: "+reparacion);
        if(condicion.equalsIgnoreCase("NORMAL") || condicion.equalsIgnoreCase("01") || condicion.equalsIgnoreCase("04") || condicion.equalsIgnoreCase("MANTENIMIENTO")){
            cond="";
        }else{
            cond=condicion;
        }

       if(reparacion==null || reparacion==""){ reparacion="0";}//en caso de que no haya reparado y el campo este vacio se establece el cero para la consideracion de abajo

    //   if(condicion.equalsIgnoreCase("REPARO") && Integer.valueOf(reparacion)<10){
      //          cond="REPARO-PENDIENTE";
              //  cond="REPROBO";
      // }



return cond;
}

public String puntos_visuales_pasantias(String nombre_materia,int uc ,String condicion, String puntos){
    String p = puntos;
    
   if(uc<=0 || condicion.equalsIgnoreCase("EQUIVALENCIA") || condicion.equalsIgnoreCase("MANTENIMIENTO")){ 
       p = "";
   }
   
   if(condicion.equalsIgnoreCase("REPROBÓ") && (nombre_materia.contains("PASANTÍA") || nombre_materia.contains("PASANTIA") || nombre_materia.contains("TRABAJO ESPECIAL DE GRADO") || nombre_materia.contains("PRÁCTICA EDUCATIVA") || nombre_materia.contains("PRACTICA EDUCATIVA"))){//ademas de reprobo no es cualitativa porque tiene ponderacion            
       p = "";  
    }


return p;
}

public void reporte_todos_indices(String cedula,String estudiante,String notas, String carrera){
 //  doc.escritor_textos("d://REPORTES//indices_"+carrera+".txt", cedula+";"+estudiante+";"+notas+";"+carrera);

    this.getReporte_indices().add(cedula);
    this.getReporte_indices().add(estudiante);
    this.getReporte_indices().add(notas);
    this.getReporte_indices().add(carrera);
}

public void tabla_merito(String cedula,String matricula, int ptos_academicos, int ptos_meritos, int uc_total, String indice_aca, String indice_mer){

    this.analisis_merito.add(matricula);//0
    this.analisis_merito.add(String.valueOf(ptos_academicos) );//1
    this.analisis_merito.add(String.valueOf(ptos_meritos));//2
    this.analisis_merito.add(String.valueOf(uc_total));//3
    this.analisis_merito.add(indice_aca);//4
    this.analisis_merito.add(indice_mer);//5
    this.analisis_merito.add(cedula);//6

}

public void guardar_tabla_merito(Connection con, LinkedList<String> informacion){


/*
    for(int f=0; f<informacion.size(); f = f+6){//recorriendo el registro

        System.out.println("-----------------------------------------");
        System.out.println("informacion "+informacion.get(f));
        System.out.println("informacion "+informacion.get(f+1));
        System.out.println("informacion "+informacion.get(f+2));
        System.out.println("informacion "+informacion.get(f+3));
        System.out.println("informacion "+informacion.get(f+4));
        System.out.println("informacion "+informacion.get(f+5));
        System.out.println("-----------------------------------------");
    }//fin recorrido
*/

  
    PreparedStatement preparada = null;


        try {
         /*   System.out.println("Niveles de Aislamiento: " );
            System.out.println("TRANSACTION_NONE "+con.TRANSACTION_NONE);
            System.out.println("TRANSACTION_READ_COMMITTED "+con.TRANSACTION_READ_COMMITTED);
            System.out.println("TRANSACTION_READ_UNCOMMITTED "+con.TRANSACTION_READ_UNCOMMITTED);
            System.out.println("TRANSACTION_REPEATABLE_READ "+con.TRANSACTION_REPEATABLE_READ);
            System.out.println("TRANSACTION_SERIALIZABLE "+con.TRANSACTION_SERIALIZABLE);*/

            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);//nivel mas fuerte de aislamiento
            System.out.println("Nivel de Aislamiento: " + con.getTransactionIsolation());

            con.setAutoCommit(false);//desabilitando el auto commit para manejo de transacciones

            preparada = con.prepareStatement("INSERT INTO control_de_estudio.orden_merito VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?)");

            for(int x=0; x<informacion.size(); x=x+7){
                preparada.clearParameters();

                preparada.setString(1,informacion.get(x+6) );//cedula
                preparada.setString(2,informacion.get(x+0) );//matricula
                preparada.setString(3,"" );//grd
                preparada.setString(4,"" );//primer nombre
                preparada.setString(5,"" );//segundo nombre
                preparada.setString(6,"" );//primer apellido
                preparada.setString(7,"" );//segundo apellido
                preparada.setString(8,informacion.get(x+1) );//pto_aca
                preparada.setString(9,informacion.get(x+2) );//pto_mer
                preparada.setString(10,informacion.get(x+3) );//cre
                preparada.setString(11,informacion.get(x+4) );//IAcademico
                preparada.setString(12,informacion.get(x+5) );//IMerito

                preparada.addBatch();
              
            }

            preparada.clearParameters();//limpiando parametros
            preparada.executeBatch();//ejecutando lotes

            con.commit();//haciendo permanente los cambios y liberando la base de datos
            preparada.clearBatch();//limpiando listas de sentencias
            preparada.close();//cerrando la sentencia
            ima.mensaje_informacion("PROCESO DE ORDEEN DE MERITO ALMACENADO CON EXITO...!!!", "ORDEN DE MERITO", "exito", "png");



        } catch (SQLException ex) {
            Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex);
            if(con!=null){
                System.err.println("Error en el envio de informacion. Transaccion revertida. Rolling Back");
                try {
                    con.rollback();//regresando los cambios
                    ima.mensaje_informacion("Error enviando informacion a la base de datos. Devolviendo cambios. Rolling Back\n"+ex.getMessage(), "ERROR SQL", "error", "png");
                } catch (SQLException ex1) {
                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex1);

                }

            }
        }finally{
            try {
                con.setAutoCommit(true);//regresando la condicion original del  autocommit
                con.close();
                System.out.println("Cerrando base de datos");

            } catch (SQLException ex2) {
                Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }


}

public String efecto_intesivo_periodo(String periodo){
    String conversion = periodo;
    
    if(periodo.startsWith("I1")) conversion = "1PIV"+periodo.substring(2);
    if(periodo.startsWith("I2")) conversion = "2PIV"+periodo.substring(2);
    if(periodo.startsWith("E1")) conversion = "1"+periodo.substring(2);
    if(periodo.startsWith("E2")) conversion = "2"+periodo.substring(2);
    
    
return conversion;
}

private String periodo_inscrito(Connection con, String cedula, String periodo_consultado){
    String periodo=null;
    
    Statement sentencia = null;
    ResultSet resultado = null;
    
    
    try{
        sentencia = con.createStatement();
        resultado = (ResultSet) sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE cedula='"+cedula+"' AND periodo='"+periodo_consultado+"'; ");
        if(resultado.next()){
            periodo = resultado.getString("periodo");//analizar este metodo para futuro uso
        }else{
            periodo = "[--SIN REGISTRO DE INSCRIPCIÓN EN ESTE PERÍODO--]";
        }
        
        sentencia.close();
        resultado.close();
                
        
    }catch(SQLException ex){
        Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);    
    }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
return periodo;
}


//** este metodo permite llenar de manera ordenada el LinkedList de egresados para luego generar el informe*/
public void llenado_notas_egresados(String cedula, String codigo_carrera, String codigo_opcion, String semestre, String cod_materia, String asignatura, String nota, String condicion, String matricula, String periodo_academico, String credito, String año_egreso ){

    this.getEgresados().add(cedula);//
    this.getEgresados().add(codigo_carrera);//
    this.getEgresados().add(codigo_opcion);//
    this.getEgresados().add(semestre);//
    this.getEgresados().add("02");//modalidad 02 que es semestre. 01 es termino
    this.getEgresados().add(reporte.arreglo_cod_mat(cod_materia));//
    this.getEgresados().add(asignatura);//
    this.getEgresados().add(nota);//
    this.getEgresados().add(condicion);//
    this.getEgresados().add(matricula);//
    this.getEgresados().add(periodo_academico);//
    this.getEgresados().add(credito);//
    this.getEgresados().add(año_egreso);//
//System.out.println("ALMACENANDO-----------"+cedula+"   ---   "+this.getEgresados().size());

}


private void informe_errores_record(String ruta, String cedula, String codigo, String nombre){
    //System.out.println("ELEMENTOS ANALIZADOS-------------||||||||||--------------: "+ruta+"\n"+cedula+"\n"+codigo+"\n"+nombre);    
        
    if(ruta!=null){
        if(nombre.equalsIgnoreCase("codigo no coincide con el pensum")){
            doc.escritor_texto_mejorado(ruta+"informe_de_errores.txt","CEDULA;MATERIA;OBSERVACION",cedula+";"+codigo+";"+this.calendario_2()+": "+nombre);
        }
    }
}

void formato_cabecera(Document documento, Font[] fuente,String estudiante, String cedula, String matricula,String carrera, String turno, String semestre) throws DocumentException{
    float[] parametros = {2.2f,10.0f,3.0f,2.2f};                                
    PdfPTable cabecera = new PdfPTable(parametros);
    cabecera.setWidthPercentage(95);
    PdfPCell filas = null;
//    filas.setBorder(Rectangle.NO_BORDER);
                               
                                    filas = new PdfPCell(new Paragraph("ALUMNO:",fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                 //   filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph(estudiante,fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                   // filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph("CÉDULA:",fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                   // filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph(cedula,fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_RIGHT); filas.setBorder(Rectangle.NO_BORDER);
                                  //  filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
             documento.add(cabecera);  
             
             parametros = new float[]{1.68f,7.70f,3.0f,1.0f};  
             cabecera = new PdfPTable(parametros);
             cabecera.setWidthPercentage(95);
                                    filas = new PdfPCell(new Paragraph("MATRÍCULA:",fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                    //filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph(matricula,fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                    //filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph("SEMESTRE:",fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                    //filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph(semestre,fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_RIGHT); filas.setBorder(Rectangle.NO_BORDER);
                                    //filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                documento.add(cabecera);                    
                parametros = new float[]{2.2f,10.0f,3.0f,2.2f};
                cabecera = new PdfPTable(parametros);
                cabecera.setWidthPercentage(95);
                                    filas = new PdfPCell(new Paragraph("CARRERA:",fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                    //filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    //filas = new PdfPCell(new Paragraph(carrera,fuente[1]));
                                    filas = new PdfPCell(new Paragraph(this.nombre_real_carrera(carrera, "", 2),fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                   // filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph("TURNO:",fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT); filas.setBorder(Rectangle.NO_BORDER);
                                    //filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                                    filas = new PdfPCell(new Paragraph(turno,fuente[1]));
                                    filas.setHorizontalAlignment(Element.ALIGN_RIGHT); filas.setBorder(Rectangle.NO_BORDER);
                                   // filas.setBackgroundColor(new Color(147,247,135));
                                    cabecera.addCell(filas);
                        
                                    cabecera.setSpacingAfter(1.0f);
       documento.add(cabecera);
 
}

void formato_contenido_tabla(PdfPTable notas, PdfPCell celdas,LinkedList<String> orden, int z, LinkedList<String> pensum, Font[] fuente, String cedula,String carrera,String turno, String matricula, String nucleo_extension){
                                   int multiplicacion = 0, uc = 0, nota = 0;
                                   
                                   String aux = "", aux2="0";
                                   
                                   if(orden.get(z+2) != null && Integer.valueOf(orden.get(z+2)) > 0) nota = Integer.valueOf(orden.get(z+2));// se considera la definitiva
                                   if(orden.get(z+3) != null && Integer.valueOf(orden.get(z+3)) > 0) nota = Integer.valueOf(orden.get(z+3));// se considera la reparacion
                                   if(orden.get(z+6) != null && Integer.valueOf(orden.get(z+6)) > 0) nota = Integer.valueOf(orden.get(z+6));// se considera la def rep.
                                   //se toma la nota de la ultima condicion que se cumpla
                                   
                                   
                                    celdas = new PdfPCell(new Paragraph(this.efecto_intesivo_periodo(orden.get(z+5)),fuente[0]));//periodo academico de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);
    
                                    
                                    //celdas = new PdfPCell(new Paragraph(this.semestre_materia(orden.get(z),orden.get(z+5) ,pensum),fuente[0]));//semestre de la materia
                                    if(orden.get(z+4).equalsIgnoreCase("EQUIVALENCIA")){aux2 = "0";}
                                    else{aux2 = this.semestre_materia(orden.get(z),orden.get(z+5) ,pensum);}//si es una materia por equivalencia se coloca el 0 el semestre
                                    celdas = new PdfPCell(new Paragraph(aux2,fuente[0]));//semestre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    String codigo_materia = orden.get(z);
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO") && (orden.get(z+1).contains("PASANTÍA") || orden.get(z+1).contains("PASANTIA") || orden.get(z+1).contains("TRABAJO ESPECIAL")) ){ codigo_materia = "";}        
                                    celdas = new PdfPCell(new Paragraph(codigo_materia,fuente[0]));//codigo de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);
                                    
                                    String nombre = orden.get(z+1);
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO") && (nombre.contains("PASANTÍA") || nombre.contains("PASANTIA")) ){ nombre = "DESARROLLO DE PASANTÍA";}
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO") && (nombre.contains("TRABAJO ESPECIAL") ) ){ nombre = "DESARROLLO TRABAJO ESPECIAL DE GRADO";}
                                    if(nombre.contains("(ELECTIVA NO TECNICA)") && carrera.contains("INGENIER") == false){ nombre = nombre.replace("ELECTIVA NO TECNICA", "ELECTIVA");}
                                    celdas = new PdfPCell(new Paragraph(nombre,fuente[0]));//nombre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    notas.addCell(celdas);

                                     //            celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),orden.get(z+2)),fuente[0]));//definitiva de la materia
                                    celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),this.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[0],orden.get(z+1)),fuente[0]));//definitiva de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);
                                    
                                     //    celdas = new PdfPCell(new Paragraph(orden.get(z+3),fuente[0]));//nota de la reparacion
                                    celdas = new PdfPCell(new Paragraph(this.nota_presentacion(orden.get(z+2),orden.get(z+3), orden.get(z+6),orden.get(z+4))[1],fuente[0]));//nota de la reparacion
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);
                                    
                                    uc = this.uc_materia(orden.get(z), orden.get(z+5),pensum);
                                    if(uc<=0){ aux = "";}else{ aux = String.valueOf(uc);}
                                    if(orden.get(z+4).equalsIgnoreCase("MANTENIMIENTO")) aux = ""; else aux = String.valueOf(uc);
                                    celdas = new PdfPCell(new Paragraph(aux,fuente[0]));//unidades de credito de la materia                                    
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);
                                    
                                   // if(uc<=0 || orden.get(z+4).equalsIgnoreCase("EQUIVALENCIA")){ aux = "";}else{ aux = String.valueOf(uc*nota);}
                                    aux = this.puntos_visuales_pasantias(orden.get(z+1),uc ,orden.get(z+4), String.valueOf(uc*nota));
                                    celdas = new PdfPCell(new Paragraph(aux,fuente[0]));//puntos
                                    celdas.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    notas.addCell(celdas);
                                    
                                   // System.out.println("RASTREO "+orden.get(z+4)+" - "+orden.get(z+3));
                                    
                                    celdas = new PdfPCell(new Paragraph(this.sin_condicion(orden.get(z+4),orden.get(z+3)),fuente[0]));//condicion de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    

                                    this.llenado_notas_egresados(cedula, //cedula del estudiante
                                                                    this.opsu_carrera(carrera), //codigo opsu de la carrera
                                                                    this.opsu_sede(nucleo_extension)+this.opsu_carrera(carrera)+this.opsu_turno(turno), //codigo de la opcion = nucleo+carrera+turno
                                                                    this.semestre_materia(orden.get(z),orden.get(z+5),pensum),//semestre de la materia
                                                                    orden.get(z),//codigo de la materia
                                                                    orden.get(z+1), //nombre de la materia
                                                                    this.sin_cero(orden.get(z+4),orden.get(z+2),orden.get(z+1)),//nota
                                                                    this.sin_condicion(orden.get(z+4),orden.get(z+3)),//condicion en letra
                                                                    matricula, //matricula
                                                                    orden.get(z+5), //periodo academico
                                                                    String.valueOf(this.uc_materia(orden.get(z),orden.get(z+5) ,pensum)), //unidades de credito
                                                                    "2013"); //año de egreso



}

public String determinar_semestre_actual(LinkedList<String> orden, LinkedList<String> pensum){   
    int semestre_min = 100;    
    LinkedList<String> mas_buscados = new LinkedList<>();
   String sem = "0";
    
    /*this.getPensum().add(pensum.getString("codigo"));//0
                this.getPensum().add(String.valueOf(pensum.getInt("semestre")));//1
                this.getPensum().add(pensum.getString("asignatura"));//2
                this.getPensum().add(String.valueOf(pensum.getInt("uc")));//3

                this.getPensum().add(pensum.getString("prelacion_1"));//4
                this.getPensum().add(pensum.getString("prelacion_2"));//5
                this.getPensum().add(pensum.getString("prelacion_3"));//6
                this.getPensum().add(pensum.getString("prelacion_4"));//7
                this.getPensum().add(pensum.getString("prelacion_5"));//8
                this.getPensum().add(pensum.getString("prelacion_6"));//9
    */
    /* this.getRecord().add(buscar.-getString("CODMAT"));   //0
                 this.getRecord().add(buscar.getString("CODMAT"));  //1
                 this.getRecord().add(String.valueOf(buscar.getInt("NOTDEF")));  //2
                 this.getRecord().add(String.valueOf(buscar.getInt("NOTREP")));  //3
                 this.getRecord().add(buscar.getString("CONDIC")); //4
                 this.getRecord().add(buscar.getString("PERACA")); //5
                 
                 this.getRecord().add(String.valueOf(buscar.getInt("DEFREP")));  //6
                 this.getRecord().add(String.valueOf(buscar.getInt("PORINA")));  //7
                 this.getRecord().add(buscar.getString("CODESP")); //8

                 this.getRecord().add(String.valueOf(buscar.getInt("NOTLAB"))); //9
                 this.getRecord().add(String.valueOf(buscar.getInt("TERMINO"))); //10*/
    boolean paso = false;
   
    for(int x=0; x<orden.size(); x = x+11){//recorriendo todas las materias vista por el estudiante        
       
            if(orden.get(x+1).contains("ELECTIVA")){//si el nombre de la materia contiene alguno de estos nombres, entonces por ahora se ignora
               // System.out.println("Ignorada: "+orden.get(x)+" - "+orden.get(x+1));
            }else{
                         paso = materia.evaluacion(x, orden);
                        // System.out.println("MATERIA: "+orden.get(x)+"-"+orden.get(x+1)+" = "+paso);
                      if(paso){//si no paso la materia
                          if(mas_buscados.size()>0){//si esta vacio el registro
                              for(int m=0; m<mas_buscados.size();m=m+2){//buscando la materia aprobada para eliminar la reprobada si el codigo es igual
                                  if(orden.get(x+0).equalsIgnoreCase(mas_buscados.get(m))){//si son iguales se elimina y asi sucesivamente las veces que aparece
                                      // System.out.println("Removiendo de los mas buscado: "+orden.get(x+0));
                                      mas_buscados.remove(m);//se elimina el codigo y se desplaza hacia atras el proximo registro asi que se posiciona en el indice actual
                                      mas_buscados.remove(m);//se elimina el indice actual. y todas las veces que aparece
                                  }
                              }//fin recorrido mas buscados
                          }       
                      }else{//sino paso la materia
                          if(mas_buscados.size()>0){//si esta vacio el registro
                              for(int m=0; m<mas_buscados.size();m=m+2){//buscando la materia aprobada para eliminar la reprobada si el codigo es igual
                                  if(orden.get(x+0).equalsIgnoreCase(mas_buscados.get(m))){//si son iguales se elimina y asi sucesivamente las veces que aparece
                                    //  System.out.println("Esta materia ya fue tomado en cuenta");
                                      break;//simplemente no se hace naca porque ya fue agregado
                                  }
                              }//fin recorrido mas buscados
                          }else{//si el registro esta vacio entonces se agrega los valores
                             // System.out.println("Agregando en mas buscado: "+orden.get(x+0));
                             mas_buscados.add(orden.get(x+0));//codigo de la materia
                             mas_buscados.add(orden.get(x+10));//semestre de la materia             
                          }
                      }

            }
        
        
    }//fin recorrido materias vista por el estudiante
   
          //  System.out.println("\n\n FIN ANALISIS");
    
        for(int s=0; s<mas_buscados.size(); s=+2){//recorriendo la lista de lo mas buscados para obtener el semestre mas bajo
           // System.out.println("--> Elementos: "+mas_buscados.get(s)+" , "+mas_buscados.get(s+1));
             if(semestre_min > Integer.valueOf(mas_buscados.get(s+1))){ 
                 semestre_min = Integer.valueOf(mas_buscados.get(s+1));
             }
        
        }
    
        if(semestre_min>=100){sem = String.valueOf(orden.getLast());}else{sem = String.valueOf(semestre_min);} 
    
    
    
   /* 
    for(int i=0; i<pensum.size(); i=i+10){
         sem = pensum.get(i+1);
        if(semestre_max < Integer.valueOf(sem)){ semestre_max = Integer.valueOf(sem);}
       
    }
    System.out.println("semestres  max:"+semestre_max);*/
    return sem;
}

public String presentacion_indice(String indice_periodo){
    String valor = "";
    System.out.println("presentacion indice: "+indice_periodo);
    if(indice_periodo.equalsIgnoreCase("�")){
        valor = "0,00";
    }else{
        valor = indice_periodo;
    }
    
    /*
        celdas = new PdfPCell(new Paragraph("         :   "+this.getAcu_per().get(conteo_2+0)+
                                            "      "+this.getAcu_per().get(conteo_2+1)+
                                            "       Indice  "+this.getAcu_per().get(conteo_2+2)+" ",fuente[3]));//semestre de la materia 
    */
    
    return valor;
}

public String ajuste_temporal_semestre_equivalencias(String periodo_actual){
    String periodo = null;
            if(periodo_actual.startsWith("E")){
                periodo = periodo_actual.substring(1); // se ignora la letra E de la equivalencia solo para formate de semestre
            }else{
                periodo = periodo_actual;
            }
    
    return periodo;
}

public void formato_record_academico(LinkedList<String> orden, Font[] fuente, Paragraph parrafo, Document documento, Chunk escudo, String nucleo_extension, String cedula, String estudiante, String carrera, String matricula, int conteo ,int conteo_2, String direccion, int ajuste, LinkedList<String> pensum, registro_autoridades ra, PdfWriter escritor, String turno) throws DocumentException{
    System.out.println("FORMATO -->RECORD ACADEMICO: "+orden.size());
        
    String semestre_actual = this.determinar_semestre_actual(orden, pensum);
                System.out.println("......................................................RECORD ACADEMICO.........................");
                                //escudo.scaleAbsolute(50, 60);
                                documento.add(escudo);

                                parrafo=new Paragraph("REPÚBLICA BOLIVARIANA DE VENEZUELA\nMINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"+
                                                       "UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA\n" +
                                                       "DE LA FUERZA ARMADA NACIONAL\n"+
                                                       nucleo_extension+"\n\n",fuente[1]);
                                parrafo.setAlignment(Element.ALIGN_CENTER);
                                documento.add(parrafo);


                                parrafo= new Paragraph("RECORD ACADÉMICO\n",fuente[2]);
                                parrafo.setAlignment(Element.ALIGN_CENTER);
                               
                                documento.add(parrafo);

//----------------------------------------------------------
                                /*parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+this.nombre_real_carrera(carrera, "", 2)+"\n"
                                                     +"MATRÍCULA: "+matricula+"\n\n",fuente[1]);*/
                               this.formato_cabecera(documento,fuente, estudiante, cedula, matricula, carrera, turno, semestre_actual);
//-----------------------------------------------------------------------------
                                //float[] ancho={0.2f,0.5f,2.5f,0.2f,0.2f,0.2f,1.2f,0.3f};
                                //float[] ancho={0.2f,0.65f,3.4f,0.27f,0.2f,0.2f,1.54f,0.54f};
                                float[] ancho={0.54f,0.4f,0.65f,3.4f,0.2f,0.2f,0.27f,0.27f,1.54f};
                                //PdfPTable notas = new PdfPTable(ancho);
                               PdfPTable notas = new PdfPTable(ancho);
                                notas.setWidthPercentage(95);
                                //notas.setTotalWidth(10.0f);
                                notas.setHeaderRows(1);//estableciendo cabecera de la tabla
                                PdfPCell celdas = null;

                                //PdfPTable notas = new PdfPTable(6);

                                //cabecera de la tabla
                                celdas = new PdfPCell(new Paragraph("PERÍODO SEMESTRE CÓDIGO"
                                                                  + "                              NOMBRE DE LA MATERIA"
                                                                  + "                                    N.F N.R UC Puntos"
                                                                  + "           Observaciones",fuente[0]));//semestre de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                celdas.setBackgroundColor(new Color(147,247,135));
                                celdas.setBorder(Rectangle.NO_BORDER);
                                celdas.setColspan(9);
                                notas.addCell(celdas);
                                

                               this.setComp_periodo(orden.get(5));


                                //for(int z=0;z<orden.size();z=z+6){//escribiendo las materias del alumno en el archivo pdf
                                for(int z=0;z<orden.size();z=z+11){//escribiendo las materias del alumno en el archivo pdf
                                    this.informe_errores_record(direccion, cedula, orden.get(z), orden.get(z+1));
                                    

                //                    System.out.println("z/6: "+(z/6));
                                    conteo=conteo+1;

                                    if(conteo>ajuste){//se ingresan datos a la tabla hasta 50 filas, luego se van compiando las otras en las paginas siguientes
                                    //   System.out.println("copiando en la pagina siguiente");


                                        this.setNumero_pagina(this.getNumero_pagina()+1);//contando cuantas paginas para presentar toda la tabla del record

                                        documento.add(notas);//agregando la tabla parcial al documento actual


                                             parrafo = new Paragraph("\n\n"+this.calendario_2(),fuente[1]);//colocando la fecha de emision del documento
                                             parrafo.setAlignment(Element.ALIGN_LEFT);
                                             documento.add(parrafo);

                                             parrafo = new Paragraph("\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("DIRECTOR(A)").split(":")[5]+"/"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[5]+"\n",fuente[1]); //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                             parrafo.setAlignment(Element.ALIGN_LEFT);
                                             documento.add(parrafo);

                                        notas.deleteBodyRows();//inicializando la tabla otra vez para agregar nuevos valores
                                        notas.setSkipFirstHeader(false);//estableciendo una cabecera por cada pagina nueva
                                        this.pie_pagina(escritor, documento,this.getNumero_pagina());
                                        documento.newPage();//pasa a la pagina siguiente
                                        conteo=0;//se inicializa el conteo nuevamente

                                        //parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+"\n"
                                        /*parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+this.nombre_real_carrera(carrera, "", 2)+"\n"
                                                      +"MATRÍCULA: "+matricula+" \n\n",fuente[1]);
                                        parrafo.setAlignment(Element.ALIGN_LEFT);
                                        documento.add(parrafo);*/
                                         this.formato_cabecera(documento,fuente, estudiante, cedula, matricula, carrera, turno, semestre_actual);

                                    }

                                 
                                   
                                    if(this.getComp_periodo().equalsIgnoreCase(this.ajuste_temporal_semestre_equivalencias(orden.get(z+5))) ){//si son el mismo periodo                                       
                                       this.formato_contenido_tabla(notas, celdas, orden, z, pensum, fuente, cedula, carrera, turno, matricula, nucleo_extension);                                       
                                   }else{//en caso que sean distinto los periodos, copio el espacio en blanco y luego la fila que corresponde


                                        //ESPACIO EN BLANCO............................................................COLOCANDO LA NOTA
                                       /*
                                        celdas = new PdfPCell(new Paragraph("Acumulado al Período    "+orden.get(z+5)+"   :   "
                                                                                                      + "      "+this.getAcu_per().get(conteo_2+0)+
                                                                                                      "    "+this.getAcu_per().get(conteo_2+1)+
                                                                                                      "    Indice    "+this.getAcu_per().get(conteo_2+2)+" ",fuente[3]));//semestre de la materia
                                        celdas.setColspan(9);
                                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER); celdas.setBorder(Rectangle.NO_BORDER);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);
                                        */
                                       celdas = new PdfPCell(new Paragraph("",fuente[3]));
                                       celdas.setColspan(3); celdas.setBorder(Rectangle.NO_BORDER);
                                       notas.addCell(celdas);
                                       
                                      // celdas = new PdfPCell(new Paragraph("Acumulado al Período    "+this.getComp_periodo()+"",fuente[3]));//semestre de la materia                                       
                                       celdas = new PdfPCell(new Paragraph("Acumulado al Período    "+this.efecto_intesivo_periodo(this.getComp_periodo())+"",fuente[3]));//semestre de la materia. actuando sobre los semestres de equivalencia e intensivos                                       
                                       celdas.setHorizontalAlignment(Element.ALIGN_RIGHT); celdas.setBorder(Rectangle.NO_BORDER);
                                       celdas.setBackgroundColor(new Color(185,190,203));
                                       notas.addCell(celdas);
                                       
                                        System.out.println("-------:::> "+((this.getAcu_per().size()/3))+" ---- indice:> "+(conteo_2/3+1));
                                       celdas = new PdfPCell(new Paragraph("         :   "+this.getAcu_per().get(conteo_2+0)+
                                                                           "      "+this.getAcu_per().get(conteo_2+1)+
                                                                           "       Indice  "+this.presentacion_indice(this.getAcu_per().get(conteo_2+2))+" ",fuente[3]));//semestre de la materia      
                                                                         //  "       Indice  "+this.getAcu_per().get(conteo_2+2)+" ",fuente[3]));//semestre de la materia      
                                       celdas.setColspan(5);
                                       celdas.setHorizontalAlignment(Element.ALIGN_LEFT); celdas.setBorder(Rectangle.NO_BORDER);
                                       celdas.setBackgroundColor(new Color(185,190,203));
                                       notas.addCell(celdas);
                                       
                                        conteo_2 = conteo_2+3;//es para recorrer el LinkedList que guarda el valor acumulado por periodo

                                        //..........................................................................................


                                    //GARANTIZANDO COPIAR EL REGISTRO ACTUAL

                                         this.formato_contenido_tabla(notas, celdas, orden, z, pensum, fuente, cedula, carrera, turno, matricula, nucleo_extension);
                                        
                                         this.setComp_periodo(orden.get(z+5));

                                          
                                   }

                                }//fin del recorrido del LinkedList orden




                                    notas.spacingBefore();//espacio despues que se coloca la tabla
                                    documento.add(notas);
                                    //UC APROBADAS, PTOS ACUMULADOS, INDICE ACADEMICO

                                    parrafo= new Paragraph("    Total de U.C. Equivalencias: "+this.getUc_equivalencias()+"\n"+
                                                           "     Total de U.C. Cursadas: "+this.getUc_cursadas()+"\n"+
                                                           "     Total de U.C.: "+(this.getUc_cursadas()+this.getUc_equivalencias())+"\n"+
                                                           "     Total de Puntos: "+this.getPuntos()+"\n"+
                                                           "     ÍNDICE ACADÉMICO: "+df.format(this.getIndice_academico()),fuente[1]);
                                    parrafo.setAlignment(Element.ALIGN_LEFT);
                                    documento.add(parrafo);

                             this.reporte_todos_indices(cedula, estudiante, df.format(this.getIndice_academico()),carrera);//GUARDANDO LOS INDICES DE TODOS LOS ESTUDIANTES DE LA CARRERA

                                PdfPTable firmas = new PdfPTable(2);
                                PdfPCell contenido = null;

                                //masivos m = new masivos();
                               // OBSERVACION EN EL RECORD
                                if(this.getObservacion().length()>1){//solo si hay algo que escribir se coloca la observacion en el record
                                    
                                    parrafo= new Paragraph("\n\nOBSERVACIÓN:\n"+this.getObservacion()+"\n\n\n\n\n\n",fuente[1]);
                                    parrafo.setAlignment(Element.ALIGN_LEFT);
                                    documento.add(parrafo);
                                    this.setObservacion("");

                                }else{

                                    parrafo= new Paragraph("\n\nOBSERVACIÓN:\n\n\n\n\n",fuente[1]);
                                    parrafo.setAlignment(Element.ALIGN_LEFT);
                                    documento.add(parrafo);
                                }
                                //------------------------------------

                                //jefe de control de estudio
                              //  contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE DE LA DIVISION DE SECRETARIA",fuente[0]));
                               
                                contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE DE LA UNIDAD DE SECRETARÍA",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);

                                contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[2]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[3]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[4]+"\n\n"+this.sedes_principales(nucleo_extension)+"\n\n DECANO",fuente[0]));
                                //contenido = new PdfPCell(new Paragraph("",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);

                                //Decano del nucleo
                                contenido = new PdfPCell(new Paragraph("",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);

                                contenido = new PdfPCell(new Paragraph("",fuente[0]));
                                //contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[2]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[3]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[4]+"\n\n"+this.sedes_principales(nucleo_extension)+"\n\n DECANO",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);                                                     //TCNEL (EJBV) PABLO JOSÉ BRAVO PARRA
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);







                                documento.add(firmas);






                                    //NOTA INFORMATIVA

                                  //  parrafo= new Paragraph("\nNota: Estas páginas estan de manera informativa, cualquier pregunta por favor dirigirse al Departamento de Secretaria de su respectiva sede.\n\n",fuente[1]);
                                   // parrafo.setAlignment(Element.ALIGN_CENTER);
                                   // documento.add(parrafo);



                                     //Chunk barra = new Chunk(this.codigo_matricial("hola"),0,-60);

                                      //SOLO SE USA CUANDO ES NECESARIO EQUIVALENCIA POR ORDEN ADMINISTRTIVA 081
                                           // parrafo = new Paragraph("\nOBSERVACIÓN: Materia(s) aprobada(s) por EQUIVALENCIA segun Orden Administrativa 081  \n\n",fuente[1]); //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                           // parrafo.setAlignment(Element.ALIGN_LEFT);
                                           // documento.add(parrafo);
                                
                                       //----------------------------------------------LEYENDA
                                     parrafo = new Paragraph("\nLEYENDA:\nN.F: NOTA FINAL.\nN.R: NOTAS REPARACIÓN.\nU.C: UNIDADES CRÉDITO.\nPIV: PERÍODO INTENSIVO VACACIONAL."
                                                            ,fuente[3]);//colocando la fecha de emision del documento
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);

                                

                                       //INICIALES Y FECHAS
                                     parrafo = new Paragraph("\n"+this.calendario_2(),fuente[1]);//colocando la fecha de emision del documento
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);

                                     parrafo = new Paragraph("\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("DIRECTOR(A)").split(":")[5]+"/"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[5]+"\n",fuente[1]); //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);



                                     documento.add(this.codigo_matricial(cedula+" - "+estudiante+" - "+carrera));//CODIGO DE BARRA

                                     this.setNumero_pagina(this.getNumero_pagina()+1);//continuando con el conteo de paginas
                                     this.pie_pagina(escritor, documento, this.getNumero_pagina());//colocando el numero de la pagina

                                   //  this.cabecera(escritor, documento,"CEDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+ "    MATRICULA: "+matricula);//cabecera del documento

                                     documento.newPage();


        //_-------------------------FIN RECORD ACADEMICO-----------------------------


}



    //metodos de la clase
public void record_academico(boolean inscripcion, boolean record, boolean constancia, String nombre_archivo,String cedula, String estudiante, String carrera, String turno, String nucleo_extension, String inscrito  , LinkedList<String> pdf_mi, LinkedList<String> orden, LinkedList<String> pensum, int ajuste, String periodo_acade) throws DocumentException, BadElementException, MalformedURLException, IOException, URISyntaxException{
encriptacion encriptar = new encriptacion();
char c[] = encriptar.conversor(Math.random()+"-"+cedula, "SHA-1").toCharArray();
String direccion = nombre_archivo;


    if(inscripcion==true || record ==true || constancia==true){//activando todos los procesos internos si unos de estos elementos son verdaderos
            int conteo=0;
            int conteo_2=0;
            String matricula=null;
            this.setNumero_pagina(0);
            registro_autoridades ra = new registro_autoridades();//creando una instancia
            ra.lista_autoridades(new conexion_base_de_datos().getConexion());//creando lista de autoridades

                    matricula=this.opsu_matricula(inscrito,this.opsu_sede(nucleo_extension), this.opsu_carrera(carrera), this.opsu_turno(turno) , cedula);

                    //------------------------------------orden de  merito

                     // this.tabla_merito(cedula,matricula, this.getPuntos(), this.getPuntos_merito(), this.getUc_cursadas(),  df.format(this.getIndice_academico()),  df.format(this.getIndice_merito()));

                   //--------------------------------------------

        System.out.println("PROCESANDO ESTUDIANTE: "+cedula);

        

            FileOutputStream archivo = null;
                try {
                    
                    if(record==true && constancia==false) nombre_archivo = nombre_archivo.concat("Record_Academico_"+cedula + ".pdf");
                    if(record==false && constancia==true) nombre_archivo = nombre_archivo.concat("Constancia_Estudio_"+cedula + ".pdf");
                    if(record==true && constancia==true) {nombre_archivo = nombre_archivo.concat("Record_y_Constancia_"+cedula + ".pdf");}
                    
                    archivo = new FileOutputStream(nombre_archivo);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                }

                    //Document documento = new Document(PageSize.LETTER);
                    Document documento = new Document(PageSize.LETTER,20,20,20,20);//margenes de 20mm = 2cm

                    PdfWriter escritor = PdfWriter.getInstance(documento, archivo);
                    documento.addTitle("REPORTE INSCRIPCIÓN");
                    documento.addSubject("Informacion del desempeño de cada estudiante");
                    documento.addKeywords("Constancia de incripcion, record académico");
                    documento.addCreator("Software Inscripcion UNEFA 2010");
                    documento.addAuthor("Ing. Cusatti Andy");
                    documento.addHeader("Expires", "0");

                    documento.open();

                      //  System.out.println("RUTA DE LA LETRA nnnnyyyyyy................."+getClass().getResource("/letra/arial.ttf").getPath());
                        File origen  = new File(getClass().getResourceAsStream("/letra/arial.ttf").toString()); //registrando nueva letra
                        FontFactory.register(origen.getPath(),"ARIAL");
                       
                        
                        Font[] fuente = new Font[5];//configuracion del tamaño y el tipo de letra
                        /*fuente[0]= FontFactory.getFont("ARIAL",6,Font.NORMAL);
                        fuente[1]= FontFactory.getFont("ARIAL",8,Font.NORMAL);
                        fuente[2]= FontFactory.getFont("ARIAL",14,Font.NORMAL);
                        fuente[3]= FontFactory.getFont("ARIAL",6,Font.BOLD);*/
                        
                        fuente[0]= FontFactory.getFont("ARIAL",8,Font.NORMAL);
                        fuente[1]= FontFactory.getFont("ARIAL",10,Font.NORMAL);
                        fuente[2]= FontFactory.getFont("ARIAL",15,Font.NORMAL);
                        fuente[3]= FontFactory.getFont("ARIAL",8,Font.BOLD);
                        fuente[4]= FontFactory.getFont("ARIAL",6,Font.NORMAL);
                        
                        
                        /*Font[] fuente = new Font[4];//configuracion del tamaño y el tipo de letra
                        fuente[0]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.NORMAL);
                        fuente[1]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.NORMAL);
                        fuente[2]= FontFactory.getFont(FontFactory.TIMES_ROMAN,14,Font.NORMAL);
                        fuente[3]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.BOLD);*/
                        

                        Image escudo = Image.getInstance(getClass().getResource("/actualizacion_de_datos/imagenes/escudo3.jpg"));
                        //escudo.scaleAbsolute(50, 60);
                        escudo.scaleAbsolute(70, 80);

                        //Chunk escud = new Chunk(escudo,0,-80);
                        Chunk escud = new Chunk(escudo,30,-80);
                       // escudo.setAlignment(escudo.ALIGN_LEFT | escudo.TEXTWRAP);


                        Paragraph parrafo = new Paragraph();
        //-----CONSTANCIA DE INSCRIPCION----------------------------------------------
                     if(inscripcion==true){//habilitando constancia de inscripcion
                        documento.add(escud);//agregando escudo

                        parrafo=new Paragraph("REPÚBLICA BOLIVARIANA DE VENEZUELA\nMINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"+
                                               "UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA\n" +
                                               "DE LA FUERZA ARMADA NACIONAL\n"+
                                               //"NÚCLEO ARAGUA-SEDE MARACAY\n\n\n\n",fuente[1]);
                                               nucleo_extension+"\n\n\n\n",fuente[1]);
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);

                        System.out.println("CONSTANCIA DE INSCRIPCION");
                        parrafo= new Paragraph("CONSTANCIA DE INSCRIPCIÓN\nPERIODO: "+pdf_mi.get(8)+"\n\n\n\n",fuente[2]);
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);


                        //parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+"\n"
                        parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+this.nombre_real_carrera(carrera,"",2)+"\n"
                                              +"MATRÍCULA: "+matricula+" \n\n",fuente[1]);
                                               //+ "MATRICULA: 1-2007-0424D-18930730\n\n",fuente[1]);
                        parrafo.setAlignment(Element.ALIGN_LEFT);
                        documento.add(parrafo);


        //System.out.println(materia.getPdf_mi().size());

                        //tabla de materias inscrita dentro del archivo pdf
                        float[] conf={0.2f,0.6f,3.8f,0.3f,0.7f,0.2f,1.5f,1.3f};                        
                        PdfPTable tabla = new PdfPTable(conf);
                        tabla.setTotalWidth(520f);//72f =1 in=2,54cm                                                
                        tabla.setLockedWidth(true);
                        PdfPCell filas = null;

                       //cabecera de la tabla
        //"SEMESTRE", "CODIGO", "MATERIA", "UC", "SECCION", "HORARIO", "DOCENTE"
                            filas = new PdfPCell(new Paragraph("S",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);

                            filas = new PdfPCell(new Paragraph("CÓDIGO",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);

                            filas = new PdfPCell(new Paragraph("ASIGNATURA",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);

                            filas = new PdfPCell(new Paragraph("UC",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);

                            filas = new PdfPCell(new Paragraph("SECCIÓN",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);

                            filas = new PdfPCell(new Paragraph("C",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);

                            filas = new PdfPCell(new Paragraph("HORARIO",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);

                            filas = new PdfPCell(new Paragraph("DOCENTE",fuente[1]));//codigo de la materia
                            filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                            filas.setBackgroundColor(new Color(147,247,135));
                            tabla.addCell(filas);
                        //fin cabecera


                            if(pdf_mi.size()<=0){System.out.println("Este alumno no tiene materias inscritas");}else{
                                    for(int i=0;i<pdf_mi.size();i=i+9){//llenando con las materias inscritas

                                     this.setUc_inscritas(this.getUc_inscritas()+Integer.valueOf(pdf_mi.get(i+3)));


                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+0),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(filas);

                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+1),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(filas);

                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+2),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    tabla.addCell(filas);

                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+3),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(filas);

                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+4),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(filas);

                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+7),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(filas);


                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+5),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    tabla.addCell(filas);

                                    filas = new PdfPCell(new Paragraph(pdf_mi.get(i+6),fuente[0]));//llenando tabla con las materias recien inscritas
                                    filas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    tabla.addCell(filas);

                                    
                                    }//fin llenando


                        tabla.setSpacingAfter(10f);
                        documento.add(tabla);
            }



                        //LEYENDA DE LA CONSTANCIA DE INSCRIPCION
                        parrafo= new Paragraph("     TOTAL U.C. INSCRITAS:..........."+this.getUc_inscritas()+"\n\n",fuente[1]);
                        parrafo.setAlignment(Element.ALIGN_LEFT);
                        documento.add(parrafo);

        //                parrafo= new Paragraph("     LEYENDA:\n"+"        S = SEMESTRE\n"+"        D = DEFINITIVA\n"+"        R = REPARACION\n"+"        P = PERIODO ACADEMICO\n"+"        UC = UNIDADES DE CREDITO\n",fuente[1]);
          //              parrafo.setAlignment(Element.ALIGN_LEFT);
            //            documento.add(parrafo);


                        //espacios en blanco

                        parrafo= new Paragraph("\n\n\n\n\n");
                        documento.add(parrafo);

                        //PIE DE PAGINA


                        PdfPTable pie = new PdfPTable(2);
                        PdfPCell fila_pie = null;

                        //estudiante

                        fila_pie = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\nBr. "+estudiante+"\n\nESTUDIANTE",fuente[0]));
                        fila_pie.setHorizontalAlignment(Element.ALIGN_CENTER);
                        fila_pie.setBorder(Rectangle.NO_BORDER);
                        pie.addCell(fila_pie);

                        //jefe de control de estudio
                        fila_pie = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE DEL DEPARTAMENTO DE SECRETARIA",fuente[0]));
                        fila_pie.setHorizontalAlignment(Element.ALIGN_CENTER);
                        fila_pie.setBorder(Rectangle.NO_BORDER);
                        pie.addCell(fila_pie);

                        documento.add(pie);

                        parrafo= new Paragraph("\n\n\n\n\n");//espacio en blanco
                        documento.add(parrafo);
                        parrafo= new Paragraph("     LEYENDA: S = SEMESTRE, D = DEFINITIVA, R = REPARACION, PA = PERIODO ACADÉMICO, UC = UNIDADES DE CRÉDITO\n"
                                              +"                           C = CONDICIÓN, N = NORMAL, E = EQUIVALENCIA, S = SUFICIENCIA ,P = REPITENCIA, L = PARALELO",fuente[0]);
                        parrafo.setAlignment(Element.ALIGN_LEFT);
                        documento.add(parrafo);


                        this.pie_pagina(escritor, documento, this.getNumero_pagina());//el numero de la pagina en la parte central inferior

                        documento.newPage();//colocando la informacion en una pagina nueva
            }//fin habilitacion constancia de inscripcion
        //------------------------FIN CONSTANCIA DE INSCRIPCION------------------------------------------------------



        if(record==true){//habilitando record academico

                System.out.println("RECORD ACADEMICO: "+orden.size());
                  if(orden.size()<=0){
                      System.out.println("Este alumno no tiene materia registrada");
                      parrafo=new Paragraph("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS",fuente[2]);
                      parrafo.setAlignment(Element.ALIGN_CENTER);
                      documento.add(parrafo);
                  }else{
                      
                System.out.println("......................................................RECORD ACADEMICO.........................");
                           //PAGINA SIGUIENTE . RECORD ACADEMICO
this.formato_record_academico(orden,
                              fuente,
                              parrafo,
                              documento,
                              escud,
                              nucleo_extension,
                              cedula,
                              estudiante,
                              carrera,
                              matricula,
                              conteo,
                              conteo_2,
                              direccion,
                              ajuste,
                              pensum,
                              ra,
                              escritor,
                              turno);
/*                
                             //Image escudo = Image.getInstance(getClass().getResource("/actualizacion_de_datos/imagenes/escudo3.jpg"));
                                escudo.scaleAbsolute(50, 60);

                               // Chunk escud = new Chunk(escudo,0,-60);
                               // escudo.setAlignment(escudo.ALIGN_LEFT | escudo.TEXTWRAP);
                                documento.add(escud);

                               // Paragraph parrafo = new Paragraph();

                                parrafo=new Paragraph("REPÚBLICA BOLIVARIANA DE VENEZUELA\nMINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"+
                                                       "UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA\n" +
                                                       "DE LA FUERZA ARMADA NACIONAL\n"+
                                                       nucleo_extension+"\n\n\n",fuente[1]);
                                parrafo.setAlignment(Element.ALIGN_CENTER);
                                documento.add(parrafo);


                                parrafo= new Paragraph("RECORD ACADÉMICO\n\n",fuente[2]);
                                parrafo.setAlignment(Element.ALIGN_CENTER);
                                documento.add(parrafo);

                             
                               
                                parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+this.nombre_real_carrera(carrera, "", 2)+"\n"
                                                     +"MATRÍCULA: "+matricula+"\n\n",fuente[1]);
                               parrafo.setAlignment(Element.ALIGN_LEFT);
                               documento.add(parrafo);

                                //float[] ancho={0.2f,0.5f,2.5f,0.2f,0.2f,0.2f,1.2f,0.3f};
                                float[] ancho={0.2f,0.65f,3.4f,0.27f,0.2f,0.2f,1.54f,0.54f};
                                //PdfPTable notas = new PdfPTable(ancho);
                               PdfPTable notas = new PdfPTable(ancho);
                                notas.setWidthPercentage(95);
                                //notas.setTotalWidth(10.0f);
                                notas.setHeaderRows(1);//estableciendo cabecera de la tabla
                                PdfPCell celdas = null;

                                //PdfPTable notas = new PdfPTable(6);

                                //cabecera de la tabla
                                    celdas = new PdfPCell(new Paragraph("S",fuente[1]));//semestre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("CÓDIGO",fuente[1]));//codigo de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("ASIGNATURA",fuente[1]));//nombre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("UC",fuente[1]));//unidades de credito de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("D",fuente[1]));//definitiva de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("R",fuente[1]));//nota de la reparacion
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("OBSERVACIÓN",fuente[1]));//condicion de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("PA",fuente[1]));//periodo academico de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);



                                //fin cabecera



                               this.setComp_periodo(orden.get(5));


                                //for(int z=0;z<orden.size();z=z+6){//escribiendo las materias del alumno en el archivo pdf
                                for(int z=0;z<orden.size();z=z+11){//escribiendo las materias del alumno en el archivo pdf
                                    this.informe_errores_record(direccion, cedula, orden.get(z), orden.get(z+1));
                                    

                //                    System.out.println("z/6: "+(z/6));
                                    conteo=conteo+1;

                                    if(conteo>ajuste){//se ingresan datos a la tabla hasta 50 filas, luego se van compiando las otras en las paginas siguientes
                                    //   System.out.println("copiando en la pagina siguiente");




                                        this.setNumero_pagina(this.getNumero_pagina()+1);//contando cuantas paginas para presentar toda la tabla del record

                                        documento.add(notas);//agregando la tabla parcial al documento actual


                                             parrafo = new Paragraph("\n\n"+this.calendario_2(),fuente[1]);//colocando la fecha de emision del documento
                                             parrafo.setAlignment(Element.ALIGN_LEFT);
                                             documento.add(parrafo);

                                             parrafo = new Paragraph("\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("DIRECTOR(A)").split(":")[5]+"/"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[5]+"\n",fuente[1]); //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                             parrafo.setAlignment(Element.ALIGN_LEFT);
                                             documento.add(parrafo);



                                        notas.deleteBodyRows();//inicializando la tabla otra vez para agregar nuevos valores
                                        notas.setSkipFirstHeader(false);//estableciendo una cabecera por cada pagina nueva
                                        this.pie_pagina(escritor, documento,this.getNumero_pagina());
                                        documento.newPage();//pasa a la pagina siguiente
                                        conteo=0;//se inicializa el conteo nuevamente



                                        //parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+"\n"
                                        parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+this.nombre_real_carrera(carrera, "", 2)+"\n"
                                                      +"MATRÍCULA: "+matricula+" \n\n",fuente[1]);
                                        parrafo.setAlignment(Element.ALIGN_LEFT);
                                        documento.add(parrafo);

                                    }





                                   if(this.getComp_periodo().equalsIgnoreCase(orden.get(z+5))){

                                    celdas = new PdfPCell(new Paragraph(this.semestre_materia(orden.get(z),orden.get(z+5) ,pensum),fuente[0]));//semestre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph(orden.get(z),fuente[0]));//codigo de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    //adaptacion para tovar . que en vez de electiva tecnica o no tecnica aparezca electiva 
                                   // String asignatura_temporal=null;
                                   //     if(orden.get(z+1).contains("ELECTIVA")) asignatura_temporal = orden.get(z+1).replace("ELECTIVA NO TÉCNICA","ELECTIVA");
                                 //       else{asignatura_temporal = orden.get(z+1);}    
                                 //   celdas = new PdfPCell(new Paragraph(asignatura_temporal,fuente[0]));//nombre de la materia
                                    
                                    celdas = new PdfPCell(new Paragraph(orden.get(z+1),fuente[0]));//nombre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph(String.valueOf(this.uc_materia(orden.get(z), orden.get(z+5),pensum)),fuente[0]));//unidades de credito de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                        //            celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),orden.get(z+2)),fuente[0]));//definitiva de la materia
                                    celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),this.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[0],orden.get(z+1)),fuente[0]));//definitiva de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                //    celdas = new PdfPCell(new Paragraph(orden.get(z+3),fuente[0]));//nota de la reparacion
                                    celdas = new PdfPCell(new Paragraph(this.nota_presentacion(orden.get(z+2),orden.get(z+3), orden.get(z+6),orden.get(z+4))[1],fuente[0]));//nota de la reparacion
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);
                                   // System.out.println("RASTREO "+orden.get(z+4)+" - "+orden.get(z+3));
                                    celdas = new PdfPCell(new Paragraph(this.sin_condicion(orden.get(z+4),orden.get(z+3)),fuente[0]));//condicion de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph(this.efecto_intesivo_periodo(orden.get(z+5)),fuente[0]));//periodo academico de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    this.llenado_notas_egresados(cedula, //cedula del estudiante
                                                                    this.opsu_carrera(carrera), //codigo opsu de la carrera
                                                                    this.opsu_sede(nucleo_extension)+this.opsu_carrera(carrera)+this.opsu_turno(turno), //codigo de la opcion = nucleo+carrera+turno
                                                                    this.semestre_materia(orden.get(z),orden.get(z+5),pensum),//semestre de la materia
                                                                    orden.get(z),//codigo de la materia
                                                                    orden.get(z+1), //nombre de la materia
                                                                    this.sin_cero(orden.get(z+4),orden.get(z+2),orden.get(z+1)),//nota
                                                                    this.sin_condicion(orden.get(z+4),orden.get(z+3)),//condicion en letra
                                                                    matricula, //matricula
                                                                    orden.get(z+5), //periodo academico
                                                                    String.valueOf(this.uc_materia(orden.get(z),orden.get(z+5) ,pensum)), //unidades de credito
                                                                    "2012"); //año de egreso


                                   }else{//en caso que sean distinto los periodos, copio el espacio en blanco y luego la fila que corresponde


                                        //ESPACIO EN BLANCO............................................................COLOCANDO LA NOTA

                                        celdas = new PdfPCell(new Paragraph("Acumulado                     UC = "+this.getAcu_per().get(conteo_2+0)+
                                                                                                      "    Puntos = "+this.getAcu_per().get(conteo_2+1)+
                                                                                                      "    Indice Acumulado = "+this.getAcu_per().get(conteo_2+2)+" ",fuente[3]));//semestre de la materia
                                        celdas.setColspan(8);
                                        celdas.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);
                                        conteo_2 = conteo_2+3;//es para recorrer el LinkedList que guarda el valos acumulado por periodo

                                      /*  celdas = new PdfPCell(new Paragraph("",fuente[0]));//codigo de la materia
                                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);

                                        celdas = new PdfPCell(new Paragraph("",fuente[0]));//nombre de la materia
                                        celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);

                                        celdas = new PdfPCell(new Paragraph("",fuente[0]));//unidades de credito de la materia
                                        celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);

                                        celdas = new PdfPCell(new Paragraph("",fuente[0]));//definitiva de la materia
                                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);

                                        celdas = new PdfPCell(new Paragraph("",fuente[0]));//nota de la reparacion
                                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);

                                        celdas = new PdfPCell(new Paragraph("",fuente[0]));//condicion de la materia
                                        celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);

                                        celdas = new PdfPCell(new Paragraph("",fuente[0]));//periodo academico de la materia
                                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        celdas.setBackgroundColor(new Color(185,190,203));
                                        notas.addCell(celdas);
                                        */

                                        //..........................................................................................


                                    //GARANTIZANDO COPIAR EL REGISTRO ACTUAL
/*
                                    celdas = new PdfPCell(new Paragraph(this.semestre_materia(orden.get(z),orden.get(z+5) ,pensum),fuente[0]));//semestre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);


                                    celdas = new PdfPCell(new Paragraph(orden.get(z),fuente[0]));//codigo de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);
                                     
                                    
                                    //String asignatura_temporalx= null;
                                     // if(orden.get(z+1).contains("ELECTIVA")) asignatura_temporalx = orden.get(z+1).replace("ELECTIVA NO TÉCNICA","ELECTIVA");
                                      //  else{asignatura_temporalx = orden.get(z+1);}    
                                    //celdas = new PdfPCell(new Paragraph(asignatura_temporalx,fuente[0]));//nombre de la materia                                   
                                    celdas = new PdfPCell(new Paragraph(orden.get(z+1),fuente[0]));//nombre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph(String.valueOf(this.uc_materia(orden.get(z), orden.get(z+5),pensum)),fuente[0]));//unidades de credito de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    //celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),orden.get(z+2)),fuente[0]));//definitiva de la materia
                                    celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),this.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[0], orden.get(z+1) ),fuente[0]));//definitiva de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    //celdas = new PdfPCell(new Paragraph(orden.get(z+3),fuente[0]));//nota de la reparacion
                                    celdas = new PdfPCell(new Paragraph(this.nota_presentacion(orden.get(z+2),orden.get(z+3), orden.get(z+6),orden.get(z+4))[1],fuente[0]));//nota de la reparacion
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph(this.sin_condicion(orden.get(z+4),orden.get(z+3)),fuente[0]));//condicion de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph(this.efecto_intesivo_periodo(orden.get(z+5)),fuente[0]));//periodo academico de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    notas.addCell(celdas);


                                   this.setComp_periodo(orden.get(z+5));

                                           this.llenado_notas_egresados(cedula, //cedula del estudiante
                                                                            this.opsu_carrera(carrera), //codigo opsu de la carrera
                                                                            this.opsu_sede(nucleo_extension)+this.opsu_carrera(carrera)+this.opsu_turno(turno), //codigo de la opcion = nucleo+carrera+turno
                                                                            this.semestre_materia(orden.get(z),orden.get(z+5),pensum),//semestre de la materia
                                                                            orden.get(z),//codigo de la materia
                                                                            orden.get(z+1), //nombre de la materia
                                                                            this.sin_cero(orden.get(z+4), orden.get(z+2), orden.get(z+1)),//nota
                                                                            this.sin_condicion(orden.get(z+4),orden.get(z+3)),//condicion en letra
                                                                            matricula, //matricula
                                                                            orden.get(z+5), //periodo academico
                                                                            String.valueOf(this.uc_materia(orden.get(z),orden.get(z+5) ,pensum)), //unidades de credito
                                                                            "2013"); //año de egreso




                                   }

                                }//fin del recorrido del LinkedList orden

                                


                                    notas.spacingBefore();//espacio despues que se coloca la tabla
                                    documento.add(notas);
                                    //UC APROBADAS, PTOS ACUMULADOS, INDICE ACADEMICO

                                    parrafo= new Paragraph("    Total de U.C. Equivalencias: "+this.getUc_equivalencias()+"\n"+
                                                           "     Total de U.C. Cursadas: "+this.getUc_cursadas()+"\n"+
                                                           "     Total de Puntos: "+this.getPuntos()+"\n"+
                                                           "     ÍNDICE ACADÉMICO: "+df.format(this.getIndice_academico()),fuente[1]);
                                    parrafo.setAlignment(Element.ALIGN_LEFT);
                                    documento.add(parrafo);

                             this.reporte_todos_indices(cedula, estudiante, df.format(this.getIndice_academico()),carrera);//GUARDANDO LOS INDICES DE TODOS LOS ESTUDIANTES DE LA CARRERA

                                PdfPTable firmas = new PdfPTable(2);
                                PdfPCell contenido = null;

                                //masivos m = new masivos();
                               // OBSERVACION EN EL RECORD
                                if(this.getObservacion().length()>1){//solo si hay algo que escribir se coloca la observacion en el record
                                    
                                    parrafo= new Paragraph("\n\n"+this.getObservacion()+"\n\n\n\n\n\n",fuente[1]);
                                    parrafo.setAlignment(Element.ALIGN_LEFT);
                                    documento.add(parrafo);
                                    this.setObservacion("");

                                }else{

                                    parrafo= new Paragraph("\n\n\n\n\n",fuente[1]);
                                    parrafo.setAlignment(Element.ALIGN_LEFT);
                                    documento.add(parrafo);
                                }
                                //------------------------------------

                                //jefe de control de estudio
                                contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE DE LA DIVISION DE SECRETARIA",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);

                                contenido = new PdfPCell(new Paragraph("",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);

                                //Decano del nucleo
                                contenido = new PdfPCell(new Paragraph("",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);

                                contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[2]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[3]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[4]+"\n\n"+this.sedes_principales(nucleo_extension)+"\n\n DECANO",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);                                                     //TCNEL (EJBV) PABLO JOSÉ BRAVO PARRA
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);







                                documento.add(firmas);






                                    //NOTA INFORMATIVA

                                  //  parrafo= new Paragraph("\nNota: Estas páginas estan de manera informativa, cualquier pregunta por favor dirigirse al Departamento de Secretaria de su respectiva sede.\n\n",fuente[1]);
                                   // parrafo.setAlignment(Element.ALIGN_CENTER);
                                   // documento.add(parrafo);



                                     //Chunk barra = new Chunk(this.codigo_matricial("hola"),0,-60);

                                      //SOLO SE USA CUANDO ES NECESARIO EQUIVALENCIA POR ORDEN ADMINISTRTIVA 081
                                           // parrafo = new Paragraph("\nOBSERVACIÓN: Materia(s) aprobada(s) por EQUIVALENCIA segun Orden Administrativa 081  \n\n",fuente[1]); //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                           // parrafo.setAlignment(Element.ALIGN_LEFT);
                                           // documento.add(parrafo);
                                
                                       //----------------------------------------------LEYENDA
                                     parrafo = new Paragraph("\nLeyenda:\nS: Semestre.\nUC: Unidades de Creditos.\nR: Nota de Reparación.\nD:Nota Definitiva.\nPA: Período Académico.\nPIV: Período Intensivo Vacacional."
                                                            ,fuente[3]);//colocando la fecha de emision del documento
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);

                                

                                       //INICIALES Y FECHAS
                                     parrafo = new Paragraph("\n"+this.calendario_2(),fuente[1]);//colocando la fecha de emision del documento
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);

                                     parrafo = new Paragraph("\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("DIRECTOR(A)").split(":")[5]+"/"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[5]+"\n",fuente[1]); //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);



                                     documento.add(this.codigo_matricial(cedula+" - "+estudiante+" - "+carrera));//CODIGO DE BARRA

                                     this.setNumero_pagina(this.getNumero_pagina()+1);//continuando con el conteo de paginas
                                     this.pie_pagina(escritor, documento, this.getNumero_pagina());//colocando el numero de la pagina

                                   //  this.cabecera(escritor, documento,"CEDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+ "    MATRICULA: "+matricula);//cabecera del documento

                                     documento.newPage();
*/
                    }
                                                    
                                                    
                                                    
            }//fin habilitacion record academico
               
//limpiando variableas acumulativas
                this.setUc_cursadas(0);
                this.setNumero_pagina(0);
                this.setUc_aprobadas(0);
                this.setUc_inscritas(0);
                this.setPuntos(0);
                this.setUc_equivalencias(0);
                this.getAlmacen().clear();
                orden.clear();//limpiando materias

        //_-------------------------FIN RECORD ACADEMICO-----------------------------




                        //-------------CONSTANCIA DE ESTUDIO--------------------

             if(constancia==true) {//habilitando la constancia de estudio
               
                    
                        System.out.println("CONSTANCIA DE ESTUDIO");
                        Font[] tamaño = new Font[4];//configuracion del tamaño y el tipo de letra
                        tamaño[0]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.NORMAL);
                        tamaño[1]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.NORMAL);
                        tamaño[2]= FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.NORMAL);
                        tamaño[3]= FontFactory.getFont(FontFactory.TIMES_ROMAN,14,Font.NORMAL);

                        System.out.println(" CONSTANCIA DE ESTUDIO----nivel 0");

                       escudo.scaleAbsolute(50, 60);
                       escud = new Chunk(escudo,0,-60);
                       // escudo.setAlignment(escudo.ALIGN_LEFT | escudo.TEXTWRAP);
                        documento.add(escud);

                         parrafo=new Paragraph("REPÚBLICA BOLIVARIANA DE VENEZUELA\nMINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"+
                                               "UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA\n" +
                                               "DE LA FUERZA ARMADA NACIONAL\n"+
                                               nucleo_extension+"\n\n\n\n\n\n",tamaño[2]);
                                               //"NÚCLEO ARAGUA-SEDE MARACAY\n\n\n\n\n\n",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);

                        parrafo=new Paragraph("CONSTANCIA DE ESTUDIO\n\n\n\n\n",tamaño[3]);
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);
                                                                                 //NUCLEO ARAGUA - SEDE MARACAY
                       /* parrafo=new Paragraph("          Quien suscribe, Director(A) del "+nucleo_extension+" de la Universidad Nacional "
                                             +"Experimental Politécnica de la Fuerza Armada Nacional (UNEFA), hace constar que el (la) ciudadano(a): "
                                             +estudiante+", titular de la cédula de identidad N° "+cedula+", es "
                                             +"alumno (a) regular de esta Universidad, quien ingresó en "+this.getMes_ingreso()+" de "+this.getAño_ingreso()+" a la carrera "+carrera+".",tamaño[2]);
                          */
                         
                   //     System.out.println(" CONSTANCIA DE ESTUDIO----nivel 1");
                        
                        String dirde=null;                                                
                        String periodo_academico = null;
                        
                            if(pdf_mi.size()>0)  periodo_academico = this.periodo_inscrito(new conexion_base_de_datos().getConexion(), cedula, pdf_mi.get(8));                       
                            else periodo_academico = periodo_acade;//si no esta inscrito entonces se toma como periodo el actual de trabajo
                            
                            //"1-2013";//Experimental....lo ideal que aparezca que no tiene materias inscritas por lo que no debe sacar constancia
                                                              //o que se verifique el ultimo periodo inscrito y se constraste con el periodo actual bajo analisis
                       
                     //   System.out.println(" CONSTANCIA DE ESTUDIO----nivel 2");
                        
                            
                        if(nucleo_extension.contains("SEDE")) dirde="DECANO(A)"; else dirde="DIRECTOR(A)";//verifica si es una extension o nucleo para colocar la autoridad correspondiente
                           // dirde="DECANO(A)"; //Nota: Va solo el decano ya que la autoridad del director ha sido removida
                        if(periodo_academico.startsWith("1")) periodo_academico = "I"+periodo_academico.substring(1);else periodo_academico = "II"+periodo_academico.substring(1);
            
                        
                         parrafo=new Paragraph("          Quien suscribe, "+dirde+" de la Universidad Nacional "
                                             +"Experimental Politécnica de la Fuerza Armada Nacional (UNEFA), hace constar que el (la) ciudadano(a): "
                                             +estudiante+", titular de la cédula de identidad N°: "+cedula+", es "
                                             //+"estudiante regular de esta Universidad y actualmente se encuentra cursando el periodo lectivo "+periodo_academico+" en la carrera de "+this.nombre_real_carrera(carrera,inscrito,2)+".",tamaño[2]);
                                            +"estudiante regular de esta Universidad y actualmente se encuentra cursando el periodo lectivo "+periodo_academico+" en la carrera de "+this.nombre_real_carrera(carrera,inscrito,2)+".",tamaño[2]); ///experimental solo para solventar un problema puntual
                         
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);

                        parrafo = new Paragraph("\n\n",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);
                                                                                                                    //Maracay
                        parrafo = new Paragraph("       Constancia que se expide a petición de la parte interesada en "+this.lugar_sede(nucleo_extension)+", a la fecha "+this.calendario()+".",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);


                        parrafo = new Paragraph("\n\n\n\n\n\n\n\n\n",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);
                        
                        System.out.println(" CONSTANCIA DE ESTUDIO----nivel 3");

                        PdfPTable firmas = new PdfPTable(2);
                        PdfPCell contenido = null;

                        
                        String autoridad1=null, autoridad2=null;                         
                       if(nucleo_extension.contains("SEDE")){//es un nucleo.. ejemplo UNEFA ARAGUA - SEDE MARACAY
                           // autoridad1 = "----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE DE LA DIVISION DE SECRETARIA";
                            autoridad1 = "----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE UNIDAD DE SECRETARÍA";
                            autoridad2 = "----------------------------------------------------------------\n\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[2]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[3]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[4]+"\n\n"+nucleo_extension+"\n\n DECANO";
                        }else{//es una extension
                            //autoridad1 = "----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[2]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[3]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[4]+"\n\nJEFE DE CONTROL DE ESTUDIO";
                           autoridad1 = "----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[2]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[3]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[4]+"\n\nL.E.A.D UNIDAD DE SECRETARÍA";
                            autoridad2 = "----------------------------------------------------------------\n\n"+ra.autoridad("DIRECTOR(A)").split(":")[2]+" "+ra.autoridad("DIRECTOR(A)").split(":")[3]+" "+ra.autoridad("DIRECTOR(A)").split(":")[4]+"\n\n"+nucleo_extension+"\n\n DIRECTOR(A)";
                        }
                        
                        //Nota: solo el decano firma.....los directores fueron desautorizados
        //                    autoridad1 = "----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE DE LA DIVISION DE SECRETARIA";
         //                   autoridad2 = "----------------------------------------------------------------\n\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[2]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[3]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[4]+"\n\n"+nucleo_extension+"\n\n DECANO";
                        
                        
                        
                     //   System.out.println(" CONSTANCIA DE ESTUDIO----nivel 4");
                        //jefe de control de estudio o secretaria
                        //contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[2]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[3]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[4]+"\n\nJEFE DE CONTROL DE ESTUDIO",fuente[0]));
                        //contenido = new PdfPCell(new Paragraph(autoridad1,fuente[0]));
                        contenido = new PdfPCell(new Paragraph("",fuente[0]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);

                        contenido = new PdfPCell(new Paragraph("",fuente[0]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);


                        //Decano del nucleo o director de extension
                        contenido = new PdfPCell(new Paragraph("",fuente[0]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);
                        
                        
                        //contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("DIRECTOR(A)").split(":")[2]+" "+ra.autoridad("DIRECTOR(A)").split(":")[3]+" "+ra.autoridad("DIRECTOR(A)").split(":")[4]+"\n\n"+nucleo_extension,fuente[0]));
                        contenido = new PdfPCell(new Paragraph(autoridad2,fuente[0]));                        
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);
                        
                    //    System.out.println(" CONSTANCIA DE ESTUDIO----nivel 5");
                        
                        //doc.escritor_texto_mejorado(direccion+"reporte_constancia_regulares.txt", "CEDULA; ESTUDIANTE; CODIGO_AUTENTICACION; SEDE; FECHA_EMISION",
                          //                          cedula+";"+estudiante+";"+c[2]+c[4]+c[6]+c[8]+c[10]+c[12]+c[14]+c[16]+c[18]+c[20]+";"+nucleo_extension+";"+this.calendario_2());//escritor codigo con usuario constancia
                                                        
                        ima.rastreo_documentos_expedidos(cedula, estudiante, ""+c[2]+c[4]+c[6]+c[8]+c[10]+c[12]+c[14]+c[16]+c[18]+c[20] , "CONSTANCIA DE ESTUDIO DE ALUMNO REGULAR",nucleo_extension,this.calendario());
                        
                     //   System.out.println("  CONSTANCIA DE ESTUDIO----nivel 5.1");
                    //    System.out.println("   CONSTANCIA DE ESTUDIO----nivel 5.1.1");
                            
                       //        System.out.println("\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("DIRECTOR(A)").split(":")[5]+"/"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[5]+"\n"+"\nSerial: "+c[2]+c[4]+c[6]+c[8]+c[10]+c[12]+c[14]+c[16]+c[18]+c[20]);
                        
                        contenido = new PdfPCell(new Paragraph("\n\n\n\n\n\n\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("DIRECTOR(A)").split(":")[5]+"/"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[5]+"\n" //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                                                               +"\nSerial: "+c[2]+c[4]+c[6]+c[8]+c[10]+c[12]+c[14]+c[16]+c[18]+c[20],fuente[4]));
                        contenido.setHorizontalAlignment(Element.ALIGN_LEFT);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);
                        
                     //   System.out.println("  CONSTANCIA DE ESTUDIO----nivel 5.2");

                        //Decano del nucleo o director de extension
                        contenido = new PdfPCell(new Paragraph("",fuente[0]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);

                    //    System.out.println(" CONSTANCIA DE ESTUDIO----nivel 6");
                        
                        

                        documento.add(firmas);


                        this.pie_pagina_documentado(escritor, documento,new Paragraph("Este documento tiene una vigencia de 6 Meses a partir de la fecha de expedicion.",fuente[4]));
                        System.out.println(" CONSTANCIA DE ESTUDIO----nivel 7");
        //----FIN CONSTANCIA DE ESTUDIO---------
            }// constancia de estudio.
            
             
                               documento.close();//cierra el documento
                              

                try {
                    archivo.close();
                } catch (IOException ex) {
                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                }



    }//Activacion activacion de los procesos de los metodos


 
}




//-------------------------------------------------------ALTERNATIVO-----------------------------------
//public void record_academico_alternativo(String nombre_archivo,String cedula, String estudiante, String carrera, String turno, String nucleo_extension, String inscrito  , LinkedList<String> pdf_mi, LinkedList<String> orden, LinkedList<String> pensum, String jefe_ce, String decano_director, String periodo_academico) throws DocumentException, BadElementException, MalformedURLException, IOException{
  public void record_academico_alternativo(String nombre_archivo,String cedula, String estudiante, String carrera, String turno, String nucleo_extension, String inscrito  , LinkedList<String> pdf_mi, LinkedList<String> orden, LinkedList<String> pensum, String periodo_academico) throws DocumentException, BadElementException, MalformedURLException, IOException{
    int conteo=0;
    String matricula=null;

            matricula=this.opsu_matricula(inscrito,this.opsu_sede(nucleo_extension), this.opsu_carrera(carrera), this.opsu_turno(turno) , cedula);

            registro_autoridades ra = new registro_autoridades();//creando instancia
            ra.lista_autoridades(new conexion_base_de_datos().getConexion());//listando todas las autoridades




System.out.println("PROCESANDO ESTUDIANTE: "+cedula);
System.out.println("MATERIAS INSCRITAS: "+pdf_mi.size()+" RECORD: "+orden.size());


        FileOutputStream archivo = null;
            try {
                archivo = new FileOutputStream(nombre_archivo);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
            }

                Document documento = new Document(PageSize.LETTER);
               // Document documento = new Document(PageSize.LETTER,36,36,36,36);

                PdfWriter escritor = PdfWriter.getInstance(documento, archivo);
                documento.addTitle("REPORTE INSCRIPCIÓN");
                documento.addSubject("Informacion del desempeño de cada estudiante");
                documento.addKeywords("Constancia de incripcion, record académico");
                documento.addCreator("Software Inscripcion UNEFA 2010");
                documento.addAuthor("Ing. Cusatti Andy");
                documento.addHeader("Expires", "0");

                documento.open();


                    Font[] fuente = new Font[3];//configuracion del tamaño y el tipo de letra
                    fuente[0]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.NORMAL);
                    fuente[1]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.NORMAL);
                    fuente[2]= FontFactory.getFont(FontFactory.TIMES_ROMAN,14,Font.NORMAL);


                    Image escudo = Image.getInstance(getClass().getResource("/actualizacion_de_datos/imagenes/escudo3.jpg"));
                    escudo.scaleAbsolute(50, 60);

                    Chunk escud = new Chunk(escudo,0,-60);
                   // escudo.setAlignment(escudo.ALIGN_LEFT | escudo.TEXTWRAP);
                    documento.add(escud);

                    Paragraph parrafo = new Paragraph();

                    parrafo=new Paragraph("REPÚBLICA BOLIVARIANA DE VENEZUELA\nMINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"+
                                           "UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA\n" +
                                           "DE LA FUERZA ARMADA NACIONAL\n"+
                                           //"NÚCLEO ARAGUA-SEDE MARACAY\n\n\n\n",fuente[1]);
                                           nucleo_extension+"\n\n\n",fuente[1]);
                    parrafo.setAlignment(Element.ALIGN_CENTER);
                    documento.add(parrafo);

                    System.out.println("CONSTANCIA DE INSCRIPCION "+periodo_academico);
                    parrafo= new Paragraph("CONSTANCIA DE INSCRIPCIÓN\nPERIODO: "+periodo_academico+"\n\n",fuente[2]);
                    parrafo.setAlignment(Element.ALIGN_CENTER);
                    documento.add(parrafo);


                    //parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+"\n"
                    parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+this.nombre_real_carrera(carrera, "", 2)+"\n"
                                          +"MATRÍCULA: "+matricula+" \n\n",fuente[1]);
                                           //+ "MATRICULA: 1-2007-0424D-18930730\n\n",fuente[1]);
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);


           if(pdf_mi.size()<=0 && orden.size()>0){//es que no tiene materias inscritas para el periodo actual pero puede ser un mantenimiento de matricula si tiene record
                    System.out.println("ESTUDIANTE NO TIENE MATERIA INSCRITA EN ESTE PERIODO, MANTENIMIENTO DE MATRICULA PORQUE TIENE RECORD");
                    parrafo= new Paragraph("MANTENIMIENTO DE MATRICULA\n\n\n\n ",fuente[2]);
                                           //+ "MATRICULA: 1-2007-0424D-18930730\n\n",fuente[1]);
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);

           }else{
                    //CREANDO TABLA
    //System.out.println(materia.getPdf_mi().size());

                    //tabla de materias inscrita dentro del archivo pdf
                    float[] conf={0.2f,0.6f,3.8f,0.3f,0.7f,0.2f,1.5f,1.3f};
                    PdfPTable tabla = new PdfPTable(conf);
                    tabla.setTotalWidth(520f);//72f =1 in=2,54cm
                    tabla.setLockedWidth(true);
                    PdfPCell filas = null;

                   //cabecera de la tabla
    //"SEMESTRE", "CODIGO", "MATERIA", "UC", "SECCION", "HORARIO", "DOCENTE"
                        filas = new PdfPCell(new Paragraph("S",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);

                        filas = new PdfPCell(new Paragraph("CÓDIGO",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);

                        filas = new PdfPCell(new Paragraph("ASIGNATURA",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);

                        filas = new PdfPCell(new Paragraph("UC",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);

                        filas = new PdfPCell(new Paragraph("SECCIÓN",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);

                        filas = new PdfPCell(new Paragraph("C",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);

                        filas = new PdfPCell(new Paragraph("HORARIO",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);

                        filas = new PdfPCell(new Paragraph("DOCENTE",fuente[1]));//codigo de la materia
                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas.setBackgroundColor(new Color(147,247,135));
                        tabla.addCell(filas);
                    //fin cabecera
    boolean materias_x_repitencia=false;

                        if(pdf_mi.size()<=0){System.out.println("Este alumno no tiene materias inscritas");
                            parrafo= new Paragraph("NO TIENEN MATERIAS INSCRITAS EN ESTE PERIODO: \n\n",fuente[2]);
                            parrafo.setAlignment(Element.ALIGN_CENTER);
                            documento.add(parrafo);

                        }else{
                                for(int i=0;i<pdf_mi.size();i=i+9){//llenando con las materias inscritas

                                    //if(!pdf_mi.get(i + 7).equals("P")){//Solo materias normales. nada de repitencias
                                    if(true){//Solo materias normales. todo
                                         this.setUc_inscritas(this.getUc_inscritas()+Integer.valueOf(pdf_mi.get(i+3)));


                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+0),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(filas);

                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+1),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(filas);

                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+2),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        tabla.addCell(filas);

                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+3),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(filas);

                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+4),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(filas);

                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+7),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(filas);


                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+5),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        tabla.addCell(filas);

                                        filas = new PdfPCell(new Paragraph(pdf_mi.get(i+6),fuente[0]));//llenando tabla con las materias recien inscritas
                                        filas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        tabla.addCell(filas);
                                        //fin discrimante materias normales
                                    }else{//hay materias por repitencias
                                           materias_x_repitencia=true;

                                    }


                                }//fin llenando


                    tabla.setSpacingAfter(10f);
                    documento.add(tabla);
        }



                    //LEYENDA DE LA CONSTANCIA DE INSCRIPCION
                    parrafo= new Paragraph("     TOTAL U.C. INSCRITAS:..........."+this.getUc_inscritas()+"\n",fuente[1]);
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);

    //                parrafo= new Paragraph("     LEYENDA:\n"+"        S = SEMESTRE\n"+"        D = DEFINITIVA\n"+"        R = REPARACION\n"+"        P = PERIODO ACADEMICO\n"+"        UC = UNIDADES DE CREDITO\n",fuente[1]);
      //              parrafo.setAlignment(Element.ALIGN_LEFT);
        //            documento.add(parrafo);


                    //espacios en blanco

                   // parrafo= new Paragraph("\n\n\n\n\n");
                   // documento.add(parrafo);

    }//fin verificacion que tenga materias inscrittas en el periodo actual


    //MINI RECORD========================================================================================================================
                                System.out.println("MINI RECORD ACADEMICO: "+orden.size());
      if(orden.size()<=0 && pdf_mi.size()>0){System.out.println(cedula+" - "+estudiante+", Este alumno no tiene materia del record registrada, pero si inscritas");
      //generando espacios automaticos para mantener la distancia
      parrafo = new Paragraph("     NUEVO INGRESO",fuente[1]);
      documento.add(parrafo);
      parrafo = new Paragraph("\n\n\n\n\n");
      documento.add(parrafo);

      }else{
    System.out.println("..............................................CONSTANCIA DE CALIFICACION PERIODO ANTERIOR......................");
               //PAGINA SIGUIENTE . CONSTANCIA DE CALIFICACION


               // System.out.println("MIRANDO: "+orden.get(orden.size()-6)+orden.get(orden.size()-7));
                    parrafo= new Paragraph("CONSTANCIA DE CALIFICACION\nPERIODO: "+orden.get(orden.size()-6)+" \n\n",fuente[2]);
                    parrafo.setAlignment(Element.ALIGN_CENTER);
                    documento.add(parrafo);



                    float[] ancho={0.2f,0.5f,3f,0.2f,0.2f,0.2f,0.8f,0.3f};
                    PdfPTable notas = new PdfPTable(ancho);
                    notas.setHeaderRows(1);//estableciendo cabecera de la tabla
                    PdfPCell celdas = null;

                    //PdfPTable notas = new PdfPTable(6);

                    //cabecera de la tabla
                        celdas = new PdfPCell(new Paragraph("S",fuente[1]));//semestre de la materia
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);

                        celdas = new PdfPCell(new Paragraph("CODIGO",fuente[1]));//codigo de la materia
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);

                        celdas = new PdfPCell(new Paragraph("ASIGNATURA",fuente[1]));//nombre de la materia
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);

                        celdas = new PdfPCell(new Paragraph("UC",fuente[1]));//unidades de credito de la materia
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);

                        celdas = new PdfPCell(new Paragraph("D",fuente[1]));//definitiva de la materia
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);

                        celdas = new PdfPCell(new Paragraph("R",fuente[1]));//nota de la reparacion
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);

                        celdas = new PdfPCell(new Paragraph("OBSERVACIÓN",fuente[1]));//condicion de la materia
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);

                        celdas = new PdfPCell(new Paragraph("PA",fuente[1]));//periodo academico de la materia
                        celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdas.setBackgroundColor(new Color(147,247,135));
                        notas.addCell(celdas);



                    //fin cabecera



                   this.setComp_periodo(orden.get(5));


                    //for(int z=0;z<orden.size();z=z+6){//escribiendo las materias del alumno en el archivo pdf
                    for(int z=0;z<orden.size();z=z+11){//escribiendo las materias del alumno en el archivo pdf

    //                    System.out.println("z/6: "+(z/6));
                        conteo=conteo+1;

                        /*if(conteo>35){//se ingresan datos a la tabla hasta 50 filas, luego se van compiando las otras en las paginas siguientes
                        //   System.out.println("copiando en la pagina siguiente");

                            this.setNumero_pagina(this.getNumero_pagina()+1);//contando cuantas paginas para presentar toda la tabla del record

                            documento.add(notas);//agregando la tabla parcial al documento actual

                            notas.deleteBodyRows();//inicializando la tabla otra vez para agregar nuevos valores
                            notas.setSkipFirstHeader(false);//estableciendo una cabecera por cada pagina nueva
                            this.pie_pagina(escritor, documento,this.getNumero_pagina());
                            documento.newPage();//pasa a la pagina siguiente
                            conteo=0;//se inicializa el conteo nuevamente



                            parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+"\n"
                                          +"MATRÍCULA: "+matricula+" \n\n",fuente[1]);
                            parrafo.setAlignment(Element.ALIGN_LEFT);
                            documento.add(parrafo);

                        }*/





                       if(this.getComp_periodo().equalsIgnoreCase(orden.get(z+5))){


                            //if(orden.get(z+5).equalsIgnoreCase("2-2010")){//discriminante para solo colocar las materias del perido anterior
                            if(orden.get(z+5).equalsIgnoreCase(orden.get(orden.size()-6))){//discriminante para solo colocar las materias del ultimo periodo academico. ultimo registro que contiene el ultimo periodo visto
                                celdas = new PdfPCell(new Paragraph(this.semestre_materia(orden.get(z),orden.get(z+5) ,pensum),fuente[0]));//semestre de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(orden.get(z),fuente[0]));//codigo de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(orden.get(z+1),fuente[0]));//nombre de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(String.valueOf(this.uc_materia(orden.get(z), orden.get(z+5),pensum)),fuente[0]));//unidades de credito de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);


                                //celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),orden.get(z+2)),fuente[0]));//definitiva de la materia
                                celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),this.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[0], orden.get(z+1) ),fuente[0]));//definitiva de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);

                                //celdas = new PdfPCell(new Paragraph(orden.get(z+3),fuente[0]));//nota de la reparacion
                                celdas = new PdfPCell(new Paragraph(this.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[1],fuente[0]));//nota de la reparacion
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);
                               // System.out.println("RASTREO "+orden.get(z+4)+" - "+orden.get(z+3));
                                celdas = new PdfPCell(new Paragraph(this.sin_condicion(orden.get(z+4),orden.get(z+3)),fuente[0]));//condicion de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(orden.get(z+5),fuente[0]));//periodo academico de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);
                           }//fin discriminante de solo escribir las materias del periodo anterior del estudiante


                       }else{//en caso que sean distinto los periodos, copio el espacio en blanco y luego la fila que corresponde

                        //ESPACIO EN BLANCO
                                /*celdas = new PdfPCell(new Paragraph("",fuente[0]));//semestre de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph("",fuente[0]));//codigo de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph("",fuente[0]));//nombre de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph("",fuente[0]));//unidades de credito de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph("",fuente[0]));//definitiva de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph("",fuente[0]));//nota de la reparacion
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph("",fuente[0]));//condicion de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph("",fuente[0]));//periodo academico de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                celdas.setBackgroundColor(new Color(185,190,203));
                                notas.addCell(celdas);*/


                        //GARANTIZANDO COPIAR EL REGISTRO ACTUAL
                               //if(orden.get(z+5).equalsIgnoreCase("2-2010")){//discriminante periodo anterior
                               if(orden.get(z+5).equalsIgnoreCase(orden.get(orden.size()-6))){//discriminante para solo colocar las materias del ultimo periodo academico. ultimo registro que contiene el ultimo periodo visto
                                celdas = new PdfPCell(new Paragraph(this.semestre_materia(orden.get(z), orden.get(z+5),pensum),fuente[0]));//semestre de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);


                                celdas = new PdfPCell(new Paragraph(orden.get(z),fuente[0]));//codigo de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(orden.get(z+1),fuente[0]));//nombre de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(String.valueOf(this.uc_materia(orden.get(z), orden.get(z+5),pensum)),fuente[0]));//unidades de credito de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);

                                //celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),orden.get(z+2)),fuente[0]));//definitiva de la materia
                                 celdas = new PdfPCell(new Paragraph(this.sin_cero(orden.get(z+4),this.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[0], orden.get(z+1) ),fuente[0]));//definitiva de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);

                                //celdas = new PdfPCell(new Paragraph(orden.get(z+3),fuente[0]));//nota de la reparacion
                                celdas = new PdfPCell(new Paragraph(this.nota_presentacion(orden.get(z+2), orden.get(z+3), orden.get(z+6),orden.get(z+4))[1],fuente[0]));//nota de la reparacion
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(this.sin_condicion(orden.get(z+4),orden.get(z+3)),fuente[0]));//condicion de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                notas.addCell(celdas);

                                celdas = new PdfPCell(new Paragraph(orden.get(z+5),fuente[0]));//periodo academico de la materia
                                celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                notas.addCell(celdas);
                           }

                               this.setComp_periodo(orden.get(z+5));


                       }

                    }//fin del recorrido del LinkedList orden




                        notas.spacingBefore();//espacio despues que se coloca la tabla
                        documento.add(notas);
                        //UC APROBADAS, PTOS ACUMULADOS, INDICE ACADEMICO

                        parrafo= new Paragraph("    Total de U.C. Equivalencias: "+this.getUc_equivalencias()+"  "+
                                               "     Total de U.C. Cursadas: "+this.getUc_cursadas()+"  "+
                                               "     Total de Puntos: "+this.getPuntos()+"  "+
                                               "     ÍNDICE ACADÉMICO: "+df.format(this.getIndice_academico())+"\n\n\n\n\n",fuente[1]);
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);


        }
    //limpiando variableas acumulativas
    this.setUc_cursadas(0);
    this.setNumero_pagina(0);
    this.setUc_aprobadas(0);
    this.setUc_inscritas(0);
    this.setPuntos(0);
    this.setUc_equivalencias(0);
    this.getAlmacen().clear();
    orden.clear();//limpiando materias
        

    //FIN MINI RECORD====================================================================================================================

                    //PIE DE PAGINA


                    PdfPTable pie = new PdfPTable(2);
                    PdfPCell fila_pie = null;

                    //estudiante

                    fila_pie = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\nBr. "+estudiante+"\n\nESTUDIANTE",fuente[0]));
                    fila_pie.setHorizontalAlignment(Element.ALIGN_CENTER);
                    fila_pie.setBorder(Rectangle.NO_BORDER);
                    pie.addCell(fila_pie);

                    //jefe de control de estudio o secretaria
                   /* if(nucleo_extension.contains("EXTENSION")) fila_pie = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[2]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[3]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[4]+"\n\nJEFE DE CONTROL DE ESTUDIO",fuente[0]));
                    else fila_pie = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE DE LA DIVISION DE SECRETARIA",fuente[0]));
                    */
                    if(nucleo_extension.contains("EXTENSION")) fila_pie = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[2]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[3]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[4]+"\n\nL.E.A.D UNIDAD DE SECRETARÍA",fuente[0]));
                    else fila_pie = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4]+"\n\nJEFE UNIDAD DE SECRETARÍA",fuente[0]));
                    
                    fila_pie.setHorizontalAlignment(Element.ALIGN_CENTER);
                    fila_pie.setBorder(Rectangle.NO_BORDER);
                    pie.addCell(fila_pie);

                    documento.add(pie);

                    parrafo= new Paragraph("\n");//espacio en blanco
                    documento.add(parrafo);
                    parrafo= new Paragraph("     LEYENDA: S = SEMESTRE, D = DEFINITIVA, R = REPARACION, PA = PERIODO ACADÉMICO, UC = UNIDADES DE CRÉDITO\n"
                                          +"                           C = CONDICIÓN, N = NORMAL, E = EQUIVALENCIA, S = SUFICIENCIA ,P = REPITENCIA, L = PARALELO",fuente[0]);
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);


                    parrafo = new Paragraph("\n\n\n"+this.calendario_2(),fuente[1]);//agregando la fecha de emision
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);


                    this.pie_pagina(escritor, documento, this.getNumero_pagina());//el numero de la pagina en la parte central inferior

                    documento.newPage();//colocando la informacion en una pagina nueva




    //------------------------FIN CONSTANCIA DE INSCRIPCION--------------------alternativo----------------------------------


        //if(materias_x_repitencia){//este modulo se carga solo si hay materias por repitencias
        if(false){//este modulo se carga solo si hay materias por repitencias
                    parrafo= new Paragraph("CENSO SOLICITUD DE MATERIA POR REPITENCIA\nPERIODO: "+pdf_mi.get(8)+"\n\n",fuente[2]);
                    parrafo.setAlignment(Element.ALIGN_CENTER);
                    documento.add(parrafo);


                    //parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+"\n"
                    parrafo= new Paragraph("CÉDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+this.nombre_real_carrera(carrera, "", 2)+"\n"
                                          +"MATRÍCULA: "+matricula+" \n\n",fuente[1]);
                                           //+ "MATRICULA: 1-2007-0424D-18930730\n\n",fuente[1]);
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);


    //System.out.println(materia.getPdf_mi().size());

                    //tabla de materias inscrita dentro del archivo pdf
                    float[] conf_x={0.2f,0.6f,3.8f,0.3f,0.7f,0.2f,1.5f,1.3f};
                    PdfPTable tabla_x = new PdfPTable(conf_x);
                    tabla_x.setTotalWidth(520f);//72f =1 in=2,54cm
                    tabla_x.setLockedWidth(true);
                    PdfPCell filas_x = null;

                   //cabecera de la tabla
    //"SEMESTRE", "CODIGO", "MATERIA", "UC", "SECCION", "HORARIO", "DOCENTE"
                        filas_x = new PdfPCell(new Paragraph("S",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);

                        filas_x = new PdfPCell(new Paragraph("CÓDIGO",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);

                        filas_x = new PdfPCell(new Paragraph("ASIGNATURA",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);

                        filas_x = new PdfPCell(new Paragraph("UC",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);

                        filas_x = new PdfPCell(new Paragraph("SECCIÓN",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);

                        filas_x = new PdfPCell(new Paragraph("C",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);

                        filas_x = new PdfPCell(new Paragraph("HORARIO",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);

                        filas_x = new PdfPCell(new Paragraph("DOCENTE",fuente[1]));//codigo de la materia
                        filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                        filas_x.setBackgroundColor(new Color(147,247,135));
                        tabla_x.addCell(filas_x);
                    //fin cabecera


                        if(pdf_mi.size()<=0){System.out.println("Este alumno no tiene materias inscritas");}else{

                                     for(int i=0;i<pdf_mi.size();i=i+9){//llenando con las materias inscritas

                                            if( pdf_mi.get(i + 7).equalsIgnoreCase("P")                                              
                                                    ){//Solo materias por repitencias
                                                     this.setUc_inscritas(this.getUc_inscritas()+Integer.valueOf(pdf_mi.get(i+3)));


                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+0),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    tabla_x.addCell(filas_x);

                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+1),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    tabla_x.addCell(filas_x);

                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+2),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_LEFT);
                                                    tabla_x.addCell(filas_x);

                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+3),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    tabla_x.addCell(filas_x);

                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+4),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    tabla_x.addCell(filas_x);

                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+7),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    tabla_x.addCell(filas_x);


                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+5),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_LEFT);
                                                    tabla_x.addCell(filas_x);

                                                    filas_x = new PdfPCell(new Paragraph(pdf_mi.get(i+6),fuente[0]));//llenando tabla con las materias recien inscritas
                                                    filas_x.setHorizontalAlignment(Element.ALIGN_LEFT);
                                                    tabla_x.addCell(filas_x);
                                         }//consideracion solo materia por repitencias



                                      }//fin llenando


                    tabla_x.setSpacingAfter(10f);
                    documento.add(tabla_x);
        }



                    //LEYENDA DE LA CONSTANCIA DE INSCRIPCION
                    parrafo= new Paragraph("     TOTAL U.C. SOLICITADAS:..........."+this.getUc_inscritas()+"\n\n",fuente[1]);
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);

    //                parrafo= new Paragraph("     LEYENDA:\n"+"        S = SEMESTRE\n"+"        D = DEFINITIVA\n"+"        R = REPARACION\n"+"        P = PERIODO ACADEMICO\n"+"        UC = UNIDADES DE CREDITO\n",fuente[1]);
      //              parrafo.setAlignment(Element.ALIGN_LEFT);
        //            documento.add(parrafo);


                    //espacios en blanco

                    parrafo= new Paragraph("\n\n\n\n\n");
                    documento.add(parrafo);

                    //PIE DE PAGINA


                    PdfPTable pie_x = new PdfPTable(2);
                    PdfPCell fila_pie_x = null;

                    //estudiante

                    fila_pie_x = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\nBr. "+estudiante+"\n\nESTUDIANTE",fuente[0]));
                    fila_pie_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                    fila_pie_x.setBorder(Rectangle.NO_BORDER);
                    pie_x.addCell(fila_pie_x);

                    //jefe de control de estudio
                    fila_pie_x = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("JEDE DE CONTROL DE ESTUDIO")+"\n\nJEFE DEL DEPARTAMENTO DE SECRETARIA",fuente[0]));
                    fila_pie_x.setHorizontalAlignment(Element.ALIGN_CENTER);
                    fila_pie_x.setBorder(Rectangle.NO_BORDER);
                    pie_x.addCell(fila_pie_x);

                    documento.add(pie_x);

                    parrafo= new Paragraph("\n\n\n\n\n");//espacio en blanco
                    documento.add(parrafo);


                     parrafo= new Paragraph("IMPORTANTE:\n"
                                          + "    LOS LISTADOS DE LAS MATERIAS POR REPITENCIAS APROBADAS, SERAN PUBLICADAS EN SU COORDINACION. SOLO QUEDARAN INSCRITAS\n"
                                           +"    AUTOMATICAMENTE LAS MATERIAS QUE SOLICITO Y ESTE ENTRE LAS APROBADAS, SIEMPRE Y CUANDO ESTAS CUMPLAN CON EL\n"
                                           +"    ARTICULO 8 DEL REGLAMENTO DE TRANSICION.\n\n\n\n\n\n\n\n\n\n",fuente[0]);
                    parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                    documento.add(parrafo);


                    parrafo= new Paragraph("     LEYENDA: S = SEMESTRE, D = DEFINITIVA, R = REPARACION, PA = PERIODO ACADÉMICO, UC = UNIDADES DE CRÉDITO\n"
                                          +"                           C = CONDICIÓN, N = NORMAL, E = EQUIVALENCIA, S = SUFICIENCIA ,P = REPITENCIA, L = PARALELO",fuente[0]);
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);

                   parrafo = new Paragraph("\n\n\n"+this.calendario_2(),fuente[1]);//agregando la fecha de emision
                    parrafo.setAlignment(Element.ALIGN_LEFT);
                    documento.add(parrafo);


                    this.pie_pagina(escritor, documento, this.getNumero_pagina()+1);//el numero de la pagina en la parte central inferior

                    documento.newPage();//colocando la informacion en una pagina nueva

        }

    //_-------------------------CENSO MATERIAS POR REPITENCIA SOLICITUD----------------alternativo-------------





    //----------FIN---CENSO MATERIAS POR REPITENCIA SOLICITUD----alternativo----------------







                        documento.close();

            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
            }



   

    
}//alternativo constancia
//---------------------------------------------------------------------------------------------------------------------------fin alternativo


//--------------------------------------------------------------------------   NUEVO INGRESO EXPERIMENTAL
  
  public void lista_cinu(Connection con, String periodo_academico, String cedula, boolean masivo){
      
      
      encriptacion encriptar = new encriptacion();
      String direccion = ima.guardado_general_directorio("GUARDAR CONSTANCIA CINU");
      LinkedList<String> estudiantes = new LinkedList<>();
      //char c[] = encriptar.conversor(, "SHA-1").toCharArray();
    if(direccion!=null){
        
      
        hilos h = new hilos("CONSTANCIAS CINU", 0, false, new progreso());
        h.start();
        h.setInformacion_1("GENERANDO CONSTANCIA CINU");
        h.setInicio(0);h.setFin(100);
      
      //System.out.println("direccion: "+direccion);
      
                    try {
                        Statement sentencia = null;
                        ResultSet resultado = null;

                        sentencia = con.createStatement();
                        if(masivo)resultado = (ResultSet) sentencia.executeQuery("SELECT * FROM cinu.nuevo_ingreso;");
                        else resultado = (ResultSet) sentencia.executeQuery("SELECT * FROM cinu.nuevo_ingreso where cedula='"+cedula+"';");

                        while(resultado.next()){//recorriendo registros
                                estudiantes.add(resultado.getString("cedula"));//colectando las cedulas 0
                                estudiantes.add(resultado.getString("nombres"));//colectando las cedulas 1
                                estudiantes.add(resultado.getString("apellidos"));//colectando las cedulas 2
                                estudiantes.add(resultado.getString("sede"));//colectando las cedulas 3
                        }//fin recorrido registros


                        sentencia.close();
                        resultado.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                    h.setFin(estudiantes.size()/4);
                if(estudiantes.size()>0){}else ima.mensaje_informacion("ESTA CÉDULA NO EXISTE: "+cedula, "ERROR", "error", "png");          

                for(int i=0; i<estudiantes.size();i=i+4){//recorriendo las cedulas
                    //genereando lo de interes
                    h.setAvance(i/4);
                    h.setInformacion_1("CEDULA: "+estudiantes.get(i+0)+" -- ("+(i/4)+"/"+(estudiantes.size()/4)+")");


                                    char c[] = encriptar.conversor(Math.random()+"-"+estudiantes.get(i+0), "SHA-1").toCharArray();
                                    try {
                                        //this.constancia_estudio_cinu(false, false, true, "d://cinu/"+resultado.getString("sede")+"_cinu_constancia_"+resultado.getString("cedula") +".pdf",
                                        this.constancia_estudio_cinu(false, false, true, direccion+estudiantes.get(i+3)+"_cinu_constancia_"+estudiantes.get(i+0)+".pdf",
                                                                    estudiantes.get(i+0),
                                                                    estudiantes.get(i+1)+", "+estudiantes.get(i+2),
                                                                    "",
                                                                    "",
                                                                    "NÚCLEO MÉRIDA - SEDE MÉRIDA",
                                                                    "",
                                                                    periodo_academico,
                                                                    ""+c[2]+c[4]+c[6]+c[8]+c[10]+c[12]+c[14]+c[16]+c[18]+c[20]);

                                      //  doc.escritor_texto_mejorado(direccion+"reporte_constancia_cinu.txt", "CEDULA; ESTUDIANTE; CODIGO_AUTENTICACION; SEDE; FECHA_EMISION", estudiantes.get(i+0)+";"+estudiantes.get(i+1)+", "+estudiantes.get(i+2)+";"+""+c[2]+c[4]+c[6]+c[8]+c[10]+c[12]+c[14]+c[16]+c[18]+c[20]+";"+estudiantes.get(i+3)+";"+this.calendario_2());//escritor codigo con usuario constancia

                                } catch (DocumentException ex) {
                                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                                }



                }//fin recorrido de cedulas
        
    
                     
        h.setAvance(h.getFin());
    
    }else{
    
        ima.mensaje_informacion("EL PROCESO HA SIDO CANCELADO: ", "CANCELADO!!!", "alto", "png");
    }
       
    
  }
  
  
  
  /**ESTE METODO ES PARA GENERAR MASIVAMENTE LAS CONSTANCIAS DE ESTUDIOS PARA LOS NUEVOS INGRESOS....ES EXPERIMENTAL */
    public void constancia_estudio_cinu(boolean inscripcion, boolean record, boolean constancia, String nombre_archivo,String cedula, String estudiante, String carrera, String turno, String nucleo_extension, String inscrito, String periodo_academico, String serial ) throws DocumentException, IOException{

    if(inscripcion==true || record ==true || constancia==true){//activando todos los procesos internos si unos de estos elementos son verdaderos
                        
            registro_autoridades ra = new registro_autoridades();//creando una instancia
            ra.lista_autoridades(new conexion_base_de_datos().getConexion());//creando lista de autoridades
            
            if(periodo_academico.startsWith("1")){//renombrando el periodo academico
                periodo_academico = "I"+periodo_academico.substring(1);            
            }else{
                periodo_academico = "II"+periodo_academico.substring(1);
            }

                    //------------------------------------orden de  merito

                     // this.tabla_merito(cedula,matricula, this.getPuntos(), this.getPuntos_merito(), this.getUc_cursadas(),  df.format(this.getIndice_academico()),  df.format(this.getIndice_merito()));

                   //--------------------------------------------

        System.out.println("PROCESANDO ESTUDIANTE CINU: "+cedula);


            FileOutputStream archivo = null;
                try {
                    archivo = new FileOutputStream(nombre_archivo);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                }

                    //Document documento = new Document(PageSize.LETTER);
                    Document documento = new Document(PageSize.LETTER,40,40,20,20);//margenes de 20mm = 2cm

                    PdfWriter escritor = PdfWriter.getInstance(documento, archivo);
                    documento.addTitle("REPORTE INSCRIPCIÓN");
                    documento.addSubject("Informacion del desempeño de cada estudiante");
                    documento.addKeywords("Constancia de incripcion, record académico");
                    documento.addCreator("Software Inscripcion UNEFA 2010");
                    documento.addAuthor("Ing. Cusatti Andy");
                    documento.addHeader("Expires", "0");

                    documento.open();

                      //  System.out.println("RUTA DE LA LETRA nnnnyyyyyy................."+getClass().getResource("/letra/arial.ttf").getPath());
                        File origen  = new File(getClass().getResourceAsStream("/letra/arial.ttf").toString()); //registrando nueva letra
                        FontFactory.register(origen.getPath(),"ARIAL");
                       
                        
                        Font[] fuente = new Font[4];//configuracion del tamaño y el tipo de letra
                        /*fuente[0]= FontFactory.getFont("ARIAL",6,Font.NORMAL);
                        fuente[1]= FontFactory.getFont("ARIAL",8,Font.NORMAL);
                        fuente[2]= FontFactory.getFont("ARIAL",14,Font.NORMAL);
                        fuente[3]= FontFactory.getFont("ARIAL",6,Font.BOLD);*/
                        
                        fuente[0]= FontFactory.getFont("ARIAL",8,Font.NORMAL);
                        fuente[1]= FontFactory.getFont("ARIAL",10,Font.NORMAL);
                        fuente[2]= FontFactory.getFont("ARIAL",15,Font.NORMAL);
                        fuente[3]= FontFactory.getFont("ARIAL",8,Font.BOLD);
                        
                        
                        /*Font[] fuente = new Font[4];//configuracion del tamaño y el tipo de letra
                        fuente[0]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.NORMAL);
                        fuente[1]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.NORMAL);
                        fuente[2]= FontFactory.getFont(FontFactory.TIMES_ROMAN,14,Font.NORMAL);
                        fuente[3]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.BOLD);*/
                        
                        
                        Image escudo = Image.getInstance(getClass().getResource("/actualizacion_de_datos/imagenes/escudo3.jpg"));
                        
                        //escudo.scaleAbsolute(50, 60);
                        escudo.scaleAbsolute(70, 80);
                        

                        //Chunk escud = new Chunk(escudo,0,-80);
                        Chunk escud = new Chunk(escudo,30,-80);
                        
                        
                        
                      //  escudo.setAlignment(escudo.ALIGN_LEFT | escudo.TEXTWRAP);
                        Paragraph parrafo = new Paragraph();
        //-----CONSTANCIA DE INSCRIPCION----------------------------------------------

                        //-------------CONSTANCIA DE ESTUDIO--------------------

             if(constancia==true) {//habilitando la constancia de estudio


                        System.out.println("CONSTANCIA DE ESTUDIO");
                        Font[] tamaño = new Font[7];//configuracion del tamaño y el tipo de letra
                        /*tamaño[0]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.NORMAL);
                        tamaño[1]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.NORMAL);
                        tamaño[2]= FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.NORMAL);
                        tamaño[3]= FontFactory.getFont(FontFactory.TIMES_ROMAN,14,Font.NORMAL);*/

                        tamaño[0]= FontFactory.getFont("ARIAL",8,Font.NORMAL);
                        tamaño[1]= FontFactory.getFont("ARIAL",10,Font.NORMAL);
                        tamaño[2]= FontFactory.getFont("ARIAL",12,Font.NORMAL);
                        tamaño[3]= FontFactory.getFont("ARIAL",14,Font.NORMAL);
                        tamaño[4]= FontFactory.getFont("ARIAL",8,Font.BOLD);
                        tamaño[5]= FontFactory.getFont("ARIAL",14,Font.BOLD);
                        tamaño[6]= FontFactory.getFont("ARIAL",6,Font.NORMAL);
                         
                       escudo.scaleAbsolute(70, 90);
                       escud = new Chunk(escudo,0,-90);
                       // escudo.setAlignment(escudo.ALIGN_LEFT | escudo.TEXTWRAP);
                        documento.add(escud);

                         parrafo=new Paragraph("REPÚBLICA BOLIVARIANA DE VENEZUELA\nMINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"+
                                               "UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA\n" +
                                               "DE LA FUERZA ARMADA NACIONAL\n"+
                                               nucleo_extension+"\n\n\n\n\n",tamaño[2]);
                                               //"NÚCLEO ARAGUA-SEDE MARACAY\n\n\n\n\n\n",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);

                        parrafo=new Paragraph("CONSTANCIA DE ESTUDIO\n\n\n",tamaño[5]);
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);
                                    
                        parrafo=new Paragraph("          Quien suscribe, Decano(A) del "+nucleo_extension+" de la Universidad Nacional "
                                             +"Experimental Politécnica de la Fuerza Armada Nacional (UNEFA), hace constar que el (la) ciudadano(a): "
                                             +estudiante+", titular de la cédula de identidad N° "+cedula+", es "
                                             +"estudiante de esta Universidad y actualmente"
                                             +" se encuentra cursando el período lectivo "+periodo_academico+", en el CURSO INTEGRAL DE NIVELACIÓN UNIVERSITARIO.",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);

                        parrafo = new Paragraph("\n\n",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);
                                    
                        //parrafo = new Paragraph("       Constancia que se expide a petición de la parte interesada en "+this.lugar_sede(nucleo_extension)+", a la fecha "+this.calendario()+".",tamaño[2]);
                        parrafo = new Paragraph("       Constancia que se expide a petición de la parte interesada en Mérida, a la fecha "+this.calendario()+".",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);


                        parrafo = new Paragraph("\n\n\n\n\n\n",tamaño[2]);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo);


                        
                        Image fff = Image.getInstance(getClass().getResource("/actualizacion_de_datos/imagenes/f.jpg"));
                        fff.scaleAbsolute(200, 110);
                        Chunk firmando = new Chunk(fff,270,-55);
                        documento.add(firmando);
                        
                        PdfPTable firmas = new PdfPTable(2);
                        PdfPCell contenido = null;


                        contenido = new PdfPCell(new Paragraph("",fuente[0]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);
                        
                        //decano del nucleo
                        contenido = new PdfPCell(new Paragraph("----------------------------------------------------------------\n\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[2]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[3]+" "+ra.autoridad("DECANO DEL NUCLEO").split(":")[4]+"\n\n"+nucleo_extension+"\n\n DECANO",tamaño[4]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);

                        
                        contenido = new PdfPCell(new Paragraph(".\n\n\n\n\n",tamaño[0]));
                        contenido.setHorizontalAlignment(Element.ALIGN_LEFT);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);
                        
                        contenido = new PdfPCell(new Paragraph("",fuente[0]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);
                        
                        contenido = new PdfPCell(new Paragraph("\n"+ra.autoridad("DECANO DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[5]+"/"+ra.autoridad("DIRECTOR(A)").split(":")[5]+"/"+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[5]+"\n\n",tamaño[1]) //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                );
                        contenido.setHorizontalAlignment(Element.ALIGN_LEFT);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);

                        ima.rastreo_documentos_expedidos(cedula, estudiante, serial, "CONSTANCIA DE ESTUDIO CINU", nucleo_extension, this.calendario());
                        //iniciales
                        contenido = new PdfPCell(new Paragraph("\n\n\n\n\n\nserial: "+serial,tamaño[6]));
                        contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                        contenido.setBorder(Rectangle.NO_BORDER);
                        firmas.addCell(contenido);

                        

                        documento.add(firmas);


                        this.pie_pagina_documentado(escritor, documento,new Paragraph("Este documento tiene una vigencia de 3 Meses a partir de la fecha de expedicion.",tamaño[1]));

        //----FIN CONSTANCIA DE ESTUDIO---------
            }//fin habilitacion constancia de estudio


                            documento.close();


                try {
                    archivo.close();
                } catch (IOException ex) {
                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                }



    }//Activacion activacion de los procesos de los metodos


 
}
  
  
  //------------------------------------------------------------------------  NUEVO INGRESO EXPERIMENTAL
  
  
  
  
  
  
  


/**Este metodo permite colocar una cabecera a la hoja del documento sobre la que se esta trabajando,
 para ello se pasa como argumento un Pdfwriter y Document ademas del texto que representa la informacion
 que se desea mostrar en la misma.*/
public void cabecera(PdfWriter escritor, Document documento, String texto){
     Font[] fuente = new Font[3];//configuracion del tamaño y el tipo de letra
                fuente[0]= FontFactory.getFont(FontFactory.TIMES_ROMAN,4,Font.NORMAL);
                fuente[1]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.NORMAL);
                fuente[2]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.NORMAL);
    Paragraph parrafo= new Paragraph();

    parrafo= new Paragraph(texto,fuente[1]);
                    
    
  //  Phrase frase = new Phrase(parrafo);
    
    PdfContentByte cb = escritor.getDirectContent();
    
    
    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, parrafo,((documento.right()-documento.left())/2)+documento.leftMargin(),documento.top()+10,0);
    


}


public void pie_pagina(PdfWriter escritor, Document documento, int pagina){
     Phrase frase = new Phrase("Página: "+pagina);
     PdfContentByte cb = escritor.getDirectContent();

      ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, frase,((documento.right()-documento.left())/2)+documento.leftMargin(),documento.bottom()+10,0);


}

public void pie_pagina_documentado(PdfWriter escritor, Document documento, Paragraph comentario){
     Phrase frase = new Phrase(comentario);
     PdfContentByte cb = escritor.getDirectContent();

      ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, frase,((documento.right()-documento.left())/2)+documento.leftMargin(),documento.bottom()+10,0);

}
public void pie_pagina_dinamico(PdfWriter escritor, Document documento, Paragraph comentario, float x, float y){
     Phrase frase = new Phrase(comentario);
     PdfContentByte cb = escritor.getDirectContent();

      ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, frase,x,y,0);

}




/** Este metodo permite crear un codigo de barra matricial codificado segun la
 * informacion que se pase como parametro y se anexa al archivo pdf como una
 * imagen.

 */
public Image codigo_matricial(String informacion){
        Image imagen = null;


    try {
            BarcodePDF417 pdf417 = new BarcodePDF417();//instanciando el codigo matricial
            String text = "Mi nombre es cusatti Andy";//informacion a codificar
            pdf417.setText(text);//codificando informacion
            imagen = pdf417.getImage();//convirtiendolo en imagen
          //  imagen.scalePercent(50, 50 * pdf417.getYHeight());//escalando la imagen
           // document.add(img);




        } catch (BadElementException ex) {
            Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
return imagen;

}
/**Este metodo comprueba de que no se pase la materia como una nota vacia sea de reparacion
 o definitiva*/




public void primera_vez(String codigo, String nota){
 boolean guardar=true;
 
    if(this.getAlmacen().size()>0){//si hay materias guardadas verificar
    
            for(int x=0; x<this.getAlmacen().size(); x=x+2){//recorriendo las reprobadas
                   if(codigo.equalsIgnoreCase(this.getAlmacen().get(x))){ //revisando todo el registro por coincidencia
                        guardar=false;//es porque ya existe
                        break;
                    }
            }//fin recorrido reprobadas

    }

    if(guardar){//solo se guardan cuando no existen entre las reprobadas
        this.getAlmacen().add(codigo);
        this.getAlmacen().add(nota);
    }

}

/**Metodo que permite elegir la nota de la materia segun lo que se tenga. Se hace la consideracion de las materias que consideran reprobadas si tienen menor a 15 como pasantia, teg y practica educativa */
private int selector_nota(String nota_definitiva,String nota_reparacion, String nombre_materia){
    int nota=0;
    if(nota_reparacion==null || nota_reparacion=="")nota_reparacion="0";//en caso de que se envie un valor de reparacion vacio

    if(nombre_materia.contains("PSI30010") || 
       nombre_materia.contains("TGR30010") ||
       nombre_materia.contains("PSI10008") ||     
       nombre_materia.contains("IRA10303") || 
       nombre_materia.contains("IRC10303") ||
       nombre_materia.contains("IRC30303") ||
       nombre_materia.contains("IRA30303")){//consideraciones excepcionales de materias reprobadas con notas menor a 15
       
        if(Integer.valueOf(nota_definitiva)>=15){//condicion minima de reparacion
            nota=Integer.valueOf(nota_definitiva);//almacenando la nota definitiva
        }else{
            nota=Integer.valueOf(nota_reparacion);//almacenando la nota de reparacion
        }        
        
    }else{//en caso de que no sean las consideraciones especiales se le tomara como una materia normal
        if(Integer.valueOf(nota_definitiva)>=10){
            nota=Integer.valueOf(nota_definitiva);//almacenando la nota definitiva
        }else{
            nota=Integer.valueOf(nota_reparacion);//almacenando la nota de reparacion
        }
    
    }
    
    
return nota;
}

/**Este metodo descuenta puntaje y uc a la materia que se esta analizando siempre y cuando
 la misma alguna vez halla estado reprobada por cualquier causa que lo amerite. La idea
 * es no dar puntaje y uc demas de lo que realmente le corresponde al estudiante.
 Siempre es el puntaje de la materia actual menos la que habia obtenido anteriormente*/
private int comprobador_aprobadas(String codigo, int creditos){
  int puntaje=0;
  int uc=0;
        System.out.println("codigo materia "+codigo+" almacen:"+this.getAlmacen().size()/2);

      for(int a=0; a<this.getAlmacen().size();a=a+2){//recorriendo las materias reprobadas
          //System.out.println("comparacion "+codigo+"  reprobadas: "+this.getAlmacen().get(a+0));
        if( codigo.equalsIgnoreCase(this.getAlmacen().get(a)) ||
            (codigo.contains("PSI30") && this.getAlmacen().get(a+0).contains("TGR30")) || // consideraciones especiales para la pasantia y trabajo especial de grado
            (codigo.contains("TGR30") && this.getAlmacen().get(a+0).contains("PSI30")) 
          ){//si hay concidencia se devuelve el valor que estaba acumulado de la materia  
            puntaje=Integer.valueOf(this.getAlmacen().get(a+1));
            this.setUc_cursadas(this.getUc_cursadas()-creditos);
            
            System.out.println("Descontando PUNTOS= "+puntaje+" UC: "+creditos);


            break;
        }
    }//fin recorrido materia reprobadas


return puntaje;
}

public boolean verificar_si_reparo(int nota){
    boolean reparo=false;

    if(nota>0)reparo=true;else{ reparo=false;//como no fue a reparar se multiplica la nota obtenida de la definitiva y se multiplica con las uc
        
        
    }

return reparo;
}


private void comprobador_reprobadas(String codigo, String nota_reparacion, int uc){
  int aux=0;
  boolean encontrada=true;
  int nota=0;
  

                if(nota_reparacion==null || nota_reparacion=="")nota_reparacion="0";//verifica que no halla una nota vacia
                   
                    nota=Integer.valueOf(nota_reparacion);
                    //System.out.println("NOTA DE REPARACION_condicionada "+codigo+" - "+nota_reparacion);
                    System.out.println("NOTA DE REPARACION "+codigo+" - "+nota_reparacion);
                    
            if(this.getAlmacen().size()<=0){//verificando si el LinkedList esta vacio para al menos ingresar el primer valor

                this.getAlmacen().add(codigo);//almacenando codigo de la materia
                this.getAlmacen().add(String.valueOf(nota*uc));//almacenando el puntaje
                this.setPuntos(this.getPuntos()+(this.selector_nota("0", String.valueOf(nota),codigo)*uc ));//obteniendo los puntos acumulados de la materia y almacenandolo
                this.setUc_cursadas(this.getUc_cursadas()+uc);//sumando unidades de credito

            }else{//en caso de que existan materias acumuladas entonces se procede a otro calculo

                    for(int a=0; a<this.getAlmacen().size();a=a+2){//recorriendo las materias que estan reprobadas para evitar recontar una materia
                        //System.out.println("codigo: "+codigo+" almacen: "+this.getAlmacen().get(a));
                                    
                                if(codigo.equalsIgnoreCase(this.getAlmacen().get(a+0)) ||
                                   (codigo.contains("PSI30") && this.getAlmacen().get(a+0).contains("TGR30")) || // consideraciones especiales para la pasantia y trabajo especial de grado
                                   (codigo.contains("TGR30") && this.getAlmacen().get(a+0).contains("PSI30"))    // ya que el estudiante puede ver las dos pero se debe considerar como si fuese la misma materia
                                  ){//si existe la materia
                                   // System.out.println("RESTADO ESPECIAL - "+codigo+" - "+this.getAlmacen().get(a+0));
                                    encontrada=true;
                                    aux=(nota*uc)-Integer.valueOf(this.getAlmacen().get(a+1));//calculo actual menos la anterior
                                    this.setPuntos(this.getPuntos()+(aux));//se suma los puntos acumulados mas el puntaje de la materia actual menos el puntaje que tuvo alguna otra vez. (materias repetidas)                                    
                                    System.out.println("RESTADO ESPECIAL - "+codigo+" - "+this.getAlmacen().get(a+0)+" resta: ("+nota+"*"+uc+")-"+this.getAlmacen().get(a+1));
                                    System.out.println("aux: "+aux+"  ptos: "+this.getPuntos());
                                    this.getAlmacen().set(a+1,String.valueOf(nota*uc));//cambiando el registro de la materia por el nuevo valor hallado NR x UC
                                    
                                    break;//se rompe el ciclo de recorrido
                                }else{

                                    encontrada=false;//en caso de que se recorra el LinkedList y no se encuentre la materia
                                }



                    }//fin de recorrido materias reprobadas


                            if(encontrada==false){//si la materia no se encuentra despues de haber recorrido todo el LinkedList se almacena como si el LinkedList estuviera vacio
                                System.out.println("Anexando materia: "+codigo);
                                this.getAlmacen().add(codigo);//almacenando codigo de la materia
                                this.getAlmacen().add(String.valueOf(nota*uc));//almacenando el puntaje
                                this.setPuntos(this.getPuntos()+(this.selector_nota("0", String.valueOf(nota), codigo)*uc ));//obteniendo los puntos acumulados de la materia y almacenandolo
                                this.setUc_cursadas(this.getUc_cursadas()+uc);//sumando unidades de credito
                            }



            }

    //System.out.println("reprobada almacenada: "+this.getAlmacen().size()/2);

}



private boolean comprobar_asociacion_merito(String cod_ele_pensum, String cod_ele_record){
    boolean existe = false;
    
    if(this.asociacion_merito.isEmpty()){//si no hay nada en el registro se añade la informacion directamente    
        existe = false;
    }else{//sino se verifica si existe ya asociado
        for(int i=0; i<this.asociacion_merito.size(); i=i+2){//recorriendo las materias asociadas en busca de coincidencia
            if(this.asociacion_merito.get(i).equalsIgnoreCase(cod_ele_pensum) &&//se busca conicidencia del codigo pensum almacenado con el que se quiere ingresar
               this.asociacion_merito.get(i+1).equalsIgnoreCase(cod_ele_record) //y se busca con respecto al codigo del record almacenado. Si existe coincidencia no debe almacenarse porque ya existe     
              ){
                System.out.println("MERITO: electiva ya se encuetra asociada" );
                existe = true;
                break;//rompiendo el ciclo    
            }else{
                existe = false;//no existe 
            }
        
        }//fin recorrido materias asociadas
        
    
    }
    
    
    //DESPUES DE ANALIZAR TODO CON RESPECTO A LA NUEVA MATERIA A INGRESAR POR ASOCIACION CON RESPECTO A LAS ALMACENADAS SE TOMA LO SIGUIENTE
    if(existe){
        System.out.println("MERITO: ESTA ASOCIACION YA EXISTE: "+cod_ele_pensum+" - "+cod_ele_record+" CONTINUANDO ANALISIS..!!!" );
        
    }else{
        System.out.println("MERITO: ASOCIANDO ELECTIVA PENSUM CON LA DEL RECORD: "+cod_ele_pensum+" - "+cod_ele_record );
        this.asociacion_merito.add(cod_ele_pensum);//se asocia la electiva del pensum
        this.asociacion_merito.add(cod_ele_record);//con la electiva del record                                   
    
    }
    
return existe;
}





private Object[] merito_real_record(String elec_pensum, String semestre,String uc_materia, LinkedList<String> orden, LinkedList<String> electivas){
        String tipo_electiva=null;
        boolean reprobo=true;
        boolean coincidencia=false;
        int posicion=0;//posicion del registro donde fue encontrada la materia
    //identificando primero el tipo de electiva
    
        if(elec_pensum.contains("ELETEC"))tipo_electiva="ELECTIVA TECNICA";else tipo_electiva="ELECTIVA NO TECNICA";
        //System.out.println(elec_pensum+" - TIPO: "+tipo_electiva);
    
    //_------------------
    
    for(int i=0; i<orden.size(); i=i+11){//recorriendo materias record
        //System.out.println("Record: "+orden.get(i));
        coincidencia = false;
       if(orden.get(i+10).equalsIgnoreCase(semestre)){//asegurando que solo busque la electiva del pensum en el semestre correcto dentro de las electivas almacenadas en el record
                                                   //SOLO SE REVISA LAS DEL SEMESTRE DEL RECORD SEGUN EL PENSUM          
           
                for(int e=0; e<electivas.size(); e = e + 4){//recorriendo las materias electivas reales
                                    //la idea es contabilizar la materia la primera vez que la vio
          //                   System.out.println("Record: "+orden.get(i)+" - Electiva: "+electivas.get(e));       
                    if(orden.get(i).equalsIgnoreCase(electivas.get(e)) &&//si los codigos de la electiva record es igual al de la lista                                   
                        electivas.get(e+3).equalsIgnoreCase(tipo_electiva) //y sean del mismo tipo     
                    ){ 
                            System.out.println("ELECTIVA ENCONTRADA. ANALISANDO COINCIDENCIA..!!");
                            reprobo = this.comprobar_asociacion_merito(elec_pensum,electivas.get(e));//cuando se asocia la respuesta es false
                            posicion=i;
                            coincidencia = true;
                            break;           

                     }

                }//fin recorrido de las materias electivas reales
                
        }
           
           if(coincidencia)break;//se rompe el ciclo si existe coincidencia o se encuentra la materia que se quiere asociar
          
            
    }//fin recorrido materias
    
    
    System.out.println("REPROBO?: "+reprobo);
    return new Object[]{reprobo,posicion};

}


/**Este metodo permite calcula el orden de merito del estudiante.
 Este orden se calcula con la nota de la materia que vio por primera vez*/
public void calculo_orden_merito(LinkedList <String> orden, LinkedList <String> pensum, LinkedList<String> electivas){
    int puntos=0;
    int credito=0;
    this.setIndice_merito(0.00F);
    this.asociacion_merito.clear();
    System.out.println("CALCULANDO ORDEN DE MERITO");
//Valores guardados en el LinkedList. Posiciones;
//0 = codigo de la materia
  //      1 = nombre de la materia
    //    2 = definitiva de la materia
      //  3 = nota de reparacion
        //4 = condicion de la materia 5 = periodo academico de la materia 6 = definitiva reparacion de la materia 7 = porcentaje de inasistencia de la materia 8 = codigo especialidad de la materia 9 = nota de laboratorio de la materia 10 = semestre de la materia
if(orden.isEmpty()){
                    credito=0;
                    this.setPuntos_merito(0);
                    this.setIndice_merito(0.00F);
                    System.out.println("NO TIENE MATERIA EN EL RECORD, IMPOSIBLE  CALCULAR ORDEN DE MERITO");
                    System.out.println("CALCULO INDICE DE MERITO");
                    System.out.println("uc: "+credito);
                    System.out.println("puntos acumulados: "+this.getPuntos_merito());
                    System.out.println("indice de Merito: "+this.getIndice_merito()+" - "+df.format(this.getIndice_merito()));

}else{
            //for(int p=0; p<pensum.size(); p=p+7){//recorriendo el pensum del estudiante
            for(int p=0; p<pensum.size(); p=p+10){//recorriendo el pensum del estudiante
                    //System.out.println("electiva: "+pensum.get(p).contains("ELETEC")+" - "+pensum.get(p));
                for(int n=0; n<orden.size(); n=n+11 ){//recorriendo el record del estudiante ordenado por periodo academico

                    if(pensum.get(p).contains("ELETEC") || pensum.get(p).contains("ELENOT")){//verificando las materias electivas solamente
                           System.out.println("ANALISIS ELECTIVA: CALCULOS "+pensum.get(p));
                           Object[] informacion = this.merito_real_record(pensum.get(p),pensum.get(p+1) ,pensum.get(p+3), orden, electivas);
                           
                        if(Boolean.valueOf(informacion[0].toString())==false){                        
                            //puntos = puntos + (Integer.valueOf(orden.get(n+2))*Integer.valueOf(pensum.get(p+3)));//almacenando los puntos x materia
                            puntos = puntos + (Integer.valueOf(orden.get(Integer.valueOf(informacion[1].toString())+2))*Integer.valueOf(pensum.get(p+3)));//almacenando los puntos x materia
                            credito = credito + Integer.valueOf(pensum.get(p+3));//almacenando las unidades de credito                           
                        }
                         
                         break;//rompuendo el ciclo y continuando con la otra materia del pensum
                        
                    }else{//evaluando el resto de las materias que no son electivas. Materias normales
                    
                        if(pensum.get(p).equalsIgnoreCase(orden.get(n))){//comparando materia del pensum con el record
                        // System.out.println("Multiplicando: def: "+orden.get(n+2)+" uc:"+pensum.get(p+3));
                            puntos = puntos + (Integer.valueOf(orden.get(n+2))*Integer.valueOf(pensum.get(p+3)));//almacenando los puntos x materia
                            credito = credito + Integer.valueOf(pensum.get(p+3));//almacenando las unidades de credito
                        // System.out.println("hallado "+orden.get(n)+" puntos: "+puntos+" credito: "+credito+" REsultado Multiplicacion: "+(Integer.valueOf(orden.get(n+2))*Integer.valueOf(pensum.get(p+3))));
                            break;
                        }
                    }
                }//fin recorrido record


                System.out.println("CALCULO MERITO:Semestre: "+pensum.get(p+1) +" Materia: "+pensum.get(p)+" - UC: "+credito+" - Puntos: "+puntos);
                
            }//fin recorrido pensum


                  //INDICE DE MERITO

                    this.setPuntos_merito(puntos);
                    this.setIndice_merito(Float.parseFloat(String.valueOf(puntos))/Float.parseFloat(String.valueOf(credito)));

                    //this.setIndice_academico(Float.parseFloat(String.valueOf(this.getPuntos()))/Float.parseFloat(String.valueOf(this.getUc_cursadas())));
                    //System.out.println("indice academico: "+this.getIndice_academico());

                 //RESUMEN

                    System.out.println("CALCULO INDICE DE MERITO");
                    System.out.println("uc: "+credito);
                    System.out.println("puntos acumulados: "+this.getPuntos_merito());
                    System.out.println("indice de Merito: "+this.getIndice_merito()+" - "+df.format(this.getIndice_merito()));
    }
}

/**Metodo que se encarga de realizar los calculos correspondientes al indice academico del estudiante.
 por lo tanto se necesita como parametros las materias del record academico y las materias del pensum*/
public void calculo_alternativo(LinkedList <String> orden, LinkedList <String> pensum, LinkedList<String> electivas){
    this.setUc_inscritas(0);
    this.setUc_cursadas(0);
    int credito=0;
    this.setUc_equivalencias(0);
    int puntaje=0;
    boolean aprobo=false;
    this.getAcu_per().clear();//guarda las UC , puntos acumulados y notas por periodos academicos
    String periodo="";//limpiando la variable para colocar los periodos academicos del alumno uno por uno

if(orden.isEmpty()){

        this.setUc_equivalencias(0);
        this.setUc_cursadas(0);
        this.setPuntos(0);
        this.setIndice_academico(0);
    
     System.out.println("IMPOSIBLE CALCULAR INDICE ACADEMICO, NO HAY MATERIAS EN EL RECORD DEL ESTUDIANTE");
     System.out.println("uc equivalencia: "+this.getUc_equivalencias());
     System.out.println("uc cursadas: "+this.getUc_cursadas());
     System.out.println("puntos acumulados: "+this.getPuntos());
     System.out.println("indice academico: "+this.getIndice_academico()+" - "+df.format(this.getIndice_academico()));
}
else{
    //PRUEBA
            this.cargando_asociacion_electiva(orden, electivas,pensum);//solo para uso de las materias electivas





                    periodo=orden.get(5);//inicializando la variable con el primer periodo del record del estudiante
                //for(int r=0; r<orden.size(); r=r+6){//recorriendo el record academico
                 for(int r=0; r<orden.size(); r=r+11){//recorriendo el record academico
                 //  System.out.println("referencia def:"+orden.get(r+2)+"  rep:"+orden.get(r+3)+"  cond:"+orden.get(r+4) );
                  //  System.out.println("valor "+orden.get(r+3) );
                  //  System.out.println(orden.get(r+3)!=null );
                  //  System.out.println("valor def("+orden.get(r+2)+")" );
                                    System.out.println("\t\t\t CALCULOS - MATERIA:  {"+orden.get(r+0)+" - "+orden.get(r+1)+"}" );
                                                //-----APARTE-----acumulando notas por periodo academico para presentarlo en el record academico---------

                                                if(orden.get(r+5).equalsIgnoreCase(periodo)){//sigue el proceso normal de calculo que se hace mas abajo
                                                }else{//si son distinto guarda lo acumulado anteriormente antes del cambio e incluso se ignora los calculos durante el cambio

                                                      this.setIndice_academico(Float.parseFloat(String.valueOf(this.getPuntos()))/Float.parseFloat(String.valueOf(this.getUc_cursadas())));//calculando el indice academico por periodo

                                                      this.getAcu_per().add(String.valueOf(this.getUc_cursadas()));//guardando uc cursadas  0
                                                      this.getAcu_per().add(String.valueOf(this.getPuntos()));//guardando puntos  1
                                                      this.getAcu_per().add(df.format(this.getIndice_academico()));//guardando guardando indice academico  2
                                                      System.out.println("ACUMULADO PERIODO: "+periodo+" -UC: "+this.getUc_cursadas()+" puntos: "+this.getPuntos()+" indice academico: "+this.getIndice_academico());

                                                      periodo=orden.get(r+5);//guardando nuevo periodo academico
                                                }
                                                //---------------------------------------------------


        //proceso normal de calculo
                    System.out.println("UC acumuladas: "+this.getUc_cursadas());
                    aprobo=materia.evaluacion(r,orden);

                    if(orden.get(r+1) == null || orden.get(r+1).isEmpty() )orden.set(r+1,"codigo no coincide con el pensum");//experimental. solo para evitar las materias vacias que no existen por codigo
                    
                      
                     if(orden.get(r+1).contains("ELECTIVA")){//CALCULO DEL PROMEDIO ESPECIAL PARA MATERIAS ELECTIVAS

                                this.calculos_electivas_asociacion(orden.get(r),periodo, aprobo, orden.get(r+2), orden.get(r+3), orden.get(r+4), pensum);//ANALISIS SOLO PARA MATERIAS ELECTIVAS

                      }else{//consideracion para materias normales

        //-------------------------------------------------------------------------------------------------------------CALCULOS MATERIAS NORMALES
                         if( aprobo || orden.get(r+4).equalsIgnoreCase("MANTENIMIENTO")){//analizando cuando la materia es aprobada o no.
                            //MATERIAS APROBADAS O POR EQUIVALENCIA

                             credito=this.uc_materia(orden.get(r),"ninguno",pensum);//obteniendo las uc de la materia segun el pensum
                             if(orden.get(r+4).equalsIgnoreCase("MANTENIMIENTO")) credito = 0; //esto es para ignorar la uc virtual cuando la materia esta en mantenimiento

                                if(orden.get(r+4).equalsIgnoreCase("EQUIVALENCIA")){//cuando es materia por equivalencia se almacena en otro lado
                                    this.setUc_equivalencias(this.getUc_equivalencias()+credito);
                                }else{//sino es una materia normal
                                        //credito=this.uc_materia(orden.get(r),pensum);//obteniendo las uc de la materia
                                        System.out.println("credito "+credito);
                                        this.setUc_cursadas(this.getUc_cursadas()+credito);//sumando las uc anteriores con la de la nueva materia aprobada
                                        puntaje=this.getPuntos()+(this.selector_nota(orden.get(r+2), orden.get(r+3), orden.get(r))*credito );//puntaje actual
                                        this.setPuntos(puntaje-this.comprobador_aprobadas(orden.get(r),credito));//obteniendo los puntos acumulados de la materia y almacenandolo

                                        System.out.println("paso_PUNTOS: "+this.getPuntos());
                                }

                         }else{
                                //if(orden.get(r + 4).equalsIgnoreCase("REPROBO-25% DE INASISTENCIA") && Integer.valueOf(orden.get(r+2))>=10){//perdio la materia por inasistencia aun teniendo pasada la materia


                               // }else{
                               //MATERIAS REPROBADAS
                                        if(orden.get(r+3)==null || orden.get(r+3)=="" || orden.get(r+3)=="0"){//verifica si fue a reparar o no
                                            System.out.println("NO FUE A REPARAR");

                                            credito=this.uc_materia(orden.get(r),"ninguno",pensum);//obteniendo las uc de la materia
                                            //this.setUc_cursadas(this.getUc_cursadas()+credito);//sumando las uc anteriores con la materia reprobada sin reparar
                                            puntaje=this.getPuntos()+(Integer.valueOf(orden.get(r+2))*credito );//puntaje actual de la materia que se encuentra reprobada. definitiva
                                            //this.setPuntos(puntaje-this.comprobador_aprobadas(orden.get(r),credito));//obteniendo los puntos acumulados de la materia y almacenandolo
                                            this.comprobador_reprobadas(orden.get(r),orden.get(r+2),credito);//obteniendo los puntos acumulados de la materia y almacenandolo

                                            //this.primera_vez(orden.get(r),orden.get(r+2));//experimental....es para guardar las materias que no fueron reparadas y no esta en la lista de los mas buscados
                                            System.out.println("no reparo_PUNTOS: "+this.getPuntos());

                                        }else{//en caso de que halla ido a reparar o que la halla visto por repitencia

                                            credito=this.uc_materia(orden.get(r),"ninguno",pensum);//obteniendo las uc de la materia
                                            this.comprobador_reprobadas(orden.get(r), orden.get(r+3), credito);

                                            System.out.println("reprobo_PUNTOS: "+this.getPuntos());

                                        }

                             //}//verificando por inasistencia


                        }//FIN ANALISIS MATERIAS APROBADAS Y REPROBADAS
        //-----------------------------------------------------------------------------------------------------------FIN CALCULOS MATERIAS NORMALES

                   }//fin distinguidor de  calculo para materias normales

                    System.out.println("\t\t\t FIN - CALCULOS - MATERIA: ----------- {"+orden.get(r+0)+" - "+orden.get(r+1)+"} ------------\n" );


                }//fin recorrido record academico

          System.out.println("------- uc: "+this.getUc_cursadas()+" puntos: "+this.getPuntos()+" - "+this.getUc_equivalencias());



                  //INDICE ACADEMICO

                   this.setIndice_academico(Float.parseFloat(String.valueOf(this.getPuntos()))/Float.parseFloat(String.valueOf(this.getUc_cursadas())));//calculando el indice academico total
                    //System.out.println("indice academico: "+this.getIndice_academico());

                 //RESUMEN

                    System.out.println("uc equivalencia: "+this.getUc_equivalencias());
                    System.out.println("uc cursadas: "+this.getUc_cursadas());
                    System.out.println("puntos acumulados: "+this.getPuntos());
                    System.out.println("indice academico: "+this.getIndice_academico()+" - "+df.format(this.getIndice_academico()));

    }//fin comprobacion que halla registro


}


/**Este metodo se encarga de identificar la materia del record y buscar la materia generica electiva segun sea el periodo 
 para luego devolver la generica que esta asociada a la real.*/
private String intercambiador(String codigo_real, String periodo){
    String aso_real=null;
    String aso_periodo=null;
    String generica="No Existe";//se establece que no existe hasta que halla coincidencia, es para que una materia del record que no este en la asociacion no se le calcule nada
    
//System.out.println("TAMAÑO ASOCIACION: "+materia.getElect_orden().size());
    for(int x=0 ; x<materia.getElect_orden().size(); x=x+5){//recorriendo las asociaciones de las electivas
        aso_real=materia.getElect_orden().get(x+2);
        aso_periodo=materia.getElect_orden().get(x+3);

      //  System.out.println("posicion: "+x+"  Intercambiador: RECORRIDO: "+materia.getElect_orden().get(x)+" "+materia.getElect_orden().get(x+1)+" "+materia.getElect_orden().get(x+2)+" "+materia.getElect_orden().get(x+3));

            if(codigo_real.equalsIgnoreCase(aso_real) && //codigos reales iguales
               periodo.equalsIgnoreCase(aso_periodo)     )//periodos iguales
            {

                generica = materia.getElect_orden().get(x+1);//se intercambia virtualmente la electiva real por la generica para que con esta se hagan las sumas y las restas. pero la materia original no se pierde, solo por razones de pensum se usa la generica, es mas facil para los calculos
                System.out.println("Intercambiador: periodo: "+aso_periodo+" real: "+aso_real+"  ->  generica: "+generica);
                break;
            }


    }//fin recorrido de las materias asociadas

    return generica;

}



/**Este es un calculo complementario que solo se realiza a las electivas. se pasa la materia del record con sus notas
 y se pasa la materia pensum para hacer la aociacion e incluirlo en los calculos. PREPROCESADO*/
private void calculos_electivas_asociacion(String codigo_real,String periodo, boolean aprobo, String definitiva, String reparacion, String condicion, LinkedList<String> pensum){
   //System.out.println("Metodo Calculos_electivas_asociacion");
    System.out.println("CALCULO ESPECIAL ELECTIVAS NOTAS");
    String generica=null;//representa el codigo de la materia generica asociada a la electiva real segun sea el periodo
    int puntaje,credito;

    //buscando la materia bajo analisis en las asociaciones. se sustituira el codigo real por el generico porque es el que aparece en el pensum y es mas facil de procesar. solo para efecto de calculos. es temporal
    generica = this.intercambiador(codigo_real, periodo);

    /*Valores guardados en el LinkedList. Posiciones;
        0 = codigo de la materia
        1 = nombre de la materia
        2 = definitiva de la materia
        3 = nota de reparacion
        4 = condicion de la materia
        5 = periodo academico de la materia
        6 = definitiva reparacion de la materia
        7 = porcentaje de inasistencia de la materia
        8 = codigo especialidad de la materia
        9 = nota de laboratorio de la materia
        10 = semestre de la materia*/

    //proceso normal de calculo
            System.out.println("UC acumuladas: "+this.getUc_cursadas());
//-------------------------------------------------------------------------------------------------------------CALCULOS
                 if( aprobo ){//analizando cuando la materia es aprobada o no.
                    //MATERIAS APROBADAS O POR EQUIVALENCIA
                     credito=this.uc_materia(generica,"ninguno",pensum);//obteniendo las uc de la materia segun el pensum

                        if(condicion.equalsIgnoreCase("EQUIVALENCIA")){//cuando es materia por equivalencia se almacena en otro lado
                            this.setUc_equivalencias(this.getUc_equivalencias()+credito);
                        }else{//sino es una materia normal
                                //credito=this.uc_materia(orden.get(r),pensum);//obteniendo las uc de la materia
                                System.out.println("credito "+credito);
                                this.setUc_cursadas(this.getUc_cursadas()+credito);//sumando las uc anteriores con la de la nueva materia aprobada
                                puntaje=this.getPuntos()+(this.selector_nota(definitiva, reparacion, generica)*credito );//puntaje actual
                                this.setPuntos(puntaje-this.comprobador_aprobadas(generica,credito));//obteniendo los puntos acumulados de la materia y almacenandolo

                                System.out.println("paso_PUNTOS: "+this.getPuntos());
                        }

               }else{
                        //if(orden.get(r + 4).equalsIgnoreCase("REPROBO-25% DE INASISTENCIA") && Integer.valueOf(orden.get(r+2))>=10){//perdio la materia por inasistencia aun teniendo pasada la materia


                       // }else{
                       //MATERIAS REPROBADAS
                                if(reparacion==null || reparacion=="" || reparacion=="0"){//verifica si fue a reparar o no
                                    System.out.println("NO FUE A REPARAR");

                                    credito=this.uc_materia(generica,"ninguno",pensum);//obteniendo las uc de la materia
                                    puntaje=this.getPuntos()+(Integer.valueOf(definitiva)*credito );//puntaje actual de la materia que se encuentra reprobada. definitiva
                                    this.comprobador_reprobadas(generica,definitiva,credito);//obteniendo los puntos acumulados de la materia y almacenandolo
                                    System.out.println("no reparo_PUNTOS: "+this.getPuntos());

                                }else{//en caso de que halla ido a reparar o que la halla visto por repitencia

                                    credito=this.uc_materia(generica,"ninguno",pensum);//obteniendo las uc de la materia
                                    this.comprobador_reprobadas(generica, reparacion, credito);
                                    System.out.println("reprobo_PUNTOS: "+this.getPuntos());

                                }

                     //}//verificando por inasistencia


               }//FIN ANALISIS MATERIAS APROBADAS Y REPROBADAS
//-----------------------------------------------------------------------------------------------------------FIN CALCULOS



}



/**Este metodo se enncarga de realizar los analisis a pensum vs record vs electivas reales
 para generar la lista de las materias asociadas entre electiva generica, real y periodo ademas
 * de saber si esta aprobada o no
 */
public void cargando_asociacion_electiva(LinkedList <String> orden, LinkedList<String> electivas_reales, LinkedList <String> pensum){//materias que no posean prelaciones
 System.out.println("[Metodo \"cargando_asociacion_electiva\"]");
    boolean confirmacion=true;
    int referencia=0;
    int maximo=0;
    boolean aprobado=true;



//    materias materia = new materias();//cada vez que se use este metodo se reinician todas las variables
    materia.electivas_x_semestre(pensum);//extrayendo la cantidad de electivs existente por semestre y tipo del pensum

    //----CONSIDERACION DE LAS ELECTIVAS TANTO TECNICAS COMO NO TECNICAS
    materia.getEle_cur().clear();//limpiando el LinkedList de electivas cursadas
    materia.getElect_orden().clear();//limpiando LinkedList donde se guarda las electivas vistas ya ordenadas por periodo aprobadas y reprobadas
    materia.getEle_repro().clear();
    
  //  this.electivas_bd(new conexion_base_de_datos().getConexion(), carrera);//cargando la lista de las electivas

    //--------------------------------------------------------------------ELECTIVAS

            //SOlo tomando del pensums las materias no preladas
                            //for(int d=0; d<pensum.size(); d=d+7){//recorriendo el pensum de la carrera
                            for(int d=0; d<pensum.size(); d=d+10){//recorriendo el pensum de la carrera
                     //  System.out.println("TAMAÑO PENSUM: "+this.getPensum().size()/7+" DEPURANDO "+this.getPensum().get(d+2)+" semestre: "+this.getPensum().get(d+1)+" C:"+this.getPensum().get(d));

                                    if(pensum.get(d+4).toString().length()>=8){//verificando que tenga prelaciones. se ignoran
                                    }else{//materias no preladas. se consideran
                                            materia.getNo_preladas().add(pensum.get(d+0));//codigo materia
                                            materia.getNo_preladas().add(pensum.get(d+1));//semestre materia
                                            materia.getNo_preladas().add(pensum.get(d+2));//nombre de la materia
                                    }

                            }//fin recorrido


            //FIN SEPARACION MATERIAS NO PRELADAS DEL PENSUM



    System.out.println("\n\n==========================================MATERIAS SIN PRELACIONES (ELECTIVAS)=======================================================");

    for(int u=0; u<materia.getNo_preladas().size(); u=u+3){//buscando el semestre mas grande de la carrera
        if(Integer.parseInt(materia.getNo_preladas().get(u+1))>maximo) {
            maximo=Integer.parseInt(materia.getNo_preladas().get(u+1)) ;
        }
    }//fin recorrido para semestre mas grande

        System.out.println("Maximo Semestre De Las No Preladas: "+maximo);


    for(int semestres=1; semestres<=maximo; semestres++){//recorriendo por semestre
        materia.setComp_semestre(semestres);//inicializando semestre
        System.out.println("\n\t[ANALIZANDO SEMESTRE:] -> "+semestres+"\n");



        for(int g=0; g<materia.getNo_preladas().size(); g=g+3){//recorriendo todas las materias que no tienen prelacion del pensum

                

                        if(materia.getComp_semestre()==Integer.valueOf(materia.getNo_preladas().get(g+1))){//comparando que lo semestres sean iguales
                             //   System.out.println("    MATERIA: "+materia.getNo_preladas().get(g+0)+" SEMESTRE= "+materia.getNo_preladas().get(g+1)+"--------------------------");

                                if(materia.getNo_preladas().get(g+2).startsWith("ELECTIVA")){//analisando solo las materias electivas, tanto tecnicas como no tecnicas
                                    //CONSIDERACION MATERIAS ELECTIVAS
                                    String salida=materia.activador_plural_singular(materia.getNo_preladas().get(g+1), materia.getNo_preladas().get(g+2));
                                    if(salida.equalsIgnoreCase("singular")){
                                        System.out.println("Activando metodo SINGULAR electivas");
                                        materia.materias_electivas_singular(materia.getNo_preladas().get(g), materia.getNo_preladas().get(g+2),Integer.valueOf(materia.getNo_preladas().get(g+1)) ,orden , electivas_reales);
                                       // System.out.println("SEGUIMIENTO ASOCIACION singular "+materia.getElect_orden().size());
                                    }else{
                                        System.out.println("Activando metodo PLURAL electivas");
                                        materia.materias_electivas_plural(materia.getNo_preladas().get(g), materia.getNo_preladas().get(g+2), salida,Integer.valueOf(materia.getNo_preladas().get(g+1)) , orden, electivas_reales );
                                      //  System.out.println("SEGUIMIENTO ASOCIACION plural "+materia.getElect_orden().size());
                                    }


                                       // this.materias_electivas(this.getNo_preladas().get(g), this.getNo_preladas().get(g+2),Integer.valueOf(this.getNo_preladas().get(g+1)) , this.getOrden(), this.getElectivas());

                                }else{//materias normales no electivas. analisis normal


                                    }//fin comprobacion si la materia es electiva o no. si la materia es electiva se hace consideraciones especiales

                        }else{}//fin comparacion coincidencia en cuanto a semestre

        }//fin del recorrido de las materias sin prelacion del pensum


                    System.out.println("semestre: "+semestres+" - referencia: "+referencia);
/*OJO la deshabilito para que todas las sin prelaciones aparezcan
             if(aprobado==false & semestres>referencia){//verificando que si alguna materia de ese semestre es reprobada solo se le pueda ofertar la del semestre actual y el siguiente de donde fue reprobada la materia
                System.out.println("LAS MATERIAS DEL SEMESTRE: "+(semestres+1)+" NO SE PODRAN HABILITAR");
                break; }else{}
OJO*/

    }//fin recorrido por semestre


System.out.println("\n |||||||< FINALIZADA LA REVISION DE LAS MATERIAS ELECTIVAS >||||||||");

for(int ecu=0; ecu<materia.getEle_cur().size(); ecu=ecu+2){
    System.out.println("Electivas cursadas: "+materia.getEle_cur().get(ecu)+" asociada: "+materia.getEle_cur().get(ecu+1));
}
//System.out.println("ASOCIACION COMPLETADA: registros: "+materia.getElect_orden().size());
for(int ert=0; ert<materia.getElect_orden().size(); ert=ert+5){
    System.out.println("Semestre: "+materia.getElect_orden().get(ert)
                      +"\tGENERICA: "+materia.getElect_orden().get(ert+1)
                      +"\tREAL: "+materia.getElect_orden().get(ert+2)
                      +"\tPeriodo: "+materia.getElect_orden().get(ert+3)
                      +"\tpaso?: "+materia.getElect_orden().get(ert+4)
            );

}

//this.asociaciones_orden(this.getElect_orden(), this.getAsociacion_ordenada());//materias asociada finalmente ordenadas por periodos





}//fin metodo esquema_normal














public void calculosx(LinkedList <String> orden, LinkedList<String> pensum){
 int credito=0;
 this.setUc_aprobadas(0);
 this.setUc_inscritas(0);//incializando variables

    //UNIDADES DE CREDITO CURSADAS
for(int z=0;z<orden.size();z=z+6){//recorriendo el record del estudiante
           if( ((Integer.valueOf(orden.get(z+2))>=10 || //que halla aprobado la materia
           (orden.get(z+3)!=null && Integer.valueOf(orden.get(z+3))>=10)) && // o que si reparo que la halla aprobado
            orden.get(z+4)!="POR INASISTENCIA" && //y que no halla perdido la materia por inasistencia
            orden.get(z+4)!="ERROR") //|| //y que la condicion de la materia este dentro de las pautadas
           // orden.get(z+4).equalsIgnoreCase("APROBADO") || //o que la condicion de la materia este en aprobado
           // orden.get(z+4).equalsIgnoreCase("EQUIVALENCIA") //o que la tenga aprobada por equivalencia...no se toma la nota
          ){

            credito=this.uc_materia(orden.get(z),"ninguno",pensum);
            this.setUc_aprobadas(this.getUc_aprobadas()+credito);//se suman las unidades de credito aprobadas
            //System.out.println("uc aprobadas: "+this.getUc_aprobadas());

            //PUNTOS ACUMULADOS
            this.setPuntos(this.getPuntos()+(this.puntos_acumulados(orden.get(z+2),orden.get(z+3))*credito));//obteniendo los puntos acumulados
              //System.out.println("puntos acumulados: "+this.getPuntos());
            

       }else{}

    }//fin recorrido record del estudiante


          //INDICE ACADEMICO
          
           this.setIndice_academico(Float.parseFloat(String.valueOf(this.getPuntos()))/Float.parseFloat(String.valueOf(this.getUc_aprobadas())));
            //System.out.println("indice academico: "+this.getIndice_academico());

         //RESUMEN
           

            System.out.println("uc cursadas: "+this.getUc_aprobadas());
            System.out.println("puntos acumulados: "+this.getPuntos());
            System.out.println("indice academico: "+this.getIndice_academico()+" - "+df.format(this.getIndice_academico()));

}

public int uc_materia(String codigo_materia,String periodo ,LinkedList<String> pensum){
 int uc=0;
 String auxiliar=null;

   auxiliar= this.intercambiador(codigo_materia,periodo);//solo para las electivas. se intercambia virtualmente el codigo real por la generica del pensum para que luego se usado en la busqueda de las uc

   // System.out.println("ORIGINAL: "+codigo_materia+" INTERCAMBIADOR: "+auxiliar);
    
   if(auxiliar.equalsIgnoreCase("No Existe") || periodo.equalsIgnoreCase("ninguno")){//simplemente se ignora el efecto del intercambiador
   }else{
    codigo_materia=auxiliar;//codigo generico de la asociacion pero que puede ser encontrado en el pensum normal
   }

        //for(int i=0; i<pensum.size(); i=i+7){//recorriendo el pensum    
        for(int i=0; i<pensum.size(); i=i+10){//recorriendo el pensum

            if(codigo_materia.equalsIgnoreCase(pensum.get(i+0))){//si se halla coincidencia del codigo de la materia buscada con la del pensum
                uc=Integer.valueOf(pensum.get(i+3));//se guarda la uc de la materia
            //    System.out.println("codigo: "+codigo_materia+", materia: "+pensum.get(i+2)+" uc: "+uc);
                break;
            }

        }//fin recorrido pensum
return uc;
}

/**Metodo que permite obtener los puntos de la materia con nota definitiva o de reparacion  */
public int puntos_acumulados(String nota_definitiva,String nota_reparacion){
    int total=0;

    if(Integer.valueOf(nota_definitiva)>=10) total=Integer.valueOf(nota_definitiva);
    if(nota_reparacion!=null && Integer.valueOf(nota_reparacion)>=10) total=Integer.valueOf(nota_reparacion);

return total;
}




public int confirmacion(String situacion){
Object[] opciones={"SI, esta bien!!!","NO, luego!"};

  int r= JOptionPane.showOptionDialog(new JFrame(),
                                      "REALMENTE DESEA GENERAR EL ARCHIVO PDF CON "+situacion+"\n",
                                      "CONFIRMACION",
                                      JOptionPane.YES_NO_OPTION,
                                      JOptionPane.QUESTION_MESSAGE,
                                      ima.imagenes("a_pdf", "png"),
                                      opciones,
                                      opciones[0]);

System.out.println("confirmacion: "+r);
return r;

}












//MODULOS TEMPORALES OPSU

public String opsu_sede(String nucleo){
    String codigo="";
  //  System.out.println("sede: "+nucleo);
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - SEDE MARACAY")) codigo="05";
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - EXTENSION CAGUA")) codigo="04";
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - EXTENSION COLONIA TOVAR")) codigo="34";
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - EXTENSION SAN CASIMIRO")) codigo="06";
    
    if(nucleo.equalsIgnoreCase("NUCLEO MERIDA - SEDE MERIDA")) codigo="31";
    if(nucleo.equalsIgnoreCase("NUCLEO MERIDA - EXTENSION TOVAR")) codigo="41";
    
return codigo;
}

public String lugar_sede(String nucleo){
    String region=null;
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - SEDE MARACAY")) region="Maracay";
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - EXTENSION CAGUA")) region="Cagua";
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - EXTENSION COLONIA TOVAR")) region="Colonia Tovar";
    if(nucleo.equalsIgnoreCase("NUCLEO ARAGUA - EXTENSION SAN CASIMIRO")) region="San Casimiro";
    
    if(nucleo.equalsIgnoreCase("NUCLEO MERIDA - SEDE MERIDA")) region="Merida";
    if(nucleo.equalsIgnoreCase("NÚCLEO MÉRIDA - SEDE MÉRIDA")) region="Mérida";
    if(nucleo.equalsIgnoreCase("NUCLEO MERIDA - EXTENSION TOVAR")) region="Tovar";
    if(nucleo.equalsIgnoreCase("NUCLEO MÉRIDA - EXTENSIÓN TOVAR")) region="Tovar";
return region;
}

public String opsu_carrera(String especialidad){
 String curso="";

            
            
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) curso="06";
            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) curso="03";
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL")) curso="05";
            
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) curso="09";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) curso="10";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) curso="11";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) curso="12";
            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA")) curso="30";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE")) curso="08";
            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO")) curso="27";
            
            
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) curso="13";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) curso="16";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) curso="18";            
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) curso="22";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) curso="23";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) curso="26";            
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) curso="24";
            if(especialidad.equalsIgnoreCase("INGENIERIA AGROINDUSTRIAL")) curso="19";
            
return curso;

}

public String opsu_turno(String turno){
    String t=null;
       if(turno.equalsIgnoreCase("DIURNO")) t="D";
       if(turno.equalsIgnoreCase("NOCTURNO")) t="N";

    return t;
}




public String opsu_matricula(String periodo, String sede, String carrera, String turno, String cedula){
    String completo=null;

        completo=periodo+"-"+sede+carrera+turno+"-"+cedula;


return completo;
}



public String [] independencia_federacion(){
    //20142648
    //independencia 1810, federacion 1859
    Calendar c = Calendar.getInstance();
    
    int independencia = 1810;
    int federacion = 1859;
    int mes = (c.get(Calendar.MONTH)+1);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    
    //resta completa sin considera mes dia
    independencia = c.get(Calendar.YEAR) - independencia;
    federacion = c.get(Calendar.YEAR) - federacion;
    
    if(mes>4){//si el mes actual es mayor al de abril
        //los calculos anteriores se mantienen        
    }else{//sino que sea igual o menor
        if(mes<4){//si es menor en mes se rectifica la restas anteriores ya que aun no ha avanzado los años hasta el actual
                independencia = independencia + 1;
                federacion = federacion + 1;
        }else{//significa que esta en el mismo mes, por lo tanto se analiza la fecha
            if(dia>=19){//si es igual o mayo a la fecha
                //se mantiene el calculo            
            }else{//sino la fecha no se ha alcanzado por lo que se rectifica la resta
                independencia = independencia + 1;
                federacion = federacion + 1;
            }
        }
    
    }
    
return new String[]{String.valueOf(independencia),String.valueOf(federacion)};
}


public String calendario(){
    String fecha=null;

        Calendar c = Calendar.getInstance();

  //  System.out.println("calendario: "+c.get(Calendar.DAY_OF_MONTH));
  //  System.out.println("calendario: "+(c.get(Calendar.MONTH)+1));
  //  System.out.println("calendario: "+c.get(Calendar.YEAR));

fecha=c.get(Calendar.DAY_OF_MONTH)+" de "+this.meses(c.get(Calendar.MONTH)+1)+" de "+c.get(Calendar.YEAR);

//System.out.println("fecha: "+fecha);

return fecha;
}

public String calendario_2(){
    String fecha=null;
    String hora=null;
    String meridiano = null;
        Calendar c = Calendar.getInstance();
        
        DecimalFormat df = new DecimalFormat("00");

  //  System.out.println("calendario: "+c.get(Calendar.DAY_OF_MONTH));
  //  System.out.println("calendario: "+(c.get(Calendar.MONTH)+1));
  //  System.out.println("calendario: "+c.get(Calendar.YEAR));
if(c.get(Calendar.AM_PM)>0)meridiano="PM";else meridiano="AM";


    fecha = df.format(c.get(Calendar.DAY_OF_MONTH))+"/"+df.format(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
    hora = df.format(c.get(Calendar.HOUR))+":"+df.format(c.get(Calendar.MINUTE))+" "+meridiano;
//System.out.println("fecha: "+fecha);

return fecha+" -- "+hora;

}

public String calendario_3(){
     String fecha=null;

    Calendar c = Calendar.getInstance();

  //  System.out.println("calendario: "+c.get(Calendar.DAY_OF_MONTH));
  //  System.out.println("calendario: "+(c.get(Calendar.MONTH)+1));
  //  System.out.println("calendario: "+c.get(Calendar.YEAR));

    fecha=this.dias_escritos(c.get(Calendar.DAY_OF_MONTH))+" dias del mes de "+this.meses(c.get(Calendar.MONTH)+1)+" de "+this.años_escritos(c.get(Calendar.YEAR));

//System.out.println("fecha: "+fecha);

return fecha;

}


public String meses(int m){
    String mes=null;

    switch(m){
        case 1:
          mes="Enero";
        break;
        case 2:
          mes="Febrero";
        break;
        case 3:
          mes="Marzo";
        break;
        case 4:
          mes="Abril";
        break;
        case 5:
          mes="Mayo";
        break;
        case 6:
          mes="Junio";
        break;
        case 7:
          mes="Julio";
        break;
        case 8:
          mes="Agosto";
        break;
        case 9:
          mes="Septiembre";
        break;
        case 10:
          mes="Octubre";
        break;
        case 11:
          mes="Noviembre";
        break;
        case 12:
          mes="Diciembre";
        break;

    default:
        mes="NINGUNO";

    }

return mes;
}


public String dias_escritos(int dia){
    String d=null;
    
 switch(dia)  {
     
    case 1: d="un"; break;
    case 2: d="dos"; break;
    case 3: d="tres"; break;
    case 4: d="cuatro"; break;
    case 5: d="cinco"; break;
    case 6: d="seis"; break;
    case 7: d="siete"; break;
    case 8: d="ocho"; break;
    case 9: d="nueve"; break;
    case 10: d="diez"; break;
    case 11: d="once"; break;
    case 12: d="doce"; break;
    case 13: d="trece"; break;
    case 14: d="catorce"; break;
    case 15: d="quince"; break;
    case 16: d="dieciseis"; break;
    case 17: d="diecisiete"; break;
    case 18: d="dieciocho"; break;
    case 19: d="diecinueve"; break;
    case 20: d="veinte"; break;
    case 21: d="veintiun"; break;
    case 22: d="veintidos"; break;
    case 23: d="veintitres"; break;
    case 24: d="veinticuatro"; break;
    case 25: d="veinticinco"; break;
    case 26: d="veintiseis"; break;
    case 27: d="veintisiete"; break;
    case 28: d="veintiocho"; break;
    case 29: d="veintinueve"; break;
    case 30: d="treinta"; break;
    case 31: d="treinta y uno"; break;
    default: d = "NE";   break;
     
 } 
    
return d;

}


public String años_escritos(int años){
    String d=null;
    String unidades, decenas, centenas, miles;
    String y = String.valueOf(años);
    
    
 switch(Integer.valueOf(String.valueOf(años).substring(2, 4)))  {     
    case 1: unidades="uno"; break;
    case 2: unidades="dos"; break;
    case 3: unidades="tres"; break;
    case 4: unidades="cuatro"; break;
    case 5: unidades="cinco"; break;
    case 6: unidades="seis"; break;
    case 7: unidades="siete"; break;
    case 8: unidades="ocho"; break;
    case 9: unidades="nueve"; break;   
    case 10: unidades="diez"; break;
    case 11: unidades="once"; break;
    case 12: unidades="doce"; break;
    case 13: unidades="trece"; break;
    case 14: unidades="catorce"; break;
    case 15: unidades="quince"; break;        
    case 16: unidades="dieciseis"; break;
    case 17: unidades="diecisiete"; break;
    case 18: unidades="dieciocho"; break;
    case 19: unidades="diecinueve"; break;   
    default: unidades = "NE";   break;     
 }
 //2013
 switch(Integer.valueOf(String.valueOf(años).substring(2, 3)))  { 
    case 1: decenas=""; break; 
    case 2: decenas="veinti"; break;
    case 3: decenas="treinti"; break;
    case 4: decenas="cuarenta y"; break;
    case 5: decenas="cincuenta y"; break;
    case 6: decenas="sesenta y"; break;
    case 7: decenas="setenta y"; break;
    case 8: decenas="ochenta y"; break;
    case 9: decenas="noventa y"; break;    
    default: decenas = "NE";   break;     
 }
 
 switch(Integer.valueOf(String.valueOf(años).substring(1, 2)))  { 
    case 0: centenas=""; break;
    case 1: centenas="ciento"; break;
    case 2: centenas="doscientos"; break;
    case 3: centenas="trescientos"; break;
    case 4: centenas="cuatrocientos"; break;
    case 5: centenas="quinientos"; break;
    case 6: centenas="seiscientos"; break;
    case 7: centenas="setecientos"; break;
    case 8: centenas="ochocientos"; break;
    case 9: centenas="novecientos"; break;    
    default: centenas = "NE";   break;     
 }
 
 
 
 
 
 d = "dos mil "+centenas+" "+decenas+" "+unidades;
    
return d;


}



public void todos_estudiantes(Connection con, String cedula){
        this.setPeriodo_ingreso("");
        this.setMes_ingreso("");
        this.setAño_ingreso("");
        String aux=null;
        
        Statement sentencia = null;
        ResultSet resultado = null;

        try {

            sentencia = con.createStatement();
          //  resultado = (ResultSet) sentencia.executeQuery("SELECT * FROM control_de_estudio.estudiantes WHERE cedula='"+cedula+"';");
            resultado = (ResultSet) sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='"+cedula+"';");//experimental
            
            while (resultado.next()) {               
               //experimental 
                aux = resultado.getString("f_inscripcion");
                this.setPeriodo_ingreso(aux.replace("/", "-"));
                this.setMes_ingreso(this.meses( Integer.valueOf(aux.substring(0,1))) );
                this.setAño_ingreso(aux.substring(2, 6));
            }

          System.out.println(this.getPeriodo_ingreso()+" "+this.getMes_ingreso()+" "+this.getAño_ingreso());


        } catch (SQLException ex) {
            Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

}




public void notas_certificadas(boolean portada, boolean notas_certificadas, String nombre_archivo,String cedula, String estudiante, String carrera, String turno, String nucleo_extension, String inscrito  , LinkedList<String> pdf_mi, LinkedList<String> orden, LinkedList<String> pensum, String jefe_ce, String decano_director, int ajuste) throws DocumentException, BadElementException, MalformedURLException, IOException{

        
            int conteo=0;
            int conteo_2=1;
            String matricula=null;
            this.setNumero_pagina(1);
            int hojas_totales=0;
            
                    matricula=this.opsu_matricula(inscrito,this.opsu_sede(nucleo_extension), this.opsu_carrera(carrera), this.opsu_turno(turno) , cedula);
                    registro_autoridades ra = new registro_autoridades();
                    ra.lista_autoridades(new conexion_base_de_datos().getConexion());
                    //------------------------------------orden de  merito

                     //--------------------------------------------

                   //--------------------------------------------

        System.out.println("PROCESANDO ESTUDIANTE NOTAS CERTIFICADAS: "+cedula);


            FileOutputStream archivo = null;
                try {
                    archivo = new FileOutputStream(nombre_archivo);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                }

                    Document documento = new Document(PageSize.LETTER);
                  
                  // Document documento = new Document(PageSize.LETTER,5f,5f,10f,10f);

                    PdfWriter escritor = PdfWriter.getInstance(documento, archivo);
                    documento.addTitle("NOTAS CERTIFICADAS");
                    documento.addSubject("Informacion de las Notas Certificadas");
                    documento.addKeywords("certificacion, record");
                    documento.addCreator("Software Inscripcion UNEFA 2010");
                    documento.addAuthor("Ing. Cusatti Andy");
                    documento.addHeader("Expires", "0");

                    documento.open();

                        File origen  = new File(getClass().getResourceAsStream("/letra/arial.ttf").toString());
                        FontFactory.register(origen.getAbsolutePath(),"ARIAL");
                       
                        
                        Font[] fuente = new Font[11];//configuracion del tamaño y el tipo de letra
                        fuente[0]= FontFactory.getFont("ARIAL",6,Font.NORMAL);
                        fuente[1]= FontFactory.getFont("ARIAL",8,Font.NORMAL);
                        fuente[2]= FontFactory.getFont("ARIAL",14,Font.NORMAL);
                        fuente[3]= FontFactory.getFont("ARIAL",10,Font.BOLD);
                        fuente[4]= FontFactory.getFont("ARIAL",12,Font.BOLD);
                        fuente[5]= FontFactory.getFont("ARIAL",10,Font.BOLD);
                        fuente[6]= FontFactory.getFont("ARIAL",8,Font.ITALIC);
                        fuente[7]= FontFactory.getFont("ARIAL",10,Font.NORMAL);
                        fuente[8] =FontFactory.getFont("ARIAL",9,Font.BOLD);
                        fuente[9]= FontFactory.getFont("ARIAL",14,Font.BOLD);
                        fuente[10]= FontFactory.getFont("ARIAL",12,Font.NORMAL);
                    
        /*
                        Font[] fuente = new Font[11];//configuracion del tamaño y el tipo de letra
                        fuente[0]= FontFactory.getFont(FontFactory.TIMES_ROMAN,6,Font.NORMAL);
                        fuente[1]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.NORMAL);
                        fuente[2]= FontFactory.getFont(FontFactory.TIMES_ROMAN,14,Font.NORMAL);
                        fuente[3]= FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD);
                        fuente[4]= FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD);
                        fuente[5]= FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD);
                        fuente[6]= FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.ITALIC);
                        fuente[7]= FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.NORMAL);
                        fuente[8]= FontFactory.getFont(FontFactory.TIMES_ROMAN,9,Font.BOLD);
                        fuente[9]= FontFactory.getFont(FontFactory.TIMES_ROMAN,14,Font.BOLD);
                        fuente[10]= FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.NORMAL);
        */
                        Image escudo = Image.getInstance(getClass().getResource("/actualizacion_de_datos/imagenes/escudo3.jpg"));
                        escudo.scaleAbsolute(50, 60);

                        Chunk escud = new Chunk(escudo,0,-60);
                       
                       // escudo.setAlignment(escudo.ALIGN_LEFT | escudo.TEXTWRAP);


                        Paragraph parrafo = new Paragraph();



        if(notas_certificadas==true){//MATERIAS CERTIFICADAS

                System.out.println("NOTAS CERTIFICADAS: "+orden.size());
                  if(orden.size()<=0){System.out.println("Este alumno no tiene materia registrada");}else{
                System.out.println("......................................................RECORD NOTAS CERTIFICADAS.........................");
                
                           //PAGINA SIGUIENTE . RECORD ACADEMICO
                System.out.println("Analizando materias para crear las notas certificadas.");
                this.certificando_materias(orden);//MATERIAS CERTIFICADAS---ORIGINAL
                //this.certificando_txt(orden);//MATERIAS CERTIFICADAS

                
                
                        //-----PORTADA NOTAS CERTIFICADAS----------------------------------------------
                     if(portada==true){//habilitando constancia de inscripcion
                        System.out.println("CREANDO LA PORTADA");

                        parrafo=new Paragraph("\n\n\nREPÚBLICA BOLIVARIANA DE VENEZUELA\nMINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"+
                                               "UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA\n" +
                                               "DE LA FUERZA ARMADA NACIONAL\n"+                                      
                                               "U.N.E.F.A.\n\n\n\n",fuente[9]);
                       
                        parrafo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(parrafo);

                        Calendar cx = Calendar.getInstance();
                        
                        parrafo= new Paragraph("CONSTANCIA DE NOTAS CERTIFICADAS: Quien suscribe, Secretaria General de la Universidad Nacional Experimental "+
                                               "Politécnica de la Fuerza Armada Nacional (UNEFA), hace constar que en el Historial Académico "+matricula+", perteneciente "
                                               +"al ciudadano(a): "+estudiante+", titular de la Cédula de Identidad N° "+cedula+", están registradas las asignaturas que "+
                                               "a continuacion se indican, con sus respectivas calificaciones correspondientes a los semestres académicos cursados en esta "+
                                               "Universidad. Escala de calificaciones de 0 a 20 puntos. Nota mínima aprobatoria 10 puntos. CARRERA: "
                                               +this.nombre_real_carrera(carrera,inscrito,1),fuente[10]);
                        parrafo.setIndentationLeft(20f);
                        parrafo.setIndentationRight(20f);
                        parrafo.setSpacingAfter(100f);
                        parrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
                        documento.add(parrafo);


                        //parrafo= new Paragraph("Dra. MARÍA JOSÉ TORRES LÓPEZ\nSECRETARIA GENERAL (E)",fuente[10]);
                        parrafo= new Paragraph(ra.autoridad("SECRETARIA GENERAL").split(":")[2]+" "+ra.autoridad("SECRETARIA GENERAL").split(":")[3]+" "+ra.autoridad("SECRETARIA GENERAL").split(":")[4]+"\nSECRETARIA GENERAL",fuente[10]);
                        parrafo.setIndentationLeft(20f);
                        parrafo.setIndentationRight(20f);
                        parrafo.setAlignment(Element.ALIGN_RIGHT);
                        documento.add(parrafo);



                        this.pie_pagina_dinamico(escritor, documento, new Paragraph("-VA SIN ENMIENDAS-",fuente[6]), 300f, 90f);
                        //this.pie_pagina_dinamico(escritor, documento, new Paragraph("Página "+this.getNumero_pagina()+"/4",fuente[6]), 300f, 70f);
                        
                                        //-------------------------ESTIMANDO HOJAS
                                        int u=Integer.valueOf(confexion.size()/4);
                                        System.out.println("AJUSTE: "+ajuste+" confexion:"+u);                                                
                                        if(u>ajuste){
                                            hojas_totales = Integer.valueOf(u/ajuste);
                                            if(Integer.valueOf(u%ajuste)>0) hojas_totales = hojas_totales + 1;//la division no es exacta, se toma el proximo inmediato. se suma 1 mas
                                            hojas_totales = hojas_totales + 2;//se suman luego 2 hojas adicionales que corresponden a la portada y la hoja final
                                        }else{hojas_totales = 3;}//estimador de cantidad de hojas
                                        //-------------------------FIN ESTIMACION DE HOJAS
                        
                        this.pie_pagina_dinamico(escritor, documento, new Paragraph("Página "+this.getNumero_pagina()+"/"+hojas_totales,fuente[6]), 300f, 70f);
                        documento.newPage();//colocando la informacion en una pagina nueva
            }//fin portada notas certificadas
        //------------------------FIN PORTADA NOTAS CERTIFICADAS------------------------------------------------------
                
                
                
                
                System.out.println("Fin analisis certificadas.\nCREANDO EL CUERPO NOTAS CERTIFICADAS.");
                               parrafo = new Paragraph(estudiante+"\n"+
                                                       "Cédula de Identidad No.: "+cedula+"\n"+
                                                       "Carrera: "+this.nombre_real_carrera(carrera,inscrito,2)+"\n",fuente[3] );
                                                       
                               parrafo.setIndentationLeft(110.0f);
                               parrafo.setAlignment(Element.ALIGN_LEFT);
                               documento.add(parrafo);
                               parrafo = new Paragraph("(Continuación)\n\n\n",fuente[5]);
                               parrafo.setAlignment(Element.ALIGN_CENTER);
                               documento.add(parrafo);


                                float[] ancho={0.5f,3.5f,1.0f,1.6f};
                                PdfPTable notas = new PdfPTable(ancho);
                                notas.setWidthPercentage(100);
                                notas.setHeaderRows(1);//estableciendo cabecera de la tabla
                                PdfPCell celdas = null;

                                //PdfPTable notas = new PdfPTable(6);

                                //cabecera de la tabla
                                    celdas = new PdfPCell(new Paragraph("P.A.",fuente[3]));//periodo de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("NOMBRE DE LA MATERIA",fuente[3]));//nombre de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("N.F.",fuente[3]));//nota final de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                    celdas = new PdfPCell(new Paragraph("OBSERVACIONES",fuente[3]));//unidades de credito de la materia
                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celdas.setBackgroundColor(new Color(147,247,135));
                                    notas.addCell(celdas);

                                //fin cabecera

                              //  System.out.println("TAMAÑO DEL LinkedList confexion: "+confexion.size());

                               this.setComp_periodo(confexion.get(3));//inicializando la variable con el primer periodo de las materias ordenadas como punto de partida


                                for(int z=0;z<confexion.size();z=z+4){//escribiendo las materias del alumno en el archivo pdf

                            //                    System.out.println("z/6: "+(z/6));
                                                conteo=conteo+1;

                                                if(conteo>=ajuste){//se ingresan datos a la tabla hasta x filas, luego se van compiando las otras en las paginas siguientes
                                                //   System.out.println("copiando en la pagina siguiente");
                                                    System.out.println("PAGINA ACTUAL: "+this.getNumero_pagina());
                                                    this.setNumero_pagina(this.getNumero_pagina()+1);//contando cuantas paginas para presentar toda la tabla del record

                                                    documento.add(notas);//agregando la tabla parcial al documento actual

                                                    notas.deleteBodyRows();//inicializando la tabla otra vez para agregar nuevos valores
                                                    notas.setSkipFirstHeader(false);//estableciendo una cabecera por cada pagina nueva
                                                    //this.pie_pagina(escritor, documento,this.getNumero_pagina());
                                                   // this.pie_pagina_dinamico(escritor, documento, new Paragraph("SELLO DE LA UNEFA",fuente[8]), 500f, 100f);
                                                    this.pie_pagina_dinamico(escritor, documento, new Paragraph("-VA SIN ENMIENDAS-",fuente[6]), 300f, 90f);
                                                    this.pie_pagina_dinamico(escritor, documento, new Paragraph("Página "+this.getNumero_pagina()+"/"+hojas_totales,fuente[6]), 300f, 70f);
                                                    documento.newPage();//pasa a la pagina siguiente
                                                    conteo=0;//se inicializa el conteo nuevamente

                                                     

                                                       parrafo = new Paragraph(estudiante+"\n"+
                                                                       "Cédula de Identidad No.: "+cedula+"\n"+
                                                                       "Carrera: "+this.nombre_real_carrera(carrera,inscrito,2)+"\n",fuente[3] );
                                                       parrafo.setIndentationLeft(110.0f);
                                                       parrafo.setAlignment(Element.ALIGN_LEFT);
                                                       documento.add(parrafo);
                                                       parrafo = new Paragraph("(Continuación)\n\n\n",fuente[5]);
                                                       parrafo.setAlignment(Element.ALIGN_CENTER);
                                                       documento.add(parrafo);

                                                      

                                                }


                                               if(this.getComp_periodo().equalsIgnoreCase(confexion.get(z+3))){

                                                    celdas = new PdfPCell(new Paragraph(String.valueOf(conteo_2),fuente[1]));// periodo de la materia pero en numero. ejm 1 2 3 4 5 6 7
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    notas.addCell(celdas);

                                                    //experimental....solo para tovar
                                                    
                                                    
                                                   // String asignatura_temporalff=null;
                                                   // if(confexion.get(z+1).contains("ELECTIVA")) asignatura_temporalff = confexion.get(z+1).replace("ELECTIVA NO TÉCNICA","ELECTIVA");
                                                   // else{asignatura_temporalff = confexion.get(z+1);}   
                                                    //celdas = new PdfPCell(new Paragraph(asignatura_temporalff,fuente[1]));//nombre de la de la materia
                                                    celdas = new PdfPCell(new Paragraph(confexion.get(z+1),fuente[1]));//nombre de la de la materia
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                                    notas.addCell(celdas);

                                                    celdas = new PdfPCell(new Paragraph(this.guion_nota(confexion.get(z+0))+" "+this.notas_letras(confexion.get(z+0)),fuente[1]));//nota final de la materia
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                                    notas.addCell(celdas);

                                                    celdas = new PdfPCell(new Paragraph(confexion.get(z+2),fuente[1]));//observaciones
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    notas.addCell(celdas);


                                               }else{//en caso que sean distinto los periodos, copio el espacio en blanco y luego la fila que corresponde


                                                    //ESPACIO EN BLANCO............................................................COLOCANDO LA NOTA

                                                    celdas = new PdfPCell(new Paragraph(" ",fuente[3]));//espacio en blanco
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setColspan(8);
                                                    celdas.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                    //celdas.setBackgroundColor(new Color(185,190,203));
                                                    notas.addCell(celdas);
                                                    conteo_2 = conteo_2+1;//contando los periodos periodo



                                                    //..........................................................................................


                                                //GARANTIZANDO COPIAR EL REGISTRO ACTUAL

                                                    celdas = new PdfPCell(new Paragraph(String.valueOf(conteo_2),fuente[1]));// periodo de la materia pero en numero. ejm 1 2 3 4 5 6 7
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    notas.addCell(celdas);

                                                   
                                                   // String asignatura_temporalf2=null;
                                                    //if(confexion.get(z+1).contains("ELECTIVA")) asignatura_temporalf2 = confexion.get(z+1).replace("ELECTIVA NO TÉCNICA","ELECTIVA");
                                                    //else{asignatura_temporalf2 = confexion.get(z+1);}  
                                                    
                                                    //celdas = new PdfPCell(new Paragraph(asignatura_temporalf2,fuente[1]));//nombre de la de la materia
                                                    celdas = new PdfPCell(new Paragraph(confexion.get(z+1),fuente[1]));//nombre de la de la materia
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                                    notas.addCell(celdas);

                                                    celdas = new PdfPCell(new Paragraph(this.guion_nota(confexion.get(z+0))+" "+this.notas_letras(confexion.get(z+0)),fuente[1]));//nota final de la materia
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_LEFT);
                                                    notas.addCell(celdas);

                                                    celdas = new PdfPCell(new Paragraph(confexion.get(z+2),fuente[1]));//observaciones
                                                    celdas.setBorder(Rectangle.NO_BORDER);//sin borde
                                                    celdas.setHorizontalAlignment(Element.ALIGN_CENTER);
                                                    notas.addCell(celdas);


                                               this.setComp_periodo(confexion.get(z+3));


                                               }

                                }//fin del recorrido del LinkedList orden

        System.out.println("Fin CUERPO CERTIFICADAS");


                                    notas.spacingBefore();//espacio despues que se coloca la tabla
                                    documento.add(notas);
                                    //UC APROBADAS, PTOS ACUMULADOS, INDICE ACADEMICO

                                    this.setNumero_pagina(this.getNumero_pagina()+1);
                                  //  this.pie_pagina_dinamico(escritor, documento, new Paragraph("SELLO DE LA UNEFA",fuente[8]), 500f, 100f);
                                    this.pie_pagina_dinamico(escritor, documento, new Paragraph("-VA SIN ENMIENDAS-",fuente[6]), 300f, 90f);
                                    this.pie_pagina_dinamico(escritor, documento, new Paragraph("Página "+this.getNumero_pagina()+"/"+hojas_totales,fuente[6]), 300f, 70f);
                                 documento.newPage();//agegando una nueva pagina




                             //pagina final--------------------------------------------------------------------------firmas y sellos

        System.out.println("REDACTANDO PAGINA FINAL DE FIRMAS Y SELLOS");
                                 //MEMBRETE
                                 parrafo = new Paragraph(estudiante+"\n"+
                                                           "Cédula de Identidad No.: "+cedula+"\n"+
                                                           "Carrera: "+this.nombre_real_carrera(carrera,inscrito,2)+"\n",fuente[3] );
                                           parrafo.setIndentationLeft(110.0f);
                                           parrafo.setAlignment(Element.ALIGN_LEFT);
                                           documento.add(parrafo);
                                           parrafo = new Paragraph("(Continuación)\n\n\n\n",fuente[5]);
                                           parrafo.setAlignment(Element.ALIGN_CENTER);
                                           documento.add(parrafo);

                                parrafo = new Paragraph("Constancia que se expide a petición de la parte interesada en "+this.lugar_sede(nucleo_extension)
                                                      // +" a los diecinueve dias del mes de marzo de dos mil trece",fuente[7]);
                                                         +" a los "+this.calendario_3(),fuente[7]);
                                
                                
                                
                                //parrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL); //ORIGINAL
                                parrafo.setAlignment(Element.ALIGN_LEFT);
                                parrafo.setIndentationLeft(20f);//espacio con respecto al margen de la izquierda
                                parrafo.setIndentationRight(20f);//espacio con respecto al margen de la derecha
                                documento.add(parrafo);

                                String indfed[] = this.independencia_federacion();
                                parrafo = new Paragraph("Año "+indfed[0]+"° y "+indfed[1]+"°\n\n",fuente[7]);
                                parrafo.setAlignment(Element.ALIGN_LEFT);
                                parrafo.setIndentationLeft(20f);
                                parrafo.setIndentationRight(20f);
                                documento.add(parrafo);

                                parrafo = new Paragraph("Elaborado por: "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[2]+" "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[3]+", "+ra.autoridad("JEFE DE CONTROL DE ESTUDIO").split(":")[4]+
                                                      "\nRevisado por: "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[2]+" "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[3]+", "+ra.autoridad("JEFE DE SECRETARIA DEL NUCLEO").split(":")[4], fuente[7]);
                                parrafo.setAlignment(Element.ALIGN_LEFT);
                                parrafo.setIndentationLeft(25f);//espacio con respecto al margen
                                parrafo.setSpacingAfter(50f);//colocando el espacio de separacion con respecto a las firmas
                                documento.add(parrafo);



                                float[] ancho_x={4.0f,2f};//estableciendo ancho por columnas
                                PdfPTable firmas = new PdfPTable(ancho_x);

                                PdfPCell contenido = null;



                                //directora de ingreso y control de estudios de caracas

                               // contenido = new PdfPCell(new Paragraph("MSc. NORIS MERCEDES DELGADO DUNO"+"\nDIRECTORA DE INGRESO Y CONTROL DE ESTUDIOS (E)",fuente[3]));
                                contenido = new PdfPCell(new Paragraph(ra.autoridad("DIRECTOR(A) DE INGRESO Y CONTROL DE ESTUDIOS").split(":")[2]+" "+ra.autoridad("DIRECTOR(A) DE INGRESO Y CONTROL DE ESTUDIOS").split(":")[3]+" "+ra.autoridad("DIRECTOR(A) DE INGRESO Y CONTROL DE ESTUDIOS").split(":")[4]+"\nDIRECTOR(A) DE INGRESO Y CONTROL DE ESTUDIOS",fuente[3]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.TOP);
                                firmas.addCell(contenido);

                                contenido = new PdfPCell(new Paragraph("",fuente[0]));
                                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido.setBorder(Rectangle.NO_BORDER);
                                firmas.addCell(contenido);
                                firmas.setSpacingAfter(60f);//espaciado entre tablas

                                documento.add(firmas);

                                float[] ancho_y={2.0f,4.0f};//estableciendo anchos por columnas
                                PdfPTable firmas_2 = new PdfPTable(ancho_y);
                                PdfPCell contenido_2 = null;
                                //secretaria general de caracas
                                contenido_2 = new PdfPCell(new Paragraph("",fuente[0]));
                                contenido_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido_2.setBorder(Rectangle.NO_BORDER);
                                firmas_2.addCell(contenido_2);

                               // contenido_2 = new PdfPCell(new Paragraph("DOCTORA MARÍA JOSÉ TORRES LÓPEZ"+"\n"+"SECRETARIA GENERAL (E)",fuente[3]));
                                contenido_2= new PdfPCell(new Paragraph(ra.autoridad("SECRETARIA GENERAL").split(":")[2]+" "+ra.autoridad("SECRETARIA GENERAL").split(":")[3]+" "+ra.autoridad("SECRETARIA GENERAL").split(":")[4]+"\nSECRETARIA GENERAL",fuente[3]) );
                                contenido_2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                contenido_2.setBorder(Rectangle.TOP);
                                firmas_2.addCell(contenido_2);

                                documento.add(firmas_2);





                                






                                    //NOTA INFORMATIVA

                                  //  parrafo= new Paragraph("\nNota: Estas páginas estan de manera informativa, cualquier pregunta por favor dirigirse al Departamento de Secretaria de su respectiva sede.\n\n",fuente[1]);
                                   // parrafo.setAlignment(Element.ALIGN_CENTER);
                                   // documento.add(parrafo);



                                     //Chunk barra = new Chunk(this.codigo_matricial("hola"),0,-60);


                                     parrafo = new Paragraph("\nLeyenda:",fuente[5]);//colocando la fecha de emision del documento
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);

                                     parrafo = new Paragraph("\nP.A.: Período Académico\nN.F.: Nota Final",fuente[1]); //iniciales decano, jefe secretaria, directora, jefe control de estudio
                                     parrafo.setAlignment(Element.ALIGN_LEFT);
                                     documento.add(parrafo);

                                   

                                     this.setNumero_pagina(this.getNumero_pagina()+1);//continuando con el conteo de paginas
                                     //this.pie_pagina(escritor, documento, this.getNumero_pagina());//colocando el numero de la pagina
                                       // this.pie_pagina_dinamico(escritor, documento, new Paragraph("SELLO DE LA UNEFA",fuente[8]), 500f, 100f);
                                        this.pie_pagina_dinamico(escritor, documento, new Paragraph("-VA SIN ENMIENDAS-",fuente[6]), 300f, 90f);
                                        this.pie_pagina_dinamico(escritor, documento, new Paragraph("Página "+this.getNumero_pagina()+"/"+hojas_totales,fuente[6]), 300f, 70f);

                                   //  this.cabecera(escritor, documento,"CEDULA: "+cedula+"    ALUMNO: "+estudiante+"    ESPECIALIDAD: "+carrera+ "    MATRICULA: "+matricula);//cabecera del documento

                                     documento.newPage();

                    }
            }//fin habilitacion record academico
                //limpiando variableas acumulativas
                this.setUc_cursadas(0);
                this.setNumero_pagina(0);
                this.setUc_aprobadas(0);
                this.setUc_inscritas(0);
                this.setPuntos(0);
                this.setUc_equivalencias(0);
                this.getAlmacen().clear();

                orden.clear();//limpiando materias del record

        //_-------------------------FIN RECORD ACADEMICO-----------------------------


                            documento.close();

                try {
                    archivo.close();
                } catch (IOException ex) {
                    Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
                }







        







}



public String notas_letras(String nota){
    int opcion=0;
    opcion=Integer.valueOf(nota);

    switch(opcion){
        case 0: nota=""; break;
        case 1: nota="(UNO)"; break;
        case 2: nota="(DOS)"; break;
        case 3: nota="(TRES)"; break;
        case 4: nota="(CUATRO)"; break;
        case 5: nota="(CINCO)"; break;
        case 6: nota="(SEIS)"; break;
        case 7: nota="(SIETE)"; break;
        case 8: nota="(OCHO)"; break;
        case 9: nota="(NUEVE)"; break;
        case 10: nota="(DIEZ)"; break;
        case 11: nota="(ONCE)"; break;
        case 12: nota="(DOCE)"; break;
        case 13: nota="(TRECE)"; break;
        case 14: nota="(CATORCE)"; break;
        case 15: nota="(QUINCE)"; break;
        case 16: nota="(DIECISEIS)"; break;
        case 17: nota="(DIECISIETE)"; break;
        case 18: nota="(DIECIOCHO)"; break;
        case 19: nota="(DIECINUEVE)"; break;
        case 20: nota="(VEINTE)"; break;

        default: nota = "NE";   break;

    }

return nota;
}


public String guion_nota(String nota){

    if(nota.equalsIgnoreCase("0")) nota="-";

   return nota;
}

public int cerapio(String nota){

    if(nota==null || nota=="" || nota.isEmpty()){ nota="0";}
    return Integer.valueOf(nota);
}

/**Este metodo crea las materias certificadas <br>
        posicion = 0 //nota definitiva<br>
        posicion = 1 //nombre de la materia<br>
        posicion = 2 //condicion de la materia<br>
        posicion = 3 //periodo academico*/
private void certificando_materias(LinkedList<String> orden){
String cn=null,pa=null,nm=null;
int nd=0,nr=0,dr=0,pi=0;//inicializando variable
confexion.clear();//limpiando el LinkedList
//cn=condicion, pa=periodo academico, nd=nota definitiva, nr=nota reparacion, dr=definitiva reparacion, pi=por inasistencia, nm=nombre materia

    //SOLO COMO LEYENDA
/*          this.getOrden().add(this.getAux2().get(s));//0  codigo de la materia
            this.getOrden().add(this.getAux2().get(s+1));//1  nombre de la materia
            this.getOrden().add(this.getAux2().get(s+2));//2  definitiva de la materia
            this.getOrden().add(this.getAux2().get(s+3));//3  nota de reparacion
            this.getOrden().add(this.getAux2().get(s+4));//4  condicion de la materia
            this.getOrden().add(this.getAux2().get(s+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAux2().get(s+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAux2().get(s+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAux2().get(s+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAux2().get(s+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAux2().get(s+10));//10  semestre de la materia*/

        for(int g=0; g<orden.size(); g=g+11){//recorriendo las materias ordenadas del record

                nd=this.cerapio(orden.get(g+2));
                nr=this.cerapio(orden.get(g+3));
                dr=this.cerapio(orden.get(g+6));
                pi=this.cerapio(orden.get(g+7));
                cn=materia.condicion_materia_inversa(orden.get(g+4));//solo se debe entregar la letra de la conversion.Ejm "APROBADO"="A"
                pa=this.efecto_intesivo_periodo(orden.get(g+5));
                nm=orden.get(g+1);
                System.out.print("MATERIA: "+nm+" cond: "+cn+" ");
                this.analisis_certificadas(nd, nr, dr, pi, cn, pa, nm);//realizando analisis por materias

        }
/*
System.out.println("REVISANDO TODO EL MATERIAL............................!!!!!!!!!!!!---"+confexion.size());

        for(int y=0; y<confexion.size(); y=y+4){//recorriendo el vecttor con las materias arregladas certificadas
            System.out.print(confexion.get(y)+" ");
            System.out.print(confexion.get(y+1)+" ");
            System.out.print(confexion.get(y+2)+" ");
            System.out.println(confexion.get(y+3));


        }
*/


}


private void analisis_certificadas(int nd, int nr, int dr, int pi, String cn, String pa, String nm){
System.out.println("Situacion: nd: "+nd+" nr: "+nr+" dr: "+dr+" pi: "+pi+" cn: "+cn);

if(nd>=10 && nr==0 && dr==0 && pi==0 && (cn.equalsIgnoreCase("N") || cn.equalsIgnoreCase("01"))){System.out.println("Analsis NORMAL");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//materia en condicion normal-NORMAL

    if(nd<10 && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("R") || cn.equalsIgnoreCase("05")) ){System.out.println("Analsis REPARÓ");
       // confexion.add(String.valueOf(nd));//nota definitiva
       // confexion.add(nm);//nombre de la materia
      //  confexion.add("REPROBÓ");//condicion de la materia 6 para cuantitativa la primera vez
       // confexion.add("");//se deja la condicion en blanco xq las bestias de caracas decidieron dejarlo asi
      //  confexion.add(pa);//periodo academico
        
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPARÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//RECUPERO

    if(nd<10 && nr>0 && nr<10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("R") || cn.equalsIgnoreCase("07"))){System.out.println("Analsis REPARÓ-PENDIENTE");
       // confexion.add(String.valueOf(nd));//nota definitiva
     //   confexion.add(nm);//nombre de la materia
     //   confexion.add("REPROBÓ");//condicion de la materia 6 para uantitativa la primera vez
        //confexion.add("");//se deja la condicion en blanco xq las bestias de caracas decidieron dejarlo asi
      //  confexion.add(pa);//periodo academico
        
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//RECUPERO-PENDIENTE

    if(nd<10 && nr==0 && dr==0 && pi==0 && (cn.equalsIgnoreCase("R") || cn.equalsIgnoreCase("09")) ){System.out.println("Analsis NO REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("NO REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//NO RECUPERO-PENDIENTE
    //--------------------------------------------------------------------------------------------------------------
    if(nd<10 && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("02")) ){System.out.println("Analsis PARALELO");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO

    if(nd<10 && nr>0 && nr<10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("14")) ){System.out.println("Analsis PARALELO-NO REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALEO-NO REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO-NO RECUPERO-PENDIENTE

    if(nd<10 && nr>0 && nr<10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("12")) ){System.out.println("Analsis PARALELO-REPARÓ-PENDIENTE");
       // confexion.add(String.valueOf(dr));//nota definitiva
       // confexion.add(nm);//nombre de la materia
       // confexion.add("PARALELO");//condicion de la materia
       // confexion.add(pa);//periodo academico
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO-REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO-RECUPERO-PENDIENTE

    if(nd<10 && nr>=10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("13")) ){System.out.println("Analsis PARALELO-RECUPERÓ");
        //confexion.add(String.valueOf(dr));//nota definitiva
       // confexion.add(nm);//nombre de la materia
      //  confexion.add("PARALELO");//condicion de la materia
     //   confexion.add(pa);//periodo academico
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO-REPARÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO-RECUPERO
    //---------------------------------------------------------------------------------------------------------------
    if(nd<10 && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("03")) ){System.out.println("Analsis REPITENCIA");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA
    //CONSIDERACION ESPECIAL DE REPITENCIA. MATERIAS REPROBADAS MENOR A 15
    if((nm.contains("PASANTÍA")||nm.contains("PASANTIA")||nm.contains("TRABAJO ESPECIAL DE GRADO")||nm.contains("PRÁCTICA EDUCATIVA")||nm.contains("PRACTICA EDUCATIVA")) 
        && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("03")) ){System.out.println("Analsis REPITENCIA");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA----MATERIAS REPROBADAS CON NOTAS MAYOR A 10 y MENOR A 15
    
    
    if(nd<10 && nr>0 && nr<10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("17")) ){System.out.println("Analsis REPITENCIA-NO REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA-NO REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA-NO RECUPERO-PENDIENTE

    if(nd<10 && nr>0 && nr<10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("15")) ){System.out.println("Analsis REPITENCIA-REPARÓ-PENDIENTE");
      //  confexion.add(String.valueOf(dr));//nota definitiva
      //  confexion.add(nm);//nombre de la materia
      //  confexion.add("REPITENCIA");//condicion de la materia
      //  confexion.add(pa);//periodo academico
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA-REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA-RECUPERO-PENDIENTE

    if(nd<10 && nr>=10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("16")) ){System.out.println("Analsis REPITENCIA-REPARÓ");
       // confexion.add(String.valueOf(dr));//nota definitiva
      //  confexion.add(nm);//nombre de la materia
      //  confexion.add("REPITENCIA");//condicion de la materia
     //   confexion.add(pa);//periodo academico    
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA-REPARÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA-RECUPERO
    //--------------------------------------------------------------------------------------------------------------
    if((pi==25 || cn.equalsIgnoreCase("10")) ){System.out.println("Analsis REPROBÓ-25% DE INASISTENCIA");//consideracion materias normales
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-25% DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-25% DE INASISTENCIA
    
    if((pi==50 || cn.equalsIgnoreCase("23")) ){System.out.println("Analsis REPROBÓ-50% DE INASISTENCIA");//consideracion materias normales
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-50% DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-50% DE INASISTENCIA

   /* if((pi==25 || cn.equalsIgnoreCase("10")) ){System.out.println("Analsis REPROBÓ-25% DE INASISTENCIA"); //consideracion materias por repitencia o paralelo
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-25 % DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-25% DE INASISTENCIA

    if( (pi==50 || cn.equalsIgnoreCase("23")) ){System.out.println("Analsis REPROBO-50% DE INASISTENCIA"); //consideracion materias por repitencia o paralelo
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-50 % DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-50% DE INASISTENCIA*/

     if(cn.equalsIgnoreCase("A") || cn.equalsIgnoreCase("08") ){System.out.println("Analsis APROBÓ");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("APROBÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("Q") || cn.equalsIgnoreCase("06") ){System.out.println("Analsis REPROBÓ");
        //este analisis es para las materias especiales que son cualitativas pero tienen condicion 06 pero no se reparan sino que se repiten
      if(nm.contains("PASANTÍA") || nm.contains("PASANTIA") || nm.contains("TRABAJO ESPECIAL DE GRADO") || nm.contains("PRÁCTICA EDUCATIVA") || nm.contains("PRACTICA EDUCATIVA"))
      {confexion.add(String.valueOf(nd));//nota definitiva
       confexion.add(nm);//nombre de la materia
       confexion.add("REPROBÓ");//condicion de la materia
       confexion.add(pa);//periodo academico
      }else{
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
       }
    }
//---------------------------------------------------------------------------
    if(cn.equalsIgnoreCase("E") || cn.equalsIgnoreCase("04") ){System.out.println("Analsis EQUIVALENCIA");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("EQUIVALENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("S") || cn.equalsIgnoreCase("11") ){System.out.println("Analsis SUFICIENCIA");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("SUFICIENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }
//----------------------------------------------------------------------------
    if(cn.equalsIgnoreCase("18") ){System.out.println("Analsis MANTENIMIENTO");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("MANTENIMIENTO");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("19") ){System.out.println("Analsis EXONERADO");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("EXONERADO");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("20") ){System.out.println("Analsis RECONOCIMIENTO DE CRÉDITOS");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("RECONOCIMIENTO DE CRÉDITOS");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("21") ){System.out.println("Analsis NIVELACIÓN");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("NIVELACIÓN");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("22") ){System.out.println("Analsis ACREDITACIÓN");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("ACREDITACIÓN");//condicion de la materia
        confexion.add(pa);//periodo academico
    }




System.out.println("ANALISIS CERTIFICADAS:  llenado confexion: "+confexion.size());
}






/**Este metodo crea las materias certificadas <br>
        posicion = 0 //nota definitiva<br>
        posicion = 1 //nombre de la materia<br>
        posicion = 2 //condicion de la materia<br>
        posicion = 3 //periodo academico*/
private void certificando_txt(LinkedList<String> orden){
String cn=null,pa=null,nm=null;
int nd=0,nr=0,dr=0,pi=0;//inicializando variable
confexion.clear();//limpiando el LinkedList

        for(int g=0; g<orden.size(); g=g+11){//recorriendo las materias ordenadas del record

                nd=this.cerapio(orden.get(g+2));
                nr=this.cerapio(orden.get(g+3));
                dr=this.cerapio(orden.get(g+6));
                pi=this.cerapio(orden.get(g+7));
                System.out.println("cond actual> "+orden.get(g+4));
                cn=materia.condicion_materia_inversa(orden.get(g+4));//solo se debe entregar la letra de la conversion.Ejm "APROBADO"="A"
                pa=orden.get(g+5);
                nm=orden.get(g+1);
                System.out.print("MATERIA: "+nm+" cond: "+cn+" ");
                
                this.analisis_para_txt(nd, nr, dr, pi, cn, pa, nm);//realizando analisis por materias

        }
}


private void analisis_para_txt(int nd, int nr, int dr, int pi, String cn, String pa, String nm){
System.out.println("TXT: nd: "+nd+" nr: "+nr+" dr: "+dr+" pi: "+pi+" cn: "+cn);

    if(nd>=10 && nr==0 && dr==0 && pi==0 && (cn.equalsIgnoreCase("N") || cn.equalsIgnoreCase("01"))){System.out.println("Analsis NORMAL");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//materia en condicion normal-NORMAL

    if(nd<10 && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("R") || cn.equalsIgnoreCase("05")) ){System.out.println("Analsis REPARÓ");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ");//condicion de la materia 6 para cuantitativa la primera vez
       // confexion.add("");//se deja la condicion en blanco xq las bestias de caracas decidieron dejarlo asi
        confexion.add(pa);//periodo academico
        
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPARÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//RECUPERO

    if(nd<10 && nr>0 && nr<10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("R") || cn.equalsIgnoreCase("07"))){System.out.println("Analsis REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ");//condicion de la materia 6 para uantitativa la primera vez
        //confexion.add("");//se deja la condicion en blanco xq las bestias de caracas decidieron dejarlo asi
        confexion.add(pa);//periodo academico
        
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//RECUPERO-PENDIENTE

    if(nd<10 && nr==0 && dr==0 && pi==0 && (cn.equalsIgnoreCase("R") || cn.equalsIgnoreCase("09")) ){System.out.println("Analsis NO REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("NO REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//NO RECUPERO-PENDIENTE
    //--------------------------------------------------------------------------------------------------------------
    if(nd<10 && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("02")) ){System.out.println("Analsis PARALELO");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO

    if(nd<10 && nr>0 && nr<10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("14")) ){System.out.println("Analsis PARALELO-NO REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALEO-NO REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO-NO RECUPERO-PENDIENTE

    if(nd<10 && nr>0 && nr<10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("12")) ){System.out.println("Analsis PARALELO-REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(dr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO");//condicion de la materia
        confexion.add(pa);//periodo academico
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO-REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO-RECUPERO-PENDIENTE

    if(nd<10 && nr>=10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("L") || cn.equalsIgnoreCase("13")) ){System.out.println("Analsis PARALELO-RECUPERÓ");
        confexion.add(String.valueOf(dr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO");//condicion de la materia
        confexion.add(pa);//periodo academico
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("PARALELO-REPARÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//PARALELO-RECUPERO
    //---------------------------------------------------------------------------------------------------------------
    if(nd<10 && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("03")) ){System.out.println("Analsis REPITENCIA");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA
    //CONSIDERACION ESPECIAL DE REPITENCIA. MATERIAS REPROBADAS MENOR A 15
    if((nm.contains("PASANTÍA")||nm.contains("PASANTIA")||nm.contains("TRABAJO ESPECIAL DE GRADO")||nm.contains("PRÁCTICA EDUCATIVA")||nm.contains("PRACTICA EDUCATIVA")) 
        && nr>=10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("03")) ){System.out.println("Analsis REPITENCIA");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA----MATERIAS REPROBADAS CON NOTAS MAYOR A 10 y MENOR A 15
    
    
    if(nd<10 && nr>0 && nr<10 && dr==0 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("17")) ){System.out.println("Analsis REPITENCIA-NO REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(nr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA-NO REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA-NO RECUPERO-PENDIENTE

    if(nd<10 && nr>0 && nr<10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("15")) ){System.out.println("Analsis REPITENCIA-REPARÓ-PENDIENTE");
        confexion.add(String.valueOf(dr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA-REPARÓ-PENDIENTE");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA-RECUPERO-PENDIENTE

    if(nd<10 && nr>=10 && dr>0 && dr<10 && pi==0 && (cn.equalsIgnoreCase("P") || cn.equalsIgnoreCase("16")) ){System.out.println("Analsis REPITENCIA-REPARÓ");
        confexion.add(String.valueOf(dr));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico    
        confexion.add(String.valueOf(nr));//nota definitiva //cuando la fue a reparar
        confexion.add(nm);//nombre de la materia
        confexion.add("REPITENCIA-REPARÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPITENCIA-RECUPERO
    //--------------------------------------------------------------------------------------------------------------
    if((pi==25 || cn.equalsIgnoreCase("10")) ){System.out.println("Analsis REPROBÓ-25% DE INASISTENCIA");//consideracion materias normales
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-25% DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-25% DE INASISTENCIA
    
    if((pi==50 || cn.equalsIgnoreCase("23")) ){System.out.println("Analsis REPROBÓ-50% DE INASISTENCIA");//consideracion materias normales
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-50% DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-50% DE INASISTENCIA

   /* if((pi==25 || cn.equalsIgnoreCase("10")) ){System.out.println("Analsis REPROBÓ-25% DE INASISTENCIA"); //consideracion materias por repitencia o paralelo
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-25 % DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-25% DE INASISTENCIA

    if( (pi==50 || cn.equalsIgnoreCase("23")) ){System.out.println("Analsis REPROBO-50% DE INASISTENCIA"); //consideracion materias por repitencia o paralelo
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ-50 % DE INASISTENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }//REPROBO-50% DE INASISTENCIA*/

     if(cn.equalsIgnoreCase("A") || cn.equalsIgnoreCase("08") ){System.out.println("Analsis APROBÓ");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("APROBÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("Q") || cn.equalsIgnoreCase("06") ){System.out.println("Analsis REPROBÓ");
        //este analisis es para las materias especiales que son cualitativas pero tienen condicion 06 pero no se reparan sino que se repiten
      if(nm.contains("PASANTÍA") || nm.contains("PASANTIA") || nm.contains("TRABAJO ESPECIAL DE GRADO") || nm.contains("PRÁCTICA EDUCATIVA") || nm.contains("PRACTICA EDUCATIVA"))
      {confexion.add(String.valueOf(nd));//nota definitiva
       confexion.add(nm);//nombre de la materia
       confexion.add("REPROBÓ");//condicion de la materia
       confexion.add(pa);//periodo academico
      }else{
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("REPROBÓ");//condicion de la materia
        confexion.add(pa);//periodo academico
       }
    }
//---------------------------------------------------------------------------
    if(cn.equalsIgnoreCase("E") || cn.equalsIgnoreCase("04") ){System.out.println("Analsis EQUIVALENCIA");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("EQUIVALENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("S") || cn.equalsIgnoreCase("11") ){System.out.println("Analsis SUFICIENCIA");
        confexion.add(String.valueOf(nd));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("SUFICIENCIA");//condicion de la materia
        confexion.add(pa);//periodo academico
    }
//----------------------------------------------------------------------------
    if(cn.equalsIgnoreCase("18") ){System.out.println("Analsis MANTENIMIENTO");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("MANTENIMIENTO");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("19") ){System.out.println("Analsis EXONERADO");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("EXONERADO");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("20") ){System.out.println("Analsis RECONOCIMIENTO DE CRÉDITOS");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("RECONOCIMIENTO DE CRÉDITOS");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("21") ){System.out.println("Analsis NIVELACIÓN");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("NIVELACIÓN");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

    if(cn.equalsIgnoreCase("22") ){System.out.println("Analsis ACREDITACIÓN");
        confexion.add(String.valueOf(0));//nota definitiva
        confexion.add(nm);//nombre de la materia
        confexion.add("ACREDITACIÓN");//condicion de la materia
        confexion.add(pa);//periodo academico
    }

System.out.println("ANALISIS TXT:: "+confexion.size());
}











/**Este metodo se encarga de decir el nombre correcto y completo de la carrera, ademas del año de ingreso
 y el año de egreso tipo 1. El tipo 2 tiene que ver con solo presentar el nombre completo de la carrera. */
public String nombre_real_carrera(String especialidad, String ingreso, int tipo){
 String nombre="NO EXISTE";//inicializando la variable y se asegura que si el parametro no se encuentra esto es lo que va aparecer.
 
Calendar c = Calendar.getInstance();//obteniendo una instancia de la fecha del computador donde corre el programa
         if(tipo==1){                                                                                                                                         //recordar poner los espacios en blanco para cuando  se justifique el documento no separe los caracteres y se vea feo
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) nombre="INGENIERÍA MECÁNICA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) nombre="LICENCIATURA EN EDUCACIÓN INTEGRAL "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) nombre="LICENCIATURA EN CONTADURÍA PÚBLICA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) nombre="LICENCIATURA EN ECONOMÍA SOCIAL "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN TURISMO "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN ENFERMERÍA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);
            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA")) nombre="LICENCIATURA EN ENFERMERÍA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) nombre="INGENIERÍA AERONÁUTICA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) nombre="INGENIERÍA CIVIL "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) nombre="INGENIERÍA ELÉCTRICA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) nombre="INGENIERÍA ELECTRÓNICA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) nombre="INGENIERÍA DE SISTEMAS "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) nombre="INGENIERÍA DE TELECOMUNICACIONES "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) nombre="LICENCIATURA EN ADMINISTRACIÓN Y GESTIÓN MUNICIPAL "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("TSU EN COMUNICACIONES Y ELECTRONICA")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN COMUNICACIONES Y ELECTRÓNICA "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("TSU EN ANALISIS Y DISEÑO DE SISTEMAS")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN ANÁLISIS Y DISEÑO DE SISTEMAS "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE"))nombre="LICENCIATURA EN ADMINISTRACIÓN DE DESASTRE "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL"))nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN MECÁNICA DENTAL "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO")) nombre="LICENCIATURA EN TURISMO "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(55);
            if(especialidad.equalsIgnoreCase("INGENIERIA AGROINDUSTRIAL")) nombre="INGENIERÍA AGROINDUSTRIAL "+ingreso.substring(2)+"-"+c.get(Calendar.YEAR)+"."+this.crear_espacios(30);
         }

         if(tipo==2){
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) nombre="INGENIERÍA MECÁNICA ";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) nombre="LICENCIATURA EN EDUCACIÓN INTEGRAL ";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) nombre="LICENCIATURA EN CONTADURÍA PÚBLICA ";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) nombre="LICENCIATURA EN ECONOMÍA SOCIAL ";
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN TURISMO ";

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN ENFERMERÍA ";
            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA")) nombre="LICENCIATURA EN ENFERMERÍA ";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) nombre="INGENIERÍA AERONÁUTICA ";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) nombre="INGENIERÍA CIVIL ";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) nombre="INGENIERÍA ELÉCTRICA ";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) nombre="INGENIERÍA ELECTRÓNICA ";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) nombre="INGENIERÍA DE SISTEMAS ";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) nombre="INGENIERÍA DE TELECOMUNICACIONES ";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) nombre="LICENCIATURA EN ADMINISTRACIÓN Y GESTIÓN MUNICIPAL ";
            
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE"))nombre="LICENCIATURA EN ADMINISTRACIÓN DE DESASTRE ";
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL"))nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN MECÁNICA DENTAL ";
            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO")) nombre="LICENCIATURA EN TURISMO ";
            
            if(especialidad.equalsIgnoreCase("TSU EN COMUNICACIONES Y ELECTRONICA")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN COMUNICACIONES Y ELECTRÓNICA ";
            if(especialidad.equalsIgnoreCase("TSU EN ANALISIS Y DISEÑO DE SISTEMAS")) nombre="TÉCNICO SUPERIOR UNIVERSITARIO EN ANÁLISIS Y DISEÑO DE SISTEMAS ";
            if(especialidad.equalsIgnoreCase("INGENIERIA AGROINDUSTRIAL")) nombre="INGENIERÍA AGROINDUSTRIAL ";
            
          }

return nombre;

}
/**Metodo que se encarga de generar la cantidad de espacios en blanco que sean necesarios de
 forma concatenada y los devuelve para ser empleado en alguna parte de utilidad. solo se necesita
 saber cuantos se desean crear.*/
public String crear_espacios(int cantidad){
        String espacios="";//inicializando la variable

        for(int eb=0; eb<=cantidad; eb++){//creando los espacios en blanco

            espacios=espacios.concat(" ");    //concatenando los espacios

        }//fin creacion espacios en blanco

return espacios;
}


/**este metodo permite generar el archivo txt de las notas de los egresados
 CEDULA	CODCARRERA CODOPCION NTERMINO MODALIDAD	CODMATERIA  NMATERIA	 NOTA	CONDICION	CIM	       PERIODOACA	UC	FEGRESO
15196975     16       3116N	3	02	 MAT10114  SEMINARIO I	   20	   01	  1-2007-3116N-15196976	 1-2008	        3	  2012  
 */
public void archivo_notas_txt(String nombre_archivo,String cedula, String carrera, String turno, String nucleo_extension, String inscrito  ,  LinkedList<String> orden, LinkedList<String> pensum){
   
   
   
    String matricula=null;
    Object [] extras = null;
    String contenido=null;
    
            matricula=this.opsu_matricula(inscrito,this.opsu_sede(nucleo_extension), this.opsu_carrera(carrera), this.opsu_turno(turno) , cedula);
            
System.out.println("PROCESANDO texto plano. ESTUDIANTE: "+cedula);

    
        System.out.println("NOTAS PARA GUARDAR EN EL TXT: "+orden.size());
          if(orden.size()<=0){System.out.println("Este alumno no tiene materia registrada");}else{
        System.out.println("......................................................ARCHIVO TXT.........................");
        
                   //PAGINA SIGUIENTE . RECORD ACADEMICO
        System.out.println("Analizando materias para crear archivo txt.");
            this.certificando_txt(orden);//MATERIAS CERTIFICADAS       
         //this.certificando_materias(orden);//MATERIAS CERTIFICADAS       
        System.out.println("Fin analisis certificadas.\nCREANDO EL CUERPO TXT.");
        System.out.println("Cédula de Identidad No.: "+cedula+"\n"+"Carrera: "+this.nombre_real_carrera(carrera,inscrito,2));
        
        materias m = new materias();
        
            
                 for(int z=0;z<confexion.size();z=z+4){//escribiendo las materias del alumno en el archivo pdf
                           
                          /*posicion = 0 //nota definitiva<br>
                            posicion = 1 //nombre de la materia<br>
                            posicion = 2 //condicion de la materia<br>
                            posicion = 3 //periodo academico*/
                           if(confexion.get(z+2)=="" || confexion.get(z+2)==" "){ 
                               //System.out.println("vacio");
                               contenido="01";   
                           }else{                                                             
                               //System.out.println("lleno");
                               contenido = m.condicion_materia_inversa(confexion.get(z+2)); 
                           }
                           System.out.println("NOMBRE DE LA MATERIA: "+confexion.get(z+1));
                           extras = this.informacion_extra(orden, pensum, confexion.get(z+1));
                           //System.out.println("condicion de la materia: "+confexion.get(z+2));
                              
                                    if(confexion.get(z+1)!=null && confexion.get(z+1).contains("ELECTIVA") ) extras[2]="3";//solo para la materias electivas se fija las unidades de credito a 3 ya que todas valen lo mismo
                                    
                           doc.escritor_texto_mejorado( nombre_archivo,
                                                        "CEDULA;CODCARRERA;CODOPCION;NTERMINO;MODALIDAD;CODMATERIA;NMATERIA;NOTA;CONDICION;CIM;PERIODOACA;UC;FEGRESO",
                                                        cedula+";"+ //cedula
                                                        this.opsu_carrera(carrera)+";"+ //codigo opsu carrera
                                                        this.opsu_sede(nucleo_extension)+this.opsu_carrera(carrera)+this.opsu_turno(turno)+";"+ //codigo de la opcion
                                                        extras[0]+";"+ //semestre de la materia
                                                        "02;"+ //modalidad. semestre
                                                        extras[1]+";"+ //codigo de la materia
                                                        confexion.get(z+1)+";"+ //nombre la de materia
                                                        confexion.get(z+0)+";"+ //nota definitiva
                                                        contenido+";"+ //condicion de la materia
                                                        matricula+";"+ //matricula del estudiante
                                                        this.efecto_intesivo_periodo(confexion.get(z+3))+";"+ //periodo academico de la materia donde fue vista
                                                        extras[2]+";"+ //unidades de credito de la materia
                                                        "2012"//año de egreso del estudiante                                                                        
                                                        );
                              

   
          /*                 textos_archivo.add(cedula);
                           textos_archivo.add(this.opsu_carrera(carrera));
                           textos_archivo.add(this.opsu_carrera(this.opsu_sede(nucleo_extension)+this.opsu_carrera(carrera)+this.opsu_turno(turno)));
                          
                           textos_archivo.add(extras[0].toString());
                           textos_archivo.add("02");
                           textos_archivo.add(extras[1].toString());
                           
                           textos_archivo.add(confexion.get(z+1));
                           textos_archivo.add(confexion.get(z+0));
                           textos_archivo.add(contenido);
                           
                           textos_archivo.add(matricula);
                           textos_archivo.add(confexion.get(z+3));
                           textos_archivo.add(extras[2].toString());
                           
                           textos_archivo.add("2012");
            */               
                           
                           
                           
                 }//fin del recorrido de las materias confexionadas
          }
          
        
        orden.clear();//limpiando materias del record
        pensum.clear();//limpiando el pensum
        
        System.out.println("INFORMACION DEL ESTUDIANTE "+cedula+" INCLUIDA");
        /*System.out.println("++++++++++++------------------++++++++++++++++++++++++++++++++++MUESTRAS "+cedula);
          for(int i=0; i<textos_archivo.size(); i=i+13){
              System.out.println(textos_archivo.get(i+0)+";"+
                                 textos_archivo.get(i+1)+";"+
                                 textos_archivo.get(i+2)+";"+
                                 textos_archivo.get(i+3)+";"+
                      textos_archivo.get(i+4)+";"+
                      textos_archivo.get(i+5)+";"+
                      textos_archivo.get(i+6)+";"+
                      textos_archivo.get(i+7)+";"+
                      textos_archivo.get(i+8)+";"+
.                      textos_archivo.get(i+9)+";"+
                      textos_archivo.get(i+10)+";"+
                      textos_archivo.get(i+11)+";"+
                      textos_archivo.get(i+12)+";"
                      );          
          }
          textos_archivo.clear();
          */
}

/**Este metodo solo da la informacion extra de semestre, codigo, unidades de credito de la materia  */
private Object[] informacion_extra(LinkedList<String> orden, LinkedList<String> pensum, String nombre_materia){
    int uc=0, semestre;
    String codigo=null;
    Object[] datos=null;
    //SOLO COMO LEYENDA
/*          this.getOrden().add(this.getAux2().get(s));//0  codigo de la materia
            this.getOrden().add(this.getAux2().get(s+1));//1  nombre de la materia
            this.getOrden().add(this.getAux2().get(s+2));//2  definitiva de la materia
            this.getOrden().add(this.getAux2().get(s+3));//3  nota de reparacion
            this.getOrden().add(this.getAux2().get(s+4));//4  condicion de la materia
            this.getOrden().add(this.getAux2().get(s+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAux2().get(s+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAux2().get(s+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAux2().get(s+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAux2().get(s+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAux2().get(s+10));//10  semestre de la materia*/
    
    //se toma del registro orden por las materias electivas y servicios comunitarios que en el pensum no aparecen
  //  System.out.println("INFORMACION MATERIA: "+nombre_materia); 19847241
    
      //  System.out.println("INFORMACION EXTRAS: "+nombre_materia);
            if(nombre_materia!=null){//si no es un archivo nulo 
                
                for(int i=0; i<orden.size(); i = i + 11){//recorriendo las materias del record ordenadas
                    
                    if(nombre_materia.equalsIgnoreCase(orden.get(i+1))){//si los nombres de las materias son coincidentes se toman los datos
                        datos = new Object[]{
                            orden.get(i+10),
                            orden.get(i+0),
                            String.valueOf(this.uc_materia(orden.get(i),orden.get(i+5) ,pensum))
                        }; 
                        break;
                    }
                    
                }//fin recorrido    
                    
            }else{
                //System.out.println("INFORMACION EXTRAS: "+nombre_materia);
                datos = new Object[]{
                            "NE",
                            "NE",
                            "NE"
                        }; 
                        
            
            }
    
    return datos;

}

private String sedes_principales(String nucleo_extension){
    String texto=null;    
    
    if(nucleo_extension.startsWith("NUCLEO ARAGUA")) texto = "NÚCLEO ARAGUA - SEDE MARACAY";
    if(nucleo_extension.startsWith("NUCLEO MERIDA")) texto = "NÚCLEO MÉRIDA - SEDE MÉRIDA";
    
    
return texto;
}
















public void s(Connection con){
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = (ResultSet) sentencia.executeQuery("SELECT * FROM pensum.electivas;");
            while (resultado.next()) {
                System.out.println("resultado : " + resultado.getString("asignatura"));
            }
        }
        /*
        public static void main(String args[]){
        registro_pdf rpdf = new registro_pdf();
        rpdf.analisis_certificadas(15, 0, 0, 0, "N", null, null);
        rpdf.analisis_certificadas(7, 13, 0, 0, "R", null, null);
        rpdf.analisis_certificadas(7, 9, 0, 0, "R", null, null);
        rpdf.analisis_certificadas(7, 0, 0, 0, "R", null, null);
        rpdf.analisis_certificadas(7, 12, 0, 0, "L", null, null);
        rpdf.analisis_certificadas(7, 6, 0, 0, "L", null, null);
        rpdf.analisis_certificadas(7, 6, 3, 0, "L", null, null);
        rpdf.analisis_certificadas(7, 11, 4, 0, "L", null, null);
        rpdf.analisis_certificadas(7, 12, 0, 0, "P", null, null);
        rpdf.analisis_certificadas(7, 6, 0, 0, "P", null, null);
        rpdf.analisis_certificadas(7, 6, 3, 0, "P", null, null);
        rpdf.analisis_certificadas(7, 11, 4, 0, "P", null, null);
        rpdf.analisis_certificadas(15, 0, 0, 25, "N", null, null);
        rpdf.analisis_certificadas(15, 0, 0, 50, "P", null, null);
        }
         */ catch (SQLException ex) {
            Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
        }


}







public static void main(String args[]){

   // encriptacion encriptar = new encriptacion();
 //   char c[] = encriptar.conversor("9477655", "SHA-1").toCharArray();
 registro_pdf rpdf = new registro_pdf();
 rpdf.lista_cinu(new conexion_base_de_datos().getConexion(),"2-2012","",true);
 
/*        try {
            rpdf.constancia_estudio_cinu(false, false, true, "d://cinu_constancia_9477655.pdf", "9477655", "JOSE CONTRERAS DE LA SANTISIMIMA TRINIDAD", "",
                    "", "NÚCLEO MÉRIDA - SEDE MÉRIDA","", "2-2012",
                    ""+c[2]+c[4]+c[6]+c[8]+c[10]+c[12]+c[14]+c[16]+c[18]+c[20]);
            
            //rpdf.s(new conexion_base_de_datos().getConexion());
           // rpdf.margenes();
           /* rpdf.analisis_certificadas(15, 0, 0, 0, "N", null, null);
            rpdf.analisis_certificadas(7, 13, 0, 0, "R", null, null);
            rpdf.analisis_certificadas(7, 9, 0, 0, "R", null, null);
            rpdf.analisis_certificadas(7, 0, 0, 0, "R", null, null);

            rpdf.analisis_certificadas(7, 12, 0, 0, "L", null, null);
            rpdf.analisis_certificadas(7, 6, 0, 0, "L", null, null);
            rpdf.analisis_certificadas(7, 6, 3, 0, "L", null, null);
            rpdf.analisis_certificadas(7, 11, 4, 0, "L", null, null);

            rpdf.analisis_certificadas(7, 12, 0, 0, "P", null, null);
            rpdf.analisis_certificadas(7, 6, 0, 0, "P", null, null);
            rpdf.analisis_certificadas(7, 6, 3, 0, "P", null, null);
            rpdf.analisis_certificadas(7, 11, 4, 0, "P", null, null);

            rpdf.analisis_certificadas(15, 0, 0, 25, "N", null, null);
            rpdf.analisis_certificadas(15, 0, 0, 50, "P", null, null);
           
        } catch (DocumentException ex) {
            Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(registro_pdf.class.getName()).log(Level.SEVERE, null, ex);
        }*/
}




}//fin de la clase
