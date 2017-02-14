/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class codigos_actualizar {

    public codigos_actualizar() {
    }
    
    
    
    private void proceder(Connection con, String pensum){
    
        Statement sentencia = null;
        
        try {

            sentencia = con.createStatement();
                if(pensum.contains("2007")){//solo se actualiza los semestres de los codigos 2007
                    sentencia.executeUpdate("UPDATE control_de_estudio.cem_notas_alumnos_todas_carreras_2007 n, pensum."+pensum+" x "+
                                        "SET n.TERMINO = x.semestre "+
                                        "WHERE n.CODMAT = x.codigo;");
                    System.out.println("vigencia 2007");
                }
                if(pensum.contains("2009")){//solo se actualiza los semestres de los codigos 2009
                    sentencia.executeUpdate("UPDATE control_de_estudio.cem_notas_alumnos_todas_carreras_2009 n, pensum."+pensum+" x "+
                                        "SET n.TERMINO = x.semestre "+
                                        "WHERE n.CODMAT = x.codigo;");
                    System.out.println("vigencia 2009");
                }
                if(pensum.contains("2010")){//solo se actualiza los semestres de los codigos 2010
                    sentencia.executeUpdate("UPDATE control_de_estudio.cem_notas_alumnos_todas_carreras_2010 n, pensum."+pensum+" x "+
                                        "SET n.TERMINO = x.semestre "+
                                        "WHERE n.CODMAT = x.codigo;");
                    System.out.println("vigencia 2010");
                }
                
            sentencia.close();
            
            


        } catch (SQLException ex) {
            Logger.getLogger(codigos_actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(codigos_actualizar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        
    }
    
    
    
    public void actualizar(Connection con){
    
        
        String elemento = null;
        try {

            ResultSet resultado = null;
            DatabaseMetaData metadatos =  con.getMetaData();
            resultado = metadatos.getTables("pensum", null, "%", null);
                while (resultado.next()) {//recorriendo las tablas
                    //listas.addItem(resultado.getString(3));
                    elemento = resultado.getString(3);
                    if(elemento.startsWith("tsu") || elemento.startsWith("lic") || elemento.startsWith("ingenieria")){                    
                        
                        System.out.println(elemento);
                        this.proceder(new conexion_base_de_datos().getConexion(), elemento);
                        System.out.println("ACTUALIZADO");
                        
                    }
                    // System.out.println(resultado.getString(3));
                }

                
            resultado.close();
            
            System.out.println("PROCESO TERMINADO CON EXITO...!!!!");
            

        } catch (SQLException ex) {
            Logger.getLogger(codigos_actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(codigos_actualizar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
    
    
    }
    
    
    
    
    public static void main(String [] args){
        codigos_actualizar ca = new codigos_actualizar();
        ca.actualizar(new conexion_base_de_datos().getConexion());
    
    }
    
}//fin de la clase
