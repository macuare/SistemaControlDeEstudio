/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import modelo.conexion_base_de_datos;
import modelo.registro_ofertas;
import vista.visor;

/**
 *
 * @author AN
 */
public class control_visor implements ActionListener, ChangeListener, ItemListener{

    private visor v;
    private registro_ofertas ro;
    private conexion_base_de_datos cbd;

    public control_visor(visor v) {
        this.v = v;
        ro = new registro_ofertas();
        cbd = new conexion_base_de_datos();
    }










    public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

                ro.exploracion(v, cbd.getConexion(), v.getCarrera().getSelectedItem().toString(), v.getSemestre().getSelectedIndex(),v.getV_periodo().getText());


            }//fin boton bustar



            if(e.getActionCommand().equalsIgnoreCase("ELIMINAR")){


            ro.borrado(cbd.getConexion(), Integer.parseInt(v.getPosiciones().getText()));
            v.getPosiciones().setText("");
            ro.exploracion(v, cbd.getConexion(), v.getCarrera().getSelectedItem().toString(), v.getSemestre().getSelectedIndex(),v.getV_periodo().getText());

            v.getEliminar().setEnabled(false);
            v.getPosiciones().setEnabled(false);
            v.getHabilitar().setSelected(false);

            }






    }//fin actionperformed

    public void stateChanged(ChangeEvent e) {


System.out.println("cambios "+v.getHabilitar().isSelected());

v.getPosiciones().setEnabled(v.getHabilitar().isSelected());
v.getEliminar().setEnabled(v.getHabilitar().isSelected());

    }

    public void itemStateChanged(ItemEvent e) {      

        if(e.getStateChange()==1){

            System.out.println("Escuchando "+v.getSemestre().getSelectedItem().toString());
            ro.secciones(cbd.getConexion(), v.getCarrera().getSelectedItem().toString(), v.getSemestre().getSelectedIndex(), v.getSecciones(), v.getV_periodo().getText());
        }
        
    }









}//fin de la clase
