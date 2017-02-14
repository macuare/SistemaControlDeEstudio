/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.conexion_base_de_datos;
import modelo.depurador_maracay;
import modelo.externo;
import vista.DEPURADOR;
import vista.progreso;

/**
 *
 * @author AN
 */
public class control_depurador_maracay implements ActionListener {

    private DEPURADOR dep;
    private depurador_maracay dm;
    private conexion_base_de_datos cbd;
    private progreso p;

    public control_depurador_maracay(DEPURADOR dep) {
        this.dep = dep;
        dm = new depurador_maracay();
        cbd = new conexion_base_de_datos();
        p = new progreso();
    }


    







    public void actionPerformed(ActionEvent e) {



        if(e.getActionCommand().equalsIgnoreCase("DEPURAR")){
                dm.lista_cedula(cbd.getConexion(),"ce_maracay","notas_alumnos_todas_carreras_2010");//listando todos los alumnos
                        dep.getBarra().setMinimum(0);
                        dep.getBarra().setMaximum(dm.getAux2().size());


                for(int i=0; i<dm.getAux2().size();i++){//recorriendo cedulas
                     System.out.println("cedula: "+dm.getAux2().get(i));
                        dep.getBarra().setValue(i);

                     dm.buscar_materias(cbd.getConexion(),Integer.parseInt(dm.getAux2().get(i)), "ce_maracay","notas_alumnos_todas_carreras_2010");//rwvisando materias de cada alumno
                     dm.etapa_3(dm.getAux2().get(i));//escribiendo las materias de los alumnos aprobados en un archivo de texto


                }




        }//fin boton depurar



        if(e.getActionCommand().equalsIgnoreCase("LOCALIZAR")){
            

            


            System.out.println("...................VIGENCIA 2007...................");
            dep.getReporte().append("...................VIGENCIA 2007...................\n");

           dm.listas_estudiantes(cbd.getConexion(),"control_de_estudio","cem_notas_alumnos_todas_carreras_2007");
           dm.ejecucion(dep.getReporte());
                 

            System.out.println("...................VIGENCIA 2009...................");
            dep.getReporte().append("...................VIGENCIA 2009...................\n");

           dm.listas_estudiantes(cbd.getConexion(),"control_de_estudio","cem_notas_alumnos_todas_carreras_2009");
           dm.ejecucion(dep.getReporte());


            System.out.println("...................VIGENCIA 2010...................");
            dep.getReporte().append("...................VIGENCIA 2010...................\n");

           dm.listas_estudiantes(cbd.getConexion(),"control_de_estudio","cem_notas_alumnos_todas_carreras_2010");
           dm.ejecucion(dep.getReporte());


        }




        if(e.getActionCommand().equalsIgnoreCase("PRUEBA")){
            p.setVisible(true);
            externo ext = new externo(dm,p);

        }







    }//fin del action listeners










}//fin de la clase
