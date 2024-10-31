package org.example.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private int idHabitacion;
    private int numHabitacion;
    private int camasSimple;
    private int camasDoble;
    private boolean ocupada = false;
    private Hotel hotel;
    private TipoHabitacion tipoHabitacion;
    private List<Amenitie> amenitieList = new ArrayList<>();

    // Constructor
    public Habitacion(int idHabitacion, int numHabitacion, int camasSimple, int camasDoble, boolean ocupada, Hotel hotel, TipoHabitacion tipoHabitacion, List<Amenitie> amenitieList) {
        this.idHabitacion = idHabitacion;
        this.numHabitacion = numHabitacion;
        this.camasSimple = camasSimple;
        this.camasDoble = camasDoble;
        this.ocupada = ocupada;
        this.hotel = hotel;
        this.tipoHabitacion = tipoHabitacion;
        this.amenitieList = amenitieList;
    }
    public Habitacion(int numHabitacion, int camasSimple, int camasDoble, boolean ocupada, Hotel hotel, TipoHabitacion tipoHabitacion) {
        this.numHabitacion = numHabitacion;
        this.camasSimple = camasSimple;
        this.camasDoble = camasDoble;
        this.hotel = hotel;
        this.tipoHabitacion = tipoHabitacion;
    }

    // Getters y Setters
    public int getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }

    public int getnumHabitacion() { return numHabitacion; }
    public void setnumHabitacion(int numHabitacion) { this.numHabitacion = numHabitacion; }


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

    public List<Amenitie> getAmenitieList() { return amenitieList; }
    public void setAmenitieList(List<Amenitie> amenitieList) { this.amenitieList = amenitieList; }
}
