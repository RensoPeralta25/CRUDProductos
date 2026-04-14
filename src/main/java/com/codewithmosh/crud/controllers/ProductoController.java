package com.codewithmosh.crud.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //listar todos
    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    //detalles de productos especficio
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    //crear
    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody @Valid Producto producto) {
        Producto nuevo = productoService.save(producto);
        URI location = URI.create("/api/productos/" + nuevo.getId());
        return ResponseEntity.created(location).body(nuevo);
    }

    //editar completo
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Long id,
            @RequestBody @Valid Producto producto) {
        return ResponseEntity.ok(productoService.update(id, producto));
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
