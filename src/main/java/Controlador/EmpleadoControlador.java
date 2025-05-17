package Controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Granja.Empleado;
import Utilidades.ConexionBD;
import Utilidades.LogUtil;

public class EmpleadoControlador {
//Resgistrar un nuevo empleado en la base de datos
    public void registrarEmpleadoEnBD(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, rol, telefono, fecha_contratacion) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getRol());
            pstmt.setString(3, empleado.getTelefono());
            pstmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            pstmt.executeUpdate();
            LogUtil.registrarLog("Empleado registrado en la base de datos: Nombre=" + empleado.getNombre() + ", Rol=" + empleado.getRol());
            System.out.println("Empleado registrado en la base de datos: " + empleado.getNombre());
        } catch (SQLException e) {
            LogUtil.registrarLog("Error al registrar el empleado en la base de datos: Nombre=" + empleado.getNombre() + ", Rol=" + empleado.getRol() + ": " + e.getMessage());
            System.err.println("Error al registrar el empleado en la base de datos: " + e.getMessage());
        }
    }
// Método para buscar un empleado por su ID
    public Empleado buscarEmpleadoPorId(int idEmpleado) {
    String sql = "SELECT nombre, rol, telefono, fecha_contratacion FROM empleados WHERE id_empleado = ?";
    Empleado empleado = null;
    try (Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement pstmt = conexion.prepareStatement(sql)) {
        pstmt.setInt(1, idEmpleado);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            empleado = new Empleado(idEmpleado, rs.getString("nombre"), rs.getString("rol"),
                                    rs.getString("telefono"), rs.getDate("fecha_contratacion").toLocalDate());
        } else {
            System.out.println("No se encontró ningún empleado con el ID: " + idEmpleado);
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar el empleado en la base de datos: " + e.getMessage());
    }
    return empleado;
}
//ACtualizar un empleado en la base de datos
    public void actualizarEmpleadoEnBD(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, rol = ?, telefono = ?, fecha_contratacion = ? WHERE id_empleado = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getRol());
            pstmt.setString(3, empleado.getTelefono());
            pstmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            pstmt.setInt(5, empleado.getIdEmpleado());
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                LogUtil.registrarLog("Empleado con ID " + empleado.getIdEmpleado() + " actualizado en la base de datos.");
                System.out.println("Empleado con ID " + empleado.getIdEmpleado() + " actualizado.");
            } else {
                System.out.println("No se pudo actualizar el empleado con ID " + empleado.getIdEmpleado() + ". Verifique si existe.");
                LogUtil.registrarLog("Intento de actualizar empleado con ID " + empleado.getIdEmpleado() + " fallido: no encontrado.");
            }
        } catch (SQLException e) {
            LogUtil.registrarLog("Error al actualizar el empleado con ID " + empleado.getIdEmpleado() + " en la base de datos: " + e.getMessage());
            System.err.println("Error al actualizar el empleado en la base de datos: " + e.getMessage());
        }
    }
//Eliminar un empleado de la base de datos
    public void eliminarEmpleadoEnBD(int idEmpleado) {
        String sql = "DELETE FROM empleados WHERE id_empleado = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idEmpleado);
            int filasEliminadas = pstmt.executeUpdate();
            if (filasEliminadas > 0) {
                LogUtil.registrarLog("Empleado con ID " + idEmpleado + " eliminado de la base de datos.");
                System.out.println("Empleado con ID " + idEmpleado + " eliminado de la base de datos.");
            } else {
                System.out.println("No se pudo eliminar el empleado con ID " + idEmpleado + ". Verifique si existe.");
                LogUtil.registrarLog("Intento de eliminar empleado con ID " + idEmpleado + " fallido: no encontrado.");
            }
        } catch (SQLException e) {
            LogUtil.registrarLog("Error al eliminar el empleado con ID " + idEmpleado + " de la base de datos: " + e.getMessage());
            System.err.println("Error al eliminar el empleado de la base de datos: " + e.getMessage());
        }
    }
}