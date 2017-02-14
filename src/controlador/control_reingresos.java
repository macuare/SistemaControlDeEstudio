/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.registro_reingresos;
import vista.reingresos;

/**
 *
 * @author AN
 */
public class control_reingresos implements ActionListener, MouseListener {
    private reingresos r;
    private registro_reingresos rr;

    public control_reingresos(reingresos r) {
        this.r = r;
        rr = new registro_reingresos();
                
    }
    
    
    
    
    
    public void actionPerformed(ActionEvent e) {
    
        if(e.getActionCommand().equalsIgnoreCase("buscar")){
            rr.llenar_historial(r.getHistorial(), r.getFoto(),r.getCedula().getText());
            r.getEstudiante().setText(rr.getEstudiante());
            r.getCarrera().setText(rr.getCarrera());
            r.getTurno().setText(rr.getTurno());
        
        }
        
        
    }

    public void mouseClicked(MouseEvent e) {
        if(e.isMetaDown()){
          //  System.out.println("PRESIONADO: tabla: "+r.getHistorial().getValueAt(r.getHistorial().getSelectedRow(),0));
            //rr.eliminar(r.getHistorial(), r.getCedula().getText());
            
            if(rr.seleccion(r.getHistorial(), r.getCedula().getText(), r.getPeriodo().getText(), r.getUsuario())) 
             rr.llenar_historial(r.getHistorial(), r.getFoto(),r.getCedula().getText());//se llena otra vez la tabla despues de los cambios
        
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
    
    
    
}//fin de la clase
