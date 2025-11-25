package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Coleccion;
import com.manejo.Pedidos.model.Hotwheels;
import com.manejo.Pedidos.model.UsuarioHW;
import com.manejo.Pedidos.repository.ColeccionRepository;
import com.manejo.Pedidos.repository.HotwheelRepository;
import com.manejo.Pedidos.repository.UsuarioHWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColeccionService {

    @Autowired
    private ColeccionRepository coleccionRepository;

    @Autowired
    private UsuarioHWRepository usuarioHWRepository;

    @Autowired
    private HotwheelRepository hotwheelRepository;

    public void setColeccionRepository(ColeccionRepository repo) {
        this.coleccionRepository = repo;
    }

    public void setUsuarioHWRepository(UsuarioHWRepository repo) {
        this.usuarioHWRepository = repo;
    }

    public void setHotwheelRepository(HotwheelRepository repo) {
        this.hotwheelRepository = repo;
    }


    // Agregar a la colección
    public Coleccion addToCollection(Long idUsuario, Long idHotwheel) {

        if (coleccionRepository.existsByUsuarioIdAndHotwheelId(idUsuario, idHotwheel)) {
            throw new RuntimeException("Ese Hotwheel ya está en la colección del usuario.");
        }

        UsuarioHW usuario = usuarioHWRepository.findById(idUsuario).orElse(null);
        Hotwheels hw = hotwheelRepository.findById(idHotwheel).orElse(null);

        if (usuario == null || hw == null) {
            throw new RuntimeException("Usuario o Hotwheel no encontrado.");
        }

        Coleccion c = new Coleccion();
        c.setUsuario(usuario);
        c.setHotwheel(hw);

        return coleccionRepository.save(c);
    }

    // Obtener colección del usuario
    public List<Coleccion> getColeccionByUsuario(Long idUsuario) {
        return coleccionRepository.findByUsuarioId(idUsuario);
    }

    // Eliminar item de colección
    public void delete(Long idColeccion) {
        coleccionRepository.deleteById(idColeccion);
    }
}
