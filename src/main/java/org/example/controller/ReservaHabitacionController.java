package org.example.controller;

import org.example.DAO.ReservaDAO;
import org.example.DAO.ReservaHabitacionDAO;
import org.example.model.Reserva;

public class ReservaHabitacionController {
    private final ReservaHabitacionDAO reservaHabitacionDAO = new ReservaHabitacionDAO();

    public boolean insertReservaHabitacion(int idReserva, int idHabitacion) {
        return  reservaHabitacionDAO.insertReservaHabitacion(idReserva, idHabitacion);
    }

    public boolean deleteReservaHabitacion(int idReserva) {
        return  reservaHabitacionDAO.deleteReservaHabitacion(idReserva);
    }

    public boolean deleteReservaHabitacionByIdReservaIdHabitacion(int idReserva, int idHabitacion) {
        return  reservaHabitacionDAO.deleteReservaHabitacionByIdReservaIdHabitacion(idReserva, idHabitacion);
    }
}
