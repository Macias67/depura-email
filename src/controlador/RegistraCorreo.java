
package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import vistas.VistaCargar;

/**
 * @author Diego
 */
public class RegistraCorreo {
    
    private final MysqlConnect conexion;
    private final String ruta;
    
    public RegistraCorreo(String ruta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.ruta= ruta;
        this.conexion = MysqlConnect.getConnection();
    }
    
    public boolean guardarCorreos() throws SQLException{
        //System.out.println("Recibi la ruta: "+this.ruta);
        boolean band=false;
        
        try {
            
            BufferedReader leer_archivo = new BufferedReader(new FileReader(this.ruta));
            
            ResultSet resp_origen = this.conexion.executeQuery("SELECT id_origen FROM "+NombreTablas.ORIGENES.getValue()+" where `origen` = '"+VistaCargar.selectOrigen.getSelectedItem()+"'");
            ResultSet resp_grupo = this.conexion.executeQuery("SELECT id_grupo FROM "+NombreTablas.GRUPOS.getValue()+" where `grupo` = '"+VistaCargar.selectGrupo.getSelectedItem()+"'");
            resp_grupo.next();
            resp_origen.next();
            
            String linea;
            int cont_nuevos=0;
            int cont_repetidos=0;
            int id_origen = resp_origen.getInt("id_origen");
            int id_grupo = resp_grupo.getInt("id_grupo");
            
            while ((linea=leer_archivo.readLine())!=null) {
                
                linea=linea.trim();
                //PRIMERO VERIFICAMOS QUE EL CORREO NO SE ENCUENTRE REPETIDO
                String query_repetidos="SELECT * FROM `"+NombreTablas.CORREOS.getValue()+"` where `correo` = '"+linea+"'";
                ResultSet respuesta = this.conexion.executeQuery(query_repetidos);
                
                //si la consulta regreso true, es porque encontro el correo y esta repetido
                if(respuesta.next()){
                    cont_repetidos++;
                    //System.err.println("Correos repetidos: "+cont_repetidos+" = "+linea);
                }else{
                    //SI NO SE CENCUENTRA REPETIDO LO INSERTAMOS
                    String query = "INSERT INTO `"+NombreTablas.CORREOS.getValue()+"` (`correo`,`id_origen`,`id_grupo`,`habilitado`) VALUES ('"+linea+"','"+id_origen+"','"+id_grupo+"','"+true+"')";
                    this.conexion.executeUpdate(query);
                    cont_nuevos++;
                    //System.out.println("Correos nuevos: "+cont_nuevos+" = "+linea);
                }
            }
            
            //cerrar el buffer
            leer_archivo.close();
            band=true;
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
