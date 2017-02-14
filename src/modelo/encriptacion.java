package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Herman Alonso Barrates Víquez
 * @author modificado por Andy Cusatti
 */
public class encriptacion {

    //algoritmos
    private  String MD2 = "MD2";
    private  String MD5 = "MD5";
    private  String SHA1 = "SHA-1";
    private  String SHA256 = "SHA-256";
    private  String SHA384 = "SHA-384";
    private  String SHA512 = "SHA-512";
    private LinkedList<String> informacion = new LinkedList<>();
    private imagenes ima;

    public encriptacion() {
        ima = new imagenes();

    }







    public LinkedList<String> getInformacion() {
        return informacion;
    }

    public void setInformacion(LinkedList<String> informacion) {
        this.informacion = informacion;
    }







    /***
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private  String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }

    /***
     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     * @param message texto a encriptar
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
     * @return mensaje encriptado
     */
    public  String getStringMessageDigest(String message, String algorithm){
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando encriptacion");
        }

  //      System.out.println("cedula;"+message+";"+toHexadecimal(digest)+";0");
        return toHexadecimal(digest);
    }

public String conversor(String clave, String tipo_codificacion){

    return this.getStringMessageDigest(clave, tipo_codificacion);

}

public void listado(Connection con){
         Statement sentencia = null;
         ResultSet resultado = null;
         this.getInformacion().clear();//limpiando LinkedList antes de usar

        try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT cedula FROM control_de_estudio.datos_personales;");
                while(resultado.next()){
                    this.getInformacion().add(resultado.getString("cedula"));//guardando cedula
                    this.getInformacion().add(this.getStringMessageDigest(resultado.getString("cedula"), "MD2").substring(0, 10));//guardando la clave
                    this.getInformacion().add("0");//acuse de inscripcion
                }

            sentencia.close();
            resultado.close();

        } catch (SQLException ex) {
            Logger.getLogger(encriptacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(encriptacion.class.getName()).log(Level.SEVERE, null, ex);
            }


        }




    }













public void inscribiendo(Connection con){
       PreparedStatement preparada = null;


    try {

            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            preparada = con.prepareStatement("INSERT INTO control_de_estudio.claves VALUES (?,?,?);");

            for (int x = 0; x < this.getInformacion().size(); x = x + 3) {
                preparada.clearParameters();
                preparada.setString(1, this.getInformacion().get(x) ); //cedula
                preparada.setString(2, this.getInformacion().get(x+1)); //clave
                preparada.setString(3, this.getInformacion().get(x+2)); //acuse
                preparada.addBatch();
            }

            preparada.clearParameters();//limpiando parametros
            preparada.executeBatch();//ejecutando lotes

            con.commit();//haciendo permanente los cambios y liberando la base de datos
            //preparada.clearBatch();//limpiando listas de sentencias
            //preparada.close();//cerrando la sentencia
            ima.mensaje_informacion("LAS CLAVES HAS SIDO GENERADAS Y GUARDADAS CON EXITO\n CANTIDAD GENERADAS: "+(this.getInformacion().size()/3)+" CLAVES...!!!","GENERACION DE CLAVES", "claves","png");

        } catch (SQLException ex) {
            ima.mensaje_informacion("ERROR ENVIANDO INFORMACION A BASE DE DATOS. CLAVES\n"+ex.getMessage(),"ERROR CONEXION BASE DE DATOS", "error","png");
           
            Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preparada.clearBatch(); //limpiando listas de sentencias
                preparada.close(); //cerrando la sentencia
            } catch (SQLException ex) {
                Logger.getLogger(modelo_nuevos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }




}



    public static void main(String [] salida){

       /* conexion_base_de_datos cbd = new conexion_base_de_datos();
        cbd.setDireccion("localhost");

        encriptacion en = new encriptacion();
       //     en.getStringMessageDigest("15196976", "MD5");

        en.listado(cbd.getConexion());

        cbd = new conexion_base_de_datos();
        cbd.setDireccion("localhost");
        en.inscribiendo(cbd.getConexion());
        * 
        */
        
        
       
       
encriptacion en = new encriptacion();      
//System.out.println("codificacion: "+en.getStringMessageDigest("PVE1519697", "SHA-1"));
//System.out.println(en.getStringMessageDigest("aa1982cm||***...", "SHA-512")); //3e095c47965cd3954e0f3bdd546a7236840c7ce7eb5da0262aa114456234682a6256c7c520bffd05d3bf18ffb7fc3992bac8dfe25b77d8e691e00c55ff82e8f4
  //System.out.println(en.getStringMessageDigest("programador2013", "SHA-512"));//erx6458yth***...
//System.out.println(en.getStringMessageDigest("__**cagua2013", "SHA-512"));   //44d6bef187e1eee715f359d9c9571474ed5940fe24714342abace8a4647456820ca9d760b3ef49fa98e3e1f0fd39da90d7982777f2627132fca530efec5597c0

 System.exit(0);

}



}//fin de la clase


