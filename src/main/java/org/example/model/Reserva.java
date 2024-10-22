package org.example.model;

public class Reserva {
    private int idReserva;
    private int cantidadPersonas;
    private String fechaReserva;
    private String fechaInicio;
    private String fechaFin;
    private String observacion;
    private Huesped huesped;  // Cambiado de idHuesped a Huesped

    // Constructor
    public Reserva(int idReserva, int cantidadPersonas, String fechaReserva, String fechaInicio, String fechaFin, String observacion, Huesped huesped) {
        this.idReserva = idReserva;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observacion = observacion;
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

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public Huesped getHuesped() { return huesped; }
    public void setHuesped(Huesped huesped) { this.huesped = huesped; }
}

