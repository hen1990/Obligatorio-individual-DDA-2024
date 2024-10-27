package org.example.model;

public class Reserva {
    private int idReserva;
    private int cantidadPersonas;
    private String fechaReserva;
    private double tarifa;
    private String fechaInicio;
    private String fechaFin;
    private Habitacion habitacion;
    private Huesped huesped;

    // Constructor
    public Reserva(int idReserva, int cantidadPersonas, String fechaReserva, double tarifa, String fechaInicio, String fechaFin, Habitacion habitacion, Huesped huesped) {
        this.idReserva = idReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaReserva = fechaReserva;
        this.tarifa = tarifa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacion = habitacion;
        this.huesped = huesped;
    }

    public Reserva(int cantidadPersonas, String fechaReserva, double tarifa, String fechaInicio, String fechaFin, Habitacion habitacion, Huesped huesped) {
        this.cantidadPersonas = cantidadPersonas;
        this.fechaReserva = fechaReserva;
        this.tarifa = tarifa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacion = habitacion;
        this.huesped = huesped;
    }

    // Getters y Setters
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public int getCantidadPersonas() { return cantidadPersonas; }
    public void setCantidadPersonas(int cantidadPersonas) { this.cantidadPersonas = cantidadPersonas; }

    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fechaReserva) { this.fechaReserva = fechaReserva; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public double getTarifa() { return tarifa; }
    public void setTarifa(double tarifa) { this.tarifa = tarifa; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }
}

