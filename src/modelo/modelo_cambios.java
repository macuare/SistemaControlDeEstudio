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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import vista.cambios;


/**
 *
 * @author AN
 */
public class modelo_cambios {

    private imagenes ima;

    public modelo_cambios() {
        ima = new imagenes();
    

    }





    public void buscar(Connection con, String cedula, JTextField carrera_actual, JTextField turno_actual, JLabel identificiacion, JButton cambiar){

        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT p.nombres, p.apellidos, d.carrera, d.turno FROM control_de_estudio.datos_academicos d, control_de_estudio.datos_personales p where d.cedula='"+cedula+"' and d.cedula=p.cedula;");
            resultado.next();
            System.out.println("filas: "+resultado.getRow());

                if(resultado.getRow()>0){
                    carrera_actual.setText(resultado.getString("d.carrera"));
                    turno_actual.setText(resultado.getString("d.turno"));
                    identificiacion.setText(resultado.getString("p.nombres")+","+resultado.getString("p.apellidos"));
                    cambiar.setEnabled(true);
                }else{

                    ima.mensaje_informacion("ESTUDIANTE NO ENCONTRADO", "NOTIFICACION", "no_existe", "png");
                    
                    carrera_actual.setText(null);
                    turno_actual.setText(null);
                    identificiacion.setText(null);
                    cambiar.setEnabled(false);
                }

            sentencia.close();
            resultado.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(modelo_cambios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void cambiar(Connection con, String cedula, String carrera, String turno, String carrera_anterior, String turno_anterior){
        Statement sentencia = null;
        
       if( ima.confirmacion("REALMENTE DESEA REALIZAR LA MODIFICACION:\n" //validando que realmente se quiera hacer la modificacion
                       +"("+carrera_anterior+") ----> |"+carrera+"|\n"+
                        "("+turno_anterior+") -----> |"+turno+"|","CONFIRMACION", "pregunta", "png")==0){

        try {


            sentencia = con.createStatement();
          int x=  sentencia.executeUpdate("UPDATE control_de_estudio.datos_academicos SET "
                                    + "carrera ='" + carrera + "',"
                                    + "turno ='" + turno + "' WHERE cedula='"+cedula+"' ;");

          System.out.println("resp: "+x);

            if(x>=1){ima.mensaje_informacion("EL REGISTRO FUE MODIFICADO CON EXITO!!!","NOTIFICACION","exito", "png");}
            else{ima.mensaje_informacion("NO SE HA REALIZADO NINGUNA MODIFICACION\nPORQUE EL ESTUDIANTE NO EXISTE. VER CEDULA","REVISAR","avisos", "png");}
            sentencia.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(modelo_cambios.class.getName()).log(Level.SEVERE, null, ex);
            ima.mensaje_informacion("HUBO UN ERROR AL INTENTAR GUARDAR LA INFORMACION EN LA BASE DE DATOS\n"+ex.getMessage(), "ERROR ENVIO", "no_hay", "png");
        }
        }//fin validacion

    }



public void estructurador_mensajes(String mensaje){
    String restructurado=mensaje;
    
    if(mensaje.length()>50){
      
        
    }


}






}
