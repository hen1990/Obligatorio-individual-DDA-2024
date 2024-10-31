package org.example.DAO;
import org.example.controller.CiudadController;
import org.example.model.Ciudad;
import org.example.model.Hotel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    private final CiudadController ciudadController = new CiudadController();

    public boolean insertHotel(Hotel hotel) {
        String query = "INSERT INTO Hotel (nombre, idCiudad, direccion, estrellas) VALUES (?, ?, ?, ?)";

        return connectionDAO.executeUpdate(query, hotel.getNombre(), hotel.getCiudad().getIdCiudad(), hotel.getDireccion(), hotel.getEstrellas());
    }

    public Hotel getHotelById(int idHotel) {
        String query = "SELECT * FROM Hotel WHERE idHotel = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHotel);

        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("idHotel");
                String nombre = resultSet.getString("nombre");
                int idCiudad = resultSet.getInt("idCiudad");
                String direccion = resultSet.getString("direccion");
                int estrellas = resultSet.getInt("estrellas");

                Ciudad ciudad = ciudadController.getCiudadById(idCiudad);
                return new Hotel(id, nombre, ciudad, direccion, estrellas);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Hotel> getAllHotel() {
        String query = "SELECT * FROM Hotel";

        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Hotel> hotelList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idHotel");
                String nombre = resultSet.getString("nombre");
                int idCiudad = resultSet.getInt("idCiudad");
                String direccion = resultSet.getString("direccion");
                int estrellas = resultSet.getInt("estrellas");

                Ciudad ciudad = ciudadController.getCiudadById(idCiudad);
                Hotel hotel = new Hotel(id, nombre, ciudad, direccion, estrellas);
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public List<Hotel> getHotelByNombre(String buscarNombre) {
        String query = "SELECT * FROM Hotel WHERE nombre like ?";

        ResultSet resultSet = connectionDAO.executeQuery(query, buscarNombre);
        List<Hotel> hotelList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idHotel");
                String nombre = resultSet.getString("nombre");
                int idCiudad = resultSet.getInt("idCiudad");
                String direccion = resultSet.getString("direccion");
                int estrellas = resultSet.getInt("estrellas");

                Ciudad ciudad = ciudadController.getCiudadById(idCiudad);
                Hotel hotel = new Hotel(id, nombre, ciudad, direccion, estrellas);
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public List<Hotel> getHotelByCiudad(int buscarIdCiudad){
        String query = "SELECT * FROM Hotel WHERE idCiudad = ?";

        ResultSet resultSet = connectionDAO.executeQuery(query, buscarIdCiudad);
        List<Hotel> hotelList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idHotel");
                String nombre = resultSet.getString("nombre");
                int idCiudad = resultSet.getInt("idCiudad");
                String direccion = resultSet.getString("direccion");
                int estrellas = resultSet.getInt("estrellas");

                Ciudad ciudad = ciudadController.getCiudadById(idCiudad);
                Hotel hotel = new Hotel(id, nombre, ciudad, direccion, estrellas);
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public List<Hotel> getHotelByEstrellas(int buscarEstrellas){
        String query = "SELECT * FROM Hotel WHERE estrellas >= ?";

        ResultSet resultSet = connectionDAO.executeQuery(query, buscarEstrellas);
        List<Hotel> hotelList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idHotel");
                String nombre = resultSet.getString("nombre");
                int idCiudad = resultSet.getInt("idCiudad");
                String direccion = resultSet.getString("direccion");
                int estrellas = resultSet.getInt("estrellas");

                Ciudad ciudad = ciudadController.getCiudadById(idCiudad);
                Hotel hotel = new Hotel(id, nombre, ciudad, direccion, estrellas);
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public boolean deleteHotel(int idHotel) {
        String query = "DELETE FROM Hotel WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, idHotel);
    }

    public boolean deleteHabitacionesDeUnHotel(int idHotel) {
        String query = "DELETE FROM Habitacion WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, idHotel);
    }

    public boolean deleteAmenitiesDeHabitacionesDeUnHotel(int idHabitacion) {
        String query = "DELETE FROM Habi_Amen WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, idHabitacion);
    }

    public boolean actualizarNombreHotel(String nombre, int idHotel) {
        String query = "update Hotel set nombre = ? WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, nombre, idHotel);
    }

    public boolean actualizarCiudadHotel(int idCiudad, int idHotel) {
        String query = "update Hotel set idCiudad = ? WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, idCiudad, idHotel);
    }

    public boolean actualizarDireccionHotel(String direccion, int idHotel) {
        String query = "update Hotel set direccion = ? WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, direccion, idHotel);
    }

    public boolean actualizarEstrellasHotel(int estrellas, int idHotel) {
        String query = "update Hotel set estrellas = ? WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, estrellas, idHotel);
    }
}
