/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HORARIO.java
 *
 * Created on 22/10/2009, 09:59:19 AM
 */

package vista;


import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import controlador.control_inscripcion;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Stack;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AURORA
 */
public class horario extends javax.swing.JFrame {
    
    private String privilegio;

    private DefaultTableModel informacion_horario = new DefaultTableModel();
    private LinkedList<String> lista_autorizada_traspaso = new LinkedList<>();
    private LinkedList<String> pensum_traspaso = new LinkedList<>();
    private LinkedList<String> trapaso_coprelaciones = new LinkedList<>();
    private LinkedList<String> trapaso_record = new LinkedList<>();
    private LinkedList<String> traspaso_electivas = new LinkedList<>();
    private LinkedList<String> traspaso_ele_cur = new LinkedList<>();
    private LinkedList<String> traspaso_comunitario = new LinkedList<>();

    /** Creates new form HORARIO */
    public horario() {
        initComponents();
        this.setLocationRelativeTo(null);

        

this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
control_inscripcion c = new control_inscripcion(this);
        

    horario.addMouseListener(c);
    eliminar.addActionListener(c);
    atras.addActionListener(c);
    siguiente.addActionListener(c);
    //principal.addActionListener(c);
    terminar.addActionListener(c);
    //imprimir.addActionListener(c);
    //revisar.addActionListener(c);
    refrescar.addActionListener(c);

    //word.addActionListener(c);

    semestre.addItemListener(c);
    this.addWindowListener(c);

    salones.addMouseListener(c);//agregando evento a la etiqueta de los salones para llegar mas rapido a la seccion

    }





    
    public JLabel getNucleo() {
        return nucleo;
    }

    public void setNucleo(JLabel nucleo) {
        this.nucleo = nucleo;
    }

    public JTextField getTurno() {
        return turno;
    }

    public void setTurno(JTextField turno) {
        this.turno = turno;
    }

    public JTable getHorario() {
        return horario;
    }

    public void setHorario(JTable horario) {
        this.horario = horario;
    }

    public JTable getSecciones() {
        return secciones;
    }

    public void setSecciones(JTable secciones) {
        this.secciones = secciones;
    }

    public JLabel getSalones() {
        return salones;
    }

    public void setSalones(JLabel salones) {
        this.salones = salones;
    }

    public JButton getAtras() {
        return atras;
    }

    public void setAtras(JButton atras) {
        this.atras = atras;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public void setEliminar(JButton eliminar) {
        this.eliminar = eliminar;
    }

   

    public DefaultTableModel getInformacion_horario() {
        return informacion_horario;
    }

    public void setInformacion_horario(DefaultTableModel informacion_horario) {
        this.informacion_horario = informacion_horario;
    }

    public JTextField getParametros() {
        return parametros;
    }

    public void setParametros(JTextField parametros) {
        this.parametros = parametros;
    }

    public JComboBox getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(JComboBox especialidad) {
        this.especialidad = especialidad;
    }

    public JComboBox getSemestre() {
        return semestre;
    }

    public void setSemestre(JComboBox semestre) {
        this.semestre = semestre;
    }

    public JLabel getUc() {
        return uc;
    }

    public void setUc(JLabel uc) {
        this.uc = uc;
    }

    public JTextField getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(JTextField estudiante) {
        this.estudiante = estudiante;
    }

    public JLabel getPeriodo_actual() {
        return periodo_actual;
    }

    public void setPeriodo_actual(JLabel periodo_actual) {
        this.periodo_actual = periodo_actual;
    }

    public LinkedList<String> getLista_autorizada_traspaso() {
        return lista_autorizada_traspaso;
    }

    public void setLista_autorizada_traspaso(LinkedList<String> lista_autorizada_traspaso) {
        this.lista_autorizada_traspaso = lista_autorizada_traspaso;
    }

    public LinkedList<String> getPensum_traspaso() {
        return pensum_traspaso;
    }

    public void setPensum_traspaso(LinkedList<String> pensum_traspaso) {
        this.pensum_traspaso = pensum_traspaso;
    }

    public LinkedList<String> getTrapaso_coprelaciones() {
        return trapaso_coprelaciones;
    }

    public void setTrapaso_coprelaciones(LinkedList<String> trapaso_coprelaciones) {
        this.trapaso_coprelaciones = trapaso_coprelaciones;
    }

    public LinkedList<String> getTrapaso_record() {
        return trapaso_record;
    }

    public void setTrapaso_record(LinkedList<String> trapaso_record) {
        this.trapaso_record = trapaso_record;
    }

    public LinkedList<String> getTraspaso_electivas() {
        return traspaso_electivas;
    }

    public void setTraspaso_electivas(LinkedList<String> traspaso_electivas) {
        this.traspaso_electivas = traspaso_electivas;
    }

    public LinkedList<String> getTraspaso_ele_cur() {
        return traspaso_ele_cur;
    }

    public void setTraspaso_ele_cur(LinkedList<String> traspaso_ele_cur) {
        this.traspaso_ele_cur = traspaso_ele_cur;
    }

    public LinkedList<String> getTraspaso_comunitario() {
        return traspaso_comunitario;
    }

    public void setTraspaso_comunitario(LinkedList<String> traspaso_comunitario) {
        this.traspaso_comunitario = traspaso_comunitario;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    public JButton getRefrescar() {
        return refrescar;
    }

    public void setRefrescar(JButton refrescar) {
        this.refrescar = refrescar;
    }

    









    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        salones = new javax.swing.JLabel();
        especialidad = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        semestre = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        atras = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        parametros = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        periodo_actual = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        estudiante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        turno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        nucleo = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        horario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        secciones = new javax.swing.JTable();
        eliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        uc = new javax.swing.JLabel();
        refrescar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        terminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HORARIOS.......Desarrollado por el Ing Cusatti Andy");

        jLabel1.setText("ESPECIALIDAD:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("SECCION:");

        salones.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        salones.setText("IM-101");
        salones.setName("salones"); // NOI18N

        especialidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INGENIERIA MECANICA", "LIC. EDUCACION INTEGRAL", "LIC. CONTADURIA PUBLICA", "LIC. ECONOMIA SOCIAL", "TSU EN TURISMO", "INGENIERIA AERONAUTICA", "INGENIERIA CIVIL", "INGENIERIA ELECTRICA", "INGENIERIA ELECTRONICA", "INGENIERIA DE SISTEMAS", "INGENIERIA DE TELECOMUNICACIONES", "TSU EN ENFERMERIA", "LIC. EN ADMINISTRACION Y GESTION MUNICIPAL", "LIC. EN ENFERMERIA", "LIC. EN ADMINISTRACION DE DESASTRE", "TSU EN MECANICA DENTAL", "LIC. EN TURISMO", "TSU EN COMUNICACIONES Y ELECTRONICA", "TSU EN ANALISIS Y DISEÑO DE SISTEMAS" }));
        especialidad.setEnabled(false);

        jLabel4.setText("SEMESTRE:");

        semestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PRIMERO", "SEGUNDO", "TERCERO", "CUARTO", "QUINTO", "SEXTO", "SEPTIMO", "OCTAVO", "NOVENO", "DECIMO" }));

        atras.setText("<<");
        atras.setActionCommand("atras");
        atras.setName("atras"); // NOI18N

        siguiente.setText(">>");
        siguiente.setActionCommand("siguiente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(atras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siguiente, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(atras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        parametros.setText("null");
        parametros.setEnabled(false);

        jLabel5.setText("CEDULA:");

        periodo_actual.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        periodo_actual.setText("2-2011");

        jLabel7.setText("PERIODO:");

        jLabel8.setText("ESTUDIANTE:");

        estudiante.setEnabled(false);

        jLabel9.setText("TURNO:");

        turno.setEnabled(false);

        jLabel10.setText("SEDE:");

        nucleo.setText("nada");
        nucleo.setEnabled(false);

        horario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"07:30-08:15 amx", "", null, null, null, null, null, null},
                {"08:20-09:05 am", null, null, null, null, null, null, null},
                {"09:10-09:55 am", "", null, null, null, null, null, null},
                {"10:00-10:45 am", null, null, null, null, null, null, null},
                {"10:50-11:35 am", null, "", null, "", null, null, null},
                {"11:40-12:25 am", null, null, null, null, null, null, null},
                {"12:30-01:15 pm", null, "", null, null, null, null, null},
                {"01:20-02:05 pm", null, null, null, null, null, null, null},
                {"02:10-02:55 pm", null, null, null, null, null, null, null},
                {"03:00-03:45 pm", null, null, null, null, null, null, null},
                {"03:50-04:35 pm", null, null, null, null, null, null, null},
                {"04:40-05:25 pm", null, null, null, null, null, null, null},
                {"05:30-06:15 pm", null, null, null, null, null, null, null},
                {"06:15-07:00 pm", null, null, null, null, null, null, null},
                {"07:00-07:45 pm", null, null, null, null, null, null, null},
                {"07:45-08:30 pm", null, null, null, null, null, null, null},
                {"08:30-09:15 pm", null, null, null, null, null, null, null},
                {"09:15-10:00 pm", null, null, null, null, null, null, null},
                {"10:00-10:45 pm", null, null, null, null, null, null, null},
                {"10:45-11:30 pm", null, null, null, null, null, null, null},
                {"11:30-11:59 pm", null, null, null, null, null, null, null}
            },
            new String [] {
                "HORAS", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        horario.setName("horario"); // NOI18N
        horario.setRowHeight(20);
        horario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(horario);

        jTabbedPane1.addTab("HORARIO DE CLASES", jScrollPane1);

        secciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SEMESTRE", "CODIGO", "MATERIA", "UC", "SECCION", "HORARIO", "DOCENTE", "CONDICION"
            }
        ));
        secciones.setName("secciones"); // NOI18N
        secciones.setRowHeight(20);
        secciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(secciones);

        eliminar.setText("ELIMINAR");

        uc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        uc.setText("00/30");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uc)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(uc))
        );

        refrescar.setText("REFRESCAR");
        refrescar.setToolTipText("sirve para actualizar las materias inscritas");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("U.C. inscritas:");

        terminar.setText("TERMINAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(refrescar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(eliminar)
                        .addGap(18, 18, 18)
                        .addComponent(refrescar)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MATERIAS INSCRITAS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(parametros, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(turno, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(estudiante)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(especialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(semestre, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(periodo_actual))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(salones, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel2)))
                                .addGap(4, 4, 4)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(nucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(parametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(turno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(semestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(periodo_actual)
                                    .addComponent(jLabel7)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(salones))
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(nucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {

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



        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new horario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox especialidad;
    private javax.swing.JTextField estudiante;
    private javax.swing.JTable horario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nucleo;
    private javax.swing.JTextField parametros;
    private javax.swing.JLabel periodo_actual;
    private javax.swing.JButton refrescar;
    private javax.swing.JLabel salones;
    private javax.swing.JTable secciones;
    private javax.swing.JComboBox semestre;
    private javax.swing.JButton siguiente;
    private javax.swing.JButton terminar;
    private javax.swing.JTextField turno;
    private javax.swing.JLabel uc;
    // End of variables declaration//GEN-END:variables

}
