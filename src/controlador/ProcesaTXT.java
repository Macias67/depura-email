/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mysql.jdbc.MySQLConnection;
import helper.Benchmark;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Correo;
import modelo.Grupo;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import modelo.Origen;
import vistas.VistaLoading;

/**
 *
 * @author Macias
 */
public class ProcesaTXT implements Runnable {

    private static ProcesaTXT instance;
    private final RegistraCorreo registraCorreo;
    private final MysqlConnect conexion;

    // Parametros para correo
    private String ruta;
    private Origen origen;
    private Grupo grupo;
    private boolean habilitado;

    //Vista que muestra el proceso
    private VistaLoading vistaLoading;

    // Contadores
    private int total_correos;
    private int cont_repetidos;
    private int cont_nuevos;

    /**
     * Constructor
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private ProcesaTXT() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.registraCorreo = new RegistraCorreo();
        this.conexion = MysqlConnect.getConnection();
    }

    public static ProcesaTXT getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (instance == null) {
            instance = new ProcesaTXT();
        }
        return instance;
    }

    public void resetInstance() {
        instance = null;
    }

    public void setParametros(String ruta, String origen, String grupo, boolean habilitado) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.ruta = ruta;
        this.origen = new RegistraOrigen().getOrigenByName(origen);
        this.grupo = new RegistraGrupo().getGrupoByName(grupo);
        this.habilitado = habilitado;
    }

    public void setVistaLoading(VistaLoading vistaLoading) {
        this.vistaLoading = vistaLoading;
    }

    private void procesaTXT() throws SQLException {
        ArrayList <String> CorreosNuevos = new ArrayList <String>();
        ArrayList <String> CorreosDB = new ArrayList <String>();
        ArrayList <String> CorreosBuenos = new ArrayList <String>();
        
        try {
            BufferedReader leer_archivo = new BufferedReader(new FileReader(ruta));
            ResultSet respuesta;
            String linea,query;
            Correo correo;
            
            while ((linea=leer_archivo.readLine())!=null) {                
                CorreosNuevos.add(linea);
                total_correos++;
            }
            leer_archivo.close();
            
            //ELIMINAMOS REPETIDOS EN ARCHIVO NUEVO CON HashSet
            HashSet<String> hashSet = new HashSet<String>(CorreosNuevos);
            CorreosNuevos.clear();
            CorreosNuevos.addAll(hashSet);
            
            query="SELECT `correo` FROM "+NombreTablas.CORREOS.getValue();
            respuesta = this.conexion.executeQuery(query);
            
            while (respuesta.next()) {
                CorreosDB.add(respuesta.getNString("correo"));
            }
            
            int correosañadidos=0;
            int total=0;
            
            if(CorreosDB.size()>0){
                for (int i = 0; i < CorreosNuevos.size(); i++) {
                    if(CorreosDB.contains(CorreosNuevos.get(i))){
                        cont_repetidos++;
                        //total=(cont_repetidos+correosañadidos);
                        //System.err.println("Totales: "+total+" - Correos repetidos: "+cont_repetidos+" = "+CorreosNuevos.get(i));
                        CorreosNuevos.set(i, "");
                    }else{
                        correosañadidos++;
                        //total = (cont_repetidos + correosañadidos);
                        //System.out.println("Totales: " + total + " - Correos nuevos: " + correosañadidos + " = " + CorreosNuevos.get(i));
                        CorreosBuenos.add(CorreosNuevos.get(i));
                    }
                }
            }
            
            if(CorreosBuenos.size()>0){
                System.out.println("entre a correos buenos");
                for (int i = 0; i < CorreosBuenos.size(); i++) {
                    correo = new Correo(CorreosBuenos.get(i), origen, grupo, habilitado);
                    if (registraCorreo.guardaCorreo(correo)) {
                        cont_nuevos++;
                        //System.out.println("Correos nuevos: " + cont_nuevos + " = " +CorreosBuenos.get(i));
                        
                        // Regla de 3 para porcentaje
                        int procesados = cont_nuevos + cont_repetidos;
                        int porcentaje = (procesados * 100) / total_correos;

                        // Actualizamos datos de la ventana
                        vistaLoading.lblInfo.setText("Procesados " + procesados + " de " + total_correos);
                        vistaLoading.pbProgreso.setValue(porcentaje);
                        vistaLoading.lblCompletado.setText(porcentaje + "% completado...");
                    }
                }
            }else{
                HashSet<String> hashSet2 = new HashSet<String>(CorreosNuevos);
                CorreosNuevos.clear();
                CorreosNuevos.addAll(hashSet2);
                
                for (int i = 0; i < CorreosNuevos.size(); i++) {
                    
                    if(!CorreosNuevos.get(i).equals("")){
                        correo = new Correo(CorreosNuevos.get(i), origen, grupo, habilitado);
                        if (registraCorreo.guardaCorreo(correo)) {
                            cont_nuevos++;
                            //System.out.println("Correos nuevos: " + cont_nuevos + " = " + CorreosNuevos.get(i));
                            // Regla de 3 para porcentaje
                            int procesados = cont_nuevos + cont_repetidos;
                            int porcentaje = (procesados * 100) / total_correos;

                            // Actualizamos datos de la ventana
                            vistaLoading.lblInfo.setText("Procesados " + procesados + " de " + total_correos);
                            vistaLoading.pbProgreso.setValue(porcentaje);
                            vistaLoading.lblCompletado.setText(porcentaje + "% completado...");
                        } 
                    }
                }
            }  
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vistaLoading, "Error : " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        JOptionPane.showMessageDialog(vistaLoading, "Termino el proceso \r\n Correos repetidos: "+cont_repetidos+"\r\n Correos nuevos: "+cont_nuevos, "Fin del proceso", JOptionPane.INFORMATION_MESSAGE);
        vistaLoading.dispose();
        
//
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
            this.procesaTXT();
            this.resetInstance();
        } catch (SQLException ex) {
            Logger.getLogger(ProcesaTXT.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vistaLoading, "Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
