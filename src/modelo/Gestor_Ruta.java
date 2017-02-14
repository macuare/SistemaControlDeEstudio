/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author AN
 */
public class Gestor_Ruta extends JFileChooser{
    private imagenes ima;
    
    
    public Gestor_Ruta() {
        ima = new imagenes();
        //this.showOpenDialog(new JFrame());
        
    }
    
    /**Crea una ventana para seleccionar exclusivamente el directorio donde se desea guardar la informacion,
     luego ese nuevo directorio pasa a ser por defecto la carpeta actual. Retorna la dirección completa de la ruta seleccionada*/
    public String guardar_archivo(){
        int respuesta=0;
        this.setDialogTitle("GUARDANDO EN CARPETA");
        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.setMultiSelectionEnabled(false);
        String ruta=null;
        
        respuesta = this.showOpenDialog(new JFrame());
        
        
        System.out.println("respuesta: "+respuesta);
        //System.out.println("DIRECTORIO SELECCIONADO: "+this.getSelectedFile().getAbsolutePath());
        //cancelar = 1
        //abrir = 0
        
               
        if(respuesta==JFileChooser.APPROVE_OPTION && 
           this.getSelectedFile().isDirectory()){
            
            ruta = this.getSelectedFile().getAbsolutePath()+File.separator;
            ima.mensaje_informacion("La información va a ser almacenada en la siguiente ruta:\n"+ruta,
                                    "RUTA SELECCIONADA", "archivo", "png");
            //System.out.println("ES UN DIRECTORIO: "+this.getSelectedFile().getAbsolutePath());
            
            this.setCurrentDirectory(this.getSelectedFile());//estableciendo la Gestor_Ruta por defecto la carpeta actual de trabajo
        }
        if(respuesta==JFileChooser.APPROVE_OPTION && 
           this.getSelectedFile().isFile()){
            ima.mensaje_informacion("Disculpe pero debe seleccionar un Directorio no un Archivo:\n"+this.getSelectedFile().getName()+"\nVUELVA A INTENTAR",
                                    "ERROR DE SELECCIÓN DE RUTA", "archivo", "png");
           
           ruta = this.guardar_archivo();
        }
        
        
        
        return ruta;
    }
    
    
    
    
    
    
    
    
    
    public static void main(String [] args){
    
        Gestor_Ruta r = new Gestor_Ruta();
        r.guardar_archivo();
    }
    
    
    
}