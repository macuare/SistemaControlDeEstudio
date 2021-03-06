/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * cambios.java
 *
 * Created on 07/04/2011, 04:24:22 PM
 */

package vista;

import controlador.control_cambios;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author AN
 */
public class cambios extends javax.swing.JFrame {

    /** Creates new form cambios */
    public cambios() {
        initComponents();

        control_cambios ccm = new control_cambios(this);
        buscar.addActionListener(ccm);
        cambiar.addActionListener(ccm);


    }

    public JLabel getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(JLabel identificacion) {
        this.identificacion = identificacion;
    }

    public JButton getCambiar() {
        return cambiar;
    }

    public void setCambiar(JButton cambiar) {
        this.cambiar = cambiar;
    }




    
    public JComboBox getCarrera() {
        return carrera;
    }

    public void setCarrera(JComboBox carrera) {
        this.carrera = carrera;
    }

    public JTextField getCarrera_ant() {
        return carrera_ant;
    }

    public void setCarrera_ant(JTextField carrera_ant) {
        this.carrera_ant = carrera_ant;
    }

    public JTextField getCedula() {
        return cedula;
    }

    public void setCedula(JTextField cedula) {
        this.cedula = cedula;
    }

    public JComboBox getTurno() {
        return turno;
    }

    public void setTurno(JComboBox turno) {
        this.turno = turno;
    }

    public JTextField getTurno_ant() {
        return turno_ant;
    }

    public void setTurno_ant(JTextField turno_ant) {
        this.turno_ant = turno_ant;
    }


    


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        carrera_ant = new javax.swing.JTextField();
        carrera = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        turno_ant = new javax.swing.JTextField();
        turno = new javax.swing.JComboBox();
        cedula = new javax.swing.JTextField();
        cambiar = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        identificacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CAMBIOS REGIMEN-CARRERA......Desarrollado por Ing. Cusatti Andy");

        carrera_ant.setEditable(false);
        carrera_ant.setToolTipText("ES LA CARRERA EN LA QUE ACTUALMENTE ESTA EL ESTUDIANTE");

        carrera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        carrera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INGENIERIA MECANICA", "LIC. EDUCACION INTEGRAL", "LIC. CONTADURIA PUBLICA", "LIC. ECONOMIA SOCIAL", "TSU EN TURISMO", "INGENIERIA AERONAUTICA", "INGENIERIA CIVIL", "INGENIERIA ELECTRICA", "INGENIERIA ELECTRONICA", "INGENIERIA DE SISTEMAS", "INGENIERIA DE TELECOMUNICACIONES", "TSU EN ENFERMERIA", "LIC. EN ADMINISTRACION Y GESTION MUNICIPAL", "LIC. EN ENFERMERIA", "LIC. EN ADMINISTRACION DE DESASTRE", "TSU EN MECANICA DENTAL", "LIC. EN TURISMO", "TSU EN COMUNICACIONES Y ELECTRONICA", "TSU EN ANALISIS Y DISEÑO DE SISTEMAS" }));

        jLabel3.setText("CEDULA:");

        jLabel2.setText("TURNO:");

        jLabel1.setText("CARRERA:");

        turno_ant.setEditable(false);
        turno_ant.setToolTipText("ES EL TURNO AL QUE PERTENECE EL ESTUDIANTE");

        turno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        turno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DIURNO", "NOCTURNO" }));

        cedula.setToolTipText("CEDULA DEL ESTUDIANTE AL QUE SE LE DESEA REALIZAR LOS CAMBIOS");

        cambiar.setText("CAMBIAR");
        cambiar.setEnabled(false);

        buscar.setText("BUSCAR");

        jLabel4.setText("CAMBIAR A:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(turno_ant, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(turno, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buscar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(carrera_ant, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 443, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(195, 195, 195))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(carrera_ant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(turno_ant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(turno, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                new cambios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JButton cambiar;
    private javax.swing.JComboBox carrera;
    private javax.swing.JTextField carrera_ant;
    private javax.swing.JTextField cedula;
    private javax.swing.JLabel identificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox turno;
    private javax.swing.JTextField turno_ant;
    // End of variables declaration//GEN-END:variables

}
