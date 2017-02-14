/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class registro_retiros {
    private imagenes ima;
    
    
    public registro_retiros() {
        ima = new imagenes();
        
    }
    
    
      
    
    
public void ingresar_retiro(Connection con, String cedula, String causal, String periodo,String usuario){
    Statement sentencia=null;
    ResultSet resultado = null;
    boolean continuar=false;
    
    try {
             
            //comprobando si ya el retiro existe en este periodo de trabajo. solo se acepta un retiro por periodo
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.cancelacion_retiros WHERE cedula='"+cedula+"' AND causa regexp 'RETIRO' AND periodo='"+periodo+"';");
            if(resultado.next()){//si existe alguno no se debe agregar otro a menos que el actual se elimine
                ima.mensaje_informacion("Disculpe pero en este periodo "+periodo+", ya existe un retiro:\n"+resultado.getString("causa")+"\nPor favor intente eliminarlo primero", "RETIRO EXISTENTE", "retiro_existente", "png");
                continuar = false; //no se continua con el ingreso
            }else{
                continuar = true; //no existe retiro en la vigencia actual se procede al ingreso
            }
            sentencia.close();
            resultado.close();
        
               
                if(continuar){
                    sentencia = con.createStatement();
                    sentencia.execute("INSERT INTO control_de_estudio.cancelacion_retiros VALUES( NULL,'"+cedula+"','"+causal+"','"+periodo+"','"+usuario+"',DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r') ) ;");
                    ima.mensaje_informacion("Se ha añadido el retiro con exito...!", "AÑADIR RETIRO", "reingreso", "png");

                    sentencia.close();
                }
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ENVIANDO INFORMACIÓN A LA BASE DE DATOS..!!!\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_retiros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_retiros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


}
    
public void eliminacion_retiro(Connection con,String cedula, String procesado){
        try {
            Statement sentencia=null;
            
            sentencia = con.createStatement();
            sentencia.execute("DELETE FROM control_de_estudio.cancelacion_retiros WHERE cedula='"+cedula+"' AND causa REGEXP 'RETIRO' AND periodo='"+procesado+"';");
            ima.mensaje_informacion("El reingreso de este periodo del estudiante ha sido eliminado...!", "ELIMINACIÓN REINGRESO", "eli_auto", "png");
            
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
    
    
    
    
    
    
}//fin de la clase
