
package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import static vistas.VistaCargar.selectGrupo;
import static vistas.VistaCargar.selectOrigen;

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
        
        System.out.println("Recibi la ruta: "+this.ruta);
        boolean band=false;
        
        try {
            //se crea el buffer que sera encargado de leer el archivo
            BufferedReader leer_archivo = new BufferedReader(new FileReader(this.ruta));
            //variable temporal que contendra la linea del archivo leida
            String linea="";
            int cont=1;
            
            //mientras la linea leida no sea nula se ejecuta el codigo
            while ((linea=leer_archivo.readLine())!=null) {
                String query = "INSERT INTO `"+NombreTablas.CORREOS.getValue()+"` (`correo`,`id_origen`,`id_grupo`,`habilitado`) VALUES ('"+linea+"','"+1+"','"+2+"','"+true+"')";
                int resp = this.conexion.executeUpdate(query);
                
                if(resp==1){
                    band=true;
                }else{
                    band=false;
                }
                
                System.out.println("Correo "+cont+" = "+linea);
                cont++;
            }
                
            leer_archivo.close();
            band=true;
        } catch (IOException e) {
            System.out.println("Error : "+e);
            band=false;
        }
        return (band);
    }
}
