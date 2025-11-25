package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Usuario;
import com.manejo.Pedidos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UsuarioRepository usuarioRepository;

    public AdminServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void actualizarPuntos(String email, Integer puntos) {

        Usuario u = usuarioRepository.findByuserEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        u.setPuntos(puntos);

        usuarioRepository.save(u);
    }
}
