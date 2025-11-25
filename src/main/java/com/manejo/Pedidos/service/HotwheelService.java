package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Hotwheels;
import com.manejo.Pedidos.repository.HotwheelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotwheelService {

    public void setHotwheelRepository(HotwheelRepository repo) {
        this.hotwheelRepository = repo;
    }


    @Autowired
    private HotwheelRepository hotwheelRepository;

    // Crear / guardar
    public Hotwheels save(Hotwheels hw) {
        return hotwheelRepository.save(hw);
    }

    // Listar todos
    public List<Hotwheels> findAll() {
        return hotwheelRepository.findAll();
    }

    // Buscar por ID
    public Hotwheels findById(Long id) {
        return hotwheelRepository.findById(id).orElse(null);
    }

    // Actualizar
    public Hotwheels update(Long id, Hotwheels datos) {
        Hotwheels existente = findById(id);

        if (existente == null) return null;

        existente.setModelo(datos.getModelo());
        existente.setColor(datos.getColor());
        existente.setSerie(datos.getSerie());
        existente.setFavorito(datos.getFavorito());
        existente.setUrlImagen(datos.getUrlImagen());

        return hotwheelRepository.save(existente);
    }

    // Eliminar
    public void delete(Long id) {
        hotwheelRepository.deleteById(id);
    }
}
