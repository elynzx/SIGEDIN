
package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/escuela_inclusiva";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";
    private static Connection conectar;

    public static synchronized Connection estableceConexion() {

        try {
            if (conectar == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conectar = DriverManager.getConnection(
                        URL,
                        USUARIO,
                        CONTRASENA);
                System.out.println("Conexion establecida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la BD: " + e.getMessage());
        }
        return conectar;
    }

    public static void cerrarConexion() throws SQLException {
        if (conectar != null && !conectar.isClosed()) {
            try {
                conectar.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }
        }
    }

}

