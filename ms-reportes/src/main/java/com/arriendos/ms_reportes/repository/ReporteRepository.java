package com.arriendos.ms_reportes.repository;

import com.arriendos.ms_reportes.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    List<Reporte> findByGeneradoTrueOrderByFechaGeneracionDesc();
}