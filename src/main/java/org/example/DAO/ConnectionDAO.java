package org.example.DAO;
import java.sql.*;

public class ConnectionDAO {

    // Configuración de la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ObligatorioDDA"; // Cambiar esto a tu URL de base de datos
    private static final String DB_USER = "root"; // Cambiar esto a tu usuario de MySQL
    private static final String DB_PASSWORD = "mysqlhg"; // Cambiar esto a tu contraseña de MySQL

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public boolean executeUpdate(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            int rowsAffected = statement.executeUpdate();
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Ocurrió un problema al conectarse con la base de datos.");
            //ex.printStackTrace();
        }
        return false;
    }

    public ResultSet executeQuery(String query, Object... params) {

        ResultSet resultSet = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            resultSet = statement.executeQuery();

            return resultSet ;
        } catch (SQLException ex) {
            System.out.println("No se pudo obtener los datos.");
            ex.printStackTrace();
        }
        return resultSet;
    }
}
