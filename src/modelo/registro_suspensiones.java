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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import vista.progreso;



/**
 *
 * @author AN
 */
public class registro_suspensiones {

    private imagenes ima;
    private activo_inactivo ai;

    public registro_suspensiones() {
        ima = new imagenes();
        ai = new activo_inactivo();
    }


    /**Este metodo permite buscar la condicion del estudiante, si esta activo o no.
     Para ello necesita una coneccion a la base de dato y la cedula del estudiante a buscar*/
    public String busqueda_estudiante(Connection con, String cedula){
         String activo = null;
         

        try {
           

            Statement sentencia = null;
            ResultSet resultado = null;

            con.setAutoCommit(false); //deshabilitando la confirmacion automatica de que llego y se grabo la informacion en la base de datos

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT activo FROM control_de_estudio.todos_alumnos WHERE cedula='"+cedula+"';");

                if(resultado.next()){//si hay algo que mostrar lo hace
                    activo = resultado.getString("activo"); //tomando la informacion de la columna activo
                    System.out.println("Estudiante: " + cedula + " Activo?: " + activo); //mostrando por pantalla
                }

            con.commit();//haciendo permanente los cambios y liberando la base de datos
            sentencia.close();
            resultado.close();

           


      //PROTOCOLO DE CERRADO SEGURO DE LA BASE DE DATOS
        } catch (SQLException ex) {
            Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            if(con!=null){
                System.err.println("Error en el envio de informacion. Transaccion revertida. Rolling Back");
                try {
                    con.rollback();//regresando los cambios
                    ima.mensaje_informacion("Error enviando informacion a la base de datos. Devolviendo cambios. Rolling Back\n"+ex.getMessage(), "ERROR SQL", "error", "png");
                } catch (SQLException ex1) {
                    Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex1);

                }

            }
        }finally{//se eejecuta pase lo que pase con la bd, es para asegurar el cierre

            try {
                con.setAutoCommit(true);//regresando la condicion original del autocommit
                con.close();
                System.out.println("Cerrando base de datos");
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

 return activo;//regresando lo que se obtuvo


    }//fin del metodo


public void cambio_activo(Connection con, String cedula, String activo){
      int salida=5;
        try {
            Statement sentencia = null;

            con.setAutoCommit(false);

            sentencia = con.    createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.todos_alumnos  SET activo='"+activo+"' WHERE cedula='"+cedula+"';");


            con.commit();//haciendo permanente los cambios y liberando la base de datos
            System.out.println("Haciendo permanente el cambio en ACTIVO del estudiante: "+cedula+" - actual: "+activo);
            sentencia.close();

            if(salida==0){//solo se muestra el mensaje si el cambio fue realizado con exito
             ima.mensaje_informacion("Cambios Realizado con Exito\n"
                                     +"Estudiante: "+cedula+" ACTIVO = "+activo, "MODIFICACION ACTIVO", "exito_2", "png");
            }

        } catch (SQLException ex) {
            Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
             if(con!=null){
                System.err.println("Error en el envio de informacion. Transaccion revertida. Rolling Back");
                try {
                    con.rollback();//regresando los cambios
                    ima.mensaje_informacion("Error enviando informacion a la base de datos. Devolviendo cambios. Rolling Back\n"+ex.getMessage(), "ERROR SQL", "error", "png");
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


/**Permite saber cual es la condicion de activo del estudiante */
public void buscando(Connection con, JTextField cedula, JButton buscar, JComboBox seleccion, JButton cambiar, JButton analizar){
    String respuesta=null;
    respuesta=this.busqueda_estudiante(con, cedula.getText());

    if(respuesta.length() >0){//buscando estudiante. revisando que halla devuelto algo
        analizar.setEnabled(true);
        seleccion.setEnabled(true);//habilitando los controles
        cambiar.setEnabled(true);
        seleccion.setSelectedItem(respuesta);
        //System.out.println(respuesta);

        cedula.setEnabled(false);//deshabilitando los controles iniciales
        buscar.setEnabled(false);

    }else{//en caso de que no encuentre nada
      ima.mensaje_informacion("Estudiante NO EXISTE", "RESULTADO DE BUSQUEDA", "no_existe", "png");
      
    }


}


public void buscando_alternativa(Connection con, JTextField cedula, JButton buscar, JComboBox seleccion, JButton cambiar, JButton analizar){
    String respuesta=null;
    respuesta=this.busqueda_estudiante(con, cedula.getText());

    if(respuesta!=null){//buscando estudiante. revisando que halla devuelto algo
        analizar.setEnabled(true);
       // seleccion.setEnabled(true);//habilitando los controles
        cambiar.setEnabled(true);
        seleccion.setSelectedItem(respuesta);
        //System.out.println(respuesta);

        cedula.setEnabled(false);//deshabilitando los controles iniciales
        buscar.setEnabled(false);
    }else{
           this.agregando(new conexion_base_de_datos().getConexion(), cedula.getText());
    
    }
}

public void agregando(Connection con, String cedula){
    int respuesta = 1;

    Statement sentencia = null;
    respuesta=ima.confirmacion("ESTE ESTUDIANTE NO EXISTE REGISTRADO AQUI!!!.\nDESEA INGRESARLO EN ESTOS NUEVOS REGISTROS\nYA QUE SON NECESARIOS", "INSERCION DE ESTUDIANTE", "confirmacion", "png");    
    try {
                    //System.out.println("RESPUESTA: "+respuesta);
     if( respuesta == 0){            
                sentencia = con.createStatement();
                sentencia.executeUpdate("INSERT INTO control_de_estudio.todos_alumnos VALUES (NULL,'"+cedula+"','NO');");
                
                sentencia = con.createStatement();
                sentencia.executeUpdate("DELETE FROM control_de_estudio.claves WHERE etapa1='"+cedula+"';");//eliminando de la base de datos el registro de clave e incripcion
                
                sentencia = con.createStatement();
                sentencia.executeUpdate("INSERT INTO control_de_estudio.claves VALUES ('"+cedula+"','123456',true);");//creando una nueva
               
                  sentencia.close();
                ima.mensaje_informacion("REGISTRO CREADO CON EXITO\nEl estudiante ha sido ingresado como INACTIVO \ny con la inscripcion BLOQUEADA", "EXITO", "exito", "png");                       
                
      }         

     } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}


public void agregando_masivo(Connection con, String cedula){
    
    Statement sentencia = null;
    
    try {
                    //System.out.println("RESPUESTA: "+respuesta);
    
                sentencia = con.createStatement();
                sentencia.executeUpdate("INSERT INTO control_de_estudio.todos_alumnos VALUES (NULL,'"+cedula+"','NO');");
                
                sentencia = con.createStatement();
                sentencia.executeUpdate("DELETE FROM control_de_estudio.claves WHERE etapa1='"+cedula+"';");//eliminando de la base de datos el registro de clave e incripcion
                
                sentencia = con.createStatement();
                sentencia.executeUpdate("INSERT INTO control_de_estudio.claves VALUES ('"+cedula+"','123456',true);");//creando una nueva
               
                  sentencia.close();
    
                
    

     } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}



public void busqueda_masiva(Connection con, String periodo, JTextArea area){
        String respuesta = null;
        String cedula = null;
        LinkedList<String> informacion = new LinkedList<>();        
        
        
                        hilos hilo = new hilos("ANÁLISIS ACTIVOS INACTIVOS",0,false, new progreso());    //iniciando hilo para la barra de progreso
                        hilo.setInformacion_1("CARGANDO CÉDULAS DE ESTUDIANTES INSCRITOS");
                        hilo.start();        
               
        
        
        try {
            Statement sentencia = null;
            ResultSet resultado = null;        
              
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.jornada_inscripcion GROUP BY cedula;");
            while(resultado.next()){//recorriendo los elementos                
             informacion.add(resultado.getString("cedula"));
            }
            
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        int longitud = informacion.size();
        
        if(longitud > 0){//solo si hay registros se procede
                hilo.setInicio(0); hilo.setFin(longitud);//inicializando parametros
                
              for(int i=0 ; i<informacion.size(); i++){  //recorriendo las cedulas
                  cedula = informacion.get(i);                
                  
                    hilo.setAvance(i);
                    hilo.setInformacion_1("CEDULA: "+cedula+" - ("+(i)+"/"+(longitud)+")");  
                  
                
                respuesta=this.busqueda_estudiante(new conexion_base_de_datos().getConexion(), cedula);
                if(respuesta!=null){//buscando estudiante. revisando que halla devuelto algo
                    ai.setResumen(area);
                    ai.procesamiento_analisis(cedula);//procesando al estudiante                    
                }else{//en caso de que no exista
                    this.agregando_masivo(new conexion_base_de_datos().getConexion(), cedula);
                }
            
              }//fin recorrido cedulas
              
              hilo.setAvance(hilo.getFin());//es para finalizar. se igualan los valores de avance con fin
        }
            
        

}








/**Permite cambiar la condicion de activo del estudiante para poderse inscribir */
public void cambiando(Connection con, JTextField cedula, JButton buscar, JComboBox seleccion, JButton cambiar, JButton analizar){

   if(ima.confirmacion("REALMENTE DESEA REALIZAR EL CAMBIO", "CAMBIO DE CONDICION ACTIVO", "configuracion", "png") == 0){//solo si se acepta el cambio
     this.cambio_activo(con, cedula.getText(), seleccion.getSelectedItem().toString());//realizando cambio

        cedula.setEnabled(true);//volviendo a las condiciones iniciales de la pantalla
        cedula.setText(null);
        buscar.setEnabled(true);
        seleccion.setEnabled(false);
        seleccion.setSelectedIndex(0);
        cambiar.setEnabled(false);
        analizar.setEnabled(false);
    }
   
}

/**Este metodo es para desbloquear el acceso a la inscripcion del estudiante una vez finalizada.
 Es otra oportunidad para inscribirse*/
public boolean consulta_bloqueo_inscripcion(Connection con, String cedula){

    boolean bloqueado=false;//inicializando la variable de bloqueo de inscripcion

    Statement sentencia = null;
    ResultSet resultado = null;

        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.claves WHERE etapa1='" + cedula + "';");
            if(resultado.next()){//si existe alguna coincidencia
                 if(resultado.getString("acuse").equalsIgnoreCase("1")){//esta bloqueado
                    bloqueado=true;
                 }else{//esta desbloqueado
                    bloqueado=false;
                 }

            }else{//no encontro coincidencia en la base de datos
                bloqueado=false;//se establece no bloqueado
                ima.mensaje_informacion("ESTE ESTUDIANTE NO EXISTE EN LAS CLAVES", "ADVERTENCIA", "exclamacion", "png");
            }


            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR CONSULTANDO EN LA TABLA CLAVES", "ADVERTENCIA", "error", "png");
            Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

return bloqueado;
}

/**Metodo para poder bloquear y desbloquear la inscripcion del estudiante de interes */
public void bloqueo_desbloqueo(Connection con, String cedula, boolean bloquear){
    int respuesta=0;
        Statement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.claves WHERE etapa1 = '"+cedula+"';");
            if(resultado.next()){//el tipo existe. se puede desbloquear o bloquear
                sentencia = con.createStatement();
                sentencia.executeUpdate("UPDATE control_de_estudio.claves SET acuse="+bloquear+" WHERE etapa1='"+cedula+"';");
            }else{//no existe y se inserta un parametro
                respuesta=ima.confirmacion("DESEA INSERTAR UN REGISTRO DE DESBLOQUEO \nAL ESTUDIANTE PORQUE NO EXISTE", "INSERCION DE DESBLOQUEO Y BLOQUEO", "confirmacion", "png");
                System.out.println("RESPUESTA: "+respuesta);
               if( respuesta == 0){
                    sentencia = con.createStatement();
                    sentencia.executeUpdate("INSERT INTO control_de_estudio.claves VALUES ('"+cedula+"','123456',"+bloquear+");");
                    ima.mensaje_informacion("REGISTRO CREADO CON EXITO", "EXITO", "exito", "png");
                }else{}

            }

       sentencia.close();
       resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR CONSULTANDO EN LA TABLA CLAVES", "ADVERTENCIA", "error", "png");
            Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


}

public void bloqueo_desbloqueo_alternativo(Connection con, String cedula, boolean bloquear){
    int respuesta=0;
        Statement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.claves WHERE etapa1 = '"+cedula+"';");
            if(resultado.next()){//el tipo existe. se puede desbloquear o bloquear
                sentencia = con.createStatement();
                sentencia.executeUpdate("UPDATE control_de_estudio.claves SET acuse="+bloquear+" WHERE etapa1='"+cedula+"';");
            }else{//no existe y se inserta un parametro
                respuesta=ima.confirmacion("DESEA INSERTAR UN REGISTRO DE DESBLOQUEO \nAL ESTUDIANTE PORQUE NO EXISTE", "INSERCION DE DESBLOQUEO Y BLOQUEO", "confirmacion", "png");
                System.out.println("RESPUESTA: "+respuesta);
               if( respuesta == 0){
                    sentencia = con.createStatement();
                    sentencia.executeUpdate("INSERT INTO control_de_estudio.claves VALUES ('"+cedula+"','123456',"+bloquear+");");
                    ima.mensaje_informacion("REGISTRO CREADO CON EXITO..!!!", "EXITO", "exito", "png");
                }else{}

            }

       sentencia.close();
       resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR CONSULTANDO EN LA TABLA CLAVES", "ADVERTENCIA", "error", "png");
            Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_suspensiones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


}
















   
}//fin de la clase
