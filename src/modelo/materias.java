/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.horario;



/**
 *
 * @author AURORA
 */
public class materias {




private LinkedList <String> pensum = new LinkedList<>();
private LinkedList <String> orden = new LinkedList<>();
private LinkedList <String> aux1 = new LinkedList<>();//materias normales primer periodo
private LinkedList <String> aux2 = new LinkedList<>();//materias normales segundo periodo
private LinkedList <String> auxI1 = new LinkedList<>();//intensivos primer periodo
private LinkedList <String> auxI2 = new LinkedList<>();//intensivos segundo periodo
private LinkedList <String> auxE1 = new LinkedList<>();//equivalencias primer periodo
private LinkedList <String> auxE2 = new LinkedList<>();//equivalencias segundo periodo
private LinkedList <Object> llenado = new LinkedList<Object>();
private LinkedList <Integer> posicion = new LinkedList<Integer>();
private LinkedList <String> preladas = new LinkedList<>();
private LinkedList <String> no_preladas = new LinkedList<>();
private LinkedList <String> lista_autorizada = new LinkedList<>();
private LinkedList <String> lista_ofertadas = new LinkedList<>();
private LinkedList <String> pdf_mi = new LinkedList<>();
private LinkedList <String> coprelaciones = new LinkedList<>();
private LinkedList <String> doble_copre = new LinkedList<>();
private LinkedList <String> vigilante = new LinkedList<>();
private LinkedList <String> ser_comunitario = new LinkedList<>();
private LinkedList <String> electivas = new LinkedList<>();
private LinkedList <String> ele_cur = new LinkedList<>();
private LinkedList <String> ele_repro = new LinkedList<>();
private LinkedList <String> eaup = new LinkedList<>();//electiva autorizada usada del pensum
private LinkedList <String> elect_orden = new LinkedList<>();//electiva autorizada usada del pensum
private LinkedList <String> asociacion_ordenada = new LinkedList<>();//asociacion electivas genericas reales
private LinkedList <String> electxsem = new LinkedList<>();//guarda la cantidad de electivas tecnicas o no por semestre

/**variable para guardar las materias electivas ya usadas para revisar por cada semestre. evitar usar la misma por cada revision*/
LinkedList<String> usadas = new LinkedList<>();

private String asignatura, condicion_materia,vigencia;
private String periodo=null;
private int mas_pequeño=0, mas_grande=0, cant_1=0, cant_2=0, credito=0, comp_semestre=0,cupos=0,seleccion_pensum, nuevo_ingreso=0, elec_peque=0, elec_grande=0;
private boolean comprobacion, comprobacion2,comprobacion3, hay_cupos=false;
private int max_sem_virtual=0;

private imagenes ima;



    public materias() {
        ima = new imagenes();
    }

/**Registro que almacena la cantidad de materias electivas tecnicas o no del mismo tipo por semestre:
 <br>semestre=0<br>tipo=1<br>cantidad=2*/
    public LinkedList<String> getElectxsem() {
        return electxsem;
    }

    public void setElectxsem(LinkedList<String> electxsem) {
        this.electxsem = electxsem;
    }
    
    public LinkedList<String> getAsociacion_ordenada() {
        return asociacion_ordenada;
    }

    public void setAsociacion_ordenada(LinkedList<String> asociacion_ordenada) {
        this.asociacion_ordenada = asociacion_ordenada;
    }


    public int getElec_grande() {
        return elec_grande;
    }

    public void setElec_grande(int elec_grande) {
        this.elec_grande = elec_grande;
    }

    public int getElec_peque() {
        return elec_peque;
    }

    public void setElec_peque(int elec_peque) {
        this.elec_peque = elec_peque;
    }

/**arreglo donde se guardan las materias del pensum:<br>codigo=0<br>semestre=1<br>asignatura=2<br>uc=3<br>prelacion_1=4<br>prelacion_2=5<br>prelacion_3=6 prelacion_4=7<br>prelacion_5=8<br>prelacion_6=9
 */
    public LinkedList<String> getPensum() {
        return pensum;
    }

    public void setPensum(LinkedList<String> pensum) {
        this.pensum = pensum;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }


   /**Valores guardados en el LinkedList. Posiciones;
     <br> 0 = codigo de la materia <br> 1 = nombre de la materia <br> 2 = definitiva de la materia <br> 3 = nota de reparacion <br> 4 = condicion de la materia <br> 5 = periodo academico de la materia <br> 6 = definitiva reparacion de la materia <br> 7 = porcentaje de inasistencia de la materia <br> 8 = codigo especialidad de la materia <br> 9 = nota de laboratorio de la materia <br> 10 =  semestre de la materia */
    public LinkedList<String> getOrden() {
        return orden;
    }

    public void setOrden(LinkedList<String> orden) {
        this.orden = orden;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getMas_grande() {
        return mas_grande;
    }

    public void setMas_grande(int mas_grande) {
        this.mas_grande = mas_grande;
    }

    public int getMas_pequeño() {
        return mas_pequeño;
    }

    public void setMas_pequeño(int mas_pequeño) {
        this.mas_pequeño = mas_pequeño;
    }

    public LinkedList<String> getAux1() {
        return aux1;
    }

    public void setAux1(LinkedList<String> aux1) {
        this.aux1 = aux1;
    }

    public LinkedList<String> getAux2() {
        return aux2;
    }

    public void setAux2(LinkedList<String> aux2) {
        this.aux2 = aux2;
    }

    public LinkedList<String> getAuxI1() {
        return auxI1;
    }

    public void setAuxI1(LinkedList<String> auxI1) {
        this.auxI1 = auxI1;
    }

    public LinkedList<String> getAuxI2() {
        return auxI2;
    }

    public void setAuxI2(LinkedList<String> auxI2) {
        this.auxI2 = auxI2;
    }

    public LinkedList<String> getAuxE1() {
        return auxE1;
    }

    public void setAuxE1(LinkedList<String> auxE1) {
        this.auxE1 = auxE1;
    }

    public LinkedList<String> getAuxE2() {
        return auxE2;
    }

    public void setAuxE2(LinkedList<String> auxE2) {
        this.auxE2 = auxE2;
    }

    
    public int getCant_1() {
        return cant_1;
    }

    public void setCant_1(int cant_1) {
        this.cant_1 = cant_1;
    }

    public int getCant_2() {
        return cant_2;
    }

    public void setCant_2(int cant_2) {
        this.cant_2 = cant_2;
    }

    /** Variable que contiene los siguientes elementos <br>
     posicion 0 = carrera <br>
     posicion 1 = semestre <br>
     posicion 2 = seccion <br>
     posicion 3 = dia <br>
     posicion 4 = hora_inicio <br>
     posicion 5 = hora_fin <br>
     posicion 6 = periodo <br>
     posicion 7 = materia <br>
     posicion 8 = docente
     */
    public LinkedList<Object> getLlenado() {
        return llenado;
    }

    public void setLlenado(LinkedList<Object> llenado) {
        this.llenado = llenado;
    }

    public LinkedList<Integer> getPosicion() {
        return posicion;
    }

    public void setPosicion(LinkedList<Integer> posicion) {
        this.posicion = posicion;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public LinkedList<String> getNo_preladas() {
        return no_preladas;
    }

    public void setNo_preladas(LinkedList<String> no_preladas) {
        this.no_preladas = no_preladas;
    }

    public LinkedList<String> getPreladas() {
        return preladas;
    }

    public void setPreladas(LinkedList<String> preladas) {
        this.preladas = preladas;
    }

     /**Distribuicion de la variable:<br>
      * posicion 0 = codigo<br>
      * posicion 1 = nombre<br>
      * posicion 2 = condicion<br>
      * posicion 3 = semestre
     */
    public LinkedList<String> getLista_autorizada() {
        return lista_autorizada;
    }

    public void setLista_autorizada(LinkedList<String> lista_autorizada) {
        this.lista_autorizada = lista_autorizada;
    }

    public int getComp_semestre() {
        return comp_semestre;
    }

    public void setComp_semestre(int comp_semestre) {
        this.comp_semestre = comp_semestre;
    }

    public LinkedList<String> getLista_ofertadas() {
        return lista_ofertadas;
    }

    public void setLista_ofertadas(LinkedList<String> lista_ofertadas) {
        this.lista_ofertadas = lista_ofertadas;
    }

    public LinkedList<String> getPdf_mi() {
        return pdf_mi;
    }

    public void setPdf_mi(LinkedList<String> pdf_mi) {
        this.pdf_mi = pdf_mi;
    }

    public String getCondicion_materia() {
        return condicion_materia;
    }

    public void setCondicion_materia(String condicion_materia) {
        this.condicion_materia = condicion_materia;
    }

    public boolean isComprobacion() {
        return comprobacion;
    }

    public void setComprobacion(boolean comprobacion) {
        this.comprobacion = comprobacion;
    }

    public boolean isComprobacion2() {
        return comprobacion2;
    }

    public void setComprobacion2(boolean comprobacion2) {
        this.comprobacion2 = comprobacion2;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public boolean isHay_cupos() {
        return hay_cupos;
    }

    public void setHay_cupos(boolean hay_cupos) {
        this.hay_cupos = hay_cupos;
    }

    public boolean isComprobacion3() {
        return comprobacion3;
    }

    public void setComprobacion3(boolean comprobacion3) {
        this.comprobacion3 = comprobacion3;
    }

    public int getSeleccion_pensum() {
        return seleccion_pensum;
    }

    public void setSeleccion_pensum(int seleccion_pensum) {
        this.seleccion_pensum = seleccion_pensum;
    }

    public int getNuevo_ingreso() {
        return nuevo_ingreso;
    }

    public void setNuevo_ingreso(int nuevo_ingreso) {
        this.nuevo_ingreso = nuevo_ingreso;
    }

    public LinkedList<String> getCoprelaciones() {
        return coprelaciones;
    }

    public void setCoprelaciones(LinkedList<String> coprelaciones) {
        this.coprelaciones = coprelaciones;
    }

    public LinkedList<String> getDoble_copre() {
        return doble_copre;
    }

    public void setDoble_copre(LinkedList<String> doble_copre) {
        this.doble_copre = doble_copre;
    }

    public LinkedList<String> getVigilante() {
        return vigilante;
    }

    public void setVigilante(LinkedList<String> vigilante) {
        this.vigilante = vigilante;
    }

    public int getMax_sem_virtual() {
        return max_sem_virtual;
    }

    public void setMax_sem_virtual(int max_sem_virtual) {
        this.max_sem_virtual = max_sem_virtual;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    /**Servicio Comunitario distribucion:<br>
       posicion 0 = codigo<br>
       posicion 1 = semestre<br>
       posicion 2 = asignatura<br>
     * posicion 3 = uc<br>
     * posicion 4 = prelacion_1<br>
     * posicion 5 = prelacion_2<br>
     * posicion 6 = prelacion_3<br>
     * posicion 7 = prelacion_4<br>
     * posicion 8 = prelacion_5<br>
     * posicion 9 = prelacion_6
     */
    public LinkedList<String> getSer_comunitario() {
        return ser_comunitario;
    }

    public void setSer_comunitario(LinkedList<String> ser_comunitario) {
        this.ser_comunitario = ser_comunitario;
    }
/** Descripcion de la variable que contiene informacion de las electivas <br>
       posicion 0 = codigo <br>
       posicion 1 = asignatura <br>
       posicion 2 = uc <br>
       posicion 3 = tipo <br>
 */
    public LinkedList<String> getElectivas() {
        return electivas;
    }

    public void setElectivas(LinkedList<String> electivas) {
        this.electivas = electivas;
    }

    public LinkedList<String> getEle_cur() {
        return ele_cur;
    }

    public void setEle_cur(LinkedList<String> ele_cur) {
        this.ele_cur = ele_cur;
    }

    public LinkedList<String> getUsadas() {
        return usadas;
    }

    public void setUsadas(LinkedList<String> usadas) {
        this.usadas = usadas;
    }

    public LinkedList<String> getEle_repro() {
        return ele_repro;
    }

    public void setEle_repro(LinkedList<String> ele_repro) {
        this.ele_repro = ele_repro;
    }

    /**Variable que almacena las materias electivas autorizadas usadas del pensum para evitar colocar la condicion
     de la electiva anterior a la actual segun el semestre<br>
     * posicion 0 = codigo_electiva_pensum<br>
     * posicion 1 = codigo_electiva_real
     */
    public LinkedList<String> getEaup() {
        return eaup;
    }

    public void setEaup(LinkedList<String> eaup) {
        this.eaup = eaup;
    }
/** Descripcion de la variable que contiene informacion de las electivas ordenadas aprobadas y reprobadas <br>
       posicion 0 = semestre <br>
       posicion 1 = generica (segun pensum) <br>
       posicion 2 = real (segun tabla electivas reales)<br>
       posicion 3 = periodo academico <br>
       posicion 4 = aprobado o reprobado <br>
 */
    public LinkedList<String> getElect_orden() {
        return elect_orden;
    }

    public void setElect_orden(LinkedList<String> elect_orden) {
        this.elect_orden = elect_orden;
    }





    
    


    

    public void mensajeria_simple(String materia, String coprelacion){

        ima.mensaje_informacion("Es obligatorio inscribir  \""+coprelacion+"\", \nya que coprela la materia que selecciono  \""+materia+"\"", "COPRELACIONES", "alto", "png");
   
    }









/**Este metodo se encarga de transformar la hora normal
    a hora militar como por ejemplo 1:30pm a 1300 horas.
 El argumento recorrido es la posicion del LinkedList llenado*/
public int conversion_hora(int recorrido, LinkedList<Object> v){
 //System.out.println("[Metodo conversion_hora]");
    int hi=0;
 String mer_1=null;
 final int h_militar=1200;
    
     hi = Integer.parseInt(v.get(recorrido+4).toString().substring(0, 5).replace(":", ""));
     mer_1 = v.get(recorrido+4).toString().substring(v.get(recorrido+4).toString().length()-2);
     if(mer_1.equalsIgnoreCase("pm") && hi>=100 && hi<1200)hi=hi+h_militar;

     //   System.out.println("HORA: "+hi);
     //   System.out.println("MERIDIANO: "+mer_1);

return hi;
}



/**Este metodo es para saber exactamente en que turno se encuentra ubicada la materia
 utiliza el metodo de conversion_hora para determinarlo*/
public boolean turnos_materias(int hora, String turno){
//System.out.println("[Metodo turnos_materias]");
    boolean coincidencia = false;
    String t = null;
    
    if(hora >= 1800){t = "NOCTURNO";}else{t = "DIURNO";}
    //if(hora >= 1200){t = "NOCTURNO";}else{t = "DIURNO";} // caso extraordinario del vigia
    if(t.equalsIgnoreCase(turno)) coincidencia = true;
    
    coincidencia = true; //EXPERIMENTAL: habilitando materias tanto del diurno como del nocturno
 return coincidencia;
}



//METODOS

/**Elimina el CO de la materia coprelada para solo tener el codigo
 original que corresponde la materia lo cual devuelve este metodo*/
public String coprelacion(String codigo){ //elimina la palabra "CO" de la materia para convertilo en el codigo de 8 caracteres
//System.out.println("[Metodo coprelaciones]");
    String codigo_nuevo=null;


    if(codigo.startsWith("CO-")){
       codigo_nuevo= codigo.substring(3);//quitando el co- a la materia para tener su codigo original para ello se corren 3 espacios
  

    }else{
    codigo_nuevo=codigo;
   

    }

return codigo_nuevo;
}

/**Este metodo solo se emplea cuando se analisa nuevamente al estudiante y tiene materias inscritas en el periodo
 actual de la jornada de inscripcion. Es para que cargue nuevamente las comparaciones de las materias copreladas
 y doblemente copreladas.*/
public void analisis_post_coprelacion(horario hor){
    String materia_aux="";

    if(hor.getSecciones().getRowCount()>0){//al menos exista una materia inscrita sino se ignora el analisis

        for(int a=0; a<hor.getSecciones().getRowCount();a++){//recorriendo las materias inscritas
           
            if(materia_aux.equalsIgnoreCase(hor.getSecciones().getValueAt(a,2).toString())){}else{

                System.out.println("coprelaciones-doblecoprelacion: "+hor.getSecciones().getValueAt(a,2).toString());
                this.analisis_coprelacion(hor,hor.getSecciones().getValueAt(a,2).toString());//se obtiene el contenido de la columna 2 el de asignatura
                materia_aux=hor.getSecciones().getValueAt(a,2).toString();

            }

        }//fin recorrido las materias inscritas

    }




}



 /**Metodo que permite el analisis de las materias que estan copreladas o que son doblemente copreladas */
public boolean analisis_coprelacion(horario hor, String materia_alternativa){//analisis posterior que se hace de la coprelacion, en el momento de inscribirla
    System.out.println("[Metodo analisis_coprelacion]");
    boolean existe=false;
    String materia=null;
    String coprelacion=null;
    boolean fase_1=false, fase_2=false, fase_3=false;

    if(materia_alternativa.equalsIgnoreCase("nada")){
         materia=hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString();//materia seleccionada del horario a ser analizada
    }else{
         materia=materia_alternativa;
    }

        //materia=hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString();//materia seleccionada del horario a ser analizada


    //for(int cop=0; cop<hor.getTrapaso_coprelaciones().size(); cop=cop+3){System.out.println(hor.getTrapaso_coprelaciones().get(cop)+" - "+hor.getTrapaso_coprelaciones().get(cop+1)+" - "+hor.getTrapaso_coprelaciones().get(cop+2));}
    for(int cop=0; cop<hor.getTrapaso_coprelaciones().size(); cop=cop+3){System.out.println(hor.getTrapaso_coprelaciones().get(cop)+" - "+hor.getTrapaso_coprelaciones().get(cop+1)+" - "+hor.getTrapaso_coprelaciones().get(cop+2));}
  //FASE 1....Verificando si la materia a inscribir coincide con las coprelaciones


     for(int cop=0; cop<hor.getTrapaso_coprelaciones().size(); cop=cop+3){//recorriendo las coprelaciones
            if(materia.equalsIgnoreCase(hor.getTrapaso_coprelaciones().get(cop+1))){

                 coprelacion=hor.getTrapaso_coprelaciones().get(cop+2);//codigo de la coprelacion
                 //coprelacion=this.codigo_materia(hor.getTrapaso_coprelaciones().get(cop+2), hor.getPensum_traspaso());//traduce el codigo al nombre
                 fase_1=true;
                 System.out.println("esta materia tiene coprelaciones "+coprelacion);
                 this.getVigilante().add(materia);//se guarda estas materias doblemente copreladas para luego
                 this.getVigilante().add(coprelacion);//en el metodo autentificador hacer los estudios

                   this.mensajeria_simple(materia, this.codigo_materia(coprelacion, hor.getPensum_traspaso())); //NOTIFICACION DE QUE DEBE INSCRIBIR LA COPRELACION

                 break;

            }else{fase_1=false;}
     }//fin recorrido del registro donde se guardan las coprelaciones


    //..............................................................................................


/*


if(fase_1==true){//la fase 2 y 3 solo se ejecutaran siempre y cuando la materia en la fase 1 sea una coprelacion
    
          //FASE 2....Verificando si la coprelacion la tiene en el record academico
        System.out.println("analisando la Fase 2");
        
             for(int rec=0; rec<hor.getTrapaso_record().size(); rec=rec+6){//recorriendo el record academico
                     //System.out.println(hor.getTrapaso_record().get(rec+1));
                     System.out.println("recorrido: "+((rec/6)+1)+"  limite"+hor.getTrapaso_record().size()/6);

                    if(coprelacion.equalsIgnoreCase(hor.getTrapaso_record().get(rec+2))){//compara solo el codigo de la materia
                        System.out.println("se hallo la coprelacion en el record academico");
                        fase_2=true;
                        break;

                    }else{fase_2=false;
                              if(((rec/6)+1)>=hor.getTrapaso_record().size()/6){ //si se termino de revisar el record y no se encontro la materia q coprela
                                  System.out.println("NO se hallo la coprelacion en el record academico");
                              }
                    }
             }//fin recorrido del record academico......fase_2
System.out.println("Fase 2"+fase_2);

           //FASE 3....Revisando si la coprelacion se encuentra entre las materias inscritas

           if(fase_2==false){//en caso de que no encuentre la coprelacion en el record
                System.out.println("analisando la Fase 3");
                
             for(int s=0; s<hor.getSecciones().getRowCount(); s++){//recorriendo la tabla de las materias inscritas
                 System.out.println("contenido: "+hor.getSecciones().getValueAt(s, 1));

                     if(coprelacion.equalsIgnoreCase(hor.getSecciones().getValueAt(s, 1).toString())){
                        System.out.println("se hallo la coprelacion entre las materias inscritas");
                        fase_3=true;
                        break;
                     }else{fase_3=false;
                        if(s>=hor.getSecciones().getRowCount()-1) System.out.println("No se hallo la coprelacion entre las materias inscritas");
                     }


             }//fin recorrido de las tablas de las materias inscritas
           }//fin fase_3

             
             //this.analisis_doble_coprelacion(hor);//analizando si esta materia ademas tiene doble coprelacion
             


}//fin fase 2 y 3



*/

    return existe;
}

/**Este metodo se emplea para realizar en tiempo real el analisis de las prelaciones al
 momento de seleccionar una materia e inscribirla en donde se revisa el record del alumno
 para saber si alguna vez la inscribio o en el momento dentro de las materias inscritas para
 ver si la coprelacion fue inscrita y evitar asi que no se le permita incluirla
        |original|coprelaciones|
        |        |             |
        |origi 1 | cop 1       | busca primero si encuentra la materia entre la origi 1 y 2
        |        |             | y luego dentro de cop 1 y 2...asi se sabe si es doble
        |origi 2 | cop 2       | coprelacion, se compara cop1 con origi 2 ya que deben ser iguales
 */
public boolean analisis_doble_coprelacion(horario hor){
    //System.out.println("[Metodo analisis_doble_coprelacion]");
    String materia=null; //materia externa (la que se selecciona del horario)
    String original_1=null, original_2=null; //materia original
    String coprelada_1=null, coprelada_2=null; //materia que coprela la original    
    String auxiliar=null;//solo ayuda
    boolean doble_coprelacion=false;//es la confirmacion de si la materia esta doblemente coprelada

    //verificando que exista o no doble coprelacion

    materia = hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString(); //obteniendo el nombre de la materia de la tabla de horario
    System.out.println("ANALISIS_DOBLE_COPRELACION ->"+materia);

    
    for(int f=0; f<hor.getTrapaso_coprelaciones().size(); f=f+3){// recorriendo el LinkedList donde estan las coprelaciones de las materias habilitadas
       // System.out.println(hor.getTrapaso_coprelaciones().get(f)+"--"+hor.getTrapaso_coprelaciones().get(f+1)+"--"+hor.getTrapaso_coprelaciones().get(f+2));


            if(materia.equalsIgnoreCase(hor.getTrapaso_coprelaciones().get(f+1))){//buscando coincidencia de la materia seleccionada con la original
                
               original_1=hor.getTrapaso_coprelaciones().get(f+1);
               coprelada_1=this.codigo_materia(hor.getTrapaso_coprelaciones().get(f+2), hor.getPensum_traspaso());


            //   System.out.println("traspaso_copre "+hor.getTrapaso_coprelaciones().size()+" pensum_traspaso: "+hor.getPensum_traspaso().size());
            //   System.out.println("original_1: "+original_1+" coprelada_1: "+coprelada_1);

            }else{}
         

            if(materia.equalsIgnoreCase(auxiliar=this.codigo_materia(hor.getTrapaso_coprelaciones().get(f+2), hor.getPensum_traspaso()))){//buscando coincidencia de la materia seleccionada con alguna coprelacion
               // System.out.println("AUXILIAR: "+auxiliar);
                
               original_2=hor.getTrapaso_coprelaciones().get(f+1);
               coprelada_2=auxiliar;

              // System.out.println("original_2: "+original_2+" coprelada_2: "+coprelada_2);


            }else{}


    }//fin recorrido coprelaciones

    if(coprelada_1.equalsIgnoreCase(original_2)){
        System.out.println("estas materias estan doblemente copreladas: ("+original_1+" ; "+original_2+")\n");
        doble_coprelacion=true;

            this.getVigilante().add(original_1);//se guarda estas materias doblemente copreladas para luego
            this.getVigilante().add(original_2);//en el metodo autentificador hacer los estudios

                             registro_ingenieria reging = new registro_ingenieria();//instanciando la clase que contiene un metodo de interes
                             ima.mensaje_informacion("Ademas de inscribir la materia seleccionada\n" +
                                             "es obligatorio que inscriba \""+original_2+"\"\n ya que esta coprela a \""+original_1+"\"", "DOBLE COPRELACION", "doble_coprelacion", "png");

                             

        
    }

return doble_coprelacion;

}

/**
 Este metodo permite verificar si las materias que son doblemente copreladas estan inscritas
 * en caso contrario, si falta al menos una, se borra la materia que le falta su coprelacion
 * ejm
 *          materia       coprelacion
            geometria      matematica        para inscribir geometria, tambien es obligatorio
 *          matematica     geomentria        inscribir matematica. Si falta una, la otra se borra
 *
 * Para ello se pasa como argumento la clase horario donde se encuentra toda la informacion requerida
 */
public void autentificador(horario h,DefaultTableModel dtm, String cedula, String periodo_actual){
    //System.out.println("[Metodo autentificador]");
    boolean opcion_1=false;
    boolean opcion_2=false;
    boolean opcion_3=false;

    boolean etapa_1=false;

    String encontrada=null;
    String original=null, coprelacion=null;
    int limite=0;


    registro_ingenieria ri= new registro_ingenieria();

    for(int c=0; c<this.getVigilante().size(); c=c+2){//recorriendo las materias doblemente copreladas
       // System.out.println("contenido coprelaciones................."+this.getVigilante().get(i));


                opcion_1=false;//por cada par de materias analizadas, se inicializan otra vez las variables
                opcion_2=false;//para asi evitar que halla malos procedimientos o procesos innecesarios
                opcion_3=false;
                etapa_1=false;


                original=this.getVigilante().get(c);
                coprelacion=this.getVigilante().get(c+1);

                System.out.println("\nMateria: "+original+" Coprelacion: "+this.codigo_materia(coprelacion, h.getPensum_traspaso()));

                //VERIFICANDO LAS COPRELACIONES EN EL RECORD.............
                    System.out.println("BUSCANDO EN EL RECORD ACADEMICO");
                    
                    //for(int r=0; r<h.getTrapaso_record().size(); r=r+6){//recorriendo el record del alumno
                    for(int r=0; r<h.getTrapaso_record().size(); r=r+11){//recorriendo el record del alumno


                        if(coprelacion.equalsIgnoreCase(h.getTrapaso_record().get(r))){//buscando por codigo
                            System.out.println("LA COPRELACION ESTA EN EL RECORD ACADEMICO...");
                            opcion_1=true;
                            etapa_1=true;
                            break;
                        }

                    }//fin recorrido del record

                
                //VERIFICANDO LAS MATERIAS INSCRITAS.....................
                 if(etapa_1==false){//en caso de que no se encuentren en el record
                     System.out.println("BUSCANDO COPRELACION ENTRE LAS MATERIAS INSCRITAS");
                     
                  for(int i=0; i<h.getSecciones().getRowCount(); i++){//recorriendo todas las materias inscritas

                                    if(this.getVigilante().get(c).equalsIgnoreCase(h.getSecciones().getValueAt(i, 2).toString())){//ubicando la materia original
                                           h.getSecciones().setRowSelectionInterval(i, i);//seleccionando la materia encontrada de la lista de inscritas
                                           opcion_3=true;
                                           
                                    }
                                   // System.out.println("coprelacion: "+this.getVigilante().get(c+1)+" inscrita: "+h.getSecciones().getValueAt(i, 2).toString());
                                    if(this.getVigilante().get(c+1).equalsIgnoreCase(h.getSecciones().getValueAt(i, 1).toString())){//buscando que la coprelacion este inscrita
                                           opcion_2=true;
                                           //h.getSecciones().setRowSelectionInterval(i, i);//seleccionando la materia encontrada de la lista de inscritas
                                           System.out.println("LA COPRELACION SE ENCUENTRA ENTRE LAS MATERIAS INSCRITAS");
                                           break;
                                    }



                     }//fin recorrido materias inscritas
                 }

                    //System.out.println("OPCIONES: "+opcion_1+" "+opcion_2);
                //PROCEDIMIENTO DE ELIMINACION DE LAS MATERIAS QUE NO CUMPLIERON CON SU COPRELACION........
                      if(opcion_1==false && opcion_2==false && opcion_3==true){//se borra la materia en caso de que la coprelacion no este en el record, ni entre las inscritas siempre que la materia que esta siendo coprelada este entre las inscritas
                          System.out.println("NO SE HALLO NADA, ELIMINANDO LA MATERIA "+original);

                          ima.mensaje_informacion("LA MATERIA  \""+original+"\"  EXIGE INSCRIBIR\n" +
                                        "SU COPRELACION \""+this.codigo_materia(coprelacion, h.getPensum_traspaso())+"\", COMO NO LO HIZO SERA ELIMINADA",
                                        "ELIMINACION AUTOMATICA", "eli_auto", "png");
             
                            this.eliminancion_materia(h.getSecciones(), dtm, new conexion_base_de_datos().getConexion(), cedula, periodo_actual);//procedimiento de eliminacion
                            h.getUc().setText(this.getCredito()+"/30");
                      }




   }//fin recorrido doble coprelacion


}//fin metodo autentificacion












/** Este metodo es para buscar el codigo de la materia dentro del pensum
 al que pertenece el estudiante e identificar la materia que esta asociada
 codigo=codigo de la materia que se quiere buscar
 LinkedList= es donde se encuentran todas las materias del pensum
 */
public String codigo_materia(String codigo, LinkedList<String> pensum){
    //System.out.println("[Metodo codigo_materia]");
    String consulta=null;
    //for(int p=0; p<pensum.size(); p=p+7){//recorriendo el pensum en busca de coincidencia de busqueda
    for(int p=0; p<pensum.size(); p=p+10){//recorriendo el pensum en busca de coincidencia de busqueda
      //  System.out.println("pensum: "+pensum.get(p)+" "+pensum.get(p+2));
       // System.out.println("codigo: "+codigo);
                if(codigo.equalsIgnoreCase(pensum.get(p))){//si el codigo enviado es igual a uno del pensum
                    consulta=pensum.get(p+2);//guarda el nombre de la materia
                    //System.out.println("consulta -> {"+consulta+"}");
                    break;
                }
                

    }//fin recorrido

return consulta;
}

public String credito_materia(String codigo, LinkedList<String> pensum){
    //System.out.println("[Metodo codigo_materia]");
    String consulta=null;
    //for(int p=0; p<pensum.size(); p=p+7){//recorriendo el pensum en busca de coincidencia de busqueda
    for(int p=0; p<pensum.size(); p=p+10){//recorriendo el pensum en busca de coincidencia de busqueda
      //  System.out.println("pensum: "+pensum.get(p)+" "+pensum.get(p+2));
       // System.out.println("codigo: "+codigo);
                if(codigo.equalsIgnoreCase(pensum.get(p))){//si el codigo enviado es igual a uno del pensum

                    consulta=pensum.get(p+3);//guarda el nombre de la materia
                    //System.out.println("consulta -> {"+consulta+"}");
                    break;
                }



    }//fin recorrido

return consulta;
}








/**metodo que transforma el literal o letra que representa la condicion de la materia que fue o va
 a ser inscrita en una legible, como por ejemplo "N = Normal", facilitando asi la interpretacion por parte del lector*/
public String condicion_materia(String letra){
  //System.out.println("[Metodo condicion_materia]");
    System.out.println("CONDICION MATERIA letra: "+letra);
       String condicion = null;
    if(letra.equalsIgnoreCase(letra)) condicion="ERROR";//en caso de que en el record del alumno se encuentre una condicion errada

    if(letra.equalsIgnoreCase("R")) condicion="REPARÓ";
    if(letra.equalsIgnoreCase("N")) condicion="NORMAL";
    if(letra.equalsIgnoreCase("L")) condicion="PARALELO";
    if(letra.equalsIgnoreCase("P")) condicion="REPITENCIA";
    if(letra.equalsIgnoreCase("G")) condicion="POR INASISTENCIA";
   // if(letra.equalsIgnoreCase("G")) condicion="NO RECUPERO - PENDIENTE";
    if(letra.equalsIgnoreCase("A")) condicion="APROBÓ";
    if(letra.equalsIgnoreCase("Q")) condicion="REPROBÓ";
    if(letra.equalsIgnoreCase("E")) condicion="EQUIVALENCIA";
    if(letra.equalsIgnoreCase("S")) condicion="SUFICIENCIA";

    if(letra.equalsIgnoreCase("C")) condicion="EQUIVALENCIA";//opcional experimental

    //NUEVAS CONDICIONES ESTANDARIZADAS

       if(letra.equalsIgnoreCase("01")) condicion="NORMAL";
       if(letra.equalsIgnoreCase("02")) condicion="PARALELO";
       if(letra.equalsIgnoreCase("03")) condicion="REPITENCIA";
       if(letra.equalsIgnoreCase("04")) condicion="EQUIVALENCIA";       
       if(letra.equalsIgnoreCase("05")) condicion="REPARÓ";

       if(letra.equalsIgnoreCase("06")) condicion="REPROBÓ";
       if(letra.equalsIgnoreCase("07")) condicion="REPARÓ-PENDIENTE";
       if(letra.equalsIgnoreCase("08")) condicion="APROBÓ";
       //if(letra.equalsIgnoreCase("09")) condicion="NO RECUPERÓ-PENDIENTE";
       if(letra.equalsIgnoreCase("09")) condicion="NO REPARÓ-PENDIENTE";
       if(letra.equalsIgnoreCase("10")) condicion="REPROBÓ-25% DE INASISTENCIA";

       if(letra.equalsIgnoreCase("11")) condicion="SUFICIENCIA";
       if(letra.equalsIgnoreCase("12")) condicion="PARALELO-REPARÓ-PENDIENTE";
       if(letra.equalsIgnoreCase("13")) condicion="PARALELO-REPARÓ";
       if(letra.equalsIgnoreCase("14")) condicion="PARALELO-NO REPARÓ-PENDIENTE";
       if(letra.equalsIgnoreCase("15")) condicion="REPITENCIA-REPARÓ-PENDIENTE";

       if(letra.equalsIgnoreCase("16")) condicion="REPITENCIA-REPARÓ";
       if(letra.equalsIgnoreCase("17")) condicion="REPITENCIA-NO REPARÓ-PENDIENTE";
       if(letra.equalsIgnoreCase("18")) condicion="MANTENIMIENTO";
       if(letra.equalsIgnoreCase("19")) condicion="EXONERADO";

       if(letra.equalsIgnoreCase("20")) condicion="RECONOCIMIENTO DE CRÉDITOS";
       if(letra.equalsIgnoreCase("21")) condicion="NIVELACIÓN";
       if(letra.equalsIgnoreCase("22")) condicion="ACREDITACIÓN";
       if(letra.equalsIgnoreCase("23")) condicion="REPROBÓ-50% DE INASISTENCIA";
    

System.out.println("SALIDA LETRA: "+letra+" - condicion: "+condicion);



return condicion;
}


/**Metodo que permite hacer el proceso inverso de conversion de condicion de las materias, ya que existe la posibilidad
 que se quiera presentar la condicion de la materia por su letra y no con su nombre. Ejemplo "REPARO"="R"
 por lo tanto se pasa como argumento la condicion para devolver su letra. REVISARLO PORQUE AUN NO ESTA TERMINADO*/
public String condicion_materia_inversa(String condicion){
        String letra=null;
    if(condicion.equalsIgnoreCase("REPARÓ")) letra="R";
    if(condicion.equalsIgnoreCase("NORMAL")) letra="N";
    if(condicion.equalsIgnoreCase("PARALELO")) letra="L";
    if(condicion.equalsIgnoreCase("REPITENCIA")) letra="P";
    if(condicion.equalsIgnoreCase("POR INASISTENCIA")) letra="G";
    //if(condicion.equalsIgnoreCase("NO RECUPERO - PENDIENTE")) letra="G";
    if(condicion.equalsIgnoreCase("APROBÓ")) letra="A";
    if(condicion.equalsIgnoreCase("REPROBÓ")) letra="Q";
    if(condicion.equalsIgnoreCase("EQUIVALENCIA")) letra="E";
    if(condicion.equalsIgnoreCase("SUFICIENCIA")) letra="S";
  
//nuevas consideraciones

        if(condicion.equalsIgnoreCase("NORMAL")) letra="01";
        if(condicion.equalsIgnoreCase("PARALELO")) letra="02";
        if(condicion.equalsIgnoreCase("REPITENCIA")) letra="03";
        if(condicion.equalsIgnoreCase("EQUIVALENCIA")) letra="04";
        if(condicion.equalsIgnoreCase("REPARÓ")) letra="05";

        if(condicion.equalsIgnoreCase("REPROBÓ")) letra="06";
        if(condicion.equalsIgnoreCase("REPARÓ-PENDIENTE")) letra="07";
        if(condicion.equalsIgnoreCase("APROBÓ")) letra="08";
        if(condicion.equalsIgnoreCase("NO REPARÓ-PENDIENTE")) letra="09";
        if(condicion.equalsIgnoreCase("REPROBÓ-25% DE INASISTENCIA")) letra="10";

        if(condicion.equalsIgnoreCase("SUFICIENCIA")) letra="11";
        if(condicion.equalsIgnoreCase("PARALELO-REPARÓ-PENDIENTE")) letra="12";
        if(condicion.equalsIgnoreCase("PARALELO-REPARÓ")) letra="13";
        if(condicion.equalsIgnoreCase("PARALELO-NO REPARÓ-PENDIENTE")) letra="14";
        if(condicion.equalsIgnoreCase("REPITENCIA-REPARÓ-PENDIENTE")) letra="15";
        
        if(condicion.equalsIgnoreCase("REPITENCIA-REPARÓ")) letra="16";
        if(condicion.equalsIgnoreCase("REPITENCIA-NO REPARÓ-PENDIENTE")) letra="17";
        if(condicion.equalsIgnoreCase("MANTENIMIENTO")) letra="18";
        if(condicion.equalsIgnoreCase("EXONERADO")) letra="19";

        if(condicion.equalsIgnoreCase("RECONOCIMIENTO DE CRÉDITOS")) letra="20";
        if(condicion.equalsIgnoreCase("NIVELACIÓN")) letra="21";
        if(condicion.equalsIgnoreCase("ACREDITACIÓN")) letra="22";
        if(condicion.equalsIgnoreCase("REPROBÓ-50% DE INASISTENCIA")) letra="23";



return letra;
}

public String calificacion(String nota){
//System.out.println("[Metodo calificacion]");
String arreglo = null;

   if(nota.equalsIgnoreCase("0") || nota.equalsIgnoreCase(null)){}else{arreglo=nota;};



return arreglo;
}

/**Este metodo se encarga de cargar las materias de equivalencias de un pensum a otro
 para que otros metodos lo verifiquen.*/
public String materias_pensum_equivalencias(Connection con, String carrera){

    this.getPensum().clear();
    //this.getSer_comunitario().clear();

    Statement sentencia;
    ResultSet pensum;

        try {

            System.out.println("carrera..prueba "+carrera);
            sentencia = con.createStatement();
            pensum = sentencia.executeQuery("SELECT * FROM pensum."+carrera+" ORDER BY semestre ASC;");

            while(pensum.next()){//SE hace el recorrido en el pensum normal

            this.getPensum().add(pensum.getString("codigo"));//0
            this.getPensum().add(String.valueOf(pensum.getInt("semestre")));//1
            this.getPensum().add(pensum.getString("asignatura"));//2
            this.getPensum().add(String.valueOf(pensum.getInt("uc")));//3

            this.getPensum().add(pensum.getString("prelacion_1"));//4
            this.getPensum().add(pensum.getString("prelacion_2"));//5
            this.getPensum().add(pensum.getString("prelacion_3"));//6
            this.getPensum().add(pensum.getString("prelacion_4"));//7
            this.getPensum().add(pensum.getString("prelacion_5"));//8
            this.getPensum().add(pensum.getString("prelacion_6"));//9

            }


      /*      sentencia = con.createStatement();
            pensum = sentencia.executeQuery("SELECT * FROM pensum.servicio_comunitario ORDER BY semestre ASC;");

            while(pensum.next()){//SE hace el recorrido en el pensum normal

            this.getSer_comunitario().add(pensum.getString("codigo"));//0
            this.getSer_comunitario().add(String.valueOf(pensum.getInt("semestre")));//1
            this.getSer_comunitario().add(pensum.getString("asignatura"));//2
            this.getSer_comunitario().add(String.valueOf(pensum.getInt("uc")));//3

            this.getSer_comunitario().add(pensum.getString("prelacion_1"));//4
            this.getSer_comunitario().add(pensum.getString("prelacion_2"));//5
            this.getSer_comunitario().add(pensum.getString("prelacion_3"));//6

            }*/


         //   sentencia= con.createStatement();
           // pensum = sentencia.executeQuery("SELECT")



            sentencia.close();
            pensum.close();
            con.close();

        } catch (SQLException ex) {

            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }




return carrera;


}



public String materias_pensum(Connection con, String especialidad, String turno){
    System.out.println("[Metodo materia_pensum]");
    this.getPensum().clear();
    this.getSer_comunitario().clear();
    this.getElectivas().clear();

    String carrera=null;
    carrera = this.carrera(especialidad, turno);//obteniendo el nombre del pensum donde se ba a realizar la busqueda

    Statement sentencia;
    ResultSet pensum;

   


        try {

            System.out.println("carrera..prueba "+carrera);
            sentencia = con.createStatement();
            pensum = sentencia.executeQuery("SELECT * FROM pensum."+carrera+" ORDER BY semestre ASC;");
            

            while(pensum.next()){//SE hace el recorrido en el pensum normal
             
                this.getPensum().add(pensum.getString("codigo"));//0
                this.getPensum().add(String.valueOf(pensum.getInt("semestre")));//1
                this.getPensum().add(pensum.getString("asignatura"));//2
                this.getPensum().add(String.valueOf(pensum.getInt("uc")));//3

                this.getPensum().add(pensum.getString("prelacion_1"));//4
                this.getPensum().add(pensum.getString("prelacion_2"));//5
                this.getPensum().add(pensum.getString("prelacion_3"));//6
                this.getPensum().add(pensum.getString("prelacion_4"));//7
                this.getPensum().add(pensum.getString("prelacion_5"));//8
                this.getPensum().add(pensum.getString("prelacion_6"));//9

            }


            sentencia = con.createStatement();
            pensum = sentencia.executeQuery("SELECT * FROM pensum.servicio_comunitario ORDER BY semestre ASC;");

            while(pensum.next()){//SE hace el recorrido en el pensum normal

                this.getSer_comunitario().add(pensum.getString("codigo"));//0
                this.getSer_comunitario().add(String.valueOf(pensum.getInt("semestre")));//1
                this.getSer_comunitario().add(pensum.getString("asignatura"));//2
                this.getSer_comunitario().add(String.valueOf(pensum.getInt("uc")));//3

                this.getSer_comunitario().add(pensum.getString("prelacion_1"));//4
                this.getSer_comunitario().add(pensum.getString("prelacion_2"));//5
                this.getSer_comunitario().add(pensum.getString("prelacion_3"));//6
                this.getSer_comunitario().add(pensum.getString("prelacion_4"));//7
                this.getSer_comunitario().add(pensum.getString("prelacion_5"));//8
                this.getSer_comunitario().add(pensum.getString("prelacion_6"));//9

            }


           sentencia= con.createStatement();
           pensum = sentencia.executeQuery("SELECT * FROM pensum.electivas WHERE carrera='"+especialidad+"';");

           while(pensum.next()){//SE hace el recorrido en el pensum normal

                this.getElectivas().add(pensum.getString("codigo"));//0
                this.getElectivas().add(pensum.getString("asignatura"));//1
                this.getElectivas().add(String.valueOf(pensum.getInt("uc")));//2
                this.getElectivas().add(pensum.getString("tipo"));//3
           }
         
            sentencia.close();
            pensum.close();
            //con.close();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            

//System.out.println("PENSUM ELECTIVAS CARGADOS: "+this.getElectivas().size()+" - Carrera= "+carrera);

return carrera;
}

/**Este metodo se encarga de buscar las materias de carreras en una sola tabla donde residen todas las carreras discriminadas
 por carrera, pensum, turno*/
public void materias_pensum_alternativo(Connection con, String especialidad, String turno){
    System.out.println("[Metodo materia_pensum_alternativo]");
    this.getPensum().clear();
    this.getSer_comunitario().clear();
    this.getElectivas().clear();

    //identificando la vigencia
    if(this.getSeleccion_pensum()==0 && this.getNuevo_ingreso()==0) this.setVigencia("2010");
    if(this.getSeleccion_pensum()==1 && this.getNuevo_ingreso()==0) this.setVigencia("2009");
    if(this.getSeleccion_pensum()==2 && this.getNuevo_ingreso()==0) this.setVigencia("2007");
    if(this.getNuevo_ingreso()==1) this.setVigencia("2007");
    
    
    Statement sentencia;
    ResultSet pensum;


        try {

            System.out.println("carrera..prueba "+especialidad+" turno: "+turno);
            sentencia = con.createStatement();      
            pensum = sentencia.executeQuery("SELECT * FROM pensum.pensa WHERE carrera='"+especialidad+"' AND pensum="+this.getVigencia()+" AND turno='"+turno+"' ORDER BY semestre ASC;");

            while(pensum.next()){//SE hace el recorrido en el pensum normal
             
                this.getPensum().add(pensum.getString("codigo"));//0
                this.getPensum().add(String.valueOf(pensum.getInt("semestre")));//1
                this.getPensum().add(pensum.getString("asignatura"));//2
                this.getPensum().add(String.valueOf(pensum.getInt("uc")));//3

                this.getPensum().add(pensum.getString("prelacion_1"));//4
                this.getPensum().add(pensum.getString("prelacion_2"));//5
                this.getPensum().add(pensum.getString("prelacion_3"));//6
                this.getPensum().add(pensum.getString("prelacion_4"));//7
                this.getPensum().add(pensum.getString("prelacion_5"));//8
                this.getPensum().add(pensum.getString("prelacion_6"));//9

            }


            sentencia = con.createStatement();
            pensum = sentencia.executeQuery("SELECT * FROM pensum.servicio_comunitario ORDER BY semestre ASC;");

            while(pensum.next()){//SE hace el recorrido en el pensum normal

                this.getSer_comunitario().add(pensum.getString("codigo"));//0
                this.getSer_comunitario().add(String.valueOf(pensum.getInt("semestre")));//1
                this.getSer_comunitario().add(pensum.getString("asignatura"));//2
                this.getSer_comunitario().add(String.valueOf(pensum.getInt("uc")));//3

                this.getSer_comunitario().add(pensum.getString("prelacion_1"));//4
                this.getSer_comunitario().add(pensum.getString("prelacion_2"));//5
                this.getSer_comunitario().add(pensum.getString("prelacion_3"));//6
                this.getSer_comunitario().add(pensum.getString("prelacion_4"));//7
                this.getSer_comunitario().add(pensum.getString("prelacion_5"));//8
                this.getSer_comunitario().add(pensum.getString("prelacion_6"));//9

            }


           sentencia= con.createStatement();
           pensum = sentencia.executeQuery("SELECT * FROM pensum.electivas WHERE carrera='"+especialidad+"';");

           while(pensum.next()){//SE hace el recorrido en el pensum normal

                this.getElectivas().add(pensum.getString("codigo"));//0
                this.getElectivas().add(pensum.getString("asignatura"));//1
                this.getElectivas().add(String.valueOf(pensum.getInt("uc")));//2
                this.getElectivas().add(pensum.getString("tipo"));//3
           }
         
            sentencia.close();
            pensum.close();
            //con.close();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}

public String carrera_equivalencia(String especialidad, String cambio, String turno){

     //System.out.println("[Metodo carrera]");
    String curso = null;

// registro_ingenieria ri = new registro_ingenieria(); //no es bueno utilizarlo asi ya que se crea un nuevo objeto y los datos que tenia ante se borran




   if(cambio.equalsIgnoreCase("2007_2009")){//equivalencia de 2007 a 2010
       System.out.println("cargando equivalencia 2007_2009");
       this.setVigencia("2009");
                //esta vigencia depende de los turnos ya que hay un pensum para el diurno y otro para el nocturno

            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA") ) curso="equivalencia_ing_mecanica_2007_2009";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL") ) curso="equivalencia_lic_educacion_2007_2009";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) curso="equivalencia_lic_contaduria_2007_2009";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") ) curso="equivalencia_lic_economia_2007_2009";
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO") ) curso="equivalencia_tsu_turismo_2007_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA") ) curso="equivalencia_ing_aeronautico_2007_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL") ) curso="equivalencia_ing_civil_2007_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) curso="equivalencia_ing_electrica_2007_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA") ) curso="";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") ) curso="equivalencia_ing_sistemas_2007_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") ) curso="equivalencia_ing_telecomunicaciones_2007_2009";
            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA") ) curso="equivalencia_tsu_enfermeria_2007_2009";
            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA") ) curso=" ";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") ) curso="equivalencia_lic_gestion_municipal_2007_2009";

   }


    if(cambio.equalsIgnoreCase("2009_2010")){//en caso de que sea pensum 2009 y sea un alumno regular
        System.out.println("cargando equivalencia 2009_2010");
        this.setVigencia("2010");
                                                                                //se considera el turno tanto diurno como nocturno
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_ing_mecanica_2009_2010";else curso="";}
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_educacion_2009_2010";else curso="equivalencia_lic_educacion_2009_2010_n";}
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_contaduria_2009_2010";else curso="equivalencia_lic_contaduria_2009_2010_n";}
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_economia_2009_2010";else curso="equivalencia_lic_economia_2009_2010_n";}
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_tsu_turismo_2009_2010";else curso="";}

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_tsu_enfermeria_2009_2010";else curso="equivalencia_tsu_enfermeria_2009_2010_n";}
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_ing_aeronautico_2009_2010";else curso="";}
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_ing_civil_2009_2010";else curso="equivalencia_ing_civil_2009_2010_n";}
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="";else curso="";}
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="";else curso="";}
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) { if(turno.equalsIgnoreCase("DIURNO"))curso="";else curso="equivalencia_ing_sistemas_2009_2010_n";}
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_ing_telecomunicaciones_2009_2010";else curso="";}
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_gestion_municipal_2009_2010";else curso="equivalencia_lic_gestion_municipal_2009_2010_n";}
    }


    if(cambio.equalsIgnoreCase("2007_2010")){//en caso de que sea pensum 2009 y sea un alumno regular
        System.out.println("cargando equivalencia 2007_2010");
        this.setVigencia("2010");

            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_ing_mecanica_2007_2010";else curso="";}
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_educacion_2007_2010";else curso="equivalencia_lic_educacion_2007_2010_n";}
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_contaduria_2007_2010";else curso="equivalencia_lic_contaduria_2007_2010_n";}
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_economia_2007_2010";else curso="equivalencia_lic_economia_2007_2010_n";}
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_tsu_turismo_2007_2010";else curso="";}

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_tsu_enfermeria_2007_2010";else curso="equivalencia_tsu_enfermeria_2007_2010_n";}
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="";else curso="";}
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_ing_civil_2007_2010";else curso="equivalencia_ing_civil_2007_2010_n";}
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="";else curso="";}
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) { if(turno.equalsIgnoreCase("DIURNO"))curso="";else curso="";}
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) { if(turno.equalsIgnoreCase("DIURNO"))curso="";else curso="equivalencia_ing_sistemas_2007_2010_n";}
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_ing_telecomunicaciones_2007_2010";else curso="";}
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) { if(turno.equalsIgnoreCase("DIURNO"))curso="equivalencia_lic_gestion_municipal_2007_2010";else curso="equivalencia_lic_gestion_municipal_2007_2010_n";}
    }






System.out.println("Pensun cargado: "+curso+" - Cambio: "+cambio);
return curso;



}


public String carrera(String especialidad, String turnos){
 //System.out.println("[Metodo carrera]");
    String curso = null;

// registro_ingenieria ri = new registro_ingenieria(); //no es bueno utilizarlo asi ya que se crea un nuevo objeto y los datos que tenia ante se borran




   if(this.getSeleccion_pensum()==0 && this.getNuevo_ingreso()==0){//en caso de que sea pensum 2010 y sea un alumno regular
       System.out.println("cargando vigencia 2010");
       this.setVigencia("2010");
                //esta vigencia depende de los turnos ya que hay un pensum para el diurno y otro para el nocturno
       
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_mecanica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_mecanica_2010_d";

            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_educacion_integral_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_educacion_integral_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_contaduria_publica_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_contaduria_publica_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_economia_social_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_economia_social_2010_n";

            if(especialidad.equalsIgnoreCase("TSU EN TURISMO") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_turismo_2010_d";
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO") && turnos.equalsIgnoreCase("NOCTURNO")) curso="tsu_turismo_2010_d";



            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_aeronautico_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_aeronautico_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_civil_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_civil_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_electrica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_electrica_2010_d";

            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_electronica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_electronica_2010_d";

            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_sistemas_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_sistemas_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_telecomunicaciones_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_telecomunicaciones_2010_n";

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_enfermeria_2010_d";
            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="tsu_enfermeria_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_enfermeria_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_enfermeria_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_gestion_municipal_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_administracion_gestion_municipal_2010_n";


            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_desastre_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_administracion_desastre_2010_n";
            
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_mecanica_dental_2010_d";
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="tsu_mecanica_dental_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_turismo_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_turismo_2010_n";
            
            if(especialidad.equalsIgnoreCase("INGENIERIA AGROINDUSTRIAL") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_agroindustrial_2010_d";
            
   }



    if(this.getSeleccion_pensum()==1 && this.getNuevo_ingreso()==0){//en caso de que sea pensum 2009 y sea un alumno regular
        System.out.println("cargando vigencia 2009");
        this.setVigencia("2009");

            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) curso="ingenieria_mecanica_2009";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) curso="lic_educacion_integral_2009";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) curso="lic_contaduria_publica_2009";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) curso="lic_economia_social_2009";
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) curso="tsu_turismo_2009";

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) curso="tsu_enfermeria_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) curso="ingenieria_aeronautico_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) curso="ingenieria_civil_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) curso="ingenieria_electrica_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) curso="ingenieria_electronica_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) curso="ingenieria_sistemas_2009";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) curso="ingenieria_telecomunicaciones_2009";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) curso="lic_administracion_gestion_municipal_2009";            

            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_desastre_2009";
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_mecanica_dental_2009";            
            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_turismo_2009";
            

    }


   if(this.getSeleccion_pensum()==2 && this.getNuevo_ingreso()==0){//en caso de que sea pensum 2007 y sea un alumno regular
       System.out.println("cargando vigencia 2007");
       this.setVigencia("2007");

            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) curso="ingenieria_mecanica_2007";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) curso="lic_educacion_integral_2007";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) curso="lic_contaduria_publica_2007";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_economia_social_2007";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_economia_social_2007";
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) curso="tsu_turismo_2007";

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) curso="tsu_enfermeria_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) curso="ingenieria_aeronautico_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) curso="ingenieria_civil_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_electrica_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_electrica_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) curso="ingenieria_electronica_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_sistemas_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_sistemas_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_telecomunicaciones_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_telecomunicaciones_2007";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_gestion_municipal_2007";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")&& turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_administracion_gestion_municipal_2007";
            
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_desastre_2007";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_administracion_desastre_2007";
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_mecanica_dental_2007";            
            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_turismo_2007";
            
            if(especialidad.equalsIgnoreCase("TSU EN COMUNICACIONES Y ELECTRONICA") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_comunicaciones_electronica_2007";            
            if(especialidad.equalsIgnoreCase("TSU EN ANALISIS Y DISEÑO DE SISTEMAS") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_analisis_diseno_sistemas_2007";            

   }


 if(this.getNuevo_ingreso()==1){//en caso de que sea un nuevo ingreso.(NUEVON)
      System.out.println("cargando vigencia 2010  nuevos");
      this.setVigencia("2010");

            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_mecanica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_mecanica_2010_d";

            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_educacion_integral_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_educacion_integral_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_contaduria_publica_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_contaduria_publica_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_economia_social_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_economia_social_2010_n";

            if(especialidad.equalsIgnoreCase("TSU EN TURISMO") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_turismo_2010_d";
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO") && turnos.equalsIgnoreCase("NOCTURNO")) curso="tsu_turismo_2010_n";


      
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_aeronautico_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_aeronautico_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_civil_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_civil_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_electrica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_electrica_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_electronica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_electronica_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_sistemas_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_sistemas_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_telecomunicaciones_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_telecomunicaciones_2010_n";

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_enfermeria_2010_d";
            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="tsu_enfermeria_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_enfermeria_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_enfermeria_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_gestion_municipal_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_administracion_gestion_municipal_2010_n";


            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_desastre_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_administracion_desastre_2010_n";
            
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_mecanica_dental_2010_d";
            if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="tsu_mecanica_dental_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_turismo_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN TURISMO") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_turismo_2010_n";
            
            if(especialidad.equalsIgnoreCase("INGENIERIA AGROINDUSTRIAL") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_agroindustrial_2010_d";



    }

System.out.println("Pensun cargado: "+curso+" - Turno: "+turnos);
return curso;
}


public String notas_especialidad(registro_ingenieria reging){
System.out.println("[Metodo notas_especialidad]");
    String notas = null;

System.out.println("TURNOS diagnostico: "+reging.getTurnos());
  if(reging.getReconocimiento()==0){//primera busqueda en el grupo 2010
        //CAGUA
       // if(reging.getTurnos().equalsIgnoreCase("DIURNO"))  notas="notas_alumnos_todas_carreras_2010d";
       // if(reging.getTurnos().equalsIgnoreCase("NOCTURNO")) notas="notas_alumnos_todas_carreras_2010n";

      //MARACAY
        if(reging.getTurnos().equalsIgnoreCase("DIURNO") || reging.getTurnos().equalsIgnoreCase("NOCTURNO"))  notas="cem_notas_alumnos_todas_carreras_2010";


    }


  if(reging.getReconocimiento()==1){//segunda busqueda en el grupo 2009

      //CAGUA
          //notas="notas_alumnos_todas_carreras_2009";
      //MARACAY
         notas="cem_notas_alumnos_todas_carreras_2009";

    }

    if(reging.getReconocimiento()==2){//tercera busqueda en el grupo 2007

        //CAGUA
         // if(bd_notas_especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) notas="notas_alumnos_edu_ing_2007";
          //if(bd_notas_especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) notas="notas_alumnos_edu_ing_2007";
          //if(bd_notas_especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) notas="notas_cet_2007";
          //if(bd_notas_especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) notas="notas_cet_2007";
          //if(bd_notas_especialidad.equalsIgnoreCase("TSU EN TURISMO")) notas="notas_cet_2007";
        //MARACAY
          notas="cem_notas_alumnos_todas_carreras_2007";

    }
  

System.out.println("Ubicacion: "+notas);
return notas;
}



public void ordenamiento_año(String prueba){
//System.out.println("[Metodo ordenamiento_año]");
  //  System.out.println("Depurando "+prueba+"  longitud "+prueba.length());
    //prueba=prueba.substring(2);
    if(prueba.length()>6)//estas son las materias intensivas.
          prueba=prueba.substring(3); //materias con periodos intensivos
    else  prueba=prueba.substring(2);//materias con periodos normales

    if(this.getMas_pequeño()==0){
        this.setMas_pequeño(Integer.valueOf(prueba));
        this.setMas_grande(Integer.valueOf(prueba));
    }else{
    if(Integer.valueOf(prueba)<this.getMas_pequeño()){this.setMas_pequeño(Integer.valueOf(prueba));}
    if(Integer.valueOf(prueba)>this.getMas_grande()){this.setMas_grande(Integer.valueOf(prueba));}
     }
    //System.out.println("PERIODOS mas pequeño: "+this.getMas_pequeño()+"  mas grande: "+this.getMas_grande());


}




public void ordenamiento_materiasx(registro_ingenieria reging){
//System.out.println("[Metodo ordenamiento_materias]");
              //  System.out.println("LONGITUD NOTAS reging= "+reging.getCodigo().size()+"\n"+
                //        "mas pequeño: "+this.getMas_pequeño()+" - mas grande: "+this.getMas_grande());
/*
    for(int x=this.getMas_pequeño(); x<=this.getMas_grande();x++){//para comparar con el año
       // System.out.println(x);
        
        for(int y=0;y<reging.getCodigo().size();y++){//ciclo para recorrer el LinkedList ....sugerencia se puede mejorar este ciclo haciendo una division con el % ya que si se divide entre 2 y el resultado es exacto se acumula en un registro sino en el otro...es util para los semestres pares e impares
                if(reging.getPeriodo().get(y).trim().equalsIgnoreCase("1-"+x)){
                  //      System.out.println("1-"+x);
                    this.getAux1().add(reging.getCodigo().get(y)); //codigo de la materia
                    this.getAux1().add(this.comparacion_pensum(reging.getMateria().get(y))); //nombre de la materia
                    this.getAux1().add(reging.getDefinitiva().get(y)); //definitiva de la materia
                    this.getAux1().add(this.calificacion(reging.getReparacion().get(y))); //nota de reparacion de la materia
                    this.getAux1().add(this.condicion_materia(reging.getCondicion().get(y))); //condicion en la que fue inscrita la materia
                    this.getAux1().add(reging.getPeriodo().get(y)); //periodo academico en la que fue inscrita la materia
                }

                if(reging.getPeriodo().get(y).trim().equalsIgnoreCase("2-"+x)){
                    //    System.out.println("2-"+x);
                    this.getAux2().add(reging.getCodigo().get(y));
                    //this.getAux2().add(reging.getMateria().get(y));
                    this.getAux2().add(this.comparacion_pensum(reging.getMateria().get(y)));
                    this.getAux2().add(reging.getDefinitiva().get(y));
                    this.getAux2().add(this.calificacion(reging.getReparacion().get(y)));
                    this.getAux2().add(this.condicion_materia(reging.getCondicion().get(y)));
                    this.getAux2().add(reging.getPeriodo().get(y));
                }

        }//fin reccorrido del LinkedList
        
          //  System.out.println(this.getAux1().size()+" - "+this.getAux2().size());
         //llenando el LinkedList principal
        for(int r=0; r<this.getAux1().size(); r=r+6){//reordeando por periodo academico que empiece por 1
        this.getOrden().add(this.getAux1().get(r));//0  codigo de la materia
        this.getOrden().add(this.getAux1().get(r+1));//1  nombre de la materia
        this.getOrden().add(this.getAux1().get(r+2));//2  definitiva de la materia
        this.getOrden().add(this.getAux1().get(r+3));//3  nota de reparacion
        this.getOrden().add(this.getAux1().get(r+4));//4  condicion de la materia
        this.getOrden().add(this.getAux1().get(r+5));//5  periodo academico de la materia

       

        }
            
        for(int s=0; s<this.getAux2().size(); s=s+6){//reordenando por periodo academico que empiece por 2
        this.getOrden().add(this.getAux2().get(s));
        this.getOrden().add(this.getAux2().get(s+1));
        this.getOrden().add(this.getAux2().get(s+2));
        this.getOrden().add(this.getAux2().get(s+3));
        this.getOrden().add(this.getAux2().get(s+4));
        this.getOrden().add(this.getAux2().get(s+5));

       

        }
                this.getAux1().clear();
                this.getAux2().clear();



    }//fin comparar año

System.out.println("FIN ORDENAMIENTO : "+this.getOrden().size());
 *
 * 
 */
}


private LinkedList<String> agrupar_periodos(){
    LinkedList<String> elementos = new LinkedList<>();
    String peraca = null;
    
    for(int m=0; m<this.getOrden().size(); m=m+11){ // Recorriendo las materias del estudiante
        peraca = this.getOrden().get(m+5); // Almacenando temporalmente el registro de interes
        
        if(elementos.isEmpty()){
            elementos.add(peraca); // Agregando el periodo actual de la materia
        }else{ // En caso de que halla registros previos, se realiza otro analisis
           
            for(int p=0; p<elementos.size(); p++){ // Recorriendo los periodos academicos almacenados
                
                if(elementos.get(p).equalsIgnoreCase(peraca)){ // Si son iguales es porque existen
                    break;
                }else{ // sino hay que ver si es el final de la revision para estar seguro de que no fue agregado previemente
                    if(p >= (elementos.size()-1)){ // Si se llego al final del registro y no se encontro coincidencia se agrega el registro nuevo
                        elementos.add(peraca); // Agregando periodo academico por ser nuevo elemento
                    }
                }
                
            } // Fin recorrido periodos academicos almacenados
        }
        
    }// Fin materias estudiantes
    
    //for(int i=0; i<elementos.size();i++){System.out.println("periodos: "+elementos.get(i));}
    
    
    return elementos;
}
private void reordenamientoXperiodoXsemestre(){
    LinkedList<String> nuevo = new LinkedList<>(); // nuevo registro
    LinkedList<String> peraca = this.agrupar_periodos(); // Periodos almacenados por tipo ya ordenados
    
     for(int p=0; p<peraca.size(); p++){ // Recorriendo los periodos academicos
         String periodo = peraca.get(p); // Almacenando temporalmente el periodo bajo analisis
        // System.out.println("periodo: "+periodo);
         
            for(int s=0; s<=14; s++){ //Se recorren los 14 terminos y dentro tambien estan los 10, 9 , 8 , 6 semestre segun carrera
              //System.out.println("  semestre: "+s);  
              
                for(int m=0; m<this.getOrden().size(); m=m+11){ // Recorriendo todas las materias del estudiante
                    String pacademico = this.getOrden().get(m+5); 
                    String semestre = this.getOrden().get(m+10);
                   // System.out.println("    "+m/11+" - materia: "+this.getOrden().get(m));
                    
                    if(this.getOrden().get(m+4).equalsIgnoreCase("EQUIVALENCIA")) semestre = "0"; // Opcional. solo si la materia es equivalencia le coloca virtualmente semestre 0 en cada periodo que este pero no modifica el registro sino el orden
                    
                    //System.out.println("comparando: "+pacademico+"=="+periodo+" y "+semestre+"=="+String.valueOf(s));
                    
                    if(pacademico.equalsIgnoreCase(periodo) && semestre.equalsIgnoreCase(String.valueOf(s))){ // Si el periodo y semestre son iguales se almacena el registro
                        nuevo.addAll(this.getOrden().subList(m, m+11));
          //              System.out.println("     -->agregado: "+this.getOrden().get(m+1));
                    }
                    
                } // Fin recorrido de todas las materias del estudiente
                
            } // Fin recorrido semestres
     } // Fin recorrido periodo academicos
    
     this.getOrden().clear();
     this.setOrden(nuevo);
     
}

public void ordenamiento_materias_alternativos(registro_ingenieria reging){
//System.out.println("[Metodo ordenamiento_materias_alternativo]");
               // System.out.println("LONGITUD NOTAS reging= "+
                 //       "mas pequeño: "+this.getMas_pequeño()+" - mas grande: "+this.getMas_grande());
              

    for(int x=this.getMas_pequeño(); x<=this.getMas_grande();x++){//para comparar con el año. se ordenan las materia por año y periodo. se separan por año y despues se ordenan por periodo del mismo año. eso se hace en cada iteracion
       // System.out.println(x);
     //   System.out.println("ORDENAMIENTO--año:"+x);

   //-----FASE 1: ANALISANDO MATERIAS NORMALES E INTESIVOS Y EQUIVALENCIAS. OBTENIENDO SUBGRUPOS POR PERIODO Y (si es normal o intensivo)
        for(int y=0; y<reging.getRecord().size(); y=y+11){//ciclo para recorrer el LinkedList ....sugerencia se puede mejorar este ciclo haciendo una division con el % ya que si se divide entre 2 y el resultado es exacto se acumula en un registro sino en el otro...es util para los semestres pares e impares
            //ANALISIS PRIMER PERIODO DEL AÑO
                if(reging.getRecord().get(y+5).trim().equalsIgnoreCase("E1-"+x)){//equivalencias que pertenecen al primer periodo
                    //   System.out.println("1-"+x);
                    this.getAuxE1().add(reging.getRecord().get(y)); //codigo de la materia
                    this.getAuxE1().add(this.comparacion_pensum(reging.getRecord().get(y+1))); //nombre de la materia
                    this.getAuxE1().add("0"); //definitiva de la materia
                    this.getAuxE1().add("0"); //nota de reparacion de la materia
                    this.getAuxE1().add(this.condicion_materia(reging.getRecord().get(y+4))); //condicion en la que fue inscrita la materia
                    this.getAuxE1().add(reging.getRecord().get(y+5)); //periodo academico en la que fue inscrita la materia
                    this.getAuxE1().add("0"); //definitiva reparacion de la materia
                    this.getAuxE1().add(reging.getRecord().get(y+7)); //porcentaje de inasistencia de la materia
                    this.getAuxE1().add(reging.getRecord().get(y+8)); //codigo especialidad de la materia
                    this.getAuxE1().add(reging.getRecord().get(y+9)); //nota de laboratorio de la materia
                    this.getAuxE1().add(reging.getRecord().get(y+10)); //semestre de la materia

                }     
                
                if(reging.getRecord().get(y+5).trim().equalsIgnoreCase("1-"+x)){//todos los que pertenecen al primer periodo
                    //   System.out.println("1-"+x);
                    this.getAux1().add(reging.getRecord().get(y)); //codigo de la materia
                    this.getAux1().add(this.comparacion_pensum(reging.getRecord().get(y+1))); //nombre de la materia
                    this.getAux1().add(reging.getRecord().get(y+2)); //definitiva de la materia
                    this.getAux1().add(this.calificacion(reging.getRecord().get(y+3))); //nota de reparacion de la materia
                    this.getAux1().add(this.condicion_materia(reging.getRecord().get(y+4))); //condicion en la que fue inscrita la materia
                    this.getAux1().add(reging.getRecord().get(y+5)); //periodo academico en la que fue inscrita la materia
                    this.getAux1().add(reging.getRecord().get(y+6)); //definitiva reparacion de la materia
                    this.getAux1().add(reging.getRecord().get(y+7)); //porcentaje de inasistencia de la materia
                    this.getAux1().add(reging.getRecord().get(y+8)); //codigo especialidad de la materia
                    this.getAux1().add(reging.getRecord().get(y+9)); //nota de laboratorio de la materia
                    this.getAux1().add(reging.getRecord().get(y+10)); //semestre de la materia

                }

                if(reging.getRecord().get(y+5).trim().equalsIgnoreCase("I1-"+x)){//periodo intensivo asociado al primer periodo academico.
                   // System.out.println("INTENSIVO - I1-"+x);
                    this.getAuxI1().add(reging.getRecord().get(y)); //codigo de la materia
                    this.getAuxI1().add(this.comparacion_pensum(reging.getRecord().get(y+1))); //nombre de la materia
                    this.getAuxI1().add(reging.getRecord().get(y+2)); //definitiva de la materia
                    this.getAuxI1().add(this.calificacion(reging.getRecord().get(y+3))); //nota de reparacion de la materia
                    this.getAuxI1().add(this.condicion_materia(reging.getRecord().get(y+4))); //condicion en la que fue inscrita la materia
                    this.getAuxI1().add(reging.getRecord().get(y+5)); //periodo academico en la que fue inscrita la materia
                    this.getAuxI1().add(reging.getRecord().get(y+6)); //definitiva reparacion de la materia
                    this.getAuxI1().add(reging.getRecord().get(y+7)); //porcentaje de inasistencia de la materia
                    this.getAuxI1().add(reging.getRecord().get(y+8)); //codigo especialidad de la materia
                    this.getAuxI1().add(reging.getRecord().get(y+9)); //nota de laboratorio de la materia
                    this.getAuxI1().add(reging.getRecord().get(y+10)); //semestre de la materia
                }
                //ANALISIS SEGUNDO PERIODO DEL AÑO
                 if(reging.getRecord().get(y+5).trim().equalsIgnoreCase("E2-"+x)){//todos los que pertenecen al segundo periodo
                     //   System.out.println("2-"+x);
                    this.getAuxE2().add(reging.getRecord().get(y)); //codigo de la materia
                    this.getAuxE2().add(this.comparacion_pensum(reging.getRecord().get(y+1))); //nombre de la materia
                    this.getAuxE2().add("0"); //definitiva de la materia
                    this.getAuxE2().add("0"); //nota de reparacion de la materia
                    this.getAuxE2().add(this.condicion_materia(reging.getRecord().get(y+4))); //condicion en la que fue inscrita la materia
                    this.getAuxE2().add(reging.getRecord().get(y+5)); //periodo academico en la que fue inscrita la materia
                    this.getAuxE2().add("0"); //definitiva reparacion de la materia
                    this.getAuxE2().add(reging.getRecord().get(y+7)); //porcentaje de inasistencia de la materia
                    this.getAuxE2().add(reging.getRecord().get(y+8)); //codigo especialidad de la materia
                    this.getAuxE2().add(reging.getRecord().get(y+9)); //nota de laboratorio de la materia
                    this.getAuxE2().add(reging.getRecord().get(y+10)); //semestre de la materia
                }
                 
                if(reging.getRecord().get(y+5).trim().equalsIgnoreCase("2-"+x)){//todos los que pertenecen al segundo periodo
                     //   System.out.println("2-"+x);
                    this.getAux2().add(reging.getRecord().get(y)); //codigo de la materia
                    this.getAux2().add(this.comparacion_pensum(reging.getRecord().get(y+1))); //nombre de la materia
                    this.getAux2().add(reging.getRecord().get(y+2)); //definitiva de la materia
                    this.getAux2().add(this.calificacion(reging.getRecord().get(y+3))); //nota de reparacion de la materia
                    this.getAux2().add(this.condicion_materia(reging.getRecord().get(y+4))); //condicion en la que fue inscrita la materia
                    this.getAux2().add(reging.getRecord().get(y+5)); //periodo academico en la que fue inscrita la materia
                    this.getAux2().add(reging.getRecord().get(y+6)); //definitiva reparacion de la materia
                    this.getAux2().add(reging.getRecord().get(y+7)); //porcentaje de inasistencia de la materia
                    this.getAux2().add(reging.getRecord().get(y+8)); //codigo especialidad de la materia
                    this.getAux2().add(reging.getRecord().get(y+9)); //nota de laboratorio de la materia
                    this.getAux2().add(reging.getRecord().get(y+10)); //semestre de la materia
                }
                
                 if(reging.getRecord().get(y+5).trim().equalsIgnoreCase("I2-"+x)){//periodo intensivo asociado al segundo periodo academico.
                   // System.out.println("INTENSIVO - I2-"+x);
                    this.getAuxI2().add(reging.getRecord().get(y)); //codigo de la materia
                    this.getAuxI2().add(this.comparacion_pensum(reging.getRecord().get(y+1))); //nombre de la materia
                    this.getAuxI2().add(reging.getRecord().get(y+2)); //definitiva de la materia
                    this.getAuxI2().add(this.calificacion(reging.getRecord().get(y+3))); //nota de reparacion de la materia
                    this.getAuxI2().add(this.condicion_materia(reging.getRecord().get(y+4))); //condicion en la que fue inscrita la materia
                    this.getAuxI2().add(reging.getRecord().get(y+5)); //periodo academico en la que fue inscrita la materia
                    this.getAuxI2().add(reging.getRecord().get(y+6)); //definitiva reparacion de la materia
                    this.getAuxI2().add(reging.getRecord().get(y+7)); //porcentaje de inasistencia de la materia
                    this.getAuxI2().add(reging.getRecord().get(y+8)); //codigo especialidad de la materia
                    this.getAuxI2().add(reging.getRecord().get(y+9)); //nota de laboratorio de la materia
                    this.getAuxI2().add(reging.getRecord().get(y+10)); //semestre de la materia
                }



        }//fin reccorrido del LinkedList


          //  System.out.println(this.getAux1().size()+" - "+this.getAux2().size());
         //FASE 2:-----LLENANDO EL LinkedList PRINCIPAL DONDE DEFINITIVAMENTE ESTARAN LAS MATERIAS ORDENADAS, TANTO NORMALES COMO INTENSIVOS Y EQUIVALENCIAS

        for(int r=0; r<this.getAuxE1().size(); r=r+11){//reordeando materias por periodo academico que empiece por 1
            this.getOrden().add(this.getAuxE1().get(r));//0  codigo de la materia
            this.getOrden().add(this.getAuxE1().get(r+1));//1  nombre de la materia
            this.getOrden().add(this.getAuxE1().get(r+2));//2  definitiva de la materia
            this.getOrden().add(this.getAuxE1().get(r+3));//3  nota de reparacion
            this.getOrden().add(this.getAuxE1().get(r+4));//4  condicion de la materia
            this.getOrden().add(this.getAuxE1().get(r+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAuxE1().get(r+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAuxE1().get(r+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAuxE1().get(r+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAuxE1().get(r+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAuxE1().get(r+10));//10  semestre de la materia
        }
        
        for(int r=0; r<this.getAux1().size(); r=r+11){//reordeando materias por periodo academico que empiece por 1
            this.getOrden().add(this.getAux1().get(r));//0  codigo de la materia
            this.getOrden().add(this.getAux1().get(r+1));//1  nombre de la materia
            this.getOrden().add(this.getAux1().get(r+2));//2  definitiva de la materia
            this.getOrden().add(this.getAux1().get(r+3));//3  nota de reparacion
            this.getOrden().add(this.getAux1().get(r+4));//4  condicion de la materia
            this.getOrden().add(this.getAux1().get(r+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAux1().get(r+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAux1().get(r+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAux1().get(r+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAux1().get(r+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAux1().get(r+10));//10  semestre de la materia
        }

         for(int r=0; r<this.getAuxI1().size(); r=r+11){//reordeando materias de INTESIVOS por periodo academico que empiece por I1
            this.getOrden().add(this.getAuxI1().get(r));//0  codigo de la materia
            this.getOrden().add(this.getAuxI1().get(r+1));//1  nombre de la materia
            this.getOrden().add(this.getAuxI1().get(r+2));//2  definitiva de la materia
            this.getOrden().add(this.getAuxI1().get(r+3));//3  nota de reparacion
            this.getOrden().add(this.getAuxI1().get(r+4));//4  condicion de la materia
            this.getOrden().add(this.getAuxI1().get(r+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAuxI1().get(r+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAuxI1().get(r+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAuxI1().get(r+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAuxI1().get(r+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAuxI1().get(r+10));//10  semestre de la materia
        }

        for(int s=0; s<this.getAuxE2().size(); s=s+11){//reordenando materias por periodo academico que empiece por 2
            this.getOrden().add(this.getAuxE2().get(s));//0  codigo de la materia
            this.getOrden().add(this.getAuxE2().get(s+1));//1  nombre de la materia
            this.getOrden().add(this.getAuxE2().get(s+2));//2  definitiva de la materia
            this.getOrden().add(this.getAuxE2().get(s+3));//3  nota de reparacion
            this.getOrden().add(this.getAuxE2().get(s+4));//4  condicion de la materia
            this.getOrden().add(this.getAuxE2().get(s+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAuxE2().get(s+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAuxE2().get(s+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAuxE2().get(s+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAuxE2().get(s+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAuxE2().get(s+10));//10  semestre de la materia
        }
        
        for(int s=0; s<this.getAux2().size(); s=s+11){//reordenando materias por periodo academico que empiece por 2
            this.getOrden().add(this.getAux2().get(s));//0  codigo de la materia
            this.getOrden().add(this.getAux2().get(s+1));//1  nombre de la materia
            this.getOrden().add(this.getAux2().get(s+2));//2  definitiva de la materia
            this.getOrden().add(this.getAux2().get(s+3));//3  nota de reparacion
            this.getOrden().add(this.getAux2().get(s+4));//4  condicion de la materia
            this.getOrden().add(this.getAux2().get(s+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAux2().get(s+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAux2().get(s+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAux2().get(s+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAux2().get(s+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAux2().get(s+10));//10  semestre de la materia
        }

        for(int s=0; s<this.getAuxI2().size(); s=s+11){//reordenando materias de INTENSIVOS por periodo academico que empiece por I2
            this.getOrden().add(this.getAuxI2().get(s));//0  codigo de la materia
            this.getOrden().add(this.getAuxI2().get(s+1));//1  nombre de la materia
            this.getOrden().add(this.getAuxI2().get(s+2));//2  definitiva de la materia
            this.getOrden().add(this.getAuxI2().get(s+3));//3  nota de reparacion
            this.getOrden().add(this.getAuxI2().get(s+4));//4  condicion de la materia
            this.getOrden().add(this.getAuxI2().get(s+5));//5  periodo academico de la materia
            this.getOrden().add(this.getAuxI2().get(s+6));//6  definitiva reparacion de la materia
            this.getOrden().add(this.getAuxI2().get(s+7));//7  porcentaje de inasistencia de la materia
            this.getOrden().add(this.getAuxI2().get(s+8));//8  codigo especialidad de la materia
            this.getOrden().add(this.getAuxI2().get(s+9));//9  nota de laboratorio de la materia
            this.getOrden().add(this.getAuxI2().get(s+10));//10  semestre de la materia
        }


            //limpiando los LinkedListes auxiliares para usos posteriores
                this.getAux1().clear();
                this.getAux2().clear();
                this.getAuxI1().clear();
                this.getAuxI2().clear();
                this.getAuxE1().clear();
                this.getAuxE2().clear();



    }//fin comparar año
    
    this.reordenamientoXperiodoXsemestre();
    
System.out.println("REGISTROS RECORDS: "+reging.getRecord().size()/11+" registros");
System.out.println("FIN ORDENAMIENTO ALTERNATIVO: "+this.getOrden().size()/11+" registros");
}





/**Metodo que sirve para identificar la materia y colocar su nombre a partir de su codigo.
 este considera las materias del pensum, servicion comunitario y ...."electivas reales"*/
public String comparacion_pensum(String informacion){
 //System.out.println("[Metodo comparacion_pensum]");
    String salida=null;


    //System.out.println("pensum: "+this.getPensum().size()+" materia comparar: "+informacion);

    for(int y=0; y<this.getPensum().size();y=y+10){//recorriendo el pensum
       // System.out.println("actual: "+informacion+"     ----     pensum: "+this.getPensum().get(y));
        if(informacion.equalsIgnoreCase(this.getPensum().get(y))){
            salida=this.getPensum().get(y+2);
            break;
        }else{ salida=null;}

    }//fin recorrido pensum

    if(salida==null){//en caso que despues de recorrer el pensum de la carrera no encontro la materia del alumno se busca en la de servicio comunitario

         for(int sc=0; sc<this.getSer_comunitario().size();sc=sc+10){//recorriendo las materias de servicio comunitario

                if(informacion.equalsIgnoreCase(this.getSer_comunitario().get(sc))){
                    salida=this.getSer_comunitario().get(sc+2);
                    break;
                 }else{ salida=null;}

         }//fin recorrido servicio comunitario

    }

    if(salida==null){//en caso que despues de recorrer el pensum de la carrera no encontro la materia del alumno se busca en la lista de electivas reales
        System.out.println("REVISANDO CASO ELECTIVA "+informacion+"  electivas tamaño"+this.getElectivas().size());

         for(int ele=0; ele<this.getElectivas().size();ele=ele+4){//recorriendo las materias electivas reales
            System.out.println("SERA IGUAL "+informacion.equalsIgnoreCase(this.getElectivas().get(ele))+"----actual="+informacion+"  electivas"+this.getElectivas().get(ele));
            
                if(informacion.equalsIgnoreCase(this.getElectivas().get(ele))){
                    salida=this.getElectivas().get(ele+1);
                    System.out.println("Comparacion:----- "+informacion+" - "+salida);

                    break;
                 }else{ salida=null;}

         }//fin recorrido servicio comunitario


    }


   /* this.getElectivas().add(pensum.getString("codigo"));//0
                this.getElectivas().add(pensum.getString("asignatura"));//1
                this.getElectivas().add(String.valueOf(pensum.getInt("uc")));//2
                this.getElectivas().add(String.valueOf(pensum.getInt("tipo")));//3*/





//System.out.println("SALIDA COMPARACION PENSUM: codigo: "+informacion+" - traduccion: "+salida);
                                     
return salida;
}


public String seccion(String especialidad){
//System.out.println("[Metodo seccion]");
String ubicacion=null;
//CAGUA
if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA"))ubicacion="IM-101";
if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL"))ubicacion="EI-101";
if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA"))ubicacion="CP-101";
if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL"))ubicacion="ES-101";
if(especialidad.equalsIgnoreCase("TSU EN TURISMO"))ubicacion="TU-101";
//MARACAY
if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA"))ubicacion="IA-101";
if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL"))ubicacion="IC-101";
if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA"))ubicacion="IE-101";
if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA"))ubicacion="EE-101";
if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS"))ubicacion="IS-101";
if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES"))ubicacion="IT-101";
if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA"))ubicacion="EN-101";
if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA"))ubicacion="LE-101";
if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL"))ubicacion="AM-101";
//MERIDA
if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE"))ubicacion="AD-101";
if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL"))ubicacion="MD-101";
if(especialidad.equalsIgnoreCase("LIC. EN TURISMO"))ubicacion="LT-101";
if(especialidad.equalsIgnoreCase("INGENIERIA AGROINDUSTRIAL"))ubicacion="IG-101";

return ubicacion;
}

public String secciones_dinamicas(JComboBox elemento, String componente,String condicion){
   //System.out.println("[Metodo secciones_dinamicas]");
    String deposito=null, salida=null;

   
    
    deposito=componente;
    this.setCant_1(Integer.valueOf(componente.substring(3)));
   

    if(elemento.getSelectedIndex()>this.getCant_2()){

        this.setCant_1(this.getCant_1()+(100*(elemento.getSelectedIndex()-this.getCant_2())));
        this.setCant_2(elemento.getSelectedIndex());
    }else{
        if(elemento.getSelectedIndex()<this.getCant_2()){
            this.setCant_1(this.getCant_1()-(100*(this.getCant_2()-elemento.getSelectedIndex())));
            this.setCant_2(elemento.getSelectedIndex());
        }
    }

    
    if(condicion.equalsIgnoreCase("suma"))this.setCant_1(this.getCant_1()+1);
    if(condicion.equalsIgnoreCase("resta"))this.setCant_1(this.getCant_1()-1);


 //System.out.println(this.getCant_1());

salida=deposito.substring(0,3).concat(String.valueOf(this.getCant_1()));
return salida;
}


public String seccion_mejorada(String seccion, String turno){
    String texto =null, carr = "--", sem = null, tur = null, num = null;
    //--1SDIURNO01
    System.out.println("seccion: "+seccion);
    
    texto = seccion.split("-")[0];//solo iniciales de la carrera. ejm: IM
    
        if(texto.equalsIgnoreCase("IC")){carr = "ICV";}
        if(texto.equalsIgnoreCase("IS")){carr = "IST";}
        if(texto.equalsIgnoreCase("IT")){carr = "ITC";}

        if(texto.equalsIgnoreCase("EE")){carr = "IEO";}
        if(texto.equalsIgnoreCase("ES")){carr = "LES";}
        if(texto.equalsIgnoreCase("AD")){carr = "LAD";}

        if(texto.equalsIgnoreCase("AM")){carr = "LAG";}
        if(texto.equalsIgnoreCase("LT")){carr = "LTR";}
        if(texto.equalsIgnoreCase("MD")){carr = "TMD";}
        if(texto.equalsIgnoreCase("IG")){carr = "IAG";}
    
    texto = seccion.split("-")[1];//solo semestre y numero del aula. ejm: 301
        
        sem = texto.substring(0, 1)+"S"; //ejm: 3
        num = texto.substring(1); //ejm: 01
    
   if(turno.equalsIgnoreCase("DIURNO")){tur = "D";}
   if(turno.equalsIgnoreCase("NOCTURNO")){tur = "N";}
        
   texto =  carr+sem+tur+num;
   System.out.println("Seccion mejorada: "+texto);
   
   return texto;//nomeclatura completa
   
   /*     //CAGUA
         if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA"))ubicacion="IM-101";
         if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL"))ubicacion="EI-101";
         if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA"))ubicacion="CP-101";
         if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL"))ubicacion="ES-101";
         if(especialidad.equalsIgnoreCase("TSU EN TURISMO"))ubicacion="TU-101";
         //MARACAY
         if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA"))ubicacion="IA-101";
         if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL"))ubicacion="IC-101";
         if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA"))ubicacion="IE-101";
         if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA"))ubicacion="EE-101";
         if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS"))ubicacion="IS-101";
         if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES"))ubicacion="IT-101";
         if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA"))ubicacion="EN-101";
         if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA"))ubicacion="LE-101";
         if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL"))ubicacion="AM-101";
         //MERIDA
         if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE"))ubicacion="AD-101";
         if(especialidad.equalsIgnoreCase("TSU EN MECANICA DENTAL"))ubicacion="MD-101";
         if(especialidad.equalsIgnoreCase("LIC. EN TURISMO"))ubicacion="LT-101";
*/
         
         
         
         
    
   
}

//15733762
public void llenado_materia(Connection con, String seccion, JTable tabla, LinkedList<String> autorizadas,String turno, String periodo){
    System.out.println("[Metodo llenado_materia]");
    boolean decision=true;
    this.getLlenado().clear();
    this.getPosicion().clear();
    int hi=0;
            Statement sentencia = null;
            ResultSet resultado = null;


        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE periodo='"+periodo+"' AND (seccion = '"+ seccion + "' OR seccion = '"+this.seccion_mejorada(seccion, turno)+"');");

               while(resultado.next()){//cargando el horario de la seccion en un LinkedList
                this.getLlenado().add(resultado.getString("carrera"));//0
                this.getLlenado().add(resultado.getInt("semestre"));//1
                this.getLlenado().add(resultado.getString("seccion"));//2
                this.getLlenado().add(resultado.getString("dia"));//3
                this.getLlenado().add(resultado.getString("hora_inicio"));//4
                this.getLlenado().add(resultado.getString("hora_fin"));//5
                this.getLlenado().add(resultado.getString("periodo"));//6
                this.getLlenado().add(resultado.getString("materia"));//7
                this.getLlenado().add(resultado.getString("docente"));//8

               }
/*
            //para verificacion
            for(int a=0; a<this.getLlenado().size();a=a+9){
            System.out.println("VERIFICANDO HORARIO: "+this.getLlenado().get(a+3)+" "+this.getLlenado().get(a+7));

            }

            for(int b=0; b<autorizadas.size(); b=b+4){
            System.out.println("AUTORIZADAS: "+autorizadas.get(b)+" "+autorizadas.get(b+1)+" "+autorizadas.get(b+2));
            }

*/

             
System.out.println("%%%%%%%%%MATERIAS OFERTADAS DE LA SECCION "+seccion+"%%%%%%%%%%%");
         for(int a=0; a<this.getLlenado().size();a=a+9){//recorriendo el registro


           // System.out.println("HORAS... "+this.conversion_hora(a, this.getLlenado()));//convirtiendo la hora a hora militar

           
            System.out.println("ANALIZANDO DIA");
            if(this.turnos_materias(this.conversion_hora(a, this.getLlenado()), turno) || this.getLlenado().get(a+3).toString().equalsIgnoreCase("SABADO") || this.getLlenado().get(a+3).toString().equalsIgnoreCase("DOMINGO") ){//si la materia corresponde a el turno del alumno la habilita para los otros analisis. El SABADO es solo una excusa

                 System.out.println("dia: "+this.getLlenado().get(a+3)+",  sabado: "+this.getLlenado().get(a+3).toString().equalsIgnoreCase("SABADO")+",  domingo: "+this.getLlenado().get(a+3).toString().equalsIgnoreCase("DOMINGO"));
             System.out.println("COMPARANDO AUTORIZADAS Y OFERTADAS");
                for(int aut=0 ;aut<autorizadas.size(); aut=aut+4){//revisando las materias autorizadas y comparandola con las ofertadas
                 System.out.println("llenado: "+this.getLlenado().get(a+7)+" - autorizadas: "+autorizadas.get(aut+1));
                 
                     if( this.getLlenado().get(a+7).toString().equalsIgnoreCase(autorizadas.get(aut+1)) || //COMPARANDO NOMBRES iguales
                        ( this.getLlenado().get(a+7).toString().endsWith("("+autorizadas.get(aut+1)+")") && // o comparando que la ofertada termine sea electiva terminando con (ELECTIVA TECNICA) 0 (ELECTIVA NO TECNICA)
                          this.getLlenado().get(a+1).toString().equalsIgnoreCase(autorizadas.get(aut+3)) )// y que sea del mismo semestre
                             ){
                         System.out.println("COMPARACION ACERTADA");
                     decision=true;
                     break;
                     }else{decision=false;}


                     }//fin revision autorizadas y ofertadas


                         if(decision==true){

                             for(int col=1; col<tabla.getColumnCount();col++){//recorriendo columnas de horarios
                                if(this.getLlenado().get(a+3).toString().equalsIgnoreCase(tabla.getColumnName(col))){//buscando el dia de la materia
                                 this.getPosicion().add(0, col);
                                 System.out.print("nombre "+tabla.getColumnName(col));
                                 System.out.print(" columna: "+this.getPosicion().get(0)+" ");
                                 break;
                                 
                                }
                             }//fin recorrido de columnas horarios


                             for(int fila=0; fila<tabla.getRowCount();fila++){//ubicando la fila donde coincide la hora
                                    System.out.println("ENTRADA: horario -> "+tabla.getValueAt(fila,0)+" ||| oferta:"+this.getLlenado().get(a+4));
                                if(this.getLlenado().get(a+4).toString().equalsIgnoreCase(tabla.getValueAt(fila,0).toString())){//buscando hora de inicio
                                   this.getPosicion().add(1, fila);
                                        System.out.println("h_inicio: "+tabla.getValueAt(fila,0).toString()+" posicion: "+this.getPosicion().get(1));
                                   break;


                                 }

                             }//fin buscando la hora de inicio


                              for(int fila2=0; fila2<tabla.getRowCount();fila2++){//ubicacion de la fila donde se halla la hora de salida de la materia
                                  System.out.println("SALIDA: horario -> "+tabla.getValueAt(fila2,0)+" ||| oferta:"+this.getLlenado().get(a+5));
                               if(this.getLlenado().get(a+5).toString().equalsIgnoreCase(tabla.getValueAt(fila2,0).toString())){//buscando coincidencia de la hora salida
                                this.getPosicion().add(2, fila2);
                                System.out.println(" h_salida: "+tabla.getValueAt(fila2,0).toString()+" posicion"+this.getPosicion().get(2));
                                break;
                               
                               }

                              }//fin de la busqueda de la segunda hora

                             int total=0;
                             total=this.getPosicion().get(2)-this.getPosicion().get(1);

                              tabla.setValueAt(this.getLlenado().get(a+7), this.getPosicion().get(1), this.getPosicion().get(0));

                         }//fin decision


            }//fin de comparacion de turnos


         }//fin recorrido registro



            
  
            sentencia.close();
            resultado.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }
}



/**Este metodo se encarga de inscribir las materias seleccionada por el estudiante, ademas
 se coloca un limite maximo de unidades de credito, el nombre de la tabla de donde se mostrara
 las materias inscritas y el modelo que manipula sus datos y la condicion con la que se inscribe la materia*/
public void materias_inscribir(JTable tabla, DefaultTableModel modelo, JTable tabla2, int limite_uc, String seccion, String condicion, horario hor){
System.out.println("[Metodo materias_inscribir]");
String salida=null, materia=null, hora=null;
int contador=0, semestre=0;

 tabla.setModel(modelo);
 LinkedList <String> nuevo = new LinkedList<>();

 //System.out.println("tamaño "+this.getPensum().size());
// System.out.println("reg materias ofertadas"+this.getLlenado().size());

semestre = hor.getSemestre().getSelectedIndex()+1;
materia=tabla2.getValueAt(tabla2.getSelectedRow(), tabla2.getSelectedColumn()).toString();//guardando el nombre de la materia seleccionada en una variable. mas facil de manejar
System.out.println("materia inscribir "+materia);



    if(materia.contains("ELECTIVA")){//analisis cuando la materia es electiva
        System.out.println("Se selecciono una materia electiva para inscribir");
        this.tipo_electiva(limite_uc, materia, seccion, String.valueOf(semestre), nuevo, hora, contador, condicion, modelo, hor);
        
    }else{//analissis cuando la materia  no es electiva


       if(materia.contains("SERVICIO COMUNITARIO")){//revisando si la materia es servicio comunitario
        System.out.println("Se selecciono una materia servicio comunitario para inscribir");
        this.inscripcion_servicio_comunitario(tabla, modelo, hor, materia, limite_uc, seccion, condicion);
       }else{//en caso de que sea una materia normal

                    //MATERIA NORMAL----------
                for(int i=0; i<this.getPensum().size(); i=i+10){//recorriendo el pensum para hallar las materias ofertadas y tomar los datos necesarios
                        System.out.println("seleccionada: "+materia+" pensum: "+this.getPensum().get(i+2));

                            if(materia.equalsIgnoreCase(this.getPensum().get(i+2).toString())){//buscando lo necesario para inscribir.materias se llamen iguales

                                System.out.println("Materia hallada....");

                                if(this.getCredito()+Integer.valueOf(this.getPensum().get(i+3))<=limite_uc){//estableciendo limite de unidades de credito a inscribir

                                        for(int b=0;b<this.getLlenado().size(); b=b+9){//ubicando el horario y el docente
                                            /*System.out.println("materia "+this.getLlenado().get(b+7)+" dia "+this.getLlenado().get(b+3));
                                            System.out.println("comparacion materias: "+this.getPensum().get(i+2)+" = "+this.getLlenado().get(b+7));
                                            System.out.println("comparacion turnos: "+this.turnos_materias(this.conversion_hora(b, this.getLlenado()))+" = "+hor.getTurno().getText());
                                            System.out.println("comparacion secciones: "+seccion+" = "+this.getLlenado().get(b+2));*/

                                            if( this.getPensum().get(i+2).equalsIgnoreCase(this.getLlenado().get(b+7).toString())  && //verificando que sean las mismas materias
                                               (this.turnos_materias(this.conversion_hora(b, this.getLlenado()),hor.getTurno().getText()) || this.getLlenado().get(b+3).toString().equalsIgnoreCase("SABADO") || this.getLlenado().get(b+3).toString().equalsIgnoreCase("DOMINGO")) && //verificando que la materia pertenzca al mismo turno del alumno ya que esta se obtiene directamente del LinkedList y no de la tabla
                                             //  tabla2.getColumnName(tabla2.getSelectedColumn()).equalsIgnoreCase(this.getLlenado().get(b+3).toString()) &&  //verificando que sea el mismo dia
                                             //  tabla2.getValueAt(tabla2.getSelectedRow(),0).toString().equalsIgnoreCase(this.getLlenado().get(b+4).toString()) &&//verificando que sean la misma hora
                                               (seccion.equalsIgnoreCase(this.getLlenado().get(b+2).toString()) || this.seccion_mejorada(seccion,hor.getTurno().getText()).equalsIgnoreCase(this.getLlenado().get(b+2).toString())) //que sea la misma seccion. dos estructuras. vieja IT-302, nueva ITC3SD02 
                                               ){
                                                      /*  System.out.println(this.getPensum().get(i+0)+"  "+this.getLlenado().get(b+6).toString());
                                                        System.out.println(tabla2.getColumnName(tabla2.getSelectedColumn())+"  "+this.getLlenado().get(b+3).toString());
                                                        System.out.println(tabla2.getValueAt(tabla2.getSelectedRow(),0)+" "+this.getLlenado().get(b+4).toString());
                                                        System.out.println(seccion+" "+this.getLlenado().get(b+2).toString());*/

                                                        contador=contador+1;
                                                        //this.tipo_de_envio(b, i, materia, nuevo, hora, contador, condicion, modelo, hor);


                                                        hora=this.getLlenado().get(b+4).toString().substring(0,5)+" a "+this.getLlenado().get(b+5).toString().substring(6);

                                                        nuevo.add(this.getPensum().get(i+1));//semestre
                                                        nuevo.add(this.getPensum().get(i+0));//codigo
                                                        nuevo.add(this.getPensum().get(i+2));//materia
                                                        if(contador>1){nuevo.add("0");}else{nuevo.add(this.getPensum().get(i+3));}
                                                        //nuevo.add(this.getPensum().get(i+3));//unidades de credito
                                                        nuevo.add(this.getLlenado().get(b+2).toString());//seccion
                                                        nuevo.add(this.getLlenado().get(b+3)+" - "+hora);//horario
                                                        nuevo.add(this.getLlenado().get(b+8).toString());//docente
                                                        nuevo.add(condicion);//condicion de la materia
                                                      //System.out.println("materia interna "+this.getLlenado().get(b+7)+" dia "+this.getLlenado().get(b+3));
                                                        modelo.addRow(nuevo.toArray());

                                                     // break;




                                                   conexion_base_de_datos con= new conexion_base_de_datos();
                                                    this.envio_informacion_db(con.getConexion(),
                                                                              hor.getParametros().getText(),//cedula
                                                                              hor.getEstudiante().getText(),//estudiante
                                                                              hor.getEspecialidad().getSelectedItem().toString(),//carrera
                                                                              Integer.parseInt(this.getPensum().get(i+1).toString()),//semestre
                                                                              this.getPensum().get(i+0).toString(),//codigo
                                                                              this.getPensum().get(i+2).toString(),//materia
                                                                              Integer.parseInt(nuevo.get(3).toString()),//UC
                                                                              this.getLlenado().get(b+3).toString(),//dia
                                                                              hora.toString(),//hora
                                                                              this.getLlenado().get(b+2).toString(),//seccion
                                                                              hor.getPeriodo_actual().getText(),//periodo
                                                                              this.getLlenado().get(b+8).toString(),//docente
                                                                              condicion.toString()//condicion
                                                                            );



                                                         nuevo.clear();//limpiando el LinkedList para cada ciclo


                                               }//fin comparaciones


                                        }//fin ubicando



                                        this.setCredito(this.getCredito()+Integer.valueOf(this.getPensum().get(i+3)));
                                       // modelo.addRow(nuevo.toArray());

                                  }else{//fin del ciclo para evitar que se sobrepasen las unidades de credito a inscribir

                                     ima.mensaje_informacion("NO PUEDE INSCRIBIRLA PORQUE EXCEDE LAS UC MAXIMAS PERMITIDAS\n",  "NOTIFICACION", "alto", "png");


                                    }//fin estableciendo limite de uc


                               break;
                            }//fin comparacion materia seleccionada vs pensum

                }//fin del recorrido del pensum


         }//fin analisis servicio comunitario. cuando es una materia normal la seleccionada

    }//fin proceso cuando la materia seleccionada es normal y no electiva

nuevo.clear();
//System.out.println(tabla.getRowCount());
       


}


/**Este metodo se encarga de la inscripcion de la materia de servicio comunitario
 que es un requisito que no aparece en el pensum original*/
public void inscripcion_servicio_comunitario(JTable tabla, DefaultTableModel modelo, horario hor, String materia, int limite_uc, String seccion, String condicion){
  /*  Servicio Comunitario distribucion:
    posicion 0 = codigo
    posicion 1 = semestre
    posicion 2 = asignatura
    posicion 3 = uc
    posicion 4 = prelacion_1
    posicion 5 = prelacion_2
    posicion 6 = prelacion_3*/
String salida=null,  hora=null;
int contador=0, semestre=0;
tabla.setModel(modelo);
LinkedList <String> nuevo = new LinkedList<>();

    for(int i=0; i<hor.getTraspaso_comunitario().size(); i=i+10){//recorriendo el pensum para hallar las materias ofertadas y tomar los datos necesarios
                       // System.out.println("seleccionada: "+materia+" pensum: "+this.getPensum().get(i+2));

                            if(materia.equalsIgnoreCase(hor.getTraspaso_comunitario().get(i+2))){//buscando lo necesario para inscribir.materias se llamen iguales

                              //  System.out.println("Materia hallada....");

                                if(this.getCredito()+Integer.valueOf(hor.getTraspaso_comunitario().get(i+3))<=limite_uc){//estableciendo limite de unidades de credito a inscribir

                                        for(int b=0;b<this.getLlenado().size(); b=b+9){//ubicando el horario y el docente
                                          //  System.out.println("materia "+this.getLlenado().get(b+7)+" dia "+this.getLlenado().get(b+3));

                                            if( hor.getTraspaso_comunitario().get(i+2).equalsIgnoreCase(this.getLlenado().get(b+7).toString())  && //verificando que sean las mismas materias
                                               (this.turnos_materias(this.conversion_hora(b, this.getLlenado()), hor.getTurno().getText()) || this.getLlenado().get(b+3).toString().equalsIgnoreCase("SABADO") || this.getLlenado().get(b+3).toString().equalsIgnoreCase("DOMINGO")) && //verificando que la materia pertenzca al mismo turno del alumno ya que esta se obtiene directamente del LinkedList y no de la tabla
                                             //  tabla2.getColumnName(tabla2.getSelectedColumn()).equalsIgnoreCase(this.getLlenado().get(b+3).toString()) &&  //verificando que sea el mismo dia
                                             //  tabla2.getValueAt(tabla2.getSelectedRow(),0).toString().equalsIgnoreCase(this.getLlenado().get(b+4).toString()) &&//verificando que sean la misma hora
                                               seccion.equalsIgnoreCase(this.getLlenado().get(b+2).toString()) //que sea la misma seccion
                                               ){
                                                        contador=contador+1;
                                                      
                                                        hora=this.getLlenado().get(b+4).toString().substring(0,5)+" a "+this.getLlenado().get(b+5).toString().substring(6);

                                                        nuevo.add(hor.getTraspaso_comunitario().get(i+1));//semestre
                                                        nuevo.add(hor.getTraspaso_comunitario().get(i+0));//codigo
                                                        nuevo.add(hor.getTraspaso_comunitario().get(i+2));//materia
                                                        if(contador>1){nuevo.add("0");}else{nuevo.add(hor.getTraspaso_comunitario().get(i+3));}
                                                        //nuevo.add(this.getPensum().get(i+3));//unidades de credito
                                                        nuevo.add(this.getLlenado().get(b+2).toString());//seccion
                                                        nuevo.add(this.getLlenado().get(b+3)+" - "+hora);//horario
                                                        nuevo.add(this.getLlenado().get(b+8).toString());//docente
                                                        nuevo.add(condicion);//condicion de la materia
                                                      //System.out.println("materia interna "+this.getLlenado().get(b+7)+" dia "+this.getLlenado().get(b+3));
                                                        modelo.addRow(nuevo.toArray());

                                                     // break;


                                                   conexion_base_de_datos con= new conexion_base_de_datos();
                                                    this.envio_informacion_db(con.getConexion(),
                                                                              hor.getParametros().getText(),//cedula
                                                                              hor.getEstudiante().getText(),//estudiante
                                                                              hor.getEspecialidad().getSelectedItem().toString(),//carrera
                                                                              Integer.parseInt(hor.getTraspaso_comunitario().get(i+1)),//semestre
                                                                              hor.getTraspaso_comunitario().get(i+0),//codigo
                                                                              hor.getTraspaso_comunitario().get(i+2),//materia
                                                                              Integer.parseInt(nuevo.get(3).toString()),//UC
                                                                              this.getLlenado().get(b+3).toString(),//dia
                                                                              hora.toString(),//hora
                                                                              this.getLlenado().get(b+2).toString(),//seccion
                                                                              hor.getPeriodo_actual().getText(),//periodo
                                                                              this.getLlenado().get(b+8).toString(),//docente
                                                                              condicion.toString()//condicion
                                                                            );

                                                         nuevo.clear();//limpiando el LinkedList para cada ciclo


                                               }//fin comparaciones


                                        }//fin ubicando



                                        this.setCredito(this.getCredito()+Integer.valueOf(hor.getTraspaso_comunitario().get(i+3)));
                                       // modelo.addRow(nuevo.toArray());

                                  }else{//fin del ciclo para evitar que se sobrepasen las unidades de credito a inscribir

                                     ima.mensaje_informacion("NO PUEDE INSCRIBIRLA PORQUE EXCEDE LAS UC MAXIMAS PERMITIDAS\n",  "NOTIFICACION", "alto", "png");


                                    }//fin estableciendo limite de uc


                               break;
                            }//fin comparacion materia seleccionada vs pensum

                }//fin del recorrido del pensum

nuevo.clear();
}




/**Este metodod se encarga de inscribir la materia cuando la seleccionada del horario es una electiva,
 hace todas las consideraciones respectiva para inscribirla a la base de datos y mostrarla como materia
 inscritas.*/
public void tipo_electiva(int limite_uc, String materia, String seccion, String semestre ,LinkedList <String> nuevo, String hora, int contador, String condicion, DefaultTableModel modelo, horario hor){
String codigo_electiva=null, uc_electiva=null, nombre_electiva=null, tipo=null;
boolean autorizacion_1=false, autorizacion_2=false, coincidencia=false; //variable para saber si se puede o no autorizar la inscripcion de la materia electiva

if(materia.endsWith("(ELECTIVA TECNICA)") || materia.contains("ELECTIVA DE IDIOMAS"))tipo="ELECTIVA TECNICA";
if(materia.endsWith("(ELECTIVA NO TECNICA)"))tipo="ELECTIVA NO TECNICA";



//-------------VERIFICANDO QUE NO SE EXCEDA DEL MAXIMO DE ELECTIVAS QUE SE PUEDAN INSCRIBIR----------
LinkedList<String> mes = this.maximo_electivas_semestre(hor); //fase1
LinkedList<String> cei = this.cantidad_electivas_inscritas(hor);//fase2

    for(int x=0; x<mes.size(); x=x+3){//recorriendo las maximas electivas permitidas por semestre
        for(int y=0; y<cei.size(); y=y+3){//recorriendo la cantidad de materias inscritas desde la interfaz grafica horario
            System.out.println("analizando electivas maximas: mes: s="+mes.get(x)+" nbre="+mes.get(x+1)+" cant="+mes.get(x+2)+" - cei: s="+cei.get(y)+" nbre="+cei.get(y+1)+" cant="+cei.get(y+2));

            if(mes.get(x).equalsIgnoreCase( cei.get(y) ) &&//si los semestres coinciden
               mes.get(x+1).equalsIgnoreCase( cei.get(y+1) ) &&//y el nombre es el mismo
               tipo.equalsIgnoreCase(mes.get(x+1)) && //y esta relacionada con la materia que deseo inscribir tanto en nombre(tipo) como
               semestre.equalsIgnoreCase(mes.get(x)) //semestre
               ){
                        System.out.println("HUBO COINCIDENCIA CON LA ELECTIVA DE INTERES");
                        coincidencia=true;
                            System.out.println("Es mayor mes que el cei =" +(Integer.valueOf( mes.get(x+2) ) > Integer.valueOf( cei.get(y+2) ) ) );
                           if( (Integer.valueOf( mes.get(x+2) ) > Integer.valueOf( cei.get(y+2) )) ){ //y las maximas permitidas no sean superadas por las inscritas                              
                                   autorizacion_1=true;//autorizando la materia porque no ha superado ni igualado al maximo permitido
                                   break;//rompiendo la primera iteracion
                            }else{autorizacion_1=false;}//no se autoriza la materia para inscribirla

                }//else{ coincidencia=false; }
            
        }//fin recorrido cei
        if(autorizacion_1)break;//rompiendo la segunda iteracion
    }//fin recorrido mes
 
                                                System.out.println("inscripcion_combinacion autorizacion_1= "+autorizacion_1+" coincidencia= "+coincidencia+" tecnica:"+(materia.endsWith("(ELECTIVA TECNICA)") || materia.contains("ELECTIVA DE IDIOMAS"))+" no tecnica:"+materia.endsWith("(ELECTIVA NO TECNICA)"));
                                        if(autorizacion_1==false && coincidencia==true && (materia.endsWith("(ELECTIVA TECNICA)") || materia.contains("ELECTIVA DE IDIOMAS"))) {ima.mensaje_informacion("DISCULPE, PERO SE HA ALCANZADO EL MAXIMO DE ELECTIVAS TECNICAS INSCRITAS PARA EL SEMESTRE "+semestre+"\nPOR LO QUE NO SE LE PERMITIRA INSCRIBIRLA", "ADVERTENCIA ELECTIVAS", "avisos", "png");}else{}
                                        if(autorizacion_1==false && coincidencia==true && materia.endsWith("(ELECTIVA NO TECNICA)")){ima.mensaje_informacion("DISCULPE, PERO SE HA ALCANZADO EL MAXIMO DE ELECTIVAS NO TECNICAS INSCRITAS PARA EL SEMESTRE "+semestre+"\nPOR LO QUE NO SE LE PERMITIRA INSCRIBIRLA", "ADVERTENCIA ELECTIVAS", "avisos", "png");}else{}

//-------------------------------------------
      
//---LOCALIZANDO ELECTIVA----------
                   
                    for(int ee=0; ee<this.getElectivas().size(); ee = ee+4){//recorrido para localizar informacion de la materia
                        if(materia.equalsIgnoreCase(this.getElectivas().get(ee+1)) ){//comparando la seleccionada con el nombre de las materias electivas reales.
                            codigo_electiva = this.getElectivas().get(ee);
                            uc_electiva =  this.getElectivas().get(ee+2);
                            nombre_electiva = this.getElectivas().get(ee+1);
                            break;
                        }
                    }
                        //revisando si ya la vio antes.
        autorizacion_2 = this.electiva_vista_record(codigo_electiva, hor.getTraspaso_ele_cur());//fase3

                                        if(autorizacion_2==false) ima.mensaje_informacion("DISCULPE, PERO ESTA MATERIA YA LA HA CURSADO ANTES Y LA TIENE APROBADA\nPOR LO QUE NO SE LE PERMITIRA INSCRIBIRLA", "ADVERTENCIA ELECTIVAS", "avisos", "png");





        
System.out.println("inscripcion_ auto1: "+autorizacion_1+" coinci: "+coincidencia+" auto2: "+autorizacion_2+" cond: "+condicion);
//_-------------------------------------------------------------------------------------------------------------------------------------
if( (autorizacion_1 || coincidencia==false) && autorizacion_2 ){//autorizando o no la materia a inscribir, siempre y cuando halla aprobado ambas pruebas

       if(this.getCredito()+Integer.valueOf(uc_electiva)<=limite_uc){//estableciendo limite de unidades de credito a inscribir

           condicion = this.condicion_real_electivas(codigo_electiva,nombre_electiva, hor);//buscando la condicion real en la que se va a inscribir la materia

           for(int d=0; d<this.getLlenado().size(); d = d + 9){//recorriendo la materia de llenado que contiene las materias ofertadas


               if( materia.equalsIgnoreCase(this.getLlenado().get(d+7).toString())  && //verificando que sean las mismas materias
                   (this.turnos_materias(this.conversion_hora(d, this.getLlenado()),hor.getTurno().getText()) || this.getLlenado().get(d+3).toString().equalsIgnoreCase("SABADO") || this.getLlenado().get(d+3).toString().equalsIgnoreCase("DOMINGO")) && //verificando que la materia pertenzca al mismo turno del alumno ya que esta se obtiene directamente del LinkedList y no de la tabla
                   seccion.equalsIgnoreCase(this.getLlenado().get(d+2).toString()) //que sea la misma seccion
                                               ){
                   

                                                        /*System.out.println(this.getPensum().get(i+0)+"  "+this.getLlenado().get(b+6).toString());
                                                        System.out.println(tabla2.getColumnName(tabla2.getSelectedColumn())+"  "+this.getLlenado().get(b+3).toString());
                                                        System.out.println(tabla2.getValueAt(tabla2.getSelectedRow(),0)+" "+this.getLlenado().get(b+4).toString());
                                                        System.out.println(seccion+" "+this.getLlenado().get(b+2).toString());*/

                                                        contador=contador+1;
                                                        //this.tipo_de_envio(b, i, materia, nuevo, hora, contador, condicion, modelo, hor);



                                                        hora=this.getLlenado().get(d+4).toString().substring(0,5)+" a "+this.getLlenado().get(d+5).toString().substring(6);

                                                        nuevo.add(semestre);//0 semestre
                                                        nuevo.add(codigo_electiva);//1 codigo
                                                        nuevo.add(nombre_electiva);//2 materia
                                                        if(contador>1){nuevo.add("0");}else{nuevo.add(uc_electiva);} //3 uc
                                                        //nuevo.add(this.getPensum().get(i+3));//unidades de credito
                                                        nuevo.add(this.getLlenado().get(d+2).toString());//4 seccion
                                                        nuevo.add(this.getLlenado().get(d+3)+" - "+hora);//5 horario
                                                        nuevo.add(this.getLlenado().get(d+8).toString());//6 docente
                                                        nuevo.add(condicion);//8 condicion de la materia
                                                      //System.out.println("materia interna "+this.getLlenado().get(b+7)+" dia "+this.getLlenado().get(b+3));
                                                        modelo.addRow(nuevo.toArray());

                                                     // break;




                                                   conexion_base_de_datos con= new conexion_base_de_datos();
                                                    this.envio_informacion_db(con.getConexion(),
                                                                              hor.getParametros().getText(),//cedula
                                                                              hor.getEstudiante().getText(),//estudiante
                                                                              hor.getEspecialidad().getSelectedItem().toString(),//carrera
                                                                              Integer.parseInt(semestre),//semestre
                                                                              codigo_electiva,//codigo
                                                                              nombre_electiva,//materia
                                                                              Integer.parseInt(nuevo.get(3)),//UC
                                                                              this.getLlenado().get(d+3).toString(),//dia
                                                                              hora.toString(),//hora
                                                                              this.getLlenado().get(d+2).toString(),//seccion
                                                                              hor.getPeriodo_actual().getText(),//periodo
                                                                              this.getLlenado().get(d+8).toString(),//docente
                                                                              condicion.toString()//condicion
                                                                            );

                                                        nuevo.clear();//limpiando el LinkedList para cada ciclo


                                               }//fin comparaciones

                                        }//fin recorrido llenado

           this.setCredito(this.getCredito()+Integer.valueOf(uc_electiva));

        }else{//fin del ciclo para evitar que se sobrepasen las unidades de credito a inscribir
           ima.mensaje_informacion("NO PUEDE INSCRIBIRLA PORQUE EXCEDE LAS UC MAXIMAS PERMITIDAS\n",  "NOTIFICACION", "alto", "png");
            
            }//fin estableciendo limite de uc//fin analisis limite credito
    
    }//fin analisis si esta autorizada la inscripcion de la materia



}
/**Este metodo determina la cantidad de electivas que el estudiante puede ver por
 * semestre y asi evitar que inscriba mas de lo que se le es permitido segun el
 * pensum. */
public LinkedList<String> maximo_electivas_semestre(horario hor){

    /*this.getLista_autorizada().add(this.getPreladas().get(s));//codigo
               this.getLista_autorizada().add(this.getPreladas().get(s+5));//nombre
               this.getLista_autorizada().add(this.getCondicion_materia());//condicion
               this.getLista_autorizada().add(this.getPreladas().get(s+1));//semestre
    */
    boolean coincidencia = false;
    LinkedList<String> cantidad = new LinkedList<>();

    for(int a=0; a<hor.getLista_autorizada_traspaso().size(); a=a+4 ){//recorriendo lista de materias autorizadas para contar cuantas electivas por semestre se pueden inscribir

            if(hor.getLista_autorizada_traspaso().get(a+1).contains("ELECTIVA")){//verificando que sea una electiva

                if(cantidad.isEmpty()){//verificando si el LinkedList esta vacio...si si se guarda el primer registro sino se revisa y donde halla coincidencia se suma uno mas
                    cantidad.add(hor.getLista_autorizada_traspaso().get(a+3));//guardando el semestre
                    cantidad.add(hor.getLista_autorizada_traspaso().get(a+1));//guardando el tipo de electiva o nombre segun el pensum
                    cantidad.add("1");//guardando cuanto de ese tipo hay
                }else{
                    for(int c=0; c<cantidad.size(); c=c+3){//recorriendo LinkedList cantidad

                        if(hor.getLista_autorizada_traspaso().get(a+1).equalsIgnoreCase(cantidad.get(c+1)) && //si los nombres coinciden
                           hor.getLista_autorizada_traspaso().get(a+3).equalsIgnoreCase(cantidad.get(c))     //son del mismo semestre
                                ){
                                    coincidencia=true;
                                    cantidad.set(c+2, String.valueOf( ( Integer.valueOf(cantidad.get(c+2))+1) ) );//sumando 1
                                    break;//rompiendo el ciclo
                                 }else{coincidencia=false;}

                    }//fin recorrido LinkedList cantidad

                            //si no se encontro nada despues de recorrer todo el LinkedList se agrega esa nueva condicion
                            if(coincidencia==false){
                                cantidad.add(hor.getLista_autorizada_traspaso().get(a+3));//guardando el semestre
                                cantidad.add(hor.getLista_autorizada_traspaso().get(a+1));//guardando el tipo de electiva o nombre segun el pensum
                                cantidad.add("1");//guardando cuanto de ese tipo hay
                            }

                }

            }//fin verificacion si es electiva

    }


    System.out.println("RESUMEN MATERIAS ELECTIVAS X SEMESTRE");
    for(int i=0; i<cantidad.size(); i=i+3){
    System.out.println("cantidad por semestre: semestre: "+cantidad.get(i)+" tipo: "+cantidad.get(i+1)+" cant: "+cantidad.get(i+2));
    }

return cantidad;

}


/**Este metodo determina la cantidad de electivas por semestre tecnicas y no tecnicas que hay inscritas
 desde la pantalla de inscripcion en horarios<br>
 posicion 0 = semestre<br>
 posicion 1 = tipo de electiva<br>
 posicion 2 = cantidad que hay en ese semestre
 */
public LinkedList<String> cantidad_electivas_inscritas(horario hor){
    DefaultTableModel contenido = (DefaultTableModel) hor.getSecciones().getModel();
    int filas = hor.getSecciones().getModel().getRowCount(); //filas de la tabla

    LinkedList<String> inscritas = new LinkedList<>();
    String nombre="ninguna"; //guarda materia temporalmente para evitar contar la misma varias veces
    boolean coincidencia=false;

    boolean entec=false;
    boolean etec=false;
    boolean semes=false;

    if(filas>0){//solo si al menos hay una fila que significa que al menos hay una materia inscrita electiva o no

        for(int i=0; i<contenido.getRowCount(); i++){//recorriendo tabla de materias inscritas
    //    System.out.println("contenido inscritas filas :"+contenido.getValueAt(i, 2)+" filas totales: "+contenido.getRowCount());


          //  if(contenido.getValueAt(i,2).toString().contains("ELECTIVA") &&
          //     !nombre.equals(contenido.getValueAt(i,2).toString())){//verificando que sea una electiva la inscrita y no repetida por el numero de veces que se ve una materia en la semana

//-----------------------------------------------
               if(contenido.getValueAt(i,2).toString().contains("ELECTIVA TECNICA") && !nombre.equals(contenido.getValueAt(i,2).toString()) ){ //analizando solo las electivas tecnicas
              //     System.out.println("Electiva tecnica detectada");
                   if(inscritas.isEmpty()){//System.out.println("ET. vacio. añadiendo electiva tecnica");
                    inscritas.add(contenido.getValueAt(i, 0).toString());//guardando el semestre de la materia inscrita
                    inscritas.add("ELECTIVA TECNICA"); //guardando el nombre completo de la electiva. ejm metodologia (ELECTIVA NO TECNICA). entonces es mejor comparar por secuencia de caracteres
                    inscritas.add("1");//guardando la cantidad
                   }else{
                        for(int et=0; et<inscritas.size(); et=et+3){//recorriendo electivas inscritas
                     //       System.out.println("COMPARANDO: guardadas: "+inscritas.get(et)+" "+inscritas.get(et+1)+" "+inscritas.get(et+2));
                     //       System.out.println("COMPARANDO: inscritas: "+contenido.getValueAt(i, 0)+" "+contenido.getValueAt(i, 2));
                            
                            if((etec=inscritas.get(et+1).contains("ELECTIVA TECNICA")) &&//coinciden nombre o tipo y
                               (semes=contenido.getValueAt(i, 0).toString().equalsIgnoreCase(inscritas.get(et)))//coincide el semestre
                               ){//entonces se suma 1
                               coincidencia=true; //System.out.println("ET. coincidencia verdadera. sumando");
                               inscritas.set(et+2, String.valueOf( ( Integer.valueOf(inscritas.get(et+2))+1) ) );//sumando 1
                               break;
                            }else{coincidencia=false;}
                            System.out.println("etec: "+etec+"  semes: "+semes);
                        }//fin recorrido inscritas

                        //si despues de recorrer todo el LinkedList no se hallo electiva tecnica correspondiente al semestre, se añade
                                    if(coincidencia==false){//System.out.println("ET.coincidencia falsa. añadiendo nuevo caso");
                                            inscritas.add(contenido.getValueAt(i, 0).toString());//guardando el semestre de la materia inscrita
                                            inscritas.add("ELECTIVA TECNICA"); //guardando el nombre completo de la electiva. ejm metodologia (ELECTIVA NO TECNICA). entonces es mejor comparar por secuencia de caracteres
                                            inscritas.add("1");//guardando la cantidad
                                          
                                    }



                   }
                //   System.out.println("FIN ELECTIVA TECNICA-------------");

                }//FIN ANALISIS ELECTIVAS TECNICAS
//------------------------------------------------------------------------

               if(contenido.getValueAt(i,2).toString().contains("ELECTIVA NO TECNICA") && !nombre.equals(contenido.getValueAt(i,2).toString()) ){
            //   else { //analizando solo las electivas NO tecnicas
                  // System.out.println("Electiva NO tecnica detectada............");
                   if(inscritas.isEmpty()){//System.out.println("ENT. vacio. añadiendo electiva NO tecnica");
                    inscritas.add(contenido.getValueAt(i, 0).toString());//guardando el semestre de la materia inscrita
                    inscritas.add("ELECTIVA NO TECNICA"); //guardando el nombre completo de la electiva. ejm metodologia (ELECTIVA NO TECNICA). entonces es mejor comparar por secuencia de caracteres
                    inscritas.add("1");//guardando la cantidad
                   }else{                   
                        for(int ent=0; ent<inscritas.size(); ent=ent+3){//recorriendo electivas inscritas
                            if(inscritas.get(ent+1).contains("ELECTIVA NO TECNICA") &&//coinciden nombre o tipo y
                               contenido.getValueAt(i,0).toString().equalsIgnoreCase(inscritas.get(ent))//coincide el semestre
                               ){//entonces se suma 1
                               coincidencia=true;//System.out.println("ENT. coincidencia verdadera. sumando");
                               inscritas.set(ent+2, String.valueOf( ( Integer.valueOf(inscritas.get(ent+2))+1) ) );//sumando 1
                               break;
                            }else{coincidencia=false;} 
                        
                      
                        }//fin recorrido inscritas

                        //si despues de recorrer todo el LinkedList no se hallo electiva tecnica correspondiente al semestre, se añade
                            if(coincidencia==false){//System.out.println("ENT.coincidencia falsa. añadiendo nuevo caso");
                                                inscritas.add(contenido.getValueAt(i, 0).toString());//guardando el semestre de la materia inscrita
                                                inscritas.add("ELECTIVA NO TECNICA"); //guardando el nombre completo de la electiva. ejm metodologia (ELECTIVA NO TECNICA). entonces es mejor comparar por secuencia de caracteres
                                                inscritas.add("1");//guardando la cantidad
                                               
                                        }


                   }
                 //  System.out.println("FIN ELECTIVA NO TECNICA.................");
                }//FIN ANALISIS ELECTIVAS NO TECNICAS



           // }//fin verificacion si es electiva

            nombre=contenido.getValueAt(i, 2).toString();

        }//fin recorrido tabla materias inscritas
    
    
    }


  //  System.out.println("RESUMEN MATERIAS ELECTIVAS INSCRITAS");
//    for(int i=0; i<inscritas.size(); i=i+3){
 //   System.out.println("electivas inscritas: semestre: "+inscritas.get(i)+" tipo: "+inscritas.get(i+1)+" cant: "+inscritas.get(i+2));
 //   }

  return inscritas;

}
/**Este metodo se encarga de determinar si la materia que se desea inscribir ya fue vista
  previamente y aprobada en el record del estudiante*/
public boolean electiva_vista_record(String codigo_inscribir, LinkedList<String> ele_aprobadas){
    boolean permitir=true;
  //      System.out.println("MATEIRAS ELECTIVAS CURSADAS Y APROBADAS. capacidad: "+ele_aprobadas.size());
    for(int i=0; i<ele_aprobadas.size(); i=i+2){//recorriendo las electivas cursadas y aprobadas

            if(codigo_inscribir.equalsIgnoreCase(ele_aprobadas.get(i) ) ){

                permitir=false;
    //            System.out.println("Materia electiva no se puede inscribir porque ya fue vista y aprobada anteriormente.");
                break;
            }

    }//fin recorrido electivas cursadas y aprobadas
return permitir;
}





/**este metodo es para confirmar si la materia que se eligio
 * realmente se desea inscribir, por lo tanto se retorna un
 * entero donde si=0 y no=1*/
public int confirmacion_materia(JTable tabla){
 //System.out.println("[Metodo confirmacion_materia]");
    String salida=null;
 int respuesta=0;


 if(tabla.getSelectedColumn()!=0 && tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()).toString()!=null){//deshabilitando la columna de horario como campo de seleccion y que el que se seleccione no este vacio
            for(int a=0; a<this.getLlenado().size();a=a+9){//buscando las caracteristica de la materia para mostrarla

                    if(tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()).toString().equalsIgnoreCase(this.getLlenado().get(a+7).toString())//comparando nombre de materia
                       && tabla.getValueAt(tabla.getSelectedRow(), 0).toString().equalsIgnoreCase(this.getLlenado().get(a+4).toString())//comparando hora de inicio
                       && tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase(this.getLlenado().get(a+3).toString())//comparando el dia
                       ){

                        salida=this.getLlenado().get(a+5).toString().substring(6);//hora de salidad de la materia

                    }


                }//fin del recorrido


                respuesta= ima.confirmacion("REALMENTE DESEA INSCRIBIR ESTA MATERIA ?"+"\n\n"+
                                 tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn())+"\n"+
                                 "DE: "+tabla.getValueAt(tabla.getSelectedRow(), 0).toString().substring(0,5)+"  a  "+salida+"\n\n",
                                 "CONFIRMACION",
                                 "sistema",
                                 "png");

               /* respuesta = JOptionPane.showConfirmDialog(
                                new JFrame(),
                                "REALMENTE DESEA INSCRIBIR ESTA MATERIA ?"+"\n\n"+
                                 tabla.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn())+"\n"+
                                 "DE: "+tabla.getValueAt(tabla.getSelectedRow(), 0).toString().substring(0,5)+"  a  "+salida+"\n\n",
                                "CONFIRMACION",
                                JOptionPane.YES_NO_OPTION);*/

 }//fin deshabilitacion

return respuesta;
 
}

/** Se realiza la eliminacion de la celda seleccionada o en caso de NO haber una seleccionada,
 entonces se elimina la ultima celda de la tabla por defecto. Para ello se pasa como argumento
 * el nombre del JTable y el modelo que contiene la informacion de la tabla DefaultTableModel.
 JTable es  el contenerdor del DefaultTableModel que es el que contiene la informacion */

public void eliminancion_materia(JTable tabla, DefaultTableModel modelo, Connection conexion, String cedula, String periodo_actual){
    //System.out.println("[Metodo eliminacion_materia]");
    String m_seleccionada=null;
    String m_inscrita=null;
    int contador=0, credito=0;
    registro_ingenieria reging = new registro_ingenieria();
//System.out.println(tabla.getRowCount());

    if(tabla.getRowCount()>0){//solo se realizan eliminaciones mientras halla contenido dentro de la tabla
          /*  if(tabla.getSelectedRow()!=-1){

                //System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 2));

                this.eliminacion_informacion_db(conexion, cedula, tabla.getValueAt(tabla.getSelectedRow(), 1).toString());//eliminando la materia de la base de datos
                this.setCredito(this.getCredito()-Integer.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 3).toString()));//restando las uc que pertenece a la materia del total
                modelo.removeRow(tabla.getSelectedRow());//elimina la celda seleccionada



             }else{

                this.eliminacion_informacion_db(conexion, cedula, tabla.getValueAt(modelo.getRowCount()-1, 1).toString());//eliminando la ultima materia inscrita segun la tabla
                System.out.println(tabla.getValueAt(tabla.getRowCount()-1, 2));
                this.setCredito(this.getCredito()-Integer.valueOf(tabla.getValueAt(tabla.getRowCount()-1, 3).toString()));//restando las uc que pertenece a la materia del total
                modelo.removeRow(modelo.getRowCount()-1);//eliminacion de la utlima celda por defecto
                
                  }
*/
 /*      System.out.println("cuenta del modelo "+modelo.getRowCount());

       m_seleccionada = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();//guardando la materia seleccionada para borrar

        for(int r=0; r<modelo.getRowCount(); r++){//recorriendo la tabla de materias inscritas GUI
            m_inscrita = tabla.getValueAt(r, 1).toString();//variable materia que el alumno tiene inscrita y se muestra en la tabla
            

             if(m_seleccionada.equalsIgnoreCase(m_inscrita)){//coincidencia de la materia que se quiere borrar con todas las que existen con el mismo codigo
               // modelo.removeRow(r+1);
                System.out.println("guardada "+m_seleccionada+" r"+r);
             }

        }//fin del recorrido de la tabla de materias inscritas

*/
        

      System.out.println("seleccionado......."+tabla.getSelectedRow());

       if(tabla.getSelectedRow()==-1){ima.mensaje_informacion("ESTUDIANTE DEBE AL MENOS SELECCIONAR UNA MATERIA", "SUGERENCIA", "avisos", "png");}else{ //verificando que al menos se halla seleccionado una materia para eliminar

            m_seleccionada = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();//guardando la materia seleccionada para borrar

           while(true){//un cico infinito de revision de la tabla de materias inscritas para borrar todas las materias con el mismo nombre

                if(contador == modelo.getRowCount())break;//si despues de revisar toda la tabla no encuentra mas materias rompe el ciclo
                m_inscrita = tabla.getValueAt(contador, 1).toString();//variable materia que el alumno tiene inscrita y se muestra en la tabla
                //System.out.println("contador "+contador+" filas"+modelo.getRowCount());
                credito=credito+Integer.parseInt(tabla.getValueAt(contador, 3).toString());//sumando las UC de las materias inscritas restantes

                contador=contador+1;//contador de ciclos

                //System.out.println("seleccionada "+m_seleccionada+" inscrita"+m_inscrita);

                if(m_seleccionada.equalsIgnoreCase(m_inscrita)){//coincidencia de la materia que se quiere borrar con todas las que existen con el mismo codigo
                    modelo.removeRow(contador-1);//borra la fila de la materia deseada
                    //System.out.println("guardada "+m_seleccionada);
                    contador=0;//cada vez que encuentre coincidencia se pone el contador en cero para que haga la revision desde el inicio
                    credito=0;//reseteando la suma de las UC
                 }



            }//fin del while


            this.eliminacion_informacion_db(conexion, cedula, m_seleccionada, periodo_actual);//eliminando la materia de la base de datos
            this.setCredito(credito);//solo se colocan las UC de las materias que quedaron inscritas

            this.borrado_asociacion_eaup(m_seleccionada);

       }//verificando que se halla seleccionado al menos una materia para eliminar

    }else{//en caso de que la tabla de materias inscritas este vacia se le da un aviso al usuario
        ima.mensaje_informacion("ESTUDIANTE NO EXISTEN MAS MATERIAS PARA ELIMINAR", "ELIMINACION DE MATERIAS", "eli_mat", "png");
        
    }



}

/**Materia que permite borrar la asociacion de la materia a elemininar */
public void borrado_asociacion_eaup(String codigo_real){
System.out.println("Borrando asociacion "+codigo_real);
int posicion=0;

    for(int i=0; i<this.getEaup().size(); i=i+2){//recorriendo las asosciaciones
    System.out.println("coincidencia asociaciones borrado: "+(codigo_real.equalsIgnoreCase(this.getEaup().get(i+1))));
        if(codigo_real.equalsIgnoreCase(this.getEaup().get(i+1))){//si hay coincidencia
            this.getEaup().remove(i);//se borra la asociacion con codigo de autorizadas
            this.getEaup().remove(i);//se borra el codigo real
            break;
        }

    }//fin recorrido asociaciones


System.out.println("ASOCIACIONES RESTANTES");
for(int i=0; i<this.getEaup().size(); i=i+2){//recorriendo las asosciaciones
    System.out.println("ASOCIACIONES -> "+this.getEaup().get(i)+" - "+this.getEaup().get(i+1));
}


}





/** Metodo que analiza si existe choques de horarios entre la materia a inscribir y
 la que tiene ya inscritas. Este metodo solo se aplica siempre y cuando ya hallan materias inscritas
 ya que no tiene sentido si no existe alguna, sino generara un error*/
public boolean choques_horarios(JTable secciones, JTable horario, int semestre){
    System.out.println("[Metodo choques_horarios]");
    registro_ingenieria reging = new registro_ingenieria();
     boolean chocan=false;
     final int h_militar=1200;
     int hi,hf,he,hs;
     String mer_1,mer_2,formato,reporte = null;


    for(int recorrido=0; recorrido<this.getLlenado().size(); recorrido=recorrido+9){//verificando cuantas materias con el mismo nombre existe en el horario y cual es o son
    //System.out.println(this.getLlenado().get(recorrido)+"    "+recorrido);
        
        if(horario.getValueAt(horario.getSelectedRow(), horario.getSelectedColumn()).toString().trim().equalsIgnoreCase(this.getLlenado().get(recorrido+7).toString().trim()) //viendo si la materia elegida es la misma que viene de las ofertadas para ese horario
           && semestre == Integer.valueOf(this.getLlenado().get(recorrido+1).toString())     //y revisando que sean los mismos semestres entre la seleccionada vs ofertadas
                ){
                System.out.println("hubo coincidencia semestre: "+semestre+ " materia: "+this.getLlenado().get(recorrido+7).toString());
           // System.out.println(this.getLlenado().get(recorrido+3));//dia
           // System.out.println(this.getLlenado().get(recorrido+7)+"  posicion: "+recorrido );//materia

          //  ---------------------------------

             //hora de inicio
                  hi=Integer.parseInt(this.getLlenado().get(recorrido+4).toString().substring(0, 5).replace(":", ""));
                  mer_1=this.getLlenado().get(recorrido+4).toString().substring(this.getLlenado().get(recorrido+4).toString().length()-2);
                  if(mer_1.equalsIgnoreCase("pm") && hi>=100 && hi<1200)hi=hi+h_militar;


    //                     System.out.println("HORA: "+hi);
      //                   System.out.println("MERIDIANO: "+mer_1);


            //hora de salida

                  hf=Integer.parseInt(this.getLlenado().get(recorrido+5).toString().substring(6, 11).replace(":", ""));//obteniendo la hora en formato militar 2:35 pm = 1435 horas
                  mer_2=this.getLlenado().get(recorrido+5).toString().substring(this.getLlenado().get(recorrido+5).toString().length()-2);//obteniendo el am o pm
                  if(mer_2.equalsIgnoreCase("pm") && hf>=100 && hf<1200)hf=hf+h_militar; //en caso que sea despues del medio dia "pm", se le suma 1200 horas a la actual

        //                 System.out.println("HORA: "+hf);
          //               System.out.println("MERIDIANO: "+mer_2);

          //  ----------------------------------


          //RECORRIENDO LAS MATERIAS INSCRITAS

//    System.out.println("total filas........"+secciones.getRowCount());
         for(int k=1; k<=secciones.getRowCount();k++){//recorriendo las materias inscritas para luego comparar con la seleccionada
            //System.out.println("inscritas:->"+secciones.getValueAt(k-1,2));

               if(secciones.getValueAt(k-1, 5).toString().startsWith(this.getLlenado().get(recorrido+3).toString())){//si empiezan con el mismo dia

                  //acondicionando la hora de las materias inscritas
                   formato = secciones.getValueAt(k-1, 5).toString().substring(this.getLlenado().get(recorrido+3).toString().length()+3).replace(":", "").trim();
                    //System.out.println(formato+"     "+formato.substring(0,4)+"->"+formato.substring(7, 11));
                    //System.out.println("mer:"+formato.substring(12));

                    he = Integer.parseInt(formato.substring(0,4)); 
                    hs = Integer.parseInt(formato.substring(7, 11));
                    if(formato.substring(12).equalsIgnoreCase("pm") && he<=1200) he=he+1200;//en caso de que la hora sea pm sumarle 12 horas y no sea el medio dia para no sumarle las 12horas
                    if(formato.substring(12).equalsIgnoreCase("pm") && hs<=1200) hs=hs+1200;
                    
                        //System.out.println(he);
                        //System.out.println(hs);


            /*   if(hi>=1730){//ignorando analisis si la hora es las 7pm....problemas. 1900 (esto esta de manera provisional)
                 System.out.println("Ignorando choques de materia del turno nocturno");
                 chocan=false;
                 reporte=secciones.getValueAt(k-1,2).toString();
                 break;

               }else{*/
                        //analisis 0. Verfica que las hora de inicio y de salida no sean iguales ya que eso significa que estan solapadas las materias
                        if(he==hi && hf==hs){
                           chocan = true; //chocan cuando las materias estan solapadas en hora de inicio y hora de salida.
                           System.out.println("analisis 0: SOLAPAMIENTO....Entrada: "+he+"="+hi+" y Salida: "+he+">="+hf);
                           System.out.println("DIA:"+this.getLlenado().get(recorrido+3)+"  choques de HORARIOS con "+secciones.getValueAt(k-1,2));

                            reporte=secciones.getValueAt(k-1,2).toString();
                            break;
                        }//fin analisis 0

                        //Para los analisis 1 y 2 voy a quitar los elementos de igualdad en la comparacion para ignorar que una materia termine cuando la siguiente empieza. ejemplo salgo a las 5:00 y la otra empieza a la misma hora sin receso
                        //analisis 1
                        if((he<hi && hi<hs) || (he<hf && hf<hs)){//verifica la materia seleccionada con la inscrita en cuanto a rango de horas entrada/salida
                            chocan=true;
                            System.out.println("analisis 1 ENTRADA: "+he+">="+hi+"<="+hs+" o SALIDA: "+he+">="+hf+"<="+hs);
                            System.out.println("DIA:"+this.getLlenado().get(recorrido+3)+"  choques de HORARIOS con "+secciones.getValueAt(k-1,2));

                            reporte=secciones.getValueAt(k-1,2).toString();
                            break;
                        }else{ //si la hora de entrada o salida esta entre las horas de entrada salida de las ya inscritas existe un choque de horario

                        //analisis 2
                        if((hi<he && he<hf) || (hi<hs && hs<hf)){//verifica que la materia inscrita no este dentro de la hora de la seleccionada, o sea absorvida
                            chocan=true;
                            System.out.println("analisis 2 ENTRADA: "+hi+">="+he+"<="+hf+" o SALIDA: "+hi+">="+hs+"<="+hf);
                            System.out.println("DIA:"+this.getLlenado().get(recorrido+3)+"  choques de HORARIOS con "+secciones.getValueAt(k-1,2));

                            reporte=secciones.getValueAt(k-1,2).toString();
                            break;
                        } //fin analisis 2
                         }//fin de analisis 1

           //    }//provisional......choque de hora 7pm que no tiene receso



               }//fin empieza en el mismo dia

                    
         }//fin recorrido de las materias inscritas
    
        }//fin coincidencias de materia seleccionada y la del horario
    }//fin recorrido

if(chocan){//se activa el mensaje si existe choque de horario
    ima.mensaje_informacion("Disculpe, pero la materia seleccionada coincide con\n"+
                            "\""+reporte+"\""+
                            "\npor lo que no la puede inscribir", "CHOQUE DE HORARIOS", "avisos", "png");


}


     
return chocan;

}




/** Este metodo evita que no se mezclen los turnos cuando se seleccione una materia para inscribir, que la materia que se desea inscribir no se
 encuente repetida en la lista de las que inscribio, y evita que exista un choque de horario entre materias*/
public String evitar_repetir(JTable tabla, String materia, JTable tabla2, String turno, int semestre){
    System.out.println("[Metodo evitar_repetir]");
    String comparacion="falso";
    boolean analisis=false;
    boolean verificacion=false;
   registro_ingenieria reging = new registro_ingenieria();
        //System.out.println(tabla.getRowCount());
//table es secciones y tabla2 es horario
//----
/*
                if((turno.equalsIgnoreCase("DIURNO") && tabla2.getSelectedRow()>=12) ){//verificando que no halla mezcla de turnos
                      comparacion="verdadero";
                      analisis=false;
                      ima.mensaje_informacion("DISCULPE, PERO USTEDE PERTENECE AL TURNO DIURNO\n"+
                            "Y NO PUEDE INSCRIBIR UNA MATERIA DEL NOCTURNO\n"+
                            "  \""+tabla2.getValueAt(tabla2.getSelectedRow(), tabla2.getSelectedColumn())+"\"",
                            "CHOQUE DE TURNOS", "avisos", "png");



                  }else{comparacion="falso";analisis=true;//fin de verificacion del turno diurno


                  if(turno.equalsIgnoreCase("NOCTURNO") && tabla2.getSelectedRow()<=11){//verificando el turno nocturno
                      comparacion="falso";
                      analisis=true;

                      //comparacion="verdadero";
                      //analisis=false;
                       //reging.mensajes("DISCULPE, PERO USTEDE PERTENECE AL TURNO NOCTURNO\n"+
                        //    "Y NO PUEDE INSCRIBIR UNA MATERIA DEL DIURNO\n"+
                         //    "  \""+tabla2.getValueAt(tabla2.getSelectedRow(), tabla2.getSelectedColumn())+"\"",
                          //  "CHOQUE DE TURNOS");
                     

                  }else{comparacion="falso";analisis=true;}//fin de verificacion del turno nocturno

                  }*/
   //----
        comparacion = "falso"; analisis=true;//EXPERIMENTAL. SE PUEDE INSCRIBIR CUALQUIER MATERIA QUE APAREZCA EN LA SECCION YA QUE LA MISMA TINENE EN SU NOMBRE EN QUE TURNO APARECE
System.out.println("comparacion "+comparacion+" analisis "+analisis+" fila:"+tabla2.getSelectedRow() );



        if(tabla.getRowCount()>0 && analisis==true){//siempre que halla una materia inscrita verifica comparando

           //PRIMERA VERIFICACION.
            for(int fila=1; fila<=tabla.getRowCount();fila++){//verificando que la materia a inscribir no se repita en la de inscripcion
               // System.out.println("seleccionada "+materia);
               // System.out.println("materia "+tabla.getValueAt(fila-1, 2)+" fila"+fila);


                if(materia.equalsIgnoreCase(tabla.getValueAt(fila-1, 2).toString())){//se repiten
                    comparacion="verdadero";

                    System.out.println("materia "+tabla.getValueAt(fila-1, 2)+" fila "+fila);
                    tabla.setRowSelectionInterval(fila-1, fila-1);
                    tabla.setSelectionBackground(new Color(255,224,62));

                    verificacion=false;//como se cumplio la primera verificacion no se hace la segunda. no es necesaria

                    ima.mensaje_informacion("\""+materia+"\"  YA LA TIENE INSCRITA.\n"+
                                            "VERIFIQUE LA FILA  "+(fila),
                                            "ADVERTENCIA", "avisos", "png");

                    


                    break;

                }else{//no se repiten
                    comparacion="falso";
                    verificacion=true;//es para poder habilitar la segunda verificacion ya que la primera no se cumplio
                }//fin verificando que se repitan

                  }//fin del recorrido 1


            //SEGUNDA VERFICACION

            if(verificacion){//ejecutando la segunda verificacion
                
                    for(int fila=1; fila<=tabla.getRowCount();fila++){//verificando que no exista choque de horarios
                       if(this.choques_horarios(tabla, tabla2, semestre)){comparacion="verdadero";break;}
                          else{comparacion="falso";}//analiza que no exista choque de horarios

                    }//fin del recorrido 2
            }//fin segunda verificacion

            
  /*  //-----------------------------------------------------------------------------

                  if(tabla.getValueAt(fila-1, 5).toString().startsWith(tabla2.getColumnName(tabla2.getSelectedColumn())) &&//comparando dias
                     tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString().substring(0,5).equalsIgnoreCase(//comparando horas
                                tabla.getValueAt(fila-1, 5).toString().substring((tabla2.getColumnName(tabla2.getSelectedColumn()).length()+3),(tabla2.getColumnName(tabla2.getSelectedColumn()).length()+3)+5))   //comparando horas
                        ){
                             comparacion="verdadero";
                             System.out.println("son iguales");
                             tabla.setRowSelectionInterval(fila-1, fila-1);
                             tabla.setSelectionBackground(new Color(249,97,97));


                            reging.mensajes("\""+materia+"\"  COINCIDE EN HORAS CON ESTA MATERIA:  \""+tabla.getValueAt(fila-1, 2).toString()+"\"\n"+
                            "POR LO QUE NO PUEDE INSCRIBIRLA\n"+
                            "VERIFIQUE LA FILA  "+(fila),
                            "CHOQUE DE HORARIO");
                            break;
                            
                            
                    //hora=this.getLlenado().get(b+4).toString().substring(0,5)+" a "+this.getLlenado().get(b+5).toString().substring(6);
                  }else{comparacion="falso";}//fin verificando que no hallan choques de horarios

  //-------------------------------------------------------------------------------
*/
      

          

               

        }else{}//fin verificacion de que al menos halla una materia inscrita

   
    
    
return comparacion;

}


public String u_p_inscrito(String codigo){
    //System.out.println("[Metodo u_p_inscrito]");
    String periodo=null;

    //for(int d=0; d<this.getOrden().size(); d=d+6){//recorriendo las materias ordenadas
    for(int d=0; d<this.getOrden().size(); d=d+11){//recorriendo las materias ordenadas
        if(codigo.equalsIgnoreCase(this.getOrden().get(d))){//si el codigo de la materia coincide con las ordenada entonces se guarda el periodo academico
            periodo=this.getOrden().get(d+5);
            //System.out.println(codigo+" "+this.getOrden().get(d+5));
        }
    }

return periodo;
}//fin metodo u_p_inscrito


public void materias_preladas_y_no_preladas(){//materias que tengan prelaciones
   System.out.println("[Metodo materias_preladas_y_no_preladas]");
    int cuenta=0, cuenta2=0;


        //for(int d=0; d<this.getPensum().size(); d=d+7){//recorriendo el pensum de la carrera
         System.out.println("longitud PENSUM: "+this.getPensum().size()/10);
        for(int d=0; d<this.getPensum().size(); d=d+10){//recorriendo el pensum de la carrera
         //  System.out.println("TAMAÑO PENSUM: "+this.getPensum().size()/7+" DEPURANDO "+this.getPensum().get(d+2)+" semestre: "+this.getPensum().get(d+1)+" C:"+this.getPensum().get(d));

            if(this.getPensum().get(d+4).toString().length()>=8){//verificando que tenga prelaciones
                    cuenta=cuenta+1;
                    this.getPreladas().add(this.getPensum().get(d+0));//0 codigo materia
                    this.getPreladas().add(this.getPensum().get(d+1));//1 semestre materia                    
                    this.getPreladas().add(this.getPensum().get(d+4));//2 prelacion 1
                    this.getPreladas().add(this.getPensum().get(d+5));//3 prelacion 2
                    this.getPreladas().add(this.getPensum().get(d+6));//4 prelacion 3
                    this.getPreladas().add(this.getPensum().get(d+7));//5 prelacion 4
                    this.getPreladas().add(this.getPensum().get(d+8));//6 prelacion 5
                    this.getPreladas().add(this.getPensum().get(d+9));//7 prelacion 6
                    this.getPreladas().add(this.getPensum().get(d+2));//8 nombre de la materia
 
            }else{//materias no preladas
                    cuenta2=cuenta2+1;
                    this.getNo_preladas().add(this.getPensum().get(d+0));//codigo materia                    
                    this.getNo_preladas().add(this.getPensum().get(d+1));//semestre materia
                    this.getNo_preladas().add(this.getPensum().get(d+2));//nombre de la materia
            }

        }//fin recorrido

System.out.println("preladas: "+cuenta+" y normales: "+cuenta2);
if(this.getPreladas().size()>0){
System.out.println(this.getPreladas().get(0)+" "+
                   this.getPreladas().get(1)+" "+
                   this.getPreladas().get(2)+" "+
                   this.getPreladas().get(3)+" "+
                   this.getPreladas().get(4)+" "+
                   this.getPreladas().get(5)+" "+
                   this.getPreladas().get(7)+" "+
                   this.getPreladas().get(8)                   
        );
    }
if(this.getNo_preladas().size()>0){
System.out.println(this.getNo_preladas().get(0)+" "+
                   this.getNo_preladas().get(1)+" "+
                   this.getNo_preladas().get(2)+"\n\n");
    }
}// fin metodo prelaciones

/**Este metodo discrimina que solo las materias instruccion militar, actividades complementarias y catedra bolivariana
 son las unicas que pueden tener condicion de aprobado por lo tanto las materias que empiecen por imi, aco y cat seran
 tomadas en cuenta, el resto de las materias cumpliran con los analisis normarles.*/
public boolean materias_condiciones_especiales(String codigo){
   boolean especial=false;
   
        //if((codigo.startsWith("ACO")||codigo.startsWith("IMI")||codigo.startsWith("CAT")||codigo.startsWith("ADG25130") )){
        if((codigo.startsWith("ACO")||codigo.startsWith("IMI")||codigo.startsWith("ADG10820")||codigo.startsWith("ADG10821")||codigo.startsWith("ADG25130") )){
//System.out.println(".................ESPECIAL............"+codigo);
             especial=true;

        }


//System.out.println("especial...CODIGO: "+codigo+"  - especial?: "+especial);
   return especial;
}

public boolean etapa_3(int z){//ESTE METODO MANEJA LAS MATERIAS CON PRELACIONES PERO ES IDENTICO EN OPERACIONES AL METODO DE ABAJO LLAMADO duplicado
//System.out.println("[Metodo etapa_3]");
    boolean aprobastes=false;



       System.out.println("definitiva: "+this.getOrden().get(z+2)+" periodo:"+this.getOrden().get(z+5));
       System.out.println("reparacion: "+this.getOrden().get(z+3)+" periodo:"+this.getOrden().get(z+5));
       System.out.println("condicion: "+this.getOrden().get(z+4)+" periodo:"+this.getOrden().get(z+5));


    aprobastes=this.evaluacion(z,this.getOrden());//evaluando si la materia la tiene aprobada o no


return aprobastes;
}

public boolean duplicado(int z, boolean mostrar){//ES IGUAL AL METODO etapa_3 SALVO QUE SE USO PARA EVALUAR LAS MATERIAS SIN PRELACIONES PERO QUE NO MUESTRE LOS MENSAJES QUE SE MUESTRAN EN EL ANTERIOR
boolean aprobastes=false;                         //SOLO FUE CUESTION DE ESTETICA
//System.out.println("[Metodo duplicado]");


        aprobastes=this.evaluacion(z,this.getOrden());//determinando si la materia esta aprobada o no

        if(mostrar==true){//es solo para mostrar el resultado en la pantalla
            System.out.println("MATERIA ORIGINAL def: "+this.getOrden().get(z+2)+
                               " Rep: "+this.getOrden().get(z+3)+
                               " cond: "+this.getOrden().get(z+4)+
                               " per: "+this.getOrden().get(z+5));

            if(aprobastes){System.out.println("paso");}else{ System.out.println("reprobado");}

        }else{}




return aprobastes;
}



public boolean prelaciones(int s, int k){//se verifican todas las condiciones de las prelaciones
 //System.out.println("[Metodo prelaciones]");

 boolean cumple=false;
 boolean coincidencia=false;
 boolean prelacion1=false;

boolean total=false;
    //PRELACIONES ----------------------------------------------
               // this.especial_coprelacion(this.getPreladas().get(s+2+k).toString());

                //ETAPA 1
              if(this.getPreladas().get(s+2+k).toString().length()>=8 ){//revisando que las materias tengan su prelacion y que el codigo sea igual a 8 caracteres o mas

                        cumple=true;
                    
              }else{cumple=false; prelacion1=true;} //si la casilla prelacion esta en blanco no importa, ignora el analisis e igual pon esa prelacion como aprobado

                        System.out.println(s+" ciclo_1: "+this.getPreladas().get(s+0)+" "+cumple);
                        

         //ETAPA INTERMEDIA...si la prelacion es una coprelacion entonces se ignora. esto es para analizarlo en otro punto
        if(this.getPreladas().get(s+2+k).toString().startsWith("CO-")){//no afecta si el regstro a analizar esta vacio o es null
                System.out.println("ESTA MATERIA ES UNA COPRELACION......");
                prelacion1=true;//se ignora el analisis de la coprelacion

                   
                   //--------esto es para guardar los parametros para hacer el analisis de las coprelaciones en el momento de inscribir una materia
                    this.getCoprelaciones().add(this.getPreladas().get(s));//codigo materia original
                    //this.getCoprelaciones().add(this.getPreladas().get(s+5));//nombre materia original
                    this.getCoprelaciones().add(this.getPreladas().get(s+8));//nombre materia original
                    this.getCoprelaciones().add(this.coprelacion(this.getPreladas().get(s+2+k).toString()));//codigo materia que coprela
                    
                  //   System.out.println("\ncodigo materia original "+this.getPreladas().get(s).toString());
                 //    System.out.println("nombre materia original "+this.getPreladas().get(s+5).toString());
                  //   System.out.println("coprelacion "+this.coprelacion(this.getPreladas().get(s+2+k).toString())+"\n");
                  //-------------------------------------------------------------------------------------------------------
                    
         }else{//si no es coprelacion entonces se hace el analisis normal de la prelacion

              //ETAPA 2
              if(cumple==true){//si hay prelacion existente de la materia ahora comparar con el record

                        //for(int z=0; z<this.getOrden().size(); z=z+6){//recorriendo el record del alumno
                        for(int z=0; z<this.getOrden().size(); z=z+11){//recorriendo el record del alumno

                            if(this.coprelacion(this.getPreladas().get(s+2+k)).equalsIgnoreCase(this.getOrden().get(z+0)) &&//verificando coincidencia de la prelacion con el record
                              // this.getPreladas().get(s+2+k).equalsIgnoreCase(this.getOrden().get(z+0)) && //verificando coincidencia de la prelacion con el record
                               this.u_p_inscrito(this.getOrden().get(z+0)).equalsIgnoreCase(this.getOrden().get(z+5))){//verificando que la materia a comparar sea la ultima que se inscribio

                                coincidencia=true;
                                prelacion1=true;
                                System.out.println("etapa 2 "+this.getPreladas().get(s+0)+"  "+this.getOrden().get(z+0)+" "+this.getOrden().get(z+5));
                                
                                prelacion1=this.etapa_3(z);//ETAPA 3
                                break;

                            }else{coincidencia=false;prelacion1=false;}


                        }//fin reccorido record

                     System.out.println("coincidencia prelacion-record "+coincidencia);

              }else{}//fin etapa2

         }//fin etapa intermedia


System.out.println(s+" "+"PRELACION: "+prelacion1+"\n");
             //----------------------------------

return prelacion1;
}

/**se comprueba que la materia la tenga en el record inscrita para luego hacer las verificaciones */
public boolean comprobacion_materia_original(String original){ //se comprueba que la materia la tenga en el record inscrita para luego hacer las verificaciones
    System.out.println("[Metodo comprobacion_materia_original]");
    boolean confirmacion=false;
    boolean otro=false;


   // for(int i=0 ; i<this.getOrden().size();i=i+6){//recorriendo la materia original en el LinkedList donde estan todas las prelaciones
    for(int i=0 ; i<this.getOrden().size();i=i+11){//recorriendo la materia original en el LinkedList donde estan todas las prelaciones

        if(original.equalsIgnoreCase(this.getOrden().get(i+0))){//verificando que la materia exista
            otro=true;
                         if(otro &&
                            this.u_p_inscrito(original).equalsIgnoreCase(this.getOrden().get(i+5)) &&
                            duplicado(i,true)==false){this.setComprobacion2(true);}else{this.setComprobacion2(false);}//ademas de existir la materia la hallas aprobado
        }//fin verificando que la materia exista
        else{
            //otro=false;
        }
             
            

        if(  original.equalsIgnoreCase(this.getOrden().get(i+0)) &&
             this.u_p_inscrito(original).equalsIgnoreCase(this.getOrden().get(i+5)) &&
             duplicado(i,false)){
            
            confirmacion=true;
            this.setComprobacion3(confirmacion);
            
            break;
        }else{
           confirmacion=false;
           this.setComprobacion3(confirmacion);
         }



    }//fin recorrido de la materia original


    System.out.println("Existe la materia en el record..?->"+otro);
    this.setComprobacion(otro);
 
return confirmacion;

}


public void esquema_prelacion_equivalencias(){

     boolean decision=false;
 this.getLista_autorizada().clear();


 System.out.println("MATERIAS CON EQUIVALENCIAS");




         //for(int s=0; s<this.getPreladas().size(); s=s+6){//recorriendo las prelaciones de la materia
           for(int s=0; s<this.getPreladas().size(); s=s+9){//recorriendo las prelaciones de la materia
           //System.out.println("materia: "+this.getPreladas().get(s+0)+" prelacion: "+this.getPreladas().get(s+2)+"  longitud="+this.getPreladas().get(s+2).length());
           System.out.println("#####################################################");


             //if(comprobacion_materia_original(this.coprelacion(this.getPreladas().get(s+0)))){//verificando prelaciones
             if(false){//forzando a que no verifique la original sino las prelaciones que son las materias necesarias para las esquivalencias
                System.out.println("MATERIA :\""+this.getPreladas().get(s+0)+"\" SIN PROBLEMAS\n");

             }else{//se analiza las prelaciones de la materia original
                 System.out.println("MATERIA :\""+this.getPreladas().get(s+0)+"\" VERIFICARLA\n");

                 //for(int prelacion=0; prelacion<=2; prelacion++){
                 for(int prelacion=0; prelacion<=5; prelacion++){//son 6 prelaciones
                      System.out.println("-asignatura: "+this.getPreladas().get(s)+" prelacion: "+this.getPreladas().get(s+2+prelacion)+" PRE°= "+(prelacion+1));
                       if(this.prelaciones(s,prelacion)){
                                 decision=true;
                       }else{    decision=false;
                                  break;
                       }
                      System.out.println("-----------------------");
                 }



            if(this.isComprobacion()==false && decision==true ){//verificando que no encuentre la materia original en su record y ademas halla pasado sus prelaciones
                System.out.println("MATERIA APROBADA POR EQUIVALENCIA..!!");
                this.setCondicion_materia("E");

            }

           if(this.isComprobacion()==true && this.isComprobacion2()==true){//que halla visto la original y la halla reprobado
                System.out.println("YA HA VISTO LA MATERIA ANTERIORMENTE");
                this.setCondicion_materia("P");

           }

                 //resultado de la revision exaustiva de las prelaciones, decision
           if(decision){
               System.out.println("LA MATERIA ("+this.getPreladas().get(s)+") PUEDE INSCRIBIRLA EN CONDICION "+this.getCondicion_materia()+"\n\n");
               this.getLista_autorizada().add(this.getPreladas().get(s));//codigo
               //this.getLista_autorizada().add(this.getPreladas().get(s+5));//nombre
               this.getLista_autorizada().add(this.getPreladas().get(s+8));//nombre
               this.getLista_autorizada().add(this.getCondicion_materia());//condicion
               this.getLista_autorizada().add(this.getPreladas().get(s+1));//semestre

           }else{
                System.out.println("LA MATERIA ("+this.getPreladas().get(s)+") NO LA PUEDE INSCRIBIR\n\n");
           }



             }//fin combrobacion

           System.out.println("#####################################################\n");
         }//fin recorrido prelaciones

            System.out.println("\n ***** FINALIZADA LA REVISION DE LAS MATERIAS QUE  POSEEN PRELACIONES****");

}




public void esquema_prelacion(){//es el esquema de prelaciones que usa el metodo prelaciones para hacer las verificaciones rigurasas por materia
 System.out.println("[Metodo esquema_prelacion]");

 boolean decision=false;
 this.getLista_autorizada().clear();


 System.out.println("MATERIAS CON PRELACIONES");




         //for(int s=0; s<this.getPreladas().size(); s=s+6){//recorriendo las prelaciones de la materia
        for(int s=0; s<this.getPreladas().size(); s=s+9){//recorriendo las prelaciones de la materia
           //System.out.println("materia: "+this.getPreladas().get(s+0)+" prelacion: "+this.getPreladas().get(s+2)+"  longitud="+this.getPreladas().get(s+2).length());                           
           System.out.println("#####################################################");


             if(comprobacion_materia_original(this.coprelacion(this.getPreladas().get(s+0)))){//verificando prelaciones
                System.out.println("MATERIA :\""+this.getPreladas().get(s+0)+"\" SIN PROBLEMAS\n");

             }else{
                 System.out.println("MATERIA :\""+this.getPreladas().get(s+0)+"\" VERIFICARLA\n");
             
                 //for(int prelacion=0; prelacion<=2; prelacion++){
                 for(int prelacion=0; prelacion<=5; prelacion++){//ahora son 6 prelaciones
                      System.out.println("-asignatura: "+this.getPreladas().get(s)+" prelacion: "+this.getPreladas().get(s+2+prelacion)+" PRE°= "+(prelacion+1));
                       if(this.prelaciones(s,prelacion)){
                                 decision=true;
                       }else{    decision=false;
                                  break;
                       }
                      System.out.println("-----------------------");
                 }



            if(this.isComprobacion()==false && decision==true ){//verificando que no encuentre la materia original en su record y ademas halla pasado sus prelaciones
                System.out.println("PRIMERA VEZ QUE VE LA MATERIA..!!");
                this.setCondicion_materia("N");

            }

           if(this.isComprobacion()==true && this.isComprobacion2()==true){//que halla visto la original y la halla reprobado
                System.out.println("YA HA VISTO LA MATERIA ANTERIORMENTE");
                this.setCondicion_materia("P");

           }

                 //resultado de la revision exaustiva de las prelaciones, decision
           if(decision){
               System.out.println("LA MATERIA ("+this.getPreladas().get(s)+") PUEDE INSCRIBIRLA EN CONDICION "+this.getCondicion_materia()+"\n\n");
               this.getLista_autorizada().add(this.getPreladas().get(s));//codigo
               //this.getLista_autorizada().add(this.getPreladas().get(s+5));//nombre
               this.getLista_autorizada().add(this.getPreladas().get(s+8));//nombre
               this.getLista_autorizada().add(this.getCondicion_materia());//condicion
               this.getLista_autorizada().add(this.getPreladas().get(s+1));//semestre

           }else{
                System.out.println("LA MATERIA ("+this.getPreladas().get(s)+") NO LA PUEDE INSCRIBIR\n\n");
           }

  

             }//fin combrobacion

           System.out.println("#####################################################\n");
         }//fin recorrido prelaciones

System.out.println("\n ***** FINALIZADA LA REVISION DE LAS MATERIAS QUE  POSEEN PRELACIONES****");
}


/**Este metodo se encarga de decidir que procedimiento emplear segun el numero
 de electivas halla por semestre.*/
public String activador_plural_singular(String semestre, String tipo){
    String salida="singular";//estableciendo un valor por defecto
    int cantidad=0;

   cantidad: for(int y=0; y<this.getElectxsem().size(); y=y+3){//recorriendo la cantidad de electivas por semestre y tipo

                    if(semestre.equalsIgnoreCase(this.getElectxsem().get(y+0))//comparando los semestres electiva actual vs la guardada
                       && tipo.equalsIgnoreCase(this.getElectxsem().get(y+1))//comparando el tipo de la electiva
                       ){

                            if((cantidad=Integer.valueOf(this.getElectxsem().get(y+2))) > 1){
                                 //es porque hay varias electivas del mismo tipo en un semestre. al menos 2
                                 salida="plural_"+cantidad;
                            }else{
                             //es porque hay una sola electiva del mismo tipo en el semestre
                                 salida="singular";
                            }
                            break cantidad;//rompe el ciclo
                    }


    }//fin recorrido electivas por semestre y tipo

  return salida;

}




/**Este metodo se enncarga de realizar los analisis a pensum vs record
 de las materias contenidas en el pensum que no poseen prelaciones y las
 electivas*/
public void esquema_normal(){//materias que no posean prelaciones
 //System.out.println("[Metodo esquema_normal]");
    boolean confirmacion=true;
    int referencia=0;
    int maximo=0;
    boolean aprobado=true;

    //----CONSIDERACION DE LAS ELECTIVAS TANTO TECNICAS COMO NO TECNICAS
    this.getEle_cur().clear();//limpiando el LinkedList de electivas cursadas
    this.getElect_orden().clear();//limpiando LinkedList donde se guarda las electivas vistas ya ordenadas por periodo aprobadas y reprobadas
    this.getEle_repro().clear();

    //  this.electivas_bd(new conexion_base_de_datos().getConexion(), carrera);//cargando la lista de las electivas

    //--------------------------------------------------------------------ELECTIVAS



    System.out.println("\n\n==========================================MATERIAS SIN PRELACIONES=======================================================");

    for(int u=0; u<this.getNo_preladas().size(); u=u+3){//buscando el semestre mas grande de la carrera
        if(Integer.parseInt(this.getNo_preladas().get(u+1))>maximo) {
            maximo=Integer.parseInt(this.getNo_preladas().get(u+1)) ;
        }
    }//fin recorrido para semestre mas grande

       // System.out.println("Maximo Semestre De Las No Preladas: "+maximo);


    for(int semestres=1; semestres<=maximo; semestres++){//recorriendo por semestre
        this.setComp_semestre(semestres);//inicializando semestre
        System.out.println("\n\t[ANALIZANDO SEMESTRE:] -> "+semestres+"\n");
        


        for(int g=0; g<this.getNo_preladas().size(); g=g+3){//recorriendo todas las materias que no tienen prelacion del pensum



                        if(this.getComp_semestre()==Integer.valueOf(this.getNo_preladas().get(g+1))){//comparando que lo semestres sean iguales
//                                System.out.println("    MATERIA: "+this.getNo_preladas().get(g+0)+" SEMESTRE= "+this.getNo_preladas().get(g+1)+"--------------------------");

                                if(this.getNo_preladas().get(g+2).startsWith("ELECTIVA")){//analisando solo las materias electivas, tanto tecnicas como no tecnicas
                                    //CONSIDERACION MATERIAS ELECTIVAS
                                        String salida=this.activador_plural_singular(this.getNo_preladas().get(g+1), this.getNo_preladas().get(g+2));//cual se activara!!!

                                    if(salida.equalsIgnoreCase("singular")){
                                        System.out.println("Activando metodo SINGULAR electivas");
                                        this.materias_electivas_singular(this.getNo_preladas().get(g), this.getNo_preladas().get(g+2),Integer.valueOf(this.getNo_preladas().get(g+1)) , this.getOrden(), this.getElectivas());
                                    }else{
                                        System.out.println("Activando metodo PLURAL electivas");
                                        this.materias_electivas_plural(this.getNo_preladas().get(g), this.getNo_preladas().get(g+2), salida ,Integer.valueOf(this.getNo_preladas().get(g+1)) ,this.getOrden(), this.getElectivas());
                                    }


                                       // this.materias_electivas(this.getNo_preladas().get(g), this.getNo_preladas().get(g+2),Integer.valueOf(this.getNo_preladas().get(g+1)) , this.getOrden(), this.getElectivas());
                                    
                                }else{//materias normales no electivas. analisis normal

                                        this.comprobacion_materia_original(this.getNo_preladas().get(g+0));


                                        if(this.isComprobacion3()==true){//verificando que la materia exista y la halla aprobado y que no la halla perdido por inasistencia en el ultimo periodo academico

                                            System.out.println(this.getNo_preladas().get(g+0)+" APROBADA...!");
                                            confirmacion=true;

                                        }else{
                                                if(this.isComprobacion3()==false){
                                                    System.out.println(this.getNo_preladas().get(g+0)+" REPROBADA o NO ENCONTRADA...!");
                                                    confirmacion=false;
                                                    aprobado=false;//solo se modifica si el alumno reprobo o no se le encontro inscrita la materia
                                                }

                                            //this.getLista_autorizada().add(this.getNo_preladas().get(g+0));

                                        }//fin verificacion existencia y aprobacion materia



                                   if(this.isComprobacion()==false ){//verificando que no encuentre la materia original en su record y ademas halla pasado sus prelaciones
                                       System.out.println("PRIMERA VEZ QUE VE LA MATERIA..!!");
                                       this.setCondicion_materia("N");
                                       System.out.println("CONDICION DE LA MATERIA "+this.getCondicion_materia()+" = NORMAL\n");

                                       if(referencia<=0) referencia=semestres;//solo se graba una vez referencia. en caso de que la materia nunca se halla inscrito

                                       this.getLista_autorizada().add(this.getNo_preladas().get(g));//codigo
                                       this.getLista_autorizada().add(this.getNo_preladas().get(g+2));//nombre
                                       this.getLista_autorizada().add(this.getCondicion_materia());//condicion
                                       this.getLista_autorizada().add(this.getNo_preladas().get(g+1));//semestre
                                   }

                                   if(this.isComprobacion()==true && confirmacion==false){//que halla visto la original y la halla reprobado o no la tenga inscrita
                                       System.out.println("YA HA VISTO LA MATERIA ANTERIORMENTE");
                                       this.setCondicion_materia("P");
                                       System.out.println("CONDICION DE LA MATERIA "+this.getCondicion_materia()+" = REPITENCIA\n");

                                       if(referencia<=0) referencia=semestres;//solo se graba una vez referencia. en caso de que la materia este reprobada

                                       this.getLista_autorizada().add(this.getNo_preladas().get(g));//codigo
                                       this.getLista_autorizada().add(this.getNo_preladas().get(g+2));//nombre
                                       this.getLista_autorizada().add(this.getCondicion_materia());//condicion
                                       this.getLista_autorizada().add(this.getNo_preladas().get(g+1));//semestre
                                   }



                            }//fin comprobacion si la materia es electiva o no. si la materia es electiva se hace consideraciones especiales

                        }else{}//fin comparacion coincidencia en cuanto a semestre





        }//fin del recorrido de las materias sin prelacion del pensum

          
                    System.out.println("semestre: "+semestres+" - referencia: "+referencia);
/*OJO la deshabilito para que todas las sin prelaciones aparezcan
             if(aprobado==false & semestres>referencia){//verificando que si alguna materia de ese semestre es reprobada solo se le pueda ofertar la del semestre actual y el siguiente de donde fue reprobada la materia
                System.out.println("LAS MATERIAS DEL SEMESTRE: "+(semestres+1)+" NO SE PODRAN HABILITAR");
                break; }else{}
OJO*/

    }//fin recorrido por semestre


System.out.println("\n ***** FINALIZADA LA REVISION DE LAS MATERIAS QUE NO POSEEN PRELACIONES****");

for(int ecu=0; ecu<this.getEle_cur().size(); ecu=ecu+2){
    System.out.println("Electivas cursadas: "+this.getEle_cur().get(ecu)+" asociada: "+this.getEle_cur().get(ecu+1));
}

for(int ert=0; ert<this.getElect_orden().size(); ert=ert+5){
    System.out.println("Semestre: "+this.getElect_orden().get(ert)
                      +"\tGENERICA: "+this.getElect_orden().get(ert+1)
                      +"\tREAL: "+this.getElect_orden().get(ert+2)
                      +"\tPeriodo: "+this.getElect_orden().get(ert+3)
                      +"\tpaso?: "+this.getElect_orden().get(ert+4)
            );

}

//this.asociaciones_orden(this.getElect_orden(), this.getAsociacion_ordenada());//materias asociada finalmente ordenadas por periodos





}//fin metodo esquema_normal





public void envio_informacion_db(Connection con, String cedula, String estudiante, String carrera,int semestre, String codigo, 
                                String materia, int uc, String dia, String hora, String seccion, String periodo, String docente, String condicion){

    //System.out.println("[Metodo envion_informacion_db ]");
    Statement sentencia=null;


    try{
       sentencia=con.createStatement();
       sentencia.executeUpdate("INSERT INTO control_de_estudio.jornada_inscripcion VALUES(NULL,'"+cedula+"','"+estudiante+"','"+carrera+"',"+semestre+",'"+codigo+"','"+materia+"',"+uc+",'"+dia+"','"+hora+"','"+seccion+"','"+periodo+"',NOW(),'"+docente+"','"+condicion+"');");


  /*     sentencia.executeUpdate("INSERT INTO control_de_estudio.jornada_inscripcion " +
               "(posicion," +
               "cedula," +
               "estudiante," +
               "carrera," +
               "semestre," +
               "codigo," +
               "materia," +
               "uc," +
               "dia," +
               "hora," +
               "seccion," +
               "periodo," +
               "dia_inscripcion,"+
               "docente" +
               "condicion) VALUES (NULL,'"
               +cedula+"','"
               +estudiante+"','"
               +carrera+"',"
               +semestre+",'"
               +codigo+"','"
               +materia+"',"
               +uc+",'"
               +dia+"','"
               +hora+"','"
               +seccion+"','"
               +periodo+"',CURDATE(),'"+docente+"','"+condicion+"');");*/

sentencia.close();
con.close();








       

    }catch(SQLException ex){
        Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
    }
 


}

public void eliminacion_informacion_db(Connection con,String cedula, String codigo, String periodo){
    //System.out.println("[Metodo eliminacion_informacion_db]");

    
    Statement sentencia = null;
         int x=0;
        try {

            sentencia = con.createStatement();
            x=sentencia.executeUpdate("DELETE FROM control_de_estudio.jornada_inscripcion WHERE cedula='" + cedula + "' AND codigo='" + codigo + "' AND periodo='"+periodo+"';");
            System.out.println("condicion del borrado "+x);

        if (x >= 1) {//x es el numero de registros encontrados cuando hace la busqueda

            ima.mensaje_informacion("ELIMINACION EXITOSA !!!", "NOTIFICACION", "eliminacion", "png");


            }else{

                ima.mensaje_informacion("ESTA MATERIA NO EXISTE,\n"+"MEJOR REVISE BIEN...!!!\n",  "ERROR DE REGISTRO", "error", "png");
                
            }


            sentencia.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }


}

public int materias_inscritas_bd(JTable tabla, DefaultTableModel modelo,Connection con, String cedula, String periodo){
      //System.out.println("[Metodo materias_inscritas_bd]");
      Statement sentencia = null;
      ResultSet resultado = null;
      int cuenta=0;

                      

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion where cedula='" + cedula + "' AND periodo='"+periodo+"';");

            while (resultado.next()) {

               Object nuevo[]={ resultado.getInt("semestre"),
                                resultado.getString("codigo"),
                                resultado.getString("materia"),
                                resultado.getInt("uc"),
                                resultado.getString("seccion"),
                                resultado.getString("dia")+" - "+resultado.getString("hora"),
                                resultado.getString("docente"),
                                resultado.getString("condicion")
               };

               modelo.addRow(nuevo);
               modelo.fireTableDataChanged();
               cuenta=cuenta+resultado.getInt("uc");

               //System.out.println(resultado.getString("materia"));

            }//fin ciclo while

            this.setCredito(cuenta);

            sentencia.close();
            resultado.close();
            con.close();



        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }




return cuenta;
}


public void pdf_materias_inscritas(Connection con, String cedula, String periodo, boolean activar_mensaje){
    //System.out.println("[Metodo pdf_materias_inscritas]");
    Statement sentencia = null;
    ResultSet resultado = null;
    this.getPdf_mi().clear();


    try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE (cedula='"+cedula+"' AND periodo='"+periodo+"')  ORDER BY semestre ASC;");

            while(resultado.next()){
                this.getPdf_mi().add(String.valueOf(resultado.getInt("semestre")));//0
                this.getPdf_mi().add(resultado.getString("codigo"));//1
                this.getPdf_mi().add(resultado.getString("materia"));//2
                this.getPdf_mi().add(String.valueOf(resultado.getInt("uc")));//3
                this.getPdf_mi().add(resultado.getString("seccion"));//4
                this.getPdf_mi().add(resultado.getString("dia")+" - "+resultado.getString("hora"));//5
                this.getPdf_mi().add(resultado.getString("docente"));//6
                this.getPdf_mi().add(resultado.getString("condicion"));//7
                this.getPdf_mi().add(resultado.getString("periodo"));//8
            }
             


            sentencia.close();
            resultado.close();
        
            if(this.getPdf_mi().isEmpty()){
                System.out.println("Este estudiante no tiene materias inscritas en este periodo academico");
                if(activar_mensaje)new imagenes().mensaje_informacion("Este estudiante no ha inscrito materias en el periodo actual : "+periodo, "MATERIAS INSCRITAS", "no_hay", "png");
            }

        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}


public String turnos(Connection con, String cedula){
    //System.out.println("[Metodo turnos]");
       Statement sentencia = null;
       ResultSet resultado = null;
       String turno=null;

    try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT turno FROM control_de_estudio.datos_academicos WHERE cedula='" + cedula + "';");
            resultado.next();
            System.out.println(resultado.getString("turno"));
            turno=resultado.getString("turno");

            sentencia.close();
            resultado.close();
            con.close();

    } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }



return turno;
}

public void capacidad_salones(Connection con,String carrera, String materia, String seccion, String periodo, String turno){
    //System.out.println("[Metodo capacidad_salones]");

        Statement sentencia = null;
        ResultSet resultado = null;
        this.setCupos(0);//inicializndo los cupos dentro del metodo
        int cuenta=0;
        registro_ingenieria ri = new registro_ingenieria();


      System.out.println("\n"+"Seccion:: "+seccion+" Materia: "+materia+" periodo: "+periodo);

        try {
            sentencia=con.createStatement();
            //resultado=sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE materia='"+materia+"'");
            resultado=sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE periodo='"+periodo+"' AND carrera ='"+carrera+"' AND materia='"+materia+"' AND (seccion='"+seccion+"' OR seccion='"+this.seccion_mejorada(seccion, turno)+"') ;");
            resultado.next();
            this.setCupos(resultado.getInt("capacidad"));
            sentencia.close();
            resultado.close();

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE periodo='"+periodo+"' AND (seccion='" + seccion + "' OR seccion='"+this.seccion_mejorada(seccion, turno)+"') AND materia='" + materia + "' GROUP BY cedula;");

            while(resultado.next()){//verificando los cupos y comparando la capacidad
                cuenta=cuenta+1;
            }//fin ciclo verificacion de cupos y capacidad
            System.out.println("Inscritos = "+cuenta);

                    if(this.getCupos()>cuenta){
                        this.setHay_cupos(true);

                    }else{this.setHay_cupos(false);
                    ima.mensaje_informacion("LO SENTIMOS, PERO YA NO QUEDAN CUPOS DISPONIBLES\n"
                            +"PARA ESTA SECCION, VERIFIQUE EN OTRA...!!!", "CUPOS AGOTADOS", "agotados", "png");

                   

                    }

            
            


                System.out.println("cupos"+this.getCupos());
                

            
            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
           
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }

}

public boolean comparacion_autorizadas_ofertadas(String ofertada, horario hor){
    boolean iguales=false;

    System.out.println("[Metodo comparacion_autorizadas_ofertadas]");

    if(ofertada.contains("ELECTIVA")){//experimental
        this.setCondicion_materia("N");//se establece esta condicion para todas las electivas a inscribir
        iguales=true;
    }else{


            for(int i=0; i<hor.getLista_autorizada_traspaso().size();i=i+4){

                if(ofertada.equalsIgnoreCase(hor.getLista_autorizada_traspaso().get(i+1)) //comparando las materias del horario con las autorizadas
                   //ofertada.contains("ELECTIVA TECNICA") || ofertada.contains("ELECTIVA NO TECNICA")
                   //hor.getLista_autorizada_traspaso().get(i+1).contains(ofertada)//o que la materia a inscribir contenga el nombre del pensum
                   ){//que contenga la palabra tecnica o no tecnica para las electivas

                        System.out.println("detalle: "+hor.getLista_autorizada_traspaso().get(i)+" "+hor.getLista_autorizada_traspaso().get(i+1)+" "+hor.getLista_autorizada_traspaso().get(i+2)+" "+hor.getLista_autorizada_traspaso().get(i+3));
                    iguales=true;
                    System.out.println("SON IGUALES? "+iguales+"    Insertando condicio a electiva: "+hor.getLista_autorizada_traspaso().get(i+2));
                    this.setCondicion_materia(hor.getLista_autorizada_traspaso().get(i+2));
                    break;
                }else{iguales=false;}


            }//fin recorrido autorizadas
    }

return iguales;

}

/**Metodo que se encarga de saber la condicion real de la electiva en el momento de 
 inscribirla. pero revisarsi hay otro metodo que hace lo mismo y no me acuerdo*/
public String condicion_real_electivas(String codigo_real, String seleccionada, horario hor){
//posicion 0 = codigo posicion 1 = nombre posicion 2 = condicion posicion 3 = semestre
    String condicion="P";//en condicion REPITENCIA por defecto
    boolean asociada=false;//se establece por defecto que no hay asociacion entre la elctiva de las autorizadas y la real segun el semestre

    for(int a=0; a<hor.getLista_autorizada_traspaso().size(); a=a+4){//recorriendo la lista de las materias autorizadas
        String autorizada = hor.getLista_autorizada_traspaso().get(a+1);//asignando nombre de la materia en una variable x comodidad
        String codigo_pensum = hor.getLista_autorizada_traspaso().get(a+0);//asignando codigo de la materia a la variable

        if( seleccionada.endsWith("("+autorizada+")") && //revisando que la seleccionada termine en (ELECTIVA TECNICA) o (ELECTIVA NO TECNICA)
            String.valueOf(hor.getSemestre().getSelectedIndex()+1).equalsIgnoreCase(hor.getLista_autorizada_traspaso().get(a+3)) //y que el semestre de la materia seleccionada corresponda con el de las autorizadas
            ){

                if(this.getEaup().isEmpty()){//si el LinkedList esta vacio se añade la informacion por primera vez
                    System.out.println("Añadiendo asociacion condicion real!!!");
                    this.getEaup().add(codigo_pensum);//agregando la asociacion al LinkedList. recordar que los codigos de las electivas
                    this.getEaup().add(codigo_real); //del pensum son unicas asi como el de las electivas reales
                    condicion = hor.getLista_autorizada_traspaso().get(a+2);//guardando la condicion de la materia de las autorizadas
                    break;//rompe el ciclo de lista autorizadas
                }else{//cuando el vetor contiene informacion se hace revision de lo que se desea saber

                    //FASE A
                    for(int w=0; w<this.getEaup().size(); w=w+2 ){//recorriendo el LinkedList eaup

                        if(this.getEaup().get(w+0).equalsIgnoreCase(codigo_pensum) &&//si el codigo autorizada guardada es el mismo que la del recorrido 
                           this.getEaup().get(w+1).equalsIgnoreCase(codigo_real)     //y el codigo real guardado es el mismo de la seleccionada
                           ){
                           System.out.println("Esta asociacion de codigos existe cp: "+codigo_pensum+" cr: "+codigo_real);
                           asociada=true;
                           break;//rompe el ciclo de las Eaup
                        }else asociada=false;
                    }//fin recorrido del LinkedList eaup

                    //FASE B
                    if(asociada==false){//en caso despues de haber recorrido la lista de las electivas asociadas y no se encontro esta asociacion se procede a realizarla
                        System.out.println("Añadiendo asociacion condicion real porque no existe en la lista!!!");
                        this.getEaup().add(codigo_pensum);//agregando la asociacion al LinkedList. recordar que los codigos de las electivas
                        this.getEaup().add(codigo_real); //del pensum son unicas asi como el de las electivas reales
                        condicion = hor.getLista_autorizada_traspaso().get(a+2);//guardando la condicion de la materia de las autorizadas
                        break;//rompe ciclo de lista autorizadas
                    }
                
                }//fin de consideracion del LinkedList eaup

        }//fin identificacion de elctiva y semestre

    }//fin recorrido de materias autorizadas

System.out.println("CONDICION REAL: "+condicion);
return condicion;
}

/**Metodo que se encarga de precargar el LinkedList con las electivas inscritas */
public void precarga_asociacion_electiva(horario hor){
    System.out.println("[METODO precarga_asociacion_electiva]");
    String materia="NINGUNA";//variable que se usara para evitar revisar la misma materia dos veces de las inscritas
    JTable inscritas=hor.getSecciones();
    boolean asociada=false;

//    Distribuicion de la variable: posicion 0 = codigo posicion 1 = nombre posicion 2 = condicion posicion 3 = semestre
   for(int i=0; i<inscritas.getRowCount(); i++){//recorriendo las materias inscritas de la interfaz


        if(inscritas.getValueAt(i, 2).toString().contains("ELECTIVA")){//columna materia. revisando si la materia es una electiva


                if(materia.equalsIgnoreCase(inscritas.getValueAt(i, 2).toString())){System.out.println("Materia ya analizada. "+inscritas.getValueAt(i, 2));}//son las mismas y se ignora ya que fue analizada
                else{//analizando cuando las materias electivas a analizar son distintas

                    for(int g=0; g<hor.getLista_autorizada_traspaso().size(); g=g+4){//recorriendo las autorizadas

                        if(inscritas.getValueAt(i,0).toString().equalsIgnoreCase(hor.getLista_autorizada_traspaso().get(g+3)) &&//que sea mismo semestre
                           inscritas.getValueAt(i,2).toString().endsWith("("+hor.getLista_autorizada_traspaso().get(g+1)+")") && //que termine con el nombre elctiva tecnica o no tecnica
                           inscritas.getValueAt(i,7).toString().equalsIgnoreCase(hor.getLista_autorizada_traspaso().get(g+2)) //y que sea la misma condicion de la materia
                        ){



                                 if(this.getEaup().isEmpty()){//si esta vacio el LinkedList se carga con la primera asociacion
                                    System.out.println("PRECARGA. Añadiendo asociacion !!! pensum:"+hor.getLista_autorizada_traspaso().get(g)+" real:"+inscritas.getValueAt(i,1).toString());
                                    this.getEaup().add(hor.getLista_autorizada_traspaso().get(g));//agregando la asociacion al LinkedList. recordar que los codigos de las electivas
                                    this.getEaup().add(inscritas.getValueAt(i,1).toString()); //del pensum son unicas asi como el de las electivas reales
                                 }else{//si contiene informacion se revisa si esta asociacion existe

                                        //FASE A
                                        for(int w=0; w<this.getEaup().size(); w=w+2 ){//recorriendo el LinkedList eaup

                                            if(this.getEaup().get(w+0).equalsIgnoreCase(hor.getLista_autorizada_traspaso().get(g)) &&//si el codigo autorizada guardada es el mismo que la del recorrido
                                               this.getEaup().get(w+1).equalsIgnoreCase(inscritas.getValueAt(i,1).toString()) //y el codigo real guardado es el mismo de la seleccionada
                                               ){
                                               System.out.println("PRECARGA. Esta asociacion de codigos existe cp: "+hor.getLista_autorizada_traspaso().get(g)+" cr: "+inscritas.getValueAt(i,1).toString());
                                               asociada=true;
                                               break;//rompe el ciclo de las Eaup
                                            }else asociada=false;
                                        }//fin recorrido del LinkedList eaup

                                        //FASE B
                                        if(asociada==false){//en caso despues de haber recorrido la lista de las electivas asociadas y no se encontro esta asociacion se procede a realizarla
                                            System.out.println("PRECARGA. Añadiendo asociacion condicion real porque no existe en la lista!!! pensum:"+hor.getLista_autorizada_traspaso().get(g)+" real:"+inscritas.getValueAt(i,1).toString());
                                            this.getEaup().add(hor.getLista_autorizada_traspaso().get(g));//agregando la asociacion al LinkedList. recordar que los codigos de las electivas
                                            this.getEaup().add(inscritas.getValueAt(i,1).toString()); //del pensum son unicas asi como el de las electivas reales

                                            //break;//rompe ciclo de lista autorizadas
                                        }

                                 }//fin analisis si la asociacion existe


                           break;//rompiendo ciclo autorizadas
                        }

                    }//fin recorrido autorizadas

            }//evitando analizar la misma materia varias veces ya que cuando esta inscrita una depende del numero de dias que se ve

                materia=inscritas.getValueAt(i, 2).toString();//guardando nombre de materia analizada para evitar repetirla

        }//fin identificacion si es electiva




    }//fin recorrido inscrita interfaz





}






/**Metodo que se encarga de revisar de que el alumno no inscriba mas del maximo de
 repitencias permitidas por el proceso de inscripcion, para ello se necesita pasar como parametro
 toda la clase de horario y las maximas repitencias autorizadas, asi como tambien el tipo de condicion de materia
 que se quiera analizar*/
public boolean maxima_repitencias(horario hor,int maxima_repitencias, String cond){
    String seleccionada=null, materia="nada", m_condicion=null;
    int contador=0;
    boolean autorizado=true;

    System.out.println("[Metodo maximas_repitencias]");

    if(hor.getHorario().getSelectedColumn()>0){//validando que no suceda nada si selecciona por accidente en la columna de hora

            seleccionada=hor.getHorario().getValueAt(hor.getHorario().getSelectedRow(), hor.getHorario().getSelectedColumn()).toString();//se obtiene el nombre de la materia seleccionada


                for(int i=0; i<hor.getLista_autorizada_traspaso().size(); i=i+4){//recorriendo la lista de materias autorizadas para comparar con la seleccionada y asi saber sus parametros

                    if(seleccionada.equalsIgnoreCase(hor.getLista_autorizada_traspaso().get(i+1))
                       || seleccionada.contains(hor.getLista_autorizada_traspaso().get(i+1))    ){//buscando coincidencia por el nombre de la materia


                        m_condicion= hor.getLista_autorizada_traspaso().get(i+2);//guardando la condicion de la materia

                    }//fin coincidencia

                 }//fin recorrido lista autorizadas


            if(m_condicion.equalsIgnoreCase(cond)){//solo se hace el siguiente analisis si la materia seleccionada tiene la condicion igual a la que se paso por parametro en "cond"

                autorizado=this.num_matxcondicion(hor, cond, maxima_repitencias);
                System.out.println("autorizada la materia? "+autorizado);
              /*  for(int s=0; s<hor.getSecciones().getRowCount(); s++){//recorriendo la tabla de materias inscritas para saber cuantas materias por condicion P=repitencia existen

                        if(cond.equalsIgnoreCase(hor.getSecciones().getValueAt(s,7).toString()) //verificando la columna condicion, para asi saber cuantas materias parecidas al parametro son iguales
                           && materia!=hor.getSecciones().getValueAt(s,1).toString() ){//verificando que la materia no se cuente mas de una vez

                            contador=contador+1;//contando el numero de veces que se encuentra la condicion que se paso por parametro
                            materia=hor.getSecciones().getValueAt(s, 1).toString();//si se cumple la condicion se guarda en la variable el codigo de la materia

                        }

                        if(contador>=maxima_repitencias){//si se alcanza el maximo permitido no se permitira inscribir otra materia con la condicion analizada
                            autorizado=false;
                            registro_ingenieria r= new registro_ingenieria();
                            r.mensajes("Disculpe, pero no puede inscribir mas materias\n" +
                                       "en condicion de: \""+this.condicion_materia(cond)+"\"",
                                       "MAXIMA REPITENCIAS ALCANZADAS ("+maxima_repitencias+")");
                            break;
                        }
                }//fin recorrido materias inscritas
*/
            }//fin analisis de la condicion buscada


    }//fin validacion


    System.out.println("esta autorizada la materia: "+seleccionada+" R="+autorizado);
    return autorizado;
}//fin metodo maxima_repitencias


public boolean num_matxcondicion(horario hor, String condicion, int maximo){
    int contador=0;
    String materia="ninguna";
    boolean autorizado=true;

    JTable tabla=hor.getSecciones();//asignando la tabla de la clase a una variable x comodidad
    
    for(int r=0; r<tabla.getRowCount(); r++){//recorriendo las materias inscritas
        
        
        if(condicion.equalsIgnoreCase(tabla.getValueAt(r, tabla.getColumnModel().getColumnIndex("CONDICION")).toString())//comparando la condicion de la materia
           && !materia.equals(tabla.getValueAt(r, tabla.getColumnModel().getColumnIndex("CODIGO")).toString())  //comparando el nombre de la materia ya que se almacena para evitar que se cuente la misma otra vez
                ){
        
                contador = contador + 1;
                materia=tabla.getValueAt(r,tabla.getColumnModel().getColumnIndex("CODIGO")).toString();
                System.out.println("Informacion: "+contador+" materia: "+materia);
                autorizado=true;

               if(contador>=maximo){
                   System.out.println("Se ha llegado al limite de materias x condicion");
                   autorizado=false;
                   registro_ingenieria ri= new registro_ingenieria();
                    ima.mensaje_informacion("Disculpe, pero no puede inscribir mas materias\n" +
                                       "en condicion de: \""+this.condicion_materia(condicion)+"\"",  "MAXIMA REPITENCIAS ALCANZADAS ("+maximo+")", "alto", "png");

                   
                   break;
               }
        }
        
    }//fin recorrido

return autorizado;

}

/**Metodo que carga las electivas de una carrera en un LinkedList llamado electivas. Carga solo las elec
 tivas de una carrera especifica. devuelve 0=codigo, 1=nombre, 2=credito, 3= tecnica o no tecnica dentro del LinkedList*/
public void electivas_bdxx(Connection con, String carrera){
        Statement sentencia = null;
        ResultSet resultado = null;


        this.getElectivas().clear();//limpia la variable cada vez que se quiere cargar.

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM pensum.electivas WHERE carrera = '" + carrera + "' ;");
            while (resultado.next()) {

                this.getElectivas().add(resultado.getString("codigo")); //0 codigo de la asignatura
                this.getElectivas().add(resultado.getString("asignatura")); //1 nombre de la asignatura
                this.getElectivas().add(String.valueOf(resultado.getInt("uc"))); //2 credito de la materia
                this.getElectivas().add(resultado.getString("tipo")); //3 tipo de electiva- tecnica o no tecnica


            }


            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



}

/**Metodo que permite determinar el periodo mas actual y mas antiguo de las materias electivas que el estudiante a cursado */
public void orden_año_electiva(String prueba){
//System.out.println("[Metodo orden_año_electiva]");
    System.out.println("año electivas periodos: "+prueba+"  longitud "+prueba.length());
    //prueba=prueba.substring(2);
    if(prueba.length()>6)//estas son las materias intensivas.
          prueba=prueba.substring(3); //materias con periodos intensivos
    else  prueba=prueba.substring(2);//materias con periodos normales

    if(this.getMas_pequeño()==0){
        this.setElec_peque(Integer.valueOf(prueba));
        this.setElec_grande(Integer.valueOf(prueba));
    }else{
    if(Integer.valueOf(prueba)<this.getElec_peque()){this.setElec_peque(Integer.valueOf(prueba));}
    if(Integer.valueOf(prueba)>this.getElec_grande()){this.setElec_grande(Integer.valueOf(prueba));}
     }
    System.out.println("PERIODOS mas pequeño: "+this.getMas_pequeño()+"  mas grande: "+this.getMas_grande());


}


//METODO para ordenar las asociaciones realizadas en las electivas
public void asociaciones_orden(LinkedList<String> desorden, LinkedList<String> ordenadas){
    LinkedList<String> aux_e1 = new LinkedList<>();
    LinkedList<String> aux_ei1 = new LinkedList<>();
    LinkedList<String> aux_e2 = new LinkedList<>();
    LinkedList<String> aux_ei2 = new LinkedList<>();
    ordenadas.clear();//limpiando el LinkedList antes de usarlas
    this.setElec_grande(0);//estableciendo valores para años de periodos electivas. limpieza
    this.setElec_peque(0);

//FASE 1: DETERMINANDO PERIODOS MAS ACTUAL Y MAS ANTIGUO
    año:for(int i=0; i<desorden.size(); i=i+5){//este recorrido es solo para determinar el periodo mas grande y mas pequeño de las materias electivas
        this.orden_año_electiva(desorden.get(i+3));//analizando periodos de las electivas
    }//fin recorrido todas las materias electivas para determinar periodos

//FASE 2: ORDENANDO LAS ASOCIACIONES MATERIAS ELECTIVAS POR PERIODO ACADEMICO
 //this.getElect_orden().add(semestre);
   // this.getElect_orden().add(generica);
   // this.getElect_orden().add(real);
    //this.getElect_orden().add(periodo);

    for(int a=this.getElec_peque(); a<=this.getElec_grande();a++){//para comparar con el año. se ordenan las materia por año y periodo. se separan por año y despues se ordenan por periodo del mismo año. eso se hace en cada iteracion

     //   System.out.println("ORDENAMIENTO--año:"+a);

   //--- ANALISANDO MATERIAS NORMALES E INTESIVOS. OBTENIENDO SUBGRUPOS POR PERIODO Y (si es normal o intensivo)
        for(int y=0; y<desorden.size(); y=y+5){//ciclo para recorrer el LinkedList ....sugerencia se puede mejorar este ciclo haciendo una division con el % ya que si se divide entre 2 y el resultado es exacto se acumula en un registro sino en el otro...es util para los semestres pares e impares
            //ANALISIS PRIMER PERIODO DEL AÑO
                if(desorden.get(y+3).trim().equalsIgnoreCase("1-"+a)){//todos los que pertenecen al primer periodo
                    //   System.out.println("1-"+x);
                    aux_e1.add(desorden.get(y));//guardando semestre
                    aux_e1.add(desorden.get(y+1));//guardando codigo generico electiva
                    aux_e1.add(desorden.get(y+2));//guardando codigo real electiva
                    aux_e1.add(desorden.get(y+3));//guardando periodo academico
                    aux_e1.add(desorden.get(y+4));//guardando si paso
                }
                if(desorden.get(y+3).trim().equalsIgnoreCase("I1-"+a)){//periodo intensivo asociado al primer periodo academico.
                   // System.out.println("INTENSIVO - I1-"+x);
                    aux_ei1.add(desorden.get(y));//guardando semestre
                    aux_ei1.add(desorden.get(y+1));//guardando codigo generico electiva
                    aux_ei1.add(desorden.get(y+2));//guardando codigo real electiva
                    aux_ei1.add(desorden.get(y+3));//guardando periodo academico
                    aux_ei1.add(desorden.get(y+4));//guardando periodo academico
                }
                //ANALISIS SEGUNDO PERIODO DEL AÑO
                if(desorden.get(y+3).trim().equalsIgnoreCase("2-"+a)){//todos los que pertenecen al segundo periodo
                     //   System.out.println("2-"+x);
                    aux_e2.add(desorden.get(y));//guardando semestre
                    aux_e2.add(desorden.get(y+1));//guardando codigo generico electiva
                    aux_e2.add(desorden.get(y+2));//guardando codigo real electiva
                    aux_e2.add(desorden.get(y+3));//guardando periodo academico
                    aux_e2.add(desorden.get(y+4));//guardando periodo academico
                }
                 if(desorden.get(y+3).trim().equalsIgnoreCase("I2-"+a)){//periodo intensivo asociado al segundo periodo academico.
                   // System.out.println("INTENSIVO - I2-"+x);
                    aux_ei2.add(desorden.get(y));//guardando semestre
                    aux_ei2.add(desorden.get(y+1));//guardando codigo generico electiva
                    aux_ei2.add(desorden.get(y+2));//guardando codigo real electiva
                    aux_ei2.add(desorden.get(y+3));//guardando periodo academico
                    aux_ei2.add(desorden.get(y+4));//guardando periodo academico
                }

        }//fin reccorrido del LinkedList


          //  System.out.println(this.getAux1().size()+" - "+this.getAux2().size());
         //----LLENANDO EL LinkedList PRINCIPAL DONDE DEFINITIVAMENTE ESTARAN LAS MATERIAS ORDENADAS, TANTO NORMALES COMO INTENSIVOS

        for(int r=0; r<aux_e1.size(); r=r+5){//reordeando materias por periodo academico que empiece por 1
            ordenadas.add(aux_e1.get(r));
            ordenadas.add(aux_e1.get(r+1));
            ordenadas.add(aux_e1.get(r+2));
            ordenadas.add(aux_e1.get(r+3));
            ordenadas.add(aux_e1.get(r+4));
        }

         for(int r=0; r<aux_ei1.size(); r=r+5){//reordeando materias de INTESIVOS por periodo academico que empiece por I1
            ordenadas.add(aux_ei1.get(r));
            ordenadas.add(aux_ei1.get(r+1));
            ordenadas.add(aux_ei1.get(r+2));
            ordenadas.add(aux_ei1.get(r+3));
            ordenadas.add(aux_ei1.get(r+4));
        }

        for(int r=0; r<aux_e2.size(); r=r+5){//reordenando materias por periodo academico que empiece por 2
            ordenadas.add(aux_e2.get(r));
            ordenadas.add(aux_e2.get(r+1));
            ordenadas.add(aux_e2.get(r+2));
            ordenadas.add(aux_e2.get(r+3));
            ordenadas.add(aux_e2.get(r+4));
        }

        for(int r=0; r<aux_ei2.size(); r=r+5){//reordenando materias de INTENSIVOS por periodo academico que empiece por I2
            ordenadas.add(aux_ei2.get(r));
            ordenadas.add(aux_ei2.get(r+1));
            ordenadas.add(aux_ei2.get(r+2));
            ordenadas.add(aux_ei2.get(r+3));
            ordenadas.add(aux_ei2.get(r+4));
        }


            //limpiando los LinkedListes auxiliares para usos posteriores
               aux_e1.clear();
               aux_ei1.clear();
               aux_e2.clear();
               aux_ei2.clear();


    }//fin comparar año



    //PRUEBA. Asociaciones ordenadas

        prueba: for(int i=0; i<ordenadas.size(); i=i+5 ){

            System.out.println("semestre:"+ordenadas.get(i)+" cod-g:"+ordenadas.get(i+1)+" cod-o:"+ordenadas.get(i+2)+" periodo:"+ordenadas.get(i+3)+" paso? "+ordenadas.get(i+4));
        }



}

/**Metodo complementario que ayuda a contar exactamente cuantas materias electivas segun su tipo hay
 por semestre. complemento de "electivas_x_semestre"*/
private void contador_electivas(String semestre, String tipo){
 boolean coincidencia=false;

    if(this.getElectxsem().isEmpty()){//si no hay registro, se anexa la primera electiva encontrada
                        //System.out.println("Añadiendo nueva electiva al registro acumulado: "+tipo);
                        this.getElectxsem().add(semestre);//guardando semestre
                        this.getElectxsem().add(tipo);//guardando tipo
                        this.getElectxsem().add("1");//añadiendo 1 a la cantidad

    }else{//sino se procede a revisar si esta entre las guardadas para sumar una o sino existe se añade como nueva

        for(int e=0; e<this.getElectxsem().size(); e=e+3){//recorriendo todas las electivas guardadas segun se van encontrando

                if(this.getElectxsem().get(e).equalsIgnoreCase(semestre) //si son del mismo semestre
                   && this.getElectxsem().get(e+1).startsWith(tipo) //y empiezan con el mismo tipo.
                        ){
                        //System.out.println("Existente: "+tipo+" sumando 1");
                        coincidencia=true;
                        this.getElectxsem().set(e+2, String.valueOf(Integer.valueOf(this.getElectxsem().get(e+2)) + 1 ) );//guardando cantidad nueva. sumando 1
                        break;//se rompe el recorrido de electivas cantidad guardadas
                }else{coincidencia=false;}

        }//fin recorrido de todas las electivas guardadas

            if(coincidencia==false){//si despues de recorrer todas las materias electivas en cantidad guardadas no se hallo una parecida se añade como registro nueva la materia electiva que esta bajo analisis
                        //System.out.println("Despues de recorrer el LinkedList no se encontro ninguna coincidencia. Añadiendo nueva electiva al registro acumulado: "+tipo);
                        this.getElectxsem().add(semestre);//guardando semestre
                        this.getElectxsem().add(tipo);//guardando tipo
                        this.getElectxsem().add("1");//añadiendo 1 a la cantidad
            }//fin analisis despues de recorrer electivas cantidad guardadas





    }//fin condicional existencia registro
}


/**Calcula cuantas materias electivas segun su tipo y semestre existen en el pensum de la carrera
segun su vigencia*/
public void electivas_x_semestre(LinkedList<String> pensum){
    this.getElectxsem().clear();//limpiando la variable
//ELETEC--=electiva tenica. ELENOT--=electiva no tecnica
    //for(int p=0;p<pensum.size();p=p+7){//recorriendo todo el pensum
    for(int p=0;p<pensum.size();p=p+10){//recorriendo todo el pensum

        if(pensum.get(p+2).startsWith("ELECTIVA TECNICA")||pensum.get(p+2).startsWith("ELECTIVA NO TECNICA")){//discriminando solo las electivas tecnicas y no tecnicas

            this.contador_electivas(pensum.get(p+1), pensum.get(p+2));


        }//fin discriminacion electivas

    }//fin recorrido pensum
    
    
//RESUMEN CONTADOR ELECTIVAS
    /*System.out.println("RESUMEN CONTADOR DE ELECTIVAS POR SEMESTRE");
    for(int i=0; i<this.getElectxsem().size(); i=i+3){//recorriendo el LinkedList con los parametros

        System.out.println("semestre: "+this.getElectxsem().get(i+0)+" tipo: "+this.getElectxsem().get(i+1)+" camtidad: "+this.getElectxsem().get(i+2));
    }//fin recorrido LinkedList con parametros
*/

}



/**Este metodo se encarga de hacer las consideraciones de las materias electivas donde se puede saber si la tiene aprobada o
 tiene que verla y poderle ofertar todas las electivas que ocuparan la casilla que le corresponde dentro del pensum. Para ello
 verifica materia a materia del pensum hasta que encuentra una electiva y es donde inicia las busqueda en el record. Las Materias
 electivas no poseen prelaciones. Si es una electiva se regresa un true sino no*/
public boolean materias_electivas_singular(String codigo, String electiva, int semestre,  LinkedList<String> orden_alt, LinkedList<String> lista_electivas){
                //this.getElectivas().add(pensum.getString("codigo"));//0
  //              this.getElectivas().add(pensum.getString("asignatura"));//1
    //            this.getElectivas().add(String.valueOf(pensum.getInt("uc")));//2
      //          this.getElectivas().add(pensum.getString("tipo"));//3
boolean es_electiva=false;
boolean se_uso=false;
boolean coincidencia=false;
boolean existe_repetida=false;
boolean aprobada=false;
boolean aprobada_usada=false;




//this.getNo_preladas().get(g), this.getNo_preladas().get(g+2)

    if(electiva.startsWith("ELECTIVA")){//verificando si la materia pensum sugiere electiva una electiva
        es_electiva=true;//la materia si es una electiva
//System.out.println("SI ES ELECTIVA --->"+electiva);
 electivas_sublista:   for(int ele=0; ele<lista_electivas.size();ele=ele+4){//recorriendo las electivas reales Nivel 0
            if(electiva.equalsIgnoreCase(lista_electivas.get(ele+3))){//identificando un solo tipo: electiva tecnica o no tecnica
//System.out.println("TIPO: "+lista_electivas.get(ele+3) );
                          //---
                          if(this.getUsadas().size()>0 && !this.getUsadas().getLast().equals(String.valueOf(semestre))){this.getUsadas().clear();System.out.println("Limpiando LinkedList");}//revisando que la lista que uso como usadas todas las materias son del mismo semestre sino se limpia el LinkedList por cada semestre
                          //---
                          for(int u=0;u<this.getUsadas().size();u=u+2){//recorriendo las electivas reales usadas para ver si la electiva que se esta usando para revisar no se este usando otra vez ya que por cada electiva del pensum simpre la revision se empieza desde cero
                              if(this.getUsadas().get(u).equalsIgnoreCase(lista_electivas.get(ele))){se_uso=true;}else{se_uso=false;}
                          }
//System.out.println("se uso? "+se_uso) ;
                          //---
//System.out.println("AGREGANDO ELECTIVA EN LA LISTA DE LAS USADAS");
                          this.getUsadas().add(lista_electivas.get(ele));//agregando electiva actual de la lista como usada ya que se usa para revisar.
                          this.getUsadas().add(String.valueOf(semestre));//agregando el semestre donde se esta haciendo la revision
                          //--

              /*            pruebas: for(int i=0; i<this.getUsadas().size(); i=i+2){
                                    System.out.println("REVISANDO ELECTIVAS: usadas: "+this.getUsadas().get(i)+" semestre: "+this.getUsadas().get(i+1));
                                    }*/


                    if(se_uso == false){ //no esta repetida. que no se esta usando la misma electiva real otra vez para realizar revision
//System.out.println("ELECTIVA DE LA LISTA NO USADA");
                            for(int x=0; x<orden_alt.size(); x = x+11){//recorriendo el record del estudiante para realizar la revision de las electivas Nivel 1
//System.out.println("Recorriendo registro orden semestre: "+orden_alt.get(x+10)+"  Record: "+orden_alt.get(x)+" - electiva actual: "+lista_electivas.get(ele));

                                if( orden_alt.get(x+10).equalsIgnoreCase(String.valueOf(semestre)) && //sublista. se revisa solo las materias que la electiva del pensum sugiera
                                    orden_alt.get(x+0).equalsIgnoreCase(lista_electivas.get(ele+0)) &&  //comparando codigo del record con el de la electiva real
                                    this.electiva_reprobada(orden_alt.get(x+0), orden_alt.get(x+10), orden_alt.get(x+5),codigo)==false //verificando que no se use la misma electiva reprobada para comparacion de las electivas restantes del pensum
                                  ){

                                        System.out.println("\t\t\t iguales? "+orden_alt.get(x+0).equalsIgnoreCase(lista_electivas.get(ele+0))+"  Materia Record: "+orden_alt.get(x)+" - Electiva Real: "+lista_electivas.get(ele));
                                        System.out.println("COINCIDENCIA ENCONTADA");
                                        coincidencia=true;//materia encontrada en el record
                                        
                                    if(this.evaluacion(x, orden_alt)){//revisando si la materia fue aprobada o no
//System.out.println("ELECTIVA APROBADA");
                                            aprobada=true;//materia aprobada
                                         if(this.getEle_cur().size()>0){//validando que el LinkedList tenga al menos un registro
                                                for(int a=0; a<this.getEle_cur().size(); a=a+2){//revisando si la materia electiva aprobada no esta en la lista de las aprobadas. evitar que se repita. dos electivas iguales aprobadas repetidas Nivel 2

                                                                if(orden_alt.get(x+0).equalsIgnoreCase(this.getEle_cur().get(a)) //comparando si la electiva revisada aprobada este nuevamente en el record bajo la misma condicion o no
                                                                   //&& codigo.equalsIgnoreCase(this.getEle_cur().get(a+1))     //y que la materia del pensum electiva sea la misma que la que se uso para revisar esta electiva real. sea la misma asociada
                                                                   ){
                                                                        aprobada_usada=true;//materia aprobada ya fue usada

                                                                         if(codigo.equalsIgnoreCase(this.getEle_cur().get(a+1))){//que la materia del pensum electiva sea la misma que la que se uso para revisar esta electiva real. sea la misma asociada
//System.out.println("error , la misma materia electiva aprobada varias veces.");
//System.out.println("ELECTIVA REPETIDA EN LOS MAS BUSCADOS");
                                                                            existe_repetida=true;
                                                                            break;//rompiendo la iteracion electivas aprobadas Nivel 2
                                                                                }
                                                                }else{existe_repetida=false;}

                                                }//fin recorrido revision electivas aprobadas
                                                              if(existe_repetida==false && aprobada_usada==false){
                                                                  this.ele_cur.add(orden_alt.get(x+0));this.ele_cur.add(codigo);//añadiendo materia electiva aprobada a la lista de las cursadas xq no esta repetida y asociandolo a la electiva del pensum
                                                                  this.electivas_ordenadas(orden_alt.get(x+10), codigo,orden_alt.get(x+0) , orden_alt.get(x+5),"aprobada");//acumulando las materias por asociacion aprobadas para analisis posteriores
                                                              }
                                                
                                          }else{//en caso de que no tenga registro se agrega. se sigue con el ciclo de recorrido del record para revisar si esta la misma materia varias veces aprobada que no deberia pasar
                                                            this.ele_cur.add(orden_alt.get(x+0));//añadiendo codigo materia electiva aprobada a la lista de las cursadas
                                                            this.ele_cur.add(codigo);//añadiendo codigo electiva pensum asociada electiva real
                                                            //ejm real: EME31173 asociada a ELETEC81, electiva del pensum que la solicito primero
                                                            this.electivas_ordenadas(orden_alt.get(x+10), codigo,orden_alt.get(x+0) , orden_alt.get(x+5),"aprobada");//acumulando las materias por asociacion aprobadas por primera vez para analisis posteriores

                                               }

                                    }else{//si no esta aprobada,
//System.out.println("ELECTIVA REPROBADA. ALMACENANDO PARA EVITAR REVISARLA OTRA VEZ");
                                           this.getEle_repro().add(orden_alt.get(x+10));//0_guardando semestre materia electiva reprobada ya revisada
                                           this.getEle_repro().add(orden_alt.get(x+0));//1_guardando codigo materia electiva reprobada ya revisada
                                           this.getEle_repro().add(codigo);//2_guardando codigo materia electiva generica reprobada
                                           this.getEle_repro().add(orden_alt.get(x+5));//3_guardando el periodo donde la materia se encontro reprobada
                                           this.electivas_ordenadas(orden_alt.get(x+10), codigo,orden_alt.get(x+0) , orden_alt.get(x+5),"reprobada");//acumulando las materias por asociacion reprobadas para analisis posteriores
                                    }//fin revision si esta aprobada o no
                                    
                                    
                                }//fin analisis comparacion record-electivas reales



                                
                            }//fin recorrido materias del record del estudiante



                    }//fin analisis de si la materia electiva real que estoy usando para revisar no la este repitiendo mientras analizo el mismo semestre
                

            }//fin identificando tipo electiva

//if(coincidencia==true)break electivas_sublista;//rompiendo iteracion Nivel 0 apenas coincidan electiva del record con alguna de la lista de electivas
        }//fin recorrido lista electivas reales

System.out.println("COMBINACION_analisis: existe_repetida: "+existe_repetida+" coincidencia: "+coincidencia+" aprobada: "+aprobada+" aprobada_usada"+aprobada_usada);
 //-----SEGUNDA FASE DECISION
if(existe_repetida){this.autorizar_materia(codigo, electiva, "P",String.valueOf(semestre)); System.out.println("Autorizando materia en Condicion de P=REPITENCIA.\n");}
if(coincidencia==true && aprobada==false){this.autorizar_materia(codigo, electiva,"P", String.valueOf(semestre)); System.out.println("Autorizando materia en Condicion de P=REPITENCIA.\n");}
if(coincidencia==false || (existe_repetida==false && aprobada_usada==true) ){this.autorizar_materia(codigo, electiva, "N", String.valueOf(semestre)); System.out.println("Autorizando materia en Condicion de N=NORMAL.\n");}

        
    }//fin verificacion si es electiva


return es_electiva;
}

/**Este metodo es para contar cuantas electivas genericas del mismo semestre y tipo hay aprobadas y por ende asociadas */
public int contador_genericas_aprobadas(LinkedList<String> asociaciones, int semestre, String generica){
    int cantidad=0;

    for(int i=0; i<asociaciones.size(); i = i + 5){//recorriendo asociaciones
        if(asociaciones.get(i).equalsIgnoreCase(String.valueOf(semestre) ) && //que sean del mismo semestre
           asociaciones.get(i+1).substring(0,6).equalsIgnoreCase(generica.substring(0,6)) && //que sean del mismo tipo de electiva
           (asociaciones.get(i+4).equalsIgnoreCase("aprobada") || asociaciones.get(i+4).equalsIgnoreCase("reprobada") )//esten aprobadas o reprobadas
           ){//si tiene la misma composicion, semestre y tipo de electiva

            cantidad = cantidad + 1;//contando la cantidad de veces que eso existe. misma electiva generica del mismo semestre existente asociadas

        }


    }//fin recorrido asociaciones

System.out.println("electivas del semestre: "+semestre+" tipo: "+generica.substring(0, 6)+" cantidad: "+cantidad+" asociadas existentes");
    
    return cantidad;



}




/**Este metodo se encarga de hacer las consideraciones de las materias electivas donde se puede saber si la tiene aprobada o
 tiene que verla y poderle ofertar todas las electivas que ocuparan la casilla que le corresponde dentro del pensum. Para ello
 verifica materia a materia del pensum hasta que encuentra una electiva y es donde inicia las busqueda en el record. Las Materias
 electivas no poseen prelaciones. Si es una electiva se regresa un true sino no*/
public boolean materias_electivas_plural(String codigo, String electiva, String cant_x_semestre,int semestre, LinkedList<String> orden_alt, LinkedList<String> lista_electivas){
                //this.getElectivas().add(pensum.getString("codigo"));//0
  //              this.getElectivas().add(pensum.getString("asignatura"));//1
    //            this.getElectivas().add(String.valueOf(pensum.getInt("uc")));//2
      //          this.getElectivas().add(pensum.getString("tipo"));//3
boolean es_electiva=false;
boolean se_uso=false;
boolean coincidencia=false;
boolean existe_repetida=false;
boolean aprobada=false;
boolean aprobada_usada=false;
boolean siguiente=false;
//Cantidad de electivas por semestre segun el pensum
int cesp=Integer.valueOf( cant_x_semestre.substring(cant_x_semestre.length()-1) );

System.out.println("***PENSUM: cantidad "+electiva+" = "+cesp);//getElectxsem



//this.getNo_preladas().get(g), this.getNo_preladas().get(g+2)

    if(electiva.startsWith("ELECTIVA")){//verificando si la materia pensum sugiere electiva una electiva
        es_electiva=true;//la materia si es una electiva
//System.out.println("SI ES ELECTIVA --->"+electiva);
 electivas_sublista:   for(int ele=0; ele<lista_electivas.size();ele=ele+4){//recorriendo las electivas reales Nivel 0
            if(electiva.equalsIgnoreCase(lista_electivas.get(ele+3))){//identificando un solo tipo: electiva tecnica o no tecnica
//System.out.println("TIPO: "+lista_electivas.get(ele+3) );
                          //---
                          if(this.getUsadas().size()>0 && !this.getUsadas().getLast().equals(String.valueOf(semestre))){this.getUsadas().clear();System.out.println("Limpiando LinkedList");}//revisando que la lista que uso como usadas todas las materias son del mismo semestre sino se limpia el LinkedList por cada semestre
                          //---
                          for(int u=0;u<this.getUsadas().size();u=u+2){//recorriendo las electivas reales usadas para ver si la electiva que se esta usando para revisar no se este usando otra vez ya que por cada electiva del pensum simpre la revision se empieza desde cero
                              if(this.getUsadas().get(u).equalsIgnoreCase(lista_electivas.get(ele))){se_uso=true;}else{se_uso=false;}
                          }
//System.out.println("se uso? "+se_uso) ;
                          //---
//System.out.println("AGREGANDO ELECTIVA EN LA LISTA DE LAS USADAS");
                          this.getUsadas().add(lista_electivas.get(ele));//agregando electiva actual de la lista como usada ya que se usa para revisar.
                          this.getUsadas().add(String.valueOf(semestre));//agregando el semestre donde se esta haciendo la revision
                          //--

              /*            pruebas: for(int i=0; i<this.getUsadas().size(); i=i+2){
                                    System.out.println("REVISANDO ELECTIVAS: usadas: "+this.getUsadas().get(i)+" semestre: "+this.getUsadas().get(i+1));
                                    }*/


                    if(se_uso == false){ //no esta repetida. que no se esta usando la misma electiva real otra vez para realizar revision
//System.out.println("ELECTIVA DE LA LISTA NO USADA");
                            for(int x=0; x<orden_alt.size(); x = x+11){//recorriendo el record del estudiante para realizar la revision de las electivas Nivel 1
//System.out.println("Recorriendo registro orden semestre: "+orden_alt.get(x+10)+"  Record: "+orden_alt.get(x)+" - electiva actual: "+lista_electivas.get(ele));

                                if( orden_alt.get(x+10).equalsIgnoreCase(String.valueOf(semestre)) && //sublista por semestre. se revisa solo las materias que la electiva del pensum sugiera
                                    orden_alt.get(x+0).equalsIgnoreCase(lista_electivas.get(ele+0)) &&  //comparando codigo del record con el de la electiva real
                                    this.electiva_reprobada(orden_alt.get(x+0), orden_alt.get(x+10), orden_alt.get(x+5),codigo)==false //verificando que no se use la misma electiva reprobada incluyendo el periodo academico para comparacion de las electivas restantes del pensum
                                  ){

                                        System.out.println("\t\t\t iguales? "+orden_alt.get(x+0).equalsIgnoreCase(lista_electivas.get(ele+0))+"  Materia Record: "+orden_alt.get(x)+" - Electiva Real: "+lista_electivas.get(ele)+" -periodo"+orden_alt.get(x+5));
                                        System.out.println("COINCIDENCIA ENCONTRADA");
                                        coincidencia=true;//materia encontrada en el record

                                    if(this.evaluacion(x, orden_alt)){//revisando si la materia fue aprobada o no
                                            //System.out.println("ELECTIVA APROBADA");
                                            aprobada=true;//materia aprobada
              //                              cesp = cesp - 1; //esto sucede cuando encuentra una electiva y esta aprobada


                                         if(this.getElect_orden().size()>0){//validando que el LinkedList tenga al menos un registro
                                            //RECORRIENDO LISTA DE LOS MAS BUSCADOS
                                              System.out.println("  ->Revisando irregularidad\n");
                                             for(int a=0; a<this.getElect_orden().size(); a=a+5){//revisando si la materia electiva aprobada no esta en la lista de las(mas buscadas). evitar que se repita. dos electivas iguales aprobadas repetidas. Nivel 2

                                                  //  System.out.println("codigos iguales -original: "+orden_alt.get(x+0)+"  elec: "+this.getElect_orden().get(a+2)+" iguales?: "+orden_alt.get(x+0).equalsIgnoreCase(this.getElect_orden().get(a+2))+"\n"
                                                    //    +"periodos iguales: original: "+orden_alt.get(x+5)+" elec:"+this.getElect_orden().get(a+3)+" iguales? "+orden_alt.get(x+5).equalsIgnoreCase(this.getElect_orden().get(a+3))
                                                    //    );

                                                            if(
                                                                     orden_alt.get(x+0).equalsIgnoreCase(this.getElect_orden().get(a+2))//codigo electiva record sea igual a electiva usadas guardadas
                                                                     && this.getElect_orden().get(a+4).equalsIgnoreCase("aprobada")
                                                                 ||(
                                                                    codigo.equalsIgnoreCase(this.getElect_orden().get(a+1))//si codigos genericos son iguales en asociacion
                                                                    && orden_alt.get(x+0).equalsIgnoreCase(this.getElect_orden().get(a+2))//y los reales tambien en misma asociacion
                                                                    && this.getElect_orden().get(a+4).equalsIgnoreCase("aprobada") //y ademas esta aprobada, no debe pasar porque se esta analizando la misma asociacion en aprobadas. por lo tanto se ignora y se procede a la siguiente electiva la cual le corresponde esa aprobada    
                                                                   )
                                                                 ||(
                                                                    codigo.equalsIgnoreCase(this.getElect_orden().get(a+1))//codigo generico pensum igual a codigo generico en electivas usadas
                                                                    && orden_alt.get(x+5).equalsIgnoreCase(this.getElect_orden().get(a+3))//periodo electiva record igual a periodo electiva usadas
                                                                    && cesp <=0 //en cuando las otras dos condiciones se cumplen y ademas sea la ultima electiva en revision
                                                                  )
                                                            ){

                                                                //aprobada_usada=true;//materia se encuentra aprobada en analisis actual y esta repetida. cuando se compara codigo real pensum con real electivas
                                                                System.out.println("Se da la condicion:");
                                                                System.out.println("CAUSA: "+this.getElect_orden().get(a+1)+"-"+this.getElect_orden().get(a+2)+"-"+this.getElect_orden().get(a+3));
                                                                System.out.println("electiva record vs guardadas: iguales? "+orden_alt.get(x+0).equalsIgnoreCase(this.getElect_orden().get(a+2)));
                                                                System.out.println("codigo generico vs generico guardadas: iguales? "+codigo.equalsIgnoreCase(this.getElect_orden().get(a+1)));
                                                                System.out.println("real paso? "+aprobada+" guardada paso? "+this.getElect_orden().get(a+4));
                                                                System.out.println("periodo record vs periodo guardadas: iguales? "+orden_alt.get(x+5).equalsIgnoreCase(this.getElect_orden().get(a+3)));
                                                                System.out.println("ELECTIVA EN REVISION DEL MISMO TIPO: "+cesp);
                                                                System.out.println("\n");
                                                            }else{
                                                                System.out.println("SIN novedad: "+this.getElect_orden().get(a+1)+"-"+this.getElect_orden().get(a+2)+"-"+this.getElect_orden().get(a+3));

                                                            }


                                                            //Se supone que para estas comprobaciones la materia del record en analisis esta aprobada.
                                                            //comprobacion 1
                                                            if(orden_alt.get(x+0).equalsIgnoreCase(this.getElect_orden().get(a+2))//codigo electiva record sea igual a electiva usadas guardadas
                                                               && this.getElect_orden().get(a+4).equalsIgnoreCase("aprobada")//y si existe en las guardadas que al menos este aprobada. para saber que esa materia ya fue asociada con otra
                                                               ){aprobada_usada=true;
                                                                    System.out.println("CODIGO ELECTIVA RECORD IGUAL A USADAS Y ADEMAS ESTA APROBADA");
                                                                }else{aprobada_usada=false;}//materia aprobada ya fue usada

                                                            //comprobacion 2
                                                            if(codigo.equalsIgnoreCase(this.getElect_orden().get(a+1))//si codigos genericos son iguales en asociacion
                                                              && orden_alt.get(x+0).equalsIgnoreCase(this.getElect_orden().get(a+2))//y los reales tambien en misma asociacion
                                                              && this.getElect_orden().get(a+4).equalsIgnoreCase("aprobada") //y ademas esta aprobada, no debe pasar porque se esta analizando la misma asociacion en aprobadas. por lo tanto se ignora y se procede a la siguiente electiva la cual le corresponde esa aprobada
                                                              ){
                                                                System.out.println("CODIGO IGUALES EN ASOCIACION, LAS REALES TAMBIEN EN MISMA ASOCIACION Y ADEMAS ESTA APROBADA Y NO DEBE SUCEDER, deberia pasar a la siguiente electiva");
                                                                aprobada_usada=true;
                                                                siguiente=true;
                                                                break;

                                                            }//materia aprobada ya fue usada y se rompe de inmediato la revision con la electiva generica para estudiar la siguiente

                                                            //comprobacion 3
                                                            if(
                                                              codigo.equalsIgnoreCase(this.getElect_orden().get(a+1))//codigo generico pensum igual a codigo generico en electivas usadas
                                                              && orden_alt.get(x+5).equalsIgnoreCase(this.getElect_orden().get(a+3))//periodo electiva record igual a periodo electiva usadas                                                             
                                                              ){                                                       
                                                                    int cga=this.contador_genericas_aprobadas(this.getElect_orden(), semestre, codigo);
                                                                        System.out.println("REVISION GENERICAS APROBADAS ASOCIADAS DEL MISMO TIPO "+cga);
                                                                        
                                                                    if(cga > cesp){ //es cuando la cantidad de genericas asociadas del mismo semestre tipo, es igual a la cantidad segun pensum y hay una electiva aprobada demas
                                                                        System.out.println("ELECTIVA EN EL MISMO PERIODO ACADEMICO DEMAS. SUPERA LA CANTIDAD QUE INDICA EL PENSUM");
                                                                        existe_repetida=true;
                                                                    }else{
                                                                        System.out.println("AVANZANDO A LA SIGUIENTE ELECTIVA PENSUM");
                                                                        siguiente=true;
                                                                        aprobada_usada=true;
                                                                        break;
                                                                    }


                                                                break;
                                                            }else{
                                                                existe_repetida=false;
                                                            }




                                                            //-----------------

                                             }//fin recorrido revision electivas aprobadas. FIN LISTA MAS BUSCADOS

                                                                //decision si la materia esta aprobada y no incumple, se hace la asociacion
                                                              if((existe_repetida==false && aprobada_usada==false)){//la materia esta aprobada pero no esta en la lista de los mas buscados. se guarda esa asociacion y se avanza a la siguiente electiva a revisar
                                                                  System.out.println("ASOCIANDO: "+orden_alt.get(x+0)+" - "+codigo+" aprobada\n");
                                                                  this.ele_cur.add(orden_alt.get(x+0));this.ele_cur.add(codigo);//añadiendo materia electiva aprobada a la lista de las cursadas xq no esta repetida y asociandolo a la electiva del pensum
                                                                  this.electivas_ordenadas(orden_alt.get(x+10), codigo,orden_alt.get(x+0) , orden_alt.get(x+5),"aprobada");//acumulando las materias por asociacion aprobadas para analisis posteriores
                                                              }

                                          }else{//en caso de que no tenga registro se agrega. se sigue con el ciclo de recorrido del record para revisar si esta la misma materia varias veces aprobada que no deberia pasar
                                                            System.out.println(":ASOCIANDO: "+orden_alt.get(x+0)+" - "+codigo+" aprobada\n");
                                                            this.ele_cur.add(orden_alt.get(x+0));//añadiendo codigo materia electiva aprobada a la lista de las cursadas
                                                            this.ele_cur.add(codigo);//añadiendo codigo electiva pensum asociada electiva real
                                                            //ejm real: EME31173 asociada a ELETEC81, electiva del pensum que la solicito primero
                                                            this.electivas_ordenadas(orden_alt.get(x+10), codigo,orden_alt.get(x+0) , orden_alt.get(x+5),"aprobada");//acumulando las materias por asociacion aprobadas por primera vez para analisis posteriores

                                               }
                                                 System.out.println("  -->>fin revisando irregularidad\n");
                                    }else{//si no esta aprobada,
//System.out.println("ELECTIVA REPROBADA. ALMACENANDO PARA EVITAR REVISARLA OTRA VEZ");
                                           aprobada=false;
                                           this.getEle_repro().add(orden_alt.get(x+10));//0_guardando semestre materia electiva reprobada ya revisada
                                           this.getEle_repro().add(orden_alt.get(x+0));//1_guardando codigo materia electiva reprobada ya revisada
                                           this.getEle_repro().add(codigo);//2_guardando codigo materia electiva generica reprobada
                                           this.getEle_repro().add(orden_alt.get(x+5));//3_guardando el periodo donde la materia se encontro reprobada
                                           this.electivas_ordenadas(orden_alt.get(x+10), codigo,orden_alt.get(x+0) , orden_alt.get(x+5),"reprobada");//acumulando las materias por asociacion reprobadas para analisis posteriores
                                    }//fin revision si esta aprobada o no


                                }//fin analisis comparacion record-electivas reales




                            }//fin recorrido materias del record del estudiante



                    }//fin analisis de si la materia electiva real que estoy usando para revisar no la este repitiendo mientras analizo el mismo semestre


            }//fin identificando tipo electiva

//if(coincidencia==true)break electivas_sublista;//rompiendo iteracion Nivel 0 apenas coincidan electiva del record con alguna de la lista de electivas
        }//fin recorrido lista electivas reales

System.out.println("COMBINACION_analisis: existe_repetida: "+existe_repetida+" coincidencia: "+coincidencia+" aprobada: "+aprobada+" aprobada_usada: "+aprobada_usada);
 //-----SEGUNDA FASE DECISION
 //si existe repetida la materia generica junto al mismo periodo, se avanzaria a la siguiente materia electiva generica del pensum. apro y repro
 //si existe repetida pero los periodos son distintos se pueden seguir haciendo asociaciones con la misma electiva generica. repro
 //si existe la electiva real en el record y esta aprobada, la electiva generica esta aprobada y se avanza.
 //si existe la electiva real en el record y esta reprobada, se sigue haciendo asociaciones con la generica siempre que los periodos sean distintos hasta que se termine de revizar todo. si siempre estvo repro se oferta la misma en repitencia, si esta apro en algun momento se avanza a la siguiente
 //si la electiva no esta en el record segun el semestre, significa que se comparo todas las electivas reales y no hubo coincidencia, esa materia electiva generica del pensum se oferta como normal
 //
if(siguiente){}else{//solo si siguiente es falso se hace estos analisis pero si es verdadero se ignora y se asume que la materia bajo analisis no se ve afectada y se sigue a la proxima electiva

    if(existe_repetida){this.autorizar_materia(codigo, electiva, "P",String.valueOf(semestre)); System.out.println("Autorizando materia en Condicion de P=REPITENCIA.\n");}
    if(coincidencia==true && aprobada==false){this.autorizar_materia(codigo, electiva,"P", String.valueOf(semestre)); System.out.println("Autorizando materia en Condicion de P=REPITENCIA.\n");}
    if(coincidencia==false || (existe_repetida==false && aprobada_usada==true && aprobada==true) ){this.autorizar_materia(codigo, electiva, "N", String.valueOf(semestre)); System.out.println("Autorizando materia en Condicion de N=NORMAL.\n");}
    
    }
if((coincidencia==true && aprobada==true && existe_repetida==false && aprobada_usada==false) || siguiente)System.out.println("MATERIA ELECTIVA APROBADA: "+codigo+"\n");

    }//fin verificacion si es electiva


return es_electiva;
}







/**Este metodo se encarga de verificar que una materia electiva reprobada de un semestre
 no se use nuevamente para la revision en el mismo semestre de otras electivas y asi evitar 
 autorizar varias electivas del pensum con la condicion de una misma materia reprobada.
  */
public boolean electiva_reprobada(String cod_electiva, String semestre, String periodo, String generica){
    boolean usada=false;
    //this.getEle_repro();
//System.out.println("revisando electivas reprodads. bajo analisis: codigo: "+cod_electiva+" semestre: "+semestre );
    if(this.getEle_repro().isEmpty()){usada=false;//si el LinkedList esta vacio se supone que nunca se ha usado la electiva en la revision
    }else{
        for(int eer=0; eer<this.getEle_repro().size(); eer=eer+4){//recorriendo electiva reprobadas
//System.out.println("---------Comparando: codigos: "+cod_electiva+" - "+this.getEle_repro().get(eer+1)+" semestres: "+semestre+" - "+this.getEle_repro().get(eer+0));
            if(cod_electiva.equalsIgnoreCase(this.getEle_repro().get(eer+1)) && //si los codigos son iguales y
               semestre.equalsIgnoreCase(this.getEle_repro().get(eer+0))   &&//el semestre es el mismo
               periodo.equalsIgnoreCase(this.getEle_repro().get(eer+3))//que los periodos sean iguales
               ||(
                 generica.equalsIgnoreCase(this.getEle_repro().get(eer+2))//o si el codigo generico es igual al generico actual de la asociacion guardada
                 && periodo.equalsIgnoreCase(this.getEle_repro().get(eer+3))//y se este analizando el mismo periodo con la misma generica, cosa que no deberia suceder. le corresponde a la siguiente electiva
               )){
       //         System.out.println("Electiva reprobada ya usada para revision. Ignorando para revision actual");
                usada=true; //esta electiva reprobada ya fue usada en la revision
                break;
            }else{usada=false;}

        }//fin recorrido electivas reprobadas
    }
return usada;
}


/**MEtodo que se emplea para simplificar la forma ordenada de guardar las asociaciones que ocurran por periodo academico
 aprovechando que las materias originales se encuentran ordenadas por la misma preferencia.*/
public void electivas_ordenadas(String semestre, String generica, String real, String periodo, String paso){

    this.getElect_orden().add(semestre);
    this.getElect_orden().add(generica);
    this.getElect_orden().add(real);
    this.getElect_orden().add(periodo);
    this.getElect_orden().add(paso);//si o no

}




/**Este metodo permite llenar la lista de materias autorizadas para que el alumno pueda inscribirlas */
public void autorizar_materia(String codigo, String nombre, String condicion, String semestre){
               this.getLista_autorizada().add(codigo);//codigo
               this.getLista_autorizada().add(nombre);//nombre
               this.getLista_autorizada().add(condicion);//condicion
               this.getLista_autorizada().add(semestre);//semestre
    }


private boolean analisis_pasantias(int z, LinkedList<String> ra){
    boolean aprobo = false;    
    if( ((Integer.valueOf(ra.get(z+2))>=15 || //que halla aprobado la materia
                   (ra.get(z+3)!=null && Integer.valueOf(ra.get(z+3))>=15)) && // o que si reparo que la halla aprobado
                    ( ra.get(z+4)!="POR INASISTENCIA" && (ra.get(z+4)!="REPROBÓ-25% DE INASISTENCIA" || ra.get(z+4)!="REPROBO-50% DE INASISTENCIA") ) && //y que no halla perdido la materia por inasistencia
                    //this.getOrden().get(z+4)!="REPROBO-25% DE INASISTENCIA" &&
                    ra.get(z+4)!="ERROR") || //y que la condicion de la materia este dentro de las pautadas
                    ra.get(z+4).equalsIgnoreCase("APROBÓ") ||//o que la condicion de la materia este en aprobado
                    ra.get(z+4).equalsIgnoreCase("EXONERADO") ||//o que la condicion de la materia este en aprobado
                    ra.get(z+4).equalsIgnoreCase("EQUIVALENCIA") //o que la tenga aprobada por equivalencia...no se toma la nota
                  ){
                    System.out.println("paso pasantia");
                    aprobo=true;

               }else{
                    System.out.println("reprobado pasantia");
                    aprobo=false;

               }
return aprobo;
}

/**Metodo que se encarga de determinar si una materia esta aprobada o no, por lo que
 devuelve un objeto tipo boolean para confirmarlo.*/
public boolean evaluacion(int z, LinkedList<String> ra){
   boolean aprobo=false;

    /*if( ((Integer.valueOf(this.getOrden().get(z+2))>=10 || //que halla aprobado la materia
                   (this.getOrden().get(z+3)!=null && Integer.valueOf(this.getOrden().get(z+3))>=10)) && // o que si reparo que la halla aprobado
                    (this.getOrden().get(z+4)!="POR INASISTENCIA" || this.getOrden().get(z+4)!="REPROBO-25% DE INASISTENCIA") && //y que no halla perdido la materia por inasistencia
                    //this.getOrden().get(z+4)!="REPROBO-25% DE INASISTENCIA" &&
                    this.getOrden().get(z+4)!="ERROR") || //y que la condicion de la materia este dentro de las pautadas
                    this.getOrden().get(z+4).equalsIgnoreCase("APROBADO") ||//o que la condicion de la materia este en aprobado
                    this.getOrden().get(z+4).equalsIgnoreCase("EQUIVALENCIA") //o que la tenga aprobada por equivalencia...no se toma la nota
                  ){
                    System.out.println("paso");
                    aprobo=true;

               }else{
                    System.out.println("reprobado");
                    aprobo=false;

               }
   */
//System.out.println("verificar condicion: "+ra.get(z+4)+" distinto: "+(ra.get(z+4)!="POR INASISTENCIA")+"  distinto_2: "+(ra.get(z+4)!="REPROBO-25% DE INASISTENCIA"));
//System.out.println("RESULTADO DECISION "+( ra.get(z+4)!="POR INASISTENCIA" && ra.get(z+4)!="REPROBO-25% DE INASISTENCIA" ));
//verificar condicion: POR INASISTENCIA distinto: false  distinto_2: true
//RESULTADO DECISION false
   
   //VERIFICANDO SI LA MATERIA ES PASANTIA/TRABAJO ESPECIA DE GRADO o UNA MATERIA NORMAL
   //Valores guardados en el LinkedList. Posiciones; 0 = codigo de la materia 1 = nombre de la materia 2 = definitiva de la materia 3 = nota de reparacion 4 = condicion de la materia 5 = periodo academico de la materia 6 = definitiva reparacion de la materia 7 = porcentaje de inasistencia de la materia 8 = codigo especialidad de la materia 9 = nota de laboratorio de la materia 10 = semestre de la materia
    if(ra.get(z+1)!=null){//los calculos o analisis se hacen solo si la materia no tiene problemas con el nombre.
        
        if(ra.get(z+1).trim().contains("PASANTÍA") || ra.get(z+1).trim().contains("PASANTIA") || ra.get(z+1).trim().contains("TRABAJO ESPECIAL DE GRADO") || ra.get(z+1).trim().contains("PRÁCTICA EDUCATIVA") || ra.get(z+1).trim().contains("PRACTICA EDUCATIVA")){//analizando solo las materias de pasantias o teg...
            aprobo = this.analisis_pasantias(z, ra);       
        }else{//es una materia normal de analisis
            if( ((Integer.valueOf(ra.get(z+2))>=10 || //que halla aprobado la materia
                        (ra.get(z+3)!=null && Integer.valueOf(ra.get(z+3))>=10)) && // o que si reparo que la halla aprobado
                         ( ra.get(z+4)!="POR INASISTENCIA" && (ra.get(z+4)!="REPROBÓ-25% DE INASISTENCIA" || ra.get(z+4)!="REPROBÓ-50% DE INASISTENCIA") ) && //y que no halla perdido la materia por inasistencia
                         //this.getOrden().get(z+4)!="REPROBO-25% DE INASISTENCIA" &&
                         ra.get(z+4)!="ERROR") || //y que la condicion de la materia este dentro de las pautadas
                         ra.get(z+4).equalsIgnoreCase("APROBÓ") ||//o que la condicion de la materia este en aprobado
                         ra.get(z+4).equalsIgnoreCase("EXONERADO") ||//o que la condicion de la materia este en aprobado
                         ra.get(z+4).equalsIgnoreCase("EQUIVALENCIA") //o que la tenga aprobada por equivalencia...no se toma la nota
                       ){
                         System.out.println("paso");
                         aprobo=true;

                    }else{
                         System.out.println("reprobado");
                         aprobo=false;

                    }   
        }
        
    }else{//si el nombre es null
        //System.out.println("materia sin nombre porque no coincide con el pensum");        
        aprobo = false;    
    }

   


   return aprobo;
}





/**Este metodo determina a partir de cuantas materias debe el estudiante, si este
 puede ir hacer las pasantias o no, todo de acuerdo a la carrera*/
public boolean requisitos_pasantia(LinkedList<String> autorizadas, String materia_analizar, String carrera ){
 boolean autorizar=false;
 System.out.println("Analizando materia del ultimo semestre");
/*INGENIERIA MECANICA
LIC. EDUCACION INTEGRAL
LIC. CONTADURIA PUBLICA
LIC. ECONOMIA SOCIAL
TSU EN TURISMO
INGENIERIA AERONAUTICA
INGENIERIA CIVIL
INGENIERIA ELECTRICA
INGENIERIA ELECTRONICA
INGENIERIA DE SISTEMAS
INGENIERIA DE TELECOMUNICACIONES
TSU EN ENFERMERIA
LIC. EN ADMINISTRACION Y GESTION MUNICIPAL
LIC. EN ENFERMERIA*/

    if(materia_analizar.contains("PASANTIA") || materia_analizar.contains("TRABAJO ESPECIAL DE GRADO")){//verificando si la materia a inscribir es una del ultimo semestre
        int faltantes=this.requisitos_pasantia_conteo(autorizadas);//cantidad de materias que aun faltan

        if(faltantes>0 ){//si se debe mas de las permitidas entonce no puede hacer las materias finales
                        autorizar=false;
                        ima.mensaje_informacion("DISCULPE, PERO LA MATERIA ("+materia_analizar+") NO SE PUEDE INSCRIBIR, PORQUE NO\n"
                                  + "HA APROBADO TODAS LAS MATERIAS DE LOS SEMESTRES ANTERIORES\n"
                                  + "O DEBE ALGUN REQUISITO PREVIO.","MATERIAS FINALES","liberar", "png");
                        }else{autorizar=true;}





           /* if(carrera.equalsIgnoreCase("TSU EN TURISMO") || carrera.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")){//consideracion especial para dos carreras que solo ven una materia final
                     if( (autorizadas.size()/4)>1 ){System.out.println("Consideracion para "+carrera);
                                autorizar=false;
                                 ima.mensaje_informacion("DISCULPE, PERO LA MATERIA ("+materia_analizar+") NO SE PUEDE INSCRIBIR, PORQUE NO\n"
                                                          + "HA APROBADO TODAS LAS MATERIAS DE LOS SEMESTRES ANTERIORES\n"
                                                          + "O DEBE ALGUN REQUISITO PREVIO.","MATERIAS FINALES","liberar", "png");
                        }else{autorizar=true;}

            }else{//demas carrera

                    if( (autorizadas.size()/4)>cantidad_permitidas ){
                        autorizar=false;
                        ima.mensaje_informacion("DISCULPE, PERO LA MATERIA ("+materia_analizar+") NO SE PUEDE INSCRIBIR, PORQUE NO\n"
                                  + "HA APROBADO TODAS LAS MATERIAS DE LOS SEMESTRES ANTERIORES\n"
                                  + "O DEBE ALGUN REQUISITO PREVIO.","MATERIAS FINALES","liberar", "png");
                        }else{autorizar=true;}




                }//fin analisis si son las demas carreras
*/
    }else{autorizar=true;}//si no son materias finales, autorizar materia desde este filtro

return autorizar;
}

public int requisitos_pasantia_conteo(LinkedList<String> autorizadas){

 int conteo =0;
 for(int i=0; i<autorizadas.size(); i=i+4 ){//recorriendo las materias autorizadas para saber la cantidad exacta. se obvia las materias finales "PASANTIA, TRABAJO DE GRADO" y "TALLER SERVCIO COMUNITARIO Y PROYECTO SERVICIO COMUNITARIO" PARA EL CONTEO

     if(autorizadas.get(i+1).equalsIgnoreCase("PASANTIA") || //si son algunas de estas materias que estan dentro de las
        autorizadas.get(i+1).equalsIgnoreCase("TRABAJO ESPECIAL DE GRADO") || //autorizadas entonces se ignora para el conteo
        autorizadas.get(i+1).equalsIgnoreCase("PROYECTO DE SERVICIO COMUNITARIO")  || //y asi saber realmente cuales debe
        autorizadas.get(i+1).equalsIgnoreCase("TALLER DE INDUCCIÓN DEL SERVICIO COMUNITARIO")
             ){

     }else{System.out.println("Sumando 1_de las que debe");
        conteo=conteo+1;//sumando uno cada vez que sea una materia distinta a las anteriores
     }


 }//fin recorrido autorizadas
System.out.println("TOTAL DE MATERIA QUE DEBE = "+conteo);
 return conteo;
}












}//fin clase materias

