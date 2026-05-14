package com.arriendos.ms_pagos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PagoRequestDTO {

    @NotNull(message = "El reservaId es obligatorio")
    private Integer reservaId;

    @NotBlank(message = "El codigo de pago es obligatorio")
    private String codigoPago;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a 0")
    private Double monto;

    @NotBlank(message = "El metodo de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "El estado pagado es obligatorio")
    private Boolean pagado;

    @NotNull(message = "La fecha de pago es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaPago;

    @NotNull(message = "El numero de cuotas es obligatorio")
    @Positive(message = "El numero de cuotas debe ser positivo")
    private Integer numeroCuotas;
}