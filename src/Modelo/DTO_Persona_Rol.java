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
public class DTO_Persona_Rol {

    private DTO_Persona persona;
    private DTO_Rol rol;
    private String contrasena;
    private Timestamp createat;
    private Timestamp lastlog;

    public DTO_Persona_Rol(DTO_Persona persona, DTO_Rol rol, String contrasena, Timestamp createat, Timestamp lastlog) {
        this.persona = persona;
        this.rol = rol;
        this.contrasena = contrasena;
        this.createat = createat;
        this.lastlog = lastlog;
    }

    public DTO_Persona_Rol(DTO_Persona persona, DTO_Rol rol, String contrasena) {
        this.persona = persona;
        this.rol = rol;
        this.contrasena = contrasena;
        this.createat = null;
        this.lastlog = null;
    }

    public DTO_Persona_Rol() {
        this.persona = new DTO_Persona();
        this.rol = new DTO_Rol();
        this.contrasena = "";
        this.createat = null;
        this.lastlog = null;
    }

    public DTO_Persona getPersona() {
        return persona;
    }

    public DTO_Rol getRol() {
        return rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Timestamp getCreateat() {
        return createat;
    }

    public Timestamp getLastlog() {
        return lastlog;
    }

    public void setCreateat(Timestamp createat) {
        this.createat = createat;
    }

    public void setRol(DTO_Rol rol) {
        this.rol = rol;
    }

}
