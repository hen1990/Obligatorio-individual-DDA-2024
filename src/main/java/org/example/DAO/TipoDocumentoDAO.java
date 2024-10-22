package org.example.DAO;
import org.example.model.TipoDocumento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public TipoDocumento getTipoDocumentoById(int idTipoDocumento) {
        String query = "SELECT * FROM TipoDocumento WHERE idTipoDocumento = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idTipoDocumento);

        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");

                return new TipoDocumento(idTipoDocumento, nombre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<TipoDocumento> getAllTipoDocumento() {
        String query = "SELECT * FROM TipoDocumento";

        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<TipoDocumento> tipoDocumentoList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idTipoDocumento");
                String nombre = resultSet.getString("nombre");

                TipoDocumento tipoDocumento = new TipoDocumento(id, nombre);
                tipoDocumentoList.add(tipoDocumento);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tipoDocumentoList;
    }
}
