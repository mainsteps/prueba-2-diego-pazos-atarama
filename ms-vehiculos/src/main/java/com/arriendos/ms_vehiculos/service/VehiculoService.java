package com.arriendos.ms_vehiculos.service;

import com.arriendos.ms_vehiculos.dto.VehiculoRequestDTO;
import com.arriendos.ms_vehiculos.dto.VehiculoResponseDTO;
import com.arriendos.ms_vehiculos.exception.ResourceNotFoundException;
import com.arriendos.ms_vehiculos.mapper.VehiculoMapper;
import com.arriendos.ms_vehiculos.model.Categoria;
import com.arriendos.ms_vehiculos.model.Vehiculo;
import com.arriendos.ms_vehiculos.repository.CategoriaRepository;
import com.arriendos.ms_vehiculos.repository.VehiculoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoService {

    private static final Logger log = LoggerFactory.getLogger(VehiculoService.class);

    private final VehiculoRepository vehiculoRepository;
    private final CategoriaRepository categoriaRepository;
    private final VehiculoMapper vehiculoMapper;

    public VehiculoService(VehiculoRepository vehiculoRepository, CategoriaRepository categoriaRepository, VehiculoMapper vehiculoMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.categoriaRepository = categoriaRepository;
        this.vehiculoMapper = vehiculoMapper;
    }

    public List<VehiculoResponseDTO> obtenerTodos() {
        log.info("Listando todos los vehiculos");

        return vehiculoRepository.findAll()
                .stream()
                .map(vehiculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VehiculoResponseDTO obtenerPorId(Integer id) {
        log.info("Buscando vehiculo por id {}", id);

        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo no encontrado con id " + id));

        return vehiculoMapper.toDTO(vehiculo);
    }

    public VehiculoResponseDTO guardar(VehiculoRequestDTO dto) {
        try {
            log.info("Guardando vehiculo {} {}", dto.getMarca(), dto.getModelo());

            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id " + dto.getCategoriaId()));

            Vehiculo vehiculo = vehiculoMapper.toEntity(dto, categoria);
            Vehiculo guardado = vehiculoRepository.save(vehiculo);

            return vehiculoMapper.toDTO(guardado);

        } catch (Exception e) {
            log.error("Error al guardar vehiculo: {}", e.getMessage());
            throw e;
        }
    }

    public VehiculoResponseDTO actualizar(Integer id, VehiculoRequestDTO dto) {
        log.info("Actualizando vehiculo con id {}", id);

        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo no encontrado con id " + id));
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id " + dto.getCategoriaId()));

        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setPrecioArriendoDiario(dto.getPrecioArriendoDiario());
        vehiculo.setDisponible(dto.getDisponible());
        vehiculo.setFechaIngreso(dto.getFechaIngreso());
        vehiculo.setCategoria(categoria);
        Vehiculo actualizado = vehiculoRepository.save(vehiculo);

        return vehiculoMapper.toDTO(actualizado);
    }

    public void eliminar(Integer id) {
        log.info("Eliminando vehiculo con id {}", id);

        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo no encontrado con id " + id));
        vehiculoRepository.delete(vehiculo);
    }

    public List<VehiculoResponseDTO> buscarDisponiblesPorPrecioMenor(Double precio) {
        log.info("Buscando vehiculos disponibles con precio menor a {}", precio);

        return vehiculoRepository.findByDisponibleTrueAndPrecioArriendoDiarioLessThan(precio)
                .stream()
                .map(vehiculoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
}