/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.conexion_base_de_datos;
import modelo.configuracion_tablas;
import modelo.editor_pensum;
import modelo.materias;
import modelo.modelo_notas_alumnos;
import modelo.registro_masivo;
import vista.notas_alumnos;
import vista.notas_historico;

/**
 *
 * @author AN
 */
public class control_notas_alumnos implements ActionListener, MouseListener, ItemListener{
    private notas_alumnos na;
    private modelo_notas_alumnos mna;
    private conexion_base_de_datos cbd;
    private materias materia;
    private notas_historico nh;
    private editor_pensum ep;
    private configuracion_tablas ctablas;
    private registro_masivo rm;

    public control_notas_alumnos(notas_alumnos na) {
        this.na = na;
        mna = new modelo_notas_alumnos();
        cbd = new conexion_base_de_datos();
        materia = new materias();
        ep = new editor_pensum();
        ctablas = new configuracion_tablas();
        rm = new registro_masivo();

    }

      public control_notas_alumnos(notas_historico nh) {
        this.nh = nh;
        mna = new modelo_notas_alumnos();
        cbd = new conexion_base_de_datos();
        materia = new materias();
        ep = new editor_pensum();
        ctablas= new configuracion_tablas();
        rm = new registro_masivo();
    }




    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("BUSCAR")){

            

            mna.busqueda_materias(cbd.getConexion(), na.getCedula().getText(), na.getNotas(),na.getObservaciones(),na.getUbicacion());//actualizando la vista con la informacion hallada
            mna.datos_extras(cbd.getConexion(),na.getCedula().getText());//cargando turno y carrera del estudiante OPSU
            na.getTurno().setText(mna.getTurno());
            na.getCarrera().setText(mna.getCarrera());
            na.getEstudiante().setText(mna.getEstudiante());

           

                   if(mna.getBase_datos().equalsIgnoreCase("NO HALLADO")){}else{ //solo se carga el pensum cuando exista registro del estudiante
                    mna.cual_pensum(mna.getBase_datos(), na.getCarrera().getText(), na.getTurno().getText(),materia);//setea las condiciones para que se puega cargar el pensum correspondiente. 2007, 2009, 2010 a materias.
                    materia.materias_pensum(cbd.getConexion(),na.getCarrera().getText(),na.getTurno().getText());//cargando materias del pensum
                   
                    mna.manipulacion_pensum(na.getSemestre(),na.getAsignaturas(),na.getCodigo(), materia.getPensum());//colocando las materias segun la carrera y el semestre
                    mna.materias_faltantes(materia.getPensum(), na.getAnalisis());
                    ep.lista_tabla_bd(cbd.getConexion(),na.getVigencias());                    
                    na.getVigencias().setModel(mna.listado_carreras(na.getVigencias()));

                        ctablas.configuracion(na.getNotas(),null,"POSICION", 5, "derecha");
                        ctablas.configuracion(na.getNotas(),null,"SEMESTRE", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"CODIGO", 5, "izquierda");
                        ctablas.configuracion(na.getNotas(),null,"CONDICION", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"DEFINITIVA", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"REPARACION", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"DEFREPARACION", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"INASISTENCIA", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"SECCION", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"PERIODO", 5, "centrado");
                        ctablas.configuracion(na.getNotas(),null,"ESPECIALIDAD", 5, "centrado");



                    }

             

        }


        if(e.getActionCommand().equalsIgnoreCase("MODIFICAR")){

            
                if(mna.confirmacion("MODIFICACION")==0){//si se acepta realizar las modificaciones
                //  System.out.println("modificacion");
                    mna.tramites(na.getNotas(),na.getResponsable().getText(),materia.getPensum(),na.getCarrera().getText(),na.getCedula().getText());//realizando la modificacion

                    mna.busqueda_materias(cbd.getConexion(), na.getCedula().getText(), na.getNotas(),na.getObservaciones(),na.getUbicacion());//actualizando la vista con la modificacion realizada
                    na.getTurno().setText(mna.getTurno());
                
                }else{}

            
        }



        if(e.getActionCommand().equalsIgnoreCase("ELIMINAR")){

                if(mna.confirmacion("ELIMINACION")==0){//si se acepta realizar eliminacion

                   mna.eliminando_materia( na.getNotas(),na.getResponsable().getText(),na.getCarrera().getText(),materia.getPensum(),na.getCedula().getText());//eliminando la materia seleccionada

                    mna.busqueda_materias(cbd.getConexion(), na.getCedula().getText(), na.getNotas(),na.getObservaciones(),na.getUbicacion());//actualizando la vista con la modificacion realizada
                    na.getTurno().setText(mna.getTurno());

                }else{}



        }




        if(e.getActionCommand().equalsIgnoreCase("INSCRIBIR")){


                if(mna.confirmacion("ADICION DE MATERIA")==0){//si se acepta realizar las modificaciones

                    mna.inscribir_materia(cbd.getConexion(),
                                  mna.getBase_datos(),
                                  na.getCedula().getText(),
                                  na.getCodigo().getText(),
                                  na.getSeccion().getText().toUpperCase(),
                                  (na.getSemestre().getSelectedIndex()+1),
                                  na.getPeriodo().getSelectedItem().toString(),
                                  materia.condicion_materia_inversa(na.getCondicion().getSelectedItem().toString()),
                                  Integer.valueOf(na.getReparacion().getText()),
                                  Integer.valueOf(na.getDefinitiva().getText()),
                                  Integer.valueOf(na.getDefreparacion().getText()),
                                  Integer.valueOf(na.getInasistencia().getText()),
                                  mna.codigo_especialidad(na.getCarrera().getText()),
                                  na.getResponsable().getText(),
                                  na.getAsignaturas().getSelectedItem().toString());



                         na.getCondicion().setSelectedIndex(0);
                         na.getSeccion().setText("");
                         na.getDefinitiva().setText("0");
                         na.getReparacion().setText("0");
                         na.getDefreparacion().setText("0");
                         na.getInasistencia().setText("0");
                         na.getPeriodo().setSelectedIndex(0);

                //    rm.depurador_estudiantes_semestres(na.getCedula().getText(),na.getCarrera().getText() );//cargando las materias autorizadas despues de que el depurador haya terminado
                 //   System.out.println("capacidad LinkedList: "+materia.getLista_autorizada().size());
                   // for(int a=0; a<materia.getLista_autorizada().size();a++){
                     //   System.out.println("revisando autorizadas: "+materia.getLista_autorizada().get(a));
                   // }
                    
                }else{}
            


        }



        if(e.getActionCommand().equalsIgnoreCase("COMPARAR")){

            mna.comparaciones_libres(cbd.getConexion(), na.getVigencias(),na.getAnalisis());

        }



        if(e.getActionCommand().equalsIgnoreCase("FORZAR INSCRIPCION")){

            mna.forzar_inscripcion(cbd.getConexion(), na.getCedula().getText());
        }





    }//fin del action performed






    public void mouseClicked(MouseEvent e) {

      // if(e.isMetaDown()){//solo responde cuando se da click derecho
            mna.seleccion(na.getNotas());
      // }

           

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
       
    }




    public void itemStateChanged(ItemEvent e) {


        if(e.getStateChange()==1 ){//para evitar rebote ya que este genera dos eventos

                if(e.getItemSelectable().toString().substring(22).startsWith("semestre") && na.getSemestre().isFocusOwner()){//que ese combobox ademas de cambiar tenga focus
                    //System.out.println("semestre");
                    mna.manipulacion_pensum(na.getSemestre(),na.getAsignaturas(),na.getCodigo(), materia.getPensum());//colocando las materias segun la carrera y el semestre

                }

                
               if(e.getItemSelectable().toString().substring(22).startsWith("asignatura") && na.getAsignaturas().isFocusOwner()){
                    //System.out.println("asignatura");
        
                    mna.materia_codigo(na.getAsignaturas(), na.getCodigo(), materia.getPensum());//colocando las materias segun la carrera y el semestre
                }


        }else{}


        
    }







}//fin de la clase
