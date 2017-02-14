/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.LinkedList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import vista.progreso;


/**
 *
 * @author AN
 */
public class registro_masivo extends Thread{

    
    private String tabla_estudiante=null;
    private registro_pdf rpdf;
    private registro_pdfs rpdfs;
    private materias materia;
    private registro_ingenieria reging;
    private materias2 materia2;
    private conexion_base_de_datos cbd;
    private imagenes ima;
    private Archivos doc;
    private reportes rep;
    private Gestor_Ruta gr;
    private RegistroNotasMasivas rnm;

    private String nombres_apellidos,turno,tipo_pensum,carreras,sede, materia_causal, periodo_causal;
    private String ruta_externa;
    private String avances, observacion;
    private boolean terminastes=false;

    

    private LinkedList<String> alumnos = new LinkedList<>();
    private LinkedList<Integer> cant_mat = new LinkedList<Integer>();
    private LinkedList<String> reprobadas = new LinkedList<>();
    private LinkedList<String> informe = new LinkedList<>();
    private LinkedList<String> comunitarios = new LinkedList<>();
    private LinkedList<String> est_x_carrera = new LinkedList<>();

    private LinkedList<String> est_y_mat = new LinkedList<>();
    private LinkedList<String> mat_y_cant = new LinkedList<>();



    public registro_masivo() {
        rnm = new RegistroNotasMasivas();
        rpdf = new registro_pdf();
        rpdfs = new registro_pdfs();
        materia = new materias();
        reging = new registro_ingenieria();
        materia2 = new materias2();
        cbd = new conexion_base_de_datos();
        ima = new imagenes();
        doc = new Archivos();
        rep = new reportes();
        gr = new Gestor_Ruta();
    }




    public LinkedList<String> getEst_y_mat() {
        return est_y_mat;
    }

    public void setEst_y_mat(LinkedList<String> est_y_mat) {
        this.est_y_mat = est_y_mat;
    }

    public LinkedList<String> getMat_y_cant() {
        return mat_y_cant;
    }

    public void setMat_y_cant(LinkedList<String> mat_y_cant) {
        this.mat_y_cant = mat_y_cant;
    }

    public String getRuta_externa() {
        return ruta_externa;
    }

    public void setRuta_externa(String ruta_externa) {
        this.ruta_externa = ruta_externa;
    }





    public LinkedList<String> getInforme() {
        return informe;
    }

    public void setInforme(LinkedList<String> informe) {
        this.informe = informe;
    }

    public LinkedList<String> getReprobadas() {
        return reprobadas;
    }

    public void setReprobadas(LinkedList<String> reprobadas) {
        this.reprobadas = reprobadas;
    }

    public LinkedList<Integer> getCant_mat() {
        return cant_mat;
    }

    public void setCant_mat(LinkedList<Integer> cant_mat) {
        this.cant_mat = cant_mat;
    }

    public LinkedList<String> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(LinkedList<String> alumnos) {
        this.alumnos = alumnos;
    }

    
    public String getTabla_estudiante() {
        return tabla_estudiante;
    }

    public void setTabla_estudiante(String tabla_estudiante) {
        this.tabla_estudiante = tabla_estudiante;
    }

    public String getNombres_apellidos() {
        return nombres_apellidos;
    }

    public void setNombres_apellidos(String nombres_apellidos) {
        this.nombres_apellidos = nombres_apellidos;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTipo_pensum() {
        return tipo_pensum;
    }

    public void setTipo_pensum(String tipo_pensum) {
        this.tipo_pensum = tipo_pensum;
    }

    public String getAvances() {
        return avances;
    }

    public void setAvances(String avances) {
        this.avances = avances;
    }

    public String getCarreras() {
        return carreras;
    }

    public void setCarreras(String carreras) {
        this.carreras = carreras;
    }

    public LinkedList<String> getComunitarios() {
        return comunitarios;
    }

    public void setComunitarios(LinkedList<String> comunitarios) {
        this.comunitarios = comunitarios;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public boolean isTerminastes() {
        return terminastes;
    }

    public void setTerminastes(boolean terminastes) {
        this.terminastes = terminastes;
    }

    public LinkedList<String> getEst_x_carrera() {
        return est_x_carrera;
    }

    public void setEst_x_carrera(LinkedList<String> est_x_carrera) {
        this.est_x_carrera = est_x_carrera;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }










public void inicializar_periodos(JComboBox desplegable){
    DefaultComboBoxModel dcbm = new DefaultComboBoxModel(ima.estimacion_periodo().toArray());    
    desplegable.setModel(dcbm);
}    
    
    
/**Este metodo determina el pensum y la carrera del estudiante que devuelve un arreglo en cadena
 de caracteres donde<br>
 * posicion 0 = pensum<br>
 * posicion 1 = carrera
 */
private String[] vigencia_alumno(Connection con, String cedula){
    Statement sentencia = null;
    ResultSet resultado = null;
    int pensum=0;
    String carrera=null;

    try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT pensum,carrera FROM control_de_estudio.datos_academicos WHERE cedula='"+cedula+"'; ");
            while(resultado.next()){            
             
             pensum = resultado.getInt("pensum");
             carrera = resultado.getString("carrera");
             System.out.println("Cedula: "+cedula+",  Pensum: "+resultado.getInt("pensum")+" Carrera: "+resultado.getString("carrera"));


            }




        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }


return new String[]{String.valueOf(pensum),carrera};
}


public void estudiantes_inscritos(Connection con, String carrera, String periodo){

    Statement sentencia = null;
    ResultSet resultado = null;

    
        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.jornada_inscripcion WHERE carrera='"+carrera+"' and periodo='"+periodo+"' GROUP BY cedula");
            while(resultado.next()){

             this.getAlumnos().add(resultado.getString("cedula"));
             this.getAlumnos().add(this.vigencia_alumno(new conexion_base_de_datos().getConexion(),resultado.getString("cedula"))[0]  );
             System.out.println("Elementos "+this.getAlumnos().size()/2);
            }




        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }



}


public void contador_uc_masivo(Connection con, String cedula){
        
    int contador=0;
            Statement sentencia = null;
            ResultSet r = null;


        try {
            sentencia = con.createStatement();
            r = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE cedula='"+cedula+"';");

            while(r.next()){
                contador=contador+r.getInt("uc");

            }

            if(contador>30)System.out.println("-------------Estudiante: "+cedula+" excede las UC: "+contador+"--------------------");


           sentencia.close();
           r.close();
           con.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }




}







public void lista_egresados(boolean txt, boolean record, boolean orden_merito ,Connection con, String carrera, String periodo_academico, boolean autorizacion, boolean culminacion, boolean certificadas , int ajuste, String periodo_lectivo){
         Statement sentencia = null;
     ResultSet resultado = null;
     LinkedList<String> cedulas = new LinkedList<>();
     String ruta = gr.guardar_archivo();
     String direccion = ruta+"UNEFA_EGRESADOS_"+periodo_lectivo.substring(2)+"_TXT.txt";
     doc.borrar_archivo(direccion);
     
      rpdf.getAnalisis_merito().clear();//limpiando para hacer tabla de merito
      this.setTerminastes(false);//inicializando la variable

      
                hilos hilo = new hilos("EGRESADOS: ",0,false, new progreso());    //iniciando hilo para la barra de progreso
                hilo.setInformacion_1("CARGANDO CÉDULAS DE ESTUDIANTES EGRESADOS DEL PERÍODO "+periodo_lectivo);
                hilo.start();
                
      

    try {
            sentencia = con.createStatement();
            
            //egresados
            periodo_lectivo=periodo_lectivo.replace("-", "/");
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE carrera='"+carrera+"' AND upi_periodo='"+periodo_lectivo+"';");//tipo_estudiante='Egresado';");//listando todos los estudiantes por cedula   
         
            while(resultado.next()){
                cedulas.add(resultado.getString("cedula"));//cargando las cedulas de interes
            }
            
            hilo.setInicio(0); hilo.setFin(cedulas.size());//estableciendo valores
            
                if(cedulas.size()<=0){
                    ima.mensaje_informacion("NO EXISTEN ESTUDIANTES PARA PROCESAR MASIVAMENTE", "INFORMACION", "exclamacion", "png");
                    hilo.setFin(0);//asi se termina el hilo igualando el inicio con el final
                
                }else{
                    for(int i=0; i<cedulas.size(); i++){//recorriendo cedula a cedula
                       hilo.setAvance(i);     
                       

                            if(certificadas || record){//solo record y notas certificadas
                                //INFORMACION DEL ESTUDIANTE
                                if(certificadas)hilo.setInformacion_1("NOTAS CERTIFICADAS: CEDULA:"+cedulas.get(i)+" ("+i+"/"+cedulas.size()+")");
                                else hilo.setInformacion_1("RECORD ACADÉMICO: CEDULA:"+cedulas.get(i)+" ("+i+"/"+cedulas.size()+")");
                                
                                    this.setObservacion("");//para que no aparezca el comentario que se puede hacer en el area de observacion
                                    // System.out.println("......PDF_MASIVOS_N°: "+resultado.getRow()+" - "+resultado.getString("cedula"));
                                    this.informacion(new conexion_base_de_datos().getConexion(),cedulas.get(i+0));
                                    this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedulas.get(i+0));//localizando a que vigencia pertenece
                                
                                //PROCESOS PARA EL ESTUDIANTE
                                    //record y certificadas
                                    this.pdf_masivos(ruta,con,cedulas.get(i+0), carrera,this.getSede(),false,record,false, ajuste, periodo_academico);//generando pdf por cada cedula de alumno inscrito de la jornada de inscripcion
                                    //rpdfs.constancia_de_culminacion(ruta + "constancia_de_culmincacion_" + cedulas.get(i+0) + ".pdf", cedulas.get(i+0), this.getNombres_apellidos().replaceAll(",", "").trim(), culminacion);
                                    this.masivo_notas_certificadas(ruta, new conexion_base_de_datos().getConexion(),cedulas.get(i+0), this.getCarreras(), this.getSede(), false, record, false, certificadas ,ajuste, periodo_academico);//generando pdf por cada cedula de alumno inscrito de la jornada de inscripcion
                            }
                            
                            if(txt){//generando el txt de los estudiantes
                                    hilo.setInformacion_1("TXT: CEDULA:"+cedulas.get(i)+" ("+i+"/"+cedulas.size()+")");
                                    
                                    this.informacion(new conexion_base_de_datos().getConexion(),cedulas.get(i) );
                                    this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedulas.get(i));//localizando a que vigencia pertenece
                                    
//                                    this.txt_carrera(new conexion_base_de_datos().getConexion(), ruta, periodo_lectivo,"Egresado");
                                    
                                    //System.out.println("direccion: "+direccion);
                                    rpdf.getAnalisis_merito().clear();//limpiando para hacer tabla de merito
                                    this.setTerminastes(false);//inicializando la variable
                                    
                                    this.masivo_notas_txt(direccion,
                                                  new conexion_base_de_datos().getConexion(),
                                                  cedulas.get(i),
                                                  this.getCarreras(),
                                                  this.getSede(),
                                                  periodo_lectivo
                                                  );
                                    this.setTerminastes(true);//inicializando la variable
                            }
                            
                            if(orden_merito){ //orden de merito
                                hilo.setInformacion_1("ORDEN DE MERITO: CEDULA:"+cedulas.get(i)+" ("+i+"/"+cedulas.size()+")");
                                    this.setRuta_externa(ruta);
                                    this.depurador_estudiantes_semestres(cedulas.get(i+0), carrera);//depurando estudiante por estudiante
                            }
                            
                            
                    }//fin recorrido cedulas
                }
                hilo.setAvance(hilo.getFin());//es para finalizar. se igualan los valores de avance con fin
                

            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
       
     
            rpdf.getReporte_indices().clear();//limpiando el LinkedList despues de usarlo
     
         this.setTerminastes(true);//termino toda la operacion masiva

        //rpdf.guardar_tabla_merito(new conexion_base_de_datos().getConexion(), rpdf.getAnalisis_merito());//TABLAS CON INFORMACION

      //System.out.println("RASTREANDO CAPACIDAD: "+rpdf.getEgresados().size());
    //  rep.reporte_notas_egresados("d://REPORTES/NOTAS_EGRESADOS/egresados_2011.csv",rpdf.getEgresados());//notas de todos los egresados



    
    

}


public void lista_inscritos_experimental(Connection con, String carrera, String periodo_academico){
     Statement sentencia = null;
     ResultSet resultado = null;
     LinkedList<String> cedulas = new LinkedList<>();
     String seleccion = ima.selector_generico("SELECCIONE CUALES ESTUDIANTES DESEA CONSIDERAR:\n   "
                                            , carrera, new Object[]{"TODA LA CARRERA","SOLO INSCRITOS DEL PERIODO: "+periodo_academico});
     
     
      rpdf.getAnalisis_merito().clear();//limpiando para hacer tabla de merito
      this.setTerminastes(false);//inicializando la variable

      
                hilos hilo = new hilos("DOCUMENTOS MASIVOS: ",0,false, new progreso());    //iniciando hilo para la barra de progreso
                hilo.setInformacion_1("CARGANDO CÉDULAS DE ESTUDIANTES EN EL PERÍODO "+periodo_academico);
                hilo.start();
                
    

    try {
            sentencia = con.createStatement();
            //
            if(seleccion.startsWith("TODA"))
                resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE carrera='"+carrera+"' GROUP BY cedula");//listando todos los estudiantes por cedula
            else
                resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.jornada_inscripcion WHERE carrera='"+carrera+"' AND periodo='"+periodo_academico+"' GROUP BY cedula");//listando todos los estudiantes por cedula
           
            while(resultado.next()){
                cedulas.add(resultado.getString("cedula"));//cargando las cedulas de interes
            }
            
            hilo.setInicio(0); hilo.setFin(cedulas.size());//estableciendo valores
            
                if(cedulas.size()<=0){
                    ima.mensaje_informacion("NO EXISTEN ESTUDIANTES PARA PROCESAR MASIVAMENTE", "INFORMACION", "exclamacion", "png");
                    hilo.setFin(0);//asi se termina el hilo igualando el inicio con el final
                }else{
                    for(int i=0; i<cedulas.size(); i++){//recorriendo cedula a cedula
                       hilo.setAvance(i);     
                       hilo.setInformacion_1("CEDULA: "+cedulas.get(i)+" ("+i+"/"+cedulas.size()+")");

                                    // System.out.println("......PDF_MASIVOS_N°: "+resultado.getRow()+" - "+resultado.getString("cedula"));
                                        this.informacion(new conexion_base_de_datos().getConexion(),cedulas.get(i+0));
                                        this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedulas.get(i+0));//localizando a que vigencia pertenece
                                        this.informacion_record_masiva(con, cedulas.get(i+0), carrera, this.getSede(), periodo_academico);
                                        
                
                    }//fin recorrido cedulas
                }
                
                hilo.setAvance(hilo.getFin());//es para finalizar. se igualan los valores de avance con fin
                

            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
       
     
            rpdf.getReporte_indices().clear();//limpiando el LinkedList despues de usarlo
     
         this.setTerminastes(true);//termino toda la operacion masiva

        //rpdf.guardar_tabla_merito(new conexion_base_de_datos().getConexion(), rpdf.getAnalisis_merito());//TABLAS CON INFORMACION

      System.out.println("RASTREANDO CAPACIDAD: "+rpdf.getEgresados().size());
    //  rep.reporte_notas_egresados("d://REPORTES/NOTAS_EGRESADOS/egresados_2011.csv",rpdf.getEgresados());//notas de todos los egresados




}


public void lista_inscritos(String ruta,boolean inscripcion, boolean record, boolean constancia,Connection con, String carrera, String periodo_academico, boolean autorizacion, boolean culminacion, boolean certificadas , int ajuste){
     Statement sentencia = null;
     ResultSet resultado = null;
     LinkedList<String> cedulas = new LinkedList<>();
     String seleccion = ima.selector_generico("SELECCIONE CUALES ESTUDIANTES DESEA CONSIDERAR:\n   "
                                            , carrera, new Object[]{"TODA LA CARRERA","SOLO INSCRITOS DEL PERIODO: "+periodo_academico});
     
     
      rpdf.getAnalisis_merito().clear();//limpiando para hacer tabla de merito
      this.setTerminastes(false);//inicializando la variable

      
                hilos hilo = new hilos("DOCUMENTOS MASIVOS: ",0,false, new progreso());    //iniciando hilo para la barra de progreso
                hilo.setInformacion_1("CARGANDO CÉDULAS DE ESTUDIANTES EN EL PERÍODO "+periodo_academico);
                hilo.start();
                
    

    try {
            sentencia = con.createStatement();
            //
            if(seleccion.startsWith("TODA"))
                resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE carrera='"+carrera+"' GROUP BY cedula");//listando todos los estudiantes por cedula
            else
                resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.jornada_inscripcion WHERE carrera='"+carrera+"' AND periodo='"+periodo_academico+"' GROUP BY cedula");//listando todos los estudiantes por cedula
            
           
            while(resultado.next()){
                cedulas.add(resultado.getString("cedula"));//cargando las cedulas de interes
            }
            
            hilo.setInicio(0); hilo.setFin(cedulas.size());//estableciendo valores
            
                if(cedulas.size()<=0){
                    ima.mensaje_informacion("NO EXISTEN ESTUDIANTES PARA PROCESAR MASIVAMENTE", "INFORMACION", "exclamacion", "png");
                    hilo.setFin(0);//asi se termina el hilo igualando el inicio con el final
                }else{
                    for(int i=96; i<cedulas.size(); i++){//recorriendo cedula a cedula
                       hilo.setAvance(i);     
                       hilo.setInformacion_1("CEDULA: "+cedulas.get(i)+" ("+i+"/"+cedulas.size()+")");

                            if(autorizacion ){//autoriza realizar los metodos mas profundos

                                System.out.println("CEDULA: "+cedulas.get(i+0));

                                this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedulas.get(i+0));//localizando a que vigencia pertenece
                                this.estudiante_materias_inscritas(new conexion_base_de_datos().getConexion(),cedulas.get(i+0),periodo_academico);//cargando las materias inscritas

                            }else{
                                    // System.out.println("......PDF_MASIVOS_N°: "+resultado.getRow()+" - "+resultado.getString("cedula"));
                                        this.informacion(new conexion_base_de_datos().getConexion(),cedulas.get(i+0));
                                        this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedulas.get(i+0));//localizando a que vigencia pertenece
                                        this.pdf_masivos(ruta,con,cedulas.get(i+0), carrera,this.getSede(),inscripcion,record,constancia, ajuste, periodo_academico);//generando pdf por cada cedula de alumno inscrito de la jornada de inscripcion
                                        
                                        
                                    
                                    rpdfs.constancia_de_culminacion(ruta + "constancia_de_culmincacion_" + cedulas.get(i+0) + ".pdf", cedulas.get(i+0), this.getNombres_apellidos().replaceAll(",", "").trim(), culminacion);
                                    this.masivo_notas_certificadas(ruta, new conexion_base_de_datos().getConexion(),cedulas.get(i+0), this.getCarreras(), this.getSede(), inscripcion, record, constancia, certificadas ,ajuste, periodo_academico);//generando pdf por cada cedula de alumno inscrito de la jornada de inscripcion
                            }

                    }//fin recorrido cedulas
                }
                hilo.setAvance(hilo.getFin());//es para finalizar. se igualan los valores de avance con fin
                

            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
       
     
            rpdf.getReporte_indices().clear();//limpiando el LinkedList despues de usarlo
     
         this.setTerminastes(true);//termino toda la operacion masiva

        //rpdf.guardar_tabla_merito(new conexion_base_de_datos().getConexion(), rpdf.getAnalisis_merito());//TABLAS CON INFORMACION

      System.out.println("RASTREANDO CAPACIDAD: "+rpdf.getEgresados().size());
    //  rep.reporte_notas_egresados("d://REPORTES/NOTAS_EGRESADOS/egresados_2011.csv",rpdf.getEgresados());//notas de todos los egresados




}

public void estudiante_materias_inscritas(Connection con, String cedula, String periodo){
        Statement sentencia = null;
        ResultSet resultado = null;

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE cedula='"+cedula+"' AND periodo='"+periodo+"' GROUP BY materia;");
            while(resultado.next()){//enviando las materias inscritas al record

                //System.out.println(resultado.getString("cedula")+" "+
                  //                 resultado.getString("carrera")+" "+
                    //               resultado.getInt("semestre")+" "+
                      //             resultado.getString("codigo")+" "+
                        //           this.anilisis_especial(resultado.getString("codigo"), resultado.getInt("uc"))+" "+
                          //         resultado.getString("seccion")+" "+
                            //       resultado.getString("periodo")+" "+
                              //     resultado.getString("condicion")+" "+
                                //   resultado.getString("dia_inscripcion")
                       // );


                        this.envio_materias(new conexion_base_de_datos().getConexion(),
                                            this.getTabla_estudiante(),
                                            resultado.getString("cedula"),
                                            resultado.getString("codigo"),
                                            resultado.getString("seccion"),
                                            Integer.valueOf(resultado.getInt("semestre")),
                                            resultado.getString("periodo"),
                                            resultado.getString("condicion"),
                                            this.anilisis_especial(resultado.getString("codigo"), resultado.getInt("uc")),
                                            resultado.getString("carrera"),
                                            resultado.getString("dia_inscripcion"));






            }


            sentencia.close();
            resultado.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }







}
/**Metodo que permite detectar alguna materia que sea instruccion militar o actividades complementarias para
 colocar sus unidades de credito a 0 en caso contrario se mantiene las uc de la materia original*/
public int anilisis_especial(String codigo, int uc){

    if(codigo.startsWith("IMI") || codigo.startsWith("ACO")){
      //  System.out.println("detectado...!!!");
        uc=0;
    }else{}
    
return uc;
}


public void envio_materias(Connection con, String tabla, String cedula, String codigo, String seccion, int semestre, String periodo, String condicion, int uc, String especialidad, String fecha_hora){
         Statement sentencia = null;


        try {

            sentencia = con.createStatement();
            sentencia.executeUpdate("INSERT INTO "+tabla+" VALUES(NULL,'"+cedula+"','"+codigo+"','','"+seccion+"',"+semestre+",'"+periodo+"','"+condicion+"',0,0,0,0,"+Integer.valueOf(rpdf.opsu_carrera(especialidad))+","+uc+",0,'inscribiendo materia','"+fecha_hora+"','',0);");


            sentencia.close();
            con.close();
           // System.out.println("Materia: "+codigo+" Enviado....!");

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
}


public void localizando_estudiante(Connection con, String cedula){
        boolean encontrado=false;
        String tabla=null;
        this.setTabla_estudiante("");
        //System.out.println("CEDULA: "+cedula);


        Statement sentencia = null;
        ResultSet resultado = null;

        for(int opcion=0; opcion<=3;opcion++){//recorriendo las tablas
            encontrado=false;

        switch(opcion){
             case 0:
                tabla="control_de_estudio.cem_notas_alumnos_todas_carreras_2010";
             break;

             case 1:
                tabla="control_de_estudio.cem_notas_alumnos_todas_carreras_2009";
             break;

             case 2:
                tabla="control_de_estudio.cem_notas_alumnos_todas_carreras_2007";
             break;
            default:
                tabla="NO HALLADO";
                System.out.println("NO SE HALLO MATERIAS REGISTRADAS DEL ESTUDIANTE");     

        }


        if(tabla.equalsIgnoreCase("NO HALLADO")){//es un nuevo
            System.out.println("ES UN NUEVO, tabla: "+tabla);
            this.setTabla_estudiante("control_de_estudio.cem_notas_alumnos_todas_carreras_2010");
           break;
        }else{

                        try {

                            sentencia = con.createStatement();
                            resultado = sentencia.executeQuery("SELECT CEDULA FROM "+tabla+" WHERE CEDULA='"+cedula+"';");
                            while(resultado.next()){
                               System.out.println("Estudiante encontrado en la tabla "+tabla);
                                this.setTabla_estudiante(tabla);

                                encontrado=true;

                                sentencia.close();
                                resultado.close();
                                con.close();

                                break;
                            }


                        } catch (SQLException ex) {
                            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
                        }

        }

        if(encontrado)break;

    }//fin recorrido



System.out.println("TABLA MAPEO: "+this.getTabla_estudiante());

}



public void vigencia_actualizados(Connection con,String nucleo, int tipo, String cedula){
         Statement sentencia = null;
         ResultSet resultado = null;
         LinkedList<String> cedulas = new LinkedList<>();
         hilos h = new hilos("VIGENCIAS MASIVAS", 0, false, new progreso());
         h.start();
         h.setInformacion_1("RECOLECTANDO CEDULAS");
         h.setInicio(0);

        try {

            sentencia = con.createStatement();

                if(tipo>=1){//es masivo
                    resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE nucleo='"+nucleo+"'");
                            while(resultado.next()){
                                cedulas.add(resultado.getString("cedula"));//almacenando las cedulas de interes                                
                            }
                          sentencia.close();
                          resultado.close();
                          
                          h.setFin(cedulas.size());//estableciendo el fin
                          for(int i=0; i<cedulas.size(); i++){//recorriendo todas las cedulas almacenadas
                              h.setAvance(i); h.setInformacion_1("PROCESANDO: "+cedulas.get(i)+"  ---- ("+i+"/"+cedulas.size()+")");
                                this.localizando_estudiante(new conexion_base_de_datos().getConexion(), cedulas.get(i));
                                this.vigencias_estudiantes(new conexion_base_de_datos().getConexion(), cedulas.get(i));                              
                                this.setAvances(cedulas.get(i));//reportando                                                          
                          }//fin recorrido de las cedulas almacenadas
                          h.setAvance(h.getFin());//se termina el hilo
                          
                    }else{//es individual
                            this.localizando_estudiante(new conexion_base_de_datos().getConexion(), cedula);
                            this.vigencias_estudiantes(new conexion_base_de_datos().getConexion(),cedula);
                            this.setAvances(cedula);//reportando
                    }
                


             
             

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




}

public void vigencias_estudiantes(Connection con, String cedula){
        try {
            Statement sentencia = null;
            sentencia = con.createStatement();
            
            
            if (this.getTabla_estudiante().endsWith("2007")) {
                System.out.println("cedula: " + cedula + " vigencia: 2007");
                sentencia.execute("UPDATE control_de_estudio.datos_academicos SET pensum = 2007 WHERE cedula='"+cedula+"'");
            }
            if (this.getTabla_estudiante().endsWith("2009")) {
                System.out.println("cedula: " + cedula + " vigencia: 2009");
                sentencia.execute("UPDATE control_de_estudio.datos_academicos SET pensum = 2009 WHERE cedula='"+cedula+"'");
            }
            if (this.getTabla_estudiante().endsWith("2010")) {
                System.out.println("cedula: " + cedula + " vigencia: 2010");
                sentencia.execute("UPDATE control_de_estudio.datos_academicos SET pensum = 2010 WHERE cedula='"+cedula+"'");
            }

                sentencia.close();
                con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }

}


public void informacion(Connection con, String cedula){
        Statement sentencia = null;
        ResultSet resultado = null;

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT nombres, apellidos FROM control_de_estudio.datos_personales WHERE cedula='"+cedula+"';");
            while(resultado.next()){
              //  this.setNombres_apellidos(resultado.getString("nombres")+", "+resultado.getString("apellidos"));
                this.setNombres_apellidos(resultado.getString("apellidos")+", "+resultado.getString("nombres"));
            
            }
            sentencia.close();
            resultado.close();

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT turno,pensum,carrera,nucleo FROM control_de_estudio.datos_academicos WHERE cedula='"+cedula+"';");
            while(resultado.next()){
                this.setTurno(resultado.getString("turno"));
                this.setTipo_pensum(String.valueOf(resultado.getInt("pensum")));
                this.setCarreras(resultado.getString("carrera"));
                this.setSede(resultado.getString("nucleo"));
                System.out.println("fuente "+resultado.getString("nucleo"));

            }
            sentencia.close();
            resultado.close();




            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }



}

public void informacion_record_masiva(Connection con, String cedula,String carrera,String nucleo_extension, String periodo){

  

    rpdf.todos_estudiantes(con, cedula);//buscando ingreso, mes y año
    
            //PREPARATIVOS PARA LOS CALCULOS POSTERIORES DEL RECORD ACADEMICO

                if(this.getTipo_pensum().equalsIgnoreCase("2010")){materia.setSeleccion_pensum(0); materia.setNuevo_ingreso(0);}//activando los pensum segun la carrera y otras caracteristicas
                if(this.getTipo_pensum().equalsIgnoreCase("2009")){materia.setSeleccion_pensum(1); materia.setNuevo_ingreso(0);}
                if(this.getTipo_pensum().equalsIgnoreCase("2007")){materia.setSeleccion_pensum(2); materia.setNuevo_ingreso(0);}
                
                materia.materias_pensum(new conexion_base_de_datos().getConexion(), carrera, this.getTurno());//cargando el pensum
              
                reging.buscar_materias_alternativo(new conexion_base_de_datos().getConexion(), cedula, this.getTabla_estudiante().substring("control_de_estudio.".length()));//ubicando las materias del estudiante por orden de aparicion(desordenada)

                for(int b=0; b<reging.getRecord().size();b=b+11){
                                               materia.ordenamiento_año(reging.getRecord().get(b+5));
                                              }

                materia.ordenamiento_materias_alternativos(reging);//ordenando materia
                System.out.println("ORDEN: "+materia.getOrden().size());
                
            //FIN PREPARATIVOS

    rpdf.calculo_alternativo(materia.getOrden(), materia.getPensum(), materia.getElectivas()); //indice, uc, equivalencias
    rpdf.calculo_orden_merito(materia.getOrden(), materia.getPensum(), materia.getElectivas()); // calcula el orden de merito del estudiante
      
    materia.pdf_materias_inscritas(new conexion_base_de_datos().getConexion(), cedula, periodo, false);
    rpdf.setObservacion(this.getObservacion());
    
    rnm.record_academico_texto(cedula, this.getNombres_apellidos(), carrera, this.getTurno(), nucleo_extension, rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), periodo);
                
}


public void pdf_masivos(String ruta, Connection con, String cedula,String carrera,String nucleo_extension ,boolean inscripcion, boolean record, boolean constancia, int ajuste, String periodo){

  

    rpdf.todos_estudiantes(con, cedula);//buscando ingreso, mes y año
    
            //PREPARATIVOS PARA LOS CALCULOS POSTERIORES DEL RECORD ACADEMICO

                if(this.getTipo_pensum().equalsIgnoreCase("2010")){materia.setSeleccion_pensum(0); materia.setNuevo_ingreso(0);}//activando los pensum segun la carrera y otras caracteristicas
                if(this.getTipo_pensum().equalsIgnoreCase("2009")){materia.setSeleccion_pensum(1); materia.setNuevo_ingreso(0);}
                if(this.getTipo_pensum().equalsIgnoreCase("2007")){materia.setSeleccion_pensum(2); materia.setNuevo_ingreso(0);}
                
                //materia.materias_pensum(new conexion_base_de_datos().getConexion(),materia.carrera(carrera, this.getTurno()));//cargando el pensum
                materia.materias_pensum(new conexion_base_de_datos().getConexion(), carrera, this.getTurno());//cargando el pensum
              //  System.out.println("verificando tabla: "+this.getTabla_estudiante().substring(new String("control_de_estudio.").length()));

           //     reging.buscar_materias(new conexion_base_de_datos().getConexion(), cedula, this.getTabla_estudiante().substring("control_de_estudio.".length()));//ubicando las materias del estudiante por orden de aparicion(desordenada)
                reging.buscar_materias_alternativo(new conexion_base_de_datos().getConexion(), cedula, this.getTabla_estudiante().substring("control_de_estudio.".length()));//ubicando las materias del estudiante por orden de aparicion(desordenada)


    //if(reging.getCodigo().size()>0){//experimental solo temporal-------------------------------------
   // if(reging.getRecord().size()>0){//experimental solo temporal-------------------------------------

                //for(int b=0; b<reging.getCodigo().size();b++){
                for(int b=0; b<reging.getRecord().size();b=b+11){
                                             // materia.ordenamiento_año(reging.getPeriodo().get(b));
                                               materia.ordenamiento_año(reging.getRecord().get(b+5));
                                              }

               // materia.ordenamiento_materias(reging);//ordenando materia
                materia.ordenamiento_materias_alternativos(reging);//ordenando materia
                System.out.println("ORDEN: "+materia.getOrden().size());
                
            //FIN PREPARATIVOS

    //rpdf.calculos(materia.getOrden(), materia.getPensum());//calculando las UC
    rpdf.calculo_alternativo(materia.getOrden(), materia.getPensum(), materia.getElectivas()); //indice, uc, equivalencias
    rpdf.calculo_orden_merito(materia.getOrden(), materia.getPensum(), materia.getElectivas()); // calcula el orden de merito del estudiante

      
    materia.pdf_materias_inscritas(new conexion_base_de_datos().getConexion(), cedula, periodo, false);
    rpdf.setObservacion(this.getObservacion());

    try {
           if(inscripcion){//seleccionando o la constancia de inscripcion o el record con constancia
                rpdf.record_academico_alternativo(ruta +"Constancia_Inscripcion_"+cedula + ".pdf", cedula, this.getNombres_apellidos(), carrera, this.getTurno(), nucleo_extension, rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), periodo);
                //rpdf.record_academico_alternativo(ruta + cedula + ".pdf", cedula, this.getNombres_apellidos(), carrera, this.getTurno(), nucleo_extension, rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), "ING. MÓNICA REYES","TCNEL (EJBV) PABLO JOSÉ BRAVO PARRA");
           }else{                                                                  try {
                    //temporal uc
                //rpdf.record_academico(inscripcion,record,constancia,ruta +"Record_Academico_"+cedula + ".pdf", cedula, this.getNombres_apellidos(), carrera, this.getTurno(), nucleo_extension, rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), ajuste);
               rpdf.record_academico(inscripcion,record,constancia,ruta, cedula, this.getNombres_apellidos(), carrera, this.getTurno(), nucleo_extension, rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), ajuste, periodo);
// rpdf.record_academico("d://documentos/" + cedula + ".pdf", cedula, this.getNombres_apellidos(), carrera, this.getTurno(), "NUCLEO ARAGUA - EXTENSION CAGUA", rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), "ING. MONICA A. REYES M.","LIC(MSc). ANA FERREIRA");
                } catch (BadElementException ex) {
                    Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
        } catch (DocumentException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }


  //  } else{System.out.println("--------------------NO TIENE MATERIAS PARA LLENAR EL RECORD----------------------------");}//fin experimental----------------------------------------


                
}





public void masivo_notas_certificadas(String ruta, Connection con , String cedula, String carrera, String nucleo_extension ,boolean inscripcion, boolean record, boolean constancia,boolean certificadas ,int ajuste, String periodo){

if(certificadas){//autorizando las notas certificadas

    rpdf.todos_estudiantes(con, cedula);//buscando ingreso, mes y año

            //PREPARATIVOS PARA LOS CALCULOS POSTERIORES DEL RECORD ACADEMICO

                if(this.getTipo_pensum().equalsIgnoreCase("2010")){materia.setSeleccion_pensum(0); materia.setNuevo_ingreso(0);}//activando los pensum segun la carrera y otras caracteristicas
                if(this.getTipo_pensum().equalsIgnoreCase("2009")){materia.setSeleccion_pensum(1); materia.setNuevo_ingreso(0);}
                if(this.getTipo_pensum().equalsIgnoreCase("2007")){materia.setSeleccion_pensum(2); materia.setNuevo_ingreso(0);}

                //materia.materias_pensum(new conexion_base_de_datos().getConexion(),materia.carrera(carrera, this.getTurno()));//cargando el pensum
                materia.materias_pensum(new conexion_base_de_datos().getConexion(),carrera, this.getTurno());//cargando el pensum
              //  System.out.println("verificando tabla: "+this.getTabla_estudiante().substring(new String("control_de_estudio.").length()));

                reging.buscar_materias_alternativo(new conexion_base_de_datos().getConexion(), cedula, this.getTabla_estudiante().substring("control_de_estudio.".length()));//ubicando las materias del estudiante por orden de aparicion(desordenada)


    if(reging.getRecord().size()>0){//experimental solo temporal-------------------------------------

                for(int b=0; b<reging.getRecord().size();b=b+11){
                                              materia.ordenamiento_año(reging.getRecord().get(b+5));//periodo en que vio la materia
                                              }

                //materia.ordenamiento_materias(reging);//ordenando materia
                materia.getOrden().clear();//limpiando el LinkedList de las materias antes de utilizarlo
                materia.ordenamiento_materias_alternativos(reging);//ordenando materia con otro tipo de LinkedList mas completo

            //FIN PREPARATIVOS

    //rpdf.calculos(materia.getOrden(), materia.getPensum());//calculando las UC
    //rpdf.calculo_alternativo(materia.getOrden(), materia.getPensum()); //indice, uc, equivalencias
    //rpdf.calculo_orden_merito(materia.getOrden(), materia.getPensum()); // calcula el orden de merito del estudiante


    materia.pdf_materias_inscritas(new conexion_base_de_datos().getConexion(), cedula, periodo, false);

                  /*
                        for(int n=0; n<materia.getOrden().size(); n=n+10){
                            System.out.println("codigo - "+materia.getOrden().get(n));
                            System.out.println("nombre - "+materia.getOrden().get(n+1));
                            System.out.println("definitiva - "+materia.getOrden().get(n+2));
                            System.out.println("reparacion - "+materia.getOrden().get(n+3));
                            System.out.println("condicion - "+materia.getOrden().get(n+4));
                            System.out.println("periodo - "+materia.getOrden().get(n+5));
                            System.out.println("defrep - "+materia.getOrden().get(n+6));
                            System.out.println("inasistencia - "+materia.getOrden().get(n+7));
                            System.out.println("codigo - "+materia.getOrden().get(n+8));
                            System.out.println("notlab - "+materia.getOrden().get(n+9));
                           }
*/


    try {
           if(inscripcion){//seleccionando o la constancia de inscripcion o el record con constancia   
               rpdf.notas_certificadas(true,true,ruta +"Notas_Certificadas_"+cedula +".pdf", cedula, this.getNombres_apellidos(), carrera, this.getTurno(), nucleo_extension, rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), "TCNEL(AVB) JOSÉ ARGENIS IRIARTE","CN. JORGE ELEAZAR GUTIERREZ GONZÁLEZ", ajuste);
           }else{ 
               rpdf.notas_certificadas(true,true,ruta +"Notas_Certificadas_"+cedula +".pdf", cedula, this.getNombres_apellidos(), carrera, this.getTurno(), nucleo_extension, rpdf.getPeriodo_ingreso(), materia.getPdf_mi(), materia.getOrden(), materia.getPensum(), "TCNEL(AVB) JOSÉ ARGENIS IRIARTE","CN. JORGE ELEAZAR GUTIERREZ GONZÁLEZ", ajuste);            
           }
        } catch (DocumentException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }


    } //fin experimental----------------------------------------

    else{System.out.println("--------------------NO TIENE MATERIAS PARA LLENAR NOTAS CERTIFICADAS----------------------------");}


}//fin autorizacion certificadas
}






private void txt_generador(LinkedList<String> cedula, String periodo_academico ,String direccion){
    
                hilos hilo = new hilos("GENERANDO TXT GRADUANDOS",0,false, new progreso());    //iniciando hilo para la barra de progreso
                hilo.setInformacion_1("CARGANDO CÉDULAS DE ESTUDIANTES GRADUANDOS EN EL PERÍODO "+periodo_academico);
                hilo.start();
                
                hilo.setInicio(0); hilo.setFin(cedula.size());//estableciendo valores
                
    for(int i=0; i<cedula.size(); i++){
        hilo.setAvance(i);//colocando el avance
        hilo.setInformacion_1(cedula.get(i)+"  ---("+i+" / "+cedula.size()+")  "+this.getCarreras());//mostrando informacion de procesamiento
        
                            this.informacion(new conexion_base_de_datos().getConexion(),cedula.get(i) );
                            this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedula.get(i));//localizando a que vigencia pertenece
                           // System.out.println("direccion: "+ruta+"UNEFA_EGRESADOS_"+periodo_academico.substring(2)+"_TXT.txt");
                            this.masivo_notas_txt(direccion,
                                                  new conexion_base_de_datos().getConexion(),
                                                  cedula.get(i),
                                                  this.getCarreras(),
                                                  this.getSede(),
                                                  periodo_academico
                                                  );
    }
    
    hilo.setAvance(hilo.getFin());//es para finalizar. se igualan los valores de avance con fin
}


public void txt_carrera_generalizado(Connection con,String ruta , String periodo_academico, String tipo_estudiante){
    LinkedList<String> cedulas_estudiantes = new  LinkedList<>();
     Statement sentencia = null;
     ResultSet resultado = null;
     String periodo=periodo_academico.replace("-", "/");
     String direccion = ruta+"UNEFA_ESTUDIANTES_"+periodo_academico.substring(2)+"_TXT.txt";
             
      rpdf.getAnalisis_merito().clear();//limpiando para hacer tabla de merito
      this.setTerminastes(false);//inicializando la variable
      doc.borrar_archivo(direccion);

    try {
            System.out.println("Solicitando > "+periodo+" - "+tipo_estudiante); 
            sentencia = con.createStatement();            
            //resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE tipo_estudiante='"+tipo_estudiante+"' ORDER BY carrera, cedula;");//listando todos los estudiantes por cedula   
           
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.jornada_inscripcion WHERE periodo = '"+periodo_academico+"' GROUP BY cedula ORDER BY carrera, cedula;");//listando todos los estudiantes por cedula   
            
            while(resultado.next()){//recorriendo cedula a cedula     
               
                           cedulas_estudiantes.add(resultado.getString("cedula"));
            }

            System.out.println("Cedulas Recolectadas: "+cedulas_estudiantes.size());
            
            sentencia.close();
            resultado.close();
            con.close();
            
            this.txt_generador(cedulas_estudiantes, periodo_academico, direccion);
            
        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    
   
        
    
      this.setTerminastes(true);//termino toda la operacion masiva

}

public void txt_carrera(Connection con,String ruta , String periodo_academico, String tipo_estudiante){
    LinkedList<String> cedulas_estudiantes = new  LinkedList<>();
     Statement sentencia = null;
     ResultSet resultado = null;
     String periodo=periodo_academico.replace("-", "/");
     String direccion = ruta+"UNEFA_EGRESADOS_"+periodo_academico.substring(2)+"_TXT.txt";
             
      rpdf.getAnalisis_merito().clear();//limpiando para hacer tabla de merito
      this.setTerminastes(false);//inicializando la variable
      doc.borrar_archivo(direccion);

    try {
            System.out.println("Solicitando > "+periodo+" - "+tipo_estudiante); 
            sentencia = con.createStatement();            
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE upi_periodo='"+periodo+"' AND tipo_estudiante='"+tipo_estudiante+"' ORDER BY carrera, cedula;");//listando todos los estudiantes por cedula   
           // resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE upi_periodo='"+periodo+"' AND tipo_estudiante='"+tipo_estudiante+"' AND carrera='LIC. EN ADMINISTRACION DE DESASTRE' ORDER BY carrera, cedula;");//listando todos los estudiantes por cedula   
           //resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE upi_periodo='"+periodo+"' AND tipo_estudiante='esperando_2007' ORDER BY cedula;");//desastre
           //resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE upi_periodo='"+periodo+"' AND tipo_estudiante='esperando' ORDER BY cedula;");//desastre
            //resultado.next();
            
            while(resultado.next()){//recorriendo cedula a cedula     
               
                           cedulas_estudiantes.add(resultado.getString("cedula"));
            }

            System.out.println("Cedulas Recolectadas: "+cedulas_estudiantes.size());
            
            sentencia.close();
            resultado.close();
            con.close();
            
            this.txt_generador(cedulas_estudiantes, periodo_academico, direccion);
            
        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    
   
        
    
      this.setTerminastes(true);//termino toda la operacion masiva

}



private void masivo_notas_txt(String ruta, Connection con , String cedula, String carrera, String nucleo_extension , String periodo){

    //rpdf.todos_estudiantes(con, cedula);//buscando ingreso, mes y año

            //PREPARATIVOS PARA LOS CALCULOS POSTERIORES DEL RECORD ACADEMICO

                if(this.getTipo_pensum().equalsIgnoreCase("2010")){materia.setSeleccion_pensum(0); materia.setNuevo_ingreso(0);}//activando los pensum segun la carrera y otras caracteristicas
                if(this.getTipo_pensum().equalsIgnoreCase("2009")){materia.setSeleccion_pensum(1); materia.setNuevo_ingreso(0);}
                if(this.getTipo_pensum().equalsIgnoreCase("2007")){materia.setSeleccion_pensum(2); materia.setNuevo_ingreso(0);}
                
                materia.materias_pensum(new conexion_base_de_datos().getConexion(),carrera, this.getTurno());//cargando el pensum
                reging.buscar_materias_alternativo(new conexion_base_de_datos().getConexion(), cedula, this.getTabla_estudiante().substring("control_de_estudio.".length()));//ubicando las materias del estudiante por orden de aparicion(desordenada)

    if(reging.getRecord().size()>0){//experimental solo temporal-------------------------------------

                for(int b=0; b<reging.getRecord().size();b=b+11){
                                              materia.ordenamiento_año(reging.getRecord().get(b+5));//periodo en que vio la materia
                                              }

                //materia.ordenamiento_materias(reging);//ordenando materia
                materia.getOrden().clear();//limpiando el LinkedList de las materias antes de utilizarlo
                materia.ordenamiento_materias_alternativos(reging);//ordenando materia con otro tipo de LinkedList mas completo

            //FIN PREPARATIVOS

             //  materia.pdf_materias_inscritas(new conexion_base_de_datos().getConexion(), cedula, periodo, false); ??????????????
               rpdf.todos_estudiantes(new conexion_base_de_datos().getConexion(), cedula);
               rpdf.archivo_notas_txt(ruta ,
                                      cedula ,
                                      carrera,                                      
                                      this.getTurno(),
                                      nucleo_extension,
                                      rpdf.getPeriodo_ingreso(),
                                      materia.getOrden(),
                                      materia.getPensum() );
    
    } //fin experimental----------------------------------------

    else{System.out.println("--------------------NO TIENE MATERIAS PARA LLENAR TXT----------------------------");}



}


public void inactivando(Connection con, String cedula){
      Statement sentencia = null;
      ResultSet resultado = null;
      boolean existe=false;
    
        try {
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.todos_alumnos WHERE cedula='"+cedula+"';");
                while(resultado.next()){
                    existe=true;
                }
                
            
            if(existe){//solo se actualiza el registro
                sentencia.executeUpdate("UPDATE control_de_estudio.todos_alumnos SET activo='NO' WHERE cedula='"+cedula+"';");
                System.out.println("ACTUALIZANDO INACTIVO");
            
            }else{//se inserta un nuevo registro
                sentencia.executeUpdate("INSERT INTO control_de_estudio.todos_alumnos VALUES (NULL,'"+cedula+"','NO');");
                System.out.println("INGRESANDO NUEVO INACTIVO");
            }
            
            
            
            
            sentencia.close();
            resultado.close();
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}


public void almacenando_cancelados(Connection con, String cedula, String causal, String periodo, String usuario){
      Statement sentencia = null;
      
        try {

            sentencia = con.createStatement();
            sentencia.execute("DELETE FROM control_de_estudio.cancelacion_retiros WHERE cedula='"+cedula+"';");
            sentencia.close();    
            
            sentencia = con.createStatement();
            sentencia.executeUpdate("INSERT INTO control_de_estudio.cancelacion_retiros VALUES (NULL,'"+cedula+"', '"+causal+"', '"+periodo+"', '"+usuario+"', DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r') );");
            System.out.println("INGRESANDO NUEVO CANCELADO: "+cedula);         
            sentencia.close();
            
            
      
        } catch (SQLException ex) {
            ima.mensaje_informacion("Hubo un error en la copia del registro de cancelados en la bd."+ex.getMessage(), "ERROR ALMACENAMIENTO CANCELADO", "error", "png");
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
}


/**Genera la lista de todos los estudiantes cancelados por carrerra de acuerdo a los criterios de cancelacion
 basado en numeros de periodos reprobados y el numero de veces de la misma materia reprobada. Si algunos de los parametros es cero
 es desactivado ese requerimiento de cancelacion*/
public void lista_cancelados(int maximo_periodos_reprobados, int maximas_materias_reprobadas, String cedula, String vigencia, String carrera, String direccion, String periodo_academico){

    //NOTa: hay que hacer que la lista de cancelado realmente escriba en la base de datos que es realmente
    
    if(direccion!=null){//se selecciono el directorio asi que se puede guardar la información
            if(this.periodos_reprobados(new conexion_base_de_datos().getConexion(), cedula, vigencia, maximo_periodos_reprobados)){

                System.out.println("Estudiante "+cedula+", se encuentra CANCELADO!!!");
                //doc.escritor_textos("d://CANCELADOS/"+carrera+"_cancelados_"+vigencia+".txt", cedula+"; PERIODOS REPROBADOS");
                doc.escritor_textos(direccion+carrera+"_cancelados_"+vigencia+".csv", cedula+"; PERIODOS REPROBADOS");
  //this.inactivando(new conexion_base_de_datos().getConexion(), cedula);
                this.almacenando_cancelados(new conexion_base_de_datos().getConexion(), cedula,this.periodo_causal ,periodo_academico,"SISTEMA");


            // ima.mensaje_informacion("ESTUDIANTE CANCELADO POR PERIODOS REPROBADOS","CANCELACION","periodos", "png");

            }else{System.out.println("SIN NOVEDAD CON PERIODOS REPROBADOS");}


            if(this.misma_materia_reprobada(cedula, vigencia, maximas_materias_reprobadas)){
                //doc.escritor_textos("d://CANCELADOS/"+carrera+"_cancelados_"+vigencia+".txt", cedula+"; MISMA MATERIA REPROBADOS 3 VECES");
                doc.escritor_textos(direccion+carrera+"_cancelados_"+vigencia+".csv", cedula+"; MISMA MATERIA REPROBADOS 3 VECES");
                System.out.println("Estudiante "+cedula+", se encuentra CANCELADO!!!");
            // ima.mensaje_informacion("ESTUDIANTE CANCELADO POR MISMAS MATERIAS REPROBADAS","CANCELACION","periodos", "png");
  //this.inactivando(new conexion_base_de_datos().getConexion(), cedula);
                this.almacenando_cancelados(new conexion_base_de_datos().getConexion(), cedula,this.materia_causal,periodo_academico,"SISTEMA");

            }else{System.out.println("SIN NOVEDAD CON MISMA MATERIA REPROBADA ");}
    
    }else{//en caso de que no se halla seleccionado nada, o se cancelo no se hace mas nada
        
        ima.mensaje_informacion("Esta Operación de ANÁLISIS DE CANCELACIÓN ha sido interrumpida..!!!", "OPERACIÓN INTERRUMPIDA", "alto", "png");
    
    }
    
}

/**Este metodo se encarga de generar los detalles de cancelacion del estudiante o los 
 estudiantes que esten sometidos al analisis del metodo. Para ello se necesita la direccion
 en donde se va a guardar el archivo csv*/
public void informe_cancelados(String ruta){
         try {
            doc.escritor_csv_2(ruta, this.getInforme(), 1);
        } catch (IOException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }

}

//public void informe_inscripcion_automatic


/**Analiza si la materia correspondiente esta aprobada o reprobada de acuerdo a varias consideraciones tanto de
 notas definitivas, reparacion y las condicion en el que se encuentra la asignatura. Para ello se pasan como
 parametros tres elementos*/
public boolean analisis_x_materia(int definitiva, int reparacion, String condicion_materia){
//System.out.println("[analisis_x_materia]");
    boolean aprobastes=false;
               

    if(((definitiva>=10 ||
         reparacion>=10) &&
         (condicion_materia!="POR INASISTENCIA" || condicion_materia!="REPROBÓ-25% DE INASISTENCIA" || condicion_materia!="REPROBÓ-50% DE INASISTENCIA") &&
          condicion_materia!="ERROR") ||
          condicion_materia.equalsIgnoreCase("APROBÓ") ||
          condicion_materia.equalsIgnoreCase("EQUIVALENCIA") ||
          condicion_materia.equalsIgnoreCase("EXONERADO")
            ){
//            System.out.println("paso");
            aprobastes=true;
    }else{
//            System.out.println("reprobado");
            aprobastes=false;
    }
    
    System.out.println("def:"+definitiva+" - rep:"+reparacion+" - cond:"+condicion_materia+" - PASO="+aprobastes);
    
    return aprobastes;
}
/**Este metodo lista todas las materias del estudiante correspondiente al periodo academico de interes,
 para asi analizar la condicion de cada materia del mismo. Para ello se envia la cedula del estudiante,
 periodo academico, vigencia al que correspende (pensum) y una conexion a la base de datos*/
public boolean materias_x_periodo(Connection con, String cedula, String periodo_academico, String vigencia){
    boolean novedad=true;//condiciones iniciales. Se asume por defecto que el periodo tiene problemas. el analisis desmentira o no la tesis

    try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.cem_notas_alumnos_todas_carreras_"+vigencia+" WHERE (CEDULA='"+cedula+"' AND PERACA='"+periodo_academico+"');");
            while (resultado.next()) {//recorriendo las materias correspondientes al periodo
                System.out.println("MATERIA: "+resultado.getString("CODMAT")+"   ");
                if(this.analisis_x_materia(resultado.getInt("NOTDEF"),resultado.getInt("NOTREP"),materia.condicion_materia(resultado.getString("CONDIC")))){//si alguna materia del periodo esta aprobada se descarta el mismo y se procede al siguiente periodo
                    novedad=false;//periodo sin novedad
                    break;

                }else{
                   novedad=true; //periodo con novedad
                }
            }//fin recorrido materias del periodo
            
            System.out.println("NOVEDAD:::"+novedad);
           sentencia.close();
           resultado.close();
           con.close();


        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ADQUIRIENDO LAS MATERIAS DEL PERIODO CORRESPONDIENTE "+periodo_academico, "ERROR GRAVE","error", "png");
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }

return novedad;
}
/**Este metodo se encarga de analizar si el estudiante correspondiente a una vigencia o pensum, cumple con
 este requisito de cancelacion por la cantidad de periodos academicos reprobados.*/
public boolean periodos_reprobados(Connection con, String cedula, String vigencia, int maximo){
      int conteo=0;
      boolean cancelado=true;//se asume que esta cancelado hasta que se demuestre lo contrario
      String notificacion="";
     //18697970

        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT PERACA FROM control_de_estudio.cem_notas_alumnos_todas_carreras_"+vigencia+" WHERE CEDULA='" + cedula + "' group by PERACA;");
            while (resultado.next()) {//recorriendo las vigencias

                if(this.materias_x_periodo(new conexion_base_de_datos().getConexion(), cedula, resultado.getString("PERACA"),vigencia)){//se analiza cada vigencia de materias inscritas del estudiante
                    conteo=conteo+1;
                    //notificacion=notificacion.concat(" "+cedula+" - "+resultado.getString("PERACA")+" - pensum: "+vigencia);
                    if(notificacion.length()<4) notificacion=resultado.getString("PERACA")+"; "; else notificacion=notificacion.concat(resultado.getString("PERACA")+"; ");
                    System.out.println("############## Conteo:"+conteo+"----"+notificacion);
                    
                        if(conteo>=maximo){//en el momento que se determina que esta cancelado se envia la informacion de inmediato
                            cancelado=true;
                            //this.getInforme().add(maximo+" Periodos Reprobados "+resultado.getString("PERACA")) ;                           
                            this.getInforme().add(maximo+" Periodos Reprobados "+notificacion) ;                           
                            //this.periodo_causal = maximo+" Periodos Reprobados "+resultado.getString("PERACA");
                            this.periodo_causal = maximo+" Periodos Reprobados "+notificacion;
                            break;                    
                        }

                }else{

                   cancelado=false;
                }

            }//fin recorrido de vigencias

            if(conteo<=0) cancelado = false; //esto es por si no hay algun registro que revisar del estudiante se supone que no tiene notas en el record. no tiene problemas
            
           sentencia.close();
           resultado.close();
           con.close();


        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR CALCULANDO PERIODOS REPROBADOS", "ERROR GRAVE","error", "png");
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }
return cancelado;
}







/**Metodo que se encarga de analizar las condiciones de las materias reprobadas del estudiante,
 en donde se listan todas las materias que por algun problema esten reprobadas y luego
 se determina si existe alguna materia que sea capaz de cancelar al estudiante. Los parametros
 son la cedula, vigencia a la que pertenece y las maximas materias reprobadas para cancelacion.
 Devuelve una respuesta logica para determinar si el estudiante esta cancelado o no*/
public boolean misma_materia_reprobada(String cedula, String vigencia, int maximas_materias_reprobadas){

    this.materias_reprobadas_estudiante(new conexion_base_de_datos().getConexion(), cedula, vigencia);
    
return this.comparaciones(maximas_materias_reprobadas, cedula);
}


/**Este metodo lista todas las materias que por alguna causa esten reprobadas
 por lo que es necesario una conexion a la base de datos, cedula del estudiante y
 la vigencia o pensum al que pertenece.*/
public void materias_reprobadas_estudiante(Connection con, String cedula, String vigencia){
    this.getReprobadas().clear();//limpiando el LinkedList antes de utilizarlo
    
        try {
            Statement sentencia = null;
            ResultSet resultado = null;

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.cem_notas_alumnos_todas_carreras_" + vigencia + " WHERE CEDULA='" + cedula + "';");
            while (resultado.next()) {
                
                if( Integer.valueOf(resultado.getString("PERACA").substring(2)) >= 2010  ){//analisando solo las materias que sean igual o mayor al periodo 1-2010 que es cuando se hizo vigente la cancelacion de tres materias reprobadas iguales
                    //System.out.println("ANALISANDO MATERIA: "+resultado.getString("CODMAT")+" - PERIODO: "+resultado.getString("PERACA"));
                    if(this.analisis_x_materia(resultado.getInt("NOTDEF"), resultado.getInt("NOTREP"), materia.condicion_materia(resultado.getString("CONDIC")))==false){//si la materia esta reprobada guardarla para el analisis posterior

                        this.getReprobadas().add(resultado.getString("CODMAT"));//almacenando las materias reprobadas
                        System.out.println("Materia Reprobada "+resultado.getString("CODMAT")+" Definitiva="+resultado.getInt("NOTDEF")+" Reparacion="+resultado.getInt("NOTREP")+" Periodo:"+resultado.getString("PERACA")+" Condicion= "+materia.condicion_materia(resultado.getString("CONDIC")));
                    }
                }else{System.out.println("ESTA VIGENCIA ESTA FUERA DE ANALISIS DE CANCELACION: "+resultado.getString("PERACA")+" - MATERIA:"+resultado.getString("CODMAT") );
                     }
            }

            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }




}

public boolean comparaciones(int max_repitencias, String cedula){
    LinkedList<String> comparacion = new LinkedList<>();//variable para almacenar una materia de cada tipo

    for(int r=0; r<this.getReprobadas().size(); r++){//recorriendo las materias reprobadas

        if(comparacion.size()<=0){//si el LinkedList esta en blanco guarda el primer registro
           comparacion.add(this.getReprobadas().get(r));//guarda

        }else{//sino revisa que no este repetida la materia (Agrupando por tipo)


            for(int i=0; i<comparacion.size(); i++){//recorriendo las materias de comparacion

                if(this.getReprobadas().get(r).equalsIgnoreCase(comparacion.get(i))){ break;}//si la materia esta repetida en el LinkedList se ignora
                else{

                    if(i>=(comparacion.size()-1)){//verifica que para agregar la materia se halla recorrido todo el LinkedList
                        comparacion.add(this.getReprobadas().get(r));//agregando materia reprobada nueva
                    }
                }
            }//fin recorriendo LinkedList comparacion

        }


    }//fin recorrido reprobadas




//    System.out.println("MATERIAS POR TIPO");
    //for(int d=0; d<comparacion.size();d++){
      //  System.out.println("Materia "+comparacion.get(d));

    //}


   //LinkedList COMPARACION VS LinkedList REPROBADAS. EL OBJETIVO ES VER CUANTAS VECES SE REPITE UNA MISMA MATERIA

   return this.apariciones(comparacion, this.reprobadas,max_repitencias, cedula);
}

/**Este medoto determina el numero de veces que una materia esta reprobada y el nombre de la misma,
 ademas determina si el estudiante se encuentra cancelado por este causal, para ello se necesitan
 pasar como parametros las materias reprobadas y la misma pero agrupada por tipo, adicional a ello
 el numero maximo de materias reprobadas*/
private boolean apariciones(LinkedList<String> tipo, LinkedList<String> reprobadas, int repitencia, String cedula){
    int c=0;
    boolean cancelado=false;

    

    for(int t=0; t<tipo.size();t++){//recorriendo las materias ordenadas por tipo
        c=0;//iniciando el conteo por cada materia

        for(int r=0; r<reprobadas.size(); r++){//recorriendo las materias reprobadas

            if(tipo.get(t).equalsIgnoreCase(reprobadas.get(r))){//si los codigos coinciden contarlas

                c=c+1;//contando el numero de coincidencias

                if(c>=repitencia){//si se alcanza el maximo es un motivo de cancelacion del estudiante
                    System.out.println("Materia Causante Cancelacion "+tipo.get(t));
                    this.getInforme().add("Materia "+repitencia+" Veces Reprobada - Causa: "+tipo.get(t));
                    materia_causal = "Materia "+repitencia+" Veces Reprobada - Causa: "+tipo.get(t);
                    cancelado=true;
                }

            }



        }//fin recorrido reprobadas

    }//fin recorrido materias por tipo



  
return cancelado;
}

/**Este metodo da el listado de los servicios comunitarios por carrera */
public void listado_servicio_comunitario(Connection con, String carrera){
        try {
            Statement sentencia = null;
            ResultSet r = null;
            sentencia = con.createStatement();
            
            r = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE nucleo='NUCLEO ARAGUA - SEDE MARACAY' AND carrera='" + carrera + "' ;");
               
                while(r.next()){
                    System.out.println("CEDULAS: "+r.getString("cedula")+" - fila: "+r.getRow());
                    this.depurador_estudiantes_semestres(r.getString("cedula"), carrera);
                }

                if(this.getComunitarios().size()>0){//si hay algo que guardar del listado se hace
                    System.out.println("PROCESANDO INFORMACION");
                    try {
                        doc.escritor_csv_2("d://LISTADO_SERVICIO_COMUNITARIO/"+carrera+".csv", this.getComunitarios(), 4);
                    } catch (IOException ex) {
                        Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            sentencia.close();
            r.close();
            con.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }

}







//----------------------------ANALISIS PARA INSCRIPCION AUTOMATICA-------------------------------------

public void depurador_estudiantes_semestres(String identidad, String especialidad){
      //  this.getEst_x_carrera().clear();
        this.cant_mat.clear();//limpiando el LinkedList que acumula la cantidad de materias por semestre
        this.limpiando();
        
     
            materia.getOrden().clear(); //limpiando la variable que almcena el record acomodado por periodo academico
            materia = new materias();//es para limpiar todas las variables
            rpdf = new registro_pdf();


            System.out.println("CEDULA DEL ESTUDIANTE: "+identidad+" CARRERA:"+especialidad+"\n");

            if(identidad.isEmpty()){//validando que la casilla de la cedula no este vacia o este buena
                //ima.mensaje_informacion("NO DEJE LA CASILLA DE LA CEDULA EN BLANCO", "ADVERTENCIA", "exclamacion", "png") ;

                //  ipcion.getCedula().setText(null);
                //  ipcion.getEstudiante().setText(null);
                //  ipcion.getCarrera().setText(null);

            }else{

               //ipcion.getInscribir().setEnabled(true);//habilitando el boton de inscribir despues de presionar validar

                reging.verificar_alumno(cbd.getConexion(), identidad);//verificando que el alumno se encuentre registrado en el sistema
                                                                                        //para luego cargar su record y darle el resto de las opciones

                    if(reging.getControl()==0){//en caso de que el alumno no se halla actualizado en el sistema
                //      ipcion.getEstudiante().setText(reging.getEstudiante());
                  //    ipcion.getCarrera().setText(reging.getCarrera());


                      for(int v=0; v<=2; v++){//revisando la base de datos 3 veces para hallar en que vigencia se encuentra el alumno
                          System.out.println("viendo"+v);
                          materia.setSeleccion_pensum(v);
                          reging.setReconocimiento(v);
                         // reging.buscar_materias(cbd.getConexion(), identidad,materia.notas_especialidad(especialidad,reging));//cargando las materias del alumno
                          reging.buscar_materias_alternativo(cbd.getConexion(), identidad,materia.notas_especialidad(reging));//cargando las materias del alumno

                          //if(reging.getPeriodo().isEmpty()){}else{break;}
                          if(reging.getRecord().isEmpty()){}else{break;}

                      }//fin revisando la base de datos 3 veces

                          System.out.println("para materia: "+reging.getReconocimiento()+" v: "+materia.getSeleccion_pensum());

                          //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera()));//cargando las materias del pensum



                    //   ipcion.setDeposito(reging.academico());//configurando la tabla jtable
                    //   ipcion.getDesempeño_academico().setModel(ipcion.getDeposito());




                            //if(reging.getPeriodo().isEmpty()){
                            if(reging.getRecord().isEmpty()){
                               //         ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "precaucion", "png");

                                        materia.setNuevo_ingreso(1);
                                        //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum
                                     //   materia.materias_pensum_equivalencias(cbd.getConexion(),materia.carrera_equivalencia(reging.getCarrera(),cambio));//cargando las materias de equivalencias
                            }
                            else{
                                       //  materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum
                                       // materia.materias_pensum_equivalencias(cbd.getConexion(),materia.carrera_equivalencia(reging.getCarrera(),cambio));//cargando las materias de equivalencias

                                            //---------ordenando las materias por periodo academico
                                            //for(int b=0; b<reging.getCodigo().size();b++){
                                              for(int b=0; b<reging.getRecord().size();b=b+11){
                                                 //materia.ordenamiento_año(reging.getPeriodo().get(b));
                                                  System.out.println("año: "+reging.getRecord().get(b+5));
                                                  materia.ordenamiento_año(reging.getRecord().get(b+5));
                                              }

                                                //materia.ordenamiento_materias(reging);//ordenando las materias del alumno por periodo academico
                                                materia.ordenamiento_materias_alternativos(reging);//ordenando materia
                                                reging.setComp_per(materia.getOrden().get(5));//inicializando la variable con el primer periodo inscrito

                                               // for(int i=0; i<materia.getOrden().size(); i=i+6){
                                           /*     for(int i=0; i<materia.getOrden().size(); i=i+11){

                                                        if(reging.getComp_per().equalsIgnoreCase(materia.getOrden().get(i+5))){ }
                                                        else{//para separar entre periodos academicos cuando se presenta en la tabla
                                                         //ipcion.getDeposito().addRow(new Object[]{null,null,null,null,null});
                                                         //ipcion.getDeposito().fireTableDataChanged();
                                                         reging.setComp_per(materia.getOrden().get(i+5));
                                                          }

                                                       Object nuevo[]={materia.getOrden().get(i),//codigo de la materia
                                                                      materia.getOrden().get(i+1),//nombre de la materia
                                                                      materia.getOrden().get(i+2),//definitiva de la materia
                                                                      materia.getOrden().get(i+3),//nota de la reparacion
                                                                      materia.getOrden().get(i+4),//condicion de la materia
                                                                      materia.getOrden().get(i+5) //periodo academico de la materia
                                                                    };

                                                                         //ipcion.getDeposito().addRow(nuevo);
                                                                         //ipcion.getDeposito().fireTableDataChanged();
                                                }*/


                                              //--------------------------------------------------FIN




                                         }  //validacion de que halla informacion del estudiante en cuanto a notas
                                           //en la base de datos


                                                            //ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CODIGO",5,"centrado");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"MATERIA",300,"izquierda");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"DEFINITIVA",5,"centrado");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"REPARACION",5,"centrado");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CONDICION",10,"izquierda");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"PERIODO",10,"centrado");


                                                           materia.materias_preladas_y_no_preladas();

                                                          materia.esquema_prelacion();
                                                         //materia.esquema_prelacion_equivalencias();
                                                         materia.esquema_normal();
                                                         materia2.servicio_comunitario(materia);
                                                        //CREANDO UNA VENTANA PARA VISUALIZAR LAS MATERIAS QUE EL ESTUDIANTE PUEDE INSCRIBIR

                                                         //--------listando los estudiantes que pueden ver servicio comunitario
                                                         if(materia2.isServicio()){
                                                             this.getComunitarios().add(reging.getCedula());//cedula
                                                             this.getComunitarios().add(reging.getEstudiante());// nombres y apellidos
                                                             this.getComunitarios().add(reging.getCarrera());//carrera
                                                             this.getComunitarios().add(reging.getTurnos());//turno del estudiante
                                                         }
                                                         

                                                         //--------------------------------------------------------------



                                                          System.out.println("\nMATERIAS AUTORIZADAS");
                                               //           System.out.println("INSERT INTO control_de_estudio.equivalencias_notas_todos (`POSICION`,`CEDULA`,`CODMAT`,`SECCION2`,`SECCION`,`TERMINO`,`PERACA`,`CONDIC`,`NOTREP`,`NOTDEF`,`DEFREP`,`PORINA`,`CODESP`,`CREDI`,`PENSUM`,`OBSERVACIÓ`,`FECHA_HORA`,`ADMINISTRA`,`NOTLAB`) VALUES");

                                                          for(int h=0; h<materia.getLista_autorizada().size();h=h+4){//buscando todas las materias autorizadas
                                                               
                                                               System.out.println("semestre: "+materia.getLista_autorizada().get(h+3)+" codigo: "+materia.getLista_autorizada().get(h+0)+" nombre: "+materia.getLista_autorizada().get(h+1)+" condicion: "+materia.getLista_autorizada().get(h+2));
                                                             //  String credito=materia.credito_materia(materia.getLista_autorizada().get(h+0),materia.getPensum());
                                                           //   System.out.println("(NULL,'"+identidad+"','"+materia.getLista_autorizada().get(h+0)+"','','',"+Integer.valueOf(materia.getLista_autorizada().get(h+3))+",'1-2011','"+materia.getLista_autorizada().get(h+2)+"',0,0,0,0,'',"+credito+","+vigencia+",'CAMBIADO DE PENSUM','','',0),");


                                                               this.conteo_materias_estudiantes_cantidad(identidad, materia.getLista_autorizada().get(h+0) , materia.getLista_autorizada().get(h+1));

                                                               this.conteo_materias_semestre(Integer.valueOf(materia.getLista_autorizada().get(h+3)));
                                                               

                                                           }

                                                         // ctablas.tabla_habilitadas(ipcion.getAutorizadas(), materia.getLista_autorizada());//escribiendo en la tabla de materias autorizadas
                                                         // ctablas.configuracion(ipcion.getAutorizadas(),null,"SEMESTRE", 5, "centrado");
                                                          //ctablas.configuracion(ipcion.getAutorizadas(),null,"CODIGO", 5, "izquierda");
                                                         // ctablas.configuracion(ipcion.getAutorizadas(),null,"MATERIA", 300, "izquierda");
                                                         // ctablas.configuracion(ipcion.getAutorizadas(),null,"CONDICION", 20, "centrado");


                                                          //ipcion.getVigencias().setText(materia.getVigencia());//colocando la vigencia del pensum a la que pertenece el alumno

                                                          //CALCULOS CORRESPONDIENTE A CADA ESTUDIANTE
                                                          DecimalFormat df = new DecimalFormat("0.00");
                                                          
                                                         
                                                      //   doc.escritor_texto_mejorado("d://REPORTES/INDICES CARRERAS/"+especialidad+".txt",//ruta
                                                        //                              "Cedula; UC Aprobadas; Puntos Acumulados; Indice de Merito",//cabecera
                                                       //                               identidad+";"+rpdf.getUc_aprobadas()+";"+rpdf.getPuntos_merito()+";"+df.format(rpdf.getIndice_merito()) );//contenido
                                                        
                                                          rpdf.calculo_alternativo(materia.getOrden(), materia.getPensum(), materia.getElectivas());
                                                          rpdf.calculo_orden_merito(materia.getOrden(), materia.getPensum(), materia.getElectivas());

                                                          
                                                          doc.escritor_texto_mejorado(this.getRuta_externa()+"notas_"+especialidad+".txt",
                                                                                     "Cedula; UC Equivalencias; UC Cursadas; Puntos Academicos Acumulado; Indice Academico; Puntos Merito Acumulado; Indice Merito",
                                                                                    identidad+";"+rpdf.getUc_equivalencias()+";"+rpdf.getUc_cursadas()+";"+rpdf.getPuntos()+";"+df.format(rpdf.getIndice_academico())+";"+rpdf.getPuntos_merito()+";"+df.format(rpdf.getIndice_merito()) );

                                                          //original
                                                       //  doc.escritor_texto_mejorado("d://REPORTES/INDICES CARRERAS/notas_"+especialidad+".txt",
                                                         //                            "Cedula; UC Equivalencias; UC Cursadas; Puntos Academicos Acumulado; Indice Academico; Puntos Merito Acumulado; Indice Merito",
                                                           //                         identidad+";"+rpdf.getUc_equivalencias()+";"+rpdf.getUc_cursadas()+";"+rpdf.getPuntos()+";"+df.format(rpdf.getIndice_academico())+";"+rpdf.getPuntos_merito()+";"+df.format(rpdf.getIndice_merito()) );
                                                           
                                                          
                                                       //   doc.escritor_textos("d://REPORTES/INDICES ACADEMICOS/"+especialidad+".txt",
                                                         //                     identidad+";"+rpdf.getUc_equivalencias()+";"+rpdf.getUc_cursadas()+";"+rpdf.getPuntos()+";"+df.format(rpdf.getIndice_academico()) );

                                                          //

/*                                                          System.out.println("uc equivalencia: "+this.getUc_equivalencias());
            System.out.println("uc cursadas: "+this.getUc_cursadas());
            System.out.println("puntos acumulados: "+this.getPuntos());
            System.out.println("indice academico: "+this.getIndice_academico()+" - "+df.format(this.getIndice_academico()));*/

 //System.out.println("visor 1 "+materia.getLista_autorizada().size());

                                                          //materia.getOrden().clear();
                                                          materia.getAux1().clear();
                                                          materia.getAux2().clear();
                                                        /*  reging.getCodigo().clear();
                                                          reging.getMateria().clear();
                                                          reging.getDefinitiva().clear();
                                                          reging.getReparacion().clear();
                                                          reging.getCondicion().clear();
                                                          reging.getPeriodo().clear();*/

                                                          reging.getRecord().clear();//limpiando record alternativo

                                                          //materia.getPensum().clear();
                                                          materia.getLlenado().clear();
                                                          materia.getPosicion().clear();
                                                          materia.getPreladas().clear();
                                                          materia.getNo_preladas().clear();
                                                        //  materia.getLista_autorizada().clear();

                    }else{
                          //ipcion.getCedula().setText(null);
                          //ipcion.getEstudiante().setText(null);
                          //ipcion.getCarrera().setText(null);
                         // ipcion.getAutorizadas().setModel(new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","CONDICION"}));
                          //ipcion.getDesempeño_academico().setModel(new DefaultTableModel(new Object [][]{},new String [] {"CODIGO", "MATERIA", "DEFINITIVA", "REPARACION", "CONDICION", "PERIODO"}));

                          }//si el estudiante no esta en el sistema no se muestra nada


                    }//fin validacion de la casilla cedula este vacia



 //andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE","CONDICION"});
 //ipcion.getSecciones().setModel(andy);
// materia.materias_inscritas_bd(ipcion.getSecciones(),andy,cbd.getConexion(),ipcion.getCedula().getText());
  //                  ctablas.configuracion(ipcion.getSecciones(),andy,"SEMESTRE",2,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"CODIGO",5,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"MATERIA",150,"izquierda");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"UC",2,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"SECCION",20,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"HORARIO",30,"izquierda");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"DOCENTE",20,"izquierda");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"CONDICION",5,"centrado");
//System.out.println("visor 2 "+materia.getLista_autorizada().size());
}

/**Este metodo es para saber cuales materias entre los estudiantes de una carrera hacen falta que vean */
private LinkedList<String> agrupando_por_materias(LinkedList<String> x){
    String materias ="";
    LinkedList<String> mat = new LinkedList<>();
    
    for(int i=0; i<x.size(); i=i+3){//recorriendo todos los alumnos de la carrera con las materias que le hacen falta ver    
        materias = x.get(i+1);
        
        if(mat.isEmpty()){
            mat.add(materias);//agregando codigo de materia
        }else{
                boolean almacenar = false;
                for(int m=0; m<mat.size(); m++){//recorriendo lista de materias guardadas
                    if(mat.get(m).equalsIgnoreCase(materias)){//si esta dentro de las guardadas
                        almacenar = false; break;
                    }else{//si no se sigue recorriendo la lista hasta llegar al final
                        almacenar = true;
                    }
                }//fin recorrido lista materias guardadas
            
            if(almacenar) mat.add(materias);
            
        }
    }//fin recorrio
 
    return mat;
}

private LinkedList<String> agrupando_por_cedulas(LinkedList<String> x){
    LinkedList<String> est = new LinkedList<>();    
    String ced;
    
    for(int i=0; i<x.size(); i=i+3){//recorriendo todos los alumnos de la carrera con las materias que le hacen falta ver    
        ced = x.get(i);
        
        if(est.isEmpty()){
            est.add(ced);//agregando codigo de materia
        }else{
                boolean almacenar = false;
                for(int m=0; m<est.size(); m++){//recorriendo lista de materias guardadas
                    if(est.get(m).equalsIgnoreCase(ced)){//si esta dentro de las guardadas
                        almacenar = false; break;
                    }else{//si no se sigue recorriendo la lista hasta llegar al final
                        almacenar = true;
                    }
                }//fin recorrido lista materias guardadas
            
            if(almacenar) est.add(ced);
            
        }
    }//fin recorrio
    
    
    return est;
}

private LinkedList<String> estructura(LinkedList<String> est, int columnas){
    LinkedList<String> aux = new LinkedList<>();
    String linea = null;
    String col="";
    System.out.println("materias: "+columnas);
    
    for(int c=1;c<=columnas;c++){//creando columnas
        //col = col.concat("; "+c);        
        col = col.concat("; ");
    }//fin creando columnas
    
    for(int i=0; i<est.size(); i++){//recorriendo los estudiantes uno a uno    
        linea = est.get(i)+col;
        aux.add(linea);//agregando la cedula con la estructura para posteriormente ser llenada
       // System.out.println(i+"-"+linea);
    }//fin recorrido estudiantes
    
    return aux;
}

private int comparacion_por_materias(LinkedList<String> mat, String materia){
    int x=0;
        
    for(int i=0; i<mat.size(); i++){
        if(materia.equalsIgnoreCase(mat.get(i))){//si coinciden se coloca una x en la posicion de interes
            x=i+1;//posicion de la materia de interes        
            break;
        }
    }
    
    return x;
}

private void relacion_estudiantes_materias(LinkedList<String> est, LinkedList<String> mat, LinkedList<String> registros , String direccion){
    //String direccion = "d://REPORTES/ESQUEMA/status.txt";
    doc.borrar_archivo(direccion);
    String cabecera = "est/mat "+mat.size()+";";        
    for(int g=0; g<mat.size(); g++){ cabecera = cabecera.concat(mat.get(g)+";");}
    //no tienen materias=nuevos. 145430001,18124364,10719375,10897582,14588483
    
    String linea = null;
    LinkedList<String> plantilla = new LinkedList<>();
    int elemento=0;
    
    plantilla = this.estructura(est, mat.size());
    
    hilos hilo = new hilos("GUARDANDO ANALISIS PROCESADO", 0, false, new progreso());
    hilo.start();
    hilo.setInicio(0); hilo.setFin(plantilla.size());
    hilo.setInformacion_1("PREPARANDOSE PARA GUARDAR LOS ANALISIS");
  
    for(int p=0; p<plantilla.size(); p++){//recorriendo la plantilla    
        
        String [] f = plantilla.get(p).split(";");
            //buscando registros del estudiante bajo analisis
         for(int r=0; r<registros.size(); r=r+3){//recorriendo estudiantes_materias
             
               String a = f[0];
               String b = registros.get(r);
               //if(registros.get(r)!=null) b = registros.get(r);
             
              // System.out.println("cedulas: "+f[0]+" - "+registros.get(r)+" IGUALES: "+(a.contains(b)));
               
              if(a.contains(b)){
                //if(f[0].toString().equalsIgnoreCase(registros.get(r).toString())){//comparando cedulas                    
                   elemento =  this.comparacion_por_materias(mat,registros.get(r+1));                    
                   //System.out.println("elementos: "+elemento);
                }
                
                if(elemento>0) f[elemento]="X";                
                
         }//fin recorrido estudiantes_materias         
        
         String reconstruccion ="";
         for(int c=0;c<f.length;c++){
             reconstruccion = reconstruccion.concat(f[c]+";");         
         }
        
        //System.out.println("GUARDANDO analizado: "+f[0]);
        hilo.setAvance(p);
        hilo.setInformacion_1("GUARDANDO REGISTROS DE: "+f[0]);
        doc.escritor_texto_mejorado(direccion, cabecera, reconstruccion);
        
    }//fin recorrido plantilla
    
    hilo.setAvance(hilo.getFin());

}


public void lista_estudiantes_materias_faltantes(String direccion, LinkedList<String> informacion){
    for(int i=0; i<informacion.size(); i=i+3){
       // System.out.println("Estudiantes Analisis: "+informacion.get(i));
        doc.escritor_texto_mejorado(direccion, "CÉDULA;CODIGO;ASIGNATURA", informacion.get(i)+";"+informacion.get(i+1)+";"+informacion.get(i+2));
    }
    

}


public void estudiantes_materias(String carrera_analizar){
    //         MAT30250;MAT31115;QUF22413
    //12342345;   x    ;   x    ;
    //14563214;        ;   x    ;   x
    String archivo = ima.guardado_general_directorio("GUARDAR DOCUMENTO");
  if(archivo!=null){
    
    hilos hilo = new hilos("PROCESAMIENTO STATUS "+carrera_analizar, 0, false, new progreso());
    hilo.start();
    hilo.setInicio(0);
   
    hilo.setInformacion_1("PREPARANDOSE PARA LA DEPURACIÓN");
   
    
       // this.setRuta_exlista_estudiantes_carreraterna(archivo);//es la ruta donde va a depositar los txt al menos de los ordenes de merito. la tabla notas......revisar mejor para mejorar
        this.lista_estudiantes_carrera(cbd.getConexion(),carrera_analizar );
        
    hilo.setFin(this.getEst_x_carrera().size()+20);
       
       for(int x=0; x<this.getEst_x_carrera().size(); x++){ //recorriendo listado de estudiantes
           System.out.println("#####################_____PROCESADO N°: ("+x+")_____#####################");
             hilo.setInformacion_1("Estudiante: "+this.getEst_x_carrera().get(x)+" --- ("+x+" / "+this.getEst_x_carrera().size()+")");
             hilo.setAvance(x); 
                this.materias_pendientes(this.getEst_x_carrera().get(x), carrera_analizar);//depurando estudiante por estudiante

       }//fin recorrido listado por estudiantes
           
       hilo.setInformacion_1("GUARDANDO ANÁLISIS REALIZADOS....!!! por favor espere");
              
      this.relacion_estudiantes_materias( this.agrupando_por_cedulas(this.getEst_y_mat()) , this.agrupando_por_materias(this.getEst_y_mat()), this.getEst_y_mat(), archivo+"/ANALISIS_"+carrera_analizar+".txt");
            
        hilo.setInformacion_1("GENERANDO RESUMEN SECUNDARIO....!!! Solo unos segundos mas");
      this.lista_estudiantes_materias_faltantes(archivo+"/MATERIAS_POR_APROBAR_"+carrera_analizar+".txt", this.getEst_y_mat());
      
      
       this.getEst_x_carrera().clear();
       this.getEst_y_mat().clear();
       this.getMat_y_cant().clear();

       hilo.setAvance(hilo.getFin());
       
  }else{
      ima.mensaje_informacion("EL PROCESO FUE CANCELADO INTENCIONALMENTE.", "MENSAJE", "exclamacion", "png");
  }

}





public void materias_pendientes(String identidad, String especialidad){
      //  this.getEst_x_carrera().clear();
        this.cant_mat.clear();//limpiando el LinkedList que acumula la cantidad de materias por semestre
        this.limpiando();
        
     
            materia.getOrden().clear(); //limpiando la variable que almcena el record acomodado por periodo academico
            materia = new materias();//es para limpiar todas las variables
            rpdf = new registro_pdf();

            //System.out.println("CEDULA DEL ESTUDIANTE: "+identidad+" CARRERA:"+especialidad+"\n");

            if(identidad.isEmpty()){//validando que la casilla de la cedula no este vacia o este buena
                
            }else{

                reging.verificar_alumno(cbd.getConexion(), identidad);//verificando que el alumno se encuentre registrado en el sistema
                                                                                        //para luego cargar su record y darle el resto de las opciones

                    if(reging.getControl()==0){//en caso de que el alumno no se halla actualizado en el sistema

                      for(int v=0; v<=2; v++){//revisando la base de datos 3 veces para hallar en que vigencia se encuentra el alumno
                          //System.out.println("viendo"+v);
                          materia.setSeleccion_pensum(v);
                          reging.setReconocimiento(v);
                          reging.buscar_materias_alternativo(cbd.getConexion(), identidad,materia.notas_especialidad(reging));//cargando las materias del alumno
                          
                          if(reging.getRecord().isEmpty()){}else{break;}

                      }//fin revisando la base de datos 3 veces

                          //System.out.println("para materia: "+reging.getReconocimiento()+" v: "+materia.getSeleccion_pensum());

                            if(reging.getRecord().isEmpty()){
                               //         ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "precaucion", "png");

                                        materia.setNuevo_ingreso(1);                                        
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum                                     
                            }
                            else{
                                       
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum

                                            //---------ordenando las materias por periodo academico
                      
                                              for(int b=0; b<reging.getRecord().size();b=b+11){
                                                  materia.ordenamiento_año(reging.getRecord().get(b+5));
                                              }

                                                materia.ordenamiento_materias_alternativos(reging);//ordenando materia
                                                reging.setComp_per(materia.getOrden().get(5));//inicializando la variable con el primer periodo inscrito


                                              //--------------------------------------------------FIN

                                         }  //validacion de que halla informacion del estudiante en cuanto a notas
                                           //en la base de datos

                                                         materia.materias_preladas_y_no_preladas();
                                                         materia.esquema_prelacion();
                                                         materia.esquema_normal();
                                                         materia2.servicio_comunitario(materia);
                                                        //CREANDO UNA VENTANA PARA VISUALIZAR LAS MATERIAS QUE EL ESTUDIANTE PUEDE INSCRIBIR

                                                         //--------listando los estudiantes que pueden ver servicio comunitario
                                                         if(materia2.isServicio()){
                                                             this.getComunitarios().add(reging.getCedula());//cedula
                                                             this.getComunitarios().add(reging.getEstudiante());// nombres y apellidos
                                                             this.getComunitarios().add(reging.getCarrera());//carrera
                                                             this.getComunitarios().add(reging.getTurnos());//turno del estudiante
                                                         }
                                                         //--------------------------------------------------------------

                                                         

              //                                            System.out.println("\nMATERIAS AUTORIZADAS");                                               

                                                          for(int h=0; h<materia.getLista_autorizada().size();h=h+4){//buscando todas las materias autorizadas
                                                               
                                                               //System.out.println("semestre: "+materia.getLista_autorizada().get(h+3)+" codigo: "+materia.getLista_autorizada().get(h+0)+" nombre: "+materia.getLista_autorizada().get(h+1)+" condicion: "+materia.getLista_autorizada().get(h+2));
                                                               
                                                               this.conteo_materias_estudiantes_cantidad(identidad, materia.getLista_autorizada().get(h+0) , materia.getLista_autorizada().get(h+1));
                                                               this.conteo_materias_semestre(Integer.valueOf(materia.getLista_autorizada().get(h+3)));
                                                               
                                            
                                                           }


                                                          
                                                          materia.getAux1().clear();
                                                          materia.getAux2().clear();                                                        
                                                          reging.getRecord().clear();//limpiando record alternativo
                                                          materia.getLlenado().clear();
                                                          materia.getPosicion().clear();
                                                          materia.getPreladas().clear();
                                                          materia.getNo_preladas().clear();
                                                        

                    }else{
                  
                          }//si el estudiante no esta en el sistema no se muestra nada


                    }//fin validacion de la casilla cedula este vacia


}





public void limpiando(){

    for(int i=0; i<=10; i++){
        this.getCant_mat().add(i, 0);
    }
}

/**Este metodo cuenta las materias que debe el estudiante por semestre. */
public void conteo_materias_semestre(int semestre){
        
            //System.out.println("Posicion "+(semestre-1));
          //this.calculando_n_semestres(semestre);
            
           // System.out.println("Calculo : Semestre= "+semestre+"  x= "+this.getCant_mat().get(semestre-1)+" suma= "+(this.getCant_mat().get(semestre-1)+1));
            this.getCant_mat().set((semestre-1),(this.getCant_mat().get(semestre-1)+1));//sumando el numero de materias encontrada por semestre
           // System.out.println("Resultado: "+this.getCant_mat().get(semestre-1));
        
}





public void serv(Connection con){
    Statement sentencia = null;
    ResultSet r = null;

   /* sentencia=con.createStatement();
    r= sentencia.executeQuery("");
    while(r.next()){

        sentencia.executeUpdate("");

        ('17970485','TAL00001','','X',1,'2-2010','A',0,0,0,0,28,0,0,'','00:00','',0);
        ('17970485','PRO00001','','X',1,'2-2010','A',0,0,0,0,28,0,0,'','00:00','',0);


    }

*/

}











public void pdf_individual(String ruta,String cedula, boolean inscripcion, boolean record, boolean constancia,boolean culminacion ,boolean certificadas,int ajuste, String periodo){

    System.out.println("Activado: Inscripcion: "+inscripcion+"\n"
                                +"Record: "+record+"\n"
                                +"Constancia: "+constancia+"\n"
                                +"Culminacion: "+culminacion+"\n"
                                +"Certificadas: "+certificadas);
hilos hilo = new hilos("DOCUMENTO INDIVIDUAL", 0, false, new progreso());
hilo.start();
hilo.setInicio(0);hilo.setFin(100);


this.informacion(new conexion_base_de_datos().getConexion(),cedula );      
hilo.setInformacion_1("PROCESANDO A "+cedula+" - "+this.getNombres_apellidos()); hilo.setAvance(15);    
if(this.getNombres_apellidos()!=null){
      this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedula);//localizando a que vigencia pertenece      
        hilo.setAvance(35);
      this.pdf_masivos(ruta, new conexion_base_de_datos().getConexion(),cedula, this.getCarreras(), this.getSede(), inscripcion, record, constancia, ajuste, periodo);//generando pdf por cada cedula de alumno inscrito de la jornada de inscripcion
        hilo.setAvance(55);
      rpdfs.constancia_de_culminacion(ruta+"constancia_de_culmincacion_" + cedula+ ".pdf",cedula, this.getNombres_apellidos().replaceAll(",", "").trim(), culminacion);
        hilo.setAvance(75);      
      this.masivo_notas_certificadas(ruta, new conexion_base_de_datos().getConexion(),cedula, this.getCarreras(), this.getSede(), inscripcion, record, constancia, certificadas ,ajuste, periodo);//generando pdf por cada cedula de alumno inscrito de la jornada de inscripcion
        hilo.setAvance(100);
      doc.selector_documentos(ruta,cedula, inscripcion, record);
}else{
    hilo.setAvance(100);
    ima.mensaje_informacion("HAY PROBLEMAS CON ESTE ESTUDIANTE","INFORMACION","advertencia", "png");
}
}



public void pdf_notas_certificadas(String ruta,String cedula, boolean inscripcion, boolean record, boolean constancia, int ajuste, String periodo){

      this.informacion(new conexion_base_de_datos().getConexion(),cedula );
      this.localizando_estudiante(new conexion_base_de_datos().getConexion(),cedula);//localizando a que vigencia pertenece
      this.masivo_notas_certificadas(ruta, new conexion_base_de_datos().getConexion(),cedula, this.getCarreras(), this.getSede(), inscripcion, record, constancia, true,ajuste, periodo);//generando pdf por cada cedula de alumno inscrito de la jornada de inscripcion


}


/**Con este metodo se tiene en un LinkedList la cantidad de estudiantes que pertenecen a una carrera en particular */
public void lista_estudiantes_carrera(Connection con, String carrera){

        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            
            //resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos where carrera!='INGENIERIA MECANICA'; ");
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE carrera='" + carrera + "' ; ");
        //   resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE carrera='" + carrera + "' GROUP BY cedula; ");
        //egresados
            //resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE carrera='" + carrera + "' AND tipo_estudiante='Egresado' AND upi_periodo='1/2013'; ");
       //     resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE carrera='" + carrera + "' AND tipo_estudiante='esperando_2007' AND upi_periodo='1/2013'; ");
           //   resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.orden_merito");

         //listado sin pasantes y egresados y que no sean pensum 0
//            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE pensum!=0 AND carrera='" + carrera + "' AND cedula NOT IN (SELECT CEDULA FROM control_de_estudio.cedulas); ");
            
            
            while (resultado.next()) {
                this.getEst_x_carrera().add(resultado.getString("cedula"));
            }
            System.out.println("Cantidad estudiante: "+this.getEst_x_carrera().size()+" carrera: "+carrera);
            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
                System.out.println("Conexion a bd cerrada");
            } catch (SQLException ex) {
                Logger.getLogger(registro_masivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}



public void conteo_materias_estudiantes_cantidad(String cedula, String codigo, String nombre_materia){
    boolean encontrado=false;

    //se guarda por estudiante las materias que debe
        this.getEst_y_mat().add(cedula);
        this.getEst_y_mat().add(codigo);
        this.getEst_y_mat().add(nombre_materia);

    //se guarda por materia la cantidad de alumnos que las debe

        if(this.getMat_y_cant().isEmpty()){//si esta vacio el LinkedList se agrega la materia y se suma uno porque es la primera
            // System.out.println("VACIO");
            this.getMat_y_cant().add(codigo);
            this.getMat_y_cant().add(nombre_materia);
            this.getMat_y_cant().add("1");

        }else{

            // System.out.println("CON INFORMACION");

            for(int mc=0; mc<this.getMat_y_cant().size(); mc=mc+3){//recorriendo el LinkedList
                
                // System.out.println("codigo actual: "+codigo+" - guardado: "+this.getMat_y_cant().get(mc));

                    if(codigo.equalsIgnoreCase(this.getMat_y_cant().get(mc+0))){
                   //     System.out.println("--------..------...- igual. codigo: "+codigo+" = "+this.getMat_y_cant().get(mc+2));
                        int total=0;
                        total=Integer.valueOf(this.getMat_y_cant().get(mc+2))+1;

                       this.getMat_y_cant().set(mc+2,String.valueOf(total));

                       encontrado=true;
                       break;

                    }

            }//fin recorrido


             if(encontrado==false){//si despues de recorrer el LinkedList no hallo ni siquiera el codigo de la materia la agrega como nueva porque no existe
               //  System.out.println("NO SE HALLO EN EL REGISTRO. AGRAGANDOLA AL MISMO");
                this.getMat_y_cant().add(codigo);
                this.getMat_y_cant().add(nombre_materia);
                this.getMat_y_cant().add("1");
             }

        }





}


/**Este metodo se encarga de ejecutar una serie de tares para obtener la lista de los
 estudiantes que se encuentran en estado de cancelacion de los inscritos en el periodo academico de interes.
 Cuando se quiere procesar a un estudiante se debe colocar la cedula pero si es a una carrera en especifico se coloca
 en el lugar de la cedula null*/
public void procesando_cancelados(String carrera, String periodo, int maximos_periodos_reprobados, int maximas_materias_reprobados, String cedula, boolean individual, boolean masivo){

String direccion = gr.guardar_archivo();//seleccionando la ruta para guardar la información
if(direccion!=null){    //verificando que se halla suministrado una direccion correcta para realizar los procedimientos

            if(masivo){    
                //DE FORMA MASIVA
                hilos hilo = new hilos("ANÁLISIS CANCELACIÓN: "+carrera,0,false, new progreso());    //iniciando hilo para la barra de progreso
                hilo.setInformacion_1("CARGANDO CÉDULAS DE ESTUDIANTES INSCRITOS EN EL PERÍODO "+periodo);
                hilo.start();
                this.estudiantes_inscritos(cbd.getConexion() ,carrera , periodo);//listando los estudiantes de la jornada de inscripcion
                System.out.println("CARRERA: "+carrera+" - POBLACION: "+(this.getAlumnos().size()/2)+" estudiantes");   

                //hilos hilo = new hilos("ANÁLISIS CANCELACIÓN: "+carrera,0,false, new progreso());    //iniciando hilo para la barra de progreso
                hilo.setInicio(0); hilo.setFin(this.getAlumnos().size()/2);
            // hilo.start();

                for(int x=0;x<this.getAlumnos().size(); x=x+2){//recorriendo todos los alumnos inscritos
                    hilo.setAvance(x/2);
                    hilo.setInformacion_1("CEDULA: "+this.getAlumnos().get(x)+" - ("+(x/2)+"/"+(this.getAlumnos().size()/2)+")");

                    System.out.println("CONTEO: "+(x/2)+" - "+(this.getAlumnos().size()/2)+" Cedula: "+this.getAlumnos().get(x)+"  pensum: "+this.getAlumnos().get(x+1));
                    this.lista_cancelados(maximos_periodos_reprobados, maximas_materias_reprobados, this.getAlumnos().get(x),this.getAlumnos().get(x+1),carrera, direccion, periodo);//calculando los cancelados por carrera
                //  rm.depurador_estudiantes_semestres(rm.getAlumnos().get(x), "LIC. ECONOMIA SOCIAL");

                }

                hilo.setAvance(hilo.getFin());//es para finalizar. se igualan los valores de avance con fin
            }


            if(individual){
                System.out.println("REVISANDO A CEDULA DEL ESTUDIANTE: "+cedula);
                //DE FORMA INDIVIDUAL
                if(cedula.isEmpty() || cedula==null){
                    ima.mensaje_informacion("DEBE COLOCAR LA CEDULA PRIMERO", "ANALIZANDO CANCELACION ESTUDIANTE", "avisos", "png");
                }else{
                    String pen_vig [] = this.vigencia_alumno(new conexion_base_de_datos().getConexion(), cedula);
                    this.lista_cancelados(maximos_periodos_reprobados, maximas_materias_reprobados, cedula, pen_vig[0], pen_vig[1],direccion, periodo);
                    ima.mensaje_informacion("EL PROCESO HA TERMINADO CON EXITO...!!!", "ANALIZANDO CANCELACION ESTUDIANTE", "finalizar", "png");
                }

            }

    
}else{
    ima.mensaje_informacion("EL PROCESO HA SIDO CANCELADO MANUALMENTE", "ANÁLISIS CANCELACIÓN INTERRUMPIDA", "exclamacion", "png");
}
    
    
}




public static void main(String args[]){
    registro_masivo rm = new registro_masivo();
    conexion_base_de_datos cbd = new conexion_base_de_datos();
   
    rm.txt_carrera_generalizado(cbd.getConexion(), new imagenes().guardado_general_directorio("GUARDAR ARCHIVO TXT"),"1-2016","Regular");
   System.exit(0);

}





}//fin de la clase
