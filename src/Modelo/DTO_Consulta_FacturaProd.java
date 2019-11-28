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
public class DTO_Consulta_FacturaProd {
    private String nombre_producto;
    private double precioProd;
    private int cantidad;
    private double precioXCant;
    
    public DTO_Consulta_FacturaProd() {
        this.nombre_producto="";
        this.precioProd=0;
        this.cantidad=0;
        this.precioXCant=0;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public double getPrecioProd() {
        return precioProd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioXCant() {
        return precioXCant;
    }

    public DTO_Consulta_FacturaProd(String nombre_producto, double precioProd, int cantidad, double precioXCant) {
        this.nombre_producto = nombre_producto;
        this.precioProd = precioProd;
        this.cantidad = cantidad;
        this.precioXCant = precioXCant;
    }
}
