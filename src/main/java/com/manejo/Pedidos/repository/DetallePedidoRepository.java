package com.manejo.Pedidos.repository;

import com.manejo.Pedidos.model.DetallePedido;
import com.manejo.Pedidos.model.Pedidos;
import com.manejo.Pedidos.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository {

    // Buscar todos los detalles de un pedido
    List<DetallePedido> findByPedido(Pedidos pedido);

    // Buscar todos los detalles donde aparezca un producto específico
    List<DetallePedido> findByProducto(Producto producto);
}
