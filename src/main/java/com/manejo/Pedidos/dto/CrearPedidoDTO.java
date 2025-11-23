package com.manejo.Pedidos.dto;

import lombok.Data;
import java.util.List;

@Data
public class CrearPedidoDTO {

    private Long idUsuario;

    private List<ItemPedidoDTO> items;
}