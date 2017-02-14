/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author AN
 */
public class editor_pensum {
    private String codigo_viejo=null;
    private LinkedList <String> codigos = new LinkedList<>();
    private imagenes ima;
    

    public editor_pensum() {
        ima = new imagenes();
    }

   
    public LinkedList<String> getCodigos() {
        return codigos;
    }

    public void setCodigos(LinkedList<String> codigos) {
        this.codigos = codigos;
    }



public void listado_tablas_pensum(Connection con, Object andy[], String coincidencia){

     LinkedList<String> sentencia = new LinkedList<>();

        try {

            ResultSet resultado = null;
            DatabaseMetaData metadatos = (DatabaseMetaData) con.getMetaData();
            resultado = metadatos.getTables("pensum", null, "%", null);
                while (resultado.next()) {//recorriendo las tablas
                    //listas.addItem(resultado.getString(3));
                    if (resultado.getString(3).startsWith(coincidencia)) {
                        sentencia.add(resultado.getString(3)); // System.out.println(resultado.getString(3));
                    }
                    // System.out.println(resultado.getString(3));
                }

            resultado.close();


        } catch (SQLException ex) {
            Logger.getLogger(editor_pensum.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(editor_pensum.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



}




public void lista_tabla_bd(Connection con, JComboBox listas){
            
            listas.setModel(new javax.swing.DefaultComboBoxModel(new String[]{})); //limpiando el JComboBox
           
            ResultSet resultado = null;


        try {

            DatabaseMetaData metadatos = (DatabaseMetaData) con.getMetaData();

               
                resultado = metadatos.getTables("pensum", null, "%", null);
                while (resultado.next()) {
                    
                    listas.addItem(resultado.getString(3));
                   // System.out.println(resultado.getString(3));
                }

                resultado.close();




        } catch (SQLException ex) {
            Logger.getLogger(editor_pensum.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(editor_pensum.class.getName()).log(Level.SEVERE, null, ex);
            }

        }






}








public void listado(Connection con, String pensum, JTable tabla){

            int semestre=1;//inicializando la variable

         DefaultTableModel dtm = new DefaultTableModel(new Object [][]{},new String [] {
        "MODIFICAR", "SEMESTRE", "CODIGO", "ASIGNATURA", "UC", "Prelacion_1", "Prelacion_2", "Prelacion_3","Prelacion_4","Prelacion_5","Prelacion_6"
         }){
          Class[] types = new Class [] {
        java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }


         };

      tabla.setModel(dtm);


            Statement sentencia = null;
            ResultSet resultado = null;
        try {

            sentencia=con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM pensum."+pensum+" ORDER BY semestre,asignatura;");
            while (resultado.next()) {
                

                if(resultado.getInt("semestre")==semestre){

                }else{//se agrega una linea en blanco para asi separar semestre a semestre
                    semestre=resultado.getInt("semestre");
                    Object [] informacion ={new Boolean(false),null,null,null,null,null,null,null,null,null,null};
                    dtm.addRow(informacion);
                    dtm.fireTableDataChanged();
                }


             Object [] informacion ={//el objeto que se pasa al modelo para ser guardado en la tabla
                new Boolean(false),
                resultado.getInt("semestre"),
                resultado.getString("codigo"),
                resultado.getString("asignatura"),
                resultado.getInt("uc"),
                resultado.getString("prelacion_1"),
                resultado.getString("prelacion_2"),
                resultado.getString("prelacion_3"),
                resultado.getString("prelacion_4"),//nuevas prelaciones por carreras que tienen mas de tres
                resultado.getString("prelacion_5"),
                resultado.getString("prelacion_6")
                };

                dtm.addRow(informacion);
                dtm.fireTableDataChanged();


            }

            sentencia.close();
            resultado.close();
            con.close();
            


        } catch (SQLException ex) {
            Logger.getLogger(editor_pensum.class.getName()).log(Level.SEVERE, null, ex);
        }




        }


/**Este metodo se usa de forma generica para realizar una confirmacion de la
 accion que se desea realizar, por lo que se pasa como parametro la situacion
 de "ELIMINACION" o "MODIFICACION" o "AGREGAR NUEVA MATERIA"*/
public int confirmacion_modificar(String situacion){
Object[] opciones={"SI, Por favor","NO, Para nada"};

  int r= JOptionPane.showOptionDialog(new JFrame(),
                                      "REALMENTE DESEA REALIZAR ESTA "+situacion+"\n",
                                      "CONFIRMACION",
                                      JOptionPane.YES_NO_OPTION,
                                      JOptionPane.QUESTION_MESSAGE,
                                      ima.imagenes("modificar", "png"),
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

    this.getCodigos().add(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());//tomando el valor de la columna codigo viejo
    System.out.println("antes: "+ tabla.getValueAt(tabla.getSelectedRow(), 2));

 if( !tabla.getColumnName(tabla.getSelectedColumn()).equals("MODIFICAR") && //si la seleccion es cualquier columna menos la de modificacion
     tabla.getModel().getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn())!=null  //y ademas el campo seleccionado no esta en blanco
         ){
            contenido=tabla.getModel().getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()).toString();

            decision = this.ventana_entrada(contenido,"Ingrese el Nuevo Valor");

                    if(decision==null || //si la respuesta es vacia null
                       decision.equalsIgnoreCase(contenido) //o no cambio nada, el contenido de la tabla igual al de la entrada
                    ){ 
                        
                    }//no hace nada
                    else{
                     tabla.getModel().setValueAt(decision.toUpperCase(), tabla.getSelectedRow(), tabla.getSelectedColumn());
                     tabla.getModel().setValueAt(new Boolean(true), tabla.getSelectedRow(), 0);
                     
                    }
     this.getCodigos().add(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());//tomando el valor de la columna codigo despues del peo
     System.out.println("despues: "+tabla.getValueAt(tabla.getSelectedRow(), 2));
 }

}


public void borrado(Connection con, String codigo, String bd){

            Statement sentencia = null;



    try {
            sentencia = con.createStatement();
            sentencia.executeUpdate("DELETE FROM pensum."+bd+" WHERE codigo='" + codigo + "';");


            sentencia.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
        }

}


public void eliminando_materia(JTable tabla, String bd){

     for(int i=0; i<tabla.getRowCount();i++){ //recorriendo las notas de la tabla

         if(tabla.getModel().getValueAt(i, 0).toString().equalsIgnoreCase("true")){//solo se borraran aquellos que sean verdaderos para modificar

             this.borrado(new conexion_base_de_datos().getConexion(), tabla.getModel().getValueAt(i, 2).toString(),bd);//borrando la materia


         }

     }//fin recorrido de la tabla
     ima.mensaje_informacion("LA MATERIA FUE ELIMINADA CORRECTAMENTE", "NOTIFICACION", "eliminacion", "png");
     
}


public void modificacion(Connection con,JTable tabla,int fila,String bd ){
         Statement sentencia = null;
         int viejo=0;
         for(int i=0; i<this.getCodigos().size();i=i+2){//recorriendo el LinkedList para comparar codigos de materia

            if(tabla.getValueAt(fila, 2).toString().equalsIgnoreCase(this.getCodigos().get(i+1))){
                viejo=i;
                break;
            }
         }//fin recorrido LinkedList

         
        try {

            sentencia = con.createStatement();
            sentencia.executeUpdate(
                                    "UPDATE pensum."+bd+
                                    " SET semestre="+Integer.parseInt(tabla.getValueAt(fila, 1).toString())+
                                    ", codigo='"+tabla.getValueAt(fila, 2).toString()+
                                    //", codigo='"+this.getCodigos().get(nuevo)+
                                    "', asignatura='"+tabla.getValueAt(fila, 3).toString()+
                                    "', uc="+Integer.parseInt(tabla.getValueAt(fila, 4).toString())+
                                    ", prelacion_1='"+tabla.getValueAt(fila, 5).toString()+
                                    "', prelacion_2='"+tabla.getValueAt(fila, 6).toString()+
                                    "', prelacion_3='"+tabla.getValueAt(fila, 7).toString()+                                   
                                    "', prelacion_4='"+tabla.getValueAt(fila, 8).toString()+
                                    "', prelacion_5='"+tabla.getValueAt(fila, 9).toString()+
                                    "', prelacion_6='"+tabla.getValueAt(fila, 10).toString()+                                   
                                    "' WHERE codigo='"+this.getCodigos().get(viejo)+"' ;");


            sentencia.close();
            con.close();

        } catch (SQLException ex) {

            Logger.getLogger(editor_pensum.class.getName()).log(Level.SEVERE, null, ex);
            ima.mensaje_informacion("ERROR ENVIANDO INFORMACION A LA BASE DE DATOS\n"+ex.getMessage(), "ERROR", "error", "png");
            
        }

}

public void tramites(JTable tabla, String bd){
    String extras=null;



             for(int i=0; i<tabla.getRowCount();i++){ //recorriendo las notas de la tabla

                    if(tabla.getModel().getValueAt(i, 0).toString().equalsIgnoreCase("true")){//solo se modificaran aquellos que sean verdaderos para modificar

                            System.out.println("modificando: "+tabla.getModel().getValueAt(i, 0)+" - "+tabla.getModel().getValueAt(i, 2));
                            this.modificacion(new conexion_base_de_datos().getConexion(), tabla, i, bd);

                     }

               }//fin recorrido notas tabla
                ima.mensaje_informacion("MODIFICACIONES REALIZADAS CON EXITO", "MODIFICACION", "modificar", "png");
                

}

public void agregar_materia(Connection con, String tabla_bd,int semestre, String codigo, String asignatura, int uc, String prelacion_1, String prelacion_2, String prelacion_3, String prelacion_4, String prelacion_5, String prelacion_6){
    Statement sentencia = null;


        try {

            sentencia = con.createStatement();
            sentencia.execute("INSERT INTO pensum."+tabla_bd+" (semestre,codigo,asignatura,uc,prelacion_1,prelacion_2,prelacion_3,prelacion_4,prelacion_5,prelacion_6) "
                            + "VALUES ("+semestre+",'"+codigo+"','"+asignatura+"',"+uc+",'"+prelacion_1+"','"+prelacion_2+"','"+prelacion_3+"','"+prelacion_4+"','"+prelacion_5+"','"+prelacion_6+"');");

            ima.mensaje_informacion("La Materia: \""+asignatura+"\", ha sido\nalmacenada con exito...!", "CONFIRMACION", "almacenando","png");
            
            sentencia.close();
            con.close();


        } catch (SQLException ex) {

            Logger.getLogger(editor_pensum.class.getName()).log(Level.SEVERE, null, ex);
            ima.mensaje_informacion("ERROR al intentar guardar la informacion en la base de datos\n"+ex.getMessage(), "ERROR", "error","png");
            
        }






}


public void codigos(String codigo_viejo, String codigo_nuevo){

   System.out.println("viejo: "+codigo_viejo+" nuevo: "+codigo_nuevo);
        this.getCodigos().add(codigo_viejo);//0
        this.getCodigos().add(codigo_nuevo);//1

   

}






















    
}//fin de la clase
