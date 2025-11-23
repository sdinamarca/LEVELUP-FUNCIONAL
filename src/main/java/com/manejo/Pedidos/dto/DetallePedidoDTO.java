package com.manejo.Pedidos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetallePedidoDTO {

    private Long detalleId;
    private Integer cantidad;
    private BigDecimal subtotal;

    private ProductoDTO producto;
}
