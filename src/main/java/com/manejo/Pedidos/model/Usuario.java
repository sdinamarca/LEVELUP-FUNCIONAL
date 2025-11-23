package com.manejo.Pedidos.model;

import com.manejo.Pedidos.model.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long userId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String userName;

    @Column(name = "email", nullable = false, unique =true, length = 50)
    private String userEmail;

    @Column(name = "password", nullable = false, length = 120)
    private String userPassword;

    @Column(name = "telefono", nullable = false, length = 20)
    private String userPhone;

    @Column(name = "direccion", nullable = false, length = 150)
    private String userAdress;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

}
