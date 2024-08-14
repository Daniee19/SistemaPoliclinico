package com.practicandoweb.sistemaclinica.modelo;

import java.util.List;

public interface Dao<T> {

    List<T> listarTodo();

    T leerPorId(int id);

    void registrar(T registrar);

    void editar(T editar);

    void eliminar(int eliminar);

}
