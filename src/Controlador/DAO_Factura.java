/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Factura;
import Modelo.DTO_Persona;
import Modelo.DTO_Persona_Rol;
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
 * @author MARCELO RUENES
 */
public class DAO_Factura {

    private ArrayList<DTO_Persona> lista_persona;
    private ArrayList<DTO_Factura> lista_venta;
    private DTO_Factura obj_Factura;
    private DTO_Persona obj_persona;
    private DAO_Persona objDatapersona;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;
    private TimestampCertificates tc;

    public DAO_Factura(ArrayList<DTO_Persona> lista_persona, DTO_Persona obj_persona, Statement statement, Conexion con, ResultSet resultSet, Connection conection, TimestampCertificates tc) {
        this.lista_persona = lista_persona;
        this.lista_venta = lista_venta;        
        this.obj_persona = obj_persona;
        this.objDatapersona = objDatapersona;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
        this.tc = tc;
    }

    public DAO_Factura() throws SQLException {
        this.lista_persona = new ArrayList<>();
        this.lista_venta = new ArrayList<>();
        this.obj_persona = new DTO_Persona();
        this.statement = null;
        this.con = new Conexion();
        this.resultSet = null;
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
        this.objDatapersona = new DAO_Persona();
        this.tc = new TimestampCertificates();
    }

    public boolean RegistrarVenta(DTO_Factura obj_factura) throws SQLException {
        String insertNewSale = "INSERT INTO"
                +" FACTURA "
                + "VALUES ('" + obj_factura.getId_Factura() + "', '"
                + obj_factura.getPersona().getDNI() + "', ' "
                + obj_factura.getFecha().getTimestamp() + "', '"
                + obj_factura.getCorreo() + "', "
                + obj_factura.getGran_Total()+ ");";
        int res = statement.executeUpdate(insertNewSale);
        return (res > 0);
    }
    
    public ArrayList<DTO_Factura> getFacturasByQuery(String id_fac) throws SQLException {
        this.lista_venta.clear();
        String query = "SELECT * FROM FACTURA "
                + "WHERE id_factura = '" + id_fac+ "';";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            this.lista_venta.add(new DTO_Factura(
                    this.resultSet.getString("Id_Factura"),
                    this.objDatapersona.getSinglePersona(this.resultSet.getString("Persona"), true),//state
                    new Timestamp(new Date(this.resultSet.getTimestamp("Fecha").getTime()), this.tc.getCertPath()),
                    this.resultSet.getString("Correo"),
                    (double) this.resultSet.getInt("Total_Factura"))
            );
        }
        return this.lista_venta;
    }
    
    public DTO_Factura getSingleFactura(String id) throws SQLException {
        this.obj_Factura = null;
        String Consulta = "SELECT * FROM FACTURA WHERE ID_PRODUCTO = '" + id + "';";
        this.resultSet = this.statement.executeQuery(Consulta);
        while (resultSet.next()) {
            this.obj_Factura = new DTO_Factura(this.resultSet.getString("Id_Factura"),
                    this.objDatapersona.getSinglePersona(this.resultSet.getString("dni"), true),//state
                    new Timestamp(new Date(this.resultSet.getTimestamp("Fecha").getTime()), this.tc.getCertPath()),
                    this.resultSet.getString("Correo"),
                    (double) this.resultSet.getInt("Precio_Compra"));

        }
        return this.obj_Factura;
    }
    
    public ArrayList<DTO_Factura> getAllSales() throws SQLException {
        this.lista_venta.clear();
        String query = "SELECT * FROM FACTURA;";
        this.resultSet = this.statement.executeQuery(query);
        while (this.resultSet.next()) {
            /*
            String Id_Producto, DTO_Marca Marca, DTO_Categoria Categoria, String Nombre, 
            double Precio_Compra, int Cantidad, double Porcentaje_Venta, Timestamp Fecha_de_Carga
             */
            this.lista_venta.add(new DTO_Factura(
                    this.resultSet.getString("Id_Factura"),
                    this.objDatapersona.getSinglePersona(this.resultSet.getString("id_persona"), true),//state
                    new Timestamp(new Date(this.resultSet.getTimestamp("Fecha").getTime()), this.tc.getCertPath()),
                    this.resultSet.getString("Correo"),
                    (double) this.resultSet.getInt("gran_total"))
            );
        }
        return this.lista_venta;
    }
}
