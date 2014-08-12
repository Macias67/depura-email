package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Correo;
import modelo.Grupo;
import modelo.Origen;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import vistas.VistaCargar;
import vistas.VistaLoading;

/**
 * @author Diego
 */
public class RegistraCorreo implements Runnable {

    private final MysqlConnect conexion;
    private String ruta;
    private String origen;
    private String grupo;
    private boolean habilitado;
    private int cont_nuevos;
    private int cont_repetidos;
    private int total_correos;

    public RegistraCorreo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }

    public void setConfig(String ruta, String origen, String grupo, boolean habilitado) {
        this.ruta = ruta;
        this.origen = origen;
        this.grupo = grupo;
        this.habilitado = habilitado;
    }

    public boolean guardarCorreos() throws SQLException {
        //System.out.println("Recibi la ruta: "+this.ruta);
        boolean band;

        try {
            BufferedReader leer_archivo = new BufferedReader(new FileReader(ruta));
            BufferedReader contadorLineas = new BufferedReader(new FileReader(ruta));

            //consulta para obtener los id del origen y grupo seleccionado el los selects de la interfaz
            ResultSet resp_origen = this.conexion.executeQuery("SELECT id_origen FROM " + NombreTablas.ORIGENES.getValue() + " where `origen` = '" + origen + "'");
            ResultSet resp_grupo = this.conexion.executeQuery("SELECT id_grupo FROM " + NombreTablas.GRUPOS.getValue() + " where `grupo` = '" + grupo + "'");
            resp_grupo.next();
            resp_origen.next();

            String linea;
            int id_origen = resp_origen.getInt("id_origen");
            int id_grupo = resp_grupo.getInt("id_grupo");

            //recorrido para saber el totral de correos
            while ((linea = contadorLineas.readLine()) != null) {
                total_correos++;
            }

            //cerrar el buffer
            contadorLineas.close();

            while ((linea = leer_archivo.readLine()) != null) {
                linea = linea.trim();
                //PRIMERO VERIFICAMOS QUE EL CORREO NO SE ENCUENTRE REPETIDO
                String query_repetidos = "SELECT * FROM `" + NombreTablas.CORREOS.getValue() + "` where `correo` = '" + linea + "'";
                ResultSet respuesta = this.conexion.executeQuery(query_repetidos);

                //si la consulta regreso true, es porque encontro el correo y esta repetido
                if (respuesta.next()) {
                    cont_repetidos++;
                    System.err.println("Correos repetidos: " + cont_repetidos + " = " + linea);
                } else {
                    //SI NO SE CENCUENTRA REPETIDO LO INSERTAMOS
                    String query = "INSERT INTO `" + NombreTablas.CORREOS.getValue() + "` (`correo`,`id_origen`,`id_grupo`,`habilitado`) VALUES ('" + linea + "','" + id_origen + "','" + id_grupo + "','" + habilitado + "')";
                    this.conexion.executeUpdate(query);
                    cont_nuevos++;
                    System.out.println("Correos nuevos: " + cont_nuevos + " = " + linea);
                }

                int procesados = cont_nuevos + cont_repetidos;
                int porcentaje = (procesados * 100) / total_correos;

                VistaLoading.lblInfo.setText("Procesados " + (cont_nuevos + cont_repetidos) + " de " + total_correos);
                VistaLoading.pbProgreso.setValue(porcentaje);
                VistaLoading.lblCompletado.setText(porcentaje + "% Completado");
            }

            //cerrar el buffer
            leer_archivo.close();
            System.out.println("Correos nuevos: " + cont_nuevos + " - Correos repetidos: " + cont_repetidos);
            band = true;

        } catch (IOException e) {
            System.out.println("Error : " + e);
            band = false;
        }

        return (band);
    }

    public Correo getCorreoByID(String ID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "SELECT * FROM `" + NombreTablas.CORREOS.getValue() + "` WHERE `id_correo` = " + ID + " LIMIT 1";
        ResultSet respuesta = this.conexion.executeQuery(query);

        if (respuesta.next()) {
            int id = respuesta.getInt("id_correo");
            String scorreo = respuesta.getNString("correo");
            int id_origen = respuesta.getInt("id_origen");
            int id_grupo = respuesta.getInt("id_grupo");
            boolean habilitado = Boolean.parseBoolean(respuesta.getNString("habilitado"));

            Origen origen = new RegistraOrigen().getOrigenByID(id_origen);
            Grupo grupo = new RegistraGrupo().getGrupoByID(id_grupo);

            return new Correo(id, scorreo, origen, grupo, habilitado);
        } else {
            return null;
        }
    }

    public Correo getCorreoByNombre(String nombre) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "SELECT * FROM `" + NombreTablas.CORREOS.getValue() + "` WHERE `correo` = '" + nombre + "' LIMIT 1";
        ResultSet respuesta = this.conexion.executeQuery(query);

        if (respuesta.next()) {
            int id = respuesta.getInt("id_correo");
            String scorreo = respuesta.getNString("correo");
            int id_origen = respuesta.getInt("id_origen");
            int id_grupo = respuesta.getInt("id_grupo");
            boolean habilitado = Boolean.parseBoolean(respuesta.getNString("habilitado"));

            Origen origen = new RegistraOrigen().getOrigenByID(id_origen);
            Grupo grupo = new RegistraGrupo().getGrupoByID(id_grupo);

            return new Correo(id, scorreo, origen, grupo, habilitado);
        } else {
            return null;
        }
    }

    public boolean editarCorreo(Correo actual, Correo nuevo) throws SQLException {
        // Deshabilito el correo actual
        String query = "UPDATE `" + NombreTablas.CORREOS.getValue() + "` SET `habilitado` = 'false' WHERE `id_correo` = " + actual.getId();
        int update = this.conexion.executeUpdate(query);
        // Inserto nuevo registro
        query = "INSERT INTO `" + NombreTablas.CORREOS.getValue() + "` (`correo`,`id_origen`,`id_grupo`,`habilitado`) "
                + "VALUES ('" + nuevo.getNombre() + "','" + nuevo.getOrigen().getId() + "','" + nuevo.getGrupo().getId() + "','" + nuevo.isHabilitado() + "')";
        int insert = this.conexion.executeUpdate(query);

        return (update == 1 && insert == 1);
    }

    public boolean existeNombreCorreo(String nombre) throws SQLException {
        String query = "SELECT `correo` FROM `" + NombreTablas.CORREOS.getValue() + "` WHERE `correo` = '" + nombre + "' LIMIT 1";
        ResultSet respuesta = this.conexion.executeQuery(query);
        
        boolean existe = respuesta.next();
        respuesta.close();
        
        return existe;
    }

    @Override
    public void run() {
        try {
            if (guardarCorreos()) {
                VistaCargar.tfArchivo.setText("");
                JOptionPane.showMessageDialog(null, "Termino el procesamiento de correos \r\n Correos nuevos: " + cont_nuevos + "\r\n Correos repetidos: " + cont_repetidos, "Nombre añadido.", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: No se guardaron tus correos :/ ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistraCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
