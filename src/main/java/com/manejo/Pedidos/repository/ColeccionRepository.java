package com.manejo.Pedidos.repository;

import com.manejo.Pedidos.model.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColeccionRepository extends JpaRepository<Coleccion, Long> {

    List<Coleccion> findByUsuarioId(Long idUsuario);

    boolean existsByUsuarioIdAndHotwheelId(Long idUsuario, Long idHotwheel);
}
