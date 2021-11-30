package com.reservaHabitaciones.microservicioReserva.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Reserva {

    @Id
    private String id;
    private String username;
    private String nombreHabitacion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaSalida;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private  Date fechaCreacionReserva;
    private long noches;
    private long valorReserva;


    public Reserva(String id, String username, String nombreHabitacion, Date fechaIngreso, Date fechaSalida, Date fechaCreacionReserva, long noches, long valorReserva) {
        this.id = id;
        this.username = username;
        this.nombreHabitacion = nombreHabitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.fechaCreacionReserva = fechaCreacionReserva;
        this.noches = noches;
        this.valorReserva = valorReserva;
    }

    public long getNoches() {
        return noches;
    }

    public void setNoches(long noches) {
        this.noches = noches;
    }

    public long getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(long valorReserva) {
        this.valorReserva = valorReserva;
    }

    public Date getFechaCreacionReserva() {
        return fechaCreacionReserva;
    }

    public void setFechaCreacionReserva(Date fechaCreacionReserva) {
        this.fechaCreacionReserva = fechaCreacionReserva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreHabitacion() {
        return nombreHabitacion;
    }

    public void setNombreHabitacion(String nombreHabitacion) {
        this.nombreHabitacion = nombreHabitacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

}
