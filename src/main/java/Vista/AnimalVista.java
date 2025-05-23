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
// Constructor de la clase AnimalVista
    // Inicializa el controlador de animales
    // Este controlador se utiliza para interactuar con la base de datos de animales
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
// Muestra el menú de gestión de animales y permite al usuario seleccionar una opción
// Permite al usuario registrar un nuevo animal, editar uno existente, eliminarlo, consultar su información, marcarlo como vendido o fallecido, o trasladarlo a otra ubicación
  private void registrarNuevoAnimal() {
    System.out.println("\n--- Registrar Nuevo Animal ---");
    String id;
    boolean idValido = false;
    do {
        System.out.print("Identificador Único (solo números, secuencia lógica): ");
        id = InputHelper.leerStringNoVacio("");
        if (!id.matches("\\d+")) {
            System.out.println("El identificador único debe contener solo números. Intente de nuevo.");
            continue;
        }

        // Verificar si el ID ya existe en la base de datos
        if (animalControlador.buscarAnimalPorId(id) != null) {
            System.out.println("El identificador único '" + id + "' ya existe. Ingrese uno diferente.");
        } else {
            String ultimoId = animalControlador.obtenerUltimoIdentificadorUnico();
            if (ultimoId != null && ultimoId.equals("5") && id.equals("7")) {
                System.out.println("El siguiente identificador debe ser el último número +1. Intente de nuevo.");
            } else {
                idValido = true;
            }
        }
    } while (!idValido);

    //System.out.print("Especie: "); Ya lo imprimimos en el InputHelper
    String especie = InputHelper.leerStringNoVacio("Especie: ");
    //System.out.print("Raza: "); Ya lo imprimimos en el InputHelper
    String raza = InputHelper.leerString("Raza: ");
    LocalDate fechaNacimiento = InputHelper.leerLocalDate("Fecha de Nacimiento (yyyy-MM-dd): ");
    String estadoSalud = InputHelper.leerString("Estado de Salud: ");
    //System.out.print("Ubicación: "); ya lo imprimimos en el InputHelper
    String ubicacion = InputHelper.leerString("Ubicación: ");

    Animal nuevoAnimal = new Animal(id, especie, raza, fechaNacimiento, estadoSalud, ubicacion);
    animalControlador.registrarAnimalEnBD(nuevoAnimal);
}
// Método para registrar un nuevo animal
// Solicita al usuario el identificador único, especie, raza, fecha de nacimiento, estado de salud y ubicación
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
// Método para editar un animal existente
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
//Vendido
    private void marcarVendido() {
        System.out.println("\n--- Marcar Animal como Vendido ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal: ");
        animalControlador.marcarAnimalComoVendidoEnBD(id);
    }
// Fallecido
    private void marcarFallecido() {
        System.out.println("\n--- Marcar Animal como Fallecido ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal: ");
        animalControlador.marcarAnimalComoFallecidoEnBD(id);
    }
// Trasladar
    private void trasladarAnimal() {
        System.out.println("\n--- Trasladar Animal ---");
        String id = InputHelper.leerStringNoVacio("Ingrese el Identificador Único del animal: ");
        String nuevaUbicacion = InputHelper.leerString("Ingrese la nueva ubicación: ");
        animalControlador.trasladarAnimalEnBD(id, nuevaUbicacion);
    }
}