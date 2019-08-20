/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Persona;
import Modelo.DTO_Rol;
import Modelo.TimestampCertificates;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class DAO_Persona {

    private ArrayList<DTO_Persona> lista_Personas;

    private DTO_Persona objPersona;
    private DTO_Rol objRol;
    private DAO_Rol objDataRol;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;
    private TimestampCertificates timestampCertificates;

    public DAO_Persona(ArrayList<DTO_Persona> lista_Personas, DTO_Persona objPersona, DTO_Rol ObjRol, DAO_Rol objDataRol, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Personas = lista_Personas;
        this.objPersona = objPersona;
        this.objRol = new DTO_Rol();
        this.objDataRol = objDataRol;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
        this.timestampCertificates = new TimestampCertificates();
    }

    public DAO_Persona() throws SQLException {
        this.lista_Personas = new ArrayList<>();
        this.objRol = new DTO_Rol();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.objPersona = new DTO_Persona();
        this.statement = conection.createStatement();
        this.timestampCertificates = new TimestampCertificates();
    }

    public boolean registrarNuevaPersona(DTO_Persona objPersona) throws SQLException {

        String insertNewPersona = "INSERT INTO "
                + "PERSONA "
                + "VALUES ('" + objPersona.getDNI() + "','"
                + objPersona.getNombre() + "','"
                + objPersona.getTelefono() + "','"
                + objPersona.getFecha().getTimestamp() + "','"
                + objPersona.getDireccion() + "','"
                + objPersona.getFecha_Nacimiento() + "',"
                + objPersona.isActivo() + ");";
        int res = statement.executeUpdate(insertNewPersona);
        return (res > 0);
    }

    public boolean modificarPersona(DTO_Persona objPersona) throws SQLException {
        String updatePersona = "UPDATE PERSONA SET "
                + "Nombre = '" + objPersona.getNombre() + "', "
                + "Telefono = '" + objPersona.getTelefono() + "', "
                + "Fecha = '" + objPersona.getFecha().getTimestamp() + "' "
                + "Direccion = '" + objPersona.getDireccion() + "', "
                + "Fecha_Nacimiento = '" + objPersona.getFecha_Nacimiento() + "' "
                + "WHERE DNI = '" + objPersona.getDNI() + "';";
        int cantResults = statement.executeUpdate(updatePersona);
        return (cantResults > 0);
    }

    public DTO_Persona getSinglePersona(String id_persona, boolean state) throws SQLException {
        this.objPersona = null;
        String sql = "SELECT * FROM PERSONA WHERE DNI = '" + id_persona + "' AND Activo = " + state + ";";
        this.resultSet = this.statement.executeQuery(sql);
        while (resultSet.next()) {
            //String DNI, String Nombre, String Telefono, String Direccion, Timestamp Fecha, Timestamp Fecha_Nacimiento
            this.objPersona = new DTO_Persona(
                    this.resultSet.getString("DNI"),
                    this.resultSet.getString("Nombre"),
                    this.resultSet.getString("Telefono"),
                    this.resultSet.getString("Direccion"), new Timestamp(new Date(this.resultSet.getTimestamp("Fecha").getTime()), this.timestampCertificates.getCertPath()),
                    this.resultSet.getDate("Fecha_Nacimiento"));
        }

        return this.objPersona;
    }

}
