/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Categoria;
import Modelo.DTO_Marca;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Categoria_Marca {

    private ArrayList<DTO_Marca> lista_Marcas;

    private DAO_Marca objDataMarca;
    private DAO_Categoria objDataCategoria;

    private DTO_Categoria objCategoria;
    private DTO_Marca objMarca;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Categoria_Marca(ArrayList<DTO_Marca> lista_Marcas, DAO_Marca objDataMarca, DAO_Categoria objDataCategoria, DTO_Categoria objCategoria, DTO_Marca objMarca, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Marcas = lista_Marcas;
        this.objDataMarca = objDataMarca;
        this.objDataCategoria = objDataCategoria;
        this.objCategoria = objCategoria;
        this.objMarca = objMarca;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Categoria_Marca() throws SQLException {

        this.objDataMarca = new DAO_Marca();
        this.objDataCategoria = new DAO_Categoria();

        this.objCategoria = new DTO_Categoria();
        this.objMarca = new DTO_Marca();
        this.lista_Marcas = new ArrayList<>();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
    }

    public boolean addNewCategoria_Marca(DTO_Categoria objCategoria, DTO_Marca objMarca) throws SQLException {
        String insert = "INSERT INTO CATEGORIA_MARCA VALUES ("
                + objCategoria.getId_Categoria() + ", "
                + objMarca.getId_Marca() + ");";
        return this.statement.executeUpdate(insert) > 0;
    }

}
