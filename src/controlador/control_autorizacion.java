/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.*;
import vista.PRINCIPAL;
import vista.autorizacion;

/**
 *
 * @author AN
 */
public class control_autorizacion implements ActionListener, KeyListener {

    private autorizacion aut;
    private conexion_base_de_datos cbd;
    private registros_bd regbd;
    private modelo_autorizacion modaut;
    private imagenes ima;
    

    public control_autorizacion(autorizacion aut) {
        this.aut = aut;
        cbd = new conexion_base_de_datos();
        regbd = new registros_bd();
        modaut = new modelo_autorizacion();
        ima = new imagenes();
       // p = new PRINCIPAL();
    }









    public void actionPerformed(ActionEvent e) {


        if(e.getActionCommand().equalsIgnoreCase("INGRESAR")){
                    
            if(modaut.verificar_usuario(cbd.getConexion(), Integer.valueOf(aut.getCedula().getText()), aut.getContraseña().getPassword())){//en caso de que sea autorizado                   
                     modaut.setVersion(aut.getVersion().getText());//estableciendo la version
                    modaut.setPeriodo(ima.seleccion_de_periodos("SELECCIONE EL PERIODO ACADEMICO CON EL QUE DESEA TRABAJAR", "PERIODO ACADEMICO"));//eligiendo el periodo academico para inicializar el programa con este
                    ima.mensaje_informacion("PERIODO SELECCIONADO: ("+modaut.getPeriodo()+")", "SELECCION", "precaucion", "png");



                    PRINCIPAL p = new PRINCIPAL();//inicializando la ventana principa
                    //p= new PRINCIPAL();
                    p.setVisible(true);

                     //colocando el periodo de inscripcion
                     p.setPeriodo_inscribir(modaut.getPeriodo());//inicializando el periodo academico

                      //cargando los valores iniciales de la pantalla principal
                     p.setResponsable(modaut.getCedulas()+" - "+modaut.getUsuario());
                     p.setTitle(p.getTitle().concat("                 USUARIO: ")+modaut.getCedulas()+" - "+modaut.getUsuario()+"          Acceso: "+modaut.getNivel());
                     p.setVersiones(modaut.getVersion());
                     modaut.accesos(p,modaut.getNivel());

                      fondos2 f = new fondos2(modaut.getVersion());//colocando un jpanel con la imagen y la version de la aplicacion
                      p.getFondo().add(f,BorderLayout.CENTER);
                   //  p.getFondo().getGraphics().drawImage(new registro_principal().cargar_imagen(modaut.getVersion()),0,0,null);
                      p.getFondo().updateUI();//actualizando el jpanel cada vez que se le agregue un componente

                      aut.dispose();//cerrando la ventana de autorizacion una vez terminado de cargar la ventana principal
            }else{}




        }//fin boton ingresar



    }

    public void keyTyped(KeyEvent e) {
       
    }

    public void keyPressed(KeyEvent e) {
       
    }

    public void keyReleased(KeyEvent e) {

       

        if(aut.getCedula().getText().equalsIgnoreCase("") && e.getComponent().getName().equalsIgnoreCase("cedula")){//analisis que solo se le hace al campo cedula
        System.out.println("campo cedula vacio");

        }else{
                if(e.getComponent().getName().equalsIgnoreCase("cedula")){//analisis solo al campo de cedula
                        // System.out.println( regbd.verificador(act.getCedula().getText()) );
                    if(regbd.verificador(aut.getCedula().getText())){
                            ima.mensaje_informacion("NO PUEDES COLOCAR SEPARADORES EN LA CEDULA, SOLO NUMEROS", "ADVERTENCIA: CAMPO CEDULA", "precaucion","png");
                            aut.getCedula().setText(modaut.getGuardar());
                    }else{modaut.setGuardar(aut.getCedula().getText());}
                }//solo analisis al campo de cedula1
        }



        if(e.getKeyCode()==10 && e.getComponent().getName().equalsIgnoreCase("contraseña")){//en caso de que presione la tecla enter en el campo de contraseña

                if(modaut.verificar_usuario(cbd.getConexion(), Integer.valueOf(aut.getCedula().getText()), aut.getContraseña().getPassword())){//en caso de que sea autorizado

                    modaut.setVersion(aut.getVersion().getText());//estableciendo la version
                    modaut.setPeriodo(ima.seleccion_de_periodos("SELECCIONE EL PERIODO ACADEMICO CON EL QUE DESEA TRABAJAR", "PERIODO ACADEMICO"));//eligiendo el periodo academico para inicializar el programa con este
                    ima.mensaje_informacion("PERIODO SELECCIONADO: ("+modaut.getPeriodo()+")", "SELECCION", "precaucion", "png");



                   PRINCIPAL p = new PRINCIPAL();//inicializando la ventana principa
                   // p= new PRINCIPAL();
                    p.setVisible(true);

                     //colocando el periodo de inscripcion
                     p.setPeriodo_inscribir(modaut.getPeriodo());//inicializando el periodo academico
                     
                      //cargando los valores iniciales de la pantalla principal
                     p.setResponsable(modaut.getCedulas()+" - "+modaut.getUsuario());
                     p.setTitle(p.getTitle().concat("                 USUARIO: ")+modaut.getCedulas()+" - "+modaut.getUsuario()+"          Acceso: "+modaut.getNivel());
                     p.setVersiones(modaut.getVersion());
                     modaut.accesos(p,modaut.getNivel());

                        fondos2 f = new fondos2(modaut.getVersion());//colocando un jpanel con la imagen y la version de la aplicacion
                      p.getFondo().add(f,BorderLayout.CENTER);
                   //  p.getFondo().getGraphics().drawImage(new registro_principal().cargar_imagen(modaut.getVersion()),0,0,null);
                      p.getFondo().updateUI();//actualizando el jpanel cada vez que se le agregue un componente

                      aut.dispose();//cerrando la ventana de autorizacion una vez terminado de cargar la ventana principal

                }else{}


        }






    }

}//fin de la clase
