package com.practicandoweb.sistemaclinica.modelo.daoclassinterface.daoimpl;

import com.practicandoweb.sistemaclinica.controlador.Conexion;
import com.practicandoweb.sistemaclinica.modelo.Usuario;
import com.practicandoweb.sistemaclinica.modelo.daoclassinterface.UsuarioDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioDaoImpl implements UsuarioDao {

    List<Usuario> usuarios;
    
    public UsuarioDaoImpl() {
    }

    @Override
    public List listarTodo() {
        usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setRol(rs.getString(4));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    @Override
    public Object leerPorId(int id) {
        return null;
    }

    @Override
    public void registrar(Object registrar) {

    }

    @Override
    public void editar(Object editar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int eliminar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void register(String nombreUsuario, String contrasenia, String rol) {
        String sql = "INSERT INTO usuario(nombreUsuario, contrasenia, rol) values (?,AES_ENCRYPT(?,'contrasenia'),?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, nombreUsuario);

            ps.setString(2, contrasenia);
            ps.setString(3, rol);
            int rs = ps.executeUpdate();

            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
                Usuario usuario = new Usuario(rol);
            } else {
                JOptionPane.showMessageDialog(null, "No se registro el usuario");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar usuario");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean login(String nombreUsuario, String contraseniaa) {
        Usuario usuario;
        StringBuilder sql = new StringBuilder();
        sql.append("Select ")
                .append(" id,")
                .append(" nombreUsuario,")
                .append(" rol")
                .append(" FROM usuario")
                .append(" where nombreUsuario=?")
                .append(" and AES_DECRYPT(contrasenia,'contrasenia')= ?");

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString());) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseniaa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Estamos dentro");
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setRol(rs.getString(3));
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
