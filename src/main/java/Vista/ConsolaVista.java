package Vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Controlador.ActividadControlador;
import Controlador.AnimalControlador;
import Controlador.EmpleadoControlador;
import Utilidades.InputHelper;

public class ConsolaVista {
    private final AnimalControlador animalControlador;
    private final EmpleadoControlador empleadoControlador;
    private final ActividadControlador actividadControlador;
    private AnimalVista animalVista;
    private EmpleadoVista empleadoVista;
    private ActividadVista actividadVista;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ConsolaVista(AnimalControlador animalControlador, EmpleadoControlador empleadoControlador, ActividadControlador actividadControlador) {
        this.animalControlador = animalControlador;
        this.empleadoControlador = empleadoControlador;
        this.actividadControlador = actividadControlador;
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de Animales");
            System.out.println("2. Gestión de Empleados");
            System.out.println("3. Registro de Actividades");
            System.out.println("4. Generar Respaldo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = InputHelper.leerEnteroPositivo("");

            switch (opcion) {
                case 1 -> {
                    if (animalVista == null) {
                        animalVista = new AnimalVista(animalControlador);
                    }
                    animalVista.mostrarMenuAnimales();
                }
                case 2 -> {
                    if (empleadoVista == null) {
                        empleadoVista = new EmpleadoVista(empleadoControlador);
                    }   
                    empleadoVista.mostrarMenuEmpleados();
                }
                case 3 -> {
                    if (actividadVista == null) {
                        actividadVista = new ActividadVista(actividadControlador);
                    }
                    actividadVista.mostrarMenuActividades();
                }
                case 4 -> Utilidades.RespaldoUtil.generarRespaldo(animalControlador, actividadControlador);
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // Métodos parseDate y parseDateTime (ahora protected)

    protected LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Useyyyy-MM-dd.");
            return null;
        }
    }

    protected LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha y hora inválido. Useyyyy-MM-dd HH:mm.");
            return null;
        }
    }

    public static void main(String[] args) {
        AnimalControlador animalControlador = new AnimalControlador();
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        ActividadControlador actividadControlador = new ActividadControlador();
        ConsolaVista vista = new ConsolaVista(animalControlador, empleadoControlador, actividadControlador);
        vista.mostrarMenuPrincipal();
    }
}