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

    //De manera global para todos
    List<Usuario> usuarios;

    public UsuarioDaoImpl() {
    }

//no se muestra pero es tipo List<Usuario> por medio del controlador
    @Override
    public List listarTodo() {
        //Se puede llamar y empezar con el metodo al llamar a este metodo "listaTodo()"
        usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setRol(rs.getString(4));

                //Agregamos a la lista el primer registro de la tabla Usuario (de la bd)
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    //FALTA MEJORAR PARA HACER UN LOGIN 
    @Override
    public Object leerPorId(int id) {

        usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario Where id='?'";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setRol(rs.getString(4));

                //Agregamos a la lista el primer registro de la tabla Usuario (de la bd)
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar Usuario");
        }
        return null;
    }

    @Override
    public void registrar(Object registrar) {
//        usuario = (Usuario) registrar;
//
//        String sql = "INSERT INTO usuario(nombreUsuario, contrasenia, rol) values (?,?,?)";
//        /**
//         * Dentro del parámetro del try resources, se debe de mencionar y
//         * asignar el valor de las clases que implementan Autocloseable, para
//         * que al finalizar el método se cierre la conexión con la base de
//         * datos.
//         */
//        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
//            ps.setString(2, usuario.getNombreUsuario());
//            ps.setBlob(3, usuario.getContrasenia());
//            ps.setString(4, usuario.getRol());
//            int rs = ps.executeUpdate();
//
//            if (rs > 0) {
//                JOptionPane.showMessageDialog(null, "Usuario agregado con éxito");
//            } else {
//                JOptionPane.showMessageDialog(null, "No se agrego el usuario");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error al agregar Usuario");
//        }

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
        //Necesito una manera de poder traer los textos de los inputs
        String sql = "INSERT INTO usuario(nombreUsuario, contrasenia, rol) values (?,AES_ENCRYPT(?,'contrasenia'),?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, nombreUsuario);

            /**
             * <<Obtienes la contraseña del JPassword.>> la cual se obtiene a
             * traves del parametro pasra establecer una relacion
             */
//            byte[] contra = contrasenia.getBytes();
//            String scontra = new String(contra);
//            InputStream contraBlob = new ByteArrayInputStream(contra);
            /**
             * No es necesario subirlo como tipo Blob (vamos a probarlo)
             */
            ps.setString(2, contrasenia);
            ps.setString(3, rol);
            int rs = ps.executeUpdate();

            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
                //Se creará la instancia dependiendo del rol que se haya escogido
                Usuario usuario = new Usuario(rol);
            } else {
                JOptionPane.showMessageDialog(null, "No se registro el usuario");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar usuario");
            System.out.println(e.getMessage());

        }
    }

    //login 30:55
    @Override
    public boolean login(String nombreUsuario, String contraseniaa) {
        Usuario usuario = null;
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
//            byte[] contra = contrasenia.getBytes();
//            InputStream contraBlob = new ByteArrayInputStream(contra);
            ps.setString(2, contraseniaa);
//            ps.setString(3, contraseniaa);
//            ps.setString(3, contraseniaa);
//            ps.setString(3, contraseniaa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Estamos dentro");
                usuario = new Usuario();
                //Indicamos que valores debe tener el usuario dependiendo del where
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
//                usuario.setContrasenia(rs.getBlob(3));
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
