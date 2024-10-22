package org.example.DAO;
import org.example.controller.PaisController;
import org.example.model.Ciudad;
import org.example.model.Pais;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    private final PaisController paisController = new PaisController();

    public Ciudad getCiudadById(int idCiudad) {
        String query = "SELECT * FROM Ciudad WHERE idCiudad = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idCiudad);

        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int idPais = resultSet.getInt("idPais");

                Pais pais = paisController.getPaisById(idPais);

                return new Ciudad(idCiudad, nombre, pais);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Ciudad> getAllCiudad() {
        String query = "SELECT * FROM Ciudad";
        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Ciudad> ciudadList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idCiudad");
                String nombre = resultSet.getString("nombre");
                int idPais = resultSet.getInt("idPais");

                Pais pais = paisController.getPaisById(idPais);
                Ciudad ciudad = new Ciudad(id, nombre, pais);
                ciudadList.add(ciudad);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ciudadList;
    }
}
