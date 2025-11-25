package com.manejo.Pedidos.repository;

import com.manejo.Pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByuserEmail(String userEmail);

    @Query("SELECT SUM(u.puntos) FROM Usuario u")
    Integer sumPuntos();



}
