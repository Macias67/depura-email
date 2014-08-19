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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Correo;
import modelo.Grupo;
import modelo.MysqlConnect;
import modelo.NombreTablas;
import modelo.Origen;

/**
 *
 * @author Macias
 */
public class Buscador implements Runnable {

    private final MysqlConnect conexion;
    private DefaultTableModel tableModel;
    private JLabel label;
    private JTable tabla;

    public static final String[] NOMBRE_COLUMNAS = {"ID", "Correo", "Origen", "Grupo", "Habilitado"};

    private String consulta;
    private String origen;
    private String grupo;
    private boolean habilitado;

    public boolean BUSCANDO = false;

    public Buscador() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.conexion = MysqlConnect.getConnection();
    }

    public void setDataTable(JLabel label, JTable tabla) {
        this.label = label;
        this.tabla = tabla;
    }

    public void setParamBusqueda(String key, String origen, String grupo, boolean habilitado) {
        this.consulta = key;
        this.origen = origen;
        this.grupo = grupo;
        this.habilitado = habilitado;
    }

    private synchronized String[][] resultadoBusqueda()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        this.BUSCANDO = true;

        String select = "SELECT * FROM `" + NombreTablas.CORREOS.getValue() + "` ";
        String count = "SELECT COUNT(*) FROM `" + NombreTablas.CORREOS.getValue() + "` ";

        boolean skey = consulta.isEmpty();
        boolean sorigen = origen.isEmpty();
        boolean sgrupo = grupo.isEmpty();

        String query = null;
        String[] querys = new String[4];

        if (!skey) {
            if (StringValidation.validaDigitos(consulta)) {
                query = "`id_correo` = " + consulta + " ";
            } else {
                query = "`correo` LIKE '%" + consulta + "%' ";
            }
            querys[0] = query;
        }

        if (!sorigen) {
            query = "`id_origen` = " + new RegistraOrigen().getOrigenByName(origen).getId() + " ";
            querys[1] = query;
        }

        if (!sgrupo) {
            query = "`id_grupo` = " + new RegistraGrupo().getGrupoByName(grupo).getId() + " ";
            querys[2] = query;
        }

        querys[3] = "`habilitado` = '" + habilitado + "'";

        boolean where = true;
        String swhere = "";
        for (int i = 0; i < querys.length; i++) {
            if (querys[i] != null) {
                if (where) {
                    swhere = "WHERE ";
                } else {
                    swhere = "AND ";
                }
                select += swhere + querys[i];
                count += swhere + querys[i];
                where = false;
            }
        }

        
        ResultSet res_count = this.conexion.executeQuery(count);
        count = null;
        
        res_count.next();
        int num_resultados=res_count.getInt(1);
        
        if(num_resultados>5000){
            select+=" LIMIT 5000";
            System.out.println(select);
            JOptionPane.showMessageDialog(null, num_resultados+" Resultados \r\n -Solo se muestran los primeros 5,000 \r\n -Intenta una busqueda mas especifica","Demaciados resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        
        ResultSet resultado = this.conexion.executeQuery(select);
        select = null;

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
                sresultado[i] = listaCorreos.get(i).toArray();
            }
        }

        this.BUSCANDO = false;
        listaCorreos.clear();

        return sresultado;
    }

    @Override
    public void run() {
        try {
            this.label.setText("Buscando todas las coincidencias...");
            String[][] respuesta = this.resultadoBusqueda();
            this.tableModel = new DefaultTableModel(respuesta, NOMBRE_COLUMNAS);
            this.tabla.setModel(tableModel);

            String mensaje = (respuesta == null) ? "No se encontraron resultados"
                    : "Se encontraron " + respuesta.length + " resultados.";

            this.label.setText(mensaje);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
