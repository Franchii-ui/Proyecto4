package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Granja.Actividad;
import Utilidades.ConexionBD;
import Utilidades.LogUtil;

public class ActividadControlador {
// Método para registrar una actividad en la base de datos
    // Este método toma un objeto Actividad y lo inserta en la tabla de actividades
    // Se utiliza un PreparedStatement para evitar inyecciones SQL
    // Se registra un log de la actividad registrada
    // Se maneja la excepción SQLException para capturar errores de la base de datos
    public void registrarActividadEnBD(Actividad actividad) {
        String sql = "INSERT INTO actividades (fecha_hora, empleado_responsable_id, tipo, animales_involucrados_ids) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(actividad.getFechaHora()));
            pstmt.setInt(2, actividad.getEmpleadoResponsableId());
            pstmt.setString(3, actividad.getTipo());
            pstmt.setString(4, actividad.getAnimalesInvolucradosIds() != null ? String.join(",", actividad.getAnimalesInvolucradosIds()) : null);
            pstmt.executeUpdate();
            LogUtil.registrarLog("Actividad registrada en la base de datos: Tipo=" + actividad.getTipo() + ", EmpleadoID=" + actividad.getEmpleadoResponsableId() + ", Animales=" + actividad.getAnimalesInvolucradosIds());
            System.out.println("Actividad registrada en la base de datos.");
        } catch (SQLException e) {
            LogUtil.registrarLog("Error al registrar la actividad en la base de datos: Tipo=" + actividad.getTipo() + ", EmpleadoID=" + actividad.getEmpleadoResponsableId() + ", Animales=" + actividad.getAnimalesInvolucradosIds() + ": " + e.getMessage());
            System.err.println("Error al registrar la actividad en la base de datos: " + e.getMessage());
        }
    }
    // Método para obtener todas las actividades de la base de datos
    // Este método ejecuta una consulta SQL para seleccionar todas las actividades
    public List<Actividad> obtenerTodasLasActividades() {
    String sql = "SELECT id_actividad, fecha_hora, empleado_responsable_id, tipo, animales_involucrados_ids FROM actividades";
    List<Actividad> actividades = new ArrayList<>();
    try (Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement pstmt = conexion.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            String animalesInvolucrados = rs.getString("animales_involucrados_ids");
            Actividad actividad = new Actividad(rs.getInt("id_actividad"), rs.getTimestamp("fecha_hora").toLocalDateTime(),
                                              rs.getInt("empleado_responsable_id"), rs.getString("tipo"),
                                              animalesInvolucrados);
            actividades.add(actividad);
        }
        LogUtil.registrarLog("Se obtuvieron todas las actividades de la base de datos para el respaldo.");
    } catch (SQLException e) {
        LogUtil.registrarLog("Error al obtener todas las actividades para el respaldo: " + e.getMessage());
        System.err.println("Error al obtener todas las actividades para el respaldo: " + e.getMessage());
    }
    return actividades;
}
// Método para buscar una actividad por su ID
    // Este método toma un ID de actividad y ejecuta una consulta SQL para buscarla
    // Se utiliza un PreparedStatement para evitar inyecciones SQL
    // Se registra un log de la actividad consultada
    public Actividad buscarActividadPorId(int idActividad) {
        String sql = "SELECT fecha_hora, empleado_responsable_id, tipo, animales_involucrados_ids FROM actividades WHERE id_actividad = ?";
        Actividad actividad = null;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idActividad);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String animalesInvolucrados = rs.getString("animales_involucrados_ids");
                actividad = new Actividad(idActividad, rs.getTimestamp("fecha_hora").toLocalDateTime(),
                                          rs.getInt("empleado_responsable_id"), rs.getString("tipo"),
                                          animalesInvolucrados);
                LogUtil.registrarLog("Actividad con ID " + idActividad + " consultada de la base de datos.");
            } else {
                System.out.println("No se encontró ninguna actividad con el ID: " + idActividad);
                LogUtil.registrarLog("Intento de consultar actividad con ID " + idActividad + " fallido: no encontrada.");
            }
        } catch (SQLException e) {
            LogUtil.registrarLog("Error al buscar la actividad con ID " + idActividad + " en la base de datos: " + e.getMessage());
            System.err.println("Error al buscar la actividad en la base de datos: " + e.getMessage());
        }
        return actividad;
    }
}