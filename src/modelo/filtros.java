/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author AN
 */
public class filtros extends FileFilter {


    @Override
    public boolean accept(File f) {
        boolean identificador=false;
        String extension="";

       // System.out.println("archivo: "+f.getAbsolutePath());

            if(f.getAbsolutePath().lastIndexOf('.')>0){//verificando que el archivo tenga extension y no sea una carpeta
            extension = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf('.'));//en caso de que asi sea se guarda la extension
         //   System.out.println("ext "+extension);
            }

            if(extension!=""){//si existe una extension entonces se compara a la que se quiere obtener .txt. solo se muestran las carpetas y los archivos con la extension buscada.
                
                identificador=extension.equalsIgnoreCase(".csv");
           //     System.out.println("opcion1: "+identificador);
                return identificador;

            }else{
             //   System.out.println("opcion2");
                return f.isDirectory();

            }


        
    }

    @Override
    public String getDescription() {
        String extension = null;

            extension=".csv";

        return extension;
    }




/*
//ApproveSelection
//CancelSelection

        if(evt.getActionCommand().equalsIgnoreCase("CancelSelection")){
            this.dispose();
        }else{}

        if(evt.getActionCommand().equalsIgnoreCase("ApproveSelection")){
            System.out.println("aprovado "+this.sitio.getSelectedFile());
            System.out.println("aprovado "+this.sitio.getFileFilter().getDescription());
            System.out.println("ruta completa "+this.sitio.getSelectedFile()+this.sitio.getFileFilter().getDescription());
            System.out.println("probando vinculo: "+);
       //     rl.exportar_data(null, ruta, ruta, ruta, ruta);

        }else{}

*/





}
