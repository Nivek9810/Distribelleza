/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Modelo.DTO_Cierre;
import Modelo.DTO_V_Total_Factura;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author user
 */
public class Chart {

    private DatasetDate objDatasetDate;
    private Calendar calendar;

    public Chart() {
    }

    public XYDataset createDataset(ArrayList<DTO_V_Total_Factura> listaVentas, Date date_I, Date date_F) {

        this.objDatasetDate = new DatasetDate(date_I, date_F);

        this.objDatasetDate.getTypeRange();
        //SimpleDateFormat dt = new SimpleDateFormat("uuuu-MM-dd HH:mm:ss");
        int ayer = this.objDatasetDate.getBefore();

        XYSeries series1 = new XYSeries("Actual");
        XYSeries series2 = new XYSeries("Anterior");
        XYSeries series3 = new XYSeries("Resto de tiempo");
        Calendar calendarBD = GregorianCalendar.getInstance();
        this.calendar = GregorianCalendar.getInstance();

        int conditional = this.objDatasetDate.getTypeConditional(), result = this.objDatasetDate.getTypeResult();

        calendar.setTime(date_F);
        listaVentas.forEach(ventas -> {
            calendarBD.setTime(ventas.getObjFactura().getFecha().getTimestamp());
            System.out.println("Calendar Result: " + calendarBD.get(result) + " - Condicional: " + conditional);
            if (calendarBD.get(conditional) == calendar.get(conditional)) {
                series1.add(calendarBD.get(result), ventas.getTotal());
            } else if (calendarBD.get(conditional) == ayer) {
                series2.add(calendarBD.get(result), ventas.getTotal());
            } else {
                series3.add(calendarBD.get(result), ventas.getTotal());
            }
        });

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;
    }

    public JPanel createChart(ArrayList<DTO_V_Total_Factura> listaVentas, Date date_I, Date date_F) {
        XYDataset dataset = this.createDataset(listaVentas, date_I, date_F);
        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Ventas del" + this.objDatasetDate.getTypeRange(),
                "Tiempo en unidades",
                "Dinero en pesos ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        /* chart.setTitle(new TextTitle("Productos más vendidos",
                new Font("Serif", Font.BOLD, 18)
        )
        );*/
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        return chartPanel;
    }

    public JPanel createChartPanel() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("One", new Integer(10));
        pieDataset.setValue("Two", new Integer(20));
        pieDataset.setValue("Three", new Integer(30));
        pieDataset.setValue("Four", new Integer(10));
        pieDataset.setValue("Five", new Integer(20));
        pieDataset.setValue("Six", new Integer(10));
        JFreeChart chart = ChartFactory.createPieChart3D("3D Pie Chart", pieDataset, true, true, true);
        chart.setTitle(new TextTitle("3D Pie Chart",
                new Font("Serif", java.awt.Font.BOLD, 18)
        )
        );
        try {
            ChartUtils.saveChartAsPNG(new File("line_chart.png"), chart, 450, 400);
        } catch (IOException ex) {
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ChartPanel(chart);
    }

    public JPanel createSecondChartPanel(ArrayList<DTO_Cierre> listaProductos, Date fecha_f) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        listaProductos.forEach(productos -> {
            dataset.setValue(productos.getCant_prod_vend(),
                    "Ganancias " + productos.getProducto_Mas_Vendido().getNombre() + ": " + (int) (productos.getProducto_Mas_Vendido().getPrecio_Compra() * productos.getProducto_Mas_Vendido().getPorcentaje_Venta()) * productos.getCant_prod_vend(),
                    productos.getProducto_Mas_Vendido().getNombre());
        });

        //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        //createStackedAreaChart
        JFreeChart chart = ChartFactory.createBarChart("Productos más vendidos del " + fecha_f.toString(),
                "Producto",
                "Cantidad de productos",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        return new ChartPanel(chart);
    }

}
