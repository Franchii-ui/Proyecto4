package Granja;

import java.time.LocalDate;
//Representa un animal en la granja
// Contiene información como identificador único, especie, raza, fecha de nacimiento, estado de salud y ubicación
public class Animal {
    private String identificadorUnico;
    private String especie;
    private String raza;
    private LocalDate fechaNacimiento;
    private String estadoSalud;
    private String ubicacion;
    private String estado; // Vendido, Fallecido, Trasladado, etc.

    public Animal(String identificadorUnico, String especie, String raza, LocalDate fechaNacimiento, String estadoSalud, String ubicacion) {
        this(identificadorUnico, especie, raza, fechaNacimiento, estadoSalud, ubicacion, "Activo");
    }
// Constructor adicional para incluir el estado
    // Constructor que permite establecer todos los atributos del animal
    // Se puede usar para crear un nuevo animal o para actualizar uno existente
    public Animal(String identificadorUnico, String especie, String raza, LocalDate fechaNacimiento, String estadoSalud, String ubicacion, String estado) {
        this.identificadorUnico = identificadorUnico;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoSalud = estadoSalud;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    // Getters y setters para todos los atributos
    public String getIdentificadorUnico() {
        return identificadorUnico;
    }

    public void setIdentificadorUnico(String identificadorUnico) {
        this.identificadorUnico = identificadorUnico;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
// Método toString para representar el objeto Animal como una cadena
    // Este método es útil para mostrar información del animal en la consola
    // o para registrar información en un archivo de log
    // Se puede personalizar para mostrar solo los atributos que se deseen
    @Override
    public String toString() {
        return "ID: " + identificadorUnico + ", Especie: " + especie + ", Raza: " + raza +
               ", Nacimiento: " + fechaNacimiento + ", Salud: " + estadoSalud + ", Ubicación: " + ubicacion +
               ", Estado: " + estado;
    }
}

