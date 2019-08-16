/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Categoria;
import Modelo.DTO_Marca;
import Modelo.DTO_Producto;
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
public class DAO_Producto {

    //CRUD Producto
    private ArrayList<DTO_Producto> lista_Productos;

    private DTO_Producto objProducto;
    private DTO_Categoria objCategoria;
    private DAO_Categoria objDataCategoria;
    private DTO_Marca objMarca;
    private DAO_Marca objDataMarca;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;
    private TimestampCertificates tc;

    public DAO_Producto(ArrayList<DTO_Producto> lista_Productos, DTO_Producto objProducto, DTO_Categoria objCategoria, DTO_Marca objMarca, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_Productos = lista_Productos;
        this.objProducto = objProducto;
        this.objCategoria = objCategoria;
        this.objMarca = objMarca;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Producto() throws SQLException {
        this.lista_Productos = new ArrayList<>();
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

    public DTO_Producto getSingleProducto(String id) throws SQLException {
        this.objProducto = null;
        String Consulta = "select * from PRODUCTO WHERE ID_PRODUCTO = '" + id + "';";
        this.resultSet = this.statement.executeQuery(Consulta);
        while (resultSet.next()) {
            this.objProducto = new DTO_Producto(
                    this.resultSet.getString("id_producto"),
                    this.objDataMarca.getSingleMarca(this.resultSet.getInt("id_marca")),
                    this.objDataCategoria.getSingleCategory(this.resultSet.getInt("id_categoria")),
                    this.resultSet.getString("nombre"),
                    (double)this.resultSet.getInt("precio_compra"),
                    this.resultSet.getInt("cantidad"),
                    this.resultSet.getDouble("porcentaje_venta"),
                    new Timestamp(new Date(this.resultSet.getTimestamp("fecha_de_carga").getTime()), this.tc.getCertPath()));

        }
        return this.objProducto;
    }
}
