package org.example.controller;
import org.example.DAO.TipoHabitacionDAO;
import org.example.model.TipoHabitacion;
import java.util.List;

public class TipoHabitacionController {
    private final TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();

    public TipoHabitacion getTipoHabitacionById(int idTipoHabitacion) {
        return tipoHabitacionDAO.getTipoHabitacionById(idTipoHabitacion);
    }

    public List<TipoHabitacion> getAllTipoHabitacion(){

        return this.tipoHabitacionDAO.getAllTipoHabitacion();
    }
}
