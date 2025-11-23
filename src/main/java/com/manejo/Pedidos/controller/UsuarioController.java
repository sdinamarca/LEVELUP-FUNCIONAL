package com.manejo.Pedidos.controller;

import com.manejo.Pedidos.dto.*;
import com.manejo.Pedidos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.manejo.Pedidos.dto.LoginResponseDTO;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    // ========== REGISTRO ==========
    @PostMapping("/registro")
    public UsuarioDTO registrar(@Valid @RequestBody UsuarioRegisterDTO dto) {
        return usuarioService.registrarUsuario(dto);
    }


//===========================LOGIN============================
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginDTO) {
        return usuarioService.login(loginDTO.getEmail(), loginDTO.getPassword());
    }



    // ========== BUSCAR POR ID ==========
    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }


    // ========== ACTUALIZAR USUARIO ==========
    @PutMapping("/{id}")
    public UsuarioDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateDTO dto) {

        return usuarioService.actualizarUsuario(id, dto);
    }


    // ========== ELIMINAR USUARIO ==========
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado correctamente.";
    }
}
