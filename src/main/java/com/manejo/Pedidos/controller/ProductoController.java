package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.model.Producto;
import com.manejo.Pedidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    // ========== OBTENER TODOS LOS PRODUCTOS ==========
    @GetMapping
    public List<Producto> listar() {
        return productoService.listarProductos();
    }


    // ========== OBTENER PRODUCTO POR ID ==========
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }


    // ========== CREAR PRODUCTO ==========
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }


    // ========== ACTUALIZAR PRODUCTO COMPLETO ==========
    @PutMapping("/{id}")
    public Producto actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto datosActualizados
    ) {
        return productoService.actualizarProducto(id, datosActualizados);
    }


    // ========== ACTUALIZAR SOLO EL STOCK ==========
    @PatchMapping("/{id}/stock")
    public Producto actualizarStock(
            @PathVariable Long id,
            @RequestParam Integer stock
    ) {
        return productoService.actualizarStock(id, stock);
    }


    // ========== ELIMINAR PRODUCTO ==========
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "Producto eliminado correctamente.";
    }
}
