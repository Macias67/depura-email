/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MyComboBoxModel;
import modelo.NombreTablas;
import vistas.carga.AddGrupo;
import vistas.carga.AddOrigen;

/**
 *
 * @author Luis Macias | Diego Rodriguez
 */
public class VistaCargar extends javax.swing.JDialog {
    
    private MyComboBoxModel comboBoxModel;
    
    private AddOrigen vistaAddOrigen;
    private AddGrupo vistaAddGrupo;

    /**
     * Creates new form Principal
     *
     * @param parent
     * @param modal
     */
    public VistaCargar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    
    private void init() {
        try {
            comboBoxModel = MyComboBoxModel.getInstance();
            origen_select.setModel(comboBoxModel.getCbmodel(NombreTablas.ORIGENES));
            grupo_select.setModel(comboBoxModel.getCbmodel(NombreTablas.GRUPOS));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(VistaCargar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        archivo_txt = new javax.swing.JTextField();
        seleccionar_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AddOrigen_btn = new javax.swing.JButton();
        origen_select = new javax.swing.JComboBox();
        AddGrupo_btn = new javax.swing.JButton();
        grupo_select = new javax.swing.JComboBox();
        procesar_archivo_btn = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargar TXT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("Ruta del archivo:");

        archivo_txt.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        archivo_txt.setEnabled(false);

        seleccionar_btn.setText("Seleccionar");
        seleccionar_btn.setToolTipText("");
        seleccionar_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionar_btnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("Origen:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Grupo:");

        AddOrigen_btn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        AddOrigen_btn.setText("+");
        AddOrigen_btn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        AddOrigen_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddOrigen_btnActionPerformed(evt);
            }
        });

        origen_select.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        AddGrupo_btn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        AddGrupo_btn.setText("+");
        AddGrupo_btn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        AddGrupo_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGrupo_btnActionPerformed(evt);
            }
        });

        procesar_archivo_btn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        procesar_archivo_btn.setText("Procesar archivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(procesar_archivo_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(archivo_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(seleccionar_btn))
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(AddOrigen_btn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(origen_select, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AddGrupo_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grupo_select, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(archivo_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionar_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddOrigen_btn)
                    .addComponent(origen_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddGrupo_btn)
                    .addComponent(grupo_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(procesar_archivo_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddOrigen_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddOrigen_btnActionPerformed
        // TODO add your handling code here:
        vistaAddOrigen = new AddOrigen(null, true);
        vistaAddOrigen.setLocationRelativeTo(null);
        vistaAddOrigen.setVisible(true);
    }//GEN-LAST:event_AddOrigen_btnActionPerformed

    private void AddGrupo_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGrupo_btnActionPerformed
        // TODO add your handling code here:
        vistaAddGrupo = new AddGrupo(null, true);
        vistaAddGrupo.setLocationRelativeTo(null);
        vistaAddGrupo.setVisible(true);
    }//GEN-LAST:event_AddGrupo_btnActionPerformed

    private void seleccionar_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionar_btnActionPerformed
        //Ventana de exploracion
        JFileChooser explorador = new JFileChooser();
        //Se le aplica un filtro al explorador
        explorador.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
        //abrimos el explorador y esperamos la desicion el usuario y abre un archivo o si calcela
        int returnVal = explorador.showOpenDialog(this);
        //si abre un archivo
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            //pintamos el nombre del archivo en el textfield por estetica
            archivo_txt.setText(explorador.getSelectedFile().getName());
            //guardamos la ruta del archivo en un objeto tipo File que procesaremos
            File ruta_archivo=explorador.getSelectedFile();
            
            try {
                //variable temporal que contendra la linea del archivo leida
                try ( //se crea el buffer que sera encargado de leer el archivo
                        BufferedReader leer_archivo = new BufferedReader(new FileReader(ruta_archivo))) {
                    //variable temporal que contendra la linea del archivo leida
                    String linea="";
                    int cont=1;
                    
                    //mientras la linea leida no sea nula se ejecuta el codigo
                    while ((linea=leer_archivo.readLine())!=null) {
                        System.out.println("Correo "+cont+" = "+linea);
                        cont++;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error : "+e);
            }
        }
    }//GEN-LAST:event_seleccionar_btnActionPerformed

    /**www.h
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VistaCargar dialog = new VistaCargar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddGrupo_btn;
    private javax.swing.JButton AddOrigen_btn;
    private javax.swing.JTextField archivo_txt;
    public static javax.swing.JComboBox grupo_select;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JComboBox origen_select;
    private javax.swing.JButton procesar_archivo_btn;
    private javax.swing.JButton seleccionar_btn;
    // End of variables declaration//GEN-END:variables
}
