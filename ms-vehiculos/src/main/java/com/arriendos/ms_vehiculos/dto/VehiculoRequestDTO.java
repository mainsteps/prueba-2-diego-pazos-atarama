package com.arriendos.ms_vehiculos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VehiculoRequestDTO {

    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 2, max = 100, message = "La marca debe tener entre 2 y 100 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(min = 2, max = 100, message = "El modelo debe tener entre 2 y 100 caracteres")
    private String modelo;

    @NotNull(message = "El año es obligatorio")
    @Positive(message = "El año debe ser positivo")
    private Integer anio;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio debe ser mayor o igual a 0")
    private Double precioArriendoDiario;

    @NotNull(message = "La disponibilidad es obligatoria")
    private Boolean disponible;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaIngreso;

    @NotNull(message = "La categoriaId es obligatoria")
    private Integer categoriaId;
    
}