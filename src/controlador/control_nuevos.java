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
import modelo.modelo_nuevos;
import vista.nuevos;

/**
 *
 * @author AN
 */
public class control_nuevos implements ActionListener, MouseListener {

    private nuevos n;
    private modelo_nuevos mn;

    public control_nuevos(nuevos n) {
        this.n = n;
        mn = new modelo_nuevos(n);
        
    }




    
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("INSCRIBIR")){
            System.out.println("INSCRIBIENDO");

            mn.ejecutando(n.getCedula().getText(),n.getPeriodo().getText());
        //    mn= new modelo_nuevos(n);
            mn.getMaterias().clear();
            mn.getInformacion().clear();
            mn.getUsadas().clear();
            
        }

        if(e.getActionCommand().equalsIgnoreCase("BORRAR")){

            mn.eliminar_inscripcion(new conexion_base_de_datos().getConexion(), n.getCedula().getText(),n.getPeriodo().getText());

        }
      
    }

    public void mouseClicked(MouseEvent e) {
      
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
