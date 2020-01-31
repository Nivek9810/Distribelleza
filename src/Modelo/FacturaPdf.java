/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Tools.Excepciones;
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
import com.itextpdf.text.Image;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 *
 * @author MARCELO RUENES
 */
public class FacturaPdf {

    public static String CodigoFactura;

    public String idFactura() {
        Date fechaid = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
        DateFormat formatoHora = new SimpleDateFormat("HHmmss");
        return formatoFecha.format(fechaid) + formatoHora.format(fechaid);
    }

    private File ruta_destino;
    private Excepciones objExc;
    String idfactura= idFactura();

    public FacturaPdf() {
        ruta_destino = null;
        objExc = new Excepciones();
    }

    public String fechaactual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoFecha.format(fecha) + " " + formatoHora.format(fecha);
    }

    /* metodo que hace uso de la clase itext para manipular archivos PDF*/
    public void crear_PDF(double totalventa, javax.swing.JTable TablaVentas) {
        //abre ventana de dialogo "guardar"
        this.CodigoFactura = idFactura();
        //Colocar_Destino();
        this.ruta_destino = new File("C:\\Users\\MARCELO RUENES\\Documents\\Fact#" + idfactura);
        if (this.ruta_destino != null) {

            try {
                Document doc = new Document();
                PdfWriter pw = PdfWriter.getInstance(doc, new FileOutputStream(this.ruta_destino + ".pdf"));
                doc.open();
                PdfContentByte cb = pw.getDirectContent();

                doc.open();

                Image imagen = Image.getInstance("C:\\Users\\MARCELO RUENES\\Desktop\\Distribelleza\\Distribelleza\\lobo.png");
                imagen.scaleAbsolute(40, 60);
                imagen.setAlignment(12);
                doc.add(imagen);

                PdfPTable TablaEncabezado = new PdfPTable(1);
                String encabezado = "DISTRIBELLEZA\n Nit: 1022433741-1\nDireccion cll a#b 1-3\n"
                        + "correo: algo@hotmail.com\n ==============================\n";
                Paragraph Titulo = new Paragraph(encabezado, FontFactory.getFont("arial", 12, java.awt.Font.PLAIN));
                Barcode39 codigoBarras = new Barcode39();
                codigoBarras.setCode(CodigoFactura);
                Titulo.setAlignment(Element.ALIGN_LEFT);
                doc.add(Titulo);

                Image codimagen = codigoBarras.createImageWithBarcode(pw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                codimagen.setAlignment(Element.ALIGN_LEFT);
                doc.add(codimagen);

                String TextoFecha = "\nFecha: " + fechaactual() + "\nFactura #" + CodigoFactura
                        + "\n------------------------------------------------------\n\n";
                Paragraph textofecha = new Paragraph(TextoFecha, FontFactory.getFont("arial", 12, java.awt.Font.PLAIN));
                textofecha.setAlignment(Element.ALIGN_LEFT);
                doc.add(textofecha);

                for (int i = 0; i < TablaVentas.getRowCount(); i++) {
                    String Producto = TablaVentas.getValueAt(i, 1).toString();
                    String PrecioUnit = "$" + TablaVentas.getValueAt(i, 3).toString();
                    String Cantidad = TablaVentas.getValueAt(i, 2).toString();
                    String Total = "$" + TablaVentas.getValueAt(i, 4).toString();

                    String Contenido = Producto + "   " + PrecioUnit + " X" + Cantidad + "  =   " + Total;
                    Paragraph contenido = new Paragraph(Contenido, FontFactory.getFont("arial", 12, java.awt.Font.PLAIN));
                    textofecha.setAlignment(Element.ALIGN_LEFT);
                    doc.add(contenido);
                }

                PdfPTable TablaTotal = new PdfPTable(1);
                PdfPCell TotalFactura = new PdfPCell();
                TotalFactura.setBorder(Rectangle.NO_BORDER);
                TotalFactura.addElement(new Paragraph("\nTotal venta= $ "
                        + totalventa, FontFactory.getFont("arial", 14, java.awt.Font.PLAIN)));
                TotalFactura.setVerticalAlignment(Element.ALIGN_LEFT);

                TablaTotal.addCell(TotalFactura);
                doc.add(TablaTotal);

                doc.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e);
            }

        }
    }

    public void impresion_automatica() {
        
        
        // printer.toFile("impresion.txt");

      FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\MARCELO RUENES\\Documents\\Fact#" +idfactura+".pdf");
            //inputStream = new FileInputStream("C:\\Users\\MARCELO RUENES\\Documents\\iwfebWEFBWEBG.pdf");
         } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();


        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        
        
//        //Tipo de dato a imprimir:autodetectado
//        
//        DocFlavor.INPUT_STREAM flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        //DocFlavor flavor =  DocFlavor.INPUT_STREAM.AUTOSENSE;
//        //Se coge el servicio de impresion por defecto(impresora predeterminada)
//        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
//
//        if (service != null) {
//            //Se crea un trabajo o servicio de impresion
//            DocPrintJob job = service.createPrintJob();
//
//            //Se crea los atributos del servicio a crear
//            DocAttributeSet das = new HashDocAttributeSet();
//
//            //Se crea el objeto archivo a imprimir
//            FileInputStream fis = new FileInputStream("C:\\Users\\MARCELO RUENES\\Documents\\Fact#" + idfactura+".pdf");
//
//            //Creacion de documento a imprimir)
//            Doc doc = new SimpleDoc(fis, flavor, das);
//            //Se envia el trabajo o servicio a imprimir
//            job.print(doc, null);
//        }
        
        


        /*esta funcion es para escoger en donde guardar pdf*/
//    public void Colocar_Destino() {
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf", "PDF");
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileFilter(filter);
//        int result = fileChooser.showSaveDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
//        }
//    }
    }
}
