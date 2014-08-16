package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Correo;
import modelo.Grupo;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import modelo.Origen;

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

    public boolean editarCorreo(String caso, Correo actual, Correo nuevo) throws SQLException {
        boolean band=false;
        String query;
        int update;
        
        switch (caso){
            case "actualizar":
                query="UPDATE `"+NombreTablas.CORREOS.getValue()+"` SET `id_origen`="+nuevo.getOrigen().getId()+", `id_grupo`="+nuevo.getGrupo().getId()+", `habilitado`='"+nuevo.isHabilitado()+"' WHERE `id_correo` = "+actual.getId();
                update = this.conexion.executeUpdate(query);
                
                band=(update==1);
            break;
            
            case "nuevo":
                // Deshabilito el correo actual
                query = "UPDATE `" + NombreTablas.CORREOS.getValue() + "` SET `habilitado` = 'false' WHERE `id_correo` = " + actual.getId();
                update = this.conexion.executeUpdate(query);
                // Inserto nuevo registro
                query = "INSERT INTO `" + NombreTablas.CORREOS.getValue() + "` (`correo`,`id_origen`,`id_grupo`,`habilitado`) "
                        + "VALUES ('" + nuevo.getNombre() + "','" + nuevo.getOrigen().getId() + "','" + nuevo.getGrupo().getId() + "','" + nuevo.isHabilitado() + "')";
                int insert = this.conexion.executeUpdate(query);

                band=(update == 1 && insert == 1);
            break;
                
            default:
                JOptionPane.showMessageDialog(null, "Error en el switch", "Error en controlador RegistraCorreo", JOptionPane.WARNING_MESSAGE);
            break;
        }
        
        return band;
    }

    public boolean existeNombreCorreo(String nombre) throws SQLException {
        String query = "SELECT `correo` FROM `" + NombreTablas.CORREOS.getValue() + "` WHERE `correo` = '" + nombre + "' LIMIT 1";
        ResultSet respuesta = this.conexion.executeQuery(query);

        boolean existe = respuesta.next();
        respuesta.close();
        return existe;
    }
    
    public boolean eliminarCorreoByID(String ID) throws SQLException {
        String query = "DELETE FROM "+NombreTablas.CORREOS.getValue()+" WHERE `id_correo` = "+ID;
        int respuesta = this.conexion.executeUpdate(query);
        
        return (respuesta==1);
    }
}
