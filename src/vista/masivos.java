/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * masivos.java
 *
 * Created on 17/11/2010, 01:31:13 PM
 */

package vista;

import controlador.control_masivo;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author AN
 */
public class masivos extends javax.swing.JFrame {

    /** Creates new form masivos */
    public masivos() {
        initComponents();

        grupo_notas.add(n_todo);
        grupo_notas.add(n_individual);

        pdf_opciones.add(pdf_individual);
        pdf_opciones.add(pdf_todos);

       c_grupo.add(c_individual);
       c_grupo.add(c_masivo);


       grupo_egresados.add(egre_certificadas);
       grupo_egresados.add(egre_record);
       grupo_egresados.add(egre_txt);
       grupo_egresados.add(egre_merito);

        control_masivo cm = new control_masivo(this);
        generador.addActionListener(cm);
        vigencias_masivas.addActionListener(cm);
        inscripcion_masiva.addActionListener(cm);
        n_iniciar.addActionListener(cm);
        v_vigencia.addActionListener(cm);
        generar_txt.addActionListener(cm);
        cinu_constancia.addActionListener(cm);
        egre_elaborar.addActionListener(cm);
        est_procesar.addActionListener(cm);
        n_cargar_actas.addActionListener(cm);
        
        pdf_inscripcion.addChangeListener(cm);
        pdf_individual.addChangeListener(cm);

        c_prueba.addActionListener(cm);
        c_realizar.addActionListener(cm);
        c_iniciar.addActionListener(cm);
        
        this.addWindowListener(cm);


    }

    public JTextField getC_ruta() {
        return c_ruta;
    }

    public void setC_ruta(JTextField c_ruta) {
        this.c_ruta = c_ruta;
    }

    

    public JComboBox getCarrera() {
        return carrera;
    }

    public void setCarrera(JComboBox carrera) {
        this.carrera = carrera;
    }

    public JTextField getN_cedula() {
        return n_cedula;
    }

    public void setN_cedula(JTextField n_cedula) {
        this.n_cedula = n_cedula;
    }

    public JRadioButton getN_individual() {
        return n_individual;
    }

    public void setN_individual(JRadioButton n_individual) {
        this.n_individual = n_individual;
    }

    public JComboBox getN_periodo() {
        return n_periodo;
    }

    public void setN_periodo(JComboBox n_periodo) {
        this.n_periodo = n_periodo;
    }

    public JRadioButton getN_todo() {
        return n_todo;
    }

    public void setN_todo(JRadioButton n_todo) {
        this.n_todo = n_todo;
    }

    public JTextField getV_cedula() {
        return v_cedula;
    }

    public void setV_cedula(JTextField v_cedula) {
        this.v_cedula = v_cedula;
    }

    public JRadioButton getPdf_individual() {
        return pdf_individual;
    }

    public void setPdf_individual(JRadioButton pdf_individual) {
        this.pdf_individual = pdf_individual;
    }

    public JRadioButton getPdf_todos() {
        return pdf_todos;
    }

    public void setPdf_todos(JRadioButton pdf_todos) {
        this.pdf_todos = pdf_todos;
    }

    public JTextField getPdf_cedula() {
        return pdf_cedula;
    }

    public void setPdf_cedula(JTextField pdf_cedula) {
        this.pdf_cedula = pdf_cedula;
    }

    public JCheckBox getPdf_estudio() {
        return pdf_estudio;
    }

    public void setPdf_estudio(JCheckBox pdf_estudio) {
        this.pdf_estudio = pdf_estudio;
    }

    public JCheckBox getPdf_record() {
        return pdf_record;
    }

    public void setPdf_record(JCheckBox pdf_record) {
        this.pdf_record = pdf_record;
    }

    public JCheckBox getPdf_inscripcion() {
        return pdf_inscripcion;
    }

    public void setPdf_inscripcion(JCheckBox pdf_inscripcion) {
        this.pdf_inscripcion = pdf_inscripcion;
    }

    public JComboBox getNucleo_extension() {
        return nucleo_extension;
    }

    public void setNucleo_extension(JComboBox nucleo_extension) {
        this.nucleo_extension = nucleo_extension;
    }

    public JTextField getPdf_ajuste() {
        return pdf_ajuste;
    }

    public void setPdf_ajuste(JTextField pdf_ajuste) {
        this.pdf_ajuste = pdf_ajuste;
    }

    public JCheckBox getPdf_certificadas() {
        return pdf_certificadas;
    }

    public void setPdf_certificadas(JCheckBox pdf_certificadas) {
        this.pdf_certificadas = pdf_certificadas;
    }

    public JCheckBox getPdf_culminacion() {
        return pdf_culminacion;
    }

    public void setPdf_culminacion(JCheckBox pdf_culminacion) {
        this.pdf_culminacion = pdf_culminacion;
    }

    public JLabel getPeriodo_academico() {
        return periodo_academico;
    }

    public void setPeriodo_academico(JLabel periodo_academico) {
        this.periodo_academico = periodo_academico;
    }

    public JTabbedPane getSelecciones() {
        return selecciones;
    }

    public void setSelecciones(JTabbedPane selecciones) {
        this.selecciones = selecciones;
    }

    public JTextArea getPdf_observacion() {
        return pdf_observacion;
    }

    public void setPdf_observacion(JTextArea pdf_observacion) {
        this.pdf_observacion = pdf_observacion;
    }

    public JComboBox getC_mmr() {
        return c_mmr;
    }

    public void setC_mmr(JComboBox c_mmr) {
        this.c_mmr = c_mmr;
    }

    public JComboBox getC_mpr() {
        return c_mpr;
    }

    public void setC_mpr(JComboBox c_mpr) {
        this.c_mpr = c_mpr;
    }

    public JTextField getC_cedula() {
        return c_cedula;
    }

    public void setC_cedula(JTextField c_cedula) {
        this.c_cedula = c_cedula;
    }

    public JRadioButton getC_individual() {
        return c_individual;
    }

    public void setC_individual(JRadioButton c_individual) {
        this.c_individual = c_individual;
    }

    public JRadioButton getC_masivo() {
        return c_masivo;
    }

    public void setC_masivo(JRadioButton c_masivo) {
        this.c_masivo = c_masivo;
    }

    public JComboBox getTxt_periodo() {
        return txt_periodo;
    }

    public void setTxt_periodo(JComboBox txt_periodo) {
        this.txt_periodo = txt_periodo;
    }

    public JTextField getCinu_cedula() {
        return cinu_cedula;
    }

    public void setCinu_cedula(JTextField cinu_cedula) {
        this.cinu_cedula = cinu_cedula;
    }

    public JRadioButton getCinu_masivo() {
        return cinu_masivo;
    }

    public void setCinu_masivo(JRadioButton cinu_masivo) {
        this.cinu_masivo = cinu_masivo;
    }

    public JTextField getEgre_lineas() {
        return egre_lineas;
    }

    public void setEgre_lineas(JTextField egre_lineas) {
        this.egre_lineas = egre_lineas;
    }

    public JComboBox getEgre_periodo_lectivo() {
        return egre_periodo_lectivo;
    }

    public void setEgre_periodo_lectivo(JComboBox egre_periodo_lectivo) {
        this.egre_periodo_lectivo = egre_periodo_lectivo;
    }

    public JRadioButton getEgre_certificadas() {
        return egre_certificadas;
    }

    public void setEgre_certificadas(JRadioButton egre_certificadas) {
        this.egre_certificadas = egre_certificadas;
    }

    public JRadioButton getEgre_merito() {
        return egre_merito;
    }

    public void setEgre_merito(JRadioButton egre_merito) {
        this.egre_merito = egre_merito;
    }

    public JRadioButton getEgre_record() {
        return egre_record;
    }

    public void setEgre_record(JRadioButton egre_record) {
        this.egre_record = egre_record;
    }

    public JRadioButton getEgre_txt() {
        return egre_txt;
    }

    public void setEgre_txt(JRadioButton egre_txt) {
        this.egre_txt = egre_txt;
    }

    





    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo_inscripcion = new javax.swing.ButtonGroup();
        grupo_notas = new javax.swing.ButtonGroup();
        pdf_opciones = new javax.swing.ButtonGroup();
        pdf_seleccion = new javax.swing.ButtonGroup();
        c_grupo = new javax.swing.ButtonGroup();
        grupo_egresados = new javax.swing.ButtonGroup();
        selecciones = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        ins_individual = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ins_general = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        n_individual = new javax.swing.JRadioButton();
        n_todo = new javax.swing.JRadioButton();
        n_cedula = new javax.swing.JTextField();
        n_iniciar = new javax.swing.JButton();
        n_periodo = new javax.swing.JComboBox();
        n_cargar_actas = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        c_individual = new javax.swing.JRadioButton();
        c_masivo = new javax.swing.JRadioButton();
        c_cedula = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        c_prueba = new javax.swing.JButton();
        c_iniciar = new javax.swing.JButton();
        c_mpr = new javax.swing.JComboBox();
        c_mmr = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        pdf_individual = new javax.swing.JRadioButton();
        pdf_todos = new javax.swing.JRadioButton();
        pdf_cedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pdf_ajuste = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        pdf_inscripcion = new javax.swing.JCheckBox();
        pdf_estudio = new javax.swing.JCheckBox();
        pdf_record = new javax.swing.JCheckBox();
        pdf_certificadas = new javax.swing.JCheckBox();
        pdf_culminacion = new javax.swing.JCheckBox();
        generador = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pdf_observacion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        v_cedula = new javax.swing.JTextField();
        vigencias_masivas = new javax.swing.JButton();
        v_vigencia = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        cinu_constancia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cinu_masivo = new javax.swing.JRadioButton();
        cinu_cedula = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        c_realizar = new javax.swing.JButton();
        c_ruta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        generar_txt = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        txt_periodo = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        egre_elaborar = new javax.swing.JButton();
        egre_certificadas = new javax.swing.JRadioButton();
        egre_record = new javax.swing.JRadioButton();
        egre_txt = new javax.swing.JRadioButton();
        egre_merito = new javax.swing.JRadioButton();
        egre_lineas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        egre_periodo_lectivo = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        est_procesar = new javax.swing.JButton();
        inscripcion_masiva = new javax.swing.JButton();
        carrera = new javax.swing.JComboBox();
        todas_carreras = new javax.swing.JRadioButton();
        nucleo_extension = new javax.swing.JComboBox();
        periodo_academico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MASIVOS");

        jPanel3.setToolTipText("Se envia todas las materias inscritas al record academico sin notas, ya que solo son materias inscritas");

        ins_individual.setText("Individual");
        ins_individual.setName("ins_individual"); // NOI18N

        jTextField1.setText("jTextField1");

        jButton1.setText("INSCRIBR");

        ins_general.setText("General");
        ins_general.setName("ins_general"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ins_individual, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ins_general))
                .addContainerGap(323, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ins_individual)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ins_general)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        selecciones.addTab("INSCRIPCION", jPanel3);

        n_individual.setSelected(true);
        n_individual.setText("INDIVIDUAL");

        n_todo.setText("TODO");

        n_cedula.setText("nada");

        n_iniciar.setText("INICIAR");
        n_iniciar.setToolTipText("empieza el proceso de carga de notas al record del estudiante");

        n_periodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2-2010", "1-2011" }));

        n_cargar_actas.setText("CARGAR ACTAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(n_iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(415, 415, 415)
                .addComponent(n_cargar_actas, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30460, 30460, 30460))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(n_todo)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(n_individual)
                        .addGap(18, 18, 18)
                        .addComponent(n_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(n_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n_individual)
                    .addComponent(n_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(n_todo)
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n_iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n_cargar_actas, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        selecciones.addTab("NOTAS", jPanel4);

        jRadioButton3.setText("jRadioButton3");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SECCIONES" }));

        jRadioButton4.setText("jRadioButton4");

        jLabel2.setText("MODALIDAD");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LISTADO", "ACTAS" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SALON" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(478, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        selecciones.addTab("LISTADOS", jPanel5);

        c_individual.setSelected(true);
        c_individual.setText("INDIVIDUAL");

        c_masivo.setText("MASIVO");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel3.setText("Causas:");

        c_prueba.setText("PRUEBAS");

        c_iniciar.setText("INICIAR");
        c_iniciar.setActionCommand("C_INICIAR");

        c_mpr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c_mpr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 PERIODO", "1 PERIODO", "2 PERIODOS", "3 PERIODOS", "4 PERIODOS", "5 PERIODOS" }));

        c_mmr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        c_mmr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 MATERIAS", "1 MATERIA", "2 MATERIAS", "3 MATERIAS" }));

        jLabel10.setText("MAXIMOS PERIODOS REPROBADOS");

        jLabel11.setText("MAXIMAS MATERIAS REPROBADAS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(c_individual)
                            .addGap(18, 18, 18)
                            .addComponent(c_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(c_prueba)
                            .addGap(117, 117, 117)
                            .addComponent(c_iniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(c_mpr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(c_mmr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(c_masivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_individual)
                    .addComponent(c_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(c_masivo)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(c_mpr, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(c_mmr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_prueba)
                    .addComponent(c_iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        selecciones.addTab("CANCELADOS", jPanel6);

        pdf_individual.setSelected(true);
        pdf_individual.setText("INDIVIDUAL");
        pdf_individual.setToolTipText("Elabora los documentos seleccionados del  estudiante");
        pdf_individual.setName("pdf_individual"); // NOI18N

        pdf_todos.setText("TODOS");
        pdf_todos.setToolTipText("genera todos los documentos que fueron seleccionado de la carrera elegida");

        pdf_cedula.setToolTipText("por favor, ingrese la cedula del estudiante");

        jLabel5.setText("Ajuste:");

        pdf_ajuste.setText("30");
        pdf_ajuste.setToolTipText("Esto permite ajustar el numero de lineas por hojas. 35 filas por defecto");

        pdf_inscripcion.setText("Constancia de Inscripcion");
        pdf_inscripcion.setName("pdf_inscripcion"); // NOI18N

        pdf_estudio.setText("Constancia de Estudio");

        pdf_record.setText("Record Academico");

        pdf_certificadas.setText("Notas Certificadas");

        pdf_culminacion.setText("Carta De Culminacion");

        generador.setText("GENERAR PDF");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pdf_record)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(generador, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pdf_estudio)
                            .addComponent(pdf_inscripcion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pdf_culminacion)
                            .addComponent(pdf_certificadas))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pdf_certificadas)
                    .addComponent(pdf_inscripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pdf_estudio)
                    .addComponent(pdf_culminacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pdf_record)
                    .addComponent(generador, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("OPCIONES");

        pdf_observacion.setColumns(20);
        pdf_observacion.setLineWrap(true);
        pdf_observacion.setRows(5);
        jScrollPane3.setViewportView(pdf_observacion);

        jLabel8.setText("Lineas");

        jLabel9.setText("Observacion: RECORD ACADEMICO");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pdf_todos)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pdf_ajuste))
                                    .addComponent(pdf_individual, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(pdf_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel6))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(246, 246, 246))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pdf_individual)
                            .addComponent(pdf_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(pdf_todos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(pdf_ajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(72, 72, 72)
                        .addComponent(jLabel9))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );

        selecciones.addTab("PDFs", jPanel7);

        jRadioButton7.setText("jRadioButton7");

        jRadioButton8.setText("jRadioButton8");

        v_cedula.setText("jTextField5");

        vigencias_masivas.setText("VIGENCIAS MASIVAS");
        vigencias_masivas.setToolTipText("realiza la actualizacion de la vigencia de todas las carreras");

        v_vigencia.setText("VIGENCIA");
        v_vigencia.setToolTipText("localiza la vigencia de la cedula ingresada");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton8)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(vigencias_masivas)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jRadioButton7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(v_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(v_vigencia))))
                .addContainerGap(410, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton7)
                    .addComponent(v_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v_vigencia))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton8))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(vigencias_masivas)))
                .addContainerGap(241, Short.MAX_VALUE))
        );

        selecciones.addTab("VIGENCIAS", jPanel8);

        cinu_constancia.setText("CONSTANCIA");
        cinu_constancia.setToolTipText("");
        cinu_constancia.setActionCommand("CONSTANCIA_CINU");

        jLabel1.setText("CEDULA:");

        cinu_masivo.setText("MASIVO CINU");

        cinu_cedula.setToolTipText("Coloque la cedula del estudiante cinu para obtener su constancia");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cinu_constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cinu_masivo)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cinu_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 506, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cinu_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(cinu_masivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(cinu_constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        selecciones.addTab("CINU", jPanel9);

        c_realizar.setText("REALIZAR");

        c_ruta.setText("no es necesario.");
        c_ruta.setToolTipText("la ruta que aparece C://REPORTES/INDICES CARRERAS/.... debe estar asi. solo pueden cambiar la unidad o la direccion de la carpeta o disco.\n<br> pero debe haber un slash \"/\" al final y el resto lo hace el sistema. solo pueden colocar el lugar de almacenamiento");

        jLabel7.setText("RUTA:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c_realizar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(c_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(c_realizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        selecciones.addTab("CONSULTAS", jPanel2);

        generar_txt.setText("GENERAR TXT");

        jLabel12.setText("CEDULA:");

        jTextField2.setText("jTextField2");

        jButton2.setText("EGRESAR");

        jRadioButton1.setText("Masivo");

        txt_periodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_periodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2-2013", "1-2013", "2-2012", "1-2012", "2-2011", "1-2011", "2-2010", "1-2010", "2-2009", "1-2009", "2-2008", "1-2008", "2-2007", "1-2007", "2-2006", "1-2006" }));

        jLabel13.setText("PERIODO:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addContainerGap(441, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(generar_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txt_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addGap(39, 39, 39)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(generar_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        selecciones.addTab("TXT", jPanel10);

        egre_elaborar.setText("ELABORAR");

        egre_certificadas.setText("NOTAS CERTIFICADAS");

        egre_record.setText("RECORDS ACADEMICOS");

        egre_txt.setText("TXT EGRESADOS");

        egre_merito.setText("ORDEN DE MERITO");

        egre_lineas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        egre_lineas.setText("30");
        egre_lineas.setToolTipText("Modifica la cantidad de lineas que van a imprimirse por pagina");

        jLabel4.setText("LINEAS:");

        egre_periodo_lectivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2-2013", "1-2013", "2-2012", "1-2012", "2-2011", "1-2011", "2-2010", "1-2010", "2-2009", "1-2009", "2-2008", "1-2008", "2-2007", "1-2007", "2-2006", "1-2006" }));

        jLabel14.setText("PERIODO EGRESADOS:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(egre_elaborar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(egre_merito)
                            .addComponent(egre_txt))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(egre_certificadas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(egre_record, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(egre_lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(egre_periodo_lectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(170, 170, 170))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(egre_certificadas)
                    .addComponent(egre_lineas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(egre_record)
                    .addComponent(egre_periodo_lectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(egre_txt)
                .addGap(18, 18, 18)
                .addComponent(egre_merito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(egre_elaborar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        selecciones.addTab("EGRESADOS", jPanel11);

        est_procesar.setText("PROCESAR");
        est_procesar.setActionCommand("est_PROCESAR");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(615, Short.MAX_VALUE)
                .addComponent(est_procesar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(282, Short.MAX_VALUE)
                .addComponent(est_procesar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        selecciones.addTab("ESTADO", jPanel12);

        inscripcion_masiva.setText("INSCRIPCION MASIVA");

        carrera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INGENIERIA MECANICA", "LIC. EDUCACION INTEGRAL", "LIC. CONTADURIA PUBLICA", "LIC. ECONOMIA SOCIAL", "TSU EN TURISMO", "INGENIERIA AERONAUTICA", "INGENIERIA CIVIL", "INGENIERIA ELECTRICA", "INGENIERIA ELECTRONICA", "INGENIERIA DE SISTEMAS", "INGENIERIA DE TELECOMUNICACIONES", "TSU EN ENFERMERIA", "LIC. EN ADMINISTRACION Y GESTION MUNICIPAL", "LIC. EN ENFERMERIA", "LIC. EN ADMINISTRACION DE DESASTRE", "TSU EN MECANICA DENTAL", "LIC. EN TURISMO", "TSU EN COMUNICACIONES Y ELECTRONICA", "TSU EN ANALISIS Y DISEÑO DE SISTEMAS" }));
        carrera.setToolTipText("SELECCIONE LA CARRERA EN LA QUE SE ENCUENTRA");

        todas_carreras.setText("TODAS LAS CARRERAS");

        nucleo_extension.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NUCLEO ARAGUA - SEDE MARACAY", "NUCLEO ARAGUA - EXTENSION CAGUA", "NUCLEO MERIDA - SEDE MERIDA", "NUCLEO MERIDA - EXTENSION TOVAR" }));

        periodo_academico.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        periodo_academico.setText("1-2021");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inscripcion_masiva, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nucleo_extension, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(periodo_academico)
                            .addComponent(todas_carreras))
                        .addGap(124, 124, 124)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nucleo_extension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(periodo_academico))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inscripcion_masiva))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(todas_carreras)))
                .addGap(18, 18, 18)
                .addComponent(selecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new masivos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField c_cedula;
    private javax.swing.ButtonGroup c_grupo;
    private javax.swing.JRadioButton c_individual;
    private javax.swing.JButton c_iniciar;
    private javax.swing.JRadioButton c_masivo;
    private javax.swing.JComboBox c_mmr;
    private javax.swing.JComboBox c_mpr;
    private javax.swing.JButton c_prueba;
    private javax.swing.JButton c_realizar;
    private javax.swing.JTextField c_ruta;
    private javax.swing.JComboBox carrera;
    private javax.swing.JTextField cinu_cedula;
    private javax.swing.JButton cinu_constancia;
    private javax.swing.JRadioButton cinu_masivo;
    private javax.swing.JRadioButton egre_certificadas;
    private javax.swing.JButton egre_elaborar;
    private javax.swing.JTextField egre_lineas;
    private javax.swing.JRadioButton egre_merito;
    private javax.swing.JComboBox egre_periodo_lectivo;
    private javax.swing.JRadioButton egre_record;
    private javax.swing.JRadioButton egre_txt;
    private javax.swing.JButton est_procesar;
    private javax.swing.JButton generador;
    private javax.swing.JButton generar_txt;
    private javax.swing.ButtonGroup grupo_egresados;
    private javax.swing.ButtonGroup grupo_inscripcion;
    private javax.swing.ButtonGroup grupo_notas;
    private javax.swing.JRadioButton ins_general;
    private javax.swing.JRadioButton ins_individual;
    private javax.swing.JButton inscripcion_masiva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton n_cargar_actas;
    private javax.swing.JTextField n_cedula;
    private javax.swing.JRadioButton n_individual;
    private javax.swing.JButton n_iniciar;
    private javax.swing.JComboBox n_periodo;
    private javax.swing.JRadioButton n_todo;
    private javax.swing.JComboBox nucleo_extension;
    private javax.swing.JTextField pdf_ajuste;
    private javax.swing.JTextField pdf_cedula;
    private javax.swing.JCheckBox pdf_certificadas;
    private javax.swing.JCheckBox pdf_culminacion;
    private javax.swing.JCheckBox pdf_estudio;
    private javax.swing.JRadioButton pdf_individual;
    private javax.swing.JCheckBox pdf_inscripcion;
    private javax.swing.JTextArea pdf_observacion;
    private javax.swing.ButtonGroup pdf_opciones;
    private javax.swing.JCheckBox pdf_record;
    private javax.swing.ButtonGroup pdf_seleccion;
    private javax.swing.JRadioButton pdf_todos;
    private javax.swing.JLabel periodo_academico;
    private javax.swing.JTabbedPane selecciones;
    private javax.swing.JRadioButton todas_carreras;
    private javax.swing.JComboBox txt_periodo;
    private javax.swing.JTextField v_cedula;
    private javax.swing.JButton v_vigencia;
    private javax.swing.JButton vigencias_masivas;
    // End of variables declaration//GEN-END:variables

}
