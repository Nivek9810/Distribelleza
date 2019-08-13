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

    public void setId_Producto(String Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public void setMarca(DTO_Marca Marca) {
        this.Marca = Marca;
    }

    public void setCategoria(DTO_Categoria Categoria) {
        this.Categoria = Categoria;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setPrecio_Compra(double Precio_Compra) {
        this.Precio_Compra = Precio_Compra;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public void setPorcentaje_Venta(double Porcentaje_Venta) {
        this.Porcentaje_Venta = Porcentaje_Venta;
    }

    public void setFecha_de_Carga(Timestamp Fecha_de_Carga) {
        this.Fecha_de_Carga = Fecha_de_Carga;
    }
    
    
}
