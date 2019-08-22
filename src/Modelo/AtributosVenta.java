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
public class AtributosVenta {

    public String codigo;
    public String nombre;
    public int precio_unidad;
    private int cant;
    private int total;

    public AtributosVenta(String codigo, String nombre, int precio_unidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_unidad = precio_unidad;
        this.cant = 0;
        this.total = 0;
    }

    public AtributosVenta(String codigo, String nombre, int precio_unidad, int cant, int total) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_unidad = precio_unidad;
        this.cant = cant;
        this.total = total;
    }

    public AtributosVenta() {
        this.codigo = "";
        this.nombre = "";
        this.precio_unidad = 0;
        this.cant = 0;
        this.total = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio_unidad() {
        return precio_unidad;
    }

    public int getCant() {
        return cant;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

}
