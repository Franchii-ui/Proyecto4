package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Granja.Animal;
import Utilidades.ConexionBD;
import Utilidades.LogUtil;

public class AnimalControlador {
// Método para registrar un animal en la base de datos
    // Este método toma un objeto Animal y lo inserta en la tabla de animales
    // Se utiliza un PreparedStatement para evitar inyecciones SQL
    // Se registra un log del animal registrado
    public void registrarAnimalEnBD(Animal animal) {
        String sql = "INSERT INTO animales (identificador_unico, especie, raza, fecha_nacimiento, estado_salud, ubicacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, animal.getIdentificadorUnico());
            pstmt.setString(2, animal.getEspecie());
            pstmt.setString(3, animal.getRaza());
            pstmt.setDate(4, Date.valueOf(animal.getFechaNacimiento()));
            pstmt.setString(5, animal.getEstadoSalud());
            pstmt.setString(6, animal.getUbicacion());
            pstmt.setString(7, animal.getEstado());
            pstmt.executeUpdate();
            LogUtil.registrarLog("Animal registrado en la base de datos con ID: " + animal.getIdentificadorUnico());
            System.out.println("Animal registrado en la base de datos con ID: " + animal.getIdentificadorUnico());
        } catch (SQLException e) {
            LogUtil.registrarLog("Error al registrar el animal en la base de datos con ID: " + animal.getIdentificadorUnico() + ": " + e.getMessage());
            System.err.println("Error al registrar el animal en la base de datos: " + e.getMessage());
        }
    }
// Método para buscar un animal por su ID
    // Este método toma un ID de animal y ejecuta una consulta SQL para buscarlo
    public Animal buscarAnimalPorId(String identificadorUnico) {
        String sql = "SELECT especie, raza, fecha_nacimiento, estado_salud, ubicacion, estado FROM animales WHERE identificador_unico = ?";
        Animal animal = null;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, identificadorUnico);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                animal = new Animal(identificadorUnico, rs.getString("especie"), rs.getString("raza"),
                                    rs.getDate("fecha_nacimiento").toLocalDate(), rs.getString("estado_salud"),
                                    rs.getString("ubicacion"));
            } else {
                System.out.println("No se encontró ningún animal con el ID: " + identificadorUnico);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el animal en la base de datos: " + e.getMessage());
        }
        return animal;
    }
    // Método para obtener todos los animales de la base de datos
    // Este método ejecuta una consulta SQL para seleccionar todos los animales
    // Se utiliza un PreparedStatement para evitar inyecciones SQL
    public List<Animal> obtenerTodosLosAnimales() {
    String sql = "SELECT identificador_unico, especie, raza, fecha_nacimiento, estado_salud, ubicacion, estado FROM animales";
    List<Animal> animales = new ArrayList<>();
    try (Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement pstmt = conexion.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
    Animal animal = new Animal(rs.getString("identificador_unico"), rs.getString("especie"),
                                rs.getString("raza"), rs.getDate("fecha_nacimiento").toLocalDate(),
                                rs.getString("estado_salud"), rs.getString("ubicacion"),
                                rs.getString("estado"));
    animales.add(animal);
    System.out.println("Animal leído de la base de datos: " + animal.getIdentificadorUnico()); // Añade esta línea
}
        LogUtil.registrarLog("Se obtuvieron todos los animales de la base de datos para el respaldo.");
    } catch (SQLException e) {
        LogUtil.registrarLog("Error al obtener todos los animales para el respaldo: " + e.getMessage());
        System.err.println("Error al obtener todos los animales para el respaldo: " + e.getMessage());
    }
    return animales;
}
// Método para actualizar un animal en la base de datos
    // Este método toma un objeto Animal y actualiza sus datos en la tabla de animales
    // Se utiliza un PreparedStatement para evitar inyecciones SQL
    public void actualizarAnimalEnBD(Animal animal) {
        String sql = "UPDATE animales SET especie = ?, raza = ?, fecha_nacimiento = ?, estado_salud = ?, ubicacion = ? WHERE identificador_unico = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, animal.getEspecie());
            pstmt.setString(2, animal.getRaza());
            pstmt.setDate(3, Date.valueOf(animal.getFechaNacimiento()));
            pstmt.setString(4, animal.getEstadoSalud());
            pstmt.setString(5, animal.getUbicacion());
            pstmt.setString(6, animal.getIdentificadorUnico());
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Animal con ID " + animal.getIdentificadorUnico() + " actualizado.");
            } else {
                System.out.println("No se pudo actualizar el animal con ID " + animal.getIdentificadorUnico() + ". Verifique si existe.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el animal en la base de datos: " + e.getMessage());
        }
    }
// Método para eliminar un animal de la base de datos
    // Este método toma un ID de animal y lo elimina de la tabla de animales
    public void eliminarAnimalEnBD(String identificadorUnico) {
        String sql = "DELETE FROM animales WHERE identificador_unico = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, identificadorUnico);
            int filasEliminadas = pstmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Animal con ID " + identificadorUnico + " eliminado de la base de datos.");
            } else {
                System.out.println("No se pudo eliminar el animal con ID " + identificadorUnico + ". Verifique si existe.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el animal de la base de datos: " + e.getMessage());
        }
    }
//Marcar animales como vendidos o fallecidos
    public void marcarAnimalComoVendidoEnBD(String identificadorUnico) {
        String sql = "UPDATE animales SET estado = 'Vendido' WHERE identificador_unico = ?";
        actualizarEstadoAnimal(identificadorUnico, sql, "vendido");
    }

    public void marcarAnimalComoFallecidoEnBD(String identificadorUnico) {
        String sql = "UPDATE animales SET estado = 'Fallecido' WHERE identificador_unico = ?";
        actualizarEstadoAnimal(identificadorUnico, sql, "fallecido");
    }
//Trasladar animales
    public void trasladarAnimalEnBD(String identificadorUnico, String nuevaUbicacion) {
        String sql = "UPDATE animales SET ubicacion = ? WHERE identificador_unico = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nuevaUbicacion);
            pstmt.setString(2, identificadorUnico);
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Animal con ID " + identificadorUnico + " trasladado a " + nuevaUbicacion + " en la base de datos.");
            } else {
                System.out.println("No se pudo trasladar el animal con ID " + identificadorUnico + ". Verifique si existe.");
            }
        } catch (SQLException e) {
            System.err.println("Error al trasladar el animal en la base de datos: " + e.getMessage());
        }
    }
//Actualizar estado de los animales
    private void actualizarEstadoAnimal(String identificadorUnico, String sql, String nuevoEstado) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, identificadorUnico);
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Animal con ID " + identificadorUnico + " marcado como " + nuevoEstado + " en la base de datos.");
            } else {
                System.out.println("No se pudo marcar el animal con ID " + identificadorUnico + ". Verifique si existe.");
            }
        } catch (SQLException e) {
            System.err.println("Error al marcar el animal como " + nuevoEstado + " en la base de datos: " + e.getMessage());
        }
    }
// Método para obtener el último identificador único de la tabla de animales
    // Este método ejecuta una consulta SQL para seleccionar el último ID
    // Se utiliza un PreparedStatement para evitar inyecciones SQL
    // Se registra un log del ID obtenido
   public String obtenerUltimoIdentificadorUnico() {
    String sql = "SELECT identificador_unico FROM animales ORDER BY identificador_unico DESC LIMIT 1";
    String ultimoId = null;
    try (Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement pstmt = conexion.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
            ultimoId = rs.getString("identificador_unico");
        }
    } catch (SQLException e) {
        LogUtil.registrarLog("Error al obtener el último identificador único: " + e.getMessage());
        System.err.println("Error al obtener el último identificador único: " + e.getMessage());
    }
    return ultimoId;
}
}