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
public class DTO_Producto {

    private String Id_Producto;
    private DTO_Marca Marca;
    private DTO_Categoria Categoria;
    private String Nombre;
    private double Precio_Compra;
    private int Cantidad;
    private double Porcentaje_Venta;
    private Timestamp Fecha_de_Carga;

    public DTO_Producto() {
        this.Id_Producto = "";
        this.Marca = new DTO_Marca();
        this.Categoria = new DTO_Categoria();
        this.Nombre = "";
        this.Precio_Compra = 0;
        this.Cantidad = 0;
        this.Porcentaje_Venta = 0;
        this.Fecha_de_Carga = null;
    }

    public DTO_Producto(String Id_Producto, DTO_Marca Marca, DTO_Categoria Categoria, String Nombre, double Precio_Compra, int Cantidad, double Porcentaje_Venta, Timestamp Fecha_de_Carga) {
        this.Id_Producto = Id_Producto;
        this.Marca = Marca;
        this.Categoria = Categoria;
        this.Nombre = Nombre;
        this.Precio_Compra = Precio_Compra;
        this.Cantidad = Cantidad;
        this.Porcentaje_Venta = Porcentaje_Venta;
        this.Fecha_de_Carga = Fecha_de_Carga;
    }

    public String getId_Producto() {
        return Id_Producto;
    }

    public DTO_Marca getMarca() {
        return Marca;
    }

    public DTO_Categoria getCategoria() {
        return Categoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public double getPrecio_Compra() {
        return Precio_Compra;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public double getPorcentaje_Venta() {
        return Porcentaje_Venta;
    }

    public Timestamp getFecha_de_Carga() {
        return Fecha_de_Carga;
    }

    
}
