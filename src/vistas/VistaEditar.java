
package vistas;

/**
 *
 * @author Diego Rodriguez
 */
public class VistaEditar extends javax.swing.JFrame {

    /**
     * Creates new form VistaEditar
     */
    public VistaEditar() {
        initComponents();
        this.setSize(350, 400);
        this.setTitle("Editar Correo");
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

        jLabel1 = new javax.swing.JLabel();
        id_correo = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        habilitado = new javax.swing.JCheckBox();
        guardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        origen = new javax.swing.JComboBox();
        grupo = new javax.swing.JComboBox();
        buscar = new javax.swing.JButton();

        getContentPane().setLayout(null);

        jLabel1.setText("ID de Correo: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 30, 90, 14);
        getContentPane().add(id_correo);
        id_correo.setBounds(110, 30, 190, 20);
        getContentPane().add(correo);
        correo.setBounds(90, 110, 210, 20);

        jLabel2.setText("Correo:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 110, 50, 14);

        jLabel3.setText("Origen: ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 160, 50, 14);

        jLabel4.setText("Grupo: ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 210, 50, 14);

        habilitado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        habilitado.setSelected(true);
        habilitado.setText("Habilitado");
        getContentPane().add(habilitado);
        habilitado.setBounds(30, 260, 100, 23);

        guardar.setText("Guardar");
        getContentPane().add(guardar);
        guardar.setBounds(120, 310, 90, 30);

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(cancelar);
        cancelar.setBounds(220, 310, 90, 30);

        origen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(origen);
        origen.setBounds(90, 160, 210, 20);

        grupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(grupo);
        grupo.setBounds(90, 210, 210, 20);

        buscar.setText("Buscar");
        getContentPane().add(buscar);
        buscar.setBounds(223, 60, 80, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        id_correo.setText("");
        correo.setText("");
        origen.setSelectedIndex(1);
        grupo.setSelectedIndex(1);
        habilitado.setSelected(true);
        this.setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaEditar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField correo;
    private javax.swing.JComboBox grupo;
    private javax.swing.JButton guardar;
    private javax.swing.JCheckBox habilitado;
    private javax.swing.JTextField id_correo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox origen;
    // End of variables declaration//GEN-END:variables
}
