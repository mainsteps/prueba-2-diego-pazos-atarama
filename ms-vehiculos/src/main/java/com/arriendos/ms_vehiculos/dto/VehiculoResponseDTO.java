package com.arriendos.ms_vehiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class VehiculoResponseDTO {

    private Integer id;
    private String marca;
    private String modelo;
    private Integer anio;
    private Double precioArriendoDiario;
    private Boolean disponible;
    private LocalDate fechaIngreso;
    private Integer categoriaId;
    
}