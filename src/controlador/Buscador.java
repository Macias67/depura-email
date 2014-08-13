/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import helper.StringValidation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Correo;
import modelo.Grupo;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import modelo.Origen;

/**
 *
 * @author Macias
 */
public class Buscador {
    
    private final MysqlConnect conexion;
    
    public Buscador() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }
    
    public String[][] resultadoBusqueda(String key, String origen, String grupo, boolean habilitado) 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        String query ="SELECT * FROM `"+NombreTablas.CORREOS.getValue()+"` ";
        
        if (StringValidation.validaDigitos(key)) {
            query += "WHERE `id_correo` = "+key+" ";
        } else {
            query += "WHERE `correo` LIKE '%"+key+"%' ";
        }
        
        if (!origen.isEmpty()) {
            query += "AND `id_origen` = "+new RegistraOrigen().getOrigenByName(origen).getId()+" ";
        }
        
        if (!grupo.isEmpty()) {
            query += "AND `id_grupo` = "+new RegistraGrupo().getGrupoByName(grupo).getId()+" ";
        }
        
        if (habilitado) {
            query += "AND `habilitado` = '"+habilitado+"'";
        }
        
        query = query.trim()+";";
        
        ResultSet resultado = this.conexion.executeQuery(query);
        
        ArrayList<Correo> listaCorreos = new ArrayList<Correo>();
        
        while (resultado.next()) {
            int id = resultado.getInt("id_correo");
            String correo = resultado.getNString("correo");
            Origen corigen = new RegistraOrigen().getOrigenByID(resultado.getInt("id_origen"));
            Grupo cgrupo = new RegistraGrupo().getGrupoByID(resultado.getInt("id_grupo"));
            boolean chabilitado = Boolean.parseBoolean(resultado.getNString("habilitado"));
            
            listaCorreos.add(new Correo(id, correo, corigen, cgrupo, chabilitado));
        }
        
        int totalCorreos = listaCorreos.size();
        
        
        String[][] sresultado = null;
        
        if (totalCorreos > 0) {
            sresultado = new String[totalCorreos][5];
            for (int i = 0; i < totalCorreos; i++) {
                    
                    Correo rcorreo = listaCorreos.get(i);
                    
                    sresultado[i][0] = rcorreo.getId()+"";
                    sresultado[i][1] = rcorreo.getNombre();
                    sresultado[i][2] = rcorreo.getOrigen().getNombre();
                    sresultado[i][3] = rcorreo.getGrupo().getNombre();
                    sresultado[i][4] = rcorreo.isHabilitado()+"";
            }
        }
        
        return sresultado;
    }
    
}
