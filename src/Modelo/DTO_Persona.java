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

    private String DNI, Nombre, Telefono, Direccion;
    private Timestamp Fecha, Fecha_Nacimiento;

    public DTO_Persona(String DNI, String Nombre, String Telefono, String Direccion, Timestamp Fecha, Timestamp Fecha_Nacimiento) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Fecha = Fecha;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public DTO_Persona() {
        this.DNI = "";
        this.Nombre = "";
        this.Telefono = "";
        this.Direccion = "";
        this.Fecha = null;
        this.Fecha_Nacimiento = null;
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

    public Timestamp getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

}
