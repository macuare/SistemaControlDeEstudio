/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import vista.progreso;


/**
 *
 * @author AN
 */
public class procesamientos {

     LinkedList<String> notas = new LinkedList<>();
    private String ultima_evaluacion;
    private imagenes ima;
    public procesamientos() {
        ima = new imagenes();
    }





    public LinkedList<String> getNotas() {
        return notas;
    }

    public void setNotas(LinkedList<String> notas) {
        this.notas = notas;
    }









    public void archivos_analizar(String direccion, JTextArea comentario){
        LinkedList<String> archivos = new LinkedList<>();
        
        
        hilos hilo = new hilos("PROCESAMIENTO DE ACTAS DE NOTAS periodo: ",0,false, new progreso());    //iniciando hilo para la barra de progreso
        hilo.setInformacion_1("CARGANDO ACTAS A PROCESAR");
        hilo.start();
        
        
        //File archivo = new File("D://ACTAS ENTREGADAS");
        File archivo = new File(direccion);
        String ficheros [] =archivo.list();
        
        hilo.setInicio(0); hilo.setFin(ficheros.length);//colocando parametros al hilo 
        comentario.append("\nTOTAL ACTAS A PROCESAR: "+ficheros.length+"\n");
        
        for(int i=0; i<ficheros.length; i++){//recorriendo todos los ficheros
            
            System.out.println("Ficheros: "+ficheros[i]);

            if(ficheros[i].endsWith(".xls") || ficheros[i].endsWith(".XLS")){
                System.out.println("Archivo EXCEL: "+ficheros[i]);
                comentario.append("Archivo EXCEL: "+ficheros[i]);
                this.archivo_excel(archivo.getPath()+archivo.separator+ficheros[i],comentario);
                
                hilo.setAvance(i);//agregando informacion de progreso al hilo
                hilo.setInformacion_1(ficheros[i]);
            }

        }//fin recorrido todos los ficheros
        
        
        hilo.setAvance(hilo.getFin());//es para finalizar. se igualan los valores de avance con fin
        //System.out.println("ACTAS PROCESADAS = "+ficheros.length);
        //comentario.append("\nACTAS PROCESADAS = "+ficheros.length+"\n");

        
        
        
    }


/**Este metodo permite encontrar el porcentaje de inasistencia de un estudiante en particular */
public int registro_inasistencia(Sheet acta, String cedula){
    int porcentaje=0;

    recorrido: for(int filas=0; filas<=100; filas++){//recorriendo acta de inasistencia

  //   System.out.println("fila:"+acta.getRow(filas).getCell(0)+" ---nrow:"+acta.getRow(filas).isFormatted()
    //                   +" .... "+acta.getRow(filas).getCell(1)+"\n--- calu:"+acta.getRow(filas).getCell(1)
      //       );
     
        String cedalu = this.eliminar_exponencial(""+acta.getRow(filas).getCell(1));
        
                if(
                    acta.getRow(filas).getCell(0)==null    //que no sea null
                   || acta.getRow(filas).getCell(0).toString().isEmpty() //que no sea solo un espacio en blanco
                   || acta.getRow(filas).getCell(0).toString().startsWith("N") //o que no empiece por N°
                   //|| acta.getRow(filas).getCell(1).getCachedFormulaResultType() == 0//revisando que el resultado de la formula tenga un resultado para poder evaluar el campo siguiente
                   || cedalu.isEmpty() //o que el campo de cedula de alumno este en blanco
                   ){
               //     System.out.println("Contenido irregular fila = "+(filas-7));

                }else{

                      //System.out.println("REGISTRO SUPERVISADO: "+acta.getRow(filas).getCell(0)+" - "+acta.getRow(filas).getCell(1).getCachedFormulaResultType());
                       // System.out.println("buscando "+cedula+ " viendo: "+acta.getRow(filas).getCell(1).getStringCellValue().trim());
                    //if(acta.getRow(filas).getCell(1).getStringCellValue().trim().equalsIgnoreCase(cedula) ){//se busca la inasistencia del estudiante
                    if(cedalu.equalsIgnoreCase(cedula) ){//se busca la inasistencia del estudiante

                       // System.out.println("---------------SUPERVISION: "+acta.getRow(filas).getCell(0)+" ### "+acta.getRow(filas).getCell(43).getNumericCellValue());
                       // porcentaje = (int) acta.getRow(filas).getCell(43).getNumericCellValue();//es para cagua con 16 semanas
                        //porcentaje = (int) acta.getRow(filas).getCell(4).getNumericCellValue();//es para 18 semanas
                        //System.out.println("PORCENTAJE....segun....formula: "+acta.getRow(filas).getCell(45));
                        porcentaje = (int) acta.getRow(filas).getCell(45).getNumericCellValue();//es para 18 semanas
                        
                       
                        //=SUMA(F12:AK12)*100/$AU$4
                        
        //                 System.out.println("encontrado "+cedula+"  Inasistencia = "+porcentaje+"%" );
                        break recorrido;
                    }

                }

        }//fin recorrido acta de inasistencia

//System.out.println("porcentaje: "+porcentaje);
    return porcentaje;

    }


private String eliminar_exponencial(String texto){
   if(texto!=null){ 
        texto = texto.trim();//eliminando espacios en blanco al inicio y al final
        
        if(texto.contains(".")) texto = texto.replace(".", "");//eliminando los puntos si los tiene
       
        if(texto.contains("E") && 
            (texto.endsWith("0") || texto.endsWith("1") || texto.endsWith("2") || texto.endsWith("3") || //y ademas de la letra contenga un numero al final que representa el orden del exponencial
             texto.endsWith("4") || texto.endsWith("5") || texto.endsWith("6") || texto.endsWith("7") ||
             texto.endsWith("8") || texto.endsWith("9"))
                ){//si contiene el exponencial se ajusta la cifra
          texto = texto.substring(0, texto.length()-2);
        }else{//en caso contrario se regresa la cifra original

        }
        
   }else texto=null;
   
   
    return texto;
}


    public void archivo_excel(String archivo, JTextArea comentario){
        this.getNotas().clear();


        InputStream inp = null;


        try {
            inp = new FileInputStream(archivo);
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor ee = new ExcelExtractor(wb);
            ee.setFormulasNotResults(false);
            ee.setIncludeSheetNames(false);

            //Sheet hoja = wb.getSheet("ACTA EVALUACION");
            Sheet hoja = wb.getSheetAt(0);//la primera hoja de excel(pestaña 1 - ACTA EVALUACION)
            Sheet hoja2 = wb.getSheetAt(1);//la segunda hoja de excel(pestaña 2 - ACTA INASISTENCIA)
           // Row fila = hoja.getRow(2);
           // Cell celda = fila.getCell(2);
           // System.out.println("contenido: "+celda.getStringCellValue());
           DecimalFormat formato_numero = new DecimalFormat("#0,000,000"); //estableciendo el patron para los numeros enteros con sus separadores
           DecimalFormat f_nota = new DecimalFormat("00");//formato notas y porcentajes

            System.out.println("\n=============PARAMETROS DE LA HOJA===========");

                System.out.println("Codigo: "+hoja.getRow(2).getCell(2)
                                 + "\nMateria: "+hoja.getRow(2).getCell(4)
                                 + "\nPeriodo: "+hoja.getRow(3).getCell(2)
                                 + "\nCedula: "+formato_numero.format(hoja.getRow(4).getCell(11).getNumericCellValue())
                                 + "\nProfesor: "+hoja.getRow(4).getCell(2)
                                 + "\nSemestre: "+hoja.getRow(3).getCell(4)
                                 + "\nCarrera: "+hoja.getRow(3).getCell(8)
                                 + "\nCredito: "+hoja.getRow(2).getCell(15)
                                 + "\nSeccion:"+hoja.getRow(3).getCell(15)

                        );

                        comentario.append("==========PARAMETROS DE LA ACTA===========\n"
                                 + "Codigo: "+hoja.getRow(2).getCell(2)
                                 + "\nMateria: "+hoja.getRow(2).getCell(4)
                                 + "\nPeriodo: "+hoja.getRow(3).getCell(2)
                                 + "\nCedula: "+formato_numero.format(hoja.getRow(4).getCell(11).getNumericCellValue())
                                 + "\nProfesor: "+hoja.getRow(4).getCell(2)
                                 + "\nSemestre: "+hoja.getRow(3).getCell(4)
                                 + "\nCarrera: "+hoja.getRow(3).getCell(8)
                                 + "\nCredito: "+hoja.getRow(2).getCell(15)
                                 + "\nSeccion:"+hoja.getRow(3).getCell(15)
                                 +"\n====================================================="
                                        );

            System.out.println("\n============================================\n");
            int contador=1;
            
            System.out.println("----NOTAS DE LOS ESTUDIANTES---- ");
            for(int filas=8; filas<=78; filas++){//recorriendo las filas
                String cedula=this.eliminar_exponencial(""+hoja.getRow(filas).getCell(1));
  //              System.out.println("FILA: "+filas);
//                System.out.println(" contenido: "+hoja.getRow(filas).getCell(0));
    //            System.out.println(" campo 1: cedula: "+cedula);

                if(hoja.getRow(filas).getCell(0)==null    //que no sea null
                   || hoja.getRow(filas).getCell(0).toString().isEmpty() //que no sea solo un espacio en blanco
                   || hoja.getRow(filas).getCell(0).toString().startsWith("N") //o que no empiece por N°
                   //|| hoja.getRow(filas).getCell(1).getStringCellValue().isEmpty() //o que el campo de cedula de alumno este en blanco
                   || cedula.isEmpty() //o que el campo de cedula de alumno este en blanco     
                   ){
               //     System.out.println("Contenido irregular fila = "+(filas-7));

                }else{
              /*  System.out.println("Contenido NOTA: "+String.valueOf(hoja.getRow(filas).getCell(4).getNumericCellValue())
                                  +" PORCENTAJE: "+String.valueOf(hoja.getRow(7).getCell(5).getNumericCellValue()*100)
                                  );*/

                         //System.out.println("N°:"+contador+"  cedula: "+hoja.getRow(filas).getCell(1).getStringCellValue()+" Nota DEF: "+hoja.getRow(filas).getCell(22).getNumericCellValue());
                    System.out.println("N°:"+contador+"  cedula: "+cedula+" Nota DEF: "+hoja.getRow(filas).getCell(22).getNumericCellValue());
                    //PROBANDO LA ACTA 2 INASISTENCIA
                   //     this.registro_inasistencia(hoja2, hoja.getRow(filas).getCell(1).getStringCellValue().trim()); //buscando inasistencia del estudiante
                    //FIN ACTA INASISTENCIA
                        
                        String codmat = hoja.getRow(2).getCell(2).getStringCellValue().trim(); //codigo materia
                        String seccion = hoja.getRow(3).getCell(15).getStringCellValue().trim(); //seccion
                        String definitiva = String.valueOf(hoja.getRow(filas).getCell(22).getNumericCellValue()).trim();
                        String periodo = hoja.getRow(3).getCell(2).getStringCellValue();
                        
                        String e1 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(4).getNumericCellValue()) ); // eva1
                        String p1 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(5).getNumericCellValue()*100)); // pciento1
                        String e2 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(6).getNumericCellValue()) ); // eva1
                        String p2 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(7).getNumericCellValue()*100)); // pciento1
                        String e3 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(8).getNumericCellValue()) ); // eva1
                        String p3 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(9).getNumericCellValue()*100)); // pciento1
                        String e4 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(10).getNumericCellValue()) ); // eva1
                        String p4 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(11).getNumericCellValue()*100)); // pciento1
                        String e5 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(12).getNumericCellValue()) ); // eva1
                        String p5 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(13).getNumericCellValue()*100)); // pciento1
                        String e6 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(14).getNumericCellValue()) ); // eva1
                        String p6 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(15).getNumericCellValue()*100)); // pciento1
                        String e7 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(16).getNumericCellValue()) ); // eva1
                        String p7 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(17).getNumericCellValue()*100)); // pciento1
                        String e8 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(18).getNumericCellValue()) ); // eva1
                        String p8 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(19).getNumericCellValue()*100)); // pciento1
                        String e9 = String.valueOf(f_nota.format(hoja.getRow(filas).getCell(20).getNumericCellValue()) ); // eva1
                        String p9 = String.valueOf(f_nota.format(hoja.getRow(7).getCell(21).getNumericCellValue()*100)); // pciento1
                        
                        
                    //this.notas_rusticas(hoja.getRow(filas).getCell(1).getStringCellValue().trim(),//cedula estudiante
                    this.notas_rusticas(cedula.trim(),//cedula estudiante
                                        codmat,//hoja.getRow(2).getCell(2).getStringCellValue().trim(), //codigo materia                                        
                                        seccion,//hoja.getRow(3).getCell(15).getStringCellValue().trim(), //seccion
                                        definitiva,//String.valueOf(hoja.getRow(filas).getCell(22).getNumericCellValue()).trim(),//definitiva
                                        periodo,//hoja.getRow(3).getCell(2).getStringCellValue(), //periodo
                                        e1,p1,e2,p2,e3,p3,e4,p4,e5,p5,e6,p6,e7,p7,e8,p8,e9,p9,
/*
                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(4).getNumericCellValue()) ), // eva1
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(5).getNumericCellValue()*100)), // pciento1

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(6).getNumericCellValue()) ), // eva2
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(7).getNumericCellValue()*100)), // pciento2

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(8).getNumericCellValue()) ), // eva3
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(9).getNumericCellValue()*100)), // pciento3

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(10).getNumericCellValue()) ), // eva4
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(11).getNumericCellValue()*100)), // pciento4

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(12).getNumericCellValue()) ), // eva5
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(13).getNumericCellValue()*100)), // pciento5

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(14).getNumericCellValue()) ), // eva6
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(15).getNumericCellValue()*100)), // pciento6

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(16).getNumericCellValue()) ), // eva7
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(17).getNumericCellValue()*100)), // pciento7

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(18).getNumericCellValue()) ), // eva8
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(19).getNumericCellValue()*100)), // pciento8

                                        String.valueOf(f_nota.format(hoja.getRow(filas).getCell(20).getNumericCellValue()) ), // eva9
                                        String.valueOf(f_nota.format(hoja.getRow(7).getCell(21).getNumericCellValue()*100)), // pciento9
*/
                                        //String.valueOf(f_nota.format( this.registro_inasistencia(hoja2, hoja.getRow(filas).getCell(1).getStringCellValue().trim()) )) //porcentaje de inasistencia
                                        String.valueOf(f_nota.format( this.registro_inasistencia(hoja2, this.eliminar_exponencial(""+hoja.getRow(filas).getCell(1)) ) )) //porcentaje de inasistencia

                                        );


                    contador=contador+1;
                    ultima_evaluacion=hoja.getRow(7).getCell(20).getStringCellValue().trim();//tipo de evaluacion. laboratorio o evaluacion normal
                }

            }//fin recorrido celdas

                 System.out.println("\n\n");

            }catch (IOException ex) {
                System.out.println("ERROR 1: "+ex.getMessage());
            Logger.getLogger(procesamientos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inp.close();
            } catch (IOException ex) {
                System.out.println("ERROR 2: "+ex.getMessage());
                Logger.getLogger(procesamientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



        //---------------------------------
       /*     System.out.println("\nRESUMEN NOTAS");
            for(int n=0; n<this.getNotas().size(); n=n+23){

                System.out.println("cedula: |"+this.getNotas().get(n)+"| codigo: |"+this.getNotas().get(n+1)+"| seccion: |"+this.getNotas().get(n+2)+"| definitiva: |"+this.getNotas().get(n+3)+"| periodo: |"+this.getNotas().get(n+4).trim()+"|");


            }
            System.out.println("\nFIN RESUMEN NOTAS\n");
*/
        //---------------------------------

        //ESCRIBIENDO NOTAS A LA BASE DE DATOS
        this.evaluaciones_bd(new conexion_base_de_datos().getConexion(), comentario);

    }

    public void evaluaciones_bd(Connection con, JTextArea comentario){
        try {
            PreparedStatement lotes = null;
            lotes = con.prepareStatement("INSERT INTO control_de_estudio.evaluaciones VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            System.out.println("LLENANDO INFORMACION A ENVIAR A BASE DE DATOS");

            con.setAutoCommit(false);
            lotes.clearParameters();

            for(int i=0; i<this.getNotas().size(); i=i+24){//agregando informacion por acta de evaluacion
                lotes.setString(1,this.getNotas().get(i)); //cedula
                lotes.setString(2,this.getNotas().get(i+1)); //codmat
                lotes.setInt(3,99);    //codesp
                lotes.setString(4,this.getNotas().get(i+2)); //seccion
                lotes.setInt(5,100); //termino
                lotes.setString(6,this.getNotas().get(i+4)); // peraca

                lotes.setInt(7, Integer.valueOf(this.getNotas().get(i+5)) );//       eva1
                lotes.setString(8, (this.getNotas().get(i+6)).concat("%") ); //pciento1
                lotes.setInt(9, Integer.valueOf(this.getNotas().get(i+7)) );//        eva2
                lotes.setString(10, (this.getNotas().get(i+8)).concat("%") ); //pciento2
                lotes.setInt(11, Integer.valueOf(this.getNotas().get(i+9)) );//        eva3
                lotes.setString(12, (this.getNotas().get(i+10)).concat("%") ); //pciento3
                lotes.setInt(13, Integer.valueOf(this.getNotas().get(i+11)) );//        eva4
                lotes.setString(14, (this.getNotas().get(i+12)).concat("%") ); //pciento4
                lotes.setInt(15, Integer.valueOf(this.getNotas().get(i+13)) );//        eva5
                lotes.setString(16, (this.getNotas().get(i+14)).concat("%") ); //pciento5
                lotes.setInt(17, Integer.valueOf(this.getNotas().get(i+15)) );//        eva6
                lotes.setString(18, (this.getNotas().get(i+16)).concat("%") ); //pciento6
                lotes.setInt(19, Integer.valueOf(this.getNotas().get(i+17)) );//        eva7
                lotes.setString(20, (this.getNotas().get(i+18)).concat("%") ); //pciento7
                lotes.setInt(21, Integer.valueOf(this.getNotas().get(i+19)) );//       eva8
                lotes.setString(22, (this.getNotas().get(i+20)).concat("%") ); //pciento8
              //  System.out.println("tipo: "+ultima_evaluacion+" tiene laboratorio? "+ultima_evaluacion.equalsIgnoreCase("LAB") );//laboratorio
                if(ultima_evaluacion.equalsIgnoreCase("LAB")){//Hay que revisar si la ultima evaluacion tiene nota o es un laboratorio
                    lotes.setInt(23, 0);//       eva9
                    lotes.setString(24,"00%"); //pciento9
                    lotes.setInt(25, Integer.valueOf(this.getNotas().get(i+21)) );//        Lab
                    lotes.setString(26, (this.getNotas().get(i+22)).concat("%") ); //pciento_lab
                }else{
                    lotes.setInt(23, Integer.valueOf(this.getNotas().get(i+21)) );//       eva9
                    lotes.setString(24, (this.getNotas().get(i+22)).concat("%") ); //pciento9
                    lotes.setInt(25, 0);//        Lab
                    lotes.setString(26,"00%"); //pciento_lab
                }


                lotes.setInt(27, Integer.valueOf( this.getNotas().get(i+3).substring(0,this.getNotas().get(i+3).indexOf(".") ) ) );//        DEF
                lotes.setString(28,(this.getNotas().get(i+23)).concat("%"));//        porina
                lotes.setInt(29, 0);//        REP
                lotes.addBatch();//agregando lote
            }
System.out.println("ENVIANDO A LA BASE DE DATOS");
                int enviado [] = lotes.executeBatch();
                con.commit();

                System.out.println("Enviados: "+enviado.length);

                lotes.close();
System.out.println("CERRANDO. ENVIO CON EXITO");
        } catch (SQLException ex) {
            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DETALLES CON ESTE REGISTRO: "+ex.getMessage()+"\nESTADO: "+ex.getSQLState());
            comentario.append("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DETALLES CON ESTE REGISTRO: "+ex.getMessage()+"\nESTADO: "+ex.getSQLState()+"\n");

            Logger.getLogger(procesamientos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                System.out.println("Cerrando conexion");
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(procesamientos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }





    }


    public void notas_rusticas(String cedula, String codigo, String seccion, String definitiva, String periodo,
                               String eva1, String pciento1,String eva2, String pciento2,String eva3, String pciento3,String eva4, String pciento4,
                               String eva5, String pciento5,String eva6, String pciento6,String eva7, String pciento7,String eva8, String pciento8,
                               String eva9, String pciento9, String inasistencias){

        //cargar el resto de los parametros desde los pensum. uniendolos para que me de uno de cada uno y filtrar por codigo ya que son unicos

        this.getNotas().add(cedula); //0
        this.getNotas().add(codigo); //1
        this.getNotas().add(seccion); //2
        this.getNotas().add(definitiva); //3
        this.getNotas().add(periodo); //4

        this.getNotas().add(eva1); //5 numerico
        this.getNotas().add(pciento1); //6 caracter
        this.getNotas().add(eva2); //7
        this.getNotas().add(pciento2); //8
        this.getNotas().add(eva3); //9
        this.getNotas().add(pciento3); //10
        this.getNotas().add(eva4); //11
        this.getNotas().add(pciento4); //12
        this.getNotas().add(eva5); //13
        this.getNotas().add(pciento5); //14
        this.getNotas().add(eva6); //15
        this.getNotas().add(pciento6); //16
        this.getNotas().add(eva7); //17
        this.getNotas().add(pciento7); //18
        this.getNotas().add(eva8); //19
        this.getNotas().add(pciento8); //20
        this.getNotas().add(eva9); //21   este registro puede ser laboratorio o una evaluacion normal
        this.getNotas().add(pciento9); //22
        this.getNotas().add(inasistencias);//23



    }


    
public void verificar_calculos(Connection con, String periodo, JTextArea comentario){
    LinkedList<String> registros = new LinkedList<>();
    LinkedList<String> reprobados = new LinkedList<>();
    
    double parcial=0.00, auxiliar=0.00;
    int total=0, calculo=0, cant_errores=0, procesados=0, aprobados=0;
    DecimalFormat df = new DecimalFormat("00.00");
    DecimalFormat df2 = new DecimalFormat("00");
   
    
        hilos hilo = new hilos("VERIFICANDO CONSISTENCIAS DE LOS CALCULOS periodo: "+periodo,0,false, new progreso());    //iniciando hilo para la barra de progreso
        hilo.setInformacion_1("ANALIZANDO TODAS LAS NOTAS CARGADAS");
        hilo.start();
    
        hilo.setInicio(0); hilo.setFin(100);//colocando parametros al hilo         
    
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM control_de_estudio.evaluaciones WHERE peraca='"+periodo+"';");
hilo.setAvance(20);//agregando informacion de progreso al hilo    
            while(resultado.next()){
               parcial=0.00; auxiliar=0.00; total=0; calculo=0;//inicializando las variables por cada registro
                
               procesados = procesados + 1;//contando la cantidad de registros procesados
                             
               
               parcial = parcial + (double)( resultado.getInt("eva1") * Integer.valueOf( resultado.getString("xciento1").substring(0, 2) ) );               
               parcial = parcial + (double)( resultado.getInt("eva2") * Integer.valueOf( resultado.getString("xciento2").substring(0, 2) ) );
               parcial = parcial + (double)( resultado.getInt("eva3") * Integer.valueOf( resultado.getString("xciento3").substring(0, 2) ) );
               parcial = parcial + (double)( resultado.getInt("eva4") * Integer.valueOf( resultado.getString("xciento4").substring(0, 2) ) );
               parcial = parcial + (double)( resultado.getInt("eva5") * Integer.valueOf( resultado.getString("xciento5").substring(0, 2) ) );
               parcial = parcial + (double)( resultado.getInt("eva6") * Integer.valueOf( resultado.getString("xciento6").substring(0, 2) ) );
               parcial = parcial + (double)( resultado.getInt("eva7") * Integer.valueOf( resultado.getString("xciento7").substring(0, 2) ) );
               parcial = parcial + (double)( resultado.getInt("eva8") * Integer.valueOf( resultado.getString("xciento8").substring(0, 2) ) );
               parcial = parcial + (double)( resultado.getInt("eva9") * Integer.valueOf( resultado.getString("xciento9").substring(0, 2) ) );
               
               if(resultado.getString("xciento_lab").contains("00%")){//en caso de que tenga laboratorio               
               }else{//si lo tiene para sumar el laboratorio tiene que al menos acumular sumando el total y el laboratorio 9.6 suponiendo 20 en el laboratorio                   
                   auxiliar = parcial + (20*Integer.valueOf( resultado.getString("xciento_lab").substring(0, 2)));//revisando que al menos emulando el 20 legue a 9.6
                  // System.out.println("LABORATORIO parcial="+parcial+" emulado="+auxiliar+"   ---  real nota lab:"+resultado.getInt("Lab")+"x"+resultado.getString("xciento_lab"));
                   if(auxiliar>=9.60){//si se cumple se suma el laboratorio
                        parcial = parcial + (double)( resultado.getInt("Lab") * Integer.valueOf( resultado.getString("xciento_lab").substring(0, 2) ) );
                   }
               }
               
hilo.setAvance(70);//agregando informacion de progreso al hilo               
               total = Integer.valueOf(String.valueOf( Math.round(parcial/100.0) ) );//convirtiendo a entero               
               System.out.println("Parcial: "+df.format(parcial/100.0)+" -> |total: "+df2.format(total)+" definitiva_acta="+df2.format(resultado.getInt("DEF"))+"|");
                           
               //comparando los calculos con la definitiva a ver si coincida
               if(resultado.getInt("DEF")!=total) {System.out.println(" -- INCONSISTENCIAS EN LOS CALCULOS: CEDULA:"+resultado.getString("cedula")
                                                                                          +" CODIGO: "+resultado.getString("codmat")
                                                                                          +" SECCION: "+resultado.getString("seccion")
                                                                                          +" PERIODO: "+resultado.getString("peraca")
                                                                                          +" verificado: "+total+" acta: "+resultado.getInt("DEF")+"\n");
                    comentario.append("\n -- INCONSISTENCIAS EN LOS CALCULOS: CEDULA:"+resultado.getString("cedula")
                                                                                          +" CODIGO: "+resultado.getString("codmat")
                                                                                          +" SECCION: "+resultado.getString("seccion")
                                                                                          +" PERIODO: "+resultado.getString("peraca")
                                                                                          +" verificado: "+df2.format(total)+" acta: "+df2.format(resultado.getInt("DEF"))+"\n");
                    
                    cant_errores = cant_errores + 1;                                                                       
               }
               
               
               //determinando los aprobados y reprobados(quienes son)
               if(total>=10){aprobados = aprobados + 1;}else{reprobados.add("CEDULA:"+resultado.getString("cedula")+" CODIGO: "+resultado.getString("codmat")+" SECCION: "+resultado.getString("seccion")+" PERIODO: "+resultado.getString("peraca")+" verificado: "+df2.format(total));}
               
               
               
hilo.setInformacion_1(resultado.getString("cedula")+" "+resultado.getString("codmat")+" "+resultado.getString("seccion"));               
               
                

               
            }//fin recorrido del ciclo
            
            sentencia.close();
            resultado.close();
            
            System.out.println("\nNOTAS PROCESADAS = "+(procesados+1) );
            comentario.append("\nNOTAS PROCESADAS = "+(procesados+1) );
            System.out.println("ERRORES TOTALES DETECTADOS = "+cant_errores);            
            comentario.append("\nERRORES TOTALES DETECTADOS = "+cant_errores);

            this.lista_reprobados(reprobados, comentario);//mostrando los reprobados
            
hilo.setAvance(100);//agregando informacion de progreso al hilo            
            
        } catch (SQLException ex) {
            Logger.getLogger(procesamientos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(procesamientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    
    
    
    


}   
    

public void lista_reprobados(LinkedList<String> r, JTextArea v){

        v.append("\n+++++++++++++++++++++++ESTUDIANTES REPROBADOS+++++++++++++++++++++++++\n\n");
        v.append("TOTAL REPROBADOS: "+r.size()+"\n");
        
        for(int i=0; i<r.size(); i++){//recorriendo registros
            
           v.append("\t"+(i+1)+"-"+r.get(i)+"\n");
            
        }//fin recorrido registro


}
    
    
    
    
    
    
    
    
//es solo para probar los metodos
 public static void main(String[] args) {
        // TODO code application logic here
        procesamientos pro = new procesamientos();
        imagenes ima = new imagenes();
//        pro.verificar_calculos(new conexion_base_de_datos().getConexion(), "1-2011");
        //m.archivo_excel();
        //pro.archivos_analizar(ima.apertura_general_directorio("SELECCIONE DIRECTORIO DONDE SE ENCUENTRAN LAS ACTAS A PROCESAR"));//ajustar el textarea
        System.exit(0);


    }





}//fin de la clase
