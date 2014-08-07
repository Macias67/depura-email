/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controlador.RegistraCorreo;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import javax.swing.JOptionPane;
import vistas.carga.AddGrupo;
import vistas.carga.AddOrigen;

/**
 *
 * @author Luis Macias | Diego Rodriguez
 */
public class VistaCargar extends javax.swing.JDialog {
    
    private AddOrigen vistaAddOrigen;
    private AddGrupo vistaAddGrupo;
    private String rutaArchivo;
    public static int correosNuevos;
    public static int correosRepetidos;

    /**
     * Creates new form Principal
     *
     * @param parent
     * @param modal
     */
    public VistaCargar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
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
        tfArchivo = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAddOrigen = new javax.swing.JButton();
        selectOrigen = new javax.swing.JComboBox();
        btnAddGrupo = new javax.swing.JButton();
        selectGrupo = new javax.swing.JComboBox();
        btnProcesar = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargar TXT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("Ruta del archivo:");

        tfArchivo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tfArchivo.setEnabled(false);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setToolTipText("");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("Origen:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Grupo:");

        btnAddOrigen.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnAddOrigen.setText("+");
        btnAddOrigen.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnAddOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOrigenActionPerformed(evt);
            }
        });

        selectOrigen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAddGrupo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnAddGrupo.setText("+");
        btnAddGrupo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnAddGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGrupoActionPerformed(evt);
            }
        });

        selectGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnProcesar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnProcesar.setText("Procesar archivo");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(tfArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSeleccionar))
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAddOrigen)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(selectOrigen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddGrupo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddOrigen)
                    .addComponent(selectOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddGrupo)
                    .addComponent(selectGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnAddOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOrigenActionPerformed
        // TODO add your handling code here:
        vistaAddOrigen = new AddOrigen(Principal.getInstance(), true);
        vistaAddOrigen.setLocationRelativeTo(this);
        vistaAddOrigen.setVisible(true);
    }//GEN-LAST:event_btnAddOrigenActionPerformed

    private void btnAddGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGrupoActionPerformed
        // TODO add your handling code here:
        vistaAddGrupo = new AddGrupo(Principal.getInstance(), true);
        vistaAddGrupo.setLocationRelativeTo(this);
        vistaAddGrupo.setVisible(true);
    }//GEN-LAST:event_btnAddGrupoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
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
            tfArchivo.setText(explorador.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
       //revision de que hay un archivo seleccionado
        if(tfArchivo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "No ha seleccionado nungun archivo!", "Problemas.", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                rutaArchivo=tfArchivo.getText();
                RegistraCorreo registraCoreo = new RegistraCorreo(rutaArchivo);
                
                if(registraCoreo.guardarCorreos()){
                    tfArchivo.setText("");
                    JOptionPane.showMessageDialog(this, "Termino el procesamiento de correos \r\n Correos nuevos: "+correosNuevos+"\r\n Correos repetidos: "+correosRepetidos, "Nombre añadido.", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "ERROR: No se guardaron tus correos :/ ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR: "+e+".", "Error", JOptionPane.ERROR_MESSAGE);
            }   
        }
    }//GEN-LAST:event_btnProcesarActionPerformed

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
    private javax.swing.JButton btnAddGrupo;
    private javax.swing.JButton btnAddOrigen;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JComboBox selectGrupo;
    public static javax.swing.JComboBox selectOrigen;
    private javax.swing.JTextField tfArchivo;
    // End of variables declaration//GEN-END:variables
}
