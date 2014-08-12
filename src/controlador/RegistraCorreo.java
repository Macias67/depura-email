package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Correo;
import modelo.Grupo;
import modelo.Origen;
import modelo.MysqlConnect;
import modelo.NombreTablas;

/**
 * @author Diego
 */
public class RegistraCorreo {

    private final MysqlConnect conexion;

    public RegistraCorreo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }

    public boolean guardaCorreo(Correo correo) throws SQLException {
        String query = "INSERT INTO `" + NombreTablas.CORREOS.getValue() + "` (`correo`,`id_origen`,`id_grupo`,`habilitado`) "
                + "VALUES ('" + correo.getNombre() + "','" + correo.getOrigen().getId() + "','" + correo.getGrupo().getId() + "','" + correo.isHabilitado() + "')";
        int resp = this.conexion.executeUpdate(query);
        return (resp == 1);
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
}
