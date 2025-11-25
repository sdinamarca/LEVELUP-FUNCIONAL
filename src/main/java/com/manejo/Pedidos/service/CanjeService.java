package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Canje;

import java.util.List;

public interface CanjeService {

    // Usuario crea un canje
    Canje crearCanje(Long usuarioId, Long productoId);

    // Admin obtiene todos los canjes
    List<Canje> obtenerTodos();

    // Admin aprueba un canje
    Canje aprobarCanje(Long idCanje);

    // Admin rechaza un canje
    Canje rechazarCanje(Long idCanje);
}
