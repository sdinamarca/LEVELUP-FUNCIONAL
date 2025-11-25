package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.repository.UsuarioRepository;
import com.manejo.Pedidos.repository.ProductoRepository;
import com.manejo.Pedidos.repository.CanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminStatsController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CanjeRepository canjeRepository;

    @GetMapping("/stats")
    public StatsResponse obtenerStats() {

        long totalUsuarios = usuarioRepository.count();

        long totalProductos = productoRepository.count();

        long totalCanjes = canjeRepository.count();

        Long totalPuntos = Long.valueOf(usuarioRepository.sumPuntos()); // debe existir en el repo

        return new StatsResponse(
                totalUsuarios,
                totalPuntos != null ? totalPuntos : 0,
                totalCanjes,
                totalProductos
        );
    }

    record StatsResponse(long totalUsuarios, long totalPuntos, long totalCanjes, long totalProductos) {}
}
