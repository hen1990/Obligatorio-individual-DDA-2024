package org.example.controller;
import org.example.DAO.ReservaHabitacionDAO;

public class ReservaHabitacionController {
    private final ReservaHabitacionDAO reservaHabitacionDAO = new ReservaHabitacionDAO();

    public boolean insertReservaHabitacion(int idReserva, int idHabitacion) {
        return  reservaHabitacionDAO.insertReservaHabitacion(idReserva, idHabitacion);
    }

    public boolean deleteReservaHabitacion(int idReserva, int idHabitacion) {
        return  reservaHabitacionDAO.deleteReservaHabitacion(idReserva, idHabitacion);
    }
}
