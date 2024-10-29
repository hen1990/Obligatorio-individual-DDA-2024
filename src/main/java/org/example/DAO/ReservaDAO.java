package org.example.DAO;
import org.example.controller.HabitacionController;
import org.example.controller.HuespedController;
import org.example.model.Habitacion;
import org.example.model.Huesped;
import org.example.model.Reserva;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    HuespedController huespedController = new HuespedController();
    HabitacionController habitacionController = new HabitacionController();

    public boolean insertReserva(Reserva reserva) {
        String query = "INSERT INTO Reserva (" +
                " cantidad_personas," +
                " fecha_reserva," +
                " tarifa," +
                " fecha_inicio," +
                " fecha_fin," +
                " idHabitacion," +
                " idHuesped)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        return connectionDAO.executeUpdate(query,
                reserva.getCantidadPersonas(),
                reserva.getFechaReserva(),
                reserva.getTarifa(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                reserva.getHabitacion().getIdHabitacion(),
                reserva.getHuesped().getIdHuesped());
    }

    public Reserva getReservaById(int idReserva) {
        String query = "SELECT * FROM Reserva WHERE idReserva = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idReserva);

        try {
            if (resultSet.next()) {
                int cantidadPersonas = resultSet.getInt("cantidad_personas");
                String fechaReserva = resultSet.getString("fecha_reserva");
                double monto = resultSet.getDouble("tarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                int idHabitacion = resultSet.getInt("idHabitacion");
                int idHuesped = resultSet.getInt("idHuesped");

                Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);
                Huesped huesped = huespedController.getHuespedById(idHuesped);
                return new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, habitacion, huesped);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Reserva> getReservaByHuesped(int idHuesped) {
        String query = "SELECT * FROM Reserva WHERE idHuesped = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHuesped);
        List<Reserva> reservaList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidad_personas");
                String fechaReserva = resultSet.getString("fecha_reserva");
                double monto = resultSet.getDouble("tarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                int idHabitacion = resultSet.getInt("idHabitacion");

                Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);
                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, habitacion, huesped);
                reservaList.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    public List<Reserva> getReservaByHotel(int idHotel) {
        String query = "SELECT * FROM Reserva r INNER JOIN Habitacion h " +
                "ON r.idHabitacion = h.idHabitacion INNER JOIN Hotel ho " +
                "ON h.idHotel = ho.idHotel " +
                "WHERE ho.idHotel = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHotel);
        List<Reserva> reservaList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidad_personas");
                String fechaReserva = resultSet.getString("fecha_reserva");
                double monto = resultSet.getDouble("tarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                int idHabitacion = resultSet.getInt("idHabitacion");
                int idHuesped = resultSet.getInt("idHuesped");

                Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);
                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, habitacion, huesped);
                reservaList.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    public List<Reserva> getReservaByFecha(String Inicio, String Fin) {
        String query = "SELECT * FROM Reserva " +
                "    WHERE  (? BETWEEN fecha_inicio AND fecha_fin)" +
                "        OR (? BETWEEN fecha_inicio AND fecha_fin)" +
                "        OR (fecha_inicio BETWEEN ? AND ?)" +
                "        OR (fecha_fin BETWEEN ? AND ?)";
        ResultSet resultSet = connectionDAO.executeQuery(query, Inicio, Fin, Inicio, Fin, Inicio, Fin);
        List<Reserva> reservaList = new ArrayList<>();

        try {
            if (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidad_personas");
                String fechaReserva = resultSet.getString("fecha_reserva");
                double monto = resultSet.getDouble("tarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                int idHabitacion = resultSet.getInt("idHabitacion");
                int idHuesped = resultSet.getInt("idHuesped");

                Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);
                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, habitacion, huesped);
                reservaList.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    public List<Reserva> getAllReserva(){
        String query = "SELECT * FROM Reserva";
        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Reserva> reservaList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidad_personas");
                String fechaReserva = resultSet.getString("fecha_reserva");
                double monto = resultSet.getDouble("tarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                int idHabitacion = resultSet.getInt("idHabitacion");
                int idHuesped = resultSet.getInt("idHuesped");

                Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);
                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, habitacion, huesped);
                reservaList.add(reserva);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    public boolean deleteReserva(int idReserva) {
        String query = "DELETE FROM Reserva WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, idReserva);
    }
}
