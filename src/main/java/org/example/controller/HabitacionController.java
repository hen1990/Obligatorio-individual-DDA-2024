package org.example.controller;
import org.example.DAO.HabitacionDAO;
import org.example.model.Habitacion;
import java.util.List;

public class HabitacionController {
    private final HabitacionDAO habitacionDAO = new HabitacionDAO();

    public boolean insertHabitacion(Habitacion habitacion) {
        return  this.habitacionDAO.insertHabitacion(habitacion);
    }
    public Habitacion getHabitacionById(int idHabitacion) {
        return  this.habitacionDAO.getHabitacionById(idHabitacion);
    }

    public List<Habitacion> getAllHabitacion(){
        return this.habitacionDAO.getAllHabitacion();
    }

    public List<Habitacion> getAllHabitacionOcupadas(boolean ocupada){
        return this.habitacionDAO.getAllHabitacionOcupadas(ocupada);
    }

    public List<Habitacion>  getHabitacionByHotel(int idHotel) {
        return  this.habitacionDAO.getHabitacionByHotel(idHotel);
    }

    public List<Habitacion> habitacionDisponiblePorCiudad(int idCiudad, String fechaInicio, String fechaFin){
        return this.habitacionDAO.habitacionDisponiblePorCiudad(idCiudad, fechaInicio, fechaFin);
    }

    public List<Habitacion> habitacionDisponiblePorHotel(int idHotel){
        return this.habitacionDAO.habitacionDisponiblePorHotel(idHotel);
    }

    public List<Habitacion> habitacionDisponiblePorHotelFecha(int idHotel, String fechaInicio, String fechaFin){
        return this.habitacionDAO.habitacionDisponiblePorHotelFecha(idHotel, fechaInicio, fechaFin);
    }

    public List<Habitacion> habitacionDisponiblePorFecha(String fechaInicio, String fechaFin){
        return this.habitacionDAO.habitacionDisponiblePorFecha(fechaInicio, fechaFin);
    }

    public List<Habitacion> habitacionPorReserva(int idReserva){
        return this.habitacionDAO.habitacionPorReserva(idReserva);
    }

    public boolean actualizarNumeroHabitacion(int numeroHabitacion, int idHabitacion) {
        return  this.habitacionDAO.actualizarNumeroHabitacion(numeroHabitacion, idHabitacion);
    }

    public boolean actualizarCamasSimples(int camasSimples, int idHabitacion) {
        return  this.habitacionDAO.actualizarCamasSimples(camasSimples, idHabitacion);
    }

    public boolean actualizarCamasDobles(int camasDobles, int idHabitacion) {
        return  this.habitacionDAO.actualizarCamasDobles(camasDobles, idHabitacion);
    }

    public boolean actualizarHotel(int idHotel, int idHabitacion) {
        return  this.habitacionDAO.actualizarHotel(idHotel, idHabitacion);
    }

    public boolean actualizarTipoHabitacion(int tipoHabitacion, int idHabitacion) {
        return  this.habitacionDAO.actualizarTipoHabitacion(tipoHabitacion, idHabitacion);
    }

    public boolean actualizarDisponibilidad(boolean ocupada, int idHabitacion) {
        return  this.habitacionDAO.actualizarDisponibilidad(ocupada, idHabitacion);
    }
}
