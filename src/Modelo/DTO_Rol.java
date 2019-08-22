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
public class DTO_Rol {

    private int Id_Rol;
    private String Nombre_Rol;
    private boolean Activo;

    public DTO_Rol(int Id_Rol, String Nombre_Rol) {
        this.Id_Rol = Id_Rol;
        this.Nombre_Rol = Nombre_Rol;
    }

    public DTO_Rol(int Id_Rol, String Nombre_Rol, boolean Activo) {
        this.Id_Rol = Id_Rol;
        this.Nombre_Rol = Nombre_Rol;
        this.Activo = Activo;
    }

    public DTO_Rol() {
        this.Id_Rol = 0;
        this.Nombre_Rol = "";
    }

    public int getId_Rol() {
        return Id_Rol;
    }

    public String getNombre_Rol() {
        return Nombre_Rol;
    }

    public boolean isActivo() {
        return Activo;
    }

}
