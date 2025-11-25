package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Producto;
import com.manejo.Pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    // ========== LISTAR TODOS ==========
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }


    // ========== BUSCAR POR ID ==========
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado."));
    }


    // ========== CREAR PRODUCTO ==========
    public Producto crearProducto(Producto producto) {

        // Validación de campos importantes
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del producto no puede estar vacío.");
        }

        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El precio debe ser mayor a cero.");
        }

        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        return productoRepository.save(producto);
    }


    // ========== ACTUALIZAR PRODUCTO COMPLETO ==========
    public Producto actualizarProducto(Long id, Producto datos) {

        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (datos.getNombre() != null) p.setNombre(datos.getNombre());
        if (datos.getDescripcion() != null) p.setDescripcion(datos.getDescripcion());
        if (datos.getPrecio() != null) p.setPrecio(datos.getPrecio());
        if (datos.getStock() != null) p.setStock(datos.getStock());
        if (datos.getCategoria() != null) p.setCategoria(datos.getCategoria());
        if (datos.getImagenUrl() != null) p.setImagenUrl(datos.getImagenUrl());
        if (datos.getPuntosNecesarios() != null) p.setPuntosNecesarios(datos.getPuntosNecesarios());
        if (datos.getPrecioReferencia() != null) p.setPrecioReferencia(datos.getPrecioReferencia());

        return productoRepository.save(p);
    }



    // ========== MODIFICAR SOLO EL STOCK ==========
    public Producto actualizarStock(Long id, Integer nuevoStock) {

        if (nuevoStock < 0) {
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado."));

        producto.setStock(nuevoStock);

        return productoRepository.save(producto);
    }


    // ========== ELIMINAR PRODUCTO ==========
    public void eliminarProducto(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado."));

        productoRepository.delete(producto);
    }
}
