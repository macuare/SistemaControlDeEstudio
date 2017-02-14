/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author AN
 */
public class modelo_progreso {

    private String informacion_1, informacion_2;
    private int cant_1, cant_2;




    public modelo_progreso() {

    }





    public int getCant_1() {
        return cant_1;
    }

    public void setCant_1(int cant_1) {
        this.cant_1 = cant_1;
    }

    public int getCant_2() {
        return cant_2;
    }

    public void setCant_2(int cant_2) {
        this.cant_2 = cant_2;
    }

    public String getInformacion_1() {
        return informacion_1;
    }

    public void setInformacion_1(String informacion_1) {
        this.informacion_1 = informacion_1;
    }

    public String getInformacion_2() {
        return informacion_2;
    }

    public void setInformacion_2(String informacion_2) {
        this.informacion_2 = informacion_2;
    }


    

public void barra_1(String informacion, int progreso){
    
    this.setInformacion_1(informacion);
    this.setCant_1(progreso);

}





}
