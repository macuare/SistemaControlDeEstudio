/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.conexion_base_de_datos;
import modelo.registro_autoridades;
import vista.autoridades;

/**
 *
 * @author AN
 */
public class control_autoridades implements ActionListener{

    private autoridades autoridad;
    private registro_autoridades ra;
    private conexion_base_de_datos cbd;

    public control_autoridades(autoridades autoridad) {
        this.autoridad = autoridad;
        cbd = new conexion_base_de_datos();
        ra = new registro_autoridades();

    }





    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){
            ra = new registro_autoridades(autoridad.getCedula().getText(), null, null, null, null, null);
            ra.buscar_autoridad(cbd.getConexion());
            ra.cargando_datos(autoridad);
        }

        if(e.getActionCommand().equalsIgnoreCase("MODIFICAR")){
            ra = new registro_autoridades(autoridad.getCedula().getText(), autoridad.getCargo().getSelectedItem().toString(), autoridad.getGrado().getText(), autoridad.getNombres().getText(), autoridad.getApellidos().getText(), autoridad.getIniciales().getText());
            //ra.buscar_autoridad(cbd.getConexion());
            ra.modificar_autoridad(new conexion_base_de_datos().getConexion());
            ra.busqueda_general(new conexion_base_de_datos().getConexion(), autoridad.getListado());
            ra.limpiar_datos(autoridad);
        }

        if(e.getActionCommand().equalsIgnoreCase("ELIMINAR")){
            ra = new registro_autoridades(autoridad.getCedula().getText(), autoridad.getCargo().getSelectedItem().toString(), autoridad.getGrado().getText(), autoridad.getNombres().getText(), autoridad.getApellidos().getText(), autoridad.getIniciales().getText());
            ra.buscar_autoridad(cbd.getConexion());
            ra.eliminar_autoridad(new conexion_base_de_datos().getConexion());
            ra.busqueda_general(new conexion_base_de_datos().getConexion(), autoridad.getListado());
            ra.limpiar_datos(autoridad);

        }

        if(e.getActionCommand().equalsIgnoreCase("NUEVO")){
            ra = new registro_autoridades(autoridad.getCedula().getText(), autoridad.getCargo().getSelectedItem().toString(), autoridad.getGrado().getText(), autoridad.getNombres().getText(), autoridad.getApellidos().getText(), autoridad.getIniciales().getText());
            ra.nueva_autoridad(cbd.getConexion());
            ra.busqueda_general(new conexion_base_de_datos().getConexion(), autoridad.getListado());
            ra.limpiar_datos(autoridad);

        }

        if(e.getActionCommand().equalsIgnoreCase("MOSTRAR")){
            ra.busqueda_general(cbd.getConexion(), autoridad.getListado());

        }






        
    }








}//fin de la clase
