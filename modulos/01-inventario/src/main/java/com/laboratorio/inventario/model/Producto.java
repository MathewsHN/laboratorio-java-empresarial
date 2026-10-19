package com.laboratorio.inventario.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "productos")
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String codigo;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private int stock;


    public Producto() {
    }

    public Producto(Long idProducto, String codigo, BigDecimal precio, int stock, String descripcion , String nombre) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
