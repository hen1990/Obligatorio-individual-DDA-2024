package org.example.DAO;
import org.example.model.Pais;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public Pais getPaisById(int idPais) {
        String query = "SELECT * FROM Pais WHERE idPais = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idPais);

        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");

                return new Pais(idPais, nombre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Pais> getAllPais() {
        String query = "SELECT * FROM Pais";

        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Pais> paisList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idPais");
                String nombre = resultSet.getString("nombre");

                Pais pais = new Pais(id, nombre);
                paisList.add(pais);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paisList;
    }
}
