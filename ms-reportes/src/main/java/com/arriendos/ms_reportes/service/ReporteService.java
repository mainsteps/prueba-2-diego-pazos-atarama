package com.arriendos.ms_reportes.service;

import com.arriendos.ms_reportes.client.PagoClient;
import com.arriendos.ms_reportes.client.ReservaClient;
import com.arriendos.ms_reportes.dto.ReporteRequestDTO;
import com.arriendos.ms_reportes.dto.ReporteResponseDTO;
import com.arriendos.ms_reportes.exception.ResourceNotFoundException;
import com.arriendos.ms_reportes.mapper.ReporteMapper;
import com.arriendos.ms_reportes.model.Reporte;
import com.arriendos.ms_reportes.repository.ReporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);
    private final ReporteRepository reporteRepository;
    private final ReporteMapper reporteMapper;
    private final ReservaClient reservaClient;
    private final PagoClient pagoClient;

    public ReporteService(ReporteRepository reporteRepository, ReporteMapper reporteMapper, ReservaClient reservaClient, PagoClient pagoClient) {
        this.reporteRepository = reporteRepository;
        this.reporteMapper = reporteMapper;
        this.reservaClient = reservaClient;
        this.pagoClient = pagoClient;
    }

    public List<ReporteResponseDTO> obtenerTodos() {

        log.info("Listando reportes");

        return reporteRepository.findAll()
                .stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReporteResponseDTO obtenerPorId(Integer id) {

        log.info("Buscando reporte por id {}", id);

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reporte no encontrado con id " + id));
        return reporteMapper.toDTO(reporte);
    }

    public ReporteResponseDTO guardar(ReporteRequestDTO dto) {
        try {

            log.info("Generando reporte {}", dto.getTitulo());

            reservaClient.obtenerReservas();
            pagoClient.obtenerPagos();
            Reporte reporte = reporteMapper.toEntity(dto);
            Reporte guardado = reporteRepository.save(reporte);
            return reporteMapper.toDTO(guardado);

        } catch (Exception e) {

            log.error("Error al generar reporte: {}", e.getMessage());

            throw e;
        }
    }

    public ReporteResponseDTO actualizar(Integer id, ReporteRequestDTO dto) {

        log.info("Actualizando reporte con id {}", id);

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reporte no encontrado con id " + id));


        reporte.setTitulo(dto.getTitulo());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setCantidadReservas(dto.getCantidadReservas());
        reporte.setMontoTotalPagos(dto.getMontoTotalPagos());
        reporte.setGenerado(dto.getGenerado());
        reporte.setFechaGeneracion(dto.getFechaGeneracion());
        Reporte actualizado = reporteRepository.save(reporte);
        return reporteMapper.toDTO(actualizado);
    }

    public void eliminar(Integer id) {

        log.info("Eliminando reporte con id {}", id);

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reporte no encontrado con id " + id));
        reporteRepository.delete(reporte);
    }

    public List<ReporteResponseDTO> buscarGenerados() {

        log.info("Buscando reportes generados");

        return reporteRepository.findByGeneradoTrueOrderByFechaGeneracionDesc()
                .stream()
                .map(reporteMapper::toDTO)
                .collect(Collectors.toList());
    }
}