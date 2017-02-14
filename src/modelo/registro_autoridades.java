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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.autoridades;

/**
 *
 * @author AN
 */
public class registro_autoridades {

    private String cargo,grado,nombres,apellidos,iniciales, cedula ;
    private boolean encontrado=false;
    private imagenes ima;
    private LinkedList<String> autoridades = new LinkedList<>();

    public registro_autoridades(){
         ima = new imagenes();
    }

    public registro_autoridades(String cedula,String cargo, String grado, String nombres, String apellidos, String iniciales) {
        this.cedula = cedula;
        this.cargo = cargo;
        this.grado = grado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.iniciales = iniciales;
       ima = new imagenes();
    }





    public void nueva_autoridad(Connection con){

        Statement sentencia = null;
        try {
            sentencia = con.createStatement();
            sentencia.execute("INSERT INTO control_de_estudio.autoridades VALUES ('"+cedula+"','"+cargo+"','"+grado+"','"+nombres+"','"+apellidos+"','"+iniciales+"');");
            sentencia.close();
            ima.mensaje_informacion("AUTORIDAD GUARDADA CON EXITO", "NOTIFICACION GUARDADO", "exito", "png");

        } catch (SQLException ex) {
            ima.mensaje_informacion("EXISTE ERROR EN LA COMUNICACION CON LA BASE DE DATOS,\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




    }

 public void modificar_autoridad(Connection con){

    
        Statement sentencia = null;
        try {
            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.autoridades SET cargo='"+cargo+"', grado='"+grado+"', nombres='"+nombres+"', apellidos='"+apellidos+"', iniciales='"+iniciales+"' WHERE cedula='"+cedula+"';");
            sentencia.close();
            ima.mensaje_informacion("AUTORIDAD MODIFICADA CON EXITO", "NOTIFICACION GUARDADO", "exito", "png");

        } catch (SQLException ex) {
            ima.mensaje_informacion("EXISTE ERROR EN LA COMUNICACION CON LA BASE DE DATOS,\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
     
     

    }

  public void buscar_autoridad(Connection con){

        Statement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = con.createStatement();
            resultado=sentencia.executeQuery("SELECT * FROM control_de_estudio.autoridades WHERE cedula='"+this.cedula+"';");
            if(resultado.next()){//si encuentra algun registro es porque existe
                this.cedula =resultado.getString("cedula"); ;
                this.cargo = resultado.getString("cargo");
                this.grado = resultado.getString("grado");
                this.nombres = resultado.getString("nombres");
                this.apellidos = resultado.getString("apellidos");
                this.iniciales = resultado.getString("iniciales");
                this.encontrado=true;
            }else{
                this.encontrado=false;
                ima.mensaje_informacion("AUTORIDAD NO EXISTE..!!!", "NOTIFICACION GUARDADO", "no_existe", "png");
            }



            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("EXISTE ERROR EN LA COMUNICACION CON LA BASE DE DATOS,\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




    }

public void eliminar_autoridad(Connection con){

    if(encontrado){
        Statement sentencia = null;
        try {
            sentencia = con.createStatement();
            sentencia.executeUpdate("DELETE FROM control_de_estudio.autoridades  WHERE cedula='"+cedula+"';");
            sentencia.close();
            ima.mensaje_informacion("AUTORIDAD ELIMINADA CON EXITO", "NOTIFICACION ELIMINACION", "exito", "png");

        } catch (SQLException ex) {
            ima.mensaje_informacion("EXISTE ERROR EN LA COMUNICACION CON LA BASE DE DATOS,\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//fin verificacion si la cedula existe
    else{
        ima.mensaje_informacion("AUTORIDAD NO SE PUEDE DESINCORPORAR PORQUE NO EXISTE", "NOTIFICACION ELIMINACION", "no_existe", "png");
    }
    }



  public void busqueda_general(Connection con, JTable tabla){

        boolean respuesta=false;
        Statement sentencia = null;
        ResultSet resultado = null;
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"CEDULA","CARGO","GRADO","NOMBRES","APELLIDOS","INICIALES"}, 0);
        tabla.setModel(modelo);
        

        try {
            sentencia = con.createStatement();
            resultado=sentencia.executeQuery("SELECT * FROM control_de_estudio.autoridades;");
            while(resultado.next()){//si encuentra algun registro es porque existe
                Object[] informacion ={
                    resultado.getString("cedula"),
                    resultado.getString("cargo"),
                    resultado.getString("grado"),
                    resultado.getString("nombres"),
                    resultado.getString("apellidos"),        
                    resultado.getString("iniciales")
                };
                modelo.addRow(informacion);
                
                respuesta=true;
            }

            if(respuesta==false){
               
                ima.mensaje_informacion("NO EXISTEN REGISTROS AUN DE AUTORIDADES", "NOTIFICACION", "no_hay", "png");

            }else{
                ima.mensaje_informacion("BUSQUEDA COMPLETADA...!!!", "NOTIFICACION", "finalizar", "png");
            }

            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("EXISTE ERROR EN LA COMUNICACION CON LA BASE DE DATOS,\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

  /**Metodo para precargar las autoridades de interes */
public void lista_autoridades(Connection con){
        boolean respuesta=false;
        autoridades.clear();
        Statement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.createStatement();
            resultado=sentencia.executeQuery("SELECT * FROM control_de_estudio.autoridades;");
            while(resultado.next()){//si encuentra algun registro es porque existe
               autoridades.add( resultado.getString("cedula"));//0
               autoridades.add( resultado.getString("cargo"));//1
               autoridades.add( resultado.getString("grado"));//2
               autoridades.add( resultado.getString("nombres"));//3
               autoridades.add( resultado.getString("apellidos"));//4
               autoridades.add( resultado.getString("iniciales"));//5
                respuesta=true;
            }

            if(respuesta==false){

                ima.mensaje_informacion("NO EXISTEN REGISTROS AUN DE AUTORIDADES", "NOTIFICACION", "no_hay", "png");

            }else{
              
            }

            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("EXISTE ERROR EN LA COMUNICACION CON LA BASE DE DATOS,\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_autoridades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}

/**Este metodo entrega la autoridad de interes y sus datos<br>
 cedula:cargo:grado:nombres:apellidos:iniciales, por lo que se puede obtener individualemente
 con un SPLIT de la cadena de caracteres*/
public String autoridad(String autoridad){
    String texto=null;

    for(int i=0; i<autoridades.size(); i=i+6){//recorriendo el registro
        if(autoridad.equalsIgnoreCase(autoridades.get(i+1))){//comparando el cargo de interes

            texto=autoridades.get(i)+":"+autoridades.get(i+1)+":"+autoridades.get(i+2)+":"+autoridades.get(i+3)+":"+autoridades.get(i+4)+":"+autoridades.get(i+5);

          break;
        }else{
            texto="No Existe:No Existe:No Existe:No Existe:No Existe:No Existe";
        }

    }//fin recorrido registro

return texto;
}



public void cargando_datos(autoridades a){

    a.getCargo().setSelectedItem(cargo);
    a.getGrado().setText(grado);
    a.getNombres().setText(nombres);
    a.getApellidos().setText(apellidos);
    a.getIniciales().setText(iniciales);

}

public void limpiar_datos(autoridades a){
    a.getCedula().setText("");
    a.getCargo().setSelectedIndex(0);
    a.getGrado().setText("");
    a.getNombres().setText("");
    a.getApellidos().setText("");
    a.getIniciales().setText("");

}








}//fin de la clase
