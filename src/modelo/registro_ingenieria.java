/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author AURORA
 */
public class registro_ingenieria {

  //  private LinkedList <String> codigo= new LinkedList <String> ();
  //  private LinkedList <String> materia= new LinkedList <String> ();
  //  private LinkedList <String> definitiva= new LinkedList <String> ();
  //  private LinkedList <String> condicion= new LinkedList <String> ();
  //  private LinkedList <String> periodo= new LinkedList <String> ();
 //   private LinkedList <String> reparacion = new LinkedList <String> ();
    private LinkedList<String> record = new LinkedList<>();

    private String cedula, estudiante, carrera, comp_per,turnos,nucleo_extension, vigencia, fecha_ingreso;
    private int control,reconocimiento;
    private BufferedImage foto;

    private imagenes ima;

    public registro_ingenieria() {
        ima = new imagenes();
    }



    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getComp_per() {
        return comp_per;
    }

    public void setComp_per(String comp_per) {
        this.comp_per = comp_per;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

  

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getReconocimiento() {
        return reconocimiento;
    }

    public void setReconocimiento(int reconocimiento) {
        this.reconocimiento = reconocimiento;
    }

    public String getTurnos() {
        return turnos;
    }

    public void setTurnos(String turnos) {
        this.turnos = turnos;
    }

    public String getNucleo_extension() {
        return nucleo_extension;
    }

    public void setNucleo_extension(String nucleo_extension) {
        this.nucleo_extension = nucleo_extension;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public LinkedList<String> getRecord() {
        return record;
    }

    public void setRecord(LinkedList<String> record) {
        this.record = record;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public BufferedImage getFoto() {
        return foto;
    }

    public void setFoto(BufferedImage foto) {
        this.foto = foto;
    }





  

public void fotos(){


}






public void verificar_alumno(Connection con, String ci) {
this.setControl(0);
int contador = 0;


        Statement sentencia = null;
        ResultSet buscar = null;

        try {

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_personales WHERE cedula='"+ ci + "';");
            buscar.next();

            this.setCedula(buscar.getString("cedula"));
            this.setEstudiante(buscar.getString("apellidos")+", "+buscar.getString("nombres"));
            
            sentencia.close();
            buscar.close();
            
            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='"+ ci + "';");
            buscar.next();
            
            this.setCarrera(buscar.getString("carrera"));
            this.setTurnos(buscar.getString("turno"));
            this.setNucleo_extension(buscar.getString("nucleo"));
            this.setVigencia(String.valueOf(buscar.getInt("pensum")));
            this.setFecha_ingreso(buscar.getString("f_inscripcion"));

            sentencia.close();
            buscar.close();

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT foto FROM carnets.fotos WHERE cedula='"+ ci + "';");
            BufferedImage bufferi = new BufferedImage(192,240, BufferedImage.TYPE_INT_RGB);

            if(buscar.next()){
                try {
                    Blob imagenes=null;
                    imagenes = buscar.getBlob("foto");
                    Graphics2D bi = (Graphics2D) bufferi.createGraphics();
                    bi.drawImage(javax.imageio.ImageIO.read(imagenes.getBinaryStream()), 0, 0, null);
                    this.setFoto(bufferi);
                } catch (IOException ex) {
                    System.out.println("ERROR CARGANDO LA FOTO DE LA BASE DE DATOS");
                    Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }else{
                    Graphics2D bi = (Graphics2D) bufferi.createGraphics();
                    bi.drawImage(new javax.swing.ImageIcon(getClass().getResource("/actualizacion_de_datos/imagenes/no_foto.png")).getImage(),0,0,null);
                    //bi.drawString("NO EXISTE ESTE ANIMAL",0,120);
                    this.setFoto(bufferi);
            }


            
           
            sentencia.close();
            buscar.close();
        



        }catch (SQLException ex) {
             this.setControl(1);
                Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
              ima.mensaje_informacion("ESTE ALUMNO NO SE ENCUENTRA REGISTRADO AUN \n", "ADVERTENCIA", "no_existe", "png");               
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



System.out.println("control: "+this.getControl());
System.out.println("metodo verificar alumno");
//return control;
    }

public void buscar_materiasx(Connection con, String ci,String tabla) {
               /*  this.getCodigo().clear();//limpiando todos los LinkedListes antes de volver a utilizar
                 this.getMateria().clear();
                 this.getDefinitiva().clear();
                 this.getReparacion().clear();
                 this.getCondicion().clear();
                 this.getPeriodo().clear();*/

System.out.println("TABLA: "+tabla+" - CEDULA: "+ci);

    
        this.setReconocimiento(0);
        
        Statement sentencia = null;
        ResultSet buscar = null;

        try {

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio."+tabla+" WHERE CEDULA='"+ ci + "' ORDER BY TERMINO;");
        
           

            while(buscar.next()){
               
            //   System.out.println(buscar.getString("CEDULA"));
                //cargando todas las materias del alumno en memoria
            /*     this.getCodigo().add(buscar.getString("CODMAT"));
                 this.getMateria().add(buscar.getString("CODMAT"));
                 this.getDefinitiva().add(String.valueOf(buscar.getInt("NOTDEF")));
                 this.getReparacion().add(String.valueOf(buscar.getInt("NOTREP")));
                 this.getCondicion().add(buscar.getString("CONDIC"));
                 this.getPeriodo().add(buscar.getString("PERACA"));*/


            }

            sentencia.close();
            buscar.close();
            con.close();

            
        }catch (SQLException ex) {
                this.setReconocimiento(1);
                Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("metodo buscar materia");
                    
                                           }
System.out.println("RECONOCIMIENTO "+this.getReconocimiento());
//return control;
    }


public void buscar_materias_alternativo(Connection con, String ci,String tabla) {
                 this.getRecord().clear();//limpiando el LinkedList

System.out.println("TABLA: "+tabla+" - CEDULA: "+ci);


        this.setReconocimiento(0);

        Statement sentencia = null;
        ResultSet buscar = null;

        try {

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio."+tabla+" WHERE CEDULA='"+ ci + "' ORDER BY TERMINO;");



            while(buscar.next()){

            //   System.out.println(buscar.getString("CEDULA"));
                //cargando todas las materias del alumno en memoria
                 this.getRecord().add(buscar.getString("CODMAT"));   //0
                 this.getRecord().add(buscar.getString("CODMAT"));  //1
                 this.getRecord().add(String.valueOf(buscar.getInt("NOTDEF")));  //2
                 this.getRecord().add(String.valueOf(buscar.getInt("NOTREP")));  //3
                 this.getRecord().add(buscar.getString("CONDIC")); //4
                 this.getRecord().add(buscar.getString("PERACA")); //5
                 
                 this.getRecord().add(String.valueOf(buscar.getInt("DEFREP")));  //6
                 this.getRecord().add(String.valueOf(buscar.getInt("PORINA")));  //7
                 this.getRecord().add(buscar.getString("CODESP")); //8

                 this.getRecord().add(String.valueOf(buscar.getInt("NOTLAB"))); //9
                 this.getRecord().add(String.valueOf(buscar.getInt("TERMINO"))); //10

            }

            sentencia.close();
            buscar.close();
            con.close();


        }catch (SQLException ex) {
                this.setReconocimiento(1);
                Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("metodo buscar materia alternativo ");

                                           }
System.out.println("RECONOCIMIENTO "+this.getReconocimiento());
//return control;
    }



public DefaultTableModel academico(){

    DefaultTableModel desempeño_academico = new DefaultTableModel(
    new Object [][] {
    },
    new String [] {
        "SEMESTRE","CODIGO", "MATERIA", "DEFINITIVA","REPARACION" ,"CONDICION", "PERIODO"
    }
) {
    boolean[] canEdit = new boolean [] {
        false,false, false, false, false, false,false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
};

return desempeño_academico;
}


public DefaultTableModel inf_horarios(){

 
    
    
    /*
    DefaultTableModel horario = new DefaultTableModel(
    new Object [][] {
        {"07:30-08:15 am", "", null, null, null, null, null, null},
        {"08:20-09:05 am", null, null, null, null, null, null, null},
        {"09:10-09:55 am", "", null, null, null, null, null, null},
        {"10:00-10:45 am", null, null, null, null, null, null, null},
        {"10:50-11:35 am", null, "", null, "", null, null, null},
        {"11:40-12:25 am", null, null, null, null, null, null, null},
        {"12:30-01:15 pm", null, "", null, null, null, null, null},
        {"01:20-02:05 pm", null, null, null, null, null, null, null},
        {"02:10-02:55 pm", null, null, null, null, null, null, null},
        {"03:00-03:45 pm", null, null, null, null, null, null, null},
        {"03:50-04:35 pm", null, null, null, null, null, null, null},
        {"04:40-05:25 pm", null, null, null, null, null, null, null},
        {"05:30-06:15 pm", null, null, null, null, null, null, null},
        {"06:15-07:00 pm", null, null, null, null, null, null, null},
        {"07:00-07:45 pm", null, null, null, null, null, null, null},
        {"07:45-08:30 pm", null, null, null, null, null, null, null},
        {"08:30-09:15 pm", null, null, null, null, null, null, null},
        {"09:15-10:00 pm", null, null, null, null, null, null, null},
        {"10:00-10:45 pm", null, null, null, null, null, null, null},
        {"10:45-11:30 pm", null, null, null, null, null, null, null},
        {"11:30-11:59 pm", null, null, null, null, null, null, null}
    },
    new String [] {
        "HORAS", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"
    }
) {
    boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false, false, false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
};*/
    DefaultTableModel horario = null;
    modelo_configuraciones mc = new modelo_configuraciones();
    horario = mc.horas_horario(new conexion_base_de_datos().getConexion());
   
return horario;

}








}//fin de la clase
