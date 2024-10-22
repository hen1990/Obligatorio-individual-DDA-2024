package org.example.DAO;

import org.example.model.TipoDocumento;
import org.example.model.TipoHabitacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public TipoHabitacion getTipoHabitacionById(int idTipoHabitacion) {
        String query = "SELECT * FROM TipoHabitacion WHERE idTipoHabitacion = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idTipoHabitacion);

        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");

                return new TipoHabitacion(idTipoHabitacion, nombre);
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
                int id = resultSet.getInt("idTipoDocumento");
                String nombre = resultSet.getString("nombre");

                TipoHabitacion tipoHabitacion = new TipoHabitacion(id, nombre);
                tipoHabitacionList.add(tipoHabitacion);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tipoHabitacionList;
    }
}
