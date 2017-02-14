/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class depuracion {


  private String almacenar=null;
  private int semestre,cant_cedula;
  private LinkedList <String> temp = new LinkedList<>();
  private LinkedList <String> aux = new LinkedList<>();



    public depuracion() {

    }

    public String getAlmacenar() {
        return almacenar;
    }

    public void setAlmacenar(String almacenar) {
        this.almacenar = almacenar;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public LinkedList<String> getTemp() {
        return temp;
    }

    public void setTemp(LinkedList<String> temp) {
        this.temp = temp;
    }

    public LinkedList<String> getAux() {
        return aux;
    }

    public void setAux(LinkedList<String> aux) {
        this.aux = aux;
    }

    public int getCant_cedula() {
        return cant_cedula;
    }

    public void setCant_cedula(int cant_cedula) {
        this.cant_cedula = cant_cedula;
    }







//metodos de la clase

    public boolean retener(boolean informacion){
    boolean aviso=false;


        if(informacion==true){
        aviso=false;
        }else{
        aviso=true;
        }

    //System.out.println(aviso);
return aviso;
    }


    public void lista_cedula(Connection con, String ruta, String notas_alumno){
                Archivos doc= new Archivos();
                doc.borrar(ruta);               
                doc.escritor_textos(ruta,"");

                int cuenta=0;
                

        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT CEDULA FROM control_de_estudio."+notas_alumno+";");

            while (resultado.next()) {
              
                    if(this.retener(doc.lector(resultado.getString("CEDULA"),ruta))==true){

                        doc.escritor_textos(ruta,resultado.getString("CEDULA"));
                    
                     cuenta= cuenta+1;
                     this.setCant_cedula(cuenta);
                    System.out.println(resultado.getString("CEDULA")+"  "+cuenta);
                    }else{
                       
                        }

            }

            resultado.close();
            sentencia.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(depuracion.class.getName()).log(Level.SEVERE, null, ex);
        }



    }



    public void semestre_virtual(Connection con, String cedula, String bd_pensum, String notas_alumno){

                Archivos doc= new Archivos();
                doc.borrar("c:/record.txt");
                doc.escritor_textos("c:/record.txt","");
               
               // doc.escritor_textos("c:/alumnos_materias_no_encontradas", "");
                
                boolean materia=true;
                this.getTemp().clear();
                doc.getTxt_simple().clear();



            Statement sentencia;
            ResultSet pensum;
            ResultSet record;

            
int x1=0,y1=0;
this.setSemestre(0);



        try {
           
            //--------------------------------------
            sentencia = con.createStatement();
            record = sentencia.executeQuery("SELECT * FROM control_de_estudio."+notas_alumno+" WHERE cedula="+cedula+";");

           
            sentencia = con.createStatement();
            pensum = sentencia.executeQuery("SELECT * FROM pensum."+bd_pensum+";");

            

        /*    //alternativo. aplicacion puntual
                    ResultSet alt=null;
                    sentencia = con.createStatement();
                    alt = sentencia.executeQuery("SELECT * FROM pensum.lic_educacion_integral_2007");
                    
                    this.getTemp().add("");
                    
                    while(alt.next()){this.getTemp().add(alt.getString("codigo"));}
            
            //alternativo. aplicacion puntual

            */

            while (record.next()) {x1=x1+1;//System.out.println(x1+record.getString("CODMAT"));
            doc.escritor_textos("c:/record.txt",record.getString("CODMAT"));
            }

            while (pensum.next()){
                this.getTemp().add(pensum.getString("codigo"));
                
                if(doc.lector(pensum.getString("codigo"),"c:/record.txt")==true){y1=y1+1;
                    //System.out.println(pensum.getString("codigo"));                
                    //System.out.println("semestre:"+pensum.getString("semestre")+" - "+pensum.getString("codigo")+" - "+pensum.getString("asignatura"));

                    if(pensum.getInt("semestre")>this.getSemestre()){
                      this.setSemestre(pensum.getInt("semestre"));
                    }


                    //System.out.println(y1);
                }else{
                    //System.out.println("no la encontro  "+pensum.getString("codigo"));
                 }
               
            }

          


//System.out.println(y1);
//System.out.println("alumno: "+cedula+" semestre: "+this.getSemestre());
            sentencia.close();
            record.close();
            pensum.close();

         //   alt.close();//alternativo. aplicacion puntual

            
            con.close();

  //---------------------------------
         // System.out.println("DESDE AQUI SE BUSCA LAS MATERIAS QUE NO SE HALLAN DEL ALUMNO");
           doc.lector_simple("c:/record.txt");//cargando record en el LinkedList para compararlo con el pensum
                                              //esto es para las materias que el alumno tiene inscrita y no aparecen en el pensum
         // System.out.println(this.getTemp().size()+" "+doc.getTxt_simple().size());

           for(int c=0;c<doc.getTxt_simple().size();c++){
              // System.out.println(c+" "+this.getTemp().get(c));
               
               for(int r=0; r<this.getTemp().size();r++){

                 if(doc.getTxt_simple().get(c).equalsIgnoreCase(this.getTemp().get(r))){
                     materia=true;break;}else{materia=false;}

               }

               
                       if(materia==false){
                         //  System.out.println("materia que no se encuentran: "+doc.getTxt_simple().get(c));
                           doc.escritor_textos("c:/alumnos_materias_no_encontradas.txt",cedula+" - "+doc.getTxt_simple().get(c));
                       }

           }

          doc.escritor_textos("c:/alumnos_materias_no_encontradas.txt","");//espacio en blanco
//-------------------------------





        }
        catch (SQLException ex) {
            Logger.getLogger(depuracion.class.getName()).log(Level.SEVERE, null, ex);
        }






    }//fin del metodo semestre virtual



    


   /*


    DROP TABLE IF EXISTS `control_de_estudio`.`notas_alumnos_edu_ing_2007`;
CREATE TABLE  `control_de_estudio`.`notas_alumnos_edu_ing_2007` (
  `POSICION` int(10) unsigned NOT NULL,
  `CEDULA` varchar(200) DEFAULT NULL,
  `CODMAT` varchar(200) DEFAULT NULL,
  `SECCION2` varchar(200) DEFAULT NULL,
  `SECCION` varchar(200) DEFAULT NULL,
  `TERMINO` int(10) unsigned DEFAULT NULL,
  `PERACA` varchar(200) DEFAULT NULL,
  `CONDIC` varchar(200) DEFAULT NULL,
  `NOTREP` int(10) unsigned DEFAULT NULL,
  `NOTDEF` int(10) unsigned DEFAULT NULL,
  `DEFREP` int(10) unsigned DEFAULT NULL,
  `NOTLAB` int(10) unsigned DEFAULT NULL,
  `PORINA` int(10) unsigned DEFAULT NULL,
  `CODESP` int(10) unsigned DEFAULT NULL,
  `CREDI` int(10) unsigned DEFAULT NULL,
  `OBSERVACIÓ` varchar(200) DEFAULT NULL,
  `FECHA_HORA` varchar(200) DEFAULT NULL,
  `ADMINISTRA` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`POSICION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `control_de_estudio`.`notas_contaduria_publica_2007`;
CREATE TABLE  `control_de_estudio`.`notas_contaduria_publica_2007` (
  `POSICION` int(10) unsigned NOT NULL,
  `CEDULA` varchar(200) DEFAULT NULL,
  `CODMAT` varchar(200) DEFAULT NULL,
  `SECCION2` varchar(200) DEFAULT NULL,
  `SECCION` varchar(200) DEFAULT NULL,
  `TERMINO` int(10) unsigned DEFAULT NULL,
  `PERACA` varchar(200) DEFAULT NULL,
  `CONDIC` varchar(200) DEFAULT NULL,
  `NOTREP` int(10) unsigned DEFAULT NULL,
  `NOTDEF` int(10) unsigned DEFAULT NULL,
  `DEFREP` int(10) unsigned DEFAULT NULL,
  `NOTLAB` int(10) unsigned DEFAULT NULL,
  `PORINA` int(10) unsigned DEFAULT NULL,
  `CODESP` int(10) unsigned DEFAULT NULL,
  `CREDI` int(10) unsigned DEFAULT NULL,
  `OBSERVACIÓ` varchar(200) DEFAULT NULL,
  `FECHA_HORA` varchar(200) DEFAULT NULL,
  `ADMINISTRA` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`POSICION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

*/




}//fin de la clase
