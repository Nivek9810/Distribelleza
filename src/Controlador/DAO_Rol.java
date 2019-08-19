/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Rol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MARCELO RUENES
 */
public class DAO_Rol {
    

    private ArrayList<DTO_Rol> lista_Rol;

    private DTO_Rol objRol;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Rol(ArrayList<DTO_Rol> lista_Rol, DTO_Rol objRol, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Rol = lista_Rol;
        this.objRol = objRol;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Rol() throws SQLException {
        this.lista_Rol = new ArrayList<>();
        this.objRol = new DTO_Rol();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
    }

    public ArrayList<DTO_Rol> getAllRoles() throws SQLException {
        this.lista_Rol.clear();
        String consulta = "SELECT * FROM ROL;";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.lista_Rol.add(new DTO_Rol(resultSet.getInt("Id_Rol"),
                    resultSet.getString("Nombre")));
        }
        return this.lista_Rol;
    }

    public DTO_Rol getSingleRol(int id_Rol) throws SQLException {
        this.objRol = null;

        String consulta = "SELECT * FROM CATEGORIA WHERE Id_Rol=" + id_Rol+ ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objRol = new DTO_Rol(
                    resultSet.getInt("Id_Rol"),
                    resultSet.getString("Nombre")
            );
        }
        return this.objRol;
    }
}
