package com.manejo.Pedidos.repository;

import com.manejo.Pedidos.model.Pedidos;
import com.manejo.Pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
        List<Pedidos> findByUsuario(Usuario usuario);
}



