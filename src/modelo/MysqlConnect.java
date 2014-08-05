/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MaciasPC
 */
public class MysqlConnect {

    private final Connection conexion;
    private Statement statement;
    private ResultSet resultset;
    private static MysqlConnect db;

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost/";
    public static final String DATABASE = "sca";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    private MysqlConnect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(this.DRIVER).newInstance();
        this.conexion = (Connection) DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
    }

    public static synchronized MysqlConnect getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (db == null) {
            db = new MysqlConnect();
        }
        return db;
    }

    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not
     * available
     * @throws SQLException
     */
    public ResultSet executeQuery(String query) throws SQLException {
        statement = db.conexion.createStatement();
        resultset = statement.executeQuery(query);
        return resultset;
    }

    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int executeUpdate(String insertQuery) throws SQLException {
        statement = db.conexion.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
    }
    
    public boolean execute(String query) throws SQLException {
        statement = db.conexion.createStatement();
        boolean result = statement.execute(query);
        return result;
    }
    
    private void closeConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    private void closeStatement() throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
    
    private void closeResultSet() throws SQLException {
        if (resultset != null) {
            resultset.close();
        }
    }
    
    public void close() throws SQLException {
        closeResultSet();
        closeStatement();
        closeConexion();
    }

}
