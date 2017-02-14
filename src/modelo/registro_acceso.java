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
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author AN
 */
public class registro_acceso {
    
    private imagenes ima;
    private int intentos=0;
    private String eleccion = null;
    private encriptacion encriptar;
    
    public registro_acceso() {
        ima  = new imagenes();
        encriptar = new encriptacion();
    }

    public String getEleccion() {
        return eleccion;
    }

    public void setEleccion(String eleccion) {
        this.eleccion = eleccion;
    }
    
    
    
    
    
/**Este metodo permite obtener rasgos del usuario y su registro se divide en <br>
 * boleano existe = 0,<br>
 * usuario = 1 <br>
 * contraseña = 2<br>
 * nivel_acceso = 3 
 */    
private Object[]  buscar_usuario(Connection con, String cedula){
    boolean existe = false;    
    Object[] informacion = null;
    
        try {
            Statement sentencia =null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM ce_maracay.privilegios WHERE cedula='"+cedula+"';");
            
            if(resultado.next()){ 
                existe = true;
                informacion = new Object[]{
                    existe, //0
                    resultado.getString("usuario"), //1
                    resultado.getString("contraseña"), //2
                    resultado.getString("nivel_acceso") //3
                };
            }else{ 
                ima.mensaje_informacion("Esta persona no existe en el sistema de usuarios!!!", "INFORMACION", "no_existe","png");
                existe = false;                
                informacion = new Object[]{
                    existe, //0
                    null, //1
                    null, //2
                    null //3
                };
            }
            
            
            
            
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("Error en la busqueda en la base de datos..!!!\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
            Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return informacion;
}   
    

private boolean verificacion_nueva_clave(JPasswordField nueva_clave, JPasswordField repetir_clave){
    String nueva="", respaldo=""; 
    boolean iguales = false;        
    
            for(int i=0; i<nueva_clave.getPassword().length; i++){//obteniendo los caracteres de las claves
                nueva = nueva.concat( String.valueOf(nueva_clave.getPassword()[i]) );    
            }//fin recorrido nueva_clave

            for(int i=0; i<repetir_clave.getPassword().length; i++){//obteniendo los caracteres de las claves
                respaldo = respaldo.concat( String.valueOf(repetir_clave.getPassword()[i]) );    
            }//fin recorrido repetir_clave

    
    if(respaldo.equals( nueva ))iguales = true; else iguales = false;//solo si son iguales se procede
    
        //System.out.println("NUEVA: "+nueva+"\nRESPALDO: "+respaldo+"\n    IGUALES: "+iguales);
    
    return iguales;
    
}


private boolean validar_actual_nueva_clave(JPasswordField nueva_clave, String actual){
    boolean coincidencia=false;
    String nueva="";
    
        for(int i=0; i<nueva_clave.getPassword().length; i++){//obteniendo los caracteres de las claves
                nueva = nueva.concat( String.valueOf(nueva_clave.getPassword()[i]) );    
            }//fin recorrido nueva_clave
    
        nueva =  encriptar.conversor(nueva,"SHA-512");
        if(actual.equals( nueva ))coincidencia = true; else coincidencia = false;//solo si son iguales se procede
            
return coincidencia;
}


/**Este metodo es para seleccionar las operaciones de acuerdo a la condicion del usuario
 las salidas son:<br>
 * nuevo usuario <br>
 * modificar usuario <br>
 * eliminar usuario
 */
public String seleccion_opciones(JTextField cedula, JComboBox nivel_acceso, JTextField usuario, JPasswordField clave_actual, JPasswordField clave_nueva, JPasswordField repetir_clave){
    Object [] x = this.buscar_usuario(new conexion_base_de_datos().getConexion(), cedula.getText());
    String clave=null, respuesta_1 = null, respuesta_2=null;
    String opciones = null;
   //ingresar a nuevo usuario 
    if( Boolean.valueOf(x[0].toString()) == false){//si no existe se procede a ingresarlo pero consultando antes
       
        if(ima.confirmacion("Desea ingresarlo al sistema?", "NUEVO USUARIO", "nuevo_usuario", "png") == 0){//se acepto el nuevo usuario           //se impone el nivel de acceso por parte del administrador             
            respuesta_1 = ima.selector_generico("POR FAVOR SELECCIONE EL NIVEL DE ACCESO PARA ESTE NUEVO USUARIO...!!!", "NIVEL DE ACCESO", new Object[]{"administrador","nivel_1","nivel_2","nivel_3","nivel_4","nivel_5","estudiante"});
            nivel_acceso.setSelectedItem(respuesta_1);                      
            cedula.setEnabled(false);
            clave_actual.setEnabled(false);
            opciones = "nuevo usuario";
        }
        
    }else{//el usuario existe y lo que se puede hacer es eliminarlo, o modificar sus registros
        //lo ideal que para modificar o eliminar algun registro se deba contar con la clave actual y el mismo usuario pueda manipular su registro e incluso eliminarlo. el filtro seria su clave actual ya que solo el deberia saberlo
        respuesta_2 = ima.selector_generico("EL USUARIO EXISTE EN EL SISTEMA...!!!\nQUE DESEA HACER?", "MANEJO DE USUARIOS", new Object[]{"MODIFICAR REGISTROS DEL USUARIO","ELIMINAR EL USUARIO"});        
        
        if(respuesta_2.equalsIgnoreCase("MODIFICAR REGISTROS DEL USUARIO")){//se establecen los parametros
            usuario.setText(x[1].toString());
            usuario.setEnabled(false);
            cedula.setEnabled(false);                            
            nivel_acceso.setSelectedItem(x[3].toString());
            opciones = "modificar usuario";
        }
        
        if(respuesta_2.equalsIgnoreCase("ELIMINAR EL USUARIO")){//se ejecuta la operacion de eliminacion del usuario
            usuario.setText(x[1].toString());
            usuario.setEnabled(false);
            cedula.setEnabled(false);                            
            nivel_acceso.setSelectedItem(x[3].toString());
            clave_nueva.setEnabled(false);
            ima.mensaje_informacion("DEBE INGRESAR LA CLAVE ACTUAL PARA ELIMINAR SU USUARIO", "ELIMINACION DE USUARIO", "avisos", "png");
            opciones = "eliminar usuario";
            
        }
        
    }
 
   return opciones; 
    
}


public void nuevo_usuario(Connection con, JTextField cedula, JTextField usuario, JPasswordField actual, JPasswordField nueva, JPasswordField repetida, JComboBox nivel_acceso){
    String contraseña ="";
   // System.out.println("NUEVO USUARIO:: son iguales: "+this.verificacion_nueva_clave(nueva, repetida));
  if(cedula.getText() != null && cedula.getText().length()>=6){  //garantizando que la cedula al menos tenga 6 digitos
      
    if(this.verificacion_nueva_clave(nueva, repetida)){//solo si las claves son coincidentes
        for(int i=0; i<nueva.getPassword().length; i++)contraseña = contraseña.concat( String.valueOf(nueva.getPassword()[i]) );
       // System.out.println("procesando"+contraseña );
        
        try{
            Statement sentencia = null;
            
            sentencia = con.createStatement();
         //   System.out.println("enviando a la base de datos: "+encriptar.conversor(contraseña,"SHA-512") );
            sentencia.execute("INSERT INTO ce_maracay.privilegios VALUES ('"+cedula.getText()+"','"+usuario.getText()+"','"+encriptar.conversor(contraseña,"SHA-512")+"','"+nivel_acceso.getSelectedItem().toString()+"');");
            contraseña = null;
            sentencia.close();
            
            ima.mensaje_informacion("Los datos han sido ingresado con exito...!!!", "INGRESO NUEVO USUARIO","exito","png");
            
            cedula.setText(null);
            cedula.setEnabled(true);
            usuario.setText(null);
            actual.setText(null);
            actual.setEnabled(true);
            nueva.setText(null);
            repetida.setText(null);
            nivel_acceso.setSelectedIndex(0);
    
        } catch (SQLException ex) {
            ima.mensaje_informacion("Error en la busqueda en la base de datos..!!!\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
            Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }else{//en caso de que las contraseñas no coincidan entonces solo se da un aviso
    
        ima.mensaje_informacion("LAS CLAVES QUE HA COLOCADO NO COINCIDE, POR FAVOR INTENTE OTRA VEZ", "CLAVES INCOMPATIBLES", "avisos", "png");
        nueva.setText(null);
        repetida.setText(null);
    }

  }
}

private void modificar_usuario(Connection con, String cedula, String contraseña_nueva){
    
    
            try{
                    Statement sentencia =null;

                    sentencia = con.createStatement();
                    sentencia.executeUpdate("UPDATE ce_maracay.privilegios SET contraseña='"+encriptar.conversor(contraseña_nueva,"SHA-512")+"' WHERE cedula='"+cedula+"';");           

                    ima.mensaje_informacion("LOS DATOS HAS SIDO MODIFICADOS CON EXITO!!!", "INFORMACION", "exito","png");
            
                    sentencia.close();

                } catch (SQLException ex) {
                    ima.mensaje_informacion("Error en la atualizacion de la base de datos..!!!","ERROR CONEXION BASE DE DATOS","error", "png");
                    Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
   
    
}

public void modificacion(Connection con, JTextField cedula, JTextField usuario, JComboBox nivel_acceso,JPasswordField actual, JPasswordField nueva, JPasswordField repetida ){
    Object [] x = this.buscar_usuario(new conexion_base_de_datos().getConexion(), cedula.getText());
    String nueva_clave="";
    
  if( this.validar_actual_nueva_clave(actual, x[2].toString())){ //comparando la clave actual con la recuperada
    
    
        if(this.verificacion_nueva_clave(nueva, repetida)){   //comparando la clave nueva y la repetida para compatibilidad
            for(int i=0; i<nueva.getPassword().length; i++){//obteniendo los caracteres de las claves
                nueva_clave = nueva_clave.concat( String.valueOf(nueva.getPassword()[i]) );    
            }//fin recorrido nueva_clave

            this.modificar_usuario(con, cedula.getText(), nueva_clave);
            intentos = 0;//reiniciando el contador
            cedula.setText(null);
            actual.setText(null);
            nueva.setText(null);
            repetida.setText(null);
            nivel_acceso.setSelectedIndex(0);
            usuario.setText(null);
            System.exit(0);
        }else{
            ima.mensaje_informacion("LAS CLAVES QUE HA COLOCADO EN (NUEVA CLAVE) y (REPETIR CLAVE) NO COINCIDE, POR FAVOR INTENTE OTRA VEZ", "CLAVES INCOMPATIBLES", "avisos", "png");
            nueva.setText(null);
            repetida.setText(null);        
            intentos = intentos + 1;
        }
    
  }else{
      ima.mensaje_informacion("LAS CONTRASEÑA QUE COLOCO EN (CLAVE ACTUAL) NO COINCIDE CON LA UTILIZABA ANTERIORMENTE, POR FAVOR INTENTE OTRA VEZ", "CLAVES INCOMPATIBLES", "avisos", "png");
      actual.setText(null);
      intentos = intentos + 1;//numero de intentos realizados
  }

  
  if(intentos >=3){
      ima.mensaje_informacion("MAXIMOS INTENTOS ALCANZADOS\nCERRANDO EL SISTEMA POR SEGURIDAD...!!!", "CIERRE FORZOZO", "alto", "png");
      System.exit(0);//cerrando el sistema
  }
  
  
  
}



private void eliminar_usuario(Connection con, String cedula){
            try{
                    Statement sentencia =null;

                    sentencia = con.createStatement();
                    sentencia.executeUpdate("DELETE FROM ce_maracay.privilegios WHERE cedula='"+cedula+"';");           

                    ima.mensaje_informacion("EL USUARIO HA SIDO BORRADO CON EXITO!!!", "INFORMACION", "exito","png");

                    sentencia.close();

                } catch (SQLException ex) {
                    ima.mensaje_informacion("Error en la atualizacion de la base de datos..!!!","ERROR CONEXION BASE DE DATOS","error", "png");
                    Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(registro_acceso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

}



public void eliminaciones(Connection con, JTextField cedula, JPasswordField actual){
    Object [] x = this.buscar_usuario(new conexion_base_de_datos().getConexion(), cedula.getText());
    if( this.validar_actual_nueva_clave(actual, x[2].toString()) ){ //comparando la clave actual con la recuperada
        this.eliminar_usuario(con, cedula.getText());
        System.exit(0);
        intentos = 0;
        actual.setText(null);
        cedula.setText(null);
    }else{
        
      ima.mensaje_informacion("LAS CONTRASEÑA QUE COLOCO EN (CLAVE ACTUAL) NO COINCIDE CON LA UTILIZABA ANTERIORMENTE, POR FAVOR INTENTE OTRA VEZ", "CLAVES INCOMPATIBLES", "avisos", "png");
      actual.setText(null);
      intentos = intentos + 1;//numero de intentos realizados
      
    
    }    
    
    if(intentos >=3){
      ima.mensaje_informacion("MAXIMOS INTENTOS ALCANZADOS\nCERRANDO EL SISTEMA POR SEGURIDAD...!!!", "CIERRE FORZOZO", "alto", "png");
      System.exit(0);//cerrando el sistema
  }
    
    
}



public static void main(String [] args){
    registro_acceso ra = new registro_acceso();
    ra.verificacion_nueva_clave(new JPasswordField("proTon"), new JPasswordField("proTon"));

}    
    
    
}//fin de la clase
