package com.arriendos.ms_vehiculos.runner;

import com.arriendos.ms_vehiculos.model.Categoria;
import com.arriendos.ms_vehiculos.model.Vehiculo;
import com.arriendos.ms_vehiculos.repository.CategoriaRepository;
import com.arriendos.ms_vehiculos.repository.VehiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VehiculoRunner implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final VehiculoRepository vehiculoRepository;

    public VehiculoRunner(CategoriaRepository categoriaRepository,
                          VehiculoRepository vehiculoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public void run(String... args) {

        if (categoriaRepository.count() == 0) {

            Categoria categoria1 = new Categoria();
            categoria1.setNombre("SUV");
            categoria1.setDescripcion("Vehiculos deportivos utilitarios");
            categoria1.setCantidadVehiculos(10);
            categoria1.setActiva(true);
            categoria1.setFechaCreacion(LocalDate.now());

            Categoria categoria2 = new Categoria();
            categoria2.setNombre("Sedan");
            categoria2.setDescripcion("Vehiculos familiares");
            categoria2.setCantidadVehiculos(8);
            categoria2.setActiva(true);
            categoria2.setFechaCreacion(LocalDate.now());

            Categoria categoria3 = new Categoria();
            categoria3.setNombre("Camioneta");
            categoria3.setDescripcion("Vehiculos de carga");
            categoria3.setCantidadVehiculos(5);
            categoria3.setActiva(true);
            categoria3.setFechaCreacion(LocalDate.now());

            categoriaRepository.save(categoria1);
            categoriaRepository.save(categoria2);
            categoriaRepository.save(categoria3);

            Vehiculo vehiculo1 = new Vehiculo();
            vehiculo1.setMarca("Toyota");
            vehiculo1.setModelo("Rav4");
            vehiculo1.setAnio(2022);
            vehiculo1.setPrecioArriendoDiario(45000.0);
            vehiculo1.setDisponible(true);
            vehiculo1.setFechaIngreso(LocalDate.now());
            vehiculo1.setCategoria(categoria1);

            Vehiculo vehiculo2 = new Vehiculo();
            vehiculo2.setMarca("Nissan");
            vehiculo2.setModelo("Sentra");
            vehiculo2.setAnio(2021);
            vehiculo2.setPrecioArriendoDiario(30000.0);
            vehiculo2.setDisponible(true);
            vehiculo2.setFechaIngreso(LocalDate.now());
            vehiculo2.setCategoria(categoria2);

            Vehiculo vehiculo3 = new Vehiculo();
            vehiculo3.setMarca("Ford");
            vehiculo3.setModelo("Raptor");
            vehiculo3.setAnio(2020);
            vehiculo3.setPrecioArriendoDiario(60000.0);
            vehiculo3.setDisponible(false);
            vehiculo3.setFechaIngreso(LocalDate.now());
            vehiculo3.setCategoria(categoria3);

            vehiculoRepository.save(vehiculo1);
            vehiculoRepository.save(vehiculo2);
            vehiculoRepository.save(vehiculo3);

            System.out.println("Datos iniciales de vehiculos y categorias cargados correctamente");
        }
    }
}