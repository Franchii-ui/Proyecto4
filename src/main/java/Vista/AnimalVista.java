package Vista;

import java.time.LocalDate;

import Controlador.AnimalControlador; // Importa InputHelper
import Granja.Animal;
import Utilidades.InputHelper;

public class AnimalVista {
    private final AnimalControlador animalControlador;
    public AnimalVista(AnimalControlador animalControlador) {
        this.animalControlador = animalControlador;
    }

    public void mostrarMenuAnimales() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Animales ---");
            System.out.println("1. Registrar Animal");
            System.out.println("2. Editar Animal");
            System.out.println("3. Eliminar Animal");
            System.out.println("4. Consultar Animal");
            System.out.println("5. Marcar como Vendido");
            System.out.println("6. Marcar como Fallecido");
            System.out.println("7. Trasladar Animal");
            System.out.println("8. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = InputHelper.leerEnteroPositivo(""); // Usar InputHelper para leer entero positivo

            switch (opcion) {
                case 1 -> registrarNuevoAnimal();
                case 2 -> editarAnimalExistente();
                case 3 -> eliminarAnimalExistente();
                case 4 -> consultarAnimalExistente();
                case 5 -> marcarVendido();
                case 6 -> marcarFallecido();
                case 7 -> trasladarAnimal();
                case 8 -> {
                }
                default -> System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 8);
    }

    private void registrarNuevoAnimal() {
        System.out.println("\n--- Registrar Nuevo Animal ---");
        String id = InputHelper.leerStringNoVacio("Identificador Único: ");
        String especie = InputHelper.leerStringNoVacio("Especie: ");
        String raza = InputHelper.leerString("Raza: ");
        LocalDate fechaNacimiento = InputHelper.leerLocalDate("Fecha de Nacimiento (yyyy-MM-dd): ");
        String estadoSalud = InputHelper.leerString("Estado de Salud: ");
        String ubicacion = InputHelper.leerString("Ubicación: ");

        Animal nuevoAnimal = new Animal(id, especie, raza, fechaNacimiento, estadoSalud, ubicacion);
        animalControlador.registrarAnimalEnBD(nuevoAnimal);
    }

    private void editarAnimalExistente() {
        System.out.println("\n--- Editar Animal Existente ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal a editar: ");
        Animal animalExistente = animalControlador.buscarAnimalPorId(id);
        if (animalExistente != null) {
            System.out.print("Nueva Especie (" + animalExistente.getEspecie() + "): ");
            String especie = InputHelper.leerString("").isEmpty() ? animalExistente.getEspecie() : InputHelper.leerString("");
            System.out.print("Nueva Raza (" + animalExistente.getRaza() + "): ");
            String raza = InputHelper.leerString("").isEmpty() ? animalExistente.getRaza() : InputHelper.leerString("");
            LocalDate fechaNacimiento = InputHelper.leerLocalDate("Nueva Fecha de Nacimiento (yyyy-MM-dd) (" + animalExistente.getFechaNacimiento() + "): ");
            System.out.print("Nuevo Estado de Salud (" + animalExistente.getEstadoSalud() + "): ");
            String estadoSalud = InputHelper.leerString("").isEmpty() ? animalExistente.getEstadoSalud() : InputHelper.leerString("");
            System.out.print("Nueva Ubicación (" + animalExistente.getUbicacion() + "): ");
            String ubicacion = InputHelper.leerString("").isEmpty() ? animalExistente.getUbicacion() : InputHelper.leerString("");

            Animal animalActualizado = new Animal(id, especie, raza, fechaNacimiento == null ? animalExistente.getFechaNacimiento() : fechaNacimiento, estadoSalud, ubicacion, animalExistente.getEstado());
            animalControlador.actualizarAnimalEnBD(animalActualizado);
        }
    }

    private void eliminarAnimalExistente() {
        System.out.println("\n--- Eliminar Animal Existente ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal a eliminar: ");
        animalControlador.eliminarAnimalEnBD(id);
    }

    private void consultarAnimalExistente() {
        System.out.println("\n--- Consultar Animal ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal a consultar: ");
        Animal animal = animalControlador.buscarAnimalPorId(id);
        if (animal != null) {
            System.out.println(animal);
        }
    }

    private void marcarVendido() {
        System.out.println("\n--- Marcar Animal como Vendido ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal: ");
        animalControlador.marcarAnimalComoVendidoEnBD(id);
    }

    private void marcarFallecido() {
        System.out.println("\n--- Marcar Animal como Fallecido ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal: ");
        animalControlador.marcarAnimalComoFallecidoEnBD(id);
    }

    private void trasladarAnimal() {
        System.out.println("\n--- Trasladar Animal ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal: ");
        String nuevaUbicacion = InputHelper.leerString("Ingrese la nueva ubicación: ");
        animalControlador.trasladarAnimalEnBD(id, nuevaUbicacion);
    }
}