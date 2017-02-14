/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.conexion_base_de_datos;
import modelo.registro_documentacion;
import vista.documentacion;

/**
 *
 * @author AN
 */
public class control_documentacion implements ActionListener{

    private documentacion docu;
    private registro_documentacion rdocu;

    public control_documentacion(documentacion docu) {
        this.docu = docu;
        rdocu = new registro_documentacion();
    }
    
    
    
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){
           if(docu.getCedula().getText().length()>0){ //solo si hay algo escrito en la cedula que no este vacio se procede
            rdocu.buscar_usuario(new conexion_base_de_datos().getConexion(), docu.getCedula().getText());
            docu.getEstudiante().setText(rdocu.getEstudiante());
            docu.getCarrera().setText(rdocu.getCarrera());
            docu.getTurno().setText(rdocu.getTurno());
            
            
            rdocu.marcando(rdocu.estado_documentacion(new conexion_base_de_datos().getConexion(), docu.getCedula().getText()),
                           docu.getPdp(),
                           docu.getFc(),
                           docu.getCs(),
                           docu.getCcinu(),
                           docu.getPi_cnu_rusnieus(),
                           docu.getFnt(), 
                           docu.getCt(),
                           docu.getNc(),
                           docu.getCrm(),
                           docu.getProcesar()
                           );
            
           }
        }
        
        if(e.getActionCommand().equalsIgnoreCase("PROCESAR")){
            rdocu.procesar(new conexion_base_de_datos().getConexion(), docu.getCedula().getText(),
                                           docu.getPdp().isSelected(),
                                           docu.getFc().isSelected(),
                                           docu.getCs().isSelected(),
                                           docu.getCcinu().isSelected(),
                                           docu.getPi_cnu_rusnieus().isSelected(),
                                           docu.getFnt().isSelected(), 
                                           docu.getCt().isSelected(),
                                           docu.getNc().isSelected(),
                                           docu.getCrm().isSelected(),
                                           "---");
        //limpiando variables    
            docu.getCedula().setText(null);
            docu.getEstudiante().setText(null);
            docu.getCarrera().setText(null);
            docu.getTurno().setText(null);
            docu.getPdp().setSelected(false);
            docu.getFc().setSelected(false);
            docu.getCs().setSelected(false);
            docu.getCcinu().setSelected(false);
            docu.getPi_cnu_rusnieus().setSelected(false);
            docu.getFnt().setSelected(false);
            docu.getCt().setSelected(false);
            docu.getNc().setSelected(false);
            docu.getCrm().setSelected(false);
            docu.getProcesar().setEnabled(true);
         }
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
}//fin de la clase
