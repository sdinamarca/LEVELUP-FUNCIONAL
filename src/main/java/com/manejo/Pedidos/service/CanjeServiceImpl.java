package com.manejo.Pedidos.service;

import com.manejo.Pedidos.model.Canje;
import com.manejo.Pedidos.model.Producto;
import com.manejo.Pedidos.model.Usuario;
import com.manejo.Pedidos.repository.CanjeRepository;
import com.manejo.Pedidos.repository.ProductoRepository;
import com.manejo.Pedidos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CanjeServiceImpl implements CanjeService {

    private final CanjeRepository canjeRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public CanjeServiceImpl(CanjeRepository canjeRepository,
                            UsuarioRepository usuarioRepository,
                            ProductoRepository productoRepository) {
        this.canjeRepository = canjeRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    // USUARIO CREA CANJE
    @Override
    @Transactional
    public Canje crearCanje(Long usuarioId, Long productoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validar puntos
        if (usuario.getPuntos() < producto.getPuntosNecesarios()) {
            throw new RuntimeException("No tienes puntos suficientes para este canje.");
        }

        // Validar stock
        if (producto.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible para este producto.");
        }

        // Descontar puntos del usuario
        usuario.setPuntos(usuario.getPuntos() - producto.getPuntosNecesarios());
        usuarioRepository.save(usuario);

        // Restar stock
        producto.setStock(producto.getStock() - 1);
        productoRepository.save(producto);

        // Crear canje en estado PENDIENTE
        Canje canje = new Canje();
        canje.setUsuario(usuario);
        canje.setProducto(producto);
        canje.setPuntosCanjeados(producto.getPuntosNecesarios());
        canje.setFecha(LocalDateTime.now());
        canje.setEstado("PENDIENTE");

        return canjeRepository.save(canje);
    }

    // ADMIN LISTA TODO
    @Override
    public List<Canje> obtenerTodos() {
        return canjeRepository.findAll();
    }

    // ADMIN APRUEBA CANJE
    @Override
    @Transactional
    public Canje aprobarCanje(Long idCanje) {
        Canje canje = canjeRepository.findById(idCanje)
                .orElseThrow(() -> new RuntimeException("Canje no encontrado"));

        canje.setEstado("APROBADO");
        return canjeRepository.save(canje);
    }

    // ADMIN RECHAZA CANJE
    @Override
    @Transactional
    public Canje rechazarCanje(Long idCanje) {
        Canje canje = canjeRepository.findById(idCanje)
                .orElseThrow(() -> new RuntimeException("Canje no encontrado"));

        // Devolver puntos al usuario
        Usuario usuario = canje.getUsuario();
        usuario.setPuntos(usuario.getPuntos() + canje.getPuntosCanjeados());
        usuarioRepository.save(usuario);

        // Devolver stock
        Producto producto = canje.getProducto();
        producto.setStock(producto.getStock() + 1);
        productoRepository.save(producto);

        canje.setEstado("RECHAZADO");
        return canjeRepository.save(canje);
    }
}
