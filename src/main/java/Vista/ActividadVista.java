package Vista;

import java.time.LocalDateTime;

import Controlador.ActividadControlador;
import Granja.Actividad;
import Utilidades.InputHelper;

public class ActividadVista {
    private final ActividadControlador actividadControlador;

    public ActividadVista(ActividadControlador actividadControlador) {
        this.actividadControlador = actividadControlador;
    }

    public void mostrarMenuActividades() {
        int opcion;
        do {
            System.out.println("\n--- Registro de Actividades ---");
            System.out.println("1. Registrar Actividad");
            System.out.println("2. Consultar Actividad");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = InputHelper.leerEnteroPositivo("");
            switch (opcion) {
                case 1 -> registrarNuevaActividad();
                case 2 -> consultarActividadExistente();
                case 3 -> {
                }
                default -> System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 3);
    }

    private void registrarNuevaActividad() {
        System.out.println("\n--- Registrar Nueva Actividad ---");
        LocalDateTime fechaHora = InputHelper.leerLocalDateTime("Fecha y Hora (yyyy-MM-dd HH:mm): ");
        if (fechaHora == null) return;
        int empleadoId = InputHelper.leerEnteroPositivo("ID del Empleado Responsable: ");
        String tipo = InputHelper.leerStringNoVacio("Tipo de Actividad: ");
        String animalesStr = InputHelper.leerString("IDs de Animales Involucrados (separados por comas, si aplica): ");

        Actividad nuevaActividad = new Actividad(0, fechaHora, empleadoId, tipo, animalesStr);
        actividadControlador.registrarActividadEnBD(nuevaActividad);
    }

    private void consultarActividadExistente() {
        System.out.println("\n--- Consultar Actividad ---");
        int id = InputHelper.leerEnteroPositivo("Ingrese el ID de la actividad a consultar: ");
        Actividad actividad = actividadControlador.buscarActividadPorId(id);
        if (actividad != null) {
            System.out.println(actividad);
        }
    }
}