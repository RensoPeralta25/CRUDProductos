package com.codewithmosh.crud.repository;

import com.codewithmosh.crud.pojo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
