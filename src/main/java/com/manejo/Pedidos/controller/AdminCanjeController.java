package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.model.Canje;
import com.manejo.Pedidos.service.CanjeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/canjes")
@CrossOrigin(origins = "*")
public class AdminCanjeController {

    private final CanjeService canjeService;

    public AdminCanjeController(CanjeService canjeService) {
        this.canjeService = canjeService;
    }

    // 🔹 1. Obtener todos los canjes
    @GetMapping
    public List<Canje> obtenerTodos() {
        return canjeService.obtenerTodos();
    }

    // 🔹 2. Aprobar un canje
    @PostMapping("/{id}/aprobar")
    public ResponseEntity<?> aprobar(@PathVariable Long id) {
        try {
            Canje canje = canjeService.aprobarCanje(id);
            return ResponseEntity.ok(canje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 3. Rechazar un canje
    @PostMapping("/{id}/rechazar")
    public ResponseEntity<?> rechazar(@PathVariable Long id) {
        try {
            Canje canje = canjeService.rechazarCanje(id);
            return ResponseEntity.ok(canje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
