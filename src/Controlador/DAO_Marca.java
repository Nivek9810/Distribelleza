/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Marca;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Marca {

    private ArrayList<DTO_Marca> lista_Marcas;

    private DAO_Persona objDataPersona;

    private DTO_Marca objMarca;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Marca(ArrayList<DTO_Marca> lista_Marcas, DTO_Marca objMarca, Statement statement, Conexion con, ResultSet resultSet, Connection conection) throws SQLException {
        this.lista_Marcas = lista_Marcas;
        this.objMarca = objMarca;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
        this.objDataPersona = new DAO_Persona();
    }

    public DAO_Marca() throws SQLException {
        this.objDataPersona = new DAO_Persona();
        this.lista_Marcas = new ArrayList<>();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.objMarca = new DTO_Marca();
        this.statement = conection.createStatement();
    }

    public ArrayList<DTO_Marca> getAllMarcas(boolean state) throws SQLException {
        this.lista_Marcas.clear();
        String consulta = "SELECT * FROM MARCA WHERE Activo = " + state + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.lista_Marcas.add(new DTO_Marca(
                    resultSet.getInt("Id_Marca"),
                    this.objDataPersona.getSinglePersona(resultSet.getString("Id_Proveedor"), true),
                    resultSet.getString("Nombre")));
        }
        return this.lista_Marcas;
    }

    public DTO_Marca getSingleMarca(int id_marca, boolean state) throws SQLException {
        this.objMarca = null;
        String consulta = "SELECT * FROM MARCA WHERE Id_Marca = " + id_marca + " AND Activo = " + state + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objMarca = new DTO_Marca(
                    resultSet.getInt("Id_Marca"),
                    this.objDataPersona.getSinglePersona(resultSet.getString("Id_Proveedor"), true),
                    resultSet.getString("Nombre"));
        }
        return this.objMarca;
    }

    public DTO_Marca addNewMarca(DTO_Marca objMarca) throws SQLException {
        String insert = "INSERT INTO MARCA (Id_Proveedor, Nombre, Activo) "
                + "VALUES ('" + objMarca.getProveedor().getDNI() + "', "
                + "'" + objMarca.getNombre() + "', "
                + true + ");";
        int res = this.statement.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
        if (res > 0) {
            this.resultSet = this.statement.getGeneratedKeys();
            while (this.resultSet.next()) {
                this.objMarca = new DTO_Marca(
                        this.resultSet.getInt("Id_Marca"),
                        this.objDataPersona.getSinglePersona(this.resultSet.getString("Id_Proveedor"), true),
                        this.resultSet.getString("Nombre"));
            }

        }

        return this.objMarca;
        //return (res > 0);
    }

    public boolean modificarMarca(DTO_Marca objMarca) throws SQLException {
        String update = "UPDATE MARCA SET "
                + " Id_Proveedor = '" + objMarca.getProveedor().getDNI() + "', "
                + " Nombre = '" + objMarca.getNombre() + "' "
                + " WHERE Id_Marca = " + objMarca.getId_Marca() + ";";
        int res = this.statement.executeUpdate(update);
        return (res > 0);
    }

    public boolean modificarEstadoMarca(int idMarca, boolean state) throws SQLException {
        String updateState = "UPDATE MARCA SET "
                + " Activo = " + state
                + " WHERE Id_Marca = " + idMarca + ";";
        int res = this.statement.executeUpdate(updateState);
        return (res > 0);
    }
}
