/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import modelo.conexion_base_de_datos;
import modelo.imagenes;
import modelo.materias;
import modelo.modelo_notas_alumnos;
import modelo.registro_ingenieria;
import modelo.registro_ofertas;
import vista.plan_b;

/**
 *
 * @author AN
 */
public class control_plan_b implements ActionListener, ItemListener, WindowListener{


 private plan_b pb;
 private registro_ofertas ro;
 private conexion_base_de_datos cbd;
 private materias materia;
 private registro_ingenieria reging;
 private modelo_notas_alumnos mna;
 private imagenes ima;

    public control_plan_b(plan_b pb) {
        this.pb = pb;
        ro = new registro_ofertas();
        cbd = new conexion_base_de_datos();
        materia = new materias();
       reging = new registro_ingenieria();
       mna = new modelo_notas_alumnos();
       ima = new imagenes();

    }









    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

            ro.verificar_alumno(cbd.getConexion(),pb.getCedula().getText());
            reging.verificar_alumno(cbd.getConexion(),pb.getCedula().getText());//este metodo hace lo mismo que el anterior pero lo empleo ya que otro metodo necesita una variable de aqui (materia.notas_especialidad). necesita la variable "turnos", esto se puede mejorar

            pb.getAlumno().setText(ro.getEstudiante());
            pb.getTurno().setText(ro.getTurnos());

                if(ro.getControl()==0){//si encuentra el alumno se proceden a hacer los siguientes analisis
                        pb.getCarrera().setSelectedItem(ro.getCarrera());//colocando la carrera del alumno
                        pb.getCarrera_inf().setText("Referencia: "+ro.getCarrera());//colocando la carrera como informacion para siempre tener la referencia

                          pb.getCondicion().setEnabled(true);
                          pb.getCarrera().setEnabled(true);
                          pb.getAsignaturas().setEnabled(true);
                          pb.getSemestre().setEnabled(true);
                          pb.getAtras().setEnabled(true);
                          pb.getSiguiente().setEnabled(true);
                          pb.getDias().setEnabled(true);
                          pb.getDesde().setEnabled(true);
                          pb.getHasta().setEnabled(true);
                          pb.getGuardar().setEnabled(true);
                       //   pb.getCapacidad().setEnabled(true);
                          pb.getDocente().setEnabled(true);



                          for(int v=0; v<=2; v++){//revisando la base de datos 3 veces para hallar en que vigencia se encuentra el alumno
                              System.out.println("viendo "+v);
                              materia.setSeleccion_pensum(v);
                              reging.setReconocimiento(v);
                             // reging.buscar_materias(cbd.getConexion(), pb.getCedula().getText(),materia.notas_especialidad(pb.getCarrera().getSelectedItem().toString(),reging));//cargando las materias del alumno
                              reging.buscar_materias_alternativo(cbd.getConexion(), pb.getCedula().getText(),materia.notas_especialidad(reging));//cargando las materias del alumno
                             // reging.buscar_materias(cbd.getConexion(), pb.getCedula().getText(),materia.notas_especialidad(pb,reging));//cargando las materias del alumno

                             // if(reging.getPeriodo().isEmpty()){}else{break;}
                              if(reging.getRecord().isEmpty()){}else{break;}

                          }//fin revisando la base de datos 2 veces

                              System.out.println("para materia: "+reging.getReconocimiento()+" v: "+materia.getSeleccion_pensum());




                            //if(reging.getPeriodo().isEmpty()){
                            if(reging.getRecord().isEmpty()){
                                        ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "pregunta", "png");

                                        materia.setNuevo_ingreso(1);
                                     //   materia.materias_pensum(cbd.getConexion(),materia.carrera(ro.getCarrera(),ro.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),ro.getCarrera(),ro.getTurnos());//cargando las materias del pensum
                            }
                            else{
                                       // materia.materias_pensum(cbd.getConexion(),materia.carrera(ro.getCarrera(),ro.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),ro.getCarrera(),ro.getTurnos());//cargando las materias del pensum

                            }





        

                }





        }//fin buscar




    if(e.getActionCommand().equalsIgnoreCase("atras")){


               pb.getSalones().setText(materia.secciones_dinamicas(
                                        pb.getSemestre(), pb.getSalones().getText(),"resta"));

               ro.capacidad_salones(cbd.getConexion(), pb.getCarrera().getSelectedItem().toString(), pb.getAsignaturas().getSelectedItem().toString(), pb.getSalones().getText());//analizando la capcidad por materia y seccion
               pb.getCapacidad().setText(ro.getInscritos()+" / "+ro.getCupos());//colocando el numero inscritos y la capacidad
               
               if(pb.getAsignaturas().getBackground()!=new Color(237, 235, 193)){ pb.getAsignaturas().setBackground(new Color(237, 235, 193)); }//si esta de otro color vuelvelo a su color original
               if(ro.getInscritos()>=ro.getCupos() && ro.getInscritos()!=0){ pb.getAsignaturas().setBackground(Color.RED);}//se colorea el campo de asignatura cuando se alcanza el maximo de cupos de materia

     }//fin atras


    if(e.getActionCommand().equalsIgnoreCase("siguiente")){
         pb.getSalones().setText(materia.secciones_dinamicas(
                                pb.getSemestre(), pb.getSalones().getText(),"suma"));

               ro.capacidad_salones(cbd.getConexion(), pb.getCarrera().getSelectedItem().toString(), pb.getAsignaturas().getSelectedItem().toString(), pb.getSalones().getText());//analizando la capcidad por materia y seccion
               pb.getCapacidad().setText(ro.getInscritos()+" / "+ro.getCupos());//colocando el numero inscritos y la capacidad

               if(pb.getAsignaturas().getBackground()!=new Color(237, 235, 193)){ pb.getAsignaturas().setBackground(new Color(237, 235, 193)); }//si esta de otro color vuelvelo a su color original
               if(ro.getInscritos()>=ro.getCupos() && ro.getInscritos()!=0){ pb.getAsignaturas().setBackground(Color.RED);}//se colorea el campo de asignatura cuando se alcanza el maximo de cupos de materia


     }//fin siguiente


     if(e.getActionCommand().equalsIgnoreCase("GUARDAR")){

              if(ro.confirmacion_inscribir(pb.getAsignaturas().getSelectedItem().toString())==0){//confirmando la oferta de la materia


                  ro.setCodigo("SIN CODIGO");


                   ro.informacion_materia(pb.getAsignaturas().getSelectedItem().toString(), materia.getPensum());


                   //esto es para cambiar los valores a traves del gui
                        if(pb.getCodigo().getText().length()>0){//si metes el codigo manual
                            ro.setCodigo(pb.getCodigo().getText());//sustituye por el manual
                            
                        }

                        if(pb.getUcreditos().getText().length()>0){//si metes las uc manual

                            ro.setUc(Integer.parseInt(pb.getUcreditos().getText()));//sustituye por el manual
                        }


                        





                             ro.inscribir_sin_restriciones(cbd.getConexion(),
                                                 pb.getCedula().getText(),
                                                 pb.getAlumno().getText(),
                                                 pb.getCarrera().getSelectedItem().toString(),
                                                 (pb.getSemestre().getSelectedIndex()+1),
                                                 ro.getCodigo().toUpperCase(),
                                                 pb.getAsignaturas().getSelectedItem().toString(),
                                                 ro.getUc(),
                                                 pb.getDias().getSelectedItem().toString(),
                                                 ro.hora_normal(pb.getDesde().getSelectedItem().toString(), pb.getHasta().getSelectedItem().toString()),
                                                 pb.getSalones().getText(),
                                                 pb.getPeriodo().getText(),
                                                 pb.getDocente().getText(),
                                                 pb.getCondicion().getSelectedItem().toString()
                                                 );

                             mna.monitoreo(new conexion_base_de_datos().getConexion(), pb.getResponsable().getText(),ro.getCodigo(),pb.getAsignaturas().getSelectedItem().toString(),"INSCRIBIENDO MATERIAS POR PLAN B","INSCRIPCION FORZOZA",pb.getCarrera().getSelectedItem().toString(),pb.getCedula().getText());//guardando cualquier modificacion hecha por inscripcion por plan b para auditar

                             /*        String cedula,
                                     String estudiante,
                                     String carrera,
                                     int semestre,
                                     String codigo,
                                     String materia,
                                     int uc,
                                     String dia,
                                     String hora,
                                     String seccion,
                                     String periodo,
                                     String docente,
                                     String condicion
        */
                             /*cedula+"','"+
                                                    estudiante+"','"+
                                                    carrera+"',"+
                                                    semestre+",'"+
                                                    codigo+"','"+
                                                    materia+"',"+
                                                    uc+",'"+
                                                    dia+"','"+
                                                    hora+"','"+
                                                    seccion+"','"+
                                                    periodo+"',NOW(),'"+
                                                    docente+"','"+
                                                    condicion+"');"
                                */



                             System.out.println(pb.getAsignaturas().getSelectedItem().toString()+"     guardado");


                            ima.mensaje_informacion("INFORMACION GUARDADA", "NOTIFICACION", "almacenando", "png");
                            



                            pb.getCodigo().setText("");
                            pb.getUcreditos().setText("");
                            pb.getDocente().setText("");//limpiando el campo donde se coloca el nombre del docente

              }//fin confirmacion

     }//fin boton guardar














    }//fin del action performed

    public void itemStateChanged(ItemEvent e) {

//           System.out.println(e.getItemSelectable().toString().substring(22));



         if(e.getStateChange()==1 ){

                if(e.getItemSelectable().toString().substring(22).startsWith("semestre")){

                        pb.getSalones().setText(materia.secciones_dinamicas(
                        pb.getSemestre(), pb.getSalones().getText(),""));

                        ro.cargar_pensum_alternativo(cbd.getConexion(), ro.especialidad_pensum(pb.getCarrera().getSelectedItem().toString()), (pb.getSemestre().getSelectedIndex()+1));
                        ro.llenado_asignaturas(pb.getAsignaturas());
                        ro.getPen().clear();//limpiando el LinkedList
                        ro.getPen_materias().clear();


                           ro.capacidad_salones(cbd.getConexion(), pb.getCarrera().getSelectedItem().toString(), pb.getAsignaturas().getSelectedItem().toString(), pb.getSalones().getText());//analizando la capcidad por materia y seccion
                           pb.getCapacidad().setText(ro.getInscritos()+" / "+ro.getCupos());//colocando el numero inscritos y la capacidad

                            if(pb.getAsignaturas().getBackground()!=new Color(237, 235, 193)){ pb.getAsignaturas().setBackground(new Color(237, 235, 193));}//si esta de otro color vuelvelo a su color original
                            if(ro.getInscritos()>=ro.getCupos() && ro.getInscritos()!=0){ pb.getAsignaturas().setBackground(Color.RED); }//se colorea el campo de asignatura cuando se alcanza el maximo de cupos de materia


                }//fin semestre

                
                if(e.getItemSelectable().toString().substring(22).startsWith("carrera")){

                        pb.getSalones().setText(materia.seccion(pb.getCarrera().getSelectedItem().toString()).substring(0,3).concat(pb.getSalones().getText().substring(3,6)));
                        System.out.println(ro.especialidad_pensum(pb.getCarrera().getSelectedItem().toString()));

                        ro.cargar_pensum_alternativo(cbd.getConexion(), ro.especialidad_pensum(pb.getCarrera().getSelectedItem().toString()), (pb.getSemestre().getSelectedIndex()+1));
                        ro.llenado_asignaturas(pb.getAsignaturas());
                        ro.getPen().clear();//limpiando el LinkedList
                        ro.getPen_materias().clear();

                        System.out.println("N° elementos: "+pb.getAsignaturas().getItemCount());


                          ro.capacidad_salones(cbd.getConexion(), pb.getCarrera().getSelectedItem().toString(), pb.getAsignaturas().getSelectedItem().toString(), pb.getSalones().getText());//analizando la capcidad por materia y seccion
                          pb.getCapacidad().setText(ro.getInscritos()+" / "+ro.getCupos());//colocando el numero inscritos y la capacidad

                          if(pb.getAsignaturas().getBackground()!=new Color(237, 235, 193)){ pb.getAsignaturas().setBackground(new Color(237, 235, 193));}//si esta de otro color vuelvelo a su color original
                          if(ro.getInscritos()>=ro.getCupos() && ro.getInscritos()!=0){ pb.getAsignaturas().setBackground(Color.RED);}//se colorea el campo de asignatura cuando se alcanza el maximo de cupos de materia




                }//fin carrera


                if(e.getItemSelectable().toString().substring(22).startsWith("asignaturas")){
                    pb.getCodigo().setText("");//limpiando las casillas por materias 
                    pb.getUcreditos().setText("");

                    ro.capacidad_salones(cbd.getConexion(), pb.getCarrera().getSelectedItem().toString(), pb.getAsignaturas().getSelectedItem().toString(), pb.getSalones().getText());//analizando la capcidad por materia y seccion
                    pb.getCapacidad().setText(ro.getInscritos()+" / "+ro.getCupos());//colocando el numero inscritos y la capacidad


                     if(pb.getAsignaturas().getBackground()!=new Color(237, 235, 193)){ pb.getAsignaturas().setBackground(new Color(237, 235, 193));}//si esta de otro color vuelvelo a su color original
                     if(ro.getInscritos()>=ro.getCupos() && ro.getInscritos()!=0){ pb.getAsignaturas().setBackground(Color.RED); }//se colorea el campo de asignatura cuando se alcanza el maximo de cupos de materia



                    System.out.println("MATERIAS: "+pb.getAsignaturas().getSelectedItem().toString());
                        
                   /* if(pb.getAsignaturas().getSelectedItem().toString().equalsIgnoreCase("PROYECTO SERVICIO COMUNITARIO")){pb.getCodigo().setText("PRO00001"); pb.getUcreditos().setText("0");}
                    if(pb.getAsignaturas().getSelectedItem().toString().equalsIgnoreCase("TALLER SERVICIO COMUNITARIO")){pb.getCodigo().setText("TAL00001"); pb.getUcreditos().setText("0");}*/
                    if(pb.getAsignaturas().getSelectedItem().toString().equalsIgnoreCase("PROYECTO DE SERVICIO COMUNITARIO")){pb.getCodigo().setText("PRO01"); pb.getUcreditos().setText("0");}
                    if(pb.getAsignaturas().getSelectedItem().toString().equalsIgnoreCase("TALLER DE INDUCCIÓN DEL SERVICIO COMUNITARIO")){pb.getCodigo().setText("TAI01"); pb.getUcreditos().setText("0");}
                    //electivas
                    if(pb.getAsignaturas().getSelectedItem().toString().equalsIgnoreCase("ADUANA (ELECTIVA)")){pb.getCodigo().setText("ECO31153"); pb.getUcreditos().setText("3");}
                    if(pb.getAsignaturas().getSelectedItem().toString().equalsIgnoreCase("AVALUO (ELECTIVA)")){pb.getCodigo().setText("ECO31123"); pb.getUcreditos().setText("3");}
                    if(pb.getAsignaturas().getSelectedItem().toString().equalsIgnoreCase("CONTABILIDAD DE COOPERATIVAS (ELECTIVA)")){pb.getCodigo().setText("ECO31113"); pb.getUcreditos().setText("3");}

                }




                
        }else{}





    }

    public void windowOpened(WindowEvent e) {


        pb.getSalones().setText(materia.seccion(pb.getCarrera().getSelectedItem().toString()).substring(0,3).concat(pb.getSalones().getText().substring(3,6)));
            System.out.println(ro.especialidad_pensum(pb.getCarrera().getSelectedItem().toString()));

            ro.cargar_pensum_alternativo(cbd.getConexion(), ro.especialidad_pensum(pb.getCarrera().getSelectedItem().toString()), (pb.getSemestre().getSelectedIndex()+1));
            ro.llenado_asignaturas(pb.getAsignaturas());
            ro.getPen().clear();//limpiando el LinkedList
            ro.getPen_materias().clear();

            System.out.println("N° elementos: "+pb.getAsignaturas().getItemCount());

            

          //  System.out.println("color de fondo del elemento: "+pb.getAsignaturas().getBackground());
        
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
