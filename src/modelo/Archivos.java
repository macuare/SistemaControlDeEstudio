/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import com.csvreader.CsvWriter;
import com.mysql.jdbc.Statement;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author AN
 */

public class Archivos {


private LinkedList <String> txt_simple = new LinkedList<>();
private LinkedList <String> contenedor = new LinkedList<>();
private imagenes ima;

    public Archivos() {
        System.out.println("Cagando archivos ...");
        ima = new imagenes();
    }



//GETTERs y SETTERs
    public LinkedList<String> getTxt_simple() {
        return txt_simple;
    }

    public void setTxt_simple(LinkedList<String> txt_simple) {
        this.txt_simple = txt_simple;
    }

    public LinkedList<String> getContenedor() {
        return contenedor;
    }

    public void setContenedor(LinkedList<String> contenedor) {
        this.contenedor = contenedor;
    }







//METODOS

      public void borrar(String ruta){

      File fichero= new File(ruta);
       //File fichero= new File("c:/lista_de_cedulas.txt");

      if(fichero.exists()){
         if(fichero.delete()){System.out.println("archivo borrado con exito");
          }else{System.out.println("hubo problemas para borrar el archivo");}

      }else{System.out.println("el archivo no existe"); }


      }//fin del metodo borrar

public boolean lector(String informacion, String ruta){
    boolean existe=true;
    File archivo=null;
    FileReader fr= null;
    BufferedReader br= null;
    String linea;

  //  archivo=new File("c:/lista_de_cedulas.txt");
     archivo=new File(ruta);
        try {
            fr = new FileReader(archivo);        
            br= new BufferedReader(fr);
       
            while ((linea=br.readLine()) != null) {
                //System.out.println(linea);
                        if(linea.equalsIgnoreCase(informacion)){
                           // System.out.println("encontrado");
                            existe=true;
                            break;
                          }else{existe=false;}

                                                   }

           
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{

            if( null != fr ){
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                 }
//System.out.println("sujeto no encontrado");

return existe;
}//fin del metodo lector



      public void escritor_textos(String ruta, String parrafo){

        FileWriter fichero = null;
        PrintWriter pw = null;

            
                    try {
                        fichero = new FileWriter(ruta,true);
                         
                       
                        pw= new PrintWriter(fichero);                           
                        pw.println(parrafo);
                        
                      


                    } catch (IOException ex) {
                        Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{

                        if (null != fichero)
                          try {
                            fichero.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                                                    

        }// fin del metodo de escritor de textos

public void escritor_texto_mejorado(String ruta,String cabecera, String parrafo){
    File archivo = null;

    //verificando si el archivo existe
        archivo = new File(ruta);
            if(archivo.exists()){//si existe, se continua escribiendo sobre el mismo archivo
                this.escritor_textos(ruta, parrafo);
            }else{//en caso de que el archivo no exista, se crea la cabecera y se añade el primer registro
                this.escritor_textos(ruta,cabecera);//escribiendo la cabecera
                this.escritor_textos(ruta, parrafo);//escribiendo el primer parrafo
            }
}

public void borrar_archivo(String ruta){
     File archivo = null;
     archivo = new File(ruta);
     if(archivo.exists()) archivo.delete();System.out.println("archivo borrado");
     
}



public void lector_simple(String ruta){

    File archivo=null;
    FileReader fr= null;
    BufferedReader br= null;
    String linea;
    this.getTxt_simple().clear();
    
    archivo=new File(ruta);
        try {
            fr = new FileReader(archivo);
            br= new BufferedReader(fr);

        /*               for(int cont=0; cont<=10; cont++){//es para solo prueba, lectura corta

                       System.out.println(br.readLine());
                       }*/

       
            while ((linea=br.readLine()) != null) {
                this.getTxt_simple().add(linea);
                System.out.println(linea);
                                                   }
        
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{

            if( null != fr ){
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                 }
}
/** se lee linea a linea el archivo de texto y se usa para guradarlo a la base de datos que le corresponda*/
public void lector_muy_simple(String ruta, Connection con, JTextArea area){

    File archivo=null;
    FileReader fr= null;
    BufferedReader br= null;
    String linea;
    Statement sentencia =null;

    area.setText("");
    this.getTxt_simple().clear();

    archivo=new File(ruta);
        try {
            fr = new FileReader(archivo);
            br= new BufferedReader(fr);


        

            while ((linea=br.readLine()) != null) {
                this.getTxt_simple().add(linea);
                System.out.println(linea);

                area.append(linea+"\n");
                sentencia=(Statement) con.createStatement();
                sentencia.executeUpdate(linea);



                                                   }

                sentencia.close();



        } catch (SQLException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            area.append("ERROR \n"+ex.getMessage()+"\n"+ex.getSQLState());
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{

            if( null != fr ){
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                 }
}




public void cargar_contenedor(String ruta){

    File archivo=null;
    FileReader fr= null;
    BufferedReader br= null;
    String linea;
    this.getContenedor().clear();

    archivo=new File(ruta);
        try {
            fr = new FileReader(archivo);
            br= new BufferedReader(fr);

        /*               for(int cont=0; cont<=10; cont++){//es para solo prueba, lectura corta

                       System.out.println(br.readLine());
                       }*/


            while ((linea=br.readLine()) != null) {
                this.getContenedor().add(linea);
               // System.out.println(linea);
                                                   }

        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{

            if( null != fr ){
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                 }


}


/** Metodo para guardar informacion en archivo CSV tipo excel. para ello se debe indicar la ruta
 donde se gurdara el archivo, el LinkedList que contiene toda la informacion y el numero de columnas de
 la tabla que se desea crear con los datos.*/
public void escritor_csv(String ruta,LinkedList<String> informacion, int columnas) throws IOException {

 CsvWriter escritor = new CsvWriter(ruta);//creando un documento csv nuevo

escritor.setDelimiter(';') ;//estableciendo el delimitador que sirve para separar entre columnas

 
    for(int f=0;f<informacion.size();f=f+columnas){//recorriendo las filas
     //   System.out.println(informacion.get(f+1));
            for(int c=0;c<=columnas-1;c++){//recorriendo las columnas

                escritor.write(informacion.get(f+c));//escribiendo linea a linea x columna

            }//fin columnas
                escritor.endRecord();//fin de linea
    }//fin filas

                escritor.close();//cerrando el documento


}


/** Metodo para guardar informacion en archivo CSV tipo excel. para ello se debe indicar la ruta
 donde se gurdara el archivo, el LinkedList que contiene toda la informacion y el numero de columnas de
 la tabla que se desea crear con los datos.*/
public void escritor_csv_2(String ruta,LinkedList<String> informacion, int columnas) throws IOException {

 CsvWriter escritor = new CsvWriter(ruta);//creando un documento csv nuevo

escritor.setDelimiter(';') ;//estableciendo el delimitador que sirve para separar entre columnas


    for(int f=0;f<informacion.size();f=f+columnas){//recorriendo las filas
        System.out.println("escribiendo csv: fila: "+(f/columnas));
            for(int c=0;c<=columnas-1;c++){//recorriendo las columnas

                escritor.write(informacion.get(f+c));//escribiendo linea a linea x columna

            }//fin columnas
                escritor.endRecord();//fin de linea
    }//fin filas

                escritor.close();//cerrando el documento


}


public void abrir_archivos_windows(String archivo_pdf){
   File x = new File(archivo_pdf);
  // Desktop escritorio = null;
    System.out.println("APERTURANDO ARCHIVO: "+archivo_pdf);
    //System.out.println("EXISTE EL ARCHIVO "+x.exists()+" se puede leer: "+x.canRead()+" tamaño: "+x.length());
    if(x.length()>0 && x.exists() && x.canRead() ){//verificando la integridad del archivo

      if( Desktop.isDesktopSupported()){//sistema primario de apertura
                try {
                    Desktop.getDesktop().open(x);
                    System.out.println("Primario. Abriendo el archivo en " + archivo_pdf);
                } catch (IOException ex) {
                    ima.mensaje_informacion("Error al abrir el archivo, por favor consulte a soporte tecnico UNEFA CAGUA", "ERROR DE APERTURA PRIMARIO", "a_pdf", "png");
                    Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
                }

      }else{//sistema secundario de apertura

            try {
                //Constancia_Inscripcion_18177351
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+archivo_pdf);

                System.out.println("Secundario. Abriendo el archivo en "+archivo_pdf);
            } catch (IOException ex) {
                ima.mensaje_informacion("Error al abrir el archivo, por favor consulte a soporte tecnico UNEFA CAGUA", "ERROR DE APERTURA SECUNDARIO", "a_pdf", "png");
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }

      }
        

    }


}


/**Este metodo se encarga de aperturar todos los archivos que se crearon */
public void selector_documentos(String ruta, String cedula ,boolean constancia_inscripcion, boolean record_academico){

    if(constancia_inscripcion) this.abrir_archivos_windows(ruta+"Constancia_Inscripcion_"+cedula+".pdf");
    if(record_academico) this.abrir_archivos_windows(ruta+"Record_Academico_"+cedula+".pdf");

}




/**public static void main(String [] args){

Archivos doc = new Archivos();
doc.abrir_archivos_windows("15196976.pdf");

}*/





}