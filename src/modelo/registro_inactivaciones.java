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
public class registro_inactivaciones {

    private imagenes ima;
    private registro_documentacion rdocu;
    private registro_reingresos rr;
    
    public registro_inactivaciones() {
        ima = new imagenes();
        rdocu = new registro_documentacion();
        rr = new registro_reingresos();
    }
    
    
    /** Si se desea inactivar se coloca "SI", "NO" a la cedula destino*/
    public void inactivando(Connection con, String cedula,String activar){
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
                sentencia.executeUpdate("UPDATE control_de_estudio.todos_alumnos SET activo='"+activar+"' WHERE cedula='"+cedula+"';");
                System.out.println("ACTUALIZANDO INACTIVO");
            
            }else{//se inserta un nuevo registro
                sentencia.executeUpdate("INSERT INTO control_de_estudio.todos_alumnos VALUES (NULL,'"+cedula+"','NO');");
                System.out.println("INGRESANDO NUEVO INACTIVO");
            }
            
            
            
            
            sentencia.close();
            resultado.close();
            
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("Problema al intentar realizar la inactivacion en la base de datos\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_inactivaciones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_inactivaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
}

/**Este metodo determina si un estudiante debe ser inactivado de acuerdo a las cancelaciones,
 retiros, reingresos y documentacion faltante*/    
public void analizando_historial(String cedula){
    String activar="SI";
    System.out.println("ANALSIS HISTORIAL de CEDULA: "+cedula);
    //analizando documentacion. PDP, CS, FNT, CT, NC
        boolean [] x = rdocu.estado_documentacion(new conexion_base_de_datos().getConexion(), cedula);
        System.out.println("documentos: "+x[0]+","+x[2]+","+x[5]+","+x[6]+","+x[7]);
        if(x[0]==false || x[2]==false || x[5]==false || x[6]==false || x[7]==false){ activar="NO"; System.out.println("Analisis DOCUMENTACION: activar:"+activar);}
        
     //analizando retiros y cancelacion vs reingresos   
        rr.cargar_causales(new conexion_base_de_datos().getConexion(), cedula);
        rr.cargar_reingresos(new conexion_base_de_datos().getConexion(), cedula);
        if(rr.comparaciones()==false){activar="NO";System.out.println("Analisis CAUSALES-REINGRESOS: activar:"+activar);}
        
        System.out.println("RESULTADO FINAL. REALIZAR ACTIVACION?: "+activar);

}    
    
    
    
    
    public static void main(String [] args){
    
        registro_inactivaciones rinac = new registro_inactivaciones();
        rinac.analizando_historial("10001820");
    }
    
    
    
    
    
    
}//fin de la clase
