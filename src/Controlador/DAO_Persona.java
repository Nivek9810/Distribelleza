/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Persona;
import Modelo.TimestampCertificates;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
class DAO_Persona {

    private ArrayList<DTO_Persona> lista_Personas;

    private DTO_Persona objPersona;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;
    private TimestampCertificates timestampCertificates;

    public DAO_Persona(ArrayList<DTO_Persona> lista_Personas, DTO_Persona objPersona, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Personas = lista_Personas;
        this.objPersona = objPersona;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
        this.timestampCertificates = new TimestampCertificates();
    }

    public DAO_Persona() throws SQLException {
        this.lista_Personas = new ArrayList<>();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.objPersona = new DTO_Persona();
        this.statement = conection.createStatement();
        this.timestampCertificates = new TimestampCertificates();
    }

    public DTO_Persona getSinglePersona(String id_persona) throws SQLException {

        String sql = "SELECT * FROM PERSONA WHERE DNI = '" + id_persona + "';";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            //String DNI, String Nombre, String Telefono, String Direccion, Timestamp Fecha, Timestamp Fecha_Nacimiento
            this.objPersona
                    = new DTO_Persona(resultSet.getString("DNI"),
                            resultSet.getString("Nombre"),
                            resultSet.getString("Telefono"),
                            resultSet.getString("Direccion"),
                            new Timestamp(new Date(resultSet.getTimestamp("Fecha").getTime()), this.timestampCertificates.getCertPath()),
                            new Timestamp(new Date(resultSet.getTimestamp("Fecha_Nacimiento").getTime()), this.timestampCertificates.getCertPath()));

        }

        return this.objPersona;
    }

}
