package com.arriendos.ms_reportes.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReporteRequestDTO {
    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 2, max = 100, message = "El titulo debe tener entre 2 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull(message = "La cantidad de reservas es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidadReservas;

    @NotNull(message = "El monto total de pagos es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a 0")
    private Double montoTotalPagos;

    @NotNull(message = "El estado generado es obligatorio")
    private Boolean generado;

    @NotNull(message = "La fecha de generacion es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaGeneracion;
}