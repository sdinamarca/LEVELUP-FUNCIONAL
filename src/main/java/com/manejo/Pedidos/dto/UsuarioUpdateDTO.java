package com.manejo.Pedidos.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {

    private String userName;

    @Email(message = "Correo inválido")
    private String userEmail;

    private String userPhone;
    private String userAdress;
    private String avatarUrl;

    private String userPassword; // opcional (si quiere cambiarla)
}
