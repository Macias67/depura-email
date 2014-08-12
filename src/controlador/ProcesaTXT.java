/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Correo;
import modelo.Grupo;
import modelo.MysqlConnect;
import modelo.Origen;
import vistas.VistaLoading;

/**
 *
 * @author Macias
 */
public class ProcesaTXT implements Runnable {

    private static ProcesaTXT instance;
    private final RegistraCorreo registraCorreo;

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
    }
    
    public static ProcesaTXT getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (instance == null) {
            instance = new ProcesaTXT();
        }
        return instance;
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

        try {
            //FileReader fileReader = new FileReader(ruta);
            // INICIO PROCESO DE CONTAR TOTAL DE LINEAS -----------------------
            BufferedReader contadorLineas = new BufferedReader(new FileReader(ruta));
            //recorrido para saber el total de correos
            String linea;
            while ((linea = contadorLineas.readLine()) != null) {
                total_correos++;
            }
            contadorLineas.close();
            // TERMINO PROCESO DE CONTAR TOTAL DE LINEAS -----------------------

            // INICIO PROCESO DE PROCESAR EL TXT -----------------------
            BufferedReader leer_archivo = new BufferedReader(new FileReader(ruta));
            Correo correo;
            while ((linea = leer_archivo.readLine()) != null) {
                linea = linea.trim();
                //PRIMERO VERIFICAMOS QUE EL CORREO NO SE ENCUENTRE REPETIDO
                if (registraCorreo.existeNombreCorreo(linea)) {
                    // Si es repetido
                    cont_repetidos++;
                    System.err.println("Correos repetidos: " + cont_repetidos + " = " + linea);
                } else {
                    //SI NO SE CENCUENTRA REPETIDO LO INSERTAMOS
                    correo = new Correo(linea, origen, grupo, habilitado);
                    if (registraCorreo.guardaCorreo(correo)) {
                        cont_nuevos++;
                        System.out.println("Correos nuevos: " + cont_nuevos + " = " + linea);
                    }
                }
                // Regla de 3 para porcentaje
                int procesados = cont_nuevos + cont_repetidos;
                int porcentaje = (procesados * 100) / total_correos;

                // Actualizamos datos de la ventana
                vistaLoading.lblInfo.setText("Procesados " + procesados + " de " + total_correos);
                vistaLoading.pbProgreso.setValue(porcentaje);
                vistaLoading.lblCompletado.setText(porcentaje + "% completado...");
            }
            //cerrar el buffer
            leer_archivo.close();
            // TERMINO EL PROCESO DE PROCESAR EL TXT -----------------------

            //System.out.println("Correos nuevos: " + cont_nuevos + " - Correos repetidos: " + cont_repetidos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vistaLoading, "Error : " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void run() {
        try {
            this.procesaTXT();
        } catch (SQLException ex) {
            Logger.getLogger(ProcesaTXT.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vistaLoading, "Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
