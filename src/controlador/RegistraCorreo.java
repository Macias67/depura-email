
package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.MysqlConnect;
import modelo.NombreTablas;

/**
 * @author Diego
 */
public class RegistraCorreo {
    
    private final MysqlConnect conexion;
    private String ruta;
    
    public RegistraCorreo(String ruta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.ruta= ruta;
        this.conexion = MysqlConnect.getConnection();
    }
    
    public boolean guardarCorreos() throws SQLException{
        //System.out.println("Recibi la ruta: "+this.ruta);
        boolean band=false;
        
        try {
            
            BufferedReader leer_archivo = new BufferedReader(new FileReader(this.ruta));
            
            String linea="";
            int cont_nuevos=0;
            int cont_repetidos=0;
            
            
            while ((linea=leer_archivo.readLine())!=null) {
                
                linea=linea.trim();
                
                //PRIMERO VERIFICAMOS QUE EL CORREO NO SE ENCUENTRE REPETIDO
                String query_repetidos="SELECT * FROM `"+NombreTablas.CORREOS.getValue()+"` where `correo` = '"+linea+"'";
                ResultSet respuesta = this.conexion.executeQuery(query_repetidos);
                
                //si la consulta regreso true, es porque encontro el correo y esta repetido
                if(respuesta.next()){
                    cont_repetidos++;
                    System.out.println("Correos repetidos: "+cont_repetidos+" = "+linea);
                    band=true;
                }else{
                    //SI NO SE CENCUENTRA REPETIDO LO INSERTAMOS
                    String query = "INSERT INTO `"+NombreTablas.CORREOS.getValue()+"` (`correo`,`id_origen`,`id_grupo`,`habilitado`) VALUES ('"+linea+"','"+1+"','"+2+"','"+true+"')";
                    int resp = this.conexion.executeUpdate(query);
                
                    if(resp==1){
                        cont_nuevos++;
                        System.out.println("Correos nuevos: "+cont_nuevos+" = "+linea);
                        band=true;
                    }else{
                        band=false;
                        break;
                    }
                    
                }
                
            }
            
            //cerrar el buffer
            leer_archivo.close();
            
            vistas.VistaCargar.correosNuevos=cont_nuevos;
            vistas.VistaCargar.correosRepetidos=cont_repetidos;
            System.out.println("Correos nuevos: "+cont_nuevos+" - Correos repetidos: "+cont_repetidos);
            
        } catch (IOException e) {
            System.out.println("Error : "+e);
            band=false;
        }
        
        return (band);
    }
}
