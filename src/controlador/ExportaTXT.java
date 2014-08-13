

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
    private Origen origen;
    private Grupo grupo;

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
        String query;
        String linea;
        ResultSet respuesta;
        String ruta_escritorio=System.getProperty("user.home")+"\\Desktop\\";
  
        try {
            if (this.caso.equals("todos")) {
                BufferedWriter escribir_archivo = new BufferedWriter(new FileWriter(new File(ruta_escritorio+this.NombreArchivo)));
                //CONSULTA PARA SABER CUANTOS RESULTADOS RETORNA LA BUSQUEDA
                query="SELECT COUNT(`correo`) FROM `"+NombreTablas.CORREOS.getValue()+"` WHERE `habilitado` = 'true'";
                respuesta = this.conexion.executeQuery(query);
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
            } else if (this.caso.equals("origen-grupo")) {
                //
            }
            
            vistaLoading.dispose();
            JOptionPane.showMessageDialog(vistaLoading, "Exportación finalizada, nuevo documento : \r\n"+ruta_escritorio+NombreArchivo, "Fin del proceso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e + ".", "Error", JOptionPane.ERROR_MESSAGE);
        }
//        long inicio = System.currentTimeMillis();
//
//        try {
//            // INICIO PROCESO DE CONTAR TOTAL DE LINEAS -----------------------
//            BufferedReader contadorLineas = new BufferedReader(new FileReader(ruta));
//            //recorrido para saber el total de correos
//            String linea;
//            while ((linea = contadorLineas.readLine()) != null) {
//                total_correos++;
//            }
//            contadorLineas.close();
//            // TERMINO PROCESO DE CONTAR TOTAL DE LINEAS -----------------------
//
//            // INICIO PROCESO DE PROCESAR EL TXT -----------------------
//            BufferedReader leer_archivo = new BufferedReader(new FileReader(ruta));
//            Correo correo;
//            while ((linea = leer_archivo.readLine()) != null) {
//                linea = linea.trim();
//                //PRIMERO VERIFICAMOS QUE EL CORREO NO SE ENCUENTRE REPETIDO
//                if (registraCorreo.existeNombreCorreo(linea)) {
//                    // Si es repetido
//                    cont_repetidos++;
//                    //System.err.println("Correos repetidos: " + cont_repetidos + " = " + linea);
//                } else {
//                    //SI NO SE CENCUENTRA REPETIDO LO INSERTAMOS
//                    correo = new Correo(linea, origen, grupo, habilitado);
//                    if (registraCorreo.guardaCorreo(correo)) {
//                        cont_nuevos++;
//                        //System.out.println("Correos nuevos: " + cont_nuevos + " = " + linea);
//                    }
//                }
//                // Regla de 3 para porcentaje
//                int procesados = cont_nuevos + cont_repetidos;
//                int porcentaje = (procesados * 100) / total_correos;
//
//                // Actualizamos datos de la ventana
//                vistaLoading.lblInfo.setText("Procesados " + procesados + " de " + total_correos);
//                vistaLoading.pbProgreso.setValue(porcentaje);
//                vistaLoading.lblCompletado.setText(porcentaje + "% completado...");
//            }
//            //cerrar el buffer
//            leer_archivo.close();
//            // TERMINO EL PROCESO DE PROCESAR EL TXT -----------------------
//
//            //System.out.println("Correos nuevos: " + cont_nuevos + " - Correos repetidos: " + cont_repetidos);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(vistaLoading, "Error : " + e, "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
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
