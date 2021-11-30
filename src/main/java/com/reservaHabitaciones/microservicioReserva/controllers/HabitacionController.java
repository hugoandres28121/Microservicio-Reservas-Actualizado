package com.reservaHabitaciones.microservicioReserva.controllers;

import com.reservaHabitaciones.microservicioReserva.exceptions.RecursoNoEncontradoException;
import com.reservaHabitaciones.microservicioReserva.models.Habitacion;
import com.reservaHabitaciones.microservicioReserva.repositories.HabitacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabitacionController {
    private final HabitacionRepository habitacionRepository;

    public HabitacionController(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    //Crear Habitaciones
    @PostMapping("/habitaciones")
    Habitacion newHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    //Listar Todas las Habitaciones
    @GetMapping("/habitaciones")
    public List<Habitacion> getAll() {
        return habitacionRepository.findAll();

    }

    //Traer una sola Habitacion
    @GetMapping("/habitaciones/{nombre}")
    Habitacion getHabitacion(@PathVariable String nombre) {
        return habitacionRepository.findById(nombre).orElseThrow(()-> new RecursoNoEncontradoException("No se encontró la habitación " + nombre + " en el sistema"));
    }


    //Actualizar Informacion de Habitacion
    @PutMapping("/habitaciones/{nombre}")
    public Habitacion updateHabitacion(@PathVariable String nombre, @RequestBody Habitacion actualizarHabitacion) {
        Habitacion habitacion = habitacionRepository.findById(nombre).
                orElseThrow(()-> new RecursoNoEncontradoException("No se encontró la habitación " + nombre + " en el sistema"));

        habitacion.setRegion(actualizarHabitacion.getRegion());
        habitacion.setDescripcion(actualizarHabitacion.getDescripcion());
        habitacion.setPrecio(actualizarHabitacion.getPrecio());
        habitacion.setTipo(actualizarHabitacion.getTipo());
        return habitacionRepository.save(habitacion);
    }

    //Eliminar Habitacion
    @DeleteMapping("/habitaciones/{nombre}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void eliminarHabitacion(@PathVariable String nombre){
        Habitacion habitacion = habitacionRepository.findById(nombre).
                orElseThrow(()-> new RecursoNoEncontradoException("No se encontró la habitación " + nombre + " en el sistema"));
        if (habitacion != null) {

            System.out.println("registro borrado existosamente");
        }
        habitacionRepository.delete(habitacion);


    }
}


