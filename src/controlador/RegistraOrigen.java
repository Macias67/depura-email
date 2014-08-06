/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import modelo.Origen;

/**
 *
 * @author Macias
 */
public class RegistraOrigen {

    private final MysqlConnect conexion;
    private final Origen origen;

    public RegistraOrigen(Origen origen) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.origen = origen;
        this.conexion = MysqlConnect.getConnection();
    }
    
    public boolean guardarOrigen() throws SQLException {
        String query = "INSERT INTO `"+NombreTablas.ORIGENES.getValue()+"` (`origen`) VALUES ('"+this.origen.getNombre()+"')";
        int resp = this.conexion.executeUpdate(query);
        return (resp == 1);
    }
}
