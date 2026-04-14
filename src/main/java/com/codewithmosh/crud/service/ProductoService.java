package com.codewithmosh.crud.service;

import com.codewithmosh.crud.pojo.Producto;
import com.codewithmosh.crud.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<Producto> findAll(){
        return ProductoRepository.getInstancia().findAll();
    }

    public void save(Producto producto) {
        if(producto == null){
            IO.println("Producto es nulo.");
            return;
        }
        ProductoRepository.getInstancia().save(producto);
    }

    public void update(Long id, Producto productoActualizado){
        ProductoRepository.getInstancia().update(id, productoActualizado);
    }

    public void deleteById(Long id){
        Producto p = ProductoRepository.getInstancia().findById(id)
                .orElse(null);
        if(p == null){
            IO.println("Producto no existe.");
            return;
        }
        ProductoRepository.getInstancia().deleteById(id);
    }

    public Producto findById(Long id){
        Producto p = ProductoRepository.getInstancia().findById(id)
                .orElse(null);
        if(p == null){
            IO.println("Producto no existe.");
            return p;
        }
        return p;
    }
}
