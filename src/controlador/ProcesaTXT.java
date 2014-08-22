/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import helper.StringValidation;
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
    public int total_correos;
    public int cont_repetidos;
    public int cont_nuevos;

    // Control del HILO
    public boolean PROCESO;

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

        // INICIO BANDERA DE PROCESO
        this.PROCESO = true;

        // Creo arraylist necesarios
        ArrayList<String> correosNuevos = new ArrayList<>();
        ArrayList<String> correosBD = new ArrayList<>();

        try {
            // Leo el archivo TXT y los agrego al arraylist
            try (BufferedReader leerArchivo = new BufferedReader(new FileReader(ruta))) {
                String linea;
                while ((linea = leerArchivo.readLine())!= null) {
                    if(!(linea.trim()).equals("")){
                        correosNuevos.add(linea);
                    }
                }
                leerArchivo.close();
            }

            // Elimino los correos repetidos del arraylist generado del TXT
            HashSet<String> hashSet = new HashSet<>(correosNuevos);
            correosNuevos.clear();
            correosNuevos.addAll(hashSet);

            // Extrigo los correos de la base de datos
            String query = "SELECT `correo` FROM " + NombreTablas.CORREOS.getValue();
            ResultSet respuesta = this.conexion.executeQuery(query);

            // SI hay correos en la BD, entonces los guardo en su arraylist
            while (respuesta.next()) {
                correosBD.add(respuesta.getNString("correo"));
            }

            // Variables auxiliares
            Correo correo;
            total_correos = correosNuevos.size();

            // SI hay correos en la BD, comparo con los correos nuevos
            // a insertar para evitar insertar repetidos
            if (correosBD.size() > 0) {
                for (String correoNuevo : correosNuevos) {
                    // SI el correo ya esta en la BD, incremento contador
                    if (PROCESO && correosBD.contains(correoNuevo)) {
                        cont_repetidos++;
                    } else {
                        boolean chabilitado = (StringValidation.validaCorreo(correoNuevo)) ? habilitado : false;

                        // SI NO solo guardo en la base de datos
                        correo = new Correo(correoNuevo, origen, grupo, chabilitado);
                        if (PROCESO && registraCorreo.guardaCorreo(correo)) {
                            cont_nuevos++;
                        }
                    }
                    // Actulizo vista del loader
                    this.actualizaLoader();
                }
            } else {
                // SI NO solo inserto desde el arraylist de archivos nuevos
                for (String correoNuevo : correosNuevos) {
                    boolean chabilitado = (StringValidation.validaCorreo(correoNuevo)) ? habilitado : false;
                    
                    correo = new Correo(correoNuevo, origen, grupo, chabilitado);
                    if (PROCESO && registraCorreo.guardaCorreo(correo)) {
                        cont_nuevos++;
                        this.actualizaLoader();
                    }
                }
            }
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(vistaLoading, "Error : " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            correosBD.clear();
            correosNuevos.clear();
        }

        vistaLoading.dispose();
        JOptionPane.showMessageDialog(vistaLoading, "Termino el proceso. \r\n Correos repetidos: " + cont_repetidos + "\r\n Correos nuevos: " + cont_nuevos, "Fin del proceso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizaLoader() {
        // Regla de 3 para porcentaje
        int procesados = cont_nuevos + cont_repetidos;
        int porcentaje = (procesados * 100) / total_correos;
        // Actualizamos datos de la ventana
        vistaLoading.lblInfo.setText("Procesados " + procesados + " de " + total_correos);
        vistaLoading.pbProgreso.setValue(porcentaje);
        vistaLoading.lblCompletado.setText(porcentaje + "% completado...");
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
