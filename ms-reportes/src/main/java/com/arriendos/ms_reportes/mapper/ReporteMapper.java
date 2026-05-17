package com.arriendos.ms_reportes.mapper;

import com.arriendos.ms_reportes.dto.ReporteRequestDTO;
import com.arriendos.ms_reportes.dto.ReporteResponseDTO;
import com.arriendos.ms_reportes.model.Reporte;
import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {
    public Reporte toEntity(ReporteRequestDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setTitulo(dto.getTitulo());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setCantidadReservas(dto.getCantidadReservas());
        reporte.setMontoTotalPagos(dto.getMontoTotalPagos());
        reporte.setGenerado(dto.getGenerado());
        reporte.setFechaGeneracion(dto.getFechaGeneracion());

        return reporte;
    }

    public ReporteResponseDTO toDTO(Reporte reporte) {
        return new ReporteResponseDTO(
                reporte.getId(),
                reporte.getTitulo(),
                reporte.getDescripcion(),
                reporte.getCantidadReservas(),
                reporte.getMontoTotalPagos(),
                reporte.getGenerado(),
                reporte.getFechaGeneracion()
        );
    }


}