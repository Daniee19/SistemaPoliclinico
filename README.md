## Base de Datos
-- Crear base de datos
--------------------------------------
create database sistemapoliclinico;

-- Crear tablas
--------------------------------------
create table usuario(
id int AUTO_INCREMENTABLE,
nombreUsuario varchar(20),
contrasenia blob,
rol varchar(20),
primary key(id)
);
