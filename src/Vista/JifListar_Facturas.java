/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.DAO_Factura;
import Modelo.DTO_Factura;
import Tools.Excepciones;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.beans.PropertyVetoException;

import java.security.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MARCELO RUENES
 */
public class JifListar_Facturas extends javax.swing.JInternalFrame {

    private final DefaultTableModel modelo;
    private JifFactura jifFactura;
    private JifConsultaFactura objconsultaFactProd;
    private DTO_Factura objFactura;
    private DAO_Factura objDataFactura;
    private ArrayList<DTO_Factura> SaleList;
    private Excepciones objExcepciones;
    public static String idFacturaSeleccionada;
    public static String VendedorFacturaSeleccionada;
    public static String FechaFacturaSeleccionada;
    public static String CorreoFacturaSeleccionada;
    public static String TotalFacturaSeleccionada;
    //private String id;

    public JifListar_Facturas() {
        initComponents();
        //this.id = "";
        this.CorreoFacturaSeleccionada="";
        this.objExcepciones = new Excepciones();
        this.SaleList = new ArrayList<>();
        this.objconsultaFactProd = new JifConsultaFactura();

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        modelo.addColumn("Codigo de factura");
        modelo.addColumn("Vendedor");
        modelo.addColumn("Fecha");
        modelo.addColumn("Correo del cliente");
        modelo.addColumn("Total factura");

        try {
            this.objDataFactura = new DAO_Factura();
            this.SaleList = this.objDataFactura.getAllSales();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        //asignamos el modelo a nuestro jtable
        this.Tbl_Facturas.setModel(modelo);
        this.addRows();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_Facturas = new javax.swing.JTable();
        TxtBuscarFact = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        Tbl_Facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tbl_Facturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_FacturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tbl_Facturas);

        TxtBuscarFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtBuscarFactActionPerformed(evt);
            }
        });
        TxtBuscarFact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtBuscarFactKeyPressed(evt);
            }
        });

        jLabel1.setText("Buscar factura :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(TxtBuscarFact, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtBuscarFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtBuscarFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtBuscarFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtBuscarFactActionPerformed

    private void Tbl_FacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_FacturasMouseClicked
        if (evt.getClickCount() == 2) {
            idFacturaSeleccionada= Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 0).toString();
            VendedorFacturaSeleccionada= Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 1).toString();
            FechaFacturaSeleccionada= Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 2).toString();
            if (Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 3)==null) {
            this.CorreoFacturaSeleccionada= "Sin correo electronico";
            }else{
            this.CorreoFacturaSeleccionada= Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 3).toString();}
            TotalFacturaSeleccionada= Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 4).toString();
            String idFactura =Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 2).toString();
            this.objconsultaFactProd = new JifConsultaFactura();
            this.objconsultaFactProd.setVisible(true);
        } else if   (evt.getClickCount() == 1) {
            this.objconsultaFactProd.setVisible(false);
        } 
    }//GEN-LAST:event_Tbl_FacturasMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (evt.getClickCount() == 1) {
            this.objconsultaFactProd.setVisible(false);
        }
    }//GEN-LAST:event_formMouseClicked

    private void TxtBuscarFactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBuscarFactKeyPressed
        this.search();
    }//GEN-LAST:event_TxtBuscarFactKeyPressed
        private void Txt_SearchActionPerformed(java.awt.event.ActionEvent evt) {
            this.search();
            this.TxtBuscarFact.setText("");
        }

        private void Txt_SearchKeyReleased(java.awt.event.KeyEvent evt) {
            this.search();
        }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tbl_Facturas;
    private javax.swing.JTextField TxtBuscarFact;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void addRows() {
        this.SaleList.forEach(Factura -> {
            this.modelo.addRow(new Object[]{
                Factura.getId_Factura(),
                Factura.getPersona().getNombre(),
                Factura.getFecha().getTimestamp(),
                Factura.getCorreo(),
                Factura.getGran_Total()});
        });
    }
    
    public String idFactura(){
    return this.Tbl_Facturas.getValueAt(Tbl_Facturas.getSelectedRow(), 0).toString();
    }

    private void search() {
        try {
            this.SaleList.clear();
            for (int i = this.Tbl_Facturas.getRowCount() - 1; i >= 0; i--) {
                this.modelo.removeRow(i);
            }
            this.SaleList = this.objDataFactura.getFacturasByQuery(this.TxtBuscarFact.getText().toUpperCase());
            this.addRows();

        } catch (SQLException ex) {
            Logger.getLogger(JifListar_Productos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
