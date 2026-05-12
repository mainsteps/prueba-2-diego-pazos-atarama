package com.arriendos.ms_vehiculos.repository;

import com.arriendos.ms_vehiculos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}