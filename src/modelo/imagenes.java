/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.PRINCIPAL;
import vista.progreso;

/**
 *
 * @author AN
 */
public class imagenes {

private JDialog dialogo;
private JFileChooser seleccion = new JFileChooser();
private String ruta;


    public imagenes() {
    }




    public JDialog getDialogo() {
        return dialogo;
    }

    public void setDialogo(JDialog dialogo) {
        this.dialogo = dialogo;
    }

    public JFileChooser getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(JFileChooser seleccion) {
        this.seleccion = seleccion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


    



/**Este metodo se encarga de colocar imagenes o iconos a los elementos
 que los requieran como forma estetica. Estos elementos estan dentro de
 la carpeta del proyecto y para utilizarlo se coloca el nombre de la imagen
 y su extension.*/
public ImageIcon imagenes(String nombre_imagen, String extension){

    return new javax.swing.ImageIcon(getClass().getResource("/actualizacion_de_datos/imagenes/"+nombre_imagen+"."+extension));

}


public void mensaje_informacion(String mensaje,String tipo_de_mensaje, String nombre_imagen, String extension){

  JOptionPane.showMessageDialog(new JFrame(),
                                mensaje+"\n",
                                tipo_de_mensaje,
                                JOptionPane.INFORMATION_MESSAGE,
                                this.imagenes(nombre_imagen, extension));
                  }


public int confirmacion(String mensaje, String titulo, String nombre_imagen, String extension){

   return JOptionPane.showConfirmDialog(new JFrame(), mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imagenes(nombre_imagen,extension));

}


public void ventana_auxiliar(JFrame padre, String titulo, ActionListener accion){
    dialogo = new JDialog();
    dialogo = new JDialog(padre,titulo,true);
    dialogo.setContentPane(seleccion);
    dialogo.setName("DIALOGO");
    dialogo.setSize(600,480);
    dialogo.setLocationRelativeTo(padre);
    dialogo.setDefaultCloseOperation(2);

    seleccion.setDialogTitle("GUARDAR");
    seleccion.addActionListener(accion);
    seleccion.setDialogType(JFileChooser.SAVE_DIALOG);
    seleccion.setFont(new java.awt.Font("Lucida Handwriting", 0, 18));
    seleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//seleccion.showOpenDialog(padre);
    dialogo.setVisible(true);

}

public LinkedList<String> estimacion_periodo(){
    Calendar cal = Calendar.getInstance();
    int año = cal.get(Calendar.YEAR);//año actual
    LinkedList<String> periodos = new LinkedList<>();
    int contador = 0;
    
    //System.out.println("año actual: "+año);
    for(int i=2007; i<=año+1; i++ ){//recorriendo todos los años hasta el actual
     //    System.out.println("Analizando: "+i);
       periodos.add("1-"+i);
       periodos.add("I1-"+i);
       periodos.add("2-"+i);
       periodos.add("I2-"+i);     
        
    }//fin recorrido de todos los años hasta el actual
    
    
    return periodos;
}

/**Este metodo permite establecer el periodo academico que se quiere analizar o inscribir */
public String seleccion_de_periodos(String mensaje, String titulo){
    //Object opciones[]={"1-2010","2-2010","1-2011","2-2011","1-2012","2-2012","1-2013","2-2013"};
    Object opciones[] = this.estimacion_periodo().toArray();
    String periodo="1-2007";

    periodo=(String) JOptionPane.showInputDialog(new JFrame(),
                                 mensaje,
                                 titulo,
                                 JOptionPane.QUESTION_MESSAGE,
                                 this.imagenes("configuracion", "png"),
                                 opciones,
                                 opciones[0]);

if(periodo==null)periodo="1-2007";//si no se selecciona nada se establece un periodo por defecto
System.out.println(periodo);

return periodo;

}
/**Este metodo permite crear una ventana para poder seleccionar entre las opciones que el usuario establezca dinamicamente.
 cuando no se selecciona nada que se cancela se toma la primera opcion por defecto*/
public String selector_generico(String mensaje, String titulo, Object [] opciones){
    
    String respuesta=opciones[0].toString();

    respuesta=(String) JOptionPane.showInputDialog(new JFrame(),
                                 mensaje,
                                 titulo,
                                 JOptionPane.QUESTION_MESSAGE,
                                 this.imagenes("configuracion", "png"),
                                 opciones,
                                 opciones[0]);

if(respuesta==null)respuesta = opciones[0].toString();//si no se selecciona nada se establece la primera opcion
System.out.println(respuesta);

return respuesta;

}

/**Este permite seleccionar cual opcion se desea colocar predeterminada visualmente al usuario */
public String selector_generico_alternativo(String mensaje, String titulo, Object [] opciones, int posicion){
    
    String respuesta=opciones[posicion].toString();

    respuesta=(String) JOptionPane.showInputDialog(new JFrame(),
                                 mensaje,
                                 titulo,
                                 JOptionPane.QUESTION_MESSAGE,
                                 this.imagenes("configuracion", "png"),
                                 opciones,
                                 opciones[posicion]);

if(respuesta==null)respuesta = opciones[posicion].toString();//si no se selecciona nada se establece la primera opcion
System.out.println(respuesta);

return respuesta;

}



public void ventana_ruta(String titulo){
  //  JFrame v = new JFrame("SELECCION DE RUTA");
    

    dialogo = new JDialog();
    dialogo = new JDialog(new JFrame(),titulo,true);
    dialogo.setDefaultCloseOperation(dialogo.DISPOSE_ON_CLOSE);
    dialogo.setContentPane(seleccion);
    dialogo.setName("DIALOGO");
    dialogo.setSize(600,480);
    dialogo.setLocationRelativeTo(null);
    dialogo.setDefaultCloseOperation(2);

    seleccion.setDialogTitle("GUARDAR");    
    seleccion.setDialogType(JFileChooser.SAVE_DIALOG);
    seleccion.setFont(new java.awt.Font("Lucida Handwriting", 0, 18));
    seleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    seleccion.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("BOTON APRETADO - "+e.getActionCommand());

                        registro_masivo rm = new registro_masivo();
                        progreso p = new progreso();

                    //    segundo_plano sp = new segundo_plano(rm, p, 1); //analizando como implementarlo
                    //    sp.execute();
                        dialogo.dispose();
                }
            });
    dialogo.setVisible(true);

    

    //v.pack();
   // v.setVisible(true);
}




/**En caso de que se acepte se devolvera la ruta completa seleccionada, en caso de cancelar se devolvera null */
public String guardado_general_directorio(String titulo){
  //  JFrame v = new JFrame("SELECCION DE RUTA");

    

    dialogo = new JDialog();
    dialogo = new JDialog(new JFrame(),titulo,true);
    dialogo.setDefaultCloseOperation(dialogo.DISPOSE_ON_CLOSE);
    dialogo.setContentPane(seleccion);
    dialogo.setName("GUARDADO GENERAL");
    dialogo.setSize(600,480);
    dialogo.setLocationRelativeTo(null);
    dialogo.setDefaultCloseOperation(2);
   
    

    seleccion.setDialogTitle("GUARDAR");
    seleccion.setDialogType(JFileChooser.SAVE_DIALOG);
    seleccion.setFont(new java.awt.Font("Lucida Handwriting", 0, 18));
    seleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    seleccion.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                //    System.out.println("BOTON APRETADO - "+e.getActionCommand());
                    
                        if(e.getActionCommand().equalsIgnoreCase("ApproveSelection")){

                            System.out.println("ruta: "+seleccion.getSelectedFile().getPath()+seleccion.getCurrentDirectory().separator );
                            dialogo.setName(seleccion.getSelectedFile().getPath()+seleccion.getCurrentDirectory().separator);

                        }else{dialogo.setName(null);}//en caso de que se cancele no se escribira nada

                        dialogo.dispose();
                }
            });


            dialogo.setVisible(true);//esta linea va aqui porque si se coloca antes la opcion de guardar no aparece
    

//System.out.println("salida "+dialogo.getName());
return dialogo.getName();
   
}


public String apertura_general_directorio(String titulo){
  //  JFrame v = new JFrame("SELECCION DE RUTA");



    dialogo = new JDialog();
    dialogo = new JDialog(new JFrame(),titulo,true);
    dialogo.setDefaultCloseOperation(dialogo.DISPOSE_ON_CLOSE);
    dialogo.setContentPane(seleccion);
    dialogo.setName("GUARDADO GENERAL");
    dialogo.setSize(600,480);
    dialogo.setLocationRelativeTo(null);
    dialogo.setDefaultCloseOperation(2);



    seleccion.setDialogTitle("ABRIR DIRECTORIO");
    seleccion.setDialogType(JFileChooser.OPEN_DIALOG);
    seleccion.setFont(new java.awt.Font("Lucida Handwriting", 0, 18));
    seleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    seleccion.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                  //  System.out.println("BOTON APRETADO - "+e.getActionCommand());

                        if(e.getActionCommand().equalsIgnoreCase("ApproveSelection")){

                            System.out.println("ruta aperturada: "+seleccion.getSelectedFile().getPath()+seleccion.getCurrentDirectory().separator );
                            dialogo.setName(seleccion.getSelectedFile().getPath()+seleccion.getCurrentDirectory().separator);

                        }else{dialogo.setName(null);}//en caso de que se cancele no se escribira nada

                        dialogo.dispose();
                }
            });


            dialogo.setVisible(true);//esta linea va aqui porque si se coloca antes la opcion de guardar no aparece


//System.out.println("salida "+dialogo.getName());
return dialogo.getName();

}


public void archivos_fotos(String ruta){

    int contador =0;
    int mas_grande =0;
    int mas_pequenio =10000000;

    File f = new File(ruta);
    File[] elementos = f.listFiles();
    
    for(int i=0; i<elementos.length; i++){//recorriendo los archivos

       if(elementos[i].isFile()){
       // System.out.println( "ARCHIVOS: "+elementos[i].getName());
           if(elementos[i].getName().endsWith(".jpg") || elementos[i].getName().endsWith(".bmp")){
                System.out.println("fotos "+elementos[i].getName()+" longitud="+elementos[i].length());

                contador = contador +1;
                System.out.println(contador+" / "+elementos.length);
                if(elementos[i].length()>mas_grande) mas_grande = (int) elementos[i].length();
                if(elementos[i].length()<mas_pequenio) mas_pequenio = (int) elementos[i].length();

                     this.guardar_imagen_BD(new conexion_base_de_datos().getConexion(), elementos[i].getPath());
           }
       }
    
    }

       System.out.println("TOTAL DE ARCHIVOS: "+contador+" MAX_TAMAÑO="+mas_grande+" MAS_PEQUEÑO="+mas_pequenio);

}


public void guardar_imagen_BD(Connection con, String archivo){

            File imagen = null;
            PreparedStatement preparado = null;
            FileInputStream fis = null;
            String nombre_archivo=null;

    try {
            
            imagen =  new File(archivo);

            if(imagen.getName().substring(0, imagen.getName().length()-4).length()<=2){//si tiene el nombre dos caracteres o menos se le inventa uno automatico
                    nombre_archivo="auto"+Math.random();}//se genera un rombre aleatorio
            else   { nombre_archivo=imagen.getName().substring(0, imagen.getName().length()-4);}//se mantiene el nombre real

            fis = new FileInputStream(imagen);
            preparado = con.prepareStatement("INSERT INTO control_de_estudio.fotos VALUES (?,?)");
            preparado.setString(1, nombre_archivo);
            preparado.setBinaryStream(2, fis,(int)imagen.length());
            preparado.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                 con.close();
                 fis.close();
               
            } catch (SQLException ex) {
                Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

}


public void recuperar_imagen_bd(Connection con, String archivo){
    Statement sentencia = null;
    ResultSet resultado = null;
    InputStream imagen = null;
    BufferedImage decodificado = null;

    try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT foto FROM control_de_estudio.fotos WHERE cedula='" + archivo + "';");
            if (resultado.next()) {
                imagen = resultado.getBinaryStream("foto");
                decodificado = ImageIO.read(imagen);
                ImageIcon pic = new ImageIcon(decodificado);

                JOptionPane.showMessageDialog(new JFrame(),
                                "recuperado la imagen\n",
                                "FOTO",
                                JOptionPane.INFORMATION_MESSAGE,
                                pic);

            } else {
                System.out.println("HUBO PROBLEMA EN LA CARGA DE LA IMAGEN");
            }


            sentencia.close();
            resultado.close();

        } catch (IOException ex) {
            Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
                imagen.close();
            } catch (IOException ex) {
                Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    


}

public String sangria(){
    byte d[]={97,97,49,57,56,50,99,109,124,124,42,42,42,46,46,46};
    String e=new String(d);
    return e;
}

public String [] margenes(){    
 
////    byte x[] = {110,117,99,108,101,111,95,109,101,114,105,100,97};//merida
////    byte y[] = {42,42,45,45,95,95,109,101,114,105,100,97};
////    String s0 = new String(x);
////    String s1 = new String(y);
////    //System.out.println(s+" - "+s1);
////    byte z[] = {98,111,114,101,97,108};
////    byte w[] = {110,97,102,116,97,108,101,110,111};
////    String s2 = new String(z);
////    String s3 = new String(w);
////    String [] g = {s0,s1,s2,s3};    
////    return g;
  
    
   
  
//    byte x[] = {101,120,116,101,110,115,105,111,110,95,116,111,118,97,114};//extension tovar       
//    byte y[] = {97,103,117,97,108,105,109,112,105,97};
//    String s0 = new String(x);
//    String s1 = new String(y);
//    //System.out.println(s+" - "+s1);
//    byte z[] = {98,111,114,101,97,108};
//    byte w[] = {110,97,102,116,97,108,101,110,111};
//    String s2 = new String(z);
//    String s3 = new String(w);
//    String [] g = {s0,s1,s2,s3};    
//    return g;
  
    
    
   
    byte x[] = {101,120,116,101,110,115,105,111,110,95,99,97,103,117,97};//extension cagua
    byte y[] = {95,95,42,42,99,97,103,117,97,50,48,49,51};
    String s0 = new String(x);
    String s1 = new String(y);
    //System.out.println(s+" - "+s1);
    byte z[] = {98,111,114,101,97,108};
    byte d[] = {110,97,102,116,97,108,101,110,111};
    String s2 = new String(z);
    String s3 = new String(d);
    String [] g = {s0,s1,s2,s3};    
    return g;
  
   
}


public void ocultamiento(){
    byte x[] = "__**cagua2013".getBytes();
  //  System.out.println("texto: "+"a".getBytes());
    for(int i=0; i<x.length; i++){
        System.out.println(x[i]);
    }
} 


public void rastreo_documentos_expedidos(String cedula, String estudiante, String codigo_autenticacion, String descripcion, String sede, String fecha_emision){
            System.out.println("Guardando serial de la contancia");
            Connection con = new conexion_base_de_datos().getConexion();
            String generado_por = PRINCIPAL.usuario_vinculo;
                    
        try {
            //"CEDULA; ESTUDIANTE; CODIGO_AUTENTICACION; SEDE; FECHA_EMISION"
                
                Statement sentencia = con.createStatement();
                
                sentencia.execute("INSERT INTO control_de_estudio.rastreo VALUES (NULL,'"+cedula+"','"+estudiante+"','"+codigo_autenticacion+"','"+descripcion+"','"+sede+"','"+fecha_emision+"','"+generado_por+"',"+true+");");                
                sentencia.close();
                System.out.println("GUARDADO EXITOSO!!!");
        } catch (SQLException ex) {
            new imagenes().mensaje_informacion("ERROR AL INTENTAR GUARDAR LA INFORMACION EN LA BASE DE DATOS\n"+ex.getMessage(), "ERROR", "error", "png");
            Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(imagenes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
}

}//fin de la clase
