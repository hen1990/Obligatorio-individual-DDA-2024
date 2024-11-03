package org.example.DAO;
import org.example.model.Tarifa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();

    public Tarifa getTarifaById(int idTarifa) {
        String query = "SELECT * FROM Tarifa WHERE idTarifa = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idTarifa);

        try {
            if (resultSet.next()) {
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                double monto = resultSet.getDouble("monto");

                return new Tarifa(idTarifa, fechaInicio, fechaFin, monto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Tarifa> getAllTarifa(){

        String query = "SELECT * FROM Tarifa";

        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Tarifa> tarifaList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int idTarifa = resultSet.getInt("idTarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                double monto = resultSet.getDouble("monto");

                Tarifa tarifa = new Tarifa (idTarifa, fechaInicio, fechaFin, monto);
                tarifaList.add(tarifa);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tarifaList;
    }

    public Tarifa getTarifaByHabitacion(int idHabitacion){

        String query = "select t.* from Tarifa t inner join TipoHabitacion th " +
                "on t.idTarifa = th.idTarifa inner join Habitacion h " +
                "on th.idTipoHabitacion = h.idTipoHabitacion " +
                "where idHabitacion = ?";

        ResultSet resultSet = connectionDAO.executeQuery(query, idHabitacion);

        try {
            if (resultSet.next()) {
                int idTarifa = resultSet.getInt("idTarifa");
                String fechaInicio = resultSet.getString("fecha_inicio");
                String fechaFin = resultSet.getString("fecha_fin");
                double monto = resultSet.getDouble("monto");

                return new Tarifa(idTarifa, fechaInicio, fechaFin, monto);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
