/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//DESARROLLADO POR EL ING CUSATTI ANDY
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

/**
 *
 * @author AACM
 */
public class registros_bd {


//declaracion de variables
    private String expediente, activo;
    private boolean inscribirse;
    private imagenes ima;

    private String id,
                   cedula,
                   nombres,
                   apellidos,
                   fn_dia,
                   fn_mes,
                   fn_año,
                   sexo,
                   edo_civil,
                   nacionalidad,
                   pais_nac,
                   etnia,
                   telf_per,
                   telf_per_otro,
                   telf_hab,
                   telf_hab_otro,
                   correo,
                   discapacidad,
                   direccion,
                   residencia;
     
    private String ds_parentesco,
                   ds_a,
                   ds_b,
                   ds_c,
                   ds_d,
                   ds_personas,
                   ds_tiempo,
                   ds_penal;

    private String da_condicion,
                   grado,
                   carrera,
                   becado,
                   atleta,
                   tipo_estudiante,
                   fi_mes,
                   fi_año,
                   upi_periodo,                   
                   upi_año,
                   cond_estudiante,
                   modalidad,
                   nucleo,
                   n_rusnieus;



    private String turno;
    
//fin declaracion de variables


//tres constructores, uno para cada pantalla
public registros_bd(String id, String cedula, String nombres, String apellidos, String fn_dia, String fn_mes, String fn_año, String sexo, String edo_civil, String nacionalidad, String pais_nac, String etnia, String telf_per, String telf_per_otro, String telf_hab, String telf_hab_otro, String correo, String discapacidad, String direccion, String residencia) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fn_dia = fn_dia;
        this.fn_mes = fn_mes;
        this.fn_año = fn_año;
        this.sexo = sexo;
        this.edo_civil = edo_civil;
        this.nacionalidad = nacionalidad;
        this.pais_nac = pais_nac;
        this.etnia = etnia;
        this.telf_per = telf_per;
        this.telf_per_otro = telf_per_otro;
        this.telf_hab = telf_hab;
        this.telf_hab_otro = telf_hab_otro;
        this.correo = correo;
        this.discapacidad = discapacidad;
        this.direccion = direccion;
        this.residencia = residencia;
    }

public registros_bd(String cedula,String ds_parentesco, String ds_a, String ds_b, String ds_c, String ds_d, String ds_personas, String ds_tiempo, String ds_penal) {
        this.cedula = cedula;
        this.ds_parentesco = ds_parentesco;
        this.ds_a = ds_a;
        this.ds_b = ds_b;
        this.ds_c = ds_c;
        this.ds_d = ds_d;
        this.ds_personas = ds_personas;
        this.ds_tiempo = ds_tiempo;
        this.ds_penal = ds_penal;
    }
//DESARROLLADO POR EL ING CUSATTI ANDY
  public registros_bd(String cedula, String da_condicion, String grado, String carrera,String turno, String becado, String atleta, String tipo_estudiante, String fi_mes, String fi_año, String upi_periodo, String upi_año, String cond_estudiante, String modalidad, String nucleo, String nro_rusnieus) {
        this.cedula = cedula;
        this.da_condicion = da_condicion;
        this.grado = grado;
        this.carrera = carrera;
        this.turno = turno;
        this.becado = becado;
        this.atleta = atleta;
        this.tipo_estudiante = tipo_estudiante;        
        this.fi_mes = fi_mes;
        this.fi_año = fi_año;
        this.upi_periodo = upi_periodo;
        this.upi_año = upi_año;
        this.cond_estudiante = cond_estudiante;
        this.modalidad = modalidad;
        this.nucleo = nucleo;
        this.n_rusnieus = nro_rusnieus;
    }

  


 public registros_bd() {
       ima = new imagenes();
    }
//fin de los constructores
//todos los setter y getters de las variables inicializadas
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAtleta() {
        return atleta;
    }

    public void setAtleta(String atleta) {
        this.atleta = atleta;
    }

    public String getBecado() {
        return becado;
    }

    public void setBecado(String becado) {
        this.becado = becado;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getDs_a() {
        return ds_a;
    }

    public void setDs_a(String ds_a) {
        this.ds_a = ds_a;
    }

    public String getDs_b() {
        return ds_b;
    }

    public void setDs_b(String ds_b) {
        this.ds_b = ds_b;
    }

    public String getDs_c() {
        return ds_c;
    }

    public void setDs_c(String ds_c) {
        this.ds_c = ds_c;
    }

    public String getDs_d() {
        return ds_d;
    }

    public void setDs_d(String ds_d) {
        this.ds_d = ds_d;
    }

    public String getDs_parentesco() {
        return ds_parentesco;
    }

    public void setDs_parentesco(String ds_parentesco) {
        this.ds_parentesco = ds_parentesco;
    }

    public String getDs_penal() {
        return ds_penal;
    }

    public void setDs_penal(String ds_penal) {
        this.ds_penal = ds_penal;
    }

    public String getDs_personas() {
        return ds_personas;
    }

    public void setDs_personas(String ds_personas) {
        this.ds_personas = ds_personas;
    }

    public String getDs_tiempo() {
        return ds_tiempo;
    }

    public void setDs_tiempo(String ds_tiempo) {
        this.ds_tiempo = ds_tiempo;
    }

    public String getEdo_civil() {
        return edo_civil;
    }
//DESARROLLADO POR EL ING CUSATTI ANDY
    public void setEdo_civil(String edo_civil) {
        this.edo_civil = edo_civil;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public String getFi_año() {
        return fi_año;
    }

    public void setFi_año(String fi_año) {
        this.fi_año = fi_año;
    }
   

    public String getFi_mes() {
        return fi_mes;
    }

    public void setFi_mes(String fi_mes) {
        this.fi_mes = fi_mes;
    }

    public String getFn_año() {
        return fn_año;
    }

    public void setFn_año(String fn_año) {
        this.fn_año = fn_año;
    }

    public String getFn_dia() {
        return fn_dia;
    }

    public void setFn_dia(String fn_dia) {
        this.fn_dia = fn_dia;
    }

    public String getFn_mes() {
        return fn_mes;
    }

    public void setFn_mes(String fn_mes) {
        this.fn_mes = fn_mes;
    }

    public String getDa_condicion() {
        return da_condicion;
    }

    public void setDa_condicion(String da_condicion) {
        this.da_condicion = da_condicion;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPais_nac() {
        return pais_nac;
    }

    public void setPais_nac(String pais_nac) {
        this.pais_nac = pais_nac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelf_hab() {
        return telf_hab;
    }

    public void setTelf_hab(String telf_hab) {
        this.telf_hab = telf_hab;
    }

    public String getTelf_hab_otro() {
        return telf_hab_otro;
    }

    public void setTelf_hab_otro(String telf_hab_otro) {
        this.telf_hab_otro = telf_hab_otro;
    }

    public String getTelf_per() {
        return telf_per;
    }

    public void setTelf_per(String telf_per) {
        this.telf_per = telf_per;
    }

    public String getTelf_per_otro() {
        return telf_per_otro;
    }

    public void setTelf_per_otro(String telf_per_otro) {
        this.telf_per_otro = telf_per_otro;
    }

    public String getTipo_estudiante() {
        return tipo_estudiante;
    }
//DESARROLLADO POR EL ING CUSATTI ANDY
    public void setTipo_estudiante(String tipo_estudiante) {
        this.tipo_estudiante = tipo_estudiante;
    }

    public String getUpi_periodo() {
        return upi_periodo;
    }

    public void setUpi_periodo(String upi_periodo) {
        this.upi_periodo = upi_periodo;
    }

    public String getUpi_año() {
        return upi_año;
    }

    public void setUpi_año(String upi_año) {
        this.upi_año = upi_año;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCond_estudiante() {
        return cond_estudiante;
    }

    public void setCond_estudiante(String cond_estudiante) {
        this.cond_estudiante = cond_estudiante;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public boolean isInscribirse() {
        return inscribirse;
    }

    public void setInscribirse(boolean inscribirse) {
        this.inscribirse = inscribirse;
    }

    public String getNucleo() {
        return nucleo;
    }

    public void setNucleo(String nucleo) {
        this.nucleo = nucleo;
    }

    public String getN_rusnieus() {
        return n_rusnieus;
    }

    public void setN_rusnieus(String n_rusnieus) {
        this.n_rusnieus = n_rusnieus;
    }


    




 ImageIcon imagen(String nombre){//inicio codigo para invocar imagenes

    ImageIcon dibujo = new javax.swing.ImageIcon(getClass().getResource("/actualizacion_de_datos/imagenes/"+nombre+".png"));

  return dibujo  ;
}//fin codigo para importar imagenes

 



//fin de los setters y getters de las variables



    
//desarrollo de los modulos para guardar la informacion en las base de datos

public void crear_bd(Connection con){
    Statement sentencia=null;
  try{

    sentencia=con.createStatement();
    sentencia.executeUpdate("CREATE DATABASE control_de_estudio");
    sentencia.close();

    sentencia=con.createStatement();
    sentencia.executeUpdate("CREATE TABLE control_de_estudio.fotos(cedula VARCHAR(200) NOT NULL, foto LONGBLOB, PRIMARY KEY (cedula));");
    sentencia.close();
//DESARROLLADO POR EL ING CUSATTI ANDY
    sentencia=con.createStatement();
    sentencia.executeUpdate("CREATE TABLE control_de_estudio.datos_personales"+
                                                                               "(id VARCHAR(200),"+
                                                                               "cedula VARCHAR(200) NOT NULL,"+
                                                                               "nombres VARCHAR(200),"+
                                                                               "apellidos VARCHAR(200),"+
                                                                               "f_nacimiento VARCHAR(200),"+
                                                                               "sexo VARCHAR(200),"+
                                                                               "edo_civil VARCHAR(200),"+
                                                                               "nacionalidad VARCHAR(200),"+
                                                                               "pais_nac VARCHAR(200),"+
                                                                               "etnia VARCHAR(200),"+
                                                                               "telf_per VARCHAR(200),"+
                                                                               "telf_per_otro VARCHAR(200),"+
                                                                               "telf_hab VARCHAR(200),"+
                                                                               "telf_hab_otro VARCHAR(200),"+
                                                                               "correo VARCHAR(200),"+
                                                                               "discapacidad VARCHAR(200),"+
                                                                               "direccion TEXT,"+
                                                                               "residencia VARCHAR(200),"+
                                                                               "PRIMARY KEY (cedula));");
    sentencia.close();

    sentencia=con.createStatement();
    sentencia.executeUpdate("CREATE TABLE control_de_estudio.datos_socioeconomicos"+
                                                                               "(cedula VARCHAR(200) NOT NULL," +
                                                                               "ds_parentesco VARCHAR(200),"+
                                                                               "ds_a TEXT,"+
                                                                               "ds_b TEXT,"+
                                                                               "ds_c TEXT,"+
                                                                               "ds_d TEXT,"+
                                                                               "ds_personas VARCHAR(200),"+
                                                                               "ds_tiempo VARCHAR(200),"+
                                                                               "ds_penal VARCHAR(200)," +
                                                                               "PRIMARY KEY(cedula));");
                                                                              
     sentencia.close();

     sentencia=con.createStatement();
     sentencia.executeUpdate("CREATE TABLE control_de_estudio.datos_academicos"+
                                                                               "(cedula VARCHAR(200) NOT NULL," +
                                                                               "condicion VARCHAR(200),"+
                                                                               "grado VARCHAR(200),"+
                                                                               "carrera VARCHAR(200),"+
                                                                               "turno VARCHAR(200),"+
                                                                               "becado VARCHAR(200),"+
                                                                               "atleta VARCHAR(200),"+
                                                                               "tipo_estudiante VARCHAR(200),"+                                                                               
                                                                               "f_inscripcion VARCHAR(200),"+
                                                                               "upi_periodo VARCHAR(200),"+                                                                              
                                                                               "cond_estudiante VARCHAR(200),"+
                                                                               "modalidad VARCHAR(200),"+
                                                                               "PRIMARY KEY(cedula));");
                                                                               
     sentencia.close();
     con.close();

     
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
                
            }
  }//fin del metodo crear_bd
//DESARROLLADO POR EL ING CUSATTI ANDY

public int buscar_alumno(Connection con, String ci) {
int control = 0;

        Statement sentencia = null;
        ResultSet buscar = null;

        try {

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_personales WHERE cedula='"+ ci + "';");
                    if(buscar.next()){//si existe algun registro asociado a la cedula a buscar entonces realiza las operaciones

                     System.out.println(buscar.getString("cedula"));

                     ima.mensaje_informacion("YA USTED SE ENCUENTRA REGISTRADO EN EL SISTEMA\n BUEN DIA.... GRACIAS!!!",
                                             "NOTIFICACION","registrado","png");
                   

                    }else{//si no la encuentras, entonces muestra un mensaje que no existe el alumno

                     control=1;

                     ima.mensaje_informacion("ESTE ALUMNO NO SE ENCUENTRA REGISTRADO AUN \n",
                                             "POR FAVOR LLENE EL SIGUIENTE FORMULARIO",
                                             "precaucion", "png");

                       
                    }
            sentencia.close();
            buscar.close();
            
           
        }catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
                
               /*control=1;

            JOptionPane.showMessageDialog(
            new JFrame(),
            "ESTE ALUMNO NO SE ENCUENTRA REGISTRADO AUN \n"+
            "POR FAVOR LLENE EL SIGUIENTE FORMULARIO",
            "ADVERTENCIA",
            JOptionPane.ERROR_MESSAGE,this.imagen("precaucion"));*/
            }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

return control;
    }

public int buscar_nuevo_ingreso(Connection con, String ci, String periodo) {
int control = 0;
periodo=periodo.replace("-", "/");
periodo="1/2012";
System.out.println("PERIODO: "+periodo);

        Statement sentencia = null;
        ResultSet buscar = null;

        try {

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='"+ ci + "' AND f_inscripcion='"+periodo+"';");

                    if(buscar.next()){//si existe algun registro asociado a la cedula a buscar entonces realiza las operaciones
                     System.out.println(buscar.getString("cedula"));
                     this.setCarrera(buscar.getString("carrera"));//almacenando la carrera en la que esta
                     this.setTurno(buscar.getString("turno"));//almacenando el turno del estudiante
                     control=0; //existe
                     ima.mensaje_informacion("ESTE ESTUDIANTE ES NUEVO INGRESO \n",
                                             "POR FAVOR LLENE EL SIGUIENTE FORMULARIO",
                                             "precaucion", "png");
                    }else{//si no la encuentras, entonces muestra un mensaje que no existe el alumno
                     control=1; //no existe
                    }
            sentencia.close();
            buscar.close();


        }catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);

            }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
           }

return control;
    }




public void ingreso_personal(Connection con, registros_bd regbd){
         Statement sentencia = null;
    try {

            sentencia = con.createStatement();
            sentencia.executeUpdate("INSERT INTO control_de_estudio.datos_personales VALUES ('"+regbd.getId()+"','"+regbd.getCedula()+"','"+regbd.getNombres().toUpperCase()+"','"+regbd.getApellidos().toUpperCase()+"','"+regbd.getFn_dia()+"/"+regbd.getFn_mes()+"/"+regbd.getFn_año()+"','"+regbd.getSexo()+"','"+regbd.getEdo_civil()+"','"+regbd.getNacionalidad()+"','"+regbd.getPais_nac()+"','"+regbd.getEtnia()+"','"+regbd.getTelf_per()+"','"+regbd.getTelf_per_otro()+"','"+regbd.getTelf_hab()+"','"+regbd.getTelf_hab_otro()+"','"+regbd.getCorreo()+"','"+regbd.getDiscapacidad()+"','"+regbd.getDireccion()+"','"+regbd.getResidencia()+"');");
            sentencia.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR INGRESO DE DATOS PERSONALES\n"+ex, "DATOS PERSONALES", "error", "png");
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}


public void actualizacion_datos_personales(Connection con, registros_bd regbd ){
     Statement sentencia = null;
    try {

            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.datos_personales SET id='"+regbd.getId()+"', nombres='"+regbd.getNombres().toUpperCase()+"', apellidos='"+regbd.getApellidos().toUpperCase()+"',f_nacimiento='"+regbd.getFn_dia()+"/"+regbd.getFn_mes()+"/"+regbd.getFn_año()+"', sexo='"+regbd.getSexo()+"', edo_civil='"+regbd.getEdo_civil()+"', nacionalidad='"+regbd.getNacionalidad()+"', pais_nac='"+regbd.getPais_nac()+"', etnia='"+regbd.getEtnia()+"', telf_per='"+regbd.getTelf_per()+"', telf_per_otro='"+regbd.getTelf_per_otro()+"', telf_hab='"+regbd.getTelf_hab()+"', telf_hab_otro='"+regbd.getTelf_hab_otro()+"', correo='"+regbd.getCorreo()+"', discapacidad='"+regbd.getDiscapacidad()+"', direccion='"+regbd.getDireccion()+"', residencia='"+regbd.getResidencia()+"' WHERE cedula='"+regbd.getCedula()+"';");
            sentencia.close();
           
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ACTUALIZACION DE DATOS PERSONALES\n"+ex, "DATOS PERSONALES", "error", "png");
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


}

public void ingreso_socioeconomico(Connection con, registros_bd regbd, String a, String b, String c, String d){
    Statement sentencia = null;
    try {

            sentencia = con.createStatement();
            sentencia.executeUpdate("INSERT INTO control_de_estudio.datos_socioeconomicos VALUES ('"+regbd.getCedula()+"','"+regbd.getDs_parentesco()+"','"+a+"','"+b+"','"+c+"','"+d+"','"+regbd.getDs_personas()+"','"+regbd.getDs_tiempo()+"','"+regbd.getDs_penal()+"');");
            sentencia.close();
           
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR INGRESO DE DATOS SOCIOECONOMICOS\n"+ex, "DATOS SOCIOECONOMICOS", "error", "png");
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


  
//DESARROLLADO POR EL ING CUSATTI ANDY
}

public void actualizacion_datos_socioeconomicos(Connection con, registros_bd regbd, String a, String b, String c, String d){
    Statement sentencia = null;
    try {

            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.datos_socioeconomicos SET ds_parentesco='"+regbd.getDs_parentesco()+"', ds_a='"+a+"', ds_b='"+b+"', ds_c='"+c+"', ds_d='"+d+"', ds_personas='"+regbd.getDs_personas()+"', ds_tiempo='"+regbd.getDs_tiempo()+"', ds_penal='"+regbd.getDs_penal()+"' WHERE cedula='"+regbd.getCedula()+"';");
            sentencia.close();
            con.close();
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ACTUALIZACION DATOS SOCIOECONOMICOS\n"+ex, "DATOS SOCIOECONOMICOS", "error", "png");
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


//DESARROLLADO POR EL ING CUSATTI ANDY
}

public void ingreso_academico(Connection con, registros_bd regbd){
 Statement sentencia = null;
    try {

            sentencia = con.createStatement();
            sentencia.executeUpdate("INSERT INTO control_de_estudio.datos_academicos VALUES ('"+regbd.getCedula()+"','"+regbd.getDa_condicion()+"','"+regbd.getGrado().toUpperCase()+"','"+regbd.getCarrera()+"','"+regbd.getTurno()+"','"+regbd.getBecado()+"','"+regbd.getAtleta().toUpperCase()+"','"+regbd.getTipo_estudiante()+"','"+regbd.getFi_mes()+"/"+regbd.getFi_año()+"','"+regbd.getUpi_periodo()+"/"+regbd.getUpi_año()+"','"+regbd.getCond_estudiante()+"','"+regbd.getModalidad()+"','"+regbd.getNucleo()+"',0,'"+regbd.getN_rusnieus()+"');");
            sentencia.close();
           
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR INGRESO DE DATOS ACADEMICOS\n"+ex, "DATOS ACADEMICOS", "error", "png");
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                                                                                                                                                                                                                
}

public void actualizacion_datos_academicos(Connection con, registros_bd regbd){
 Statement sentencia = null;
    try {
     
            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.datos_academicos SET condicion='"+regbd.getDa_condicion()+"', grado='"+regbd.getGrado().toUpperCase()+"', carrera='"+regbd.getCarrera()+"', turno='"+regbd.getTurno()+"', becado='"+regbd.getBecado()+"', atleta='"+regbd.getAtleta().toUpperCase()+"', tipo_estudiante='"+regbd.getTipo_estudiante()+"', f_inscripcion='"+regbd.getFi_mes()+"/"+regbd.getFi_año()+"', upi_periodo='"+regbd.getUpi_periodo()+"/"+regbd.getUpi_año()+"', cond_estudiante='"+regbd.getCond_estudiante()+"', modalidad='"+regbd.getModalidad()+"', nucleo='"+regbd.getNucleo()+"', pensum=0 , rusnieus='"+regbd.getN_rusnieus()+"' WHERE cedula='"+regbd.getCedula()+"' ;");
            sentencia.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR MODIFICACION DE DATOS ACADEMICOS\n"+ex, "DATOS ACADEMICOS", "error", "png");
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

}

public void eliminar_registro(Connection con, String cedula){
       Statement sentencia = null;
       int x;
    try {

            sentencia = con.createStatement();
            x=sentencia.executeUpdate("DELETE FROM control_de_estudio.datos_personales WHERE cedula='" + cedula + "';");
            sentencia.close();
if (x == 1) {

            ima.mensaje_informacion("ELIMINACION EXITOSA !!!", "NOTIFICACION", "eliminacion","png");

            }else{

            ima.mensaje_informacion("ESTA PERSONA NO EXISTE,\nMEJOR CONSULTE PARA COMPROBAR\nO REVISE QUE LA CEDULA ESTE CORRECTA!!!", "ERROR DE REGISTRO", "no_existe", "png");
             
            }
            System.out.println(x);
            sentencia = con.createStatement();
            sentencia.executeUpdate("DELETE FROM control_de_estudio.datos_socioeconomicos WHERE cedula='" + cedula + "';");
            sentencia.close();
            sentencia = con.createStatement();
            sentencia.executeUpdate("DELETE FROM control_de_estudio.datos_academicos WHERE cedula='" + cedula + "';");
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);

            ima.mensaje_informacion(ex.getMessage()+"\nERROR EN EL BORRADO DE REGISTRO", "ERROR", "error", "png");

        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//DESARROLLADO POR EL ING CUSATTI ANDY
}





public boolean verificador_numerico(String numero, boolean activador){
    boolean status=false;

        if (activador){//solo se activa cuando se quiere usar puntualmente
           try{
            int x=Integer.parseInt(numero);

                if(x>=0 && x<=20){//si esta entre 0 y 20 no pasa nada
                }else{//sino no puede ser
                    status=true;
                    ima.mensaje_informacion("DISCULPE, PERO\nLAS NOTAS DEBEN ESTAR ENTRE 0 y 20 PUNTOS.", "EXCEDENCIA DE NOTAS", "precaucion", "png");
                }



            }catch(NumberFormatException nfe){
                ima.mensaje_informacion("SOLO DEBEN SER NUMEROS.","ERROR", "error", "png");
                status=true;
            }
        }

return status;
}

public boolean verificador_inasistencia(String numero, boolean activador){
    boolean status=false;

        if (activador){//solo se activa cuando se quiere usar puntualmente
           try{
            int x=Integer.parseInt(numero);

                if(x>=0 && x<=100){//si esta entre 0 y 20 no pasa nada
                }else{//sino no puede ser
                    status=true;
                    ima.mensaje_informacion("DISCULPER, PERO\nLA INASISTENCIA DEBE ESTAR ENTRE 0 y 100 porciento.", "EXCEDENCIA DE PORCENTAJE", "precaucion", "png");
                    

                }

            }catch(NumberFormatException nfe){
                ima.mensaje_informacion("SOLO DEBEN SER NUMEROS.","ERROR", "error", "png");
                status=true;
            }
        }

return status;
}




/**Este metodo se encarga de verificar que la entrada no posean caracteres especiales para evitar inyeccion de codigos */
public boolean verificador(String numero){
boolean status=false;
//System.out.println("leyendo ingreso: "+numero);

    if(numero.contains(".")) status=true;
    if(numero.contains("-")) status=true;
    if(numero.contains("_")) status=true;
    if(numero.contains("(")) status=true;
    if(numero.contains(")")) status=true;
    if(numero.contains("*")) status=true;

    if(numero.contains(":")) status=true;
    if(numero.contains(";")) status=true;
    if(numero.contains(",")) status=true;
    if(numero.contains("?")) status=true;
    if(numero.contains("¿")) status=true;
    if(numero.contains("\"")) status=true;

    if(numero.contains("\\")) status=true;
    if(numero.contains("!")) status=true;
    if(numero.contains("#")) status=true;
    if(numero.contains("$")) status=true;
    if(numero.contains("%")) status=true;
    if(numero.contains("&")) status=true;

    if(numero.contains("/")) status=true;
    if(numero.contains("=")) status=true;
    if(numero.contains("¡")) status=true;
    if(numero.contains("|")) status=true;
    if(numero.contains("°")) status=true;
    if(numero.contains("¬")) status=true;




   
   
/*
   try{
    int x=Integer.parseInt(numero);
  
    }catch(NumberFormatException nfe){
        this.mensaje_general("SOLO DEBEN SER NUMEROS", "ERROR CAMPO CEDULA");
        status=true;
    }
*/
  return  status;
}





public void aviso(String identificacion){

        ima.mensaje_informacion(identificacion+":\n\n"+"SUS DATOS FUERON ENVIADOS Y ALMACENADOS CON EXITO...GRACIAS!!!", "ADVERTENCIA", "almacenando","png");

}

/** Metodo para conocer si el alumno debe algun expediente o simplemente se encuentra inactivo para no
 permitirle la inscripcion y deba pasar a solventar su problema, tiene como parametro la conecion a 
 la base de datos y la cedula del alumno para realizar el analisis de su condicion*/

public void analisis(Connection con, String cedula){
       Statement sentencia = null;
       ResultSet buscar = null;


        try {
            
            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio.todos_alumnos WHERE cedula='" + cedula + "';");
            
           if(buscar.next()){//si existe algun registro
                this.setActivo(buscar.getString("activo"));
                    if(this.getActivo().equalsIgnoreCase("SI")) this.setInscribirse(true); else this.setInscribirse(false);
               // this.setExpediente(buscar.getString("expediente"));
                
           }else{//sino colocar que no existe en la lista
                this.setActivo("no existe");
               // this.setExpediente("no existe");
                this.setInscribirse(true);}


            sentencia.close();
            buscar.close();
            con.close();


        } catch (SQLException ex) {
            
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);

        }

       


}//fin del metodo analsis



public boolean clave_estudiante(Connection con, String cedula, char [] clave ){
       Statement sentencia = null;
       ResultSet resultado = null;
            
       boolean autorizado = false;
       String texto="";
imagenes ima = new imagenes();


       for(int i=0; i<clave.length; i++){//recorriendo todos los caracteres que contiene la clave
           texto=texto.concat(String.valueOf(clave[i]) );
       }//fin recorrido
//System.out.println("contraseña es: "+texto);

    try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.claves WHERE etapa1='"+cedula+"' AND etapa2='"+texto+"';");
            if (resultado.next()) {//si se encontro coincidencia se revisa el acuse
                if(resultado.getBoolean("acuse")==false){//revisando si se puede inscribir
                    autorizado = true;
                    System.out.println("AUTORIZADO");
                }else{//si existe un acuse verdadero es que ya se inscribio y no se puede inscribir nuevamente
                    autorizado = false;
                    System.out.println("NO AUTORIZADO, ya se inscribio");
                    ima.mensaje_informacion("USUARIO YA INSCRITO. \nYa usted se encuentra inscrito, dirijase a CONTROL DE ESTUDIO", "SEGURIDAD", "alto", "png");
                }
            } else {//no se encontro las condiciones de busqueda entre cedula y clave
                autorizado = false;
                
                ima.mensaje_informacion("USUARIO NO AUTORIZADO. \nVerifique su clave y cedula esten correctamente escrito", "SEGURIDAD", "alto", "png");
            }

        } catch (SQLException ex) {
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autorizado;
}

/**Este metodo es para colocar que el alumno se inscribio, por lo que se genera un acuse de inscripcion y evitar que se inscriba otra
 vez sin autorizacion*/
public void acuse_inscripcion(Connection con, String cedula){

    Statement sentencia=null;
        try {
            sentencia = con.createStatement();

            sentencia.executeUpdate("UPDATE control_de_estudio.claves SET acuse=1 WHERE ETAPA1='"+cedula+"';");

        } catch (SQLException ex) {
            Logger.getLogger(registros_bd.class.getName()).log(Level.SEVERE, null, ex);
        }

}










}//fin de la clase




