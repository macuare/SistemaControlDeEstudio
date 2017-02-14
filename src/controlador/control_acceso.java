/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.conexion_base_de_datos;
import modelo.registro_acceso;
import vista.accesos;

/**
 *
 * @author AN
 */
public class control_acceso implements KeyListener{

    private accesos acc;
    private registro_acceso ra;

    public control_acceso(accesos acc) {
        this.acc = acc;
        ra = new registro_acceso();
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
        
        if(e.getKeyCode()==10 && e.getSource().toString().contains("cedula")){//solo si es enter
          ra.setEleccion( ra.seleccion_opciones(acc.getCedula(), acc.getNivel_acceso(), acc.getUsuario(), acc.getClave_actual(), acc.getClave_nueva(), acc.getRepetir_clave()) );        
        }
        
        if(e.getKeyCode()==10 && e.getSource().toString().contains("repetir_clave")){//solo si es enter
            if(ra.getEleccion().equalsIgnoreCase("nuevo usuario")) ra.nuevo_usuario(new conexion_base_de_datos().getConexion(),acc.getCedula(), acc.getUsuario(), acc.getClave_actual(), acc.getClave_nueva(), acc.getRepetir_clave(), acc.getNivel_acceso());
            if(ra.getEleccion().equalsIgnoreCase("modificar usuario")) ra.modificacion(new conexion_base_de_datos().getConexion(), acc.getCedula(), acc.getUsuario(), acc.getNivel_acceso(), acc.getClave_actual(), acc.getClave_nueva(), acc.getRepetir_clave());
            if(ra.getEleccion().equalsIgnoreCase("eliminar usuario")) ra.eliminaciones(new conexion_base_de_datos().getConexion(), acc.getCedula(), acc.getClave_actual());
        }
        
    }
    
    
    
    
    
    
    
    
    
}//fin de la clase
