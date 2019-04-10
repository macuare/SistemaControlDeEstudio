/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import modelo.*;
import vista.guardar;
import vista.listados;

/**
 *
 * @author AN
 */
public class control_listados implements ActionListener, WindowListener, ItemListener{

    private listados l;
    private registro_listados rl;
    private conexion_base_de_datos cbd;
    private guardar g;
    private registro_notas rn;
    private Gestor_Ruta gr;

    public control_listados(listados l) {
        this.l = l;
        rl = new registro_listados();
        cbd = new conexion_base_de_datos();
        rn = new registro_notas();
        gr = new Gestor_Ruta();
    }

    public control_listados(guardar g) {
        this.g = g;
        rl = new registro_listados();
        cbd = new conexion_base_de_datos();
        rn = new registro_notas();
    }








    public void actionPerformed(ActionEvent e) {

       // System.out.println("rastreo: "+e);

        if(e.getActionCommand().equalsIgnoreCase("LISTAR")){

                rl.busqueda_filtrada(cbd.getConexion(), l.getCarrera().getSelectedItem().toString(), l.getMaterias().getSelectedItem().toString() ,l.getSeccion().getSelectedItem().toString(), l.getTabla_alumnos(), l.getPeriodo_actual().getText());
                l.getCantidad().setText(String.valueOf(rl.getCapacidad()));
                

        }


        if(e.getActionCommand().equalsIgnoreCase("GENERAR")){
            
            rl.setCarreras(l.getCarrera().getSelectedItem().toString());
            rl.setMaterias(l.getMaterias().getSelectedItem().toString());
            rl.setSeccion(l.getSeccion().getSelectedItem().toString());
            rl.setAulas(l.getSeccion());
            rl.setHabilitado(l.getTodos_listados().isSelected());
            rl.setAuxiliar(l.getMaterias());
            rl.setPeriodo(l.getPeriodo_actual().getText());
            
                if(l.getTodos_listados().isSelected()){//solo si todo_listado esta seleccionado
                    
                    Runnable rx = new Runnable() {//independizando proceso
                        public void run() {
                                          rl.exportar_data_masivo(cbd,
                                                    l.getCarrera().getSelectedItem().toString(),
                                                    l.getMaterias(),
                                                    gr.guardar_archivo(),
                                                    ".csv",
                                                    l.getSeccion(),
                                                    l.getPeriodo_actual().getText());
                                          
                         
                        }
                    };
                    Thread t = new Thread(rx, "listado_masivo_secciones");
                    t.start();
                  
                }else{//sino se genera solo el de la carrera segun la materia de interes
                    
                                    rl.exportar_data(cbd.getConexion(),
                                         rl.getCarreras(),
                                         rl.getMaterias(),
                                         rl.getSeccion(),
                                         gr.guardar_archivo().concat(File.separator+rl.getMaterias()+"_"+rl.getSeccion()+".csv"),
                                         rl.getPeriodo());
                                    new imagenes().mensaje_informacion("Generación del listado terminado...!!!", "LISTA DE ASISTENCIA", "exito", "png");
                }
            
            
            
            
            /*
            g = new guardar();
            g.setVisible(true);
            g.setRl(rl);
            g.getSitio().setSelectedFile(new File("lista_"+l.getMaterias().getSelectedItem().toString()+"_"+l.getSeccion().getSelectedItem().toString()));//estableciendo ruta por defecto
            * */
            
        }


        if(e.getActionCommand().equalsIgnoreCase("CancelSelection")){
            g.dispose();
        }

        if(e.getActionCommand().equalsIgnoreCase("ApproveSelection")){
            //listados lx=new listados();

            System.out.println("aprobado "+g.getSitio().getSelectedFile());
            System.out.println("aprobado "+g.getSitio().getFileFilter().getDescription());
            System.out.println("ruta completa "+g.getSitio().getSelectedFile()+g.getSitio().getFileFilter().getDescription());
            
            System.out.println("vinculo  "+g.getRl().getCarreras()+" "+g.getRl().getSeccion());
            System.out.println("otros "+g.getSitio().getSelectedFile()+g.getSitio().getFileFilter().getDescription());
             System.out.println("ruta opcional "+g.getSitio().getCurrentDirectory().getAbsolutePath());

            
                        if(g.getRl().getHabilitado()){//si se habilita esta opcion se imprimiran las listas de toda la carrera
                       
                            rl.exportar_data_masivo(cbd,
                                                    g.getRl().getCarreras(),
                                                    g.getRl().getAuxiliar(),
                                                    g.getSitio().getCurrentDirectory().getAbsolutePath(),
                                                    g.getSitio().getFileFilter().getDescription(),
                                                    g.getRl().getAulas(),
                                                    g.getRl().getPeriodo());

                           rn.listado_no_inscritos(cbd.getConexion(), g.getRl().getCarreras(),g.getSitio().getCurrentDirectory().getAbsolutePath(),g.getRl().getPeriodo());//exportando dentro de la carpeta los estudiantes no inscritos
                           rl.aularios_carrera(cbd.getConexion(), g.getRl().getCarreras(), g.getSitio().getCurrentDirectory().getAbsolutePath(),g.getRl().getPeriodo());//distribucion de los aularios


                        }else{//en caso contrario solo la de conveniencia
                            
                            rl.exportar_data(cbd.getConexion(),
                                         g.getRl().getCarreras(),
                                         g.getRl().getMaterias(),
                                         g.getRl().getSeccion(),
                                         g.getSitio().getSelectedFile()+g.getSitio().getFileFilter().getDescription(),
                                         g.getRl().getPeriodo());
                            
                        }
                    

  


            g.dispose();

        }





        



    }//fin action command


    public void itemStateChanged(ItemEvent e) {

         if(e.getStateChange()==1 ){
            // System.out.println(e.getItemSelectable().getSelectedObjects()[0]);
             //System.out.println(e.getItemSelectable());
             


                 if(e.getItemSelectable().toString().substring(22).startsWith("carrera")){

                    rl.inicializacion(cbd.getConexion(), l.getCarrera().getSelectedItem().toString(), l.getMaterias() ,l.getSeccion());

                 }
     // rl.inicializacion(cbd.getConexion(), l.getCarrera().getSelectedItem().toString(),l.getSeccion());

                if(e.getItemSelectable().toString().substring(22).startsWith("materia")){

                    l.getSeccion().setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
                    
                     rl.inicializacion_2(cbd.getConexion(), l.getCarrera().getSelectedItem().toString(), l.getMaterias() ,l.getSeccion());
                   


                }







        }else{}



    }
  




    public void windowOpened(WindowEvent e) {

          rl.inicializacion(cbd.getConexion(), l.getCarrera().getSelectedItem().toString(), l.getMaterias() ,l.getSeccion());
        

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









}
