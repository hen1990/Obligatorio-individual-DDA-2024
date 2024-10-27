package org.example.DAO;
import org.example.controller.TarifaController;
import org.example.model.Tarifa;
import org.example.model.TipoHabitacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    private final TarifaController tarifaController = new TarifaController();

    public TipoHabitacion getTipoHabitacionById(int idTipoHabitacion) {
        String query = "SELECT * FROM TipoHabitacion WHERE idTipoHabitacion = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idTipoHabitacion);

        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("tipo");
                int idTarifa = resultSet.getInt("idTarifa");
                Tarifa tarifa = tarifaController.getTarifaById(idTarifa);

                return new TipoHabitacion(idTipoHabitacion, nombre, tarifa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<TipoHabitacion> getAllTipoHabitacion() {
        String query = "SELECT * FROM TipoHabitacion";

        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<TipoHabitacion> tipoHabitacionList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idTipoHabitacion = resultSet.getInt("idTipoHabitacion");
                String nombre = resultSet.getString("tipo");
                int idTarifa = resultSet.getInt("idTarifa");
                Tarifa tarifa = tarifaController.getTarifaById(idTarifa);

                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHabitacion, nombre, tarifa);
                tipoHabitacionList.add(tipoHabitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tipoHabitacionList;
    }
}
