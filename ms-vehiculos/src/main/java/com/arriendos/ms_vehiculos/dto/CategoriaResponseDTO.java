package com.arriendos.ms_vehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CategoriaResponseDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidadVehiculos;
    private Boolean activa;
    private LocalDate fechaCreacion;
    
}