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
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import vista.DEPURADOR;

/**
 *
 * @author AN
 */
public class depurador_maracay {

private LinkedList <String> orden = new LinkedList<>();
private LinkedList <String> aux1 = new LinkedList<>();
private LinkedList <String> aux2 = new LinkedList<>();
private LinkedList <String> cedulas = new LinkedList<>();
private int avance=0;





    public depurador_maracay() {
    }

    public LinkedList<String> getAux1() {
        return aux1;
    }

    public void setAux1(LinkedList<String> aux1) {
        this.aux1 = aux1;
    }

    public LinkedList<String> getAux2() {
        return aux2;
    }

    public void setAux2(LinkedList<String> aux2) {
        this.aux2 = aux2;
    }

    public LinkedList<String> getOrden() {
        return orden;
    }

    public void setOrden(LinkedList<String> orden) {
        this.orden = orden;
    }

    public LinkedList<String> getCedulas() {
        return cedulas;
    }

    public void setCedulas(LinkedList<String> cedulas) {
        this.cedulas = cedulas;
    }

    public int getAvance() {
        return avance;
    }

    public void setAvance(int avance) {
        this.avance = avance;
    }









 public void lista_cedula(Connection con,String bd ,String tabla){
this.getAux2().clear();



        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT CEDULA FROM "+bd+"."+tabla+" where codesp=1 GROUP BY CEDULA;");

            while (resultado.next()) {
              this.getAux2().add(resultado.getString("CEDULA"));

            }

            resultado.close();
            sentencia.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(depuracion.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
 




public void buscar_materias(Connection con, int ci,String bd,String tabla) {
this.getAux1().clear();


        Statement sentencia = null;
        ResultSet buscar = null;

        try {

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM "+bd+"."+tabla+" WHERE CEDULA="+ ci + ";");



            while(buscar.next()){

            //   System.out.println(buscar.getString("CEDULA"));
                //cargando todas las materias del alumno en memoria
                 this.getAux1().add(buscar.getString("CODMAT"));                 
                 this.getAux1().add(String.valueOf(buscar.getInt("NOTDEF")));
                 this.getAux1().add(String.valueOf(buscar.getInt("NOTREP")));
                 this.getAux1().add(buscar.getString("CONDIC"));
                 this.getAux1().add(buscar.getString("PERACA"));

            }

            sentencia.close();
            buscar.close();
            con.close();


        }catch (SQLException ex) {

                Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("metodo buscar materia");

                                           }


    }



    public boolean etapa_3(String cedula){//ESTE METODO MANEJA LAS MATERIAS CON PRELACIONES PERO ES IDENTICO EN OPERACIONES AL METODO DE ABAJO LLAMADO duplicado
//System.out.println("[Metodo etapa_3]");
    boolean aprobastes=false;
        Archivos doc = new Archivos();

        doc.escritor_textos("d://aeronautica.txt",cedula);//se graba primero la cedula
        System.out.println("capacidad: "+this.getAux1().size()/5);
        
    for(int z=0;z<this.getAux1().size();z=z+5){//recorriendo el LinkedList

        System.out.println("elementos: "+this.getAux1().get(z+1));

        if( ((Integer.valueOf(this.getAux1().get(z+1))>=10 || //que halla aprobado la materia
           (this.getAux1().get(z+2)!=null && Integer.valueOf(this.getOrden().get(z+2))>=10)) && // o que si reparo que la halla aprobado
            this.getAux1().get(z+3)!="POR INASISTENCIA" && //y que no halla perdido la materia por inasistencia
            this.getAux1().get(z+3)!="ERROR") || //y que la condicion de la materia este dentro de las pautadas
            this.getAux1().get(z+3).equalsIgnoreCase("APROBADO") ||//o que la condicion de la materia este en aprobado
            this.getAux1().get(z+3).equalsIgnoreCase("EQUIVALENCIA") //o que la tenga aprobada por equivalencia...no se toma la nota
          ){
            System.out.println("paso");
        doc.escritor_textos("d://aeronautica.txt",this.getAux1().get(z+0));    //se graba cada materia aprobada


       }else{
            System.out.println("reprobado");
            

       }
        }//fin recorrido



return aprobastes;
}

public void listas_estudiantes(Connection con, String bd, String tabla){
    this.getCedulas().clear();


     try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT CEDULA FROM "+bd+"."+tabla+" GROUP BY CEDULA;");

            while (resultado.next()) {
              this.getCedulas().add(resultado.getString("CEDULA"));
              //System.out.println("n°= "+resultado.getRow());
             

            }

            resultado.close();
            sentencia.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(depuracion.class.getName()).log(Level.SEVERE, null, ex);
        }




}



public void comparador_cambios(Connection con, String cedula, JTextArea reporte){

            Statement sentencia = null;
            ResultSet resultado = null;

   

            try {

                sentencia = con.createStatement();
                resultado = sentencia.executeQuery("SELECT CEDULA FROM control_de_estudio.cambios_pensum GROUP BY CEDULA;");

                while (resultado.next()) {
                    if(cedula.equalsIgnoreCase(resultado.getString("CEDULA"))){
                        System.out.println("Cedula: "+cedula+", encontrado en !Cambios de Pensum!");
                        reporte.append("Cedula: "+cedula+", encontrado en !Cambios de Pensum!\n");
                        break;
                    }

                }

                resultado.close();
                sentencia.close();
                con.close();

            } catch (SQLException ex) {
                Logger.getLogger(depuracion.class.getName()).log(Level.SEVERE, null, ex);
            }
   
}


public void comparador_cancelados(Connection con, String cedula, JTextArea reporte){
            Statement sentencia = null;
            ResultSet resultado = null;



         try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT CEDULA FROM control_de_estudio.activacion_cancelados GROUP BY CEDULA;");

            while (resultado.next()) {
                if(cedula.equalsIgnoreCase(resultado.getString("CEDULA"))){
                    System.out.println("Cedula: "+cedula+", encontrado en !Cancelados!");
                    reporte.append("Cedula: "+cedula+", encontrado en !Cancelados!\n");
                    break;
                }
                
            }

            resultado.close();
            sentencia.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(depuracion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
}

public void ejecucion(JTextArea reporte){
      

   for(int s=0; s<this.getCedulas().size(); s++){//mostrando todos los de cambio de pensum
 //   reporte.append(s+"/"+(this.getCedulas().size()-1)+" cedula: "+this.getCedulas().get(s)+"\n");
    System.out.println("n°= "+s+"/"+(this.getCedulas().size()-1));
   // progreso.setValue(s);
    this.setAvance(s);
    
        this.comparador_cambios(new conexion_base_de_datos().getConexion(),this.getCedulas().get(s), reporte);
        
       

    }

   for(int s=0; s<this.getCedulas().size(); s++){//mostrando todos los de cancelados
 //   reporte.append(s+"/"+(this.getCedulas().size()-1)+" cedula: "+this.getCedulas().get(s)+"\n");
    System.out.println("n°= "+s+"/"+(this.getCedulas().size()-1));

        this.setAvance(s);
        this.comparador_cancelados(new conexion_base_de_datos().getConexion(), this.getCedulas().get(s), reporte);

    }

}






    

}//fin de la clase
