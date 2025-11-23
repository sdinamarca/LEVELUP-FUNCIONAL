package com.manejo.Pedidos.service;

import com.manejo.Pedidos.dto.*;
import com.manejo.Pedidos.mapper.PedidoMapper;
import com.manejo.Pedidos.model.DetallePedido;
import com.manejo.Pedidos.model.Pedidos;
import com.manejo.Pedidos.model.Producto;
import com.manejo.Pedidos.model.Usuario;
import com.manejo.Pedidos.repository.PedidosRepository;
import com.manejo.Pedidos.repository.ProductoRepository;
import com.manejo.Pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidosRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // ========== CREAR PEDIDO ==========
    public PedidoDTO crearPedido(CrearPedidoDTO dto) {

        // 1) Validar usuario
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        Pedidos pedido = new Pedidos();
        pedido.setUsuario(usuario);
        pedido.setFecha(LocalDateTime.now());

        List<DetallePedido> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        // 2) Procesar cada item
        for (ItemPedidoDTO item : dto.getItems()) {

            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getIdProducto()));

            // Validar stock
            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Calcular subtotal
            BigDecimal subtotal = producto.getPrecio()
                    .multiply(BigDecimal.valueOf(item.getCantidad()));
            total = total.add(subtotal);

            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setSubtotal(subtotal);
            detalle.setPedido(pedido);

            detalles.add(detalle);

            // Descontar stock
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);
        }

        // 3) Guardar total y detalles
        pedido.setTotal(total);
        pedido.setDetalles(detalles);

        // 4) Guardar pedido final
        Pedidos pedidoGuardado = pedidoRepository.save(pedido);

        // 5) RETORNAR *DTO* (Mapper aplicado aquí)
        return pedidoMapper.toPedidoDTO(pedidoGuardado);
    }


    // ========== BUSCAR PEDIDO POR ID ==========
    public PedidoDTO buscarPorId(Long id) {

        Pedidos pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado."));

        // RETORNO reemplazado por DTO
        return pedidoMapper.toPedidoDTO(pedido);
    }


    // ========== LISTAR POR USUARIO ==========
    public List<PedidoDTO> listarPorUsuario(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        List<Pedidos> pedidos = pedidoRepository.findByUsuario(usuario);

        // RETORNO reemplazado por DTO en stream
        return pedidos.stream()
                .map(pedidoMapper::toPedidoDTO)
                .toList();
    }


    // ========== LISTAR TODOS ==========
    public List<PedidoDTO> listarTodos() {

        List<Pedidos> pedidos = pedidoRepository.findAll();

        // RETORNO reemplazado por DTO
        return pedidos.stream()
                .map(pedidoMapper::toPedidoDTO)
                .toList();
    }
}
