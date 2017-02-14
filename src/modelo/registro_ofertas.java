/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.ofertas;
import vista.visor;

/**
 *
 * @author AN
 */
public class registro_ofertas {

    
    private LinkedList<String> pen = new LinkedList<>();
    private LinkedList<String> pen_materias = new LinkedList<>();
    private String cedula,estudiante,carrera,turnos, codigo;
    private int control=0, cupos=0, inscritos=0, uc=0;

    private imagenes ima;

    public registro_ofertas() {
        ima = new imagenes();
    }








    //getters y setters
    public LinkedList<String> getPen() {
        return pen;
    }

    public void setPen(LinkedList<String> pen) {
        this.pen = pen;
    }

    public LinkedList<String> getPen_materias() {
        return pen_materias;
    }

    public void setPen_materias(LinkedList<String> pen_materias) {
        this.pen_materias = pen_materias;
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

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getTurnos() {
        return turnos;
    }

    public void setTurnos(String turnos) {
        this.turnos = turnos;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getUc() {
        return uc;
    }

    public void setUc(int uc) {
        this.uc = uc;
    }

   




public void cargando_horas(Connection con, JComboBox desde, JComboBox hasta){
    Statement sentencia = null;
    ResultSet resultado = null;
    LinkedList<String> horas = new LinkedList<>();
    /* 07:30-08:15 am
08:20-09:05 am
09:10-09:55 am
10:00-10:45 am
10:50-11:35 am
11:40-12:25 am
12:30-01:15 pm
01:20-02:05 pm
02:10-02:55 pm
03:00-03:45 pm
03:50-04:35 pm
04:40-05:25 pm
05:30-06:15 pm
06:15-07:00 pm
07:00-07:45 pm
07:45-08:30 pm
08:30-09:15 pm
09:15-10:00 pm
10:00-10:45 pm
10:45-11:30 pm
11:30-11:59 pm*/

    try{
    sentencia=con.createStatement();
    resultado = sentencia.executeQuery("SELECT * FROM configuraciones.horario" );
    while(resultado.next()){
        horas.add(resultado.getString("hora"));
    }
    
    
        sentencia.close();
    
    
        if(horas.size()<=0){ima.mensaje_informacion("NO HAY HORAS ESTABLECIDAS PARA EL HORARIO. CARGUE LAS HORAS PRIMERO", "NO EXISTEN HORAS", "error", "png");}
        else{
                //DefaultComboBoxModel dcbm = new DefaultComboBoxModel(horas.toArray());
                desde.setModel(new DefaultComboBoxModel(horas.toArray()));
                hasta.setModel(new DefaultComboBoxModel(horas.toArray()));
        }
        
    
    
    }catch(SQLException ex){
        Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
    
    }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}





public void ofertas_materias(Connection con, String carrera,int semestre,String seccion,String dia,String hora_inicio,String hora_fin,String periodo,String materia,String docente, int capacidad){
    Statement sentencia=null;

    try{
    sentencia=con.createStatement();
    sentencia.executeUpdate("INSERT INTO control_de_estudio.jornada_inscripcion_ofertadas" +
                            "(carrera,semestre,seccion,dia,hora_inicio,hora_fin,periodo,materia,docente,capacidad) VALUES('" +
                            carrera+"',"
                            +semestre+",'"
                            +seccion+"','"
                            +dia+"','"
                            +hora_inicio+"','"
                            +hora_fin+"','"
                            +periodo+"','"
                            +materia+"','"
                            +docente+"',"
                            +capacidad+
                            ");"  );

    sentencia.close();
   

    }catch(SQLException ex){
        Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
    
    }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}



//INSERT INTO prototipo_im (asignatura, semestre) SELECT asignatura, semestre FROM ingenieria_mecanica_2010_d;
//SELECT * FROM pensum.prototipo_im group by asignatura order by asignatura;



public void cargar_pensum(Connection con, String carrera, int semestre){
     Statement sentencia;
     ResultSet resultado;
     this.getPen().clear();



        try {
            sentencia =con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM pensum."+carrera+" WHERE semestre="+semestre+";");

            while(resultado.next()){

            this.getPen().add(resultado.getString("codigo"));
            this.getPen().add(String.valueOf(resultado.getInt("semestre")));
            this.getPen().add(resultado.getString("asignatura"));
            this.getPen().add(String.valueOf(resultado.getInt("uc")));
            this.getPen_materias().add(resultado.getString("codigo")+"-"+resultado.getString("asignatura"));//guerdando las materias aparte
            }

            sentencia.close();
            resultado.close();
            con.close();
            

        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }

}

public String especialidad_pensum(String carrera){
    /*INGENIERIA MECANICA
    LIC. EDUCACION INTEGRAL
    LIC. CONTADURIA PUBLICA
    LIC. ECONOMIA SOCIAL
    TSU EN TURISMO*/

        String salida=null;
//SEDE CAGUA
    if(carrera.equalsIgnoreCase("INGENIERIA MECANICA")) salida="prototipo_im";
    if(carrera.equalsIgnoreCase("LIC. EDUCACION INTEGRAL")) salida="prototipo_ei";
    if(carrera.equalsIgnoreCase("LIC. CONTADURIA PUBLICA")) salida="prototipo_cp";
    if(carrera.equalsIgnoreCase("LIC. ECONOMIA SOCIAL")) salida="prototipo_es";
    if(carrera.equalsIgnoreCase("TSU EN TURISMO")) salida="prototipo_tu";
//SEDE MARACAY

     if(carrera.equalsIgnoreCase("INGENIERIA AERONAUTICA")) salida="prototipo_ia";
     if(carrera.equalsIgnoreCase("INGENIERIA CIVIL")) salida="prototipo_ic";
     if(carrera.equalsIgnoreCase("INGENIERIA ELECTRICA")) salida="prototipo_ie";
     if(carrera.equalsIgnoreCase("INGENIERIA ELECTRONICA")) salida="prototipo_ee";
     if(carrera.equalsIgnoreCase("INGENIERIA DE SISTEMAS")) salida="prototipo_is";
     if(carrera.equalsIgnoreCase("INGENIERIA DE TELECOMUNICACIONES")) salida="prototipo_it";
     if(carrera.equalsIgnoreCase("TSU EN ENFERMERIA")) salida="prototipo_en";
     if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION Y GESTION MUNICIPAL")) salida="prototipo_am";
     if(carrera.equalsIgnoreCase("LIC. EN ENFERMERIA")) salida="prototipo_en";
     
 //SEDE MERIDA
     if(carrera.equalsIgnoreCase("LIC. EN ADMINISTRACION DE DESASTRE")) salida="prototipo_ad";
     if(carrera.equalsIgnoreCase("TSU EN MECANICA DENTAL")) salida="prototipo_md";
     if(carrera.equalsIgnoreCase("LIC. EN TURISMO")) salida="prototipo_lt";
     
     
     

return salida;
}




public void cargar_pensum_alternativo(Connection con, String carrera, int semestre){//es para cargar la combinacion de pensum llamado prototipos

     Statement sentencia;
     ResultSet resultado;
     this.getPen().clear();

     System.out.println("semestre "+semestre);

        try {
            sentencia =con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM pensum."+carrera+" WHERE semestre = "+semestre+" GROUP BY asignatura ORDER BY asignatura;");

            while(resultado.next()){
            


                    this.getPen().add(resultado.getString("asignatura"));


            }//fin del while

            System.out.println("tamaño"+this.getPen().size());

            sentencia.close();
            resultado.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }






}


public void llenado_asignaturas(JComboBox elemento){
DefaultComboBoxModel modelo = null;
     
     elemento.setModel(new javax.swing.DefaultComboBoxModel(this.getPen().toArray()));

    
     this.getPen_materias().clear();
     this.getPen().clear();

  // elemento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "andy", "cusatti", "Item 3", "Item 4" }));

}


public int confirmacion_ofertar(String nombre_materia){
Object[] opciones={"SI, Por favor","NO, Para nada"};

  int r= JOptionPane.showOptionDialog(new JFrame(),
                                      "REALMENTE DESEA OFERTAR ESTA MATERIA\n"
                                      +nombre_materia,
                                      "CONFIRMACION",
                                      JOptionPane.YES_NO_OPTION,
                                      JOptionPane.QUESTION_MESSAGE,
                                      ima.imagenes("pregunta", "png"),
                                      opciones,
                                      opciones[0]);

System.out.println("confirmacion: "+r);
return r;

}

public int confirmacion_inscribir(String nombre_materia){
Object[] opciones={"SI, Por favor","NO, Para nada"};

  int r= JOptionPane.showOptionDialog(new JFrame(),
                                      "REALMENTE DESEA OFERTAR ESTA MATERIA\n"
                                      +nombre_materia,
                                      "CONFIRMACION",
                                      JOptionPane.YES_NO_OPTION,
                                      JOptionPane.QUESTION_MESSAGE,
                                      ima.imagenes("pregunta", "png"),
                                      opciones,
                                      opciones[0]);

System.out.println("confirmacion: "+r);
return r;

}


/**Este metodo permite listar las secciones que pertenecen a un semestre */
public void secciones(Connection con, String carrera, int semestre, JComboBox seccion, String periodo){

    DefaultComboBoxModel dcbm = new DefaultComboBoxModel(new String[] { "TODAS" });
    System.out.println("semestre: "+semestre);

    if(semestre==0){}else{//solo se ejecutan las sentencias cuando al menos se seleccione un semestre
                Statement sentencia = null;
                ResultSet resultado = null;

                try {
                        

                        sentencia = con.createStatement();
                        resultado = sentencia.executeQuery("SELECT seccion FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE periodo='"+periodo+"' AND carrera = '"+carrera+"' AND semestre= "+semestre+" GROUP BY seccion;");
                        
                        while(resultado.next()){
                            Object informacion=resultado.getString("seccion");
                            dcbm.addElement(informacion);
                        }

                       sentencia.close();
                       resultado.close();
          

                    } catch (SQLException ex) {
                        Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
                    }


    }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
        }

    seccion.setModel(dcbm);


}



/**metodo para consultar y poder ver las materias que se han ofertados de la base de datos*/
public void exploracion(visor v, Connection con, String carrera,int semestres, String periodo){

DefaultTableModel modelo = new DefaultTableModel(
    new Object [][] {},
    new String [] { "POSICION", "CARRERA", "SEMESTRE", "SECCION", "DIA", "HORA INICIO", "HORA FIN", "CODIGO", "MATERIA", "DOCENTE", "CAPACIDAD"}
) {
    boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false, false, false, false, false, false
    };

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
};

v.getExplorador().setModel(modelo);//diciendole a la tabla que va a trabajar con este modelo



System.out.println("carrera: "+carrera+" - semestre: "+semestres);


     Statement sentencia;
     ResultSet r = null;


        try {
            sentencia =con.createStatement();

           if(semestres==0){
            r = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE periodo='"+periodo+"' AND carrera = '"+carrera+"' ORDER BY semestre;");
           }else{

                    if(v.getSecciones().getSelectedItem().toString().equalsIgnoreCase("TODAS")){//listando todas las materias de todas las secciones
                        r = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE periodo='"+periodo+"' AND carrera = '"+carrera+"' AND semestre= "+semestres+" ORDER BY seccion;");
                    }else{//listando solo las que pertenecen a una seccion en particular
                        r = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE periodo='"+periodo+"' AND carrera = '"+carrera+"' AND semestre= "+semestres+" and seccion='"+v.getSecciones().getSelectedItem().toString()+"' ORDER BY materia;");
                    }
           }

    
            while(r.next()){

               Object [] informacion={r.getInt("posicion"),
                                      r.getString("carrera"),
                                      r.getInt("semestre"),
                                      r.getString("seccion"),
                                      r.getString("dia"),
                                      r.getString("hora_inicio"),
                                      r.getString("hora_fin"),
                                      r.getString("periodo"),
                                      r.getString("materia"),
                                      r.getString("docente"),
                                      r.getInt("capacidad")      };

               modelo.addRow(informacion);
               modelo.fireTableDataChanged();

            }//fin del while

v.getExplorador().setAutoCreateColumnsFromModel(false);
v.getExplorador().setAutoCreateRowSorter(true);


            sentencia.close();
            r.close();
            con.close();


        } catch (SQLException ex) {
            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }


}//fin metodo explorador


public void busqueda_ofertadas(Connection con, int posicion, ofertas vo){


    try {
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM `control_de_estudio`.`jornada_inscripcion_ofertadas` WHERE posicion="+posicion+";");

            if (resultado.next()) {

         vo.getCarrera().setSelectedItem(resultado.getString("carrera"));
         vo.getSemestre().setSelectedIndex(resultado.getInt("semestre")-1);
         vo.getSalones().setText(resultado.getString("seccion"));
         vo.getDias().setSelectedItem(resultado.getString("dia"));
         vo.getDesde().setSelectedItem(resultado.getString("hora_inicio"));
         vo.getHasta().setSelectedItem(resultado.getString("hora_fin"));
                //resultado.getString("codigo"); el codigo de la materia no se muestra ni modifica no interesa por la diversidad de pensum
         vo.getAsignaturas().setSelectedItem(resultado.getString("materia"));
         vo.getDocente().setText(resultado.getString("docente"));
         vo.getCapacidad().setText(String.valueOf(resultado.getInt("capacidad")));

         vo.getAsignaturas().setEnabled(false);
         vo.getCarrera().setEnabled(false);
         vo.getSemestre().setEnabled(false);

            }else{ima.mensaje_informacion("Disculpe pero no se hallo alguna materia\n" +
                                  "asociada a la posicion a la que hace referencia.\n" +
                                  "Revise nuevamente. ", "POSICION NO ENCONTRADA", "no_hay", "png");
                }


   

            sentencia.close();
            resultado.close();
            con.close();



        }
        catch (SQLException ex) {
            Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
        }

}//fin metodo buequeda_ofertadas


public void modificacion_ofertadas(Connection con,int posicion, ofertas vo){

            Statement sentencia = null;


            try{
            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE control_de_estudio.jornada_inscripcion_ofertadas SET  " +
                    "carrera= '"+vo.getCarrera().getSelectedItem().toString()+"',"+
                    "semestre="+(vo.getSemestre().getSelectedIndex()+1)+","+
                    "seccion='"+vo.getSalones().getText()+"',"+
                    "dia='"+vo.getDias().getSelectedItem().toString()+"',"+
                    "hora_inicio='"+vo.getDesde().getSelectedItem().toString()+"',"+
                    "hora_fin='"+vo.getHasta().getSelectedItem().toString()+"',"+
                    "materia='"+vo.getAsignaturas().getSelectedItem().toString()+"',"+
                    "docente='"+vo.getDocente().getText()+"',"+
                    "capacidad='"+String.valueOf(vo.getCapacidad().getText())+"'"+
                    "WHERE posicion="+posicion+";"
                    );
                    ima.mensaje_informacion("MODIFICACION EXITOSA", "NOTIFICACION", "exito", "png");
            
            
        
         sentencia.close();
         con.close();

            }

            catch (SQLException ex) {
            Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
            }




            }





public void borrado(Connection con, int posicion){
        
            int salida = 0;
            Statement sentencia = null;
    
    
    
    try {
            sentencia = con.createStatement();
            salida = sentencia.executeUpdate("DELETE FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE posicion=" + posicion + ";");
            System.out.println("salida: "+salida);
            sentencia.close();
            con.close();

            
        } catch (SQLException ex) {
            Logger.getLogger(registro_ofertas.class.getName()).log(Level.SEVERE, null, ex);
        }

}



//.................................PLAN B .............................................

public void verificar_alumno(Connection con, String ci) {
System.out.println("Metodo verificar_alumno");
this.setControl(0);//inicializando la variable


        Statement sentencia = null;
        ResultSet buscar = null;

        try {

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_personales WHERE cedula='"+ ci + "';");

            if(buscar.next()){

            this.setCedula(buscar.getString("cedula"));
            this.setEstudiante(buscar.getString("apellidos")+", "+buscar.getString("nombres"));

            sentencia.close();
            buscar.close();

            sentencia = con.createStatement();
            buscar = sentencia.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='"+ ci + "';");
            buscar.next();

            this.setCarrera(buscar.getString("carrera"));
            this.setTurnos(buscar.getString("turno"));

            sentencia.close();
            buscar.close();
            con.close();
            }else{

                sentencia.close();
                buscar.close();
                con.close();

                this.setControl(1);//en caso de que no se encuentre el alumno

                    ima.mensaje_informacion( "ESTE ALUMNO NO SE ENCUENTRA REGISTRADO AUN \n", "ADVERTENCIA", "perdido", "png");
                    



            }

          



        }catch (SQLException ex) {
                Logger.getLogger(registro_ingenieria.class.getName()).log(Level.SEVERE, null, ex);
              
            }


//return control;
    }


public void capacidad_salones(Connection con,String carrera, String materia, String seccion){
    System.out.println("[Metodo capacidad_salones]");

        Statement sentencia = null;
        ResultSet resultado = null;
        this.setCupos(0);//inicializndo los cupos dentro del metodo
        int cuenta=0;
        this.setInscritos(0);
        


      System.out.println("\n"+"Seccion: "+seccion+" Materia: "+materia);

        try {
            
            sentencia=con.createStatement();
            resultado=sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion_ofertadas WHERE carrera ='"+carrera+"' AND materia='"+materia+"' AND seccion='"+seccion+"';");


            if(resultado.next()){//solo si encontro registro alguno analisis 1
                    System.out.println("CAPACIDAD");

                    this.setCupos(resultado.getInt("capacidad"));
                    sentencia.close();
                    resultado.close();

                    sentencia = con.createStatement();
                    resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.jornada_inscripcion WHERE seccion='" + seccion + "' AND materia='" + materia + "' GROUP BY cedula;");
                    System.out.println("INSCRITOS");

                        while(resultado.next()){//verificando los cupos y comparando la capacidad
                            cuenta=cuenta+1;
                        }//fin ciclo verificacion de cupos y capacidad

                    System.out.println("Inscritos = "+cuenta);

                    this.setInscritos(cuenta);


            }//fin analisis 1


                   
                System.out.println("cupos "+this.getCupos());



            sentencia.close();
            resultado.close();
            con.close();

        } catch (SQLException ex) {

            Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);
        }

}




/**Metodo desarrollado con el fin de poder inscribir cualquier materia a cualquier alumno
 sin ningun tipo de restriciones*/
public void inscribir_sin_restriciones(Connection con,
                             String cedula,
                             String estudiante,
                             String carrera,
                             int semestre,
                             String codigo,
                             String materia,
                             int uc,
                             String dia,
                             String hora,
                             String seccion,
                             String periodo,
                             String docente,
                             String condicion){


    Statement sentencia=null;
    try{
    sentencia=con.createStatement();
    sentencia.executeUpdate("INSERT INTO control_de_estudio.jornada_inscripcion " +
                            "VALUES(NULL,'"+cedula+"','"+
                                            estudiante+"','"+
                                            carrera+"',"+
                                            semestre+",'"+
                                            codigo+"','"+
                                            materia+"',"+
                                            uc+",'"+
                                            dia+"','"+
                                            hora+"','"+
                                            seccion+"','"+
                                            periodo+"',NOW(),'"+
                                            docente+"','"+
                                            condicion+"');"  );

    sentencia.close();
   con.close();

    }catch(SQLException ex){
        Logger.getLogger(materias.class.getName()).log(Level.SEVERE, null, ex);

    }

}



/**Metodo que permite sabes el codigo y las unidades de credito de una materia de un pensum
 en particular*/
public void informacion_materia(String materia, LinkedList<String> pensum){
    boolean encontrada_1=false, encontrada_2=false, encontrada_3=false;

    for(int p=0; p<pensum.size(); p=p+7){//recorriendo el pensum
        System.out.println("pensum: "+pensum.get(p+2)+", comparando: "+materia);


        if(materia.equalsIgnoreCase(pensum.get(p+2))){
         this.setCodigo(pensum.get(p));
         this.setUc(Integer.parseInt(pensum.get(p+3)));
         break;
        }
    }//fin recorrido pensum


System.out.println("codigo: "+this.getCodigo()+" materia: "+materia+" uc:"+this.getUc());





}

//*Me da la hora de entrada y de salida de cada materia */
public String hora_normal(String desde, String hasta){
String hora=null;
hora=desde.toString().substring(0,5)+" a "+hasta.toString().substring(6);
return hora;
}











//..................................FIN PLAN B ........................................................









}//fin de la clase
