/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author MARCELO RUENES
 */
public class DTO_Factura_Producto {
    private String Id_Producto;
    private String Id_Factura;
    private int cantidad;
    
    public DTO_Factura_Producto() {
        this.Id_Producto = "";
        this.Id_Factura = "";
        this.cantidad = 0;
    }

    public DTO_Factura_Producto(String Id_Producto, String Id_Factura, int cantidad) {
        this.Id_Producto = Id_Producto;
        this.Id_Factura = Id_Factura;
        this.cantidad = cantidad;
    }

    public String getId_Producto() {
        return Id_Producto;
    }

    public String getId_Factura() {
        return Id_Factura;
    }

    public int getCantidad() {
        return cantidad;
    }

   
    
}
