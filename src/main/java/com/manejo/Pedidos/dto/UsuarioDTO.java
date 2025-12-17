package com.manejo.Pedidos.dto;

import com.manejo.Pedidos.model.Rol;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long userId;
    private String userName;
    private String userEmail;

    private Rol rol;
    private String avatarUrl;
}
