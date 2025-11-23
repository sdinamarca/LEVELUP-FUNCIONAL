package com.manejo.Pedidos.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long userId;
    private String userName;
    private String userEmail;

    private String avatarUrl;
}
