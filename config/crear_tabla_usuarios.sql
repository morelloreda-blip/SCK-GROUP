-- Script para crear la tabla de usuarios del servicio web
-- Proyecto: SCK-GROUP
-- Autor: Cindy Johana Ramirez

USE sck_group_db;

-- Tabla de usuarios para el login y registro
CREATE TABLE IF NOT EXISTS usuarios_api (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    clave VARCHAR(255) NOT NULL,
    perfil VARCHAR(20) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Usuario de prueba (clave: admin123)
-- La clave se guarda encriptada con MD5
INSERT INTO usuarios_api (nombre_usuario, clave, perfil)
VALUES ('admin', MD5('admin123'), 'ADMINISTRADOR');
