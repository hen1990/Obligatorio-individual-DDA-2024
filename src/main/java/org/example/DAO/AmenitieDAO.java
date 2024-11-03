package org.example.DAO;
import org.example.model.Amenitie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmenitieDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public Amenitie getTipoAmenitieById(int idAmenitie) {
        String query = "SELECT * FROM Amenitie WHERE idAmenitie = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idAmenitie);

        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");

                return new Amenitie(idAmenitie, nombre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Amenitie> getAllAmenitie() {
        String query = "SELECT * FROM Amenitie";

        ResultSet resultSet = connectionDAO.executeQuery(query);
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

    public List<Amenitie> getAmenitieByHabitacion(int idHabitacion) {
        String query = "SELECT a.* FROM Amenitie a INNER JOIN Habi_Amen ha " +
                "on a.idAmenitie = ha.idAmenitie WHERE ha.idHabitacion = ?";

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
