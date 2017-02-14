/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//DESARROLLADO POR EL ING CUSATTI ANDY
package controlador;


import vista.actualizacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import modelo.conexion_base_de_datos;
import modelo.imagenes;
import modelo.parametros;
import modelo.registro_actualizacion;
import modelo.registro_masivo;
import modelo.registros_bd;
import vista.inicio;


/**
 *
 * @author AACM
 */
public class control_actualizacion implements ActionListener, ChangeListener, ItemListener, KeyListener {

    private actualizacion act;
   
   private registro_actualizacion rac;
   private registros_bd regbd;
   private conexion_base_de_datos conexion;
   private parametros par;
   private imagenes ima;
   private registro_masivo rm;
   


    public control_actualizacion(actualizacion act) {
        this.act = act;
       
        rac = new registro_actualizacion();
        regbd= new registros_bd();
        conexion = new conexion_base_de_datos();
        par= new parametros();
        ima = new imagenes();
        rm = new registro_masivo();
    }

//DESARROLLADO POR EL ING. CUSATTI ANDY

    public void actionPerformed(ActionEvent e) {
//System.out.println(e.getSource().toString().subSequence(20,27));
//System.out.println(e.getSource());


if(e.getActionCommand().equalsIgnoreCase("ENVIAR") ){

        if(act.getCedula().getText().equalsIgnoreCase("")){

        ima.mensaje_informacion("USUARIO EL CAMPO DE LA 'CEDULA'\n"+ "EN DATOS PERSONALES DEBE ESTAR LLENA",  "ADVERTENCIA: CAMPO CEDULA", "precaucion","null");
        
        act.getPaneles().setSelectedIndex(0);
       
       
    }else{

            
                    String opcion=null;
                    String opcion2=null;
                    rac.inicializar();


                    if(act.getDiurno().isSelected()){opcion=act.getDiurno().getText();}else{opcion=act.getNocturno().getText();}

                    if(act.getCivil().isSelected()){opcion2=act.getCivil().getText();}else{opcion2=act.getMilitar().getText();}


           
                   registros_bd rbd1 = new registros_bd(String.valueOf(act.getId().getSelectedItem()), act.getCedula().getText(), act.getNombres().getText(), act.getApellidos().getText(), String.valueOf(act.getFn_dia().getSelectedItem()), String.valueOf(act.getFn_mes().getSelectedIndex()+1), String.valueOf(act.getFn_año().getSelectedItem()), String.valueOf(act.getSexo().getSelectedItem()), String.valueOf(act.getEdo_civil().getSelectedItem()), String.valueOf(act.getNacionalidad().getSelectedItem()), String.valueOf(act.getPais_nac().getSelectedItem()), String.valueOf(act.getEtnia().getSelectedItem()), act.getTelf_per().getText(), act.getTelf_per_otro().getText(), act.getTelf_hab().getText(), act.getTelf_hab_otro().getText(), act.getCorreo().getText(), String.valueOf(act.getDiscapacidad().getSelectedItem()), act.getDireccion().getText(),String.valueOf(act.getResidencia().getSelectedItem()));
                   regbd.ingreso_personal(conexion.getConexion(), rbd1);

                   registros_bd rbd2 = new registros_bd(act.getCedula().getText(), String.valueOf(act.getDs_parentesco().getSelectedItem()), String.valueOf(act.getDs_a().getSelectedItem()), String.valueOf(act.getDs_b().getSelectedItem()), String.valueOf(act.getDs_c().getSelectedItem()), String.valueOf(act.getDs_d().getSelectedItem()), String.valueOf(act.getDs_personas().getSelectedItem()), String.valueOf(act.getDs_tiempo().getSelectedItem()), String.valueOf(act.getDs_penal().getSelectedItem()));
                   regbd.ingreso_socioeconomico(conexion.getConexion(), rbd2,rac.getB1()[Integer.parseInt((String) act.getDs_a().getSelectedItem().toString().subSequence(2,3))-1].substring(5),rac.getB2()[Integer.parseInt((String) act.getDs_b().getSelectedItem().toString().subSequence(2,3))-1].substring(5),rac.getB3()[Integer.parseInt((String) act.getDs_c().getSelectedItem().toString().subSequence(2,3))-1].substring(5),rac.getB4()[Integer.parseInt((String) act.getDs_d().getSelectedItem().toString().subSequence(2,3))-1].substring(5));

                   registros_bd rbd3 = new registros_bd(act.getCedula().getText(),opcion2,act.getGrado().getText(), String.valueOf(act.getCarrera().getSelectedItem()),opcion, String.valueOf(act.getBecado().getSelectedItem()), act.getAtleta().getText(), String.valueOf(act.getTipo_estudiante().getSelectedItem()), String.valueOf(act.getFi_mes().getSelectedIndex()+1), String.valueOf(act.getFi_año().getSelectedItem()), String.valueOf(act.getUpi_periodo().getSelectedItem()), String.valueOf(act.getUpi_año().getSelectedItem()),String.valueOf(act.getCond_estudiante().getSelectedItem()),String.valueOf(act.getModalidad().getSelectedItem()),act.getNucleo().getSelectedItem().toString(),act.getN_rusnieus().getText());
                   regbd.ingreso_academico(conexion.getConexion(), rbd3);
                   
                   rm.vigencia_actualizados(conexion.getConexion(), act.getNucleo().getSelectedItem().toString(),0,act.getCedula().getText()); //actualizando la vigencia del estudiante una vez actualizado
                                                     //(String cedula, String grado, String carrera,String turno, String becado, String atleta, String tipo_estudiante, String fi_dia, String fi_mes, String fi_año, String upi_periodo, String upi_año, String cond_estudiante, String modalidad)

          //DESARROLLADO POR EL ING CUSATTI ANDY


          regbd.aviso(act.getNombres().getText().toUpperCase()+", "+act.getApellidos().getText().toUpperCase());
          act.getPaneles().setSelectedIndex(0);
          act.limpiar();

          
         // new vista.inicio().setVisible(true);
          inicio ini = new inicio();//es para poder recordar el rpivilegio que solo se usa para que los estudiantes puedan inscribirse autonomamente
          ini.setVisible(true);
          ini.setPrivilegio(act.getPrivi_actua());//pasando privilegio para no perder referencias entre las vistas
          ini.setPeriodo_actual(act.getPeriodo_actual());//devolviendo el periodo actual de inscripcion
          System.out.println("revisando_automatico: inicio: "+ini.getPrivilegio()+" - actualizacion: "+act.getPrivi_actua());
          if(act.getPrivi_actua().equalsIgnoreCase("123 - ESTUDIANTE")) {ini.getClave().setVisible(true); ini.getClave_etiqueta().setVisible(true);}//habilitando la opcion de las claves porque el usuario es un alumno
          act.dispose();
    }

  
}// fin del boton ENVIAR


    if(e.getActionCommand().equalsIgnoreCase("MODIFICAR")){

                 if(act.getCedula().getText().equalsIgnoreCase("")){

                    ima.mensaje_informacion("USUARIO EL CAMPO DE LA 'CEDULA'\n"+ "EN DATOS PERSONALES DEBE ESTAR LLENA",  "ADVERTENCIA: CAMPO CEDULA", "precaucion","png");
                    act.getPaneles().setSelectedIndex(0);


                }else{


                                String opcion=null;
                                String opcion2=null;
                                rac.inicializar();


                                if(act.getDiurno().isSelected()){opcion=act.getDiurno().getText();}else{opcion=act.getNocturno().getText();}

                                if(act.getCivil().isSelected()){opcion2=act.getCivil().getText();}else{opcion2=act.getMilitar().getText();}



                               registros_bd rbd1 = new registros_bd(String.valueOf(act.getId().getSelectedItem()), act.getCedula().getText(), act.getNombres().getText(), act.getApellidos().getText(), String.valueOf(act.getFn_dia().getSelectedItem()), String.valueOf(act.getFn_mes().getSelectedIndex()+1), String.valueOf(act.getFn_año().getSelectedItem()), String.valueOf(act.getSexo().getSelectedItem()), String.valueOf(act.getEdo_civil().getSelectedItem()), String.valueOf(act.getNacionalidad().getSelectedItem()), String.valueOf(act.getPais_nac().getSelectedItem()), String.valueOf(act.getEtnia().getSelectedItem()), act.getTelf_per().getText(), act.getTelf_per_otro().getText(), act.getTelf_hab().getText(), act.getTelf_hab_otro().getText(), act.getCorreo().getText(), String.valueOf(act.getDiscapacidad().getSelectedItem()), act.getDireccion().getText(),String.valueOf(act.getResidencia().getSelectedItem()));
                               regbd.actualizacion_datos_personales(conexion.getConexion(), rbd1);

                               registros_bd rbd2 = new registros_bd(act.getCedula().getText(), String.valueOf(act.getDs_parentesco().getSelectedItem()), String.valueOf(act.getDs_a().getSelectedItem()), String.valueOf(act.getDs_b().getSelectedItem()), String.valueOf(act.getDs_c().getSelectedItem()), String.valueOf(act.getDs_d().getSelectedItem()), String.valueOf(act.getDs_personas().getSelectedItem()), String.valueOf(act.getDs_tiempo().getSelectedItem()), String.valueOf(act.getDs_penal().getSelectedItem()));
                               regbd.actualizacion_datos_socioeconomicos(conexion.getConexion(), rbd2,rac.getB1()[Integer.parseInt((String) act.getDs_a().getSelectedItem().toString().subSequence(2,3))-1].substring(5),rac.getB2()[Integer.parseInt((String) act.getDs_b().getSelectedItem().toString().subSequence(2,3))-1].substring(5),rac.getB3()[Integer.parseInt((String) act.getDs_c().getSelectedItem().toString().subSequence(2,3))-1].substring(5),rac.getB4()[Integer.parseInt((String) act.getDs_d().getSelectedItem().toString().subSequence(2,3))-1].substring(5));

                               registros_bd rbd3 = new registros_bd(act.getCedula().getText(),opcion2,act.getGrado().getText(), String.valueOf(act.getCarrera().getSelectedItem()),opcion, String.valueOf(act.getBecado().getSelectedItem()), act.getAtleta().getText(), String.valueOf(act.getTipo_estudiante().getSelectedItem()), String.valueOf(act.getFi_mes().getSelectedIndex()+1), String.valueOf(act.getFi_año().getSelectedItem()), String.valueOf(act.getUpi_periodo().getSelectedItem()), String.valueOf(act.getUpi_año().getSelectedItem()),String.valueOf(act.getCond_estudiante().getSelectedItem()),String.valueOf(act.getModalidad().getSelectedItem()),act.getNucleo().getSelectedItem().toString(),act.getN_rusnieus().getText());
                               regbd.actualizacion_datos_academicos(conexion.getConexion(), rbd3);
                                                                 //(String cedula, String grado, String carrera,String turno, String becado, String atleta, String tipo_estudiante, String fi_dia, String fi_mes, String fi_año, String upi_periodo, String upi_año, String cond_estudiante, String modalidad)

                      //DESARROLLADO POR EL ING CUSATTI ANDY


                      regbd.aviso(act.getNombres().getText().toUpperCase()+", "+act.getApellidos().getText().toUpperCase());
                      act.getPaneles().setSelectedIndex(0);
                      act.limpiar();


                     // new vista.inicio().setVisible(true);
                      inicio ini = new inicio();//es para poder recordar el rpivilegio que solo se usa para que los estudiantes puedan inscribirse autonomamente
                      ini.setVisible(true);
                      ini.setPrivilegio(act.getPrivi_actua());//pasando privilegio para no perder referencias entre las vistas
                      ini.setPeriodo_actual(act.getPeriodo_actual());//devolviendo el periodo actual de inscripcion
                      System.out.println("revisando_automatico: inicio: "+ini.getPrivilegio()+" - actualizacion: "+act.getPrivi_actua());
                      if(act.getPrivi_actua().equalsIgnoreCase("123 - ESTUDIANTE")) {ini.getClave().setVisible(true); ini.getClave_etiqueta().setVisible(true);}//habilitando la opcion de las claves porque el usuario es un alumno
                      act.dispose();
                }


    }//fin boton modificar






if(e.getActionCommand().equalsIgnoreCase("BD")){

regbd.crear_bd(conexion.getConexion());
 System.out.println("LISTO");
    act.getBorrar().setVisible(false);
    act.getBd().setVisible(false);

}

if(e.getActionCommand().equalsIgnoreCase("BORRAR")){
    regbd.eliminar_registro(conexion.getConexion(), act.getCedula().getText());
    act.getCedula().setText("");
    act.getBorrar().setVisible(false);
    act.getBd().setVisible(false);
   

}


//DESARROLLADO POR EL ING CUSATTI ANDY


     if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

         act.getFoto().setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\All Users\\Escritorio\\FOTOS_ACTUALIZACION\\"+act.getCedula().getText()+".jpg"));
        System.out.println(act.getCedula().getText());


     }//fin buscar

if(e.getSource().toString().subSequence(20,27).toString().equalsIgnoreCase("elegir1")){
    rac.inicializar();
    rac.muestra(rac.getB1()[0]+rac.getB1()[1]+rac.getB1()[2]+rac.getB1()[3]+rac.getB1()[4],rac.getB1()[5]);
    act.getDs_a().setEnabled(true);
}

if(e.getSource().toString().subSequence(20,27).toString().equalsIgnoreCase("elegir2")){
    rac.inicializar();
    rac.muestra(rac.getB2()[0]+rac.getB2()[1]+rac.getB2()[2]+rac.getB2()[3]+rac.getB2()[4],rac.getB2()[5]);
    act.getDs_b().setEnabled(true);
}

if(e.getSource().toString().subSequence(20,27).toString().equalsIgnoreCase("elegir3")){
    rac.inicializar();
    rac.muestra(rac.getB3()[0]+rac.getB3()[1]+rac.getB3()[2]+rac.getB3()[3]+rac.getB3()[4],rac.getB3()[5]);
    act.getDs_c().setEnabled(true);
}

if(e.getSource().toString().subSequence(20,27).toString().equalsIgnoreCase("elegir4")){
    rac.inicializar();
    rac.muestra(rac.getB4()[0]+rac.getB4()[1]+rac.getB4()[2]+rac.getB4()[3]+rac.getB4()[4],rac.getB4()[5]);
    act.getDs_d().setEnabled(true);
}




//DESARROLLADO POR EL ING CUSATTI ANDY



    }//fin del actionperformed

    public void stateChanged(ChangeEvent e) {
      if(act.getSi().isSelected()){act.getAtleta().setEnabled(true);act.getAtleta().setText("");}else {act.getAtleta().setEnabled(false);act.getAtleta().setText("NO");}
 
    

      if(act.getMilitar().isSelected()){act.getGrado().setEnabled(true);act.getGrado().setText("");}else {act.getGrado().setEnabled(false);act.getGrado().setText("CIVIL");}




      }

  

    public void itemStateChanged(ItemEvent e) {

        System.out.println(e.getStateChange());
        if(e.getStateChange()==1 && act.getId().getSelectedItem().toString().equalsIgnoreCase("Pasaporte")){

            act.getNacionalidad().setSelectedItem("EXTRANJERA");

            act.getPais_nac().setSelectedItem("Afganistan");
            act.getPais_nac().setEnabled(true);
        }else{act.getNacionalidad().setSelectedItem("VENEZOLANA"); act.getPais_nac().setSelectedItem("Venezuela");act.getPais_nac().setEnabled(false);}



    }

    public void keyTyped(KeyEvent e) {

  

    }

    public void keyPressed(KeyEvent e) {
       //System.out.println(e.getKeyCode());
        if(e.getKeyCode()==118){act.getBd().setVisible(true);act.getBorrar().setVisible(true);}else{act.getBd().setVisible(false);act.getBorrar().setVisible(false);}
        


        
      //DESARROLLADO POR EL ING CUSATTI ANDY


    //    jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
    }

    public void keyReleased(KeyEvent e) {


        if(act.getCedula().getText().equalsIgnoreCase("")){
        System.out.println("campo cedula vacio");

        }else{

            // System.out.println( regbd.verificador(act.getCedula().getText()) );
        if(regbd.verificador(act.getCedula().getText())){
                ima.mensaje_informacion("NO PUEDES COLOCAR SEPARADORES EN LA CEDULA",  "ADVERTENCIA: CAMPO CEDULA", "precaucion", "png");
                
                act.getCedula().setText(rac.getGuardar());


            }else{rac.setGuardar(act.getCedula().getText());}

            }



     

    }

    



}
