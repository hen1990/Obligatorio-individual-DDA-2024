package org.example.model;

public class Amenitie {
    private int idAmenitie;
    private String nombre;

    // Constructor
    public Amenitie(int idAmenitie, String nombre) {
        this.idAmenitie = idAmenitie;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdAmenitie() { return idAmenitie; }
    public void setIdAmenitie(int idAmenitie) { this.idAmenitie = idAmenitie; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

