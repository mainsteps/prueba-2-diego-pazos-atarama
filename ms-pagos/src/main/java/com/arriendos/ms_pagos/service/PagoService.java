package com.arriendos.ms_pagos.service;

import com.arriendos.ms_pagos.client.ReservaClient;
import com.arriendos.ms_pagos.dto.PagoRequestDTO;
import com.arriendos.ms_pagos.dto.PagoResponseDTO;
import com.arriendos.ms_pagos.exception.ResourceNotFoundException;
import com.arriendos.ms_pagos.mapper.PagoMapper;
import com.arriendos.ms_pagos.model.Pago;
import com.arriendos.ms_pagos.repository.PagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);
    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final ReservaClient reservaClient;

    public PagoService(PagoRepository pagoRepository, PagoMapper pagoMapper, ReservaClient reservaClient) {
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
        this.reservaClient = reservaClient;
    }

    public List<PagoResponseDTO> obtenerTodos() {

        log.info("Listando pagos");

        return pagoRepository.findAll()
                .stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PagoResponseDTO obtenerPorId(Integer id) {

        log.info("Buscando pago por id {}", id);

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pago no encontrado con id " + id));

        return pagoMapper.toDTO(pago);
    }

    public PagoResponseDTO guardar(PagoRequestDTO dto) {

        try {

            log.info("Guardando pago para reserva {}", dto.getReservaId());

            try {
                reservaClient.obtenerReservaPorId(dto.getReservaId());
            } catch (Exception e) {
                throw new ResourceNotFoundException("Reserva no encontrada con id " + dto.getReservaId());
            }

            Pago pago = pagoMapper.toEntity(dto);
            Pago guardado = pagoRepository.save(pago);
            return pagoMapper.toDTO(guardado);

        } catch (Exception e) {

            log.error("Error al guardar pago: {}", e.getMessage());

            throw e;
        }
    }

    public PagoResponseDTO actualizar(Integer id, PagoRequestDTO dto) {

        log.info("Actualizando pago con id {}", id);

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pago no encontrado con id " + id));

        try {
            reservaClient.obtenerReservaPorId(dto.getReservaId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Reserva no encontrada con id " + dto.getReservaId());
        }

        pago.setReservaId(dto.getReservaId());
        pago.setCodigoPago(dto.getCodigoPago());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setPagado(dto.getPagado());
        pago.setFechaPago(dto.getFechaPago());
        pago.setNumeroCuotas(dto.getNumeroCuotas());
        Pago actualizado = pagoRepository.save(pago);

        return pagoMapper.toDTO(actualizado);
    }

    public void eliminar(Integer id) {

        log.info("Eliminando pago con id {}", id);

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pago no encontrado con id " + id));
        pagoRepository.delete(pago);
    }

    public List<PagoResponseDTO> buscarPorRangoMonto(Double montoMin, Double montoMax) {

        log.info("Buscando pagos entre {} y {}", montoMin, montoMax);

        return pagoRepository.buscarPorRangoMonto(montoMin, montoMax)
                .stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }
}