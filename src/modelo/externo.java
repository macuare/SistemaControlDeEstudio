/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import vista.DEPURADOR;
import vista.progreso;

/**
 *
 * @author AN
 */
public class externo implements Runnable {
    private Thread hilo;
   
    private depurador_maracay dp;
    private progreso p;

    public externo(depurador_maracay dp, progreso p) {
        this.p = p;
        this.dp = dp;
        hilo = new Thread(this);
        hilo.start();
        
    }






    public void run() {
       p.getPrincipal().setMaximum(100);
       //System.out.println("maximo: "+(dp.getCedulas().size()-1));
       int x=0;

       while(true){
       
           x=x+1;
         System.out.println("hilo.........."+dp.getAvance());
         
         /* p.getPrincipal().setValue(dp.getAvance());
          p.getInformacion_1().setText(""+dp.getAvance());
         */
        //  System.out.println("hilo.........."+x);

          p.getPrincipal().setValue(x);
          p.getInformacion_1().setText(""+x);







          
     
    }
    }

}//fin de la clase
