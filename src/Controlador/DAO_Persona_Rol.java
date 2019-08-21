/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Persona;
import Modelo.DTO_Persona_Rol;
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
public class DAO_Persona_Rol {

    private TimestampCertificates timestampCertificates;

    private ArrayList<DTO_Persona> lista_Personas;
    private ArrayList<DTO_Rol> lista_Roles;

    private DAO_Persona objDataPersona;
    private DAO_Rol objDataRol;

    private DTO_Persona_Rol objPersona_Rol;

    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Persona_Rol(ArrayList<DTO_Persona> lista_Personas, ArrayList<DTO_Rol> lista_Roles, DAO_Persona objDataPersona, DAO_Rol objDataRol, DTO_Persona_Rol objPersona_Rol, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Personas = lista_Personas;
        this.lista_Roles = lista_Roles;
        this.objDataPersona = objDataPersona;
        this.objDataRol = objDataRol;
        this.objPersona_Rol = objPersona_Rol;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Persona_Rol() throws SQLException {
        this.objDataPersona = new DAO_Persona();
        this.objDataRol = new DAO_Rol();
        this.objPersona_Rol = new DTO_Persona_Rol();
        this.lista_Personas = new ArrayList<>();
        this.lista_Roles = new ArrayList<>();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();

        this.statement = conection.createStatement();
        this.timestampCertificates = new TimestampCertificates();
    }

    public DTO_Persona_Rol getSessionPersona(String DNI, String Password) throws SQLException {
        Statement logSmt = this.conection.createStatement();

        this.objPersona_Rol = null;
        String query = "SELECT * FROM PERSONA_ROL WHERE DNI = '"
                + DNI + "' AND Contrasena = '"
                + Password + "';";

        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.objPersona_Rol = new DTO_Persona_Rol(
                    this.objDataPersona.getSinglePersona(DNI, true),
                    this.objDataRol.getSingleRol(this.resultSet.getInt("Id_Rol"), true),
                    this.resultSet.getString("Contrasena") //Hacer lo de MB5
            );
            if (this.resultSet.getTimestamp("CreateAt") != null) {
                this.objPersona_Rol.setCreateat(new Timestamp(new Date(this.resultSet.getTimestamp("CreateAt").getTime()),
                        this.timestampCertificates.getCertPath()));
            }
        }
        return this.objPersona_Rol;
    }

    public boolean setNewSession(DTO_Persona_Rol objPersonaRol) throws SQLException {
        Date currentDate = new Timestamp(new Date(), this.timestampCertificates.getCertPath()).getTimestamp();
        String update = (objPersonaRol.getCreateat() != null)
                ? "UPDATE PERSONA_ROL SET LastLog = '"
                + currentDate
                + "' WHERE DNI = '" + objPersonaRol.getPersona().getDNI() + "'"
                + " AND Id_Rol = " + objPersonaRol.getRol().getId_Rol() + ";"
                : "UPDATE PERSONA_ROL SET LastLog = '"
                + currentDate + "', CreateAt = '"
                + currentDate + "'"
                + " WHERE DNI = '" + objPersonaRol.getPersona().getDNI() + "'"
                + " AND Id_Rol = " + objPersonaRol.getRol().getId_Rol() + ";";

        return this.statement.executeUpdate(update) > 0;

    }

    public ArrayList<DTO_Rol> getListOfRols(String DNI) throws SQLException {
        this.lista_Roles.clear();
        String query = "SELECT PR.Id_Rol FROM PERSONA_ROL AS PR "
                + "WHERE DNI = '" + DNI + "';";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.lista_Roles.add(this.objDataRol.getSingleRol(this.resultSet.getInt("Id_Rol"), true));
        }
        return this.lista_Roles;
    }
}
