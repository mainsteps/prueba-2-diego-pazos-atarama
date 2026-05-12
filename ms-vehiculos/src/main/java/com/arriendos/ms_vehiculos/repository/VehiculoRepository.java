package com.arriendos.ms_vehiculos.repository;

import com.arriendos.ms_vehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    List<Vehiculo> findByDisponibleTrueAndPrecioArriendoDiarioLessThan(Double precio);
}