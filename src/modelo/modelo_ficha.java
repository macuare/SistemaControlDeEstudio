/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 *
 * @author AN
 */
public class modelo_ficha {




    public modelo_ficha() {
    }


public void imagen_fondos(JPanel panel){
  //  ImageIcon imagen = new ImageIcon(getClass().getResource("/actualizacion_de_datos/fondos/a2_secretaria.jpg"));
    ImageIcon imagen = new ImageIcon(getClass().getResource("/actualizacion_de_datos/fondos/a2_secretaria.jpg"));
    //= new Image(getClass().getResource("/actualizacion_de_datos/fondos/a2_secretaria.jpg")) {};
    //panel.setPreferredSize(new Dimension(800, 600));
    panel.getGraphics().drawImage(imagen.getImage(), 0,0 ,800,600, null);
    //panel.updateUI();

    


}





public String pagina(){
    String htmls;

    htmls="<html> "
            + "<head>hola soy andy</head>"
            + "<body>"
            + "</body>"
            + "</html>";

return htmls;
}









}//fin de la clase
