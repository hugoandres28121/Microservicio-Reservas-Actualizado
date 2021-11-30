package com.reservaHabitaciones.microservicioReserva.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.reservaHabitaciones.microservicioReserva.exceptions.RecursoNoEncontradoException;
import com.reservaHabitaciones.microservicioReserva.models.Habitacion;
import com.reservaHabitaciones.microservicioReserva.models.Reserva;
import com.reservaHabitaciones.microservicioReserva.repositories.HabitacionRepository;
import com.reservaHabitaciones.microservicioReserva.repositories.ReservaRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@RestController
public class ReservaController {
    public final ReservaRepository reservaRepository;
    public final HabitacionRepository habitacionRepository;

    public ReservaController(ReservaRepository reservaRepository, HabitacionRepository habitacionRepository) {
        this.reservaRepository = reservaRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public static long diasEntreDosFechas(Date fechaDesde, Date fechaHasta){
        long startTime = fechaDesde.getTime() ;
        long endTime = fechaHasta.getTime();
        long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long dias = diasHasta - diasDesde;

        return dias;
    }

    @PostMapping("/reservas")
    public Reserva newReserva(@RequestBody Reserva reserva){

        Habitacion nameHabitacion=habitacionRepository.findById(reserva.getNombreHabitacion()).orElse(null);

        //Validando que  La Habitacion Exista Para poder crear La reserva

        if (nameHabitacion == null) {
            throw new RecursoNoEncontradoException("No se encontró la habitación " + reserva.getNombreHabitacion() + " en el sistema");
        }
        reserva.setFechaCreacionReserva(new Date());

        //Validaciones Iniciales

        //Validacion de Fechas de Ingreso y salida
        if(reserva.getFechaIngreso().after(reserva.getFechaSalida())){
            throw new RecursoNoEncontradoException("Fecha de Ingreso Es Mayor a Fecha de Salida, Porfavor valide");

        }

        if (reserva.getFechaIngreso().before(reserva.getFechaCreacionReserva())) {
            throw new RecursoNoEncontradoException("Fecha de Ingreso Invalida, la fecha  es anterior a la fecha actual");
        }

        else if (reserva.getFechaSalida().before(reserva.getFechaCreacionReserva())) {
            throw new RecursoNoEncontradoException("Fecha de Salida Invalida, la fecha es anterior a la fecha actual");
        }


        List <Reserva>listaReservas=this.reservaRepository.findByNombreHabitacion(reserva .getNombreHabitacion());
            if (listaReservas.isEmpty()){
                reserva.setNoches(diasEntreDosFechas(reserva.getFechaIngreso(),reserva.getFechaSalida()));
                System.out.println(reserva.getNoches());
                reserva.setValorReserva(nameHabitacion.getPrecio()*reserva.getNoches());
                System.out.println(reserva.getValorReserva());
                habitacionRepository.save(nameHabitacion);
            }
            else{
                //Inicio Foreach
                listaReservas.forEach(elemento->{
                    //Validacion rango de Fecha
                    if      ((reserva.getFechaIngreso()).after(elemento.getFechaIngreso())
                            &&reserva.getFechaIngreso().before(elemento.getFechaSalida())){

                        throw new RecursoNoEncontradoException("Fecha Reservada");

                    }
                    //Fechas de Reserva iguales a las fechas reservadas
                    else if(reserva.getFechaIngreso().compareTo(elemento.getFechaIngreso())==0
                            &&reserva.getFechaSalida().compareTo(elemento.getFechaSalida())==0){
                        throw new RecursoNoEncontradoException("Fecha Reservada");
                    }

                    //Fecha de Salida entre Reservas

                    else if (reserva.getFechaIngreso().before(elemento.getFechaIngreso())
                            &&reserva.getFechaSalida().after(elemento.getFechaIngreso())
                            &&reserva.getFechaSalida().before(elemento.getFechaSalida())){

                        throw new RecursoNoEncontradoException("Fecha Reservada");
                    }

                    //fecha de Salida igual a la fecha de Ingreso reserva
                    else  if ((reserva.getFechaSalida().compareTo(elemento.getFechaIngreso())==0)){
                        throw new RecursoNoEncontradoException("Fecha Reservada");
                    }

                    //fecha de Entrada igual a la fecha de salida reserva
                    else if(reserva.getFechaIngreso().compareTo(elemento.getFechaSalida())==0){
                        throw new RecursoNoEncontradoException("Fecha Reservada");

                    }
                    //Fecha de Salida Mayor fecha Ingreso Reservada
                    else if(reserva.getFechaSalida().after(elemento.getFechaIngreso())
                            &&reserva.getFechaSalida().before(elemento.getFechaSalida())){
                        throw new RecursoNoEncontradoException("Fecha Reservada");
                    }

                    //Fecha anterior al intervalo y mayor  a la fecha de salida de la reserva
                    else if(reserva.getFechaIngreso().before(elemento.getFechaIngreso())
                            &&reserva.getFechaSalida().after(elemento.getFechaSalida())){
                        throw new RecursoNoEncontradoException("Fecha Reservada");
                    }
                    //Guardar Cuando las Fecha de Entrada y Fecha de Salida sean el mismo Dia

                    else if(reserva.getFechaIngreso().compareTo(reserva.getFechaSalida())==0){

                        throw new RecursoNoEncontradoException("Reserva No Permitida");


                    }
                });

                //Fin Foreach
            }
        reserva.setNoches(diasEntreDosFechas(reserva.getFechaIngreso(),reserva.getFechaSalida()));
        System.out.println(reserva.getNoches());
        reserva.setValorReserva(nameHabitacion.getPrecio()*reserva.getNoches());
        System.out.println(reserva.getValorReserva());

            return reservaRepository.save(reserva);
            }

    @GetMapping("/reservas/{username}")
    List<Reserva> getReservaUser (@PathVariable String username){
        List<Reserva> habitacion = reservaRepository.findByUsername(username);
        if(habitacion.isEmpty()){
            throw  new RecursoNoEncontradoException("No Hay Reservas o este usuario no Existe");
        }
        return habitacion;
    }

    @DeleteMapping("/reservas/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void eliminarReserva(@PathVariable String id){
      Reserva  reserva=reservaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se encontró El ID " + id + "  en las Reservas"));

      reservaRepository.deleteById(id);
        System.out.println("Reserva Eliminada");

    }

    }

