package Vista;

import java.time.LocalDate;

import Controlador.EmpleadoControlador;
import Granja.Empleado;
import Utilidades.InputHelper;

public class EmpleadoVista {
    private final EmpleadoControlador empleadoControlador;

    public EmpleadoVista(EmpleadoControlador empleadoControlador) {
        this.empleadoControlador = empleadoControlador;
    }

    public void mostrarMenuEmpleados() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Empleados ---");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Editar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Consultar Empleado");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = InputHelper.leerEnteroPositivo("");
            switch (opcion) {
                case 1 -> registrarNuevoEmpleado();
                case 2 -> editarEmpleadoExistente();
                case 3 -> eliminarEmpleadoExistente();
                case 4 -> consultarEmpleadoExistente();
                case 5 -> {
                }
                default -> System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void registrarNuevoEmpleado() {
        System.out.println("\n--- Registrar Nuevo Empleado ---");
        String nombre = InputHelper.leerStringNoVacio("Nombre: ");
        String rol = InputHelper.leerString("Rol: ");
        String telefono = InputHelper.leerTelefono("Teléfono (máximo 9 dígitos numéricos): ");
        LocalDate fechaContratacion = InputHelper.leerLocalDate("Fecha de Contratación (yyyy-MM-dd): ");
        if (fechaContratacion == null) return;

        Empleado nuevoEmpleado = new Empleado(0, nombre, rol, telefono, fechaContratacion);
        empleadoControlador.registrarEmpleadoEnBD(nuevoEmpleado);
    }

    private void editarEmpleadoExistente() {
        System.out.println("\n--- Editar Empleado Existente ---");
        int id = InputHelper.leerEnteroPositivo("Ingrese el ID del empleado a editar: ");
        Empleado empleadoExistente = empleadoControlador.buscarEmpleadoPorId(id);
        if (empleadoExistente != null) {
            System.out.print("Nuevo Nombre (" + empleadoExistente.getNombre() + "): ");
            String nombre = InputHelper.leerString("").isEmpty() ? empleadoExistente.getNombre() : InputHelper.leerString("");
            System.out.print("Nuevo Rol (" + empleadoExistente.getRol() + "): ");
            String rol = InputHelper.leerString("").isEmpty() ? empleadoExistente.getRol() : InputHelper.leerString("");
            System.out.print("Nuevo Teléfono (" + empleadoExistente.getTelefono() + ", máximo 9 dígitos numéricos): ");
            String telefono = InputHelper.leerTelefono("");
            if (telefono.isEmpty()) telefono = empleadoExistente.getTelefono();
            LocalDate fechaContratacion = InputHelper.leerLocalDate("Nueva Fecha de Contratación (yyyy-MM-dd) (" + empleadoExistente.getFechaContratacion() + "): ");

            Empleado empleadoActualizado = new Empleado(id, nombre, rol, telefono, fechaContratacion == null ? empleadoExistente.getFechaContratacion() : fechaContratacion);
            empleadoControlador.actualizarEmpleadoEnBD(empleadoActualizado);
        }
    }

    private void eliminarEmpleadoExistente() {
        System.out.println("\n--- Eliminar Empleado Existente ---");
        int id = InputHelper.leerEnteroPositivo("Ingrese el ID del empleado a eliminar: ");
        empleadoControlador.eliminarEmpleadoEnBD(id);
    }

    private void consultarEmpleadoExistente() {
        System.out.println("\n--- Consultar Empleado ---");
        int id = InputHelper.leerEnteroPositivo("Ingrese el ID del empleado a consultar: ");
        Empleado empleado = empleadoControlador.buscarEmpleadoPorId(id);
        if (empleado != null) {
            System.out.println(empleado);
        }
    }
}