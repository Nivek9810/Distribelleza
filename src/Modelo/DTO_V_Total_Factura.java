/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author user
 */
public class DTO_V_Total_Factura {

    private DTO_Factura objFactura;
    private double total;

    public DTO_V_Total_Factura(DTO_Factura objFactura, double total) {
        this.objFactura = objFactura;
        this.total = total;
    }

    public DTO_V_Total_Factura() {
        this.objFactura = new DTO_Factura();
        this.total = 0;
    }

    public DTO_Factura getObjFactura() {
        return objFactura;
    }

    public double getTotal() {
        return total;
    }

}
