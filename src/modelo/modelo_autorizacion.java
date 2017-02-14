/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.PRINCIPAL;

/**
 *
 * @author AN
 */
public class modelo_autorizacion {
    private encriptacion encriptar;
    private String usuario,guardar,nivel, version, periodo;
    private int cedulas;
    private imagenes ima;



    public modelo_autorizacion() {
        ima = new imagenes();
        encriptar = new encriptacion();
    }

    public int getCedulas() {
        return cedulas;
    }

    public void setCedulas(int cedulas) {
        this.cedulas = cedulas;
    }

  
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGuardar() {
        return guardar;
    }

    public void setGuardar(String guardar) {
        this.guardar = guardar;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }






public String seleccion(){
Object[] opciones={"MODIFICADOR DE NOTAS","PLAN B"};
//Object[] opciones={"MODIFICADOR DE NOTAS"};

  String r= (String) JOptionPane.showInputDialog(new JFrame(),
                                      "POR FAVOR, SELECCIONE EL ENTORNO CON EL QUE DESEA TRABAJAR",
                                      "CONFIRMACION",                                     
                                      JOptionPane.QUESTION_MESSAGE,
                                      ima.imagenes("alumnos_doc", "png"),
                                      opciones,
                                      opciones[0]);

System.out.println("confirmacion: "+r);
return r;

}





    public boolean verificar_usuario(Connection con, int cedula, char [] clave){

            boolean autorizado = false;
            String palabra=null;
            Statement sentencia = null;
            ResultSet resultado = null;

            palabra=encriptar.conversor(String.copyValueOf(clave), "SHA-512");
            

    try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM ce_maracay.privilegios WHERE cedula="+cedula+" AND contraseña='"+palabra+"';");

                if(resultado.next()){//si la persona existe

                    ima.mensaje_informacion("Bienvenido (a),  "+resultado.getString("usuario")+"\nQue tenga un buen dia!!!","AUTORIZADO","feliz","png" );
                    autorizado=true;
                    this.setUsuario(resultado.getString("usuario"));
                    this.setCedulas(cedula);
                    this.setNivel(resultado.getString("nivel_acceso"));
                    System.out.println("probando busqueda de usuario.....................");

                }else{

                    ima.mensaje_informacion("Disculpe pero usted no tiene autorizacion","NO AUTORIZADO","triste","png");
                    autorizado=false;

                }

                sentencia.close();
                resultado.close();
                con.close();



        } catch (SQLException ex) {
            Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
return autorizado;
}

public void accesos(PRINCIPAL p, String nivel){
  System.out.println("NIVEL DE ACCESO "+nivel);


   if(nivel.equalsIgnoreCase("administrador")){
        
        p.getEstudiantes().setEnabled(true);//inscriociones del estudiante
        p.getPlan_b().setEnabled(true);//inscripciones forsoza
        p.getPensum().setEnabled(true);//modificaciones al pensum
        p.getNotas_alumnos().setEnabled(true);//modificacion de las notas del alumno y asignacion de materias (record academico)
        p.getListado_secciones().setEnabled(true);//generacion de los listados de secciones inscritos
        p.getMaterias_inscritas().setEnabled(true);//pasando las materias inscritas al record
        p.getConstancias().setEnabled(true);//generacion de todo tipos de constancias para el estudiante
        p.getCorreos().setEnabled(true);//envio de correos electronicos
        p.getOfertas().setEnabled(true);//modificacion y ofertas de materias
        p.getCambios().setEnabled(true);//cambios de carrera regimen
        p.getConvalidacion().setEnabled(true);//convalidaciones
        p.getReactivacion().setEnabled(true);//suspencion o no suspencion de estudiante
        p.getNuevo_ingreso().setEnabled(true);//inscripcion de nuevos ingresos(NUEVOS)
        p.getAutoridades().setEnabled(true);//autoridades autorizado
        p.getReingresos().setEnabled(true);//habilitando los reingresos
        p.getDocumentos().setEnabled(true);//habilitando la documentacion
        p.getAccesos().setEnabled(true);//habilitando el sistema de claves y usuarios
        p.getRetiros().setEnabled(true);//habilitando los retiros

   }

  if(nivel.equalsIgnoreCase("nivel_1")){

        p.getEstudiantes().setEnabled(true);//inscriociones del estudiante
        p.getPlan_b().setEnabled(true);//inscripciones forsoza
        p.getPensum().setEnabled(true);//modificaciones al pensum
        p.getNotas_alumnos().setEnabled(true);//modificacion de las notas del alumno y asignacion de materias (record academico)
        p.getReactivacion().setEnabled(true);//suspencion o no suspencion de estudiante
        
        p.getConstancias().setEnabled(true);//generacion de todo tipos de constancias para el estudiante
        
        p.getOfertas().setEnabled(true);//modificacion y ofertas de materias
        p.getNuevo_ingreso().setEnabled(true);
        
        p.getReingresos().setEnabled(true);//habilitando los reingresos
        p.getDocumentos().setEnabled(true);//habilitando la documentacion
        p.getRetiros().setEnabled(true);//habilitando los retiros
   }


   if(nivel.equalsIgnoreCase("nivel_2")){

        p.getEstudiantes().setEnabled(true);//inscriociones del estudiante
        p.getPlan_b().setEnabled(true);//inscripciones forsoza
        
        p.getNotas_alumnos().setEnabled(true);//modificacion de las notas del alumno y asignacion de materias (record academico)
        
        p.getOfertas().setEnabled(true);//modificacion y ofertas de materias
        p.getNuevo_ingreso().setEnabled(true);



   }

   if(nivel.equalsIgnoreCase("nivel_3")){

        p.getEstudiantes().setEnabled(true);//inscriociones del estudiante
        
        p.getNotas_alumnos().setEnabled(true);//modificacion de las notas del alumno y asignacion de materias (record academico)
        
        p.getOfertas().setEnabled(true);//modificacion y ofertas de materias
        p.getNuevo_ingreso().setEnabled(true);
   }

    if(nivel.equalsIgnoreCase("nivel_4")){

        p.getEstudiantes().setEnabled(true);//inscriociones del estudiante

       

        p.getOfertas().setEnabled(true);//modificacion y ofertas de materias

         p.getMaterias_inscritas().setEnabled(true);//pasando las materias inscritas al record
            
   }

  if(nivel.equalsIgnoreCase("nivel_5")){

        //p.getEstudiantes().setEnabled(true);//inscripciones del estudiante
   }

  if(nivel.equalsIgnoreCase("estudiante")){

        p.getEstudiantes().setEnabled(true);//inscripciones del estudiante
   }


}











}//fin de la clase
