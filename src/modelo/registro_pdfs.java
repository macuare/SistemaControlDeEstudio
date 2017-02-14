/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class registro_pdfs {




    public registro_pdfs() {
    }












public void super_listado(Connection con, String nucleo_sede ,String carrera, String ruta){
        try {
            Statement s = null;
            ResultSet r = null;

            s = con.createStatement();
            r = s.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE nucleo='"+nucleo_sede+"' AND carrera='"+carrera+"'; ");
              //r = s.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE nucleo='NUCLEO ARAGUA - SEDE MARACAY' AND carrera='"+carrera+"'; ");
            //r = s.executeQuery("SELECT * FROM control_de_estudio.orden_merito ;");
             // r = s.executeQuery("SELECT * FROM control_de_estudio.datos_academicos WHERE cedula='17703531';");
            while(r.next()){
                //System.out.println("fila: "+r.getRow()+"   cedula: "+r.getString("cedula"));

                registro_ingenieria ri = new registro_ingenieria();
                ri.verificar_alumno(new conexion_base_de_datos().getConexion(),r.getString("cedula"));
                //this.constancia_de_culminacion("d://CULMINACION/constancia_de_culmincacion_"+ri.getCedula()+".pdf",ri.getCedula(),ri.getEstudiante().replaceAll(",", "").trim());
                this.constancia_de_culminacion(ruta+"constancia_de_culmincacion_"+ri.getCedula()+".pdf",ri.getCedula(),ri.getEstudiante().replaceAll(",", "").trim(),true);

            }

            s.close();
            r.close();


        } catch (SQLException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
            }

        }




}



public void documento_pdf(){

    


        FileOutputStream archivo = null;
        try {
            archivo = new FileOutputStream("d://NUEVO_PDF_VERSION_5.pdf"); //ruta del archivo
            Document doc = new Document(PageSize.LETTER); //creando documento
            doc.setMargins(10f, 10f, 30f, 30f);
            PdfWriter.getInstance(doc, archivo); //creando instancia pdf writer

            doc.open();//abriendo documento pdf

            doc.add(new Paragraph("HOLA ESTO ES UNA PRUEBA DE LA NUEVA VERSION DE LOS ARCHIVOS PDF!"));//agregando contenido

            doc.close();//cerrando el documento pdf
            

        } catch (DocumentException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


}


public void constancia_de_culminacion(String ruta, String cedula, String estudiante, boolean activar) {

if(activar){
    System.out.println("CONSTANCIA DE CULMINACION");
 FileOutputStream archivo = null;
        try {
            archivo = new FileOutputStream(ruta); //ruta del archivo
            //Rectangle tamaño = new Rectangle(urx, ury);
            Document doc = new Document(PageSize.LETTER,60f,60f,80f,30f); //creando documento
            PdfWriter escritor = PdfWriter.getInstance(doc, archivo); //creando instancia pdf writer
            //PdfWriter.getInstance(doc, archivo);
            doc.addTitle("REPORTE INSCRIPCIÓN");
            doc.addSubject("CONSTANCIA DE CULMINACION DE ESTUDIOS");
            doc.addKeywords("nombres,cedula,carrera,fechas");
            doc.addCreator("Software Inscripcion UNEFA 2010");
            doc.addAuthor("Ing. Cusatti Andy");
            doc.addHeader("Expires", "0");


            doc.open();//abriendo documento pdf
                Paragraph parrafo = new Paragraph();//parrafos
                File direccion = new File(getClass().getResource("/letra/arial.ttf").toURI());//registrando nueva letra
                FontFactory.register(direccion.getAbsolutePath(),"ARIAL");
               
                
                Font[] fuente = new Font[10];//configuracion del tamaño y el tipo de letra
                fuente[0]= FontFactory.getFont("ARIAL",6,Font.NORMAL);
                fuente[1]= FontFactory.getFont("ARIAL",12,Font.NORMAL);
                fuente[2]= FontFactory.getFont("ARIAL",14,Font.NORMAL);
                fuente[3]= FontFactory.getFont("ARIAL",14,Font.BOLD);
                fuente[4]= FontFactory.getFont("ARIAL",18,Font.NORMAL);
                fuente[5]= FontFactory.getFont("ARIAL",8,Font.NORMAL);
                fuente[6]= FontFactory.getFont("ARIAL",12,Font.BOLDITALIC);
                fuente[7]= FontFactory.getFont("ARIAL",10,Font.BOLD);
                fuente[8] = FontFactory.getFont("ARIAL",10,Font.BOLDITALIC);
                fuente[9]= FontFactory.getFont("ARIAL",12,Font.BOLD);
               

                this.cuerpo(doc, parrafo, fuente,cedula, estudiante);//contenido del cuerpo del pdf


                this.firma_secretaria_general(doc, fuente, "MARÍA JOSÉ TORRES LÓPEZ", "DOCTORA");//casilla de firmas

                this.pie_pagina_documentado(escritor, doc,new Paragraph("NMDD/AGF", fuente[5]));//siglas al final de la hoja





            
            doc.close();//cerrando el documento pdf


        } catch (URISyntaxException ex) {System.out.println("urisintaxis");
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {System.out.println("exception documento");
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {System.out.println("ruta no encontrada");
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {System.out.println("problema de cerrado");
                Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    }//fin activacion

}


public void cuerpo(Document doc, Paragraph parrafo, Font[] fuente, String cedula, String estudiante) throws DocumentException{

    this.parrafos_2(doc, parrafo, fuente, "REPÚBLICA BOLIVARIANA DE VENEZUELA");//linea 0
    this.parrafos_2(doc, parrafo, fuente, "MINISTERIO DEL PODER POPULAR PARA LA DEFENSA");//linea 0
    this.parrafos_2(doc, parrafo, fuente, "UNIVERSIDAD NACIONAL EXPERIMENTAL");//linea 0
    this.parrafos_2(doc, parrafo, fuente, "POLITÉCNICA DE LA FUERZA ARMADA BOLIVARIANA");//linea 0
    this.parrafos_2(doc, parrafo, fuente, "NÚCLEO ARAGUA - SEDE MARACAY");//linea 0

   /* parrafo = new Paragraph("REPÚBLICA BOLIVARIANA DE VENEZUELA\n"
                          + "MINISTERIO DEL PODER POPULAR PARA LA DEFENSA\n"
                          + "UNIVERSIDAD NACIONAL EXPERIMENTAL\n"
                          + "POLITÉCNICA DE LA FUERZA ARMADA BOLIVARIANA\n"
                          + "NÚCLEO ARAGUA - SEDE MARACAY", fuente[1]);//texto a escribir
    parrafo.setAlignment(Element.ALIGN_CENTER);//alinenacion
    parrafo.setSpacingAfter(70f);
    doc.add(parrafo);//escribiendolo en el documento*/


    parrafo = new Paragraph("CONSTANCIA DE CULMINACIÓN DE ESTUDIOS",fuente[3]);//titulo
    parrafo.setAlignment(Element.ALIGN_CENTER);
    parrafo.setSpacingBefore(60f);
    parrafo.setSpacingAfter(50f);
    doc.add(parrafo);


   /* parrafo = new Paragraph("Quien suscribe, secretaria de la Universidad Nacional Experimental "
                          +"Politécnica de la Fuerza Armada Bolivariana (UNEFA), hace constar que el (la) "
                          + "ciudadano (la): ANDY ALDEMARO CUSATTI MACUARE, titular de la Cédula de "
                          + "Identidad N°. 15196976, ha culminado sus obligaciones académicas con la "
                          + "Universidad y solo tiene pendiente el Acto de Graduación previsto para el mes "
                          + "de julio del año en curso, donde le será conferido el Título de INGENIERO "
                          + "ELECTRÓNICO.",fuente[1]);*/
        Chunk andy = new Chunk();

        this.parrafos(doc, parrafo, fuente,1,0f, "    Quien suscribe, Secretaria General (E) de la Universidad Nacional Experimental");//linea 1
        this.parrafos(doc, parrafo, fuente,1,0f, "Politécnica de la Fuerza Armada Bolivariana (UNEFA), hace constar que el (la)");//linea 2
        
        Paragraph p = new Paragraph();
        Phrase x = new Phrase(); x.add(this.incrustar(doc, fuente,1, "ciudadano (a): ")); x.add(this.incrustar(doc, fuente,6, estudiante)); x.add(this.incrustar(doc, fuente,1,", titular de la Cédula de"));
        p.add(x); p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);p.setSpacingAfter(10f); doc.add(p);
        
        p = new Paragraph();
        x = new Phrase(); x.add(this.incrustar(doc, fuente,1, "Identidad N°. ")); x.add(this.incrustar(doc, fuente,6, cedula)); x.add(this.incrustar(doc, fuente,1,", ha culminado sus obligaciones académicas con la"));
        p.add(x); p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);p.setSpacingAfter(10f); doc.add(p);

        this.parrafos(doc, parrafo, fuente,1,0f, "Universidad y solo tiene pendiente el Acto de Graduación previsto para el mes");//linea 5
        
        p = new Paragraph();
        x = new Phrase(); x.add(this.incrustar(doc, fuente,1, "de julio del año en curso, donde le será conferido el Título de ")); x.add(this.incrustar(doc, fuente,9, "TÉCNICO"));
        p.add(x); p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);p.setSpacingAfter(10f); doc.add(p);

        this.parrafos(doc, parrafo, fuente,9,0f, "SUPERIOR UNIVERSITARIO EN ENFERMERÍA.                                             ");//linea 7

       

        parrafo = new Paragraph("",fuente[1]);//espacio en blanc0
        parrafo.setAlignment(Element.ALIGN_CENTER);
        parrafo.setSpacingAfter(25f);
        doc.add(parrafo);

        this.parrafos(doc, parrafo, fuente,1,0f ,"    Constancia que se expide a petición de la parte interesada en Maracay a");//linea 8
        this.parrafos(doc, parrafo, fuente,1,0f ,"los doce días del mes de mayo de 2011                                           ");//linea 9

        parrafo.setSpacingAfter(80f);
        doc.add(parrafo);
    //RECORDAR QUE HAY QUE HACER UN METODO QUE COMPLETE UNA LINEA CON ESPACIOS EN BLANCO. SE SUPONE LA LONGITUD DE TODA LA LINEA...SE LE RESTA LO QUE SE VA
        //A COLOCAR Y EL SOBRANTE SE COMPLETA CON ESPACIOS EN BLANCO
    
}




//----------------------------ACCESORIOS PARA EL PDF----------------------------------------
public void parrafos(Document doc, Paragraph parrafo, Font[] fuente,int tipo, Float antes, String linea) throws DocumentException{
   
    parrafo = new Paragraph(linea,fuente[tipo]);//linea a escribir
    parrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
    parrafo.setSpacingAfter(10f);
    parrafo.setSpacingBefore(antes);
    doc.add(parrafo);

}

public void parrafos_2(Document doc, Paragraph parrafo, Font[] fuente, String linea) throws DocumentException{

    parrafo = new Paragraph(linea,fuente[7]);//linea a escribir
    parrafo.setAlignment(Element.ALIGN_CENTER);
    parrafo.setSpacingAfter(-2f);
    doc.add(parrafo);

}

public Chunk incrustar(Document doc, Font[] fuente, int tipo, String texto ) throws DocumentException{


    Chunk andy = new Chunk(texto, fuente[tipo]);
    return andy;

}




public void escudo(Document doc){
        try {
            Image escudo = Image.getInstance(getClass().getResource("/actualizacion_de_datos/imagenes/escudo3.jpg"));
            escudo.scaleAbsolute(50, 60);
            Chunk insercion = new Chunk(escudo, 0, -1);

            
            try {
                
                doc.add(insercion);//agregando al documento pdf

            } catch (DocumentException ex) {
                Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (BadElementException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        }


       

}

public void firma_secretaria_general(Document doc, Font[] fuente ,String secretaria_caracas, String grado_estudio) throws DocumentException{

                PdfPTable firmas = new PdfPTable(1);
                firmas.setWidthPercentage(50f);
                firmas.setHorizontalAlignment(Element.ALIGN_RIGHT);

                PdfPCell contenido = null;
                Phrase frase = new Phrase();

                frase.add(this.incrustar(doc, fuente,6, secretaria_caracas+"\n\n"));
                frase.add(this.incrustar(doc, fuente, 8, grado_estudio+"\n\n"));
                frase.add(this.incrustar(doc, fuente, 9, "SECRETARIA GENERAL (E)"));

                //jefe de control de estudio
                //contenido = new PdfPCell(new Paragraph(secretaria_caracas+"\n\n"+grado_estudio+"\n\nSECRETARIA DE LA UNEFA",fuente[6]));
                contenido = new PdfPCell(frase);
                contenido.setHorizontalAlignment(Element.ALIGN_CENTER);
                contenido.setBorder(Rectangle.TOP);
                firmas.addCell(contenido);
                doc.add(firmas);

}

public void pie_pagina(PdfWriter escritor, Document documento, int pagina){
     Phrase frase = new Phrase("pagina: "+pagina);
     PdfContentByte cb = escritor.getDirectContent();

      ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, frase,((documento.right()-documento.left())/2)+documento.leftMargin(),documento.bottom()+10,0);


}

public void pie_pagina_documentado(PdfWriter escritor, Document documento, Paragraph comentario){
     Phrase frase = new Phrase(comentario);
     PdfContentByte cb = escritor.getDirectContent();

      ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, frase,documento.leftMargin(),documento.bottom()+10,0);

}


public static void main(String args[]){

    System.out.println("ARMARNDO ARCHIVO PDF....");
    registro_pdfs pdf = new registro_pdfs();
    conexion_base_de_datos cbd = new conexion_base_de_datos();
  //  registro_ingenieria ri = new registro_ingenieria();
/*

    ri.verificar_alumno(cbd.getConexion(),"15196976");

        try {
            //pdf.documento_pdf();
            pdf.constancia_de_culminacion("d://CULMINACION/constancia_de_culmincacion_"+ri.getCedula()+".pdf",ri.getCedula(),ri.getEstudiante().replaceAll(",", ""));
        } catch (URISyntaxException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(registro_pdfs.class.getName()).log(Level.SEVERE, null, ex);
        }
 *
 */
//    pdf.super_listado(cbd.getConexion(), "TSU EN ENFERMERIA");
    System.out.println("Archivo pdf creado....");
//23801468,
}




}//fin de la clase
