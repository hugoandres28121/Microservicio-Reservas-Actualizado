package com.reservaHabitaciones.microservicioReserva.exceptions;

public class RecursoNoEncontradoException extends RuntimeException{
    public RecursoNoEncontradoException(String message) {
        super (message);
    }
}
