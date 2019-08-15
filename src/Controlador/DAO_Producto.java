/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Categoria;
import Modelo.DTO_Marca;
import Modelo.DTO_Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Producto {

    //CRUD Producto
    private ArrayList<DTO_Producto> lista_Productos;

    private DTO_Categoria objCategoria;
    private DTO_Marca objMarca;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Producto(ArrayList<DTO_Producto> lista_Productos, DTO_Categoria objCategoria, DTO_Marca objMarca, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Productos = lista_Productos;
        this.objCategoria = objCategoria;
        this.objMarca = objMarca;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Producto() throws SQLException {
        this.lista_Productos = new ArrayList<>();
        this.objCategoria = new DTO_Categoria();
        this.objMarca = new DTO_Marca();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
    }

    public boolean registrarNuevoProducto(DTO_Producto objProducto) throws SQLException {
        boolean r = false;
        /*
        INSERT INTO PRODUCTO 
VALUES ('7703252001131',1,1,'SHAMPOO TEST', 5000, 45,0.05, '06/08/2019 14:34:04')
         */
        String insertRH = "INSERT INTO "
                + "PRODUCTO "
                + "VALUES ('" + objProducto.getId_Producto() + "',"
                + objProducto.getMarca().getId_Marca() + ","
                + objProducto.getCategoria().getId_Categoria() + ", '"
                + objProducto.getNombre() + "',"
                + objProducto.getPrecio_Compra() + ","
                + objProducto.getCantidad() + ","
                + objProducto.getPorcentaje_Venta() + ",'"
                + objProducto.getFecha_de_Carga().getTimestamp() + "');";
        int res = statement.executeUpdate(insertRH);

        return (res > 0);
    }
}
