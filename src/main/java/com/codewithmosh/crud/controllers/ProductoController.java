package com.codewithmosh.crud.controllers;

import com.codewithmosh.crud.enums.CategoriaProducto;
import com.codewithmosh.crud.enums.UnidadMedida;
import com.codewithmosh.crud.pojo.Producto;
import com.codewithmosh.crud.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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
    public ResponseEntity<Producto> create(@RequestBody @Valid Long id_producto,
                                           @RequestBody @Valid String nombre,
                                           @RequestBody @Valid double precioUnitario,
                                           @RequestBody @Valid CategoriaProducto categoria,
                                           @RequestBody @Valid UnidadMedida unidadMedida) {
        Producto neoProducto = new Producto(id_producto,nombre,precioUnitario,categoria,unidadMedida);
        productoService.save(neoProducto);
        URI location = URI.create("/api/productos/" + id_producto);
        return ResponseEntity.created(location).body(neoProducto);
    }

    //editar completo
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Long id,
            @RequestBody @Valid Long id_producto,
            @RequestBody @Valid String nombre,
            @RequestBody @Valid double precioUnitario,
            @RequestBody @Valid CategoriaProducto categoria,
            @RequestBody @Valid UnidadMedida unidadMedida) {
        Producto productoAct = new Producto(id_producto,nombre,precioUnitario,categoria,unidadMedida);
        productoService.update(id, productoAct);
        return ResponseEntity.ok(productoAct);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
