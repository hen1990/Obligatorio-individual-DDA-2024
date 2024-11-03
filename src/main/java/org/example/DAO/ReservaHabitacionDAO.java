package org.example.DAO;

import org.example.controller.HabitacionController;
import org.example.controller.HuespedController;
import org.example.model.Reserva;

public class ReservaHabitacionDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    HabitacionController habitacionController = new HabitacionController();

    public boolean insertReservaHabitacion(int idReserva, int idHabitacion) {
        String query = "INSERT INTO ReservaHabitacion (" +
                " idReserva," +
                " idHabitacion)" +
                " VALUES (?, ?)";

        return connectionDAO.executeUpdate(query, idReserva, idHabitacion);
    }

    public boolean deleteReservaHabitacion(int idReserva) {
        String query = "DELETE FROM ReservaHabitacion WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, idReserva);
    }

    public boolean deleteReservaHabitacionByIdReservaIdHabitacion(int idReserva, int idHabitacion) {
        String query = "DELETE FROM ReservaHabitacion WHERE idReserva = ? AND idHabitacion = ?";
        return connectionDAO.executeUpdate(query, idReserva, idHabitacion);
    }
}
