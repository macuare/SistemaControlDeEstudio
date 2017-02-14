/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.conexion_base_de_datos;
import modelo.modelo_configuraciones;
import vista.configuraciones;


/**
 *
 * @author AN
 */
public class control_configuraciones implements ActionListener, MouseListener{

    private configuraciones conf;
    private conexion_base_de_datos cbd;
    private modelo_configuraciones mc;

    public control_configuraciones(configuraciones conf) {
        this.conf = conf;
        cbd = new conexion_base_de_datos();
        mc = new modelo_configuraciones();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("CARGAR HORARIO")){
            mc.cargar_configuracion_horario(cbd.getConexion(), conf.getHorario());
        }
        
        if(e.getActionCommand().equalsIgnoreCase("GUARDAR")){
            mc.guardar_horario(cbd.getConexion(), conf.getHorario());//guarda
            mc.cargar_configuracion_horario(cbd.getConexion(), conf.getHorario());//luego se vuelve a cargar los datos
        }
        
        
        
    }

    
    
    public void mouseClicked(MouseEvent e) {
        if(e.isMetaDown()){
            mc.modificador_en_vivo(conf.getHorario());
        }
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
    
    
    
    
    
    
    
    
}