/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import modelo.modelo_ficha;
import vista.ficha;

/**
 *
 * @author AN
 */
public class control_ficha implements HyperlinkListener, ActionListener{

    
    private ficha fi;
    private modelo_ficha mf;

    public control_ficha(ficha fi) {
        this.fi = fi;
        mf = new modelo_ficha();
    }


    







    public void hyperlinkUpdate(HyperlinkEvent e) {


        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
            try {
                fi.getEscritorio().setPage(e.getURL());
            } catch (IOException ex) {
                System.out.println("No se puede seguir la ruta web");
                Logger.getLogger(control_ficha.class.getName()).log(Level.SEVERE, null, ex);
            }



        }

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("MOSTRAR")){
            System.out.println("MOSTRANDO CONTENIDO WEB");
           //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actualizacion_de_datos/imagenes/Blu Style png sites.png"))); // NOI18N

                try {
                   // fi.getEscritorio().setPage(new URL("http://www.gmail.com/"));
                  //  fi.getEscritorio().setPage(new URL("file://"+getClass().getResource("/ahtml/probando.html")));
                    fi.getEscritorio().setPage(new URL("file:///C:/Users/AN/Documents/NetBeansProjects/CONTROL_DE_ESTUDIO/src/ahtml/probando.html"));


                } catch (IOException ex) {
                    Logger.getLogger(control_ficha.class.getName()).log(Level.SEVERE, null, ex);
                }


        }

        if(e.getActionCommand().equalsIgnoreCase("DIBUJA")){
            System.out.println("DIBUJANDO");
            mf.imagen_fondos(fi.getCuadro());

        }

      
    }



















}//fin de la clase
