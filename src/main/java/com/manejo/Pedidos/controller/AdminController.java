package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.model.Usuario;
import com.manejo.Pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✔ Obtener todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    // ✔ Actualizar puntos
    @PutMapping("/usuarios/puntos")
    public String actualizarPuntos(@RequestParam String email, @RequestParam Integer puntos) {
        Usuario u = usuarioRepository.findByuserEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        u.setPuntos(puntos);
        usuarioRepository.save(u);

        return "Puntos actualizados correctamente";
    }
}
