/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
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
    
    public boolean checarOrigen(Origen origen) throws SQLException{
        String query = "SELECT * FROM `"+NombreTablas.ORIGENES.getValue()+"` where `origen`='"+origen.getNombre()+"'";
        ResultSet respuesta = this.conexion.executeQuery(query);
        
        return (respuesta.next());
    }
    
    public boolean guardarOrigen() throws SQLException {
        String query = "INSERT INTO `"+NombreTablas.ORIGENES.getValue()+"` (`origen`) VALUES ('"+this.origen.getNombre()+"')";
        int resp = this.conexion.executeUpdate(query);
        return (resp == 1);
    }
}
