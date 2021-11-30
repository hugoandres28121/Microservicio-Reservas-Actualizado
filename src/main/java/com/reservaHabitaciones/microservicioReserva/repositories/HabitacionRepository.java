package com.reservaHabitaciones.microservicioReserva.repositories;

import com.reservaHabitaciones.microservicioReserva.models.Habitacion;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface HabitacionRepository extends MongoRepository<Habitacion,String> {

}
