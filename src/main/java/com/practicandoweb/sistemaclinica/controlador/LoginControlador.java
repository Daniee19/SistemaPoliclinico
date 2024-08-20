package com.practicandoweb.sistemaclinica.controlador;

import com.practicandoweb.sistemaclinica.modelo.Usuario;
import com.practicandoweb.sistemaclinica.modelo.daoclassinterface.UsuarioDao;
import com.practicandoweb.sistemaclinica.modelo.daoclassinterface.daoimpl.UsuarioDaoImpl;
import com.practicandoweb.sistemaclinica.vista.AspectoWindows;
import com.practicandoweb.sistemaclinica.vista.LoginFrame;
import com.practicandoweb.sistemaclinica.vista.RegisterFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;

//import java.util.List;
//import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LoginControlador implements ActionListener {

    LoginFrame view = new LoginFrame();
    Usuario usuario = new Usuario();

    //No se llama en la instancia al Dao<Usuario> porque lo que queremos evitar definir los metodos del CRUD en esta clase Controlador
    UsuarioDao usuDao = new UsuarioDaoImpl();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public LoginControlador(LoginFrame view) {
        //Estoy haciendo una conexión global por medio de la interfaz mencionada en el main
        this.view = view;
        //Para hacer que ese componente funcione

//        this.view.btn
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        AspectoWindows.AspectoW();
        agregarEventos();
    }

    public void agregarEventos() {
        this.view.getBtnLogin().addActionListener(this);
        this.view.getLblCrearCuenta().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // Maneja el clic en el JLabel
                goRegistrarse();
            }

        });
    }

    public void btn() {
        System.out.println("Hola que tal");
        JOptionPane.showMessageDialog(null, "Hello World");
        usuDao.listarTodo();
    }

    public void verificarUsuario() {

        char[] pass = view.getTxtContrasenia().getPassword();
        String contrasenia = new String(pass);

        boolean existeUsuario = usuDao.login(view.getTxtUsuario().getText(), contrasenia);
        
        if(existeUsuario){
            JOptionPane.showMessageDialog(null, "Bienvenido");
        }else{
            JOptionPane.showMessageDialog(null, "Error: No existe el usuario");
        }
    }

    public void goRegistrarse() {
        view.dispose();
        RegisterFrame rf = new RegisterFrame();
        RegisterControlador rc = new RegisterControlador(rf);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnLogin()) {
            verificarUsuario();
        }
    }

    //Investigar mas acerca de JTable y DefaultTableModel
    /**
     * Habia leido que en Jtable y DefaultTableModel, uno es la estructura y el
     * otro es la parte interna de la tabla.
     */
    /**
     * Para IGU que tenga tablas
     */
//    public void listar(JTable tabla) {
//        modeloTabla = (DefaultTableModel) tabla.getModel();
//        //listaUsuarios viene a ser las filas de la tabla
//        List<Usuario> listaUsuarios = usuDao.listarTodo();
//        //El object servirá para ser las columnas de la tabla
//        Object[] obj = new Object[3];
//        for (int i = 0; i < listaUsuarios.size(); i++) {
//            obj[0] = listaUsuarios.get(i).getId();
//            obj[1] = listaUsuarios.get(i).getNombreUsuario();
//            obj[2] = listaUsuarios.get(i).getContrasenia();
//            modeloTabla.addRow(obj);
//        }
//        
//    }
}
