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

    public List<Habitacion>  getHabitacionByHotel(int idHotel) {
        return  this.habitacionDAO.getHabitacionByHotel(idHotel);
    }

    public List<Habitacion> habitacionDisponiblePorCiudad(int idCiudad, String fechaInicio, String fechaFin){
        return this.habitacionDAO.habitacionDisponiblePorCiudad(idCiudad, fechaInicio, fechaFin);
    }

    public List<Habitacion> habitacionDisponiblePorHotel(int idHotel, String fechaInicio, String fechaFin){
        return this.habitacionDAO.habitacionDisponiblePorHotel(idHotel, fechaInicio, fechaFin);
    }
}
