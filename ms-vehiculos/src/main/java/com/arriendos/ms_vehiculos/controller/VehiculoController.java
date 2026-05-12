package com.arriendos.ms_vehiculos.controller;

import com.arriendos.ms_vehiculos.dto.VehiculoRequestDTO;
import com.arriendos.ms_vehiculos.dto.VehiculoResponseDTO;
import com.arriendos.ms_vehiculos.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> listar() {
        return ResponseEntity.ok(vehiculoService.obtenerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vehiculoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> guardar(@Valid @RequestBody VehiculoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody VehiculoRequestDTO dto) {

        return ResponseEntity.ok(vehiculoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles/precio-menor")
    public ResponseEntity<List<VehiculoResponseDTO>> buscarDisponiblesPorPrecioMenor(
            @RequestParam Double precio) {

        return ResponseEntity.ok(vehiculoService.buscarDisponiblesPorPrecioMenor(precio));
    }
    
}