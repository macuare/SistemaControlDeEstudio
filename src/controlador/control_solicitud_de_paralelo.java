/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.parametros;
import vista.solicitud_de_paralelo;

/**
 *
 * @author AACM
 */
public class control_solicitud_de_paralelo implements ActionListener {

    private solicitud_de_paralelo sp;
    private parametros par;

    public control_solicitud_de_paralelo(solicitud_de_paralelo sp) {
        this.sp = sp;
        par= new parametros();
    }


    



    public void actionPerformed(ActionEvent e) {

if(e.getActionCommand().equalsIgnoreCase("SOLICITAR")){

  
    new vista.inicio().setVisible(true);
    sp.dispose();

}




    }










}
