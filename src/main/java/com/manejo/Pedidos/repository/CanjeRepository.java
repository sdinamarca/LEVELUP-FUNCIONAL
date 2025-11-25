package com.manejo.Pedidos.repository;

import com.manejo.Pedidos.model.Canje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanjeRepository extends JpaRepository<Canje, Long> {

    List<Canje> findByEstado(String estado);

}
