package com.practicandoweb.sistemaclinica.controlador;

import com.practicandoweb.sistemaclinica.modelo.Dao;
import com.practicandoweb.sistemaclinica.modelo.Usuario;
import com.practicandoweb.sistemaclinica.modelo.daoclassinterface.UsuarioDao;
import com.practicandoweb.sistemaclinica.modelo.daoclassinterface.daoimpl.UsuarioDaoImpl;
import com.practicandoweb.sistemaclinica.vista.LoginFrame;
import com.practicandoweb.sistemaclinica.vista.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.sql.Blob;

public class RegisterControlador implements ActionListener {

    RegisterFrame rf;
    Dao<Usuario> daoUsuario;

    public RegisterControlador(RegisterFrame rf) {
        //Estoy haciendo una conexión global por medio de la interfaz mencionada en el main
        this.rf = rf;
        //Para hacer que ese componente funcione

//        this.view.btn
        rf.setVisible(true);
        rf.setLocationRelativeTo(null);
        agregarEventos();
    }

    public void agregarEventos() {
        rf.getBtnRegister().addActionListener(this);
        rf.getLblTieneCuenta().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rf.dispose();
                LoginFrame lf = new LoginFrame();
                LoginControlador lc = new LoginControlador(lf);

            }
        });
    }

    public void registrar() {
        daoUsuario = new UsuarioDaoImpl();
        Usuario usuario = new Usuario();
        UsuarioDao usu = new UsuarioDaoImpl();
        
        usuario.setNombreUsuario(rf.getTxtUsuario().getText());

        //Obtener la contraseña desde el passwordfield
//        char[] passwordChards = rf.getTxtContrasenia().getPassword();
//        String passwordString = new String(passwordChards);
//        usuario.setContrasenia();
        usuario.setRol(rf.getTxtRol().getText());
        usu.register(usuario, rf.getTxtContrasenia().getText());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rf.getBtnRegister()) {
            registrar();
        }

    }
}
