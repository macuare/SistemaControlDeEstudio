/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author AURORA
 */
public class parametros {

    private imagenes ima;

 private String direccion_url, usuario, clave;

    public parametros(String direccion_url, String usuario, String clave) {
        this.direccion_url = direccion_url;
        this.usuario = usuario;
        this.clave = clave;
    }

    public parametros() {
        ima = new imagenes();
    }






    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDireccion_url() {
        return direccion_url;
    }

    public void setDireccion_url(String direccion_url) {
        this.direccion_url = direccion_url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }




    public String secciones_rapida(String secciones){
        Object salida=null;
        String respuesta = "";
        String aux=null;

        aux=secciones;


        salida = JOptionPane.showInputDialog(new JFrame(),
                                    "Ingrese el Nro Seccion "+aux.substring(0,3),
                                    "SECCIONES RAPIDAS",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    null,
                                    aux.substring(3,aux.length()));

//evita valores vacio y si se copia algo lo presenta
           if(  salida==null || salida=="") {
               respuesta = secciones;
           } //se matiente lo que entro
           else{
                if(salida.toString().isEmpty()) respuesta=secciones;
                else respuesta = aux.substring(0,3).concat(salida.toString());
           }


System.out.println("RESPUESTA: "+respuesta);

return respuesta;
    }






    public static void main(String [] args){

        parametros p = new parametros();
        p.secciones_rapida("IM-401");
    }




}//fin de la clase
