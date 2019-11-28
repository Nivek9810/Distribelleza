/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Consulta_FacturaProd;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.postgresql.util.PSQLException;

/**
 *
 * @author MARCELO RUENES
 */
public class DAO_Consulta_FacturaProd {

    private ArrayList<DTO_Consulta_FacturaProd> ConsultaFacturaProd;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Consulta_FacturaProd(ArrayList<DTO_Consulta_FacturaProd> ConsultaFacturaProd, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.ConsultaFacturaProd = ConsultaFacturaProd;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

    public DAO_Consulta_FacturaProd() throws SQLException {
        this.ConsultaFacturaProd = new ArrayList<>();
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
        this.resultSet = null;
    }

    public ArrayList<DTO_Consulta_FacturaProd> consultAll(String IdFactura) throws SQLException {
        this.ConsultaFacturaProd.clear();
        String query = "select distinct nombre,round(precio_compra + precio_compra*porcentaje_venta) AS Precio_v,cant_productos,\n"
                + "round((precio_compra + precio_compra*porcentaje_venta) * cant_productos) AS TOTAL from producto\n"
                + "inner join factura_producto\n"
                + "on producto.id_producto=factura_producto.id_producto\n"
                + "inner join factura \n"
                + "on factura_producto.id_factura='" + IdFactura + "';";
        this.resultSet = this.statement.executeQuery(query);

        while (this.resultSet.next()) {
            this.ConsultaFacturaProd.add(new DTO_Consulta_FacturaProd(
                    this.resultSet.getString("Nombre"),
                    (double)this.resultSet.getDouble("Precio_v"),
                    this.resultSet.getInt("cant_productos"),
                    (double)this.resultSet.getDouble("TOTAL")
            )
            );
        }

        return this.ConsultaFacturaProd;
    }
    
   
}
