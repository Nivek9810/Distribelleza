/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author user
 */
public class DTO_Marca {

    private int Id_Marca;
    private DTO_Persona Proveedor;
    private String Nombre;
    private boolean Activo;

    public DTO_Marca(int Id_Marca, DTO_Persona Proveedor, String Nombre) {
        this.Id_Marca = Id_Marca;
        this.Proveedor = Proveedor;
        this.Nombre = Nombre;
    }

    public DTO_Marca(int Id_Marca, DTO_Persona Proveedor, String Nombre, boolean Activo) {
        this.Id_Marca = Id_Marca;
        this.Proveedor = Proveedor;
        this.Nombre = Nombre;
        this.Activo = Activo;
    }

    public DTO_Marca(DTO_Persona Proveedor, String Nombre) {
        this.Proveedor = Proveedor;
        this.Nombre = Nombre;
    }

    public DTO_Marca() {
        this.Id_Marca = 0;
        this.Proveedor = new DTO_Persona();
        this.Nombre = "";
    }

    public int getId_Marca() {
        return Id_Marca;
    }

    public DTO_Persona getProveedor() {
        return Proveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setProveedor(DTO_Persona Proveedor) {
        this.Proveedor = Proveedor;
    }

}
