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
//librerias ajenas a itext
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MARCELO RUENES
 */
public class FacturaPdf {
    private File ruta_destino;
    private Excepciones objExc;

    public FacturaPdf() {
        ruta_destino = null;
        objExc = new Excepciones();
    }

    
     public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        return formato.format(fecha);
    }
    
    /* metodo que hace uso de la clase itext para manipular archivos PDF*/
public void crear_PDF() {
        //abre ventana de dialogo "guardar"
        Colocar_Destino();
        //si destino es diferente de null
        if (this.ruta_destino != null) {
            
            try {
            Document doc = new Document();
            doc.open();

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
            celdaB.addElement(new Paragraph("Fecha: "+fechaactual(), FontFactory.getFont("arial", 14, java.awt.Font.PLAIN)));
            TablaEncabezado.addCell(celdaB);
            doc.add(TablaEncabezado);
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
