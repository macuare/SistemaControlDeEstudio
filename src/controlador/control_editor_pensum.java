/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.table.DefaultTableModel;
import modelo.conexion_base_de_datos;
import modelo.configuracion_tablas;
import modelo.editor_pensum;
import vista.pensum;

/**
 *
 * @author AN
 */
public class control_editor_pensum implements ActionListener, MouseListener, WindowListener, KeyListener{

    private pensum p;
    private editor_pensum ep;
    private conexion_base_de_datos cbd;
    private configuracion_tablas ct;

    public control_editor_pensum(pensum p) {
        this.p = p;
        ep = new editor_pensum();
        cbd = new conexion_base_de_datos();
        ct = new configuracion_tablas();
    }




    



    public void actionPerformed(ActionEvent e) {


            if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

                ep.getCodigos().clear();//limpiando el LinkedList que guarda los codigos viejos y nuevos de todos incluso los mismos

                ep.listado(cbd.getConexion(), p.getPensum().getSelectedItem().toString(), p.getMaterias());
            
                ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "SEMESTRE", 30, "centrado");            
                ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "ASIGNATURA", 350, "izquierda");
                ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "UC", 20, "centrado");



            }


    if(e.getActionCommand().equalsIgnoreCase("MODIFICAR")){


                if(ep.confirmacion_modificar("MODIFICACION")==0){//si se acepta realizar las modificaciones

                    ep.tramites(p.getMaterias(),p.getPensum().getSelectedItem().toString());//realizando la modificacion
                    ep.listado(cbd.getConexion(), p.getPensum().getSelectedItem().toString(), p.getMaterias());//buscando otra vez para que se vea la modificacion
                         ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "SEMESTRE", 30, "centrado");
                         ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "ASIGNATURA", 350, "izquierda");
                         ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "UC", 20, "centrado");

                          ep.getCodigos().clear();//limpiando el LinkedList que guarda los codigos viejos y nuevos de todos incluso los mismos

                }else{}


        }



        if(e.getActionCommand().equalsIgnoreCase("ELIMINAR")){

                if(ep.confirmacion_modificar("ELIMINACION")==0){//si se acepta realizar eliminacion

                   ep.eliminando_materia(p.getMaterias(),p.getPensum().getSelectedItem().toString());//eliminando la materia seleccionada

                    ep.listado(cbd.getConexion(), p.getPensum().getSelectedItem().toString(), p.getMaterias());//buscando otra vez para que se vea la eliminacion
                             ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "SEMESTRE", 30, "centrado");
                             ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "ASIGNATURA", 350, "izquierda");
                             ct.configuracion(p.getMaterias(), (DefaultTableModel) p.getMaterias().getModel(), "UC", 20, "centrado");

                              ep.getCodigos().clear();//limpiando el LinkedList que guarda los codigos viejos y nuevos de todos incluso los mismos

                }else{}



        }



        if(e.getActionCommand().equalsIgnoreCase("AGREGAR")){


                if(ep.confirmacion_modificar("ADICION DE MATERIA")==0){//si se acepta realizar la adicion de materia
                    System.out.println("Adicion de materia confirmada");
                    ep.agregar_materia(cbd.getConexion(),
                                       p.getPensum().getSelectedItem().toString(),
                                       p.getSemestre().getSelectedIndex()+1,
                                       p.getCodigo().getText().toUpperCase(),
                                       p.getAsignatura().getText().toUpperCase(),
                                       p.getUc().getSelectedIndex()+1,
                                       p.getPrelacion_1().getText().toUpperCase(),
                                       p.getPrelacion_2().getText().toUpperCase(),
                                       p.getPrelacion_3().getText().toUpperCase(),
                                       p.getPrelacion_4().getText().toUpperCase(),
                                       p.getPrelacion_5().getText().toUpperCase(),
                                       p.getPrelacion_6().getText().toUpperCase());
                    //limpiando los campos
                    p.getSemestre().setSelectedIndex(0);
                    p.getCodigo().setText("");
                    p.getAsignatura().setText("");
                    p.getUc().setSelectedIndex(0);
                    p.getPrelacion_1().setText("");
                    p.getPrelacion_2().setText("");
                    p.getPrelacion_3().setText("");
                    p.getPrelacion_4().setText("");
                    p.getPrelacion_5().setText("");
                    p.getPrelacion_6().setText("");
                    
                    p.getSemestre().requestFocus();//solicitando foco
                    

                }else{}




        }












    }//fin del action performed








    public void mouseClicked(MouseEvent e) {

       
        ep.seleccion(p.getMaterias());

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
       
    }





    public void windowOpened(WindowEvent e) {
        ep.lista_tabla_bd(cbd.getConexion(), p.getPensum());
    }

    public void windowClosing(WindowEvent e) {

    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }





    
    public void keyTyped(KeyEvent e) {
       
    }

    public void keyPressed(KeyEvent e) {
       // System.out.println("teclas: "+e.getKeyCode()+" - "+e.getKeyChar());
        if(e.getKeyCode()==10){//cuando se presiona la tecla ENTER

                if(ep.confirmacion_modificar("ADICION DE MATERIA")==0){//si se acepta realizar la adicion de materia
                    System.out.println("Adicion de materia confirmada");
                    ep.agregar_materia(cbd.getConexion(),
                                       p.getPensum().getSelectedItem().toString(),
                                       p.getSemestre().getSelectedIndex()+1,
                                       p.getCodigo().getText(),
                                       p.getAsignatura().getText(),
                                       p.getUc().getSelectedIndex()+1,
                                       p.getPrelacion_1().getText(),
                                       p.getPrelacion_2().getText(),
                                       p.getPrelacion_3().getText(),
                                       p.getPrelacion_4().getText(),
                                       p.getPrelacion_5().getText(),
                                       p.getPrelacion_6().getText() 
                                        );

                    //limpiando los campos
                    p.getSemestre().setSelectedIndex(0);
                    p.getCodigo().setText("");
                    p.getAsignatura().setText("");
                    p.getUc().setSelectedIndex(0);
                    p.getPrelacion_1().setText("");
                    p.getPrelacion_2().setText("");
                    p.getPrelacion_3().setText("");
                    p.getPrelacion_4().setText("");
                    p.getPrelacion_5().setText("");
                    p.getPrelacion_6().setText("");

                    p.getSemestre().requestFocus();//solicitando foco

                }else{}

        }
        

    }

    public void keyReleased(KeyEvent e) {

    }









}
