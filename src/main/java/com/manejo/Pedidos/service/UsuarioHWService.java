package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.UsuarioHW;
import com.manejo.Pedidos.repository.UsuarioHWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioHWService {

    public void setUsuarioHWRepository(UsuarioHWRepository repo) {
        this.usuarioHWRepository = repo;
    }

    @Autowired
    private UsuarioHWRepository usuarioHWRepository;

    // Registrar nuevo usuario
    public UsuarioHW registrar(UsuarioHW usuario) {

        if (usuarioHWRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }

        return usuarioHWRepository.save(usuario);
    }

    // Login básico (sin encriptación por ahora)
    public UsuarioHW login(String email, String contrasena) {
        return usuarioHWRepository.findByEmail(email)
                .filter(u -> u.getContrasena().equals(contrasena))
                .orElse(null);
    }

    // Obtener todos los usuarios
    public List<UsuarioHW> findAll() {
        return usuarioHWRepository.findAll();
    }

    // Buscar usuario por ID
    public UsuarioHW findById(Long id) {
        return usuarioHWRepository.findById(id).orElse(null);
    }

    // Eliminar usuario
    public void delete(Long id) {
        usuarioHWRepository.deleteById(id);
    }
}
