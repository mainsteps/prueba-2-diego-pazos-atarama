package com.arriendos.ms_vehiculos.service;

import com.arriendos.ms_vehiculos.dto.CategoriaRequestDTO;
import com.arriendos.ms_vehiculos.dto.CategoriaResponseDTO;
import com.arriendos.ms_vehiculos.exception.ResourceNotFoundException;
import com.arriendos.ms_vehiculos.mapper.CategoriaMapper;
import com.arriendos.ms_vehiculos.model.Categoria;
import com.arriendos.ms_vehiculos.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaResponseDTO> obtenerTodas() {

        log.info("Listando todas las categorias");

        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO obtenerPorId(Integer id) {

        log.info("Buscando categoria por id {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria no encontrada con id " + id));

        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto) {

        try {

            log.info("Guardando categoria {}", dto.getNombre());

            Categoria categoria = categoriaMapper.toEntity(dto);
            Categoria guardada = categoriaRepository.save(categoria);

            return categoriaMapper.toDTO(guardada);

        } catch (Exception e) {

            log.error("Error al guardar categoria: {}", e.getMessage());

            throw e;
        }
    }

    public CategoriaResponseDTO actualizar(Integer id, CategoriaRequestDTO dto) {

        log.info("Actualizando categoria con id {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria no encontrada con id " + id));

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setCantidadVehiculos(dto.getCantidadVehiculos());
        categoria.setActiva(dto.getActiva());
        categoria.setFechaCreacion(dto.getFechaCreacion());
        Categoria actualizada = categoriaRepository.save(categoria);

        return categoriaMapper.toDTO(actualizada);
    }

    public void eliminar(Integer id) {

        log.info("Eliminando categoria con id {}", id);

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria no encontrada con id " + id));

        categoriaRepository.delete(categoria);
    }

}