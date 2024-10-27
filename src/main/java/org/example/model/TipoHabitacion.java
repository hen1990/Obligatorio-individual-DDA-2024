package org.example.model;

public class TipoHabitacion {
    private int idTipoHabitacion;
    private String tipo;
    private Tarifa tarifa;

    public TipoHabitacion(int idTipoHabitacion, String tipo, Tarifa tarifa) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.tipo = tipo;
        this.tarifa = tarifa;
    }

    public int getIdTipoHabitacion() { return idTipoHabitacion; }
    public void setIdTipoHabitacion(int idTipoHabitacion) { this.idTipoHabitacion = idTipoHabitacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Tarifa getTarifa() { return tarifa; }
    public void setTarifa(Tarifa tarifa) { this.tarifa = tarifa; }
}
