/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.conexion_base_de_datos;
import modelo.registro_convalidaciones;
import vista.convalidacion;

/**
 *
 * @author AN
 */
public class control_convalidaciones implements ActionListener{


    private convalidacion cv;
    private registro_convalidaciones rc;
    private conexion_base_de_datos cbd;

    public control_convalidaciones(convalidacion cv) {
        this.cv = cv;
        rc = new registro_convalidaciones();
        cbd = new conexion_base_de_datos();
    }


    


    public void actionPerformed(ActionEvent e) {


        if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

            rc.datos_estudiante(cbd.getConexion(), cv.getCedula().getText());
            cv.getEstudiante().setText(rc.getEstudiante());
            cv.getCarrera().setText(rc.getCarrera());
            cv.getVigencia().setText(rc.getVigencia());
            cv.getCod_opsu().setText(rc.getCodigo_opsu());


        }


        if(e.getActionCommand().equalsIgnoreCase("SIMULAR")){


            rc.convalidacion_estudiante(cv.getCedula().getText(), cv.getCarrera().getText(), cv.getCambios().getSelectedItem().toString(),cv.getInformacion());
            cv.getUc_equi().setText(String.valueOf(rc.getTotal_uc()));
            cv.getMat_equi().setText(String.valueOf(rc.getTotal_materias()));


        }


        if(e.getActionCommand().equalsIgnoreCase("CONVALIDAR")){

            rc.envio_equivalencia_bd(cbd.getConexion(), rc.getEnviando(),cv.getCambios().getSelectedItem().toString(), cv.getInformacion());
           // for(int i=0; i<rc.getEnviando().size(); i++){
           // System.out.println(rc.getEnviando().get(i));

            //}


        }




    }



    

}//fin de la clase
