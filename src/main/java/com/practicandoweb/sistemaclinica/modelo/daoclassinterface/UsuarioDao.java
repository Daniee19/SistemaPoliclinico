package com.practicandoweb.sistemaclinica.modelo.daoclassinterface;

import com.practicandoweb.sistemaclinica.modelo.Dao;

public interface UsuarioDao extends Dao {

    void register(String nombreUsuario, String contrasenia, String rol);

    boolean login(String nombreUsuario, String contrasenia);
}
