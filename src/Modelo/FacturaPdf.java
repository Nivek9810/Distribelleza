/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.*;
//archivos
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Rectangle;

//librerias ajenas a itext
import javax.swing.JFileChooser;
import com.itextpdf.text.Paragraph;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author MARCELO RUENES
 */
public class FacturaPdf {
    
    public String  idFactura(){
    Date fechaid = new Date();
    DateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
    DateFormat formatoHora = new SimpleDateFormat("HHmmss");
    return formatoFecha.format(fechaid) + formatoHora.format(fechaid);
    }

    

    private File ruta_destino;
    private Excepciones objExc;

    public FacturaPdf() {
        ruta_destino = null;
        objExc = new Excepciones();
    }

    public  String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");  
        return formatoFecha.format(fecha)+" "+formatoHora.format(fecha);
    }

    /* metodo que hace uso de la clase itext para manipular archivos PDF*/
    public void crear_PDF(double totalventa, javax.swing.JTable TablaVentas) {
        //abre ventana de dialogo "guardar"
        Colocar_Destino();
        //si destino es diferente de null
        if (this.ruta_destino != null) {

            try {
                Document doc = new Document();
                PdfWriter pw = PdfWriter.getInstance(doc, new FileOutputStream(this.ruta_destino + ".pdf"));
                doc.open();
                PdfContentByte cb = pw.getDirectContent();

//                    
                PdfPCell cellImagen = new PdfPCell();

                doc.open();
                PdfPTable TablaLogo = new PdfPTable(1);
                TablaLogo.setWidthPercentage(12f);
                PdfPCell cellImage;
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("C:\\Users\\MARCELO RUENES\\Desktop\\Distribelleza\\Distribelleza\\lobo.png");
                cellImagen.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                cellImagen = new PdfPCell(image);
                TablaLogo.addCell(cellImagen);
                doc.add(TablaLogo);

                PdfPTable TablaEncabezado = new PdfPTable(1);
                String encabezado = "DISTRIBELLEZA\n Nit: 1022433741-1\nDireccion cll a#b 1-3\n"
                        + "correo: algo@hotmail.com\n ----------------------------------";
                Paragraph Titulo = new Paragraph(encabezado, FontFactory.getFont("arial", 12, java.awt.Font.PLAIN));
                Titulo.setAlignment(Element.ALIGN_CENTER);
                doc.add(Titulo);

                PdfPCell celdaB = new PdfPCell();
                celdaB.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                celdaB.setBackgroundColor(BaseColor.WHITE);
                celdaB.setVerticalAlignment(Element.ALIGN_MIDDLE);
                celdaB.addElement(new Paragraph("Fecha: " + fechaactual(), FontFactory.getFont("arial", 14, java.awt.Font.PLAIN)));
                celdaB.addElement(new Paragraph("ID: " + idFactura(), FontFactory.getFont("arial", 14, java.awt.Font.PLAIN)));
                TablaEncabezado.addCell(celdaB);
                doc.add(TablaEncabezado);

                PdfPTable TablaVentaFactura = new PdfPTable(4);
                TablaVentaFactura.addCell("Producto");
                TablaVentaFactura.addCell("Precio unidad");
                TablaVentaFactura.addCell("Cantidad");
                TablaVentaFactura.addCell("Total");

                for (int i = 0; i < TablaVentas.getRowCount(); i++) {
                    String Producto = TablaVentas.getValueAt(i, 1).toString();
                    String PrecioUnit = "$ " + TablaVentas.getValueAt(i, 3).toString();
                    String Cantidad = TablaVentas.getValueAt(i, 2).toString();
                    String Total = "$ " + TablaVentas.getValueAt(i, 4).toString();

                    TablaVentaFactura.addCell(Producto);
                    TablaVentaFactura.addCell(PrecioUnit);
                    TablaVentaFactura.addCell(Cantidad);
                    TablaVentaFactura.addCell(Total);
                }
                doc.add(TablaVentaFactura);

                PdfPTable TablaTotal = new PdfPTable(1);
                PdfPCell TotalFactura = new PdfPCell();
                TotalFactura.setBorder(Rectangle.NO_BORDER);
                TotalFactura.addElement(new Paragraph("Total venta= $ "
                        + totalventa, FontFactory.getFont("arial", 12, java.awt.Font.PLAIN)));
                TotalFactura.setVerticalAlignment(Element.ALIGN_RIGHT);
                //TotalFactura.setal

                TablaTotal.addCell(TotalFactura);
                doc.add(TablaTotal);

                doc.close();

                JOptionPane.showMessageDialog(null, "Creado");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e);
            }

        }
    }

    /* abre la ventana de dialogo GUARDAR*/
    public void Colocar_Destino() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf", "PDF");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    }
}
