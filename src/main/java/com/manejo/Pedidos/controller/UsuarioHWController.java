package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.model.UsuarioHW;
import com.manejo.Pedidos.service.UsuarioHWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuariohw")
public class UsuarioHWController {

    @Autowired
    private UsuarioHWService usuarioHWService;

    // Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody UsuarioHW usuario) {
        try {
            UsuarioHW nuevo = usuarioHWService.registrar(usuario);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String contrasena) {
        UsuarioHW usuario = usuarioHWService.login(email, contrasena);

        if (usuario == null) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        return ResponseEntity.ok(usuario);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<UsuarioHW>> obtenerTodos() {
        return ResponseEntity.ok(usuarioHWService.findAll());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        UsuarioHW usuario = usuarioHWService.findById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        usuarioHWService.delete(id);
        return ResponseEntity.ok("Usuario eliminado");
    }
}
