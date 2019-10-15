/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.DAO_Factura;
import Controlador.DAO_Persona;
import Controlador.DAO_Producto;
import Modelo.FacturaPdf;
import Modelo.AtributosVenta;
import Modelo.DTO_Factura;
import Modelo.DTO_Persona;
import Modelo.DTO_Producto;
import Modelo.FacturaPdf;
import Modelo.TimestampCertificates;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import Modelo.Excepciones;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class JifFactura extends javax.swing.JInternalFrame {

    /**
     * Creates new form JifFactura
     */
    private final DefaultTableModel modelo;
    private DAO_Producto objDataProducto;
    private DAO_Persona objDataPersona;
    private DAO_Factura objDataFactura;
    private DTO_Producto objProducto;
    private Excepciones objExcepciones;
    private ArrayList<AtributosVenta> saleList;
    private FacturaPdf objPDF;
    private String Datos;
    private AtributosVenta objVentas;
    private TimestampCertificates tc;

    public JifFactura() {
        initComponents();
        this.TxtProducto.grabFocus();
        this.TxtProducto.requestFocusInWindow();
        this.objProducto = new DTO_Producto();
        this.objVentas = new AtributosVenta();
        this.tc = new TimestampCertificates();
        this.objExcepciones = new Excepciones();
        try {
            this.objDataProducto = new DAO_Producto();
            this.objDataPersona = new DAO_Persona();
            this.objDataFactura = new DAO_Factura();
        } catch (SQLException ex) {
            Logger.getLogger(JifFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.saleList = new ArrayList<>();
        objPDF = new FacturaPdf();
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        modelo.addColumn("Código de barras");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio unitario");
        modelo.addColumn("Precio total");

        this.TablaVentas.setModel(modelo);
        Datos = "";
//        this.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ADD) {
//                    SpnCantidad.setValue((int) SpnCantidad.getValue() + 1);
//                }
//            }
//            
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
    }

    public JifFactura(String titulo) {
        initComponents();
        this.lblTitulo.setText(titulo);
        this.objProducto = new DTO_Producto();
        this.saleList = new ArrayList<>();
        this.objVentas = new AtributosVenta();
        this.tc = new TimestampCertificates();
        this.objExcepciones = new Excepciones();
        try {
            this.objDataProducto = new DAO_Producto();
            this.objDataPersona = new DAO_Persona();
            this.objDataFactura = new DAO_Factura();
        } catch (SQLException ex) {
            Logger.getLogger(JifFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        modelo.addColumn("Código de barras");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio unitario");
        modelo.addColumn("Precio total");

        this.TablaVentas.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        TxtProducto = new javax.swing.JTextField();
        SpnCantidad = new javax.swing.JSpinner();
        BtnAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        TxtTotalCompra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        lblTitulo.setText("Título");

        TxtProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtProductoActionPerformed(evt);
            }
        });
        TxtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtProductoKeyReleased(evt);
            }
        });

        SpnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));
        SpnCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpnCantidadStateChanged(evt);
            }
        });
        SpnCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SpnCantidadMouseClicked(evt);
            }
        });
        SpnCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpnCantidadKeyPressed(evt);
            }
        });

        BtnAgregar.setText("GENERAR FACTURA");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        TablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaVentas);

        jScrollPane2.setViewportView(jScrollPane1);

        TxtTotalCompra.setEditable(false);
        TxtTotalCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTotalCompraActionPerformed(evt);
            }
        });

        jLabel2.setText("TOTAL VENTA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SpnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAgregar)
                        .addGap(0, 298, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAgregar))
                .addContainerGap(164, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtProductoActionPerformed
        //this.SpnCantidad.setValue(1); 
        try {
            int cant = 1;
            TablaVentas.getSelectionModel().clearSelection();
            DefaultTableModel modelo = new DefaultTableModel();
            for (int i = 0; i < TablaVentas.getRowCount(); i++) {
                if ((TablaVentas.getValueAt(i, 0).toString()).equals(TxtProducto.getText())) {
                    cant = Integer.parseInt(TablaVentas.getValueAt(i, 2).toString()) + 1;
                    TablaVentas.getSelectionModel().addSelectionInterval(i, i);
                    this.modelo.removeRow(TablaVentas.getSelectedRow());
                    this.SpnCantidad.setValue(cant);
                }
            }
            this.search();
            TablaVentas.getSelectionModel().addSelectionInterval(TablaVentas.getRowCount() - 1, TablaVentas.getRowCount() - 1);
            TotalVenta();
            this.TxtProducto.setText("");
            this.SpnCantidad.setValue(cant);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Este producto no se encuentra registrado");
            this.TxtProducto.setText("");
        }

    }//GEN-LAST:event_TxtProductoActionPerformed

    public void ModificarPorSpinner() {

        int filaSelect = TablaVentas.getSelectedRow();
        if (filaSelect >= 0) {
            if ((int) this.SpnCantidad.getValue() == 0) {
                modelo.removeRow(filaSelect);
            } else {

                //this.SpnCantidad.setValue(TablaVentas.getValueAt(filaSelect, 2));
                this.objVentas.setCant((int) this.SpnCantidad.getValue());
                this.objVentas.setTotal((int) this.SpnCantidad.getValue() * objVentas.getPrecio_unidad());
                modelo.removeRow(filaSelect);
                this.addRows();
                TablaVentas.getSelectionModel().addSelectionInterval(TablaVentas.getRowCount() - 1, TablaVentas.getRowCount() - 1);
            }
        }
    }

    double Total;

    public void TotalVenta() {

        if (TablaVentas.getRowCount() > 0) {

            Total = 0;

            double posicion = 0;
            for (int i = 0; i < TablaVentas.getRowCount(); i++) {
                posicion = Double.parseDouble(TablaVentas.getValueAt(i, 4).toString());
                Total += posicion;
            }
            String TotalText = String.valueOf(Total);
            this.TxtTotalCompra.setText(TotalText);
        } else {
            this.TxtTotalCompra.setText("");
        }

    }

    public void limpiarTabla() {

        int a = modelo.getRowCount() - 1;
        System.out.println(a);
        for (int i = a; i >= 0; i--) {
            System.out.println(i);
            modelo.removeRow(i);
        }
    }

    public void eventos(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            SpnCantidad.setValue((int) SpnCantidad.getValue() + 1);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            SpnCantidad.setValue((int) SpnCantidad.getValue() - 1);
        }
    }

    private void TxtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyPressed
        eventos(evt);
        TotalVenta();

    }//GEN-LAST:event_TxtProductoKeyPressed

    private DTO_Persona getPersonaById() throws SQLException {
        String item = "";
        return this.objDataPersona.getSinglePersona(item, true);
    }

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        factura.crear_PDF(Total, TablaVentas);
        try {
            //DAO_Factura objFactura = new DAO_Factura();
            FacturaPdf objfactpdf = new FacturaPdf();
            if (this.objExcepciones.validarCamposFactura(objfactpdf.idFactura(), "1123456789", objfactpdf.fechaactual(), "correo x", TxtTotalCompra)
                    && this.objExcepciones.validarCamposErrFactura(objfactpdf.idFactura(), "1123456789", objfactpdf.fechaactual(), "correo x", TxtTotalCompra)) {

                //@START: Nuevo Producto
                if (objDataFactura.RegistrarVenta(new DTO_Factura(objfactpdf.idFactura(),
                        this.objDataPersona.getSinglePersona("1123456789", true),
                        new Timestamp(new Date(), this.tc.getCertPath()),
                        "correo x",
                        Double.parseDouble(this.TxtTotalCompra.getText())
                ))) {
                    JOptionPane.showMessageDialog(this, "¡El producto " + objfactpdf.idFactura() + " se ha registrado con éxito!", "Registro de producto", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "Algo salió mal", "Registro de producto", JOptionPane.ERROR_MESSAGE);
                }
                //@END: Nuevo Producto                

            } else {
                JOptionPane.showMessageDialog(this, "No puede dejar campos vacios o erroneos", "Registro de producto", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JifFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void SpnCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpnCantidadStateChanged
        ModificarPorSpinner();
        //TotalVenta();
    }//GEN-LAST:event_SpnCantidadStateChanged

    private void SpnCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpnCantidadKeyPressed
        eventos(evt);
    }//GEN-LAST:event_SpnCantidadKeyPressed

    private void TablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentasMouseClicked

        this.SpnCantidad.setValue(TablaVentas.getValueAt(TablaVentas.getSelectedRow(), 2));

    }//GEN-LAST:event_TablaVentasMouseClicked

    private void SpnCantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SpnCantidadMouseClicked
        // TotalVenta();
    }//GEN-LAST:event_SpnCantidadMouseClicked

    private void TxtTotalCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTotalCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalCompraActionPerformed

    private void TxtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtProductoKeyReleased

    FacturaPdf factura = new FacturaPdf();


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JSpinner SpnCantidad;
    private javax.swing.JTable TablaVentas;
    private javax.swing.JTextField TxtProducto;
    private javax.swing.JTextField TxtTotalCompra;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables

    private void addRows() {
        this.saleList.forEach(venta -> {
            this.modelo.addRow(new Object[]{
                venta.getCodigo(),
                venta.getNombre(),
                venta.getCant(),
                venta.getPrecio_unidad(),
                venta.getTotal()});
        });
    }

    private void search() {

        try {
            this.saleList.clear();
//            System.out.println("Texto ");
            this.objVentas = this.objDataProducto.vistaVenta(this.TxtProducto.getText());
            this.objVentas.setCant((int) this.SpnCantidad.getValue());
            this.objVentas.setTotal((int) this.SpnCantidad.getValue() * objVentas.getPrecio_unidad());
            this.saleList.add(this.objVentas);

            this.addRows();
        } catch (SQLException ex) {
            Logger.getLogger(JifFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
