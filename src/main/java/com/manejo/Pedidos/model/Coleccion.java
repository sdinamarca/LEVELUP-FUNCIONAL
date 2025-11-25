package com.manejo.Pedidos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "coleccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coleccion", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioHW usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hotwheel", nullable = false)
    private Hotwheels hotwheel;

    @Column(name = "fecha_agregado", nullable = false)
    private LocalDate fechaAgregado = LocalDate.now();
}
