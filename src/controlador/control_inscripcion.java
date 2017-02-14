/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import com.lowagie.text.DocumentException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.registro_ingenieria;
import vista.horario;
import vista.inscripcion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import modelo.conexion_base_de_datos;
import modelo.configuracion_tablas;
import modelo.depuracion;
import modelo.Archivos;
import modelo.imagenes;
import modelo.materias;
import modelo.materias2;
import modelo.modelo_notas_alumnos;
import modelo.otros;
import modelo.parametros;
import modelo.registro_inicio;
import modelo.registro_pdf;
import modelo.registros_bd;


import vista.inicio;
import vista.reporte;

/**
 *
 * @author AURORA
 */
public class control_inscripcion implements ActionListener, MouseListener, KeyListener, ItemListener, WindowListener{

    private registro_ingenieria reging;
    private inscripcion ipcion;
    private horario hor;
    private conexion_base_de_datos cbd;
    private materias materia;
    private configuracion_tablas ctablas;
    private registro_pdf regpdf;
    private depuracion depura;
    private Archivos doc;
    private registros_bd regbd;
    private registro_inicio regini;
    private reporte report;
    private materias2 materia2;
    private modelo_notas_alumnos mna;
    private imagenes ima;
    private otros ots;
    private parametros para;

private DefaultTableModel andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE","CONDICION"});
 private int x=0;


    public control_inscripcion(inscripcion ipcion) {
        this.ipcion = ipcion;
        reging = new registro_ingenieria();
        cbd= new conexion_base_de_datos();
        materia = new materias();
        ctablas = new configuracion_tablas();
        regpdf = new registro_pdf();
        depura = new depuracion();
        doc = new Archivos();
        regbd = new registros_bd();
        regini = new registro_inicio();
        report = new reporte();
        materia2 = new materias2();
        mna = new modelo_notas_alumnos();
        ima = new imagenes();
        ots = new otros();
        para = new parametros();
    }

   
    public control_inscripcion(horario hor) {
        this.hor = hor;
        reging = new registro_ingenieria();
        cbd = new conexion_base_de_datos();
        materia = new materias();
        ctablas = new configuracion_tablas();
        regpdf = new registro_pdf();
        depura = new depuracion();
        doc = new Archivos();
        regbd = new registros_bd();
        regini = new registro_inicio();
        materia2 = new materias2();
        ima = new imagenes();
        ots = new otros();
        para = new parametros();
    }

   

     public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public DefaultTableModel getAndy() {
        return andy;
    }

    public void setAndy(DefaultTableModel andy) {
        this.andy = andy;
    }




    public void actionPerformed(ActionEvent e) {

          if(e.getActionCommand().equalsIgnoreCase("REFRESCAR")){//se encarga de
                               

            ipcion = new inscripcion();
            ipcion.setVisible(true);
            ipcion.getCedula().setText(hor.getParametros().getText());
            ipcion.getPeriodo().setText(hor.getPeriodo_actual().getText());
            ipcion.setPrivilegio(hor.getPrivilegio());

            hor.dispose();
            
          }








          if(e.getActionCommand().equalsIgnoreCase("principal")){

            vista.inscripcion inscribir = new vista.inscripcion();
            inscribir.setVisible(true);
            inscribir.getCedula().setText(hor.getParametros().getText());
            hor.dispose();

          }





        if(e.getActionCommand().equalsIgnoreCase("VALIDAR")){//recordar implementar la contraseña
            materia.getOrden().clear(); //limpiando la variable que almcena el record acomodado por periodo academico
            materia = new materias();//es para limpiar todas las variables            
            if(ipcion.getCedula().getText().equalsIgnoreCase(null) || ipcion.getCedula().getText().equalsIgnoreCase("") ){//validando que la casilla de la cedula no este vacia
                ima.mensaje_informacion("NO DEJE LA CASILLA DE LA CEDULA EN BLANCO", "ADVERTENCIA", "exclamacion", "png") ;               
                  ipcion.getCedula().setText(null);
                  ipcion.getEstudiante().setText(null);
                  ipcion.getCarrera().setText(null);
                  ipcion.getNucleo().setText(null);                  
            }else{
               ipcion.getInscribir().setEnabled(true);//habilitando el boton de inscribir despues de presionar validar
               reging.verificar_alumno(cbd.getConexion(), ipcion.getCedula().getText());//verificando que el alumno se encuentre registrado en el sistema
                                                                                         //para luego cargar su record y darle el resto de las opciones
          
                    if(reging.getControl()==0){//en caso de que el alumno se halla actualizado en el sistema
                      ipcion.getEstudiante().setText(reging.getEstudiante());
                      ipcion.getCarrera().setText(reging.getCarrera());
                      ipcion.getNucleo().setText(reging.getNucleo_extension());
                      
                     // ipcion.getFoto().getGraphics().drawImage(reging.getFoto(), 0, 0,null);
                       // ipcion.getFoto_bd().setIcon(new ImageIconUIResource(reging.getFoto()));//fotos
                        ipcion.getFoto_bd().setIcon(new ImageIcon(reging.getFoto().getScaledInstance(185, 215, 2))); //2=scale fast. reesclando la imagen

                      ipcion.getMatricula().setText(regpdf.opsu_matricula(reging.getFecha_ingreso().replace("/","-"),
                                                                          regpdf.opsu_sede(reging.getNucleo_extension()),
                                                                          regpdf.opsu_carrera(reging.getCarrera()),
                                                                          regpdf.opsu_turno(reging.getTurnos()),
                                                                          ipcion.getCedula().getText()));
                     

                      for(int v=0; v<=2; v++){//revisando la base de datos 3 veces para hallar en que vigencia se encuentra el alumno
                          System.out.println("viendo"+v);
                          materia.setSeleccion_pensum(v);
                          reging.setReconocimiento(v);
                          //reging.buscar_materias(cbd.getConexion(), ipcion.getCedula().getText(),materia.notas_especialidad(ipcion.getCarrera().getText(),reging));//cargando las materias del alumno
                          reging.buscar_materias_alternativo(cbd.getConexion(), ipcion.getCedula().getText(),materia.notas_especialidad(reging));//cargando las materias del alumno

                          //if(reging.getPeriodo().isEmpty()){}else{break;}
                          if(reging.getRecord().isEmpty()){}else{break;}

                      }//fin revisando la base de datos 3 veces

                          System.out.println("para materia: "+reging.getReconocimiento()+" v: "+materia.getSeleccion_pensum());

                          //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera()));//cargando las materias del pensum




                          

                       ipcion.setDeposito(reging.academico());//configurando la tabla jtable
                       ipcion.getDesempeño_academico().setModel(ipcion.getDeposito());

                            //if(reging.getPeriodo().isEmpty()){
                            if(reging.getRecord().isEmpty()){
                                        ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "precaucion", "png");

                                        materia.setNuevo_ingreso(1);
                                        //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum
                            }
                            else{
                                        //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum

                                            //---------ordenando las materias por periodo academico
                                            //for(int b=0; b<reging.getCodigo().size();b++){
                                            for(int b=0; b<reging.getRecord().size();b=b+11){
                                              //materia.ordenamiento_año(reging.getPeriodo().get(b));
                                              materia.ordenamiento_año(reging.getRecord().get(b+5));
                                            }

                                                //materia.ordenamiento_materias(reging);//ordenando las materias del alumno por periodo academico
                                                materia.ordenamiento_materias_alternativos(reging);//ordenando las materias del alumno por periodo academico
                                                reging.setComp_per(materia.getOrden().get(5));//inicializando la variable con el primer periodo inscrito

                                                //for(int i=0; i<materia.getOrden().size(); i=i+6){//recorriendo las materias ordenadas
                                                for(int i=0; i<materia.getOrden().size(); i=i+11){//recorriendo las materias ordenadas

                                                        if(reging.getComp_per().equalsIgnoreCase(materia.getOrden().get(i+5))){ }
                                                        else{//para separar entre periodos academicos cuando se presenta en la tabla
                                                         ipcion.getDeposito().addRow(new Object[]{null,null,null,null,null,null});
                                                         ipcion.getDeposito().fireTableDataChanged();
                                                         reging.setComp_per(materia.getOrden().get(i+5));
                                                          }
                                                     //-----BUSCANDO EL SEMESTRE DE LA MATERIA
                                                       // String sem_mat="";//inicializando la variable
                                                        //for(int sem=0; sem<materia.getPensum().size(); sem=sem+7){if(materia.getOrden().get(i).equalsIgnoreCase(materia.getPensum().get(sem+0))){sem_mat=materia.getPensum().get(sem+1);break;}}
                                                       // for(int sem=0; sem<materia.getPensum().size(); sem=sem+7){if(materia.getOrden().get(i).equalsIgnoreCase(materia.getPensum().get(sem+0))){sem_mat=materia.getPensum().get(sem+1);break;}}
                                                     //----------------------------------------
                                                        
                                                         //   System.out.println("SEMESTRE DE LA MATERIA: "+materia.getOrden().get(i+1)+" - semestre: "+sem_mat);

                                                     Object nuevo[]={ materia.getOrden().get(i+10), //semestre de la materia
                                                                      materia.getOrden().get(i),//codigo de la materia
                                                                      materia.getOrden().get(i+1),//nombre de la materia                                                                      
                                                                      regpdf.nota_presentacion(materia.getOrden().get(i+2), materia.getOrden().get(i+3), materia.getOrden().get(i+6), materia.getOrden().get(i+4))[0],
                                                                      regpdf.nota_presentacion(materia.getOrden().get(i+2), materia.getOrden().get(i+3), materia.getOrden().get(i+6), materia.getOrden().get(i+4))[1],
                                                                      //materia.getOrden().get(i+2),//definitiva de la materia
                                                                     // materia.getOrden().get(i+3),//nota de la reparacion
                                                                      materia.getOrden().get(i+4),//condicion de la materia

                                                                      materia.getOrden().get(i+5) //periodo academico de la materia
                                                                    };

                                                                         ipcion.getDeposito().addRow(nuevo);
                                                                         ipcion.getDeposito().fireTableDataChanged();
                                                }


                                              //--------------------------------------------------FIN




                                         }  //validacion de que halla informacion del estudiante en cuanto a notas
                                           //en la base de datos

                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"SEMESTRE",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CODIGO",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"MATERIA",300,"izquierda");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"DEFINITIVA",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"REPARACION",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CONDICION",10,"izquierda");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"PERIODO",10,"centrado");

                                                          materia.electivas_x_semestre(materia.getPensum());//me entrega las asociaciones electivas pensum vs las reales record y peeriodo
                                                          materia.materias_preladas_y_no_preladas();
                                                          materia.esquema_prelacion();
                                                          materia.esquema_normal();
                                                          materia2.servicio_comunitario(materia);
                                                        //CREANDO UNA VENTANA PARA VISUALIZAR LAS MATERIAS QUE EL ESTUDIANTE PUEDE INSCRIBIR

              


                                                          System.out.println("\nMATERIAS AUTORIZADAS");
                                                           for(int h=0; h<materia.getLista_autorizada().size();h=h+4){//buscando todas las materias autorizadas
                                                            System.out.println("semestre: "+materia.getLista_autorizada().get(h+3)+" codigo: "+materia.getLista_autorizada().get(h+0)+" nombre: "+materia.getLista_autorizada().get(h+1)+" condicion: "+materia.getLista_autorizada().get(h+2));
                                                           
                                                           }

                                                          ctablas.tabla_habilitadas(ipcion.getAutorizadas(), materia.getLista_autorizada());//escribiendo en la tabla de materias autorizadas
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"SEMESTRE", 5, "centrado");
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"CODIGO", 5, "izquierda");
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"MATERIA", 300, "izquierda");
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"CONDICION", 20, "centrado");


                                                          ipcion.getVigencias().setText(materia.getVigencia());//colocando la vigencia del pensum a la que pertenece el alumno
                                                    
                                                    



                                                          //materia.getOrden().clear();
                                                          materia.getAux1().clear();
                                                          materia.getAux2().clear();
                                                        /* reging.getCodigo().clear();
                                                          reging.getMateria().clear();
                                                          reging.getDefinitiva().clear();
                                                          reging.getReparacion().clear();
                                                          reging.getCondicion().clear();
                                                          reging.getPeriodo().clear();*/

                                                          reging.getRecord().clear();

                                                          //materia.getPensum().clear();
                                                          materia.getLlenado().clear();
                                                          materia.getPosicion().clear();
                                                          materia.getPreladas().clear();
                                                          materia.getNo_preladas().clear();
                                                        //  materia.getLista_autorizada().clear();

                    }else{
                          ipcion.getCedula().setText(null);
                          ipcion.getEstudiante().setText(null);
                          ipcion.getCarrera().setText(null);                          
                          ipcion.getAutorizadas().setModel(new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","CONDICION"}));
                          ipcion.getDesempeño_academico().setModel(new DefaultTableModel(new Object [][]{},new String [] {"SEMESTRE","CODIGO", "MATERIA", "DEFINITIVA", "REPARACION", "CONDICION", "PERIODO"}));
                          ipcion.getNucleo().setText(null);
                          }//si el estudiante no esta en el sistema no se muestra nada


                    }//fin validacion de la casilla cedula este vacia



 andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE","CONDICION"});
 ipcion.getSecciones().setModel(andy);
 materia.materias_inscritas_bd(ipcion.getSecciones(),andy,cbd.getConexion(),ipcion.getCedula().getText(),ipcion.getPeriodo().getText());
                    ctablas.configuracion(ipcion.getSecciones(),andy,"SEMESTRE",2,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"CODIGO",5,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"MATERIA",150,"izquierda");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"UC",2,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"SECCION",20,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"HORARIO",30,"izquierda");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"DOCENTE",20,"izquierda");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"CONDICION",5,"centrado");



                    


                                                        }//fin del boton VALIDAR






        if(e.getActionCommand().equalsIgnoreCase("INSCRIBIR")){

            vista.horario horario = new vista.horario();
            horario.setVisible(true);
            horario.getParametros().setText(ipcion.getCedula().getText());
            horario.getEspecialidad().setSelectedItem(ipcion.getCarrera().getText());
            horario.getSalones().setText((materia.seccion(ipcion.getCarrera().getText())));//colocando el semestre segun la especialidad
            horario.getEstudiante().setText(ipcion.getEstudiante().getText());
            horario.setLista_autorizada_traspaso(materia.getLista_autorizada());
            horario.setPensum_traspaso(materia.getPensum());
            horario.getNucleo().setText(ipcion.getNucleo().getText());

            horario.setTraspaso_comunitario(materia.getSer_comunitario());
            horario.setTraspaso_ele_cur(materia.getEle_cur());//respaldando las eletivas vistas aprobadas
            horario.setTraspaso_electivas(materia.getElectivas());//respaldando materias electivas reales
            horario.setTrapaso_record(materia.getOrden()); //respaldando materias ordenadas del record
            horario.setTrapaso_coprelaciones(materia.getCoprelaciones()); // respaldando todas las coprelaciones que hasta ahora afectan al alumno
            horario.getPeriodo_actual().setText(ipcion.getPeriodo().getText());//enviando el periodo actual de trabajo
            horario.setPrivilegio(ipcion.getPrivilegio());
           // System.out.println("PRIVILEGIO: "+ipcion.getPrivilegio());
            if(ipcion.getPrivilegio().equalsIgnoreCase("123 - ESTUDIANTE"))horario.getRefrescar().setEnabled(false);//solo para el caso de que sea solo estudiante.
            ipcion.dispose();

           

         //  System.out.println("lista autorizada "+materia.getLista_autorizada().size()/3);//viendo la cantidad de informacion traspazada

        }

        if(e.getActionCommand().equalsIgnoreCase("REVISAR")){

            System.out.println("record "+hor.getTrapaso_record().size()/11+"  coprelaciones "+hor.getTrapaso_coprelaciones().size()/3);

            for(int i=0; i<hor.getTrapaso_record().size(); i++){
            System.out.println(i+" registrando record "+hor.getTrapaso_record().get(i));
            }
        
            for(int i=0; i<hor.getTrapaso_coprelaciones().size(); i++){
            System.out.println(i+" registrando coprelaciones "+hor.getTrapaso_coprelaciones().get(i));
            }

        }





   //-------------------------------------------------------------



        if(e.getActionCommand().equalsIgnoreCase("eliminar")){

                   /*andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE"});
                     hor.getSecciones().setModel(andy);*/
               materia.eliminancion_materia(hor.getSecciones(), andy,cbd.getConexion(),hor.getParametros().getText(), hor.getPeriodo_actual().getText());
               hor.getUc().setText(materia.getCredito()+"/30");
        }

    




    if(e.getActionCommand().equalsIgnoreCase("atras")){
       hor.getSalones().setText(materia.secciones_dinamicas(hor.getSemestre(), hor.getSalones().getText(),"resta"));

      System.out.println( hor.getHorario().getValueAt(0, 0));

       hor.getHorario().setModel(reging.inf_horarios());//limpiando la tabla
       materia.llenado_materia(cbd.getConexion(),hor.getSalones().getText(),hor.getHorario(),hor.getLista_autorizada_traspaso(),hor.getTurno().getText(), hor.getPeriodo_actual().getText());//LLENADO DE HORARIO

                                                        }//fin boton atras


    if(e.getActionCommand().equalsIgnoreCase("siguiente")){
       
        
         hor.getSalones().setText(materia.secciones_dinamicas(hor.getSemestre(), hor.getSalones().getText(),"suma"));
         
         hor.getHorario().setModel(reging.inf_horarios());//limpiando la tabla
         materia.llenado_materia(cbd.getConexion(),hor.getSalones().getText(),hor.getHorario(),hor.getLista_autorizada_traspaso(),hor.getTurno().getText(), hor.getPeriodo_actual().getText());//LLENADO DE HORARIO

         

           // System.out.println("traspazado= "+hor.getLista_autorizada_traspaso().size()/3);
                                                          }//fin boton siguiente





          

    if(e.getActionCommand().equalsIgnoreCase("FINALIZAR")){

        ipcion.dispose();
        new inicio().setVisible(true);

       // if(hor.isVisible()){hor.dispose();new inicio().setVisible(true);}
       // if(ipcion.isVisible()){ipcion.dispose();new inicio().setVisible(true);}
    }

    if(e.getActionCommand().equalsIgnoreCase("TERMINAR")){


        
     //reging.buscar_materias(cbd.getConexion(), hor.getParametros().getText(),materia.notas_especialidad(hor.getEspecialidad().getSelectedItem().toString(),reging));//cargando las notas del alumno
     //materia.materias_pensum(cbd.getConexion(),materia.carrera(hor.getEspecialidad().getSelectedItem().toString()));//cargando las materias del pensum
     //materia.setPensum(hor.getPensum_traspaso());

     // for(int b=0; b<reging.getCodigo().size();b++){
     //      materia.ordenamiento_año(reging.getPeriodo().get(b));
     // }

       // materia.ordenamiento_materias(reging);//ordenando las materias del alumno por periodo academico
       // materia.pdf_materias_inscritas(cbd.getConexion(), hor.getParametros().getText());


        
      
         
        materia.autentificador(hor, andy, hor.getParametros().getText(),hor.getPeriodo_actual().getText())   ;

     
                if(regpdf.confirmacion("\nLa Constancia de Inscripcion\nRecord Academico\nConstancia de Estudio")==0){//confirmacion para crear el pdf despues de generar la inscripcion
               
                    regpdf.todos_estudiantes(cbd.getConexion(),hor.getParametros().getText());//buscando ingreso, mes y año

                    ots.sistema_operativo();//identificando el sistema operativo


                        try {
                             
                            //regpdf.calculos(hor.getTrapaso_record(), hor.getPensum_traspaso());//calculando uc aprobadas
                            regpdf.calculo_alternativo(hor.getTrapaso_record(), hor.getPensum_traspaso(), hor.getTraspaso_electivas());//calculando uc aprobadas
                            materia.pdf_materias_inscritas(cbd.getConexion(),hor.getParametros().getText(),hor.getPeriodo_actual().getText(),false);

                                if(ots.getSistema().startsWith("Windows")){
                                                    regpdf.record_academico_alternativo("c://constancias/"+hor.getParametros().getText()+".pdf",//windows
                            //regpdf.record_academico("/home/usuario/inscripciones/"+hor.getParametros().getText()+".pdf",//linux
                                                    hor.getParametros().getText(),
                                                    hor.getEstudiante().getText(),
                                                    hor.getEspecialidad().getSelectedItem().toString(),
                                                    hor.getTurno().getText(),
                                                    hor.getNucleo().getText(),//"NUCLEO ARAGUA - SEDE MARACAY",
                                                    regpdf.getPeriodo_ingreso(),
                                                    materia.getPdf_mi(),
                                                    hor.getTrapaso_record(),
                                                    hor.getPensum_traspaso(),
                                                    hor.getPeriodo_actual().getText());
                            
                                
                                }else{//otro sistema operativo usa otra ruta

                                      //  regpdf.record_academico("c://constancias/"+hor.getParametros().getText()+".pdf",//windows
                                                    regpdf.record_academico_alternativo("/home/usuario/inscripciones/"+hor.getParametros().getText()+".pdf",//linux
                                                    hor.getParametros().getText(),
                                                    hor.getEstudiante().getText(),
                                                    hor.getEspecialidad().getSelectedItem().toString(),
                                                    hor.getTurno().getText(),
                                                    hor.getNucleo().getText(),//"NUCLEO ARAGUA - SEDE MARACAY",
                                                    regpdf.getPeriodo_ingreso(),
                                                    materia.getPdf_mi(),
                                                    hor.getTrapaso_record(),
                                                    hor.getPensum_traspaso(),
                                                    hor.getPeriodo_actual().getText());

                                }

                           
                            //regpdf.crear_pdf("d://prueba_pdf_andy_cusatti_tipo2.pdf");//archivo pdf de prueba

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(control_inscripcion.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(control_inscripcion.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (DocumentException ex) {
                            Logger.getLogger(control_inscripcion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//fin confirmacion pdf
                                                /*  materia.getOrden().clear();
                                                  materia.getAux1().clear();
                                                  materia.getAux2().clear();
                                                  reging.getCodigo().clear();
                                                  reging.getMateria().clear();
                                                  reging.getDefinitiva().clear();
                                                  reging.getReparacion().clear();
                                                  reging.getCondicion().clear();
                                                  reging.getPeriodo().clear();

                                                  materia.getPensum().clear();
                                                  materia.getLlenado().clear();
                                                  materia.getPosicion().clear();
                                                  materia.getPreladas().clear();
                                                  materia.getNo_preladas().clear();
                                                 // materia.getLista_autorizada().clear();
                                               */


       registros_bd regbd = new registros_bd();
       regbd.acuse_inscripcion(new conexion_base_de_datos().getConexion(), hor.getParametros().getText() );

       
       inicio ini = new inicio();
       ini.setPeriodo_actual(hor.getPeriodo_actual().getText());
       ini.setPrivilegio(hor.getPrivilegio());
       System.out.println("privilegio horario-inicio: "+hor.getPrivilegio());       
       ini.setVisible(true);
       if(hor.getPrivilegio().equalsIgnoreCase("123 - ESTUDIANTE")){ini.getClave().setVisible(true);ini.getClave_etiqueta().setVisible(true);}//esto es en caso que la inscripcion la este haciendo el estudiante personalemente
       hor.dispose();
    //   new inicio().setVisible(true);
        

       // if(hor.isVisible()){hor.dispose();new inicio().setVisible(true);}
       // if(ipcion.isVisible()){ipcion.dispose();new inicio().setVisible(true);}
    }





        if(e.getActionCommand().equalsIgnoreCase("imprimir")){

        depura.lista_cedula(cbd.getConexion(),"c:/lista_de_cedulas.txt","notas_alumnos_todas_carreras_2009");
        System.out.println("termino la depuracion de las cedulas\n\n");


        System.out.println("REVISANDO ULTIMO PERIODO INSCRITO DE CADA ALUMNO");
        doc.cargar_contenedor("c:/lista_de_cedulas.txt");//cargando la lista de las cedulas
        System.out.println("CONTENEDOR "+doc.getContenedor().size());

               

                
            for(int f=0; f<doc.getContenedor().size(); f++){//buscando el ultimo periodo inscrito
                materia.setMas_grande(0);
                materia.setMas_pequeño(0);
                System.out.println(f+"/"+(doc.getContenedor().size()-1));

              if(doc.getContenedor().get(f).length()>=5){//evitar cedula en blanco o cedulas erroneas

                    

                   // reging.buscar_materias(cbd.getConexion(),doc.getContenedor().get(f),"notas_alumnos_todas_carreras_2009" );//cargando la nota por alumno
                    reging.buscar_materias_alternativo(cbd.getConexion(),doc.getContenedor().get(f),"notas_alumnos_todas_carreras_2009" );//cargando la nota por alumno

                    //for(int b=0; b<reging.getCodigo().size();b++){//ordenando el periodo actual
                    for(int b=0; b<reging.getRecord().size();b=b+11){//ordenando el periodo actual
                            //materia.ordenamiento_año(reging.getPeriodo().get(b));
                            materia.ordenamiento_año(reging.getRecord().get(b+5));
                    }

                    System.out.println("ALUMNO: "+doc.getContenedor().get(f)+"  U.P.I -> "+materia.getMas_grande());
                    doc.escritor_textos("c:/list_todas_las_carreras_2009.txt", doc.getContenedor().get(f)+"   "+materia.getMas_grande());


              }else{}


            }//fin buscando ultimo periodo inscrito

            

      /*  int cuenta=0;
        cuenta=doc.getContenedor().size();
        System.out.println("tamaño del LinkedList: "+cuenta);

        for(int f=0; f<doc.getContenedor().size(); f++){


        depura.semestre_virtual(cbd.getConexion(),doc.getContenedor().get(f),"ingenieria_mecanica_2007","notas_alumnos_edu_ing_2007");
        System.out.println("posicion="+f+"/"+(cuenta-1)+"  prueba: "+doc.getContenedor().get(f));

        }
        doc.getContenedor().clear();
*/




        }//fin del boton imprimir






          
          

 if(e.getActionCommand().equalsIgnoreCase("opcional")){
 System.out.println("opcional funcionando");
  //hor.getHorario().setValueAt("123456789012345", 12, 1);
 hor.getHorario().setModel(reging.inf_horarios());//limpiando la tabla
 materia.llenado_materia(cbd.getConexion(),hor.getSalones().getText(),hor.getHorario(),hor.getLista_autorizada_traspaso(),hor.getTurno().getText(), hor.getPeriodo_actual().getText());//LLENADO DE HORARIO


 andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE"});
 hor.getSecciones().setModel(andy);
 hor.getUc().setText(String.valueOf(materia.materias_inscritas_bd(hor.getSecciones(),andy,cbd.getConexion(),hor.getParametros().getText(),ipcion.getPeriodo().getText()))+"/30");




 }

    }//fin del ActionPerformed







    
    public void mouseClicked(MouseEvent e) {
       // System.out.println("##########################GENERADOR DEL EVENTO: "+e.getComponent().getName());
               
       if(e.isMetaDown() && e.getComponent().getName().equalsIgnoreCase("salones")){
          // System.out.println("Apretado click derecho");
           hor.getSalones().setText(para.secciones_rapida(hor.getSalones().getText()));

           hor.getHorario().setModel(reging.inf_horarios());//limpiando la tabla
           materia.llenado_materia(cbd.getConexion(),hor.getSalones().getText(),hor.getHorario(),hor.getLista_autorizada_traspaso(),hor.getTurno().getText(), hor.getPeriodo_actual().getText());//LLENADO DE HORARIO
       }

       if(e.getComponent().getName().equalsIgnoreCase("horario") && e.isMetaDown()==false ){//sean los clicks normal para inscribir}

               if(hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn())!=null && //evitando que la casilla seleccionada este en blanco
                  hor.getHorario().getSelectedColumn()!=0 && //evitando que cuando se de click a alguna casilla sea el de la hora
                  materia.evitar_repetir(hor.getSecciones(), hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(),//evita que la materia se repita en las inscritas y no se mezclen los turnos
                  hor.getHorario().getSelectedColumn()).toString(), hor.getHorario(),hor.getTurno().getText(), (hor.getSemestre().getSelectedIndex()+1) ).equalsIgnoreCase("falso")
                  && materia.maxima_repitencias(hor,2,"P") //analiza si se pueden seguir inscribiendo materias por repitencia segun el maximo establecido
                  && materia.requisitos_pasantia(hor.getLista_autorizada_traspaso(), hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString(),hor.getEspecialidad().getSelectedItem().toString())//revisando si es una materia final de pasantia o especial de grado y cumplio o no con los requisitos
                  ){


                //    materia.analisis_coprelacion(hor);//analisis de las coprelaciones y dobles coprelaciones



                   // materia.capacidad_salones(cbd.getConexion(),hor.getSalones().getText(), hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString());


                    System.out.println("VERIFICANDO IGUALDAD "+materia.comparacion_autorizadas_ofertadas(hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString(), hor));

                    //materia.maxima_2_repitencia(hor.getHorario(),hor.getSecciones() , hor);



                        if(materia.confirmacion_materia(hor.getHorario())==0){//en caso de que acepte la confirmacion se procedera a inscribir la materia

                             materia.analisis_coprelacion(hor,"nada");//analisis de las coprelaciones y dobles coprelaciones

                             materia.capacidad_salones(cbd.getConexion(),hor.getEspecialidad().getSelectedItem().toString(),hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString(),hor.getSalones().getText(), hor.getPeriodo_actual().getText(),hor.getTurno().getText());//verificando la capacidad de salones de la materia en caso de aceptar insribirla
                             System.out.println("cant cupos: "+materia.getCupos()+"  hay cupos: "+materia.isHay_cupos());

                               if(materia.isHay_cupos()==true){//comprobando que existan cupos en la seccion seleccionada

                                         materia.materias_inscribir(hor.getSecciones(), andy, hor.getHorario(),30,hor.getSalones().getText(),materia.getCondicion_materia(),hor);
                                         hor.getUc().setText(materia.getCredito()+"/30");


                                        // System.out.println(hor.getSecciones().getValueAt(hor.getSecciones().getRowCount()-1, 5).toString());//dia
                                        // System.out.println(hor.getHorario().getColumnName(hor.getHorario().getSelectedColumn()));
                                        // System.out.println(hor.getSecciones().getValueAt(hor.getSecciones().getRowCount()-1,5).toString().substring(hor.getHorario().getColumnName(hor.getHorario().getSelectedColumn()).length()+3));
                                        // System.out.println("cmateria: "+hor.getSecciones().getValueAt(hor.getSecciones().getRowCount()-1, 7).toString());


                                    ctablas.configuracion(hor.getSecciones(),andy,"SEMESTRE",2,"centrado");
                                    ctablas.configuracion(hor.getSecciones(),andy,"CODIGO",5,"centrado");
                                    ctablas.configuracion(hor.getSecciones(),andy,"MATERIA",150,"izquierda");
                                    ctablas.configuracion(hor.getSecciones(),andy,"UC",2,"centrado");
                                    ctablas.configuracion(hor.getSecciones(),andy,"SECCION",20,"centrado");
                                    ctablas.configuracion(hor.getSecciones(),andy,"HORARIO",30,"izquierda");
                                    ctablas.configuracion(hor.getSecciones(),andy,"DOCENTE",20,"izquierda");
                                    ctablas.configuracion(hor.getSecciones(),andy,"CONDICION",5,"centrado");


                                }//fin comprobacion de cupos


                         }else{}//fin confirmacion de inscribir materia





                    }else{}//fin evitar que la casilla seleccionada de la tabla este en blanco

        }//fin analisis clicks normal de inscripcion
     
 }

    public void mousePressed(MouseEvent e) {
       
    }

    public void mouseReleased(MouseEvent e) {
       
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
       
    }




    
    public void keyTyped(KeyEvent e) {
       
    }

    public void keyPressed(KeyEvent e) {
       
    }

    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == 10){//comprobando que se le de a la tecla enter como valido para ejecutar las operaciones siguientes
        //-----------------boton validar. segunda forma de activar-------
            materia.getOrden().clear(); //limpiando la variable que almcena el record acomodado por periodo academico
            materia = new materias();//es para limpiar todas las variables
            if(ipcion.getCedula().getText().equalsIgnoreCase(null) || ipcion.getCedula().getText().equalsIgnoreCase("") ){//validando que la casilla de la cedula no este vacia
                ima.mensaje_informacion("NO DEJE LA CASILLA DE LA CEDULA EN BLANCO", "ADVERTENCIA", "exclamacion", "png") ;
                  ipcion.getCedula().setText(null);
                  ipcion.getEstudiante().setText(null);
                  ipcion.getCarrera().setText(null);
                  ipcion.getNucleo().setText(null);
            }else{
               ipcion.getInscribir().setEnabled(true);//habilitando el boton de inscribir despues de presionar validar
               reging.verificar_alumno(cbd.getConexion(), ipcion.getCedula().getText());//verificando que el alumno se encuentre registrado en el sistema
                                                                                         //para luego cargar su record y darle el resto de las opciones

                    if(reging.getControl()==0){//en caso de que el alumno se halla actualizado en el sistema
                      ipcion.getEstudiante().setText(reging.getEstudiante());
                      ipcion.getCarrera().setText(reging.getCarrera());
                      ipcion.getNucleo().setText(reging.getNucleo_extension());
                      
                      //ipcion.getFoto_bd().setIcon(new ImageIconUIResource(reging.getFoto()));//fotos
                       ipcion.getFoto_bd().setIcon(new ImageIcon(reging.getFoto().getScaledInstance(185, 215, 2))); //2=scale fast. reesclando la imagen

                       ipcion.getMatricula().setText(regpdf.opsu_matricula(reging.getFecha_ingreso().replace("/","-"),
                                                                          regpdf.opsu_sede(reging.getNucleo_extension()),
                                                                          regpdf.opsu_carrera(reging.getCarrera()),
                                                                          regpdf.opsu_turno(reging.getTurnos()),
                                                                          ipcion.getCedula().getText()));


                      for(int v=0; v<=2; v++){//revisando la base de datos 3 veces para hallar en que vigencia se encuentra el alumno
                          System.out.println("viendo"+v);
                          materia.setSeleccion_pensum(v);
                          reging.setReconocimiento(v);
                          //reging.buscar_materias(cbd.getConexion(), ipcion.getCedula().getText(),materia.notas_especialidad(ipcion.getCarrera().getText(),reging));//cargando las materias del alumno
                          reging.buscar_materias_alternativo(cbd.getConexion(), ipcion.getCedula().getText(),materia.notas_especialidad(reging));//cargando las materias del alumno

                          //if(reging.getPeriodo().isEmpty()){}else{break;}
                          if(reging.getRecord().isEmpty()){}else{break;}

                      }//fin revisando la base de datos 3 veces

                          System.out.println("para materia: "+reging.getReconocimiento()+" v: "+materia.getSeleccion_pensum());

                          //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera()));//cargando las materias del pensum






                       ipcion.setDeposito(reging.academico());//configurando la tabla jtable
                       ipcion.getDesempeño_academico().setModel(ipcion.getDeposito());

                            //if(reging.getPeriodo().isEmpty()){
                            if(reging.getRecord().isEmpty()){
                                        ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "precaucion", "png");

                                        materia.setNuevo_ingreso(1);
                                        //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum
                            }
                            else{
                                        //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum

                                            //---------ordenando las materias por periodo academico
                                            //for(int b=0; b<reging.getCodigo().size();b++){
                                            for(int b=0; b<reging.getRecord().size();b=b+11){
                                              //materia.ordenamiento_año(reging.getPeriodo().get(b));
                                              materia.ordenamiento_año(reging.getRecord().get(b+5));
                                            }

                                                //materia.ordenamiento_materias(reging);//ordenando las materias del alumno por periodo academico
                                                materia.ordenamiento_materias_alternativos(reging);//ordenando las materias del alumno por periodo academico
                                                reging.setComp_per(materia.getOrden().get(5));//inicializando la variable con el primer periodo inscrito

                                                //for(int i=0; i<materia.getOrden().size(); i=i+6){//recorriendo las materias ordenadas
                                                for(int i=0; i<materia.getOrden().size(); i=i+11){//recorriendo las materias ordenadas

                                                        if(reging.getComp_per().equalsIgnoreCase(materia.getOrden().get(i+5))){ }
                                                        else{//para separar entre periodos academicos cuando se presenta en la tabla
                                                         ipcion.getDeposito().addRow(new Object[]{null,null,null,null,null,null});
                                                         ipcion.getDeposito().fireTableDataChanged();
                                                         reging.setComp_per(materia.getOrden().get(i+5));
                                                          }
                                                     //-----BUSCANDO EL SEMESTRE DE LA MATERIA
                                                       // String sem_mat="";//inicializando la variable
                                                        //for(int sem=0; sem<materia.getPensum().size(); sem=sem+7){if(materia.getOrden().get(i).equalsIgnoreCase(materia.getPensum().get(sem+0))){sem_mat=materia.getPensum().get(sem+1);break;}}
                                                       // for(int sem=0; sem<materia.getPensum().size(); sem=sem+7){if(materia.getOrden().get(i).equalsIgnoreCase(materia.getPensum().get(sem+0))){sem_mat=materia.getPensum().get(sem+1);break;}}
                                                     //----------------------------------------

                                                         //   System.out.println("SEMESTRE DE LA MATERIA: "+materia.getOrden().get(i+1)+" - semestre: "+sem_mat);

                                                    Object nuevo[]={ materia.getOrden().get(i+10), //semestre de la materia
                                                                      materia.getOrden().get(i),//codigo de la materia
                                                                      materia.getOrden().get(i+1),//nombre de la materia
                                                                      regpdf.nota_presentacion(materia.getOrden().get(i+2), materia.getOrden().get(i+3), materia.getOrden().get(i+6), materia.getOrden().get(i+4))[0],
                                                                      regpdf.nota_presentacion(materia.getOrden().get(i+2), materia.getOrden().get(i+3), materia.getOrden().get(i+6), materia.getOrden().get(i+4))[1],
                                                                      //materia.getOrden().get(i+2),//definitiva de la materia
                                                                     // materia.getOrden().get(i+3),//nota de la reparacion
                                                                      materia.getOrden().get(i+4),//condicion de la materia

                                                                      materia.getOrden().get(i+5) //periodo academico de la materia
                                                                    };

                                                                         ipcion.getDeposito().addRow(nuevo);
                                                                         ipcion.getDeposito().fireTableDataChanged();
                                                }


                                              //--------------------------------------------------FIN




                                         }  //validacion de que halla informacion del estudiante en cuanto a notas
                                           //en la base de datos

                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"SEMESTRE",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CODIGO",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"MATERIA",300,"izquierda");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"DEFINITIVA",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"REPARACION",5,"centrado");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CONDICION",10,"izquierda");
                                                            ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"PERIODO",10,"centrado");

                                                          materia.electivas_x_semestre(materia.getPensum());//me entrega las asociaciones electivas pensum vs las reales record y peeriodo
                                                          materia.materias_preladas_y_no_preladas();
                                                          materia.esquema_prelacion();
                                                          materia.esquema_normal();
                                                          materia2.servicio_comunitario(materia);
                                                        //CREANDO UNA VENTANA PARA VISUALIZAR LAS MATERIAS QUE EL ESTUDIANTE PUEDE INSCRIBIR


                                                          System.out.println("\nMATERIAS AUTORIZADAS");
                                                           for(int h=0; h<materia.getLista_autorizada().size();h=h+4){//buscando todas las materias autorizadas
                                                            System.out.println("semestre: "+materia.getLista_autorizada().get(h+3)+" codigo: "+materia.getLista_autorizada().get(h+0)+" nombre: "+materia.getLista_autorizada().get(h+1)+" condicion: "+materia.getLista_autorizada().get(h+2));

                                                           }

                                                          ctablas.tabla_habilitadas(ipcion.getAutorizadas(), materia.getLista_autorizada());//escribiendo en la tabla de materias autorizadas
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"SEMESTRE", 5, "centrado");
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"CODIGO", 5, "izquierda");
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"MATERIA", 300, "izquierda");
                                                          ctablas.configuracion(ipcion.getAutorizadas(),null,"CONDICION", 20, "centrado");


                                                          ipcion.getVigencias().setText(materia.getVigencia());//colocando la vigencia del pensum a la que pertenece el alumno





                                                          //materia.getOrden().clear();
                                                          materia.getAux1().clear();
                                                          materia.getAux2().clear();
                                                        /* reging.getCodigo().clear();
                                                          reging.getMateria().clear();
                                                          reging.getDefinitiva().clear();
                                                          reging.getReparacion().clear();
                                                          reging.getCondicion().clear();
                                                          reging.getPeriodo().clear();*/

                                                          reging.getRecord().clear();

                                                          //materia.getPensum().clear();
                                                          materia.getLlenado().clear();
                                                          materia.getPosicion().clear();
                                                          materia.getPreladas().clear();
                                                          materia.getNo_preladas().clear();
                                                        //  materia.getLista_autorizada().clear();

                    }else{
                          ipcion.getCedula().setText(null);
                          ipcion.getEstudiante().setText(null);
                          ipcion.getCarrera().setText(null);
                          ipcion.getAutorizadas().setModel(new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","CONDICION"}));
                          ipcion.getDesempeño_academico().setModel(new DefaultTableModel(new Object [][]{},new String [] {"SEMESTRE","CODIGO", "MATERIA", "DEFINITIVA", "REPARACION", "CONDICION", "PERIODO"}));
                          ipcion.getNucleo().setText(null);
                          }//si el estudiante no esta en el sistema no se muestra nada


                    }//fin validacion de la casilla cedula este vacia



 andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE","CONDICION"});
 ipcion.getSecciones().setModel(andy);
 materia.materias_inscritas_bd(ipcion.getSecciones(),andy,cbd.getConexion(),ipcion.getCedula().getText(),ipcion.getPeriodo().getText());
                    ctablas.configuracion(ipcion.getSecciones(),andy,"SEMESTRE",2,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"CODIGO",5,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"MATERIA",150,"izquierda");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"UC",2,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"SECCION",20,"centrado");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"HORARIO",30,"izquierda");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"DOCENTE",20,"izquierda");
                    ctablas.configuracion(ipcion.getSecciones(),andy,"CONDICION",5,"centrado");

        }//fin verificacion que se le halla dado a la tecla enter

        //-------------------inscripcion Boton: validar------------------

        /*
        if(ipcion.getCedula().getText().equalsIgnoreCase("")){
        System.out.println("campo cedula vacio");

        }else{

                        // System.out.println( regbd.verificador(act.getCedula().getText()) );
                    if(regbd.verificador(ipcion.getCedula().getText())){
                            ima.mensaje_informacion("NO PUEDES COLOCAR SEPARADORES EN LA CEDULA",  "ADVERTENCIA: CAMPO CEDULA", "avisos", "png");
                            
                            ipcion.getCedula().setText(regini.getCedula());


                        }else{regini.setCedula(ipcion.getCedula().getText());}

            }*/
    }

    public void itemStateChanged(ItemEvent e) {
       

        if(e.getStateChange()==1 ){
                hor.getSalones().setText(materia.secciones_dinamicas(
                                hor.getSemestre(), hor.getSalones().getText(),""));
                 hor.getHorario().setModel(reging.inf_horarios());//limpiando la tabla
                 materia.llenado_materia(cbd.getConexion(),hor.getSalones().getText(),hor.getHorario(),hor.getLista_autorizada_traspaso(),hor.getTurno().getText(), hor.getPeriodo_actual().getText());//LLENADO DE HORARIO

        
        }else{}
    }

    public void windowOpened(WindowEvent e) {


        //System.out.println("VENTANA ABIERTA");
       // materia.materias_pensum(cbd.getConexion(),materia.carrera(hor.getEspecialidad().getSelectedItem().toString()));//cargando las materias del pensum
        materia.setPensum(hor.getPensum_traspaso());
        materia.setElectivas(hor.getTraspaso_electivas());


        hor.getHorario().setModel(reging.inf_horarios());//limpiando la tabla
         hor.getTurno().setText(materia.turnos(cbd.getConexion(), hor.getParametros().getText()));//cargando el turno del estudiante
        materia.llenado_materia(cbd.getConexion(),hor.getSalones().getText(),hor.getHorario(),hor.getLista_autorizada_traspaso(),hor.getTurno().getText(), hor.getPeriodo_actual().getText());//LLENADO DE HORARIO
        //System.out.println("comprobando turno: "+hor.getTurno().getText());

        andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE","CONDICION"});
        hor.getSecciones().setModel(andy);
        hor.getUc().setText(String.valueOf(materia.materias_inscritas_bd(hor.getSecciones(),andy,cbd.getConexion(),hor.getParametros().getText(),hor.getPeriodo_actual().getText()))+"/30");

                    ctablas.configuracion(hor.getSecciones(),andy,"SEMESTRE",2,"centrado");
                    ctablas.configuracion(hor.getSecciones(),andy,"CODIGO",5,"centrado");
                    ctablas.configuracion(hor.getSecciones(),andy,"MATERIA",150,"izquierda");
                    ctablas.configuracion(hor.getSecciones(),andy,"UC",2,"centrado");
                    ctablas.configuracion(hor.getSecciones(),andy,"SECCION",20,"centrado");
                    ctablas.configuracion(hor.getSecciones(),andy,"HORARIO",30,"izquierda");
                    ctablas.configuracion(hor.getSecciones(),andy,"DOCENTE",20,"izquierda");
                    ctablas.configuracion(hor.getSecciones(),andy,"CONDICION",5,"centrado");



    //Analisis porsterior que se hace cuando se habre la ventana y tiene materias inscritas.
     materia.analisis_post_coprelacion(hor);
     materia.num_matxcondicion(hor, "P",2);

     materia.precarga_asociacion_electiva(hor);



    }

    public void windowClosing(WindowEvent e) {

        /*//estas tres lineas de codigo se ejecutan cuando la ventana de inscripcion se este cerrando dando click a la X, hace el mismo analisis cando se le da al boton TERMINAR
        materia.autentificador(hor, andy, hor.getParametros().getText(), hor.getPeriodo_actual().getText())   ;//busca que las coprelaciones doble se analicen

        inicio ini = new inicio();
        ini.setPeriodo_actual(hor.getPeriodo_actual().getText());//estableciendo el periodo con el que se esta trabajando
        //ini.setVisible(true);
        //new inicio().setVisible(true);//se abre la ventana de inicio
        ini.setPrivilegio(hor.getPrivilegio());
        System.out.println("privilegio horario-inicio_ventana: "+hor.getPrivilegio());
        ini.setVisible(true);
        if(hor.getPrivilegio().equalsIgnoreCase("123 - ESTUDIANTE")){ini.getClave().setVisible(true);ini.getClave_etiqueta().setVisible(true);}//esto es en caso que la inscripcion la este haciendo el estudiante personalemente
        hor.dispose();//se cierra la ventana de inscripcion
       //...........................................
*/



        materia.autentificador(hor, andy, hor.getParametros().getText(),hor.getPeriodo_actual().getText())   ;
                if(regpdf.confirmacion("\nLa Constancia de Inscripcion\nRecord Academico\nConstancia de Estudio")==0){//confirmacion para crear el pdf despues de generar la inscripcion
                    regpdf.todos_estudiantes(cbd.getConexion(),hor.getParametros().getText());//buscando ingreso, mes y año
                    ots.sistema_operativo();//identificando el sistema operativo
                        try {
                            //regpdf.calculos(hor.getTrapaso_record(), hor.getPensum_traspaso());//calculando uc aprobadas
                            regpdf.calculo_alternativo(hor.getTrapaso_record(), hor.getPensum_traspaso(), hor.getTraspaso_electivas());//calculando uc aprobadas
                            materia.pdf_materias_inscritas(cbd.getConexion(),hor.getParametros().getText(),hor.getPeriodo_actual().getText(),false);
                                if(ots.getSistema().startsWith("Windows")){
                                                    regpdf.record_academico_alternativo("c://constancias/"+hor.getParametros().getText()+".pdf",//windows
                            //regpdf.record_academico("/home/usuario/inscripciones/"+hor.getParametros().getText()+".pdf",//linux
                                                    hor.getParametros().getText(),
                                                    hor.getEstudiante().getText(),
                                                    hor.getEspecialidad().getSelectedItem().toString(),
                                                    hor.getTurno().getText(),
                                                    hor.getNucleo().getText(),//"NUCLEO ARAGUA - SEDE MARACAY",
                                                    regpdf.getPeriodo_ingreso(),
                                                    materia.getPdf_mi(),
                                                    hor.getTrapaso_record(),
                                                    hor.getPensum_traspaso(),
                                                    hor.getPeriodo_actual().getText());
                                }else{//otro sistema operativo usa otra ruta
                                      //  regpdf.record_academico("c://constancias/"+hor.getParametros().getText()+".pdf",//windows
                                                    regpdf.record_academico_alternativo("/home/usuario/inscripciones/"+hor.getParametros().getText()+".pdf",//linux
                                                    hor.getParametros().getText(),
                                                    hor.getEstudiante().getText(),
                                                    hor.getEspecialidad().getSelectedItem().toString(),
                                                    hor.getTurno().getText(),
                                                    hor.getNucleo().getText(),//"NUCLEO ARAGUA - SEDE MARACAY",
                                                    regpdf.getPeriodo_ingreso(),
                                                    materia.getPdf_mi(),
                                                    hor.getTrapaso_record(),
                                                    hor.getPensum_traspaso(),
                                                    hor.getPeriodo_actual().getText());
                                }
                            //regpdf.crear_pdf("d://prueba_pdf_andy_cusatti_tipo2.pdf");//archivo pdf de prueba
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(control_inscripcion.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(control_inscripcion.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (DocumentException ex) {
                            Logger.getLogger(control_inscripcion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//fin confirmacion pdf

       registros_bd regbd = new registros_bd();
       regbd.acuse_inscripcion(new conexion_base_de_datos().getConexion(), hor.getParametros().getText() );
       inicio ini = new inicio();
       ini.setPeriodo_actual(hor.getPeriodo_actual().getText());
       ini.setPrivilegio(hor.getPrivilegio());
       System.out.println("privilegio horario-inicio: "+hor.getPrivilegio());
       ini.setVisible(true);
       if(hor.getPrivilegio().equalsIgnoreCase("123 - ESTUDIANTE")){ini.getClave().setVisible(true);ini.getClave_etiqueta().setVisible(true);}//esto es en caso que la inscripcion la este haciendo el estudiante personalemente
       hor.dispose();

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
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}//fin de la clase control_inscripcion
