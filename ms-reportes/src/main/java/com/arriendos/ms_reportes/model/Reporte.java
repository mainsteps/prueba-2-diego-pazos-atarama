package com.arriendos.ms_reportes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer cantidadReservas;

    @Column(nullable = false)
    private Double montoTotalPagos;

    @Column(nullable = false)
    private Boolean generado;

    @Column(nullable = false)
    private LocalDate fechaGeneracion;
}