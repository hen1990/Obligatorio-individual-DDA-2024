package org.example.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private int idHabitacion;
    private int camasSimple;
    private int camasDoble;
    private boolean ocupada;
    private Hotel hotel;  // Cambiado de idHotel a Hotel
    private TipoHabitacion tipoHabitacion;  // Cambiado de idTipoHabitacion a TipoHabitacion
    private List<Amenitie> amenitieList = new ArrayList<>();

    // Constructor
    public Habitacion(int idHabitacion, int camasSimple, int camasDoble, boolean ocupada, Hotel hotel, TipoHabitacion tipoHabitacion, List<Amenitie> amenitieList) {
        this.idHabitacion = idHabitacion;
        this.camasSimple = camasSimple;
        this.camasDoble = camasDoble;
        this.ocupada = ocupada;
        this.hotel = hotel;
        this.tipoHabitacion = tipoHabitacion;
        this.amenitieList = amenitieList;
    }
    public Habitacion(int camasSimple, int camasDoble, boolean ocupada, Hotel hotel, TipoHabitacion tipoHabitacion) {
        this.camasSimple = camasSimple;
        this.camasDoble = camasDoble;
        this.ocupada = ocupada;
        this.hotel = hotel;
        this.tipoHabitacion = tipoHabitacion;
    }

    // Getters y Setters
    public int getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }

    public int getCamasSimple() { return camasSimple; }
    public void setCamasSimple(int camasSimple) { this.camasSimple = camasSimple; }

    public int getCamasDoble() { return camasDoble; }
    public void setCamasDoble(int camasDoble) { this.camasDoble = camasDoble; }

    public boolean isOcupada() { return ocupada; }
    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public TipoHabitacion getTipoHabitacion() { return tipoHabitacion; }
    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) { this.tipoHabitacion = tipoHabitacion; }
}
