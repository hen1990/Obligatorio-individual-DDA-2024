package org.example.DAO;

import org.example.model.Amenitie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmenitieHabitacionDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public boolean insertAmenitieHabitacion(int idAmenitie, int idHabitacion) {
        String query = "INSERT INTO Habi_Amen (" +
                " idAmenitie," +
                " idHabitacion)" +
                " VALUES (?, ?)";

        return connectionDAO.executeUpdate(query, idAmenitie, idHabitacion);
    }

    public boolean deleteAmenitieHabitacion(int idAmenitie, int idHabitacion) {
        String query = "DELETE FROM Habi_Amen WHERE idAmenitie = ? AND idHabitacion = ?";
        return connectionDAO.executeUpdate(query, idAmenitie, idHabitacion);
    }

    public boolean deleteAllAmenitieEnHabitacion(int idHabitacion) {
        String query = "DELETE FROM Habi_Amen WHERE idHabitacion = ?";
        return connectionDAO.executeUpdate(query, idHabitacion);
    }

    public List<Amenitie> getAmenitieNotInHabitacion(int idHabitacion) {
        String query = "SELECT * FROM Amenitie WHERE idAmenitie NOT IN ( " +
                " SELECT idAmenitie FROM Habi_Amen WHERE idHabitacion = ?)";

        ResultSet resultSet = connectionDAO.executeQuery(query, idHabitacion);
        List<Amenitie> amenitieList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idAmenitie");
                String nombre = resultSet.getString("nombre");

                Amenitie amenitie = new Amenitie(id, nombre);
                amenitieList.add(amenitie);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amenitieList;
    }

}
