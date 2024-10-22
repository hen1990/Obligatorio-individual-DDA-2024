package org.example.model;

public class HabiAmen {
    private Amenitie amenitie;  // Cambiado de idAmenitie a Amenitie
    private Habitacion habitacion;  // Cambiado de idHabitacion a Habitacion
    private boolean disponible;

    // Constructor
    public HabiAmen(Amenitie amenitie, Habitacion habitacion, boolean disponible) {
        this.amenitie = amenitie;
        this.habitacion = habitacion;
        this.disponible = disponible;
    }

    // Getters y Setters
    public Amenitie getAmenitie() { return amenitie; }
    public void setAmenitie(Amenitie amenitie) { this.amenitie = amenitie; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}

