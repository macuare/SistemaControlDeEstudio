/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import javax.swing.ComboBoxModel;
import modelo.imagenes;
import modelo.registro_principal;
import vista.*;

/**
 *
 * @author AN
 */
public class control_PRINCIPAL implements ActionListener, WindowListener{

    private PRINCIPAL pri;
    private imagenes ima;
    private registro_principal rp;
    private inicio ini;
    private plan_b pb;
    private pensum pensu;
    private notas_alumnos na;
    private listados listado;
    private masivos masivo;
    private ofertas of;
    private cambios cambio;
    private convalidacion conva;
    private suspenciones suspencion;
    private nuevos nuevo;
    private autoridades auto;
    private reingresos rein;
    private documentacion docu;
    private accesos acc;
    private retiros ret;

    public control_PRINCIPAL(PRINCIPAL pri) {
        this.pri = pri;
        ima = new imagenes();
        rp = new registro_principal();
        
    }

    public void actionPerformed(ActionEvent e) {


       System.out.println("menus .."+e.getActionCommand());

       if(e.getActionCommand().equalsIgnoreCase("Accesos")){
         acc = new accesos();//inicializando todo
         acc.setName("accesos");//colocando nombre a la ventana
         acc.setVisible(true);
         rp.ventanas_abiertas(acc);//notificando que esta ventana se esta aperturando         
         acc.setPrivilegio(pri.getResponsable());                 
       }
       
       
       
       if(e.getActionCommand().equalsIgnoreCase("Estudiantes")){
         ini = new inicio();//inicializando todo
         ini.setName("INICIO");//colocando nombre a la ventana
         ini.setVisible(true);
         rp.ventanas_abiertas(ini);//notificando que esta ventana se esta aperturando
         ini.setPeriodo_actual(pri.getPeriodo_inscribir());
         ini.setPrivilegio(pri.getResponsable());
         if(pri.getResponsable().equalsIgnoreCase("123 - ESTUDIANTE")) {ini.getClave().setVisible(true);ini.getClave_etiqueta().setVisible(true);}
        // p.setResponsable(modaut.getCedulas()+" - "+modaut.getUsuario());
       }


       if(e.getActionCommand().equalsIgnoreCase("Plan B")){
            pb = new plan_b();
            pb.setName("PLAN B");//colocando nombre a la ventana
            pb.setVisible(true);
            rp.ventanas_abiertas(pb);//notificando que esta ventana se esta aperturando
            pb.getResponsable().setText(pri.getResponsable());
            pb.getPeriodo().setText(pri.getPeriodo_inscribir());
       }

        if(e.getActionCommand().equalsIgnoreCase("reactivacion")){
            suspencion = new suspenciones();
            suspencion.setName("REACTIVACION");//colocando nombre a la ventana
            suspencion.setVisible(true);
            rp.ventanas_abiertas(suspencion);//notificando que esta ventana se esta aperturando

       }

       if(e.getActionCommand().equalsIgnoreCase("Pensum")){
           pensu = new pensum();
           pensu.setName("PENSUM");//colocando nombre a la ventana
           pensu.setVisible(true);
           rp.ventanas_abiertas(pensu);//notificando que esta ventana se esta aperturando

       }

       if(e.getActionCommand().equalsIgnoreCase("Notas del Alumno")){
           LinkedList<String> p = new LinkedList<>();
          na = new notas_alumnos();
          na.setName("NOTAS ALUMNOS");//colocando nombre a la ventana
          na.setVisible(true);
          na.getResponsable().setText(pri.getResponsable());
          na.getPeriodo().removeAllItems();
          p = new imagenes().estimacion_periodo();
          for(String per : p){
               na.getPeriodo().addItem(per);
          }
             
          rp.ventanas_abiertas(na);//notificando que esta ventana se esta aperturando

       }

       if(e.getActionCommand().equalsIgnoreCase("Listados de Secciones")){
           listado = new listados();
           listado.setName("LISTADO SECCIONES");//colocando nombre a la ventana
           listado.setVisible(true);
           listado.getPeriodo_actual().setText(pri.getPeriodo_inscribir());
           rp.ventanas_abiertas(listado);//notificando que esta ventana se esta aperturando

       }

       if(e.getActionCommand().equalsIgnoreCase("Materias Inscritas")){
         masivo = new masivos();
         masivo.setName("MATERIAS INSCRITAS");//colocando nombre a la ventana
         masivo.setVisible(true);
         masivo.getPeriodo_academico().setText(pri.getPeriodo_inscribir());
         rp.ventanas_abiertas(masivo);//notificando que esta ventana se esta aperturando
         System.out.println("SUPERVISANDO ACCCESO: "+pri.getTitle());
             if(pri.getTitle().endsWith("nivel_4")){//es temporal ....solo se le habilita esta opcion PDF de todas las pestañas
                masivo.getSelecciones().setEnabledAt(0, false);
                masivo.getSelecciones().setEnabledAt(1, false);
                masivo.getSelecciones().setEnabledAt(2, false);
                masivo.getSelecciones().setEnabledAt(3, false);
                masivo.getSelecciones().setEnabledAt(4, true);
                masivo.getSelecciones().setEnabledAt(5, false);
                masivo.getSelecciones().setEnabledAt(6, false);
                masivo.getSelecciones().setEnabledAt(7, false);
             }

       }

       if(e.getActionCommand().equalsIgnoreCase("Constancias")){


       }

       if(e.getActionCommand().equalsIgnoreCase("Correos")){


       }

       if(e.getActionCommand().equalsIgnoreCase("Ofertas")){
        of = new ofertas();
        of.setName("OFERTAS");//colocando nombre a la ventana
        of.getPeriodo().setText(pri.getPeriodo_inscribir());
        rp.ventanas_abiertas(of);//notificando que esta ventana se esta aperturando
        of.setVisible(true);

       }

       if(e.getActionCommand().equalsIgnoreCase("Cambios")){
           cambio = new cambios();
           cambio.setName("CAMBIOS");//colocando nombre a la ventana
           rp.ventanas_abiertas(cambio);//notificando que esta ventana se esta aperturando
           cambio.setVisible(true);

       }

        if(e.getActionCommand().equalsIgnoreCase("Convalidacion")){
         conva = new convalidacion();
         conva.setName("CONVALIDACION");//colocando nombre a la ventana
         rp.ventanas_abiertas(conva);//notificando que esta ventana se esta aperturando
         conva.setVisible(true);

       }

       if(e.getActionCommand().equalsIgnoreCase("NUEVO INGRESO")){
         nuevo = new nuevos();
         nuevo.setName("NUEVO INGRESO");//colocando nombre a la ventana
         rp.ventanas_abiertas(nuevo);//notificando que esta ventana se esta aperturando
         nuevo.getPeriodo().setText(pri.getPeriodo_inscribir());
         nuevo.setVisible(true);

       }

       if(e.getActionCommand().equalsIgnoreCase("AUTORIDADES")){
         auto = new autoridades();
         auto.setName("AUTORIDADES");//colocando nombre a la ventana
         rp.ventanas_abiertas(auto);//notificando que esta ventana se esta aperturando
         auto.setVisible(true);

       }
       if(e.getActionCommand().equalsIgnoreCase("REINGRESOS")){
         rein = new reingresos();
         rein.setName("REIGNRESOS");//colocando nombre a la ventana
         rein.getPeriodo().setText(pri.getPeriodo_inscribir());
         rp.ventanas_abiertas(rein);//notificando que esta ventana se esta aperturando
         rein.setVisible(true);

       }
       if(e.getActionCommand().equalsIgnoreCase("DOCUMENTOS")){
         docu = new documentacion();
         docu.setName("DOCUMENTOS");//colocando nombre a la ventana
         docu.getPeriodo().setText(pri.getPeriodo_inscribir());
         rp.ventanas_abiertas(docu);//notificando que esta ventana se esta aperturando
         docu.setVisible(true);

       }

       if(e.getActionCommand().equalsIgnoreCase("RETIROS")){
         ret = new retiros();
         ret.setName("RETIROS");//colocando nombre a la ventana
         ret.getPeriodo().setText(pri.getPeriodo_inscribir());
         rp.ventanas_abiertas(ret);//notificando que esta ventana se esta aperturando
         ret.setVisible(true);

       }
       
       if(e.getActionCommand().equalsIgnoreCase("periodo academico")){
           
             pri.setPeriodo_inscribir(ima.seleccion_de_periodos("SELECCIONE EL PERIODO ACADEMICO A TRABAJAR", "PERIODO ACADEMICO ACTUAL"));
             rp.cerrando_ventanas();//cerrando todas las ventanas aperturadas
          
       }




    }

    public void windowOpened(WindowEvent e) {
      // pri.getFondo().getGraphics().drawString("que cosaa", 100, 200);
      // pri.getFondo().getGraphics().drawImage(new registro_principal().cargar_imagen("soy andy"),0,0,null);
       
    }

    public void windowClosing(WindowEvent e) {
       System.out.println("cerrando ventana");
        if(ima.confirmacion("DESEA SALIR DE LA APLICACION","CONFIRMACION","confirmacion","png")==0) pri.setDefaultCloseOperation(pri.EXIT_ON_CLOSE);
        else pri.setDefaultCloseOperation(pri.DO_NOTHING_ON_CLOSE);
    }

    public void windowClosed(WindowEvent e) {
       System.out.println("ventana cerrada");
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
