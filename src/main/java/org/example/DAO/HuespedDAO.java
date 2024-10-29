package org.example.DAO;
import org.example.controller.PaisController;
import org.example.controller.TipoDocumentoController;
import org.example.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {
    private final ConnectionDAO connectionDAO = new ConnectionDAO();
    private final PaisController paisController = new PaisController();
    private final TipoDocumentoController tipoDocumentoController = new TipoDocumentoController();

    public boolean insertHuesped(Huesped huesped) {
        String query = "INSERT INTO Huesped (" +
                " nombre," +
                " ap_paterno," +
                " ap_materno," +
                " num_documento," +
                " fecha_nacimiento," +
                " telefono," +
                " idPais," +
                " idTipoDocumento)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return connectionDAO.executeUpdate(query,
                huesped.getNombre(),
                huesped.getApPaterno(),
                huesped.getApMaterno(),
                huesped.getNumDocumento(),
                huesped.getFechaNacimiento(),
                huesped.getTelefono(),
                huesped.getPais().getIdPais(),
                huesped.getTipoDocumento().getIdTipoDocumento());
    }

    public Huesped getHuespedById(int idHuesped) {
        String query = "SELECT * FROM Huesped WHERE idHuesped = ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, idHuesped);

        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombre");
                String apPaterno = resultSet.getString("ap_paterno");
                String apMaterno = resultSet.getString("ap_materno");
                String numDocumento = resultSet.getString("num_documento");
                String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais");
                int idTipoDocumento = resultSet.getInt("idTipoDocumento");

                Pais pais = paisController.getPaisById(idPais);
                TipoDocumento tipoDocumento = tipoDocumentoController.getTipoDocumentoById(idTipoDocumento);
                return new Huesped(id, nombre, apPaterno, apMaterno, numDocumento, fechaNacimiento, telefono, pais, tipoDocumento);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Huesped> getHuespedByNombre(String nombreHuesped) {
        String query = "SELECT * FROM Huesped WHERE nombre like ? OR ap_paterno like ? OR ap_materno like ?";
        ResultSet resultSet = connectionDAO.executeQuery(query, nombreHuesped, nombreHuesped, nombreHuesped);

        List<Huesped> huespedList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombre");
                String apPaterno = resultSet.getString("ap_paterno");
                String apMaterno = resultSet.getString("ap_materno");
                String numDocumento = resultSet.getString("num_documento");
                String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais");
                int idTipoDocumento = resultSet.getInt("idTipoDocumento");

                Pais pais = paisController.getPaisById(idPais);
                TipoDocumento tipoDocumento = tipoDocumentoController.getTipoDocumentoById(idTipoDocumento);
                Huesped huesped = new Huesped(id, nombre, apPaterno, apMaterno, numDocumento, fechaNacimiento, telefono, pais, tipoDocumento);
                huespedList.add(huesped);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedList;
    }

    public List<Huesped> getAllHuesped() {
        String query = "SELECT * FROM Huesped";

        ResultSet resultSet = connectionDAO.executeQuery(query);
        List<Huesped> huespedList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombre");
                String apPaterno = resultSet.getString("ap_paterno");
                String apMaterno = resultSet.getString("ap_materno");
                String numDocumento = resultSet.getString("num_documento");
                String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais");
                int idTipoDocumento = resultSet.getInt("idTipoDocumento");

                Pais pais = paisController.getPaisById(idPais);
                TipoDocumento tipoDocumento = tipoDocumentoController.getTipoDocumentoById(idTipoDocumento);

                Huesped huesped = new Huesped(id, nombre, apPaterno, apMaterno, numDocumento, fechaNacimiento, telefono, pais, tipoDocumento);
                huespedList.add(huesped);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedList;
    }

    public boolean deleteHuesped(int idHuesped) {
        String query = "DELETE FROM Huesped WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, idHuesped);
    }

    public boolean actualizarNombreHuesped(String nombre, int idHuesped) {
        String query = "update Huesped set nombre = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, nombre, idHuesped);
    }

    public boolean actualizarApPaternoHuesped(String apPaterno, int idHuesped) {
        String query = "update Huesped set ap_paterno = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, apPaterno, idHuesped);
    }

    public boolean actualizarApMaternoHuesped(String apMaterno, int idHuesped) {
        String query = "update Huesped set ap_materno = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, apMaterno, idHuesped);
    }

    public boolean actualizarNumDocumentoHuesped(String numDocumento, int idHuesped) {
        String query = "update Huesped set num_documento = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, numDocumento, idHuesped);
    }

    public boolean actualizarFechaNacHuesped(String fechaNac, int idHuesped) {
        String query = "update Huesped set fecha_nacimiento = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, fechaNac, idHuesped);
    }

    public boolean actualizarTelefonoHuesped(String telefono, int idHuesped) {
        String query = "update Huesped set telefono = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, telefono, idHuesped);
    }

    public boolean actualizarPaisHuesped(int idPais, int idHuesped) {
        String query = "update Huesped set idPais = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, idPais, idHuesped);
    }

    public boolean actualizarTipoDocumentoHuesped(int idTipoDocumento, int idHuesped) {
        String query = "update Huesped set idTipoDocumento = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, idTipoDocumento, idHuesped);
    }

}
