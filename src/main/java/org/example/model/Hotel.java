package org.example.model;

public class Hotel {
    private int idHotel;
    private String nombre;
    private Ciudad ciudad;
    private String direccion;
    private int estrellas;

    public Hotel(int idHotel, String nombre, Ciudad ciudad, String direccion, int estrellas) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.estrellas = estrellas;
    }
    public Hotel(String nombre, Ciudad ciudad, String direccion, int estrellas) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.estrellas = estrellas;
    }

    // Getters y Setters
    public int getIdHotel() { return idHotel; }
    public void setIdHotel(int idHotel) { this.idHotel = idHotel; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }
}

