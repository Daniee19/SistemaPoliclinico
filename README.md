## Base de Datos
```sql
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
```
![usuario](https://github.com/user-attachments/assets/4e44d30c-453c-42d9-ac74-427be6be366c)
