package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.dto.CrearPedidoDTO;
import com.manejo.Pedidos.dto.PedidoDTO;
import com.manejo.Pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;


    // ========== CREAR PEDIDO ==========
    @PostMapping
    public PedidoDTO crearPedido(@RequestBody CrearPedidoDTO dto) {
        return pedidoService.crearPedido(dto); // ← YA devuelve DTO
    }


    // ========== OBTENER PEDIDO POR ID ==========
    @GetMapping("/{id}")
    public PedidoDTO obtenerPedido(@PathVariable Long id) {
        return pedidoService.buscarPorId(id); // ← Ya devuelve PedidoDTO
    }


    // ========== LISTAR PEDIDOS POR USUARIO ==========
    @GetMapping("/usuario/{idUsuario}")
    public List<PedidoDTO> obtenerPedidosPorUsuario(@PathVariable Long idUsuario) {
        return pedidoService.listarPorUsuario(idUsuario); // ← Ya devuelve List<PedidoDTO>
    }


    // ========== LISTAR TODOS LOS PEDIDOS ==========
    @GetMapping
    public List<PedidoDTO> listarTodos() {
        return pedidoService.listarTodos(); // ← Ya devuelve List<PedidoDTO>
    }
}
