package Utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
    // Método para leer un número entero positivo
    public static String leerStringNoVacio(String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Este campo no puede estar vacío. Intente de nuevo.");
            }
        } while (input.isEmpty());
        return input;
    }


    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    public static int leerEnteroPositivo(String mensaje) {
        int id;
        while (true) {
            try {
                System.out.print(mensaje);
                id = scanner.nextInt();
                if (id > 0) {
                    scanner.nextLine(); // Consumir la nueva línea
                    return id;
                } else {
                    System.out.println("Ingrese un valor positivo. Intente de nuevo.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un número entero. Intente de nuevo.");
                scanner.next(); // Descartar la entrada inválida
            }
            scanner.nextLine(); // Consumir la nueva línea en caso de error
        }
    }

    public static LocalDate leerLocalDate(String mensaje) {
        LocalDate fecha = null;
        do {
            System.out.print(mensaje);
            String fechaStr = scanner.nextLine().trim();
            if (!fechaStr.isEmpty()) {
                try {
                    fecha = LocalDate.parse(fechaStr, dateFormatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Useyyyy-MM-dd. Intente de nuevo.");
                }
            } else {
                fecha = null; // Permitir campo vacío si es opcional, manejar en la Vista si es obligatorio
                break;
            }
        } while (fecha == null && !mensaje.contains("opcional")); // Si es obligatorio, el bucle debe continuar
        return fecha;
    }

    public static LocalDateTime leerLocalDateTime(String mensaje) {
        LocalDateTime fechaHora = null;
        do {
            System.out.print(mensaje);
            String fechaHoraStr = scanner.nextLine().trim();
            if (!fechaHoraStr.isEmpty()) {
                try {
                    fechaHora = LocalDateTime.parse(fechaHoraStr, dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha y hora inválido. Useyyyy-MM-dd HH:mm. Intente de nuevo.");
                }
            } else {
                fechaHora = null; // Permitir campo vacío si es opcional, manejar en la Vista si es obligatorio
                break;
            }
        } while (fechaHora == null && !mensaje.contains("opcional")); // Si es obligatorio, el bucle debe continuar
        return fechaHora;
    }

    public static String leerTelefono(String mensaje) {
    String telefono;
    do {
        System.out.print(mensaje);
        telefono = scanner.nextLine().trim();
        if (!telefono.isEmpty() && !telefono.matches("\\d{1,9}")) {
            System.out.println("El teléfono debe contener hasta 9 dígitos numéricos. Intente de nuevo.");
        } else {
            break;
        }
    } while (true);
    return telefono;
}
public static Scanner obtenerScanner() {
        return scanner;
    }
}