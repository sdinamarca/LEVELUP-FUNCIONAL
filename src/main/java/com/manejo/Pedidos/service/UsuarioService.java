package com.manejo.Pedidos.service;

import com.manejo.Pedidos.config.JwtUtil;
import com.manejo.Pedidos.dto.*;
import com.manejo.Pedidos.model.Rol;
import com.manejo.Pedidos.model.Usuario;
import com.manejo.Pedidos.repository.RolRepository;
import com.manejo.Pedidos.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RolRepository rolRepository;



    // ------------------------------------------
    // 1. REGISTRO DE USUARIO
    // ------------------------------------------
    public UsuarioDTO registrarUsuario(UsuarioRegisterDTO dto) {
        Usuario usuario = new Usuario();



        Rol rolUser = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
        usuario.setRol(rolUser);

        // Validar email duplicado
        if (usuarioRepository.findByuserEmail(dto.getUserEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        usuario.setUserName(dto.getUserName());
        usuario.setUserEmail(dto.getUserEmail());
        usuario.setUserPhone(dto.getUserPhone());
        usuario.setUserAdress(dto.getUserAdress());
        usuario.setAvatarUrl(dto.getAvatarUrl());

        // Encriptar contraseña
        usuario.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));

        Usuario guardado = usuarioRepository.save(usuario);

        return toUsuarioDTO(guardado);
    }


    // ------------------------------------------
// 2. LOGIN (ahora devuelve token + usuario)
// ------------------------------------------
    public LoginResponseDTO login(String email, String password) {

        Usuario usuario = usuarioRepository
                .findByuserEmail(email)
                .orElseThrow(() -> new RuntimeException("Correo o contraseña incorrectos."));

        // Validar contraseña
        if (!passwordEncoder.matches(password, usuario.getUserPassword())) {
            throw new RuntimeException("Correo o contraseña incorrectos.");
        }

        // Generar token usando el email como "username"
        String token = jwtUtil.generateToken(usuario.getUserEmail());

        // Armar respuesta
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUsuario(toUsuarioDTO(usuario));

        return response;
    }




    // ------------------------------------------
    // 3. BUSCAR POR ID
    // ------------------------------------------
    public UsuarioDTO buscarPorId(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        return toUsuarioDTO(usuario);
    }



    // ------------------------------------------
    // 4. BUSCAR POR EMAIL
    // ------------------------------------------
    public UsuarioDTO buscarPorEmail(String email) {

        Usuario usuario = usuarioRepository
                .findByuserEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        return toUsuarioDTO(usuario);
    }



    // ------------------------------------------
    // 5. ACTUALIZAR USUARIO
    // ------------------------------------------
    public UsuarioDTO actualizarUsuario(Long idUsuario, UsuarioUpdateDTO dto) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        if (dto.getUserName() != null)
            usuario.setUserName(dto.getUserName());

        if (dto.getUserEmail() != null) {
            Optional<Usuario> existente = usuarioRepository.findByuserEmail(dto.getUserEmail());
            if (existente.isPresent() && !existente.get().getUserId().equals(idUsuario))
                throw new RuntimeException("El correo ya está en uso por otro usuario.");
            usuario.setUserEmail(dto.getUserEmail());
        }

        if (dto.getUserPhone() != null)
            usuario.setUserPhone(dto.getUserPhone());

        if (dto.getUserAdress() != null)
            usuario.setUserAdress(dto.getUserAdress());

        if (dto.getAvatarUrl() != null)
            usuario.setAvatarUrl(dto.getAvatarUrl());

        if (dto.getUserPassword() != null)
            usuario.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));

        Usuario actualizado = usuarioRepository.save(usuario);

        return toUsuarioDTO(actualizado);
    }



    // ------------------------------------------
    // 6. ELIMINAR USUARIO
    // ------------------------------------------
    public void eliminarUsuario(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        usuarioRepository.delete(usuario);
    }



    // ------------------------------------------
    // 7. MAPPER INTERNO → Entidad a UsuarioDTO
    // ------------------------------------------
    private UsuarioDTO toUsuarioDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();

        dto.setUserId(usuario.getUserId());
        dto.setUserName(usuario.getUserName());
        dto.setUserEmail(usuario.getUserEmail());
        dto.setAvatarUrl(usuario.getAvatarUrl());
        dto.setRol(usuario.getRol());

        return dto;
    }
}
