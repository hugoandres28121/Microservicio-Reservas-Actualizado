package com.reservaHabitaciones.microservicioReserva.repositories;


import com.reservaHabitaciones.microservicioReserva.models.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva,String> {
    List    <Reserva> findByNombreHabitacion    (String nombresHabitacion);
    List    <Reserva> findByUsername            (String username);

}
