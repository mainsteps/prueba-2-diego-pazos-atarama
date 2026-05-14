package com.arriendos.ms_pagos.repository;

import com.arriendos.ms_pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    @Query("SELECT p FROM Pago p WHERE p.monto BETWEEN :montoMin AND :montoMax ORDER BY p.fechaPago DESC")

    List<Pago> buscarPorRangoMonto(Double montoMin, Double montoMax);

}