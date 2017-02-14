/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import modelo.activo_inactivo;
import modelo.conexion_base_de_datos;
import modelo.registro_suspensiones;
import vista.suspenciones;

/**
 *
 * @author AN
 */
public class control_suspensiones implements ActionListener{

    private activo_inactivo ai ;
    private suspenciones s;
    private registro_suspensiones rs;
    private conexion_base_de_datos cbd ;

    public control_suspensiones(suspenciones s) {
        this.s = s;
        rs = new registro_suspensiones();
        cbd = new conexion_base_de_datos();
        ai = new activo_inactivo();
    }






    public void actionPerformed(ActionEvent e) {
        
       if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

           //rs.buscando(cbd.getConexion(), s.getCedula(), s.getBuscar(), s.getActivo(), s.getCambiar(), s.getAnalizar());
           rs.buscando_alternativa(new conexion_base_de_datos().getConexion(), s.getCedula(), s.getBuscar(), s.getActivo(), s.getCambiar(), s.getAnalizar());
           s.getBloqueo().setSelected(rs.consulta_bloqueo_inscripcion(new conexion_base_de_datos().getConexion(), s.getCedula().getText()));
       }

       if(e.getActionCommand().equalsIgnoreCase("CAMBIAR")){

            rs.bloqueo_desbloqueo_alternativo(new conexion_base_de_datos().getConexion(), s.getCedula().getText(), s.getBloqueo().isSelected());
           // rs.cambiando(cbd.getConexion(), s.getCedula(), s.getBuscar(), s.getActivo(), s.getCambiar(), s.getAnalizar());
            
       }
       
       if(e.getActionCommand().equalsIgnoreCase("ANALIZAR")){
           ai.setResumen(s.getResumen());
           ai.procesamiento_analisis(s.getCedula().getText());           
           rs.buscando_alternativa(new conexion_base_de_datos().getConexion(), s.getCedula(), s.getBuscar(), s.getActivo(), s.getCambiar(), s.getAnalizar());
           s.getBloqueo().setSelected(rs.consulta_bloqueo_inscripcion(new conexion_base_de_datos().getConexion(), s.getCedula().getText()));
       
       }


       if(e.getActionCommand().equalsIgnoreCase("MASIVO")){
            
             Runnable rx = new Runnable() {
                public void run() {
                        rs.busqueda_masiva(new conexion_base_de_datos().getConexion(), null, s.getResumen());                }
              } ;    
                Thread xx = new Thread(rx, "aplicacion");
                xx.start();
           
            
            
       }






    }


}//fin de la clase
