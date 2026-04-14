package com.codewithmosh.crud.service;

import com.codewithmosh.crud.pojo.Producto;
import com.codewithmosh.crud.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto save(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("Producto es nulo.");
        }

        producto.setId_producto(null);
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto productoActualizado) {
        Producto existente = findById(id);

        existente.setNombre(productoActualizado.getNombre());
        existente.setPrecioUnitario(productoActualizado.getPrecioUnitario());
        existente.setCategoria(productoActualizado.getCategoria());
        existente.setCantidad(productoActualizado.getCantidad());
        existente.setUnidadMedida(productoActualizado.getUnidadMedida());

        return productoRepository.save(existente);
    }

    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NoSuchElementException("Producto no existe con id: " + id);
        }

        productoRepository.deleteById(id);
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no existe con id: " + id));
    }
}
