package org.example.DAO;

public class ReservaHabitacionDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public boolean insertReservaHabitacion(int idReserva, int idHabitacion) {
        String query = "INSERT INTO Reserva_Habi (idReserva, idHabitacion) VALUES (?, ?)";
        return connectionDAO.executeUpdate(query, idReserva, idHabitacion);
    }

    public boolean deleteReservaHabitacion(int idReserva, int idHabitacion) {
        String query = "DELETE FROM Reserva_Habi WHERE idReserva = ? AND idHabitacion = ?";
        return connectionDAO.executeUpdate(query, idReserva, idHabitacion);
    }
}
