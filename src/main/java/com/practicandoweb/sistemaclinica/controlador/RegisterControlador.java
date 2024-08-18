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
import javax.swing.JOptionPane;
//import java.sql.Blob;

public class RegisterControlador implements ActionListener {

    RegisterFrame rf;
    UsuarioDao daoUsuario;

    public RegisterControlador(RegisterFrame rf) {
        //Estoy haciendo una conexión global por medio de la interfaz mencionada en el main
        this.rf = rf;
        //Para hacer que ese componente funcione

//        this.view.btn
        rf.setVisible(true);
        rf.setLocationRelativeTo(null);
        agregarEventos();
        combobox();

    }

    public void combobox() {
        rf.getCbRol().addItem("<Seleccione un rol>");
        rf.getCbRol().addItem("Administrador");
        rf.getCbRol().addItem("Doctor");
        rf.getCbRol().addItem("Farmaceutico");
        rf.getCbRol().addItem("Secretario");
    }

    public void agregarEventos() {
        rf.getCbRol().addActionListener(this);
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

        usuario.setNombreUsuario(rf.getTxtUsuario().getText());

        //Verificacion del combobox
        String rolSeleccionado = rf.getCbRol().getSelectedItem().toString();

        if (!rolSeleccionado.equals("<Seleccione un rol>")) {
            usuario.setRol(rolSeleccionado);

            //Convertir de String a char (el tipo password tenemos que parsearlo a char[])
            char[] contraseniaChar = rf.getTxtContrasenia().getPassword();
            String contrasenia = new String(contraseniaChar);

            daoUsuario.register(usuario.getNombreUsuario(), contrasenia, rolSeleccionado);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un rol");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rf.getBtnRegister()) {
            registrar();
        }

    }
}
