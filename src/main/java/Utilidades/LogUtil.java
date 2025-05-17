package Utilidades;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {
// Clase para registrar logs de actividades en la granja
    // Nombre del archivo de log
    // Se puede cambiar a una ruta absoluta si se desea
    private static final String NOMBRE_ARCHIVO_LOG = "registro_actividad.txt";
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void registrarLog(String mensaje) {
        LocalDateTime ahora = LocalDateTime.now();
        String marcaDeTiempo = ahora.format(FORMATO_FECHA_HORA);
        String lineaLog = "[" + marcaDeTiempo + "] " + mensaje + "\n";
        // Escribir el log en el archivo

        try (FileWriter escritor = new FileWriter(NOMBRE_ARCHIVO_LOG, true)) {
            escritor.write(lineaLog);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}