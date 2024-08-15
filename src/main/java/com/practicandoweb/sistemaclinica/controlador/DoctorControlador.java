package com.practicandoweb.sistemaclinica.controlador;

import com.practicandoweb.sistemaclinica.vista.Jframe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

public class DoctorControlador implements ActionListener {

    Jframe view;

    public DoctorControlador() {
        /**
         * Se hace así para que ni bien se cargue el controlador se pueda
         * detectar con el Jframe En el controlador se ejecuta ultimo, pero a
         * parte del porque lo instanciamos en el constructor? Es por buena
         * practica? Ya que si fuese atributo igual lo ejecutaría.
         */
    }

    public void jpInicio() {
        view.getJpInicio();
        JTabbedPane tabla = new JTabbedPane();

    }
    public void iniciar(){
        view.setTitle("Sistema Clínica");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
