USE railway;

CREATE TABLE inventario (
    codigo VARCHAR(5) PRIMARY KEY,
    descripcion VARCHAR(100),
    stock INT
);

CREATE TABLE empleados (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    tipo INT
);