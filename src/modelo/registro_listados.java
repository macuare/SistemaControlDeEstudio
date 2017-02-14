/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.listados;
import vista.progreso;


/**
 *
 * @author AN
 */
public class registro_listados {
    private int capacidad;
    private Archivos doc;
    private imagenes ima;
   

    private String carreras, seccion, materias, periodo;
    private Boolean habilitado;
    private JComboBox aulas, auxiliar;





    public registro_listados() {
       this.setCapacidad(0);
       doc = new Archivos();
       ima = new imagenes();
       
    }




    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getCarreras() {
        return carreras;
    }

    public void setCarreras(String carreras) {
        this.carreras = carreras;
    }

 

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public JComboBox getAulas() {
        return aulas;
    }

    public void setAulas(JComboBox aulas) {
        this.aulas = aulas;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public JComboBox getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(JComboBox auxiliar) {
        this.auxiliar = auxiliar;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    











public void inicializacion(Connection con, String carrera,JComboBox materia ,JComboBox seccion){
            Statement sentencia = null;
            ResultSet resultado = null;
           //inicializando y limpiando cada JComboBox
            materia.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
            seccion.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));




        try {

            sentencia = con.createStatement();


             resultado = sentencia.executeQuery("SELECT materia FROM control_de_estudio.jornada_inscripcion WHERE carrera='"+carrera+"' GROUP BY materia ORDER BY materia;");

             while(resultado.next()){

                Object inf_asignaturas=resultado.getString("materia");
                materia.addItem(inf_asignaturas);

             }

        /*    resultado = sentencia.executeQuery("SELECT seccion,materia FROM control_de_estudio.jornada_inscripcion WHERE carrera='"+carrera+"' AND materia='"+materia.getSelectedItem().toString()+"' GROUP BY seccion ORDER BY seccion;");

            Object todas="TODAS";//agregando el una opcion para listar todas las secciones
            seccion.addItem(todas);

                while (resultado.next()) {

                    Object inf_seccion=resultado.getString("seccion");
                    seccion.addItem(inf_seccion);//buscando todas las secciones aperturadas
                    
                }

*/
            
            sentencia.close();
            resultado.close();
            con.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
        }




}


public void inicializacion_2(Connection con, String carrera,JComboBox materia ,JComboBox seccion){

            Statement sentencia = null;
            ResultSet resultado = null;
           //inicializando y limpiando cada JComboBox
        seccion.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));

        try {

            sentencia = con.createStatement();


            resultado = sentencia.executeQuery("SELECT seccion,materia FROM control_de_estudio.jornada_inscripcion WHERE carrera='"+carrera+"' AND materia='"+materia.getSelectedItem().toString()+"' GROUP BY seccion ORDER BY seccion;");

            Object todas="TODAS";//agregando el una opcion para listar todas las secciones
            seccion.addItem(todas);

                while (resultado.next()) {

                    Object inf_seccion=resultado.getString("seccion");
                    seccion.addItem(inf_seccion);//buscando todas las secciones aperturadas
                    
                }


            
            sentencia.close();
            resultado.close();
            con.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
        }





}









public void busqueda_filtrada(Connection con, String carrera,String materia ,String seccion, JTable tabla, String periodo){

         Statement sentencia = null;
         ResultSet resultado = null;
         int conteo=0;
         this.setCapacidad(0);

        DefaultTableModel modelo= new DefaultTableModel(//es para limpiar la tabla cada vez que se use
                                       new Object [][] {},
                                       new String [] {"N", "CEDULA", "ESTUDIANTE","SECCION"});

         tabla.setModel(modelo);//se le dice a la tabla que va a trabajar con el modelo de datos anterior


        try {

            sentencia = con.createStatement();



            if(seccion.equalsIgnoreCase("TODAS")){
                resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE periodo='"+periodo+"' AND carrera='"+carrera+"'AND materia='"+materia+"' GROUP BY cedula ORDER BY estudiante;");
                System.out.println("opcion 1");
            }else{
                resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE periodo='"+periodo+"' AND carrera='"+carrera+"' AND materia='"+materia+"' AND seccion='"+seccion+"' GROUP BY cedula ORDER BY estudiante;");
                System.out.println("opcion 2");
            }



            while (resultado.next()) {//solo se hara el recorrido si previamente se hallo informacion
                conteo=conteo+1; //es para colocar numero en la lista de alumnos
               

                    Object informacion []={
                        conteo,
                        resultado.getString("cedula"),
                        resultado.getString("estudiante"),
                        resultado.getString("seccion")
                    };

                    modelo.addRow(informacion);
                    modelo.fireTableDataChanged();

            }

            this.setCapacidad(conteo);
            tabla.setAutoCreateColumnsFromModel(false);
            tabla.setAutoCreateRowSorter(true);

            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
        }



}



public void exportar_data(Connection con, String carrera,String materia, String seccion, String ruta, String periodo){
 Statement sentencia = null;
         ResultSet resultado = null;
         int conteo=0;
         LinkedList<String> datos= new LinkedList<>();

         System.out.println("exportar "+carrera+" "+seccion);
        try {

            sentencia = con.createStatement();

            if(seccion.equalsIgnoreCase("TODAS")){
                resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE periodo='"+periodo+"' AND carrera='"+carrera+"' AND materia='"+materia+"'  GROUP BY cedula ORDER BY estudiante;");
                System.out.println("opcion 1....");
            }else{
                resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE periodo='"+periodo+"' AND carrera='"+carrera+"' AND materia='"+materia+"' AND seccion='"+seccion+"' GROUP BY cedula ORDER BY estudiante;");
                System.out.println("opcion 2....");
            }

            String linea=null;

          //colocando cabecera de la tabla
                datos.add("N°");
                datos.add("CEDULAS");
                datos.add("ESTUDIANTES");
                datos.add("SECCION");
                datos.add("CARRERA");


                
           while (resultado.next()) {//solo se hara el recorrido si previamente se hallo informacion
                conteo=conteo+1; //es para colocar numero en la lista de alumnos
                //System.out.print("conteo lineas "+conteo);
                linea=conteo+"  "+resultado.getString("cedula")+"  "+resultado.getString("estudiante")+"  "+resultado.getString("seccion");

                //    doc.escritor_textos(ruta,linea);//escribiendo linea a linea en el documento txt
                datos.add(String.valueOf(conteo));//llenando el LinkedList de la informacion necesaria
                datos.add(resultado.getString("cedula"));
                datos.add(resultado.getString("estudiante"));
                datos.add(resultado.getString("seccion"));
                datos.add(resultado.getString("carrera"));

            }

                
            try {
                doc.escritor_csv(ruta, datos, 5); //enviando la informacion para crear el archivo csv
            } catch (IOException ex) {
                Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
            }


            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public String acondicionamiento(String expresion){
    
    if(expresion.endsWith("\u0020")){
           System.out.println("espacio en blanco hallado: "+expresion);
           return expresion.substring(0, (expresion.length()-1));//elimina el espacio en blanco al final de la palabra
    }else{
           return expresion; //en otro caso devuelve la misma palabra
    }


}

/** Metodo para generar todas las listas de una carrera de acuerdo a todas 
 las secciones que existen registradas despues de las inscripciones, para ella
 se pasa como parametro una conexion a la base de datos, la carrera, el directorio y
 la extension del archivo con el que se desea guardar, ademas un LinkedList que contenga
 todas las materias aperturadas*/
public void exportar_data_masivo(conexion_base_de_datos cbd, String carrera,JComboBox asignaturas, String directorio, String extension, JComboBox elementos, String periodo){
    String ruta;
   
   
    
    LinkedList <String> aulas= new LinkedList<>();
    Statement sentencia = null;
    ResultSet resultado = null;

       if(directorio!=null){
           
            hilos hilo = new hilos("LISTADOS SECCIONES "+carrera, 0, false, new progreso());
            hilo.setInformacion_1("CARGANDO TODAS LAS SECCIONES...!!!");
            hilo.start();
           
           
            //File f=new File(directorio+"\\"+carrera);
           File f=new File(directorio+carrera);
            if( f.mkdir()){//creando la carpeta de la carrera

            for(int a=0; a<=asignaturas.getItemCount()-1;a++){//recorriendo todas las asignaturas
                hilo.setFin((asignaturas.getItemCount()-1)+30);//estableciendo limite superior barra progreso, se le suma 30 puntos para dar 15 a los procesos alumnos no inscritos y aularios
                hilo.setAvance(a);
                
                        //f=new File(directorio+"\\"+carrera+"\\"+this.acondicionamiento(asignaturas.getItemAt(a).toString()));//creando directorio
                        f=new File(directorio+carrera+File.separator+this.acondicionamiento(asignaturas.getItemAt(a).toString()));//creando directorio
                        if(f.mkdir()){//verificando que se halla creado la carpeta con el nombre de las materias

                        cbd = new conexion_base_de_datos();//abriendo nueva conexion
                        try {//buscando las secciones por materias
                        sentencia = cbd.getConexion().createStatement();
                        resultado = sentencia.executeQuery("SELECT seccion,materia FROM control_de_estudio.jornada_inscripcion WHERE periodo='"+periodo+"' AND carrera='"+carrera+"' AND materia='"+asignaturas.getItemAt(a).toString()+"' GROUP BY seccion ORDER BY seccion;");

                        while (resultado.next()) {//por cada seccion se busca el numero de alumnos
                        //System.out.println("guardando: "+directorio+"\\"+carrera+"\\"+asignaturas.getItemAt(a)+"\\lista_"+resultado.getString("seccion")+extension);

                            aulas.add(resultado.getString("seccion"));

                        }


                        sentencia.close();
                        resultado.close();
                        cbd.getConexion().close();

                        //guardando en los archivos csv
                            for(int csv=0;csv<aulas.size();csv++){
                                cbd = new conexion_base_de_datos();
                               // ruta=directorio+"\\"+carrera+"\\"+this.acondicionamiento(asignaturas.getItemAt(a).toString())+"\\lista_"+aulas.get(csv)+extension;
                                 ruta=directorio+carrera+File.separator+this.acondicionamiento(asignaturas.getItemAt(a).toString())+File.separator+"lista_"+aulas.get(csv)+extension;
                                 System.out.println("csv: "+ruta);
                                 
                                hilo.setInformacion_1(asignaturas.getItemAt(a).toString()+" \\ "+aulas.get(csv)); //mostrando las operaciones en la barra progreso
                                this.exportar_data(cbd.getConexion(), carrera, asignaturas.getItemAt(a).toString(),aulas.get(csv), ruta, periodo);
                                
                            }

                            aulas.clear();//limpiando el LinkedList despues de utilizar




                        } catch (SQLException ex) {
                            ima.mensaje_informacion("Hubo problemas con la conexion a base de datos\n"+ex.getMessage(), "ERROR", "error", "png");
                            Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
                        }

            }//fin comprobacion de creacion de carpeta por materia
            else{System.out.println("Error: No se pudo crear el directorio");}


            }//fin recorrido asignaturas
                
            
            }//fin comprobacion de carpeta de la carrera
            
            
            
         registro_notas rn = new registro_notas();
         hilo.setInformacion_1("DETERMINANDO ESTUDIANTES NO INSCRITOS");
         rn.listado_no_inscritos(new conexion_base_de_datos().getConexion(), carrera, directorio, periodo);//generando la lista de los no inscritos
         hilo.setAvance(hilo.getAvance()+15);//el otro 15 porciento de lista no inscritos
         
         hilo.setInformacion_1("DETERMINANDO LISTA DE AULARIOS");
         this.aularios_carrera(new conexion_base_de_datos().getConexion(), carrera, directorio, periodo);   
         hilo.setAvance(hilo.getAvance()+15);//el otro 15 porciento de lista aularios
          
          
       }else{
           ima.mensaje_informacion("EL PROCESO DE LISTADOS MASIVO HA SIDO DETENIDO", "LISTADO MASIVOS "+carrera, "exclamacion", "png");
           
       }



}//fin metodo exportar data masivo


/**Este metodo se encarga de entregar la distribucion de los aulas, cantidad de estudiantes
 inscritos versus la capacidad para asi poder cerrar o aperturar materia sea la necesidad*/
public void aularios_carrera(Connection con,String carrera, String ruta, String periodo ){
    LinkedList<String> aularios = new LinkedList<>();
                        aularios.add("SECCION");//CABECERA
                        aularios.add("CODIGO");
                        aularios.add("ASIGNATURA");
                        aularios.add("INSCRITOS");
                        aularios.add("CAPACIDAD");
                        
           Statement sentencia = null;
           ResultSet resultado = null;


        try {




            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT i.seccion, i.codigo, i.materia, " +
                                               "COUNT(DISTINCT i.cedula),o.capacidad " +
                                               "FROM control_de_estudio.jornada_inscripcion i, control_de_estudio.jornada_inscripcion_ofertadas o " +
                                               "WHERE i.periodo='"+periodo+"' AND i.seccion=o.seccion AND i.carrera='"+carrera+"' GROUP BY i.materia, i.seccion " +
                                               "HAVING COUNT(DISTINCT i.cedula)>'0' ORDER BY seccion;");
                    while(resultado.next()){
                        aularios.add(resultado.getString(1));
                        aularios.add(resultado.getString(2));
                        aularios.add(resultado.getString(3));
                        aularios.add(resultado.getString(4));
                        aularios.add(resultado.getString(5));

                    }

            sentencia.close();
            resultado.close();
            con.close();


            if(aularios.size()>0){//solo si existen registro se guardan los mismos en el archivo csv
                System.out.println("Reporte de Distribucion de los aularios "+aularios.size());
                try {
                    //solo si existen registro se guardan los mismos en el archivo csv
                    doc.escritor_csv_2(ruta+File.separator+carrera+File.separator+"AULARIOS_"+carrera+".csv", aularios, 5); //enviando la informacion para crear el archivo csv
                } catch (IOException ex) {
                    Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
                    ima.mensaje_informacion("ERROR ESCRIBIENDO INFORME\n"+ex.getMessage(), "ERROR", "error", "png");
                }
            
            }



        } catch (SQLException ex) {
            Logger.getLogger(registro_listados.class.getName()).log(Level.SEVERE, null, ex);
        }




}













    
}//fin de la clase
