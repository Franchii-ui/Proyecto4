package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/granja_digital?serverTimezone=Europe/Madrid";
    private static final String USUARIO = "root"; // Reemplaza con tu nombre de usuario de MySQL
    private static final String PASSWORD = "@POkemon_2301"; // Reemplaza con tu contraseña de MySQL
    private static Connection conexion;

    public static Connection obtenerConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                // Cargar el driver JDBC de MySQL (a partir de la versión 8.0, no es estrictamente necesario)
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexión a la base de datos exitosa.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Error al cargar el driver JDBC de MySQL: " + e.getMessage());
            }
        }
        return conexion;
    }

    public static void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión a la base de datos cerrada.");
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = ConexionBD.obtenerConexion();
            if (conn != null) {
                System.out.println("Prueba de conexión exitosa.");
                ConexionBD.cerrarConexion();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar o cerrar la base de datos: " + e.getMessage());
        }
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}

