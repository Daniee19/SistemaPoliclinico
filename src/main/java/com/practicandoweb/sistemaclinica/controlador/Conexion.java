package com.practicandoweb.sistemaclinica.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Static para llamarlo por medio de las clases
    private static Connection con;
    private static String driver;
    private static String url;
    private static String usuario;
    private static String contrasenia;
    private static String bd = "sistemapoliclinico";

    public static Connection getConnection() {
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/" + bd;
        usuario = "root";
        contrasenia = "";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, contrasenia);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Falla de conexion");
        }
        return con;
    }

    public static void cerrarConexion() throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
