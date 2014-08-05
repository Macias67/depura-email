/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package email;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MysqlConnect;
import vistas.Principal;

/**
 *
 * @author Macias
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //Principal.main(args);
            MysqlConnect conexion = MysqlConnect.getConnection();
            int exito = conexion.executeUpdate("INSERT INTO `origenes` (`origin`) VALUES ('macias')");
            String resp = (exito==1) ? "consulta con exito" : "Consulta incorrecta";
            System.out.println(resp);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
