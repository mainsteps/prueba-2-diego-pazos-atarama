package com.arriendos.ms_vehiculos.mapper;

import com.arriendos.ms_vehiculos.dto.VehiculoRequestDTO;
import com.arriendos.ms_vehiculos.dto.VehiculoResponseDTO;
import com.arriendos.ms_vehiculos.model.Categoria;
import com.arriendos.ms_vehiculos.model.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class VehiculoMapper {

    public Vehiculo toEntity(VehiculoRequestDTO dto, Categoria categoria) {

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setPrecioArriendoDiario(dto.getPrecioArriendoDiario());
        vehiculo.setDisponible(dto.getDisponible());
        vehiculo.setFechaIngreso(dto.getFechaIngreso());

        vehiculo.setCategoria(categoria);

        return vehiculo;
    }

    public VehiculoResponseDTO toDTO(Vehiculo vehiculo) {

        return new VehiculoResponseDTO(
                vehiculo.getId(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnio(),
                vehiculo.getPrecioArriendoDiario(),
                vehiculo.getDisponible(),
                vehiculo.getFechaIngreso(),
                vehiculo.getCategoria().getId()
        );
    }

}