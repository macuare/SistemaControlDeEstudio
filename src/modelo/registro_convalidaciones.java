/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AN
 */
public class registro_convalidaciones {


    private materias materia;
    private registro_masivo rm;
    private registro_ingenieria reging;
    private conexion_base_de_datos cbd;
    private imagenes ima;
    private registro_pdf rpdf;
    private configuracion_tablas ctablas;

    private String estudiante, carrera, vigencia, codigo_opsu;
    private int total_materias, total_uc;

    private LinkedList<String> enviando = new LinkedList<>();



    public registro_convalidaciones() {

        materia = new materias();
        rm = new registro_masivo();
        reging = new registro_ingenieria();
        cbd = new conexion_base_de_datos();
        ima = new imagenes();
        rpdf = new registro_pdf();
        ctablas = new configuracion_tablas();
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCodigo_opsu() {
        return codigo_opsu;
    }

    public void setCodigo_opsu(String codigo_opsu) {
        this.codigo_opsu = codigo_opsu;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public int getTotal_materias() {
        return total_materias;
    }

    public void setTotal_materias(int total_materias) {
        this.total_materias = total_materias;
    }

    public int getTotal_uc() {
        return total_uc;
    }

    public void setTotal_uc(int total_uc) {
        this.total_uc = total_uc;
    }

    public LinkedList<String> getEnviando() {
        return enviando;
    }

    public void setEnviando(LinkedList<String> enviando) {
        this.enviando = enviando;
    }


   














    public void datos_estudiante(Connection con, String cedula){
        reging.verificar_alumno(con, cedula);//recurperando informacion del estudiante
        this.setCarrera(reging.getCarrera());
        this.setCodigo_opsu(rpdf.opsu_sede(reging.getNucleo_extension())+rpdf.opsu_carrera(reging.getCarrera())+rpdf.opsu_turno(reging.getTurnos()));
        this.setEstudiante(reging.getEstudiante());
        this.setVigencia(reging.getVigencia());
    }

    public String proxima_vigencia(String cambio){

       return cambio.substring(5,9);//tomando la vigencia destino del cambio

    }

public void tabla_informacion(JTable tabla, LinkedList<String> datos ){
    String comparacion="1";
    DefaultTableModel dtm = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE", "CODIGO", "ASIGNATURA", "UC"});//limpiando el modelo de datos
    tabla.setModel(dtm);//insertando el modelo dentro de la tabla contenedora para que lo muestre
    this.setTotal_uc(0);//inicializando
    this.setTotal_materias(0);//inicializando




    for(int d=0; d<datos.size(); d=d+4){//recorriendo la informacion para presentarla en la tabla
                System.out.println("semestre: "+materia.getLista_autorizada().get(d+3)+" codigo: "+materia.getLista_autorizada().get(d+0)+" nombre: "+materia.getLista_autorizada().get(d+1)+" condicion: "+materia.getLista_autorizada().get(d+2));
                String credito=materia.credito_materia(materia.getLista_autorizada().get(d+0),materia.getPensum());
                    //System.out.println("COMPARANDO "+comparacion+" - "+materia.getLista_autorizada().get(d+3));

                    this.setTotal_uc(this.getTotal_uc()+Integer.valueOf(credito));//sumando las uc por equivalencias
                    this.setTotal_materias(this.getTotal_materias()+1);//materias totales aprobadas por equivalencias

                    if(!comparacion.equals(materia.getLista_autorizada().get(d + 3))){//si los semestres son distintos. Esto es para separar entre semestres
                        dtm.addRow(new Object[]{"","","",""});//se agrega un espacio en blanco para separar
                        comparacion=materia.getLista_autorizada().get(d+3);//se inicializa la variable con el nuevo valor de semestre
                    }

                    Object nuevo[]={datos.get(d+3),//semestre de la materia
                                         datos.get(d+0),//codigo de la materia
                                         datos.get(d+1),//nombre de la materia
                                         credito,//uc de la materia
                                         };
                    dtm.addRow(nuevo);//ingresando la informacion al modelo

    }//fin recorrido de LinkedList datos


    ctablas.configuracion(tabla,dtm,"SEMESTRE",100,"centrado");
    ctablas.configuracion(tabla,dtm,"CODIGO",100,"izquierda");
    ctablas.configuracion(tabla,dtm,"ASIGNATURA",600,"izquierda");
    ctablas.configuracion(tabla,dtm,"UC",50,"centrado");

  


}

/**Este metodo permite establecer la vigencia como apoyo a la convalidacion.
   establece a la vigencia destino */
public void vigencia_rapida(Connection con, String cedula ,int destino){
         Statement sentencia = null;


        try {

            con.setAutoCommit(false);
            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.datos_academicos SET pensum="+destino+" WHERE cedula='"+cedula+"';");
            con.commit();
            sentencia.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex);

            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex1);
                }

            }


        }finally{
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}






    public void convalidacion_estudiante(String identidad, String especialidad, String cambio, JTable presentacion){

        rm.limpiando();
        this.getEnviando().clear();//limpiando la variable


            materia.getOrden().clear(); //limpiando la variable que almcena el record acomodado por periodo academico
//            materia = new materias();//es para limpiar todas las variables

            System.out.println("CEDULA DEL ESTUDIANTE: "+identidad+" CARRERA:"+especialidad+"\n");

            if(identidad.isEmpty()){//validando que la casilla de la cedula no este vacia o este buena
                //ima.mensaje_informacion("NO DEJE LA CASILLA DE LA CEDULA EN BLANCO", "ADVERTENCIA", "exclamacion", "png") ;

                //  ipcion.getCedula().setText(null);
                //  ipcion.getEstudiante().setText(null);
                //  ipcion.getCarrera().setText(null);

            }else{

               //ipcion.getInscribir().setEnabled(true);//habilitando el boton de inscribir despues de presionar validar

                reging.verificar_alumno(cbd.getConexion(), identidad);//verificando que el alumno se encuentre registrado en el sistema
                                                                                        //para luego cargar su record y darle el resto de las opciones

                    if(reging.getControl()==0){//en caso de que el alumno no se halla actualizado en el sistema
                //      ipcion.getEstudiante().setText(reging.getEstudiante());
                  //    ipcion.getCarrera().setText(reging.getCarrera());


                      for(int v=0; v<=2; v++){//revisando la base de datos 3 veces para hallar en que vigencia se encuentra el alumno
                          System.out.println("viendo"+v);
                          materia.setSeleccion_pensum(v);
                          reging.setReconocimiento(v);
                          //reging.buscar_materias(cbd.getConexion(), identidad,materia.notas_especialidad(especialidad,reging));//cargando las materias del alumno
                          reging.buscar_materias_alternativo(cbd.getConexion(), identidad,materia.notas_especialidad(reging));//cargando las materias del alumno

                          //if(reging.getPeriodo().isEmpty()){}else{break;}
                          if(reging.getRecord().isEmpty()){}else{break;}

                      }//fin revisando la base de datos 3 veces

                          System.out.println("para materia: "+reging.getReconocimiento()+" v: "+materia.getSeleccion_pensum());

                          //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera()));//cargando las materias del pensum



                    //   ipcion.setDeposito(reging.academico());//configurando la tabla jtable
                    //   ipcion.getDesempeño_academico().setModel(ipcion.getDeposito());




                            //if(reging.getPeriodo().isEmpty()){
                            if(reging.getRecord().isEmpty()){
                                        ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "precaucion", "png");

                                        presentacion.setModel(new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE", "CODIGO", "ASIGNATURA", "UC"}));//limpiando el modelo de datos)

                                        materia.setNuevo_ingreso(1);
                                       // materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum_equivalencias(cbd.getConexion(),materia.carrera_equivalencia(reging.getCarrera(),cambio, reging.getTurnos()));//cargando las materias de equivalencias
                            }
                            else{
                                        //materia.materias_pensum(cbd.getConexion(),materia.carrera(reging.getCarrera(),reging.getTurnos()));//cargando las materias del pensum
                                        materia.materias_pensum_equivalencias(cbd.getConexion(),materia.carrera_equivalencia(reging.getCarrera(),cambio,reging.getTurnos()));//cargando las materias de equivalencias

                                            //---------ordenando las materias por periodo academico
                                            //for(int b=0; b<reging.getCodigo().size();b++){
                                            for(int b=0; b<reging.getRecord().size();b=b+11){
                                              //materia.ordenamiento_año(reging.getPeriodo().get(b));
                                              materia.ordenamiento_año(reging.getRecord().get(b+5));
                                              }

                                                //materia.ordenamiento_materias(reging);//ordenando las materias del alumno por periodo academico
                                                materia.ordenamiento_materias_alternativos(reging);//ordenando las materias del alumno por periodo academico
                                                reging.setComp_per(materia.getOrden().get(5));//inicializando la variable con el primer periodo inscrito

                                                //for(int i=0; i<materia.getOrden().size(); i=i+6){
                                                 for(int i=0; i<materia.getOrden().size(); i=i+11){

                                                        if(reging.getComp_per().equalsIgnoreCase(materia.getOrden().get(i+5))){ }
                                                        else{//para separar entre periodos academicos cuando se presenta en la tabla
                                                         //ipcion.getDeposito().addRow(new Object[]{null,null,null,null,null});
                                                         //ipcion.getDeposito().fireTableDataChanged();
                                                         reging.setComp_per(materia.getOrden().get(i+5));
                                                          }

                                                       Object nuevo[]={materia.getOrden().get(i),//codigo de la materia
                                                                      materia.getOrden().get(i+1),//nombre de la materia
                                                                      materia.getOrden().get(i+2),//definitiva de la materia
                                                                      materia.getOrden().get(i+3),//nota de la reparacion
                                                                      materia.getOrden().get(i+4),//condicion de la materia
                                                                      materia.getOrden().get(i+5) //periodo academico de la materia
                                                                    };

                                                                         //ipcion.getDeposito().addRow(nuevo);
                                                                         //ipcion.getDeposito().fireTableDataChanged();
                                                }


                                              //--------------------------------------------------FIN




                                         }  //validacion de que halla informacion del estudiante en cuanto a notas
                                           //en la base de datos


                                                            //ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CODIGO",5,"centrado");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"MATERIA",300,"izquierda");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"DEFINITIVA",5,"centrado");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"REPARACION",5,"centrado");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"CONDICION",10,"izquierda");
                                                           // ctablas.configuracion(ipcion.getDesempeño_academico(),ipcion.getDeposito(),"PERIODO",10,"centrado");


                                                           materia.materias_preladas_y_no_preladas();

                                                          //materia.esquema_prelacion();
                                                         materia.esquema_prelacion_equivalencias();
                                                         materia.esquema_normal();
                                                          //materia2.servicio_comunitario(materia);
                                                        //CREANDO UNA VENTANA PARA VISUALIZAR LAS MATERIAS QUE EL ESTUDIANTE PUEDE INSCRIBIR


                                                          System.out.println("\nMATERIAS AUTORIZADAS");
                                                          //System.out.println("INSERT INTO control_de_estudio.equivalencias_notas_todos (`POSICION`,`CEDULA`,`CODMAT`,`SECCION2`,`SECCION`,`TERMINO`,`PERACA`,`CONDIC`,`NOTREP`,`NOTDEF`,`DEFREP`,`PORINA`,`CODESP`,`CREDI`,`PENSUM`,`OBSERVACIÓ`,`FECHA_HORA`,`ADMINISTRA`,`NOTLAB`) VALUES");

                                                          this.tabla_informacion(presentacion, materia.getLista_autorizada()); //ENVIANDO INFORMACION A LA TABLA DE LAS POSIBLES EQUIVALENCIAS
                                                          this.vigencia_rapida(cbd.getConexion(), identidad, Integer.valueOf(this.proxima_vigencia(cambio)));//actualizando el pensum en datos academicos
                                                          
                                                          for(int h=0; h<materia.getLista_autorizada().size();h=h+4){//buscando todas las materias autorizadas

                                                              // System.out.println("semestre: "+materia.getLista_autorizada().get(h+3)+" codigo: "+materia.getLista_autorizada().get(h+0)+" nombre: "+materia.getLista_autorizada().get(h+1)+" condicion: "+materia.getLista_autorizada().get(h+2));
                                                              String credito=materia.credito_materia(materia.getLista_autorizada().get(h+0),materia.getPensum());
                                                              //System.out.println("(NULL,'"+identidad+"','"+materia.getLista_autorizada().get(h+0)+"','','',"+Integer.valueOf(materia.getLista_autorizada().get(h+3))+",'1-2011','"+materia.getLista_autorizada().get(h+2)+"',0,0,0,0,'',"+credito+","+this.proxima_vigencia(cambio)+",'CAMBIADO DE PENSUM "+cambio+"','','',0),");

                                                              //System.out.println("INSERT INTO control_de_estudio.equivalencias_notas_todos (
                                                             

                                                         //     this.getEnviando().add("(NULL,'"+identidad+"','"+materia.getLista_autorizada().get(h+0)+"','','',"+Integer.valueOf(materia.getLista_autorizada().get(h+3))+",'1-2011','"+
                                                           //           materia.getLista_autorizada().get(h+2)+"',0,0,0,0,'"+this.getCodigo_opsu()+"',"+credito+","+this.proxima_vigencia(cambio)
                                                             //         +",'CAMBIADO DE PENSUM "+cambio+"','','',0),");

                                                                this.getEnviando().add(identidad);//CEDULA
                                                                this.getEnviando().add(materia.getLista_autorizada().get(h+0));//CODMAT
                                                                this.getEnviando().add("");//SECCION2
                                                                this.getEnviando().add("");//SECCION
                                                                this.getEnviando().add(materia.getLista_autorizada().get(h+3));//TERMINO
                                                                this.getEnviando().add("1-2011");//PERACA
                                                                this.getEnviando().add(materia.getLista_autorizada().get(h+2));//CONDIC
                                                                this.getEnviando().add("0");//NOTREP
                                                                this.getEnviando().add("0");//NOTDEF
                                                                this.getEnviando().add("0");//DEFREP
                                                                this.getEnviando().add("0");//PORINA
                                                                this.getEnviando().add(this.getCodigo_opsu());//CODESP
                                                                this.getEnviando().add(credito);//CREDI
                                                                this.getEnviando().add(this.proxima_vigencia(cambio));//PENSUM
                                                                this.getEnviando().add("CAMBIADO DE PENSUM "+cambio);//OBSERVACIÓ
                                                                this.getEnviando().add("");//FECHA_HORA
                                                                this.getEnviando().add("");//ADMINISTRA
                                                                this.getEnviando().add("0");//NOTLAB



                                                               //this.conteo_materias_semestre(Integer.valueOf(materia.getLista_autorizada().get(h+3)));


                                                           }

                                                         // ctablas.tabla_habilitadas(ipcion.getAutorizadas(), materia.getLista_autorizada());//escribiendo en la tabla de materias autorizadas
                                                         // ctablas.configuracion(ipcion.getAutorizadas(),null,"SEMESTRE", 5, "centrado");
                                                          //ctablas.configuracion(ipcion.getAutorizadas(),null,"CODIGO", 5, "izquierda");
                                                         // ctablas.configuracion(ipcion.getAutorizadas(),null,"MATERIA", 300, "izquierda");
                                                         // ctablas.configuracion(ipcion.getAutorizadas(),null,"CONDICION", 20, "centrado");


                                                          //ipcion.getVigencias().setText(materia.getVigencia());//colocando la vigencia del pensum a la que pertenece el alumno



 System.out.println("visor 1 "+materia.getLista_autorizada().size());

                                                          //materia.getOrden().clear();
                                                          materia.getAux1().clear();
                                                          materia.getAux2().clear();
                                                         /* reging.getCodigo().clear();
                                                          reging.getMateria().clear();
                                                          reging.getDefinitiva().clear();
                                                          reging.getReparacion().clear();
                                                          reging.getCondicion().clear();
                                                          reging.getPeriodo().clear();*/

                                                          reging.getRecord().clear();//record alternativo

                                                          //materia.getPensum().clear();
                                                          materia.getLlenado().clear();
                                                          materia.getPosicion().clear();
                                                          materia.getPreladas().clear();
                                                          materia.getNo_preladas().clear();
                                                        //  materia.getLista_autorizada().clear();

                    }else{
                          //ipcion.getCedula().setText(null);
                          //ipcion.getEstudiante().setText(null);
                          //ipcion.getCarrera().setText(null);
                         // ipcion.getAutorizadas().setModel(new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","CONDICION"}));
                          //ipcion.getDesempeño_academico().setModel(new DefaultTableModel(new Object [][]{},new String [] {"CODIGO", "MATERIA", "DEFINITIVA", "REPARACION", "CONDICION", "PERIODO"}));

                          }//si el estudiante no esta en el sistema no se muestra nada


                    }//fin validacion de la casilla cedula este vacia



 //andy = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","UC","SECCION","HORARIO","DOCENTE","CONDICION"});
 //ipcion.getSecciones().setModel(andy);
// materia.materias_inscritas_bd(ipcion.getSecciones(),andy,cbd.getConexion(),ipcion.getCedula().getText());
  //                  ctablas.configuracion(ipcion.getSecciones(),andy,"SEMESTRE",2,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"CODIGO",5,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"MATERIA",150,"izquierda");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"UC",2,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"SECCION",20,"centrado");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"HORARIO",30,"izquierda");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"DOCENTE",20,"izquierda");
   //                 ctablas.configuracion(ipcion.getSecciones(),andy,"CONDICION",5,"centrado");
System.out.println("visor 2 "+materia.getLista_autorizada().size());
}









public void envio_equivalencia_bd(Connection con, LinkedList<String> d, String vigencia, JTable tabla){

  //  Statement sentencia = null;
    PreparedStatement preparada = null;


        try {
         /*   System.out.println("Niveles de Aislamiento: " );
            System.out.println("TRANSACTION_NONE "+con.TRANSACTION_NONE);
            System.out.println("TRANSACTION_READ_COMMITTED "+con.TRANSACTION_READ_COMMITTED);
            System.out.println("TRANSACTION_READ_UNCOMMITTED "+con.TRANSACTION_READ_UNCOMMITTED);
            System.out.println("TRANSACTION_REPEATABLE_READ "+con.TRANSACTION_REPEATABLE_READ);
            System.out.println("TRANSACTION_SERIALIZABLE "+con.TRANSACTION_SERIALIZABLE);*/
            
            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);//nivel mas fuerte de aislamiento
            System.out.println("Nivel de Aislamiento: " + con.getTransactionIsolation());

            con.setAutoCommit(false);//desabilitando el auto commit para manejo de transacciones

            preparada = con.prepareStatement("INSERT INTO control_de_estudio.cem_notas_alumnos_todas_carreras_"+this.proxima_vigencia(vigencia)+" VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            for(int x=0; x<d.size(); x=x+18){
                preparada.clearParameters();
                preparada.setString(1,d.get(x+0) );//CEDULA
                preparada.setString(2,d.get(x+1) );//CODMAT
                preparada.setString(3,d.get(x+2) );//SECCION2
                preparada.setString(4,d.get(x+3) );//SECCION
                preparada.setInt(5,Integer.valueOf(d.get(x+4)) );//TERMINO
                preparada.setString(6,d.get(x+5) );//PERACA
                preparada.setString(7,d.get(x+6) );//CONDIC
                preparada.setInt(8,Integer.valueOf(d.get(x+7)) );//NOTREP
                preparada.setInt(9,Integer.valueOf(d.get(x+8)) );//NOTDEF
                preparada.setInt(10,Integer.valueOf(d.get(x+9)) );//DEFREP
                preparada.setInt(11,Integer.valueOf(d.get(x+10)) );//PORINA
                preparada.setString(12,d.get(x+11).substring(2, 4) );//CODESP
                preparada.setInt(13,Integer.valueOf(d.get(x+12)) );//CREDI
                preparada.setInt(14,Integer.valueOf(d.get(x+13)) );//PENSUM
                preparada.setString(15,d.get(x+14) );//OBSERVACIÓ
                preparada.setString(16,d.get(x+15) );//FECHA_HORA
                preparada.setString(17,d.get(x+16) );//ADMINISTRA
                preparada.setInt(18,Integer.valueOf(d.get(x+17)) );//NOTLAB
                preparada.addBatch();
                //System.out.println("revisando "+d.get(x+11));
            }

            preparada.clearParameters();//limpiando parametros
            preparada.executeBatch();//ejecutando lotes

            con.commit();//haciendo permanente los cambios y liberando la base de datos
            preparada.clearBatch();//limpiando listas de sentencias
            preparada.close();//cerrando la sentencia
            ima.mensaje_informacion("PROCESO DE EQUIVALENCIA ALMACENADO CON EXITO...!!!", "CONVALIDACION", "convalidar", "png");
      
            

        } catch (SQLException ex) {
            Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex);
            if(con!=null){
                System.err.println("Error en el envio de informacion. Transaccion revertida. Rolling Back");
                try {
                    con.rollback();//regresando los cambios
                    ima.mensaje_informacion("Error enviando informacion a la base de datos. Devolviendo cambios. Rolling Back\n"+ex.getMessage(), "ERROR SQL", "error", "png");
                } catch (SQLException ex1) {
                    Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex1);
                    
                }

            }
        }finally{
            try {
                con.setAutoCommit(true);
                con.close();
                tabla.setModel(new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE", "CODIGO", "ASIGNATURA", "UC"}));//limpiando el modelo de datos);

            } catch (SQLException ex2) {
                Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }






}
















}//fin de la clase
