/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import modelo.*;

import vista.guardar1;
import vista.masivos;
import vista.procesos;
import vista.progreso;

/**
 *
 * @author AN
 */
public class control_masivo implements WindowListener, ActionListener, ChangeListener, Runnable{

    private masivos m;
    private registro_masivo rm;
    private conexion_base_de_datos cbd;
    private modelo_progreso mp;
    private Thread hilos;
    private registro_notas rn;
    private imagenes ima;
    private Archivos doc;
    private guardando gndo;
    


    private guardar1 g;

  


    public control_masivo(guardar1 g) {
       this.g = g;
      
        //rm = new registro_masivo();
        //cbd = new conexion_base_de_datos();
        //mp = new modelo_progreso();
        //rn = new registro_notas();
        //ima = new imagenes();
        //doc = new Archivos();
    }
    


    public control_masivo(masivos m) {
        this.m = m;        
        rm = new registro_masivo();
        cbd = new conexion_base_de_datos();
        mp = new modelo_progreso();
        rn = new registro_notas();
        ima = new imagenes();
        doc = new Archivos();
        gndo = new guardando();
        
        //hilos = new Thread(this);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("INSCRIPCION MASIVA")){
            System.out.println("inscripcion masiva");

            //rm.lista_inscritos("",false,false,false,cbd.getConexion(), m.getCarrera().getSelectedItem().toString(),"2-2010",true);

            ima.mensaje_informacion("INSCRIPCION MASIVA TERMINADA","INFORMACION", "exito", "png");

        }

        
        if(e.getActionCommand().equalsIgnoreCase("VIGENCIAS MASIVAS")){

//            hilos.start();
                 Runnable rx = new Runnable() {
                               public void run() {
                                    rm.vigencia_actualizados(cbd.getConexion(), m.getNucleo_extension().getSelectedItem().toString(),1,null);
                                                 } 
                }; Thread xx = new Thread(rx, "VIGENCIAS_MASIVAS"); xx.start();
                
                //ima.mensaje_informacion("VIGENCIAS MASIVAS TERMINADA","INFORMACION", "exito", "png");

        }

        if(e.getActionCommand().equalsIgnoreCase("VIGENCIA")){



                rm.vigencia_actualizados(cbd.getConexion(), m.getNucleo_extension().getSelectedItem().toString(),0,m.getV_cedula().getText());
                ima.mensaje_informacion("ACTUALIZACION DE VIGENCIA TERMINADA","INFORMACION", "exito", "png");

        }

        if(e.getActionCommand().equalsIgnoreCase("OK")){
            System.out.println("probando_pestañas ---- indice: "+m.getSelecciones().getModel().getSelectedIndex());
            m.getSelecciones().setEnabledAt(m.getSelecciones().getModel().getSelectedIndex(), false);



        }


        if(e.getActionCommand().equalsIgnoreCase("GENERAR PDF")){
            ima = new imagenes();//inicializando
//            m = new masivos();//inicializando
            ima.ventana_auxiliar(m,"DIRECTORIO          Desarrollado por Ing. Cusatti Andy",this);
            

            //g = new guardar1();
            //g.setVisible(true);
            //g.getSitio().setSelectedFile(new File("dejar en blanco"));//estableciendo ruta por defecto

        }

                if(e.getActionCommand().equalsIgnoreCase("CancelSelection")){
                   System.out.println("cancelando: "+m.getPdf_cedula().getText()+" ruta: "+ima.getSeleccion().getCurrentDirectory());                  
                   ima.getDialogo().dispose();
                }

                if(e.getActionCommand().equalsIgnoreCase("ApproveSelection")){

                        rm.setObservacion(m.getPdf_observacion().getText());//agregando observacion en el record academico

                    System.out.println("cedula "+m.getPdf_cedula().getText()+" ruta: "+ima.getSeleccion().getSelectedFile()+ima.getSeleccion().getCurrentDirectory().separator);

                            if(m.getPdf_individual().isSelected()){//individual
                               

                                    Runnable rx = new Runnable() {
                                            public void run() {
                                                //String ruta= ima.getSeleccion().getSelectedFile()+ima.getSeleccion().getCurrentDirectory().separator+"Constancia_Inscripcion_"+m.getPdf_cedula().getText()+".pdf";
                                                String ruta= ima.getSeleccion().getSelectedFile()+ima.getSeleccion().getCurrentDirectory().separator;
                                                String cedula = m.getPdf_cedula().getText() ;   
                                                rm.pdf_individual(ima.getSeleccion().getSelectedFile()+ima.getSeleccion().getCurrentDirectory().separator, m.getPdf_cedula().getText(), m.getPdf_inscripcion().isSelected(), m.getPdf_record().isSelected(), m.getPdf_estudio().isSelected(),m.getPdf_culminacion().isSelected() ,m.getPdf_certificadas().isSelected() , Integer.valueOf(m.getPdf_ajuste().getText()), m.getPeriodo_academico().getText());
                                                m.getPdf_cedula().setText("");
                                    //ima.mensaje_informacion("DOCUMENTO INDIVIDUAL TERMINADA","INFORMACION", "exito", "png");
                                                    m.getPdf_ajuste().setText("30");
                                //     doc.abrir_archivos_windows(ruta);
                                         //       doc.selector_documentos(ruta,cedula, m.getPdf_inscripcion().isSelected(), m.getPdf_record().isSelected());
                                            } 
                                    } ; Thread xx = new Thread(rx, "individual"); xx.start();

                               
                            }else{//masivos
                                
                                    Runnable rx = new Runnable() {
                                        public void run() {
                                            rm.lista_inscritos(ima.getSeleccion().getSelectedFile()+ima.getSeleccion().getCurrentDirectory().separator, m.getPdf_inscripcion().isSelected(), m.getPdf_record().isSelected(), m.getPdf_estudio().isSelected(),cbd.getConexion(), m.getCarrera().getSelectedItem().toString(), m.getPeriodo_academico().getText(), false, m.getPdf_culminacion().isSelected(), m.getPdf_certificadas().isSelected() ,Integer.valueOf(m.getPdf_ajuste().getText()));                               
                                            m.getPdf_ajuste().setText("30");    
                                        } 
                                    } ; Thread xx = new Thread(rx, "masivos"); xx.start();

                            }


                   ima.getDialogo().dispose();
                }
        



        if(e.getActionCommand().equalsIgnoreCase("INICIAR")){

            
               if(m.getN_todo().isSelected()){
                    //System.out.println("todo...."+m.getN_cedula().getText()+" "+m.getN_periodo().getSelectedItem().toString()+" "+m.getCarrera().getSelectedItem().toString());
                    rn.pase_de_notas("TODO",m.getN_cedula().getText(),m.getN_periodo().getSelectedItem().toString(), m.getCarrera().getSelectedItem().toString());
                    ima.mensaje_informacion("ACTUALIZACION MASIVA TERMINADA","INFORMACION", "exito", "png");
                }else{
                    //System.out.println("solito...."+m.getN_cedula().getText()+" "+m.getN_periodo().getSelectedItem().toString()+" "+m.getCarrera().getSelectedItem().toString());
                    rn.pase_de_notas("INDIVIDUAL",m.getN_cedula().getText(),m.getN_periodo().getSelectedItem().toString(), m.getCarrera().getSelectedItem().toString());
                    ima.mensaje_informacion("ACTUALIZACION INDIVIDUAL TERMINADA","INFORMACION", "exito", "png");
                }
                   // rn.pase_de_notas(m.getN_todo().getText(), null, null, null);
                m.getN_cedula().setText("");

        }


if(e.getActionCommand().equalsIgnoreCase("CARGAR ACTAS")){

    new procesos().setVisible(true);    


}        
        

        
        
        
        
if(e.getActionCommand().equalsIgnoreCase("ELABORAR")){
    
   Runnable rx = new Runnable() {
        public void run() {
                       rm.lista_egresados( m.getEgre_txt().isSelected(), m.getEgre_record().isSelected(), m.getEgre_merito().isSelected(),cbd.getConexion(), m.getCarrera().getSelectedItem().toString(), m.getPeriodo_academico().getText(), false, false, m.getEgre_certificadas().isSelected() ,Integer.valueOf(m.getEgre_lineas().getText()),m.getEgre_periodo_lectivo().getSelectedItem().toString());
                       m.getEgre_lineas().setText("30");    
                           } 
    } ; Thread xx = new Thread(rx, "masivos"); xx.start();

    

}



if(e.getActionCommand().equalsIgnoreCase("REALIZAR")){
    registro_masivo rm = new registro_masivo();
    progreso p = new progreso();
    segundo_plano sp = new segundo_plano(rm, p, 1,m.getCarrera().getSelectedItem().toString());
    sp.execute();


}




//_------PESTAÑA CANCELADOS--------

    if(e.getActionCommand().equalsIgnoreCase("C_INICIAR")){
       
        Runnable rx = new Runnable() {
                public void run() {
                        new registro_masivo().procesando_cancelados(m.getCarrera().getSelectedItem().toString(),
                                                    m.getPeriodo_academico().getText(),
                                                    m.getC_mpr().getSelectedIndex(),
                                                    m.getC_mmr().getSelectedIndex(),
                                                    m.getC_cedula().getText(),
                                                    m.getC_individual().isSelected(),
                                                    m.getC_masivo().isSelected());
                }
            } ;    
        Thread xx = new Thread(rx, "aplicacion");
        xx.start();
        
        
    }



    if(e.getActionCommand().equalsIgnoreCase("GENERAR TXT")){
         
         //ima.mensaje_informacion("ARCHIVO TXT DE LOS EGRESADOS DE TODAS LAS CARRERAS DEL PERIODO "+m.getTxt_periodo().getSelectedItem().toString()+"\nTERMINADA!!!","INFORMACION", "exito", "png");
         
        Runnable rx = new Runnable() {
                public void run() {
                      rm.txt_carrera(new conexion_base_de_datos().getConexion(), ima.guardado_general_directorio("GUARDAR ARCHIVO TXT"), m.getTxt_periodo().getSelectedItem().toString(),"Egresado");
                }
            } ;    
        Thread xx = new Thread(rx, "aplicacion");
        xx.start();
        
         
         
    }


    if(e.getActionCommand().equalsIgnoreCase("CONSTANCIA_CINU")){
        Runnable rx = new Runnable() {
                public void run() {
                    registro_pdf rpdf = new registro_pdf();
                    rpdf.lista_cinu(new conexion_base_de_datos().getConexion(), m.getPeriodo_academico().getText(), m.getCinu_cedula().getText(), m.getCinu_masivo().isSelected());
                }
            } ;    
        Thread xx = new Thread(rx, "constancia_cinu");
        xx.start();
    }


    if(e.getActionCommand().equalsIgnoreCase("est_PROCESAR")){
        Runnable rx = new Runnable() {
                public void run() {
                   registro_masivo rm = new registro_masivo();
                    rm.estudiantes_materias(m.getCarrera().getSelectedItem().toString());
                }
            } ;    
        Thread xx = new Thread(rx, "ANALISIS CARRERA");
        xx.start();
    }




    }//FIN ACTION EVENT

    
    
    
    

    public void stateChanged(ChangeEvent e) {
            //System.out.println("cambio estado "+e.getSource());
           // System.out.println("COINCIDENCIAS "+e.getSource().toString().contains("pdf_inscripcion"));

       if(m.getPdf_individual().isSelected()){
           System.out.println("HABILITANDO");
            m.getPdf_cedula().setText("");
            m.getPdf_cedula().setEnabled(true);
       }else{
            System.out.println("DESHABILITANDO");
            m.getPdf_cedula().setText("");
            m.getPdf_cedula().setEnabled(false);
       }


       if(m.getPdf_inscripcion().isSelected()){
            m.getPdf_estudio().setSelected(false);
            m.getPdf_estudio().setEnabled(false);
            m.getPdf_record().setSelected(false);
            m.getPdf_record().setEnabled(false);
            m.getPdf_certificadas().setSelected(false);
            m.getPdf_certificadas().setEnabled(false);
            m.getPdf_culminacion().setSelected(false);
            m.getPdf_culminacion().setEnabled(false);
       }else{
            m.getPdf_estudio().setEnabled(true);
            m.getPdf_record().setEnabled(true);
            m.getPdf_culminacion().setEnabled(true);
            m.getPdf_certificadas().setEnabled(true);
       }
    }

    public void run() {
        
    }

    public void windowOpened(WindowEvent e) {
       rm.inicializar_periodos(m.getEgre_periodo_lectivo());
       rm.inicializar_periodos(m.getTxt_periodo());
       rm.inicializar_periodos(m.getN_periodo());
       
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









}//fin de la clase
