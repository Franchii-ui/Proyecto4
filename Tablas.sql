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