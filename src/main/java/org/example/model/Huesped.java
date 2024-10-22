package org.example.model;

public class Huesped {
    private int idHuesped;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String numDocumento;
    private String fechaNacimiento;
    private String telefono;
    private Pais pais;  // Cambiado de idPais a Pais
    private TipoDocumento tipoDocumento;  // Cambiado de idTipoDocumento a TipoDocumento

    // Constructor
    public Huesped(int idHuesped, String nombre, String apPaterno, String apMaterno, String numDocumento, String fechaNacimiento, String telefono, Pais pais, TipoDocumento tipoDocumento) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.numDocumento = numDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.pais = pais;
        this.tipoDocumento = tipoDocumento;
    }
    public Huesped(String nombre, String apPaterno, String apMaterno, String numDocumento, String fechaNacimiento, String telefono, Pais pais, TipoDocumento tipoDocumento) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.numDocumento = numDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.pais = pais;
        this.tipoDocumento = tipoDocumento;
    }

    // Getters y Setters
    public int getIdHuesped() { return idHuesped; }
    public void setIdHuesped(int idHuesped) { this.idHuesped = idHuesped; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApPaterno() { return apPaterno; }
    public void setApPaterno(String apPaterno) { this.apPaterno = apPaterno; }

    public String getApMaterno() { return apMaterno; }
    public void setApMaterno(String apMaterno) { this.apMaterno = apMaterno; }

    public String getNumDocumento() { return numDocumento; }
    public void setNumDocumento(String numDocumento) { this.numDocumento = numDocumento; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }

    public TipoDocumento getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(TipoDocumento tipoDocumento) { this.tipoDocumento = tipoDocumento; }
}
