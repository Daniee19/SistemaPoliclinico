package com.practicandoweb.sistemaclinica.modelo;

import java.sql.Blob;

public class Usuario<T> {

    int id;
    String nombreUsuario;
    String contrasenia;
    String rol;
    Doctor doctor;
    Administrador admin;
    Secretario secretario;
    Farmaceutico farmaceutico;
    Paciente paciente;

    public Usuario() {
    }

    public Usuario(String rol) {

        switch (rol) {
            case "Administrador" -> {
                new Administrador();
            }
            case "Doctor" -> {
                new Doctor();
            }
            case "Secretario" -> {
                new Secretario();
            }
            case "Farmaceutico" -> {
                new Farmaceutico();
            }
            case "Paciente" -> {
                new Paciente();
            }
            default ->
                System.out.println("Error en escoger una clase");
        }
    }

    public Usuario(int id, String nombreUsuario, String contrasenia, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + ", rol=" + rol + '}';
    }

}
