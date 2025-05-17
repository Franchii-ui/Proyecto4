package Granja;

import java.time.LocalDate;

public class Empleado {
    private final int idEmpleado;
    private String nombre;
    private String rol;
    private String telefono;
    private LocalDate fechaContratacion;
// Constructor que permite establecer todos los atributos del empleado
    // Se puede usar para crear un nuevo empleado o para actualizar uno existente
    public Empleado(int idEmpleado, String nombre, String rol, String telefono, LocalDate fechaContratacion) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.rol = rol;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
    }
    // Getters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }


    @Override
    public String toString() {
        return "ID: " + idEmpleado + ", Nombre: " + nombre + ", Rol: " + rol +
               ", Teléfono: " + telefono + ", Contratación: " + fechaContratacion;
    }
}