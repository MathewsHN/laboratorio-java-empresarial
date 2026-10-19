package com.laboratorio.inventario.service;


import com.laboratorio.inventario.model.Producto;
import com.laboratorio.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl  implements  ProductoService{

    private  final ProductoRepository productoRepository;


    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }


    @Override
    public Producto registrarProducto(Producto producto) {


      boolean existe = productoRepository.existsByCodigo(producto.getCodigo());

      if(existe) {

          throw  new RuntimeException(
                  "El producto ya se encuentra registrado en el sistema"
          );
      }

        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id ,Producto producto) {

        Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No se encontro el producto"
                ));

        boolean codigoDuplicado = productoRepository
                .existsByCodigoAndIdProductoNot(producto.getCodigo(), id);

        if (codigoDuplicado) {
            throw new IllegalArgumentException(
                    "El código ya pertenece a otro producto"
            );
        }

        productoEncontrado.setCodigo(producto.getCodigo());
        productoEncontrado.setNombre(producto.getNombre());
        productoEncontrado.setDescripcion(producto.getDescripcion());
        productoEncontrado.setPrecio(producto.getPrecio());

        return  productoRepository.save(productoEncontrado);
    }

    @Override
    public List<Producto> listarProductos() {

        return  productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Long id) {

        Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No se encontro el producto"
                ));

        return productoEncontrado;
    }

    @Override
    public void eliminarProductoPorId(Long id) {

        Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No se encontro el producto"
                ));

        productoRepository.deleteById(id);
    }

    @Override
    public void registrarEntrada(Long id ,int entrada) {

        if (entrada <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de entrada debe ser mayor que cero"
            );
        }

       Producto ProductoEncontrado = productoRepository.findById(id)
               .orElseThrow(()-> new RuntimeException(
                       "No se encontro el producto"
               ));


      ProductoEncontrado.setStock(ProductoEncontrado.getStock() + entrada);

      productoRepository.save(ProductoEncontrado);




    }

    @Override
    public void registrarSalida(Long id ,int salida) {

        if (salida <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de salida debe ser mayor que cero"
            );
        }


        Producto ProductoEncontrado = productoRepository.findById(id).orElseThrow(()-> new RuntimeException(
                "No se encontro el producto"
        ));

       if (ProductoEncontrado.getStock() < salida) {

           throw new RuntimeException(
                   "No contamos con stock suficiente"
           );

       }


        ProductoEncontrado.setStock(ProductoEncontrado.getStock() - salida);

        productoRepository.save(ProductoEncontrado);

    }
}
