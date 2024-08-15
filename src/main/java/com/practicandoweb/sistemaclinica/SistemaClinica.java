package com.practicandoweb.sistemaclinica;

import com.practicandoweb.sistemaclinica.controlador.LoginControlador;
import com.practicandoweb.sistemaclinica.vista.LoginFrame;

public class SistemaClinica {

    public static void main(String[] args) {
        LoginFrame login = new LoginFrame();
        LoginControlador log = new LoginControlador(login);
    }
}
