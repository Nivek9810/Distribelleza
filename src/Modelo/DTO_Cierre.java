/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.security.Timestamp;
import java.util.Date;

/**
 *
 * @author user
 */
public class DTO_Cierre {

    private Date Fecha;
    private double Total_Venta;
    private DTO_Producto Producto_Mas_Vendido;
    private String Tipo;
    private int cant_prod_vend;

    public DTO_Cierre(Date Fecha, DTO_Producto Producto_Mas_Vendido, int cant_prod_vend) {
        this.Fecha = Fecha;
        this.Total_Venta = 0;
        this.Producto_Mas_Vendido = Producto_Mas_Vendido;
        this.Tipo = "";
        this.cant_prod_vend = cant_prod_vend;
    }

    public DTO_Cierre(Date Fecha, double Total_Venta, DTO_Producto Producto_Mas_Vendido, String Tipo) {
        this.Fecha = Fecha;
        this.Total_Venta = Total_Venta;
        this.Producto_Mas_Vendido = Producto_Mas_Vendido;
        this.Tipo = Tipo;
        this.cant_prod_vend = 0;
    }

    public DTO_Cierre() {
        this.Fecha = null;
        this.Total_Venta = 0;
        this.Producto_Mas_Vendido = new DTO_Producto();
        this.Tipo = "";
        this.cant_prod_vend = 0;
    }

    public Date getFecha() {
        return Fecha;
    }

    public double getTotal_Venta() {
        return Total_Venta;
    }

    public DTO_Producto getProducto_Mas_Vendido() {
        return Producto_Mas_Vendido;
    }

    public String getTipo() {
        return Tipo;
    }

    public int getCant_prod_vend() {
        return cant_prod_vend;
    }

}
