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
public class DTO_Factura {

    private String Id_Factura;
    private DTO_Persona Persona;
    private Timestamp Fecha;
    private String Correo;
    private double Gran_Total;

    public DTO_Factura(String Id_Factura, DTO_Persona Persona, Timestamp Fecha, String Correo, double Gran_Total) {
        this.Id_Factura = Id_Factura;
        this.Persona = Persona;
        this.Fecha = Fecha;
        this.Correo = Correo;
        this.Gran_Total = Gran_Total;
    }

    public DTO_Factura() {
        this.Id_Factura = "";
        this.Persona = new DTO_Persona();
        this.Fecha = null;
        this.Correo = "";
        this.Gran_Total = 0;
    }

    public String getId_Factura() {
        return Id_Factura;
    }

    public DTO_Persona getPersona() {
        return Persona;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public String getCorreo() {
        return Correo;
    }

    public double getGran_Total() {
        return Gran_Total;
    }

}
