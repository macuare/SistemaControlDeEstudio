/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.conexion_base_de_datos;
import modelo.modelo_cambios;
import vista.cambios;

/**
 *
 * @author AN
 */
public class control_cambios implements ActionListener{

    private cambios cb;
    private modelo_cambios mc;
    private conexion_base_de_datos cbd;

    public control_cambios(cambios cb) {
        this.cb = cb;
        mc = new modelo_cambios();
        cbd = new conexion_base_de_datos();
    }









    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

            mc.buscar(cbd.getConexion(), cb.getCedula().getText(), cb.getCarrera_ant(), cb.getTurno_ant(),cb.getIdentificacion(),cb.getCambiar());


        }

        if(e.getActionCommand().equalsIgnoreCase("CAMBIAR")){


            mc.cambiar(cbd.getConexion(), cb.getCedula().getText(), cb.getCarrera().getSelectedItem().toString(), cb.getTurno().getSelectedItem().toString(),cb.getCarrera_ant().getText(),cb.getTurno_ant().getText());
            cb.getCedula().setText(null);
            cb.getCarrera_ant().setText(null);
            cb.getTurno_ant().setText(null);
            cb.getIdentificacion().setText(null);
            cb.getCambiar().setEnabled(false);
        }

















    }

}//fin de la clase
