package org.example.model;

public class Tarifa {
    private int idTarifa;
    private String fechaInicio;
    private String fechaFin;
    private double monto;

    // Constructor
    public Tarifa(int idTarifa, String fechaInicio, String fechaFin, double monto) {
        this.idTarifa = idTarifa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.monto = monto;
    }

    // Getters y Setters
    public int getIdTarifa() { return idTarifa; }
    public void setIdTarifa(int idTarifa) { this.idTarifa = idTarifa; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
}
