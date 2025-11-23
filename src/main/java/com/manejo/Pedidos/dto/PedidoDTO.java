package com.manejo.Pedidos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {

    private Long pedidoId;
    private BigDecimal total;
    private LocalDateTime fecha;

    private UsuarioDTO usuario;   // Resumen del usuario
    private List<DetallePedidoDTO> detalles;
}
