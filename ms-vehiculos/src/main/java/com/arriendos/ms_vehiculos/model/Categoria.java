package com.arriendos.ms_vehiculos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer cantidadVehiculos;

    @Column(nullable = false)
    private Boolean activa;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "categoria")
    private List<Vehiculo> vehiculos;

}
