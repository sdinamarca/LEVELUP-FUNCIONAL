package com.manejo.Pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Override
    public String toString() {
        return "Usuario{id=" + userId + ", email=" + userEmail + "}";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    private Long userId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String userName;

    @Column(name = "email", nullable = false, unique = true, length = 50)
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

    @Column(name = "puntos", nullable = false)
    private Integer puntos = 0;

    @Column(name = "codigo_referido", length = 20)
    private String codigoReferido;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "usuario_referidos", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "referido_codigo")
    private List<String> referidos = new ArrayList<>();

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro = LocalDate.now();

    public boolean esAdmin() {
        return rol != null &&
                rol.getNombre() != null &&
                rol.getNombre().equalsIgnoreCase("ADMIN");
    }
}
