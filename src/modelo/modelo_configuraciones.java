/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AN
 */
public class modelo_configuraciones {
    private imagenes ima;
    private editor_pensum ep;

    
    public modelo_configuraciones() {
        ima = new imagenes();
        ep = new editor_pensum();
    }
    
    
    
    public void cargar_configuracion_horario(Connection con, JTable tabla){
        DefaultTableModel dtm = new DefaultTableModel(new String[][]{}, new Object[]{"MODIFICADO","No", "HORAS", "TURNO"}){
            Class[] types = new Class [] {java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
            public Class getColumnClass(int columnIndex) {return types [columnIndex];}
            boolean[] canEdit = new boolean [] {true, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {return canEdit [columnIndex];}
        };
        tabla.setModel(dtm);
        

        try {
            Statement sentencia =null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM configuraciones.horario;");
            
            while(resultado.next()){ 
                dtm.addRow(new Object[]{false,resultado.getInt("no"), resultado.getString("hora"), resultado.getString("turno")});
            }
            
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("Error en la busqueda en la base de datos..!!!\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
            Logger.getLogger(modelo_configuraciones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_configuraciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }
    
    
   
    
    private void insertando(Connection con, JTable tabla){
    
        PreparedStatement preparada = null;
        
        try {        

            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);//nivel mas fuerte de aislamiento
            //System.out.println("Nivel de Aislamiento: " + con.getTransactionIsolation());

            con.setAutoCommit(false);//desabilitando el auto commit para manejo de transacciones
            //LIMPIANDO LA TABLA DE LA BASE DE DATOS ANTES DE GUARDAR LA NUEVA INFORMACION
            preparada = con.prepareStatement("DELETE FROM configuraciones.horario");
            preparada.execute();
            con.commit();
            preparada.clearBatch();
            preparada.close();
            
            con.setAutoCommit(false);//desabilitando el auto commit para manejo de transacciones
            preparada = con.prepareStatement("INSERT INTO configuraciones.horario VALUES (?,?,?)");

            for(int x=0; x<tabla.getRowCount(); x++){
                preparada.clearParameters();

                preparada.setInt(1,Integer.valueOf( tabla.getValueAt(x, 1).toString()) );//no
                preparada.setString(2,tabla.getValueAt(x, 2).toString() );//hora
                preparada.setString(3,tabla.getValueAt(x, 3).toString() );//turno               

                preparada.addBatch();
              
            }

            preparada.clearParameters();//limpiando parametros
            preparada.executeBatch();//ejecutando lotes

            con.commit();//haciendo permanente los cambios y liberando la base de datos
            preparada.clearBatch();//limpiando listas de sentencias
            preparada.close();//cerrando la sentencia
            ima.mensaje_informacion("EL HORARIO FUE ALMACENADO CON EXITO CON EXITO...!!!", "CONVALIDACION", "convalidar", "png");



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
                con.setAutoCommit(true);//regresando la condicion original del  autocommit
                con.close();
                System.out.println("Cerrando base de datos");

            } catch (SQLException ex2) {
                Logger.getLogger(registro_convalidaciones.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
    
    }
    
    public void guardar_horario(Connection con, JTable tabla){
        
        if(tabla.getRowCount()>0){//verificando que al menos halla una fila
           this.insertando(con, tabla);        
        }else{
            ima.mensaje_informacion("NO HAY REGISTRO PARA GUARDAR, PROCESO CANCELADO!!!","ADVERTENCIA","alto", "png");
        }
    
    
    }
    
public void modificador_en_vivo(JTable tabla){
    String decision=null;
    String contenido=null;

    
    System.out.println("antes: "+ tabla.getValueAt(tabla.getSelectedRow(), 1));
if(tabla.getSelectedColumn()>1){
        if( !tabla.getColumnName(tabla.getSelectedColumn()).equals("MODIFICAR") && //si la seleccion es cualquier columna menos la de modificacion
            tabla.getModel().getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn())!=null  //y ademas el campo seleccionado no esta en blanco
                ){
                    contenido=tabla.getModel().getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()).toString();

                    decision = ep.ventana_entrada(contenido,"Ingrese el Nuevo Valor");

                            if(decision==null || //si la respuesta es vacia null
                            decision.equalsIgnoreCase(contenido) //o no cambio nada, el contenido de la tabla igual al de la entrada
                            ){ 

                            }//no hace nada
                            else{
                            tabla.getModel().setValueAt(decision.toUpperCase(), tabla.getSelectedRow(), tabla.getSelectedColumn());
                            tabla.getModel().setValueAt(new Boolean(true), tabla.getSelectedRow(), 0);//activa la casilla de que fue modificado

                            }

            System.out.println("despues: "+tabla.getValueAt(tabla.getSelectedRow(), 1));
        }
}



}
    
    

public DefaultTableModel horas_horario(Connection con){
        
        DefaultTableModel dtm = new DefaultTableModel(new String[][]{}, new Object[]{"HORAS", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"}){                        
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {return canEdit [columnIndex];}
        };        
        

        try {
            Statement sentencia =null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM configuraciones.horario;");
            
            while(resultado.next()){ 
                dtm.addRow(new Object[]{resultado.getString("hora"), null, null, null, null, null, null, null});
            }
            
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            ima.mensaje_informacion("Error en la busqueda en la base de datos..!!!\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS","error", "png");
            Logger.getLogger(modelo_configuraciones.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(modelo_configuraciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
    return dtm;
    }
        

public void calculo_horas(){




}






}//fin de la clase