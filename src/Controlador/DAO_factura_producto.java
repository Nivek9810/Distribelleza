/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Factura;
import Modelo.DTO_Factura_Producto;
import Modelo.DTO_Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MARCELO RUENES
 */
public class DAO_factura_producto {
    private ArrayList<DTO_Producto> lista_producto;
    private ArrayList<DTO_Factura> lista_factura;
    private ArrayList<DTO_Factura_Producto> lista_factura_producto;
    private DTO_Factura obj_Factura;
    private DTO_Producto obj_producto;
    private DTO_Factura_Producto obj_fact_producto;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_factura_producto(ArrayList<DTO_Producto> lista_producto, ArrayList<DTO_Factura> lista_factura, ArrayList<DTO_Factura_Producto> lista_factura_producto, DTO_Factura obj_Factura, DTO_Producto obj_producto, DTO_Factura_Producto obj_fact_producto, Statement statement, Conexion con, ResultSet resultSet, Connection conection) {
        this.lista_producto = lista_producto;
        this.lista_factura = lista_factura;
        this.lista_factura_producto = lista_factura_producto;
        this.obj_Factura = obj_Factura;
        this.obj_producto = obj_producto;
        this.obj_fact_producto = obj_fact_producto;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
    }

   
     public DAO_factura_producto() throws SQLException {
         this.lista_factura = new ArrayList<>();
        this.lista_producto= new ArrayList<>();
        this.lista_factura_producto= new ArrayList<>();
        this.obj_producto = new DTO_Producto();
        this.obj_Factura = new DTO_Factura();
        this.obj_fact_producto= new DTO_Factura_Producto();
        this.statement = null;
        this.con = new Conexion();
        this.resultSet = null;
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
     }
    
      public boolean RegistrarFactProd(DTO_Factura_Producto obj_factura_prod) throws SQLException {
        String insertNewFact = "INSERT INTO"
                +" FACTURA_PRODUCTO"
                + " VALUES ('" + obj_factura_prod.getId_Producto()+ "', '"
                + obj_factura_prod.getId_Factura()+ "', "
                + obj_factura_prod.getCantidad()+ ");";
        int res = statement.executeUpdate(insertNewFact);
        return (res > 0);
    }
      
    
    
}
