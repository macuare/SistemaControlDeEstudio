/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.conexion_base_de_datos;
import modelo.registro_retiros;
import vista.retiros;

/**
 *
 * @author AN
 */
public class control_retiros implements ActionListener{
    
    private retiros retiro;
    private registro_retiros rret;

    public control_retiros(retiros retiro) {
        this.retiro = retiro;
        rret = new registro_retiros();
    }

    
    
    
    
    public void actionPerformed(ActionEvent e) {
      
        if(e.getActionCommand().equalsIgnoreCase("AGREGAR")){
        
            rret.ingresar_retiro(new conexion_base_de_datos().getConexion(),
                                 retiro.getCedula().getText() ,
                                 retiro.getTipo_retiro().getSelectedItem().toString().concat("_"+retiro.getObservacion().getText().toUpperCase()),
                                 retiro.getPeriodo().getText(),
                                 retiro.getUsuario());
           
        }
            
        
        
        if(e.getActionCommand().equalsIgnoreCase("ELIMINAR")){
        
            rret.eliminacion_retiro(new conexion_base_de_datos().getConexion(),
                                    retiro.getCedula().getText(),
                                    retiro.getPeriodo().getText()
                                    );
        
        }
        
        retiro.getCedula().setText(null);
        retiro.getObservacion().setText(null);
        retiro.getTipo_retiro().setSelectedIndex(0);
        
        
    }
    
    
    
    
    
    
    
}//fin de la clase
