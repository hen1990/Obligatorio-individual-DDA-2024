package org.example.DAO;
import org.example.controller.AmenitieController;
import org.example.controller.HotelController;
import org.example.controller.TipoHabitacionController;
import org.example.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    private final HotelController hotelController = new HotelController();
    private final TipoHabitacionController tipoHabitacionController = new TipoHabitacionController();
    AmenitieController amenitieController = new AmenitieController();

    public boolean insertHabitacion(Habitacion habitacion) {
        String query = "INSERT INTO Habitacion (numHabitacion, camas_simple, camas_doble, ocupada, idHotel, idTipoHabitacion) VALUES (?, ?, ?, ?, ?, ?)";

        return connectionDAO.executeUpdate(query,
                habitacion.getnumHabitacion(),
                habitacion.getCamasSimple(),
                habitacion.getCamasDoble(),
                habitacion.isOcupada(),
                habitacion.getHotel().getIdHotel(),
                habitacion.getTipoHabitacion().getIdTipoHabitacion());
    }

    public Habitacion getHabitacionById(int idHabitacion) {
        String query = "SELECT * FROM Habitacion WHERE idHabitacion = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHabitacion);

        try {
            if (resultSet.next()) {
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                return new Habitacion(idHabitacion, numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Habitacion> getAllHabitacionOcupadas(boolean habOcupada) {
        String query = "SELECT * FROM Habitacion WHERE ocupada = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, habOcupada);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion, numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public List<Habitacion> getAllHabitacion() {
        String query = "SELECT * FROM Habitacion";
        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion, numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public List<Habitacion> getHabitacionByHotel(int idHotel) {
        String query = "SELECT * FROM Habitacion WHERE idHotel = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHotel);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion, numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public List<Habitacion> habitacionDisponiblePorHotel(int idHotel){
        String query = "SELECT * FROM Habitacion WHERE idHotel = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHotel);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion,numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public List<Habitacion> habitacionDisponiblePorHotelFecha(int idHotel, String fechaInicio, String fechaFin){
        String query = "SELECT * FROM Habitacion h WHERE h.idHotel = ? " +
                "AND ocupada = 0 AND NOT EXISTS ( SELECT * " +
                "FROM ReservaHabitacion rh INNER JOIN Reserva r " +
                "ON rh.idReserva = r.idReserva " +
                "    WHERE rh.idHabitacion = h.idHabitacion AND ( " +
                "        (? BETWEEN r.fecha_inicio AND r.fecha_fin) " +
                "        OR (? BETWEEN r.fecha_inicio AND r.fecha_fin) " +
                "        OR (r.fecha_inicio BETWEEN ? AND ?) " +
                "        OR (r.fecha_fin BETWEEN ? AND ?) " +
                "    ) " +
                ")";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHotel, fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion,numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public List<Habitacion> habitacionDisponiblePorCiudad(int idCiudad, String fechaInicio, String fechaFin){
        String query = "SELECT * FROM Habitacion h INNER JOIN Hotel ho " +
                "ON h.idHotel = ho.idHotel WHERE ho.idCiudad = ? " +
                "AND ocupada = 0 AND NOT EXISTS ( SELECT * " +
                "FROM ReservaHabitacion rh INNER JOIN Reserva r " +
                "ON rh.idReserva = r.idReserva " +
                "    WHERE rh.idHabitacion = h.idHabitacion AND ( " +
                "        (? BETWEEN r.fecha_inicio AND r.fecha_fin) " +
                "        OR (? BETWEEN r.fecha_inicio AND r.fecha_fin) " +
                "        OR (r.fecha_inicio BETWEEN ? AND ?) " +
                "        OR (r.fecha_fin BETWEEN ? AND ?) " +
                "    ) " +
                ")";
        ResultSet resultSet = connectionDAO.executeQuery(query, idCiudad, fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion,numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public List<Habitacion> habitacionDisponiblePorFecha(String fechaInicio, String fechaFin){
        String query = "SELECT * FROM Habitacion h WHERE " +
                "ocupada = 0 AND NOT EXISTS ( SELECT * " +
                "FROM ReservaHabitacion rh INNER JOIN Reserva r " +
                "ON rh.idReserva = r.idReserva " +
                "    WHERE rh.idHabitacion = h.idHabitacion AND ( " +
                "        (? BETWEEN r.fecha_inicio AND r.fecha_fin) " +
                "        OR (? BETWEEN r.fecha_inicio AND r.fecha_fin) " +
                "        OR (r.fecha_inicio BETWEEN ? AND ?) " +
                "        OR (r.fecha_fin BETWEEN ? AND ?) " +
                "    ))";
        ResultSet resultSet = connectionDAO.executeQuery(query, fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion,numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public List<Habitacion> habitacionPorReserva(int idReserva){
        String query = "SELECT * FROM Habitacion h INNER JOIN ReservaHabitacion rh " +
        "ON h.idHabitacion = rh.idHabitacion INNER JOIN Reserva r " +
        "ON rh.idReserva = r.idReserva " +
        "WHERE r.idReserva = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idReserva);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("camas_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");

                List<Amenitie> amenitieList = amenitieController.getAmenitieByHabitacion(idHabitacion);
                Hotel hotel = hotelController.getHotelById(idHotel);
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);

                Habitacion habitacion = new Habitacion(idHabitacion,numHabitacion, camas_simple, cama_doble,
                        ocupada, hotel, tipoHabitacion, amenitieList);
                habitacionList.add(habitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitacionList;
    }

    public boolean actualizarNumeroHabitacion(int numeroHabitacion, int idHabitacion) {
        String query = "update Habitacion set numHabitacion = ? WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, numeroHabitacion, idHabitacion);
    }

    public boolean actualizarCamasSimples(int camasSimples, int idHabitacion) {
        String query = "update Habitacion set camas_simple = ? WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, camasSimples, idHabitacion);
    }

    public boolean actualizarCamasDobles(int camasDobles, int idHabitacion) {
        String query = "update Habitacion set camas_doble = ? WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, camasDobles, idHabitacion);
    }

    public boolean actualizarHotel(int idHotel, int idHabitacion) {
        String query = "update Habitacion set idHotel = ? WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, idHotel, idHabitacion);
    }

    public boolean actualizarTipoHabitacion(int tipoHabitacion, int idHabitacion) {
        String query = "update Habitacion set idTipoHabitacion = ? WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, tipoHabitacion, idHabitacion);
    }

    public boolean actualizarDisponibilidad(boolean ocupada, int idHabitacion) {
        String query = "update Habitacion set ocupada = ? WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, ocupada, idHabitacion);
    }

}
