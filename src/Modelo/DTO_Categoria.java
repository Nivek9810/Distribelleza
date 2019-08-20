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
public class DTO_Categoria {

    private int Id_Categoria;
    private String Nombre;
    private boolean Activo;

    public DTO_Categoria(int Id_Categoria, String Nombre) {
        this.Id_Categoria = Id_Categoria;
        this.Nombre = Nombre;
    }
    
    public DTO_Categoria(int Id_Categoria, String Nombre, boolean Activo) {
        this.Id_Categoria = Id_Categoria;
        this.Nombre = Nombre;
        this.Activo = Activo;
    }

    public DTO_Categoria() {
        this.Id_Categoria = 0;
        this.Nombre = "";
    }

    public int getId_Categoria() {
        return Id_Categoria;
    }

    public String getNombre() {
        return Nombre;
    }
    
    public boolean isActivo(){
        return Activo;
    }
}
