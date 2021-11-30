package com.reservaHabitaciones.microservicioReserva.models;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;

public class Habitacion {

    @Id
    private String nombre;
    private String region;
    private String descripcion;
    private String tipo;
    private Boolean estado;
    private long precio;

    public Habitacion(String nombre, String region, String descripcion, String tipo, Boolean estado, long precio) {
        this.nombre = nombre;
        this.region = region;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.estado = estado;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }
}