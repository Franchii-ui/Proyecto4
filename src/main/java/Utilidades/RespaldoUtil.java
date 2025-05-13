package Utilidades;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Controlador.ActividadControlador;
import Controlador.AnimalControlador;
import Granja.Actividad;
import Granja.Animal;

public class RespaldoUtil {

    private static final String NOMBRE_ARCHIVO_RESPALDO = "respaldo_granja_" + LocalDate.now().format(DateTimeFormatter.ISO_DATE) + ".txt";
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void generarRespaldo(AnimalControlador animalControlador, ActividadControlador actividadControlador) {
        try (FileWriter escritor = new FileWriter(NOMBRE_ARCHIVO_RESPALDO)) {
            escritor.write("--- RESPALDO DE LA GRANJA - " + LocalDateTime.now().format(FORMATO_FECHA_HORA) + " ---\n\n");

            // Respaldo de Animales
            escritor.write("--- ANIMALES ---\n");
            List<Animal> animales = obtenerTodosLosAnimales(animalControlador);
            for (Animal animal : animales) {
                escritor.write(animal.toString() + "\n");
            }
            escritor.write("\n");

            // Respaldo de Actividades
            escritor.write("--- ACTIVIDADES ---\n");
            List<Actividad> actividades = obtenerTodasLasActividades(actividadControlador);
            for (Actividad actividad : actividades) {
                escritor.write(actividad.toString() + "\n");
            }
            escritor.write("\n");

            System.out.println("Respaldo generado exitosamente en: " + NOMBRE_ARCHIVO_RESPALDO);

        } catch (IOException e) {
            System.err.println("Error al generar el respaldo: " + e.getMessage());
        }
    }

   private static List<Animal> obtenerTodosLosAnimales(AnimalControlador controlador) {
    return controlador.obtenerTodosLosAnimales();
}

private static List<Actividad> obtenerTodasLasActividades(ActividadControlador controlador) {
    return controlador.obtenerTodasLasActividades();
}
}