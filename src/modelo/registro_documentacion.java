/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 *
 * @author AN
 */
public class registro_documentacion {
    private String estudiante,carrera,turno,cedula;
    private imagenes ima;

    public registro_documentacion() {
        ima = new imagenes();
       
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
    
    
    
    
    
    public void buscar_usuario(Connection con, String cedula){
        
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            
          //buscando su nombre apellido  carrera turno
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_personales WHERE cedula='"+cedula+"';");
            if(resultado.next()){
                this.cedula=cedula ;
                this.estudiante = resultado.getString("nombres")+", "+resultado.getString("apellidos");
            }else{this.estudiante = null;}
            
            sentencia.close();
            resultado.close();
            
          //buscando carrera y turno            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='"+cedula+"';");
            if(resultado.next()){
                this.carrera = resultado.getString("carrera");
                this.turno = resultado.getString("turno");
            }else{this.carrera=null; this.turno=null;}
            sentencia.close();
            resultado.close();
       
            
            if(this.estudiante==null || this.estudiante.isEmpty())ima.mensaje_informacion("Este estudiante no existe...!!!", "SIN REGISTRO", "no_existe", "png");
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("Error en la busqueda del estudiante en la base de datos..!!!\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
            Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    


}
    
 
 private void ingresar_nuevo(Connection con, String cedula, boolean pdp, boolean fc, boolean cs, boolean ccinu, boolean pi_cnu_rusnieus, boolean fnt, boolean ct, boolean nc, boolean crm, String usuario){
 
     if(this.cedula.equalsIgnoreCase(cedula)){//solo si la cedula que se coloco en el momento de buscar al estudiante es la misma que se usa para guardar entonces se puede proceder
        try {
                Statement sentencia = null;
//18230384

            //actualizando documentacion
                sentencia = con.createStatement();
                sentencia.execute("INSERT INTO control_de_estudio.documentos  VALUES('"+cedula+"', "+pdp+", "+fc+", "+cs+", "+ccinu+", "+pi_cnu_rusnieus+", "+fnt+", "+ct+", "+nc+", "+crm+", '"+usuario+"', DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r') );");

                sentencia.close();

                ima.mensaje_informacion("Los datos han sido ingresados con exito...!!!", "ACTUALIZACIÓN DOCUMENTACIÓN","exito","png");

            } catch (SQLException ex) {
                ima.mensaje_informacion("Error en ingreso de registros del estudiante en la base de datos..!!! \n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     }else{//en caso contrario hay una violacion de seguridad. se intento buscar con una cedula y guardar con otra
         ima.mensaje_informacion("SE HA INTENTADO UN PROCEDIMIENTO NO CONTEMPLADO !\n VERIFIQUE QUE LA CEDULA SEA LA MISMA QUE USO CUANDO BUSCO AL ESTUDIANTE.\nINTENTO DE VIOLACIÓN DE SEGURIDAD BLOQUEADA", "SEGURIDAD", "seguridad", "png");
     
     }
     
 }   
    
    
 private void actualizar_documentacion(Connection con, String cedula, boolean pdp, boolean fc, boolean cs, boolean ccinu, boolean pi_cnu_rusnieus, boolean fnt, boolean ct, boolean nc, boolean crm, String usuario){
     
     
     if(this.cedula.equalsIgnoreCase(cedula)){//solo si la cedula que se coloco en el momento de buscar al estudiante es la misma que se usa para guardar entonces se puede proceder
        try {
                Statement sentencia = null;


            //actualizando documentacion
                sentencia = con.createStatement();
                sentencia.executeUpdate("UPDATE control_de_estudio.documentos SET "+
                                    "pdp="+pdp+", "+
                                    "fc="+fc+", "+
                                    "cs="+cs+", "+
                                    "ccinu="+ccinu+", "+
                                    "pi_cnu_rusnieus="+pi_cnu_rusnieus+", "+
                                    "fnt="+fnt+", "+
                                    "ct="+ct+", "+
                                    "nc="+nc+", "+
                                    "crm="+crm+", "+
                                    "usuario='"+usuario+"', "+
                                    "fecha=DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r') "
                                    + "WHERE cedula='"+cedula+"';");

                sentencia.close();

                ima.mensaje_informacion("Los datos han sido actualizados con exito...!!!", "ACTUALIZACIÓN DOCUMENTACIÓN","exito","png");

            } catch (SQLException ex) {
                ima.mensaje_informacion("Error en actualizacion de registro del estudiante en la base de datos..!!! \n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     }else{//en caso contrario hay una violacion de seguridad. se intento buscar con una cedula y guardar con otra
         ima.mensaje_informacion("SE HA INTENTADO UN PROCEDIMIENTO NO CONTEMPLADO !\n VERIFIQUE QUE LA CEDULA SEA LA MISMA QUE USO CUANDO BUSCO AL ESTUDIANTE.\nINTENTO DE VIOLACIÓN DE SEGURIDAD BLOQUEADA", "SEGURIDAD", "seguridad", "png");
     
     }
 }   
    
 private boolean verificar_que_exista(Connection con, String cedula){
     boolean existe=false;
     ResultSet r=null;
     Statement s=null;
           
     try{
        s = con.createStatement();
        r = s.executeQuery("SELECT * FROM control_de_estudio.documentos WHERE cedula='"+cedula+"';");
        if(r.next()){
           existe = true;
        }else{
           existe = false;
        }
     
        s.close();
        r.close();
        
     }catch(SQLException ex){
        ima.mensaje_informacion("Error en actualizacion de registro del estudiante en la base de datos..!!! \n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
        Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
     }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_documentacion.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
        
     
     return existe;
             
 }
 
 public void procesar(Connection con, String cedula, boolean pdp, boolean fc, boolean cs, boolean ccinu, boolean pi_cnu_rusnieus, boolean fnt, boolean ct, boolean nc, boolean crm, String usuario){

     if(this.verificar_que_exista(new conexion_base_de_datos().getConexion(), cedula))
        this.actualizar_documentacion(con, cedula, pdp, fc, cs, ccinu, pi_cnu_rusnieus, fnt, ct, nc, crm, usuario);
     else
        this.ingresar_nuevo(con, cedula, pdp, fc, cs, ccinu, pi_cnu_rusnieus, fnt, ct, nc, crm, usuario);
     
     
 }
 
 /**pdp=planilla datos personales, fc=foto carnet, cs=certificado de salud, ccinu=constancia cinu<br>
    pi_cnu_rusnieus=planilla inscripcion CNU o RUSNIEUS, fnt=fondo negro titulo, ct=fotocopia titulo<br>
    nc=notas certificadas, crm=copia registro militar*/
 public boolean[] estado_documentacion(Connection con, String cedula){
     boolean [] condiciones={};
     try {
            Statement sentencia = null;
            ResultSet r = null;
            
          //buscando su nombre apellido  carrera turno
            sentencia = con.createStatement();
            r = sentencia.executeQuery("SELECT * FROM control_de_estudio.documentos WHERE cedula='"+cedula+"';");
            if(r.next()){
                condiciones =new boolean[]{r.getBoolean("pdp"),
                                           r.getBoolean("fc"),
                                           r.getBoolean("cs"),
                                           r.getBoolean("ccinu"),
                                           r.getBoolean("pi_cnu_rusnieus"),
                                           r.getBoolean("fnt"),
                                           r.getBoolean("ct"),
                                           r.getBoolean("nc"),
                                           r.getBoolean("crm")                   
                                            };
                
            }
            
            
            
            sentencia.close();
            r.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_reingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
 return condiciones;
 
 }   
    
 public void marcando(boolean [] condiciones, JCheckBox pdp, JCheckBox fc, JCheckBox cs, JCheckBox ccinu, JCheckBox pi_cnu_rusnieus, JCheckBox fnt, JCheckBox ct, JCheckBox nc, JCheckBox crm, JButton procesar){
     
     if(condiciones.length<=0){//no existe registro de documentos entregados del estudiante
        ima.mensaje_informacion("Este estudiante no tiene documentos ni registros..!!!\nREVISAR SITUACIÓN","NO POSEE HISTORIAL","error", "png");
        pdp.setSelected(false); fc.setSelected(false);cs.setSelected(false);ccinu.setSelected(false);
        pi_cnu_rusnieus.setSelected(false); fnt.setSelected(false);ct.setSelected(false);
        nc.setSelected(false);crm.setSelected(false);
        procesar.setEnabled(true);
     }else{//se llenan los elementos con los respetivos registros de interes
     
                      pdp.setSelected(condiciones[0]);
                      fc.setSelected(condiciones[1]);
                      cs.setSelected(condiciones[2]);
                      
                      ccinu.setSelected(condiciones[3]);
                      pi_cnu_rusnieus.setSelected(condiciones[4]);
                      fnt.setSelected(condiciones[5]);
                      
                      ct.setSelected(condiciones[6]);
                      nc.setSelected(condiciones[7]);
                      crm.setSelected(condiciones[8]);
                      procesar.setEnabled(true);
     }           
 }
 
    
    
}//fin de la clase
