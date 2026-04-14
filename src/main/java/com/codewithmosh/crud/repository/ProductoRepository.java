package com.codewithmosh.crud.repository;

import com.codewithmosh.crud.pojo.Producto;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductoRepository {
    private final Map<Long, Producto> productos = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);
    private static ProductoRepository instancia;

    public static ProductoRepository getInstancia () {
        if (instancia == null) {
            instancia = new ProductoRepository();
        }
        return instancia;
    }

    private ProductoRepository () {

    }


    public List<Producto> findAll() {
        return new ArrayList<>(productos.values());
    }

    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    public Producto save (Producto producto) {
        if (producto.getId_producto() == null) {
            producto.setId_producto(sequence.incrementAndGet());
        }

        productos.put(producto.getId_producto(), producto);
        return producto;
    }

    public Optional<Producto> update(Long id, Producto productoActualizado) {
        Producto existente = productos.get(id);
        if (existente == null) {
            return Optional.empty();
        }
        existente.setNombre(productoActualizado.getNombre());
        existente.setPrecioUnitario(productoActualizado.getPrecioUnitario());
        existente.setCategoria(productoActualizado.getCategoria());
        existente.setUnidadMedida(productoActualizado.getUnidadMedida());
        productos.put(id, existente);
        return Optional.of(existente);
    }

    public void deleteById(Long id) {
        productos.remove(id);
    }
}
