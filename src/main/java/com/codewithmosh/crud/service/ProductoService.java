package com.codewithmosh.crud.service;

import com.codewithmosh.crud.pojo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    public ProductoService(){ }

    public List<Producto> findAll(){
        return ProductoRepository.getInstance().findAll();
    }

    public void save(Producto producto) throws Exception {
        if(producto == null){
            throw new Exception("Producto es nulo.");
            return;
        }
        ProductoRepository.getInstance().save(producto);
    }

    public void update(Long id, Producto productoActualizado){
        ProductoRepository.getInstance().update(id, productoActualizado);
    }

    public void deleteById(Long id) throws Exception {
        Producto p = ProductoRepository.getInstance().findById(id)
                .orElse(null);
        if(p == null){
            throw new Exception("Producto no existe.");
        }
        ProductoRepository.getInstance().deleteById(id);
    }

    public Producto findById(Long id) throws Exception {
        Producto p = ProductoRepository.getInstance().findById(id)
                .orElse(null);
        if(p == null){
            throw new Exception("Producto no existe.");
        }
        return p;
    }
}
