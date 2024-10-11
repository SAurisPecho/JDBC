SHOW databases;
CREATE DATABASE vivero2;
USE vivero2;
SHOW tables;

CREATE TABLE oficina (
    id_oficina INT PRIMARY KEY AUTO_INCREMENT,
    codigo_oficina VARCHAR(10) NOT NULL,
    ciudad VARCHAR(30) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    region VARCHAR(50),
    codigo_postal VARCHAR(10) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

describe oficina;

CREATE TABLE empleado (
    id_empleado INT PRIMARY KEY AUTO_INCREMENT,
    codigo_empleado INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    extension VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    id_oficina INT NOT NULL,
    id_jefe INT,
    puesto VARCHAR(50),
    FOREIGN KEY (id_oficina) REFERENCES oficina(id_oficina),
    FOREIGN KEY (id_jefe) REFERENCES empleado(id_empleado)
);

DESCRIBE empleado;

CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    codigo_cliente INT NOT NULL,
    nombre_cliente VARCHAR(50) NOT NULL,
    nombre_contacto VARCHAR(30),
    apellido_contacto VARCHAR(30),
    telefono VARCHAR(15) NOT NULL,
    fax VARCHAR(15) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    region VARCHAR(50),
    pais VARCHAR(50),
    codigo_postal VARCHAR(10),
    id_empleado INT,
    limite_credito DECIMAL(15, 2),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado)
);

DESCRIBE cliente;
show columns from cliente;

CREATE TABLE pago (
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    forma_pago VARCHAR(40) NOT NULL, 
    id_transaccion VARCHAR(50) NOT NULL,
    fecha_pago DATE NOT NULL,
    total DECIMAL(15, 2),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

DESCRIBE pago;

CREATE TABLE gama_producto (
    id_gama INT PRIMARY KEY AUTO_INCREMENT,
    gama VARCHAR(50) NOT NULL,
    descripcion_texto TEXT,
    descripcion_html TEXT,
    imagen VARCHAR(256)   
);

DESCRIBE gama_producto;

CREATE TABLE producto (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    codigo_producto VARCHAR(15) NOT NULL,
    nombre VARCHAR(70) NOT NULL,
    id_gama INT,
    dimensiones VARCHAR(25),
    proveedor VARCHAR(50),
    descripcion TEXT,
    cantidad_en_stock SMALLINT NOT NULL,
    precio_venta DECIMAL(15, 2) NOT NULL,
    precio_proveedor DECIMAL(15, 2),   
    FOREIGN KEY (id_gama) REFERENCES gama_producto(id_gama)
);

DESCRIBE producto;

CREATE TABLE pedido (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    codigo_pedido INT NOT NULL,
    fecha_pedido DATE NOT NULL,
    fecha_esperada DATE NOT NULL,
    fecha_entrega DATE,
    estado VARCHAR(15) NOT NULL, 
    comentarios TEXT,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

DESCRIBE pedido;

CREATE TABLE detalle_pedido (
    id_detalle_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unidad DECIMAL(15, 2) NOT NULL,
    numero_linea SMALLINT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

DESCRIBE detalle_pedido;