package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Usuario;
import java.util.List;

public interface AdminService {
    List<Usuario> listarUsuarios();
    void actualizarPuntos(String email, Integer puntos);
}
