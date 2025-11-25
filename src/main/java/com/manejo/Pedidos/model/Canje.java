package com.manejo.Pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "canjes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Canje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_canje", nullable = false, unique = true)
    private Long idCanje;

    // Usuario que realiza el canje
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Producto que se canjea
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    // Puntos que se descontaron al usuario
    @Column(name = "puntos_canjeados", nullable = false)
    private Integer puntosCanjeados;

    // Estado del canje: PENDIENTE, APROBADO, RECHAZADO
    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "PENDIENTE";

    // Fecha del canje
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();
}
