

package controlador;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Grupo;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import modelo.Origen;
import vistas.VistaLoading;

/**
 * @author Diego
 */
public class ExportaTXT implements Runnable{

    private static ExportaTXT instance;
    private final MysqlConnect conexion;
    

    // Parametros para la exportacion
    private String caso;
    private String NombreArchivo;
    private Origen origen=null;
    private Grupo grupo=null;

    //Vista que muestra el proceso
    private VistaLoading vistaLoading;

    // Contadores
    private int total_correos;
    private int cont_escritos;
    
    private ExportaTXT() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }

    public static ExportaTXT getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (instance == null) {
            instance = new ExportaTXT();
        }
        return instance;
    }

    public void resetInstance() {
        instance = null;
    }
 
    public void setParametros(String caso, String NombreArchivo, String origen, String grupo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.caso=caso;
        this.NombreArchivo=NombreArchivo+".txt";
        
        if(origen!=null){
            this.origen = new RegistraOrigen().getOrigenByName(origen);
        }
        if(grupo!=null){
            this.grupo = new RegistraGrupo().getGrupoByName(grupo);
        }
    }

    public void setVistaLoading(VistaLoading vistaLoading) {
        this.vistaLoading = vistaLoading;
    }
    
    private void exportaTXT() throws SQLException {
        String query="";
        String query_contador="";
        String linea;
        ResultSet respuesta;
        boolean band=false;
        String ruta_escritorio=System.getProperty("user.home")+"\\Desktop\\";
  
        try {
            //BURRE SE USARA INDEPENDIENTEMENTE DEL CASO
             BufferedWriter escribir_archivo = new BufferedWriter(new FileWriter(new File(ruta_escritorio+this.NombreArchivo)));
             
            //EN CASO DE EXPORTAR TODO
            if (this.caso.equals("todos")) {
                
                //CONSULTA PARA SABER CUANTOS RESULTADOS RETORNA LA BUSQUEDA
                query_contador="SELECT COUNT(`correo`) FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true'";
                respuesta = this.conexion.executeQuery(query_contador);
                respuesta.next();
                total_correos=respuesta.getInt(1);
                
                //CONSULTA PARA EXTRAER TODOS LOS CORREOS DE LA BD
                query="SELECT `correo` FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true'";
                respuesta = this.conexion.executeQuery(query);
                
                while (respuesta.next()) {
                    cont_escritos++;
                    linea=respuesta.getNString("correo");
                    escribir_archivo.write(linea);
                    
                    if(cont_escritos<total_correos){
                        escribir_archivo.write("\r\n");
                    }
                     // Regla de 3 para porcentaje
                    int porcentaje = (cont_escritos * 100) / total_correos;
                    // Actualizamos datos de la ventana
                    vistaLoading.lblInfo.setText("Exportando " + cont_escritos + " de " + total_correos);
                    vistaLoading.pbProgreso.setValue(porcentaje);
                    vistaLoading.lblCompletado.setText(porcentaje + "% completado...");
                }
                
                escribir_archivo.close();
                band=true;
                
             //EN CASO DE EXPORTAR ORIGEN Y/O GRUPO 
            } else if (this.caso.equals("origen-grupo")) {
                
                //SE CREA LA CONSULTA DEPENDIENDO EL ORIGEN Y GRUPO SELECCIONADO
                if(origen!=null && grupo!=null){
                    query_contador="SELECT COUNT(`correo`) FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true' AND `id_origen` = "+origen.getId()+" AND `id_grupo` = "+grupo.getId();
                    query="SELECT `correo` FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true' AND `id_origen` = "+origen.getId()+" AND `id_grupo` = "+grupo.getId();
                    
                }else if(origen!=null && grupo==null){
                    query_contador="SELECT COUNT(`correo`) FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true' AND `id_origen` = "+origen.getId();
                    query="SELECT `correo` FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true' AND `id_origen` = "+origen.getId();
                    
                }else if(origen==null && grupo!=null){
                    query_contador="SELECT COUNT(`correo`) FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true' AND `id_grupo` = "+grupo.getId();
                    query="SELECT `correo` FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true' AND `id_grupo` = "+grupo.getId();
                }
                
                //CONSULTA PARA SABER CUANTOS RESULTADOS RETORNA LA BUSQUEDA
                respuesta = this.conexion.executeQuery(query_contador);
                respuesta.next();
                total_correos=respuesta.getInt(1);
                
                //CONSULTA PARA EXTRAER TODOS LOS CORREOS DE LA BD
                respuesta = this.conexion.executeQuery(query);
                
                while (respuesta.next()) {
                    cont_escritos++;
                    linea=respuesta.getNString("correo");
                    escribir_archivo.write(linea);
                    
                    if(cont_escritos<total_correos){
                        escribir_archivo.write("\r\n");
                    }
                     // Regla de 3 para porcentaje
                    int porcentaje = (cont_escritos * 100) / total_correos;
                    // Actualizamos datos de la ventana
                    vistaLoading.lblInfo.setText("Exportando " + cont_escritos + " de " + total_correos);
                    vistaLoading.pbProgreso.setValue(porcentaje);
                    vistaLoading.lblCompletado.setText(porcentaje + "% completado...");
                }
                
                escribir_archivo.close();
                band=true;
            }
                
            vistaLoading.dispose();
            if(band==true){
                JOptionPane.showMessageDialog(vistaLoading, "Exportación finalizada, nuevo documento : \r\n"+ruta_escritorio+NombreArchivo, "Fin del proceso", JOptionPane.INFORMATION_MESSAGE);   
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }
//        long inicio = System.currentTimeMillis();
        
//        long termina = System.currentTimeMillis();
//
//        long totaltiempo = termina - inicio;
//                      
//        String mensaje = "Se han guardo " + cont_nuevos + " correos nuevos de " + total_correos + ". ("+Benchmark.calculaTiempo(totaltiempo)+")";
//        
//        JOptionPane.showMessageDialog(vistaLoading, mensaje, "Fin del proceso", JOptionPane.INFORMATION_MESSAGE);
//        vistaLoading.dispose();
    }
    
    
    @Override
    public void run() {
        try {
            this.exportaTXT();
            this.resetInstance();
        } catch (SQLException ex) {
            Logger.getLogger(ExportaTXT.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vistaLoading, "Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }   
}
