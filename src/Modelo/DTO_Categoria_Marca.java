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
public class DTO_Categoria_Marca {

    private DTO_Categoria categoria;
    private DTO_Marca marca;

    public DTO_Categoria_Marca(DTO_Categoria categoria, DTO_Marca marca) {
        this.categoria = categoria;
        this.marca = marca;
    }

    public DTO_Categoria_Marca() {
        this.categoria = new DTO_Categoria();
        this.marca = new DTO_Marca();
    }

    public DTO_Categoria getCategoria() {
        return categoria;
    }

    public DTO_Marca getMarca() {
        return marca;
    }
}
