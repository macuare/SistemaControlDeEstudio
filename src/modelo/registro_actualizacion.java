/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//DESARROLLADO POR EL ING CUSATTI ANDY
package modelo;

import javax.swing.JOptionPane;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

/**
 *
 * @author AACM
 */
public class registro_actualizacion {

    private String [] b1,b2,b3,b4,b5;
    private String guardar;

    public registro_actualizacion() {
       
    }
   

   

    public registro_actualizacion(String[] b1, String[] b2, String[] b3, String[] b4, String[] b5, String guardar) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.guardar= guardar;

    }

    public String[] getB1() {
        return b1;
    }

    public void setB1(String[] b1) {
        this.b1 = b1;
    }

    public String[] getB2() {
        return b2;
    }

    public void setB2(String[] b2) {
        this.b2 = b2;
    }

    public String[] getB3() {
        return b3;
    }

    public void setB3(String[] b3) {
        this.b3 = b3;
    }

    public String[] getB4() {
        return b4;
    }

    public void setB4(String[] b4) {
        this.b4 = b4;
    }

    public String[] getB5() {
        return b5;
    }

    public void setB5(String[] b5) {
        this.b5 = b5;
    }
//DESARROLLADO POR EL ING CUSATTI ANDY
    public String getGuardar() {
        return guardar;
    }

    public void setGuardar(String guardar) {
        this.guardar = guardar;
    }


   
public void inicializar(){
    /*StyledDocument doc = pa1.getStyledDocument();
    Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.
    DEFAULT_STYLE);
    Style regular = doc.addStyle("regular", def);
    StyleConstants.setFontFamily(regular, "Century Gothic");
    StyleConstants.setAlignment(regular, StyleConstants.ALIGN_CENTER);*/
//DESARROLLADO POR EL ING CUSATTI ANDY
String x[]={ "A_1: Profesión universitaria o su equivalente. Incluye a propietarios de empresas o\n"+
             "industrias de gran productividad, ejecutivos y comerciantes de alto nivel.\n"+
             "Gerente de empresas con más de 100 empleados\n\n",
             "A_2: Profesión Técnica Superior.\n\n",
             "A_3: Empleado sin profesión Universitaria, técnico medio, pequeños comerciantes\n"+
             "o productores. Incluye a egresados de escuelas normales y de escuelas técnicas\n"+
             "del nivel medio y los egresados de bachillerato o empleados de organismos públicos\n"+
             "y privados.\n\n",
             "A_4: Obreros especializados y parte de los trabajadores del sector informal de la economía\n"+
             "(sin título profesional). Incluye a chóferes, electricista, plomeros y similares especializados.\n"+
             "Deben tener la primaria aprobada y haber realizado cursos para alcanzar la especialización.\n\n",
             "A_5: Obreros no especilizados. Incluye a jornaleros, trabajadores del aseo urbano, obreros de\n"+
             "la construcción y similares. También se consideran a los trabajadores del sector informal con\n"+
             "primaria incompleta. Todos aquellos trabajadores con ingresos inestables.\n\n",
             "¿Cuál es la ocupación del jefe o jefa de familia?"
            };

this.setB1(x);


String y[]={"B_1: Profesión Universitaria o su equivalente. Se incluye a los egresados de universidades,\n"+
            "politécnicos y pedagógicos.\n\n",
            "B_2: Secundaria completa. Técnico superior completo. Se incluye a egresadas de bachillerato\n"+
            "(con titulo), institutos de técnico medio y de institutos técnicos superiores (sin titulo).\n\n",
            "B_3: Secundaria incompleta. Técnica inferior. Se considera a aquellas personas que al menos\n"+
            "han cursado el segundo año de educación media o normal sin haber dejado materias y\n"+
            "quienes tienen cursos definidos y debidamente calificados y han obtenido certificado.\n\n",
            "B_4: Instrucción primaria o alfabeta (con algún grado de instrucción primaria). Se incluye a \n"+
            "la madre que ha egresado del sexto grado aprobado con su certificado y aquellas que\n"+
            "hayan cursado 1er y 2do año incompleto de educación media.\n\n",
            "B_5: Analfabeta. Personas que no saben leer ni escribir.\n\n",
            "¿Cuál es el nivel de instrucción de la madre?"
            };
this.setB2(y);

String z[]={"C_1: Fortuna heredada o adquirida. La familia vive de rentas\n"+
            "producidas por dinero heredado o adquirido.\n\n",
            "C_2: Los ingresos consisten en honorarios profesionales, ganancias y beneficios.\n"+
            "Ejemplo: Ingresos obtenidos como productos de la participación en una empresa\n"+
            "o negociaciones directas o indirectas. No dependen de un sueldo.\n\n",
            "c_3: Salario mensual. Remuneración cancelada generalmente mensual o quincenal.\n\n",
            "c_4: Salario semanal, por día, entrada a destajo. Se incluye a personas que ejecutan trabajos\n"+
            "ocasionales y aquellas que perciben ganancias inestables, por ejemplo, los taxistas.\n"+
            "Cuando la fuente de ingreso proviene de actividades de buhonería siempre es un destajo\n"+
            "independientemente del monto.\n\n",
            "c_5: Ingresos obtenidos por ayudas o subsidios otorgados por organismos públicos o privados o\n"+ 
            "de donaciones de origen familiar. No se incluye en esta categoría las indemnizaciones del\n"+
            "Seguro Social. (cesantes, enfermos, jubilados, pensionados). Si la familia depende de tales\n"+
            "indemnizaciones, rellene el óvalo del número de la fuente de ingreso que tenía cuando trabajaba.\n\n",
            "¿Cuál es la fuente de ingreso de la familia?"
            };
this.setB3(z);


String w[]={"D_1: Vivienda con óptimas condiciones sanitarias, mansión, quinta o apartamento con ambientes\n"+
            "de gran lujo que ofrecen máximas comodidades y confort. Se incluye apartamento tipo\n"+
            "pent-house muy lujosos con terrazas, varios recibos (sala o salon) y en ambos casos las\n"+
            "habitaciones deben ser amplias y suficientes para el número de miembros de la familia,\n"+
            "una persona por habitación con un baño privado. Donde es costumbre disponer de transporte\n"+
            "propio para movilizarse, y en general, mas de un transporte.\n\n",
            "D_2: Casa o apartamento de menor categoría, espacioso, muy cómodo con áreas bien diferenciadas,\n"+
            "habitaciones suficientes para los miembros de la familia y un número adecuado de baños\n"+
            "(no se considera indispensable un baño por habitación). En casa o apartamento se exigen\n"+
            "condiciones sanitarias óptimas y en excelentes condiciones internas. Ubicada en zonas residenciales\n"+
            "elegantes.\n\n",
            "D_3: Vivienda en buenas condiciones sanitarias en espacios reducidos, casa o apartamento modesto,\n"+
            "bien construido y en buenas condiciones sanitarias, condiciones adecuadas de iluminación, aire\n"+
            "y espacios separados para cocina, baños y habitaciones. Se incluye apartamentos tipo estudio\n"+
            "ocupado por una sola persona o máximo una pareja. Pueden estar ubicadas en zonas residenciales,\n"+
            "comerciales e industriales.\n\n",
            "D_4: Vivienda con ambientes espaciosos o reducidos con deficiencias en algunas condiciones sanitarias.\n"+
            "Este tipo de viviendas comprende las casas o apartamentos de construcción sólida, pero con diferencia\n"+
            "en alguna de las siguientes condiciones: número de baños, agua, electricidad, ventilación y luz natural,\n"+
            "con exceso de personas. Generalmente está ubicada en urbanizaciones o barrios, creados dentro de\n"+
            "tipo de ''interés social'' o en barrios obreros o similares en zonas comerciales o industriales.\n\n",
            "D_5: Rancho o vivienda con una sola habitación y condiciones sanitarias muy deficientes. Comprende la\n"+
            "vivienda improvisada, construida con materiales de desecho (rancho, barracas, casa de vecindad)\n"+
            "o de construcción sólida carentes de servicios básicos y en su mayoría con servicios de toma ilegal.\n"+
            "Se incluye asentamientos de damnificados, las viviendas rurales y algunas calificadas como de interés\n"+
            "social que no reúnen las condiciones de espacio, ventilación, iluminación y que está en condiciones\n"+
            "precarias.\n\n",
            "¿Cuáles son las condiciones de alojamiento de la familia?"
            };

this.setB4(w);


}




//DESARROLLADO POR EL ING CUSATTI ANDY






   public void muestra(String mensaje,String titulo){


            JOptionPane.showMessageDialog(null, mensaje ,titulo, 2);
   }

    













}
