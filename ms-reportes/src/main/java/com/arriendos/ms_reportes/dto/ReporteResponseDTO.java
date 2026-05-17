package com.arriendos.ms_reportes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class ReporteResponseDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer cantidadReservas;
    private Double montoTotalPagos;
    private Boolean generado;
    private LocalDate fechaGeneracion;

    
}