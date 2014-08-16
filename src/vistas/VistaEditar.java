/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controlador.RegistraCorreo;
import controlador.RegistraGrupo;
import controlador.RegistraOrigen;
import helper.StringValidation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelo.Correo;
import modelo.Grupo;
import modelo.MyComboBoxModel;
import modelo.NombreTablas;
import modelo.Origen;

/**
 *
 * @author Macias | Diego
 */
public class VistaEditar extends javax.swing.JDialog {

    private Correo correoActual;
    private MyComboBoxModel comboBoxModel;

    /**
     * Creates new form VistaEditar
     * @param parent
     * @param modal
     */
    public VistaEditar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        try {
            comboBoxModel = MyComboBoxModel.getInstance();
            selectGrupo.setModel(comboBoxModel.getCbmodel(NombreTablas.GRUPOS));
            selectOrigen.setModel(comboBoxModel.getCbmodel(NombreTablas.ORIGENES));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(VistaEditar.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tfIdCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfCorreo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        selectOrigen = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        selectGrupo = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        cbxHabilitado = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        rbtnId = new javax.swing.JRadioButton();
        rbtnCorreo = new javax.swing.JRadioButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar correo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar Correo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("Correo:");

        tfCorreo.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Origen:");

        selectOrigen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectOrigen.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("Grupo:");

        selectGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectGrupo.setEnabled(false);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        cbxHabilitado.setText("Habilitado");
        cbxHabilitado.setEnabled(false);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        buttonGroup1.add(rbtnId);
        rbtnId.setSelected(true);
        rbtnId.setText("ID");

        buttonGroup1.add(rbtnCorreo);
        rbtnCorreo.setText("Correo");
        rbtnCorreo.setToolTipText("");

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfIdCorreo)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectGrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectOrigen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfCorreo)))))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxHabilitado)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtnId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbtnCorreo)))
                        .addGap(0, 202, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnGuardar, btnSalir});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnId)
                    .addComponent(rbtnCorreo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfIdCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(selectOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(selectGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxHabilitado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir)
                    .addComponent(btnCancelar))
                .addContainerGap())
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

    private void habilitarEdicion(){
        rbtnId.setEnabled(false);
        rbtnCorreo.setEnabled(false);
        tfIdCorreo.setEnabled(false);
        btnBuscar.setEnabled(false);
        
        tfCorreo.setEnabled(true);
        selectOrigen.setEnabled(true);
        selectGrupo.setEnabled(true);
        cbxHabilitado.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    
    private void reiniciarComponentes(){
        correoActual=null;
        
        tfIdCorreo.setText("");
        tfCorreo.setText("");
        selectOrigen.setSelectedIndex(0);
        selectGrupo.setSelectedIndex(0);
        cbxHabilitado.setSelected(false);
        
        rbtnId.setEnabled(true);
        rbtnCorreo.setEnabled(true);
        tfIdCorreo.setEnabled(true);
        btnBuscar.setEnabled(true);
        tfCorreo.setEnabled(false);
        selectOrigen.setEnabled(false);
        selectGrupo.setEnabled(false);
        cbxHabilitado.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String textoCorreo = tfIdCorreo.getText().trim();
        if (!textoCorreo.equals("")) {

            try {

                if (rbtnCorreo.isSelected()) {
                    correoActual = new RegistraCorreo().getCorreoByNombre(textoCorreo);
                } else if (rbtnId.isSelected()) {

                    Pattern patron = Pattern.compile("[0-9]+");

                    if (patron.matcher(textoCorreo).matches()) {
                        correoActual = new RegistraCorreo().getCorreoByID(textoCorreo);
                    } else {
                        JOptionPane.showMessageDialog(this, "Solo se aceptan dígitos como ID", "Escribe números", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if (correoActual != null) {
                    this.habilitarEdicion();
                    
                    tfCorreo.setText(correoActual.getNombre());
                    selectOrigen.setSelectedItem(correoActual.getOrigen().getNombre());
                    selectGrupo.setSelectedItem(correoActual.getGrupo().getNombre());
                    cbxHabilitado.setSelected(correoActual.isHabilitado());
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron resulados", "No existe correo", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(VistaEditar.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Escriba un ID o correo", "Campo vacío", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String correo = tfCorreo.getText();

        //VERIFICACION DE QUE SEA UN CORREO VALIDO
        if (StringValidation.validaCorreo(correo)) {
            try {
                //SE CREA OBJET Y SE INICIAN VARIABLES INDEPENDIENTEMENTE DE SI ES NUEVO O ACTUALIZACION
                RegistraCorreo registraCorreo = new RegistraCorreo();
                
                String sorigen = (String) selectOrigen.getSelectedItem();
                String sgrupo = (String) selectGrupo.getSelectedItem();
                boolean habilitado = cbxHabilitado.isSelected();

                Origen origen = new RegistraOrigen().getOrigenByName(sorigen);
                Grupo grupo = new RegistraGrupo().getGrupoByName(sgrupo);

                Correo nuevo = new Correo(correo, origen, grupo, habilitado);

                //VERIFICACION SI SE TRATA DE ACTUALIZACION O CORREO NUEVO
                if (correoActual.getNombre().equals(correo)) {
                    if (registraCorreo.editarCorreo("actualizar", correoActual, nuevo)) {
                        JOptionPane.showMessageDialog(this, "El correo ha sido actualizado.", "Correo actualizado", JOptionPane.INFORMATION_MESSAGE);
                        reiniciarComponentes();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se edito el correo.", "No se edito el correo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    //SI ES NUEVO CORREO VERIFICAMOS QUE NO SEA UNO YA EXISTENTE
                    if (!registraCorreo.existeNombreCorreo(correo)) {
                        if (registraCorreo.editarCorreo("nuevo", correoActual, nuevo)) {
                            JOptionPane.showMessageDialog(this, "El correo ha sido editado.", "Correo editado", JOptionPane.INFORMATION_MESSAGE);
                            reiniciarComponentes();
                        } else {
                            JOptionPane.showMessageDialog(this, "No se edito el correo.", "No se edito el correo", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Ya existe ese nombre de correo", "Ya existe correo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(VistaEditar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Escribe un correo válido", "Error de correo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.reiniciarComponentes();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VistaEditar dialog = new VistaEditar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbxHabilitado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbtnCorreo;
    private javax.swing.JRadioButton rbtnId;
    private javax.swing.JComboBox selectGrupo;
    private javax.swing.JComboBox selectOrigen;
    private javax.swing.JTextField tfCorreo;
    private javax.swing.JTextField tfIdCorreo;
    // End of variables declaration//GEN-END:variables
}
