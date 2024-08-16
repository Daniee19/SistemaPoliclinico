package com.practicandoweb.sistemaclinica.modelo.daoclassinterface;

import com.practicandoweb.sistemaclinica.modelo.Dao;
import com.practicandoweb.sistemaclinica.modelo.Usuario;

public interface UsuarioDao extends Dao {

    void register(Usuario user, String contrasenia);
}
