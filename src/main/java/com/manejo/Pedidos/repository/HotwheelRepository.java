package com.manejo.Pedidos.repository;

import com.manejo.Pedidos.model.Hotwheels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotwheelRepository extends JpaRepository<Hotwheels, Long> {
}
