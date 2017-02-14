/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.conexion_base_de_datos;
import modelo.imagenes;
import modelo.parametros;
import modelo.registro_inicio;
import modelo.registros_bd;
import vista.actualizacion;
import vista.inicio;

/**
 *
 * @author AACM
 */
public class control_inicio implements ActionListener, KeyListener{


    private inicio ini;

    private registro_inicio reg_inicio;
    private registros_bd regbd;
    private conexion_base_de_datos conexion;
    private parametros par;

    private imagenes ima;

    public control_inicio(inicio ini) {
        this.ini = ini;
        
        reg_inicio = new registro_inicio();
        regbd = new registros_bd();
        conexion = new conexion_base_de_datos();
        par= new parametros();
        ima = new imagenes();
    }



    
    public void actionPerformed(ActionEvent e) {

if(e.getActionCommand().equalsIgnoreCase("ENTRAR")){

    
    if(regbd.buscar_alumno(conexion.getConexion(), ini.getCedula().getText())==1){//si no se ha registrado entonces registrarse
        new vista.actualizacion().setVisible(true);
        ini.dispose();
      
    }else{
     
     regbd.analisis(conexion.getConexion(), ini.getCedula().getText());//buscando el perfil del esudiante
     //System.out.println("ACTIVO: " + regbd.getActivo() + "EXPEDIENTE: " + regbd.getExpediente());

     if(regbd.getActivo().equalsIgnoreCase("SI") //si se encuentra inactivo//&& regbd.getExpediente().equalsIgnoreCase("SI")//si no debes expediente o no estas inactivo puede proceder a inscribirse
               || regbd.isInscribirse()==true ){//o que no exista en la lista de inactivo o que deba expediente

                //new vista.inscripcion().setVisible(true);

                 

                if(ima.confirmacion("Desea Inscribir este Estudiante", "CONFIRMACION INSCRIPCION", "pregunta", "png")==0){//confirmando si se desea inscribir al estudiante despues de verificar que si existe en el sistema y no tiene ningun problema
                   vista.inscripcion v = new vista.inscripcion();
                   v.setVisible(true);
                   v.getCedula().setText(ini.getCedula().getText());
                   v.getPeriodo().setText(ini.getPeriodo_actual());//pasando variable periodo academico con el que se va a trabajar
                    ini.dispose();
                    //        ini.getCedula().setText("");
                }
                
            }else{

                ima.mensaje_informacion("USTED TIENE PROBLEMAS PARA INSCRIBIRSE, POR FAVOR\n"+
                                      "DIRIJASE AL PERSONAL DE INSCRIPCION\n"+
                                      "   ACTIVO? = "+regbd.getActivo()+"\n", "ADVERTENCIA", "precaucion", "png");
                

            }


                //esto esta asi pora evitar la revision de expediente y si esta activo del estudiante...borre la tabla por accidente
       //         new vista.inscripcion().setVisible(true);
         //       ini.dispose();


    }

    
}//fin del boton ENTRAR



        
    }//fin del metodo ActionPerformed

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {

        if(ini.getCedula().getText().equalsIgnoreCase("")){
        System.out.println("campo cedula vacio");

        }else{

                // System.out.println( regbd.verificador(act.getCedula().getText()) );
                if(regbd.verificador(ini.getCedula().getText())){
                    ima.mensaje_informacion("NO PUEDES COLOCAR SEPARADORES EN LA CEDULA", "ADVERTENCIA: CAMPO CEDULA", "avisos", "png");
                    ini.getCedula().setText(reg_inicio.getCedula());
                }else{reg_inicio.setCedula(ini.getCedula().getText());}
          }


         if( ( e.getComponent().getName().equalsIgnoreCase("cedula") || e.getComponent().getName().equalsIgnoreCase("clave")) && e.getKeyCode()==10){//habilitando el enter en sustitucion del boton entrar



                    if(ini.getClave().isVisible()){//buscar clave de usuario del estudiante
                            System.out.println("Campo Clave Visible");

                                if(regbd.buscar_nuevo_ingreso(new conexion_base_de_datos().getConexion(), ini.getCedula().getText() , ini.getPeriodo_actual()) == 0){//EXPERIMENTAL. IDENTIFICANDO SI ES NUEVO INGRESO
                                    //es nuevo ingreso
                                    System.out.println("ESTUDIANTE NUEVO INGRESO");
                                      actualizacion act = new actualizacion();
                                       act.setVisible(true);
                                       act.setPrivi_actua(ini.getPrivilegio());//pasando los privilegios entre vista para no perder referencia
                                       act.getModificar().setVisible(true);
                                       act.getEnviar().setVisible(false);

                                       act.getCedula().setText(ini.getCedula().getText());//autocolocando la cedula en la actualizacion
                                       act.getCedula().setEditable(false);//deshabilitando el campo cedula para que no puedan cambiarla
                                       act.getCarrera().setSelectedItem(regbd.getCarrera());//autocolocando la carrera
                                       act.getCarrera().setEnabled(false);//deshabilitando el campo carrera para que no puedan cambiarla
                                       if(regbd.getTurno().equalsIgnoreCase("DIURNO")){ act.getDiurno().setSelected(true);act.getNocturno().setSelected(false);} else {act.getNocturno().setSelected(true);act.getDiurno().setSelected(false);}//autocolocando el turno.
                                       act.getDiurno().setEnabled(false);//deshabilitando el turno para que no lo cambien
                                       act.getNocturno().setEnabled(false);//deshabilitando el turno para que no lo cambien
                                       act.setPeriodo_actual(ini.getPeriodo_actual());//colocando el periodo actual
                                       act.getFi_mes().setEnabled(false); act.getFi_año().setEnabled(false); act.getUpi_periodo().setEnabled(false);act.getUpi_año().setEnabled(false);//inactivando ultimo periodo inscrito y periodo de entrada a la universidad
                                       act.getTipo_estudiante().setEnabled(false); act.getCond_estudiante().setEnabled(false);//inactivando tipo de estudiante y condicion de estudiante
                                       act.getUpi_año().setSelectedItem("2012");//fijando el año que no queria fijarse solo por la vista. revisar luego. no es tajante. esto deberia ser dinamico por cada año de epocas de nuevos ingresos a la institucion


                                       System.out.println("REVISIONES inicio: "+ini.getPrivilegio()+" - actualizacion: "+act.getPrivi_actua());
                                       ini.dispose();
                                }else{


                                    if(regbd.buscar_alumno(conexion.getConexion(), ini.getCedula().getText())==1){//si no se ha registrado entonces registrarse
                                       //new vista.actualizacion().setVisible(true);
                                       actualizacion act = new actualizacion();
                                       act.setVisible(true);
                                       act.setPrivi_actua(ini.getPrivilegio());//pasando los privilegios entre vista para no perder referencia
                                       act.setPeriodo_actual(ini.getPeriodo_actual());//colocando el periodo actual
                                       System.out.println("REVISIONES inicio: "+ini.getPrivilegio()+" - actualizacion: "+act.getPrivi_actua());
                                       ini.dispose();

                                    }else{//si ya esta registrado

                                            regbd.analisis(conexion.getConexion(), ini.getCedula().getText());//buscando el perfil del esudiante
                                             //System.out.println("ACTIVO: " + regbd.getActivo() + "EXPEDIENTE: " + regbd.getExpediente());

                                             if( (regbd.getActivo().equalsIgnoreCase("SI") //si se encuentra inactivo//&& regbd.getExpediente().equalsIgnoreCase("SI")//si no debes expediente o no estas inactivo puede proceder a inscribirse
                                                  || regbd.isInscribirse()==true) && //o que no exista en la lista de inactivo o que deba expediente
                                                regbd.clave_estudiante(new conexion_base_de_datos().getConexion(), ini.getCedula().getText(), ini.getClave().getPassword())
                                                ){//y que la clave este correcta

                                                        //new vista.inscripcion().setVisible(true);
                                                        if(ima.confirmacion("Desea Inscribirse Estudiante?", "CONFIRMACION INSCRIPCION", "pregunta", "png")==0){//confirmando si se desea inscribir al estudiante despues de verificar que si existe en el sistema y no tiene ningun problema
                                                               vista.inscripcion v = new vista.inscripcion();
                                                               v.setVisible(true);
                                                               v.getCedula().setText(ini.getCedula().getText());
                                                               v.getPeriodo().setText(ini.getPeriodo_actual());//pasando variable periodo academico con el que se va a trabajar
                                                               v.setPrivilegio(ini.getPrivilegio());
                                                               v.getCedula().setText(ini.getCedula().getText());//enviando la cedula
                                                               v.getCedula().setEnabled(false);
                                                                ini.dispose();
                                                                //        ini.getCedula().setText("");
                                                         }

                                                    }else{

                                                        ima.mensaje_informacion("USTED TIENE PROBLEMAS PARA INSCRIBIRSE, POR FAVOR\n"+
                                                                              "DIRIJASE AL PERSONAL DE INSCRIPCION\n"+
                                                                              "   ACTIVO? = "+regbd.getActivo()+"\n o YA ESTA INSCRITO", "ADVERTENCIA", "precaucion", "png");


                                                    }


                                                //esto esta asi pora evitar la revision de expediente y si esta activo del estudiante...borre la tabla por accidente
                                       //         new vista.inscripcion().setVisible(true);
                                         //       ini.dispose();
                                    }

                            }//EXPERIMENTAL. ES SOLO PARA DETECTAR LOS NUEVOS INGRESOS



                       }else{//proceso normal de trabajo

                                   if(regbd.buscar_alumno(conexion.getConexion(), ini.getCedula().getText())==1){//si no se ha registrado entonces registrarse
                                       actualizacion act = new actualizacion();
                                       act.setVisible(true);
                                       act.setPeriodo_actual(ini.getPeriodo_actual());//colocando el periodo actual
                                       ini.dispose();

                                 }else{//si ya esta registrado

                                     regbd.analisis(conexion.getConexion(), ini.getCedula().getText());//buscando el perfil del esudiante
                                     //System.out.println("ACTIVO: " + regbd.getActivo() + "EXPEDIENTE: " + regbd.getExpediente());

                                     if(regbd.getActivo().equalsIgnoreCase("SI") //si se encuentra inactivo//&& regbd.getExpediente().equalsIgnoreCase("SI")//si no debes expediente o no estas inactivo puede proceder a inscribirse
                                               || regbd.isInscribirse()==true ){//o que no exista en la lista de inactivo o que deba expediente


                                                //new vista.inscripcion().setVisible(true);
                                                if(ima.confirmacion("Desea Inscribir este Estudiante", "CONFIRMACION INSCRIPCION", "pregunta", "png")==0){//confirmando si se desea inscribir al estudiante despues de verificar que si existe en el sistema y no tiene ningun problema
                                                       vista.inscripcion v = new vista.inscripcion();
                                                       v.setVisible(true);
                                                       v.getCedula().setText(ini.getCedula().getText());
                                                       v.getPeriodo().setText(ini.getPeriodo_actual());//pasando variable periodo academico con el que se va a trabajar
                                                       v.setPrivilegio(ini.getPrivilegio());
                                                        ini.dispose();
                                                        //        ini.getCedula().setText("");
                                                 }

                                            }else{

                                                ima.mensaje_informacion("ESTE ESTUDIANTE TIENE PROBLEMAS PARA PODER SER INSCRITO\n"+
                                                                      "SU SITUACION DEBE SER CORREGIDA...!!!\n"+
                                                                      "   ACTIVO? = "+regbd.getActivo()+"\n"
                                                                      +"LA SIGUIENTE VENTANA SOLO SERA DE REFERENCIA. INSCRIPCION DESHABILITADA..!!!"
                                                                      , "ADVERTENCIA", "precaucion", "png");

                                                //esto se hace para que por lo menos pueda ver el registro del estudiante ya que el personal es de la universidad
                                                //pero se deshabilitara el boton de inscribir de la ventana para evitar cualquier inscripcion. Se debe corregir el error para entrar normalmente
                                                //Solo se mostrara como vizualizdor nada mas de cualquier estudiante.
                                                       vista.inscripcion v = new vista.inscripcion();
                                                       v.setVisible(true);
                                                       v.getCedula().setText(ini.getCedula().getText());
                                                       v.getPeriodo().setText(ini.getPeriodo_actual());//pasando variable periodo academico con el que se va a trabajar
                                                       v.setPrivilegio(ini.getPrivilegio());
                                                       v.getInscribir().setVisible(false);//deshabilitando el boton de inscribir.
                                                       ini.dispose();


                                            }
                                                //esto esta asi pora evitar la revision de expediente y si esta activo del estudiante...borre la tabla por accidente
                                       //         new vista.inscripcion().setVisible(true);
                                         //       ini.dispose();
                                    }




                       }//fin proceso normal de trabajo




                         
                }//fin habilitacion enter en el campo cedula



    }






}
