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
public class DTO_Persona {
    
    
    private DTO_Rol Rol;
    private String DNI, Nombre, Telefono, Direccion, Fecha_Nacimiento;
    private Timestamp Fecha;
    
    
    
    public DTO_Persona(String DNI, String Nombre,DTO_Rol Rol, String Telefono, String Direccion, Timestamp Fecha, String Fecha_Nacimiento) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Rol=Rol;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Fecha = Fecha;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        
    }
    
    public DTO_Persona() {
        this.DNI = "";
        this.Nombre = "";
        this.Rol=new DTO_Rol();
        this.Telefono = "";
        this.Direccion = "";
        this.Fecha = null;
        this.Fecha_Nacimiento ="";
    }


    public DTO_Rol getRol() {
        return Rol;
    }

   
    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

}
