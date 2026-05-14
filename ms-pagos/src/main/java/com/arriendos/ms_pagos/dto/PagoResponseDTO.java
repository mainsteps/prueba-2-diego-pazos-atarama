package com.arriendos.ms_pagos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class PagoResponseDTO {
    private Integer id;
    private Integer reservaId;
    private String codigoPago;
    private Double monto;
    private String metodoPago;
    private Boolean pagado;
    private LocalDate fechaPago;
    private Integer numeroCuotas;

    
}