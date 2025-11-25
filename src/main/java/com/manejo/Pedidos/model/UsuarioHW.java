package com.manejo.Pedidos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuario_hw")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioHW {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();
}
