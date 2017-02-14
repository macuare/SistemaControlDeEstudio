/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AN
 */
public class modelo_notas_alumnos {

    private String turno=null, carrera=null, estudiante=null;
    private DefaultTableModel dtm ;
    private String base_datos,usuario;
    private Date horarios;
    private LinkedList <Object> anterior = new LinkedList<Object>();
    private LinkedList <String> trazador = new LinkedList<>();
    private LinkedList <String> historiador = new LinkedList<>();

    private registros_bd rbd;
    private registro_pdf rpdf;
    
    private imagenes ima;



    public modelo_notas_alumnos() {
        rbd = new registros_bd();
        ima = new imagenes();
        rpdf = new registro_pdf();
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public String getBase_datos() {
        return base_datos;
    }

    public void setBase_datos(String base_datos) {
        this.base_datos = base_datos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LinkedList<Object> getAnterior() {
        return anterior;
    }

    public void setAnterior(LinkedList<Object> anterior) {
        this.anterior = anterior;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public LinkedList<String> getHistoriador() {
        return historiador;
    }

    public void setHistoriador(LinkedList<String> historiador) {
        this.historiador = historiador;
    }

    







public String especialidades(int codigo){
 String carrera=null;

        switch(codigo){
            case 1:
                carrera="INGENIERIA AERONAUTICA";
                //this.setTurno("DIURNO");
            break;

            case 2:
                carrera="INGENIERIA CIVIL";
                //this.setTurno("DIURNO");
            break;

            case 24:
                carrera="INGENIERIA CIVIL";
                //this.setTurno("NOCTURNO");
            break;


            case 3:
                carrera="INGENIERIA ELECTRICA";
                //this.setTurno("DIURNO");
            break;

            case 4:
                carrera="INGENIERIA ELECTRONICA";
                //this.setTurno("DIURNO");
            break;

            case 9:
                carrera="INGENIERIA DE SISTEMAS";
                //this.setTurno("NOCTURNO");
            break;

            case 8:
                carrera="INGENIERIA DE TELECOMUNICACIONES";
                //this.setTurno("DIURNO");
            break;

            case 28:
                carrera="TSU EN ENFERMERIA";
                //this.setTurno("DIURNO");
            break;

            case 53:
                carrera="LIC. ADMINISTRACION Y GESTION MUNICIPAL";
                //this.setTurno("DIURNO");
            break;

            case 52:
                carrera="LIC. ADMINISTRACION Y GESTION MUNICIPAL";
                //this.setTurno("NOCTURNO");
            break;

            case 42:
                carrera="LIC. ECONOMIA SOCIAL";
                //this.setTurno("DIURNO");
            break;

            case 43:
                carrera="LIC. ECONOMIA SOCIAL";
               // this.setTurno("NOCTURNO");
            break;

            default: carrera="NO SE HALLO CARRERA";

        }

return carrera;
}

public String campos_vacios(String texto){
   String devolver=null;
    if(texto==null || texto.isEmpty()){
            devolver="";
    }else{devolver=texto;}

return devolver;
}





public void busqueda_materias(Connection con, String cedula, JTable jtabla, JTextArea area, JLabel ubicacion){

    this.getAnterior().clear();
    area.setText("");

            boolean hallado = false;
            String tablas = null;

dtm = new DefaultTableModel(new Object [][] {},new String [] {"POSICION", "MODIFICAR", "SEMESTRE", "CODIGO", "CONDICION", "DEFINITIVA", "REPARACION", "DEFREPARACION", "INASISTENCIA", "SECCION", "PERIODO", "ESPECIALIDAD"    })
{
    Class[] types = new Class [] {
        java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
    };
    boolean[] canEdit = new boolean [] {
        false, true, true, true, true, true, true, true, true, true, true, true
    };

    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
};


           jtabla.setModel(dtm);
          

            Statement sentencia = null;
            ResultSet resultado = null;

        try {
        sentencia = con.createStatement();
            for (int i = 0; i <= 3; i++) {
                //recorriendo las tabla de la base de datos
                switch (i) {
                //menu de tablas de notas de alumnos
                    case 0:
                        tablas = "control_de_estudio.cem_notas_alumnos_todas_carreras_2010";
                        break;
                    case 1:
                        tablas = "control_de_estudio.cem_notas_alumnos_todas_carreras_2009";
                        break;
                    case 2:
                        tablas = "control_de_estudio.cem_notas_alumnos_todas_carreras_2007";
                        break;
                    default:
                        tablas = "NO HALLADO";
                        System.out.println("NO SE HALLO EN NINGUNO DE LOS CASOS");
                        ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "precaucion", "png");
                        
                        break;
                }
                System.out.println(i+" - "+tablas);


                this.setBase_datos(tablas);//guardando en una variable global la tabla de la base de datos

                   if(tablas.equalsIgnoreCase("NO HALLADO")){}else{//en caso de que no se halle nada del estudiante se evita una busqueda mas

                        resultado = sentencia.executeQuery("SELECT CEDULA FROM " + tablas + " WHERE CEDULA='" + cedula + "' GROUP BY CEDULA;");
                            if(resultado.next()) {
                                //en caso de que se encuentre el alumno se detiene la busqueda
                                System.out.println("hallado? "+resultado.getString("CEDULA"));

                                break;
                            }
                    }//fin no hallado estudiante

            } //fin recorrido tabla base de datos


            ubicacion.setText(tablas);//tabla dentro de la base de datos donde se encontro al estudiante
            sentencia.close();
            
            System.out.println("tabla "+ubicacion.getText());//verificando

            if(tablas.equalsIgnoreCase("NO HALLADO")){System.out.println("NO HALLADO");}else{//estos metodos se ejecutan siempre que se encuentra algun registro en la base de datos sino no hace falta

                    sentencia=con.createStatement();
                    //BUSCANDO EL ALUMNO  CON TODAS SUS MATERIAS
                    resultado = sentencia.executeQuery("SELECT * FROM " + tablas + " WHERE CEDULA='" + cedula + "';");


                    while (resultado.next()) {//recorriendo el registro
                        Object[] informacion={
                            resultado.getInt("POSICION"),//posicion
                            new Boolean(false),//habilitacion modificar
                            resultado.getInt("TERMINO"),//semestre
                            resultado.getString("CODMAT"),//codigo de la materia
                                                          //nombre de la materia
                            resultado.getString("CONDIC"),//condicion de la materia
                            resultado.getInt("NOTDEF"),//nota definitiva
                            resultado.getInt("NOTREP"),//nota de reparacion
                            resultado.getInt("DEFREP"),//definitiva reparacion
                            resultado.getInt("PORINA"),//porcentaje por inasistencia
                            resultado.getString("SECCION"),//seccion donde inscribio la materia
                            resultado.getString("PERACA"),//periodo academico en el que la inscribio
                            resultado.getString("CODESP") //codigo de la especialidad
                        };

                            this.getAnterior().add(resultado.getInt("POSICION"));//posicion 0
                            this.getAnterior().add(String.valueOf(resultado.getInt("TERMINO")));//semestre 1
                            this.getAnterior().add(resultado.getString("CODMAT"));//codigo de la materia 2
                                                          //nombre de la materia
                            this.getAnterior().add(resultado.getString("CONDIC"));//condicion de la materia 3
                            this.getAnterior().add(String.valueOf(resultado.getInt("NOTDEF")));//nota definitiva 4
                            this.getAnterior().add(String.valueOf(resultado.getInt("NOTREP")));//nota de reparacion 5
                            this.getAnterior().add(String.valueOf(resultado.getInt("DEFREP")));//definitiva reparacion 6
                            this.getAnterior().add(String.valueOf(resultado.getInt("PORINA")));//porcentaje por inasistencia 7
                            this.getAnterior().add(resultado.getString("SECCION"));//seccion donde inscribio la materia 8
                            this.getAnterior().add(resultado.getString("PERACA"));//periodo academico en el que la inscribio 9
                            this.getAnterior().add(resultado.getString("CODESP")); //codigo de la especialidad 10
                            this.getAnterior().add(this.campos_vacios(resultado.getString("OBSERVACIÓ"))); //observacion de la materia 11

                               

                            //if(resultado.getString("OBSERVACIÓ").isEmpty() || resultado.getString("OBSERVACIÓ")==""){
                            if(this.campos_vacios(resultado.getString("OBSERVACIÓ")).equalsIgnoreCase("")){
                                }else{area.append(resultado.getString("OBSERVACIÓ"));
                                                                                  area.append("\n.\n");
                           }//solo se escribe en el historial en caso de que este campo contenga algo que mostrar


                        dtm.addRow(informacion);//agregando la informacion al modelo de la tabla.

                        dtm.fireTableDataChanged();//notifica que hubo un cambio de datos



                    }//fin recorrido registro

                    sentencia.close();
                    resultado.close();
                    con.close();

            }//fin no hallado materias del estudiante....no se realiza este procedimiento para evitar errores


                } catch (SQLException ex) {
                    Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);

                    ima.mensaje_informacion("Error con la base de datos\n"+ex.getMessage(), "ERROR", "error", "png");

                }
System.out.println("anterior "+this.getAnterior().size());//mensaje



}




public void modificacion(Connection con,JTable tabla, int fila, String extra){
         Statement sentencia = null;
                System.out.println("Fila: "+fila);
                System.out.println("MODIFICANDO: posicion="+tabla.getValueAt(tabla.convertRowIndexToView(fila), tabla.getColumnModel().getColumnIndex("POSICION"))+" , "+tabla.getValueAt(tabla.convertRowIndexToView(fila), tabla.getColumnModel().getColumnIndex("SEMESTRE"))+" , "+tabla.getValueAt(tabla.convertRowIndexToView(fila), tabla.getColumnModel().getColumnIndex("CODIGO"))+" , "+tabla.getValueAt(tabla.convertRowIndexToView(fila),tabla.getColumnModel().getColumnIndex("CONDICION"))+" , "+tabla.getValueAt(tabla.convertRowIndexToView(fila), tabla.getColumnModel().getColumnIndex("DEFINITIVA")));
        try {

            sentencia = con.createStatement();
            sentencia.executeUpdate(
                                    "UPDATE "+this.getBase_datos()+
                                    " SET TERMINO="+tabla.getValueAt(tabla.convertRowIndexToView(fila),(tabla.getColumnModel().getColumnIndex("SEMESTRE")))+ //semestre
                                    ",  CODMAT='"+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("CODIGO"))).toString()+ //codigo
                                    "', CONDIC='"+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("CONDICION"))).toString()+ //condicion
                                    "', NOTDEF="+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("DEFINITIVA")))+ //definitiva
                                    ",  NOTREP="+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("REPARACION")))+ //reparacion
                                    ",  DEFREP="+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("DEFREPARACION")))+ //defreparacion
                                    ",  PORINA="+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("INASISTENCIA")))+ //inasistencia
                                    ",  SECCION='"+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("SECCION"))).toString()+ //seccion
                                    "', PERACA='"+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("PERIODO"))).toString()+ //periodo
                                    "', CODESP='"+tabla.getValueAt(tabla.convertRowIndexToView(fila), (tabla.getColumnModel().getColumnIndex("ESPECIALIDAD")))+ //especialidad
                                    "' , OBSERVACIÓ="+extra+ //observaciones
                                    " WHERE POSICION="+tabla.getValueAt(tabla.convertRowIndexToView(fila),(tabla.getColumnModel().getColumnIndex("POSICION")))+" ;");//POSICION


            sentencia.close();
            con.close();
                       

        } catch (SQLException ex) {

            Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
            ima.mensaje_informacion("ERROR ENVIANDO INFORMACION A LA BASE DE DATOS"+ex.getMessage(), "ERROR", "error", "png");
            
        }

}

/**Este metodo permite guardar las modificaciones y los valores antes de realizar las mismas, ademas el nombre del usuario
 que esta realizando la misma asi como la fecha y la hora del servidor, ademas se coloca una referencia para recoger lo que
 habia antes en las observaciones de la materia y anexarle la nueva modificacion que se realice para ellos se emplean los
 parametros que se fijan en el argumento*/
public String antes_despues(String usuario,String antes, String despues, String referencia){
     String comentario=null;
     String fecha="DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r')";
//CONCAT(DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r'),' andy modificando')


        comentario="CONCAT('"+referencia+"\n','MODIFICADOR POR: "+usuario+" - Dia: ',"+fecha+",'\nAntes: "+antes+"','\nDespues: "+despues+"')";


    return comentario;
}

public void tramites(JTable tabla, String usuario, LinkedList<String> pensum, String carrera, String cedula){
    String extras=null;
    String antes=null,despues=null;
    materias m = new materias();

System.out.println("TRAMITES");

             for(int i=0; i<tabla.getRowCount();i++){ //recorriendo las notas de la tabla
                System.out.println(i+" "+tabla.getModel().getValueAt(i, 1)+" "+tabla.getModel().getValueAt(i, 3));//verificando

                    if(tabla.getModel().getValueAt((i),tabla.getColumnModel().getColumnIndex("MODIFICAR")).toString().equalsIgnoreCase("true")){//solo se modificaran aquellos que sean verdaderos para modificar

                            //System.out.println("analisis: "+tabla.getModel().getValueAt(i, 0)+" , "+tabla.getModel().getValueAt(i, 1));
                            //System.out.println("LinkedList anterior: "+this.getAnterior().size());
                            //this.modificacion(cbd.getConexion(), na.getNotas(), i);//metodo para realizar la modificacion

                            for(int r=0; r<this.getAnterior().size(); r=r+12){//se recorre el LinkedList que contiene la informacion sin modificar
                                
                                       //System.out.println("revisando: "+this.getAnterior().get(r)+" - "+tabla.getModel().getValueAt(i, 0));
                                  //     System.out.println("posicion: "+tabla.getModel().getValueAt(i, 0)+" anterior: "+this.getAnterior().get(r) );

                                       if(tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("POSICION")).equals(this.getAnterior().get(r))){//si las posiciones coinciden
                                        System.out.println("se hallo coincidencia: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("POSICION")));
                                      


                                            /*antes="termino: "+this.getAnterior().get(r+1)+
                                                  " ,codmat: "+this.getAnterior().get(r+2)+
                                                  " ,condic: "+this.getAnterior().get(r+3)+
                                                  " ,notdef: "+this.getAnterior().get(r+4)+
                                                  " ,notrep: "+this.getAnterior().get(r+5)+
                                                  " ,defrep: "+this.getAnterior().get(r+6)+
                                                  " ,porina: "+this.getAnterior().get(r+7)+
                                                  " ,seccion: "+this.getAnterior().get(r+8)+
                                                  " ,peraca: "+this.getAnterior().get(r+9)+
                                                  " ,codesp: "+this.getAnterior().get(r+10);

                                            despues="termino: "+tabla.getModel().getValueAt(i, 2)+
                                                  " ,codmat: "+tabla.getModel().getValueAt(i, 3)+
                                                  " ,condic: "+tabla.getModel().getValueAt(i, 4)+
                                                  " ,notdef: "+tabla.getModel().getValueAt(i, 5)+
                                                  " ,notrep: "+tabla.getModel().getValueAt(i, 6)+
                                                  " ,defrep: "+tabla.getModel().getValueAt(i, 7)+
                                                  " ,porina: "+tabla.getModel().getValueAt(i, 8)+
                                                  " ,seccion: "+tabla.getModel().getValueAt(i, 9)+
                                                  " ,peraca: "+tabla.getModel().getValueAt(i, 10)+
                                                  " ,codesp: "+tabla.getModel().getValueAt(i, 11);*/


                                        antes="termino: "+this.getAnterior().get(r+1)+
                                                  " ,codmat: "+this.getAnterior().get(r+2)+
                                                  " ,condic: "+this.getAnterior().get(r+3)+
                                                  " ,notdef: "+this.getAnterior().get(r+4)+
                                                  " ,notrep: "+this.getAnterior().get(r+5)+
                                                  " ,defrep: "+this.getAnterior().get(r+6)+
                                                  " ,porina: "+this.getAnterior().get(r+7)+
                                                  " ,seccion: "+this.getAnterior().get(r+8)+
                                                  " ,peraca: "+this.getAnterior().get(r+9)+
                                                  " ,codesp: "+this.getAnterior().get(r+10);

                                            despues="termino: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("SEMESTRE"))+
                                                  " ,codmat: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("CODIGO"))+
                                                  " ,condic: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("CONDICION"))+
                                                  " ,notdef: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("DEFINITIVA"))+
                                                  " ,notrep: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("REPARACION"))+
                                                  " ,defrep: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("DEFREPARACION"))+
                                                  " ,porina: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("INASISTENCIA"))+
                                                  " ,seccion: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("SECCION"))+
                                                  " ,peraca: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("PERIODO"))+
                                                  " ,codesp: "+tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("ESPECIALIDAD"));
                                        


                                          extras=this.antes_despues(usuario,antes,despues,this.getAnterior().get(r+11).toString());

                                          System.out.println("EXTRAS "+extras );
                                         // System.out.println("posicion real: "+i+" relativa: "+tabla.convertRowIndexToModel(i));

                                           this.modificacion(new conexion_base_de_datos().getConexion(), tabla, i,extras);//metodo para realizar la modificacion

                                         // System.out.println("capacidad: "+pensum.size());
                                           this.monitoreo(new conexion_base_de_datos().getConexion(), usuario,tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("CODIGO")).toString() ,m.codigo_materia(tabla.getModel().getValueAt((i), tabla.getColumnModel().getColumnIndex("CODIGO")).toString(), pensum) ,antes+"\n"+despues ,"MODIFICACION", carrera, cedula);
                                       }

                            }//fin recorrido LinkedList informacion sin modificar
                     }

               }//fin recorrido notas tabla
               ima.mensaje_informacion("MODIFICACIONES REALIZADAS CON EXITO", "MODIFICACION", "modificar", "png");
                

}

/**Este metodo se usa de forma generica para realizar una confirmacion de la
 accion que se desea realizar, por lo que se pasa como parametro la situacion
 de "ELIMINACION" o "MODIFICACION" o "ADICION"*/
public int confirmacion(String situacion){
Object[] opciones={"SI, Por favor","NO, Para nada"};

  int r= JOptionPane.showOptionDialog(new JFrame(),
                                      "REALMENTE DESEA REALIZAR ESTA "+situacion+"\n",
                                      "CONFIRMACION",
                                      JOptionPane.YES_NO_OPTION,
                                      JOptionPane.QUESTION_MESSAGE,
                                      ima.imagenes("multiples", "png"),
                                      opciones,
                                      opciones[0]);

System.out.println("confirmacion: "+r);
return r;

}



public String ventana_entrada(Object valor_inicial, Object mensaje){
    String respuesta=null;
         
       respuesta= JOptionPane.showInputDialog(mensaje,valor_inicial);
        System.out.println(" "+respuesta);
        
return respuesta;
}



public void seleccion(JTable tabla){
    String decision=null;
    String contenido=null;

 if((tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("CODIGO") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("CONDICION") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("REPARACION") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("DEFINITIVA") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("SEMESTRE") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("CODIGO") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("DEFREPARACION") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("INASISTENCIA") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("SECCION") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("PERIODO") ||
     tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("ESPECIALIDAD")
     ) && //si la seleccion esta entre las columnas ya mencionadas
     tabla.getModel().getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn())!=null  //y ademas el campo seleccionado no esta en blanco
         ){
         contenido=tabla.getModel().getValueAt(tabla.convertRowIndexToModel(tabla.getSelectedRow()),tabla.convertColumnIndexToModel(tabla.getSelectedColumn())).toString();            
         
            if(tabla.getSelectedColumn()==4){//solo si el click es la columna de condicion de la materia se traduce el nombre y viceversa cuando se quiera ingresar el dato
                materias m = new materias();
                int posicion = Integer.valueOf( m.condicion_materia_inversa(m.condicion_materia(contenido))  );
                decision = ima.selector_generico_alternativo("CONDICION DE LA MATERIA", "CONDICION", new Object[]{  "NORMAL",
                                                                                                                    "PARALELO",
                                                                                                                    "REPITENCIA",
                                                                                                                    "EQUIVALENCIA",
                                                                                                                    "REPARÓ",
                                                                                                                    "REPROBÓ",
                                                                                                                    "REPARÓ-PENDIENTE",
                                                                                                                    "APROBÓ",
                                                                                                                    "NO REPARÓ-PENDIENTE",
                                                                                                                    "REPROBÓ-25% DE INASISTENCIA",
                                                                                                                    "SUFICIENCIA",
                                                                                                                    "PARALELO-REPARÓ-PENDIENTE",
                                                                                                                    "PARALELO-REPARÓ",
                                                                                                                    "PARALELO-NO REPARÓ-PENDIENTE",
                                                                                                                    "REPITENCIA-REPARÓ-PENDIENTE",
                                                                                                                    "REPITENCIA-REPARÓ",
                                                                                                                    "REPITENCIA-NO REPARÓ-PENDIENTE",
                                                                                                                    "MANTENIMIENTO",
                                                                                                                    "EXONERADO",
                                                                                                                    "RECONOCIMIENTO DE CRÉDITOS",
                                                                                                                    "NIVELACIÓN",
                                                                                                                    "ACREDITACIÓN",
                                                                                                                    "REPROBÓ-50% DE INASISTENCIA"},
                                                                                                                     (posicion-1) );

                decision = m.condicion_materia_inversa(decision);
                
            }else{// en caso de que no
               // contenido=tabla.getModel().getValueAt(tabla.convertRowIndexToModel(tabla.getSelectedRow()),tabla.convertColumnIndexToModel(tabla.getSelectedColumn())).toString();            
                decision = this.ventana_entrada(contenido,"Ingrese el Nuevo Valor");
            }
            
            //contenido=tabla.getModel().getValueAt(tabla.convertRowIndexToModel(tabla.getSelectedRow()),tabla.convertColumnIndexToModel(tabla.getSelectedColumn())).toString();            
            //decision = this.ventana_entrada(contenido,"Ingrese el Nuevo Valor");
 
                    if(decision==null || //si la respuesta es vacia null
                       decision.equalsIgnoreCase(contenido) //o no cambio nada, el contenido de la tabla igual al de la entrada
                    ){}//no hace nada
                    else{

                        System.out.println("columna "+tabla.getColumnName(tabla.getSelectedColumn()));

                            if((tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("REPARACION") || //esta verificacion se encarga de analizar
                                tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("DEFINITIVA") || //de que cuando la informacion tenga que ver con
                                tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("DEFREPARACION") ||//estos campos se haga una consideracion especial
                                tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("INASISTENCIA"))
                              ){


                                    System.out.println("def,rep,defrep "+decision);

                                           if(!tabla.getColumnName(tabla.getSelectedColumn()).equals("INASISTENCIA") && rbd.verificador_numerico(decision, true)==false){//Si la nota es mayor a 20 o menor a 0 hay un error
                                                    tabla.getModel().setValueAt(decision.toUpperCase(), tabla.convertRowIndexToModel(tabla.getSelectedRow()),tabla.convertColumnIndexToModel(tabla.getSelectedColumn()));
                                                    tabla.getModel().setValueAt(new Boolean(true), tabla.convertRowIndexToModel(tabla.getSelectedRow()), tabla.convertColumnIndexToModel(tabla.getColumnModel().getColumnIndex("MODIFICAR")));
                                           }


                                    System.out.println("inasistencia "+decision);

                                           if(tabla.getColumnName(tabla.getSelectedColumn()).equalsIgnoreCase("INASISTENCIA") && rbd.verificador_inasistencia(decision, true)==false){//Si la inasistencia es mayor a 100 o menor a 0 hay un error
                                                     tabla.getModel().setValueAt(decision.toUpperCase(), tabla.convertRowIndexToModel(tabla.getSelectedRow()),tabla.convertColumnIndexToModel(tabla.getSelectedColumn()));
                                                     tabla.getModel().setValueAt(new Boolean(true), tabla.convertRowIndexToModel(tabla.getSelectedRow()), tabla.convertColumnIndexToModel(tabla.getColumnModel().getColumnIndex("MODIFICAR")));
                                           }


                              }else{//esto es en caso de que no sea ninunoo de los campos especiales y se hace aqui para obligar a que se hagan las dos revisiones sino que no haga caso
                                  tabla.getModel().setValueAt(decision.toUpperCase(), tabla.convertRowIndexToModel(tabla.getSelectedRow()),tabla.convertColumnIndexToModel(tabla.getSelectedColumn()));
                                  tabla.getModel().setValueAt(new Boolean(true), tabla.convertRowIndexToModel(tabla.getSelectedRow()), tabla.convertColumnIndexToModel(tabla.getColumnModel().getColumnIndex("MODIFICAR")));
                              }


                            



                     //tabla.getModel().setValueAt(decision.toUpperCase(), tabla.getSelectedRow(), tabla.getSelectedColumn());
                     //tabla.getModel().setValueAt(new Boolean(true), tabla.getSelectedRow(), 1);
                    }
            
 }

}







public void borrado(Connection con, int posicion){
           
            Statement sentencia = null;



    try {
            sentencia = con.createStatement();
            sentencia.executeUpdate("DELETE FROM "+this.getBase_datos()+" WHERE posicion=" + posicion + ";");
            
            
            sentencia.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
        }

}


public void eliminando_materia(JTable tabla, String responsable, String carrera, LinkedList<String> pensum,String cedula){
    materias m = new materias();


     for(int i=0; i<tabla.getRowCount();i++){ //recorriendo las notas de la tabla
                  
         if(tabla.getModel().getValueAt(i, 1).toString().equalsIgnoreCase("true")){//solo se modificaran aquellos que sean verdaderos para modificar
            
             this.borrado(new conexion_base_de_datos().getConexion(), Integer.valueOf(tabla.getModel().getValueAt(i, 0).toString()));//borrando la materia


             this.monitoreo(new conexion_base_de_datos().getConexion(), responsable, tabla.getModel().getValueAt(i, 3).toString(),m.codigo_materia(tabla.getModel().getValueAt(i, 3).toString(), pensum),"ELIMINANDO MATERIA","ELIMINACION", carrera, cedula);//enviando al historial
         }
         
     }//fin recorrido de la tabla


     ima.mensaje_informacion("LA MATERIA FUE ELIMINADA CORRECTAMENTE", "NOTIFICACION", "eliminacion", "png");
     
}


public void datos_extras(Connection con,String cedula){

        Statement sentencia = null;
        ResultSet resultado = null;

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT carrera,turno FROM control_de_estudio.datos_academicos WHERE cedula='"+cedula+"';");
            while(resultado.next()){
            
                this.setTurno(resultado.getString("turno"));
                this.setCarrera(resultado.getString("carrera"));


            }


            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT nombres,apellidos FROM control_de_estudio.datos_personales WHERE cedula='"+cedula+"';");
            while(resultado.next()){
            
                this.setEstudiante(resultado.getString("nombres")+", "+resultado.getString("apellidos"));
                
            }

            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }



}


public String cual_pensum(String base_datos,String especialidad,String turnos, materias materia){
    String curso=null;

            //esta vigencia depende de los turnos ya que hay un pensum para el diurno y otro para el nocturno


    if(base_datos.endsWith("2010")){
            materia.setSeleccion_pensum(0);//llenando parametros
            materia.setNuevo_ingreso(0);


/*
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
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="";

            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_civil_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_civil_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_electrica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="";

            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_electronica_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="";

            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_sistemas_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turnos.equalsIgnoreCase("NOCTURNO")) curso="ingenieria_sistemas_2010_n";

            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("DIURNO")) curso="ingenieria_telecomunicaciones_2010_d";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turnos.equalsIgnoreCase("NOCTURNO")) curso="";

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA") && turnos.equalsIgnoreCase("DIURNO")) curso="tsu_enfermeria_2010_d";
            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA") && turnos.equalsIgnoreCase("NOCTURNO")) curso="";

            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_administracion_gestion_municipal_2010_d";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turnos.equalsIgnoreCase("NOCTURNO")) curso="lic_administracion_gestion_municipal_2010_n";

            if(especialidad.equalsIgnoreCase("LIC. EN ENFERMERIA") && turnos.equalsIgnoreCase("DIURNO")) curso="lic_enfermeria_2010_d ";
*/
   }


if(base_datos.endsWith("2009")){
            materia.setSeleccion_pensum(1);//llenando parametros
            materia.setNuevo_ingreso(0);

            /*if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) curso="ingenieria_mecanica_2009";
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

             */
}


if(base_datos.endsWith("2007")){
            materia.setSeleccion_pensum(2);//llenando parametros
            materia.setNuevo_ingreso(0);
    /*
            if(especialidad.equalsIgnoreCase("INGENIERIA MECANICA")) curso="ingenieria_mecanica_2007";
            if(especialidad.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) curso="lic_educacion_integral_2007";
            if(especialidad.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) curso="lic_contaduria_publica_2007";
            if(especialidad.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) curso="lic_economia_social_2007";
            if(especialidad.equalsIgnoreCase("TSU EN TURISMO")) curso="tsu_turismo_2007";

            if(especialidad.equalsIgnoreCase("TSU EN ENFERMERIA")) curso="tsu_enfermeria_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA AERONAUTICA")) curso="ingenieria_aeronautico_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA CIVIL")) curso="ingenieria_civil_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRICA")) curso="ingenieria_electrica_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA ELECTRONICA")) curso="ingenieria_electronica_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) curso="ingenieria_sistemas_2007";
            if(especialidad.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) curso="ingenieria_telecomunicaciones_2007";
            if(especialidad.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) curso="lic_administracion_gestion_municipal_2007";
*/
   }

System.out.println("pensum: "+curso);
 return curso;
}






public void manipulacion_pensum(JComboBox semestre, JComboBox asignatura, JTextField codigo, LinkedList<String> pensum){
    DefaultComboBoxModel dtm= new DefaultComboBoxModel(new String[]{});//limpiando el modelo de asignaturas
    asignatura.setModel(dtm);//asignando el modelo




    //for(int t=0; t<pensum.size();t=t+7){//recorriendo el pensum
    for(int t=0; t<pensum.size();t=t+10){//recorriendo el pensum

        if((semestre.getSelectedIndex()+1)==Integer.valueOf(pensum.get(t+1))){//comparando si hay coincidencia de semestre
            //listando nombres de materia por semestre elegido
            dtm.addElement(pensum.get(t+2).toString());//agregando el elemento
            
           
        }

   }//fin recorrido pensume


    //for(int c=0;c<pensum.size();c=c+7){//recorriendo el pensum
    for(int c=0;c<pensum.size();c=c+10){//recorriendo el pensum

        if(asignatura.getSelectedItem().toString().equalsIgnoreCase(pensum.get(c+2))){//ubicando el codigo de la materia

            codigo.setText(pensum.get(c+0).toString());//escribiendo el codigo de la materia
        }


    }//fin recorido




}


public void materia_codigo(JComboBox asignatura, JTextField codigo,LinkedList<String> pensum){

    //for(int c=0;c<pensum.size();c=c+7){//recorriendo el pensum
    for(int c=0;c<pensum.size();c=c+10){//recorriendo el pensum

        if(asignatura.getSelectedItem().toString().equalsIgnoreCase(pensum.get(c+2))){//ubicando el codigo de la materia

            codigo.setText(pensum.get(c+0).toString());//escribiendo el codigo de la materia
            break;
        }


    }//fin recorido

}


public int codigo_especialidad(String carrera){
    int codigo=0;
    
//System.out.println("Codigo Especialidad: "+carrera+" - "+turno);
    /*if(carrera.equalsIgnoreCase("INGENIERIA AERONAUTICA") && turno.equalsIgnoreCase("DIURNO")) codigo=1;
    if(carrera.equalsIgnoreCase("INGENIERIA CIVIL") && turno.equalsIgnoreCase("DIURNO")) codigo=2;
    if(carrera.equalsIgnoreCase("INGENIERIA CIVIL") && turno.equalsIgnoreCase("NOCTURNO")) codigo=24;
    if(carrera.equalsIgnoreCase("INGENIERIA ELECTRICA") && turno.equalsIgnoreCase("DIURNO")) codigo=3;
    if(carrera.equalsIgnoreCase("INGENIERIA ELECTRONICA") && turno.equalsIgnoreCase("DIURNO")) codigo=4;
    if(carrera.equalsIgnoreCase("INGENIERIA DE SISTEMAS") && turno.equalsIgnoreCase("NOCTURNO")) codigo=9;
    if(carrera.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES") && turno.equalsIgnoreCase("DIURNO")) codigo=8;
    if(carrera.equalsIgnoreCase("TSU EN ENFERMERIA") && turno.equalsIgnoreCase("DIURNO")) codigo=28;
    if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turno.equalsIgnoreCase("DIURNO")) codigo=53;
    if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL") && turno.equalsIgnoreCase("NOCTURNO")) codigo=52;
    if(carrera.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turno.equalsIgnoreCase("DIURNO")) codigo=42;
    if(carrera.equalsIgnoreCase("LIC. ECONOMIA SOCIAL") && turno.equalsIgnoreCase("NOCTURNO")) codigo=43;*/
    
    
    codigo = Integer.valueOf(rpdf.opsu_carrera(carrera));



        
System.out.println("codigo: "+codigo);
return codigo;

}



public void inscribir_materia(Connection con, String bd, String cedula,String codigo,String seccion, int semestre,String periodo ,String condicion, int reparacion, int definitiva, int defreparacion, int inasistencia, int especialidad, String responsable, String asignatura){
        try {
            Statement sentencia = null;
             sentencia = con.createStatement();
             sentencia.executeUpdate("INSERT INTO "+bd+" (POSICION,CEDULA,CODMAT,SECCION,TERMINO,PERACA,CONDIC,NOTREP,NOTDEF,DEFREP,PORINA,CODESP,OBSERVACIÓ) "
                                   + "VALUES(NULL,'"+cedula+"','"+codigo+"','"+seccion+"',"+semestre+",'"+periodo+"','"+condicion+"',"+reparacion+","+definitiva+","+defreparacion+","+inasistencia+","+especialidad+",'');");

              sentencia.close();
              con.close();

              this.monitoreo(new conexion_base_de_datos().getConexion(), responsable, codigo,asignatura,"AÑADIENDO MATERIA DENTRO DE SU RECORD ACADEMICO","ADICION",this.especialidades(especialidad), cedula);//enviando al historial
            ima.mensaje_informacion("Materia inscrita con exito", "NOTIFICACION", "inscrito", "png");


        } catch (SQLException ex) {
            Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
            ima.mensaje_informacion("Error al enviar la informacion \n"+ex.getMessage(), "ERROR", "error", "png");
            
        }
}



public void monitoreo(Connection con, String autor, String codigo_materia,String asignatura ,String descripcion, String tipo, String carrera, String cedula_estudiante){
   

        try {
            Statement sentencia = null;
            sentencia = con.createStatement();
            sentencia.executeUpdate("INSERT INTO control_de_estudio.monitoreo VALUES(NULL,'"+autor+"','"+codigo_materia+"','"+asignatura+"','"+cedula_estudiante+"','"+descripcion+"','"+tipo+"','"+carrera+"',DATE_FORMAT(NOW(),'%e%/%c%/%Y %- %r'));");

            sentencia.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }


}

/**Analiza las materias del estudiante que tiene en el record y la compara con el pensum que le
 corresponde para determinar por pensum las materias que le hacen falta por semestre*/
public void materias_faltantes(LinkedList<String> pensum, JTextArea pizarra){
   String informacion=null;
   
   pizarra.setText("");

   int semestre=1;

   pizarra.append("MATERIAS QUE EL ALUMNO NO TIENE SEGUN SU PENSUM.\n"
                + "Nota: Materia que no aparezca solo significa que la vio\n"
                + "alguna vez, no que la halla aprobado.\n\n");
   pizarra.append("     S - CODIGO ------- MATERIA.\n\n");




        //for(int p=0;p<pensum.size(); p=p+7){//recorriendo el pensum
        for(int p=0;p<pensum.size(); p=p+10){//recorriendo el pensum
            if(Integer.valueOf(pensum.get(p+1))>semestre){semestre=Integer.valueOf(pensum.get(p+1));pizarra.append("\n");}//espacio en blanco

            for(int i=0; i<this.getAnterior().size(); i=i+12){//recorriendo las materias del record del estudiante

                  if(pensum.get(p+0).equalsIgnoreCase(this.getAnterior().get(i+2).toString())){//comparando codigo pensum y record
                    informacion="";
                    break;
                  }else{//en caso de que no se halle la materia del pensum en el record se lista

                    informacion=("     "+pensum.get(p+1)+" - "+pensum.get(p+0)+" - "+pensum.get(p+2)+"\n");//mostrando semestre, codigo y materia
                  
                  }
              
              
            }//fin recorrido record

           pizarra.append(informacion);//presentando la informacion

            


        }//fin recorrido pensum



}

public void comparaciones_libres(Connection con, JComboBox listas, JTextArea reporte){
            Statement sentencia = null;
            ResultSet resultado = null;
            System.out.println("bd seleccionada: pensum."+listas.getSelectedItem().toString());
            reporte.setText("");
            reporte.append("LISTA DE MATERIAS NO CURSADAS. pensum."+listas.getSelectedItem().toString()+" \n"
                         + "NOTA: El que no aparezca la materia no quiere decir que la aprobo. Solo que alguna vez la vio\n\n");

           int semestre=1;


        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM pensum."+listas.getSelectedItem().toString()+" ORDER BY semestre");
            while(resultado.next()){
                  if(resultado.getInt("semestre")>semestre){reporte.append("\n"); semestre=resultado.getInt("semestre");}

                 for(int p=0; p<this.getAnterior().size(); p=p+12){//comparando codigo record con pensum que se desea comparar

                         if(resultado.getString("codigo").equalsIgnoreCase(this.getAnterior().get(p+2).toString())){
                             System.out.println("materias Cursadas: "+resultado.getString("codigo")+" - "+resultado.getString("asignatura"));
                             break;
                         }else{
                           //   System.out.println("record: "+this.getAnterior().size()+" p: "+p);
                            if(p>=this.getAnterior().size()-12){
                              //  System.out.println("Materia no hallada: "+resultado.getInt("semestre")+" - "+resultado.getString("codigo")+" - "+resultado.getString("asignatura"));
                                 reporte.append("Materia no Cursadas: "+resultado.getInt("semestre")+" - "+resultado.getString("codigo")+" - "+resultado.getString("asignatura")+"\n");
                            }

                         }

                 }//fin recorrido


            }

            sentencia.close();
            resultado.close();
            


        } catch (SQLException ex) {
            Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    


}

public String forzando_tabla(){
    String respuesta=null;
    String [] opciones={"VIGENCIA 2007","VIGENCIA 2009","VIGENCIA 2010"};

       respuesta= (String) JOptionPane.showInputDialog(new JFrame() , 
               "No olvide borrar este valor que por defecto se ingresa\ncon el proposito de que el alumno exista dentro de\nunas de las vigencias que se especifican",
               "FORZANDO",
               JOptionPane.OK_OPTION,
               ima.imagenes("forzando", "png"),
               opciones,
               opciones[0]);

       if(respuesta==null){}else{
            if(respuesta.equalsIgnoreCase("VIGENCIA 2007")) respuesta="control_de_estudio.cem_notas_alumnos_todas_carreras_2007";
            if(respuesta.equalsIgnoreCase("VIGENCIA 2009")) respuesta="control_de_estudio.cem_notas_alumnos_todas_carreras_2009";
            if(respuesta.equalsIgnoreCase("VIGENCIA 2010")) respuesta="control_de_estudio.cem_notas_alumnos_todas_carreras_2010";
        }



        System.out.println(" "+respuesta);

return respuesta;
}





/**Este metodo se realiza para ingresar un registro cualquiera dentro de unas de las tablas donde se guarda las notas 
para asi poder ingresarle las materias deseadas que se desea. Es solo para habilirlo */
public void forzar_inscripcion(Connection con, String cedula){
    Statement sentencia = null;
    String tabla=null;
    tabla=this.forzando_tabla();
    
    if(tabla!=null){//solo si se selecciona una vigencia
            try {

                    sentencia = con.createStatement();
                    sentencia.executeUpdate("INSERT INTO "+tabla+" VALUES(NULL,'"+cedula+"',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);");


                    sentencia.close();
                    con.close();
                    ima.mensaje_informacion("INGRESO FORZOSO REALIZADO CON EXITO..!!!", "NOTIFICACION", "forzozo", "png");
                    

                } catch (SQLException ex) {
                    ima.mensaje_informacion("ERROR EN EL MOMENTO DE FORZAR EL INGRESO DE LA MATERIA\n"+ex.getMessage(), "ERROR", "error", "png");
                    Logger.getLogger(modelo_notas_alumnos.class.getName()).log(Level.SEVERE, null, ex);
                }
    }


}


public DefaultComboBoxModel listado_carreras(JComboBox lista){    
    String elemento = null;
    DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    
    for(int i=0; i<lista.getItemCount(); i++){//recorriendo la lista       
        elemento = lista.getItemAt(i).toString();
        
        if(elemento.startsWith("tsu") || elemento.startsWith("lic") || elemento.startsWith("ingenieria")){
            //System.out.println("elementos: "+elemento);
            dcbm.addElement(lista.getItemAt(i));
            
        }
    }//fin recorrido
    
    
    
return dcbm;
}





}//fin de la clase
