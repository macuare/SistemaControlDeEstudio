/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AN
 */
public class registro_reingresos {
    
    private LinkedList<String> causales = new LinkedList<>();
    private LinkedList<String> reingresos = new LinkedList<>();
    private LinkedList<String> asociacion = new LinkedList<>();
    private String estudiante, carrera, turno;
    private BufferedImage foto;
    private configuracion_tablas ct;
    private imagenes ima;
    
    
    public registro_reingresos() {
        ima = new imagenes();
        ct = new  configuracion_tablas();
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public BufferedImage getFoto() {
        return foto;
    }

    public void setFoto(BufferedImage foto) {
        this.foto = foto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

 
    
    
    
    
    
public void buscar_usuario(Connection con, String cedula){
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
          //buscando su nombre apellido  carrera turno
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_personales WHERE cedula='"+cedula+"';");
            if(resultado.next()){
                estudiante = resultado.getString("nombres")+", "+resultado.getString("apellidos");
            }else{estudiante = null;}
            
            sentencia.close();
            resultado.close();
            
          //buscando carrera y turno            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='"+cedula+"';");
            if(resultado.next()){
                carrera = resultado.getString("carrera");
                turno = resultado.getString("turno");
            }
            sentencia.close();
            resultado.close();
          
           //buscando foto  
           sentencia = con.createStatement();
           resultado = sentencia.executeQuery("SELECT foto FROM carnets.fotos WHERE cedula='"+ cedula + "';");
           BufferedImage bufferi = new BufferedImage(192,240, BufferedImage.TYPE_INT_RGB);

            if(resultado.next()){
                try {
                    Blob imagenes=null;
                    imagenes = resultado.getBlob("foto");
                    Graphics2D bi = (Graphics2D) bufferi.createGraphics();
                    bi.drawImage(javax.imageio.ImageIO.read(imagenes.getBinaryStream()), 0, 0, null);
                    foto = bufferi;
                } catch (IOException ex) {
                    System.out.println("ERROR CARGANDO LA FOTO DE LA BASE DE DATOS");
                    Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }else{
                    Graphics2D bi = (Graphics2D) bufferi.createGraphics();
                    bi.drawImage(new javax.swing.ImageIcon(getClass().getResource("/actualizacion_de_datos/imagenes/no_foto.png")).getImage(),0,0,null);                    
                    foto = bufferi;
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    


}
    
 
public void cargar_causales(Connection con, String cedula){
    causales.clear();
    try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
          //buscando cancelaciones
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.cancelacion_retiros WHERE cedula='"+cedula+"';");
            while(resultado.next()){                
                causales.add(resultado.getString("causa"));// 0
                causales.add(resultado.getString("periodo"));// 1                
            }
            sentencia.close();
            resultado.close();            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
}
    
public void cargar_reingresos(Connection con, String cedula){

    reingresos.clear();
    try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
          //buscando cancelaciones
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.reingresos WHERE cedula='"+cedula+"';");
            while(resultado.next()){           
                System.out.println("CARGANDO REINGRESOS: "+resultado.getString("justificacion")+" - "+resultado.getString("periodo"));
                reingresos.add(resultado.getString("justificacion"));// 0
                reingresos.add(resultado.getString("periodo"));// 1                
            }
            sentencia.close();
            resultado.close();            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



}    

/** Este metodo compara los causales de cancelacion y retiros versus los reingresos. 
 Solo si el causal posee reingreso se devolvera una salidad true sino false. Ademas se llena 
 un arreglo para otros analisis. LinkedList<String> asociacion*/
public boolean comparaciones(){
boolean coincidencia=false;
boolean justificado=true;
asociacion.clear();    
    
   if(causales.isEmpty()){   
       System.out.println("ESTE ESTUDIANTE NO TIENE NI RETIROS NI HA SIDO CANCELADO");
   } else{
       for(int i=0; i<causales.size(); i=i+2){//recorriendo los causales de cancelacion retiros
            coincidencia=false; //reseteando por cada busqueda
            
            
           for(int r=0 ; r<reingresos.size(); r=r+2){//recorriendo los reingresos
           
               if(causales.get(i).equalsIgnoreCase(reingresos.get(r)) //comparando causa retiro_cancelacion y justificacion reingreso                 
                  ){ 
                  asociacion.add(causales.get(i));//guardando causal
                  asociacion.add(causales.get(i+1));//guardando causal periodo
                  asociacion.add(reingresos.get(r));//guardando justificacion
                  asociacion.add(reingresos.get(r+1));//guardando justificacion periodo                   
                  coincidencia = true; 
                  //justificado=true; //esto es para otro proceso de interes donde se dice que existe reingreso para este causal
                  break;
               }
               
           }//fin recorrido reingresos
       
           
           
          if(coincidencia==false){//Si no encontro reingresos para los causales de retiros cancelacion entonces se añaden pero sin reingresos porque no tienen
              asociacion.add(causales.get(i));//guardando causal
              asociacion.add(causales.get(i+1));//guardando causal periodo
              asociacion.add(null);//guardando justificacion
              asociacion.add(null);//guardando justificacion periodo                             
              justificado = false; //el causal no tiene reingreso. esto es usado para los procedimientos de iactivaciones
          } 
        
       }//fin recorrido causales

       
    //verificando que si hay algun reingreso sobrante
       coincidencia = false;
       for(int x=0; x<reingresos.size(); x=x+2){//recorriendo reingresos existentes para ver si hay alguno sin pareja que no deberia pasar
           coincidencia = false;
           for(int c=0; c<causales.size(); c=c+2){//revisando motivos de cancelacion
               if(reingresos.get(x).equalsIgnoreCase(causales.get(c))){coincidencia=true; break;}//mientras el reingreso tenga pareja sin novedad 
               else coincidencia=false;//pero si al final de la revision de las cancelaciones el reingreso no tiene pareja es un detalle a mostrar
           }  
           System.out.println("reingreso: "+reingresos.get(x)+" coincidencia: "+coincidencia);
         if(coincidencia==false){//se muestra el reingreso haciendo enfasis que no tiene causal que lo justifica asi que deberia ser borrado. un causa un reingreso es la ley
             System.out.println("AÑADIENDO falsos");
              asociacion.add("se sugiere eliminar este reingreso porque no tiene causal que lo justifique");//guardando causal
              asociacion.add("eliminar");//guardando causal periodo
              asociacion.add(reingresos.get(x));//guardando justificacion
              asociacion.add(reingresos.get(x+1));//guardando justificacion periodo 
         }  
       }//fin recorrido reingreso
       
       
   }
   
   return justificado;
}


/**Este metodo es para discriminar por color atributos visualmente el historial del estudiante */
private Object[] adecuacion_informacion(LinkedList<String> x, int a){
    Object [] informacion = null;
    
   if(asociacion.get(a)!=null && asociacion.get(a+2)!=null){//si tiene una cancelacion y tiene su reingreso no hay problema       
           informacion = new Object[]{
            ct.atributos_texto(asociacion.get(a),"#A2B800", true, false, false),
            ct.atributos_texto(asociacion.get(a+1),"#A2B800", true,false, false),
            ct.atributos_texto(asociacion.get(a+2),"#A2B800", true,false, false),
            ct.atributos_texto(asociacion.get(a+3),"#A2B800", true, false, false)      
           };
   }
    
   if(asociacion.get(a)!=null && asociacion.get(a+2)==null){//si tiene una cancelacion sin reingreso hay problema       
           informacion = new Object[]{
            ct.atributos_texto(asociacion.get(a),"#EB001C", true, true, false),
            ct.atributos_texto(asociacion.get(a+1),"#EB001C", true, true, false),
            asociacion.get(a+2),
            asociacion.get(a+3)      
           };
   }
   
   if(asociacion.get(a)==null && asociacion.get(a+2)!=null){//si no tiene cancelacion pero hay un reingreso sin justificacion hay un detalle no mayor      
           informacion = new Object[]{
            asociacion.get(a),
            asociacion.get(a+1),
            ct.atributos_texto(asociacion.get(a+2),"#6C007A", true, true, true),
            ct.atributos_texto(asociacion.get(a+3),"#6C007A", true, true, true)      
           };
   }
   

return informacion;
}


public void llenar_historial(JTable tabla, JLabel fotico, String cedula){
 /*   historial.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {
        {null, null, null, null}
    },
    new String [] {
        "CAUSAS", "PERIODO", "REINGRESO", "PERIODO"
    }
));
  */  
       
    this.buscar_usuario(new conexion_base_de_datos().getConexion(), cedula);
    if(estudiante!=null){//solo si el estudiante existe se procesa
        this.cargar_causales(new conexion_base_de_datos().getConexion(), cedula);
        this.cargar_reingresos(new conexion_base_de_datos().getConexion(), cedula);
        this.comparaciones();    

        fotico.setIcon(new ImageIcon(foto.getScaledInstance(192, 240, 2)));

        DefaultTableModel dtm = new DefaultTableModel(new String[][]{}, new String[]{"CAUSAS","PERIODO","REINGRESO","PROCESADO"});
        tabla.setModel(dtm);

        for(int a=0; a<asociacion.size(); a=a+4){//recorriendo las asociaciones realizadas
            //original
            dtm.addRow(new Object[]{asociacion.get(a),
                                    asociacion.get(a+1),
                                    asociacion.get(a+2),
                                    asociacion.get(a+3)});
            
            //dtm.addRow(this.adecuacion_informacion(asociacion, a));
            
        }//fin recorrido


        ct.configuracion(tabla,dtm,"CAUSAS",400,"izquierda");
        ct.configuracion(tabla,dtm,"PERIODO",100,"centrado");
        ct.configuracion(tabla,dtm,"REINGRESO",400,"izquierda");
        ct.configuracion(tabla,dtm,"PROCESADO",100,"centrado");
        
        if(asociacion.isEmpty())ima.mensaje_informacion("Este estudiante no tiene antecedentes\n de Cancelacion ni de Retiros...!!!", "HISTORIAL", "antecedentes", "png");
    }else{
        ima.mensaje_informacion("Este estudiante no existe en el sistema...!!!", "NO EXISTE", "no_existe", "png");
    }
   /* 
    ct.configuracion_alternativa(tabla,dtm,"CAUSAS",150,"izquierda");
    ct.configuracion_alternativa(tabla,dtm,"PERIODO",15,"centrado");
    ct.configuracion_alternativa(tabla,dtm,"REINGRESO",150,"izquierda");
    ct.configuracion_alternativa(tabla,dtm,"PERIODO",15,"centrado");
*/

}

private void eliminacion_reingreso(Connection con,String cedula ,String justificacion, String procesado){
        try {
            Statement sentencia=null;
            
            sentencia = con.createStatement();
            sentencia.execute("DELETE FROM control_de_estudio.reingresos WHERE cedula='"+cedula+"' AND justificacion='"+justificacion+"' AND periodo='"+procesado+"';");
            ima.mensaje_informacion("El reingreso seleccionado ha sido eliminado...!", "ELIMINACIÓN REINGRESO", "eli_auto", "png");
            
            sentencia.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ENVIANDO INFORMACIÓN A LA BASE DE DATOS..!!!\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    
    
}


private void eliminar(JTable tabla, String cedula){

    if(tabla.getSelectedRow()<0){//es un valor negativo porque no hay elemento seleccionado de la tabla
        ima.mensaje_informacion("Debe seleccionar un registro primero", "ADVERTENCIA", "precaucion", "png");
    }else{
        System.out.println("ELiminando reingreso: "+tabla.getValueAt(tabla.getSelectedRow(), 2));
        if(tabla.getValueAt(tabla.getSelectedRow(), 2)!=null ){//eliminando el reingreso solo si existe
            System.out.println("ELiminando reingreso: procesando");
                     this.eliminacion_reingreso(new conexion_base_de_datos().getConexion(),
                                                cedula,
                                                tabla.getValueAt(tabla.getSelectedRow(), 2).toString(),//columna reingreso de la tabla
                                                tabla.getValueAt(tabla.getSelectedRow(), 3).toString()//columna procesado
                                                );    
        }else{//en caso de que no tenga reingreso se da una advertencia
            ima.mensaje_informacion("Este causal de cancelacion no tiene reingreso por lo que la eliminación\n"
                                    + "de la misma no se puede realizar.", "ADVERTENCIA", "precaucion", "png");        
        }
    
    }
    
    
}


private void ingresar_reingreso(Connection con, String cedula, String causal, String periodo,String usuario){
    Statement sentencia=null;
    
    try {
                        
            sentencia = con.createStatement();
            sentencia.execute("INSERT INTO control_de_estudio.reingresos VALUES( NULL,'"+cedula+"','"+causal+"','"+periodo+"','"+usuario+"',DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r') ) ;");
            ima.mensaje_informacion("El reingreso ha sido realizado con exito...!", "NUEVO REINGRESO", "reingreso", "png");
            
            sentencia.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ENVIANDO INFORMACIÓN A LA BASE DE DATOS..!!!\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


}

private void añadir(JTable tabla, String cedula, String periodo,String usuario){

    if(tabla.getSelectedRow()<0){//es un valor negativo porque no hay elemento seleccionado de la tabla
        ima.mensaje_informacion("Debe seleccionar un registro primero", "ADVERTENCIA", "precaucion", "png");
    }else{
        if(tabla.getValueAt(tabla.getSelectedRow(), 2)==null){//añadiendo el reingreso solo si no tiene uno                     
             this.ingresar_reingreso(new conexion_base_de_datos().getConexion(), cedula, tabla.getValueAt(tabla.getSelectedRow(), 0).toString(), periodo, usuario);
        }else{
            ima.mensaje_informacion("Disculpe pero este causal ya tiene reingreso\nIntente borrar primero el reingreso para agregar uno nuevo", "ADVERTENCIA", "sugerencia", "png");
        }
        
    }
    

}

public boolean seleccion(JTable tabla, String cedula, String periodo,String usuario){
    String s = null;
    boolean procesar = false;
    
    
    
    if(tabla.getSelectedRow()<0){//es un valor negativo porque no hay elemento seleccionado de la tabla
        ima.mensaje_informacion("Debe seleccionar un registro primero", "ADVERTENCIA", "precaucion", "png");
    }else{
       
        s = ima.selector_generico("QUE DESEA HACER?", "SELECCIÓN DE OPERACIÓN", new Object[]{"--------------","AÑADIR REINGRESO","ELIMINAR REINGRESO"});
    
        if(s.equalsIgnoreCase("--------------")){//no se selecciono nada y no pasa nada
            procesar = false;
        }else{//en caso de algun proceso se ejecuta solo el seleccionado
            procesar = true;
            if(s.equalsIgnoreCase("AÑADIR REINGRESO")) this.añadir(tabla, cedula, periodo, usuario);
            if(s.equalsIgnoreCase("ELIMINAR REINGRESO")) this.eliminar(tabla, cedula);   
        }
        
    }
    
    
return procesar;
}


    
}//fin de la clase


