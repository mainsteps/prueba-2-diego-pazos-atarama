package com.arriendos.ms_pagos.mapper;

import com.arriendos.ms_pagos.dto.PagoRequestDTO;
import com.arriendos.ms_pagos.dto.PagoResponseDTO;
import com.arriendos.ms_pagos.model.Pago;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public Pago toEntity(PagoRequestDTO dto) {


        Pago pago = new Pago();
        pago.setReservaId(dto.getReservaId());
        pago.setCodigoPago(dto.getCodigoPago());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setPagado(dto.getPagado());
        pago.setFechaPago(dto.getFechaPago());
        pago.setNumeroCuotas(dto.getNumeroCuotas());
        return pago;
    }

    public PagoResponseDTO toDTO(Pago pago) {

        return new PagoResponseDTO(
                pago.getId(),
                pago.getReservaId(),
                pago.getCodigoPago(),
                pago.getMonto(),
                pago.getMetodoPago(),
                pago.getPagado(),
                pago.getFechaPago(),
                pago.getNumeroCuotas()
        );
    }
}