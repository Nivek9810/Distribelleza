/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Categoria {

    private ArrayList<DTO_Categoria> lista_Categorias;

    private DTO_Categoria objCategoria;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Categoria(ArrayList<DTO_Categoria> lista_Categorias, DTO_Categoria objCategoria, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Categorias = lista_Categorias;
        this.objCategoria = objCategoria;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Categoria() throws SQLException {
        this.lista_Categorias = new ArrayList<>();
        this.objCategoria = new DTO_Categoria();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
    }

    public ArrayList<DTO_Categoria> getAllCategories() throws SQLException {
        this.lista_Categorias.clear();
        String consulta = "SELECT * FROM CATEGORIA;";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.lista_Categorias.add(new DTO_Categoria(resultSet.getInt("Id_Categoria"),
                    resultSet.getString("Nombre")));
        }
        return this.lista_Categorias;
    }

    public DTO_Categoria getSingleCategory(int id_categoria) throws SQLException {
        this.objCategoria = null;

        String consulta = "SELECT * FROM CATEGORIA WHERE Id_Categoria=" + id_categoria + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objCategoria = new DTO_Categoria(
                    resultSet.getInt("Id_Categoria"),
                    resultSet.getString("Nombre")
            );
        }
        return this.objCategoria;
    }
}
