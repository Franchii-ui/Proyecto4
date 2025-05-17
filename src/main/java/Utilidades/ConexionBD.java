package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // URL de la base de datos, usuario y contraseña
    // Cambia estos valores según tu configuración de MySQL

    private static final String URL = "jdbc:mysql://localhost:3306/granja_digital?serverTimezone=Europe/Madrid";
    private static final String USUARIO = "root"; // Reemplaza con tu nombre de usuario de MySQL
    private static final String PASSWORD = "@POkemon_2301"; // Reemplaza con tu contraseña de MySQL
    private static Connection conexion;
// Singleton para la conexión a la base de datos
    // Se utiliza un patrón Singleton para asegurar que solo haya una conexión a la base de datos
    // en toda la aplicación, lo que mejora el rendimiento y la gestión de recursos
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
// Método para cerrar la conexión a la base de datos
    // Este método se utiliza para cerrar la conexión a la base de datos cuando ya no es necesaria
    // Es importante cerrar la conexión para liberar recursos y evitar fugas de memoria
    // Se utiliza un bloque try-catch para manejar posibles excepciones al cerrar la conexión
    // Se verifica si la conexión no es nula y no está cerrada antes de intentar cerrarla
    // Se imprime un mensaje de confirmación al cerrar la conexión
    public static void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión a la base de datos cerrada.");
        }
    }
// Método principal para probar la conexión a la base de datos
    // Este método se utiliza para probar la conexión a la base de datos
    // Se crea una conexión a la base de datos y se imprime un mensaje de éxito
    // Si ocurre una excepción, se imprime un mensaje de error
    // Finalmente, se cierra la conexión a la base de datos
    // Se utiliza un bloque try-catch para manejar excepciones al conectar y cerrar la conexión
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

