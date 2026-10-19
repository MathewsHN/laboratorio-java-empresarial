package com.laboratorio.inventario.controller;


import com.laboratorio.inventario.model.Producto;
import com.laboratorio.inventario.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public  ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long id) {

        Producto  productoEncontrado  =productoService.buscarProductoPorId(id);

        return ResponseEntity.ok(productoEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){

        List<Producto> productos = productoService.listarProductos();

        return ResponseEntity.ok(productos);
    }


    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {

       Producto productoRegistrado = productoService.registrarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoRegistrado);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> eliminarProductoPorId(@PathVariable Long id){

        productoService.eliminarProductoPorId(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/entrada")
    public ResponseEntity<Void> agregarStock(@PathVariable Long id , @RequestParam int entrada) {

       productoService.registrarEntrada(id, entrada);

        return ResponseEntity.noContent().build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id , @RequestBody Producto producto){


        Producto productoActualizado = productoService.actualizarProducto(id , producto);

        return  ResponseEntity.status(HttpStatus.OK)
                .body(productoActualizado);
    }


    @PostMapping("/{id}/salida")
    public ResponseEntity<Void> disminuirStock(@PathVariable Long id , @RequestParam int salida) {

        productoService.registrarSalida(id, salida);

        return ResponseEntity.noContent().build();

    }
}
