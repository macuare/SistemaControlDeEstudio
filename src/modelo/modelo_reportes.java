/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 *
 * @author ANDY CUSATTI
 * LA MAYORIA DE LOS METODOS FUERON TRAIDO DE LA CLASE REGISTRO MASIVO PARA INDEPENDIZAR EL DEPURADOR Y USARLO COMO HERRAMIENTO PARA
 * GENERAR REPORTES
 */
public class modelo_reportes {
    private registro_ingenieria reging;
    private materias materia;
    private registro_pdf rpdf;
    private conexion_base_de_datos cbd;
    private materias2 materia2;


    private LinkedList<Integer> cant_mat = new LinkedList<Integer>();
    private LinkedList<String> comunitarios = new LinkedList<>();
    private LinkedList<String> est_y_mat = new LinkedList<>();
    private LinkedList<String> mat_y_cant = new LinkedList<>();


    //constructor
    public modelo_reportes() {
        //inicializando las clases cuando esta se esta cargando
        reging = new registro_ingenieria();
        materia = new materias();
        rpdf = new registro_pdf();
        cbd = new conexion_base_de_datos();
        materia2 = new materias2();

    }



    //get y set
    public LinkedList<Integer> getCant_mat() {
        return cant_mat;
    }

    public void setCant_mat(LinkedList<Integer> cant_mat) {
        this.cant_mat = cant_mat;
    }

    public LinkedList<String> getComunitarios() {
        return comunitarios;
    }

    public void setComunitarios(LinkedList<String> comunitarios) {
        this.comunitarios = comunitarios;
    }

    public LinkedList<String> getEst_y_mat() {
        return est_y_mat;
    }

    public void setEst_y_mat(LinkedList<String> est_y_mat) {
        this.est_y_mat = est_y_mat;
    }

    public LinkedList<String> getMat_y_cant() {
        return mat_y_cant;
    }

    public void setMat_y_cant(LinkedList<String> mat_y_cant) {
        this.mat_y_cant = mat_y_cant;
    }






















/**establece los valores dentro del LinkedList a cero, pero se generan la cantidad de registros necesarios
    para luego ir llenando segun el semestre y la cantidad de materia que sean requeridos.*/
    public void limpiando(){

    for(int i=0; i<=10; i++){
        this.getCant_mat().add(i, 0);
    }
}

   /**Este metodo cuenta las materias que debe el estudiante por semestre. */
public void conteo_materias_semestre(int semestre){

            //System.out.println("Posicion "+(semestre-1));
          //this.calculando_n_semestres(semestre);

           // System.out.println("Calculo : Semestre= "+semestre+"  x= "+this.getCant_mat().get(semestre-1)+" suma= "+(this.getCant_mat().get(semestre-1)+1));
            this.getCant_mat().set((semestre-1),(this.getCant_mat().get(semestre-1)+1));//sumando el numero de materias encontrada por semestre
           // System.out.println("Resultado: "+this.getCant_mat().get(semestre-1));

}

    public void conteo_materias_estudiantes_cantidad(String cedula, String codigo, String nombre_materia){
    boolean encontrado=false;

    //se guarda por estudiante las materias que debe
        this.getEst_y_mat().add(cedula);
        this.getEst_y_mat().add(codigo);
        this.getEst_y_mat().add(nombre_materia);

    //se guarda por materia la cantidad de alumnos que las debe

        if(this.getMat_y_cant().isEmpty()){//si esta vacio el LinkedList se agrega la materia y se suma uno porque es la primera
            // System.out.println("VACIO");
            this.getMat_y_cant().add(codigo);
            this.getMat_y_cant().add(nombre_materia);
            this.getMat_y_cant().add("1");

        }else{

            // System.out.println("CON INFORMACION");

            for(int mc=0; mc<this.getMat_y_cant().size(); mc=mc+3){//recorriendo el LinkedList

                // System.out.println("codigo actual: "+codigo+" - guardado: "+this.getMat_y_cant().get(mc));

                    if(codigo.equalsIgnoreCase(this.getMat_y_cant().get(mc+0))){
                   //     System.out.println("--------..------...- igual. codigo: "+codigo+" = "+this.getMat_y_cant().get(mc+2));
                        int total=0;
                        total=Integer.valueOf(this.getMat_y_cant().get(mc+2))+1;

                       this.getMat_y_cant().set(mc+2,String.valueOf(total));

                       encontrado=true;
                       break;

                    }

            }//fin recorrido


             if(encontrado==false){//si despues de recorrer el LinkedList no hallo ni siquiera el codigo de la materia la agrega como nueva porque no existe
               //  System.out.println("NO SE HALLO EN EL REGISTRO. AGRAGANDOLA AL MISMO");
                this.getMat_y_cant().add(codigo);
                this.getMat_y_cant().add(nombre_materia);
                this.getMat_y_cant().add("1");
             }

        }





}

/**Este metodo calcula practicamente todo respecto a un estudiante. solo se necesita conocer la cedula y su especialidad.
 se cuenta con un arreglo para elegir que informacion se quiere visualizar.*/
    public void depurador_estudiantes_semestres(String identidad, String especialidad){
      //  this.getEst_x_carrera().clear();
        this.cant_mat.clear();//limpiando el LinkedList que acumula la cantidad de materias por semestre
        this.limpiando();


            materia.getOrden().clear(); //limpiando la variable que almcena el record acomodado por periodo academico
            materia = new materias();//es para limpiar todas las variables
            rpdf = new registro_pdf();


           // System.out.println("CEDULA DEL ESTUDIANTE: "+identidad+" CARRERA:"+especialidad+"\n");

            if(identidad.isEmpty()){//validando que la casilla de la cedula no este vacia o este buena
              //      System.out.println("CAMPO CEDULA VACIA");

            }else{

               
                reging.verificar_alumno(cbd.getConexion(), identidad);//verificando que el alumno se encuentre registrado en el sistema
                                                                                        //para luego cargar su record y darle el resto de las opciones

                    if(reging.getControl()==0){//en caso de que el alumno se halla actualizado en el sistema
                
                      for(int v=0; v<=2; v++){//revisando la base de datos 3 veces para hallar en que vigencia se encuentra el alumno
                      //    System.out.println("viendo"+v);
                          materia.setSeleccion_pensum(v);
                          reging.setReconocimiento(v);               
                          reging.buscar_materias_alternativo(cbd.getConexion(), identidad,materia.notas_especialidad(reging));//cargando las materias del alumno
                          
                          if(reging.getRecord().isEmpty()){}else{break;}//condicional que solo verifica si el alumnos tiene materias en el record para proceder a realizar los siguientes analisis

                      }//fin revisando la base de datos 3 veces

                          //System.out.println("para materia: "+reging.getReconocimiento()+" v: "+materia.getSeleccion_pensum());

                            if(reging.getRecord().isEmpty()){//cuando no hay materias en el record del estudiante. Un nuevo o simplemente no se han cargado en la base de datos sus materias
                               //         ima.mensaje_informacion("ESTE ESTUDIANTE NO TIENE MATERIAS REGISTRADAS", "ADVERTENCIA", "precaucion", "png");

                                        materia.setNuevo_ingreso(1);                                        
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum aun si no tiene materias en record para saber mas adelante las que puede inscribir partiendo del pensu segun su vigencia

                                     //   materia.materias_pensum_equivalencias(cbd.getConexion(),materia.carrera_equivalencia(reging.getCarrera(),cambio));//cargando las materias de equivalencias
                            }
                            else{//en caso de que tenga materias registradas en el record academico
                                       
                                        materia.materias_pensum(cbd.getConexion(),reging.getCarrera(),reging.getTurnos());//cargando las materias del pensum
                                       // materia.materias_pensum_equivalencias(cbd.getConexion(),materia.carrera_equivalencia(reging.getCarrera(),cambio));//cargando las materias de equivalencias

                                            //---------ordenando las materias por periodo academico
                                       
                                              for(int b=0; b<reging.getRecord().size();b=b+11){//recorre buscando ordenar los periodos academicos desde el mas viejo hasta el mas reciente
                                                //  System.out.println("año: "+reging.getRecord().get(b+5));
                                                  materia.ordenamiento_año(reging.getRecord().get(b+5));
                                              }
                                                
                                                materia.ordenamiento_materias_alternativos(reging);//ordenando materia
                                                reging.setComp_per(materia.getOrden().get(5));//inicializando la variable con el primer periodo inscrito. Es para iniciar los analisis del primer periodo que el estudiante curso materia y esta en el record academico


                                              //--------------------------------------------------FIN




                                         }  //validacion de que halla informacion del estudiante en cuanto a notas
                                           //en la base de datos


                                                          
                                                        //LAS MATERIAS QUE EL ESTUDIANTE PUEDE VER DEPENDE DE ESTOS ANALISIS
                                                          materia.materias_preladas_y_no_preladas();//separa y analiza por grupo las materias que poseen prelacion y las que no
                                                          materia.esquema_prelacion();//analiza las materias que poseen prelaciones vs el record del estudiante
                                                       
                                                          materia.esquema_normal();//analiza las materias que no poseen prelaciones vs el record del estudiante
                                                          materia2.servicio_comunitario(materia);//analizando si el estudiante puede ver o no las materias comunitarias o si vio alguna cual o cuales fueron.


                                                         //--------listando los estudiantes que pueden ver servicio comunitario
                                                         if(materia2.isServicio()){
                                                             this.getComunitarios().add(reging.getCedula());//cedula
                                                             this.getComunitarios().add(reging.getEstudiante());// nombres y apellidos
                                                             this.getComunitarios().add(reging.getCarrera());//carrera
                                                             this.getComunitarios().add(reging.getTurnos());//turno del estudiante
                                                         }


                                                         //--------------------------------------------------------------




                                                     //     System.out.println("\n--------------MATERIAS AUTORIZADAS-------------");
    

                                                          for(int h=0; h<materia.getLista_autorizada().size();h=h+4){//buscando todas las materias autorizadas que el estudiante puede cursar

                                                               System.out.println("semestre: "+materia.getLista_autorizada().get(h+3)+" codigo: "+materia.getLista_autorizada().get(h+0)+" nombre: "+materia.getLista_autorizada().get(h+1)+" condicion: "+materia.getLista_autorizada().get(h+2));
                                                           
                                                               this.conteo_materias_estudiantes_cantidad(identidad, materia.getLista_autorizada().get(h+0) , materia.getLista_autorizada().get(h+1));

                                                               this.conteo_materias_semestre(Integer.valueOf(materia.getLista_autorizada().get(h+3)));


                                                           }//fin recorrido materias autorizadas

                                                      
                                                        System.out.println("\n---------------PROMEDIOS-----------------------");
                                                          //CALCULOS CORRESPONDIENTE A CADA ESTUDIANTE
                                                          DecimalFormat df = new DecimalFormat("0.00");


                                                          rpdf.calculo_alternativo(materia.getOrden(), materia.getPensum(), materia.getElectivas());
                                                          rpdf.calculo_orden_merito(materia.getOrden(), materia.getPensum(), materia.getElectivas());

                                                      
                                                          materia.getAux1().clear();
                                                          materia.getAux2().clear();

                                                          reging.getRecord().clear();//limpiando record alternativo

                                                          //materia.getPensum().clear();
                                                          materia.getLlenado().clear();
                                                          materia.getPosicion().clear();
                                                          materia.getPreladas().clear();
                                                          materia.getNo_preladas().clear();
                                                        //  materia.getLista_autorizada().clear();

                    }else{
                        System.out.println("ESTUDIANTE NO SE ENCUENTRA REGISTRADO EN EL SISTEMA");
                          
                          }//si el estudiante no esta en el sistema no se muestra nada


                    }//fin validacion de la casilla cedula este vacia




//System.out.println("visor 2 "+materia.getLista_autorizada().size());
}


public static void main(String [] args){

    modelo_reportes mr = new modelo_reportes();
    mr.depurador_estudiantes_semestres("10001820" , "LIC. CONTADURIA PUBLICA");



}









}//fin de la clase
