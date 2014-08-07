/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Macias
 */
public class GrupoComboBoxModel {

    private static GrupoComboBoxModel instance;
    private final MysqlConnect conexion;
    private DefaultComboBoxModel cbmodel;
    private ArrayList lista;

    private GrupoComboBoxModel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }

    public static GrupoComboBoxModel getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (instance == null) {
            instance = new GrupoComboBoxModel();
        }
        return instance;
    }

    public DefaultComboBoxModel getCbmodel() {
        return cbmodel;
    }

    public void setDataComboBoxModel() throws SQLException {
        String query = "SELECT `grupo` FROM " + NombreTablas.GRUPOS.getValue() + "";
        ResultSet resultado = this.conexion.executeQuery(query);
        
        lista = new ArrayList();
        
        while (resultado.next()) {
            lista.add(resultado.getString("grupo"));
        }
        
        String[] array;
     
        if (lista.size() > 0) {
            array = new String[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                array[i] = (String) lista.get(i);
            }
        } else {
            array = new String[]{""};
        }
        cbmodel = new DefaultComboBoxModel(array);
    }

}
