/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.Color;
import java.awt.Component;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author AURORA
 */
public class configuracion_tablas extends JLabel implements TableCellRenderer{



public void configuracion_alternativa(JTable tabla, DefaultTableModel modelo, String columna, int ancho, String alineacion){


       DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
       tabla.getColumn(columna).setCellRenderer(alinear);
       TableColumn tc1 = tabla.getColumn(columna);


       //System.out.println("Analisis antes: min:"+tc1.getMinWidth()+" max:"+tc1.getMaxWidth()+" preferido:"+tc1.getPreferredWidth()+" actual:"+tc1.getWidth()+" quiero:"+ancho);
       tabla.setAutoResizeMode(1);
       tc1.setMinWidth(ancho-1);//estableciendo el minimo
       tc1.setPreferredWidth(ancho+1);//estableciendo el preferido
       tc1.setWidth(ancho);//estableciendo el ancho real

        if(alineacion.equalsIgnoreCase("centrado")){//tc1.setPreferredWidth(ancho);
        alinear.setHorizontalAlignment(SwingConstants.CENTER);}
        if(alineacion.equalsIgnoreCase("izquierda")){//tc1.setPreferredWidth(ancho);
        alinear.setHorizontalAlignment(SwingConstants.LEFT);}
        if(alineacion.equalsIgnoreCase("derecha")){//tc1.setPreferredWidth(ancho);
        alinear.setHorizontalAlignment(SwingConstants.RIGHT);}

       System.out.println("Analisis despues: min:"+tc1.getMinWidth()+" max:"+tc1.getMaxWidth()+" preferido:"+tc1.getPreferredWidth()+" actual:"+tc1.getWidth()+" quiero:"+ancho);


  }



  public void configuracion(JTable tabla, DefaultTableModel modelo, String columna, int ancho, String alineacion){


       DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
       tabla.getColumn(columna).setCellRenderer(alinear);
       TableColumn tc1 = tabla.getColumn(columna);


        if(alineacion.equalsIgnoreCase("centrado")){tc1.setPreferredWidth(ancho); alinear.setHorizontalAlignment(SwingConstants.CENTER);}
        if(alineacion.equalsIgnoreCase("izquierda")){tc1.setPreferredWidth(ancho);alinear.setHorizontalAlignment(SwingConstants.LEFT);}
        if(alineacion.equalsIgnoreCase("derecha")){tc1.setPreferredWidth(ancho); alinear.setHorizontalAlignment(SwingConstants.RIGHT);}

       


  }



public void tabla_habilitadas(JTable tabla, LinkedList datos){

    materias materia= new materias();


    DefaultTableModel informacion = new DefaultTableModel(new Object [][] {},new String [] {"SEMESTRE","CODIGO","MATERIA","CONDICION"}){
        Class[] types = new Class [] {
        java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
    };
    boolean[] canEdit = new boolean [] {
        false, false, false, false
    };

    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
                                                                                                                                    };




    tabla.setModel(informacion);//estableciendo a la tabla que use el modelo llamado informacion

     for(int i=0; i<datos.size(); i=i+4){//recorriendo el LinkedList
      
         Object ingreso[]={Integer.valueOf(datos.get(i+3).toString()),//  columna semestre
                          datos.get(i), //columna codigo
                          datos.get(i+1), //columna materia
                          materia.condicion_materia(datos.get(i+2).toString()) //columna condicion
        };
         informacion.addRow(ingreso);
         informacion.fireTableDataChanged();

tabla.setAutoCreateColumnsFromModel(false);
tabla.setAutoCreateRowSorter(true);

     
     }//fin recorricdo del LinkedList

}//fin metodo de autorizadas

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

if (isSelected) {
/* Para celdas seleccionadas:
según el valor de 'value', pones el color, el tipo de letra,
el formato, o incluso el tooltip.
*/
setForeground(Color.BLUE);
//setBackground(table.getSelectionBackground());
setBackground(Color.BLUE);

} else {
/* Para celdas no seleccionadas:
según el valor de 'value', pones el color, el tipo de letra,
el formato, o incluso el tooltip.
*/
setForeground(table.getForeground());
setBackground(Color.RED);
setBackground(Color.RED);
}

setText("andy");
return this;



        
    }




/**Este metodo permite dar color al texto segun codigo html incrustado para facilidad. 
 para ello hay que llenar ciertas caracteristicas, el color debe ser en formato hexadecimal
 como por ejemplo "#CD5C5C". El texto se devuelve formateado segun lo deseado*/
public String atributos_texto(String texto, String color, boolean negritas, boolean cursiva, boolean tachar){
    String armado = null;
    
    //por defecto
    armado = "<html>";        
    //opciones    
    if(negritas)armado = armado.concat("<b>");
    if(cursiva)armado = armado.concat("<i>");
    if(tachar)armado = armado.concat("<s>");
    //incrustando todos los atributos
    
        armado = armado.concat("<font color="+color+">"+texto);//linea principal
    
    //cerrando todo el codigo
    if(negritas)armado = armado.concat("</b>");
    if(cursiva)armado = armado.concat("</i>");
    if(tachar)armado = armado.concat("</s>");
    
    armado = armado.concat("</html>");
    
    texto = armado;
    /*
    texto="<html><font color="+color+">"+texto+"</font></html>"; //color    
    texto="<html><b><font color="+color+">"+texto+"</font></b></html>"; //negrita
    texto="<html><i><font color="+color+">"+texto+"</font></i></html>"; //cursiva
    texto="<html><s><font color="+color+">"+texto+"</s></i></html>"; //tachar
*/

return texto;
}


public static void main(String [] args){

    configuracion_tablas ct = new configuracion_tablas();
   System.out.println( ct.atributos_texto("andy","#011400", true, true, true));

}







}//fin de la clase
