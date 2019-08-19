/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.security.Timestamp;
import java.util.Date;

/**
 *
 * @author user
 */
public class DTO_Persona {

    private String DNI, Nombre, Telefono, Direccion;
    private Date Fecha_Nacimiento;
    private Timestamp Fecha;

    public DTO_Persona(String DNI, String Nombre, String Telefono, String Direccion, Timestamp Fecha, Date Fecha_Nacimiento) {
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
        this.Fecha_Nacimiento = new Date();
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

    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

}
