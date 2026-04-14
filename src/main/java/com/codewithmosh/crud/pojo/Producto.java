package com.codewithmosh.crud.pojo;

import com.codewithmosh.crud.enums.CategoriaProducto;
import com.codewithmosh.crud.enums.UnidadMedida;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
    private int id_producto;
    private String nombre;
    private double precioUnitario;
    private CategoriaProducto categoria;
    private UnidadMedida unidadMedida;

    public Producto (int id, String nombre, double precioUnitario, CategoriaProducto categoria, UnidadMedida unidadMedida) {
        this.id_producto = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.categoria = categoria;
        this.unidadMedida = unidadMedida;
    }
}
