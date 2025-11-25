package com.manejo.Pedidos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRegisterDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String userName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String userEmail;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String userPassword;

    @NotBlank(message = "El teléfono es obligatorio")
    private String userPhone;

    private String userAdress;

    private String avatarUrl; // opcional
}
