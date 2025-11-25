package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.model.Canje;
import com.manejo.Pedidos.service.CanjeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canjes")
@CrossOrigin(origins = "*")
public class UserCanjeController {

    private final CanjeService canjeService;

    public UserCanjeController(CanjeService canjeService) {
        this.canjeService = canjeService;
    }

    // 🔹 1. Usuario crea un canje
    @PostMapping("/crear")
    public ResponseEntity<?> crearCanje(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId
    ) {
        try {
            Canje canje = canjeService.crearCanje(usuarioId, productoId);
            return ResponseEntity.ok(canje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 2. Usuario obtiene su historial de canjes
    @GetMapping("/mis-canjes")
    public ResponseEntity<?> misCanjes(@RequestParam Long usuarioId) {
        try {
            List<Canje> canjes = canjeService.obtenerTodos()
                    .stream()
                    .filter(c -> c.getUsuario().getUserId().equals(usuarioId))
                    .toList();

            return ResponseEntity.ok(canjes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo obtener historial.");
        }
    }
}
