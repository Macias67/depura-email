/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Grupo;
import modelo.MysqlConnect;
import modelo.NombreTablas;

/**
 *
 * @author Macias
 */
public class RegistraGrupo {

    private final MysqlConnect conexion;
    private Grupo grupo;

    public RegistraGrupo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }

    public RegistraGrupo(Grupo grupo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.grupo = grupo;
        this.conexion = MysqlConnect.getConnection();
    }
    
    public boolean checarGrupo(Grupo grupo) throws SQLException{
        String query = "SELECT * FROM `"+NombreTablas.GRUPOS.getValue()+"` where `grupo`='"+grupo.getNombre()+"'";
        ResultSet respuesta = this.conexion.executeQuery(query);
        
        return (respuesta.next());
    }

    public boolean guardarGrupo() throws SQLException {
        String query = "INSERT INTO `" + NombreTablas.GRUPOS.getValue() + "` (`grupo`) VALUES ('" + this.grupo.getNombre() + "')";
        int resp = this.conexion.executeUpdate(query);
        return (resp == 1);
    }
    
    public Grupo getGrupoByID(int ID) throws SQLException {
        String query = "SELECT * FROM `"+NombreTablas.GRUPOS.getValue()+"` WHERE `id_grupo` = "+ID+" LIMIT 1";
        ResultSet respuesta = this.conexion.executeQuery(query);
        
        Grupo grupo = null;
        
        if (respuesta.next()) {
            int id_grupo = respuesta.getInt("id_grupo");
            String sgrupo = respuesta.getNString("grupo");
            grupo = new Grupo(id_grupo, sgrupo);
            return grupo;
        } else {
            return grupo;
        }
    } 
}
