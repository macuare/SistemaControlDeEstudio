/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * notas_alumnos.java
 *
 * Created on 01/10/2010, 07:59:58 AM
 */

package vista;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import controlador.control_notas_alumnos;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AN
 */
public class notas_alumnos extends javax.swing.JFrame {
DefaultTableModel dtm;
private String t_usuario;
private int t_cedula;

    /** Creates new form notas_alumnos */
    public notas_alumnos() {
        initComponents();
       setLocationRelativeTo(null);
       

        control_notas_alumnos cna = new control_notas_alumnos(this);

        buscar.addActionListener(cna);
        modificar.addActionListener(cna);
        eliminar.addActionListener(cna);
        comparar.addActionListener(cna);
        forzar.addActionListener(cna);


        notas.addMouseListener(cna);
        

        semestre.addItemListener(cna);
        asignaturas.addItemListener(cna);

        inscribir.addActionListener(cna);
       


    }

    public JTextField getCedula() {
        return cedula;
    }

    public void setCedula(JTextField cedula) {
        this.cedula = cedula;
    }

    public JTable getNotas() {
        return notas;
    }

    public void setNotas(JTable notas) {
        this.notas = notas;
    }

    public JTextField getTurno() {
        return turno;
    }

    public void setTurno(JTextField turno) {
        this.turno = turno;
    }

    public JTextField getResponsable() {
        return responsable;
    }

    public void setResponsable(JTextField responsable) {
        this.responsable = responsable;
    }

    public JTextArea getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(JTextArea observaciones) {
        this.observaciones = observaciones;
    }

    public JLabel getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(JLabel ubicacion) {
        this.ubicacion = ubicacion;
    }

    public JTextField getCarrera() {
        return carrera;
    }

    public void setCarrera(JTextField carrera) {
        this.carrera = carrera;
    }

    public JComboBox getSemestre() {
        return semestre;
    }

    public void setSemestre(JComboBox semestre) {
        this.semestre = semestre;
    }

    public JComboBox getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(JComboBox asignaturas) {
        this.asignaturas = asignaturas;
    }

    public JTextField getCodigo() {
        return codigo;
    }

    public void setCodigo(JTextField codigo) {
        this.codigo = codigo;
    }

    public JComboBox getCondicion() {
        return condicion;
    }

    public void setCondicion(JComboBox condicion) {
        this.condicion = condicion;
    }

    public JTextField getDefinitiva() {
        return definitiva;
    }

    public void setDefinitiva(JTextField definitiva) {
        this.definitiva = definitiva;
    }

    public JTextField getDefreparacion() {
        return defreparacion;
    }

    public void setDefreparacion(JTextField defreparacion) {
        this.defreparacion = defreparacion;
    }

    public JTextField getInasistencia() {
        return inasistencia;
    }

    public void setInasistencia(JTextField inasistencia) {
        this.inasistencia = inasistencia;
    }

    public JComboBox getPeriodo() {
        return periodo;
    }

    public void setPeriodo(JComboBox periodo) {
        this.periodo = periodo;
    }

    

    public JTextField getReparacion() {
        return reparacion;
    }

    public void setReparacion(JTextField reparacion) {
        this.reparacion = reparacion;
    }

    public JTextField getSeccion() {
        return seccion;
    }

    public void setSeccion(JTextField seccion) {
        this.seccion = seccion;
    }

    public JTextField getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(JTextField estudiante) {
        this.estudiante = estudiante;
    }

    public JTextArea getAnalisis() {
        return analisis;
    }

    public void setAnalisis(JTextArea analisis) {
        this.analisis = analisis;
    }

    public JComboBox getVigencias() {
        return vigencias;
    }

    public void setVigencias(JComboBox vigencias) {
        this.vigencias = vigencias;
    }






 

    //ttraspaso
    public int getT_cedula() {
        return t_cedula;
    }

    public void setT_cedula(int t_cedula) {
        this.t_cedula = t_cedula;
    }

    public String getT_usuario() {
        return t_usuario;
    }

    public void setT_usuario(String t_usuario) {
        this.t_usuario = t_usuario;
    }


    //traspaso










    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cedula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        turno = new javax.swing.JTextField();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        responsable = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        notas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        semestre = new javax.swing.JComboBox();
        asignaturas = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        condicion = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        inscribir = new javax.swing.JButton();
        codigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        seccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        inasistencia = new javax.swing.JTextField();
        defreparacion = new javax.swing.JTextField();
        reparacion = new javax.swing.JTextField();
        definitiva = new javax.swing.JTextField();
        forzar = new javax.swing.JButton();
        periodo = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        analisis = new javax.swing.JTextArea();
        vigencias = new javax.swing.JComboBox();
        comparar = new javax.swing.JButton();
        ubicacion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        carrera = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        estudiante = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NOTAS ALUMNOS......................Desarrollado por Ing Cusatti Andy");

        cedula.setText("15196976");
        cedula.setToolTipText("Casilla para ingresar la cedula del estudiante");

        jLabel1.setText("CEDULA:");

        buscar.setText("BUSCAR");
        buscar.setToolTipText("boton que inicia la busqueda de las materias del alumno");

        jLabel2.setText("TURNO:");

        turno.setEnabled(false);

        modificar.setText("MODIFICAR");

        eliminar.setText("ELIMINAR");
        eliminar.setToolTipText("Elimina el registro seleccionado");

        jLabel3.setText("USUARIO:");

        responsable.setToolTipText("Toda modificacion que se realice se atribuira al usuario actual");
        responsable.setEnabled(false);

        notas.setAutoCreateRowSorter(true);
        notas.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        notas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "POSICION", "MODIFICAR", "SEMESTRE", "CODIGO", "CONDICION", "DEFINITIVA", "REPARACION", "DEFREPARACION", "INASISTENCIA", "SECCION", "PERIODO", "ESPECIALIDAD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        notas.setIntercellSpacing(new java.awt.Dimension(5, 5));
        notas.setRowHeight(25);
        notas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(notas);

        jTabbedPane1.addTab("NOTAS DE ALUMNOS", jScrollPane1);

        observaciones.setColumns(20);
        observaciones.setRows(5);
        jScrollPane2.setViewportView(observaciones);

        jTabbedPane1.addTab("HISTORIAL", jScrollPane2);

        semestre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        semestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PRIMERO", "SEGUNDO", "TERCERO", "CUARTO", "QUINTO", "SEXTO", "SEPTIMO", "OCTAVO", "NOVENO", "DECIMO" }));
        semestre.setToolTipText("Semestre de la carrera");
        semestre.setName("semestre"); // NOI18N

        asignaturas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        asignaturas.setToolTipText("Nombre de la materia");
        asignaturas.setName("asignatura"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Semestre:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Asignatura:");

        condicion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        condicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NORMAL", "PARALELO", "REPITENCIA", "EQUIVALENCIA", "REPARÓ", "REPROBÓ", "REPARÓ-PENDIENTE", "APROBÓ", "NO REPARÓ-PENDIENTE", "REPROBÓ-25% DE INASISTENCIA", "SUFICIENCIA", "PARALELO-REPARÓ-PENDIENTE", "PARALELO-REPARÓ", "PARALEO-NO REPARÓ-PENDIENTE", "REPITENCIA-REPARÓ-PENDIENTE", "REPITENCIA-REPARÓ", "REPITENCIA-NO REPARÓ-PENDIENTE", "MANTENIMIENTO", "EXONERADO", "RECONOCIMIENTO DE CRÉDITOS", "NIVELACIÓN", "ACREDITACIÓN", "REPROBÓ-50% DE INASISTENCIA" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Condicion:");

        inscribir.setText("INSCRIBIR");

        codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codigo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Codigo:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Seccion:");

        seccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Periodo:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Definitiva:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Reparacion:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("DefReparacion:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Inasistencia:");

        inasistencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inasistencia.setText("0");

        defreparacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        defreparacion.setText("0");

        reparacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reparacion.setText("0");

        definitiva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        definitiva.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(reparacion)
                    .addComponent(definitiva, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inasistencia)
                    .addComponent(defreparacion, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(definitiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(defreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(inasistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        forzar.setText("FORZAR INSCRIPCION");
        forzar.setToolTipText("Se coloca un registro en la vigencia deseada solo para habilitar colocar las materias deseadas");

        periodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        periodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2-2013", "1-2013", "2-2012", "1-2012", "2-2011", "1-2011", "2-2010", "1-2010", "2-2009", "1-2009", "2-2008", "1-2008", "2-2007", "1-2007", "2-2006", "1-2006" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(semestre, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(11, 11, 11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(asignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(seccion))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addComponent(inscribir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel7)
                                .addComponent(condicion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(309, 309, 309)
                            .addComponent(forzar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(110, 110, 110)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(asignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semestre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(condicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(forzar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inscribir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("NUEVA MATERIA", jPanel1);

        analisis.setColumns(20);
        analisis.setRows(5);
        jScrollPane3.setViewportView(analisis);

        vigencias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "nada" }));

        comparar.setText("COMPARAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(comparar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vigencias, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(217, 217, 217))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(vigencias, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comparar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ANALISIS", jPanel3);

        ubicacion.setText("ubicacion");
        ubicacion.setToolTipText("Ubicacion del alumno en la base de datos");

        jLabel6.setText("ESPECIALIDAD:");

        carrera.setEnabled(false);

        jLabel11.setText("ESTUDIANTE:");

        estudiante.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(20, 20, 20)))
                                    .addComponent(jLabel1))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(turno, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buscar))
                                    .addComponent(estudiante, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carrera, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(ubicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(modificar)
                                .addGap(18, 18, 18)
                                .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(responsable, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(responsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(turno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(ubicacion)
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {

        
      NimRODTheme nt = new NimRODTheme();
      NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
      NimRODLF.setCurrentTheme( nt);
      UIManager.setLookAndFeel( NimRODLF);






        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new notas_alumnos().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea analisis;
    private javax.swing.JComboBox asignaturas;
    private javax.swing.JButton buscar;
    private javax.swing.JTextField carrera;
    private javax.swing.JTextField cedula;
    private javax.swing.JTextField codigo;
    private javax.swing.JButton comparar;
    private javax.swing.JComboBox condicion;
    private javax.swing.JTextField definitiva;
    private javax.swing.JTextField defreparacion;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField estudiante;
    private javax.swing.JButton forzar;
    private javax.swing.JTextField inasistencia;
    private javax.swing.JButton inscribir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton modificar;
    private javax.swing.JTable notas;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JComboBox periodo;
    private javax.swing.JTextField reparacion;
    private javax.swing.JTextField responsable;
    private javax.swing.JTextField seccion;
    private javax.swing.JComboBox semestre;
    private javax.swing.JTextField turno;
    private javax.swing.JLabel ubicacion;
    private javax.swing.JComboBox vigencias;
    // End of variables declaration//GEN-END:variables

}
