/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class registro_notas {


    
private String cedula, codigo, condicion, periodo,codigo_opsu, seccion;
private int definitiva, reparacion, pensum, termino, uc, laboratorio, def_rep, inasis;
private LinkedList<String> inscritos = new LinkedList<>();
private LinkedList<Object> llenas = new LinkedList<Object>();
private LinkedList<String> materias_condicion = new LinkedList<>();

private String carrera, turno, nucleo;

private materias materia;
private registro_pdf rpdf;
private registro_ingenieria reging;
private Archivos doc;
    
    public registro_notas() {
        materia = new materias();
        rpdf = new registro_pdf();
        reging = new registro_ingenieria();
        doc = new Archivos();
    }



    public LinkedList<String> getInscritos() {
        return inscritos;
    }

    public void setInscritos(LinkedList<String> inscritos) {
        this.inscritos = inscritos;
    }

   
    public LinkedList<Object> getLlenas() {
        return llenas;
    }

    public void setLlenas(LinkedList<Object> llenas) {
        this.llenas = llenas;
    }

    public LinkedList<String> getMaterias_condicion() {
        return materias_condicion;
    }

    public void setMaterias_condicion(LinkedList<String> materias_condicion) {
        this.materias_condicion = materias_condicion;
    }


/**Metodo que permite obtener todos los estudiantes inscritos en la jornada de inscripcion */
public void lista_estudiantes_jornada(Connection con, String carrera){//recordar que de ahora en adelante las materias que se inscriben en jornada se almacene tambien el periodo academico

    Statement sentencia = null;
    ResultSet resultado = null;

    System.out.println("PASO_1");
    try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.jornada_inscripcion WHERE carrera='"+carrera+"' group by cedula;");

            while(resultado.next()){//recorriendo los estudiantes por carrera
                this.getInscritos().add(resultado.getString("cedula"));

            }

            System.out.println("Estudiantes Inscritos en "+carrera+" "+this.getInscritos().size());

            sentencia.close();
            resultado.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_notas.class.getName()).log(Level.SEVERE, null, ex);
        }

}

/**Este metodo permite obtener la informacion necesaria referente a un alumno en particular, para usos
 posteriores en procedimientos.*/
public void informacion_estudiante(Connection con, String cedula){
    Statement sentencia = null;
    ResultSet resultado = null;


        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='"+cedula+"';");
            while(resultado.next()){
                carrera = resultado.getString("carrera");
                turno = resultado.getString("turno");
                nucleo = resultado.getString("nucleo");
                pensum = resultado.getInt("pensum");
            }
            sentencia.close();
            resultado.close();
            con.close();


             codigo_opsu = rpdf.opsu_sede(nucleo)+rpdf.opsu_carrera(carrera)+rpdf.opsu_turno(turno); //codigo opsu completo

             System.out.println(cedula+"-"+carrera+"-"+turno+"-"+pensum+"-"+codigo_opsu+"-"+nucleo);

        } catch (SQLException ex) {
            Logger.getLogger(registro_notas.class.getName()).log(Level.SEVERE, null, ex);
        }
}


/**Metodo que permite obtener las materias del estudiantes proveniente de su record academico, en
 el cual se ordenara por periodo academico*/
public void cargando_materias_estudiante(Connection con,String cedula){
   //reging.buscar_materias(con,cedula , "cem_notas_alumnos_todas_carreras_"+pensum);//buscando las materias del estudiante
   reging.buscar_materias_alternativo(con,cedula , "cem_notas_alumnos_todas_carreras_"+pensum);//buscando las materias del estudiante

    //for(int b=0; b<reging.getCodigo().size();b++){//recorriendo todo el LinkedList para determinar el periodo mas pequeño y el mas grande
    for(int b=0; b<reging.getRecord().size();b=b+11){//recorriendo todo el LinkedList para determinar el periodo mas pequeño y el mas grande
      //materia.ordenamiento_año(reging.getPeriodo().get(b));
      materia.ordenamiento_año(reging.getRecord().get(b+5));
    }

   // materia.ordenamiento_materias(reging);//ordenando las materias del alumno por periodo academico
    materia.ordenamiento_materias_alternativos(reging);//ordenando las materias del alumno por periodo academico

                  /*for(int x=0; x<materia.getOrden().size();x=x+6){//mostrando materias del record
                    System.out.println(materia.getOrden().get(x)+"-"+
                                       materia.getOrden().get(x+1)+"-"+
                                       materia.getOrden().get(x+2)+"-"+
                                       materia.getOrden().get(x+3)+"-"+
                                       materia.getOrden().get(x+4)+"-("+
                                       materia.getOrden().get(x+5)+")"
                                       );

                  }//fin mostrar materias del record*/



}

public void cargando_pensum(String especialidad, Connection con){
//this.getSeleccion_pensum()==0 && this.getNuevo_ingreso()==0
  
    if(pensum==2010){materia.setSeleccion_pensum(0);materia.setNuevo_ingreso(0); System.out.println("Vigencia pensum cargado: "+pensum);}//se selecciona la opcion de acuerdo a la vigencia que se necesite. estas opciones estan
    if(pensum==2009){materia.setSeleccion_pensum(1);materia.setNuevo_ingreso(0); System.out.println("Vigencia pensum cargado: "+pensum);}//predeterminadas en la clase de donde se esta haciendo uso
    if(pensum==2007){materia.setSeleccion_pensum(2);materia.setNuevo_ingreso(0); System.out.println("Vigencia pensum cargado: "+pensum);}//de estos metodos
    
    //materia.materias_pensum(con,materia.carrera(especialidad, turno));//cargando las materias del pensum correspondiente
    materia.materias_pensum(con,especialidad, turno);//cargando las materias del pensum correspondiente

           
          /* for(int p=0;p<materia.getPensum().size();p=p+7){//mostrando el pensum cargado
                System.out.println(materia.getPensum().get(p)+"-"+
                                   materia.getPensum().get(p+1)+"-"+
                                   materia.getPensum().get(p+2)+"-"+
                                   materia.getPensum().get(p+3)+"-"+
                                   materia.getPensum().get(p+4)+"-"+
                                   materia.getPensum().get(p+5)
                        );


            }//solo para mostrar las materias del pensum que se cargaron*/


}
/**Se cargan todas las materias con su condicion correspondiente */
public void busca_condicion(Connection con, String cedula,String periodo){
    this.getMaterias_condicion().clear();
    
    Statement sentencia = null;
       ResultSet resultado = null;

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT codigo, condicion FROM prueba.jornada_inscripcion  WHERE (cedula='"+cedula+"' AND periodo='"+periodo+"') GROUP BY codigo;");
            while(resultado.next()){                
               this.getMaterias_condicion().add(resultado.getString("codigo"));
               this.getMaterias_condicion().add(resultado.getString("condicion"));
            }

            sentencia.close();
            resultado.close();
            con.close();                      

        } catch (SQLException ex) {
            Logger.getLogger(registro_notas.class.getName()).log(Level.SEVERE, null, ex);
        }

}

public String comparacion_codigo_condicion(String codigo){
    String condicion="REVISAR";
        for(int c=0;c<this.getMaterias_condicion().size();c=c+2){//recorriendo las materias con su condicion
            if(codigo.equalsIgnoreCase(this.getMaterias_condicion().get(c))){//si se halla la materia se anexa la condicion de la misma
                condicion=this.getMaterias_condicion().get(c+1);
                break;
            }//

        }//fin recorrido materias con condicion

 return condicion;
}



public void cargando_materias_llenadas(Connection con, String cedula, String periodo){
       Statement sentencia = null;
       ResultSet resultado = null;
       this.getLlenas().clear();//limpiando LinkedList

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM MEN.notalu where (cedula='"+cedula+"' AND peraca='"+periodo+"');");
            while(resultado.next()){
                this.getLlenas().add(resultado.getString("codmat"));//0
                this.getLlenas().add(resultado.getString("seccion"));//1
                this.getLlenas().add(this.comparacion_codigo_condicion(resultado.getString("codmat")));//2
                this.getLlenas().add(resultado.getInt("DEF"));//3
                this.getLlenas().add(resultado.getInt("REP"));//4
                this.getLlenas().add(resultado.getInt("Lab"));//5
                this.getLlenas().add(resultado.getInt("porina"));//6


            }
                System.out.println("codigo, seccion, condicion, def, rep, lab, porina");
                //mostrando las materias halladas con notas del estudiante
                    if(this.getLlenas().size()>0){//si existe registro muestralas
                       for(int r=0; r<this.getLlenas().size();r=r+7){//recorriendo todas las materias encontradas
                        System.out.println(
                                "cod: "+this.getLlenas().get(r)+" - sec:("+
                                this.getLlenas().get(r+1)+") - condi: "+
                                this.getLlenas().get(r+2)+" - def: "+
                                this.getLlenas().get(r+3)+" - rep: "+
                                this.getLlenas().get(r+4)+" - lab:"+
                                this.getLlenas().get(r+5)+" - porina:"+
                                this.getLlenas().get(r+6)
                                );
                        }//fin recorrido
                    }else{
                            System.out.println("NO SE HALLO MATERIAS INSCRITAS DEL ESTUDIANTE "+cedula);
                    }




        } catch (SQLException ex) {
            Logger.getLogger(registro_notas.class.getName()).log(Level.SEVERE, null, ex);
        }

}







//--------------PROCESO DE PASE DE MATERIA AL RECORD------------------------



private void armado_materia(Connection con,String identidad, int opcion){
        Statement sentencia = null;


 try {
        sentencia = con.createStatement();

        this.inasistencia_25(inasis);//comprobando lo del 25% de la materia


// inasis=0;
     if(opcion==1){
         System.out.println("ACTUALIZANDO");
      //  System.out.println("UPDATE control_de_estudio.cem_notas_alumnos_todas_carreras_"+pensum+" SET "+
       sentencia.executeUpdate("UPDATE control_de_estudio.cem_notas_alumnos_todas_carreras_"+pensum+" SET "+
                            "SECCION='"+seccion+"',"//seccion
                           +"TERMINO="+termino+","//termino
                           +"PERACA='"+periodo+"',"//peraca
                           +"CONDIC='"+condicion+"',"//condic
                           +"NOTREP="+reparacion+","//notrep
                           +"NOTDEF="+definitiva+","//notdef
                           +"DEFREP="+def_rep+"," //"0,"//defrep
                           +"PORINA="+inasis+","//porina
                           +"CODESP='"+codigo_opsu+"',"//codesp
                           +"CREDI="+uc+","//credi
                           +"PENSUM="+pensum+","//pensum
                           +"NOTLAB="+laboratorio+" "//notlab
                           +"WHERE (CEDULA='"+identidad+"' AND CODMAT='"+codigo+"' AND PERACA='"+periodo+"') ;"//codmat
                            );

    }else{
        System.out.println("INSERTANDO");
       // System.out.println("INSERT INTO control_de_estudio.cem_notas_alumnos_todas_carreras_"+pensum+" VALUES("
        sentencia.executeUpdate("INSERT INTO control_de_estudio.cem_notas_alumnos_todas_carreras_"+pensum+" VALUES("
                    + "NULL,'"//posicion
                    +identidad+"','"//cedula
                    +codigo+"','"//codmat
                    + "','"//seccion2
                    +seccion+"',"//seccion
                    +termino+",'"//termino
                    +periodo+"','"//peraca
                    +condicion+"',"//condic
                    +reparacion+","//notrep
                    +definitiva+","//notdef
                    +def_rep+"," //"0,"//defrep
                    +inasis+",'"//porina
                    +codigo_opsu+"',"//codesp
                    +uc+","//credi
                    +pensum+",'"//pensum
                    + "','"//observacio
                    + "','"//fecha_hora
                    + "',"//administra
                    +laboratorio+""//notlab
                    + ");");

    }




        
        
          /*  sentencia = con.createStatement();
            sentencia.executeUpdate("INSERT INTO control_de_estudio.cem_notas_alumnos_todas_carreras_"+pensum+" VALUES("
                    + "NULL,'"//posicion
                    +identidad+"','"//cedula
                    +codigo+"','"//codmat
                    + "','"//seccion2
                    +seccion+"',"//seccion
                    +termino+",'"//termino
                    +periodo+"','"//peraca
                    +condicion+"',"//condic
                    +reparacion+","//notrep
                    +definitiva+","//notdef
                    + "0,"//defrep
                    + "0,'"//porina
                    +codigo_opsu+"',"//codesp
                    +uc+","//credi
                    +pensum+",'"//pensum
                    + "','"//observacio
                    + "','"//fecha_hora
                    + "',"//administra
                    +laboratorio+""//notlab
                    + ");");
*/
            sentencia.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_notas.class.getName()).log(Level.SEVERE, null, ex);
        }


}


/**Metodo que permite obtener a partir del codigo de la materia suministrada
 el termino al que pertenece y las unidades de credito*/
public void c_p_c_uc(String codigo){

    for(int x=0; x<materia.getPensum().size(); x=x+7){//recorriendo el pensum
      if(codigo.equalsIgnoreCase(materia.getPensum().get(x))){//si existen coincidencias
        termino= Integer.valueOf(materia.getPensum().get(x+1));
        uc= Integer.valueOf(materia.getPensum().get(x+3));
        break;
      }           

    }//fin recorrido pensum

}


/**Metodo que permite hacer las consideraciones si la materia es cualitativa,
 ya que se deben cambiar unos parametros, condicion= aprobado o reprobado y
 la definitiva como la reparacion en cero debido a que la nota no se presenta*/
public void materia_cualitativa( String condic){

    if(codigo.startsWith("ACO20")||codigo.startsWith("IMI")||codigo.startsWith("ADG25130")||codigo.startsWith("CAT00001")){
        System.out.println("Materia Cualitativa. Consideracion Especial");
        condicion=condic;
        definitiva=0;//esto es porque son materias cualitativas y no tienen nota
        reparacion=0;
    }

}

/**Metodo que permite saber si la materia esta con problema del 25% de inasistencia,
 por lo que el procedimiento es cambiar la condicion a "G"*/
public void inasistencia_25(int inasistencias){
    System.out.println("inasistencia= "+inasistencias+" cond: "+condicion);

   if(inasistencias>=25){
      
        if(condicion.equalsIgnoreCase("P")){
           condicion="PX";
           System.out.println("Materia de REPITENCIA perdida por inasistencia");
        }else{
           condicion="G";
           System.out.println("Materia perdida por inasistencia");
        }

          this.materia_cualitativa("Q");//cuando una materia cualitativa es reprobada.
    }
}


public void parametros(String cod,String sec, String period, String condicio, int rep, int def, int lab, int dr, int inasistencia){
    System.out.println("Cargando parametros");
        codigo=cod;
        seccion=sec;
        this.c_p_c_uc(codigo);//se obtiene del pensum el termino y las uc
        //termino="";
        periodo=period;
        condicion=condicio;
        reparacion=rep;
        definitiva=def;
        //uc="";
        laboratorio=lab;
        def_rep = dr;
        inasis = inasistencia;

}

/**Este metodo permite analizar la condicion de la materia que ya ha visto el estudiante.
 es para saber si esa materia fue reparada o no y saber que nota tomar para repetirla en 
 la materia nueva que se esta armando y enviarla al record academico. La condicion de 
 la materia seria fijara a R = reprobo a menos que en el futuro se establezcan otras condiciones.*/
public void vieja_reparacion(int ubicacion, String periodo_academico){


    if(materia.getOrden().get(ubicacion+3)==null || materia.getOrden().get(ubicacion+3).equalsIgnoreCase("0") ){
        materia.getOrden().set(ubicacion+3,"0");//evitar el null y sustituirlo por cero
    }

    if(Integer.valueOf(materia.getOrden().get(ubicacion+3))>0){//verificando si la materia vieja fue reparada
         System.out.println("VIEJA SI fue reparada");
            definitiva=Integer.valueOf(materia.getOrden().get(ubicacion+3));//se coloca la nota de reparacion de la vieja en la definitiva de la materia que se esta armando
            condicion="P";//se establece la materia armada como repitencia.

    }else{//si no fue reparada
        System.out.println("VIEJA NO fue reparada");
        
            definitiva=Integer.valueOf(materia.getOrden().get(ubicacion+2));//se coloca la nota definitiva de la vieja en la definitiva de la materia que se esta armando
            condicion="P";//se establece la materia armada como repitencia.

    }

System.out.println("VIENDO. vieja: "+materia.getOrden().get(ubicacion)+" R="+materia.getOrden().get(ubicacion+3)+" D="+materia.getOrden().get(ubicacion+2)+" periodo:"+materia.getOrden().get(ubicacion+5)+"  def_armada:"+definitiva);
}



/**Este metodo analiza que hacer si un alumno que le toca reparar una materia
 decide realizarla o no. Si no repara la nota estara en cero y condicion R.
 Ademas si pasa o no la reparacion.*/
public void nueva_reparacion(int ubicacion, String periodo_academico){

    if(Integer.valueOf(this.getLlenas().get(ubicacion+4).toString())>0){//se verifica primero que el estudiante halla ido a reparar. si tiene mas de cero entonces fue sino no fue.
        System.out.println("SI fue a reparar");

                    //verficando que halla pasaso o no la reparacion
                    if(Integer.valueOf(this.getLlenas().get(ubicacion+4).toString())>=10){//si la nota de reparacion es mayor a 10
                        System.out.println("SI paso reparacion");

                            this.parametros(this.getLlenas().get(ubicacion).toString(),
                            this.getLlenas().get(ubicacion+1).toString(),
                            periodo_academico,
                            "R",//condicion de la materia es reprobo
                            Integer.valueOf(this.getLlenas().get(ubicacion+4).toString()),//se guarda la nota de reparacion
                            Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()),//se guarda la nota definitiva
                            Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                            0 ,      //def_rep
                            Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia

                                this.materia_cualitativa("A");//solo si es una materia cualitativa

                     }else{//no paso la reparacion
                        System.out.println("NO paso reparacion");

                            this.parametros(this.getLlenas().get(ubicacion).toString(),
                            this.getLlenas().get(ubicacion+1).toString(),
                            periodo_academico,
                            "R",//condicion de la materia es reprobo y queda pendiente
                            Integer.valueOf(this.getLlenas().get(ubicacion+4).toString()),//se guarda la nota de reparacion
                            Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()),//se guarda la nota definitiva
                            Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                            0,      //def_rep
                            Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia
                                
                                this.materia_cualitativa("Q");//solo si es una materia cualitativa
                        }



    }else{//no fue a reparar la materia se guarda como cero en la reparacion y en condicion R reprobo
        System.out.println("NO fue a reparar");
            this.parametros(this.getLlenas().get(ubicacion).toString(),
                            this.getLlenas().get(ubicacion+1).toString(),
                            periodo_academico,
                            "R",//condicion
                            0,//nota de reparacion a cero
                            Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()),
                            Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                            0, //def_rep
                            Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia

                                this.materia_cualitativa("Q");//solo si es una materia cualitativa
    }//fin analisis si fue o no a reparar




                
}

/**Metodo que realiza el analisis de la materia nueva en base de que la vio anteriormente, por lo
 que cualquier nota que se obtiene residira en la nota de reparacion de la que se esta
 armando para el envio al record*/
public void nueva_vieja_reparacion(int ubicacion, String periodo_academico){

    if(Integer.valueOf(this.getLlenas().get(ubicacion+4).toString())>0){//se verifica primero que el estudiante halla ido a reparar. si tiene mas de cero entonces fue sino no fue.
        System.out.println("SI fue a reparar la repitencia");

                    //verficando que halla pasaso o no la reparacion
                    if(Integer.valueOf(this.getLlenas().get(ubicacion+4).toString())>=10){//si la nota de reparacion es mayor a 10
                        System.out.println("SI paso reparacion repitencia");

                            this.parametros(this.getLlenas().get(ubicacion).toString(),
                            this.getLlenas().get(ubicacion+1).toString(),
                            periodo_academico,
                            "P",//condicion de la materia es reprobo
                            Integer.valueOf(this.getLlenas().get(ubicacion+4).toString()),//se guarda la nota de reparacin en la  reparacion de la que se esta armando
                            definitiva,//se mantiene en la definitiva la nota de la materia de repitencia anterior
                            Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                            Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()),//en el def_rep se guarda la definitiva que se obtuvo antes de ir a repararla la haya pasado o no
                            Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia

                                this.materia_cualitativa("A");//solo si es una materia cualitativa

                     }else{//no paso la reparacion
                        System.out.println("NO paso reparacion repitencia");

                            this.parametros(this.getLlenas().get(ubicacion).toString(),
                            this.getLlenas().get(ubicacion+1).toString(),
                            periodo_academico,
                            "P",//condicion de la materia es reprobo y queda pendiente
                            Integer.valueOf(this.getLlenas().get(ubicacion+4).toString()),//se guarda la nota de reparacion de la nueva en la rep de la que se esta armando
                            definitiva,//se mantiene en la definitiva la nota de la materia de repitencia anterior
                            Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                            Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()), //en el def_rep se guarda la definitiva que se obtuvo antes de ir a repararla la haya pasado o no
                            Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia

                                this.materia_cualitativa("Q");//solo si es una materia cualitativa
                        }



    }else{//no fue a reparar la materia se guarda como cero en la reparacion y en condicion R reprobo
        System.out.println("NO fue a reparar repitencia");
            this.parametros(this.getLlenas().get(ubicacion).toString(),
                            this.getLlenas().get(ubicacion+1).toString(),
                            periodo_academico,
                            "P",//condicion P repitencia.
                            Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()),//se coloca en la reparacion la definitiva de la repitencia
                            definitiva,//se mantiene la definitiva de la vieja sobre la que se esta armando
                            Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                            0, //def_rep no fue a reparar
                            Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia

                                this.materia_cualitativa("Q");//solo si es una materia cualitativa
    }//fin analisis si fue o no a reparar



}





/**Este metodo permite saber si la materia nueva fue reparada o no. Cuando la materia que se esta analizando
 se asume que aqui no se encontro coincidencia. es la primera vez que la ve*/
public void nueva_analisis_1(int ubicacion, String identidad, String periodo_academico){
//recordar limpiar las variables


    if(Integer.valueOf(this.getLlenas().get(ubicacion+3).toString())>=10){//si la definitiva es mayor a 10 paso
        //rellenando datos faltantes
        System.out.println("\nPASO LA MATERIA");
        this.parametros(this.getLlenas().get(ubicacion).toString(),
                        this.getLlenas().get(ubicacion+1).toString(),
                        periodo_academico,
                        this.getLlenas().get(ubicacion+2).toString(),
                        Integer.valueOf(this.getLlenas().get(ubicacion+4).toString()),
                        Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()),
                        Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                        0, //def_rep
                        Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia

                            this.materia_cualitativa("A");//solo si es una materia cualitativa

      /*  codigo=this.getLlenas().get(ubicacion).toString();
        seccion=this.getLlenas().get(ubicacion+1).toString();
        this.c_p_c_uc(codigo);//se obtiene del pensum el termino y las uc
        //termino="";
        periodo=periodo_academico;
        condicion=this.getLlenas().get(ubicacion+2).toString();
        reparacion=Integer.valueOf(this.getLlenas().get(ubicacion+4).toString());
        definitiva=Integer.valueOf(this.getLlenas().get(ubicacion+3).toString());
        //uc="";
        laboratorio=Integer.valueOf(this.getLlenas().get(ubicacion+5).toString());*/


//        this.materia_cualitativa();//revisando si la materia es cualitativa
       // this.armado_materia(new conexion_base_de_datos().getConexion(), identidad);//envio de la materia a el record
        this.analisis_duplicacion(new conexion_base_de_datos().getConexion(), identidad,codigo, periodo_academico);

    }else{//revisar cuando no pasa la materia
        System.out.println("\nNO PASO LA MATERIA.");
        this.nueva_reparacion(ubicacion, periodo_academico);
     //   this.materia_cualitativa();//revisando si la materia es cualitativa
        //this.armado_materia(new conexion_base_de_datos().getConexion(), identidad);//envio de la materia a el record
        this.analisis_duplicacion(new conexion_base_de_datos().getConexion(), identidad,codigo, periodo_academico);
    
    }




}
/**Este metodo analiza la materia nueva en base a la vieja. No es la primera vez que ve la materia,
 por lo que se verifica lo sucedido con la materia cuando la vio la ultima vez.*/
public void nueva_analisis_2(int ubicacion, String identidad, String periodo_academico, int vieja){

      this.vieja_reparacion(vieja, periodo_academico);//analizando el pasado de la materia


     if(Integer.valueOf(this.getLlenas().get(ubicacion+3).toString())>=10){//si la definitiva es mayor a 10 paso
        //rellenando datos faltantes
        System.out.println("\nPASO LA MATERIA REPITENCIA");
        this.parametros(this.getLlenas().get(ubicacion).toString(),
                        this.getLlenas().get(ubicacion+1).toString(),
                        periodo_academico,
                        condicion,//es condicion de repitencia P
                        Integer.valueOf(this.getLlenas().get(ubicacion+3).toString()),//la definitiva de la nueva en la reparacion de la nueva armada
                        definitiva,//se mantiene la nota de la materia vieja segun el procedimiento anterior. vieja_reparacion
                        Integer.valueOf(this.getLlenas().get(ubicacion+5).toString()),
                        0,//def_rep
                        Integer.valueOf(this.getLlenas().get(ubicacion+6).toString()) );//inasistencia
                        
                            this.materia_cualitativa("A");//solo si es una materia cualitativa

     //   this.materia_cualitativa();//revisando si la materia es cualitativa
        //this.armado_materia(new conexion_base_de_datos().getConexion(), identidad);//envio de la materia a el record
        this.analisis_duplicacion(new conexion_base_de_datos().getConexion(), identidad,codigo, periodo_academico);

    }else{//revisar cuando no pasa la materia
        System.out.println("\nNO PASO LA MATERIA REPITENCIA.");
        this.nueva_vieja_reparacion(ubicacion, periodo_academico);
      //  this.materia_cualitativa();//revisando si la materia es cualitativa
        //this.armado_materia(new conexion_base_de_datos().getConexion(), identidad);//envio de la materia a el record
        this.analisis_duplicacion(new conexion_base_de_datos().getConexion(), identidad,codigo, periodo_academico);


    }

}
/**Este metodo esta destinado a verificar que no se inscriba una misma materia en el mismo periodo, en caso de que si exista
 entonces se procedera a realizar un UPDATE sino un INSERT*/
public void analisis_duplicacion(Connection con, String identidad, String codigo_materia, String periodo_academico){
        boolean existe=false;
        Statement sentencia = null;
        ResultSet resultado = null;


        try {


           sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.cem_notas_alumnos_todas_carreras_" + pensum + " WHERE (CEDULA='"+identidad+"' AND CODMAT='"+codigo_materia+"' AND PERACA='"+periodo_academico+"');");
            while(resultado.next()){
                existe=true;
                System.out.println("existe ya inscrita en esta vigencia "+periodo_academico+" materia: "+codigo_materia+"?  ||="+existe+"=||");
            }

            sentencia.close();
            resultado.close();
            con.close();


                if(existe){//si ya la materia existe solo se actualiza
                    this.armado_materia(new conexion_base_de_datos().getConexion(), identidad,1);//envio de la materia a el record
                }else{//sino se inserta la materia
                    this.armado_materia(new conexion_base_de_datos().getConexion(), identidad,2);//envio de la materia a el record
                }





        } catch (SQLException ex) {
            Logger.getLogger(registro_notas.class.getName()).log(Level.SEVERE, null, ex);
        }




    

}



public void comparacion_record_inscritas(String identidad, String periodo_academico){
 boolean coincidencia=false;
 boolean mismo_periodo=false;
 int posicion=0;
 int vieja=0;

 

    for(int i=0; i<this.getLlenas().size(); i=i+7){//recorriendo las materias que se desean pasar
        posicion=0; //limpiando valor para nuevo analisis
        vieja=0;//limpiando el valor de la posicion de la materia vieja.
        coincidencia=false;//estableciendo a falso para nuevo analisis
        mismo_periodo=false;
        
        //for(int r=0; r<materia.getOrden().size(); r=r+6){//recorriendo el record academico ordenado
             for(int r=0; r<materia.getOrden().size(); r=r+11){//recorriendo el record academico ordenado
                //System.out.println("viendo: "+materia.getOrden().get(r+5)+" "+periodo_academico);

                if(materia.getOrden().get(r+5).equalsIgnoreCase(periodo_academico)){//System.out.println("iguales:...................... ");//viendo si las materias tienen el mismo periodo academico. Es para evitar analizar nuevamente las materias que ya fueron pasadas
                }else{
                     // System.out.println("Distinto:...................... ");
                           if(this.getLlenas().get(i).toString().equalsIgnoreCase(materia.getOrden().get(r)) ){//buscando coincidencia de materia por codigo e ignorando vigencia actual para que  no cuente las materias recientes ingresadas
                            coincidencia=true;//recordar buscar la materia hasta el ultimo periodo para tomar la nota de alli de la ultima vez que la vio
                            posicion=i;//posicion de la materia donde se hallo por ultima vez. ultimo periodo donde la vio. es para el analisis cuando hay coincidencias
                            vieja=r;//se almacena la posicion de la materia vieja que fue encontrada. repitencia
                           }
                }

        }////fin recorrido record academico ordenado


                

                    if(coincidencia){//si hubo coincidencia
                            System.out.println("\nAnalizando Materia por Repitencia ");
                            this.nueva_analisis_2(posicion, identidad, periodo_academico, vieja);

                    }else{//sino es la primera vez que ve la materia

                            this.nueva_analisis_1(i, identidad,periodo_academico);

                    }

                




    }//fin recorrido materias que se desean pasar






}

private void limpieza(){
 cedula="";
 codigo="";
 condicion="";
 periodo="";
 codigo_opsu="";
 seccion="";
 
 definitiva=0;
 reparacion=0;
 pensum=0;
 termino=0;
 uc=0;
 laboratorio=0;
 def_rep=0;
 inasis=0;

 carrera=""; 
 turno="";
 nucleo="";

 //private LinkedList<String> inscritos = new LinkedList<>();
//private LinkedList<Object> llenas = new LinkedList<Object>();
//private LinkedList<String> materias_condicion = new LinkedList<>();


}


/**Este metodo inicia los procedimientos de acuerdo al tipo que se elija.
 si es individual solo sera al estudiante si se elige todo entonces, sera
 toda la carrera.*/
public void pase_de_notas(String tipo, String cedula_id, String periodo_academico, String carrera_est){

    if(tipo.equalsIgnoreCase("TODO")){
        System.out.println("todo");

        this.lista_estudiantes_jornada(new conexion_base_de_datos().getConexion(),carrera_est);

        for(int i=0; i<this.getInscritos().size(); i++){//recorriendo todos los estudiantes inscritos de la carrera
             //if(i>=741){  //temporal para continuar el conteo
                System.out.println("INSCRIBIENDO: "+i+" / "+this.getInscritos().size());

                this.getLlenas().clear();
                this.getMaterias_condicion().clear();
                this.limpieza();
                materia.getOrden().clear();
                materia.getPensum().clear();

                cedula_id=this.getInscritos().get(i);//estableciendo la cedula del estudiante
                this.informacion_estudiante(new conexion_base_de_datos().getConexion(),cedula_id);
                this.cargando_materias_estudiante(new conexion_base_de_datos().getConexion(),cedula_id);
                this.cargando_pensum(carrera, new conexion_base_de_datos().getConexion());
                this.busca_condicion(new conexion_base_de_datos().getConexion(), cedula_id, periodo_academico);
                this.cargando_materias_llenadas(new conexion_base_de_datos().getConexion(), cedula_id, periodo_academico);
                this.comparacion_record_inscritas(cedula_id, periodo_academico);

        //    }//temporal para continuar el conteo
        }//fin recorrido de estudiantes inscritos de la carrera
        this.getInscritos().clear();

    }else{
                System.out.println("individual");
                this.getLlenas().clear();
                this.getMaterias_condicion().clear();
                this.limpieza();
                materia.getOrden().clear();
                materia.getPensum().clear();


                this.informacion_estudiante(new conexion_base_de_datos().getConexion(),cedula_id);
                this.cargando_materias_estudiante(new conexion_base_de_datos().getConexion(),cedula_id);
                this.cargando_pensum(carrera, new conexion_base_de_datos().getConexion());
                this.busca_condicion(new conexion_base_de_datos().getConexion(), cedula_id, periodo_academico);
                this.cargando_materias_llenadas(new conexion_base_de_datos().getConexion(), cedula_id, periodo_academico);
                this.comparacion_record_inscritas(cedula_id, periodo_academico);


    }

}












//ES OPCIONAL PARA BUSCAR QUE ESTUDIANTES AUN NO HAN PASADO POR EL PROCESO CON RESPECTO DE LOS REGISTRADOS Y LOS INSCRITOS
public void listado_no_inscritos(Connection con, String carrera, String ruta, String periodo){
    

    LinkedList<String> inscritos = new LinkedList<>();
    LinkedList<String> registrados = new LinkedList<>();
    boolean encontrado=false;
    int contador=0;
            Statement sentencia = null;
            ResultSet resultado = null;

    try {
        System.out.println("CARGANDO LAS CEDULAS DE LOS ESTUDIANTES INSCRITOS");
        //doc.escritor_textos("d://ESTUDIANTES FALTANTES/ESTUDIANTE_FALTANTES_"+carrera+".txt","CARGANDO LAS CEDULAS DE LOS ESTUDIANTES INSCRITOS");//escribiendolo en archivo de texto
        doc.escritor_textos(ruta+File.separator+carrera+File.separator+"ESTUDIANTES_FALTANTES_"+carrera+".txt","CARGANDO LAS CEDULAS DE LOS ESTUDIANTES INSCRITOS");//escribiendolo en archivo de texto
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.jornada_inscripcion WHERE periodo='"+periodo+"' AND carrera='" + carrera + "' GROUP BY cedula;");
            while(resultado.next()){
                //System.out.println(resultado.getString("cedula"));
                inscritos.add(resultado.getString("cedula"));
            }
            sentencia.close();
            resultado.close();
            
        System.out.println("CARGANDO LAS CEDULAS DE LOS ESTUDIANTES REGISTRADOS");
        //doc.escritor_textos("d://ESTUDIANTES FALTANTES/ESTUDIANTE_FALTANTES_"+carrera+".txt","CARGANDO LAS CEDULAS DE LOS ESTUDIANTES REGISTRADOS");//escribiendolo en archivo de texto
        doc.escritor_textos(ruta+File.separator+carrera+File.separator+"ESTUDIANTES_FALTANTES_"+carrera+".txt","CARGANDO LAS CEDULAS DE LOS ESTUDIANTES REGISTRADOS");//escribiendolo en archivo de texto
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_academicos WHERE carrera='" + carrera + "';");
            while(resultado.next()){
                //System.out.println(resultado.getString("cedula"));
                registrados.add(resultado.getString("cedula"));
            }
            sentencia.close();
            resultado.close();

        System.out.println("cantidades INSCRITOS:"+inscritos.size()+"  REGISTRADOS:"+registrados.size());
        //doc.escritor_textos("d://ESTUDIANTES FALTANTES/ESTUDIANTE_FALTANTES_"+carrera+".txt","cantidades INSCRITOS:"+inscritos.size()+"  REGISTRADOS:"+registrados.size());//escribiendolo en archivo de texto
        doc.escritor_textos(ruta+File.separator+carrera+File.separator+"ESTUDIANTES_FALTANTES_"+carrera+".txt","cantidades INSCRITOS:"+inscritos.size()+"  REGISTRADOS:"+registrados.size());//escribiendolo en archivo de texto

        System.out.println("ANALIZANDO QUE ESTUDIANTES NO SE HAN INSCRITOS TODAVIA");
        //doc.escritor_textos("d://ESTUDIANTES FALTANTES/ESTUDIANTE_FALTANTES_"+carrera+".txt","ANALIZANDO QUE ESTUDIANTES NO SE HAN INSCRITOS TODAVIA");//escribiendolo en archivo de texto
        doc.escritor_textos(ruta+File.separator+carrera+File.separator+"ESTUDIANTES_FALTANTES_"+carrera+".txt","ANALIZANDO QUE ESTUDIANTES NO SE HAN INSCRITOS TODAVIA");//escribiendolo en archivo de texto

                for(int r=0; r<registrados.size(); r++){//recorriendo registrados
                    encontrado=false; //inicializando x cada estudiante buscado

                        for(int i=0; i<inscritos.size();i++){//recorriendo los inscritos
                            //System.out.println("r="+registrados.get(r)+" i="+inscritos.get(i));
                            if(registrados.get(r).equalsIgnoreCase(inscritos.get(i))){//comparando cedulas registrados vs inscritos
                                encontrado=true;
                                break;
                            }

                        }//fin recorrido inscritos
//System.out.println("econtrado "+encontrado);

                   if(encontrado==false){//si no se hallo es de los que faltan
                       //System.out.println("rastreando");
                        contador=contador+1;
                        sentencia = con.createStatement();
                        resultado=sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_personales WHERE cedula='"+registrados.get(r)+"';");
                        resultado.first();
                        System.out.println(contador+"- Falta: "+registrados.get(r)+" - "+resultado.getString("nombres")+", "+resultado.getString("apellidos"));
                        //doc.escritor_textos("d://ESTUDIANTES FALTANTES/ESTUDIANTE_FALTANTES_"+carrera+".txt",contador+"- Falta: "+registrados.get(r)+" - "+resultado.getString("nombres")+", "+resultado.getString("apellidos"));//escribiendolo en archivo de texto
                        doc.escritor_textos(ruta+File.separator+carrera+File.separator+"ESTUDIANTES_FALTANTES_"+carrera+".txt",contador+"- Falta: "+registrados.get(r)+" - "+resultado.getString("nombres")+", "+resultado.getString("apellidos"));//escribiendolo en archivo de texto
                   }




                }//fin recorrido registrados



            sentencia.close();
            resultado.close();
            con.close();

System.out.println("ANALISIS TERMINADO");
//doc.escritor_textos("d://ESTUDIANTES FALTANTES/ESTUDIANTE_FALTANTES_"+carrera+".txt","ANALISIS TERMINADO");//escribiendolo en archivo de texto
doc.escritor_textos(ruta+"//"+carrera+"//ESTUDIANTES_FALTANTES_"+carrera+".txt","ANALISIS TERMINADO");//escribiendolo en archivo de texto

        } catch (SQLException ex) {
            Logger.getLogger(registro_notas.class.getName()).log(Level.SEVERE, null, ex);
        }


}








public static void main(String args[]){

        registro_notas rn = new registro_notas();
        conexion_base_de_datos cbd = new conexion_base_de_datos();
/*
        rn.lista_estudiantes_jornada(cbd.getConexion(),"INGENIERIA CIVIL");
        rn.informacion_estudiante(new conexion_base_de_datos().getConexion(),"504562");
        rn.cargando_materias_estudiante(new conexion_base_de_datos().getConexion(),"504562");
        rn.cargando_pensum("INGENIERIA CIVIL", new conexion_base_de_datos().getConexion());
        rn.busca_condicion(new conexion_base_de_datos().getConexion(), "504562", "2-2010");
        rn.cargando_materias_llenadas(new conexion_base_de_datos().getConexion(), "504562", "2-2010");
        rn.comparacion_record_inscritas("504562", "2-2010");
*/
        
       /* rn.listado_no_inscritos(cbd.getConexion(), "INGENIERIA DE TELECOMUNICACIONES");
        rn.listado_no_inscritos(cbd.getConexion(), "INGENIERIA AERONAUTICA");
        rn.listado_no_inscritos(cbd.getConexion(), "INGENIERIA CIVIL");
        rn.listado_no_inscritos(cbd.getConexion(), "INGENIERIA DE SISTEMAS");
        rn.listado_no_inscritos(cbd.getConexion(), "INGENIERIA ELECTRICA");
        rn.listado_no_inscritos(cbd.getConexion(), "INGENIERIA ELECTRONICA");
        rn.listado_no_inscritos(cbd.getConexion(), "LIC. ECONOMIA SOCIAL");
        rn.listado_no_inscritos(cbd.getConexion(), "LIC. EN ADMINISTRACION Y GESTION MUNICIPAL");
        rn.listado_no_inscritos(cbd.getConexion(), "LIC. EN ENFERMERIA");
        rn.listado_no_inscritos(cbd.getConexion(), "TSU EN ENFERMERIA");*/
}




}//fin de la clase
