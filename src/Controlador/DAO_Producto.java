/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AtributosVenta;
import Modelo.DTO_Categoria;
import Modelo.DTO_Marca;
import Modelo.DTO_Producto;
import Tools.TimestampCertificates;
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
public class DAO_Producto {

    //CRUD Producto
    private ArrayList<DTO_Producto> lista_Productos;
    private ArrayList<AtributosVenta> datos_productos;

    private DTO_Producto objProducto;
    private AtributosVenta objVenta;
    private DTO_Categoria objCategoria;
    private DAO_Categoria objDataCategoria;
    private DTO_Marca objMarca;
    private DAO_Marca objDataMarca;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;
    private TimestampCertificates tc;

    public DAO_Producto(ArrayList<DTO_Producto> lista_Productos, ArrayList<AtributosVenta> datos_productos, DTO_Producto objProducto, AtributosVenta objVenta, DTO_Categoria objCategoria, DAO_Categoria objDataCategoria, DTO_Marca objMarca, DAO_Marca objDataMarca, Statement statement, Conexion con, ResultSet resultSet, Connection conection, TimestampCertificates tc) {
        this.lista_Productos = lista_Productos;
        this.datos_productos = datos_productos;
        this.objProducto = objProducto;
        this.objVenta = objVenta;
        this.objCategoria = objCategoria;
        this.objDataCategoria = objDataCategoria;
        this.objMarca = objMarca;
        this.objDataMarca = objDataMarca;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
        this.tc = tc;
    }

    public DAO_Producto() throws SQLException {
        this.lista_Productos = new ArrayList<>();
        this.objVenta = new AtributosVenta();
        this.objProducto = new DTO_Producto();
        this.objCategoria = new DTO_Categoria();
        this.objMarca = new DTO_Marca();
        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
        this.objDataCategoria = new DAO_Categoria();
        this.objDataMarca = new DAO_Marca();
        this.tc = new TimestampCertificates();
    }

    public boolean registrarNuevoProducto(DTO_Producto objProducto) throws SQLException {
        String insertNewProduct = "INSERT INTO "
                + "PRODUCTO "
                + "VALUES ('" + objProducto.getId_Producto() + "',"
                + objProducto.getMarca().getId_Marca() + ","
                + objProducto.getCategoria().getId_Categoria() + ", '"
                + objProducto.getNombre() + "',"
                + objProducto.getPrecio_Compra() + ","
                + objProducto.getCantidad() + ","
                + objProducto.getPorcentaje_Venta() + ",'"
                + objProducto.getFecha_de_Carga().getTimestamp() + "',"
                + objProducto.isActivo() + ");";
        int res = statement.executeUpdate(insertNewProduct);
        return (res > 0);
    }

    public boolean modificarProducto(DTO_Producto objProducto) throws SQLException {
        String updateProduct = "UPDATE PRODUCTO SET "
                + "Id_Marca = " + objProducto.getMarca().getId_Marca() + ", "
                + "Id_Categoria = " + objProducto.getCategoria().getId_Categoria() + ", "
                + "Nombre = '" + objProducto.getNombre() + "', "
                + "Precio_Compra = " + objProducto.getPrecio_Compra() + ", "
                + "Cantidad = " + objProducto.getCantidad() + ", "
                + "Porcentaje_Venta = " + objProducto.getPorcentaje_Venta() + ", "
                + "Fecha_de_Carga = '" + objProducto.getFecha_de_Carga().getTimestamp() + "' "
                + "WHERE Id_Producto = '" + objProducto.getId_Producto() + "';";
        int cantResults = statement.executeUpdate(updateProduct);
        return (cantResults > 0);
    }

    public boolean modificarEstadoProducto(String id_producto, boolean state) throws SQLException {
        String updateProduct = "UPDATE PRODUCTO SET "
                + "Activo = " + state + " "
                + "WHERE Id_Producto = '" + id_producto + "';";
        int cantResults = statement.executeUpdate(updateProduct);
        return (cantResults > 0);
    }

    public ArrayList<DTO_Producto> getAllProducts(boolean state) throws SQLException {
        this.lista_Productos.clear();
        String query = "SELECT * FROM PRODUCTO WHERE Activo = " + state + ";";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            /*
            String Id_Producto, DTO_Marca Marca, DTO_Categoria Categoria, String Nombre, 
            double Precio_Compra, int Cantidad, double Porcentaje_Venta, Timestamp Fecha_de_Carga
             */
            this.lista_Productos.add(new DTO_Producto(
                    this.resultSet.getString("Id_Producto"),
                    this.objDataMarca.getSingleMarca(this.resultSet.getInt("Id_Marca"), true),//state
                    this.objDataCategoria.getSingleCategory(this.resultSet.getInt("Id_Categoria"), true),//state
                    this.resultSet.getString("Nombre"),
                    (double) this.resultSet.getInt("Precio_Compra"),
                    this.resultSet.getInt("Cantidad"),
                    this.resultSet.getDouble("Porcentaje_Venta"),
                    new Timestamp(new Date(this.resultSet.getTimestamp("Fecha_de_Carga").getTime()), this.tc.getCertPath()),
                    this.resultSet.getBoolean("Activo"))
            );
        }
        return this.lista_Productos;
    }

    public DTO_Producto getSingleProducto(String id, boolean state) throws SQLException {
        this.objProducto = null;
        String Consulta = "SELECT * FROM PRODUCTO WHERE ID_PRODUCTO = '" + id + "' AND Activo = " + state + ";";
        this.resultSet = this.statement.executeQuery(Consulta);
        while (resultSet.next()) {
            this.objProducto = new DTO_Producto(
                    this.resultSet.getString("id_producto"),
                    this.objDataMarca.getSingleMarca(this.resultSet.getInt("id_marca"), true),//state
                    this.objDataCategoria.getSingleCategory(this.resultSet.getInt("id_categoria"), true),//state
                    this.resultSet.getString("nombre"),
                    (double) this.resultSet.getInt("precio_compra"),
                    this.resultSet.getInt("cantidad"),
                    this.resultSet.getDouble("porcentaje_venta"),
                    new Timestamp(new Date(this.resultSet.getTimestamp("fecha_de_carga").getTime()), this.tc.getCertPath()),
                    this.resultSet.getBoolean("Activo"));

        }
        return this.objProducto;
    }

    public ArrayList<DTO_Producto> getProductosByQuery(String nombre, boolean state) throws SQLException {
        this.lista_Productos.clear();
        String query = "SELECT * FROM PRODUCTO ORDER BY id_producto asc "
                + "WHERE Nombre LIKE '%" + nombre + "%' AND Activo = " + state + ";";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.lista_Productos.add(new DTO_Producto(
                    this.resultSet.getString("Id_Producto"),
                    this.objDataMarca.getSingleMarca(this.resultSet.getInt("Id_Marca"), true),//state
                    this.objDataCategoria.getSingleCategory(this.resultSet.getInt("Id_Categoria"), true),//state
                    this.resultSet.getString("Nombre"),
                    (double) this.resultSet.getInt("Precio_Compra"),
                    this.resultSet.getInt("Cantidad"),
                    this.resultSet.getDouble("Porcentaje_Venta"),
                    new Timestamp(new Date(this.resultSet.getTimestamp("Fecha_de_Carga").getTime()), this.tc.getCertPath()),
                    this.resultSet.getBoolean("Activo"))
            );
        }
        return this.lista_Productos;
    }

    public AtributosVenta vistaVenta(String id_prod) throws SQLException {
        this.objVenta = null;
        String query = "SELECT id_producto,nombre,(precio_compra+(precio_compra*porcentaje_venta)) as Precio_Compra FROM PRODUCTO "
                + "WHERE id_producto = '" + id_prod + "';";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.objVenta = new AtributosVenta(
                    this.resultSet.getString("Id_Producto"),
                    this.resultSet.getString("Nombre"),
                    (int) this.resultSet.getInt("Precio_Compra"));
        }
        return this.objVenta;
    }

}
