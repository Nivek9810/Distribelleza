/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Cierre;
import Modelo.DTO_Factura;
import Modelo.DTO_V_Total_Factura;
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
public class DAO_Cierre {

    private ArrayList<DTO_V_Total_Factura> listaFacturas;
    private ArrayList<DTO_Cierre> listaProductos;

    private DTO_V_Total_Factura objFactura;
    private DTO_Cierre objProducto_mas_vendido;

    private DAO_Persona objDataPersona;
    private DAO_Producto objDataProducto;

    private TimestampCertificates tsc;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Cierre(ArrayList<DTO_V_Total_Factura> listaFacturas, ArrayList<DTO_Cierre> listaProductos, DTO_V_Total_Factura objFactura, DTO_Cierre objProducto_mas_vendido, DAO_Persona objDataPersona, DAO_Producto objDataProducto, TimestampCertificates tsc, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.listaFacturas = listaFacturas;
        this.listaProductos = listaProductos;
        this.objFactura = objFactura;
        this.objProducto_mas_vendido = objProducto_mas_vendido;
        this.objDataPersona = objDataPersona;
        this.objDataProducto = objDataProducto;
        this.tsc = tsc;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Cierre() throws SQLException {
        this.listaFacturas = new ArrayList<>();
        this.listaProductos = new ArrayList<>();

        this.objFactura = new DTO_V_Total_Factura();
        this.objProducto_mas_vendido = new DTO_Cierre();
        this.tsc = new TimestampCertificates();

        this.statement = null;
        this.resultSet = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
        this.objDataPersona = new DAO_Persona();
        this.objDataProducto = new DAO_Producto();
    }

    public ArrayList<DTO_V_Total_Factura> getAllFacturas(Date fecha_inicial, Date fecha_final) throws SQLException {
        this.listaFacturas.clear();
        String query = "SELECT VTF.* "
                + "FROM V_TOTAL_FACTURA  AS VTF "
                + "WHERE VTF.FECHA BETWEEN '" + fecha_inicial + "' AND '" + fecha_final + "' "
                + "AND "
                + "VTF.GRAN_TOTAL IS NOT NULL;";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.listaFacturas.add(new DTO_V_Total_Factura(
                    new DTO_Factura(this.resultSet.getString("Id_Factura"),
                            this.objDataPersona.getSinglePersona(this.resultSet.getString("Id_Persona"), true),
                            new Timestamp(new Date(this.resultSet.getTimestamp("Fecha").getTime()), this.tsc.getCertPath()),
                            this.resultSet.getString("Correo"),
                            this.resultSet.getDouble("Gran_Total")),
                    this.resultSet.getDouble("Total")));
        }
        return this.listaFacturas;
    }

    public ArrayList<DTO_Cierre> getAllSoldProductsByDate(Date fecha_inicial, Date fecha_final, int cant) throws SQLException {
        this.listaProductos.clear();
        String query = "SELECT FP.Id_Producto, SUM(FP.cant_productos) AS CANT_PROD_VEND "
                + "FROM FACTURA_PRODUCTO AS FP "
                + "INNER JOIN V_TOTAL_FACTURA  AS VTF ON VTF.ID_FACTURA = FP.ID_FACTURA "
                + "WHERE VTF.FECHA BETWEEN '" + fecha_inicial + "' AND '" + fecha_final + "' "
                + "GROUP BY(1) "
                + "ORDER BY (CANT_PROD_VEND) DESC "
                + "LIMIT " + cant + ";";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.listaProductos.add(new DTO_Cierre(fecha_final,
                    this.objDataProducto.getSingleProducto(this.resultSet.getString("Id_Producto"), true),
                    this.resultSet.getInt("Cant_Prod_Vend")));
        }
        return this.listaProductos;
    }

    public ArrayList<DTO_Cierre> getAllSoldProducts(int cant) throws SQLException {
        this.listaProductos.clear();
        String query = "SELECT FP.Id_Producto, SUM(FP.cant_productos) AS CANT_PROD_VEND "
                + "FROM FACTURA_PRODUCTO AS FP "
                + "GROUP BY(1) "
                + "ORDER BY (CANT_PROD_VEND) DESC "
                + "LIMIT " + cant + ";";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.listaProductos.add(new DTO_Cierre(new Date(),
                    this.objDataProducto.getSingleProducto(this.resultSet.getString("Id_Producto"), true),
                    this.resultSet.getInt("Cant_Prod_Vend")));
        }
        return this.listaProductos;
    }

}
