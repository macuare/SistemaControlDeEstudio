/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.nuevos;

/**
 *
 * @author AN
 */
public class modelo_nuevos {

        private imagenes ima;
        private nuevos ns;

        private LinkedList<String> materias = new LinkedList<>();
        private LinkedList<String> informacion = new LinkedList<>();
        private LinkedList<String> usadas = new LinkedList<>();
      
        private LinkedList<String> alumnado = new LinkedList<>();

        private String cedulas,seccion,periodo,estudiante,semestre,condicion,carrera,codigo,materia,uc,dia,hora,docente,dia_inscripcion, turno,resumen;
        private boolean autorizar=false;
        private boolean esta_inscrito=false;
    
    public modelo_nuevos() {
        ima = new imagenes();
    }

    public modelo_nuevos(nuevos n){
        this.ns = n;
        ima = new imagenes();
        this.carga_previa(new conexion_base_de_datos().getConexion());
    }


    /**Organizacion del registro que acumula las materias del pensum de interes <br>Codigo = 0<br> Semestre = 1<br> Asignatura = 2<br> Uc = 3 */
    public LinkedList<String> getMaterias() {
        return materias;
    }

    public void setPensum(LinkedList<String> materias) {
        this.materias = materias;
    }

    public String getCedulas() {
        return cedulas;
    }

    public void setCedulas(String cedulas) {
        this.cedulas = cedulas;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDia_inscripcion() {
        return dia_inscripcion;
    }

    public void setDia_inscripcion(String dia_inscripcion) {
        this.dia_inscripcion = dia_inscripcion;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }
        /**DISTRIBUCION DEL LinkedList: <br>cedula=0<br>estudiante=1<br>carrera=2<br>semestre=3<br>codigo=4<br>materia=5<br>uc=6<br>dia=7<br>hora=8<br>seccion=9<br>periodo=10<br>dia_inscripcion=11<br>docente=12<br>condicion=13 */
    public LinkedList<String> getInformacion() {
        return informacion;
    }

    public void setInformacion(LinkedList<String> informacion) {
        this.informacion = informacion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public boolean isAutorizar() {
        return autorizar;
    }

    public void setAutorizar(boolean autorizar) {
        this.autorizar = autorizar;
    }

    public LinkedList<String> getUsadas() {
        return usadas;
    }

    public void setUsadas(LinkedList<String> usadas) {
        this.usadas = usadas;
    }

    public boolean isEsta_inscrito() {
        return esta_inscrito;
    }

    public void setEsta_inscrito(boolean esta_inscrito) {
        this.esta_inscrito = esta_inscrito;
    }

    


    



public void exterior(String mensaje){
                this.setCedulas("");
                this.setSeccion("NINGUNA");
                this.setPeriodo("1-2012");
                this.setResumen(mensaje);
}

public void resumenes(String mensaje){
    ns.getResumen().append(mensaje+"\n");
}


public void revision_inscrito(Connection con, String cedula){

            Statement sentencia = null;
            ResultSet resultado = null;
        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE cedula='" + cedula + "';");
            if(resultado.next()){
                this.setEsta_inscrito(true);//se verifica que el estudiante ya fue inscrito
                ima.mensaje_informacion("Este estudiante ya esta inscrito", "INSCRIPCION NUEVO INGRESO","exclamacion" , "png");
            }else{
                this.setEsta_inscrito(false);//el estudiante no esta inscrito porque no tiene materias inscritas               
            }
            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR VERIFICANDO SI EL ESTUDIANTE ESTA INSCRITO EN LA BASE DE DATOS\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}



public void busqueda(Connection con, String cedula){
            Statement sentencia = null;
            ResultSet resultado = null;


        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.nuevo_ingreso WHERE cedula='" + cedula + "';");
            if(resultado.next()){
                this.setCedulas(resultado.getString("cedula"));
                this.setSeccion(resultado.getString("seccion"));
                ns.getSeccion().setText(this.getSeccion());
                this.setAutorizar(true);
            }else{
                this.exterior("ESTUDIANTE: "+cedula+" NO EXISTE..!!!");
                this.resumenes("ESTUDIANTE: "+cedula+" NO EXISTE..!!!\n--------------------------------------------------------------------------------------------------------------------\n");
                this.setAutorizar(false);
                ima.mensaje_informacion("Este estudiante no existe", "INSCRIPCION NUEVO INGRESO","no_existe" , "png");                
            }
            sentencia.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR BUSCANDO SI EL ESTUDIANTE ESTA AUTORIZADO EN LA BASE DE DATOS\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            this.resumenes("ERROR BUSCANDO SI EL ESTUDIANTE ESTA AUTORIZADO EN LA BASE DE DATOS\n"+ex.getMessage());
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

public void estudiante(Connection con){

    Statement sentencia = null;
            ResultSet resultado = null;

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_personales WHERE cedula='" + this.getCedulas() + "';");
            if(resultado.next()){
                this.setEstudiante(resultado.getString("apellidos")+", "+resultado.getString("nombres"));
            }else{
                this.exterior("ESTUDIANTE: "+this.getCedulas()+" NO SE HA ACTUALIZADO..!!!");
                ima.mensaje_informacion("Este estudiante no se ha actualizado aun!!!", "INSCRIPCION NUEVO INGRESO","no_existe" , "png");
                this.resumenes("Este estudiante no se ha actualizado aun!!!");
            }
            sentencia.close();
            
            
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='" + this.getCedulas() + "';");
            if(resultado.next()){
                this.setCarrera(resultado.getString("carrera"));
                this.setTurno(resultado.getString("turno"));
            }else{
                this.exterior("ESTUDIANTE: "+this.getCedulas()+" NO REGISTRO SU CARRERA..!!!");
                this.resumenes("ESTUDIANTE: "+this.getCedulas()+" NO REGISTRO SU CARRERA..!!!");
                ima.mensaje_informacion("Este estudiante no registro su carrera", "INSCRIPCION NUEVO INGRESO","no_existe" , "png");
            }
            sentencia.close();        
                    
            
            

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR BUSCANDO ESTUDIANTE EN LA BASE DE DATOS\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            this.resumenes("ERROR BUSCANDO ESTUDIANTE EN LA BASE DE DATOS\n"+ex.getMessage());
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
}




/**ESTE METODO SE ENCARGA DE ANALIZAR QUE PENSUM SE VA A CARGAR */
public String identificador_carrera(String carrera, String turno){
    String tabla="ninguno";
   System.out.println("Carrera: "+carrera+" - Turno: "+turno);
   
    if(carrera.equalsIgnoreCase("INGENIERIA MECANICA") && turno.equalsIgnoreCase("DIURNO"))tabla="ingenieria_mecanica_2010_d";
    
    if(carrera.equalsIgnoreCase("LIC. CONTADURIA PUBLICA") && turno.equalsIgnoreCase("DIURNO"))tabla="lic_contaduria_publica_2010_d";
    if(carrera.equalsIgnoreCase("LIC. CONTADURIA PUBLICA") && turno.equalsIgnoreCase("NOCTURNO"))tabla="lic_contaduria_publica_2010_n";

    if(carrera.equalsIgnoreCase("LIC. EDUCACION INTEGRAL") && turno.equalsIgnoreCase("DIURNO"))tabla="lic_educacion_integral_2010_d";
    if(carrera.equalsIgnoreCase("LIC. EDUCACION INTEGRAL") && turno.equalsIgnoreCase("NOCTURNO"))tabla="lic_educacion_integral_2010_n";

    if(carrera.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turno.equalsIgnoreCase("DIURNO"))tabla="lic_economia_social_2010_d";
    if(carrera.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turno.equalsIgnoreCase("NOCTURNO"))tabla="lic_economia_social_2010_n";

    if(carrera.equalsIgnoreCase("TSU EN TURISMO") && turno.equalsIgnoreCase("DIURNO"))tabla="tsu_turismo_2010_d";

    //if(carrera.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turno.equalsIgnoreCase("DIURNO"))tabla="ingenieria_telecomunicaciones_2010_d";
    
            if(carrera.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turno.equalsIgnoreCase("DIURNO")) tabla="ingenieria_aeronautico_2010_d";
            if(carrera.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turno.equalsIgnoreCase("NOCTURNO")) tabla="";

            if(carrera.equalsIgnoreCase("INGENIERIA CIVIL") && turno.equalsIgnoreCase("DIURNO")) tabla="ingenieria_civil_2010_d";
            if(carrera.equalsIgnoreCase("INGENIERIA CIVIL") && turno.equalsIgnoreCase("NOCTURNO")) tabla="ingenieria_civil_2010_n";

            if(carrera.equalsIgnoreCase("INGENIERIA ELECTRICA") && turno.equalsIgnoreCase("DIURNO")) tabla="ingenieria_electrica_2010_d";
            if(carrera.equalsIgnoreCase("INGENIERIA ELECTRICA") && turno.equalsIgnoreCase("NOCTURNO")) tabla="ingenieria_electrica_2010_d";

            if(carrera.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turno.equalsIgnoreCase("DIURNO")) tabla="ingenieria_electronica_2010_d";
            if(carrera.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turno.equalsIgnoreCase("NOCTURNO")) tabla="ingenieria_electronica_2010_d";

            if(carrera.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turno.equalsIgnoreCase("DIURNO")) tabla="ingenieria_sistemas_2010_d";
            if(carrera.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turno.equalsIgnoreCase("NOCTURNO")) tabla="ingenieria_sistemas_2010_n";

            if(carrera.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turno.equalsIgnoreCase("DIURNO")) tabla="ingenieria_telecomunicaciones_2010_d";
            if(carrera.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turno.equalsIgnoreCase("NOCTURNO")) tabla="ingenieria_telecomunicaciones_2010_d";

            if(carrera.equalsIgnoreCase("TSU EN ENFERMERIA") && turno.equalsIgnoreCase("DIURNO")) tabla="tsu_enfermeria_2010_d";
            if(carrera.equalsIgnoreCase("TSU EN ENFERMERIA") && turno.equalsIgnoreCase("NOCTURNO")) tabla="";

            if(carrera.equalsIgnoreCase("LIC. EN ENFERMERIA") && turno.equalsIgnoreCase("DIURNO")) tabla="lic_enfermeria_2010_d ";
            if(carrera.equalsIgnoreCase("LIC. EN ENFERMERIA") && turno.equalsIgnoreCase("NOCTURNO")) tabla="";

            if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turno.equalsIgnoreCase("DIURNO")) tabla="lic_administracion_gestion_municipal_2010_d";
            if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turno.equalsIgnoreCase("NOCTURNO")) tabla="lic_administracion_gestion_municipal_2010_n";


            if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turno.equalsIgnoreCase("DIURNO")) tabla="lic_administracion_desastre_2010_d";
            if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turno.equalsIgnoreCase("NOCTURNO")) tabla="lic_administracion_desastre_2010_n";
            
            if(carrera.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turno.equalsIgnoreCase("DIURNO")) tabla="tsu_mecanica_dental_2010_d";
            if(carrera.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turno.equalsIgnoreCase("NOCTURNO")) tabla="tsu_mecanica_dental_2010_n";

            if(carrera.equalsIgnoreCase("LIC. EN TURISMO") && turno.equalsIgnoreCase("DIURNO")) tabla="lic_turismo_2010_d";
            if(carrera.equalsIgnoreCase("LIC. EN TURISMO") && turno.equalsIgnoreCase("NOCTURNO")) tabla="lic_turismo_2010_n";
    
    System.out.println("TABLA: "+tabla);
return tabla;
}


/**METODO PARA CARGAR TODO EL PENSUM A TRABAJAR */
public void consulta_pensum(Connection con, String carrera, String turno){
            Statement sentencia = null;
            ResultSet resultado = null;



        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM pensum."+this.identificador_carrera(carrera, turno)+";");
            while(resultado.next()){//recorriendo las materias encontradas
                this.getMaterias().add(resultado.getString("codigo"));
                this.getMaterias().add(resultado.getString("semestre"));
                this.getMaterias().add(resultado.getString("asignatura"));
                this.getMaterias().add(resultado.getString("uc"));
            }

            sentencia.close();



        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR CONSULTANDO PENSUM CON BASE DE DATOS\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            this.resumenes("ERROR CONSULTANDO PENSUM CON BASE DE DATOS\n"+ex.getMessage());
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }




    }

/**Este metodo permite determinar si una materia de las ofertadas fue usada para inscribir, por lo tanto las 
 otras que pertenecen a otro dia del mismo horario se guardaran sin sumar nuevamente las unidades de credito,
 por lo que se asumira que es cero*/
public void precaucion(String materia){
    boolean coincidencia=true;

    if(this.getUsadas().isEmpty()){//si no hay algun registro se agrega el primero
        this.getUsadas().add(materia);
    }else{//si existe algun registro se verifica si existe coincidencia
        for(int i=0; i<this.getUsadas().size(); i++){//recorriendo las usadas en busca de coincidencia

            if(materia.equalsIgnoreCase(this.getUsadas().get(i))){//si hay coincidencia
                System.out.println("se hallo coincidencia en ofertadas vs inscribir uc=0\n");
                this.setUc("0");
                coincidencia=true;
                break;
            }else{//no hay coincidencia por lo tanto se coloca esta variable para mas adelante anexar esa materia que no se encuentra en la lista
                coincidencia=false;
            }


        }//fin recorrido

        if(coincidencia==false) this.getUsadas().add(materia);//despues de recorrer todas las materias usadas y no encontrar coincidencia se anexa esta nueva materia

    }

}


/**Este metodo permite buscar a partir del nombre en ofertadas todos los datos de interes de la materia */
public void comparacion_materias(){

    for(int i=0; i<this.getMaterias().size(); i=i+4){//buscando datos de la materia
       // System.out.println("IGUALES ? "+this.getMateria()+" - "+this.getMaterias().get(i+2));

        if(this.getMateria().equalsIgnoreCase(this.getMaterias().get(i+2))){
            System.out.println("materia "+this.getMateria()+" encontrada");

            this.setCodigo(this.getMaterias().get(i));
            this.setSemestre(this.getMaterias().get(i+1));
            this.setMateria(this.getMaterias().get(i+2));
            this.setUc(this.getMaterias().get(i+3));

            this.precaucion(this.getMaterias().get(i+2));//solo para evitar repetir las uc cuando se inscribe las materias
            break;//se rompe el ciclo apenas halla coincidencia
        }

    }//fin recorrido






}

public void consulta_ofertadas(Connection con, String periodo){
         Statement sentencia = null;
         ResultSet resultado = null;

System.out.println("S:"+this.getSeccion()+" P:"+periodo);
        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE seccion='"+this.getSeccion()+"' AND periodo='"+periodo+"';");
            while (resultado.next()) {//recorriendo materias ofertadas
                this.setDocente(resultado.getString("docente"));
                this.setDia(resultado.getString("dia"));
                this.setHora(resultado.getString("hora_inicio").substring(0,5)+" a "+resultado.getString("hora_fin").substring(6, 14));
                this.setMateria(resultado.getString("materia"));
                
                System.out.println("ofertada: "+this.getMateria()+" - "+this.getDia());

               
                this.comparacion_materias();//obteniendo los parametros de interes de la materia
                this.setPeriodo(periodo);//estableciendo el periodo academico
                this.armando_informacion();//armando toda la informacion debido a que ya todos los parametros estan completos
            }


            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR CONSULTANDO CON LA BASE DE DATOS\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            this.resumenes("ERROR CONSULTANDO CON LA BASE DE DATOS\n"+ex.getMessage());
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }






}










/**MEtodo que se encarga de guardar en el LinkedList informacion los parametros de interes */
public void armando_informacion(){
    this.setCondicion("N");//valores fijo
    this.setDia_inscripcion("hoy");
  //  this.setPeriodo("1-2012");
//2012-10-10 10:03:44
    
    this.getInformacion().add(this.getCedulas());//cedula
    this.getInformacion().add(this.getEstudiante());//estudiante
    this.getInformacion().add(this.getCarrera());//carrera
    this.getInformacion().add(this.getSemestre());//semestre
    this.getInformacion().add(this.getCodigo());//codigo
    this.getInformacion().add(this.getMateria());//materia
    this.getInformacion().add(this.getUc());//uc
    this.getInformacion().add(this.getDia());//dia
    this.getInformacion().add(this.getHora());//hora
    this.getInformacion().add(this.getSeccion());//seccion
    this.getInformacion().add(this.getPeriodo());//periodo
    this.getInformacion().add(this.getDia_inscripcion());//dia_inscripcion
    this.getInformacion().add(this.getDocente());//docente
    this.getInformacion().add(this.getCondicion());//condicion

    this.resumenes("INSCRIBIENDO MATERIA: "+"Cedula: "+this.getCedulas()+"\n"
                       +"Estudiante: "+this.getEstudiante()+"\n"
                       +"Carrera: "+this.getCarrera()+"\n"
                       +"Semestre: "+this.getSemestre()+"\n"
                       +"Codigo: "+this.getCodigo()+"\n"
                       +"Materia: "+this.getMateria()+"\n"
                       +"Uc: "+this.getUc()+"\n"
                       +"Dia: "+this.getDia()+"\n"
                       +"Hora: "+this.getHora()+"\n"
                       +"Seccion: "+this.getSeccion()+"\n"
                       +"Periodo: "+this.getPeriodo()+"\n"
                       +"Dia Inscripcion: "+this.getDia_inscripcion()+"\n"
                       +"Docente: "+this.getDocente()+"\n"
                       +"Condicion: "+this.getCondicion()+"\n");

     System.out.println("\n\nINFORMACION ENVIADA: "
                       +"Cedula: "+this.getCedulas()+"\n"
                       +"Estudiante: "+this.getEstudiante()+"\n"
                       +"Carrera: "+this.getCarrera()+"\n"
                       +"Semestre: "+this.getSemestre()+"\n"
                       +"Codigo: "+this.getCodigo()+"\n"
                       +"Materia: "+this.getMateria()+"\n"
                       +"Uc: "+this.getUc()+"\n"
                       +"Dia: "+this.getDia()+"\n"
                       +"Hora: "+this.getHora()+"\n"
                       +"Seccion: "+this.getSeccion()+"\n"
                       +"Periodo: "+this.getPeriodo()+"\n"
                       +"Dia Inscripcion: "+this.getDia_inscripcion()+"\n"
                       +"Docente: "+this.getDocente()+"\n"
                       +"Condicion: "+this.getCondicion()+"\n"
            );



}


/**METODO FINAL PARA INSCRIPCION */
public void inscribiendo(Connection con){
       PreparedStatement preparada = null;
    
    
    try {

            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            preparada = con.prepareStatement("INSERT INTO control_de_estudio.jornada_inscripcion VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?);");

            for (int x = 0; x < this.getInformacion().size(); x = x + 14) {
                preparada.clearParameters();
                preparada.setString(1, this.getInformacion().get(x) ); //cedula
                preparada.setString(2, this.getInformacion().get(x+1)); //estudiante
                preparada.setString(3, this.getInformacion().get(x+2)); //carrera
                preparada.setString(4, this.getInformacion().get(x+3)); //semestre
                preparada.setString(5, this.getInformacion().get(x+4)); //codigo
                preparada.setString(6, this.getInformacion().get(x+5)); //materia
                preparada.setString(7, this.getInformacion().get(x+6)); //uc
                preparada.setString(8, this.getInformacion().get(x+7)); //dia
                preparada.setString(9, this.getInformacion().get(x+8)); //hora
                preparada.setString(10, this.getInformacion().get(x+9)); //seccion
                preparada.setString(11, this.getInformacion().get(x+10)); //periodo
                //preparada.setString(12, informacion.get(x + 5)); //dia_inscripcion
                preparada.setString(12, this.getInformacion().get(x+12)); //docente
                preparada.setString(13, this.getInformacion().get(x+13)); //condicion
                preparada.addBatch();
            }

            preparada.clearParameters();//limpiando parametros
            preparada.executeBatch();//ejecutando lotes

            con.commit();//haciendo permanente los cambios y liberando la base de datos
            //preparada.clearBatch();//limpiando listas de sentencias
            //preparada.close();//cerrando la sentencia
            this.resumenes("ESTUDIANTE "+this.getCedulas()+" "+this.getEstudiante()+"\nINSCRITO CON EXITO...!!!\n--------------------------------------------------------------------------------------------------------------------\n");
            ima.mensaje_informacion("ESTUDIANTE "+this.getCedulas()+" "+this.getEstudiante()+"\nINSCRITO CON EXITO...!!!", "INSCRIPCION AUTOMATICA NUEVO INGRESO", "inscrito","png");

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ENVIANDO INFORMACION A BASE DE DATOS\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            this.resumenes("ERROR ENVIANDO INFORMACION A BASE DE DATOS\n"+ex.getMessage());
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preparada.clearBatch(); //limpiando listas de sentencias
                preparada.close(); //cerrando la sentencia
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }




}


public void ejecutando(String identificacion, String periodo){
    //llenando paulatinamente los parametros de interes de los nuevos ingresos
    this.setPeriodo(periodo);
 //PASO 0: Verificando si el estudiante ya esta inscrito
    this.revision_inscrito(new conexion_base_de_datos().getConexion(), identificacion);
        if(this.isEsta_inscrito()){}else{//verificando que no este inscrito para seguir los pasos posteriores
         //PASO 1: Buscando si el estudiante esta autorizado para inscribirse
            this.busqueda(new conexion_base_de_datos().getConexion(), identificacion);

                if(this.isAutorizar()){//estos pasos se ejecutan siempre y cuando el estudiante este autorizado para inscribirse
                     //PASO 2: Cargando parametros personales del estudiante si existe
                        this.estudiante(new conexion_base_de_datos().getConexion());

                     //PASO 3: Cargando el pensum al que pertenece.
                        this.consulta_pensum(new conexion_base_de_datos().getConexion(), this.getCarrera(), this.getTurno());

                     //PASO 4: Cargando ofertadas y armando materias para inscribir
                        this.consulta_ofertadas(new conexion_base_de_datos().getConexion(),periodo);

                     //PASO 5: INSCRIBIENDO LA MATERIA ARMADA EN BASE DE DATOS
                        this.inscribiendo(new conexion_base_de_datos().getConexion());
                    }//fin verificar autorizacion
       }//fin verificacion que este inscrito
   

}


public void carga_previa(Connection con){

            Statement sentencia = null;
            ResultSet resultado = null;

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.nuevo_ingreso;");
            while(resultado.next()){
                alumnado.add(resultado.getString("cedula"));
            }

            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR CARGANDO INFORMACION DE ALUMNOS AUTORIZADOS NUEVO INGRESO\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);

        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


}

public void eliminar_inscripcion(Connection con, String cedula, String periodo){
    boolean autorizar = false;
    Statement sentencia = null;

    for(int i=0; i<alumnado.size(); i++){//recorriendo registro en busqueda de solo el personal nuevo ingreso
        if(alumnado.get(i).equalsIgnoreCase(cedula)){//si hay coincidencia de cedula se puede eliminar el estudiante
            autorizar=true;
            break;
        }else{//sino no se puede autorizar porque esta fuera de la lista. asi se evita por accidente borrar un estudiante de otros semestre por error
            autorizar = false;
        }


    }//fin recorrido


    
    try{
        if(autorizar){//si esta en la lista de los nuevos ingresos se autoriza a eliminacion de todos sus registros
            sentencia = con.createStatement();
            sentencia.execute("DELETE FROM control_de_estudio.jornada_inscripcion WHERE cedula='"+cedula+"' AND periodo='"+periodo+"';");
            sentencia.close();
            ima.mensaje_informacion("INSCRIPCION DEL ESTUDIANTE:"+cedula+" ELIMINADO CON EXITO","BORRADO EXITOSO", "eliminacion","png");
            ns.getResumen().append("INSCRIPCION DEL ESTUDIANTE:"+cedula+" ELIMINADO CON EXITO\n--------------------------------------------------------------------------------------------------------------------");
        }else{
            ima.mensaje_informacion("IMPOSIBLE BORRAR INSCRIPCION, ESTE ESTUDIANTE NO ES NUEVO INGRESO","BORRADO NO AUTORIZADO", "no","png");

        }

    } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR BORRANDO INSCRIPCION NUEVO INGRESO\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);

        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


}







}//fin de la clase
