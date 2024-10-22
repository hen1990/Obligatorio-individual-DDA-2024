package org.example.DAO;

import org.example.controller.HotelController;
import org.example.controller.TipoHabitacionController;
import org.example.model.*;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    private final HotelController hotelController = new HotelController();
    private final TipoHabitacionController tipoHabitacionController = new TipoHabitacionController();

    public Habitacion getHabitacionById(int idHabitacion) {
        String query = "SELECT h.*, a.nombre " +
                "FROM Habitacion h INNER JOIN Habi_Amen ha " +
                "ON h.idHabitacion = ha.idHabitacion INNER JOIN Amenitie a " +
                "ON ha.idAmenitie = a.idAmenitie WHERE idHabitacion = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHabitacion);

        try {
            if (resultSet.next()) {
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("cama_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                List<Amenitie> amenitieList = (List<Amenitie>) resultSet.getArray("nombre");

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

    public List<Habitacion> getAllHabitacion() {
        String query = "SELECT h.*, a.nombre " +
                "FROM Habitacion h INNER JOIN Habi_Amen ha " +
                "ON h.idHabitacion = ha.idHabitacion INNER JOIN Amenitie a " +
                "ON ha.idAmenitie = a.idAmenitie";
        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("cama_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                 boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                List<Amenitie> amenitieList = (List<Amenitie>) resultSet.getArray("nombre");

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

    public List<Habitacion> habitacionDisponiblePorHotel(String fechaInicio, String fechaFin){
        String query = "SELECT h.*, a.nombre " +
                "FROM Habitacion h INNER JOIN Habi_Amen ha " +
                "ON h.idHabitacion = ha.idHabitacion INNER JOIN Amenitie a " +
                "ON ha.idAmenitie = a.idAmenitie INNER JOIN Hotel ho " +
                "ON h.idHotel = ho.idHotel " +
                "WHERE ho.idHotel = ? and ";
        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Habitacion> habitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int numHabitacion = resultSet.getInt("numHabitacion");
                int cama_doble = resultSet.getInt("cama_doble");
                int camas_simple = resultSet.getInt("camas_simple");
                boolean ocupada = resultSet.getBoolean("ocupada");
                int idHotel = resultSet.getInt("idHotel");
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                List<Amenitie> amenitieList = (List<Amenitie>) resultSet.getArray("nombre");

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
}
