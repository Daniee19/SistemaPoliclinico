package com.practicandoweb.sistemaclinica.modelo;

import java.sql.Blob;

public class Usuario {

    int id;
    String nombreUsuario;
    Blob contrasenia;
    String rol;

    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, Blob contrasenia, String rol) {
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

    public Blob getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(Blob contrasenia) {
        this.contrasenia = contrasenia;
    }

}
