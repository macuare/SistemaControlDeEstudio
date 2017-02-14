/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author AN
 */
public class registro_principal {

    private LinkedList<JFrame> abiertas = new LinkedList<JFrame>();


    public registro_principal() {
    }




    public LinkedList<JFrame> getAbiertas() {
        return abiertas;
    }

    public void setAbiertas(LinkedList<JFrame> abiertas) {
        this.abiertas = abiertas;
    }

    




    public void ventanas_abiertas(JFrame ventana){
        boolean existe=false;
        System.out.println("VENTANA "+ventana.getName());

        this.getAbiertas().add(ventana);//guardando la ventana cada vez que se abra....ya que asi cierro incluso la misma si fue abierta varias veces. aun si se cierra manual el dispose lo que hace es como ocultarla pero sigue estando en memorio por lo que busca cerrar incluso esas para liberar memoria

        /*
        if(this.getAbiertas().isEmpty()) {this.getAbiertas().add(ventana); System.out.println("GUARDANDO VENTANA "+ventana); }//se anexa el nombre de la ventana como primer registro xq esta vacio
        else{//es porque hay ventanas almacenadas y se va a verificar si ya la ventana existe guardada o se anexa porque no esta

            for(int x=0; x<this.getAbiertas().size(); x++){//recorriendo ventanas abiertas

                if(ventana.getName().equalsIgnoreCase(this.getAbiertas().get(x).getName())){//si existe simplemente se ignora porque ya existe
                    System.out.println("VENTANA YA APERTURADA"+ventana.getName());
                    existe=true;
                    break;
                }else{
                    existe=false;
                }

            }//fin recorrido ventanas abiertas


            if(existe==false) { this.getAbiertas().add(ventana); System.out.println("GUARDANDO VENTANA XQ NO ESTA"+ventana.getName());} //despues de recorrer las ventanas abiertas y ver que no esta alli la de interes se agrega

        }
*/
    }

    public void cerrando_ventanas(){
        System.out.println("BUSCANDO CERRAR VENTANAS APERTURADAS PARA HACER EFECTIVO EL CAMBIO DE PERIODO ACADEMICO");

        for(int i=0; i<this.getAbiertas().size(); i++){//recorriendo ventanas abiertas
            System.out.println("CERRANDO VENTANA: "+this.getAbiertas().get(i).getName());
           this.getAbiertas().get(i).dispose();
        }//fin recorrido ventanas abiertas

        this.getAbiertas().clear();

    }

/**Metodo que permite dibujar una foto sobre un panel y ademas se puede inscrustar un texto en la misma */
public BufferedImage cargar_imagen(String texto){
   
        
            System.out.println("CARGANDO IMAGEN");
            
           ImageIcon imagen = new ImageIcon(getClass().getResource("/actualizacion_de_datos/fondos/a2_secretaria.jpg"));
            
            BufferedImage bi = new BufferedImage(714, 402, BufferedImage.TYPE_INT_RGB );            
           
            Graphics2D g2d = bi.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
            g2d.drawImage(imagen.getImage(), 0, 0,714,402, null);  //dibujando imagen          
            
            g2d.setFont(new java.awt.Font("LetterOMatic!", 0, 18));
            g2d.drawString(texto, 700, 390);//agregando texto          
           
            g2d.dispose();
           
                        
            
            //panel.getGraphics().drawImage(bi, 0, 0, null);
   
            return bi;
}
    
    
   




    
    
    
    
}//fin de la clase
