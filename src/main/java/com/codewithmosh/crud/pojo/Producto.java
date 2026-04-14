package com.codewithmosh.crud.pojo;

import com.codewithmosh.crud.enums.CategoriaProducto;
import com.codewithmosh.crud.enums.UnidadMedida;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {
    private Long id_producto;
    private String nombre;
    private double precioUnitario;
    private CategoriaProducto categoria;
    private int cantidad;
    private UnidadMedida unidadMedida;

    public Producto (Long id, String nombre, double precioUnitario, CategoriaProducto categoria, int cantidad, UnidadMedida unidadMedida) {
        this.id_producto = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }
}
