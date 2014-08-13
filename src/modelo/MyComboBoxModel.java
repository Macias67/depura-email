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
public class MyComboBoxModel {

    private static MyComboBoxModel instance;
    private final MysqlConnect conexion;
    private DefaultComboBoxModel cbmodel;
    private ArrayList lista;

    private MyComboBoxModel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }

    public static MyComboBoxModel getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (instance == null) {
            instance = new MyComboBoxModel();
        }
        return instance;
    }

    public DefaultComboBoxModel getCbmodel(NombreTablas tabla) throws SQLException {
        setDataComboBoxModel(tabla);
        return cbmodel;
    }

    private void setDataComboBoxModel(NombreTablas tabla) throws SQLException {
        
        String table = "";
        
        if (tabla == NombreTablas.GRUPOS) {
            table = "grupo";
        } else if(tabla == NombreTablas.ORIGENES) {
            table = "origen";
        }
        
        String query = "SELECT `"+table+"` FROM " + tabla.getValue() + "";
        ResultSet resultado = this.conexion.executeQuery(query);

        lista = new ArrayList();

        while (resultado.next()) {
            lista.add(resultado.getString(table));
        }

        String[] array;

        if (lista.size() > 0) {
            array = new String[lista.size()+1];
            for (int i = 0; i < array.length; i++) {
                if (i==0) {
                    array[i] = "";
                    continue;
                }
                array[i] = (String) lista.get(i-1);
            }
        } else {
            array = new String[]{""};
        }
        cbmodel = new DefaultComboBoxModel(array);
    }

}
