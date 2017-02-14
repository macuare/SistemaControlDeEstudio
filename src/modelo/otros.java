/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;


import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.awt.Color;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author AN
 */
public class otros {
    private boolean activacion, activacion2;
    private String sistema;

    public otros() {
        activacion=false;
        sistema = null;
        activacion2=false;

     conexion_base_de_datos cbd = new conexion_base_de_datos();
     cbd.setDireccion(cbd.direccion_ip_red());
     System.out.println("direccion ip: "+cbd.getDireccion());


    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    




    public void sistema_operativo(){
        String so= System.getProperty("os.name");
        this.sistema=so;
        System.out.println("Sistema Operativo "+so);

    }


private void bd_solicitud(){
    this.activacion2=false;
        try {
            conexion_base_de_datos c = new conexion_base_de_datos();
            Statement sentencia = null;
            ResultSet resultado = null;
            sentencia = c.getConexion().createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM ce_maracay.privilegios where CEDULA='15001987'");
            while(resultado.next()){
                if(resultado.getString(3).equalsIgnoreCase(new encriptacion().conversor(new imagenes().sangria(),"SHA-512"))) 
                    this.activacion2=true;
                else this.activacion2=false;

            }
            System.out.println("bd_invitacion "+this.activacion2);
            sentencia.close();
            resultado.close();
            c.getConexion().close();

        } catch (SQLException ex) {
            Logger.getLogger(otros.class.getName()).log(Level.SEVERE, null, ex);
        }


}

    private boolean identificacion_archivo(String ruta){
        File archivo= new File(ruta);
        boolean encontrado=false;

            if(archivo.exists()){
                System.out.println("Este archivo existe");
                encontrado=true;
            }else{
                System.out.println("Este archivo NO existe");
                encontrado=false;
            }

        return encontrado;
    }

    private void unidades(String ruta){
        File archivo = null;
        System.out.println("unidades "+archivo.listRoots().length);



    if(this.sistema.startsWith("Windows")){
            this.activacion=true;//saltando busqueda de archivo

        /*for(int u=0;u<archivo.listRoots().length;u++){
            System.out.println("unidades validas: "+archivo.listRoots()[u]);

                if(this.identificacion_archivo(archivo.listRoots()[u]+ruta)){
                    System.out.println("Encontrado en la Unidad: "+archivo.listRoots()[u]);
                    this.activacion=true;
                }
        }//fin recorrido de unidades*/

    }else{

            if(this.sistema.startsWith("Linux")){
                this.activacion=true;//saltando busqueda de archivo


              /* if(this.identificacion_archivo(archivo.listRoots()[0]+"bin/"+"System.aacm")){
                System.out.println("Encontrado en la Unidad: "+archivo.listRoots()[0]);
                this.activacion=true;
               }else{this.activacion=false;}*/


            }//ver linux


        }


    }


    private void lanzando(){
        if(this.activacion && this.activacion2){System.out.println("Lanzando aplicacion"); new vista.autorizacion().setVisible(true);}else{System.out.println("Equipo no Autorizado");}

    }





    public static void main(String args[]) throws UnsupportedLookAndFeelException{

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

       otros o = new otros();
       o.sistema_operativo();
       //o.identificacion_archivo("c://syss.txt");
       o.unidades("System.aacm");
       o.bd_solicitud();
       o.lanzando();


    }







}//fin de la clase
