package com.manejo.Pedidos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotwheel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotwheels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotwheel", nullable = false, unique = true)
    private Long id;

    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "serie", length = 100)
    private String serie;

    @Column(name = "favorito", nullable = false)
    private Boolean favorito = false;

    @Column(name = "url_imagen", length = 255)
    private String urlImagen;
}
