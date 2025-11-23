package com.manejo.Pedidos.mapper;

import com.manejo.Pedidos.dto.*;
import com.manejo.Pedidos.model.Pedidos;
import com.manejo.Pedidos.model.DetallePedido;
import com.manejo.Pedidos.model.Producto;
import com.manejo.Pedidos.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    // ========== USUARIO A DTO ==========
    public UsuarioDTO toUsuarioDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();
        dto.setUserId(usuario.getUserId());
        dto.setUserName(usuario.getUserName());
        dto.setUserEmail(usuario.getUserEmail());
        dto.setAvatarUrl(usuario.getAvatarUrl());

        return dto;
    }

    // ========== PRODUCTO A DTO ==========
    public ProductoDTO toProductoDTO(Producto producto) {

        ProductoDTO dto = new ProductoDTO();
        dto.setProductoId(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setImagenUrl(producto.getImagenUrl());

        return dto;
    }

    // ========== DETALLE A DTO ==========
    public DetallePedidoDTO toDetalleDTO(DetallePedido detalle) {

        DetallePedidoDTO dto = new DetallePedidoDTO();

        dto.setDetalleId(detalle.getDetalleId());
        dto.setCantidad(detalle.getCantidad());
        dto.setSubtotal(detalle.getSubtotal());

        // Producto del detalle
        dto.setProducto(toProductoDTO(detalle.getProducto()));

        return dto;
    }

    // ========== PEDIDO A DTO ==========
    public PedidoDTO toPedidoDTO(Pedidos pedido) {

        PedidoDTO dto = new PedidoDTO();

        dto.setPedidoId(pedido.getPedidoId());
        dto.setFecha(pedido.getFecha());
        dto.setTotal(pedido.getTotal());

        // Usuario del pedido
        dto.setUsuario(toUsuarioDTO(pedido.getUsuario()));

        // Lista de detalles
        List<DetallePedidoDTO> detallesDTO =
                pedido.getDetalles().stream()
                        .map(this::toDetalleDTO)
                        .toList();

        dto.setDetalles(detallesDTO);

        return dto;
    }
}
