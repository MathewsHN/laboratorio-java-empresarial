package com.laboratorio.inventario.repository;


import com.laboratorio.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, Long> {

    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdProductoNot(String codigo , Long idProducto );
}
