package com.manejo.Pedidos.repository;

import com.manejo.Pedidos.model.UsuarioHW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioHWRepository extends JpaRepository<UsuarioHW, Long> {

    Optional<UsuarioHW> findByEmail(String email);

    boolean existsByEmail(String email);
}
