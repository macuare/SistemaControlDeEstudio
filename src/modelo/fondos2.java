/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author AN
 */
public class fondos2 extends JPanel{
    private JLabel version;
    
    public fondos2(String comentario) {
        setSize(692,390);

        this.etiqueta(comentario);
        this.add(version);
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));



    }

    @Override
    protected void paintComponent(Graphics g) {

         ImageIcon imagen = new ImageIcon(getClass().getResource("/actualizacion_de_datos/fondos/a2_secretaria.jpg"));
         g.drawImage(imagen.getImage(), 0, 0,691,389, null);//lo ideal es que el tamaño se adaptara a la imagen real
         setOpaque(false);

        super.paintComponent(g);
    }

    public void etiqueta(String comentario){

            version = new JLabel();

        version.setFont(new java.awt.Font("LetterOMatic!", 0, 18));
        version.setForeground(new java.awt.Color(255, 255, 255));
        version.setText(comentario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(version)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(version)
                .addContainerGap())
        );


    }








}
