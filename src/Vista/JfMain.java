/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Excepciones;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class JfMain extends javax.swing.JFrame {

    /**
     * Creates new form JfMain
     */
    private JifProducto objJifProducto;
    private JifFactura objJifFactura;
    private JifListar_Productos obJifListar_Productos;
    private JifListar_Facturas obJifListar_Facturas;
    private JifEmpleado obJifEmpleado;
    private JifLogin objJifLogin;
    private JifCierre objJifCierre;
    private final Excepciones objExcepciones;

    public JfMain() {
        initComponents();
        this.setExtendedState(JfMain.MAXIMIZED_BOTH);
        this.objExcepciones = new Excepciones();

        this.objJifLogin = new JifLogin();
        if (this.objJifLogin.getObjPersona_Rol() == null) {
            this.MBR_Opciones.setEnabled(false);
        }
        this.objExcepciones.controlaInstancia(this.objJifLogin, this.jdpEscritorio);
        this.objJifLogin.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jdpEscritorio = new javax.swing.JDesktopPane();
        MBR_Opciones = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMAccionProd = new javax.swing.JMenu();
        jMINuevoProducto = new javax.swing.JMenuItem();
        jMIEliminarProd = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuFactura = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        ItemAgregEmp = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMCierre = new javax.swing.JMenu();
        jMIGenerarCierre = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jdpEscritorioLayout = new javax.swing.GroupLayout(jdpEscritorio);
        jdpEscritorio.setLayout(jdpEscritorioLayout);
        jdpEscritorioLayout.setHorizontalGroup(
            jdpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jdpEscritorioLayout.setVerticalGroup(
            jdpEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        jMenu1.setText("Producto");

        jMAccionProd.setText("Acción");

        jMINuevoProducto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMINuevoProducto.setText("Nuevo Producto");
        jMINuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMINuevoProductoActionPerformed(evt);
            }
        });
        jMAccionProd.add(jMINuevoProducto);

        jMIEliminarProd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMIEliminarProd.setText("Listar productos");
        jMIEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEliminarProdActionPerformed(evt);
            }
        });
        jMAccionProd.add(jMIEliminarProd);

        jMenu1.add(jMAccionProd);

        MBR_Opciones.add(jMenu1);

        jMenu3.setText("Venta");

        jMenu4.setText("Opciones");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Generar Factura");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenu3.add(jMenu4);

        jMenu5.setText("Consultar");

        menuFactura.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuFactura.setText("Ver factura");
        menuFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFacturaActionPerformed(evt);
            }
        });
        jMenu5.add(menuFactura);

        jMenu3.add(jMenu5);

        MBR_Opciones.add(jMenu3);

        jMenu6.setText("Persona");

        jMenu7.setText("Datos de empleado");

        ItemAgregEmp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        ItemAgregEmp.setText("Agregar Persona");
        ItemAgregEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemAgregEmpActionPerformed(evt);
            }
        });
        jMenu7.add(ItemAgregEmp);

        jMenu6.add(jMenu7);

        MBR_Opciones.add(jMenu6);

        jMenu2.setText("Cierre");

        jMCierre.setText("Acción");

        jMIGenerarCierre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMIGenerarCierre.setText("Generar Cierre");
        jMIGenerarCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGenerarCierreActionPerformed(evt);
            }
        });
        jMCierre.add(jMIGenerarCierre);

        jMenu2.add(jMCierre);
        jMenu2.add(jSeparator1);

        MBR_Opciones.add(jMenu2);

        setJMenuBar(MBR_Opciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpEscritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMINuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMINuevoProductoActionPerformed
        try {
            confGen("Módulo de producto");
            //visibilidad(false, false);
        } catch (IOException ex) {
            System.out.println("Se ha generado la excepción: " + ex.getMessage());
        }
    }//GEN-LAST:event_jMINuevoProductoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.objJifFactura = new JifFactura("Mi factura");
        this.objExcepciones.controlaInstancia(objJifFactura, this.jdpEscritorio);
        this.objJifFactura.setVisible(true);
        this.objJifFactura.setClosable(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMIEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEliminarProdActionPerformed
        this.obJifListar_Productos = new JifListar_Productos();
        this.objExcepciones.controlaInstancia(this.obJifListar_Productos, this.jdpEscritorio);
        this.obJifListar_Productos.setVisible(true);
        this.obJifListar_Productos.setClosable(true);
    }//GEN-LAST:event_jMIEliminarProdActionPerformed

    private void ItemAgregEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemAgregEmpActionPerformed
        this.obJifEmpleado = new JifEmpleado();
        this.objExcepciones.controlaInstancia(obJifEmpleado, jdpEscritorio);
        this.obJifEmpleado.setVisible(true);
        this.obJifEmpleado.setClosable(true);
    }//GEN-LAST:event_ItemAgregEmpActionPerformed

    private void jMIGenerarCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGenerarCierreActionPerformed
        this.objJifCierre = new JifCierre();
        this.objExcepciones.controlaInstancia(this.objJifCierre, this.jdpEscritorio);
        this.objJifCierre.setVisible(true);
        this.objJifCierre.setClosable(true);
    }//GEN-LAST:event_jMIGenerarCierreActionPerformed

    public void confGen(String Titulo) throws IOException {
        objJifProducto = new JifProducto(Titulo);
        objExcepciones.controlaInstancia(objJifProducto, this.jdpEscritorio);
        objJifProducto.setVisible(true);
        objJifProducto.setClosable(true);
    }

    public void visibilidad(boolean V1, boolean V2) {
        //objJifProducto.cmbCilindraje.setVisible(V1);
        //objJifProducto.cmbTipoSP.setVisible(V2);
        //objJifProducto.lblCilindraje.setVisible(V1);
        //objJifProducto.lblTSer.setVisible(V2);
    }

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
            java.util.logging.Logger.getLogger(JfMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ItemAgregEmp;
    private javax.swing.JMenuBar MBR_Opciones;
    private javax.swing.JMenu jMAccionProd;
    private javax.swing.JMenu jMCierre;
    private javax.swing.JMenuItem jMIEliminarProd;
    private javax.swing.JMenuItem jMIGenerarCierre;
    private javax.swing.JMenuItem jMINuevoProducto;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JDesktopPane jdpEscritorio;
    private javax.swing.JMenuItem menuFactura;
    // End of variables declaration//GEN-END:variables

    private void menuFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFacturaActionPerformed
        this.obJifListar_Facturas = new JifListar_Facturas();
        this.objExcepciones.controlaInstancia(this.obJifListar_Facturas, this.jdpEscritorio);
    }//GEN-LAST:event_menuFacturaActionPerformed

}
