/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PRINCIPAL.java
 *
 * Created on 14/10/2010, 10:32:02 AM
 */

package vista;


import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import controlador.control_PRINCIPAL;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modelo.imagenes;

/**
 *
 * @author AN
 */
public class PRINCIPAL extends javax.swing.JFrame {
    private String responsable;
    private imagenes ima;
    private String periodo_inscribir ;
    private String versiones;
    public static String usuario_vinculo;    


    /** Creates new form PRINCIPAL */
    public PRINCIPAL() {
        initComponents();
        ima = new imagenes();
        
       


       //inicializando algunos componentes.
        estudiantes.setIcon(ima.imagenes("alumnos", "png"));
        plan_b.setIcon(ima.imagenes("plan_b", "png"));
        reactivacion.setIcon(ima.imagenes("reactivacion", "png"));
        pensum.setIcon(ima.imagenes("pensum", "png"));
        notas_alumnos.setIcon(ima.imagenes("notas", "png"));
        ofertas.setIcon(ima.imagenes("ofertas","png"));
        listado_secciones.setIcon(ima.imagenes("secciones", "png"));
        materias_inscritas.setIcon(ima.imagenes("inscritas", "png"));
        constancias.setIcon(ima.imagenes("constancias", "png"));
        correos.setIcon(ima.imagenes("correo", "png"));
        cambios.setIcon(ima.imagenes("cambios", "png"));
        convalidacion.setIcon(ima.imagenes("convalidacion", "png"));
        nuevo_ingreso.setIcon(ima.imagenes("nuevo_estudiante", "jpg"));
        autoridades.setIcon(ima.imagenes("escudo_3d","jpg"));
        documentos.setIcon(ima.imagenes("documentos","png"));
        reingresos.setIcon(ima.imagenes("reingresos","png"));
        accesos.setIcon(ima.imagenes("accesos","png"));
        retiros.setIcon(ima.imagenes("retiros","png"));

        control_PRINCIPAL cp = new control_PRINCIPAL(this);

            estudiantes.addActionListener(cp);
            plan_b.addActionListener(cp);
            pensum.addActionListener(cp);
            notas_alumnos.addActionListener(cp);
            listado_secciones.addActionListener(cp);
            materias_inscritas.addActionListener(cp);
            constancias.addActionListener(cp);
            correos.addActionListener(cp);
            ofertas.addActionListener(cp);
            cambios.addActionListener(cp);
            convalidacion.addActionListener(cp);
            periodo_aca.addActionListener(cp);
            reactivacion.addActionListener(cp);
            nuevo_ingreso.addActionListener(cp);
            autoridades.addActionListener(cp);
            reingresos.addActionListener(cp);
            documentos.addActionListener(cp);
            accesos.addActionListener(cp);
            retiros.addActionListener(cp);
            
            this.addWindowListener(cp);

//inicializando periodo academico
                  
                //  this.setPeriodo_inscribir(ima.seleccion_de_periodos("SELECCIONE EL PERIODO ACADEMICO CON EL QUE DESEA TRABAJAR", "PERIODO ACADEMICO"));
                //  ima.mensaje_informacion("PERIODO SELECCIONADO: ("+this.getPeriodo_inscribir()+")", "SELECCION", "precaucion", "png");


 
 
        
      
    }


   public JMenuItem getAutoridades() {
        return autoridades;
    }

    public void setAutoridades(JMenuItem autoridades) {
        this.autoridades = autoridades;
    }

    public JMenuItem getReactivacion() {
        return reactivacion;
    }

    public void setReactivacion(JMenuItem reactivacion) {
        this.reactivacion = reactivacion;
    }
    
    public JMenuItem getConstancias() {
        return constancias;
    }

    public void setConstancias(JMenuItem constancias) {
        this.constancias = constancias;
    }

    public JMenuItem getCorreos() {
        return correos;
    }

    public void setCorreos(JMenuItem correos) {
        this.correos = correos;
    }

    public JMenu getEdicion() {
        return edicion;
    }

    public void setEdicion(JMenu edicion) {
        this.edicion = edicion;
    }

    public JMenuItem getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(JMenuItem estudiantes) {
        this.estudiantes = estudiantes;
    }

    public JMenu getIncripciones() {
        return incripciones;
    }

    public void setIncripciones(JMenu incripciones) {
        this.incripciones = incripciones;
    }

    public JMenuItem getListado_secciones() {
        return listado_secciones;
    }

    public void setListado_secciones(JMenuItem listado_secciones) {
        this.listado_secciones = listado_secciones;
    }

    public JMenu getMasivos() {
        return masivos;
    }

    public void setMasivos(JMenu masivos) {
        this.masivos = masivos;
    }

    public JMenuItem getMaterias_inscritas() {
        return materias_inscritas;
    }

    public void setMaterias_inscritas(JMenuItem materias_inscritas) {
        this.materias_inscritas = materias_inscritas;
    }

    public JMenuBar getMenu_general() {
        return menu_general;
    }

    public void setMenu_general(JMenuBar menu_general) {
        this.menu_general = menu_general;
    }

    public JMenu getModificadores() {
        return modificadores;
    }

    public void setModificadores(JMenu modificadores) {
        this.modificadores = modificadores;
    }

    public JMenuItem getNotas_alumnos() {
        return notas_alumnos;
    }

    public void setNotas_alumnos(JMenuItem notas_alumnos) {
        this.notas_alumnos = notas_alumnos;
    }

    public JMenuItem getPensum() {
        return pensum;
    }

    public void setPensum(JMenuItem pensum) {
        this.pensum = pensum;
    }

    public JMenuItem getPlan_b() {
        return plan_b;
    }

    public void setPlan_b(JMenuItem plan_b) {
        this.plan_b = plan_b;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
        usuario_vinculo = responsable;
    }

    public JMenuItem getOfertas() {
        return ofertas;
    }

    public void setOfertas(JMenuItem ofertas) {
        this.ofertas = ofertas;
    }

    public JMenuItem getCambios() {
        return cambios;
    }

    public void setCambios(JMenuItem cambios) {
        this.cambios = cambios;
    }

    public JMenuItem getConvalidacion() {
        return convalidacion;
    }

    public void setConvalidacion(JMenuItem convalidacion) {
        this.convalidacion = convalidacion;
    }

    public String getPeriodo_inscribir() {
        return periodo_inscribir;
    }

    public void setPeriodo_inscribir(String periodo_inscribir) {
        this.periodo_inscribir = periodo_inscribir;
    }

    public JMenuItem getPeriodo_aca() {
        return periodo_aca;
    }

    public void setPeriodo_aca(JMenuItem periodo_aca) {
        this.periodo_aca = periodo_aca;
    }

    public String getVersiones() {
        return versiones;
    }

    public void setVersiones(String versiones) {
        this.versiones = versiones;
    }

    public JPanel getFondo() {
        return fondo;
    }

    public void setFondo(JPanel fondo) {
        this.fondo = fondo;
    }

    public JMenuItem getNuevo_ingreso() {
        return nuevo_ingreso;
    }

    public void setNuevo_ingreso(JMenuItem nuevo_ingreso) {
        this.nuevo_ingreso = nuevo_ingreso;
    }

    public JMenuItem getDocumentos() {
        return documentos;
    }

    public void setDocumentos(JMenuItem documentos) {
        this.documentos = documentos;
    }

    public JMenuItem getReingresos() {
        return reingresos;
    }

    public void setReingresos(JMenuItem reingresos) {
        this.reingresos = reingresos;
    }

    public JMenuItem getAccesos() {
        return accesos;
    }

    public void setAccesos(JMenuItem accesos) {
        this.accesos = accesos;
    }

    public JMenuItem getRetiros() {
        return retiros;
    }

    public void setRetiros(JMenuItem retiros) {
        this.retiros = retiros;
    }




    











    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        fondo = new javax.swing.JPanel();
        menu_general = new javax.swing.JMenuBar();
        edicion = new javax.swing.JMenu();
        periodo_aca = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        accesos = new javax.swing.JMenuItem();
        incripciones = new javax.swing.JMenu();
        estudiantes = new javax.swing.JMenuItem();
        plan_b = new javax.swing.JMenuItem();
        reactivacion = new javax.swing.JMenuItem();
        nuevo_ingreso = new javax.swing.JMenuItem();
        modificadores = new javax.swing.JMenu();
        pensum = new javax.swing.JMenuItem();
        notas_alumnos = new javax.swing.JMenuItem();
        ofertas = new javax.swing.JMenuItem();
        cambios = new javax.swing.JMenuItem();
        convalidacion = new javax.swing.JMenuItem();
        autoridades = new javax.swing.JMenuItem();
        reingresos = new javax.swing.JMenuItem();
        documentos = new javax.swing.JMenuItem();
        retiros = new javax.swing.JMenuItem();
        masivos = new javax.swing.JMenu();
        listado_secciones = new javax.swing.JMenuItem();
        materias_inscritas = new javax.swing.JMenuItem();
        constancias = new javax.swing.JMenuItem();
        correos = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRINCIPAL");
        setResizable(false);

        fondo.setName("fondo"); // NOI18N

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        menu_general.setPreferredSize(new java.awt.Dimension(252, 25));

        edicion.setText("Archivos");
        edicion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        periodo_aca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_MASK));
        periodo_aca.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        periodo_aca.setForeground(new java.awt.Color(255, 255, 255));
        periodo_aca.setText("PERIODO ACADEMICO");
        edicion.add(periodo_aca);

        menu_general.add(edicion);

        jMenu2.setText("SEGURIDAD");
        jMenu2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        accesos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        accesos.setText("Accesos");
        accesos.setEnabled(false);
        jMenu2.add(accesos);

        menu_general.add(jMenu2);

        incripciones.setText("Inscripciones");
        incripciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        estudiantes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        estudiantes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        estudiantes.setForeground(new java.awt.Color(255, 255, 255));
        estudiantes.setText("Estudiantes");
        estudiantes.setEnabled(false);
        incripciones.add(estudiantes);

        plan_b.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        plan_b.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        plan_b.setForeground(new java.awt.Color(255, 255, 255));
        plan_b.setText("Plan B");
        plan_b.setEnabled(false);
        incripciones.add(plan_b);

        reactivacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        reactivacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        reactivacion.setForeground(new java.awt.Color(255, 255, 255));
        reactivacion.setText("REACTIVACION");
        reactivacion.setEnabled(false);
        incripciones.add(reactivacion);

        nuevo_ingreso.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        nuevo_ingreso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        nuevo_ingreso.setForeground(new java.awt.Color(255, 255, 255));
        nuevo_ingreso.setText("NUEVO INGRESO");
        nuevo_ingreso.setEnabled(false);
        incripciones.add(nuevo_ingreso);

        menu_general.add(incripciones);

        modificadores.setText("MODIFICADORES");
        modificadores.setToolTipText("");
        modificadores.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        pensum.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        pensum.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pensum.setForeground(new java.awt.Color(255, 255, 255));
        pensum.setText("Pensum");
        pensum.setEnabled(false);
        modificadores.add(pensum);

        notas_alumnos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        notas_alumnos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        notas_alumnos.setForeground(new java.awt.Color(255, 255, 255));
        notas_alumnos.setText("Notas del Alumno");
        notas_alumnos.setEnabled(false);
        modificadores.add(notas_alumnos);

        ofertas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        ofertas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ofertas.setForeground(new java.awt.Color(255, 255, 255));
        ofertas.setText("OFERTAS");
        ofertas.setEnabled(false);
        modificadores.add(ofertas);

        cambios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        cambios.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cambios.setForeground(new java.awt.Color(255, 255, 255));
        cambios.setText("Cambios");
        cambios.setEnabled(false);
        modificadores.add(cambios);

        convalidacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        convalidacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        convalidacion.setForeground(new java.awt.Color(255, 255, 255));
        convalidacion.setText("CONVALIDACION");
        convalidacion.setEnabled(false);
        modificadores.add(convalidacion);

        autoridades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        autoridades.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        autoridades.setForeground(new java.awt.Color(255, 255, 255));
        autoridades.setText("AUTORIDADES");
        autoridades.setEnabled(false);
        modificadores.add(autoridades);

        reingresos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        reingresos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        reingresos.setForeground(new java.awt.Color(255, 255, 255));
        reingresos.setText("REINGRESOS");
        reingresos.setEnabled(false);
        modificadores.add(reingresos);

        documentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        documentos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        documentos.setForeground(new java.awt.Color(255, 255, 255));
        documentos.setText("DOCUMENTOS");
        documentos.setEnabled(false);
        modificadores.add(documentos);

        retiros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        retiros.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        retiros.setForeground(new java.awt.Color(255, 255, 255));
        retiros.setText("RETIROS");
        retiros.setEnabled(false);
        modificadores.add(retiros);

        menu_general.add(modificadores);

        masivos.setText("MASIVOS");
        masivos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        listado_secciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        listado_secciones.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listado_secciones.setForeground(new java.awt.Color(255, 255, 255));
        listado_secciones.setText("Listados de Secciones");
        listado_secciones.setEnabled(false);
        masivos.add(listado_secciones);

        materias_inscritas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        materias_inscritas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        materias_inscritas.setForeground(new java.awt.Color(255, 255, 255));
        materias_inscritas.setText("Materias Inscritas");
        materias_inscritas.setEnabled(false);
        masivos.add(materias_inscritas);

        constancias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        constancias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        constancias.setForeground(new java.awt.Color(255, 255, 255));
        constancias.setText("Constancias");
        constancias.setEnabled(false);
        masivos.add(constancias);

        correos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        correos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        correos.setForeground(new java.awt.Color(255, 255, 255));
        correos.setText("Correos");
        correos.setEnabled(false);
        masivos.add(correos);

        menu_general.add(masivos);

        setJMenuBar(menu_general);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        try {
            NimRODTheme nt = new NimRODTheme();
            nt.setPrimary(new Color(153, 244, 51));
            nt.setPrimary1(new Color(133, 224, 31));
            nt.setPrimary2(new Color(143, 234, 41));
            nt.setPrimary3(new Color(153, 244, 51));
            nt.setSecondary(new Color(222, 230, 250));
            nt.setSecondary1(new Color(202, 210, 230));
            nt.setSecondary2(new Color(212, 220, 240));
            nt.setSecondary3(new Color(222, 230, 250));
            nt.setMenuOpacity(20);
            nt.setFrameOpacity(20);
            nt.setOpacity(20);
            NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
            NimRODLF.setCurrentTheme(nt);
            UIManager.setLookAndFeel(NimRODLF);
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    new PRINCIPAL().setVisible(true);

                }
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PRINCIPAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem accesos;
    private javax.swing.JMenuItem autoridades;
    private javax.swing.JMenuItem cambios;
    private javax.swing.JMenuItem constancias;
    private javax.swing.JMenuItem convalidacion;
    private javax.swing.JMenuItem correos;
    private javax.swing.JMenuItem documentos;
    private javax.swing.JMenu edicion;
    private javax.swing.JMenuItem estudiantes;
    private javax.swing.JPanel fondo;
    private javax.swing.JMenu incripciones;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem listado_secciones;
    private javax.swing.JMenu masivos;
    private javax.swing.JMenuItem materias_inscritas;
    private javax.swing.JMenuBar menu_general;
    private javax.swing.JMenu modificadores;
    private javax.swing.JMenuItem notas_alumnos;
    private javax.swing.JMenuItem nuevo_ingreso;
    private javax.swing.JMenuItem ofertas;
    private javax.swing.JMenuItem pensum;
    private javax.swing.JMenuItem periodo_aca;
    private javax.swing.JMenuItem plan_b;
    private javax.swing.JMenuItem reactivacion;
    private javax.swing.JMenuItem reingresos;
    private javax.swing.JMenuItem retiros;
    // End of variables declaration//GEN-END:variables

}
