/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

//import guarderia.vista.principal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author AN
 */
public class calendarios {

    public calendarios() {
    }
  //  principal pri;
    
  //  public calendarios(principal pri) {
    //    this.pri = pri;
   // }
    
    
    
public void estimacion_horas(String d_inicio, String d_fin, String n_inicio, String n_fin, String h_academica, String h_almuerzo, String receso){
    
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
    
    


}
    


public void bloques(){

}    

public String bloques_horas(String hora_inicio, String hora_fin){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");           
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
            int hora=0, minutos=0;
            int he=0,me=0,hs=0,ms=0;   
            String salida=null;
    
        try {
            Date hora_minuto_entrada = sdf.parse(hora_inicio);//convirtiendo el formato de 12 horas a Date para luego manipularlo en cualquier formato
            Date hora_minuto_salida = sdf.parse(hora_fin);//convirtiendo el formato de 12 horas a Date para luego manipularlo en cualquier formato
            Date estimacion = null;
               
            //hora y minutos de entrada
            he = Integer.valueOf( sdf2.format(hora_minuto_entrada).split(":")[0].toString() );
            me = Integer.valueOf( sdf2.format(hora_minuto_entrada).split(":")[1].toString() );
            System.out.println("hora inicio: "+he+":"+me);
            //hora y minuto de servicio solicitado
            hs = Integer.valueOf( sdf2.format(hora_minuto_salida).split(":")[0].toString() );
            ms = Integer.valueOf( sdf2.format(hora_minuto_salida).split(":")[1].toString() );
            System.out.println("hora fin: "+hs+":"+ms);
            //calculo de estimacion de salida
                //analizando los minutos.     
                minutos = ms - me;
                if(minutos<0){
                    minutos = minutos + 60;//revertiendo el numero negativo sumango 60 = 1hora por lo que el exceso de la hora se debe compensar restando
                    hora = hora - 1;
                }
                
                for(int i=0; i<=2; i++){//analizando los minutos para agregar horas adicionales si se sobrepasan los 60 minutos
                    if(minutos>59){//si es mayor a los 60 min, se hace la resta, y se adiciona una hora hasta que los minutos sea inferior a 60 min
                        minutos = minutos - 60;//se resta 60 min al total almacenado
                        hora = hora + 1; //se suma 1 hora por cada 60 min acumulado
                    }else //pero sino se mantienen los minutos
                        break;
                
                }//fin analisis minutos*/
            
                //analizando las horas. se toma el valor de la hora resultante de los minutos si existiese
                hora = hora + (hs-he);               
               //tranformando el tiempo resultante a formato 24h
                        System.out.println("DURACION: "+hora+":"+minutos);

                        
                        
                        
                        
                        
                        estimacion = sdf2.parse(hora+":"+minutos+":00");
                        System.out.println("HORA DE SALIDA 24h: "+sdf2.format(estimacion));
                    
                
                //trasnformando el tiempo a 12h
                System.out.println("HORA DE SALIDA 12h: "+sdf.format(estimacion));
                salida = sdf.format(estimacion);
        
        } catch (ParseException ex) {
            Logger.getLogger(calendarios.class.getName()).log(Level.SEVERE, null, ex);
        }
  return salida;


}

    
//--------------------------------------------------    
    
    public String calendario_2(){
    String fecha=null;
    String hora=null;
    String meridiano = null;
    
    
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
    Calendar c = Calendar.getInstance();

  //  System.out.println("calendario: "+c.get(Calendar.DAY_OF_MONTH));
  //  System.out.println("calendario: "+(c.get(Calendar.MONTH)+1));
  //  System.out.println("calendario: "+c.get(Calendar.YEAR));
//if(c.get(Calendar.AM_PM)>0)meridiano="PM";else meridiano="AM";


 //   fecha = c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
    //hora = c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+" "+meridiano;
    hora = sdf.format(c.getTime());
//System.out.println("fecha: "+fecha);

return hora;

}
    
    
    
    
public String estimacion_salida(String hora_entrada, String duracion_servicio){
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
            int hora=0, minutos=0;
            int he=0,me=0,hs=0,ms=0;   
            String salida=null;
    
        try {
  
            
            
            Date hora_minuto_entrada = sdf.parse(hora_entrada);//convirtiendo el formato de 12 horas a Date para luego manipularlo en cualquier formato
            Date estimacion = null;
               
            //hora y minutos de entrada
            he = Integer.valueOf( sdf2.format(hora_minuto_entrada).split(":")[0].toString() );
            me = Integer.valueOf( sdf2.format(hora_minuto_entrada).split(":")[1].toString() );
            System.out.println("entrada: "+he+":"+me);
            //hora y minuto de servicio solicitado
            hs = Integer.valueOf( duracion_servicio.split(":")[0].toString() );
            ms = Integer.valueOf( duracion_servicio.split(":")[1].toString() );
            System.out.println("solicitado: "+hs+":"+ms);
            //calculo de estimacion de salida
                //analizando los minutos.     
                minutos = me + ms;
                for(int i=0; i<=2; i++){//analizando los minutos para agregar horas adicionales si se sobrepasan los 60 minutos
                    if(minutos>59){//si es mayor a los 60 min, se hace la resta, y se adiciona una hora hasta que los minutos sea inferior a 60 min
                        minutos = minutos - 60;//se resta 60 min al total almacenado
                        hora = hora +1; //se suma 1 hora por cada 60 min acumulado
                    }else //pero sino se mantienen los minutos
                        break;
                
                }//fin analisis minutos
            
                //analizando las horas. se toma el valor de la hora resultante de los minutos si existiese
                hora = hora + (he+hs);
               //tranformando el tiempo resultante a formato 24h
                    

                        estimacion = sdf2.parse(hora+":"+minutos+":00");
                        System.out.println("HORA DE SALIDA 24h: "+sdf2.format(estimacion));
                    
                
                //trasnformando el tiempo a 12h
                System.out.println("HORA DE SALIDA 12h: "+sdf.format(estimacion));
                salida = sdf.format(estimacion);
        
        } catch (ParseException ex) {
            Logger.getLogger(calendarios.class.getName()).log(Level.SEVERE, null, ex);
        }
  return salida;
}    
    
public String calculo_tiempo(String entrada, String solicitado) throws ParseException{

    int hora=0, minutos=0, segundos=0;
    String meridiano=null;
    String inicio=null,fin=null;
    String resultado=null;
    
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
    
    
   // Date hora_minuto = sdf.parse("12:58:40 pm");    
     Date hora_minuto = sdf.parse(entrada);    
    
    
    System.out.println("conversion: "+sdf2.format(hora_minuto));    
    String viejo = sdf2.format(hora_minuto);    
    System.out.println("desglose: "+viejo.split(":")[0]+"-"+viejo.split(":")[1]+"-"+viejo.split(":")[2]);
    
    
    Calendar actual = Calendar.getInstance();      
    System.out.println("tiempo: "+sdf2.format(actual.getTime()));
    
    inicio = sdf2.format(hora_minuto);
    fin = sdf2.format(actual.getTime());
    
    System.out.println("comparacion: "+inicio+" , "+fin);
    System.out.println("CALCULO HORA:: "+( (Integer.valueOf(fin.split(":")[0])-Integer.valueOf(inicio.split(":")[0]))-1 )+"______"+(( (Integer.valueOf(fin.split(":")[0])-Integer.valueOf(inicio.split(":")[0]))-1 ) < 0));
    
    
    boolean negativo=false;//es para saber si la resta de minutos es negativa o no
    minutos=Integer.valueOf(fin.split(":")[1])-Integer.valueOf(inicio.split(":")[1]);//calculo minutos fin - inicio
    if(minutos < 0){
        negativo = true;
        minutos = minutos + 60;        
    }else{
        negativo = false;
        //minutos = minutos;
    }
    
    hora = (Integer.valueOf(fin.split(":")[0])-Integer.valueOf(inicio.split(":")[0]));//calculo hora fin - inicio
    if(hora == 0){//si la resta da cero es porque esta en la misma hora y solo se restaran los minutos.
        hora = 0;    
    }else{
        if(hora>0 && negativo==true){//se resta una hora porque se estan agrgando 60 min en el calculo de los minutos
            hora=hora-1;
        }
        
        if(hora>0 && negativo==false){//la resta de hora se mantiene sin modificacion
            //hora=hora;
        }
        
        
    }
    
    DecimalFormat df = new DecimalFormat("00");
    //SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm:ss");
   // System.out.println("RESTA: "+hora+":"+minutos+":"+segundos);
    //System.out.println("FORMATO: "+df.format(hora)+":"+df.format(minutos)+":00");
    resultado = df.format(hora)+":"+df.format(minutos)+":00";
   
    
    resultado = this.resta_tiempo(resultado, solicitado);
    
    
return resultado;
    
}    
    
/**Este metodo se encarga de determinar si el tiempo usado excede al solicitado y por cuanto ademas lo expresa en numero
 negativo si se excede*/
private String resta_tiempo(String tiempo_usado, String tiempo_solicitado){
    String tiempo=null, marcador=null;
    int tu_h=0,tu_m=0, ts_h=0, ts_m=0;
    int horas=0, minutos=0;
    
        //tiempo usado = 00:41:00    tiempo solicitado = 00:35:00
        
        tu_h = Integer.valueOf( tiempo_usado.split(":")[0].toString() );//tiempo usado. HORA
        tu_m = Integer.valueOf( tiempo_usado.split(":")[1].toString() );//tiempo usado. MINUTO
    
        ts_h = Integer.valueOf( tiempo_solicitado.split(":")[0].toString() );//tiempo solicitado. HORA
        ts_m = Integer.valueOf( tiempo_solicitado.split(":")[1].toString() );//tiempo solicitado. MINUTO
    
       //realizando resta de solicitado menos usado
        horas = ts_h - tu_h;        
        minutos = ts_m - tu_m;
    
        if( horas < 0 || minutos < 0){  //analizando para ver cuanto tiempo queda      
            marcador = "-";
            horas = horas*(-1);//colocando los numeros siempre positivos
            minutos = minutos*(-1);//colocando los numeros siempre positivos
        }else{
            marcador = "+";
        }
    
        DecimalFormat df = new DecimalFormat("00");
        
        tiempo = "("+marcador+") "+df.format(horas)+":"+df.format(minutos)+":00";
             //   System.out.println("SALIDA RESTA TIEMPO: "+tiempo);
    return tiempo;
}

/*


public void pintando(String tiempo){

    //System.out.println("pintando: "+tiempo.split(":")[0].substring(0, 1));
    this.numero(Integer.valueOf(tiempo.split(":")[0].substring(0, 1)), pri.getH_a());
    //System.out.println("pintando: "+tiempo.split(":")[0].substring(1, 2));
    this.numero(Integer.valueOf(tiempo.split(":")[0].substring(1, 2)), pri.getH_b());
    
    //System.out.println("pintando: "+tiempo.split(":")[1].substring(0, 1));
    this.numero(Integer.valueOf(tiempo.split(":")[1].substring(0, 1)), pri.getM_a());
    //System.out.println("pintando: "+tiempo.split(":")[1].substring(1, 2));
    this.numero(Integer.valueOf(tiempo.split(":")[1].substring(1, 2)), pri.getM_b());
    
    //System.out.println("pintando: "+tiempo.split(":")[2].substring(0, 1));
    this.numero(Integer.valueOf(tiempo.split(":")[2].substring(0, 1)), pri.getS_a());
    //System.out.println("pintando: "+tiempo.split(":")[2].substring(1, 2));
    this.numero(Integer.valueOf(tiempo.split(":")[2].substring(1, 2)), pri.getS_b());
    
    //System.out.println("pintando: "+tiempo.split(":")[2].substring(3,5));
    pri.getHorario().setText(tiempo.split(":")[2].substring(3,5));

}

*/

private void numero(int numero, JLabel x){

    switch(numero){
        case 0:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/CERO.png"))); // NOI18N      
        break;
    
        case 1:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/UNO.png"))); // NOI18N      
        break;
            
        case 2:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/DOS.png"))); // NOI18N      
        break;    
    
        case 3:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/TRES.png"))); // NOI18N      
        break;    
            
        case 4:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/CUATRO.png"))); // NOI18N      
        break;
            
        case 5:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/CINCO.png"))); // NOI18N      
        break;
            
        case 6:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/SEIS.png"))); // NOI18N      
        break;
            
        case 7:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/SIETE.png"))); // NOI18N      
        break;
            
        case 8:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/OCHO.png"))); // NOI18N      
        break;
            
        case 9:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/NUEVE.png"))); // NOI18N      
        break;
            
        default:
          x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guarderia/imagenes/CERO.png"))); // NOI18N      
        break;    
            
            
    }
    
}



public static void main(String [] args){

    calendarios cal = new calendarios();
    cal.bloques_horas("07:41:00 am", "06:10:00 pm");
    

}

    
  /*  
public void actualizacion(){    
    
    pri.setHora_actual(this.calendario_2());
    this.pintando(pri.getHora_actual());
    }
*/
    //@Override
/*    public void run() {
        super.run();
        while(true){//ciclo infinito hora
           // System.out.println("hora actual");
         //   this.actualizacion();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(calendarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
  */  
    
    /*
    public static void main(String [] args){
    
        calendarios cale = new calendarios(null);
        
            //cale.calculo_tiempo("");
            cale.estimacion_salida("06:30:00 PM", "12:35:00");
        
    
    }
    */
    
    
    
}