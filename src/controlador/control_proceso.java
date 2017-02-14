/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.conexion_base_de_datos;
import modelo.imagenes;
import modelo.procesamientos;
import vista.procesos;

/**
 *
 * @author AN
 */
public class control_proceso implements ActionListener {

    private procesos pro;
    private procesamientos p;
    private imagenes ima;

    public control_proceso(procesos pro) {
        this.pro = pro;
        p = new procesamientos();
        ima = new imagenes();
    }


    












public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("PROCESAR ACTAS")){

            Runnable rx = new Runnable() {//procesando en un hilo distinto en otra instancia
                public void run() {
                       p.archivos_analizar(ima.apertura_general_directorio("SELECCIONE DIRECTORIO DONDE SE ENCUENTRAN LAS ACTAS A PROCESAR"), pro.getResumen());
                       
                       p.verificar_calculos(new conexion_base_de_datos().getConexion(),"1-2011", pro.getResumen());
                }
             } ;    
           Thread xx = new Thread(rx, "PROCESAMIENTO_ACTAS_NOTAS");
           xx.start();
            
            /*
           Runnable rx2 = new Runnable() {//procesando en un hilo distinto en otra instancia
                public void run() {
                       p.verificar_calculos(new conexion_base_de_datos().getConexion(),"1-2011");
                }
             } ;    
           Thread xx2 = new Thread(rx2, "REVISANDO CALCULOS DE NOTAS");
           xx2.start();
           */
                

        }
    }











}//fin de la clase
