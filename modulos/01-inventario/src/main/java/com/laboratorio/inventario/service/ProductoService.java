package com.laboratorio.inventario.service;

import com.laboratorio.inventario.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto registrarProducto(Producto producto);

    Producto actualizarProducto(Long id , Producto producto);

    List<Producto> listarProductos();

    Producto buscarProductoPorId(Long id);

     void eliminarProductoPorId(Long id);

     void registrarEntrada(Long id,int entrada);

     void registrarSalida(Long id ,int salida);
}
