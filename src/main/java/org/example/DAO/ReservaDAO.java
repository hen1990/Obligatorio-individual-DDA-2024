package org.example.DAO;
import org.example.controller.HuespedController;
import org.example.model.Huesped;
import org.example.model.Reserva;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    HuespedController huespedController = new HuespedController();

    public boolean insertReserva(Reserva reserva) {
        String query = "INSERT INTO Reserva (" +
                " cantidad_personas," +
                " fecha_reserva," +
                " tarifa," +
                " fecha_inicio," +
                " fecha_fin," +
                " idHuesped)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        return connectionDAO.executeUpdate(query,
                reserva.getCantidadPersonas(),
                reserva.getFechaReserva(),
                reserva.getTarifa(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
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
                int idHuesped = resultSet.getInt("idHuesped");

                Huesped huesped = huespedController.getHuespedById(idHuesped);
                return new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, huesped);
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

                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, huesped);
                reservaList.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    public List<Reserva> getReservaByHabitacion(int idHabitacion) {
        String query = "SELECT r.* FROM Reserva r INNER JOIN ReservaHabitacion rh " +
                "ON r.idReserva = rh.idReserva WHERE rh.idHabitacion = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHabitacion);
        List<Reserva> reservaList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidad_personas");
                String fechaReserva = resultSet.getString("fecha_reserva");
                double monto = resultSet.getDouble("tarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                int idHuesped = resultSet.getInt("idHuesped");

                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, huesped);
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
                int idHuesped = resultSet.getInt("idHuesped");

                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, huesped);
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
                int idHuesped = resultSet.getInt("idHuesped");

                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, huesped);
                reservaList.add(reserva);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaList;
    }

    public Reserva getUltimaReserva() {
        String query = "SELECT * FROM Reserva ORDER BY idReserva DESC LIMIT 1";
        ResultSet resultSet = connectionDAO.executeQuery(query);

        try {
            if (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                int cantidadPersonas = resultSet.getInt("cantidad_personas");
                String fechaReserva = resultSet.getString("fecha_reserva");
                double monto = resultSet.getDouble("tarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                int idHuesped = resultSet.getInt("idHuesped");

                Huesped huesped = huespedController.getHuespedById(idHuesped);
                return new Reserva(idReserva, cantidadPersonas, fechaReserva, monto, fechaInicio, fechaFin, huesped);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean deleteReserva(int idReserva) {
        String query = "DELETE FROM Reserva WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, idReserva);
    }

    public boolean actualizarCantidadPersonas(int cantidadPersonas, int idReserva) {
        String query = "update Reserva set cantidad_personas = ? WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, cantidadPersonas, idReserva);
    }

    public boolean actualizarTarifa(double tarifa, int idReserva) {
        String query = "update Reserva set tarifa = ? WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, tarifa, idReserva);
    }

    public boolean actualizarFechaInicio(String fechaInicio, int idReserva) {
        String query = "update Reserva set fecha_inicio = ? WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, fechaInicio, idReserva);
    }

    public boolean actualizarFechaFin(String fechaFin, int idReserva) {
        String query = "update Reserva set fecha_fin = ? WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, fechaFin, idReserva);
    }

    public boolean actualizarHuesped(int idHuesped, int idReserva) {
        String query = "update Reserva set idHuesped = ? WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, idHuesped, idReserva);
    }
}
