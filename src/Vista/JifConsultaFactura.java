/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.DAO_Consulta_FacturaProd;
import Modelo.DTO_Consulta_FacturaProd;
import Tools.Excepciones;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MARCELO RUENES
 */
public class JifConsultaFactura extends javax.swing.JFrame {

    private JifListar_Facturas obJifListar_Facturas;
    private ArrayList<DTO_Consulta_FacturaProd> ConsultList;
    private Excepciones objExcepciones;
    private String NumFactura;
    private String FechaFactura;
    private String VendedorFactura;
    private String CorreoFactura;
    private String TotalFactura;
    private final DefaultTableModel modelo;
    private DAO_Consulta_FacturaProd objDataConsultaFactProd;

    public JifConsultaFactura() {
        initComponents();
        this.objExcepciones = new Excepciones();
        this.NumFactura = "";
        this.FechaFactura = "";
        this.VendedorFactura = "";
        this.CorreoFactura = "";
        this.TotalFactura = "";
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        //this.obJifListar_Facturas= new JifListar_Facturas();
        this.NumFactura = JifListar_Facturas.idFacturaSeleccionada;
        this.FechaFactura = JifListar_Facturas.FechaFacturaSeleccionada;
        this.CorreoFactura = JifListar_Facturas.CorreoFacturaSeleccionada;
        this.VendedorFactura = JifListar_Facturas.VendedorFacturaSeleccionada;
        this.TotalFactura = JifListar_Facturas.TotalFacturaSeleccionada;

        modelo.addColumn("Producto");
        modelo.addColumn("precio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");

        try {
            this.objDataConsultaFactProd = new DAO_Consulta_FacturaProd();
            this.ConsultList = this.objDataConsultaFactProd.consultAll(this.NumFactura);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        this.LblId.setText("FACTURA  #" + this.NumFactura);
        this.LblFecha.setText("FECHA VENTA: " + this.FechaFactura);
        this.LblVendedor.setText("VENDEDOR: " + this.VendedorFactura);
        this.LblCorreo.setText("CORREO CLIENTE: " + this.CorreoFactura);
        this.LblTotal.setText("TOTAL VENTA: $" + this.TotalFactura);

        //asignamos el modelo a nuestro jtable
        this.TablaFacturaProd.setModel(modelo);
        this.addRows();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LblId = new javax.swing.JLabel();
        LblFecha = new javax.swing.JLabel();
        LblCorreo = new javax.swing.JLabel();
        LblVendedor = new javax.swing.JLabel();
        BtnAceptar = new javax.swing.JButton();
        LblTotal = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaFacturaProd = new javax.swing.JTable();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(110, 80));
        setUndecorated(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("DETALLE DE VENTA:");

        LblId.setText("FACTURA  #");

        LblFecha.setText("FECHA VENTA:");

        LblCorreo.setText("CORREO CLIENTE:");

        LblVendedor.setText("VENDEDOR:");

        BtnAceptar.setText("ACEPTAR");
        BtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarActionPerformed(evt);
            }
        });

        LblTotal.setText("TOTAL VENTA:");

        TablaFacturaProd.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaFacturaProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TablaFacturaProdKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TablaFacturaProd);

        jScrollPane3.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblFecha)
                            .addComponent(LblCorreo)
                            .addComponent(LblVendedor)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblId)
                            .addComponent(jLabel1))))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LblTotal)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnAceptar)
                        .addGap(206, 206, 206))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblId)
                .addGap(26, 26, 26)
                .addComponent(LblFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblCorreo)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblTotal)
                .addGap(18, 18, 18)
                .addComponent(BtnAceptar)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarActionPerformed
        this.obJifListar_Facturas = new JifListar_Facturas();
        this.obJifListar_Facturas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BtnAceptarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.obJifListar_Facturas = new JifListar_Facturas();
            this.obJifListar_Facturas.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_formKeyPressed

    private void TablaFacturaProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaFacturaProdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.obJifListar_Facturas = new JifListar_Facturas();
            this.obJifListar_Facturas.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_TablaFacturaProdKeyPressed

    private void addRows() {
        this.ConsultList.forEach(ConsultaFactProd -> {
            this.modelo.addRow(new Object[]{
                ConsultaFactProd.getNombre_producto(),
                ConsultaFactProd.getPrecioProd(),
                ConsultaFactProd.getCantidad(),
                ConsultaFactProd.getPrecioXCant()});
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAceptar;
    private javax.swing.JLabel LblCorreo;
    private javax.swing.JLabel LblFecha;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblTotal;
    private javax.swing.JLabel LblVendedor;
    private javax.swing.JTable TablaFacturaProd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
