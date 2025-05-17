package Granja;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Actividad {
    private int idActividad; // También podría ser autoincremental en la base de datos
    private LocalDateTime fechaHora;
    private int empleadoResponsableId; // Clave foránea a la tabla Empleados
    private String tipo;
    private List<String> animalesInvolucradosIds; // Lista de IDs de animales involucrados

    public Actividad(int idActividad, LocalDateTime fechaHora, int empleadoResponsableId, String tipo, String animalesInvolucrados) {
        this.idActividad = idActividad;
        this.fechaHora = fechaHora;
        this.empleadoResponsableId = empleadoResponsableId;
        this.tipo = tipo;
        this.animalesInvolucradosIds = convertirCadenaALista(animalesInvolucrados);
    }

    // Removed duplicate method to resolve the conflict

    public void setAnimalesInvolucradosIds(String animalesInvolucrados) {
        this.animalesInvolucradosIds = convertirCadenaALista(animalesInvolucrados);
    }

    private List<String> convertirCadenaALista(String cadena) {
        if (cadena != null && !cadena.isEmpty()) {
            return Arrays.stream(cadena.split(","))
                         .map(String::trim)
                         .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    // Getters y setters para todos los atributos
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getEmpleadoResponsableId() {
        return empleadoResponsableId;
    }

    public void setEmpleadoResponsableId(int empleadoResponsableId) {
        this.empleadoResponsableId = empleadoResponsableId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getAnimalesInvolucradosIds() {
        return animalesInvolucradosIds;
    }
//Convierte la lista de IDs de animales involucrados a una cadena separada por comas.
    public void setAnimalesInvolucradosIds(List<String> animalesInvolucradosIds) {
        this.animalesInvolucradosIds = animalesInvolucradosIds;
    }
//evuelve una representación en cadena de la actividad.
    @Override
    public String toString() {
        return "ID: " + idActividad + ", Fecha/Hora: " + fechaHora + ", Empleado ID: " + empleadoResponsableId +
               ", Tipo: " + tipo + ", Animales: " + animalesInvolucradosIds;
    }
}

