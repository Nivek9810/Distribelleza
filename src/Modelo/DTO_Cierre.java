/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.security.Timestamp;

/**
 *
 * @author user
 */
public class DTO_Cierre {

    private Timestamp Fecha;
    private double Total_Venta;
    private DTO_Producto Producto_Mas_Vendido;
    private String Tipo;

    public DTO_Cierre(Timestamp Fecha, double Total_Venta, DTO_Producto Producto_Mas_Vendido, String Tipo) {
        this.Fecha = Fecha;
        this.Total_Venta = Total_Venta;
        this.Producto_Mas_Vendido = Producto_Mas_Vendido;
        this.Tipo = Tipo;
    }

    public DTO_Cierre() {
        this.Fecha = null;
        this.Total_Venta = 0;
        this.Producto_Mas_Vendido = new DTO_Producto();
        this.Tipo = "";
    }

    public Timestamp getFecha() {
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

}
