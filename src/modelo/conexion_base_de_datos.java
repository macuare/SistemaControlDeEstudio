/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//DESARROLLADO POR EL ING CUSATTI ANDY
package modelo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class conexion_base_de_datos {
  
	//DESARROLLADO POR EL ING CUSATTI ANDY

    private String usuario,clave;
    private String confirmacion;
    private static String direccion;

    public conexion_base_de_datos(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
            }

  public conexion_base_de_datos() {
        
    }

    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public static String getDireccion() {
        return direccion;
    }

    public static void setDireccion(String direccion) {
        conexion_base_de_datos.direccion = direccion;
    }

   

public String direccion_ip_red(){

        String ip, nueva_ip="";
        String octeto="";
        String r[]= new String[]{};
        try {
            ip=InetAddress.getLocalHost().getHostAddress();            
            System.out.println("Direccion Actual: "+ip);
            ip = ip.replace(".", ":");
            octeto = "70";
          
            if(ip.split(":")[3].equalsIgnoreCase(octeto) || ip.split(":")[3].equalsIgnoreCase("1") ){
                nueva_ip = "localhost";
            }else{
                nueva_ip = ip.split(":")[0]+"."+ip.split(":")[1]+"."+ip.split(":")[2]+"."+octeto;
            }
            
            System.out.println("Direccion a Utilizar: "+nueva_ip);
   
        } catch (UnknownHostException ex) {
            Logger.getLogger(conexion_base_de_datos.class.getName()).log(Level.SEVERE, null, ex);
        }
return nueva_ip;
    }


   public Connection getConexion() {
        Connection conexion = null;
         String url=null;
        	try {
                    
            this.setConfirmacion("false");
			Class.forName("com.mysql.jdbc.Driver");                        
                        usuario = "jdbc:mysql://200.150.168.180"+"trxxxdssed";
                        clave = "++--dd-d";
 //UNEFA CAGUA MARACAY

     if(this.getDireccion().equalsIgnoreCase("localhost")){//se conecta en modo local
            url = "jdbc:mysql://localhost"; //personal
            conexion = DriverManager.getConnection(url,"root","clave");//conexion normal superusuario
          }else{//se conecta en modo remoto

            url="jdbc:mysql://"+this.getDireccion(); //control de estudio cagua
            conexion = DriverManager.getConnection(url,"usuario","clave");//conexion Servidor remoto
 

       }
  			System.out.println("Conexion establecida con exito!");
            this.setConfirmacion("true");
           

            
        } catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                new JFrame(),e.getLocalizedMessage()+
                "Base de Datos no encontrada",
                "ADVERTENCIA",
                JOptionPane.INFORMATION_MESSAGE);

            System.out.println(e.getLocalizedMessage()+"Base de Datos no encontrada");

	}

        return conexion;
    }

//DESARROLLADO POR EL ING CUSATTI ANDY

public static void main(String [] args){
    conexion_base_de_datos cbd = new conexion_base_de_datos();
    //System.out.println("direccion final: "+cbd.direccion_ip_red());
    cbd.direccion = cbd.direccion_ip_red();
    Connection c = cbd.getConexion();
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion_base_de_datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

   
    
        }//fin de la clase