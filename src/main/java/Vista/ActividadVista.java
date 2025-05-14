package Vista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        List<String> animalesInvolucradosIds = leerAnimalesInvolucrados();

        Actividad nuevaActividad = new Actividad(0, fechaHora, empleadoId, tipo, String.join(",", animalesInvolucradosIds));
        actividadControlador.registrarActividadEnBD(nuevaActividad);
    }

    private List<String> leerAnimalesInvolucrados() {
        List<String> animalesIds = new ArrayList<>();
        System.out.println("Ingrese los IDs de los animales involucrados (separados por comas, solo enteros):");
        String animalesStr = InputHelper.leerString("");
        if (!animalesStr.isEmpty()) {
            String[] ids = animalesStr.split(",");
            for (String id : ids) {
                String trimmedId = id.trim();
                if (!trimmedId.matches("\\d+")) {
                    System.out.println("ID de animal inválido: " + trimmedId + ". Solo se aceptan enteros.");
                    return new ArrayList<>(); // Devolver una lista vacía o manejar el error de otra manera
                }
                animalesIds.add(trimmedId);
            }
        }
        return animalesIds;
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