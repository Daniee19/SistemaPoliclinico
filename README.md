## Base de Datos
-- Crear base de datos
--------------------------------------
create database sistemapoliclinico;

-- Crear tablas
--------------------------------------
create table usuario(
id int AUTO_INCREMENT,
nombreUsuario varchar(20),
contrasenia blob,
rol varchar(20),
primary key(id)
);
