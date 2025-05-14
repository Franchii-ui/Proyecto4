# Proyecto de Gestión de Granja Digital

Sistema de gestión para una granja digital desarrollado en Java. Permite el registro, control y monitoreo de animales, empleados y actividades diarias, utilizando una base de datos MySQL para la persistencia de datos y archivos de texto para respaldos y logs.

## Inicialización del Proyecto

Para inicializar y ejecutar este proyecto en tu entorno local, sigue los siguientes pasos:

### Requisitos Previos

* **Java Development Kit (JDK):** Asegúrate de tener instalado Java en tu sistema. Se recomienda la versión 17 o superior. Puedes verificar tu instalación ejecutando `java -version` en la terminal.
* **Apache Maven:** El proyecto utiliza Maven para la gestión de dependencias y la construcción. Asegúrate de tener Maven instalado y configurado en tu sistema. Puedes verificar tu instalación ejecutando `mvn -v` en la terminal.
* **MySQL Server:** Necesitas tener un servidor MySQL instalado y en ejecución.

### Pasos de Inicialización

1.  **Clonar el Repositorio:** Si aún no lo has hecho, clona este repositorio a tu máquina local utilizando Git:

    ```bash
    git clone [URL_DEL_REPOSITORIO]
    cd [nombre_del_repositorio]
    ```

    Reemplaza `[URL_DEL_REPOSITORIO]` con la URL de tu repositorio en GitHub (https://github.com/Franchii-ui/Proyecto4) y `[nombre_del_repositorio]` con el nombre de la carpeta local donde se clonará el repositorio.

2.  **Configurar la Base de Datos MySQL:**
    * **Crear la Base de Datos:** Utiliza un cliente MySQL (como MySQL Workbench o la línea de comandos de MySQL) para crear una nueva base de datos llamada `granja_digital`. Puedes hacerlo ejecutando el siguiente comando SQL:

        ```sql
        CREATE DATABASE granja_digital;
        ```

    * **Crear las Tablas:** Ejecuta las siguientes sentencias SQL para crear las tablas necesarias (`animales` y `empleados`). Puedes copiar y pegar estos comandos en una ventana de consulta SQL conectada a tu base de datos `granja_digital`.

        ```sql
        CREATE TABLE animales (
            identificador_unico VARCHAR(255) PRIMARY KEY,
            especie VARCHAR(100) NOT NULL,
            raza VARCHAR(100),
            fecha_nacimiento DATE,
            estado_salud VARCHAR(255),
            ubicacion VARCHAR(255),
            estado VARCHAR(50) DEFAULT 'Activo'
        );

        CREATE TABLE empleados (
            id_empleado INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(255) NOT NULL,
            rol VARCHAR(100),
            telefono VARCHAR(20),
            fecha_contratacion DATE
        );

        CREATE TABLE actividades (
            id_actividad INT AUTO_INCREMENT PRIMARY KEY,
            fecha_hora DATETIME NOT NULL,
            empleado_responsable_id INT NOT NULL,
            tipo VARCHAR(100) NOT NULL,
            animales_involucrados_ids TEXT, -- Almacenaremos IDs separados por comas
            FOREIGN KEY (empleado_responsable_id) REFERENCES empleados(id_empleado)
        );
        ```

    * **Configurar las Credenciales de la Base de Datos:** Abre el archivo `ConexionBD.java` ubicado en `src/main/java/Utilidades/` y modifica las siguientes líneas con tus credenciales de acceso a MySQL:

        ```java
        private static final String URL = "jdbc:mysql://localhost:3306/granja_digital?serverTimezone=Europe/Madrid";
        private static final String USUARIO = "tu_usuario"; // Reemplaza con tu nombre de usuario de MySQL
        private static final String CONTRASEÑA = "tu_contraseña"; // Reemplaza con tu contraseña de MySQL
        ```
*En este caso solo tenemos el usuario root con su respectiva contraseña, crear en MySQL el root con la contraseña que prefieras para poder acceder*

3.  **Construir el Proyecto con Maven:** Navega a la raíz del proyecto en la terminal y ejecuta el siguiente comando para descargar las dependencias y construir el proyecto:

    ```bash
    mvn clean install
    ```

4.  **Ejecutar la Aplicación:** Una vez que la construcción sea exitosa, puedes ejecutar la aplicación principal (`ConsolaVista`) desde la terminal con el siguiente comando:

    ```bash
    mvn exec:java -D"exec.mainClass"="Vista.ConsolaVista"
    ```

    También puedes ejecutar la aplicación directamente desde tu IDE (como VS Code o Eclipse) configurando una ejecución para la clase `Vista.ConsolaVista`.

¡Ahora deberías poder ejecutar el sistema de gestión de la granja digital en tu entorno local!
# Proyecto4
