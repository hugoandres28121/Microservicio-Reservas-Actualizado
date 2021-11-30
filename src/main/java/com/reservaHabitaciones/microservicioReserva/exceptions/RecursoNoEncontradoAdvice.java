package com.reservaHabitaciones.microservicioReserva.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class RecursoNoEncontradoAdvice {
    @ResponseBody
    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String HabitacionOcupadaAdvice(RecursoNoEncontradoException ex) {
        return ex.getMessage();
    }
}
