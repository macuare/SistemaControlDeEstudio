/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//DESARROLLADO POR EL ING CUSATTI ANDY
/*
 * actualizacion.java
 *
 * Created on 17-mar-2009, 16:53:39
 */

package vista;


import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author AACM
 */
public class actualizacion extends javax.swing.JFrame {

    private String privi_actua;
    private String periodo_actual;



    /** Creates new form actualizacion */
    public actualizacion() {
      


        initComponents();
this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
      
   this.pais_nac.setSelectedItem("Venezuela");
   this.bd.setVisible(false);
   this.borrar.setVisible(false);
   this.getModalidad().setSelectedItem("Por Cuenta Propia (Pagina Web)");
   this.getUpi_año().setSelectedItem("2009");

//File fichero= new File("C://Documents and Settings/All Users/Escritorio/FOTOS_ACTUALIZACION");
//fichero.mkdir();

        grupo1.add(civil);
        grupo1.add(militar);
        grupo2.add(diurno);
        grupo2.add(nocturno);
        grupo3.add(no);
        grupo3.add(si);
       


        controlador.control_actualizacion ca=new controlador.control_actualizacion(this);
       
        elegir1.addActionListener(ca);
        elegir2.addActionListener(ca);
        elegir3.addActionListener(ca);
        elegir4.addActionListener(ca);
        enviar.addActionListener(ca);
        bd.addActionListener(ca);
        borrar.addActionListener(ca);
        modificar.addActionListener(ca);

        modificar.setVisible(false);//solo para actualizar


        
     

        militar.addChangeListener(ca);
        si.addChangeListener(ca);

        id.addItemListener((ItemListener) ca);
       

        paneles.addKeyListener((KeyListener) ca);
        cedula.addKeyListener(ca);
      //  direccion.addKeyListener(ca);

//DESARROLLADO POR EL ING CUSATTI ANDY

    }

    public JTabbedPane getPaneles() {
        return paneles;
    }

    public JButton getBd() {
        return bd;
    }

    public JButton getBorrar() {
        return borrar;
    }


   
  


//VARIABLES DATOS PERSONALES

     public JComboBox getResidencia() {
        return residencia;
    }

    public JTextField getApellidos() {
        return apellidos;
    }

    public JTextField getCedula() {
        return cedula;
    }

    public JTextField getCorreo() {
        return correo;
    }

    public JComboBox getDiscapacidad() {
        return discapacidad;
    }

    public JTextArea getDireccion() {
        return direccion;
    }

    public JComboBox getEdo_civil() {
        return edo_civil;
    }

    public JComboBox getEtnia() {
        return etnia;
    }

    public JComboBox getFn_año() {
        return fn_año;
    }

    public JComboBox getFn_dia() {
        return fn_dia;
    }

    public JComboBox getFn_mes() {
        return fn_mes;
    }

    public JLabel getFoto() {
        return foto;
    }

    public JComboBox getId() {
        return id;
    }

    public JComboBox getNacionalidad() {
        return nacionalidad;
    }

    public JTextField getNombres() {
        return nombres;
    }

    public JComboBox getPais_nac() {
        return pais_nac;
    }

    public JComboBox getSexo() {
        return sexo;
    }

    public JTextField getTelf_hab() {
        return telf_hab;
    }

    public JTextField getTelf_hab_otro() {
        return telf_hab_otro;
    }

    public JTextField getTelf_per() {
        return telf_per;
    }

    public JTextField getTelf_per_otro() {
        return telf_per_otro;
    }




//VARIABLES DATOS SOCIOECONOMICOS

    public JComboBox getDs_a() {
        return ds_a;
    }

    public JComboBox getDs_b() {
        return ds_b;
    }

    public JComboBox getDs_c() {
        return ds_c;
    }

    public JComboBox getDs_d() {
        return ds_d;
    }

    public JComboBox getDs_parentesco() {
        return ds_parentesco;
    }

    public JComboBox getDs_penal() {
        return ds_penal;
    }

    public JComboBox getDs_personas() {
        return ds_personas;
    }

    public JComboBox getDs_tiempo() {
        return ds_tiempo;
    }


//VARIABLES DATOS ACADEMICOS
    
 public JTextField getAtleta() {
        return atleta;
    }

    public JComboBox getBecado() {
        return becado;
    }

    public JComboBox getCarrera() {
        return carrera;
    }

    public JRadioButton getCivil() {
        return civil;
    }

    public JRadioButton getDiurno() {
        return diurno;
    }

    public JComboBox getFi_año() {
        return fi_año;
    }

    

    public JComboBox getFi_mes() {
        return fi_mes;
    }

    public JTextField getGrado() {
        return grado;
    }

    public JRadioButton getMilitar() {
        return militar;
    }

    public JComboBox getModalidad() {
        return modalidad;
    }

    public JRadioButton getNo() {
        return no;
    }

    public JRadioButton getNocturno() {
        return nocturno;
    }

    public JRadioButton getSi() {
        return si;
    }

    public JComboBox getTipo_estudiante() {
        return tipo_estudiante;
    }

    public JComboBox getUpi_año() {
        return upi_año;
    }

    public JComboBox getUpi_periodo() {
        return upi_periodo;
    }
    
    public JComboBox getCond_estudiante() {
        return cond_estudiante;
    }

    public JComboBox getNucleo() {
        return nucleo;
    }

    public void setNucleo(JComboBox nucleo) {
        this.nucleo = nucleo;
    }

    public JTextField getN_rusnieus() {
        return n_rusnieus;
    }

    public void setN_rusnieus(JTextField n_rusnieus) {
        this.n_rusnieus = n_rusnieus;
    }

    public String getPrivi_actua() {
        return privi_actua;
    }

    public void setPrivi_actua(String privi_actua) {
        this.privi_actua = privi_actua;
    }

    public JButton getModificar() {
        return modificar;
    }

    public void setModificar(JButton modificar) {
        this.modificar = modificar;
    }

    public JButton getEnviar() {
        return enviar;
    }

    public void setEnviar(JButton enviar) {
        this.enviar = enviar;
    }

    public String getPeriodo_actual() {
        return periodo_actual;
    }

    public void setPeriodo_actual(String periodo_actual) {
        this.periodo_actual = periodo_actual;
    }






public void limpiar(){
 //DATOS PERSONALES
  this.getId().setSelectedItem("Cedula");
  this.getCedula().setText("");
  this.getNombres().setText("");
  this.getApellidos().setText("");
  this.getFn_dia().setSelectedItem("1");
  this.getFn_mes().setSelectedItem("Enero");
  this.getFn_año().setSelectedItem("1901");
  this.getEtnia().setSelectedItem("No pertenezco a un pueblo indigena");
  this.getTelf_per().setText("");
  this.getTelf_per_otro().setText("");
  this.getTelf_hab().setText("");
  this.getTelf_hab_otro().setText("");
  this.getCorreo().setText("@");
  this.getSexo().setSelectedItem("M");
  this.getEdo_civil().setSelectedItem("SOLTERO (A)");
  this.getNacionalidad().setSelectedItem("VENEZOLANA");
  this.getPais_nac().setSelectedItem("Venezuela");
  this.getDiscapacidad().setSelectedItem("No poseo ninguna discapacidad");
  this.getDireccion().setText("");
  this.getResidencia().setSelectedItem("Residente");

 //DATOS SOCIO ECONOMICOS
 this.getDs_parentesco().setSelectedItem("Yo soy Jefe o Jefa del Hogar");
 this.getDs_a().setSelectedItem("A_1");
 this.getDs_b().setSelectedItem("B_1");
 this.getDs_c().setSelectedItem("C_1");
 this.getDs_d().setSelectedItem("D_1");
 this.getDs_a().setEnabled(false);
 this.getDs_b().setEnabled(false);
 this.getDs_c().setEnabled(false);
 this.getDs_d().setEnabled(false);
 this.getDs_personas().setSelectedItem("4 personas o menos");
 this.getDs_tiempo().setSelectedItem("1/2 hora o menos");
 this.getDs_penal().setSelectedItem("Sin condena penal");
//DESARROLLADO POR EL ING CUSATTI ANDY
 //DATOS ACADEMICOS
  this.getCivil().setSelected(true);
  this.getCarrera().setSelectedItem("INGENIERIA MECANICA");
  this.getDiurno().setSelected(true);
  this.getBecado().setSelectedItem("NO");
  this.getNo().setSelected(true);
  this.getTipo_estudiante().setSelectedItem("Regular");
 
  this.getFi_mes().setSelectedItem("Enero");
  this.getFi_año().setSelectedItem("2000");
  
  this.getUpi_periodo().setSelectedItem("1");
  this.getUpi_año().setSelectedItem("2009");
  this.getCond_estudiante().setSelectedItem("Alumno Regular");
  this.getModalidad().setSelectedItem("Por Cuenta Propia (Pagina Web)");

  
  this.getN_rusnieus().setText("00000");

}












    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        grupo2 = new javax.swing.ButtonGroup();
        grupo3 = new javax.swing.ButtonGroup();
        grupo4 = new javax.swing.ButtonGroup();
        paneles = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        cedula = new javax.swing.JTextField();
        id = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nombres = new javax.swing.JTextField();
        apellidos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fn_dia = new javax.swing.JComboBox();
        fn_mes = new javax.swing.JComboBox();
        fn_año = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox();
        edo_civil = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        nacionalidad = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        pais_nac = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        etnia = new javax.swing.JComboBox();
        telf_per = new javax.swing.JTextField();
        telf_per_otro = new javax.swing.JTextField();
        telf_hab = new javax.swing.JTextField();
        telf_hab_otro = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        discapacidad = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        direccion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        residencia = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        ds_parentesco = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        ds_personas = new javax.swing.JComboBox();
        ds_tiempo = new javax.swing.JComboBox();
        ds_a = new javax.swing.JComboBox();
        ds_b = new javax.swing.JComboBox();
        ds_c = new javax.swing.JComboBox();
        ds_d = new javax.swing.JComboBox();
        elegir1 = new javax.swing.JButton();
        elegir2 = new javax.swing.JButton();
        elegir3 = new javax.swing.JButton();
        elegir4 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        ds_penal = new javax.swing.JComboBox();
        bd = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        civil = new javax.swing.JRadioButton();
        militar = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        grado = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        diurno = new javax.swing.JRadioButton();
        nocturno = new javax.swing.JRadioButton();
        carrera = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        si = new javax.swing.JRadioButton();
        no = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        atleta = new javax.swing.JTextField();
        becado = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        fi_mes = new javax.swing.JComboBox();
        fi_año = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        upi_año = new javax.swing.JComboBox();
        upi_periodo = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        modalidad = new javax.swing.JComboBox();
        tipo_estudiante = new javax.swing.JComboBox();
        jLabel42 = new javax.swing.JLabel();
        enviar = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        cond_estudiante = new javax.swing.JComboBox();
        jLabel45 = new javax.swing.JLabel();
        nucleo = new javax.swing.JComboBox();
        n_rusnieus = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        modificar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ACTUALIZACION DE DATOS...Desarrollado por Ing. Cusatti Andy");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        paneles.setName("paneles"); // NOI18N

        cedula.setToolTipText("INSERTE SU CEDULA POR FAVOR SIN SEPARADORES EJM: 12054658");
        cedula.setName("cedula"); // NOI18N

        id.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cedula", "Pasaporte" }));
        id.setToolTipText("SI ES EXTRANJERO SELECCIONE PASAPORTE");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Nº IDENTIFICACION");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("NOMBRES");

        nombres.setToolTipText("INSERTE SUS DOS NOMBRES EN CASO DE TENERLOS");

        apellidos.setToolTipText("INSERTE SUS DOS APELLIDOS  EN CASO DE TENERLOS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("APELLIDOS");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("FECHA DE NACIMIENTO");

        fn_dia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        fn_dia.setToolTipText("DIA DE NACIMIENTO");

        fn_mes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        fn_mes.setToolTipText("MES DE NACIMIENTO");

        fn_año.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000" }));
        fn_año.setToolTipText("AÑO DE NACIMIENTO");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel3)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(nombres, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .add(apellidos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                                .add(id, 0, 106, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(cedula, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(fn_dia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(fn_mes, 0, 93, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(fn_año, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(id, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cedula, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(nombres, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(apellidos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(fn_dia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(fn_año, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(fn_mes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("SEXO");

        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));
        sexo.setToolTipText("SELECCIONE SU GENERO");

        edo_civil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SOLTERO (A)", "CASADO (A)", "DIVORCIADO (A)", "VIUDO (A)" }));
        edo_civil.setToolTipText("SELECCIONE SU ESTADO CIVIL");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("EDO. CIVIL");

        nacionalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VENEZOLANA", "EXTRANJERA" }));
        nacionalidad.setToolTipText("SELECCIONE SU NACIONALIDAD");
        nacionalidad.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("NACIONALIDAD");

        pais_nac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afganistan", "Africa del Sur", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda", "Antillas Holandesas", "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarusia", "Belgica", "Belice", "Benin", "Bermudas", "Bolivia", "Bosnia", "Botswana", "Brasil", "Brunei Darussulam", "Bulgaria", "Burkina Faso", "Burundi", "Butan", "Camboya", "Camerun", "Canada", "Cape Verde", "Chad", "Chile", "China", "Chipre", "Colombia", "Comoros", "Congo", "Corea del Norte", "Corea del Sur", "Costa de Marfíl", "Costa Rica", "Croasia", "Cuba", "Dinamarca", "Djibouti", "Dominica", "Ecuador", "Egipto", "El Salvador", "Emiratos Arabes Unidos", "Eritrea", "Eslovenia", "España", "Estados Unidos", "Estonia", "Etiopia", "Fiji", "Filipinas", "Finlandia", "Francia", "Gabon", "Gambia", "Georgia", "Ghana", "Granada", "Grecia", "Groenlandia", "Guadalupe", "Guam", "Guatemala", "Guayana Francesa", "Guerney", "Guinea", "Guinea-Bissau", "Guinea Equatorial", "Guyana", "Haiti", "Holanda", "Honduras", "Hong Kong", "Hungria", "India", "Indonesia", "Irak", "Iran", "Irlanda", "Islandia", "Islas Caiman", "Islas Faroe", "Islas Malvinas", "Islas Marshall", "Islas Solomon", "Islas Virgenes Britanicas", "Islas Virgenes (U.S.)", "Israel", "Italia", "Jamaica", "Japon", "Jersey", "JJordania", "Kazakhstan", "Kenia", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lesotho", "Libano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo", "Macao", "Macedonia", "Madagascar", "Malasia", "Malawi", "Maldivas", "Mali", "Malta", "Marruecos", "Martinica", "Mauricio", "Mauritania", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Mozambique", "Myanmar (Burma)", "Namibia", "Nepal", "Nicaragua", "Niger", "Nigeria", "Noruega", "Nueva Caledonia", "Nueva Zealandia", "Oman", "Pakistan", "Palestina", "Panama", "Papua Nueva Guinea", "Paraguay", "Peru", "Polinesia Francesa", "Polonia", "Portugal", "Puerto Rico", "Qatar", "Reino Unido", "Republica Centroafricana", "Republica Checa", "Republica Democratica del Congo", "Republica Dominicana", "Republica Eslovaca", "Reunion", "Ruanda", "Rumania", "Rusia", "Sahara", "Samoa", "San Cristobal-Nevis (St. Kitts)", "San Marino", "San Vincente y las Granadinas", "Santa Helena", "Santa Lucia", "Santa Sede (Vaticano)", "Sao Tome & Principe", "Senegal", "Seychelles", "Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka (Ceilan)", "Sudan", "Suecia", "Suiza", "Sur Africa", "Surinam", "Swaziland", "Tailandia", "Taiwan", "Tajikistan", "Tanzania", "Timor Oriental", "Togo", "Tokelau", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkmenistan", "Turquia", "Ucrania", "Uganda", "Union Europea", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe" }));
        pais_nac.setToolTipText("SELECCIONE PAIS DE NACIMIENTO");
        pais_nac.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("PAíS DE NACIMIENTO");

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel6)
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(152, 152, 152)
                        .add(sexo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel7Layout.createSequentialGroup()
                                .add(jLabel31)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jLabel7)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(pais_nac, 0, 205, Short.MAX_VALUE)
                            .add(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(nacionalidad, 0, 205, Short.MAX_VALUE)
                                    .add(jPanel7Layout.createSequentialGroup()
                                        .add(edo_civil, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(69, 69, 69)))))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(sexo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(edo_civil, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7))
                .add(18, 18, 18)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(nacionalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel31)
                    .add(pais_nac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        etnia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No pertenezco a un pueblo indigena", "Akawayo", "Amorua", "Añú", "Arawak", "Ayamán", "Baniva", "Baré", "Barí", "Camentza", "Chaima", "Cumanagoto", "Curripaco", "Eñepa", "Gayón", "Guanono", "Guazabara", "Hoti", "Inga", "Japreira ", "Jíwi", "Kariña", "Kubeo", "Kuiba", "Mako", "Ñengatú/Yeral", "Otra", "Pemón", "Piapoco", "Piaroa", "Pumé", "Puninave", "Quinaroe", "Sáliva", "Sanema/Shirian", "Sape", "Timotes", "Uruak/Arutani", "Wanaí/Mapoyo", "Warao", "Warekena", "Wayuu", "Yabarana/Mako", "Yanomami", "Ye'kuana", "Yukpa" }));
        etnia.setToolTipText("ETNIA A LA QUE PERTENECE");

        telf_per.setToolTipText("INGRESE EL NUMERO TELEFONICO QUE USUALMENTE USA");

        telf_per_otro.setToolTipText("TELEFONO QUE SIRVA PARA PODER UBICARLO FACILMENTE, FAMILIAR, VECINOS O AMIGOS");

        telf_hab.setToolTipText("INGRESE EL TELEFONO DE DONDE VIVE O RESIDE ACTUALMENTE");

        telf_hab_otro.setToolTipText("TELEFONO QUE SIRVA PARA PODER UBICARLO FACILMENTE, FAMILIAR, VECINO O AMIGOS");

        correo.setText("@");
        correo.setToolTipText("INGRESE SU CORREO ELECTRONICO, EN CASO DE QUE SEAN VARIOS SEPARELOS POR PUNTO Y COMA (;)");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("EMAIL");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("TELEFONO HABITACION");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("TELEFONO PERSONAL");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("ETNIA");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("DISCAPACIDAD?");

        discapacidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No poseo ninguna discapacidad", "Psíquica mental", "Sensorial visual", "Sensorial Auditiva", "Física (músculo-esquelética y del Sistema Nervioso Central)", "Mixta o plurideficiente", "Psíquica-intelectual" }));
        discapacidad.setToolTipText("SELECCIONE UNA DISCAPACIDAD SI POSEE ALGUNA");

        direccion.setColumns(20);
        direccion.setRows(5);
        direccion.setToolTipText("INGRESE SU DIRECCION ACTUAL DONDE VIVE");
        jScrollPane1.setViewportView(direccion);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("DIRECCION");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("CONDICION DE RESIDENCIA");

        residencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Residente", "Transeunte" }));

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jLabel29)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(discapacidad, 0, 327, Short.MAX_VALUE))
                    .add(jPanel10Layout.createSequentialGroup()
                        .add(jLabel28)
                        .add(18, 18, 18)
                        .add(residencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel29)
                    .add(discapacidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(26, 26, 26)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel28)
                    .add(residencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 30, Short.MAX_VALUE)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel11)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Otro");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Otro");

        jLabel46.setText("V 2.3");

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9Layout.createSequentialGroup()
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel10)
                            .add(jPanel9Layout.createSequentialGroup()
                                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel9Layout.createSequentialGroup()
                                            .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel4)
                                                .add(jLabel5))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9Layout.createSequentialGroup()
                                            .add(jLabel18)
                                            .add(32, 32, 32)))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .add(jLabel27)
                                        .add(32, 32, 32)))
                                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, telf_hab_otro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, telf_hab, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, telf_per_otro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, telf_per, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, correo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                            .add(jPanel9Layout.createSequentialGroup()
                                .add(jLabel12)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 48, Short.MAX_VALUE)
                                .add(etnia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 265, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(38, 38, 38)
                        .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel46))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9Layout.createSequentialGroup()
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel12)
                            .add(etnia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(telf_per, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(telf_per_otro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel18))
                        .add(18, 18, 18)
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(telf_hab, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(telf_hab_otro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel27))
                        .add(18, 18, 18)
                        .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel10)
                            .add(correo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 20, Short.MAX_VALUE)
                .add(jLabel46))
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(43, 43, 43)
                        .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 32, Short.MAX_VALUE)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(276, 276, 276))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        paneles.addTab("DATOS PERSONALES", jPanel2);

        jPanel8.setToolTipText("LLENE TODAS LAS OPCIONES POR FAVOR");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("¿Qué relación o parentesco tiene con el jefe o jefa del hogar?");

        ds_parentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yo soy Jefe o Jefa del Hogar", "Esposo(a) o compañero(a)", "Hijo(a) o hijastro(a)", "Yerno o nuera", "Padre, madre suegro(a)", "Hermano(a), cuñado(a)", "Sobrino(a)", "Otro Pariente", "No soy pariente" }));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("¿Cuál es la ocupación del jefe o jefa de familia?");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("¿Cuál es el nivel de instrucción de la madre?");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("¿Cuál es la fuente de ingreso de la familia?");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("¿Cuáles son las condiciones de alojamiento de la familia?");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("¿Cuántas personas integran su grupo familiar?");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("¿Cuánto es el tiempo que le tomará trasladarse de su");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText(" residencia a la institución seleccionada en su 1era opción?");

        ds_personas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4 personas o menos", "5 personas", "6 personas", "7 personas", "8 personas o más" }));

        ds_tiempo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1/2 hora o menos", "1/2 hora o más pero menos de una hora", "1 hora o mas pero menos de una hora y media", "1 hora y 1/2 o más pero menos de dos horas", "2 horas o más" }));

        ds_a.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A_1", "A_2", "A_3", "A_4", "A_5" }));
        ds_a.setEnabled(false);

        ds_b.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "B_1", "B_2", "B_3", "B_4", "B_5" }));
        ds_b.setEnabled(false);

        ds_c.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "C_1", "C_2", "C_3", "C_4", "C_5" }));
        ds_c.setEnabled(false);

        ds_d.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "D_1", "D_2", "D_3", "D_4", "D_5" }));
        ds_d.setEnabled(false);

        elegir1.setText("LEER PRIMERO");
        elegir1.setName("elegir1"); // NOI18N

        elegir2.setText("LEER PRIMERO");
        elegir2.setName("elegir2"); // NOI18N

        elegir3.setText("LEER PRIMERO");
        elegir3.setName("elegir3"); // NOI18N

        elegir4.setText("LEER PRIMERO");
        elegir4.setName("elegir4"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel30, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .add(jLabel32)
                            .add(jLabel33)
                            .add(jLabel34)
                            .add(jLabel36)
                            .add(jPanel8Layout.createSequentialGroup()
                                .add(31, 31, 31)
                                .add(ds_a, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(54, 54, 54)
                                .add(elegir1))
                            .add(jLabel38)))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(40, 40, 40)
                        .add(ds_parentesco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(43, 43, 43)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel8Layout.createSequentialGroup()
                                .add(ds_c, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(elegir3))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel8Layout.createSequentialGroup()
                                .add(ds_b, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(55, 55, 55)
                                .add(elegir2))))
                    .add(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel35))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(44, 44, 44)
                        .add(ds_d, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(56, 56, 56)
                        .add(elegir4))
                    .add(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel37))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(43, 43, 43)
                        .add(ds_personas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(45, 45, 45)
                        .add(ds_tiempo, 0, 345, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel30)
                .add(13, 13, 13)
                .add(ds_parentesco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel32)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ds_a, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(elegir1))
                .add(15, 15, 15)
                .add(jLabel33)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ds_b, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(elegir2))
                .add(2, 2, 2)
                .add(jLabel34)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ds_c, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(elegir3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel35)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ds_d, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(elegir4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 14, Short.MAX_VALUE)
                .add(jLabel36)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(ds_personas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel37)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel38)
                .add(11, 11, 11)
                .add(ds_tiempo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("PRIVADO DE LIBERTAD");

        ds_penal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sin condena penal", "Cumpliendo condena penal" }));
        ds_penal.setToolTipText("SELECCIONE ALGUNO SI POSEE COMPROMISO CON LA LEY PENITENCIARIA");

        bd.setText("BD");

        borrar.setText("BORRAR");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(92, 92, 92)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel43)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(bd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(ds_penal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(54, 54, 54)
                        .add(borrar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .add(jPanel3Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jLabel43)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ds_penal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 284, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(borrar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(69, 69, 69))
        );

        paneles.addTab("DATOS SOCIOECONOMICOS", jPanel3);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("CONDICION");

        civil.setSelected(true);
        civil.setText("CIVIL");

        militar.setText("MILITAR");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Grado - Fuerza:");

        grado.setText("CIVIL");
        grado.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("TURNO");

        diurno.setSelected(true);
        diurno.setText("DIURNO");

        nocturno.setText("NOCTURNO");

        carrera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INGENIERIA MECANICA", "LIC. EDUCACION INTEGRAL", "LIC. CONTADURIA PUBLICA", "LIC. ECONOMIA SOCIAL", "TSU EN TURISMO", "INGENIERIA AERONAUTICA", "INGENIERIA CIVIL", "INGENIERIA ELECTRICA", "INGENIERIA ELECTRONICA", "INGENIERIA DE SISTEMAS", "INGENIERIA DE TELECOMUNICACIONES", "TSU EN ENFERMERIA", "LIC. EN ADMINISTRACION Y GESTION MUNICIPAL", "LIC. EN ENFERMERIA", "LIC. EN ADMINISTRACION DE DESASTRE", "TSU EN MECANICA DENTAL", "LIC. EN TURISMO", "TSU EN COMUNICACIONES Y ELECTRONICA", "TSU EN ANALISIS Y DISEÑO DE SISTEMAS" }));
        carrera.setToolTipText("SELECCIONE LA CARRERA EN LA QUE SE ENCUENTRA");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("CARRERA");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel5Layout.createSequentialGroup()
                                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel5Layout.createSequentialGroup()
                                        .add(civil)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 317, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel5Layout.createSequentialGroup()
                                        .add(militar)
                                        .add(18, 18, 18)
                                        .add(jLabel20)
                                        .add(18, 18, 18)
                                        .add(grado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                                .add(4, 4, 4))
                            .add(jLabel21)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel22)
                            .add(carrera, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(19, 19, 19)
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(nocturno)
                            .add(diurno)
                            .add(jLabel23))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .add(jLabel21)
                .add(3, 3, 3)
                .add(civil)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(militar)
                    .add(grado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel20))
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jLabel22)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(carrera, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(7, 7, 7)
                        .add(jLabel23)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(diurno)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nocturno)))
                .add(11, 11, 11))
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("ATLETA DE COMPETENCIA?");

        si.setText("SI");

        no.setSelected(true);
        no.setText("NO");

        jLabel26.setText("Especifique");

        atleta.setText("NO");
        atleta.setToolTipText("SI ES ATLETA DE COMPETENCIA ESPECIFIQUE CUAL");
        atleta.setEnabled(false);

        becado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO", "COMEDOR", "PREPARADURIA", "TRABAJO", "ALTO RENDIMIENTO", "FUNDA AYACUCHO", "OTRO" }));
        becado.setToolTipText("SELECCIONE UNA OPCION SI ES BECADO DE LA UNIVERSIDAD");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("BECADO?");

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel24)
                    .add(becado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 102, Short.MAX_VALUE)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(no)
                    .add(si)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jLabel26)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(atleta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel25))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jLabel24)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(becado, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jLabel25)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(si)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(no)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel26)
                    .add(atleta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fi_mes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        fi_mes.setToolTipText("MES DE INGRESO");

        fi_año.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021" }));
        fi_año.setSelectedIndex(12);
        fi_año.setToolTipText("AÑO DE INGRESO");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Periodo de Ingreso");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("Modalidad de Ingreso");

        upi_año.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021" }));
        upi_año.setSelectedIndex(12);
        upi_año.setToolTipText("AÑO DEL ULTIMO PERIODO INSCRITO");

        upi_periodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        upi_periodo.setToolTipText("FECHA DEL ULTIMO PERIODO INSCRITO O EL PERIODO ACTUAL QUE SE ENCUENTRA CURSANDO");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("Ultimo Periodo Inscrito");

        modalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CNU - RUSNIEUS", "Prueba Aptitud Academica", "Prueba Interna", "Convenio", "Por Cuenta Propia (Pagina Web)", "Equivalencia/reingreso/manual", "Mision Sucre", "Otros" }));
        modalidad.setToolTipText("LA MANERA COMO INGRESO A LA UNEFA");

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel39)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel41)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel40)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel11Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jPanel11Layout.createSequentialGroup()
                                .add(fi_mes, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(26, 26, 26)
                                .add(fi_año, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel11Layout.createSequentialGroup()
                                .add(upi_periodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(upi_año, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, modalidad, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel39)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(fi_mes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(fi_año, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel41)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(upi_año, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(upi_periodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jLabel40)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(modalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(36, 36, 36))
        );

        tipo_estudiante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular", "Egresado" }));
        tipo_estudiante.setToolTipText("SELECCIONE UN TIPO DE ESTUDIANTE");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("TIPO DE ESTUDIANTE");

        enviar.setText("ENVIAR");
        enviar.setToolTipText("ENVIAR TODA LA INFORMACION SUMINISTRADA A TRAVES DE ESTE FORMULARIO");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("CONDICION DEL ESTUDIANTE");

        cond_estudiante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alumno Regular", "Nuevos inscrito por primera vez (CIU)", "Nuevos inscritos por traslado de otras universidades", "Nuevos inscritos por equivalencia", "Nuevos inscritos por reingreso", "Nuevos inscritos por convalidacion" }));
        cond_estudiante.setToolTipText("Seleccione una condicion");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 204, 0));
        jLabel45.setText("NUCLEO-EXTENSION");

        nucleo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NUCLEO ARAGUA - SEDE MARACAY", "NUCLEO ARAGUA - EXTENSION CAGUA", "NUCLEO MERIDA - SEDE MERIDA", "NUCLEO MERIDA - EXTENSION TOVAR" }));

        n_rusnieus.setText("000000");
        n_rusnieus.setToolTipText("numero de planilla RUSNIEUS");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("N° RUSNIEUS:");

        modificar.setText("MODIFICAR");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, n_rusnieus)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel42, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, tipo_estudiante, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel47))
                        .add(28, 28, 28)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(modificar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 158, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(cond_estudiante, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabel44, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel45)
                    .add(nucleo, 0, 360, Short.MAX_VALUE)
                    .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(588, Short.MAX_VALUE)
                .add(enviar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 237, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel42)
                            .add(jLabel44))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(tipo_estudiante, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(cond_estudiante, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(jLabel47)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(n_rusnieus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(modificar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabel45)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nucleo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 109, Short.MAX_VALUE)
                        .add(enviar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(36, 36, 36))))
        );

        paneles.addTab("DATOS ACADEMICOS", jPanel1);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actualizacion_de_datos/imagenes/unafa_mod_1.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel14.setText("REPUBLICA BOLIVARIANA DE VENEZUELA");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel15.setText("MINISTERIO DEL PODER POPULAR PARA LA DEFENSA");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel16.setText("UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA DE LA FUERZA ARMADA NACIONAL");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel17.setText("CONTROL DE ESTUDIO");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel19.setText("NUCLEO ARAGUA - EXTENSION CAGUA");

        foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/actualizacion_de_datos/imagenes/unafa_mod_1.png"))); // NOI18N
        foto.setMaximumSize(new java.awt.Dimension(128, 128));
        foto.setMinimumSize(new java.awt.Dimension(128, 128));
        foto.setPreferredSize(new java.awt.Dimension(128, 128));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(32, 32, 32)
                .add(jLabel13)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(200, 200, 200)
                                .add(jLabel14))
                            .add(layout.createSequentialGroup()
                                .add(169, 169, 169)
                                .add(jLabel15))
                            .add(layout.createSequentialGroup()
                                .add(85, 85, 85)
                                .add(jLabel16))
                            .add(layout.createSequentialGroup()
                                .add(211, 211, 211)
                                .add(jLabel19)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel17)
                        .add(242, 242, 242)))
                .add(foto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(paneles)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel14)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel15)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel16)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel19)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel17))
                    .add(jLabel13)
                    .add(foto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(paneles)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {

    //  JFrame actualizacion=new JFrame();
     // actualizacion.setDefaultLookAndFeelDecorated(true);

      NimRODTheme nt = new NimRODTheme();

        nt.setPrimary( new Color(153,244,51));
        nt.setPrimary1( new Color(133,224,31));
        nt.setPrimary2( new Color(143,234,41));
        nt.setPrimary3( new Color(153,244,51));

        nt.setSecondary(new Color(222,230,250));
        nt.setSecondary1(new Color(202,210,230));
        nt.setSecondary2(new Color(212,220,240));
        nt.setSecondary3(new Color(222,230,250));

        nt.setMenuOpacity(20);
        nt.setFrameOpacity(20);
        nt.setOpacity(20);

            NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
            NimRODLF.setCurrentTheme( nt);
            UIManager.setLookAndFeel( NimRODLF);




     //   UIManager.setLookAndFeel(new com.nilo.plaf.nimrod.NimRODLookAndFeel());










        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista.actualizacion().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidos;
    private javax.swing.JTextField atleta;
    private javax.swing.JButton bd;
    private javax.swing.JComboBox becado;
    private javax.swing.JButton borrar;
    private javax.swing.JComboBox carrera;
    private javax.swing.JTextField cedula;
    private javax.swing.JRadioButton civil;
    private javax.swing.JComboBox cond_estudiante;
    private javax.swing.JTextField correo;
    private javax.swing.JTextArea direccion;
    private javax.swing.JComboBox discapacidad;
    private javax.swing.JRadioButton diurno;
    private javax.swing.JComboBox ds_a;
    private javax.swing.JComboBox ds_b;
    private javax.swing.JComboBox ds_c;
    private javax.swing.JComboBox ds_d;
    private javax.swing.JComboBox ds_parentesco;
    private javax.swing.JComboBox ds_penal;
    private javax.swing.JComboBox ds_personas;
    private javax.swing.JComboBox ds_tiempo;
    private javax.swing.JComboBox edo_civil;
    private javax.swing.JButton elegir1;
    private javax.swing.JButton elegir2;
    private javax.swing.JButton elegir3;
    private javax.swing.JButton elegir4;
    private javax.swing.JButton enviar;
    private javax.swing.JComboBox etnia;
    private javax.swing.JComboBox fi_año;
    private javax.swing.JComboBox fi_mes;
    private javax.swing.JComboBox fn_año;
    private javax.swing.JComboBox fn_dia;
    private javax.swing.JComboBox fn_mes;
    private javax.swing.JLabel foto;
    private javax.swing.JTextField grado;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.ButtonGroup grupo2;
    private javax.swing.ButtonGroup grupo3;
    private javax.swing.ButtonGroup grupo4;
    private javax.swing.JComboBox id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton militar;
    private javax.swing.JComboBox modalidad;
    private javax.swing.JButton modificar;
    private javax.swing.JTextField n_rusnieus;
    private javax.swing.JComboBox nacionalidad;
    private javax.swing.JRadioButton no;
    private javax.swing.JRadioButton nocturno;
    private javax.swing.JTextField nombres;
    private javax.swing.JComboBox nucleo;
    private javax.swing.JComboBox pais_nac;
    private javax.swing.JTabbedPane paneles;
    private javax.swing.JComboBox residencia;
    private javax.swing.JComboBox sexo;
    private javax.swing.JRadioButton si;
    private javax.swing.JTextField telf_hab;
    private javax.swing.JTextField telf_hab_otro;
    private javax.swing.JTextField telf_per;
    private javax.swing.JTextField telf_per_otro;
    private javax.swing.JComboBox tipo_estudiante;
    private javax.swing.JComboBox upi_año;
    private javax.swing.JComboBox upi_periodo;
    // End of variables declaration//GEN-END:variables

}
