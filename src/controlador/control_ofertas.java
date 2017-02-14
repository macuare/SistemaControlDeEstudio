/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import modelo.*;
import vista.ofertas;
import vista.visor;

/**
 *
 * @author AN
 */
public class control_ofertas implements ActionListener, ItemListener, WindowListener, ChangeListener, MouseListener{

    private ofertas oferta;
    private registro_ofertas reg;
    private materias materia;
    private conexion_base_de_datos cbd;
    private Archivos docu;
    private imagenes ima;

    public control_ofertas(ofertas oferta) {
        this.oferta=oferta;
        reg= new registro_ofertas();
        materia= new materias();
        cbd = new conexion_base_de_datos();
        docu = new Archivos();
        ima = new imagenes();
    }
    



    public void actionPerformed(ActionEvent e) {

       if(e.getActionCommand().equalsIgnoreCase("atras")){
           

           oferta.getSalones().setText(materia.secciones_dinamicas(
                                    oferta.getSemestre(), oferta.getSalones().getText(),"resta"));
          
                                                        }


    if(e.getActionCommand().equalsIgnoreCase("siguiente")){
         oferta.getSalones().setText(materia.secciones_dinamicas(
                                oferta.getSemestre(), oferta.getSalones().getText(),"suma"));

                                                          }


     if(e.getActionCommand().equalsIgnoreCase("GUARDAR")){

              if(reg.confirmacion_ofertar(oferta.getAsignaturas().getSelectedItem().toString())==0){//confirmando la oferta de la materia

                    String docentes=null;
                    if(oferta.getDocente().getText().length()>0){docentes=oferta.getDocente().getText().toUpperCase();}else{docentes=oferta.getLista_docente().getSelectedItem().toString().toUpperCase();}//seleccionando la informacion del docente. de la lista. o la introduccion manual

                     reg.ofertas_materias(cbd.getConexion(),
                     oferta.getCarrera().getSelectedItem().toString(),
                     oferta.getSemestre().getSelectedIndex()+1,
                     oferta.getSalones().getText(),
                     oferta.getDias().getSelectedItem().toString(),
                     oferta.getDesde().getSelectedItem().toString(),
                     oferta.getHasta().getSelectedItem().toString(),
                     oferta.getPeriodo().getText(),
                     oferta.getAsignaturas().getSelectedItem().toString(),
                     docentes,
                     Integer.valueOf(oferta.getCapacidad().getText()));

                     System.out.println(oferta.getAsignaturas().getSelectedItem().toString()+"     guardado");


                    registro_ingenieria regis = new registro_ingenieria();
                    ima.mensaje_informacion("INFORMACION GUARDADA", "NOTIFICACION", "exito", "png");


                    oferta.getDocente().setText("");//limpiando el campo donde se coloca el nombre del docente

              }//fin confirmacion

     }//fin boton guardar



       if(e.getActionCommand().equalsIgnoreCase("VISOR")){

                visor v = new visor();
                v.setVisible(true);
                v.getV_periodo().setText(oferta.getPeriodo().getText());
       }




       if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

           reg.busqueda_ofertadas(cbd.getConexion(), Integer.valueOf(oferta.getPosicion().getText()), oferta);
           oferta.getGuardar().setEnabled(false);
            
       }


        if(e.getActionCommand().equalsIgnoreCase("MODIFICAR")){

        
            reg.modificacion_ofertadas(cbd.getConexion(),Integer.valueOf(oferta.getPosicion().getText()), oferta);

            oferta.getActivar().setSelected(false);
            oferta.getAsignaturas().setEnabled(true);
            oferta.getCarrera().setEnabled(true);
            oferta.getSemestre().setEnabled(true);
            oferta.getPosicion().setText("");
            oferta.getGuardar().setEnabled(true);


            
       }







       


    }//fin del ActionPerformed




//INSERT INTO prototipo_cp SELECT asignatura FROM lic_contaduria_publica_2007;
//INSERT INTO prototipo_es SELECT asignatura FROM lic_economia_social_2010_n;
//INSERT INTO prototipo_ei SELECT asignatura FROM lic_educacion_integral_2010_n;
//INSERT INTO prototipo_tu SELECT asignatura FROM tsu_turismo_2010_d;





    public void itemStateChanged(ItemEvent e) {
        //System.out.println(e.getItemSelectable().toString().substring(22));


        if(e.getStateChange()==1 ){
        
        if(e.getItemSelectable().toString().substring(22).startsWith("semestre")){
        
        oferta.getSalones().setText(materia.secciones_dinamicas(
        oferta.getSemestre(), oferta.getSalones().getText(),""));

            reg.cargar_pensum_alternativo(cbd.getConexion(), reg.especialidad_pensum(oferta.getCarrera().getSelectedItem().toString()), (oferta.getSemestre().getSelectedIndex()+1));
            reg.llenado_asignaturas(oferta.getAsignaturas());
            reg.getPen().clear();//limpiando el LinkedList
            reg.getPen_materias().clear();
        
            /*reg.cargar_pensum(cbd.getConexion(), materia.carrera(oferta.getCarrera().getSelectedItem().toString()),oferta.getSemestre().getSelectedIndex()+1);//cargando el pensum segun la carrera y el semestre
            reg.llenado_asignaturas(oferta.getAsignaturas());
            reg.getPen().clear();//limpiando el LinkedList
            reg.getPen_materias().clear();*/
        
        }

            if(e.getItemSelectable().toString().substring(22).startsWith("carrera")){

            oferta.getSalones().setText(materia.seccion(oferta.getCarrera().getSelectedItem().toString()).substring(0,3).concat(oferta.getSalones().getText().substring(3,6)));
            System.out.println(reg.especialidad_pensum(oferta.getCarrera().getSelectedItem().toString()));

            reg.cargar_pensum_alternativo(cbd.getConexion(), reg.especialidad_pensum(oferta.getCarrera().getSelectedItem().toString()), (oferta.getSemestre().getSelectedIndex()+1));
            reg.llenado_asignaturas(oferta.getAsignaturas());
            reg.getPen().clear();//limpiando el LinkedList
            reg.getPen_materias().clear();

            System.out.println("N° elementos: "+oferta.getAsignaturas().getItemCount());

                /*reg.cargar_pensum(cbd.getConexion(), materia.carrera(oferta.getCarrera().getSelectedItem().toString()),oferta.getSemestre().getSelectedIndex()+1);//cargando el pensum segun la carrera y el semestre
                reg.llenado_asignaturas(oferta.getAsignaturas());
                reg.getPen().clear();//limpiando el LinkedList
                reg.getPen_materias().clear();*/


            }        
        }else{}

    }

    public void windowOpened(WindowEvent e) {


           oferta.getSalones().setText(materia.seccion(oferta.getCarrera().getSelectedItem().toString()).substring(0,3).concat(oferta.getSalones().getText().substring(3,6)));
            System.out.println(reg.especialidad_pensum(oferta.getCarrera().getSelectedItem().toString()));

            reg.cargar_pensum_alternativo(cbd.getConexion(), reg.especialidad_pensum(oferta.getCarrera().getSelectedItem().toString()), (oferta.getSemestre().getSelectedIndex()+1));
            reg.llenado_asignaturas(oferta.getAsignaturas());
            reg.getPen().clear();//limpiando el LinkedList
            reg.getPen_materias().clear();

            reg.cargando_horas(cbd.getConexion(),oferta.getDesde(),oferta.getHasta());            
            System.out.println("N° elementos: "+oferta.getAsignaturas().getItemCount());


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

    public void stateChanged(ChangeEvent e) {

        oferta.getPosicion().setEnabled(oferta.getActivar().isSelected());
        oferta.getBuscar().setEnabled(oferta.getActivar().isSelected());
        oferta.getModificar().setEnabled(oferta.getActivar().isSelected());

            if(oferta.getActivar().isSelected()){
                    oferta.getGuardar().setEnabled(false);
                    
            }
            else {oferta.getGuardar().setEnabled(true);}



    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
       //System.out.println(e.getButton());
     //  oferta.setOpacity(0.2f);
    }

    public void mouseReleased(MouseEvent e) {
       // oferta.setOpacity(1f);
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }












}//fin de la clase control_ofertas
