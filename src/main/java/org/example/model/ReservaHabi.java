package org.example.model;

public class ReservaHabi {
    private Reserva reserva;  // Cambiado de idReserva a Reserva
    private Habitacion habitacion;  // Cambiado de idHabitacion a Habitacion

    // Constructor
    public ReservaHabi(Reserva reserva, Habitacion habitacion) {
        this.reserva = reserva;
        this.habitacion = habitacion;
    }

    // Getters y Setters
    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }
}
