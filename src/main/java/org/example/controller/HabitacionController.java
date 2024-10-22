package org.example.controller;
import org.example.DAO.HabitacionDAO;
import org.example.model.Habitacion;
import java.util.List;

public class HabitacionController {
    private final HabitacionDAO habitacionDAO = new HabitacionDAO();

    public Habitacion getHabitacionById(int idHabitacion) {
        return  this.habitacionDAO.getHabitacionById(idHabitacion);
    }

    public List<Habitacion> getAllHabitacion(){

        return this.habitacionDAO.getAllHabitacion();
    }
}
