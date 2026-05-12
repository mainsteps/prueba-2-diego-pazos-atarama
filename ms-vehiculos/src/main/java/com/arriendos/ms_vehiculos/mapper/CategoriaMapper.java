package com.arriendos.ms_vehiculos.mapper;

import com.arriendos.ms_vehiculos.dto.CategoriaRequestDTO;
import com.arriendos.ms_vehiculos.dto.CategoriaResponseDTO;
import com.arriendos.ms_vehiculos.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequestDTO dto) {

        Categoria categoria = new Categoria();

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setCantidadVehiculos(dto.getCantidadVehiculos());
        categoria.setActiva(dto.getActiva());
        categoria.setFechaCreacion(dto.getFechaCreacion());

        return categoria;
        
    }

    public CategoriaResponseDTO toDTO(Categoria categoria) {

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getCantidadVehiculos(),
                categoria.getActiva(),
                categoria.getFechaCreacion()
        );
    }

}